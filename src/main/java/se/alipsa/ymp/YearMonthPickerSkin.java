package se.alipsa.ymp;

import javafx.scene.control.SkinBase;

public class YearMonthPickerSkin extends SkinBase<YearMonthPicker> {

    private YearMonthPicker yearMonthPicker;

    public YearMonthPickerSkin(YearMonthPicker yearMonthPicker) {
        super(yearMonthPicker);

        this.yearMonthPicker = yearMonthPicker;
    }

    protected double computeMinWidth(double height, double topInset, double rightInset, double bottomInset, double leftInset) {
        return 50.0D;
    }

}
