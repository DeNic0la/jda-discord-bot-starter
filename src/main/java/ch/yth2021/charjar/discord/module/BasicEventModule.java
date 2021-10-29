package ch.yth2021.charjar.discord.module;

import net.dv8tion.jda.api.events.message.MessageReceivedEvent;

public interface BasicEventModule {
    public void onMessageReceived(MessageReceivedEvent event);
}
