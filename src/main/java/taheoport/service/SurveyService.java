package taheoport.service;

import taheoport.model.Picket;
import taheoport.model.SurveyStation;
import taheoport.repository.SurveyRepository;

import java.util.List;

/**
 * This interface defines methods for working with survey project
 */
public interface SurveyService {

    /**
     * Updates basePoints
     */
    void updateBasePoints();

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
    SurveyRepository getAllStations();

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

    /**
     * Checks repository is empty
     * @return result of check
     */
    boolean isEmptyRepository();

    /**
     * Gets repository sizi
     * @return int
     */
    int sizeRepository();

    /**
     * Removes stations from repository by id
     * @param id int
     */
    void removeStation(int id);

    /**
     * Inserts station to repository by id
     * @param id int id
     * @return SurveyStation
     */
    SurveyStation insertStation(int id);

    /**
     * Inserts picket by id to station by id
     * @param stationId int station id
     * @param picketId int picket id
     */
    void insertPicket(int stationId, int picketId);

    /**
     * Removes picket by id from station by id
     * @param stationId int station index
     * @param picketId int picket index
     */
    void removePicket(int stationId, int picketId);

    /**
     * Gets picket by stationId and picketId
     * @param stationId int
     * @param picketId int
     * @return picket
     */
    Picket getPicketById(int stationId, int picketId);

    /**
     * Gets count of pickets at the survey station by id
     * @param stationId int
     * @return int
     */
    int sizePickets(int stationId);
}

