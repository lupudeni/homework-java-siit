package com.homework.week16;

import org.junit.Before;
import org.junit.Test;

import java.time.LocalDate;
import java.time.Month;
import java.time.Year;
import java.util.List;
import java.util.Map;

import static java.time.Month.*;
import static org.assertj.core.api.Assertions.*;

public class DateAndTimeTest {

    private DateAndTime sut;

    @Before
    public void setUp() {
        sut = new DateAndTime();
    }

    @Test
    public void given_year_when_report_length_then_return_length_of_each_month() {
        //given
        Year year = Year.parse("2020");

        //when
        Map<Month, Integer> monthIntegerMap = sut.reportLengthOfEachMonthInYear(year);

        //then
        assertThat(monthIntegerMap).containsOnly(
                entry(JULY, 31),
                entry(JUNE, 30),
                entry(JANUARY, 31),
                entry(DECEMBER, 31),
                entry(MARCH, 31),
                entry(MAY, 31),
                entry(NOVEMBER, 30),
                entry(FEBRUARY, 29),
                entry(AUGUST, 31),
                entry(SEPTEMBER, 30),
                entry(APRIL, 30),
                entry(OCTOBER, 31));
    }

    @Test
    public void given_null_when_report_length_then_return_empty_map() {
        //when
        Map<Month, Integer> monthIntegerMap = sut.reportLengthOfEachMonthInYear(null);

        //then
        assertThat(monthIntegerMap).isEmpty();
    }

    @Test
    public void given_month_when_list_all_mondays_then_return_list_of_LocalDate_of_Mondays() {
        //when
        List<LocalDate> mondays = sut.listAllMondaysOfTheMonth(JUNE);

        //then
        assertThat(mondays).containsExactlyInAnyOrder(LocalDate.of(2020, 6, 1),
                LocalDate.of(2020, 6, 8),
                LocalDate.of(2020, 6, 15),
                LocalDate.of(2020, 6, 22),
                LocalDate.of(2020, 6, 29));
    }

    @Test
    public void given_null_when_list_all_mondays_then_return_empty_list() {
        //when
        List<LocalDate> mondays = sut.listAllMondaysOfTheMonth(null);

        //then
        assertThat(mondays).isEmpty();
    }

    @Test
    public void given_date_of_friday_13_when_testing_if_friday_13th_then_return_true() {
        //given
        LocalDate date = LocalDate.of(2020, 3, 13);

        //when
        boolean status = sut.isFriday13th(date);

        //then
        assertThat(status).isTrue();
    }

    @Test
    public void given_other_date_when_testing_if_friday_13th_then_return_false() {
        //given
        LocalDate date = LocalDate.of(2020, 4, 13);

        //when
        boolean status = sut.isFriday13th(date);

        //then
        assertThat(status).isFalse();
    }

    @Test
    public void given_null_when_testing_if_friday_13th_then_return_false() {
        //when
        boolean status = sut.isFriday13th(null);

        //then
        assertThat(status).isFalse();
    }

}
