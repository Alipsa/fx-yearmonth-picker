package test.alipsa.ymp;

import com.sun.xml.internal.ws.api.model.wsdl.WSDLOutput;
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

        YearMonthPicker ymp = new YearMonthPicker();
        ymp.setOnAction(a -> System.out.println("Default YearMonthPicker, Value picked was " + ymp.getValue()));
        HBox hbox = new HBox(new Label("YearMonthPicker: "), ymp);
        hbox.setPadding(new Insets(10));
        vBox.getChildren().add(hbox);

        YearMonthPicker picker = new YearMonthPicker(YearMonth.of(2019, 1), YearMonth.of(2020, 1),
            YearMonth.of(2019,12), Locale.SIMPLIFIED_CHINESE, "MMMM");
        picker.setOnAction(a -> System.out.println("Chinese YearMonthPicker, Value picked was " + picker.getValue()));
        HBox hbox2 = new HBox(new Label("YearMonthPicker: "), picker);
        hbox2.setPadding(new Insets(10));
        vBox.getChildren().add(hbox2);

        YearMonthPickerCombo ympc = new YearMonthPickerCombo();
        ympc.setOnAction(a -> System.out.println("YearMonthPickerCombo: value picked was " + ympc.getValue()));
        HBox cboBox = new HBox(new Label("YearMonthPickerCombo: "), ympc);
        cboBox.setPadding(new Insets(10));
        vBox.getChildren().add(cboBox);

        YearMonthPickerCombo ympc2 = new YearMonthPickerCombo(YearMonth.now().minusYears(2),
                YearMonth.now().plusYears(2), YearMonth.now(), "LLL yy", Locale.forLanguageTag("SE_sv"));
        ympc2.setOnAction(a -> System.out.println("YearMonthPickerCombo: value picked was " + ympc2.getValue()));
        HBox cboBox2 = new HBox(new Label("YearMonthPickerCombo: "), ympc2);
        cboBox2.setPadding(new Insets(10));
        vBox.getChildren().add(cboBox2);

        Scene scene = new Scene(vBox, 350, 220);
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
