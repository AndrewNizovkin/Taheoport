package taheoport.service;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import static org.junit.jupiter.api.Assertions.*;

class GeoCalculatorTest {

    @ParameterizedTest
    @CsvSource({
            "0, 0, 0, 0, 0",
            "0, 0, 100, 0, 0",
            "0, 0, 0, 100, 1.570796327",
            "0, 0, 100, 100, 0.785398163",
            "0, 0, -100, 100, 2.35619449",
            "200, 0, 100, 0, 3.141592654",
            "0, 0, -100, -100, 3.926990817",
            "0, 0, 0, -100, 4.71238898",
            "0, 0, 100, -100, 5.497787144",
            "478676.113, 2296967.264, 478682.557, 2296984.845, 1.2194702",
            "478676.113, 2296967.264, 478660.283, 2297003.862, 1.979033772",
            "478676.113, 2296967.264, 478651.137, 2296963.839, 3.277874279",
            "478676.113, 2296967.264, 478685.000, 2296963.047, 5.840134121"
    })
    void getDirABTest(String xA, String yA, String xB, String yB, double expectResult) {

        double actualResult = GeoCalculator.getDirAB(xA, yA, xB, yB);

        assertEquals(expectResult, actualResult, 0.0000001);
    }

    @ParameterizedTest
    @CsvSource({
            "0, 0.0000, 0",
            "100.315, 0.0245, 100.3149679",
            "100.315, -0.0245, 100.3149679",
            "100.315, 2.0245, 100.2510582",
            "100.315, -2.0245, 100.2510582",
            "100.315, 90.00000, 0"
    })
    void getHorLineTest(String line, String vert, double expectHorLine) {

        double actualHorLine = GeoCalculator.getHorLine(line, vert);

        assertEquals(expectHorLine, actualHorLine, 0.000001);
    }

    @ParameterizedTest
    @CsvSource({
            "0, 0, 0",
            "100.456, 0, 100.456",
            "100.456, 1.570796327, 0",
            "100.456, 3.141592654, -100.456",
            "100.456, 4.71238898, 0",
            "100.456, 0.798706298, 70.08153822",
            "100.456, 2.859135355, -96.4752752",
            "100.456, 4.496748703, -21.49486368",
            "100.456, 5.706126127, 84.18922724"
    })
    void getDXTest(double horLine, double direction, double expectDX) {

        double actualDX = GeoCalculator.getDX(horLine, direction);

        assertEquals(expectDX, actualDX, 0.000001);

    }

    @ParameterizedTest
    @CsvSource({
            "0, 0, 0",
            "100.456, 0, 0",
            "100.456, 1.570796327, 100.456",
            "100.456, 3.141592654, 0",
            "100.456, 4.71238898, -100.456",
            "100.456, 0.798706298, 71.9721191",
            "100.456, 2.859135355, 27.99873588",
            "100.456, 4.496748703, -98.1293981",
            "100.456, 5.706126127, -54.80494459"
    })
    void getDYTest(double horLine, double direction, double expectDY) {

        double actualDY = GeoCalculator.getDY(horLine, direction);

        assertEquals(expectDY, actualDY, 0.0000001);
    }
}