package com.homework.week16;

import java.time.*;
import java.util.*;
import java.util.stream.Collectors;

/**
 * 1. https://www.hackerrank.com/challenges/java-date-and-time/problem (see package hackerranck.date_and_time)
 * 2. Write a method that, for a given year, reports the length of each month within that year.
 * 3. Write a method that, for a given month of the current year, lists all of the Mondays in that month.
 * 4. Write an example that tests whether a given date occurs on Friday the 13th.
 */

public class DateAndTime {

    public Map<Month, Integer> reportLengthOfEachMonthInYear(Year year) {
        if (year == null) {
            return Collections.emptyMap();
        }
        return Arrays.stream(Month.values())
                .collect(Collectors.toMap(month -> month, month -> month.length(year.isLeap())));
    }

    public List<LocalDate> listAllMondaysOfTheMonth(Month month) {
        if (month == null) {
            return Collections.emptyList();
        }

        int daysInMonth = month.length(Year.now().isLeap());
        int year = Year.now().getValue();
        Calendar calendar = new GregorianCalendar(year, month.getValue() - 1, 1);
        List<LocalDate> mondays = new ArrayList<>();
        for (int day = 1; day <= daysInMonth; day++) {
            int dayOfWeek = calendar.get(Calendar.DAY_OF_WEEK);
            if (dayOfWeek == Calendar.MONDAY) {
                LocalDate monday = LocalDate.of(year, month, day);
                mondays.add(monday);
            }
            calendar.roll(Calendar.DAY_OF_WEEK, true);
        }
        return mondays;
    }

    public boolean isFriday13th(LocalDate date) {
        if (date == null) {
            return false;
        }
        return date.getDayOfMonth() == 13 && date.getDayOfWeek() == DayOfWeek.FRIDAY;
    }
}
