package ch.yth2021.charjar.discord.bot.listener;

import ch.yth2021.charjar.discord.bot.Application;
import net.dv8tion.jda.api.events.interaction.SlashCommandEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import org.jetbrains.annotations.NotNull;

public class CommandListener extends ListenerAdapter {

    @Override
    public void onSlashCommand(@NotNull SlashCommandEvent event) {
        if (Application.getCommands().containsKey(event.getName())) {
            try {
                Application.getCommands().get(event.getName()).getClass().getDeclaredConstructor().newInstance().execute(event);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
