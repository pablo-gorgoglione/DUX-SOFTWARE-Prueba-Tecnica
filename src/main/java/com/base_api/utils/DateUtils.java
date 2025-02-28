package com.base_api.utils;

import java.time.Duration;
import java.time.Instant;
import java.util.Date;

public class DateUtils {
    public static Date addHours(Date date, int hours) {
        Instant instant = date.toInstant().plus(Duration.ofHours(hours));
        return Date.from(instant);
    }

    public static Date addDays(Date date, int days) {
        Instant instant = date.toInstant().plus(Duration.ofDays(days));
        return Date.from(instant);
    }

    public static Date addWeeks(Date date, int weeks) {
        Instant instant = date.toInstant().plus(Duration.ofDays(weeks * 7));
        return Date.from(instant);
    }

    public static Date addWeeks(int weeks) {
        Date date = new Date();
        return addWeeks(date, weeks);
    }
}
