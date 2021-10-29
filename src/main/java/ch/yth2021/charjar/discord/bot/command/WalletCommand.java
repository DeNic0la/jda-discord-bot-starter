package ch.yth2021.charjar.discord.bot.command;

import ch.yth2021.charjar.API.User;
import ch.yth2021.charjar.API.model.APIRespondedBullshitException;
import ch.yth2021.charjar.discord.bot.Application;
import net.dv8tion.jda.api.events.interaction.SlashCommandEvent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;

public class WalletCommand extends BotCommand {
    private final Logger logger = LoggerFactory.getLogger(Application.class);

    public WalletCommand() {
        super("wallet", "Shows your current balance.");
    }

    @Override
    public void initCommandOptions() {
    }

    @Override
    public void execute(SlashCommandEvent event) {
        System.out.println("WalletCommand executed");
        Application.getCommandExecutor().execute(() ->
                {
                    User user = new User(event.getUser().getId());
                    try {
                        event.reply("Your current balance is " + user.getPoints()).queue();
                    } catch (IOException e) {
                        event.reply("There was an network error :(").queue();
                        logger.debug("Network Error", e);
                    } catch (APIRespondedBullshitException e) {
                        System.err.println(e);
                        event.reply("There was an unexpected error :(").queue();
                        logger.debug("Data from API could not be Processed", e);
                    }
                }
        );
    }
}
