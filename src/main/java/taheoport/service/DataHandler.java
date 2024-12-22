package taheoport.service;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * This class provides methods for parsing, analyzing, and converting a string str
 * @author Andrew Nizovkin
 * Copyright Nizovkin A.V. 2022
 */
public class DataHandler {
    private String str;

    /**
     * Constructor
     * @param str String
     */
    public DataHandler(String str) {
        this.str = str;
    }

    /**
     * Constructor
     * @param dbl Double
     */
    public DataHandler(Double dbl) {
        this.str = String.valueOf(dbl);
    }


    public DataHandler() {
        this.str = "0";
    }

    /**
     * Formats a decimal number in string form with a
     * specified (f) number of digits after the decimal separator
     * @param f int
     * @return this
     */
    public DataHandler format(int f) {
//        if (new DataHandler(str).isNumber()) {
            BigDecimal bd = new BigDecimal(str);
            bd = bd.setScale(f, RoundingMode.HALF_EVEN);
            str = bd.toString();
//            str = String.valueOf(bd.setScale(f, RoundingMode.HALF_EVEN));
//            str = bd.setScale(f, RoundingMode.HALF_EVEN).toString();
//        }
        return this;
    }

    /**
     * Formats str to tabular format
     * @param width column width of the table
     * @return this
     */
    public DataHandler toTable(int width) {
        String to = " ".repeat(width);
        str = to + str;
        str = str.substring(str.length() - width);
        return this;
    }

    /**
     * Replaces multiple duplicate separators with one
     * @param separator String separator
     * @return this
     */
    public DataHandler compress(String separator) {
            String ss = separator + separator;
            while (str.contains(ss)) {
                str = str.replaceAll(ss, separator);
            }
        return this;
    }

    /**
     * Replaces char ',' with '.'
     * @return
     */
    public DataHandler commaToPoint() {
        while (str.contains(",")){
            str = str.replaceAll(",", ".");
        }
        return this;
    }

    /**
     * Сhecks the validity of the entered name
     * @return
     */
    public boolean isValidName() {
        if (str.isEmpty()) return false;
        return !str.contains(" ") & !str.contains("/");
    }

    /**
     * Checks the validity of the entered only positive number
     * @return boolean result
     */
    public boolean isPositiveNumber() {
        int points = 0;
        for (int i = 0; i < str.length(); i++) {
            char ch = str.charAt(i);
            if (ch == '.') {
                points ++;
                if (str.length() == 1) {
                    return false;
                }
            }
            Matcher m = Pattern.compile("[0-9/.]").matcher(String.valueOf(ch));
            if (!m.matches() | ch == '/') {
                return false;
            }

        }
        if (points > 1) {
            return false;
        }
        return !str.equals("");
    }

    /**
     * Checks the validity of the entered number
     * @return result of check
     */
    public boolean isNumber() {
        String s = str;
        if (str.startsWith("-")) {
            if (s.length() == 1) {
                return false;
            } else {
                s = str.substring(1);
            }
        }
        return new DataHandler(s).isPositiveNumber();
    }

    /**
     * Converts zenith (D.MS) to vertical angle (D.MS)
     * @return String str
     */
    public DataHandler ZenithToVert() {
        double dbl = - (dmsToDeg() - 90.0);
        degToDms(dbl);
        return this;
    }

    /**
     * Converts double (deg) to a String (d.ms)
     * @param dbl double
     * @return LineHandler this
     */
    public DataHandler degToDms(double dbl) {
        StringBuilder sb = new StringBuilder();
        BigDecimal bd = BigDecimal.valueOf(Math.abs(dbl * 3600)).setScale(0, RoundingMode.HALF_EVEN);
        int s = bd.intValue();
        int d = 0;
        int m = 0;
        int sgn = (int) Math.signum(dbl);
        while (s >= 3600) {
            s -= 3600;
            d++;
        }
        while (s >= 60) {
            s -= 60;
            m++;
        }
        if (sgn < 0) {
            sb.append('-');
        }
        sb.append(d).append('.');
        if (m <10) {
            sb.append('0').append(m);
        } else {
            sb.append(m);
        }
        if (s < 10) {
            sb.append('0').append(s);
        } else {
            sb.append(s);
        }
        str = String.valueOf(sb);
        sb = null;
        return this;
    }

    /**
     * Converts String (d.ms) to double (Degree)
     * @return Double
     */
    public double dmsToDeg() {
        double dbl = Math.signum(Double.parseDouble(str));
        if (!str.isEmpty()) {
            int i = str.indexOf('.');
            String d = str.substring(0, i);
            String m = str.substring(i+1, i+3);
            String s = str.substring(i+3, i+5);
            dbl = dbl * (Math.abs(Double.parseDouble(d)) + Double.parseDouble(m) / 60.0 +
                    Double.parseDouble(s) / 3600.0);
        }
        return dbl;
    }

    /**
     * Removes leading zeros
     * @return This
     */
    public DataHandler removeFirstZero() {
        StringBuilder s = new StringBuilder(str);
        if (s.length() > 1) {
            while (s.charAt(0) == '0') {
                if ((s.length() >= 2) & (s.charAt(1) == '.')) break;
                s.deleteCharAt(0);
                if (s.length() == 1) break;
            }
        }
        str = String.valueOf(s);
        return this;
    }

    /**
     * Sets the decimal separator to the specified position
     * @param k int the position of the decimal point
     *          from the start of the line
     * @return String modified stroka
     */
    public DataHandler setPointPosition(int k) {
        StringBuilder sb = new StringBuilder(str);
        str = String.valueOf(sb.insert(k, '.'));
        return this;
    }

    /**
     * Get String str
     * @return String str
     */
    public String getStr() {
        return str;
    }

    /**
     * Get Double str
     * @return Double str
     */
    public double getDbl() {
        return Double.parseDouble(str);
    }

    /**
     * Converts String str d.ms to Double radians
     * @return Double
     */
    public double dmsToRad() {
        return Math.toRadians(dmsToDeg());
    }

    /**
     * Convert Double angle in radians to String angle in d.ms
     * @param dbl angle in radians
     * @return this
     */
    public DataHandler radToDms(double dbl) {
        return this.degToDms(Math.toDegrees(dbl));
    }

    //Setter
//    public void setStr(String str) {
//        this.str = str;
//    }
}
