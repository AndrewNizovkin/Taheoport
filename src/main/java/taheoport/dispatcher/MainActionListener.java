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

    /**
     * Constructor
     * @param parentFrame instance of MainWin
     */
    public MainActionListener(MainWin parentFrame) {
        this.parentFrame = parentFrame;
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
            case "tUpdate" -> catalogService.updateBasePoints(parentFrame.getMode());
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
        if (surveyService.getSurveyRepository() != null) {
            if (surveyService.containPolygon()) {
                surveyService.processSourceData();
                polygonService.loadPolList(extractService.extractPolygonProject());
                parentFrame.setMode(1);
                parentFrame.reloadPolygonEditor();
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
        switch (parentFrame.getMode()) {
            case 0 -> {
                surveyService.processSourceData();
                new ShowViewResults(parentFrame);
            }
            case 1 -> {
                processSourceData();
                if (polygonService.getPolygonRepository().getPerimeter() > 0.0) {
                    new ShowViewAdjustment(parentFrame);
                }
            }
        }
    }


    /**
     * Writes an *.dat file with the coordinates of the pickets to disk
     */
    private void processSourceData() {
        switch (parentFrame.getMode()) {
            case 0 -> {
                surveyService.processSourceData();
                JOptionPane.showMessageDialog(parentFrame,"The data has been processed successfully");
            }
            case 1 -> {
                polygonService.processSourceData();
                parentFrame.reloadPolygonEditor();
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
                parentFrame.reloadSurveyEditor();
            }
            case 1 -> {
                polygonService.newProject();
                parentFrame.reloadPolygonEditor();
            }
        }
    }

    /**
     * open Tah file
     */
    private void openFile() {
        switch (parentFrame.getMode()) {
            case 0 -> {
                surveyService.importTah();
                parentFrame.reloadSurveyEditor();
            }
            case 1 -> {
                polygonService.importPol();
                parentFrame.reloadPolygonEditor();
            }
        }
    }

    /**
     * import from Leica
     */
    private void importLeica() {
        surveyService.importLeica();
        parentFrame.reloadSurveyEditor();
        surveyService.saveProjectAs();
    }

    /**
     * import from Nicon
     */
    private void importNicon() {
        surveyService.importNicon();
        parentFrame.reloadSurveyEditor();
        surveyService.saveProjectAs();
    }

    /**
     * import from Topcon
     */
    private void importTopcon() {
        surveyService.importTopcon();
        parentFrame.reloadSurveyEditor();
        surveyService.saveProjectAs();
    }

    /**
     * save sp <SurveyProject> to Tah file to AbsoluteTahPath if it exists.
     * set absoluteTahPath
     */
    private void save() {
        switch (parentFrame.getMode()) {
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
        parentFrame.setCurrentCatalog(!catalogService.isEmpty());
    }
}
