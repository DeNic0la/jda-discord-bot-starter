package io.viascom.discord.bot;

import javax.security.auth.login.LoginException;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.JDABuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Application {

    private static Logger logger = LoggerFactory.getLogger(Application.class);

    public static void main(String[] args) throws InterruptedException, LoginException {
        var properties = new ApplicationProperties();

        var token = properties.getDiscordToken();
        var clientId = properties.getDiscordClientId();

        printInviteLink(clientId);


        JDA jda = JDABuilder.createDefault(token).build();
        jda.awaitReady();
    }

    public static void printInviteLink(String clientId) {
        //You can get the PERMISSIONS INTEGER on the discord developer page
        var inviteLink = "https://discordapp.com/oauth2/authorize?client_id=" + clientId + "&scope=bot%20applications.commands&permissions=137439333440";

        logger.info("==================");
        logger.info("Invite me: " + inviteLink);
        logger.info("==================");
    }

}
