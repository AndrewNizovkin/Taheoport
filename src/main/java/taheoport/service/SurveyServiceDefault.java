package taheoport.service;

import taheoport.gui.MainWin;
import taheoport.model.Picket;
import taheoport.model.Settings;
import taheoport.model.Shell;
import taheoport.model.SurveyStation;
import taheoport.repository.SurveyRepository;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.NoSuchElementException;

/**
 * This class encapsulates methods for working with survey project
 */
public class SurveyServiceDefault implements SurveyService {
    private String absoluteTahPath;
    private final MainWin parentFrame;
    private SurveyRepository surveyRepository;
    private final IOService ioService;
    private final ImportService importService;

    /**
     * Constructor
     * @param frame mainWin
     */
    public SurveyServiceDefault(MainWin frame) {
        parentFrame = frame;
        absoluteTahPath = "";
        surveyRepository = new SurveyRepository();
        ioService = new IOServiceDefault(parentFrame);
        importService = new ImportServiceDefault(parentFrame);

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
    public SurveyRepository getSurveyRepository() {
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
//        surveyRepository = parentFrame.getSurveyRepository();
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
        HashMap<String, String> titlesReports = parentFrame.getTitles();
//        surveyRepository = parentFrame.getSurveyRepository();
        Picket picket;
        SurveyStation surveyStation;
        LinkedList<String> listReport = new LinkedList<>();
        LinkedList<String> listTopReport = new Shell(parentFrame).getTopReportSurvey();
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
        GeoCalc geoCalc = new GeoCalc();
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
            dirBase = geoCalc.getDirAB(
                    llStation.getX(),
                    llStation.getY(),
                    llStation.getxOr(),
                    llStation.getyOr());
            for (int j = 0; j < llStation.sizePickets(); j++) {
                picket = llStation.getPicket(j);


                if (parentFrame.getSettings().getOrientStation() == 1) {
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
                horLine = geoCalc.getHorLine(picket.getLine(), picket.getVert());
                dX = geoCalc.getDX(horLine, dirPicket);
                dY = geoCalc.getDY(horLine, dirPicket);
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
        List <String>  list = ioService.readTextFile(
                parentFrame.getSettings().getPathWorkDir(),
                "gsi",
                parentFrame.getTitles().get("MWopenFileTitle"));
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
                parentFrame.getSettings().getPathWorkDir(),
                "raw",
                parentFrame.getTitles().get("MWopenFileTitle"));

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
                parentFrame.getSettings().getPathWorkDir(),
                "txt",
                parentFrame.getTitles().get("MWopenFileTitle"));

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
                parentFrame.getSettings().getPathWorkDir(),
                "tah",
                parentFrame.getTitles().get("MWopenFileTitle"));

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
        surveyRepository.clean();
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
                    parentFrame.getSettings().getPathWorkDir(),
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
                parentFrame.getSettings().getPathWorkDir(),
                "tah",
                parentFrame.getTitles().get("MWsaveTahTitle"));
        if (s != null) {
            absoluteTahPath = s;
        }
    }
}
