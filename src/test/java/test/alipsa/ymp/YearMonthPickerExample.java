package test.alipsa.ymp;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import se.alipsa.ymp.YearMonthPicker;
import se.alipsa.ymp.YearMonthPickerCombo;

import java.time.YearMonth;
import java.util.Locale;

public class YearMonthPickerExample extends Application {

    @Override
    public void start(Stage stage) throws Exception {
        VBox vBox = new VBox();
        vBox.setPadding(new Insets(10));

        HBox hbox = new HBox(new Label("YearMonthPicker: "), new YearMonthPicker());
        hbox.setPadding(new Insets(10));
        vBox.getChildren().add(hbox);

        YearMonthPicker picker = new YearMonthPicker(YearMonth.of(2019, 1), YearMonth.of(2020, 1),
            YearMonth.of(2019,12), Locale.SIMPLIFIED_CHINESE, "MMMM");
        HBox hbox2 = new HBox(new Label("YearMonthPicker: "), picker);
        hbox2.setPadding(new Insets(10));
        vBox.getChildren().add(hbox2);


        HBox cboBox = new HBox(new Label("YearMonthPickerCombo: "), new YearMonthPickerCombo());
        cboBox.setPadding(new Insets(10));
        vBox.getChildren().add(cboBox);
        Scene scene = new Scene(vBox, 350, 200);
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
