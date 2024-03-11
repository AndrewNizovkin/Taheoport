package taheoport.controllers;

import taheoport.gui.MainWin;
import taheoport.model.ExtractStation;
import taheoport.model.Shell;
import taheoport.model.SurveyProject;

import java.util.LinkedList;
import java.util.List;

public class ExtractControllerDefault implements ExtractController{
    private final MainWin parentFrame;
    List<ExtractStation> extractProject;

    public ExtractControllerDefault(MainWin frame) {
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
        SurveyProject surveyProject = new SurveyProject(parentFrame);
        for (int i = 0; i < parentFrame.getSurveyProject().sizeStations(); i++) {
            if (parentFrame.getSurveyProject().getStation(i).getName().charAt(0)
                    != (char) parentFrame.getSettings().getPrefixEX() &
                    parentFrame.getSurveyProject().getStation(i).sizePickets() >= 2) {
                surveyProject.addStation(parentFrame.getSurveyProject().getStation(i));
            }
        }

        ExtractStation extractStation = new ExtractStation();
        extractStation.setName(surveyProject.getStation(0).getPicket(0).getpName());
        extractStation.setHorBack(surveyProject.getStation(0).getPicket(0).getHor());
        extractStation.setHorForward(surveyProject.getStation(0).getPicket(0).getHor());
        extractStation.setLineBack(surveyProject.getStation(0).getPicket(0).getpHorLine());
        extractStation.setLineForward(surveyProject.getStation(0).getPicket(0).getpHorLine());
        extractStation.setdZBack(new DataHandler(surveyProject.getStation(0).
                getPicket(0).getDZ()).format(3).getStr());
        extractStation.setdZForward(new DataHandler(-1 * Double.parseDouble(surveyProject.getStation(0).
                getPicket(0).getDZ())).format(3).getStr());
        extractProject.add(extractStation);

        for (int i = 0; i <= surveyProject.sizeStations() - 2; i++) {
            extractStation = new ExtractStation();
            extractStation.setName(surveyProject.getStation(i).getName());
            extractStation.setHorBack(surveyProject.getStation(i).getPicket(0).getHor());
            extractStation.setHorForward(surveyProject.getStation(i).getPicket(1).getHor());
            extractStation.setLineBack(surveyProject.getStation(i + 1).getPicket(0).getpHorLine());
            extractStation.setLineForward(surveyProject.getStation(i).getPicket(1).getpHorLine());
            extractStation.setdZBack(new DataHandler(surveyProject.getStation(i + 1).
                    getPicket(0).getDZ()).format(3).getStr());
            extractStation.setdZForward(new DataHandler( surveyProject.getStation(i).
                    getPicket(1).getDZ()).format(3).getStr());
            extractProject.add(extractStation);
        }

        extractStation = new ExtractStation();
        extractStation.setName(surveyProject.getStation(surveyProject.sizeStations() - 1).getName());
        extractStation.setHorBack(surveyProject.getStation(surveyProject.sizeStations() - 1).
                getPicket(0).getHor());
        extractStation.setHorForward(surveyProject.getStation(surveyProject.sizeStations() - 1).
                getPicket(1).getHor());
        extractStation.setLineBack(surveyProject.getStation(surveyProject.sizeStations() - 1).
                getPicket(1).getpHorLine());
        extractStation.setLineForward(surveyProject.getStation(surveyProject.sizeStations() - 1).
                getPicket(1).getpHorLine());
        extractStation.setdZBack(new DataHandler(-1 * Double.parseDouble(surveyProject.getStation(surveyProject.sizeStations() - 1).
                getPicket(1).getDZ())).format(3).getStr());
        extractStation.setdZForward(new DataHandler(surveyProject.getStation(surveyProject.sizeStations() - 1).
                getPicket(1).getDZ()).format(3).getStr());
        extractProject.add(extractStation);

        llPolList.add("");
        for (ExtractStation station : extractProject) {
            llPolList.add(station.getName() + " " +
                    station.getHorTrue() + " " +
                    station.getLineTrue() + " " +
                    station.getDZTrue() + " Not Not Not");
        }
        llPolList.add(surveyProject.getStation(surveyProject.sizeStations() - 1).
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
        llExtractReport.add("| " + new DataHandler(parentFrame.getSurveyProject().
                getStation(parentFrame.getSurveyProject().sizeStations() - 1).
                getPicket(1).getpName()).toTable(10).getStr() +
                " |          |          |          |        |          |          |          |        |");
        llExtractReport.add("--------------------------------------------------------------------------------------------------");
        return llExtractReport;

    }
}
