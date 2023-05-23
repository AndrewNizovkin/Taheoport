package taheoport;

import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

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
     *  Import from *.txt file Topcon
     */
    public SurveyProject loadTopconList(LinkedList<String> list) {
        SurveyStation surveyStation;
        Picket picket;
        list.removeFirst();
        String[] measurements;
        String[] measurement;
        String [] array = list.removeFirst().split("_'");
        for (String row : array) {
            Matcher m = Pattern.compile("^.+?\\+").matcher(row);
            if (m.find()) {
                surveyStation = addStation();
                surveyStation.setName(row.substring(0, row.indexOf("_", 0)));
                surveyStation.setVi(new DataHandler(row.substring(row.indexOf(")", 0) + 1,
                        row.indexOf("_", row.indexOf(")", 0)))).format(3).getStr());
                measurements = row.substring(m.end()).split("_\\+");
                for (String record : measurements) {
                    picket = surveyStation.addPicket(surveyStation);
                    Matcher mm = Pattern.compile("[_*\\?*\\+m*d*,*]").matcher(record);
                    record = new DataHandler(mm.replaceAll(" ")).compress(" ").getStr();
                    measurement = record.split(" ");
                    picket.setpName(measurement[0]);
                    picket.setLine(new DataHandler(measurement[1]).setPointPosition(5).format(3).getStr());
                    picket.setVert(new DataHandler(measurement[2]).setPointPosition(3).ZenithToVert().format(4).getStr());
                    picket.setHor(new DataHandler(measurement[3]).setPointPosition(3).format(4).getStr());
                    picket.setV(new DataHandler(measurement[7]).format(3).getStr());
                }
            }
        }
        return this;
    }

    /**
     * Import from *.row file Nicon
     * @param l list
     */
    public SurveyProject loadNiconList(LinkedList<String> l) {
        SurveyStation surveyStation = new SurveyStation();
        l.removeFirst();
        try {
            while (l.size() > 0){
                String [] array = l.removeFirst().split(",");
                switch (array[0]) {
                    case "ST" -> surveyStation = addStation(array[1],
                            "0.000", "0.000", "0.000",
                            array[3],
                            "0.000", "0.000", "0.000",
                            new DataHandler(array[5]).format(3).getStr());
                    case "SS" -> surveyStation.addPicket(array[7],
                            new DataHandler(array[3]).format(3).getStr(),
                            new DataHandler(array[4]).format(4).getStr(),
                            new DataHandler(array[5]).format(4).getStr(),
                            new DataHandler(array[2]).format(3).getStr(),
                            array[1], surveyStation);
                }
            }
            l = null;

        } catch (NoSuchElementException e) {
            System.out.println("element not found");
        }
        return this;
    }
    /**
     * Import from *.gsi file Leica
     */
    public SurveyProject loadLeicaList(LinkedList<String> list) {
        SurveyStation surveyStation = new SurveyStation();
        String sep =" ";
        String code = "Not";
        String iCur = "Not";
        DataHandler[] lh;
        int res = 0;
        String [] array;
        list.removeFirst();
        try {
            while (list.size() > 0) {
                array = (list.removeFirst()).split(sep);
                lh = new DataHandler[array.length];
                switch (array[0].substring(0, 2)) {
                    case "41" ->
                            code = array[0].substring(7);
                    case "11" -> {
                        for (String s : array) {
                            switch (s.substring(0, 2)) {
                                case "11":
                                    lh[6] = new DataHandler(s.substring(7)).removeFirstZero();
                                case "31":
                                    lh[1] = new DataHandler(s.substring(7)).setPointPosition(5).format(3);
                                    break;
                                case "21":
                                    lh[2] = new DataHandler(s.substring(7)).setPointPosition(3).format(4);
                                    break;
                                case "22":
                                    lh[3] = new DataHandler(s.substring(7)).setPointPosition(3).ZenithToVert().format(4);
                                    break;
                                case "87":
                                    lh[4] = new DataHandler(s.substring(7)).setPointPosition(5).format(3);
                                    break;
                                case "88":
                                    lh[5] = new DataHandler(s.substring(7)).setPointPosition(5).format(3);
                                    break;
                            }
                        }
                        if (!code.equals("Not")) {
                            lh[0] = new DataHandler(code).removeFirstZero();
                        } else {
                            lh[0] = lh[6];
                        }
                        if (lh[5].getStr().equals(iCur)) {
                            surveyStation.addPicket(lh[0].getStr(),
                                    lh[1].getStr(),
                                    lh[2].getStr(),
                                    lh[3].getStr(),
                                    lh[4].getStr(),
                                    lh[6].getStr(), surveyStation);
                        } else {
                            surveyStation = addStation();
                            surveyStation.setVi(lh[5].getStr());
                            iCur = lh[5].getStr();
                            surveyStation.addPicket(lh[0].getStr(),
                                    lh[1].getStr(),
                                    lh[2].getStr(),
                                    lh[3].getStr(),
                                    lh[4].getStr(),
                                    lh[6].getStr(), surveyStation);
                        }
                    }
                }
            }

        } catch (NoSuchElementException e) {
            System.out.println("element not found");
        }
        return this;
    }

    /**
     * Import from *.tah file
     */
        public SurveyProject loadTahList(LinkedList<String> l){
            SurveyStation surveyStation;
            String sep =" ";
            String str;
            String [] array;
            if (l == null) {
                return null;
            }
            absoluteTahPath = l.removeFirst();
            try {
                str = l.removeFirst();
                while (!str.contains("//") && l.size() > 1) {
                    str = new DataHandler(str).compress(sep).getStr();
                    array = str.split(sep);
                    addStation(array[0], array[1], array[2], array[3], array[5], array[6], array[7], "0.000", array[4]);
                    str = (String) l.removeFirst();
                }
                int index = 0;
                surveyStation = getStation(index);
                while (l.size() > 0) {
                    str = (String) l.removeFirst();
                      if (!str.contains("//")) {
                      str = new DataHandler(str).compress(sep).getStr();
                        array = str.split(sep);
                        surveyStation.addPicket(array[0], array[1], array[2], array[3], array[4], String.valueOf(surveyStation.sizePickets()), surveyStation);
                    } else {
                        index = index + 1;
                        if (index < sizeStations()) {
                            surveyStation = getStation(index);
                        }
                    }
                }
            } catch (NoSuchElementException e) {
                System.out.println("element not found");
            }
            return this;
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
     * Appends new instance of SurveyStation to the end of this.surveyStations
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
        surveyStation.addPicket(surveyStation);
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
     * Rerurns the element (Station) at the specfied position of list ll
     * @param index element index
     * @return element (Station)
     */
    public SurveyStation getStation(int index){
            return surveyStations.get(index);
    }

    /**
     * Removes the element (Station) at the specified position of list ll
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
    public boolean havePolygon() {
        for (SurveyStation llStation : surveyStations) {
            if (llStation.sizePickets() < 2) {
                return false;
            }
        }
        return true;
    }
}

