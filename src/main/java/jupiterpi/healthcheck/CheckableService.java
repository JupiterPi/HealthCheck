package jupiterpi.healthcheck;

import jupiterpi.tools.files.Path;
import jupiterpi.tools.files.csv.CSVCastable;
import jupiterpi.tools.files.csv.CSVObjectsFile;

import java.util.List;

public class CheckableService implements CSVCastable {
    private String name;
    private String pingUrl;

    private CheckableService(String name, String pingUrl) {
        this.name = name;
        this.pingUrl = pingUrl;
    }

    public String getName() {
        return name;
    }

    public String getPingUrl() {
        return pingUrl;
    }

    /* csv */

    public CheckableService(String[] f) {
        name = f[0];
        pingUrl = f[1];
    }

    @Override
    public String[] toCSV() {
        return new String[]{name, pingUrl};
    }

    /* disc access */

    private static final Path registryFile = Path.getRunningDirectory().file("services.csv");

    private static List<CheckableService> services = null;

    public static List<CheckableService> getServices() {
        if (services == null) {
            CSVObjectsFile<CheckableService> file = new CSVObjectsFile<CheckableService>(registryFile, CheckableService.class);
            services = file.getObjects();
        }
        return services;
    }
}