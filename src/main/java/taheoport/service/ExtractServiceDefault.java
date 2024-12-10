package taheoport.service;

import taheoport.gui.MainWin;
import taheoport.model.ExtractStation;
import taheoport.model.Shell;
import taheoport.repository.ExtractRepository;
import taheoport.repository.SurveyRepository;

import java.util.LinkedList;
import java.util.List;

/**
 * This class encapsulates data and methods for extracting polygon from survey journal
 */
public class ExtractServiceDefault implements ExtractService {
    private final MainWin parentFrame;
    private final ExtractRepository extractRepository;
    private final SurveyService surveyService;

    public ExtractServiceDefault(MainWin frame) {
        this.parentFrame = frame;
        extractRepository = new ExtractRepository();
        surveyService = parentFrame.getSurveyService();
    }

    /**
     * extracts PolList from SurveyProject
     *
     * @return LinkedList<String>
     */
    @Override
    public List<String> extractPolygonProject() {
        extractRepository.clear();
        List<String> llPolList = new LinkedList<>();
        SurveyRepository surveyCopyRepository = new SurveyRepository();
        SurveyRepository parentSurveyRepository = surveyService.getSurveyRepository();
        for (int i = 0; i < parentSurveyRepository.sizeStations(); i++) {
            if (parentSurveyRepository.findById(i).getName().charAt(0)
                    != (char) parentFrame.getSettings().getPrefixEX() &
                    parentFrame.getSurveyRepository().findById(i).sizePickets() >= 2) {
                surveyCopyRepository.addStation(parentSurveyRepository.findById(i));
            }
        }

        ExtractStation extractStation = new ExtractStation();
        extractStation.setName(surveyCopyRepository.findById(0).getPicket(0).getpName());
        extractStation.setHorBack(surveyCopyRepository.findById(0).getPicket(0).getHor());
        extractStation.setHorForward(surveyCopyRepository.findById(0).getPicket(0).getHor());
        extractStation.setLineBack(surveyCopyRepository.findById(0).getPicket(0).getpHorLine());
        extractStation.setLineForward(surveyCopyRepository.findById(0).getPicket(0).getpHorLine());
        extractStation.setdZBack(new DataHandler(surveyCopyRepository.findById(0).
                getPicket(0).getDZ()).format(3).getStr());
        extractStation.setdZForward(new DataHandler(-1 * Double.parseDouble(surveyCopyRepository.findById(0).
                getPicket(0).getDZ())).format(3).getStr());
        extractRepository.add(extractStation);

        for (int i = 0; i <= surveyCopyRepository.sizeStations() - 2; i++) {
            extractStation = new ExtractStation();
            extractStation.setName(surveyCopyRepository.findById(i).getName());
            extractStation.setHorBack(surveyCopyRepository.findById(i).getPicket(0).getHor());
            extractStation.setHorForward(surveyCopyRepository.findById(i).getPicket(1).getHor());
            extractStation.setLineBack(surveyCopyRepository.findById(i + 1).getPicket(0).getpHorLine());
            extractStation.setLineForward(surveyCopyRepository.findById(i).getPicket(1).getpHorLine());
            extractStation.setdZBack(new DataHandler(surveyCopyRepository.findById(i + 1).
                    getPicket(0).getDZ()).format(3).getStr());
            extractStation.setdZForward(new DataHandler( surveyCopyRepository.findById(i).
                    getPicket(1).getDZ()).format(3).getStr());
            extractRepository.add(extractStation);
        }

        extractStation = new ExtractStation();
        extractStation.setName(surveyCopyRepository.findById(surveyCopyRepository.sizeStations() - 1).getName());
        extractStation.setHorBack(surveyCopyRepository.findById(surveyCopyRepository.sizeStations() - 1).
                getPicket(0).getHor());
        extractStation.setHorForward(surveyCopyRepository.findById(surveyCopyRepository.sizeStations() - 1).
                getPicket(1).getHor());
        extractStation.setLineBack(surveyCopyRepository.findById(surveyCopyRepository.sizeStations() - 1).
                getPicket(1).getpHorLine());
        extractStation.setLineForward(surveyCopyRepository.findById(surveyCopyRepository.sizeStations() - 1).
                getPicket(1).getpHorLine());
        extractStation.setdZBack(new DataHandler(-1 * Double.parseDouble(surveyCopyRepository.findById(surveyCopyRepository.sizeStations() - 1).
                getPicket(1).getDZ())).format(3).getStr());
        extractStation.setdZForward(new DataHandler(surveyCopyRepository.findById(surveyCopyRepository.sizeStations() - 1).
                getPicket(1).getDZ()).format(3).getStr());
        extractRepository.add(extractStation);

        llPolList.add("");
        for (ExtractStation station : extractRepository) {
            llPolList.add(station.getName() + " " +
                    station.getHorTrue() + " " +
                    station.getLineTrue() + " " +
                    station.getDZTrue() + " Not Not Not");
        }
        llPolList.add(surveyCopyRepository.findById(surveyCopyRepository.sizeStations() - 1).
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
        List<String> listExtractReport = new Shell(parentFrame).getTopReportExtract();

        for (ExtractStation extractStation : extractRepository) {
            listExtractReport.add("| " + new DataHandler(extractStation.getName()).toTable(10).getStr() +
                    " |          |          |          |        |          |          |          |        |");
            listExtractReport.add("|            | " +
                    new DataHandler(extractStation.getLineForward()).toTable(8).getStr() + " | " +
                    new DataHandler(extractStation.getLineBack()).toTable(8).getStr() + " | " +
                    new DataHandler(extractStation.getLineTrue()).toTable(8).getStr() + " | " +
                    new DataHandler(extractStation.getDLine()).toTable(6).getStr() + " | " +
                    new DataHandler(extractStation.getdZForward()).toTable(8).getStr() + " | " +
                    new DataHandler(extractStation.getdZBack()).toTable(8).getStr() + " | " +
                    new DataHandler(extractStation.getDZTrue()).toTable(8).getStr() + " | " +
                    new DataHandler(extractStation.getDDZ()).toTable(6).getStr() + " |");
        }
        listExtractReport.add("| " + new DataHandler(parentFrame.getSurveyRepository().
                findById(parentFrame.getSurveyRepository().sizeStations() - 1).
                getPicket(1).getpName()).toTable(10).getStr() +
                " |          |          |          |        |          |          |          |        |");
        listExtractReport.add("--------------------------------------------------------------------------------------------------");
        return listExtractReport;

    }
}
