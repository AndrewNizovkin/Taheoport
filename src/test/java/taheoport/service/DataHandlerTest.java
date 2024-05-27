package taheoport.service;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;

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
    void isValidName() {
    }

    @org.junit.jupiter.api.Test
    void isPositiveNumber() {
    }

    @org.junit.jupiter.api.Test
    void isNumber() {
    }

    @org.junit.jupiter.api.Test
    void zenithToVert() {
    }

    @org.junit.jupiter.api.Test
    void degToDms() {
    }

    @org.junit.jupiter.api.Test
    void dmsToDeg() {
    }

    @org.junit.jupiter.api.Test
    void removeFirstZero() {
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