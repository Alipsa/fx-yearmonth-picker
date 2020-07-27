package se.alipsa.ymp;

import javafx.scene.control.ListCell;

import java.text.DateFormatSymbols;
import java.text.SimpleDateFormat;
import java.time.YearMonth;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

public class YearMonthCell extends ListCell<YearMonth> {

  private DateTimeFormatter dateFormat;

  YearMonthCell(Locale locale, String format) {
    dateFormat = DateTimeFormatter.ofPattern(format, locale);
  }

  @Override
  protected void updateItem(YearMonth item, boolean empty) {
    super.updateItem(item, empty);
    if (empty || item == null) {
      setText(null);
    } else {
      setText(dateFormat.format(item));
    }
  }
}
