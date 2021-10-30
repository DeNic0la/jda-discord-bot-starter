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
        Application.getCommandExecutor().execute(() ->
                {
                    User user = new User(event.getUser().getId());
                    try {
                        int userPoints = user.getPoints();
                        event.reply("Your current balance is " + userPoints).queue();
                    }  catch (IOException e) {
                        event.reply("There was an unexpected error :(").queue();
                        logger.error("Network Error", e);
                    } catch (APIRespondedBullshitException e) {
                        event.reply("There was an unexpected error :(").queue();
                        logger.error("Data from API could not be Processed", e);
                    }
                }
        );
    }
}
