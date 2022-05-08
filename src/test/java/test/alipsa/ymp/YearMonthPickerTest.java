package test.alipsa.ymp;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import se.alipsa.ymp.YearMonthPicker;
import se.alipsa.ymp.YearMonthPickerCombo;
import org.junit.jupiter.api.Test;

import java.time.YearMonth;
import java.util.Locale;
import java.util.concurrent.CountDownLatch;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class YearMonthPickerTest extends Application {

    Stage stage;

    @Override
    public void start(Stage stage) {
        this.stage = stage;
        VBox vBox = new VBox();
        vBox.setPadding(new Insets(10));



        YearMonthPicker picker = new YearMonthPicker(YearMonth.of(2019, 1), YearMonth.of(2020, 8),
            YearMonth.of(2019,12), Locale.SIMPLIFIED_CHINESE, "MMMM", "yyyy MMMM");
        picker.setOnAction(a -> System.out.println("Chinese YearMonthPicker, Value picked was " + picker.getValue()));
        HBox hbox2 = new HBox(new Label("YearMonthPicker: "), picker);
        hbox2.setAlignment(Pos.CENTER_LEFT);
        hbox2.setPadding(new Insets(10));
        vBox.getChildren().add(hbox2);

        YearMonthPickerCombo ympc = new YearMonthPickerCombo();
        ympc.setOnAction(a -> System.out.println("YearMonthPickerCombo: value picked was " + ympc.getValue()));
        HBox cboBox = new HBox(new Label("YearMonthPickerCombo: "), ympc);
        cboBox.setAlignment(Pos.CENTER_LEFT);
        cboBox.setPadding(new Insets(10));
        vBox.getChildren().add(cboBox);

        YearMonthPickerCombo ympc2 = new YearMonthPickerCombo(YearMonth.now().minusYears(2),
                YearMonth.now().plusYears(2), YearMonth.now(), Locale.forLanguageTag("sv-SE"), "MMMM yy");
        ympc2.setOnAction(a -> System.out.println("YearMonthPickerCombo: value picked was " + ympc2.getValue()));
        HBox cboBox2 = new HBox(new Label("YearMonthPickerCombo: "), ympc2);
        cboBox2.setAlignment(Pos.CENTER_LEFT);
        cboBox2.setPadding(new Insets(10));
        vBox.getChildren().add(cboBox2);

        Scene scene = new Scene(vBox, 350, 220);
        stage.setScene(scene);
        stage.show();
        stage.toFront();
    }


    @BeforeAll
    public static void setupJavaFX() throws InterruptedException {
        final CountDownLatch latch = new CountDownLatch( 1 );

        // initializes JavaFX environment
        Platform.startup( () -> {
            /* No need to do anything here */
            System.out.println("Starting javaFX");
        } );
        latch.countDown();
        latch.await();
    }

    @AfterAll
    public static void tearDown() {
        System.out.println("Stopping javaFX");
        Platform.exit();
    }

    @Test
    public void testYearMonthPicker() {
        System.out.println("Running testYearMonthPicker");
        YearMonthPicker ymp = new YearMonthPicker();
        ymp.setValue(YearMonth.of(2022, 5));
        ymp.setOnAction(a -> ymp.setValue(ymp.getValue().plusMonths(1)));
        Event.fireEvent(ymp, new ActionEvent());
        assertEquals(YearMonth.of(2022, 6), ymp.getValue());
    }

    @Test
    public void testYearMonthPickerCombo() {
        System.out.println("Running testYearMonthPickerCombo");
        YearMonthPickerCombo ympc2 = new YearMonthPickerCombo(YearMonth.now().minusYears(2),
            YearMonth.now().plusYears(2), YearMonth.now(), Locale.forLanguageTag("sv-SE"), "MMMM yy");
        ympc2.setOnAction(a -> ympc2.setValue(ympc2.getValue().minusYears(1)));
        Event.fireEvent(ympc2, new ActionEvent());
        assertEquals(YearMonth.now().minusYears(1), ympc2.getValue());
    }
}
