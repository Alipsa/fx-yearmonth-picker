# fx-yearmonth-picker
A year-month date picker component for java fx. Requires java fx 8 or higher (tested with jdk/fx 8 and 11)

There are two versions:
1. YearMonthPickerCombo - a combobox that return a YearMonth. 
2. YearMonthPicker - A custom control that resembles DatePicker where the user can click a calendar and then pick the 
desired year month.


See test.alipsa.ymp.YearMonthPickerExample for a simple example of both. Here's an example screenshot:
<img src="https://raw.githubusercontent.com/perNyfelt/fx-yearmonth-picker/master/docs/example.png" alt="Example Screenshot" width="350" height="150" />

Both works similar to a compbobox i.e. you do setOnAction() to capture the value change e.g.
```java
YearMonthPicker ymp = new YearMonthPicker();
ymp.setOnAction(a -> System.out.println("Default YearMonthPicker, Value picked was " + ymp.getValue()));
```

## se.alipsa.ymp.YearMonthPickerCombo
There are 5 constructors:

__YearMonthPickerCombo()__
This gives you YearMonths 3 year back and 3 years into the future from now.

__YearMonthPickerCombo(YearMonth initial)__
This gives you YearMonths 3 year back and 3 years into the future from initial.

__YearMonthPickerCombo(YearMonth from, YearMonth to, YearMonth initial)__
This gives you all yearmonths between from and to (both from and to included) with the
initial value as the default selected.

__YearMonthPickerCombo(YearMonth from, YearMonth to, YearMonth initial, String format)__
This gives you all yearmonths between from and to (both from and to included) with the
initial value as the default selected displayed in the format specified for the default locale.

__YearMonthPickerCombo(YearMonth from, YearMonth to, YearMonth initial, String format, Locale locale)__
This gives you all yearmonths between from and to (both from and to included) with the
initial value as the default selected displayed in the format specified for the locale specified.

## se.alipsa.ymp.YearMonthPicker
<img src="https://raw.githubusercontent.com/perNyfelt/fx-yearmonth-picker/master/docs/example2.png" alt="YearMonthPickerCombo Screenshot" width="350" />
There are 4 constructors:

__YearMonthPicker()__ This gives you YearMonths 3 year back and 3 years into the future from now in 
the system default locale.
    
__YearMonthPicker(YearMonth initial)__ 
This gives you YearMonths 3 year back and 3 years into the future from initial in 
the system default locale.

__YearMonthPicker(YearMonth initial, Locale locale)__ 
This gives you YearMonths 3 year back and 3 years into the future from initial in 
the specified locale. Month names are in full (long) format.

__YearMonthPicker(YearMonth from, YearMonth to, YearMonth initial, Locale locale, String monthNameFormat)__ 
This gives you all yearmonths between from and to (both from and to included) with the
initial value as the default selected in the locale specified. MonthNameFormat is how the
month names will be displayed in the popup. Default is "MMMM" (long format), set to "MMM" for the letter short style
or include the year with "yyyy-MMM" or whatever.
