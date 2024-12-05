package taheoport.repository;

import taheoport.model.SurveyStation;

import java.util.*;

/**
 * This class represents a total station survey model.
 * It provides various methods for importing field information
 * from various types of storage devices (total stations).
 * Version information
 * @version 1.0
 * Date
 * @author Andrew Nizovkin
 * Copyright Nizovkin A.V. 2022
 */
public class SurveyRepository {
//    private SurveyRepository surveyRepository;
    private String absoluteTahPath;
    private final List <SurveyStation> surveyStations;

    /**
     * Constructor
     */
    public SurveyRepository() {
        absoluteTahPath = "";
        surveyStations = new LinkedList <>();
    }

    /**
     * Removes all records from surveyStations
     */
    public void clean() {
        surveyStations.clear();
        absoluteTahPath = "";
    }

    /**
     * Inserts new instance SurveyStation to this.surveyStations to index position
     * @param index int index
     * @return SurveyStation
     */
    public SurveyStation insertStation(int index) {
        SurveyStation surveyStation;
        surveyStations.add(index, new SurveyStation());
        surveyStation = surveyStations.get(index);
        surveyStation.addPicket();
        return surveyStation;
    }

    /**
     * Addes new instance SurveyStation to this.surveyStations to end position
     * @param surveyStation instance of SurveyStation
     */
    public SurveyStation addStation(SurveyStation surveyStation) {
        surveyStations.add(surveyStation);
        return surveyStation;
    }

    /**
     * Gets the element (Station) at the specified position of this.surveyStations
     * @param index element index
     * @return element (Station)
     */
    public SurveyStation findById(int index){
            return surveyStations.get(index);
    }

    /**
     * Removes the element (Station) at the specified position this.surveyStations
     * @param index element index
     */
    public void removeStation(int index){
        surveyStations.remove(index);
    }

    /**
     * Return the size of list  of Stations
     * @return int size list of Stations
     */
    public int sizeStations(){
        return surveyStations.size();
    }

    /**
     * Set absolute path to current file tah
     * @param absoluteTahPath String
     */
    public void setAbsoluteTahPath(String absoluteTahPath) {
        this.absoluteTahPath = absoluteTahPath;
    }

    /**
     * Get absolute path to current file tah
     * @return String
     */
    public String getAbsoluteTahPath() {
        return absoluteTahPath;
    }


    /**
     * checks the possibility to get TheoProject from this SurveyProfect
     * @return boolean
     */
    public boolean containPolygon() {
        for (SurveyStation surveyStation : surveyStations) {
            if (surveyStation.sizePickets() < 2) {
                return false;
            }
        }
        return true;
    }
}

