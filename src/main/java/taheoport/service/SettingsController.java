package taheoport.service;

/**
 * This interface defines methods for working with program settings
 */
public interface SettingsController {

    /**
     * Writes settings to file taheoport.ini
     */
    void saveOptions();

    /**
     * Read options from taheoport.ini
     */
    void loadOptions();

    /**
     * gets path to work directories
     * @return String
     */
    String getPathWorkDir();

    /**
     * Gets current Language
     * @return int
     */
    int getLanguage();

    /**
     * Gets prefix for free station
     * @return int
     */
    int getPrefixEX();

    /**
     * Sets current language
     * @param language int
     */
    void setLanguage(int language);

    /**
     * Sets path to work directories
     * @param pathWorkDir String
     */
    void setPathWorkDir(String pathWorkDir);

    /**
     * Sets prefix for free station
     * @param prefixEX int
     */
    void setPrefixEX(int prefixEX);

    /**
     * Gets array of FHs
     * @return String[]
     */
    String[] getFHs();

    /**
     * Gets index FH in array FHs
     * @return int
     */
    int getIdxFH();

    /**
     * Sets index FH in array FHs
     */
    void setIdxFH(int idxFH);

    /**
     * Gets array of values angular error
     * @return String[]
     */
    String[] getFHors();

    /**
     * Gets current index in array of angular errors
     * @return int
     */
    public int getIdxFHor();

    /**
     * Sets current index in array of angular errors
     * @param idxFHor int
     */
    void setIdxFHor(int idxFHor);

    /**
     * Gets array of values linear absolute errors
     * @return String[]
     */
    String[] getFAbss();

    /**
     * Gets current index in array of linear absolute errors
     * @return int
     */
    int getIdxFAbs();

    /**
     * Sets current index in array of linear absolute errors
     * @param idxFAbs int
     */
    void setIdxFAbs(int idxFAbs);

    /**
     * Gets array of values linear relative errors
     * @return String[]
     */
    String[] getFOtns();

    /**
     * Gets current index in array of linear relative errors
     * @return int
     */
    int getIdxFOtn();

    /**
     * Sets current index in array of linear relative errors
     * @param idxFOtn int
     */
    void setIdxFOtn(int idxFOtn);

    /**
     * Sets type orientation to back station
     * @param orientStation int
     */
    void setOrientStation(int orientStation);

    /**
     * Gets type orientation to back station
     * @return int
     */
    int getOrientStation();

    /**
     * Gets offset distance type
     * @return int
     */
    int getOffsetDistanceType();

    /**
     * Sets type of change distance
     * @param offsetDistanceType int
     */
    void setOffsetDistanceType(int offsetDistanceType);

    /**
     * Checks that changes has been
     * @return boolean result
     */
    boolean isChanged();

    /**
     * Sets values of changed
     * @param changed boolean
     */
    void setChanged(boolean changed);

    /**
     * Gets offset direction
     * @param offsetDirection String
     */
    void setOffsetDirection(String offsetDirection);

    /**
     * Gets offset direction
     * @return String
     */
    String getOffsetDirection();

    /**
     * Gets type of offset direction
     * @return int
     */
    int getOffsetDirectionType();

    /**
     * Sets type of offset direction
     * @param offsetDirectionType int
     */
    void setOffsetDirectionType(int offsetDirectionType);

    /**
     * Sets offset tilt angle
     * @param offsetTiltAngle String
     */
    void setOffsetTiltAngle(String offsetTiltAngle);

    /**
     * Gets offset tilt angle
     * @return String
     */
    String getOffsetTiltAngle();

    /**
     * Gets type of change
     * @return int
     */
    int getOffsetTiltType();

    /**
     * Sets type of changes
     * @param offsetTiltType int
     */
    void setOffsetTiltType(int offsetTiltType);

    /**
     * Sets offset distance
     * @param offsetDistance String
     */
    void setOffsetDistance(String offsetDistance);

    /**
     * Gets offset distance
     * @return
     */
    String getOffsetDistance();

    /**
     * Gets value of FHor
     * @return double
     */
    double getValueFHor();

    /**
     * Gets value of H
     * @return double
     */
    double getValueFH();

    /**
     * Gets value of FAbs
     * @return double
     */
    double getValueFAbs();

    /**
     * Gets value of FOtn
     * @return String
     */
    String getValueFOtn();

}
