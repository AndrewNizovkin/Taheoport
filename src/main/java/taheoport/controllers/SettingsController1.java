package taheoport.controllers;

import taheoport.gui.MainWin;
import taheoport.model.Settings;

import java.io.File;
import java.util.LinkedList;

public class SettingsController1 implements SettingsController{

    private final MainWin parentFrame;

    /**
     * Constructor
     * @param parentFrame MainWin
     */
    public SettingsController1(MainWin parentFrame) {
        this.parentFrame = parentFrame;
    }

    /**
     * Writes settings to file taheoport.ini
     */
    @Override
    public void saveOptions() {
        Settings settings = parentFrame.getSettings();
        LinkedList<String> list = new LinkedList<>();
        list.add("taheoport_ini");
        list.add("pathWorkDir=" + settings.getPathWorkDir());
        list.add("idxFH=" + settings.getIdxFH());
        list.add("idxFHor=" + settings.getIdxFHor());
        list.add("idxFAbs=" + settings.getIdxFAbs());
        list.add("idxFOtn=" + settings.getIdxFOtn());
        list.add("prefixEX=" + settings.getPrefixEX());
        list.add("orientStation=" + settings.getOrientStation());
        list.add("language=" + settings.getLanguage());
        list.add("offsetDistanceType=" + settings.getOffsetDistanceType());
        list.add("offsetDirectionType=" + settings.getOffsetDirectionType());
        list.add("offsetTiltAngleType=" + settings.getOffsetTiltType());

        parentFrame.getIoController().writeTextFile(list, "taheoport.ini");
    }

    /**
     * Read options from taheoport.ini
     */
    @Override
    public void loadOptions() {
        Settings settings = parentFrame.getSettings();
        File f = new File("taheoport.ini");
        if (!f.isFile()) {
            saveOptions();
        }

        LinkedList <String > list = parentFrame.getIoController().readTextFile("taheoport.ini");
        list.pollFirst();
        String firstLine;
        firstLine = list.pollFirst();

        if (firstLine  == null || !firstLine.equals("taheoport_ini")) {
            saveOptions();
            list = parentFrame.getIoController().readTextFile("taheoport.ini");
            list.pollFirst();
        }

        String [] array;
        while ((firstLine = list.pollFirst()) != null) {
            array = firstLine.split("=");
            switch (array[0]) {
                case "pathWorkDir" -> settings.setPathWorkDir(array[1]);
                case "idxFH" -> settings.setIdxFH(Integer.parseInt(array[1]));
                case "idxFHor" -> settings.setIdxFHor(Integer.parseInt(array[1]));
                case "idxFAbs" -> settings.setIdxFAbs(Integer.parseInt(array[1]));
                case "idxFOtn" -> settings.setIdxFOtn(Integer.parseInt(array[1]));
                case "prefixEX" -> settings.setPrefixEX(Integer.parseInt(array[1]));
                case "orientStation" -> settings.setOrientStation(Integer.parseInt(array[1]));
                case "language" -> settings.setLanguage(Integer.parseInt(array[1]));
                case "offsetDistanceType" -> settings.setOffsetDistanceType(Integer.parseInt(array[1]));
                case "offsetDirectionType" -> settings.setOffsetDirectionType(Integer.parseInt(array[1]));
                case "offsetTiltAngleType" -> settings.setOffsetTiltType(Integer.parseInt(array[1]));
            }
        }


    }
}
