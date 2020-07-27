package se.alipsa.ymp;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.geometry.Bounds;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.stage.Popup;

import java.time.Year;
import java.time.YearMonth;
import java.util.Locale;

public class YearMonthPicker extends ComboBoxBase<YearMonth> {

    private Locale locale;
    private YearMonth startYearMonth;
    private YearMonth endYearMonth;
    private YearMonth initial;
    private TextField inputField;
    private String monthPattern;
    private Popup popup;

    public YearMonthPicker() {
        this(YearMonth.now());
    }

    public YearMonthPicker(YearMonth initial) {
        this(initial, Locale.getDefault());
    }

    public YearMonthPicker(YearMonth initial, Locale locale) {
        this(initial.minusYears(3), initial.plusYears(3), initial, locale, "MMMM");
    }

    public YearMonthPicker(YearMonth from, YearMonth to, YearMonth initial, Locale locale, String monthPattern) {
        setStart(from);
        setEnd(to);
        setInitial(initial);
        setLocale(locale);
        setMonthPattern(monthPattern);
        createLayout();
    }

    private void createLayout() {
        BorderPane borderPane = new BorderPane();
        this.getChildren().add(borderPane);
        HBox topBox = new HBox();
        borderPane.setTop(topBox);
        setValue(initial);
        inputField = new TextField(getValue().toString());
        inputField.setTooltip(new Tooltip("yyyy-MM"));
        inputField.setPrefColumnCount(7);
        inputField.setPrefHeight(30);
        inputField.setEditable(false);
        topBox.getChildren().add(inputField);
        Button pickerButton = new Button();
        pickerButton.setOnAction(this::handlePopup);
        topBox.getChildren().add(pickerButton);
        pickerButton.setGraphic(new ImageView(new Image("calendar.png", 20, 20, true, true)));
        borderPane.autosize();
    }

    private void handlePopup(ActionEvent a) {
        a.consume(); //need to consume the event, otherwise it is triggering onAction for clients
        showHideSelectBox();
    }

    private void showHideSelectBox() {
        if (popup != null) {
            popup.hide();
            popup = null;
            return;
        }
        popup = new Popup();
        BorderPane selectBox = new BorderPane();
        //selectBox.setStyle("-fx-background-color:white; -fx-border-color: derive(-fx-color,-23%)");
        selectBox.getStyleClass().add("list-view");
        popup.getContent().add(selectBox);

        final ObservableList<YearMonth> items = FXCollections.observableArrayList();
        HBox top = new HBox();
        top.setPadding(new Insets(3));
        top.setAlignment(Pos.CENTER);
        selectBox.setTop(top);
        Label yearLabel = new Label(String.valueOf(getValue().getYear()));
        yearLabel.setPadding(new Insets(0,7,0,7));
        Button yearBackButton = new Button("<");
        yearBackButton.setOnAction(e -> {
            int yearNum = Year.parse(yearLabel.getText()).minusYears(1).getValue();
            if (yearNum < startYearMonth.getYear()) return;
            yearLabel.setText(String.valueOf(yearNum));
            items.clear();
            for (int i = 1; i <= 12; i++) {
                items.add(YearMonth.of(yearNum, i));
            }
        });
        Button yearForwardButton = new Button(">");
        yearForwardButton.setOnAction(e -> {
            int yearNum = Year.parse(yearLabel.getText()).plusYears(1).getValue();
            if (yearNum > endYearMonth.getYear()) return;
            yearLabel.setText(String.valueOf(yearNum));
            items.clear();
            for (int i = 1; i <= 12; i++) {
                items.add(YearMonth.of(yearNum, i));
            }
        });
        top.getChildren().addAll(yearBackButton, yearLabel, yearForwardButton);

        for (int i = 1; i <= 12; i++) {
            items.add(YearMonth.of(Integer.parseInt(yearLabel.getText()), i));
        }
        ListView<YearMonth> listView = new ListView<>(items);
        selectBox.setCenter(listView);
        listView.setEditable(false);
        listView.setCellFactory(yearMonthListView -> new YearMonthCell(locale, monthPattern));
        listView.getSelectionModel().select(getValue());
        listView.getSelectionModel().selectedItemProperty().addListener((observableValue, oldYearMonth, newYearMonth) -> {
            if (newYearMonth == null) return;
            inputField.setText(newYearMonth.toString());
            setValue(newYearMonth);
            //System.out.println("Value selected was " + newYearMonth);
            this.fireEvent(new ActionEvent());
            popup.hide();
            popup = null;
        });

        Parent parent = getParent();
        Bounds childBounds = getBoundsInParent();
        Bounds parentBounds = parent.localToScene(parent.getBoundsInLocal());
        double layoutX = childBounds.getMinX() + parentBounds.getMinX() + parent.getScene().getX() + parent.getScene().getWindow().getX();
        double layoutY = childBounds.getMaxY() + parentBounds.getMinY() + parent.getScene().getY() + parent.getScene().getWindow().getY();
        popup.show(this, layoutX, layoutY);
        popup.requestFocus();
        popup.focusedProperty().addListener((observableValue, aBoolean, t1) -> {
            if (t1 != aBoolean) {
                popup.hide();
                popup = null;
            }
        });
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
        return startYearMonth;
    }

    private void setStart(YearMonth start) {
        this.startYearMonth = start;
    }

    public YearMonth getEnd() {
        return endYearMonth;
    }

    private void setEnd(YearMonth end) {
        this.endYearMonth = end;
    }

    public YearMonth getInitial() {
        return initial;
    }

    private void setInitial(YearMonth initial) {
        this.initial = initial;
    }

    public String getMonthPattern() {
        return monthPattern;
    }

    private void setMonthPattern(String monthPattern) {
        this.monthPattern = monthPattern;
    }
}
