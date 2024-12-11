package taheoport.dispatcher;

import taheoport.gui.ShowCatalog;
import taheoport.gui.SurveyEditorRenderer;
import taheoport.gui.TmodelPickets;
import taheoport.model.CatalogPoint;
import taheoport.model.SurveyStation;
import taheoport.service.CatalogService;
import taheoport.service.SurveyService;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SurveyEditorActionListener implements ActionListener {
    private final SurveyEditorRenderer renderer;
    private final CatalogService catalogService;
    private final SurveyService surveyService;

    /**
     * Constructor
     * @param surveyEditorRenderer SurveyEditorRenderer
     */
    public SurveyEditorActionListener(SurveyEditorRenderer surveyEditorRenderer) {
        renderer = surveyEditorRenderer;
        catalogService = renderer.getParentFrame().getCatalogService();
        surveyService = renderer.getParentFrame().getSurveyService();
    }


    /**
     * Invoked when an action occurs.
     *
     * @param e the event to be processed
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        switch (e.getActionCommand()) {
            case "btnStationName" -> setStationFromCatalog();
            case "btnOrName" -> setOrFromCatalog();
            case "btnDeleteStation" -> deleteStation();
            case "btnInsertStationBefore" -> insertStation();
            case "btnInsertStationAfter" -> {
                renderer.setCurrentStationIndex(renderer.getCurrentStationIndex() + 1);
                insertStation();
            }
            case "btnDeleteRow" -> removePicket();
            case "btnInsertRowBefore" -> insertPicketBeforeCurrentPosition();
            case "btnInsertRowAfter" -> insertPicketAfterCurrentPosition();

        }


    }

    /**
     * Sets station coordinates from catalog
     */
    private void setStationFromCatalog() {
        catalogService.setChoice(-1);
        new ShowCatalog(renderer.getParentFrame());
        if (catalogService.getChoice() >=0) {
            SurveyStation surveyStation = surveyService.findStationById(renderer.getCurrentStationIndex());
            CatalogPoint catalogPoint = catalogService.findById(catalogService.getChoice());
            surveyStation.setName(catalogPoint.getName());
            surveyStation.setX(catalogPoint.getX());
            surveyStation.setY(catalogPoint.getY());
            surveyStation.setZ(catalogPoint.getZ());
            renderer.updateStation(surveyStation);
        }
    }

    /**
     * Sets orientir coordinates from catalog
     */
    private void setOrFromCatalog() {
        catalogService.setChoice(-1);
        new ShowCatalog(renderer.getParentFrame());
        if (catalogService.getChoice() >=0) {
            SurveyStation surveyStation = surveyService.findStationById(renderer.getCurrentStationIndex());
            CatalogPoint catalogPoint = catalogService.findById(catalogService.getChoice());
            surveyStation.setNameOr(catalogPoint.getName());
            surveyStation.setxOr(catalogPoint.getX());
            surveyStation.setyOr(catalogPoint.getY());
            surveyStation.setzOr(catalogPoint.getZ());
            renderer.updateStation(surveyStation);
        }
    }

    /**
     * Removes current survey station
     */
    private void deleteStation() {
        if (surveyService.sizeRepository() > 1) {
            surveyService.removeStation(renderer.getCurrentStationIndex());
            if (renderer.getCurrentStationIndex() == surveyService.sizeRepository()) {
                renderer.setCurrentStationIndex(renderer.getCurrentStationIndex() - 1);
            }
            renderer.reloadStations(renderer.getCurrentStationIndex());
            renderer.reloadStationPickets(renderer.getCurrentStationIndex());
        }
    }

    /**
     * Inserts new station before current position
     */
    private void insertStation() {
        surveyService.insertStation(renderer.getCurrentStationIndex());
        renderer.reloadStations(renderer.getCurrentStationIndex());
        renderer.reloadStationPickets(renderer.getCurrentStationIndex());
    }

    /**
     * Remove picket from current station
     */
    private void removePicket() {
        JTable tblPickets = renderer.getTablePickets();
        TmodelPickets tmodelPickets = (TmodelPickets) tblPickets.getModel();
        if (tmodelPickets.getRowCount() > 1) {
            int k = renderer.getSelRow();
            surveyService.removePicket(renderer.getCurrentStationIndex(), renderer.getSelRow());
            tmodelPickets.removeRow(renderer.getSelRow());
            if (k == surveyService.findStationById(renderer.getCurrentStationIndex()).sizePickets()) {
                k--;
            }
            renderer.setSelRow(k);
            setPicketsSelectionInterval();
        }
    }

    /**
     * Inserts new picket before current position
     */
    private void insertPicketBeforeCurrentPosition() {
        JTable tblPickets = renderer.getTablePickets();
        if (renderer.getSelRow() >= 0) {
            surveyService.insertPicket(renderer.getCurrentStationIndex(), renderer.getSelRow());
            TmodelPickets model = (TmodelPickets) tblPickets.getModel();
            model.addRow(renderer.getSelRow(), new String[]{"noname", "0.000", "0.0000", "0.0000", "0.000"});
            renderer.setSelRow(renderer.getSelRow() - 1);
            setPicketsSelectionInterval();
        }
    }

    /**
     * Inserts new picket after current position
     */
    private void insertPicketAfterCurrentPosition() {
        JTable tblPickets = renderer.getTablePickets();
        if (renderer.getSelRow() >= 0) {
            renderer.setSelRow(renderer.getSelRow() + 1);
            surveyService.insertPicket(renderer.getCurrentStationIndex(), renderer.getSelRow());
            TmodelPickets model = (TmodelPickets) tblPickets.getModel();
            model.addRow(renderer.getSelRow(), new String[]{"noname", "0.000", "0.0000", "0.0000", "0.000"});
            setPicketsSelectionInterval();
        }
    }

    /**
     * Sets selection interval for pickets table
     */
    private void setPicketsSelectionInterval() {
        JTable tblPickets = renderer.getTablePickets();
        tblPickets.getSelectionModel().setSelectionInterval(renderer.getSelRow(), renderer.getSelRow());
        tblPickets.getColumnModel().getSelectionModel().setSelectionInterval(renderer.getSelRow(), renderer.getSelColumn());
        tblPickets.requestFocusInWindow();
    }

}
