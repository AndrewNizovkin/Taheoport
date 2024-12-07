package taheoport.service;

import taheoport.gui.MainWin;
import taheoport.model.ExtractStation;
import taheoport.model.Shell;
import taheoport.repository.SurveyRepository;

import java.util.LinkedList;
import java.util.List;

/**
 * This class encapsulates data and methods for extracting polygon from survey journal
 */
public class ExtractServiceDefault implements ExtractService {
    private final MainWin parentFrame;
    List<ExtractStation> extractProject;

    public ExtractServiceDefault(MainWin frame) {
        this.parentFrame = frame;
        extractProject = new LinkedList<>();
    }

    /**
     * extracts PolList from SurveyProject
     *
     * @return LinkedList<String>
     */
    @Override
    public List<String> extractPolygonProject() {
        extractProject.clear();
        LinkedList<String> llPolList = new LinkedList<>();
        SurveyRepository surveyRepository = new SurveyRepository();
        for (int i = 0; i < parentFrame.getSurveyRepository().sizeStations(); i++) {
            if (parentFrame.getSurveyRepository().findById(i).getName().charAt(0)
                    != (char) parentFrame.getSettings().getPrefixEX() &
                    parentFrame.getSurveyRepository().findById(i).sizePickets() >= 2) {
                surveyRepository.addStation(parentFrame.getSurveyRepository().findById(i));
            }
        }

        ExtractStation extractStation = new ExtractStation();
        extractStation.setName(surveyRepository.findById(0).getPicket(0).getpName());
        extractStation.setHorBack(surveyRepository.findById(0).getPicket(0).getHor());
        extractStation.setHorForward(surveyRepository.findById(0).getPicket(0).getHor());
        extractStation.setLineBack(surveyRepository.findById(0).getPicket(0).getpHorLine());
        extractStation.setLineForward(surveyRepository.findById(0).getPicket(0).getpHorLine());
        extractStation.setdZBack(new DataHandler(surveyRepository.findById(0).
                getPicket(0).getDZ()).format(3).getStr());
        extractStation.setdZForward(new DataHandler(-1 * Double.parseDouble(surveyRepository.findById(0).
                getPicket(0).getDZ())).format(3).getStr());
        extractProject.add(extractStation);

        for (int i = 0; i <= surveyRepository.sizeStations() - 2; i++) {
            extractStation = new ExtractStation();
            extractStation.setName(surveyRepository.findById(i).getName());
            extractStation.setHorBack(surveyRepository.findById(i).getPicket(0).getHor());
            extractStation.setHorForward(surveyRepository.findById(i).getPicket(1).getHor());
            extractStation.setLineBack(surveyRepository.findById(i + 1).getPicket(0).getpHorLine());
            extractStation.setLineForward(surveyRepository.findById(i).getPicket(1).getpHorLine());
            extractStation.setdZBack(new DataHandler(surveyRepository.findById(i + 1).
                    getPicket(0).getDZ()).format(3).getStr());
            extractStation.setdZForward(new DataHandler( surveyRepository.findById(i).
                    getPicket(1).getDZ()).format(3).getStr());
            extractProject.add(extractStation);
        }

        extractStation = new ExtractStation();
        extractStation.setName(surveyRepository.findById(surveyRepository.sizeStations() - 1).getName());
        extractStation.setHorBack(surveyRepository.findById(surveyRepository.sizeStations() - 1).
                getPicket(0).getHor());
        extractStation.setHorForward(surveyRepository.findById(surveyRepository.sizeStations() - 1).
                getPicket(1).getHor());
        extractStation.setLineBack(surveyRepository.findById(surveyRepository.sizeStations() - 1).
                getPicket(1).getpHorLine());
        extractStation.setLineForward(surveyRepository.findById(surveyRepository.sizeStations() - 1).
                getPicket(1).getpHorLine());
        extractStation.setdZBack(new DataHandler(-1 * Double.parseDouble(surveyRepository.findById(surveyRepository.sizeStations() - 1).
                getPicket(1).getDZ())).format(3).getStr());
        extractStation.setdZForward(new DataHandler(surveyRepository.findById(surveyRepository.sizeStations() - 1).
                getPicket(1).getDZ()).format(3).getStr());
        extractProject.add(extractStation);

        llPolList.add("");
        for (ExtractStation station : extractProject) {
            llPolList.add(station.getName() + " " +
                    station.getHorTrue() + " " +
                    station.getLineTrue() + " " +
                    station.getDZTrue() + " Not Not Not");
        }
        llPolList.add(surveyRepository.findById(surveyRepository.sizeStations() - 1).
                getPicket(1).getpName() + " Not Not Not Not Not Not");
        return llPolList;
    }

    /**
     * gets report of extracting pol from SurveyProject
     *
     * @return LinkedList<String>
     */
    @Override
    public List<String> getExtractReport() {
        List<String> llExtractReport = new LinkedList<>();
        String str;
        LinkedList<String> llTopReportExtract = new Shell(parentFrame).getTopReportExtract();
        while ((str = llTopReportExtract.pollFirst()) != null) {
            llExtractReport.add(str);
        }

        for (ExtractStation extractStation : extractProject) {
            llExtractReport.add("| " + new DataHandler(extractStation.getName()).toTable(10).getStr() +
                    " |          |          |          |        |          |          |          |        |");
            llExtractReport.add("|            | " +
                    new DataHandler(extractStation.getLineForward()).toTable(8).getStr() + " | " +
                    new DataHandler(extractStation.getLineBack()).toTable(8).getStr() + " | " +
                    new DataHandler(extractStation.getLineTrue()).toTable(8).getStr() + " | " +
                    new DataHandler(extractStation.getDLine()).toTable(6).getStr() + " | " +
                    new DataHandler(extractStation.getdZForward()).toTable(8).getStr() + " | " +
                    new DataHandler(extractStation.getdZBack()).toTable(8).getStr() + " | " +
                    new DataHandler(extractStation.getDZTrue()).toTable(8).getStr() + " | " +
                    new DataHandler(extractStation.getDDZ()).toTable(6).getStr() + " |");
        }
        llExtractReport.add("| " + new DataHandler(parentFrame.getSurveyRepository().
                findById(parentFrame.getSurveyRepository().sizeStations() - 1).
                getPicket(1).getpName()).toTable(10).getStr() +
                " |          |          |          |        |          |          |          |        |");
        llExtractReport.add("--------------------------------------------------------------------------------------------------");
        return llExtractReport;

    }
}
