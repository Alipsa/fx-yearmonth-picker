# fx-yearmonth-picker
A year-month date picker component for java fx


There are two versions:
1. YearMonthPickerCombo - a combobox that return a YearMonth. This one works fine.
2. YearMonthPicker - A custom control that resembles DatePicker where the user can click a calendar and then pick the 
desired year month. This control is still work in progress.


See test.alipsa.ymp.YearMonthPickerExample for a simple example of both. 

### YearMonthPickerCombo
There are 3 constructors:

__YearMonthPickerCombo()__
This gives you YearMonts 3 year back and 3 years into the future from now.

__YearMonthPickerCombo(YearMonth initial)__
This gives you YearMonts 3 year back and 3 years into the future from initial.

__YearMonthPickerCombo(YearMonth from, YearMonth to, YearMonth initial)__
This gives you all yearmonths between from and to (both from and to included) with the
initial value as the default selected.