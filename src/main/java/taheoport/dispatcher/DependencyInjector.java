package taheoport.dispatcher;

/**
 * This class is intended for injecting dependencies into objects
 */
public class DependencyInjector {

    private static DependencyInjector instance;

    /**
     * Constructor
     */
    private DependencyInjector() {

    }

    /**
     * Gets instance of DependencyInjector
     * @return dependencyInjector
     */
    public static DependencyInjector getInstance() {
        if (instance == null) {
            instance = new DependencyInjector();
        }
        return instance;
    }
}
