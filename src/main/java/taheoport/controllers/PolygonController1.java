package taheoport.controllers;

import taheoport.gui.MainWin;
import taheoport.model.PolygonProject;
import taheoport.model.PolygonStation;
import taheoport.model.Shell;

import java.util.HashMap;
import java.util.LinkedList;

import static taheoport.model.PolygonProject.BindType.TZ;

public class PolygonController1 implements PolygonController{

    private final MainWin parentFrame;
    private final Adjuster adjuster;


    public PolygonController1(MainWin frame) {
        this.parentFrame = frame;
        adjuster = new Adjuster1(frame);
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
        String sep = " ";
        String s = "";
        LinkedList <String> llPol = new LinkedList<>();
        PolygonStation polygonStation;
        for (int i = 0; i < parentFrame.getPolygonProject().getSizePolygonStations(); i++) {
            polygonStation = parentFrame.getPolygonProject().getPolygonStation(i);
            s = polygonStation.getName() + sep +
                    polygonStation.getHor() + sep +
                    polygonStation.getLine() + sep +
                    polygonStation.getDZ() + sep;
            if (polygonStation.getStatus()) {
                s += polygonStation.getX() + sep +
                        polygonStation.getY() + sep +
                        polygonStation.getZ();
            } else {
                s += "Not" + sep +
                        "Not" + sep +
                        "Not";
            }
            llPol.add(s);
        }
        return llPol;
    }

    /**
     * Gets polygonometry adjustment report
     *
     * @return LinkedList <String>
     */
    @Override
    public LinkedList<String> getReportXY() {
        LinkedList<String> llReportXY = new LinkedList<String>();
        LinkedList<String> llTopReportXY = new Shell(parentFrame).getTopReportXY();
        HashMap<String, String> titlesReports = new Shell(parentFrame).getTitlesReports();
        String str;
        PolygonProject polygonProject = parentFrame.getPolygonProject();
        while ((str = llTopReportXY.pollFirst()) != null) {
            llReportXY.add(str);
        }

        switch (polygonProject.getBindType()) {
            case TZ, TO, TT -> {
                llReportXY.add("| " + new DataHandler(polygonProject.getPolygonStation(0).getName()).toTable(10).getStr() +
                        " |          |          |         |          |          |        |          |        | " +
                        new DataHandler(polygonProject.getPolygonStation(0).getX()).toTable(12).getStr() +
                        " | " +
                        new DataHandler(polygonProject.getPolygonStation(0).getY()).toTable(12).getStr() +
                        " |");
                llReportXY.add("|            |          |          |         |          |          |        |          |        |              |              |");
            }
            case ZT, OT, OO -> {
                llReportXY.add("| " + new DataHandler(polygonProject.getPolygonStation(0).getName()).toTable(10).getStr() +
                        " | " +
                        new DataHandler(polygonProject.getPolygonStation(0).getHor()).toTable(8).getStr() +
                        " |          | " +
                        new DataHandler(polygonProject.getPolygonStation(0).getDDHor()).format(2).toTable(7).getStr() +
                        " |          |          |        |          |        | " +
                        new DataHandler(polygonProject.getPolygonStation(0).getX()).toTable(12).getStr() +
                        " | " +
                        new DataHandler(polygonProject.getPolygonStation(0).getY()).toTable(12).getStr() +
                        " |");
                llReportXY.add("|            |          | " +
                        new DataHandler(polygonProject.getPolygonStation(0).getLine()).toTable(8).getStr() +
                        " |         | " +
                        new DataHandler(polygonProject.getPolygonStation(0).getDirection()).format(4).toTable(8).getStr() +
                        " | " +
                        new DataHandler(polygonProject.getPolygonStation(0).getDX()).format(3).toTable(8).getStr() +
                        " | " +
                        new DataHandler(polygonProject.getPolygonStation(0).getDDX()).format(3).toTable(6).getStr() +
                        " | " +
                        new DataHandler(polygonProject.getPolygonStation(0).getDY()).format(3).toTable(8).getStr() +
                        " | " +
                        new DataHandler(polygonProject.getPolygonStation(0).getDDY()).format(3).toTable(6).getStr() +
                        " |              |              |");
            }
        }
        for (int i = 1; i < polygonProject.getSizePolygonStations() -2; i++) {
            llReportXY.add("| " + new DataHandler(polygonProject.getPolygonStation(i).getName()).toTable(10).getStr() +
                    " | " +
                    new DataHandler(polygonProject.getPolygonStation(i).getHor()).toTable(8).getStr() +
                    " |          | " +
                    new DataHandler(polygonProject.getPolygonStation(i).getDDHor()).format(2).toTable(7).getStr() +
                    " |          |          |        |          |        | " +
                    new DataHandler(polygonProject.getPolygonStation(i).getX()).toTable(12).getStr() +
                    " | " +
                    new DataHandler(polygonProject.getPolygonStation(i).getY()).toTable(12).getStr() +
                    " |");
            llReportXY.add("|            |          | " +
                    new DataHandler(polygonProject.getPolygonStation(i).getLine()).toTable(8).getStr() +
                    " |         | " +
                    new DataHandler(polygonProject.getPolygonStation(i).getDirection()).format(4).toTable(8).getStr() +
                    " | " +
                    new DataHandler(polygonProject.getPolygonStation(i).getDX()).format(3).toTable(8).getStr() +
                    " | " +
                    new DataHandler(polygonProject.getPolygonStation(i).getDDX()).format(3).toTable(6).getStr() +
                    " | " +
                    new DataHandler(polygonProject.getPolygonStation(i).getDY()).format(3).toTable(8).getStr() +
                    " | " +
                    new DataHandler(polygonProject.getPolygonStation(i).getDDY()).format(3).toTable(6).getStr() +
                    " |              |              |");
        }
        llReportXY.add("| " + new DataHandler(polygonProject.getPolygonStation(polygonProject.getSizePolygonStations() - 2).getName()).toTable(10).getStr() +
                " | " +
                new DataHandler(polygonProject.getPolygonStation(polygonProject.getSizePolygonStations() - 2).getHor()).toTable(8).getStr() +
                " |          | " +
                new DataHandler(polygonProject.getPolygonStation(polygonProject.getSizePolygonStations() - 2).getDDHor()).format(2).toTable(7).getStr() +
                " |          |          |        |          |        | " +
                new DataHandler(polygonProject.getPolygonStation(polygonProject.getSizePolygonStations() - 2).getX()).toTable(12).getStr() +
                " | " +
                new DataHandler(polygonProject.getPolygonStation(polygonProject.getSizePolygonStations() - 2).getY()).toTable(12).getStr() +
                " |");

        switch (polygonProject.getBindType()) {
            case OO, TO, TZ -> llReportXY.add("|            |          | " +
                    new DataHandler(polygonProject.getPolygonStation(polygonProject.getSizePolygonStations() - 2).getLine()).toTable(8).getStr() +
                    " |         | " +
                    new DataHandler(polygonProject.getPolygonStation(polygonProject.getSizePolygonStations() - 2).getDirection()).format(4).toTable(8).getStr() +
                    " | " +
                    new DataHandler(polygonProject.getPolygonStation(polygonProject.getSizePolygonStations() - 2).getDX()).format(3).toTable(8).getStr() +
                    " | " +
                    new DataHandler(polygonProject.getPolygonStation(polygonProject.getSizePolygonStations() - 2).getDDX()).format(3).toTable(6).getStr() +
                    " | " +
                    new DataHandler(polygonProject.getPolygonStation(polygonProject.getSizePolygonStations() - 2).getDY()).format(3).toTable(8).getStr() +
                    " | " +
                    new DataHandler(polygonProject.getPolygonStation(polygonProject.getSizePolygonStations() - 2).getDDY()).format(3).toTable(6).getStr() +
                    " |              |              |");
            case TT, ZT, OT -> llReportXY.add("|            |          |          |         |          |          |        |          |        |              |              |");
        }
        llReportXY.add("| " + new DataHandler(polygonProject.getPolygonStation(polygonProject.getSizePolygonStations() - 1).getName()).toTable(10).getStr() +
                " |          |          |         |          |          |        |          |        | " +
                new DataHandler(polygonProject.getPolygonStation(polygonProject.getSizePolygonStations() - 1).getX()).toTable(12).getStr() +
                " | " +
                new DataHandler(polygonProject.getPolygonStation(polygonProject.getSizePolygonStations() - 1).getY()).toTable(12).getStr() +
                " |");
        llReportXY.add("-------------------------------------------------------------------------------------------------------------------------------");
        llReportXY.add("");
        llReportXY.add(titlesReports.get("TPfoterTitle"));
        llReportXY.add(titlesReports.get("TPperimeter") + new DataHandler(polygonProject.getPerimeter()).format(3).getStr() + titlesReports.get("TPm"));
        llReportXY.add(titlesReports.get("TPangleResidues"));
        if (polygonProject.getBindType() == PolygonProject.BindType.TT) {
            llReportXY.add(titlesReports.get("TPactual") + new DataHandler(polygonProject.getfHor()).format(2).getStr() + titlesReports.get("TPsek"));
            llReportXY.add(titlesReports.get("TPacceptable") +
                    new DataHandler(parentFrame.getSettings().getValueFHor() * Math.sqrt(polygonProject.getSizePolygonStations() - 2)).format(0).getStr() +
                    titlesReports.get("TPsek"));
        } else {
            llReportXY.add(titlesReports.get("TPactual") + "-.-");
            llReportXY.add(titlesReports.get("TPacceptable") + "-.-");

        }
        llReportXY.add(titlesReports.get("TPlineResidues"));
        if (polygonProject.getBindType() == TZ | polygonProject.getBindType() == PolygonProject.BindType.ZT) {
            llReportXY.add(titlesReports.get("TPlineDX") + "-.-");
            llReportXY.add(titlesReports.get("TPlineDY") + "-.-");
            llReportXY.add(titlesReports.get("TPabsoluteDeviation") + "-.-");
            llReportXY.add(titlesReports.get("TPactualRelativeDeviation") + "-.-");
            llReportXY.add(titlesReports.get("TPacceptableRelativeDeviation") + "-.-");
        } else {
            llReportXY.add(titlesReports.get("TPlineDX") + new DataHandler(polygonProject.getfX()).format(3).getStr() + "м.");
            llReportXY.add(titlesReports.get("TPlineDY") + new DataHandler(polygonProject.getfY()).format(3).getStr() + "м.");
            llReportXY.add(titlesReports.get("TPabsoluteDeviation") + new DataHandler(polygonProject.getfAbs()).format(3).getStr() + "м.");
            llReportXY.add(titlesReports.get("TPactualRelativeDeviation") + "1:" + polygonProject.getfOtn());
            llReportXY.add(titlesReports.get("TPacceptableRelativeDeviation") + "1:" + parentFrame.getSettings().getValueFOtn());

        }
        return llReportXY;

    }

    /**
     * Gets leveling  adjustment report
     *
     * @return LinkedList <String>
     */
    @Override
    public LinkedList<String> getReportZ() {
        PolygonProject polygonProject = parentFrame.getPolygonProject();
        double dZCorrected;
        double sumDZ = 0.0;
        double sumDDZ = 0.0;
        double sumDZCorrected = 0.0;
        int start = 0;
        int finish = polygonProject.getSizePolygonStations() - 1;
        LinkedList<String> llReportZ = new LinkedList<>();
        LinkedList<String> llTopReportZ = new Shell(parentFrame).getTopReportZ();
        HashMap<String, String> titlesReports = new Shell(parentFrame).getTitlesReports();
        String str;
        while ((str = llTopReportZ.pollFirst()) != null) {
            llReportZ.add(str);
        }

        if (polygonProject.getBindType() == PolygonProject.BindType.TT |
                polygonProject.getBindType() == PolygonProject.BindType.TO |
                polygonProject.getBindType() == PolygonProject.BindType.TZ) {
            start = 1;
        }
        if (polygonProject.getBindType() == PolygonProject.BindType.ZT |
                polygonProject.getBindType() == PolygonProject.BindType.OT |
                polygonProject.getBindType() == PolygonProject.BindType.TT) {
            finish = polygonProject.getSizePolygonStations() - 2;
        }
        for (int i = start; i < finish; i++) {
            dZCorrected = Double.parseDouble(polygonProject.getPolygonStation(i).getDZ()) +
                    polygonProject.getPolygonStation(i).getDDZ();
            sumDZCorrected += dZCorrected;
            sumDDZ += polygonProject.getPolygonStation(i).getDDZ();
            sumDZ += Double.parseDouble(polygonProject.getPolygonStation(i).getDZ());
            llReportZ.add("| " + new DataHandler(polygonProject.getPolygonStation(i).getName()).toTable(10).getStr() +
                    " |          |            |        |            | " +
                    new DataHandler(polygonProject.getPolygonStation(i).getZ()).toTable(8).getStr() + " |");
            llReportZ.add("|            | " +
                    new DataHandler(polygonProject.getPolygonStation(i).getLine()).toTable(8).getStr() +
                    " | " +
                    new DataHandler(polygonProject.getPolygonStation(i).getDZ()).toTable(10).getStr() +
                    " | " +
                    new DataHandler(polygonProject.getPolygonStation(i).getDDZ() * 1000).format(2).toTable(6).getStr() +
                    " | " +
                    new DataHandler(dZCorrected).format(3).toTable(10).getStr() +
                    " |          |"
            );
        }
        llReportZ.add("| " + new DataHandler(polygonProject.getPolygonStation(finish).getName()).toTable(10).getStr() +
                " |          |            |        |            | " +
                new DataHandler(polygonProject.getPolygonStation(finish).getZ()).toTable(8).getStr() + " |");
        llReportZ.add("|------------|----------|------------|--------|------------|----------|");
        llReportZ.add("|" + titlesReports.get("TPcontrol") +" | " +
                new DataHandler(polygonProject.getPerimeter()).format(3).toTable(8).getStr() +
                " | " +
                new DataHandler(sumDZ).format(3).toTable(10).getStr() +
                " | " +
                new DataHandler(sumDDZ * 1000).format(2).toTable(6).getStr() +
                " | " +
                new DataHandler(sumDZCorrected).format(3).toTable(10).getStr() +
                " |          |"
        );
//            llReportZ.add("|  суммы     |          |            |        |            |          |");
        llReportZ.add("-----------------------------------------------------------------------");
        llReportZ.add("");
        llReportZ.add(titlesReports.get("TPfoterTitle"));
        llReportZ.add(titlesReports.get("TPperimeter") +
                new DataHandler(polygonProject.getPerimeter()).format(3).getStr() +
                titlesReports.get("TPm"));
        if (polygonProject.getBindType() == PolygonProject.BindType.TZ |
                polygonProject.getBindType() == PolygonProject.BindType.ZT) {
            llReportZ.add(titlesReports.get("TPactualResidue") + "-.-");
            llReportZ.add(titlesReports.get("TPacceptableResidue") + "-.-");

        } else {
            llReportZ.add(titlesReports.get("TPactualResidue") +
                    new DataHandler(polygonProject.getfZ() * 1000).format(0).getStr() +
                    titlesReports.get("TPmm"));
            llReportZ.add(titlesReports.get("TPacceptableResidue") +
                    new DataHandler(parentFrame.getSettings().getValueFH() *
                            Math.sqrt(polygonProject.getPerimeter() / 1000)).format(0).getStr() +
                    titlesReports.get("TPmm"));
        }
        return llReportZ;
    }

    /**
     * Returns a list of rows in the report <Name X Y Z>
     *
     * @return LinkedList <String>
     */
    @Override
    public LinkedList<String> getReportNXYZ() {
        String sep = " ";
        LinkedList<String> llReportNXYZ = new LinkedList<>();
        PolygonStation polygonStation;
        for (int i = 0; i < parentFrame.getPolygonProject().getSizePolygonStations(); i++) {
            polygonStation = parentFrame.getPolygonProject().getPolygonStation(i);
            llReportNXYZ.add(polygonStation.getName() + sep +
                    polygonStation.getX() + sep +
                    polygonStation.getY() + sep +
                    polygonStation.getZ());
        }
        return llReportNXYZ;

    }

    /**
     * Processes the source data.
     * Adjustment the network and determines the coordinates of the defined points llTheoStations
     */
    @Override
    public void processSourceData() {
        adjuster.adjustPolygon();
    }
}
