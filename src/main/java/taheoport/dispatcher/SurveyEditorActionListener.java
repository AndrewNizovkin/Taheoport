package taheoport.dispatcher;

import taheoport.gui.ShowCatalog;
import taheoport.gui.SurveyEditorRenderer;
import taheoport.model.CatalogPoint;
import taheoport.model.SurveyStation;
import taheoport.service.CatalogService;
import taheoport.service.SurveyService;

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
//            case "btnInsertCoordinates" ->
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

    }





}
