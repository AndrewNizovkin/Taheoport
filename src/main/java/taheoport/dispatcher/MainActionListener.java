package taheoport.dispatcher;

import taheoport.gui.*;
import taheoport.service.CatalogService;
import taheoport.service.ExtractService;
import taheoport.service.PolygonService;
import taheoport.service.SurveyService;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * This class provides scripts for handling events of the main frame of the user interface
 * @author Nizovkin A.V.
 */
public class MainActionListener implements ActionListener {
    private final MainWin parentFrame;
    private final SurveyService surveyService;
    private final PolygonService polygonService;
    private final CatalogService catalogService;
    private final ExtractService extractService;
    private final MainRenderer renderer;
    /**
     * Constructor
     * @param mainRenderer MainRenderer
     */
    public MainActionListener(MainRenderer mainRenderer) {
        renderer = mainRenderer;
        parentFrame = renderer.getParentFrame();
        surveyService = parentFrame.getSurveyService();
        polygonService = parentFrame.getPolygonService();
        catalogService = parentFrame.getCatalogService();
        extractService = parentFrame.getExtractService();
    }

    /**
     * Invoked when an action occurs.
     * @param e the event to be processed
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        switch (e.getActionCommand()) {
            case "fExit" -> System.exit(0);
            case "fNew", "bntNew" -> newFile();
            case "fOpen", "btnOpen" -> openFile();
            case "iLeica", "ppiLeica" -> importLeica();
            case "iNicon", "ppiNicon" -> importNicon();
            case "iTopcon", "ppiTopcon" -> importTopcon();
            case "fSave", "btnSave" -> save();
            case "fSaveAs" -> saveAs();
            case "tLoadCat", "btnLoadCat" -> loadCatalog();
            case "tUpdate" -> catalogService.updateBasePoints(renderer.getMode());
            case "tRun", "btnRun" -> processSourceData();
            case "tView", "btnView" -> viewResult();
            case "tExtractPol" -> extractPol();
            case "tOptions" -> new ShowSettings(parentFrame);
            case "hAbout" -> new ShowAbout(parentFrame);
            case "hHelp" -> new ShowHelp(parentFrame);
        }
    }

    /**
     * extracts polygon from survey project and open new polygon project
     */
    private void extractPol() {
        if (surveyService.getAllStations() != null) {
            if (surveyService.containPolygon()) {
                surveyService.processSourceData();
                polygonService.loadPolList(extractService.extractPolygonProject());
                renderer.setMode(1);
                renderer.reloadPolygonEditor();
                new ShowViewExtractPol(parentFrame);
            } else {
                JOptionPane.showMessageDialog(
                        parentFrame,
                        "The measurement set does not contain a polygon",
                        "Error",
                        JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    /**
     * Show result of processing or Adjustments
     */
    private void viewResult() {
        switch (renderer.getMode()) {
            case 0 -> {
                surveyService.processSourceData();
                new ShowViewResults(parentFrame);
            }
            case 1 -> {
                processSourceData();
                if (polygonService.getPerimeter() > 0.0) {
                    new ShowViewAdjustment(parentFrame);
                }
            }
        }
    }


    /**
     * Writes an *.dat file with the coordinates of the pickets to disk
     */
    private void processSourceData() {
        switch (renderer.getMode()) {
            case 0 -> {
                surveyService.processSourceData();
                JOptionPane.showMessageDialog(parentFrame,"The data has been processed successfully");
            }
            case 1 -> {
                polygonService.processSourceData();
                renderer.reloadPolygonEditor();
            }
        }
    }

    /**
     * Create new sp with one Station with one Picket
     */
    private void newFile() {
        switch (parentFrame.getMode()) {
            case 0 -> {
                surveyService.newProject();
                renderer.reloadSurveyEditor();
            }
            case 1 -> {
                polygonService.newProject();
                renderer.reloadPolygonEditor();
            }
        }
    }

    /**
     * open Tah file
     */
    private void openFile() {
        switch (renderer.getMode()) {
            case 0 -> {
                surveyService.importTah();
                renderer.reloadSurveyEditor();
            }
            case 1 -> {
                polygonService.importPol();
                renderer.reloadPolygonEditor();
            }
        }
    }

    /**
     * import from Leica
     */
    private void importLeica() {
        surveyService.importLeica();
        renderer.reloadSurveyEditor();
        surveyService.saveProjectAs();
    }

    /**
     * import from Nicon
     */
    private void importNicon() {
        surveyService.importNicon();
        renderer.reloadSurveyEditor();
        surveyService.saveProjectAs();
    }

    /**
     * import from Topcon
     */
    private void importTopcon() {
        surveyService.importTopcon();
        renderer.reloadSurveyEditor();
        surveyService.saveProjectAs();
    }

    /**
     * save sp <SurveyProject> to Tah file to AbsoluteTahPath if it exists.
     * set absoluteTahPath
     */
    private void save() {
        switch (renderer.getMode()) {
            case 0 -> {
                surveyService.saveProject();
                parentFrame.setTitle("Taheoport: " + surveyService.getAbsoluteTahPath());
            }
            case 1 -> {
                polygonService.savePol();
                parentFrame.setTitle("Taheoport: " + polygonService.getAbsolutePolPath());
            }
        }
    }

    /**
     * save survey project to Tah file
     * set absoluteTahPath
     */
    private void saveAs() {
        switch (parentFrame.getMode()) {
            case 0 -> {
                surveyService.saveProjectAs();
                parentFrame.setTitle("Taheoport: " + surveyService.getAbsoluteTahPath());
            }
            case 1 -> {
                polygonService.savePolAs();
                parentFrame.setTitle("Taheoport: " + polygonService.getAbsolutePolPath());
            }
        }
    }

    /**
     * This action load points coordinates from text file *.kat
     */
    private void loadCatalog() {
        catalogService.importCatalog();
        renderer.setCurrentCatalog(!catalogService.isEmpty());
    }
}
