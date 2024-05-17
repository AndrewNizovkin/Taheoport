package taheoport.service;

import java.util.List;

/**
 * This interface defines methods for extracting the polygon from survey journal
 */
public interface ExtractService {

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
