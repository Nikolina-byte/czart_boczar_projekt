package pwr.edu.czart_boczar_projekt;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

public class Test {
    public static void main(String[] args) {
        LocalDate today = LocalDate.of(2021,5, 10);
        LocalDate nextdat = LocalDate.of(2021,5, 21);
        System.out.println(calcWeekDays1(today, nextdat)+1);
    }

    private static long calcWeekDays1(final LocalDate start, final LocalDate end) {
        final DayOfWeek startW = start.getDayOfWeek();
        final DayOfWeek endW = end.getDayOfWeek();

        final long days = ChronoUnit.DAYS.between(start, end);
        final long daysWithoutWeekends = days - 2 * ((days + startW.getValue())/7);

        //adjust for starting and ending on a Sunday:
        return daysWithoutWeekends + (startW == DayOfWeek.SUNDAY ? 1 : 0) + (endW == DayOfWeek.SUNDAY ? 1 : 0);
    }
}
