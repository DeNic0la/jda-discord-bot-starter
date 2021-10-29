package ch.yth2021.charjar.discord.bot.listener;

import ch.yth2021.charjar.discord.bot.Application;
import ch.yth2021.charjar.discord.swearjar.Swearjar;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import org.jetbrains.annotations.NotNull;

public class EventListener extends ListenerAdapter {

    @Override
    public void onMessageReceived(@NotNull MessageReceivedEvent event) {

        Application.getCommandExecutor().submit(() -> {
            Swearjar.startService(event.getMessage().getContentDisplay());
        });

        System.out.printf("[%s]: %s\n", event.getAuthor().getName(), event.getMessage().getContentDisplay());

    }
}
