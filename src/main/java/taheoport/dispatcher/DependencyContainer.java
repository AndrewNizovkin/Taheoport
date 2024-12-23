package taheoport.dispatcher;

import taheoport.service.*;

import javax.swing.*;
import java.util.HashMap;

public class DependencyContainer implements DependencyInjector{
    private static DependencyContainer instance;
    private JFrame parentFrame;
    private IOService ioService;
    private ImportService importService;
    private SurveyService surveyService;
    private PolygonService polygonService;
    private ExtractService extractService;
    private CatalogService catalogService;
    private SettingsService settingsService;
    private ManualService manualService;
    private Shell shell;


    /**
     * Constructor
     */
    private DependencyContainer() {
    }

    /**
     * Gets instance of DependencyContainer
     * @return DependencyContainer
     */
    public static DependencyContainer getInstance() {
        if (instance == null) {
            instance = new DependencyContainer();
        }
        return instance;
    }

    /**
     * Initialises DependencyContainer
     */
    public void initDependencyContainer(JFrame parentFrame) {
        this.parentFrame = parentFrame;
        importService = new ImportServiceDefault();
        ioService = new IOServiceDefault(this);
        settingsService = new SettingsServiceDefault(this);
        shell = new Shell(this);
        catalogService = new CatalogServiceDefault(this);
        surveyService = new SurveyServiceDefault(this);
        polygonService = new PolygonServiceDefault(this);
        extractService = new ExtractServiceDefault(this);
        manualService = new ManualService(this);

    }

    /**
     * Gets reference to main frame
     *
     * @return JFrame
     */
    @Override
    public JFrame getMainFrame() {
        return parentFrame;
    }

    /**
     * Gets IOService
     *
     * @return Instance of IOController
     */
    @Override
    public IOService getIoService() {
        return ioService;
    }

    /**
     * Gets ImportService
     *
     * @return instance of ImportService
     */
    @Override
    public ImportService getImportService() {
        return importService;
    }

    /**
     * Gets CatalogService
     *
     * @return instance of CatalogService
     */
    @Override
    public CatalogService getCatalogService() {
        return catalogService;
    }

    /**
     * Gets SurveyService
     *
     * @return instance of SurveyService
     */
    @Override
    public SurveyService getSurveyService() {
        return surveyService;
    }

    /**
     * Gets PolygonService
     *
     * @return instance of PolygonService
     */
    @Override
    public PolygonService getPolygonService() {
        return polygonService;
    }

    /**
     * Gets ExtractService
     *
     * @return instance of ExtractService
     */
    @Override
    public ExtractService getExtractService() {
        return extractService;
    }

    /**
     * Gets SettingsService
     *
     * @return instance of SettingService
     */
    @Override
    public SettingsService getSettingsService() {
        return settingsService;
    }

    /**
     * gets HashMap titles
     *
     * @return titles
     */
    @Override
    public HashMap<String, String> getTitles() {
        return shell.getTitles();
    }

    /**
     * Gets object translating shell
     *
     * @return shell
     */
    @Override
    public Shell getShell() {
        return shell;
    }

    /**
     * Gets manual
     *
     * @return manual
     */
    @Override
    public ManualService getManual() {
        return manualService;
    }
}
