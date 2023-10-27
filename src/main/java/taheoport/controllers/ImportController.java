package taheoport.controllers;

import taheoport.model.SurveyProject;

import java.util.LinkedList;

public interface ImportController {

    /**
     * Loads from tah
     * @return SurveyProject
     */
    SurveyProject loadTah(LinkedList<String> list);

    /**
     * Loads from Leica *.gis
     * @return SurveyProject
     */
    SurveyProject loadLeica(LinkedList<String> list);

    /**
     * Loads from Topcon *.txt
     * @return SurveyProject
     */
    SurveyProject loadTopcon(LinkedList<String> list);

    /**
     * Loads from Nicon *.row
     * @return SurveyProject
     */
    SurveyProject loadNicon(LinkedList<String> list);
}
