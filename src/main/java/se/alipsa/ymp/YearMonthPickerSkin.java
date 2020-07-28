package se.alipsa.ymp;

import javafx.scene.control.SkinBase;

public class YearMonthPickerSkin extends SkinBase<YearMonthPicker> {

  public YearMonthPickerSkin(YearMonthPicker yearMonthPicker) {
        super(yearMonthPicker);
  }

  protected double computeMinWidth(double height, double topInset, double rightInset, double bottomInset, double leftInset) {
      return 50.0D;
  }

}
