package io.viascom.discord.bot.listener;

import io.viascom.discord.bot.Application;
import io.viascom.discord.bot.handler.CommandHandler;
import net.dv8tion.jda.api.entities.Activity;
import net.dv8tion.jda.api.events.ReadyEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import org.jetbrains.annotations.NotNull;

public class ReadListener extends ListenerAdapter {

    @Override
    public void onReady(@NotNull ReadyEvent event) {
        //Register Commands
        new CommandHandler().initCommands();

        //Set the activity to "I'm Ready"
        Application.getJDA().getPresence().setActivity(Activity.playing("I'm Ready"));
    }
}
