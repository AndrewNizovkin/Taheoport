package taheoport.dispatcher;

import taheoport.gui.MainRenderer;
import taheoport.service.ManualService;
import taheoport.service.Shell;
import taheoport.service.*;

import javax.swing.*;
import java.util.HashMap;

/**
 * This interface defines methods for injection dependencies in objects
 */
public interface DependencyInjector {

    /**
     * Gets reference to main frame
     * @return JFrame
     */
    JFrame getMainFrame();

    /**
     * Gets IOService
     * @return Instance of IOController
     */
    IOService getIoService();

    /**
     * Gets ImportService
     * @return instance of ImportService
     */
    ImportService getImportService();

    /**
     * Gets CatalogService
     * @return instance of CatalogService
     */
    CatalogService getCatalogService();

    /**
     * Gets SurveyService
     * @return instance of SurveyService
     */
    SurveyService getSurveyService();

    /**
     * Gets PolygonService
     * @return instance of PolygonService
     */
    PolygonService getPolygonService();

    /**
     * Gets ExtractService
     * @return instance of ExtractService
     */
    ExtractService getExtractService();

    /**
     * Gets SettingsService
     * @return instance of SettingService
     */
    SettingsService getSettingsService();

    /**
     * gets HashMap titles
     * @return titles
     */
    HashMap<String, String> getTitles();

    /**
     * Gets object translating shell
     * @return shell
     */
    Shell getShell();

    /**
     * Gets manual
     * @return manual
     */
    ManualService getManual();
}
