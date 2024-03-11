package taheoport.controllers;

import java.util.List;

public interface SurveyController {
    /**
     * Gets SurveyProject as list
     * @return LinkedList
     */
    List<String> getTahList();

    /**
     * Gets pickets coordinate catalog
     * @return LinkedList
     */
    List<String> getPickets();

    /**
     * Gets report to print
     * @return LinkedList
     */
    List<String> getReport();

    /**
     * process SurveyProject and sets for all pickets:
     * directions,DX, DY, DZ, X, Y, Z
     */
    void processSourceData();
}
