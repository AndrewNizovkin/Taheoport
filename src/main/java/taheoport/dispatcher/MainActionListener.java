package taheoport.dispatcher;

import taheoport.gui.*;
import taheoport.model.BindType;
import taheoport.model.CatalogPoint;
import taheoport.model.PolygonStation;
import taheoport.model.SurveyStation;
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
    private final JFrame parentFrame;
    private final SurveyService surveyService;
    private final PolygonService polygonService;
    private final CatalogService catalogService;
    private final ExtractService extractService;
    private final MainRenderer renderer;
    private final DependencyInjector dependencyInjector;
    /**
     * Constructor
     * @param dependencyInjector DependencyInjector
     */
    public MainActionListener(DependencyInjector dependencyInjector, MainRenderer mainRenderer) {
        this.dependencyInjector = dependencyInjector;
        renderer = mainRenderer;
        parentFrame = renderer.getParentFrame();
        surveyService = dependencyInjector.getSurveyService();
        polygonService = dependencyInjector.getPolygonService();
        catalogService = dependencyInjector.getCatalogService();
        extractService = dependencyInjector.getExtractService();
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
            case "tUpdate" -> updateBasePoints();
            case "tRun", "btnRun" -> processSourceData();
            case "tView", "btnView" -> viewResult();
            case "tExtractPol" -> extractPol();
            case "tOptions" -> new ShowSettings(dependencyInjector, renderer);
            case "hAbout" -> new ShowAbout(dependencyInjector);
            case "hHelp" -> new ShowHelp(dependencyInjector);
        }
    }


    /**
     * Updates basePoints
     */
    private void updateBasePoints() {
        switch (renderer.getMode()) {
            case 0 -> {
                surveyService.updateBasePoints();
                renderer.reloadSurveyEditor();
            }

            case 1 -> {
                polygonService.updateBasePoints();
                polygonService.setBindType(BindType.ZZ);
                renderer.reloadPolygonEditor();
            }
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
                polygonService.setBindType(BindType.ZZ);
                renderer.reloadPolygonEditor();
                new ShowViewExtractPol(dependencyInjector);
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
                new ShowViewResults(dependencyInjector);
            }
            case 1 -> {
                processSourceData();
                if (polygonService.getPerimeter() > 0.0) {
                    new ShowViewAdjustment(dependencyInjector);
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
        switch (renderer.getMode()) {
            case 0 -> {
                surveyService.newProject();
                renderer.reloadSurveyEditor();
            }
            case 1 -> {
                polygonService.newProject();
                polygonService.setBindType(BindType.ZZ);
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
                polygonService.setBindType(BindType.ZZ);
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
        switch (renderer.getMode()) {
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
