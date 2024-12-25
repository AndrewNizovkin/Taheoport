package taheoport.service;

import taheoport.dispatcher.DependencyInjector;
import taheoport.model.CatalogPoint;
import taheoport.model.Picket;
import taheoport.model.SurveyStation;
import taheoport.repository.SurveyRepository;

import javax.swing.*;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.NoSuchElementException;

/**
 * This class encapsulates methods for working with survey project
 */
public class SurveyServiceDefault implements SurveyService {
    private final JFrame parentFrame;
    private String absoluteTahPath;
    private SurveyRepository surveyRepository;
    private final IOService ioService;
    private final ImportService importService;
    private final SettingsService settingsService;
    private final CatalogService catalogService;
    private final Shell shell;

    /**
     * Constructor
     * @param dependencyInjector DependencyInjector
     */
    public SurveyServiceDefault(DependencyInjector dependencyInjector) {
        parentFrame = dependencyInjector.getMainFrame();
        shell = dependencyInjector.getShell();
        absoluteTahPath = "";
        surveyRepository = new SurveyRepository();
        ioService = dependencyInjector.getIoService();
        importService = dependencyInjector.getImportService();
        settingsService = dependencyInjector.getSettingsService();
        catalogService = dependencyInjector.getCatalogService();

    }

    /**
     * Updates basePoints
     */
    @Override
    public void updateBasePoints() {
        int q = 0;
        for (SurveyStation surveyStation: surveyRepository) {
            for (CatalogPoint catalogPoint: catalogService.getAllCatalogPoints()) {
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
                q + shell.getTitles().get("MWupdateMessage"),
                shell.getTitles().get("MWupdateMessageTitle"),
                JOptionPane.INFORMATION_MESSAGE);
    }

    /**
     * Sets absoluteTahPath
     * @param absoluteTahPath path to file
     */
    public void setAbsoluteTahPath(String absoluteTahPath) {
        this.absoluteTahPath = absoluteTahPath;
    }

    /**
     * Gets absoluteTahPath
     * @return String
     */
    public String getAbsoluteTahPath() {
        return absoluteTahPath;
    }

    /**
     * Sets surveyRepository
     * @param surveyRepository surveyRepository
     */
    public void setSurveyRepository(SurveyRepository surveyRepository) {
        this.surveyRepository = surveyRepository;
        absoluteTahPath = surveyRepository.getAbsoluteTahPath();
    }

    /**
     * Gets surveyRepository
     * @return surveyRepository
     */
    public SurveyRepository getAllStations() {
        return surveyRepository;
    }

    /**
     * Checks the potential presence of a polygon in the measurements
     * @return result of check
     */
    public boolean containPolygon() {
        return surveyRepository.containPolygon();
    }

    /**
     * Gets SurveyProject as list
     * @return List
     */
    @Override
    public List<String> getTahList() {
        SurveyStation surveyStation;
        Picket picket;
        String sep = " ";
        List<String> list = new LinkedList<>();
        try {
            for (int i = 0; i < surveyRepository.sizeStations(); i++) {
                surveyStation = surveyRepository.findById(i);
                list.add(surveyStation.getName() + sep +
                        surveyStation.getX() + sep +
                        surveyStation.getY() + sep +
                        surveyStation.getZ() + sep +
                        surveyStation.getVi() + sep +
                        surveyStation.getNameOr() + sep +
                        surveyStation.getxOr() + sep +
                        surveyStation.getyOr());
            }
            list.add("//");
            for (int i = 0; i < surveyRepository.sizeStations(); i++) {
                surveyStation = surveyRepository.findById(i);
                for (int j = 0; j < surveyStation.sizePickets(); j++ ) {
                    picket = surveyStation.getPicket(j);
                    list.add(picket.getpName() + sep + picket.getLine() + sep +
                            picket.getHor() + sep + picket.getVert() + sep +
                            picket.getV() + sep + i);
                }
                list.add("//");
            }
        } catch (NoSuchElementException e) {
            System.out.println("element not found");
        }
        return list;
    }

    /**
     * Gets pickets coordinate catalog
     *
     * @return LinkedList
     */
    @Override
    public List<String> getPickets() {
        SurveyStation surveyStation;
        Picket picket;
        String sep = " ";
        List<String> list = new LinkedList<>();
        try {
            for (int i = 0; i < surveyRepository.sizeStations(); i++) {
                surveyStation = surveyRepository.findById(i);
                for (int j = 0; j < surveyStation.sizePickets(); j++) {
                    picket = surveyStation.getPicket(j);
                    list.add(picket.getpName() + sep +
                            picket.getX() + sep +
                            picket.getY() + sep +
                            picket.getZ());
                }
            }
        } catch (NoSuchElementException e) {
            System.out.println("element not found");
        }
        return list;
    }

    /**
     * Gets report to print
     *
     * @return LinkedList
     */
    @Override
    public List<String> getReport() {
        HashMap<String, String> titlesReports = shell.getTitles();
//        Shell shell = .getShell();
        Picket picket;
        SurveyStation surveyStation;
        LinkedList<String> listReport = new LinkedList<>();
        LinkedList<String> listTopReport = shell.getTopReportSurvey();
        String str;

        while ((str = listTopReport.pollFirst()) != null) {
            listReport.add(str);
        }

        for (int i = 0; i < surveyRepository.sizeStations(); i++) {
            surveyStation = surveyRepository.findById(i);
            listReport.add("                                   " + titlesReports.get("SPstation") +
                    surveyStation.getName() +
                    "       " + titlesReports.get("SPorientir") +
                    surveyStation.getNameOr() +
                    "      " + titlesReports.get("SPtoolHeight") +
                    surveyStation.getVi());
            listReport.add("-----------------------------------------------------------------------------------------------------------------------------------------------");
            listReport.add("| " +
                    new DataHandler(surveyStation.getName()).toTable(10).getStr() +
                    " |          |          |         |          |          |          |          |           | " +
                    new DataHandler(surveyStation.getX()).toTable(11).getStr() + " | " +
                    new DataHandler(surveyStation.getY()).toTable(11).getStr() + " | " +
                    new DataHandler(surveyStation.getZ()).toTable(10).getStr() + " |");
            listReport.add("| " +
                    new DataHandler(surveyStation.getNameOr()).toTable(10).getStr() +
                    " |          |          |         |          |          |          |          |           | " +
                    new DataHandler(surveyStation.getxOr()).toTable(11).getStr() + " | " +
                    new DataHandler(surveyStation.getyOr()).toTable(11).getStr() + " |            |");

            for (int j = 0; j < surveyStation.sizePickets(); j++) {
                picket = surveyStation.getPicket(j);
                listReport.add("| " +
                        new DataHandler(picket.getpName()).toTable(10).getStr() + " | " +
                        new DataHandler(picket.getLine()).toTable(8).getStr() + " | " +
                        new DataHandler(picket.getHor()).toTable(8).getStr() + " | " +
                        new DataHandler(picket.getVert()).toTable(7).getStr() + " | " +
                        new DataHandler(picket.getV()).toTable(8).getStr() + " | " +
                        new DataHandler(picket.getPDirection()).toTable(8).getStr() + " | " +
                        new DataHandler(picket.getDX()).toTable(8).getStr() + " | " +
                        new DataHandler(picket.getDY()).toTable(8).getStr() + " | " +
                        new DataHandler(picket.getDZ()).toTable(9).getStr() + " | " +
                        new DataHandler(picket.getX()).toTable(11).getStr() + " | " +
                        new DataHandler(picket.getY()).toTable(11).getStr() + " | " +
                        new DataHandler(picket.getZ()).toTable(10).getStr() + " |");

            }
            listReport.add("-----------------------------------------------------------------------------------------------------------------------------------------------");
        }
        return listReport;
    }

    /**
     * process SurveyProject and sets for all pickets:
     * directions,DX, DY, DZ, X, Y, Z
     */
    @Override
    public void processSourceData() {
        SurveyStation llStation;
        double dirBase;
        double dirPicket;
        double horLine;
        double dX;
        double dY;
        double dZ;
        Picket picket;

        for (int i = 0; i < surveyRepository.sizeStations(); i++) {
            llStation = surveyRepository.findById(i);
            dirBase = GeoCalculator.getDirAB(
                    llStation.getX(),
                    llStation.getY(),
                    llStation.getxOr(),
                    llStation.getyOr());
            for (int j = 0; j < llStation.sizePickets(); j++) {
                picket = llStation.getPicket(j);


                if (settingsService.getOrientStation() == 1) {
                    dirPicket = dirBase + new DataHandler(picket.getHor()).dmsToRad() -
                            new DataHandler(llStation.getPicket(0).getHor()).dmsToRad();
                    while (dirPicket < 0) {
                        dirPicket += 2 * Math.PI;
                    }
                } else {
                    dirPicket = dirBase + new DataHandler(picket.getHor()).dmsToRad();
                }


                while (dirPicket > 2 * Math.PI) {
                    dirPicket -= 2 * Math.PI;
                }
                horLine = GeoCalculator.getHorLine(picket.getLine(), picket.getVert());
                dX = GeoCalculator.getDX(horLine, dirPicket);
                dY = GeoCalculator.getDY(horLine, dirPicket);
                dZ = Double.parseDouble(llStation.getVi()) -
                        Double.parseDouble(llStation.getPicket(j).getV()) +
                        Double.parseDouble(picket.getLine()) * Math.sin(new DataHandler(picket.getVert()).dmsToRad());
                picket.setDirection(dirPicket);
                picket.setHorLine(horLine);
                picket.setdX(dX);
                picket.setdY(dY);
                picket.setdZ(dZ);
                picket.setX(Double.parseDouble(llStation.getX()) + dX);
                picket.setY(Double.parseDouble(llStation.getY()) + dY);
                picket.setZ(Double.parseDouble(llStation.getZ()) + dZ);
            }
        }
    }

    /**
     * Imports measurement from Lieca gsi
     */
    @Override
    public void importLeica() {
        HashMap<String, String> titles = shell.getTitles();
//        Shell shell = dependencyInjector.getShell();

        List <String>  list = ioService.readTextFile(
                settingsService.getPathWorkDir(),
                "gsi",
                titles.get("MWopenFileTitle"));
        if (list != null) {
            surveyRepository = importService.loadLeica(list);
        }
        absoluteTahPath = surveyRepository.getAbsoluteTahPath();
    }

    /**
     * Imports measurement from Nicon txt
     */
    @Override
    public void importNicon() {
        List <String>  list = ioService.readTextFile(
                settingsService.getPathWorkDir(),
                "raw",
                shell.getTitles().get("MWopenFileTitle"));

        if (list != null) {
            surveyRepository = importService.loadNicon(list);
            absoluteTahPath = surveyRepository.getAbsoluteTahPath();
        }
    }

    /**
     * Imports measurement from Topcon txt
     */
    @Override
    public void importTopcon() {
        List<String> list = ioService.readTextFile(
                settingsService.getPathWorkDir(),
                "txt",
                shell.getTitles().get("MWopenFileTitle"));

        if (list != null) {
            surveyRepository = importService.loadTopcon(list);
            absoluteTahPath = surveyRepository.getAbsoluteTahPath();
        }
    }

    /**
     * Imports measurement from *.tah
     */
    @Override
    public void importTah() {
        List<String> llTahList = ioService.readTextFile(
                settingsService.getPathWorkDir(),
                "tah",
                shell.getTitles().get("MWopenFileTitle"));

        if (llTahList != null) {
            surveyRepository = importService.loadTah(llTahList);
            absoluteTahPath = surveyRepository.getAbsoluteTahPath();
        }
    }

    /**
     * Create new Survey Project
     */
    @Override
    public void newProject() {
        surveyRepository.clearRepository();
        absoluteTahPath = surveyRepository.getAbsoluteTahPath();
        SurveyStation st = surveyRepository.addStation(new SurveyStation());
        st.addPicket();
    }

    /**
     * Save current survey project
     */
    @Override
    public void saveProject() {
        if (absoluteTahPath.isEmpty()) {
            String s = ioService.writeTextFile(
                    this.getTahList(),
                    settingsService.getPathWorkDir(),
                    "tah",
                    "Write Tah");
            if (s != null) {
                absoluteTahPath = s;
            }
        } else {
            absoluteTahPath = ioService.writeTextFile(
                    this.getTahList(),
                    absoluteTahPath);
        }
    }

    /**
     * Save current survey project
     */
    @Override
    public void saveProjectAs() {
        String s = ioService.writeTextFile(
                this.getTahList(),
                settingsService.getPathWorkDir(),
                "tah",
                shell.getTitles().get("MWsaveTahTitle"));
        if (s != null) {
            absoluteTahPath = s;
        }
    }

    /**
     * Gets survey station from repository by id
     *
     * @param id
     * @return
     */
    @Override
    public SurveyStation findStationById(int id) {
        return surveyRepository.findById(id);
    }

    /**
     * Checks repository is empty
     *
     * @return result of check
     */
    @Override
    public boolean isEmptyRepository() {
        return surveyRepository.sizeStations() > 0;
    }

    /**
     * Gets repository sizi
     *
     * @return int
     */
    @Override
    public int sizeRepository() {
        return surveyRepository.sizeStations();
    }

    /**
     * Removes stations from repository by id
     *
     * @param id int
     */
    @Override
    public void removeStation(int id) {
        surveyRepository.removeStation(id);
    }

    /**
     * Inserts station to repository by id
     *
     * @param id int id
     * @return SurveyStation
     */
    @Override
    public SurveyStation insertStation(int id) {
        return surveyRepository.insertStation(id);
    }

    /**
     * Inserts picket by id to station by id
     *
     * @param stationId int station id
     * @param picketId  int picket id
     */
    @Override
    public void insertPicket(int stationId, int picketId) {
        surveyRepository.findById(stationId).insertPicket(picketId);
    }

    /**
     * Removes picket by id from station by id
     *
     * @param stationId int station index
     * @param picketId  int picket index
     */
    @Override
    public void removePicket(int stationId, int picketId) {
        surveyRepository.findById(stationId).removePicket(picketId);
    }

    /**
     * Gets picket by stationId and picketId
     *
     * @param stationId int
     * @param picketId  int
     * @return picket
     */
    @Override
    public Picket getPicketById(int stationId, int picketId) {
        return surveyRepository.findById(stationId).getPicket(picketId);
    }

    /**
     * Gets count of pickets at the survey station by id
     *
     * @param stationId int
     * @return int
     */
    @Override
    public int sizePickets(int stationId) {
        return surveyRepository.findById(stationId).sizePickets();
    }
}
