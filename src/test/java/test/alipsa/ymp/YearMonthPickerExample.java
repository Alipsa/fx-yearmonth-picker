package test.alipsa.ymp;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import se.alipsa.ymp.YearMonthPicker;
import se.alipsa.ymp.YearMonthPickerCombo;

public class YearMonthPickerExample extends Application {

    @Override
    public void start(Stage stage) throws Exception {
        VBox vBox = new VBox();
        vBox.setPadding(new Insets(10));
        HBox hbox = new HBox(new Label("YearMonthPicker: "), new YearMonthPicker());
        hbox.setPadding(new Insets(10));
        vBox.getChildren().add(hbox);
        HBox cboBox = new HBox(new Label("YearMonthPickerCombo: "), new YearMonthPickerCombo());
        cboBox.setPadding(new Insets(10));
        vBox.getChildren().add(cboBox);
        Scene scene = new Scene(vBox, 350, 150);
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
