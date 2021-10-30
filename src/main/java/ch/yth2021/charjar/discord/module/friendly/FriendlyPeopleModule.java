package ch.yth2021.charjar.discord.module.friendly;

import ch.yth2021.charjar.API.User;
import ch.yth2021.charjar.API.model.APIRespondedBullshitException;
import ch.yth2021.charjar.discord.bot.Application;
import ch.yth2021.charjar.discord.module.BasicEventModule;
import ch.yth2021.charjar.discord.module.FileReadHelper;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;

public class FriendlyPeopleModule implements BasicEventModule {
    private static final int POINTS_FOR_FRIENDLY_BEHAVIOUR = 10;
    private static final String FILENAME = "friendlywords.txt";
    private final Logger logger = LoggerFactory.getLogger(Application.class);

    @Override
    public void onMessageReceived(MessageReceivedEvent event) {
        String message = event.getMessage().getContentDisplay().toLowerCase();
        if (messageContainsFriendlyWord(message)) {
            User user = new User(event.getAuthor().getId());
            try {
                user.modPoints(POINTS_FOR_FRIENDLY_BEHAVIOUR);
            } catch (IOException e) {
                logger.debug("Network Error", e);
            } catch (APIRespondedBullshitException e) {
                logger.debug("Data from API could not be Processed", e);
            }

            event.getMessage().addReaction("U+1F601").queue();

        }
    }


    private boolean messageContainsFriendlyWord(String message) {
        String[] linesAsArray = FileReadHelper.getLinesAsArray(FILENAME);
        for (String friendlyWord : linesAsArray) {
            if (message.contains(friendlyWord)) {
                return true;
            }
        }
        return false;
    }
}

