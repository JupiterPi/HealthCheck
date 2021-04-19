package jupiterpi.healthcheck.discord;

import jupiterpi.healthcheck.ResourcesFile;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.entities.Guild;
import net.dv8tion.jda.api.entities.TextChannel;

import javax.security.auth.login.LoginException;

public class DiscordBot {
    private String localName;

    private JDA jda;
    private TextChannel channel;

    public DiscordBot(String localName) {
        this.localName = localName;
        try {
            jda = JDABuilder.createDefault(ResourcesFile.get("token"))
                    .build();
            jda.awaitReady();
            Guild guild = jda.getGuildById(ResourcesFile.get("guild-id"));
            channel = guild.getTextChannelById(ResourcesFile.get("console-channel-id"));
        } catch (LoginException e) {
            e.printStackTrace();
            System.exit(-1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private String prefix() {
        return String.format("[%s] ", localName);
    }

    public void sendOtherDown(String otherName) {
        channel.sendMessage(prefix() + "Didn't get response from other server " + otherName).queue();
    }

    public void sendServiceDown(String serviceName) {
        channel.sendMessage(prefix() + "Couldn't get response from local service " + serviceName).queue();
    }
}