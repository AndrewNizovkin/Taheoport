package taheoport.gui;

import javax.swing.*;

/**
 * This interface defines methods for redrawing SurveyEditor
 */
public interface SurveyEditorRenderer {

    /**
     * Sets current station index
     * @param index int
     */
    void setCurrentStationIndex(int index);

    /**
     * Gets current station index
     * @return int
     */
    int getCurrentStationIndex();

    /**
     * Gets ParentFrame
     * @return MainWin
     */
    MainWin getParentFrame();

    /**
     * Sets controls enable is true
     */
    void controlOn();

    /**
     * Sets controls enable is false
     */
    void controlOff();

    /**
     * refreshes Stations panel
     * @param index int index
     */
    void reloadStations(int index);

    /**
     * Refreshes Pickets panel
     * @param index int
     */
    void reloadStationPickets(int index);

    /**
     * Gets tmodelPickets
     * @return TmodelPickets
     */
    JTable getTablePickets();

    /**
     * Gets selected row index of tablePickets
     * @return
     */
    int getSelRow();

    /**
     * Sets
     */
    void setSelRow(int index);

    int getSelColumn();

}
