package se.alipsa.ymp;

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

import java.time.YearMonth;
import java.util.Locale;

public class YearMonthPicker extends ComboBoxBase<YearMonth> {

    private Locale locale;
    private YearMonth start;
    private YearMonth end;

    public YearMonthPicker() {
        this(YearMonth.now());
    }

    public YearMonthPicker(YearMonth initial) {
        this(initial, Locale.getDefault());
    }

    public YearMonthPicker(YearMonth initial, Locale locale) {
        this(initial.minusYears(3), initial.plusYears(3), initial, locale);
    }

    public YearMonthPicker(YearMonth from, YearMonth to, YearMonth initial, Locale locale) {
        start = from;
        end = to;
        setValue(initial);
        setLocale(locale);
        createLayout();
    }

    private void createLayout() {
        BorderPane borderPane = new BorderPane();
        this.getChildren().add(borderPane);
        HBox topBox = new HBox();
        borderPane.setTop(topBox);
        TextField inputField = new TextField();
        inputField.setTooltip(new Tooltip("yyyy-MM"));
        inputField.setPrefColumnCount(7);
        inputField.setPrefHeight(30);
        topBox.getChildren().add(inputField);
        Button pickerButton = new Button();
        pickerButton.setOnAction(a -> showHideSelectBox());
        topBox.getChildren().add(pickerButton);
        pickerButton.setGraphic(new ImageView(new Image("calendar.png", 20, 20, true, true)));
        borderPane.autosize();
    }

    private void showHideSelectBox() {
        //Alert alert = new Alert(Alert.AlertType.INFORMATION);
        //alert.setTitle("YearMonthPicker calendar control");
        //alert.setHeaderText("Not yet implemented");
        //alert.setContentText("Use YearMonthPickerCombo for now instead!");
        //alert.showAndWait();
        Dialog<YearMonth> selectBox = new Dialog<>();
        BorderPane borderPane = new BorderPane();
        selectBox.getDialogPane().getChildren().add(borderPane);
        HBox top = new HBox();
        borderPane.setTop(top);
        Button yearBackButton = new Button("<");
        Label yearLabel = new Label(String.valueOf(getValue().getYear()));
        Button yearForwardButton = new Button(">");
        top.getChildren().addAll(yearBackButton, yearLabel, yearForwardButton);
        selectBox.showAndWait();
    }


    @Override protected Skin<?> createDefaultSkin() {
        return new YearMonthPickerSkin(this);
    }

    public Locale getLocale() {
        return locale;
    }

    private void setLocale(Locale locale) {
        this.locale = locale;
    }

    public YearMonth getStart() {
        return start;
    }

    private void setStart(YearMonth start) {
        this.start = start;
    }

    public YearMonth getEnd() {
        return end;
    }

    private void setEnd(YearMonth end) {
        this.end = end;
    }
}
