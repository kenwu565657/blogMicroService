package com.contentfarm.utils;

import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;

import static org.junit.jupiter.api.Assertions.*;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class ContentFarmStringUtilsTest {

    @Nested
    class testIsBlank {

        @Test
        void testIsBlankNullCase() {
            assertTrue(ContentFarmStringUtils.isBlank(null));
        }

        @Test
        void testIsBlankEmptyCase() {
            assertTrue(ContentFarmStringUtils.isBlank(""));
        }

        @Test
        void testIsBlankWhitespaceCase() {
            assertTrue(ContentFarmStringUtils.isBlank(" "));
        }

        @Test
        void testIsBlankHappyCase() {
            assertFalse(ContentFarmStringUtils.isBlank("any string"));
        }

    }
}