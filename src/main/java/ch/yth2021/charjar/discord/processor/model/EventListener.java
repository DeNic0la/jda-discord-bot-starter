package ch.yth2021.charjar.discord.processor.model;

import net.dv8tion.jda.api.events.message.MessageReceivedEvent;

public interface EventListener {
    public void onEvent(MessageReceivedEvent event);
}
