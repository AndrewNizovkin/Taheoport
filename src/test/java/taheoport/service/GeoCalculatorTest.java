package taheoport.service;

import org.junit.jupiter.api.Test;
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
    })
    void getHorLineTest(String line, String vert, double expectResult) {

        double actualResult = GeoCalculator.getHorLine(line, vert);

        assertEquals(expectResult, actualResult, 0.000001);
    }

    @Test
    void getDX() {
    }

    @Test
    void getDY() {
    }
}