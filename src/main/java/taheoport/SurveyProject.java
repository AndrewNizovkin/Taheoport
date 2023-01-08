package taheoport;
/**
 * Classname
 * This class represents a total station survey model.
 * It provides various methods for importing field information
 * from various types of storage devices (total stations).
 * Version information
 *@version 1.0
 * Date
 * @author Andrey Nizovkin
 * Copyright Nizovkin A.V. 2022
 */
//import org.jetbrains.annotations.Contract;
import java.io.File;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class SurveyProject {
    private String path = new File("").getAbsolutePath(); // Path to the working directory
    private String absoluteTahPath = "";
//    private String absoluteCatalogPath;
    private SurveyStation st; //   Reference variable of the Station class
    private  Picket p; // Reference variable of the Picket class
    private  LinkedList <SurveyStation> llStations = new LinkedList <SurveyStation>(); // List of Stations
    private LinkedList <CatalogPoint> llCatalog = new LinkedList<CatalogPoint>();
    private MainWin frameParent;

    public SurveyProject(MainWin frame) {
        frameParent = frame;
    }

    /**
     *  Import from *.txt file Topcon
     */
    public SurveyProject loadTopconList(LinkedList<String> l) {
//        LinkedList <String> l = new MyChooser().readTextFile(path, "txt", "Import from Topcon");
//        if (l == null) {
//            return null;
//        }
        l.removeFirst();
        String str = l.removeFirst();
        String [] array = str.split("_'");
        for (String s : array) {
            Matcher m = Pattern.compile("^.+?\\+").matcher(s);
            if (m.find()) {
                st = addStation();
                st.setName(s.substring(0, s.indexOf("_", 0)));
                st.setVi(new DataHandler(s.substring(s.indexOf(")", 0) + 1, s.indexOf("_", s.indexOf(")", 0)))).format(3).getStr());
                String[] pickets = s.substring(m.end()).split("_\\+");
                for (String s1 : pickets) {
                    p = st.addPicket(st);
                    Matcher mm = Pattern.compile("[_*\\?*\\+m*d*,*]").matcher(s1);
                    s1 = new DataHandler(mm.replaceAll(" ")).compress(" ").getStr();
                    String[] picket = s1.split(" ");
                    p.setpName(picket[0]);
                    p.setLine(new DataHandler(picket[1]).setPointPosition(5).format(3).getStr());
                    p.setVert(new DataHandler(picket[2]).setPointPosition(3).ZenithToVert().format(4).getStr());
                    p.setHor(new DataHandler(picket[3]).setPointPosition(3).format(4).getStr());
                    p.setV(new DataHandler(picket[7]).format(3).getStr());
                }
            }
        }
        return this;
    }

    /**
     * Import from *.row file Nicon
     */
    public SurveyProject loadNiconList(LinkedList<String> l) {
//        LinkedList <String>  l = new MyChooser().readTextFile(path, "raw", "Import From Nicon");
//        if (l == null) {
//            return null;
//        }
        l.removeFirst();
        try {
            while (l.size() > 0){
                String [] array = l.removeFirst().split(",");
                switch (array[0]) {
                    case "ST" -> st = addStation(array[1],
                            "0.000", "0.000", "0.000",
                            array[3],
                            "0.000", "0.000", "0.000",
                            new DataHandler(array[5]).format(3).getStr());
                    case "SS" -> p = st.addPicket(array[7],
                            new DataHandler(array[3]).format(3).getStr(),
                            new DataHandler(array[4]).format(4).getStr(),
                            new DataHandler(array[5]).format(4).getStr(),
                            new DataHandler(array[2]).format(3).getStr(),
                            array[1], st);
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
    public SurveyProject loadLeicaList(LinkedList<String> l) {
        String sep =" ";
        String code = "Not";
        String iCur = "Not";
        DataHandler[] lh;
        int res = 0;
        String [] array;
//        LinkedList <String>  l = new MyChooser().readTextFile(path, "gsi", "Import From Leica");
//        if (l == null) {
//            return null;
//        }
        l.removeFirst();
        try {
            while (l.size() > 0) {
                array = (l.removeFirst()).split(sep);
                lh = new DataHandler[array.length];
                switch (array[0].substring(0, 2)) {
                    case "41" ->
                            //запоминаем код pName
                            code = array[0].substring(7);
                    case "11" -> {
                        for (String s : array) {
                            switch (s.substring(0, 2)) {
                                case "11":
                                    //название (номер) pAltName пикета
                                    lh[6] = new DataHandler(s.substring(7)).removeFirstZero();
                                case "31":
                                    //измеренная линия
                                    lh[1] = new DataHandler(s.substring(7)).setPointPosition(5).format(3);
                                    break;
                                case "21":
                                    //горизонтальный угол
                                    lh[2] = new DataHandler(s.substring(7)).setPointPosition(3).format(4);
                                    break;
                                case "22":
                                    //зенитное расстояние
                                    lh[3] = new DataHandler(s.substring(7)).setPointPosition(3).ZenithToVert().format(4);
                                    break;
                                case "87":
                                    //высота наведения
                                    lh[4] = new DataHandler(s.substring(7)).setPointPosition(5).format(3);
                                    break;
                                case "88":
                                    //высота инструмента
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
                            st.addPicket(lh[0].getStr(),
                                    lh[1].getStr(),
                                    lh[2].getStr(),
                                    lh[3].getStr(),
                                    lh[4].getStr(),
                                    lh[6].getStr(), st);
                        } else {
                            st = addStation();
                            st.setVi(lh[5].getStr());
                            iCur = lh[5].getStr();
                            st.addPicket(lh[0].getStr(),
                                    lh[1].getStr(),
                                    lh[2].getStr(),
                                    lh[3].getStr(),
                                    lh[4].getStr(),
                                    lh[6].getStr(), st);
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
            String sep =" ";
//            int index = 0;
            String str;
            String [] array;
//            LinkedList <String> l = new MyChooser().readTextFile(path, "tah", "Import from tah");
            if (l == null) {
                return null;
            }
            absoluteTahPath = l.removeFirst();
            try {
                str = l.removeFirst();
                while (!str.contains("//") && l.size() > 1) {
                    str = new DataHandler(str).compress(sep).getStr();
                    array = str.split(sep);
                    st = addStation(array[0], array[1], array[2], array[3], array[5], array[6], array[7], "0.000", array[4]);
                    str = (String) l.removeFirst();
                }
                int index = 0;
                st = getStation(index);
                while (l.size() > 0) {
                    str = (String) l.removeFirst();
                      if (!str.contains("//")) {
                      str = new DataHandler(str).compress(sep).getStr();
                        array = str.split(sep);
                        st.addPicket(array[0], array[1], array[2], array[3], array[4], String.valueOf(st.sizePickets()), st);
                    } else {
                        index = index + 1;
                        if (index < sizeStations()) {
                            st = getStation(index);
                        }
                    }
                }
            } catch (NoSuchElementException e) {
                System.out.println("element not found");
            }
            return this;
        }

    /**
     * Returns Tah in the form LinkedList
     * @return Linkedlist
     */
    public   LinkedList <String> getTahList() {
        String sep = " ";
//        String str;
        LinkedList <String> l = new LinkedList<String>();
            try {
                 for (int i = 0; i < this.sizeStations(); i++) {
                     st = this.getStation(i);
                     l.add(st.getName() + sep + st.getX() + sep + st.getY() + sep + st.getZ() +
                             sep + st.getVi() + sep + st.getNameOr() + sep + st.getxOr() + sep + st.getyOr());
                 }
                 l.add("//");
                 for (int i = 0; i < this.sizeStations(); i++) {
                     st = this.getStation(i);
                     for (int j = 0; j < st.sizePickets(); j++ ) {
                         p = st.getPicket(j);
                         l.add(p.getpName() + sep + p.getLine() + sep +
                                 p.getHor() + sep + p.getVert() + sep +
                                 p.getV() + sep + i);
                     }
                     l.add("//");
                 }
            } catch (NoSuchElementException e) {
                System.out.println("element not found");
            }
            return l;
//--->>
        }

    /**
     * return list Pickets: name X Y Z
     * @return LinkedList
     */

//    @Contract(pure = true)
        public LinkedList <String> getPicketsList() {
            String sep = " ";
            LinkedList <String> l = new LinkedList<String>();
            try {
                for (int i = 0; i < this.sizeStations(); i++) {
                    st = this.getStation(i);
                    for (int j = 0; j < st.sizePickets(); j++) {
                        p = st.getPicket(j);
                        l.add(p.getpName() + sep +
                                p.getX() + sep +
                                p.getY() + sep +
                                p.getZ());
                    }
                }
            } catch (NoSuchElementException e) {
                System.out.println("element not found");
            }
            return l;
        }

    /**
     * gets listReport
     * @return LinkedList<String>
     */
    public LinkedList<String> getReportList() {
            HashMap<String, String> titlesReports = new Shell(frameParent).getTitlesReports();
            Picket p;
            LinkedList<String> llReport = new LinkedList<String>();
            LinkedList<String> llTopReport = new Shell(frameParent).getTopReportSurvey();
            String str;
            while ((str = llTopReport.pollFirst()) != null) {
                llReport.add(str);
            }

/*
            llReport.add("");
            llReport.add("                           В  Е  Д  О  М  О  С  Т  Ь    В  Ы  Ч  И  С  Л  Е  Н  И  Я    К  О  О  Р  Д  И  Н  А  Т");
            llReport.add("-----------------------------------------------------------------------------------------------------------------------------------------------");
            llReport.add("|  Название   |  Длина  | Направ-  |  Угол   |  Высота  |   Дир.   |      Приращения координат       |              Координаты                |");
            llReport.add("|   точки     |  линии  |  ление   | наклона | наведения|   угол   |---------------------------------|----------------------------------------|");
            llReport.add("|             |    м.   |  г.мс    |   г.мс  |     м.   |   г.мс   |   DX, м. |   DY, м. |   DZ, м.  |     X, м.   |    Y, м.    |     Z, м.  |");
            llReport.add("|-------------|---------|----------|---------|----------|----------|----------|----------|-----------|--------- ---|-------------|------------|");
            llReport.add("|      1      |     2   |     3    |    4    |     5    |     6    |     7    |     8    |      9    |     10      |      11     |      12    |");
            llReport.add("-----------------------------------------------------------------------------------------------------------------------------------------------");
*/

        for (SurveyStation llStation : llStations) {
            llReport.add("                                   " + titlesReports.get("SPstation") +
                    llStation.getName() +
                    "       " + titlesReports.get("SPorientir") +
                    llStation.getNameOr() +
                    "      " + titlesReports.get("SPtoolHeight") +
                    llStation.getVi());
            llReport.add("-----------------------------------------------------------------------------------------------------------------------------------------------");
            llReport.add("| " +
                    new DataHandler(llStation.getName()).toTable(10).getStr() +
                    " |          |          |         |          |          |          |          |           | " +
                    new DataHandler(llStation.getX()).toTable(11).getStr() + " | " +
                    new DataHandler(llStation.getY()).toTable(11).getStr() + " | " +
                    new DataHandler(llStation.getZ()).toTable(10).getStr() + " |");
            llReport.add("| " +
                    new DataHandler(llStation.getNameOr()).toTable(10).getStr() +
                    " |          |          |         |          |          |          |          |           | " +
                    new DataHandler(llStation.getxOr()).toTable(11).getStr() + " | " +
                    new DataHandler(llStation.getyOr()).toTable(11).getStr() + " |            |");

            for (int j = 0; j < llStation.sizePickets(); j++) {
                p = llStation.getPicket(j);
                llReport.add("| " +
                        new DataHandler(p.getpName()).toTable(10).getStr() + " | " +
                        new DataHandler(p.getLine()).toTable(8).getStr() + " | " +
                        new DataHandler(p.getHor()).toTable(8).getStr() + " | " +
                        new DataHandler(p.getVert()).toTable(7).getStr() + " | " +
                        new DataHandler(p.getV()).toTable(8).getStr() + " | " +
                        new DataHandler(p.getPDirection()).toTable(8).getStr() + " | " +
                        new DataHandler(p.getDX()).toTable(8).getStr() + " | " +
                        new DataHandler(p.getDY()).toTable(8).getStr() + " | " +
                        new DataHandler(p.getDZ()).toTable(9).getStr() + " | " +
                        new DataHandler(p.getX()).toTable(11).getStr() + " | " +
                        new DataHandler(p.getY()).toTable(11).getStr() + " | " +
                        new DataHandler(p.getZ()).toTable(10).getStr() + " |");

            }

            llReport.add("-----------------------------------------------------------------------------------------------------------------------------------------------");
        }

            return llReport;
        }

/*
        public LinkedList <String> getListStations() {
            String sep = " ";
            LinkedList <String> l = new LinkedList<String >();
            for (int i = 0; i < sizeStations(); i++) {
                l.add(getStation(i).getName() + sep +
                        getStation(i).getX() + sep +
                        getStation(i).getY() + sep +
                        getStation(i).getY());
            }

            return l;
        }
*/
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
     public SurveyStation addStation(String sName, String sXst, String sYst, String sZst, String sNameOr, String sXor, String sYor, String sZor, String sI) throws NullPointerException{
        st = new SurveyStation(sName, sXst, sYst, sZst, sNameOr, sXor, sYor, sZor, sI);
        llStations.add(st);
        st = llStations.getLast();
        return st;
    }

    /**
     * Appends new element Station to the end of list ll without parameters
     * @return Station
     */
    public SurveyStation addStation() {
         return addStation("noname", "0.000", "0.000",
                 "0.000","noname", "0.000",
                 "0.000", "0.000", "0.000");
    }

    public SurveyStation addStation(int index) {

        st = new SurveyStation("noname", "0.000", "0.000",
                "0.000","noname", "0.000",
                "0.000", "0.000", "0.000");
        llStations.add(index, st);
        st = llStations.get(index);
        st.addPicket(st);
        return st;
    }

    public SurveyStation addStation(SurveyStation tahStation) {
        llStations.add(tahStation);
        return llStations.getLast();
    }

    /**
     * Rerurns the element (Station) at the specfied position of list ll
     * @param i element index
     * @return element (Station)
     */
    public SurveyStation getStation(int i){
            st = llStations.get(i);
            return st;
    }

    /**
     * Removes the element (Station) at the specified position of list ll
     * @param i element index
     * @return element (Station)
     */
    public SurveyStation removeStation(int i){
            st = llStations.remove(i);
            return st;
    }

    /**
     * Return the size of list  of Stations ll
     * @return int size list of Stations ll
     */
    public int sizeStations(){
        return llStations.size();
    }

    /**
     * get the String path
     * @return path
     */
    public String getPath() {
        return path;
    }

    /**
     * Set the String path
     * @param path
     */
    public void setPath(String path) {
        this.path = path;
    }

    /**
     * Set absolute path to current file tah
     * @param absoluteTahPath
     */
    public void setAbsoluteTahPath(String absoluteTahPath) {
        this.absoluteTahPath = absoluteTahPath;
    }

    /**
     * Get absolute path to current file tah
     * @return
     */
    public String getAbsoluteTahPath() {
        if (absoluteTahPath == null) {
            absoluteTahPath = "";
        }
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
        for (int i = 0; i < llStations.size(); i++) {
            dirBase = geoCalc.getDirAB(
                    llStations.get(i).getX(),
                    llStations.get(i).getY(),
                    llStations.get(i).getxOr(),
                    llStations.get(i).getyOr());
            for (int j = 0; j < llStations.get(i).sizePickets(); j++) {
                picket = llStations.get(i).getPicket(j);


                if (frameParent.getOptions().getOrientStation() == 1) {
                    dirPicket = dirBase + new DataHandler(picket.getHor()).dmsToRad() - new DataHandler(llStations.get(i).getPicket(0).getHor()).dmsToRad();
                    while (dirPicket < 0) {
                        dirPicket += 2 *Math.PI;
                    }
                }else {
                    dirPicket = dirBase + new DataHandler(picket.getHor()).dmsToRad();
                }


                while (dirPicket  > 2 * Math.PI) {
                    dirPicket -= 2 *Math.PI;
                }
                horLine = geoCalc.getHorLine(picket.getLine(), picket.getVert());
                dX = geoCalc.getDX(horLine, dirPicket);
                dY = geoCalc.getDY(horLine, dirPicket);
                dZ = Double.parseDouble(llStations.get(i).getVi()) -
                        Double.parseDouble(llStations.get(i).getPicket(j).getV()) +
                        Double.parseDouble(picket.getLine()) * Math.sin(new DataHandler(picket.getVert()).dmsToRad());
                picket.setDirection(dirPicket);
                picket.setHorLine(horLine);
                picket.setdX(dX);
                picket.setdY(dY);
                picket.setdZ(dZ);
                picket.setX(Double.parseDouble(llStations.get(i).getX()) + dX);
                picket.setY(Double.parseDouble(llStations.get(i).getY()) + dY);
                picket.setZ(Double.parseDouble(llStations.get(i).getZ()) + dZ);
            }
        }
    }

    /**
     * checks the possibility to get TheoProject from this SurveyProfect
     * @return boolean
     */
    public boolean haveTheo() {
/*
        if (llStations.size() < 3) {
            return false;
        }
*/
        for (SurveyStation llStation : llStations) {
            if (llStation.sizePickets() < 2) {
                return false;
            }
        }
        return true;
    }

// The END of Class
}

