package taheoport.model;

import taheoport.controllers.DataHandler;
import taheoport.controllers.GeoCalc;
import taheoport.gui.MainWin;

import java.util.*;

/**
 * This class represents a total station survey model.
 * It provides various methods for importing field information
 * from various types of storage devices (total stations).
 * Version information
 * @version 1.0
 * Date
 * @author Andrew Nizovkin
 * Copyright Nizovkin A.V. 2022
 */
public class SurveyProject {
    private String absoluteTahPath;
    private final LinkedList <SurveyStation> surveyStations;
    private final MainWin parentFrame;

    /**
     * Constructor
     * @param parentFrame MainWin
     */
    public SurveyProject(MainWin parentFrame) {
        this.parentFrame = parentFrame;
        absoluteTahPath = "";
        surveyStations = new LinkedList <>();
    }

    /**
     * Returns Tah as LinkedList
     * @return Linkedlist
     */
    public   LinkedList <String> getTahList() {
        SurveyStation surveyStation;
        Picket picket;
        String sep = " ";
        LinkedList <String> list = new LinkedList<>();
            try {
                 for (int i = 0; i < this.sizeStations(); i++) {
                     surveyStation = this.getStation(i);
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
                 for (int i = 0; i < this.sizeStations(); i++) {
                     surveyStation = this.getStation(i);
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
     * return list Pickets: name X Y Z
     * @return LinkedList
     */
        public LinkedList<String> getPicketsList() {
            SurveyStation surveyStation;
            Picket picket;
            String sep = " ";
            LinkedList<String> list = new LinkedList<>();
            try {
                for (int i = 0; i < this.sizeStations(); i++) {
                    surveyStation = this.getStation(i);
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
     * gets listReport
     * @return LinkedList<String>
     */
    public LinkedList<String> getReportList() {
            HashMap<String, String> titlesReports = new Shell(parentFrame).getTitlesReports();
            Picket picket;
            LinkedList<String> listReport = new LinkedList<>();
            LinkedList<String> listTopReport = new Shell(parentFrame).getTopReportSurvey();
            String str;
            while ((str = listTopReport.pollFirst()) != null) {
                listReport.add(str);
            }
        for (SurveyStation surveyStation : surveyStations) {
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
     * Appends new element Station to the end of list ll
     * @param sName Station name
     * @param sXst Station coordinate X, measured in metres
     * @param sYst Station coordinate Y, measured in metres
     * @param sZst Station coordinate Z, measured in metres
     * @param sNameOr Oriented name
     * @param sXor Oriented coordinate X, measured in metres
     * @param sYor Oriented coordinate Y, measured in metres
     * @param sZor Oriented coordinate Z, measured in metres
     * @param sI Tool height
     * @return Station
     */
     public SurveyStation addStation(String sName,
                                     String sXst,
                                     String sYst,
                                     String sZst,
                                     String sNameOr,
                                     String sXor,
                                     String sYor,
                                     String sZor,
                                     String sI) throws NullPointerException{
        surveyStations.add(new SurveyStation(sName,
                sXst,
                sYst,
                sZst,
                sNameOr,
                sXor,
                sYor,
                sZor,
                sI));
        return surveyStations.getLast();
    }

    /**
     * Appends new Empty instance of SurveyStation to the end of this.surveyStations
     * @return Station
     */
    public SurveyStation addStation() {
        surveyStations.add(new SurveyStation());
         return surveyStations.getLast();
    }

    /**
     * Addes new instance SurveyStation to this.surveyStations to index position
     * @param index int index
     * @return SurveyStation
     */
    public SurveyStation addStation(int index) {
        SurveyStation surveyStation;
        surveyStations.add(index, new SurveyStation());
        surveyStation = surveyStations.get(index);
        surveyStation.addPicket();
        return surveyStation;
    }

    /**
     * Addes new instance SurveyStation to this.surveyStations to end position
     * @param surveyStation instance of SurveyStation
     */
    public void addStation(SurveyStation surveyStation) {
        surveyStations.add(surveyStation);
    }

    /**
     * Rerurns the element (Station) at the specfied position of this.surveyStations
     * @param index element index
     * @return element (Station)
     */
    public SurveyStation getStation(int index){
            return surveyStations.get(index);
    }

    /**
     * Removes the element (Station) at the specified position this.surveyStations
     * @param index element index
     */
    public void removeStation(int index){
        surveyStations.remove(index);
    }

    /**
     * Return the size of list  of Stations
     * @return int size list of Stations
     */
    public int sizeStations(){
        return surveyStations.size();
    }

    /**
     * Set absolute path to current file tah
     * @param absoluteTahPath String
     */
    public void setAbsoluteTahPath(String absoluteTahPath) {
        this.absoluteTahPath = absoluteTahPath;
    }

    /**
     * Get absolute path to current file tah
     * @return String
     */
    public String getAbsoluteTahPath() {
        return absoluteTahPath;
    }

    /**
     * process SurveyProject and sets for all pickets:
     * directions,DX, DY, DZ, X, Y, Z
     */
    public void processSourceData() {
        GeoCalc geoCalc = new GeoCalc();
        double dirBase;
        double dirPicket;
        double horLine;
        double dX;
        double dY;
        double dZ;
        Picket picket;
        for (SurveyStation llStation : surveyStations) {
            dirBase = geoCalc.getDirAB(
                    llStation.getX(),
                    llStation.getY(),
                    llStation.getxOr(),
                    llStation.getyOr());
            for (int j = 0; j < llStation.sizePickets(); j++) {
                picket = llStation.getPicket(j);


                if (parentFrame.getOptions().getOrientStation() == 1) {
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

    /**
     * checks the possibility to get TheoProject from this SurveyProfect
     * @return boolean
     */
    public boolean containPolygon() {
        for (SurveyStation llStation : surveyStations) {
            if (llStation.sizePickets() < 2) {
                return false;
            }
        }
        return true;
    }
}

