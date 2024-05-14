package taheoport.service;

import taheoport.gui.MainWin;
import taheoport.model.Picket;
import taheoport.model.SurveyProject;
import taheoport.model.SurveyStation;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ImportControllerDefault implements ImportController{
    private MainWin parentFrame;

    public ImportControllerDefault(MainWin frame) {
        this.parentFrame = frame;
    }

    /**
     * Loads from tah
     *
     * @return SurveyProject
     */
    @Override
    public SurveyProject loadTah(List<String> list) {
        SurveyProject surveyProject = new SurveyProject(parentFrame);
        SurveyStation surveyStation;
        String sep =" ";
        String str;
        String [] array;
        if (list == null) {
            return null;
        }
        surveyProject.setAbsoluteTahPath(list.remove(0));
        try {
            str = list.remove(0);
            while (!str.contains("//") && list.size() > 1) {
                str = new DataHandler(str).compress(sep).getStr();
                array = str.split(sep);
                surveyProject.addStation(array[0], array[1], array[2], array[3], array[5], array[6], array[7], "0.000", array[4]);
                str = (String) list.remove(0);
            }
            int index = 0;
            surveyStation = surveyProject.getStation(index);
            while (!list.isEmpty()) {
                str = (String) list.remove(0);
                if (!str.contains("//")) {
                    str = new DataHandler(str).compress(sep).getStr();
                    array = str.split(sep);
                    surveyStation.addPicket(array[0], array[1], array[2], array[3], array[4], String.valueOf(surveyStation.sizePickets()));
                } else {
                    index = index + 1;
                    if (index < surveyProject.sizeStations()) {
                        surveyStation = surveyProject.getStation(index);
                    }
                }
            }
        } catch (NoSuchElementException e) {
            System.out.println("element not found");
        }
        return surveyProject;
    }

    /**
     * Loads from Leica *.gis
     *
     * @return SurveyProject
     */
    @Override
    public SurveyProject loadLeica(List<String> list) {
        SurveyProject surveyProject = new SurveyProject(parentFrame);
        SurveyStation surveyStation = new SurveyStation();
        String sep =" ";
        String code = "Not";
        String currentToolHeight = "Not";
        DataHandler[] lineHandlers;
//        int res = 0;
        String [] array;
        surveyProject.setAbsoluteTahPath(list.remove(0));
        try {
            while (!list.isEmpty()) {
                array = (list.remove(0)).split(sep);
                lineHandlers = new DataHandler[array.length];
                switch (array[0].substring(0, 2)) {
                    case "41" ->
                            code = array[0].substring(7);
                    case "11" -> {
                        for (String str : array) {
                            switch (str.substring(0, 2)) {
                                case "11":
                                    lineHandlers[6] = new DataHandler(str.substring(7)).removeFirstZero();
                                case "31":
                                    lineHandlers[1] = new DataHandler(str.substring(7)).setPointPosition(5).format(3);
                                    break;
                                case "21":
                                    lineHandlers[2] = new DataHandler(str.substring(7)).setPointPosition(3).format(4);
                                    break;
                                case "22":
                                    lineHandlers[3] = new DataHandler(str.substring(7)).setPointPosition(3).ZenithToVert().format(4);
                                    break;
                                case "87":
                                    lineHandlers[4] = new DataHandler(str.substring(7)).setPointPosition(5).format(3);
                                    break;
                                case "88":
                                    lineHandlers[5] = new DataHandler(str.substring(7)).setPointPosition(5).format(3);
                                    break;
                            }
                        }
                        if (!code.equals("Not")) {
                            lineHandlers[0] = new DataHandler(code).removeFirstZero();
                        } else {
                            lineHandlers[0] = lineHandlers[6];
                        }
                        if (lineHandlers[5].getStr().equals(currentToolHeight)) {
                            surveyStation.addPicket(lineHandlers[0].getStr(),
                                    lineHandlers[1].getStr(),
                                    lineHandlers[2].getStr(),
                                    lineHandlers[3].getStr(),
                                    lineHandlers[4].getStr(),
                                    lineHandlers[6].getStr());
                        } else {
                            surveyStation = surveyProject.addStation();
                            surveyStation.setVi(lineHandlers[5].getStr());
                            currentToolHeight = lineHandlers[5].getStr();
                            surveyStation.addPicket(lineHandlers[0].getStr(),
                                    lineHandlers[1].getStr(),
                                    lineHandlers[2].getStr(),
                                    lineHandlers[3].getStr(),
                                    lineHandlers[4].getStr(),
                                    lineHandlers[6].getStr());
                        }
                    }
                }
            }

        } catch (NoSuchElementException e) {
            System.out.println("element not found");
        }
        return surveyProject;
    }

    /**
     * Loads from Topcon *.txt
     *
     * @return SurveyProject
     */
    @Override
    public SurveyProject loadTopcon(List<String> list) {
        SurveyProject surveyProject = new SurveyProject(parentFrame);
        SurveyStation surveyStation;
        Picket picket;
        surveyProject.setAbsoluteTahPath(list.remove(0));
        String[] measurements;
        String[] measurement;
        String [] array = list.remove(0).split("_'");
        for (String row : array) {
            Matcher m = Pattern.compile("^.+?\\+").matcher(row);
            if (m.find()) {
                surveyStation = surveyProject.addStation();
                surveyStation.setName(row.substring(0, row.indexOf("_", 0)));
                surveyStation.setVi(new DataHandler(row.substring(row.indexOf(")", 0) + 1,
                        row.indexOf("_", row.indexOf(")", 0)))).format(3).getStr());
                measurements = row.substring(m.end()).split("_\\+");
                for (String record : measurements) {
                    picket = surveyStation.addPicket();
                    Matcher mm = Pattern.compile("[_*\\?*\\+m*d*,*]").matcher(record);
                    record = new DataHandler(mm.replaceAll(" ")).compress(" ").getStr();
                    measurement = record.split(" ");
                    picket.setpName(measurement[0]);
                    picket.setLine(new DataHandler(measurement[1]).setPointPosition(5).format(3).getStr());
                    picket.setVert(new DataHandler(measurement[2]).setPointPosition(3).ZenithToVert().format(4).getStr());
                    picket.setHor(new DataHandler(measurement[3]).setPointPosition(3).format(4).getStr());
                    picket.setV(new DataHandler(measurement[7]).format(3).getStr());
                }
            }
        }
        return surveyProject;
    }

    /**
     * Loads from Nicon *.row
     *
     * @return SurveyProject
     */
    @Override
    public SurveyProject loadNicon(List<String> list) {
        SurveyProject surveyProject = new SurveyProject(parentFrame);
        SurveyStation surveyStation = new SurveyStation();
        surveyProject.setAbsoluteTahPath(list.remove(0));
        try {
            while (!list.isEmpty()){
                String [] array = list.remove(0).split(",");
                switch (array[0]) {
                    case "ST" -> surveyStation = surveyProject.addStation(array[1],
                            "0.000", "0.000", "0.000",
                            array[3],
                            "0.000", "0.000", "0.000",
                            new DataHandler(array[5]).format(3).getStr());
                    case "SS" -> surveyStation.addPicket(array[7],
                            new DataHandler(array[3]).format(3).getStr(),
                            new DataHandler(array[4]).format(4).getStr(),
                            new DataHandler(array[5]).format(4).getStr(),
                            new DataHandler(array[2]).format(3).getStr(),
                            array[1]);
                }
            }
        } catch (NoSuchElementException e) {
            System.out.println("element not found");
        }
        return surveyProject;
    }
}
