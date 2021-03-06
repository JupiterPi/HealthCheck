package jupiterpi.healthcheck;

import jupiterpi.healthcheck.discord.DiscordBot;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

@RestController
@RequestMapping("")
public class Controller {
    DiscordBot bot = new DiscordBot(ResourcesFile.get("local-name"));

    @GetMapping("/test")
    public String test() {
        return "ok";
    }

    @GetMapping("/ping")
    public String ping() throws IOException {
        HttpRequest request = new HttpRequest("http://localhost:8080/tust");
        HttpRequest.Response response = request.send();

        System.out.println(response.responseCode);
        System.out.println(response.responseText);

        if (checkServices()) return "ok";
        else return "failed";
    }

    private boolean checkServices() {
        for (CheckableService service : CheckableService.getServices()) {
            String url = service.getPingUrl();
            HttpRequest.Response response = new HttpRequest(url).send();
            if (response.responseCode != 200) {
                bot.sendServiceDown(service.getName());
                return false;
            }
        }
        return true;
    }

    @GetMapping(value = {"/service1", "/service2", "/service3"})
    public String service() {
        return "ok";
    }
}