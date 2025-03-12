package com.contentfarm.utils;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class ContentFarmLocaleDateTimeUtils {
    private ContentFarmLocaleDateTimeUtils() {}

    private static final DateTimeFormatter DATE_TIME_yyyy_MM_dd_FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd");

    public static String formatTo_yyyy_MM_dd(LocalDateTime localDateTime) {
        return formatToString(localDateTime, DATE_TIME_yyyy_MM_dd_FORMATTER);
    }

    public static LocalDateTime ofNow() {
        return LocalDateTime.now();
    }

    private static String formatToString(LocalDateTime localDateTime, DateTimeFormatter dateTimeFormatter) {
        if (localDateTime == null) {
            return null;
        }
        return localDateTime.format(dateTimeFormatter);
    }
}
