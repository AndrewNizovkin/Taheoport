package taheoport.service;

import taheoport.model.BindType;
import taheoport.model.PolygonStation;
import taheoport.repository.PolygonRepository;

import java.util.List;

/**
 * This interface defines methods for working with polygon project
 */
public interface PolygonService {

    /**
     * Create new polygon project
     */
    void newProject();

    /**
     * Loads polygon project from pol-file
     */
    void importPol();

    /**
     * Saves current polygon project to pol-file
     */
    void savePol();

    /**
     * Saves current polygon project to pol-file
     */
    void savePolAs();

    /**
     * Gets polygonRepository
     * @return polygonRepository
     */
    PolygonRepository getAllPolygonStations();

    /**
     * Gets absolutePolPath
     * @return String
     */
    String getAbsolutePolPath();

    /**
     * Load PolygonProject from LinkedList<String> list
     * @param list list
     */
    void loadPolList(List<String> list);

    /**
     * gets list for writing to file current PolygonProject
     * @return LinkedList
     */
    List<String> getPolList();

    /**
     * Gets polygonometry adjustment report
     * @return LinkedList <String>
     */
    List<String> getReportXY ();

    /**
     * Gets leveling  adjustment report
     * @return LinkedList <String>
     */
    List<String> getReportZ ();

    /**
     * Returns a list of rows in the report <Name X Y Z>
     * @return LinkedList <String>
     */
    List<String> getReportNXYZ();

    /**
     * Processes the source data.
     * Adjustment the network and determines the coordinates of the defined points llTheoStations
     */
    void processSourceData();

    /**
     * Сhecks the possibility of inserting before idx position
     * @param idx int idx
     * @return boolean
     */
    boolean isInsertBefore(int idx);

    /**
     * Сhecks the possibility of inserting after idx position
     * @param idx int idx
     * @return boolean
     */
    boolean isInsertAfter(int idx);

    /**
     * Inserts station to repository by index
     * @param idx int
     */
    void insertStation(int idx);

    /**
     * remove Station from llTheoStations
     * @param idx int index removed element
     */
    void removeStation(int idx);

    /**
     * Gets polygonStation by id from repository
     * @param idx int index
     * @return polygonStation
     */
    PolygonStation findById(int idx);

    /**
     * Gets repository size
     * @return int
     */
    int getSizePolygonStations();

    /**
     * Gets polygon perimeter
     * @return double
     */
    double getPerimeter();

    /**
     * Sets perimeter
     * @param perimeter double
     */
    void setPerimeter(double perimeter);

    /**
     * Gets the actual angular error
     * @return double
     */
    double getfHor();

    /**
     * Gets linear actual error on the X-axis
     * @return double
     */
    double getfX();

    /**
     * Gets linear actual error on the Y-axis
     * @return double
     */
    double getfY();

    /**
     * Gets linear actual error on the Z-axis
     * @return double
     */
    double getfZ();

    /**
     * the actual linear absolute error
     * @return double
     */
    double getfAbs();

    /**
     * the actual linear relative error
     * @return String
     */
    String getfOtn();

    /**
     * Gets type of binding the polygon to base points
     * @return BindType
     */
    BindType getBindType();

    /**
     * Sets bindType
     * @param bindType BindType
     */
    void setBindType(BindType bindType);

    /**
     * Sets fHor
     * @param fHor
     */
    void setfHor(double fHor);

    /**
     * Sets fX
     * @param fX double fX
     */
    void setfX(double fX);

    /**
     * Sets fY
     * @param fY double fY
     */
    void setfY(double fY);

    /**
     * Sets fZ
     * @param fZ double fZ
     */
    void setfZ(double fZ);

    /**
     * Sets fAbs
     * @param fAbs double fAbs
     */
    void setfAbs(double fAbs);

    /**
     * Sets fOtn
     * @param fOtn String fOtn
     */
    void setfOtn(String fOtn);
    }
