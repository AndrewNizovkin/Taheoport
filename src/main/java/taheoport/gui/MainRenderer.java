package taheoport.gui;

public interface MainRenderer {

    /**
     * Sets or off current catalog
     * @param turnOn choice action
     */
    void setCurrentCatalog(boolean turnOn);

    /**
     * Gets parentFrame
     * @return MainWin
     */
    MainWin getParentFrame();

    /**
     * Reload surveyEditor
     */
    void reloadSurveyEditor();

    /**
     * Reload PolygonEditor
     */
    void reloadPolygonEditor();

    /**
     * Gets index of active tab
     * @return int
     */
    int getMode();

    /**
     * Sets current mode
     * @param mode int mode
     */
    void setMode(int mode);

    /**
     * Translate interface
     */
    void translate();


}
