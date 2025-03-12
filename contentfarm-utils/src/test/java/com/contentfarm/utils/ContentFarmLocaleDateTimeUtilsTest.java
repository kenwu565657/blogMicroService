package com.contentfarm.utils;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.text.MessageFormat;
import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

class ContentFarmLocaleDateTimeUtilsTest {
    @Test
    void formatTo_yyyy_MM_dd() {
        LocalDateTime now = ContentFarmLocaleDateTimeUtils.ofNow();
        String formattedString = ContentFarmLocaleDateTimeUtils.formatTo_yyyy_MM_dd(now);
        String year = now.getYear() + "";
        String month = now.getMonth().getValue() > 10 ? now.getMonth().getValue() + "" : "0" + now.getMonth().getValue();
        String day = now.getDayOfMonth() > 10 ? now.getDayOfMonth() + "" : "0" + now.getDayOfMonth();
        Assertions.assertEquals(MessageFormat.format("{0}-{1}-{2}", year, month, day), formattedString);

        LocalDateTime testingDate2 = LocalDateTime.of(2025, 12, 1, 0, 0, 0);
        formattedString = ContentFarmLocaleDateTimeUtils.formatTo_yyyy_MM_dd(testingDate2);
        Assertions.assertEquals("2025-12-01", formattedString);

        LocalDateTime testingDate3 = LocalDateTime.of(2025, 3, 31, 0, 0, 0);
        formattedString = ContentFarmLocaleDateTimeUtils.formatTo_yyyy_MM_dd(testingDate3);
        Assertions.assertEquals("2025-03-31", formattedString);

        formattedString = ContentFarmLocaleDateTimeUtils.formatTo_yyyy_MM_dd(null);
        assertNull(formattedString);
    }
}