package taheoport.service;

import taheoport.dispatcher.DependencyInjector;
import taheoport.gui.MainWin;
import taheoport.model.PolygonStation;
import taheoport.model.SurveyStation;
import taheoport.repository.CatalogRepository;
import taheoport.model.CatalogPoint;

import javax.swing.*;
import java.util.HashMap;
import java.util.List;

/**
 * This class encapsulates data and methods for working with base point catalog
 */
public class CatalogServiceDefault implements CatalogService {
    private final CatalogRepository catalogRepository;
    private final IOService ioService;
    private final SettingsService settingsService;
    private final Shell shell;
    private String absoluteCatalogPath;
    private int choice;


    /**
     * Constructor
     * @param dependencyInjector DependencyInjector
     */
    public CatalogServiceDefault(DependencyInjector dependencyInjector) {
        shell = dependencyInjector.getShell();
        ioService = dependencyInjector.getIoService();
        settingsService = dependencyInjector.getSettingsService();
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
                settingsService.getPathWorkDir(),
                "kat",
                shell.getTitles().get("MWloadCatalogTitle"));

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
