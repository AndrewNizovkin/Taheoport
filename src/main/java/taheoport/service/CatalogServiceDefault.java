package taheoport.service;

import taheoport.gui.MainWin;
import taheoport.model.Catalog;
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

    public CatalogServiceDefault(MainWin parentFrame) {
        this.parentFrame = parentFrame;
    }

    /**
     * Downloads catalog from list
     * @param list LinkedList
     */
    @Override
    public Catalog loadCatalogList(List<String> list) {
        Catalog catalog = new Catalog();
        if (list != null) {
            String line;
            String[] array;
            CatalogPoint cPoint;
            catalog.setAbsoluteCatalogPath(list.remove(0));
            while (!list.isEmpty()) {
                line = new DataHandler(list.remove(0)).compress(" ").getStr();
                array = line.split(" ");
                if (array.length >= 4) {
                    cPoint = new CatalogPoint(array[0],
                            new DataHandler(array[1]).commaToPoint().format(3).getStr(),
                            new DataHandler(array[2]).commaToPoint().format(3).getStr(),
                            new DataHandler(array[3]).commaToPoint().format(3).getStr());
                    catalog.add(cPoint);
                }
            }
        }
        return catalog;
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
                if (parentFrame.getCatalog() != null & parentFrame.getSurveyProject() != null) {
                    SurveyRepository surveyRepository = parentFrame.getSurveyProject();
                    Catalog catalog = parentFrame.getCatalog();
                    int q = 0;
                    for (int i = 0; i < surveyRepository.sizeStations(); i++) {
                        for (int j = 0; j < catalog.getSizeCatalog(); j++) {
                            if (surveyRepository.getStation(i).getName().equals(catalog.get(j).getName())) {
                                surveyRepository.getStation(i).setName(catalog.get(j).getName());
                                surveyRepository.getStation(i).setX(catalog.get(j).getX());
                                surveyRepository.getStation(i).setY(catalog.get(j).getY());
                                surveyRepository.getStation(i).setZ(catalog.get(j).getZ());
                                q++;
                            }
                            if (surveyRepository.getStation(i).getNameOr().equals(catalog.get(j).getName())) {
                                surveyRepository.getStation(i).setNameOr(catalog.get(j).getName());
                                surveyRepository.getStation(i).setxOr(catalog.get(j).getX());
                                surveyRepository.getStation(i).setyOr(catalog.get(j).getY());
                                surveyRepository.getStation(i).setzOr(catalog.get(j).getZ());
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
                PolygonRepository polygonRepository = parentFrame.getPolygonProject();
                Catalog catalog = parentFrame.getCatalog();
                int q = 0;
                for (int i = 0; i < polygonRepository.getSizePolygonStations(); i++) {
                    for (int j = 0; j < catalog.getSizeCatalog(); j++) {
                        if (polygonRepository.getPolygonStation(i).getName().equals(catalog.get(j).getName()) &
                                polygonRepository.getPolygonStation(i).getStatus()) {
                            polygonRepository.getPolygonStation(i).setName(catalog.get(j).getName());
                            polygonRepository.getPolygonStation(i).setX(catalog.get(j).getX());
                            polygonRepository.getPolygonStation(i).setY(catalog.get(j).getY());
                            polygonRepository.getPolygonStation(i).setZ(catalog.get(j).getZ());
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
}
