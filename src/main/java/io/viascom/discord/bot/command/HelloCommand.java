package io.viascom.discord.bot.command;

import net.dv8tion.jda.api.events.interaction.SlashCommandEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import org.jetbrains.annotations.NotNull;

public class HelloCommand extends ListenerAdapter {

    @Override
    public void onSlashCommand(@NotNull SlashCommandEvent event) {
        if(event.getName().equals("hello")){
            System.out.println("Command /hello got used");
            event.reply("Hello").queue();
        }
    }
}
