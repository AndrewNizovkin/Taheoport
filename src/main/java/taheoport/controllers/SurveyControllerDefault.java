package taheoport.controllers;

import taheoport.gui.MainWin;
import taheoport.model.Picket;
import taheoport.model.Shell;
import taheoport.model.SurveyStation;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.NoSuchElementException;

public class SurveyControllerDefault implements SurveyController {
    private final MainWin parentFrame;

    public SurveyControllerDefault(MainWin frame) {
        this.parentFrame = frame;
    }

    /**
     * Gets SurveyProject as list
     *
     * @return LinkedList
     */
    @Override
    public List<String> getTahList() {
        SurveyStation surveyStation;
        Picket picket;
        String sep = " ";
        List<String> list = new LinkedList<>();
        try {
            for (int i = 0; i < parentFrame.getSurveyProject().sizeStations(); i++) {
                surveyStation = parentFrame.getSurveyProject().getStation(i);
                list.add(surveyStation.getName() + sep +
                        surveyStation.getX() + sep +
                        surveyStation.getY() + sep +
                        surveyStation.getZ() + sep +
                        surveyStation.getVi() + sep +
                        surveyStation.getNameOr() + sep +
                        surveyStation.getxOr() + sep +
                        surveyStation.getyOr());
            }
            list.add("//");
            for (int i = 0; i < parentFrame.getSurveyProject().sizeStations(); i++) {
                surveyStation = parentFrame.getSurveyProject().getStation(i);
                for (int j = 0; j < surveyStation.sizePickets(); j++ ) {
                    picket = surveyStation.getPicket(j);
                    list.add(picket.getpName() + sep + picket.getLine() + sep +
                            picket.getHor() + sep + picket.getVert() + sep +
                            picket.getV() + sep + i);
                }
                list.add("//");
            }
        } catch (NoSuchElementException e) {
            System.out.println("element not found");
        }
        return list;
    }

    /**
     * Gets pickets coordinate catalog
     *
     * @return LinkedList
     */
    @Override
    public List<String> getPickets() {
        SurveyStation surveyStation;
        Picket picket;
        String sep = " ";
        LinkedList<String> list = new LinkedList<>();
        try {
            for (int i = 0; i < parentFrame.getSurveyProject().sizeStations(); i++) {
                surveyStation = parentFrame.getSurveyProject().getStation(i);
                for (int j = 0; j < surveyStation.sizePickets(); j++) {
                    picket = surveyStation.getPicket(j);
                    list.add(picket.getpName() + sep +
                            picket.getX() + sep +
                            picket.getY() + sep +
                            picket.getZ());
                }
            }
        } catch (NoSuchElementException e) {
            System.out.println("element not found");
        }
        return list;
    }

    /**
     * Gets report to print
     *
     * @return LinkedList
     */
    @Override
    public List<String> getReport() {
        HashMap<String, String> titlesReports = new Shell(parentFrame).getTitlesReports();
        Picket picket;
        SurveyStation surveyStation;
        LinkedList<String> listReport = new LinkedList<>();
        LinkedList<String> listTopReport = new Shell(parentFrame).getTopReportSurvey();
        String str;

        while ((str = listTopReport.pollFirst()) != null) {
            listReport.add(str);
        }

        for (int i = 0; i < parentFrame.getSurveyProject().sizeStations(); i++) {
            surveyStation = parentFrame.getSurveyProject().getStation(i);
            listReport.add("                                   " + titlesReports.get("SPstation") +
                    surveyStation.getName() +
                    "       " + titlesReports.get("SPorientir") +
                    surveyStation.getNameOr() +
                    "      " + titlesReports.get("SPtoolHeight") +
                    surveyStation.getVi());
            listReport.add("-----------------------------------------------------------------------------------------------------------------------------------------------");
            listReport.add("| " +
                    new DataHandler(surveyStation.getName()).toTable(10).getStr() +
                    " |          |          |         |          |          |          |          |           | " +
                    new DataHandler(surveyStation.getX()).toTable(11).getStr() + " | " +
                    new DataHandler(surveyStation.getY()).toTable(11).getStr() + " | " +
                    new DataHandler(surveyStation.getZ()).toTable(10).getStr() + " |");
            listReport.add("| " +
                    new DataHandler(surveyStation.getNameOr()).toTable(10).getStr() +
                    " |          |          |         |          |          |          |          |           | " +
                    new DataHandler(surveyStation.getxOr()).toTable(11).getStr() + " | " +
                    new DataHandler(surveyStation.getyOr()).toTable(11).getStr() + " |            |");

            for (int j = 0; j < surveyStation.sizePickets(); j++) {
                picket = surveyStation.getPicket(j);
                listReport.add("| " +
                        new DataHandler(picket.getpName()).toTable(10).getStr() + " | " +
                        new DataHandler(picket.getLine()).toTable(8).getStr() + " | " +
                        new DataHandler(picket.getHor()).toTable(8).getStr() + " | " +
                        new DataHandler(picket.getVert()).toTable(7).getStr() + " | " +
                        new DataHandler(picket.getV()).toTable(8).getStr() + " | " +
                        new DataHandler(picket.getPDirection()).toTable(8).getStr() + " | " +
                        new DataHandler(picket.getDX()).toTable(8).getStr() + " | " +
                        new DataHandler(picket.getDY()).toTable(8).getStr() + " | " +
                        new DataHandler(picket.getDZ()).toTable(9).getStr() + " | " +
                        new DataHandler(picket.getX()).toTable(11).getStr() + " | " +
                        new DataHandler(picket.getY()).toTable(11).getStr() + " | " +
                        new DataHandler(picket.getZ()).toTable(10).getStr() + " |");

            }
            listReport.add("-----------------------------------------------------------------------------------------------------------------------------------------------");
        }
        return listReport;
    }

    /**
     * process SurveyProject and sets for all pickets:
     * directions,DX, DY, DZ, X, Y, Z
     */
    @Override
    public void processSourceData() {
        GeoCalc geoCalc = new GeoCalc();
        SurveyStation llStation;
        double dirBase;
        double dirPicket;
        double horLine;
        double dX;
        double dY;
        double dZ;
        Picket picket;
//        for (SurveyStation llStation : surveyStations) {

        for (int i = 0; i < parentFrame.getSurveyProject().sizeStations(); i++) {
            llStation = parentFrame.getSurveyProject().getStation(i);
            dirBase = geoCalc.getDirAB(
                    llStation.getX(),
                    llStation.getY(),
                    llStation.getxOr(),
                    llStation.getyOr());
            for (int j = 0; j < llStation.sizePickets(); j++) {
                picket = llStation.getPicket(j);


                if (parentFrame.getSettings().getOrientStation() == 1) {
                    dirPicket = dirBase + new DataHandler(picket.getHor()).dmsToRad() -
                            new DataHandler(llStation.getPicket(0).getHor()).dmsToRad();
                    while (dirPicket < 0) {
                        dirPicket += 2 * Math.PI;
                    }
                } else {
                    dirPicket = dirBase + new DataHandler(picket.getHor()).dmsToRad();
                }


                while (dirPicket > 2 * Math.PI) {
                    dirPicket -= 2 * Math.PI;
                }
                horLine = geoCalc.getHorLine(picket.getLine(), picket.getVert());
                dX = geoCalc.getDX(horLine, dirPicket);
                dY = geoCalc.getDY(horLine, dirPicket);
                dZ = Double.parseDouble(llStation.getVi()) -
                        Double.parseDouble(llStation.getPicket(j).getV()) +
                        Double.parseDouble(picket.getLine()) * Math.sin(new DataHandler(picket.getVert()).dmsToRad());
                picket.setDirection(dirPicket);
                picket.setHorLine(horLine);
                picket.setdX(dX);
                picket.setdY(dY);
                picket.setdZ(dZ);
                picket.setX(Double.parseDouble(llStation.getX()) + dX);
                picket.setY(Double.parseDouble(llStation.getY()) + dY);
                picket.setZ(Double.parseDouble(llStation.getZ()) + dZ);
            }
        }
    }
}
