package io.viascom.discord.bot.listener;

import io.viascom.discord.bot.Application;
import net.dv8tion.jda.api.entities.Activity;
import net.dv8tion.jda.api.events.ReadyEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import org.jetbrains.annotations.NotNull;

public class ReadListener extends ListenerAdapter {

    @Override
    public void onReady(@NotNull ReadyEvent event) {
        Application.getJDA().getPresence().setActivity(Activity.playing("I'm Ready"));

        Application.getJDA().getGuildById(Application.HACKATHON_SERVER_ID).upsertCommand("hello", "Send a hello message").queue();
    }
}
