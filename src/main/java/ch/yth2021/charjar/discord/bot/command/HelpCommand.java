package ch.yth2021.charjar.discord.bot.command;

import ch.yth2021.charjar.discord.bot.Application;
import net.dv8tion.jda.api.events.interaction.SlashCommandEvent;

public class HelpCommand extends BotCommand {
    public HelpCommand() {
        super("help", "Lists all of the available commands.");
    }

    @Override
    public void initCommandOptions() {
    }

    @Override
    public void execute(SlashCommandEvent event) {
        Application.getCommandExecutor().execute(() -> {
            String message = "Available commands: \n";
            for (String key : Application.getCommands().keySet()) {
                message += " /" + key + "\n";
            }

            event.reply(message).queue();
        });
    }
}
