package taheoport.controllers;

import taheoport.gui.MainWin;
import taheoport.model.Catalog;
import taheoport.model.CatalogPoint;
import taheoport.model.PolygonProject;
import taheoport.model.SurveyProject;

import javax.swing.*;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

public class CatalogControllerDefault implements CatalogController{

    private final MainWin parentFrame;

    public CatalogControllerDefault(MainWin parentFrame) {
        this.parentFrame = parentFrame;
    }

    /**
     * Downloads catalog from l
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
                    SurveyProject surveyProject = parentFrame.getSurveyProject();
                    Catalog catalog = parentFrame.getCatalog();
                    int q = 0;
                    for (int i = 0; i < surveyProject.sizeStations(); i++) {
                        for (int j = 0; j < catalog.getSizeCatalog(); j++) {
                            if (surveyProject.getStation(i).getName().equals(catalog.get(j).getName())) {
                                surveyProject.getStation(i).setName(catalog.get(j).getName());
                                surveyProject.getStation(i).setX(catalog.get(j).getX());
                                surveyProject.getStation(i).setY(catalog.get(j).getY());
                                surveyProject.getStation(i).setZ(catalog.get(j).getZ());
                                q++;
                            }
                            if (surveyProject.getStation(i).getNameOr().equals(catalog.get(j).getName())) {
                                surveyProject.getStation(i).setNameOr(catalog.get(j).getName());
                                surveyProject.getStation(i).setxOr(catalog.get(j).getX());
                                surveyProject.getStation(i).setyOr(catalog.get(j).getY());
                                surveyProject.getStation(i).setzOr(catalog.get(j).getZ());
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
                PolygonProject polygonProject = parentFrame.getPolygonProject();
                Catalog catalog = parentFrame.getCatalog();
                int q = 0;
                for (int i = 0; i < polygonProject.getSizePolygonStations(); i++) {
                    for (int j = 0; j < catalog.getSizeCatalog(); j++) {
                        if (polygonProject.getPolygonStation(i).getName().equals(catalog.get(j).getName()) &
                                polygonProject.getPolygonStation(i).getStatus()) {
                            polygonProject.getPolygonStation(i).setName(catalog.get(j).getName());
                            polygonProject.getPolygonStation(i).setX(catalog.get(j).getX());
                            polygonProject.getPolygonStation(i).setY(catalog.get(j).getY());
                            polygonProject.getPolygonStation(i).setZ(catalog.get(j).getZ());
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
