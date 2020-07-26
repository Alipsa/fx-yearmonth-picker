# fx-yearmonth-picker
A year-month date picker component for java fx. Requires java fx 8 or higher (tested with jdk/fx 8 and 11)

There are two versions:
1. YearMonthPickerCombo - a combobox that return a YearMonth. This one works fine.
2. YearMonthPicker - A custom control that resembles DatePicker where the user can click a calendar and then pick the 
desired year month. This control is still work in progress.


See test.alipsa.ymp.YearMonthPickerExample for an simple example of both. Here's an example screenshot:
<img src="https://raw.githubusercontent.com/perNyfelt/fx-yearmonth-picker/master/docs/example.png" alt="Example Screenshot" width="350" height="150" />

### se.alipsa.ymp.YearMonthPickerCombo
There are 3 constructors:

__YearMonthPickerCombo()__
This gives you YearMonths 3 year back and 3 years into the future from now.

__YearMonthPickerCombo(YearMonth initial)__
This gives you YearMonths 3 year back and 3 years into the future from initial.

__YearMonthPickerCombo(YearMonth from, YearMonth to, YearMonth initial)__
This gives you all yearmonths between from and to (both from and to included) with the
initial value as the default selected.


## se.alipsa.ymp.YearMonthPicker
There are 4 constructors:

__YearMonthPicker()__ This gives you YearMonths 3 year back and 3 years into the future from now in 
the system default locale.
    
__YearMonthPicker(YearMonth initial)__ 
This gives you YearMonths 3 year back and 3 years into the future from initial in 
the system default locale.

__YearMonthPicker(YearMonth initial, Locale locale)__ 
This gives you YearMonths 3 year back and 3 years into the future from initial in 
the specified locale.

__YearMonthPicker(YearMonth from, YearMonth to, YearMonth initial, Locale locale)__ 
This gives you all yearmonths between from and to (both from and to included) with the
initial value as the default selected in the locale specified.