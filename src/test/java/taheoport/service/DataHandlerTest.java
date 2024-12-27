package taheoport.service;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.junit.jupiter.api.Assertions.*;

class DataHandlerTest {

    @ParameterizedTest
    @CsvSource({"1.234, 1.2343234, 3",
            "1.235, 1.2345677, 3",
            "1.235, 1.234789, 3",
            "1, 1.4345, 0"})
    void formatTest(String expectNumber, String rawNumber, int format) {
        DataHandler dataHandler = new DataHandler(rawNumber);

        String actualString = dataHandler.format(format).getStr();

        assertEquals(expectNumber, actualString);
    }

    @Test
    void toTableTest() {
        DataHandler dataHandler = new DataHandler("5.3456");
        String expect = "  5.3456";

        String actual = dataHandler.toTable(8).getStr();

        assertEquals(expect, actual);
    }

    @Test
    void compressTest() {
        DataHandler dataHandler = new DataHandler("124    23   345");
        String expect = "124 23 345";

        String actual = dataHandler.compress(" ").getStr();

        assertEquals(expect, actual);
    }

    @Test
    void commaToPointTest() {
        DataHandler dataHandler = new DataHandler("1,234");
        String expect = "1.234";

        String actual = dataHandler.commaToPoint().getStr();

        assertEquals(expect, actual);
    }

    @ParameterizedTest
    @CsvSource({
            "name1",
            "NAME@%&*()$#@",
            "111123567089968745"
    })
    void isValidNameTrueTest(String actualName) {
        DataHandler dataHandler = new DataHandler("name1");

        boolean actualResult = dataHandler.isValidName();

        assertTrue(actualResult);
    }

    @ParameterizedTest
    @CsvSource({"name 1",
            "name/"})
    void isValidNameFalse(String string) {
        DataHandler dataHandler = new DataHandler(string);

        boolean actualResult = dataHandler.isValidName();

        assertFalse(actualResult);
    }

    @ParameterizedTest
    @CsvSource({"1.345",
            "0", "1", "1000.2332"})
    void isPositiveNumberTrue(String actualNumber) {
        DataHandler dataHandler = new DataHandler(actualNumber);

        boolean actualCheckResult = dataHandler.isPositiveNumber();

        assertTrue(actualCheckResult);
    }

    @ParameterizedTest
    @CsvSource({"-1.345", "1/", "-100"})
    void isPositiveNumberFalse(String actualNumber) {
        DataHandler dataHandler = new DataHandler(actualNumber);

        boolean actualCheckResult = dataHandler.isPositiveNumber();

        assertFalse(actualCheckResult);
    }

    @ParameterizedTest
    @CsvSource({"1.345", "0", "1", "1000.2332", "-1.345", "-1", "-1000.2332"})
    void isNumberTrueTest(String actualNumber) {
        DataHandler dataHandler = new DataHandler(actualNumber);

        boolean actualCheckResult = dataHandler.isNumber();

        assertTrue(actualCheckResult);
    }

    @ParameterizedTest
    @CsvSource({"1.345a", "sdf", "//1"})
    void isNumberTrueFalse(String actualNumber) {
        DataHandler dataHandler = new DataHandler(actualNumber);

        boolean actualCheckResult = dataHandler.isNumber();

        assertFalse(actualCheckResult);
    }

    @ParameterizedTest
    @CsvSource({
            "090.02380, -0.0238",
            "089.53090, 0.0651",
            "090.51020, -0.5102",
            "090.00000, 0.0000"
    })
    void zenithToVertTest(String zenithAngle, String vertAngle) {
        DataHandler dataHandler = new DataHandler(zenithAngle);

        String actual = dataHandler.ZenithToVert().format(4).getStr();

        assertEquals(vertAngle, actual);
    }

    @ParameterizedTest
    @CsvSource({
            "10.50861111, 10.3031",
            "0.033611111, 0.0201",
            "-0.033611111, -0.0201",
            "0, 0.0000",
            "359.9997222, 359.5959",
            "-359.9997222, -359.5959",
            "89.99972222, 89.5959",
            "180, 180.0000",
            "270.0169444, 270.0101"
    })
    void degToDmsTest(double angleOnDegree, String expectResult ) {
        DataHandler dataHandler = new DataHandler();

        String actual = dataHandler.degToDms(angleOnDegree).format(4).getStr();

        assertEquals(expectResult, actual);
    }

    @ParameterizedTest
    @CsvSource({
            "10.3031, 10.50861111",
            "0.0201, 0.033611111",
            "-0.0201, -0.033611111",
            "0.0000, 0",
            "359.5959, 359.9997222",
            "-359.5959, -359.9997222",
            "89.5959, 89.99972222",
            "180.0000, 180",
            "270.0101, 270.0169444"
    })
    void dmsToDegTest(String angleDMS, double expectResult) {
        DataHandler dataHandler = new DataHandler(angleDMS);

        double actualResult = dataHandler.dmsToDeg();

        assertEquals(expectResult, actualResult, 0.0000001);
    }

    @ParameterizedTest
    @CsvSource({
            "000010.3031, 10.3031",
            "0000.0201, 0.0201",
            "00000, 0",
            "0, 0",
            ".0201, .0201",
            "35454545000, 35454545000"
    })
    void removeFirstZeroTest(String rowString, String expectResult) {
        DataHandler dataHandler = new DataHandler(rowString);

        String actualResult = dataHandler.removeFirstZero().getStr();

        assertEquals(expectResult, actualResult);
    }

    @ParameterizedTest
    @CsvSource({
            "12344, 2, 12.344",
            "12344, 3, 123.44",
            "12344, 4, 1234.4",
            "12344, 1, 1.2344",
            "12344, 0, .12344"
    })
    void setPointPositionTest(String rawData, int pointPosition, String expectResult) {
        DataHandler dataHandler = new DataHandler(rawData);

        String actualResult = dataHandler.setPointPosition(pointPosition).getStr();

        assertEquals(expectResult, actualResult);
    }

    @ParameterizedTest
    @CsvSource({
            "0", "10000", "sdfasdf", "-2345678", "000000"
    })
    void getStrTest(String expectData) {
        DataHandler dataHandler = new DataHandler(expectData);

        String actualResult = dataHandler.getStr();

        assertEquals(expectData, actualResult);
    }

    @ParameterizedTest
    @CsvSource({
            "0, 0",
            "10000, 10000",
            "-23.45678, -23.45678"
    })
    void getDbl(String rawData, double expectResult) {
        DataHandler dataHandler = new DataHandler(rawData);

        double actualResult = dataHandler.getDbl();

        assertEquals(expectResult, actualResult, 0.0000001);
    }

    @ParameterizedTest
    @CsvSource({
            "0.0002, 0.000009696",
            "359.5959, 6.283180459",
            "90.0001, 1.570801175",
            "180.1245, 3.145301478",
            "270.1513, 4.716815329"
    })
    void dmsToRadTest(String angleDMS, double angleRadian) {
        DataHandler dataHandler = new DataHandler(angleDMS);

        double actualResult = dataHandler.dmsToRad();

        assertEquals(angleRadian, actualResult, 0.0000001);
    }

    @ParameterizedTest
    @CsvSource({
            "0.000009696, 0.0002",
            "6.283180459, 359.5959",
            "1.570801175, 90.0001",
            "3.145301478, 180.1245",
            "4.716815329, 270.1513"
    })
    void radToDmsTest(double angleRadian, String expectAngleDMS) {
        DataHandler dataHandler = new DataHandler();

        String actualResult = dataHandler.radToDms(angleRadian).getStr();

        assertEquals(expectAngleDMS, actualResult);

    }

}