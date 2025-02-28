package com.base_api.utils;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Date;

public class DateUtilsTest {

    @Test
    public void testAddHours() {
        Date currentDate = new Date();
        Date newDate = DateUtils.addHours(currentDate, 5);

        Assertions.assertTrue(newDate.after(currentDate));
        Assertions.assertTrue(newDate.getTime() - currentDate.getTime() >= 5 * 60 * 60 * 1000L);
    }

    @Test
    public void testAddDays() {
        Date currentDate = new Date();
        Date newDate = DateUtils.addDays(currentDate, 3);

        Assertions.assertTrue(newDate.after(currentDate));
        Assertions.assertTrue(newDate.getTime() - currentDate.getTime() >= 3 * 24 * 60 * 60 * 1000L);
    }

    @Test
    public void testAddWeeks() {
        Date currentDate = new Date();
        Date newDate = DateUtils.addWeeks(currentDate, 2);

        Assertions.assertTrue(newDate.after(currentDate));
        Assertions.assertTrue(newDate.getTime() - currentDate.getTime() >= 2 * 7 * 24 * 60 * 60 * 1000L);
    }

    @Test
    public void testAddWeeksNoParams() {
        Date currentDate = new Date();
        Date newDate = DateUtils.addWeeks(2);

        Assertions.assertTrue(newDate.after(currentDate));
        Assertions.assertTrue(newDate.getTime() - currentDate.getTime() >= 2 * 7 * 24 * 60 * 60 * 1000L);
    }
}
