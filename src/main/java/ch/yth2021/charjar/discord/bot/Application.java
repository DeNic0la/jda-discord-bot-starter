package ch.yth2021.charjar.discord.bot;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;
import javax.security.auth.login.LoginException;

import ch.yth2021.charjar.discord.bot.command.BotCommand;
import ch.yth2021.charjar.discord.bot.command.HelloCommand;
import ch.yth2021.charjar.discord.bot.listener.CommandListener;
import ch.yth2021.charjar.discord.bot.listener.ReadListener;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.entities.Activity;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Application {

    public static final String HACKATHON_SERVER_ID = "902564942685802576";
    private static JDA jda;
    private static Logger logger = LoggerFactory.getLogger(Application.class);

    private static HashMap<String, BotCommand> commands = new HashMap<>();
    private static ThreadPoolExecutor commandExecutor = (ThreadPoolExecutor) Executors.newCachedThreadPool();

    public static void main(String[] args) throws InterruptedException, LoginException {
        var properties = new ApplicationProperties();

        var token = properties.getDiscordToken();
        var clientId = properties.getDiscordClientId();

        printInviteLink(clientId);

        //Register Commands
        commands.put("hello", new HelloCommand());

        jda = JDABuilder.createDefault(token)
                        .setActivity(Activity.playing("Loading..."))
                        .addEventListeners(new ReadListener())
                        .addEventListeners(new CommandListener())
                        .build();
    }

    public static JDA getJDA() {
        return jda;
    }

    public static HashMap<String, BotCommand> getCommands() {
        return commands;
    }

    public static ThreadPoolExecutor getCommandExecutor() {
        return commandExecutor;
    }

    public static void printInviteLink(String clientId) {
        //You can get the PERMISSIONS INTEGER on the discord developer page
        var inviteLink = "https://discordapp.com/oauth2/authorize?client_id=" + clientId + "&scope=bot%20applications.commands&permissions=137439333440";

        logger.info("==================");
        logger.info("Invite me: " + inviteLink);
        logger.info("==================");
    }

}
