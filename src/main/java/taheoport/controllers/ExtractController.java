package taheoport.controllers;

import java.util.LinkedList;

public interface ExtractController {

    /**
     * extracts PolList from SurveyProject
     * @return LinkedList<String>
     */
    LinkedList<String> extractPolygonProject();

    /**
     * gets report of extracting pol from SurveyProject
     * @return LinkedList<String>
     */
    LinkedList<String> getExtractReport();
}
