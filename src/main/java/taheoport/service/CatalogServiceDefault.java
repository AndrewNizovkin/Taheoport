package taheoport.service;

import taheoport.gui.MainWin;
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

    public CatalogRepository getCatalogRepository() {
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
                if (parentFrame.getCatalogRepository() != null & parentFrame.getSurveyRepository() != null) {
                    SurveyRepository surveyRepository = parentFrame.getSurveyRepository();
                    CatalogRepository catalogRepository = parentFrame.getCatalogRepository();
                    int q = 0;
                    for (int i = 0; i < surveyRepository.sizeStations(); i++) {
                        for (int j = 0; j < catalogRepository.getSizeCatalog(); j++) {
                            if (surveyRepository.findById(i).getName().equals(catalogRepository.get(j).getName())) {
                                surveyRepository.findById(i).setName(catalogRepository.get(j).getName());
                                surveyRepository.findById(i).setX(catalogRepository.get(j).getX());
                                surveyRepository.findById(i).setY(catalogRepository.get(j).getY());
                                surveyRepository.findById(i).setZ(catalogRepository.get(j).getZ());
                                q++;
                            }
                            if (surveyRepository.findById(i).getNameOr().equals(catalogRepository.get(j).getName())) {
                                surveyRepository.findById(i).setNameOr(catalogRepository.get(j).getName());
                                surveyRepository.findById(i).setxOr(catalogRepository.get(j).getX());
                                surveyRepository.findById(i).setyOr(catalogRepository.get(j).getY());
                                surveyRepository.findById(i).setzOr(catalogRepository.get(j).getZ());
                                q++;
                            }
                        }
                    }
                    JOptionPane.showMessageDialog(parentFrame,
                            q + titles.get("MWupdateMessage"),
                            titles.get("MWupdateMessageTitle"),
                            JOptionPane.INFORMATION_MESSAGE);
                }
                parentFrame.reloadSurveyEditor();
            }

            case 1 -> {
                PolygonRepository polygonRepository = parentFrame.getPolygonRepository();
                CatalogRepository catalogRepository = parentFrame.getCatalogRepository();
                int q = 0;
                for (int i = 0; i < polygonRepository.getSizePolygonStations(); i++) {
                    for (int j = 0; j < catalogRepository.getSizeCatalog(); j++) {
                        if (polygonRepository.findById(i).getName().equals(catalogRepository.get(j).getName()) &
                                polygonRepository.findById(i).getStatus()) {
                            polygonRepository.findById(i).setName(catalogRepository.get(j).getName());
                            polygonRepository.findById(i).setX(catalogRepository.get(j).getX());
                            polygonRepository.findById(i).setY(catalogRepository.get(j).getY());
                            polygonRepository.findById(i).setZ(catalogRepository.get(j).getZ());
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
