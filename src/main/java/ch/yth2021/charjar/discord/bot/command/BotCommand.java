package ch.yth2021.charjar.discord.bot.command;

import net.dv8tion.jda.api.events.interaction.SlashCommandEvent;
import net.dv8tion.jda.api.interactions.commands.build.CommandData;
import org.jetbrains.annotations.NotNull;

public abstract class BotCommand extends CommandData {

    /**
     * Create a command builder.
     *
     * @param name The command name, 1-32 lowercase alphanumeric characters
     * @param description The command description, 1-100 characters
     * @throws IllegalArgumentException If any of the following requirements are not met
     * <ul>
     *     <li>The name must be lowercase alphanumeric (with dash), 1-32 characters long</li>
     *     <li>The description must be 1-100 characters long</li>
     * </ul>
     */
    public BotCommand(@NotNull String name, @NotNull String description) {
        super(name, description);
    }

    public void initCommandOptions() {
    }

    public abstract void execute(SlashCommandEvent event);

}
