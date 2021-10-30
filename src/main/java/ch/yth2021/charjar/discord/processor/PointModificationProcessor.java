package ch.yth2021.charjar.discord.processor;

import ch.yth2021.charjar.API.User;
import ch.yth2021.charjar.API.model.APIRespondedBullshitException;
import ch.yth2021.charjar.discord.bot.Application;
import ch.yth2021.charjar.discord.processor.model.EventListener;
import net.dv8tion.jda.api.entities.TextChannel;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;

public class PointModificationProcessor implements EventListener {
    private final Logger logger = LoggerFactory.getLogger(Application.class);

    @Override
    public void onEvent(MessageReceivedEvent event) {
        if (Application.properties.getKickuserActivated().equals("true")) {
            User user = new User(event.getAuthor().getId());
            try {
                if (user.getPoints() <= 0) {
                    event.getGuild().kick(event.getAuthor().getId()).queue();
                    user.modPoints(10);
                    TextChannel channel = Application.getJDA().getTextChannelById(event.getChannel().getId());
                    String author = event.getAuthor().getName();
                    channel.sendMessage(author + " got yeeted from the channel because of their bad behavior.").queue();
                }
            } catch (IOException e) {
                logger.debug("Network Error", e);
            } catch (APIRespondedBullshitException e) {
                logger.debug("Data from API could not be Processed", e);
            }
        }
    }
}
