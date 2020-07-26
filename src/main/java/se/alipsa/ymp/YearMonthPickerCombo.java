package se.alipsa.ymp;

import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;

import java.time.YearMonth;

public class YearMonthPickerCombo extends ComboBox<YearMonth> {

    private YearMonth value;

    public YearMonthPickerCombo() {
        this(YearMonth.now());
        //createLayout();
    }

    public YearMonthPickerCombo(YearMonth initial) {
        this(initial.minusYears(3), initial.plusYears(3), initial);
    }

    public YearMonthPickerCombo(YearMonth from, YearMonth to, YearMonth initial) {
        setValue(initial);
        YearMonth ym = from;
        while (! ym.isAfter(to)) {
            getItems().add(ym);
            ym = ym.plusMonths(1);
        }
    }
}
