package taheoport.service;

import taheoport.model.SurveyProject;

import java.util.List;

public interface ImportController {

    /**
     * Loads from tah
     * @return SurveyProject
     */
    SurveyProject loadTah(List<String> list);

    /**
     * Loads from Leica *.gis
     * @return SurveyProject
     */
    SurveyProject loadLeica(List<String> list);

    /**
     * Loads from Topcon *.txt
     * @return SurveyProject
     */
    SurveyProject loadTopcon(List<String> list);

    /**
     * Loads from Nicon *.row
     * @return SurveyProject
     */
    SurveyProject loadNicon(List<String> list);
}
