package ch.yth2021.charjar.discord.bot.listener;

import ch.yth2021.charjar.discord.bot.Application;
import ch.yth2021.charjar.discord.module.BasicEventModule;
import ch.yth2021.charjar.discord.module.friendly.FriendlyPeopleModule;
import ch.yth2021.charjar.discord.module.swear.SwearModule;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

public class MessageEventListener extends ListenerAdapter {

    public MessageEventListener() {
        listeners.add(new SwearModule());
        listeners.add(new FriendlyPeopleModule());
    }

    List<BasicEventModule> listeners = new ArrayList<>();

    @Override
    public void onMessageReceived(@NotNull MessageReceivedEvent event) {
        for (BasicEventModule bem : listeners) {
            Application.getCommandExecutor().submit(() -> {
                bem.onMessageReceived(event);
            });
        }


        System.out.printf("[%s]: %s\n", event.getAuthor().getName(), event.getMessage().getContentDisplay());

    }
}
