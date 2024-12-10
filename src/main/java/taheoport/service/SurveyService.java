package taheoport.service;

import taheoport.model.SurveyStation;
import taheoport.repository.SurveyRepository;

import java.util.List;

/**
 * This interface defines methods for working with survey project
 */
public interface SurveyService {

    /**
     * Sets the path to file contains measurement
     * @param absoluteTahPath path to file
     */
    void setAbsoluteTahPath(String absoluteTahPath);

    /**
     * Gets path to measurement file
     * @return path to file
     */
    String getAbsoluteTahPath();

    /**
     * Sets the current surveyRepository
     * @param surveyRepository surveyRepository
     */
    void setSurveyRepository(SurveyRepository surveyRepository);

    /**
     * Gets surveyRepository
     * @return surveyRepository
     */
    SurveyRepository getSurveyRepository();

    /**
     * Checks the potential presence of a polygon in the measurements
     * @return result of check
     */
    boolean containPolygon();

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

    /**
     * Imports measurement from Lieca gsi
     */
    void importLeica();

    /**
     * Imports measurement from Nicon txt
     */
    void importNicon();

    /**
     * Imports measurement from Topcon txt
     */
    void importTopcon();

    /**
     * Imports measurement from *.tah
     */
    void importTah();

    /**
     * Create new Survey Project
     */
    void newProject();

    /**
     * Save current survey project
     */
    void saveProject();

    /**
     * Save current survey project
     */
    void saveProjectAs();

    /**
     * Gets survey station from repository by id
     * @param id
     * @return
     */
    SurveyStation findStationById(int id);
}

