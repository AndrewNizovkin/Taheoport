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
    void format(String actual, String expect, int format) {
        DataHandler expectHandler = new DataHandler(expect);

        DataHandler actualHandler = expectHandler.format(format);

        assertEquals(actual, actualHandler.getStr());
    }

    @Test
    void toTable() {
        DataHandler dataHandler = new DataHandler("5.3456");
        String expect = "  5.3456";

        String actual = dataHandler.toTable(8).getStr();

        assertEquals(expect, actual);
    }

    @Test
    void compress() {
        DataHandler dataHandler = new DataHandler("124    23   345");
        String expect = "124 23 345";

        String actual = dataHandler.compress(" ").getStr();

        assertEquals(expect, actual);
    }

    @Test
    void commaToPoint() {
        DataHandler dataHandler = new DataHandler("1,234");
        String expect = "1.234";

        String actual = dataHandler.commaToPoint().getStr();

        assertEquals(expect, actual);
    }

    @Test
    void isValidNameTrue() {
        DataHandler dataHandler = new DataHandler("name1");

        boolean expect = dataHandler.isValidName();

        assertTrue(expect);
    }

    @ParameterizedTest
    @CsvSource({"name 1",
            "name /"})
    void isValidNameFalse(String string) {
        DataHandler dataHandler = new DataHandler(string);

        boolean expect = dataHandler.isValidName();

        assertFalse(expect);
    }

    @ParameterizedTest
    @CsvSource({"1.345",
            "0", "1", "1000.2332"})
    void isPositiveNumberTrue(String str) {
        DataHandler dataHandler = new DataHandler(str);

        boolean expect = dataHandler.isPositiveNumber();

        assertTrue(expect);
    }

    @ParameterizedTest
    @CsvSource({"-1.345", "1/", "-100"})
    void isPositiveNumberFalse(String str) {
        DataHandler dataHandler = new DataHandler(str);

        boolean expect = dataHandler.isPositiveNumber();

        assertFalse(expect);
    }

    @ParameterizedTest
    @CsvSource({"1.345", "0", "1", "1000.2332", "-1.345", "-1", "-1000.2332"})
    void isNumberTrueTest(String str) {
        DataHandler dataHandler = new DataHandler(str);

        boolean actual = dataHandler.isNumber();

        assertTrue(actual);
    }

    @ParameterizedTest
    @CsvSource({"1.345a", "sdf", "//1"})
    void isNumberTrueFalse(String str) {
        DataHandler dataHandler = new DataHandler(str);

        boolean actual = dataHandler.isNumber();

        assertFalse(actual);
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

    @org.junit.jupiter.api.Test
    void setPointPosition() {
    }

    @org.junit.jupiter.api.Test
    void getStr() {
    }

    @org.junit.jupiter.api.Test
    void getDbl() {
    }

    @org.junit.jupiter.api.Test
    void dmsToRad() {
    }

    @org.junit.jupiter.api.Test
    void radToDms() {
    }

    @org.junit.jupiter.api.Test
    void setStr() {
    }
}