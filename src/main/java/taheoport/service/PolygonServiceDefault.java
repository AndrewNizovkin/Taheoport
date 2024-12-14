package taheoport.service;

import taheoport.gui.MainWin;
import taheoport.repository.PolygonRepository;
import taheoport.model.PolygonStation;
import taheoport.model.Shell;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

import static taheoport.repository.PolygonRepository.BindType.TZ;

/**
 * This class encapsulates methods for working with polygon project
 */
public class PolygonServiceDefault implements PolygonService {

    private final PolygonRepository polygonRepository;
    private String absolutePolPath;
    private final MainWin parentFrame;
    private final Adjuster adjuster;
    private final IOService ioService;

    public PolygonServiceDefault(MainWin frame) {
        parentFrame = frame;
        adjuster = new AdjusterDefault(frame);
        ioService = new IOServiceDefault(parentFrame);
        polygonRepository = new PolygonRepository();
        absolutePolPath = "";
    }

    /**
     * Create new polygon project
     */
    @Override
    public void newProject() {
        polygonRepository.clear();
        polygonRepository.addStation(new PolygonStation());
        absolutePolPath = polygonRepository.getAbsolutePolPath();
    }

    /**
     * Loads polygon project from pol-file
     */
    @Override
    public void importPol() {
        List<String> list = ioService.readTextFile(
                parentFrame.getSettings().getPathWorkDir(),
                "pol",
                parentFrame.getTitles().get("MWopenFileTitle"));
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
                    parentFrame.getSettings().getPathWorkDir(),
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
                parentFrame.getSettings().getPathWorkDir(),
                "pol",
                parentFrame.getTitles().get("MWsavePolTitle"));
        if (s != null) {
            absolutePolPath = s;
        }
    }

    /**
     * Gets polygonRepository
     * @return polygonRepository
     */
    public PolygonRepository getPolygonRepository() {
        return polygonRepository;
    }

    public String getAbsolutePolPath() {
        return absolutePolPath;
    }

    /**
     * Load PolygonProject from LinkedList<String> list
     *
     * @param list list
     * @return PolygonProject
     */
    @Override
    public void loadPolList(List<String> list) {
        polygonRepository.clear();
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
        for (int i = 0; i < parentFrame.getPolygonRepository().getSizePolygonStations(); i++) {
            polygonStation = parentFrame.getPolygonRepository().findById(i);
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
        List<String> llTopReportXY = new Shell(parentFrame).getTopReportXY();
        HashMap<String, String> titlesReports = new Shell(parentFrame).getTitlesReports();
        PolygonRepository polygonRepository = parentFrame.getPolygonRepository();
        List<String> llReportXY = new LinkedList<>(llTopReportXY);

        switch (polygonRepository.getBindType()) {
            case TZ, TO, TT -> {
                llReportXY.add("| " + new DataHandler(polygonRepository.findById(0).getName()).toTable(10).getStr() +
                        " |          |          |         |          |          |        |          |        | " +
                        new DataHandler(polygonRepository.findById(0).getX()).toTable(12).getStr() +
                        " | " +
                        new DataHandler(polygonRepository.findById(0).getY()).toTable(12).getStr() +
                        " |");
                llReportXY.add("|            |          |          |         |          |          |        |          |        |              |              |");
            }
            case ZT, OT, OO -> {
                llReportXY.add("| " + new DataHandler(polygonRepository.findById(0).getName()).toTable(10).getStr() +
                        " | " +
                        new DataHandler(polygonRepository.findById(0).getHor()).toTable(8).getStr() +
                        " |          | " +
                        new DataHandler(polygonRepository.findById(0).getDDHor()).format(2).toTable(7).getStr() +
                        " |          |          |        |          |        | " +
                        new DataHandler(polygonRepository.findById(0).getX()).toTable(12).getStr() +
                        " | " +
                        new DataHandler(polygonRepository.findById(0).getY()).toTable(12).getStr() +
                        " |");
                llReportXY.add("|            |          | " +
                        new DataHandler(polygonRepository.findById(0).getLine()).toTable(8).getStr() +
                        " |         | " +
                        new DataHandler(polygonRepository.findById(0).getDirection()).format(4).toTable(8).getStr() +
                        " | " +
                        new DataHandler(polygonRepository.findById(0).getDX()).format(3).toTable(8).getStr() +
                        " | " +
                        new DataHandler(polygonRepository.findById(0).getDDX()).format(3).toTable(6).getStr() +
                        " | " +
                        new DataHandler(polygonRepository.findById(0).getDY()).format(3).toTable(8).getStr() +
                        " | " +
                        new DataHandler(polygonRepository.findById(0).getDDY()).format(3).toTable(6).getStr() +
                        " |              |              |");
            }
        }
        for (int i = 1; i < polygonRepository.getSizePolygonStations() -2; i++) {
            llReportXY.add("| " + new DataHandler(polygonRepository.findById(i).getName()).toTable(10).getStr() +
                    " | " +
                    new DataHandler(polygonRepository.findById(i).getHor()).toTable(8).getStr() +
                    " |          | " +
                    new DataHandler(polygonRepository.findById(i).getDDHor()).format(2).toTable(7).getStr() +
                    " |          |          |        |          |        | " +
                    new DataHandler(polygonRepository.findById(i).getX()).toTable(12).getStr() +
                    " | " +
                    new DataHandler(polygonRepository.findById(i).getY()).toTable(12).getStr() +
                    " |");
            llReportXY.add("|            |          | " +
                    new DataHandler(polygonRepository.findById(i).getLine()).toTable(8).getStr() +
                    " |         | " +
                    new DataHandler(polygonRepository.findById(i).getDirection()).format(4).toTable(8).getStr() +
                    " | " +
                    new DataHandler(polygonRepository.findById(i).getDX()).format(3).toTable(8).getStr() +
                    " | " +
                    new DataHandler(polygonRepository.findById(i).getDDX()).format(3).toTable(6).getStr() +
                    " | " +
                    new DataHandler(polygonRepository.findById(i).getDY()).format(3).toTable(8).getStr() +
                    " | " +
                    new DataHandler(polygonRepository.findById(i).getDDY()).format(3).toTable(6).getStr() +
                    " |              |              |");
        }
        llReportXY.add("| " + new DataHandler(polygonRepository.findById(polygonRepository.getSizePolygonStations() - 2).getName()).toTable(10).getStr() +
                " | " +
                new DataHandler(polygonRepository.findById(polygonRepository.getSizePolygonStations() - 2).getHor()).toTable(8).getStr() +
                " |          | " +
                new DataHandler(polygonRepository.findById(polygonRepository.getSizePolygonStations() - 2).getDDHor()).format(2).toTable(7).getStr() +
                " |          |          |        |          |        | " +
                new DataHandler(polygonRepository.findById(polygonRepository.getSizePolygonStations() - 2).getX()).toTable(12).getStr() +
                " | " +
                new DataHandler(polygonRepository.findById(polygonRepository.getSizePolygonStations() - 2).getY()).toTable(12).getStr() +
                " |");

        switch (polygonRepository.getBindType()) {
            case OO, TO, TZ -> llReportXY.add("|            |          | " +
                    new DataHandler(polygonRepository.findById(polygonRepository.getSizePolygonStations() - 2).getLine()).toTable(8).getStr() +
                    " |         | " +
                    new DataHandler(polygonRepository.findById(polygonRepository.getSizePolygonStations() - 2).getDirection()).format(4).toTable(8).getStr() +
                    " | " +
                    new DataHandler(polygonRepository.findById(polygonRepository.getSizePolygonStations() - 2).getDX()).format(3).toTable(8).getStr() +
                    " | " +
                    new DataHandler(polygonRepository.findById(polygonRepository.getSizePolygonStations() - 2).getDDX()).format(3).toTable(6).getStr() +
                    " | " +
                    new DataHandler(polygonRepository.findById(polygonRepository.getSizePolygonStations() - 2).getDY()).format(3).toTable(8).getStr() +
                    " | " +
                    new DataHandler(polygonRepository.findById(polygonRepository.getSizePolygonStations() - 2).getDDY()).format(3).toTable(6).getStr() +
                    " |              |              |");
            case TT, ZT, OT -> llReportXY.add("|            |          |          |         |          |          |        |          |        |              |              |");
        }
        llReportXY.add("| " + new DataHandler(polygonRepository.findById(polygonRepository.getSizePolygonStations() - 1).getName()).toTable(10).getStr() +
                " |          |          |         |          |          |        |          |        | " +
                new DataHandler(polygonRepository.findById(polygonRepository.getSizePolygonStations() - 1).getX()).toTable(12).getStr() +
                " | " +
                new DataHandler(polygonRepository.findById(polygonRepository.getSizePolygonStations() - 1).getY()).toTable(12).getStr() +
                " |");
        llReportXY.add("-------------------------------------------------------------------------------------------------------------------------------");
        llReportXY.add("");
        llReportXY.add(titlesReports.get("TPfoterTitle"));
        llReportXY.add(titlesReports.get("TPperimeter") + new DataHandler(polygonRepository.getPerimeter()).format(3).getStr() + titlesReports.get("TPm"));
        llReportXY.add(titlesReports.get("TPangleResidues"));
        if (polygonRepository.getBindType() == PolygonRepository.BindType.TT) {
            llReportXY.add(titlesReports.get("TPactual") + new DataHandler(polygonRepository.getfHor()).format(2).getStr() + titlesReports.get("TPsek"));
            llReportXY.add(titlesReports.get("TPacceptable") +
                    new DataHandler(parentFrame.getSettings().getValueFHor() * Math.sqrt(polygonRepository.getSizePolygonStations() - 2)).format(0).getStr() +
                    titlesReports.get("TPsek"));
        } else {
            llReportXY.add(titlesReports.get("TPactual") + "-.-");
            llReportXY.add(titlesReports.get("TPacceptable") + "-.-");

        }
        llReportXY.add(titlesReports.get("TPlineResidues"));
        if (polygonRepository.getBindType() == TZ | polygonRepository.getBindType() == PolygonRepository.BindType.ZT) {
            llReportXY.add(titlesReports.get("TPlineDX") + "-.-");
            llReportXY.add(titlesReports.get("TPlineDY") + "-.-");
            llReportXY.add(titlesReports.get("TPabsoluteDeviation") + "-.-");
            llReportXY.add(titlesReports.get("TPactualRelativeDeviation") + "-.-");
            llReportXY.add(titlesReports.get("TPacceptableRelativeDeviation") + "-.-");
        } else {
            llReportXY.add(titlesReports.get("TPlineDX") + new DataHandler(polygonRepository.getfX()).format(3).getStr() + "м.");
            llReportXY.add(titlesReports.get("TPlineDY") + new DataHandler(polygonRepository.getfY()).format(3).getStr() + "м.");
            llReportXY.add(titlesReports.get("TPabsoluteDeviation") + new DataHandler(polygonRepository.getfAbs()).format(3).getStr() + "м.");
            llReportXY.add(titlesReports.get("TPactualRelativeDeviation") + "1:" + polygonRepository.getfOtn());
            llReportXY.add(titlesReports.get("TPacceptableRelativeDeviation") + "1:" + parentFrame.getSettings().getValueFOtn());

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
        PolygonRepository polygonRepository = parentFrame.getPolygonRepository();
        double dZCorrected;
        double sumDZ = 0.0;
        double sumDDZ = 0.0;
        double sumDZCorrected = 0.0;
        int start = 0;
        int finish = polygonRepository.getSizePolygonStations() - 1;

        LinkedList<String> llTopReportZ = new Shell(parentFrame).getTopReportZ();
        LinkedList<String> llReportZ = new LinkedList<>(llTopReportZ);
        HashMap<String, String> titlesReports = new Shell(parentFrame).getTitlesReports();
//        String str;
//        while ((str = llTopReportZ.pollFirst()) != null) {
//            llReportZ.add(str);
//        }

        if (polygonRepository.getBindType() == PolygonRepository.BindType.TT |
                polygonRepository.getBindType() == PolygonRepository.BindType.TO |
                polygonRepository.getBindType() == PolygonRepository.BindType.TZ) {
            start = 1;
        }
        if (polygonRepository.getBindType() == PolygonRepository.BindType.ZT |
                polygonRepository.getBindType() == PolygonRepository.BindType.OT |
                polygonRepository.getBindType() == PolygonRepository.BindType.TT) {
            finish = polygonRepository.getSizePolygonStations() - 2;
        }
        for (int i = start; i < finish; i++) {
            dZCorrected = Double.parseDouble(polygonRepository.findById(i).getDZ()) +
                    polygonRepository.findById(i).getDDZ();
            sumDZCorrected += dZCorrected;
            sumDDZ += polygonRepository.findById(i).getDDZ();
            sumDZ += Double.parseDouble(polygonRepository.findById(i).getDZ());
            llReportZ.add("| " + new DataHandler(polygonRepository.findById(i).getName()).toTable(10).getStr() +
                    " |          |            |        |            | " +
                    new DataHandler(polygonRepository.findById(i).getZ()).toTable(8).getStr() + " |");
            llReportZ.add("|            | " +
                    new DataHandler(polygonRepository.findById(i).getLine()).toTable(8).getStr() +
                    " | " +
                    new DataHandler(polygonRepository.findById(i).getDZ()).toTable(10).getStr() +
                    " | " +
                    new DataHandler(polygonRepository.findById(i).getDDZ() * 1000).format(2).toTable(6).getStr() +
                    " | " +
                    new DataHandler(dZCorrected).format(3).toTable(10).getStr() +
                    " |          |"
            );
        }
        llReportZ.add("| " + new DataHandler(polygonRepository.findById(finish).getName()).toTable(10).getStr() +
                " |          |            |        |            | " +
                new DataHandler(polygonRepository.findById(finish).getZ()).toTable(8).getStr() + " |");
        llReportZ.add("|------------|----------|------------|--------|------------|----------|");
        llReportZ.add("|" + titlesReports.get("TPcontrol") +" | " +
                new DataHandler(polygonRepository.getPerimeter()).format(3).toTable(8).getStr() +
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
                new DataHandler(polygonRepository.getPerimeter()).format(3).getStr() +
                titlesReports.get("TPm"));
        if (polygonRepository.getBindType() == PolygonRepository.BindType.TZ |
                polygonRepository.getBindType() == PolygonRepository.BindType.ZT) {
            llReportZ.add(titlesReports.get("TPactualResidue") + "-.-");
            llReportZ.add(titlesReports.get("TPacceptableResidue") + "-.-");

        } else {
            llReportZ.add(titlesReports.get("TPactualResidue") +
                    new DataHandler(polygonRepository.getfZ() * 1000).format(0).getStr() +
                    titlesReports.get("TPmm"));
            llReportZ.add(titlesReports.get("TPacceptableResidue") +
                    new DataHandler(parentFrame.getSettings().getValueFH() *
                            Math.sqrt(polygonRepository.getPerimeter() / 1000)).format(0).getStr() +
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
        for (int i = 0; i < parentFrame.getPolygonRepository().getSizePolygonStations(); i++) {
            polygonStation = parentFrame.getPolygonRepository().findById(i);
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
        if (polygonRepository.findById(idx).getStatus() & idx < parentFrame.getPolygonRepository().getSizePolygonStations() - 1) {
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
}
