package jupiterpi.healthcheck;

import jupiterpi.tools.files.Path;
import jupiterpi.tools.files.csv.CSVCastable;
import jupiterpi.tools.files.csv.CSVObjectsFile;

import java.util.ArrayList;
import java.util.List;

public class Connection implements CSVCastable {
    private String name;
    private String pingUrl;

    private Connection(String name, String pingUrl) {
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

    public Connection(String[] f) {
        name = f[0];
        pingUrl = f[1];
    }

    @Override
    public String[] toCSV() {
        return new String[]{name, pingUrl};
    }

    /* disc access */

    private static final Path registryFile = Path.getRunningDirectory().file("connections");

    private static List<Connection> connections = null;

    public static List<Connection> getConnections() {
        if (connections == null) {
            CSVObjectsFile<Connection> file = new CSVObjectsFile<Connection>(registryFile, Connection.class);
            connections = file.getObjects();
        }
        return connections;
    }
}