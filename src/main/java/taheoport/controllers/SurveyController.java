package taheoport.controllers;

import java.util.LinkedList;

public interface SurveyController {
    /**
     * Gets SurveyProject as list
     * @return LinkedList
     */
    LinkedList<String> getTahList();

    /**
     * Gets pickets coordinate catalog
     * @return LinkedList
     */
    LinkedList<String> getPickets();

    /**
     * Gets report to print
     * @return LinkedList
     */
    LinkedList<String> getReport();

    /**
     * process SurveyProject and sets for all pickets:
     * directions,DX, DY, DZ, X, Y, Z
     */
    void processSourceData();
}
