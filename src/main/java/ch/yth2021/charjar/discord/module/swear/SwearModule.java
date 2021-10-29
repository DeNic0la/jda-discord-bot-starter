package ch.yth2021.charjar.discord.module.swear;

import ch.yth2021.charjar.API.User;
import ch.yth2021.charjar.API.model.APIRespondedBullshitException;
import ch.yth2021.charjar.discord.bot.Application;
import ch.yth2021.charjar.discord.module.BasicEventModule;
import ch.yth2021.charjar.discord.module.FileReadHelper;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import org.apache.commons.io.IOUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.io.InputStream;
import java.util.Locale;

public class SwearModule implements BasicEventModule {
    private static final String FILENAME = "swearwords.txt";
    private final Logger logger = LoggerFactory.getLogger(Application.class);

    private int countSwearwords(String message) {
        int swearWordCounter = 0;
        String[] linesAsArray = FileReadHelper.getLinesAsArray(FILENAME);
        for (String swearWord : linesAsArray) {
            if (message.contains(swearWord)) {
                swearWordCounter++;
            }
        }
        return swearWordCounter;
    }

    @Override
    public void onMessageReceived(MessageReceivedEvent event) {
        String message = event.getMessage().getContentDisplay().toLowerCase();
        int numberOfSwearwords = countSwearwords(message);
        if (numberOfSwearwords > 0) {
            User user = new User(event.getAuthor().getId());
            try {
                user.modPoints(-numberOfSwearwords);
            } catch (IOException e) {
                logger.debug("Network Error", e);
            } catch (APIRespondedBullshitException e) {
                logger.debug("Data from API could not be Processed", e);
            }
        }
    }
}
