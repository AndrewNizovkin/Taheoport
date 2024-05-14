package taheoport.service;

public interface SettingsController {

    /**
     * Writes settings to file taheoport.ini
     */
    void saveOptions();

    /**
     * Read options from taheoport.ini
     */
    void loadOptions();

}
