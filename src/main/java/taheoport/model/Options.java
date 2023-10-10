package taheoport.model;

import taheoport.services.MyChooser;
import taheoport.gui.MainWin;

import java.io.File;
import java.util.LinkedList;

/**
 * This class encapsulates settings of program
 * @author Andrew Nizovkin
 * Copyright Nizovkin A.V. 2022
 */
public class Options {
    private final String[] FHs = new String[] {"5√L", "10√L", "20√L", "30√L", "40√L", "50√L"};
    private final String[] FHors = new String[] {"2√n", "5√n", "10√n", "20√n", "30√n", "60√n"};
    private final String[] FAbss = new String[] {"0.01", "0.05", "0.10", "0.20", "0.30"};
    private final String[] FOtns = new String[] {"1:1000", "1:2000", "1:5000", "1:10000", "1:20000"};
    private boolean isChanged = false;
    private int idxFH = 0;
    private int idxFHor = 0;
    private int idxFAbs = 0;
    private int idxFOtn = 0;
    private int language = 0;
    private String pathWorkDir;
    private int orientStation = 0;
    private int offsetDistanceType = 0;
    private int offsetDirectionType = 0;
    private int offsetTiltType = 0;
    private String offsetDistance = "0.000";
    private String offsetDirection = "0.0000";
    private String offsetTiltAngle = "0.0000";
    private final MainWin parentFrame;
    private int prefixEX = 86;


    /**
     * Constructor
     * @param frame MainWin
     */
    public Options(MainWin frame) {
        this.parentFrame = frame;
        File f = new File("taheoport.ini");
        if (!f.isFile()) {
            this.pathWorkDir = System.getProperty("user.dir");
            saveOptions();
        }

        LinkedList <String > list = new MyChooser(parentFrame).readTextFile("taheoport.ini");
        list.pollFirst();
        String s;
        s = list.pollFirst();

        if (s  == null ) {
            this.pathWorkDir = System.getProperty("user.dir");
            saveOptions();
            list = new MyChooser(parentFrame).readTextFile("taheoport.ini");
            list.pollFirst();
            s = list.pollFirst();
        }


        assert s != null;
        if (!s.equals("taheoport_ini")) {
            this.pathWorkDir = System.getProperty("user.dir");
            saveOptions();
            list = new MyChooser(parentFrame).readTextFile("taheoport.ini");
            list.pollFirst();
        }


        String [] array;
            while ((s = list.pollFirst()) != null) {
                array = s.split("=");
                switch (array[0]) {
                    case "pathWorkDir" -> pathWorkDir = array[1];
                    case "idxFH" -> idxFH = Integer.parseInt(array[1]);
                    case "idxFHor" -> idxFHor = Integer.parseInt(array[1]);
                    case "idxFAbs" -> idxFAbs = Integer.parseInt(array[1]);
                    case "idxFOtn" -> idxFOtn = Integer.parseInt(array[1]);
                    case "prefixEX" -> prefixEX = Integer.parseInt(array[1]);
                    case "orientStation" -> orientStation = Integer.parseInt(array[1]);
                    case "language" -> language = Integer.parseInt(array[1]);
                    case "offsetDistanceType" -> offsetDistanceType = Integer.parseInt(array[1]);
                    case "offsetDirectionType" -> offsetDirectionType = Integer.parseInt(array[1]);
                    case "offsetTiltAngleType" -> offsetTiltType = Integer.parseInt(array[1]);
                }
            }


    }

    public void setPathWorkDir(String pathWorkDir) {
        this.pathWorkDir = pathWorkDir;
    }

    public void setIdxFH(int idxFH) {
        this.idxFH = idxFH;
    }

    public void setIdxFHor(int idxFHor) {
        this.idxFHor = idxFHor;
    }

    public void setIdxFAbs(int idxFAbs) {
        this.idxFAbs = idxFAbs;
    }

    public void setIdxFOtn(int idxFOtn) {
        this.idxFOtn = idxFOtn;
    }

    public void setPrefixEX(int prefixEX) {
        if (prefixEX >= 65 & prefixEX <= 90) {
            this.prefixEX = prefixEX;
        }
    }

    public void setOffsetDistanceType(int offsetDistanceType) {
        this.offsetDistanceType = offsetDistanceType;
    }

    public void setOffsetDistance(String offsetDistance) {
        this.offsetDistance = offsetDistance;
    }

    public void setOffsetDirectionType(int offsetDirectionType) {
        this.offsetDirectionType = offsetDirectionType;
    }

    public void setOffsetTiltType(int offsetTiltType) {
        this.offsetTiltType = offsetTiltType;
    }

    public void setOffsetDirection(String offsetDirection) {
        this.offsetDirection = offsetDirection;
    }

    public void setOffsetTiltAngle(String offsetTiltAngle) {
        this.offsetTiltAngle = offsetTiltAngle;
    }

    public void setChanged(boolean changed) {
        isChanged = changed;
    }

    public double getValueFH() {
        return Double.parseDouble(FHs[idxFH].substring(0, FHs[idxFH].length() - 2));
    }

    public double getValueFHor() {
        return Double.parseDouble(FHors[idxFHor].substring(0, FHors[idxFHor].length() - 2));
    }

    public double getValueFAbs() {
        return Double.parseDouble(FAbss[idxFAbs]);
    }

    public String getValueFOtn() {
        return FOtns[idxFOtn].substring(2);
    }

    public int getOrientStation() {
        return orientStation;
    }

    public int getLanguage() {
        return language;
    }

    public String getPathWorkDir() {
        return pathWorkDir;
    }

    public int getIdxFH() {
        return idxFH;
    }

    public int getIdxFHor() {
        return idxFHor;
    }

    public int getIdxFAbs() {
        return idxFAbs;
    }

    public int getIdxFOtn() {
        return idxFOtn;
   }

    public String[] getFHs() {
        return FHs;
    }

    public String[] getFHors() {
        return FHors;
    }

    public String[] getFAbss() {
        return FAbss;
    }

    public String[] getFOtns() {
        return FOtns;
    }

    public int getPrefixEX() {
        return prefixEX;
    }

    public int getOffsetDistanceType() {
        return offsetDistanceType;
    }

    public String getOffsetDistance() {
        return offsetDistance;
    }

    public int getOffsetDirectionType() {
        return offsetDirectionType;
    }

    public int getOffsetTiltType() {
        return offsetTiltType;
    }

    public String getOffsetDirection() {
        return offsetDirection;
    }

    public String getOffsetTiltAngle() {
        return offsetTiltAngle;
    }

    public boolean isChanged() {
        return isChanged;
    }

    public void setOrientStation(int orientStation) {
        this.orientStation = orientStation;
    }

    public void setLanguage(int language) {
        this.language = language;
    }

    /**
     * Writes settings to file taheoport.ini
     */
    public void saveOptions() {
        LinkedList <String> list = new LinkedList<>();
        list.add("taheoport_ini");
        list.add("pathWorkDir=" + pathWorkDir);
        list.add("idxFH=" + idxFH);
        list.add("idxFHor=" + idxFHor);
        list.add("idxFAbs=" + idxFAbs);
        list.add("idxFOtn=" + idxFOtn);
        list.add("prefixEX=" + prefixEX);
        list.add("orientStation=" + orientStation);
        list.add("language=" + language);
        list.add("offsetDistanceType=" + offsetDistanceType);
        list.add("offsetDirectionType=" + offsetDirectionType);
        list.add("offsetTiltAngleType=" + offsetTiltType);

        new MyChooser(parentFrame).writeTextFile("taheoport.ini", list);
    }

}
