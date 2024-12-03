package taheoport.repository;

import taheoport.gui.MainWin;
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
    private String absoluteTahPath;
    private final LinkedList <SurveyStation> surveyStations;
//    private final MainWin parentFrame;

    /**
     * Constructor
//     * @param parentFrame MainWin
     */
    public SurveyRepository() {
//        this.parentFrame = parentFrame;
        absoluteTahPath = "";
        surveyStations = new LinkedList <>();
    }

    /**
     * Appends new element Station to the end of list ll
     * @param sName Station name
     * @param sXst Station coordinate X, measured in metres
     * @param sYst Station coordinate Y, measured in metres
     * @param sZst Station coordinate Z, measured in metres
     * @param sNameOr Oriented name
     * @param sXor Oriented coordinate X, measured in metres
     * @param sYor Oriented coordinate Y, measured in metres
     * @param sZor Oriented coordinate Z, measured in metres
     * @param sI Tool height
     * @return Station
     */
     public SurveyStation addStation(String sName,
                                     String sXst,
                                     String sYst,
                                     String sZst,
                                     String sNameOr,
                                     String sXor,
                                     String sYor,
                                     String sZor,
                                     String sI) throws NullPointerException{
        surveyStations.add(new SurveyStation(sName,
                sXst,
                sYst,
                sZst,
                sNameOr,
                sXor,
                sYor,
                sZor,
                sI));
        return surveyStations.getLast();
    }

    /**
     * Appends new Empty instance of SurveyStation to the end of this.surveyStations
     * @return Station
     */
    public SurveyStation addStation() {
        surveyStations.add(new SurveyStation());
         return surveyStations.getLast();
    }

    /**
     * Addes new instance SurveyStation to this.surveyStations to index position
     * @param index int index
     * @return SurveyStation
     */
    public SurveyStation addStation(int index) {
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
    public void addStation(SurveyStation surveyStation) {
        surveyStations.add(surveyStation);
    }

    /**
     * Rerurns the element (Station) at the specfied position of this.surveyStations
     * @param index element index
     * @return element (Station)
     */
    public SurveyStation getStation(int index){
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
        for (SurveyStation llStation : surveyStations) {
            if (llStation.sizePickets() < 2) {
                return false;
            }
        }
        return true;
    }
}

