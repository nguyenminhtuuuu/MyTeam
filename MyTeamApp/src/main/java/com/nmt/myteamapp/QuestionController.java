/*s
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package com.nmt.myteamapp;

import com.nmt.myteamapp.pojo.Category;
import com.nmt.myteamapp.pojo.Choice;
import com.nmt.myteamapp.pojo.Question;
import com.nmt.myteamapp.services.CategoryServices;
import com.nmt.myteamapp.services.FlyweightFactory;
import com.nmt.myteamapp.services.updateQuestion;
import com.nmt.myteamapp.utils.MyAlert;
import com.nmt.myteamapp.utils.theme.theme;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.UUID;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

/**
 * FXML Controller class
 *
 * @author DELL
 */
public class QuestionController implements Initializable {
    @FXML private TextField txtQuestion;
    @FXML private TextField txtA;
    @FXML private TextField txtB;
    @FXML private TextField txtC;
    @FXML private TextField txtD;
    @FXML private RadioButton  rdoA;
    @FXML private RadioButton  rdoB;
    @FXML private RadioButton  rdoC;
    @FXML private RadioButton  rdoD;
    @FXML private ComboBox<Category> cbCates;
    @FXML private TableView tbQuestion;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {
            
            //nap cau hoi len tableview
            this.loadQuestion();
            //nap category len
            //this.cbCates.getItems().addAll(new CategoryServices().list());
            this.cbCates.setItems(FXCollections
                    .observableArrayList(FlyweightFactory.getData(new CategoryServices(), "categories")));
            //tim kiem
            this.txtQuestion.textProperty().addListener((e) -> {
                try {
                    this.tbQuestion.setItems(FXCollections
                            .observableList(updateQuestion.getQuestion(txtQuestion.getText())));
                } catch (SQLException ex) {
                    Logger.getLogger(QuestionController.class.getName()).log(Level.SEVERE, null, ex);
                }
            });
            this.cbCates.getSelectionModel().selectedItemProperty().addListener((e) ->{
                try {
                    this.tbQuestion.setItems(FXCollections
                            .observableArrayList(updateQuestion
                                    .getQuestion(this.cbCates.getSelectionModel().getSelectedItem())));
                } catch (SQLException ex) {
                    Logger.getLogger(QuestionController.class.getName()).log(Level.SEVERE, null, ex);
                }
            });
            
        } catch (SQLException ex) {
            Logger.getLogger(QuestionController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }    
    
    public void addQuestion(ActionEvent event) throws SQLException{
        //sinh questionId ngau nhien
        String questionId = UUID.randomUUID().toString();
        
        //tao Builder cho cau hoi
        Question.Builder b = new Question.Builder(questionId,txtQuestion.getText()
                , this.cbCates.getSelectionModel().getSelectedItem());
        
        //Lay cau tra loi
        List<Choice> choices = new ArrayList<>();
        b.addChoice(new Choice(UUID.randomUUID().toString(), txtA.getText(), rdoA.isSelected()
                , questionId));
        b.addChoice(new Choice(UUID.randomUUID().toString(), txtB.getText(), rdoB.isSelected()
                , questionId));
        b.addChoice(new Choice(UUID.randomUUID().toString(), txtC.getText(), rdoC.isSelected()
                , questionId));
        b.addChoice(new Choice(UUID.randomUUID().toString(), txtD.getText(), rdoD.isSelected()
                , questionId));

        //tao cau hoi
        Question q = b.build();
        
        try{
        updateQuestion.addQuestion(q);
            MyAlert.getInstance().showMsg("Thêm câu hỏi thành công!", Alert.AlertType.INFORMATION);
        }catch(SQLException ex){
             MyAlert.getInstance().showMsg("Thêm câu hỏi thất bại!", Alert.AlertType.INFORMATION);
        }
        
        
    }
    
    public void loadQuestion() throws SQLException{
        this.tbQuestion.setItems(FXCollections
                    .observableList(updateQuestion.getQuestion("")));
        
        TableColumn colContent = new TableColumn("Content");
        colContent.setCellValueFactory(new PropertyValueFactory("content"));
        
        TableColumn colChoices = new TableColumn("Choices");
        colChoices.setCellValueFactory(new PropertyValueFactory("choices"));
        
        TableColumn colAction = new TableColumn();
        colAction.setCellFactory((e) -> {
            TableCell cell = new TableCell();
            
            Button btn = new Button("Xoá");
            btn.setOnAction((event) -> {
                Optional<ButtonType> type = 
                        MyAlert.getInstance().showMsg("Xác nhận xóa!", Alert.AlertType.CONFIRMATION);
                if(type.isPresent() && type.get().equals(ButtonType.OK)){
                    TableCell cl = (TableCell) ((Button)event.getSource()).getParent();
                    Question q = (Question) cl.getTableRow().getItem();
                    
                    try {
                        updateQuestion.deleteQuestion(q.getQuestionId());
                        this.tbQuestion.getItems().clear();
                        this.tbQuestion.setItems(FXCollections.observableList(updateQuestion.getQuestion("")));
                        MyAlert.getInstance().showMsg("Xóa thành công!", Alert.AlertType.INFORMATION);
                    } catch (SQLException ex) {
                        MyAlert.getInstance().showMsg("Xóa thất bại!" + ex.getMessage(), Alert.AlertType.INFORMATION);
                    }
                }
            });
             cell.setGraphic(btn);
             return cell;
        });
        this.tbQuestion.getColumns().addAll(colContent, colChoices,colAction);
        
        
    }
}

