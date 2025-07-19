/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package com.nmt.myteamapp;

import com.nmt.myteamapp.pojo.Choice;
import com.nmt.myteamapp.pojo.Question;
import com.nmt.myteamapp.services.exam.BaseExamService;
import com.nmt.myteamapp.services.exam.FixedExam;
import com.nmt.myteamapp.services.exam.SpecificExam;
import com.nmt.myteamapp.services.exam.exam;
import com.nmt.myteamapp.utils.MyAlert;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;

/**
 * FXML Controller class
 *
 * @author DELL
 */
public class ExamController implements Initializable {
    @FXML private TextField txtNum;
    @FXML private ComboBox<exam> cbType;
    @FXML private ListView<Question> listViewQuestions;
    private BaseExamService ex;
    private List<Question> questions = new ArrayList<>();
    private Map<String, Choice> results;
    
    public void handleStart(ActionEvent even) throws SQLException{
        if (this.cbType.getSelectionModel().getSelectedItem() == exam.FIXED){
            this.ex = new FixedExam();
        }
        else{
            this.ex = new SpecificExam(Integer.parseInt(this.txtNum.getText()));
        }
    this.results = new HashMap<>();
    this.questions = ex.getQuestions();
    this.listViewQuestions.setItems(FXCollections.observableArrayList(questions));
    
    this.listViewQuestions.setCellFactory(e -> new ListCell<Question>(){
        @Override
            protected void updateItem(Question t, boolean bln) {
                super.updateItem(t, bln);
                if(t == null || bln  == true)
                    this.setGraphic(null);
                else{
                    //Tạo VBox, 1: nội dung câu hỏi, 4: câu trả lời
                    VBox v = new VBox(5);
                    
                    //xử lý giao diện của VBox
                    v.setStyle("-fx-padding: 10; -fx-border-color: gray;"
                            + " -fx-border-width: 3");
                    
                    //Lấy nội dung câu hỏi
                    Text tx = new Text(t.getContent());
                    
                    //Thêm nội dung câu hỏi vào VBox
                    v.getChildren().add(tx);
                    
                    //Gom nhóm các Button trong 1 VBox
                    ToggleGroup g = new ToggleGroup(); 
                    for (var c : t.getChoices()) {
                        //Tạo nút Radio: hiển thị nội dụng 1 choice
                        RadioButton r = new RadioButton(c.getContent());
                        
                        //Đưa r vào nhóm ToggleGroup: chọn duy nhất 1 đáp án
                        r.setToggleGroup(g);
                        
                        //khôi phục lựa chọn khi cuộn ListView
                        if(results.get(t.getQuestionId()) == c)
                            r.setSelected(true);
                        
                        //Lưu lại Key: id câu hỏi và Value: choice được chọn vào Results
                        r.setOnAction(e -> {
                            if(r.isSelected())
                                results.put(t.getQuestionId(), c);
                        });
                        
                        //Thêm từng đáp án vào VBox
                        v.getChildren().add(r);
                    }
                    //Hiển thị giao diện VBox
                    this.setGraphic(v);
                }
            }
            
        });
    
    
    }
    
    public void handleFinish(ActionEvent event){
        int cnt = 0;
        for(var c: this.results.values()){
            if(c.isIsCorrect() == true)
                cnt++;
        }
        MyAlert.getInstance().showMsg(String.format("Bạn làm đúng %d/%d", cnt, this.questions.size()), Alert.AlertType.INFORMATION);
    }
    
    public void handleSave(ActionEvent event) throws SQLException{
        if(this.questions == null || this.questions.isEmpty()){
            MyAlert.getInstance().showMsg("Không có câu hỏi để lưu!", Alert.AlertType.INFORMATION);
        }
        else{
            Optional<ButtonType> type = MyAlert.getInstance().showMsg("Xác nhận lưu!"
                    , Alert.AlertType.CONFIRMATION);
            if(type.isPresent() && type.get().equals(ButtonType.OK)){
                this.ex.addExam(questions);
                MyAlert.getInstance().showMsg("Lưu thành công!", Alert.AlertType.CONFIRMATION);
            }
            else{
                MyAlert.getInstance().showMsg("Lưu thất bại!", Alert.AlertType.ERROR);
            }
        }
    }

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        this.cbType.setItems(FXCollections.observableArrayList(exam.values()));
    }    
    
}
