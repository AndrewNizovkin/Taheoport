package taheoport.controllers;

import taheoport.gui.MainWin;
import taheoport.model.PolygonProject;
import taheoport.model.PolygonStation;

import java.util.LinkedList;

public class PolygonController1 implements PolygonController{

    private final MainWin parentFrame;
    private final Adjuster adjuster;


    public PolygonController1(MainWin frame) {
        this.parentFrame = frame;
        adjuster = new Adjuster1();
    }

    /**
     * Load PolygonProject from LinkedList<String> list
     *
     * @param list list
     * @return PolygonProject
     */
    @Override
    public PolygonProject loadPolList(LinkedList<String> list) {
        PolygonProject polygonProject = new PolygonProject(parentFrame);
        PolygonStation ts;
        String sep = " ";
        String str = "";
        String[] array;
        if (list == null) {
            return null;
        }
        parentFrame.getPolygonProject().setAbsolutePolPath(list.removeFirst());
        while (list.size() > 0) {
            str = new DataHandler(list.removeFirst()).compress(sep).getStr();
            array = str.split(sep);
            if (array.length == 7) {
                ts = new PolygonStation();
                ts.setName(array[0]);
                ts.setHor(array[1]);
                ts.setLine(array[2]);
                ts.setdZ(array[3]);
                ts.setX(array[4]);
                ts.setY(array[5]);
                ts.setZ(array[6]);
                if (new DataHandler(ts.getX()).isNumber() & new DataHandler(ts.getY()).isNumber() & new DataHandler(ts.getZ()).isNumber()) {
                    ts.setStatus(true);
                }
                polygonProject.addStation(ts);
            }

        }
        return polygonProject;
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

    /**
     * Processes the source data.
     * Adjustment the network and determines the coordinates of the defined points llTheoStations
     */
    @Override
    public void processSourceData() {

    }
}
