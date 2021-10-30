package ch.yth2021.charjar.discord.bot;


import ch.yth2021.charjar.API.Quiz;
import ch.yth2021.charjar.API.User;
import ch.yth2021.charjar.discord.bot.command.*;
import ch.yth2021.charjar.discord.bot.listener.CommandListener;
import ch.yth2021.charjar.discord.bot.listener.MessageEventListener;
import ch.yth2021.charjar.discord.bot.listener.ReactionListener;
import ch.yth2021.charjar.discord.bot.listener.ReadListener;
import ch.yth2021.charjar.discord.module.swear.SwearModule;
import ch.yth2021.charjar.discord.processor.PointModificationProcessor;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.entities.Activity;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.security.auth.login.LoginException;
import java.util.HashMap;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;

public class Application {

    public static String HACKATHON_SERVER_ID;
    private static JDA jda;
    private static Logger logger = LoggerFactory.getLogger(Application.class);
    public static ApplicationProperties properties;
    public static String clientId = "";

    private static HashMap<String, BotCommand> commands = new HashMap<>();
    private static ThreadPoolExecutor commandExecutor = (ThreadPoolExecutor) Executors.newCachedThreadPool();
    private static ThreadPoolExecutor eventExecutor = (ThreadPoolExecutor) Executors.newCachedThreadPool();

    public static void main(String[] args) throws InterruptedException, LoginException {

        properties = new ApplicationProperties();

        HACKATHON_SERVER_ID = properties.getServerID();

        //INIT USER API
        User.BASE_URL = properties.getApiURL();

        //INIT Quiz API
        Quiz.BASE_URL = properties.getQuizURL();

        var token = properties.getDiscordToken();
        clientId = properties.getDiscordClientId();

        printInviteLink(clientId);

        //Register Commands
        commands.put("hello", new HelloCommand());
        commands.put("wallet", new WalletCommand());
        commands.put("startrandomevents", new StartRandomEventSchedulerCommand());
        commands.put("quiz", new QuizCommand());

        jda = JDABuilder.createDefault(token)
                .setActivity(Activity.playing("Loading..."))
                .addEventListeners(new ReadListener())
                .addEventListeners(new CommandListener())
                .addEventListeners(new MessageEventListener())
                .addEventListeners(new ReactionListener())
                        .build();

        SwearModule.register(new PointModificationProcessor());
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

    public static ThreadPoolExecutor getEventExecutorExecutor() {
        return eventExecutor;
    }

    public static String getClientId() {
        return clientId;
    }

    public static void printInviteLink(String clientId) {
        //You can get the PERMISSIONS INTEGER on the discord developer page
        var inviteLink = "https://discordapp.com/oauth2/authorize?client_id=" + clientId + "&scope=bot%20applications.commands&permissions=137439333440";

        logger.info("==================");
        logger.info("Invite me: " + inviteLink);
        logger.info("==================");
    }

}
