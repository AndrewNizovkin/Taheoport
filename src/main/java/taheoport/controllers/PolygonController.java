package taheoport.controllers;

import taheoport.model.PolygonProject;

import java.util.LinkedList;

public interface PolygonController {

    /**
     * Load PolygonProject from LinkedList<String> list
     * @param list list
     * @return PolygonProject
     */
    PolygonProject loadPolList(LinkedList<String> list);

    /**
     * gets list for writing to file current PolygonProject
     * @return LinkedList
     */
    LinkedList<String> getPolList();

    /**
     * Gets polygonometry adjustment report
     * @return LinkedList <String>
     */
    LinkedList<String> getReportXY ();

    /**
     * Gets leveling  adjustment report
     * @return LinkedList <String>
     */
    LinkedList<String> getReportZ ();

    /**
     * Returns a list of rows in the report <Name X Y Z>
     * @return LinkedList <String>
     */
    LinkedList<String> getReportNXYZ();

    /**
     * Processes the source data.
     * Adjustment the network and determines the coordinates of the defined points llTheoStations
     */
    void processSourceData();
}
