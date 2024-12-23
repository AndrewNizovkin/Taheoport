package taheoport.gui;

import javax.swing.*;

/**
 * This interface defines methods for redrawing SurveyEditor
 */
public interface PolygonEditorRenderer {

    /**
     * Gets polygon stations table
     * @return JTable
     */
    TmodelPolygonStations getModel();

    /**
     * Gets index of selected row in polygon station table
     * @return int index
     */
    int getSelRow();

    /**
     * Sets new value to selRow
     * @param selRow int index
     */
    void setSelRow(int selRow);

    /**
     * Gets index of selected column in polygon station table
     * @return int
     */
    int getSelColumn();

    /**
     * set focus on tblStations
     */
    void setFocusTable(int row, int column);

    /**
     * sets bindings value
     */
    void setBindings();

    }
