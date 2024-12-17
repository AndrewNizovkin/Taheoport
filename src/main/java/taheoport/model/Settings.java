package taheoport.model;

import taheoport.gui.MainWin;

import java.io.File;
import java.util.LinkedList;

/**
 * This class encapsulates settings of program
 * @author Andrew Nizovkin
 * Copyright Nizovkin A.V. 2022
 */
public class Settings {
    private final String[] FHs;
    private final String[] FHors;
    private final String[] FAbss;
    private final String[] FOtns;
    private boolean isChanged;
    private int idxFH;
    private int idxFHor;
    private int idxFAbs;
    private int idxFOtn;
    private int language;
    private String pathWorkDir;
    private int orientStation;
    private int offsetDistanceType;
    private int offsetDirectionType;
    private int offsetTiltType;
    private String offsetDistance;
    private String offsetDirection;
    private String offsetTiltAngle;
    private int prefixEX;

    /**
     * Constructor
     */
    public Settings() {
        FHs = new String[] {"5√L", "10√L", "20√L", "30√L", "40√L", "50√L"};
        FHors = new String[] {"2√n", "5√n", "10√n", "20√n", "30√n", "60√n"};
        FAbss = new String[] {"0.01", "0.05", "0.10", "0.20", "0.30"};
        FOtns = new String[] {"1:1000", "1:2000", "1:5000", "1:10000", "1:20000"};
        isChanged = false;
        idxFH = 0;
        idxFHor = 0;
        idxFAbs = 0;
        idxFOtn = 0;
        language = 0;
        pathWorkDir = System.getProperty("user.dir");
        orientStation = 0;
        offsetDistanceType = 0;
        offsetDirectionType = 0;
        offsetTiltType = 0;
        offsetDistance = "0.000";
        offsetDirection = "0.0000";
        offsetTiltAngle = "0.0000";
        prefixEX = 86;
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
}
