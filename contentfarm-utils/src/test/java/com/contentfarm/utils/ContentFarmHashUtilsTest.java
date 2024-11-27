package com.contentfarm.utils;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;

import java.text.MessageFormat;
import java.util.logging.Logger;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class ContentFarmHashUtilsTest {
    Logger logger = Logger.getLogger(ContentFarmHashUtilsTest.class.getName());

    @Nested
    class testHash {
        @Test
        void happyCase() {
            var password = "password";
            var hashedPassword1 = ContentFarmHashUtils.hash(password);
            logger.info(MessageFormat.format("hashedPassword: {0}", hashedPassword1));
            var hashedPassword2 = ContentFarmHashUtils.hash(password);
            logger.info(MessageFormat.format("hashedPassword: {0}", hashedPassword2));
        }
    }

    @Nested
    class testMatch {
        private final String password = "password";
        private final String wrongPassword = "wrongPassword";
        private final String blankPassword = "";
        private final String nullPassword = null;

        @Test
        void happyCase() {
            var hashedPassword1 = ContentFarmHashUtils.hash(password);
            var hashedPassword2 = ContentFarmHashUtils.hash(password);

            Assertions.assertNotEquals(hashedPassword1, hashedPassword2);
            Assertions.assertTrue(ContentFarmHashUtils.match(password, hashedPassword1));
            Assertions.assertTrue(ContentFarmHashUtils.match(password, hashedPassword2));
        }

        @Test
        void wrongPasswordCase() {
            var hashedPassword1 = ContentFarmHashUtils.hash(password);
            var hashedPassword2 = ContentFarmHashUtils.hash(password);

            Assertions.assertNotEquals(hashedPassword1, hashedPassword2);
            Assertions.assertFalse(ContentFarmHashUtils.match(wrongPassword, hashedPassword1));
            Assertions.assertFalse(ContentFarmHashUtils.match(wrongPassword, hashedPassword2));
        }

        @Test
        void blankPasswordCase() {
            var hashedPassword1 = ContentFarmHashUtils.hash(password);
            var hashedPassword2 = ContentFarmHashUtils.hash(password);

            Assertions.assertNotEquals(hashedPassword1, hashedPassword2);
            Assertions.assertFalse(ContentFarmHashUtils.match(blankPassword, hashedPassword1));
            Assertions.assertFalse(ContentFarmHashUtils.match(blankPassword, hashedPassword2));
        }

        @Test
        void nullPasswordCase() {
            var hashedPassword1 = ContentFarmHashUtils.hash(password);
            var hashedPassword2 = ContentFarmHashUtils.hash(password);

            Assertions.assertNotEquals(hashedPassword1, hashedPassword2);
            Assertions.assertFalse(ContentFarmHashUtils.match(nullPassword, hashedPassword1));
            Assertions.assertFalse(ContentFarmHashUtils.match(nullPassword, hashedPassword2));
        }
    }


}