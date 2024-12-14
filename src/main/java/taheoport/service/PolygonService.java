package taheoport.service;

import taheoport.model.PolygonStation;
import taheoport.repository.PolygonRepository;

import java.util.List;

/**
 * This interface defines methods for working with polygon project
 */
public interface PolygonService {

    /**
     * Create new polygon project
     */
    void newProject();

    /**
     * Loads polygon project from pol-file
     */
    void importPol();

    /**
     * Saves current polygon project to pol-file
     */
    void savePol();

    /**
     * Saves current polygon project to pol-file
     */
    void savePolAs();

    /**
     * Gets polygonRepository
     * @return polygonRepository
     */
    PolygonRepository getPolygonRepository();

    /**
     * Gets absolutePolPath
     * @return String
     */
    String getAbsolutePolPath();

    /**
     * Load PolygonProject from LinkedList<String> list
     * @param list list
     */
    void loadPolList(List<String> list);

    /**
     * gets list for writing to file current PolygonProject
     * @return LinkedList
     */
    List<String> getPolList();

    /**
     * Gets polygonometry adjustment report
     * @return LinkedList <String>
     */
    List<String> getReportXY ();

    /**
     * Gets leveling  adjustment report
     * @return LinkedList <String>
     */
    List<String> getReportZ ();

    /**
     * Returns a list of rows in the report <Name X Y Z>
     * @return LinkedList <String>
     */
    List<String> getReportNXYZ();

    /**
     * Processes the source data.
     * Adjustment the network and determines the coordinates of the defined points llTheoStations
     */
    void processSourceData();

    /**
     * Сhecks the possibility of inserting before idx position
     * @param idx int idx
     * @return boolean
     */
    boolean isInsertBefore(int idx);

    /**
     * Сhecks the possibility of inserting after idx position
     * @param idx int idx
     * @return boolean
     */
    boolean isInsertAfter(int idx);

    /**
     * Inserts station to repository by index
     * @param idx int
     */
    void insertStation(int idx);

    /**
     * Gets polygonStation by id from repository
     * @param idx int index
     * @return polygonStation
     */
    PolygonStation findById(int idx);

    /**
     * Gets repository size
     * @return int
     */
    int getSizePolygonStations();

    }
