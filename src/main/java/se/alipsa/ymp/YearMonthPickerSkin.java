package se.alipsa.ymp;

import javafx.scene.control.SkinBase;

public class YearMonthPickerSkin extends SkinBase<YearMonthPicker> {

    private YearMonthPicker datePicker;

    public YearMonthPickerSkin(YearMonthPicker datePicker) {
        super(datePicker);

        this.datePicker = datePicker;
    }

    protected double computeMinWidth(double height, double topInset, double rightInset, double bottomInset, double leftInset) {
        return 50.0D;
    }

}
