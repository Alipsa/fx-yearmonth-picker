package se.alipsa.ymp;

import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;

import java.time.YearMonth;

public class YearMonthPicker extends ComboBoxBase<YearMonth> {

    public YearMonthPicker() {
        createLayout();
    }

    public YearMonthPicker(YearMonth initial) {
        this();
        setValue(initial);
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
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("YearMonthPicker calendar control");
        alert.setHeaderText("Not yet implemented");
        alert.setContentText("Use YearMonthPickerCombo for now instead!");
        alert.showAndWait();
    }


    @Override protected Skin<?> createDefaultSkin() {
        return new YearMonthPickerSkin(this);
    }
}
