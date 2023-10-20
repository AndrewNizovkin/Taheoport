package taheoport.controllers;

import java.util.LinkedList;

public interface SurveyController {
    /**
     * Gets SurveyProject as list
     * @return LinkedList
     */
    public LinkedList<String> getTahList();

    /**
     * Gets pickets coordinate catalog
     * @return LinkedList
     */
    public LinkedList<String> getPickets();

    /**
     * Gets report to print
     * @return LinkedList
     */
    public LinkedList<String> getReport();

    /**
     * process SurveyProject and sets for all pickets:
     * directions,DX, DY, DZ, X, Y, Z
     */
    public void processSourceData();
}
