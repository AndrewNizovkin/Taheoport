    package taheoport;

    import java.util.LinkedList;

    /**
     * This class encapsulates result of extracting the polygon from measurements
     * @author Andrey Nizovkin
     * Copyright Nizovkin A.V. 2022
     */
    public class ExtractProject extends LinkedList<ExtractStation> {
        private MainWin parentFrame;

        /**
         * Constructor
         * @param frame
         */
        public ExtractProject(MainWin frame) {
            parentFrame = frame;
        }

        /**
         * extracts PolList from SurveyProject
         * @return
         */
        public LinkedList<String> extractTheoProject() {
        LinkedList<String> llPolList = new LinkedList<String>();
        SurveyProject surveyProject = new SurveyProject(parentFrame);
        for (int i = 0; i < parentFrame.getSurveyProject().sizeStations(); i++) {
            if (parentFrame.getSurveyProject().getStation(i).getName().charAt(0) != (char) parentFrame.getOptions().getPrefixEX() &
                    parentFrame.getSurveyProject().getStation(i).sizePickets() >= 2) {
                surveyProject.addStation(parentFrame.getSurveyProject().getStation(i));
            }
        }

        ExtractStation extractStation = new ExtractStation();
        extractStation.setName(surveyProject.getStation(0).getPicket(0).getpName());
        extractStation.setHorBack(surveyProject.getStation(0).getPicket(0).getHor());
        extractStation.setHorForward(surveyProject.getStation(0).getPicket(0).getHor());
        extractStation.setLineBack(surveyProject.getStation(0).getPicket(0).getLine());
        extractStation.setLineForward(surveyProject.getStation(0).getPicket(0).getLine());
        extractStation.setdZBack(new DataHandler(surveyProject.getStation(0).getPicket(0).getDZ()).format(3).getStr());
        extractStation.setdZForward(new DataHandler(-1 * Double.parseDouble(surveyProject.getStation(0).getPicket(0).getDZ())).format(3).getStr());
        add(extractStation);

        for (int i = 0; i <= surveyProject.sizeStations() - 2; i++) {
            extractStation = new ExtractStation();
            extractStation.setName(surveyProject.getStation(i).getName());
            extractStation.setHorBack(surveyProject.getStation(i).getPicket(0).getHor());
            extractStation.setHorForward(surveyProject.getStation(i).getPicket(1).getHor());
            extractStation.setLineBack(surveyProject.getStation(i + 1).getPicket(0).getLine());
            extractStation.setLineForward(surveyProject.getStation(i).getPicket(1).getLine());
            extractStation.setdZBack(new DataHandler(surveyProject.getStation(i + 1).getPicket(0).getDZ()).format(3).getStr());
            extractStation.setdZForward(new DataHandler( surveyProject.getStation(i).getPicket(1).getDZ()).format(3).getStr());
            add(extractStation);
        }

            extractStation = new ExtractStation();
            extractStation.setName(surveyProject.getStation(surveyProject.sizeStations() - 1).getName());
            extractStation.setHorBack(surveyProject.getStation(surveyProject.sizeStations() - 1).getPicket(0).getHor());
            extractStation.setHorForward(surveyProject.getStation(surveyProject.sizeStations() - 1).getPicket(1).getHor());
            extractStation.setLineBack(surveyProject.getStation(surveyProject.sizeStations() - 1).getPicket(1).getLine());
            extractStation.setLineForward(surveyProject.getStation(surveyProject.sizeStations() - 1).getPicket(1).getLine());
            extractStation.setdZBack(new DataHandler(-1 * Double.parseDouble(surveyProject.getStation(surveyProject.sizeStations() - 1).getPicket(1).getDZ())).format(3).getStr());
            extractStation.setdZForward(new DataHandler(surveyProject.getStation(surveyProject.sizeStations() - 1).getPicket(1).getDZ()).format(3).getStr());
            add(extractStation);

            llPolList.add("");
            for (int i = 0; i < size(); i++) {
                llPolList.add(get(i).getName() + " " +
                        get(i).getHorTrue() + " " +
                        get(i).getLineTrue() + " " +
                        get(i).getDZTrue() + " Not Not Not");
            }
            llPolList.add(surveyProject.getStation(surveyProject.sizeStations() - 1).getPicket(1).getpName() + " Not Not Not Not Not Not");
        return llPolList;
    }

        /**
         * gets report of extracting pol from SurveyProject
         * @return LinkedList<String>
         */
    public LinkedList<String> getExtractReport() {
            LinkedList<String> llExtractReport = new LinkedList<String>();
            String str;
            LinkedList<String> llTopReportExtract = new Shell(parentFrame).getTopReportExtract();
            while ((str = llTopReportExtract.pollFirst()) != null) {
                llExtractReport.add(str);
            }

            for (int i = 0; i < size(); i++) {
                llExtractReport.add("| " + new DataHandler(get(i).getName()).toTable(10).getStr() +
                        " |          |          |          |        |          |          |          |        |");
                llExtractReport.add("|            | " + new DataHandler(get(i).getLineForward()).toTable(8).getStr() + " | " +
                                new DataHandler(get(i).getLineBack()).toTable(8).getStr() + " | " +
                                new DataHandler(get(i).getLineTrue()).toTable(8).getStr() + " | " +
                                new DataHandler(get(i).getDLine()).toTable(6).getStr() + " | " +
                                new DataHandler(get(i).getdZForward()).toTable(8).getStr() + " | " +
                                new DataHandler(get(i).getdZBack()).toTable(8).getStr() + " | " +
                                new DataHandler(get(i).getDZTrue()).toTable(8).getStr() + " | " +
                                new DataHandler(get(i).getDDZ()).toTable(6).getStr() + " |");
            }
        llExtractReport.add("| " + new DataHandler(parentFrame.getSurveyProject().getStation(parentFrame.getSurveyProject().sizeStations() - 1).getPicket(1).getpName()).toTable(10).getStr() +
                " |          |          |          |        |          |          |          |        |");
            llExtractReport.add("--------------------------------------------------------------------------------------------------");
            return llExtractReport;
    }

    // The END of ExtractProject
    }
