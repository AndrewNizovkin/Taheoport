package taheoport.service;

import taheoport.gui.MainWin;
import taheoport.model.PolygonStation;
import taheoport.model.SurveyStation;
import taheoport.repository.CatalogRepository;
import taheoport.model.CatalogPoint;
import taheoport.repository.PolygonRepository;
import taheoport.repository.SurveyRepository;

import javax.swing.*;
import java.util.HashMap;
import java.util.List;

/**
 * This class encapsulates data and methods for working with base point catalog
 */
public class CatalogServiceDefault implements CatalogService {

    private final MainWin parentFrame;
    private final CatalogRepository catalogRepository;
    private final PolygonService polygonService;
    private final SurveyService surveyService;
    private final IOService ioService;
    private final SettingsController settingsController;
    private String absoluteCatalogPath;
    private int choice;


    /**
     * Constructor
     * @param parentFrame
     */
    public CatalogServiceDefault(MainWin parentFrame) {
        this.parentFrame = parentFrame;
        ioService = parentFrame.getIoService();
        settingsController = parentFrame.getSettingsController();
        polygonService = parentFrame.getPolygonService();
        surveyService = parentFrame.getSurveyService();
        catalogRepository = new CatalogRepository();
        choice = -1;
    }

    /**
     * Checks catalog is Empty
     *
     * @return result
     */
    @Override
    public boolean isEmpty() {
        return catalogRepository.isEmpty();
    }

    public CatalogRepository getAllCatalogPoints() {
        return catalogRepository;
    }

    public String getAbsoluteCatalogPath() {
        return absoluteCatalogPath;
    }



    /**
     * Downloads catalog from list
     */
    @Override
    public void importCatalog() {
        List<String> list = ioService.readTextFile(
                settingsController.getPathWorkDir(),
                "kat",
                parentFrame.getTitles().get("MWloadCatalogTitle"));

        catalogRepository.clear();
        if (list != null) {
            String line;
            String[] array;
            CatalogPoint cPoint;
            absoluteCatalogPath = list.remove(0);
            while (!list.isEmpty()) {
                line = new DataHandler(list.remove(0)).compress(" ").getStr();
                array = line.split(" ");
                if (array.length >= 4) {
                    cPoint = new CatalogPoint(array[0],
                            new DataHandler(array[1]).commaToPoint().format(3).getStr(),
                            new DataHandler(array[2]).commaToPoint().format(3).getStr(),
                            new DataHandler(array[3]).commaToPoint().format(3).getStr());
                    catalogRepository.add(cPoint);
                }
            }
        }
    }

    /**
     * Updates base points with current catalog
     * @param target 0: updates surveyProject; 1: updates polygonProject
     */
    @Override
    public void updateBasePoints(int target) {
        HashMap<String, String> titles = parentFrame.getTitles();
        switch (target) {
            case 0 -> {
                    int q = 0;
                    for (SurveyStation surveyStation: surveyService.getAllStations()) {
                        for (CatalogPoint catalogPoint: catalogRepository) {
                            if (surveyStation.getName().equals(catalogPoint.getName())) {
                                surveyStation.setName(catalogPoint.getName());
                                surveyStation.setX(catalogPoint.getX());
                                surveyStation.setY(catalogPoint.getY());
                                surveyStation.setZ(catalogPoint.getZ());
                                q++;
                            }
                            if (surveyStation.getNameOr().equals(catalogPoint.getName())) {
                                surveyStation.setNameOr(catalogPoint.getName());
                                surveyStation.setxOr(catalogPoint.getX());
                                surveyStation.setyOr(catalogPoint.getY());
                                surveyStation.setzOr(catalogPoint.getZ());
                                q++;
                            }
                        }
                    }
                    JOptionPane.showMessageDialog(parentFrame,
                            q + titles.get("MWupdateMessage"),
                            titles.get("MWupdateMessageTitle"),
                            JOptionPane.INFORMATION_MESSAGE);
                parentFrame.reloadSurveyEditor();
            }

            case 1 -> {
                int q = 0;
                for (PolygonStation polygonStation: polygonService.getAllPolygonStations()) {
                    for (CatalogPoint catalogPoint: catalogRepository) {
                        if (polygonStation.getName().equals(catalogPoint.getName()) &
                                polygonStation.getStatus()) {
                            polygonStation.setName(catalogPoint.getName());
                            polygonStation.setX(catalogPoint.getX());
                            polygonStation.setY(catalogPoint.getY());
                            polygonStation.setZ(catalogPoint.getZ());
                            q++;
                        }
                    }
                }
                JOptionPane.showMessageDialog(parentFrame,
                        q + titles.get("MWupdateMessage"),
                        titles.get("MWupdateMessageTitle"),
                        JOptionPane.INFORMATION_MESSAGE);

                parentFrame.reloadPolygonEditor();
            }
        }
    }

    /**
     * Sets the index of the selected item
     *
     * @param choice int
     */
    @Override
    public void setChoice(int choice) {
        this.choice = choice;
    }

    /**
     * Gets the index of the selected item
     */
    @Override
    public int getChoice() {
        return choice;
    }

    /**
     * Gets catalogPoint by id
     *
     * @return CatalogPoint
     */
    @Override
    public CatalogPoint findById(int id) {
        return catalogRepository.findById(id);
    }
}
