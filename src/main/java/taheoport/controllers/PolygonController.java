package taheoport.controllers;

import taheoport.model.PolygonProject;

import java.util.LinkedList;

public interface PolygonController {

    /**
     * Load PolygonProject from LinkedList<String> list
     * @param list list
     * @return PolygonProject
     */
    public PolygonProject loadPolList(LinkedList<String> list);

    /**
     * gets list for writing to file current PolygonProject
     * @return LinkedList
     */
    public LinkedList<String> getPolList();

    /**
     * Gets polygonometry adjustment report
     * @return LinkedList <String>
     */
    public LinkedList<String> getReportXY ();

    /**
     * Gets leveling  adjustment report
     * @return LinkedList <String>
     */
    public LinkedList<String> getReportZ ();

    /**
     * Returns a list of rows in the report <Name X Y Z>
     * @return LinkedList <String>
     */
    public LinkedList<String> getReportNXYZ();
}
