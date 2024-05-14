package taheoport.service;

import java.util.List;

public interface ExtractController {

    /**
     * extracts PolList from SurveyProject
     * @return LinkedList<String>
     */
    List<String> extractPolygonProject();

    /**
     * gets report of extracting pol from SurveyProject
     * @return LinkedList<String>
     */
    List<String> getExtractReport();
}
