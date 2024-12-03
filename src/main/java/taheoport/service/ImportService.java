package taheoport.service;

import taheoport.repository.SurveyRepository;

import java.util.List;

/**
 * This interface defines methods for import data from different types of total stations
 */
public interface ImportService {

    /**
     * Loads from tah
     * @return SurveyProject
     */
    SurveyRepository loadTah(List<String> list);

    /**
     * Loads from Leica *.gis
     * @return SurveyProject
     */
    SurveyRepository loadLeica(List<String> list);

    /**
     * Loads from Topcon *.txt
     * @return SurveyProject
     */
    SurveyRepository loadTopcon(List<String> list);

    /**
     * Loads from Nicon *.row
     * @return SurveyProject
     */
    SurveyRepository loadNicon(List<String> list);
}
