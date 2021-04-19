package jupiterpi.healthcheck;

import jupiterpi.healthcheck.discord.DiscordBot;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

@RestController
@RequestMapping("")
public class Controller {
    DiscordBot bot = new DiscordBot(ResourcesFile.get("local-name"));

    @GetMapping("/ping")
    public String ping() throws IOException {
        //for (CheckableService service : CheckableService.getServices()) {
            //URL url = new URL(service.getPingUrl());
        URL url = new URL("https://github.com");
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setRequestMethod("GET");

        int status = connection.getResponseCode();
        System.out.println(status);

        BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
        String inputLine;
        StringBuffer content = new StringBuffer();
        while ((inputLine = in.readLine()) != null) {
            content.append(inputLine);
        }
        in.close();

        String response = content.toString();
        System.out.println(response);

        connection.disconnect();
        //}

        return "ok";
    }
}