package taheoport.dispatcher;

import taheoport.gui.*;
import taheoport.model.CatalogPoint;
import taheoport.model.SurveyStation;
import taheoport.service.*;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SurveyEditorActionListener implements ActionListener {
    private final SurveyEditorRenderer renderer;
    private final CatalogService catalogService;
    private final SurveyService surveyService;
    private final SettingsService settingsService;
    private final DependencyInjector dependencyInjector;
    private final Shell shell;

    /**
     * Constructor
     * @param surveyEditorRenderer SurveyEditorRenderer
     */
    public SurveyEditorActionListener(SurveyEditorRenderer surveyEditorRenderer) {
        dependencyInjector = DependencyContainer.getInstance();
        renderer = surveyEditorRenderer;
        catalogService = dependencyInjector.getCatalogService();
        surveyService = dependencyInjector.getSurveyService();
        settingsService = dependencyInjector.getSettingsService();
        shell = dependencyInjector.getShell();
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
            case "btnChangeDistance" -> changeDistance();
            case "btnChangeDirection" -> changeDirection();
            case "btnChangeTilt" -> changeTilt();
        }
    }

    /**
     * Sets station coordinates from catalog
     */
    private void setStationFromCatalog() {
        new ShowCatalog();
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
//        catalogService.setChoice(-1);
        new ShowCatalog();
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

    /**
     * Change Distance in the tblPickets
     */
    private void changeDistance() {
        TmodelPickets tmodelPickets = (TmodelPickets) renderer.getTablePickets().getModel();
        new ShowChangeDistance();
        if (settingsService.isChanged()) {
            String str;
            double line = Double.parseDouble((String) tmodelPickets.getValueAt(renderer.getSelRow(), 1));
            double tilt = new DataHandler((String) tmodelPickets.getValueAt(renderer.getSelRow(), 3)).dmsToRad();
            double offset = Double.parseDouble(settingsService.getOffsetDistance());
            switch (settingsService.getOffsetDistanceType()) {
                case 0 -> {
                    str = new DataHandler(line + offset / Math.cos(tilt)).format(3).getStr();
                    tmodelPickets.setValueAt(str, renderer.getSelRow(), 1);
                }
                case 1 -> {
                    str = new DataHandler(line + offset).format(3).getStr();
                    tmodelPickets.setValueAt(str, renderer.getSelRow(), 1);
                }
            }
        }
        setPicketsSelectionInterval();
    }

    /**
     * Changes Direction in the tblPickets
     */
    private void changeDirection() {
        TmodelPickets tmodelPickets = (TmodelPickets) renderer.getTablePickets().getModel();
        new ShowChangeAngle(shell.getTitles().get("SCAtitleChangeDirection"));
        if (settingsService.isChanged()) {
            switch (settingsService.getOffsetDirectionType()) {
                case 0 -> {
                    if (renderer.getSelRow() < tmodelPickets.getRowCount() - 1) {
                        tmodelPickets.setValueAt(tmodelPickets.getValueAt(
                                renderer.getSelRow() + 1,
                                2),
                                renderer.getSelRow(),
                                2);
                    }
                }
                case 1 -> {
                    double angle = new DataHandler((String) tmodelPickets.getValueAt(renderer.getSelRow(), 2)).dmsToDeg();
                    double offset = new DataHandler(settingsService.getOffsetDirection()).dmsToDeg();
                    angle += offset;
                    while (angle < 0) {
                        angle += 360;
                    }
                    while (angle > 360) {
                        angle -= 360;
                    }
                    tmodelPickets.setValueAt(new DataHandler().degToDms(angle).getStr(), renderer.getSelRow(), 2);
                }
            }
        }
        setPicketsSelectionInterval();
    }

    /**
     * Changes Direction in the tblPickets
     */
    private void changeTilt() {
        TmodelPickets tmodelPickets = (TmodelPickets) renderer.getTablePickets().getModel();
        int selRow = renderer.getSelRow();
        new ShowChangeAngle(shell.getTitles().get("SCAtitleChangeTiltAngle"));
        if (settingsService.isChanged()) {
            double line = Double.parseDouble((String) tmodelPickets.getValueAt(selRow, 1));
            double tilt = new DataHandler((String) tmodelPickets.getValueAt(selRow, 3)).dmsToRad();
            switch (settingsService.getOffsetTiltType()) {
                case 0 -> {
                    if (selRow < tmodelPickets.getRowCount() - 1) {
                        double tiltNext = new DataHandler((String) tmodelPickets.getValueAt(selRow + 1, 3)).dmsToRad();
                        line = line * Math.cos(tilt) / Math.cos(tiltNext);
                        tmodelPickets.setValueAt(new DataHandler(line).format(3).getStr(), selRow, 1);
                        tmodelPickets.setValueAt(tmodelPickets.getValueAt(selRow + 1, 3), selRow, 3);
                        tmodelPickets.setValueAt("0.000", selRow,4);
                    }

                }
                case 1 -> {
                    double angle = new DataHandler((String) tmodelPickets.getValueAt(selRow, 3)).dmsToDeg();
                    double offset = new DataHandler(settingsService.getOffsetTiltAngle()).dmsToDeg();
                    line = line * Math.cos(tilt) / Math.cos(new DataHandler().degToDms(angle + offset).dmsToRad());
                    tmodelPickets.setValueAt(new DataHandler(line).format(3).getStr(), selRow, 1);
                    tmodelPickets.setValueAt(new DataHandler().degToDms(angle + offset).getStr(), selRow, 3);
                }
            }
        }
        setPicketsSelectionInterval();
    }

}
