package taheoport.controllers;

import taheoport.model.SurveyProject;

import java.util.LinkedList;

public interface ImportController {

    /**
     * Loads from tah
     * @return SurveyProject
     */
    public SurveyProject loadTah(LinkedList<String> list);

    /**
     * Loads from Leica *.gis
     * @return SurveyProject
     */
    public SurveyProject loadLeica(LinkedList<String> list);

    /**
     * Loads from Topcon *.txt
     * @return SurveyProject
     */
    public SurveyProject loadTopcon(LinkedList<String> list);

    /**
     * Loads from Nicon *.row
     * @return SurveyProject
     */
    public SurveyProject loadNicon(LinkedList<String> list);
}
