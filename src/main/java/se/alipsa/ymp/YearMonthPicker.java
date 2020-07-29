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
import java.time.format.DateTimeFormatter;
import java.util.Locale;

public class YearMonthPicker extends ComboBoxBase<YearMonth> {

    private Locale locale;
    private YearMonth startYearMonth;
    private YearMonth endYearMonth;
    private YearMonth initial;
    private Label inputField;
    private String monthPattern;
    private Popup popup;
    private final DateTimeFormatter yearMonthFormatter;

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
        this(from, to, initial, locale, monthPattern, "yyyy-MM");
    }

    public YearMonthPicker(YearMonth from, YearMonth to, YearMonth initial, Locale locale, String monthPattern, String yearMonthPattern) {
        getStyleClass().add("year-month-picker");
        setStart(from);
        setEnd(to);
        setInitial(initial);
        setLocale(locale);
        setMonthPattern(monthPattern);
        yearMonthFormatter = DateTimeFormatter.ofPattern(yearMonthPattern, locale);
        createLayout();
    }

    private void createLayout() {
        HBox pane = new HBox();

        pane.setAlignment(Pos.CENTER_LEFT);
        pane.setFillHeight(true);
        this.getChildren().add(pane);
        setValue(initial);

        inputField = new Label(yearMonthFormatter.format(getValue()));
        inputField.setFocusTraversable(false);
        inputField.setPadding(new Insets(0,5,0,5));

        pane.getChildren().add(inputField);
        Button pickerButton = new Button();
        inputField.setLabelFor(pickerButton);
        pickerButton.setOnAction(this::handlePopup);
        pane.getChildren().add(pickerButton);
        pickerButton.setGraphic(new ImageView(new Image("calendar.png", 20, 20, true, true)));
        pane.autosize();
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
        selectBox.getStyleClass().addAll("list-view", "combo-box-popup");
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
                YearMonth val = YearMonth.of(yearNum, i);
                if (!val.isBefore(startYearMonth)) {
                    items.add(YearMonth.of(yearNum, i));
                }
            }
        });
        Button yearForwardButton = new Button(">");
        yearForwardButton.setOnAction(e -> {
            int yearNum = Year.parse(yearLabel.getText()).plusYears(1).getValue();
            if (yearNum > endYearMonth.getYear()) return;
            yearLabel.setText(String.valueOf(yearNum));
            items.clear();
            for (int i = 1; i <= 12; i++) {
                YearMonth val = YearMonth.of(yearNum, i);
                if (!val.isAfter(endYearMonth)) {
                    items.add(val);
                }
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
            inputField.setText(yearMonthFormatter.format(newYearMonth));
            setValue(newYearMonth);
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

    @Override
    protected Skin<?> createDefaultSkin() {
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
