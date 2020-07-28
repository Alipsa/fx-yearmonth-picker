package se.alipsa.ymp;

import javafx.scene.control.ComboBox;
import javafx.util.StringConverter;

import java.time.YearMonth;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

public class YearMonthPickerCombo extends ComboBox<YearMonth> {

    private YearMonth value;

    public YearMonthPickerCombo() {
        this(YearMonth.now());
    }

    public YearMonthPickerCombo(YearMonth initial) {
        this(initial.minusYears(3), initial.plusYears(3), initial);
    }

    public YearMonthPickerCombo(YearMonth from, YearMonth to, YearMonth initial) {
       this(from, to, initial, Locale.getDefault());
    }

    public YearMonthPickerCombo(YearMonth from, YearMonth to, YearMonth initial, Locale locale) {
        this(from, to, initial, locale, "yyyy-MM");
    }

    public YearMonthPickerCombo(YearMonth from, YearMonth to, YearMonth initial, Locale locale, String format) {
        getStyleClass().add("year-month-picker-combo");
        setValue(initial);
        YearMonth ym = from;
        while (! ym.isAfter(to)) {
            getItems().add(ym);
            ym = ym.plusMonths(1);
        }
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(format, locale);
        setConverter(new StringConverter<YearMonth>() {
            @Override
            public String toString(YearMonth yearMonth) {
                return formatter.format(yearMonth);
            }

            @Override
            public YearMonth fromString(String val) {
                return YearMonth.parse(val, formatter);
            }
        });
    }
}
