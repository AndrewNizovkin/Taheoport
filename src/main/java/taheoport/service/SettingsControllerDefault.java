package taheoport.service;

import taheoport.gui.MainWin;
import taheoport.model.Settings;

import java.io.File;
import java.util.LinkedList;
import java.util.List;

/**
 * This class encapsulates methods for working with program settings
 */
public class SettingsControllerDefault implements SettingsController {

//    private final MainWin parentFrame;
    private final Settings settings;
    private final IOService ioService;

    /**
     * Constructor
     * @param parentFrame MainWin
     */
    public SettingsControllerDefault(MainWin parentFrame) {
//        this.parentFrame = parentFrame;
        settings = new Settings();
        ioService = parentFrame.getIoService();
        loadOptions();

    }

    /**
     * Writes settings to file taheoport.ini
     */
    @Override
    public void saveOptions() {
//        Settings settings = parentFrame.getSettings();
        LinkedList<String> list = new LinkedList<>();
        list.add("taheoport_ini");
        list.add("pathWorkDir=" + settings.getPathWorkDir());
        list.add("idxFH=" + settings.getIdxFH());
        list.add("idxFHor=" + settings.getIdxFHor());
        list.add("idxFAbs=" + settings.getIdxFAbs());
        list.add("idxFOtn=" + settings.getIdxFOtn());
        list.add("prefixEX=" + settings.getPrefixEX());
        list.add("orientStation=" + settings.getOrientStation());
        list.add("language=" + settings.getLanguage());
        list.add("offsetDistanceType=" + settings.getOffsetDistanceType());
        list.add("offsetDirectionType=" + settings.getOffsetDirectionType());
        list.add("offsetTiltAngleType=" + settings.getOffsetTiltType());

        ioService.writeTextFile(list, "taheoport.ini");
    }

    /**
     * Read options from taheoport.ini
     */
    @Override
    public void loadOptions() {
//        Settings settings = parentFrame.getSettings();
        File f = new File("taheoport.ini");
        if (!f.isFile()) {
            saveOptions();
        }

        List<String > list = ioService.readTextFile("taheoport.ini");
        list.remove(0);
        String firstLine;
        firstLine = list.remove(0);

        if (firstLine  == null || !firstLine.equals("taheoport_ini")) {
            saveOptions();
            list = ioService.readTextFile("taheoport.ini");
            list.remove(0);
        }

        String [] array;
        while (!list.isEmpty()) {
            firstLine = list.remove(0);
            array = firstLine.split("=");
            switch (array[0]) {
                case "pathWorkDir" -> settings.setPathWorkDir(array[1]);
                case "idxFH" -> settings.setIdxFH(Integer.parseInt(array[1]));
                case "idxFHor" -> settings.setIdxFHor(Integer.parseInt(array[1]));
                case "idxFAbs" -> settings.setIdxFAbs(Integer.parseInt(array[1]));
                case "idxFOtn" -> settings.setIdxFOtn(Integer.parseInt(array[1]));
                case "prefixEX" -> settings.setPrefixEX(Integer.parseInt(array[1]));
                case "orientStation" -> settings.setOrientStation(Integer.parseInt(array[1]));
                case "language" -> settings.setLanguage(Integer.parseInt(array[1]));
                case "offsetDistanceType" -> settings.setOffsetDistanceType(Integer.parseInt(array[1]));
                case "offsetDirectionType" -> settings.setOffsetDirectionType(Integer.parseInt(array[1]));
                case "offsetTiltAngleType" -> settings.setOffsetTiltType(Integer.parseInt(array[1]));
            }
        }
    }

    /**
     * gets path to work directories
     *
     * @return String
     */
    @Override
    public String getPathWorkDir() {
        return settings.getPathWorkDir();
    }

    /**
     * Gets current Language
     *
     * @return
     */
    @Override
    public int getLanguage() {
        return settings.getLanguage();
    }

    /**
     * Gets prefix for free station
     *
     * @return int
     */
    @Override
    public int getPrefixEX() {
        return settings.getPrefixEX();
    }

    /**
     * Sets current language
     *
     * @param language int
     */
    @Override
    public void setLanguage(int language) {
        settings.setLanguage(language);
    }

    /**
     * Sets path to work directories
     *
     * @param pathWorkDir String
     */
    @Override
    public void setPathWorkDir(String pathWorkDir) {
        settings.setPathWorkDir(pathWorkDir);
    }

    /**
     * Sets prefix for free station
     *
     * @param prefixEX int
     */
    @Override
    public void setPrefixEX(int prefixEX) {
        settings.setPrefixEX(prefixEX);
    }

    /**
     * Gets array of FHs
     *
     * @return String[]
     */
    @Override
    public String[] getFHs() {
        return settings.getFHs();
    }

    /**
     * Gets index FH in array FHs
     *
     * @return int
     */
    @Override
    public int getIdxFH() {
        return settings.getIdxFH();
    }

    /**
     * Sets index FH in array FHs
     *
     * @param idxFH
     */
    @Override
    public void setIdxFH(int idxFH) {
        settings.setIdxFH(idxFH);
    }

    /**
     * Gets array of values angular error
     *
     * @return String[]
     */
    @Override
    public String[] getFHors() {
        return settings.getFHors();
    }

    /**
     * Gets current index in array of angular errors
     *
     * @return int
     */
    @Override
    public int getIdxFHor() {
        return settings.getIdxFHor();
    }

    /**
     * Sets current index in array of angular errors
     *
     * @param idxFHor int
     */
    @Override
    public void setIdxFHor(int idxFHor) {
        settings.setIdxFHor(idxFHor);
    }

    /**
     * Gets array of values linear absolute errors
     *
     * @return String[]
     */
    @Override
    public String[] getFAbss() {
        return settings.getFAbss();
    }

    /**
     * Gets current index in array of linear absolute errors
     *
     * @return int
     */
    @Override
    public int getIdxFAbs() {
        return settings.getIdxFAbs();
    }

    /**
     * Sets current index in array of linear absolute errors
     *
     * @param idxFAbs int
     */
    @Override
    public void setIdxFAbs(int idxFAbs) {
        settings.setIdxFAbs(idxFAbs);
    }

    /**
     * Gets array of values linear relative errors
     *
     * @return String[]
     */
    @Override
    public String[] getFOtns() {
        return settings.getFOtns();
    }

    /**
     * Gets current index in array of linear relative errors
     * @return int
     */
    @Override
    public int getIdxFOtn() {
        return settings.getIdxFOtn();
    }

    /**
     * Sets current index in array of linear relative errors
     *
     * @param idxFOtn int
     */
    @Override
    public void setIdxFOtn(int idxFOtn) {
        settings.setIdxFAbs(idxFOtn);
    }

    /**
     * Sets type orientation to back station
     *
     * @param orientStation int
     */
    @Override
    public void setOrientStation(int orientStation) {
        settings.setOrientStation(orientStation);
    }

    /**
     * Gets type orientation to back station
     *
     * @return int
     */
    @Override
    public int getOrientStation() {
        return settings.getOrientStation();
    }

    /**
     * Gets offset distance type
     *
     * @return int
     */
    @Override
    public int getOffsetDistanceType() {
        return settings.getOffsetDistanceType();
    }

    /**
     * Sets type of change distance
     *
     * @param offsetDistanceType int
     */
    @Override
    public void setOffsetDistanceType(int offsetDistanceType) {
        settings.setOffsetDistanceType(offsetDistanceType);
    }

    /**
     * Checks that changes has been
     *
     * @return boolean result
     */
    @Override
    public boolean isChanged() {
        return settings.isChanged();
    }

    /**
     * Sets values of changed
     *
     * @param changed boolean
     */
    @Override
    public void setChanged(boolean changed) {
        settings.setChanged(changed);
    }

    /**
     * Gets offset direction
     *
     * @param offsetDirection String
     */
    @Override
    public void setOffsetDirection(String offsetDirection) {
        settings.setOffsetDirection(offsetDirection);
    }

    /**
     * Gets offset direction
     *
     * @return String
     */
    @Override
    public String getOffsetDirection() {
        return settings.getOffsetDirection();
    }

    /**
     * Gets type of offset direction
     *
     * @return int
     */
    @Override
    public int getOffsetDirectionType() {
        return settings.getOffsetDirectionType();
    }

    /**
     * Sets type of offset direction
     *
     * @param offsetDirectionType int
     */
    @Override
    public void setOffsetDirectionType(int offsetDirectionType) {
        settings.setOffsetDirectionType(offsetDirectionType);
    }

    /**
     * Sets offset tilt angle
     *
     * @param offsetTiltAngle String
     */
    @Override
    public void setOffsetTiltAngle(String offsetTiltAngle) {
        settings.setOffsetTiltAngle(offsetTiltAngle);
    }

    /**
     * Gets offset tilt angle
     *
     * @return String
     */
    @Override
    public String getOffsetTiltAngle() {
        return settings.getOffsetTiltAngle();
    }

    /**
     * Gets type of change
     *
     * @return int
     */
    @Override
    public int getOffsetTiltType() {
        return settings.getOffsetTiltType();
    }

    /**
     * Sets type of changes
     *
     * @param offsetTiltType int
     */
    @Override
    public void setOffsetTiltType(int offsetTiltType) {
        settings.setOffsetTiltType(offsetTiltType);
    }

    /**
     * Sets offset distance
     *
     * @param offsetDistance String
     */
    @Override
    public void setOffsetDistance(String offsetDistance) {
        settings.setOffsetDistance(offsetDistance);
    }

    /**
     * Gets offset distance
     *
     * @return
     */
    @Override
    public String getOffsetDistance() {
        return settings.getOffsetDistance();
    }

    /**
     * Gets value of FHor
     *
     * @return double
     */
    @Override
    public double getValueFHor() {
        return settings.getValueFHor();
    }

    /**
     * Gets value of H
     *
     * @return double
     */
    @Override
    public double getValueFH() {
        return settings.getValueFH();
    }

    /**
     * Gets value of FAbs
     *
     * @return double
     */
    @Override
    public double getValueFAbs() {
        return settings.getValueFAbs();
    }

    /**
     * Gets value of FOtn
     *
     * @return String
     */
    @Override
    public String getValueFOtn() {
        return settings.getValueFOtn();
    }


}
