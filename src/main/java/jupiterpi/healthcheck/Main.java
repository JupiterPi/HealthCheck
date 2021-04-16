package jupiterpi.healthcheck;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Main {
    private final static String HELP_TEXT = "With this application, two devices check each other regularly, and if no answer arrives, they send a Discord message to the system administrator. ";

    public static void main(String[] args) {
        if (args[0].equalsIgnoreCase("help")) {
            System.out.println(HELP_TEXT);
            System.out.println("");
            System.out.println("-- Application not started.");
        } else SpringApplication.run(Main.class, args);
    }
}
