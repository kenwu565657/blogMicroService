package com.contentfarm.utils;

import com.contentfarm.utils.exception.ContentFarmJsonProcessingException;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.List;
import java.util.logging.Logger;

import static org.junit.jupiter.api.Assertions.*;

class ContentFarmJsonUtilsTest {
    private final Logger logger = Logger.getLogger(ContentFarmJsonUtilsTest.class.getName());

    @Test
    void serializeToJsonString() {
        var testingInnerObject1 = new TestingInnerObject();
        testingInnerObject1.setString("TestString");
        testingInnerObject1.setInteger(1);
        var testingInnerObject2 = new TestingInnerObject();
        testingInnerObject2.setString("TestString2");
        testingInnerObject2.setInteger(2);
        var testingInnerObject3 = new TestingInnerObject();
        testingInnerObject3.setString("TestString3");
        testingInnerObject3.setInteger(3);
        var testingInnerObjectList = List.of(testingInnerObject1, testingInnerObject2, testingInnerObject3);

        var testingObject = new TestingObject();
        testingObject.setString("TestString");
        testingObject.setInteger(2);
        testingObject.setStringList(List.of("TestingString1", "TestingString2"));
        testingObject.setLocalDateTime(LocalDateTime.of(2025, 3, 3, 7, 11, 37));
        testingObject.setTestingInnerObject(testingInnerObject1);
        testingObject.setTestingInnerObjectList(testingInnerObjectList);

        String jsonString = ContentFarmJsonUtils.serializeToJsonString(testingObject);
        logger.info(jsonString);
        Assertions.assertEquals(getExpectedJsonString(), jsonString);
    }

    @Test
    void serializeToJsonString_emptyObject() {
        String jsonString = ContentFarmJsonUtils.serializeToJsonString(new TestingObject());
        String expectedJsonString = "{\"string\":null,\"integer\":0,\"stringList\":null,\"localDateTime\":null,\"testingInnerObject\":null,\"testingInnerObjectList\":null}";
        logger.info(jsonString);
        Assertions.assertEquals(expectedJsonString, jsonString);
    }

    @Test
    void serializeToJsonString_nullObject() {
        String jsonString = ContentFarmJsonUtils.serializeToJsonString(null);
        Assertions.assertEquals("null", jsonString);
    }

    @Test
    void deserializeFromJsonString() {
        String jsonString =
                """
                        {
                          "string": "TestString",
                          "integer": 2,
                          "stringList": [
                            "TestingString1",
                            "TestingString2"
                          ],
                          "localDateTime": [
                            2025,
                            3,
                            3,
                            7,
                            11,
                            37
                          ],
                          "testingInnerObject": {
                            "string": "TestString",
                            "integer": 1
                          },
                          "testingInnerObjectList": [
                            {
                              "string": "TestString",
                              "integer": 1
                            },
                            {
                              "string": "TestString2",
                              "integer": 2
                            },
                            {
                              "string": "TestString3",
                              "integer": 3
                            }
                          ]
                        }
        """;
        TestingObject testingObject = ContentFarmJsonUtils.deserializeFromJsonString(jsonString, TestingObject.class);
        Assertions.assertEquals("TestString", testingObject.getString());
        Assertions.assertEquals(2, testingObject.getInteger());
        Assertions.assertEquals(List.of("TestingString1", "TestingString2"), testingObject.getStringList());
        Assertions.assertEquals(LocalDateTime.of(2025, 3, 3, 7, 11, 37), testingObject.getLocalDateTime());

        Assertions.assertNotNull(testingObject.getTestingInnerObject());
        Assertions.assertEquals("TestString", testingObject.getTestingInnerObject().getString());
        Assertions.assertEquals(1, testingObject.getTestingInnerObject().getInteger());

        Assertions.assertNotNull(testingObject.getTestingInnerObjectList());
        Assertions.assertEquals(3, testingObject.getTestingInnerObjectList().size());
        Assertions.assertEquals("TestString", testingObject.getTestingInnerObjectList().getFirst().getString());
        Assertions.assertEquals(1, testingObject.getTestingInnerObjectList().getFirst().getInteger());
        Assertions.assertEquals("TestString2", testingObject.getTestingInnerObjectList().get(1).getString());
        Assertions.assertEquals(2, testingObject.getTestingInnerObjectList().get(1).getInteger());
        Assertions.assertEquals("TestString3", testingObject.getTestingInnerObjectList().get(2).getString());
        Assertions.assertEquals(3, testingObject.getTestingInnerObjectList().get(2).getInteger());
    }

    @Test
    void deserializeFromJsonString_invalidJsonString() {
        String jsonString =
                """
                        {
                          "string": "TestString",
                          "integer": 2,
                          "stringList": [
                            "TestingString1",
                            "TestingString2"
                        }
        """;
        ContentFarmJsonProcessingException contentFarmJsonProcessingException = Assertions.assertThrows(ContentFarmJsonProcessingException.class, () -> ContentFarmJsonUtils.deserializeFromJsonString(jsonString, TestingObject.class));
        Assertions.assertEquals("Content Farm JSON processing error. Message: Unexpected close marker '}': expected ']' (for Array starting at [Source: REDACTED (`StreamReadFeature.INCLUDE_SOURCE_IN_LOCATION` disabled); line: 4, column: 33])\n" +
                " at [Source: REDACTED (`StreamReadFeature.INCLUDE_SOURCE_IN_LOCATION` disabled); line: 7, column: 17] (through reference chain: com.contentfarm.utils.ContentFarmJsonUtilsTest$TestingObject[\"stringList\"]->java.util.ArrayList[2]).", contentFarmJsonProcessingException.getMessage());
    }

    @Test
    void deserializeFromJsonString_nullString() {
        ContentFarmJsonProcessingException exception = Assertions.assertThrows(ContentFarmJsonProcessingException.class, () -> ContentFarmJsonUtils.deserializeFromJsonString(null, TestingObject.class));
        Assertions.assertEquals("Content Farm JSON processing error. Message: argument \"content\" is null.", exception.getMessage());
    }

    @Test
    void deserializeFromJsonString_blankString() {
        ContentFarmJsonProcessingException contentFarmJsonProcessingException = Assertions.assertThrows(ContentFarmJsonProcessingException.class, () -> ContentFarmJsonUtils.deserializeFromJsonString("", TestingObject.class));
        Assertions.assertEquals("Content Farm JSON processing error. Message: No content to map due to end-of-input\n" +
                " at [Source: REDACTED (`StreamReadFeature.INCLUDE_SOURCE_IN_LOCATION` disabled); line: 1].", contentFarmJsonProcessingException.getMessage());

        ContentFarmJsonProcessingException contentFarmJsonProcessingException2 = Assertions.assertThrows(ContentFarmJsonProcessingException.class, () -> ContentFarmJsonUtils.deserializeFromJsonString(" ", TestingObject.class));
        Assertions.assertEquals("Content Farm JSON processing error. Message: No content to map due to end-of-input\n" +
                " at [Source: REDACTED (`StreamReadFeature.INCLUDE_SOURCE_IN_LOCATION` disabled); line: 1].", contentFarmJsonProcessingException2.getMessage());
    }

    @Test
    void deserializeFromJsonString_emptyString() {
        String jsonString = "{}";
        TestingObject testingObject = ContentFarmJsonUtils.deserializeFromJsonString(jsonString, TestingObject.class);
        Assertions.assertNull(testingObject.getString());
        Assertions.assertEquals(0, testingObject.getInteger());
        Assertions.assertNull(testingObject.getStringList());
        Assertions.assertNull(testingObject.getLocalDateTime());
        Assertions.assertNull(testingObject.getTestingInnerObject());
        Assertions.assertNull(testingObject.getTestingInnerObjectList());
    }

    private static class TestingObject {
        private String string;
        private int integer;
        private List<String> stringList;
        private LocalDateTime localDateTime;
        private TestingInnerObject testingInnerObject;
        private List<TestingInnerObject> testingInnerObjectList;

        public String getString() {
            return string;
        }

        public void setString(String string) {
            this.string = string;
        }

        public int getInteger() {
            return integer;
        }

        public void setInteger(int integer) {
            this.integer = integer;
        }

        public List<String> getStringList() {
            return stringList;
        }

        public void setStringList(List<String> stringList) {
            this.stringList = stringList;
        }

        public LocalDateTime getLocalDateTime() {
            return localDateTime;
        }

        public void setLocalDateTime(LocalDateTime localDateTime) {
            this.localDateTime = localDateTime;
        }

        public TestingInnerObject getTestingInnerObject() {
            return testingInnerObject;
        }

        public void setTestingInnerObject(TestingInnerObject testingInnerObject) {
            this.testingInnerObject = testingInnerObject;
        }

        public List<TestingInnerObject> getTestingInnerObjectList() {
            return testingInnerObjectList;
        }

        public void setTestingInnerObjectList(List<TestingInnerObject> testingInnerObjectList) {
            this.testingInnerObjectList = testingInnerObjectList;
        }
    }

    private static class TestingInnerObject {
        private String string;
        private int integer;

        public String getString() {
            return string;
        }

        public void setString(String string) {
            this.string = string;
        }

        public int getInteger() {
            return integer;
        }

        public void setInteger(int integer) {
            this.integer = integer;
        }
    }

    private String getExpectedJsonString() {
        return "{\"string\":\"TestString\",\"integer\":2,\"stringList\":[\"TestingString1\",\"TestingString2\"],\"localDateTime\":[2025,3,3,7,11,37],\"testingInnerObject\":{\"string\":\"TestString\",\"integer\":1},\"testingInnerObjectList\":[{\"string\":\"TestString\",\"integer\":1},{\"string\":\"TestString2\",\"integer\":2},{\"string\":\"TestString3\",\"integer\":3}]}";
    }
}