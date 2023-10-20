package taheoport.controllers;

import taheoport.gui.MainWin;
import taheoport.model.PolygonProject;

import java.util.LinkedList;

public class PolygonController1 implements PolygonController{

    private final MainWin parentFrame;


    public PolygonController1(MainWin frame) {
        this.parentFrame = frame;
    }

    /**
     * Load PolygonProject from LinkedList<String> list
     *
     * @param list list
     * @return PolygonProject
     */
    @Override
    public PolygonProject loadPolList(LinkedList<String> list) {
        return null;
    }

    /**
     * gets list for writing to file current PolygonProject
     *
     * @return LinkedList
     */
    @Override
    public LinkedList<String> getPolList() {
        return null;
    }

    /**
     * Gets polygonometry adjustment report
     *
     * @return LinkedList <String>
     */
    @Override
    public LinkedList<String> getReportXY() {
        return null;
    }

    /**
     * Gets leveling  adjustment report
     *
     * @return LinkedList <String>
     */
    @Override
    public LinkedList<String> getReportZ() {
        return null;
    }

    /**
     * Returns a list of rows in the report <Name X Y Z>
     *
     * @return LinkedList <String>
     */
    @Override
    public LinkedList<String> getReportNXYZ() {
        return null;
    }
}
