module com.mycompany.flyintothesky {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;
    requires javafx.graphics;

    opens com.mycompany.flyintothesky to javafx.fxml;
    exports com.mycompany.flyintothesky;
    exports com.mycompany.pojo;
    requires javafx.graphicsEmpty;
    requires java.base;
}
