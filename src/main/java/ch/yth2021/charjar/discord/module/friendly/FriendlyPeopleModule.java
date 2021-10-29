package ch.yth2021.charjar.discord.module.friendly;

import ch.yth2021.charjar.API.User;
import ch.yth2021.charjar.discord.module.BasicEventModule;
import ch.yth2021.charjar.discord.module.FileReadHelper;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;

public class FriendlyPeopleModule implements BasicEventModule {
    private static final int POINTS_FOR_FRIENDLY_BEHAVIOUR = 10;
    private static final String FILENAME = "friendlywords.txt";

    @Override
    public void onMessageReceived(MessageReceivedEvent event) {
        String message = event.getMessage().getContentDisplay().toLowerCase();
        if (messageContainsFriendlyWord(message)) {
            User user = new User(event.getAuthor().getId());
            try {
                user.modPoints(POINTS_FOR_FRIENDLY_BEHAVIOUR);
            } catch (Exception e) {
                return;
            }
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

