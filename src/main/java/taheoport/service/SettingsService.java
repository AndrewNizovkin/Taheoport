package taheoport.service;

/**
 * This interface defines methods for working with program settings
 */
public interface SettingsService {

    /**
     * Writes settings to file taheoport.ini
     */
    void saveOptions();

    /**
     * Read options from taheoport.ini
     */
    void loadOptions();

}
