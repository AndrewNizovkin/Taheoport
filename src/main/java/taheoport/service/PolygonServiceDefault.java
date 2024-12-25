package taheoport.service;

import taheoport.dispatcher.DependencyInjector;
import taheoport.gui.MainWin;
import taheoport.model.BindType;
import taheoport.model.CatalogPoint;
import taheoport.repository.PolygonRepository;
import taheoport.model.PolygonStation;

import javax.swing.*;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

//import static taheoport.repository.PolygonRepository.BindType.TZ;

/**
 * This class encapsulates methods for working with polygon project
 */
public class PolygonServiceDefault implements PolygonService {
    private final JFrame parentFrame;
    private final PolygonRepository polygonRepository;
    private String absolutePolPath;
    private final Adjuster adjuster;
    private final IOService ioService;
    private final SettingsService settingsService;
    private final CatalogService catalogService;
    private final Shell shell;

    public PolygonServiceDefault(DependencyInjector dependencyInjector) {
        parentFrame = dependencyInjector.getMainFrame();
        shell = dependencyInjector.getShell();
        settingsService = dependencyInjector.getSettingsService();
        ioService = dependencyInjector.getIoService();
        catalogService = dependencyInjector.getCatalogService();
        polygonRepository = new PolygonRepository();
        absolutePolPath = "";
        adjuster = new AdjusterDefault(this);
    }

    /**
     * Updates basePoints
     */
    @Override
    public void updateBasePoints() {
        int q = 0;
        for (PolygonStation polygonStation: polygonRepository) {
            for (CatalogPoint catalogPoint: catalogService.getAllCatalogPoints()) {
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
                q + shell.getTitles().get("MWupdateMessage"),
                shell.getTitles().get("MWupdateMessageTitle"),
                JOptionPane.INFORMATION_MESSAGE);

    }

    /**
     * Create new polygon project
     */
    @Override
    public void newProject() {
        polygonRepository.clearRepository();
        polygonRepository.addStation(new PolygonStation());
        absolutePolPath = polygonRepository.getAbsolutePolPath();
    }

    /**
     * Loads polygon project from pol-file
     */
    @Override
    public void importPol() {
        List<String> list = ioService.readTextFile(
                settingsService.getPathWorkDir(),
                "pol",
                shell.getTitles().get("MWopenFileTitle"));
        if (list != null) {
            loadPolList(list);
        }
    }

    /**
     * Saves current polygon project to pol-file
     */
    @Override
    public void savePol() {
        if (absolutePolPath.isEmpty()) {
            String s = ioService.writeTextFile(
                    getPolList(),
                    settingsService.getPathWorkDir(),
                    "pol",
                    "Write *.pol");
                    if (s != null) {
                        absolutePolPath = s;
                    }
        } else {
            ioService.writeTextFile(getPolList(), absolutePolPath);
        }
    }

    /**
     * Saves current polygon project to pol-file
     */
    @Override
    public void savePolAs() {
        String s = ioService.writeTextFile(
                getPolList(),
                settingsService.getPathWorkDir(),
                "pol",
                shell.getTitles().get("MWsavePolTitle"));
        if (s != null) {
            absolutePolPath = s;
        }
    }

    /**
     * Gets polygonRepository
     * @return polygonRepository
     */
    public PolygonRepository getAllPolygonStations() {
        return polygonRepository;
    }

    public String getAbsolutePolPath() {
        return absolutePolPath;
    }

    /**
     * Load PolygonProject from LinkedList<String> list
     *
     * @param list list
     */
    @Override
    public void loadPolList(List<String> list) {
        polygonRepository.clearRepository();
        PolygonStation ts;
        String sep = " ";
        String str;
        String[] array;
        if (list == null) {
            return;
        }
        absolutePolPath = list.remove(0);
        while (!list.isEmpty()) {
            str = new DataHandler(list.remove(0)).compress(sep).getStr();
            array = str.split(sep);
            if (array.length == 7) {
                ts = new PolygonStation();
                ts.setName(array[0]);
                ts.setHor(array[1]);
                ts.setLine(array[2]);
                ts.setdZ(array[3]);
                ts.setX(array[4]);
                ts.setY(array[5]);
                ts.setZ(array[6]);
                if (new DataHandler(ts.getX()).isNumber() & new DataHandler(ts.getY()).isNumber() & new DataHandler(ts.getZ()).isNumber()) {
                    ts.setStatus(true);
                }
                polygonRepository.addStation(ts);
            }

        }
    }

    /**
     * gets list for writing to file current PolygonProject
     *
     * @return LinkedList
     */
    @Override
    public List<String> getPolList() {
        String sep = " ";
        String s;
        List <String> llPol = new LinkedList<>();
        PolygonStation polygonStation;
        for (int i = 0; i < getSizePolygonStations(); i++) {
            polygonStation = findById(i);
            s = polygonStation.getName() + sep +
                    polygonStation.getHor() + sep +
                    polygonStation.getLine() + sep +
                    polygonStation.getDZ() + sep;
            if (polygonStation.getStatus()) {
                s += polygonStation.getX() + sep +
                        polygonStation.getY() + sep +
                        polygonStation.getZ();
            } else {
                s += "Not" + sep +
                        "Not" + sep +
                        "Not";
            }
            llPol.add(s);
        }
        return llPol;
    }

    /**
     * Gets polygonometry adjustment report
     *
     * @return LinkedList <String>
     */
    @Override
    public List<String> getReportXY() {
        List<String> llTopReportXY = shell.getTopReportXY();
        HashMap<String, String> titlesReports = shell.getTitlesReports();
        List<String> llReportXY = new LinkedList<>(llTopReportXY);
        PolygonStation firstPolygonStation = findById(0);
        switch (getBindType()) {
            case TZ, TO, TT -> {
                llReportXY.add("| " + new DataHandler(firstPolygonStation.getName()).toTable(10).getStr() +
                        " |          |          |         |          |          |        |          |        | " +
                        new DataHandler(firstPolygonStation.getX()).toTable(12).getStr() +
                        " | " +
                        new DataHandler(firstPolygonStation.getY()).toTable(12).getStr() +
                        " |");
                llReportXY.add("|            |          |          |         |          |          |        |          |        |              |              |");
            }
            case ZT, OT, OO -> {
                llReportXY.add("| " + new DataHandler(firstPolygonStation.getName()).toTable(10).getStr() +
                        " | " +
                        new DataHandler(firstPolygonStation.getHor()).toTable(8).getStr() +
                        " |          | " +
                        new DataHandler(firstPolygonStation.getDDHor()).format(2).toTable(7).getStr() +
                        " |          |          |        |          |        | " +
                        new DataHandler(firstPolygonStation.getX()).toTable(12).getStr() +
                        " | " +
                        new DataHandler(firstPolygonStation.getY()).toTable(12).getStr() +
                        " |");
                llReportXY.add("|            |          | " +
                        new DataHandler(firstPolygonStation.getLine()).toTable(8).getStr() +
                        " |         | " +
                        new DataHandler(firstPolygonStation.getDirection()).format(4).toTable(8).getStr() +
                        " | " +
                        new DataHandler(firstPolygonStation.getDX()).format(3).toTable(8).getStr() +
                        " | " +
                        new DataHandler(firstPolygonStation.getDDX()).format(3).toTable(6).getStr() +
                        " | " +
                        new DataHandler(firstPolygonStation.getDY()).format(3).toTable(8).getStr() +
                        " | " +
                        new DataHandler(firstPolygonStation.getDDY()).format(3).toTable(6).getStr() +
                        " |              |              |");
            }
        }
        PolygonStation polygonStation;
        for (int i = 1; i < getSizePolygonStations() - 2; i++) {
            polygonStation = findById(i);
            llReportXY.add("| " + new DataHandler(polygonStation.getName()).toTable(10).getStr() +
                    " | " +
                    new DataHandler(polygonStation.getHor()).toTable(8).getStr() +
                    " |          | " +
                    new DataHandler(polygonStation.getDDHor()).format(2).toTable(7).getStr() +
                    " |          |          |        |          |        | " +
                    new DataHandler(polygonStation.getX()).toTable(12).getStr() +
                    " | " +
                    new DataHandler(polygonStation.getY()).toTable(12).getStr() +
                    " |");
            llReportXY.add("|            |          | " +
                    new DataHandler(polygonStation.getLine()).toTable(8).getStr() +
                    " |         | " +
                    new DataHandler(polygonStation.getDirection()).format(4).toTable(8).getStr() +
                    " | " +
                    new DataHandler(polygonStation.getDX()).format(3).toTable(8).getStr() +
                    " | " +
                    new DataHandler(polygonStation.getDDX()).format(3).toTable(6).getStr() +
                    " | " +
                    new DataHandler(polygonStation.getDY()).format(3).toTable(8).getStr() +
                    " | " +
                    new DataHandler(polygonStation.getDDY()).format(3).toTable(6).getStr() +
                    " |              |              |");
        }
        PolygonStation beforeLastPolygonStation = findById(getSizePolygonStations() - 2);
        llReportXY.add("| " + new DataHandler(beforeLastPolygonStation.getName()).toTable(10).getStr() +
                " | " +
                new DataHandler(beforeLastPolygonStation.getHor()).toTable(8).getStr() +
                " |          | " +
                new DataHandler(beforeLastPolygonStation.getDDHor()).format(2).toTable(7).getStr() +
                " |          |          |        |          |        | " +
                new DataHandler(beforeLastPolygonStation.getX()).toTable(12).getStr() +
                " | " +
                new DataHandler(beforeLastPolygonStation.getY()).toTable(12).getStr() +
                " |");

        switch (getBindType()) {
            case OO, TO, TZ -> llReportXY.add("|            |          | " +
                    new DataHandler(beforeLastPolygonStation.getLine()).toTable(8).getStr() +
                    " |         | " +
                    new DataHandler(beforeLastPolygonStation.getDirection()).format(4).toTable(8).getStr() +
                    " | " +
                    new DataHandler(beforeLastPolygonStation.getDX()).format(3).toTable(8).getStr() +
                    " | " +
                    new DataHandler(beforeLastPolygonStation.getDDX()).format(3).toTable(6).getStr() +
                    " | " +
                    new DataHandler(beforeLastPolygonStation.getDY()).format(3).toTable(8).getStr() +
                    " | " +
                    new DataHandler(beforeLastPolygonStation.getDDY()).format(3).toTable(6).getStr() +
                    " |              |              |");
            case TT, ZT, OT -> llReportXY.add("|            |          |          |         |          |          |        |          |        |              |              |");
        }
        PolygonStation lastPolygonStation = findById(getSizePolygonStations() - 1);
        llReportXY.add("| " + new DataHandler(lastPolygonStation.getName()).toTable(10).getStr() +
                " |          |          |         |          |          |        |          |        | " +
                new DataHandler(lastPolygonStation.getX()).toTable(12).getStr() +
                " | " +
                new DataHandler(lastPolygonStation.getY()).toTable(12).getStr() +
                " |");
        llReportXY.add("-------------------------------------------------------------------------------------------------------------------------------");
        llReportXY.add("");
        llReportXY.add(titlesReports.get("TPfoterTitle"));
        llReportXY.add(titlesReports.get("TPperimeter") + new DataHandler(getPerimeter()).format(3).getStr() + titlesReports.get("TPm"));
        llReportXY.add(titlesReports.get("TPangleResidues"));
        if (getBindType() == BindType.TT) {
            llReportXY.add(titlesReports.get("TPactual") + new DataHandler(getfHor()).format(2).getStr() + titlesReports.get("TPsek"));
            llReportXY.add(titlesReports.get("TPacceptable") +
                    new DataHandler(settingsService.getValueFHor() * Math.sqrt(getSizePolygonStations() - 2)).format(0).getStr() +
                    titlesReports.get("TPsek"));
        } else {
            llReportXY.add(titlesReports.get("TPactual") + "-.-");
            llReportXY.add(titlesReports.get("TPacceptable") + "-.-");

        }
        llReportXY.add(titlesReports.get("TPlineResidues"));
        if (getBindType() == BindType.TZ | getBindType() == BindType.ZT) {
            llReportXY.add(titlesReports.get("TPlineDX") + "-.-");
            llReportXY.add(titlesReports.get("TPlineDY") + "-.-");
            llReportXY.add(titlesReports.get("TPabsoluteDeviation") + "-.-");
            llReportXY.add(titlesReports.get("TPactualRelativeDeviation") + "-.-");
            llReportXY.add(titlesReports.get("TPacceptableRelativeDeviation") + "-.-");
        } else {
            llReportXY.add(titlesReports.get("TPlineDX") + new DataHandler(getfX()).format(3).getStr() + "м.");
            llReportXY.add(titlesReports.get("TPlineDY") + new DataHandler(getfY()).format(3).getStr() + "м.");
            llReportXY.add(titlesReports.get("TPabsoluteDeviation") + new DataHandler(getfAbs()).format(3).getStr() + "м.");
            llReportXY.add(titlesReports.get("TPactualRelativeDeviation") + "1:" + getfOtn());
            llReportXY.add(titlesReports.get("TPacceptableRelativeDeviation") + "1:" + settingsService.getValueFOtn());

        }
        return llReportXY;

    }

    /**
     * Gets leveling  adjustment report
     *
     * @return LinkedList <String>
     */
    @Override
    public List<String> getReportZ() {
        double dZCorrected;
        double sumDZ = 0.0;
        double sumDDZ = 0.0;
        double sumDZCorrected = 0.0;
        int start = 0;
        int finish = getSizePolygonStations() - 1;

        LinkedList<String> llTopReportZ = shell.getTopReportZ();
        LinkedList<String> llReportZ = new LinkedList<>(llTopReportZ);
        HashMap<String, String> titlesReports = shell.getTitlesReports();

        if (getBindType() == BindType.TT |
                getBindType() == BindType.TO |
                getBindType() == BindType.TZ) {
            start = 1;
        }
        if (getBindType() == BindType.ZT |
                getBindType() == BindType.OT |
                getBindType() == BindType.TT) {
            finish = getSizePolygonStations() - 2;
        }
        PolygonStation polygonStation;
        for (int i = start; i < finish; i++) {
            polygonStation = findById(i);
            dZCorrected = Double.parseDouble(polygonStation.getDZ()) +
                    polygonStation.getDDZ();
            sumDZCorrected += dZCorrected;
            sumDDZ += polygonStation.getDDZ();
            sumDZ += Double.parseDouble(polygonStation.getDZ());
            llReportZ.add("| " + new DataHandler(polygonStation.getName()).toTable(10).getStr() +
                    " |          |            |        |            | " +
                    new DataHandler(polygonStation.getZ()).toTable(8).getStr() + " |");
            llReportZ.add("|            | " +
                    new DataHandler(polygonStation.getLine()).toTable(8).getStr() +
                    " | " +
                    new DataHandler(polygonStation.getDZ()).toTable(10).getStr() +
                    " | " +
                    new DataHandler(polygonStation.getDDZ() * 1000).format(2).toTable(6).getStr() +
                    " | " +
                    new DataHandler(dZCorrected).format(3).toTable(10).getStr() +
                    " |          |"
            );
        }
        llReportZ.add("| " + new DataHandler(findById(finish).getName()).toTable(10).getStr() +
                " |          |            |        |            | " +
                new DataHandler(findById(finish).getZ()).toTable(8).getStr() + " |");
        llReportZ.add("|------------|----------|------------|--------|------------|----------|");
        llReportZ.add("|" + titlesReports.get("TPcontrol") +" | " +
                new DataHandler(getPerimeter()).format(3).toTable(8).getStr() +
                " | " +
                new DataHandler(sumDZ).format(3).toTable(10).getStr() +
                " | " +
                new DataHandler(sumDDZ * 1000).format(2).toTable(6).getStr() +
                " | " +
                new DataHandler(sumDZCorrected).format(3).toTable(10).getStr() +
                " |          |"
        );
//            llReportZ.add("|  суммы     |          |            |        |            |          |");
        llReportZ.add("-----------------------------------------------------------------------");
        llReportZ.add("");
        llReportZ.add(titlesReports.get("TPfoterTitle"));
        llReportZ.add(titlesReports.get("TPperimeter") +
                new DataHandler(getPerimeter()).format(3).getStr() +
                titlesReports.get("TPm"));
        if (getBindType() == BindType.TZ |
                getBindType() == BindType.ZT) {
            llReportZ.add(titlesReports.get("TPactualResidue") + "-.-");
            llReportZ.add(titlesReports.get("TPacceptableResidue") + "-.-");

        } else {
            llReportZ.add(titlesReports.get("TPactualResidue") +
                    new DataHandler(getfZ() * 1000).format(0).getStr() +
                    titlesReports.get("TPmm"));
            llReportZ.add(titlesReports.get("TPacceptableResidue") +
                    new DataHandler(settingsService.getValueFH() *
                            Math.sqrt(getPerimeter() / 1000)).format(0).getStr() +
                    titlesReports.get("TPmm"));
        }
        return llReportZ;
    }

    /**
     * Returns a list of rows in the report <Name X Y Z>
     *
     * @return LinkedList <String>
     */
    @Override
    public List<String> getReportNXYZ() {
        String sep = " ";
        List<String> llReportNXYZ = new LinkedList<>();
        PolygonStation polygonStation;
        for (int i = 0; i < getSizePolygonStations(); i++) {
            polygonStation = findById(i);
            llReportNXYZ.add(polygonStation.getName() + sep +
                    polygonStation.getX() + sep +
                    polygonStation.getY() + sep +
                    polygonStation.getZ());
        }
        return llReportNXYZ;

    }

    /**
     * Processes the source data.
     * Adjustment the network and determines the coordinates of the defined points llTheoStations
     */
    @Override
    public void processSourceData() {
        adjuster.adjustPolygon();
    }

    /**
     * Сhecks the possibility of inserting before idx position
     *
     * @param idx int idx
     * @return boolean
     */
    @Override
    public boolean isInsertBefore(int idx) {
        if (!polygonRepository.findById(idx).getStatus()) return true;
        if (polygonRepository.findById(idx).getStatus() & idx > 0) {
            return !polygonRepository.findById(idx - 1).getStatus();
        }
        return false;
    }

    /**
     * Сhecks the possibility of inserting after idx position
     *
     * @param idx int idx
     * @return boolean
     */
    @Override
    public boolean isInsertAfter(int idx) {
        if (!polygonRepository.findById(idx).getStatus()) return true;
        if (polygonRepository.findById(idx).getStatus() & idx < getSizePolygonStations() - 1) {
            return !polygonRepository.findById(idx + 1).getStatus();
        }
        return false;
    }

    /**
     * Inserts station to repository by index
     *
     * @param idx int
     */
    @Override
    public void insertStation(int idx) {
        polygonRepository.insertStation(idx);
    }

    /**
     * remove Station from llTheoStations
     *
     * @param idx int index removed element
     */
    @Override
    public void removeStation(int idx) {
        polygonRepository.removeStation(idx);
    }

    /**
     * Gets polygonStation by id from repository
     *
     * @param idx int index
     * @return polygonStation
     */
    @Override
    public PolygonStation findById(int idx) {
        return polygonRepository.findById(idx);
    }

    /**
     * Gets repository size
     *
     * @return int
     */
    @Override
    public int getSizePolygonStations() {
        return polygonRepository.getSizePolygonStations();
    }

    /**
     * Gets polygon perimeter
     *
     * @return double
     */
    @Override
    public double getPerimeter() {
        return polygonRepository.getPerimeter();
    }

    /**
     * Sets perimeter
     *
     * @param perimeter double
     */
    @Override
    public void setPerimeter(double perimeter) {
        polygonRepository.setPerimeter(perimeter);
    }

    /**
     * Gets the actual angular error
     *
     * @return double
     */
    @Override
    public double getfHor() {
        return polygonRepository.getfHor();
    }

    /**
     * Gets linear actual error on the X-axis
     * @return double
     */
    @Override
    public double getfX() {
        return polygonRepository.getfX();
    }

    /**
     * Gets linear actual error on the Y-axis
     * @return double
     */
    @Override
    public double getfY() {
        return polygonRepository.getfY();
    }

    /**
     * Gets linear actual error on the Z-axis
     * @return double
     */
    @Override
    public double getfZ() {
        return polygonRepository.getfZ();
    }

    /**
     * the actual linear absolute error
     * @return double
     */
    @Override
    public double getfAbs() {
        return polygonRepository.getfAbs();
    }

    /**
     * the actual linear relative error
     * @return String
     */
    @Override
    public String getfOtn() {
        return polygonRepository.getfOtn();
    }

    /**
     * Gets type of binding the polygon to base points
     *
     * @return BindType
     */
    @Override
    public BindType getBindType() {
        return polygonRepository.getBindType();
    }

    /**
     * Sets bindType
     *
     * @param bindType BindType
     */
    @Override
    public void setBindType(BindType bindType) {
        polygonRepository.setBindType(bindType);
    }

    /**
     * Sets fHor
     *
     * @param fHor
     */
    @Override
    public void setfHor(double fHor) {
        polygonRepository.setfHor(fHor);
    }

    /**
     * Sets fX
     *
     * @param fX double fX
     */
    @Override
    public void setfX(double fX) {
        polygonRepository.setfX(fX);
    }

    /**
     * Sets fY
     *
     * @param fY double fY
     */
    @Override
    public void setfY(double fY) {
        polygonRepository.setfY(fY);
    }

    /**
     * Sets fZ
     *
     * @param fZ double fZ
     */
    @Override
    public void setfZ(double fZ) {
        polygonRepository.setfZ(fZ);
    }

    /**
     * Sets fAbs
     *
     * @param fAbs double fAbs
     */
    @Override
    public void setfAbs(double fAbs) {
        polygonRepository.setfAbs(fAbs);
    }

    /**
     * Sets fOtn
     *
     * @param fOtn String fOtn
     */
    @Override
    public void setfOtn(String fOtn) {
        polygonRepository.setfOtn(fOtn);
    }

}
