package taheoport;

import java.io.File;
import java.util.LinkedList;

public class Options {
    private Boolean isChanged = false;
    private String pathWorkDir;
    private int idxFH = 0;
    private int idxFHor = 0;
    private int idxFAbs = 0;
    private int idxFOtn = 0;
    private final MainWin parentFrame;
    private int prefixEX = 86;
    private int orientStation = 0;
    private int language = 0;
    private int offsetDistanceType = 0;
    private int offsetDirectionType = 0;
    private int offsetTiltType = 0;
    private String offsetDistance = "0.000";
    private String offsetDirection = "0.0000";
    private String offsetTiltAngle = "0.0000";
    private final String[] FHs = new String[] {"5√L", "10√L", "20√L", "30√L", "40√L", "50√L"};
    private final String[] FHors = new String[] {"2√n", "5√n", "10√n", "20√n", "30√n", "60√n"};
    private final String[] FAbss = new String[] {"0.01", "0.05", "0.10", "0.20", "0.30"};
    private final String[] FOnts = new String[] {"1:1000", "1:2000", "1:5000", "1:10000", "1:20000"};


    /**
     * Constructor
     * @param frame
     */
    public Options(MainWin frame) {
        this.parentFrame = frame;
        File f = new File("taheoport.ini");
        if (!f.isFile()) {
            this.pathWorkDir = new File("").getAbsolutePath();
            saveOptions();
        }

        LinkedList <String > ll = new MyChooser(parentFrame).readTextFile("taheoport.ini");
        ll.pollFirst();
        String s;
        s = ll.pollFirst();

        if (s  == null ) {
            this.pathWorkDir = new File("").getAbsolutePath();
            saveOptions();
            ll = new MyChooser(parentFrame).readTextFile("taheoport.ini");
            ll.pollFirst();
            s = ll.pollFirst();
        }


        assert s != null;
        if (!s.equals("taheoport_ini")) {
            this.pathWorkDir = new File("").getAbsolutePath();
            saveOptions();
            ll = new MyChooser(parentFrame).readTextFile("taheoport.ini");
            ll.pollFirst();
        }


        String [] array;
            while ((s = ll.pollFirst()) != null) {
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

    public void setChanged(Boolean changed) {
        isChanged = changed;
    }

    public double getRatioFH() {
        return Double.parseDouble(FHs[idxFH].substring(0, FHs[idxFH].length() - 2));
    }

    public double getRatioFHor() {
        return Double.parseDouble(FHors[idxFHor].substring(0, FHors[idxFHor].length() - 2));
    }

    public double getRatioFAbs() {
        return Double.parseDouble(FAbss[idxFAbs]);
    }

    public String getRatioFOtn() {
        return FOnts[idxFOtn].substring(2);
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

    public String[] getFOnts() {
        return FOnts;
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

    public Boolean getChanged() {
        return isChanged;
    }

    public void setOrientStation(int orientStation) {
        this.orientStation = orientStation;
    }

    public void setLanguage(int language) {
        this.language = language;
    }

    public void saveOptions() {
        LinkedList <String> ll = new LinkedList<>();
        ll.add("taheoport_ini");
        ll.add("pathWorkDir=" + pathWorkDir);
        ll.add("idxFH=" + idxFH);
        ll.add("idxFHor=" + idxFHor);
        ll.add("idxFAbs=" + idxFAbs);
        ll.add("idxFOtn=" + idxFOtn);
        ll.add("prefixEX=" + prefixEX);
        ll.add("orientStation=" + orientStation);
        ll.add("language=" + language);
        ll.add("offsetDistanceType=" + offsetDistanceType);
        ll.add("offsetDirectionType=" + offsetDirectionType);
        ll.add("offsetTiltAngleType=" + offsetTiltType);

        new MyChooser(parentFrame).writeTextFile("taheoport.ini", ll);
    }

    public void setOptions() {

    }

}
