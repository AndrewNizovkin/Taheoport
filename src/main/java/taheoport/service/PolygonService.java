package taheoport.service;

import taheoport.model.PolygonProject;

import java.util.List;

/**
 * This interface defines methods for working with polygon project
 */
public interface PolygonService {

    /**
     * Load PolygonProject from LinkedList<String> list
     * @param list list
     * @return PolygonProject
     */
    PolygonProject loadPolList(List<String> list);

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
}
