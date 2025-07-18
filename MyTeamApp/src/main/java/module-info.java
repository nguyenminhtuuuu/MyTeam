module com.nmt.myteamapp {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.base;
    requires java.sql;
    requires lombok;

    opens com.nmt.myteamapp to javafx.fxml;
    exports com.nmt.myteamapp;
    exports com.nmt.myteamapp.utils;
    exports com.nmt.myteamapp.utils.theme;
    exports com.nmt.myteamapp.services;
    exports com.nmt.myteamapp.pojo;
    
}
