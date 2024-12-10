package taheoport.service;

import taheoport.gui.MainWin;
import taheoport.model.Settings;

import java.io.File;
import java.util.LinkedList;
import java.util.List;

/**
 * This class encapsulates methods for working with program settings
 */
public class SettingsServiceDefault implements SettingsService {

    private final MainWin parentFrame;

    /**
     * Constructor
     * @param parentFrame MainWin
     */
    public SettingsServiceDefault(MainWin parentFrame) {
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

        parentFrame.getIoService().writeTextFile(list, "taheoport.ini");
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

        List<String > list = parentFrame.getIoService().readTextFile("taheoport.ini");
        list.remove(0);
        String firstLine;
        firstLine = list.remove(0);

        if (firstLine  == null || !firstLine.equals("taheoport_ini")) {
            saveOptions();
            list = parentFrame.getIoService().readTextFile("taheoport.ini");
            list.remove(0);
        }

        String [] array;
        while (!list.isEmpty()) {
            firstLine = list.remove(0);
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
