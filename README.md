# fx-yearmonth-picker
[![Maven Central](https://maven-badges.herokuapp.com/maven-central/se.alipsa/fx-yearmonth-picker/badge.svg)](https://maven-badges.herokuapp.com/maven-central/se.alipsa/fx-yearmonth-picker)
[![javadoc](https://javadoc.io/badge2/se.alipsa/fx-yearmonth-picker/javadoc.svg)](https://javadoc.io/doc/se.alipsa/fx-yearmonth-picker)

A year-month date picker component for java fx. Requires java 11 and javafx 11 or higher (tested with jdk 11 and jfx 17.02)

There are two versions:
1. YearMonthPickerCombo - a combobox that return a YearMonth. 
2. YearMonthPicker - A custom control that resembles DatePicker where the user can click a calendar and then pick the 
desired year month.


See test.alipsa.ymp.YearMonthPickerExample for a simple example of both. Here's an example screenshot:
<img src="https://raw.githubusercontent.com/perNyfelt/fx-yearmonth-picker/master/docs/example.png" alt="Example Screenshot" width="350" height="150" />

Both works similar to a combo box i.e. you do getValue() to get the value and
setOnAction() to capture a value change e.g.
```java
YearMonthPicker ymp = new YearMonthPicker();
ymp.setOnAction(a -> System.out.println("Default YearMonthPicker, Value picked was " + ymp.getValue()));
```

There are no external dependencies, and the jar file is about 40kb so nice and small. You can download the jar file from 
the release section or add the following to your maven file:
```xml
<dependency>
  <groupId>se.alipsa</groupId>
  <artifactId>fx-yearmonth-picker</artifactId>
  <version>1.1.0</version>
</dependency>
```
...or the equivalent to you favorite build tool.

Note that the dependency on java fx is set to "provided" meaning that you need to add
a dependency to javafx explicitly in your project or use a jvm where javafx is bundled 
(such as Bellsofts liberica full)

The module name for this library is `se.alipsa.ymp`

## se.alipsa.ymp.YearMonthPickerCombo
There are 5 constructors:

__YearMonthPickerCombo()__
This gives you YearMonths 3 year back and 3 years into the future from now.

__YearMonthPickerCombo(YearMonth initial)__
This gives you YearMonths 3 year back and 3 years into the future from initial.

__YearMonthPickerCombo(YearMonth from, YearMonth to, YearMonth initial)__
This gives you all yearmonths between from and to (both from and to included) with the
initial value as the default selected.

__YearMonthPickerCombo(YearMonth from, YearMonth to, YearMonth initial, Locale locale)__
This gives you all yearmonths between from and to (both from and to included) with the
initial value as the default selected displayed in the locale specified formatted as "yyyy-MM".

__YearMonthPickerCombo(YearMonth from, YearMonth to, YearMonth initial, Locale locale, String format)__
This gives you all yearmonths between from and to (both from and to included) with the
initial value as the default selected, displayed in the locale specified in the format specified.

## se.alipsa.ymp.YearMonthPicker
<img src="https://raw.githubusercontent.com/perNyfelt/fx-yearmonth-picker/master/docs/example2.png" alt="YearMonthPickerCombo Screenshot" width="350" />
There are 5 constructors:

__YearMonthPicker()__ This gives you YearMonths 3 year back and 3 years into the future from now in 
the system default locale.
    
__YearMonthPicker(YearMonth initial)__ 
This gives you YearMonths 3 year back and 3 years into the future from initial in 
the system default locale.

__YearMonthPicker(YearMonth initial, Locale locale)__ 
This gives you YearMonths 3 year back and 3 years into the future from initial in 
the specified locale. Month names are in full (long) format.

__YearMonthPicker(YearMonth from, YearMonth to, YearMonth initial)__
This gives you all yearmonths between from and to (both from and to included) with the
initial value as the default selected in the default locale. 
The display value (when a year month is chosen) will be displayed in the
format "yyyy-MM".

__YearMonthPicker(YearMonth from, YearMonth to, YearMonth initial, String monthNameFormat)__
This gives you all yearmonths between from and to (both from and to included) with the
initial value as the default selected in the default locale. MonthNameFormat is how the
month names will be displayed in the popup. Set it to "MMM" for the letter short style
or include the year with "yyyy-MMM" or whatever. 
The display value (when a year month is chosen) will be displayed in the
format "yyyy-MM".

__YearMonthPicker(YearMonth from, YearMonth to, YearMonth initial, Locale locale, String monthNameFormat)__ 
This gives you all yearmonths between from and to (both from and to included) with the
initial value as the default selected in the locale specified. MonthNameFormat is how the
month names will be displayed in the popup. Default is "MMMM" (long format), set to "MMM" for the letter short style
or include the year with "yyyy-MMM" or whatever. The display value (when a year month is chosen) will be displayed in the 
format "yyyy-MM".

__YearMonthPicker(YearMonth from, YearMonth to, YearMonth initial, Locale locale, String monthPattern, String yearMonthPattern)__
This gives you all yearmonths between from and to (both from and to included) with the
initial value as the default selected in the locale specified. MonthNameFormat is how the
month names will be displayed in the popup. Default is "MMMM" (long format), set to "MMM" for the letter short style
or include the year with "yyyy-MMM" or whatever. The display value (when a year month is chosen) will be displayed in the 
format provided with the yearMonthPattern argument.

## Customization
The YearMonthPicker has a style class called "year-month-picker", and the YearMonthPickerCombo a style class called
"year-month-picker-combo" which can be used to specify specific css styling for the components. 

Both components are styled like a default combobox.
