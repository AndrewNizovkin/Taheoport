package taheoport.service;

import java.util.Calendar;
import java.util.GregorianCalendar;

public class SecurityImpl implements Security{
    /**
     * Checks the possibility of using the application
     *
     * @return Result of the check
     */
    @Override
    public boolean pass() {
        GregorianCalendar barrier = new GregorianCalendar(2027, Calendar.JANUARY, 1);
        GregorianCalendar now = new GregorianCalendar();
//        return now.before(barrier);
        return true;
    }
}
