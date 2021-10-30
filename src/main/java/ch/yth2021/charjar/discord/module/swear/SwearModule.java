package ch.yth2021.charjar.discord.module.swear;

import ch.yth2021.charjar.API.User;
import ch.yth2021.charjar.API.model.APIRespondedBullshitException;
import ch.yth2021.charjar.discord.bot.Application;
import ch.yth2021.charjar.discord.module.BasicEventModule;
import ch.yth2021.charjar.discord.module.FileReadHelper;
import ch.yth2021.charjar.discord.processor.model.EventListener;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


public class SwearModule implements BasicEventModule {
    private static final String FILENAME = "swearwords.txt";
    private final Logger logger = LoggerFactory.getLogger(Application.class);
    private static final String REGEXP_SPECIAL_CHAR = "[^a-z ]";
    private static final String REGEXP_STAR_FOR_REPLACEMENT = "*";
    private static final String TEMPLATE_REGEXP_MATCH_ON_CHAR_OR_STAR = "[%s\\*]";
    private static final String TEMPLATE_REGEXP_MATCH = ".*(%s).*";
    private static List<String> RegexpSwearWords = new ArrayList<>();

    private static List<EventListener> toNotify = new ArrayList<>();

    public static void register(EventListener el){
        toNotify.add(el);
    }

    private static void notifyListeners(MessageReceivedEvent event){
        for (EventListener eventListener : toNotify) {
            eventListener.onEvent(event);
        }
    }

    public int countSwearwords(String message) {
        int swearWordCounter = 0;

        String[] words = reshapeMessageData(message).split(" ");

        List<String> swearWordsRegexp = getSwearWordsRegexed();
        for (String word : words) {
            for (String swearWordRegexp : swearWordsRegexp) {
                if (word.matches(swearWordRegexp)) {
                    swearWordCounter++;
                    System.out.println(word);
                    break;//No Double matches on same word
                }
            }
        }
        return swearWordCounter;
    }

    private static String reshapeMessageData(String message) {
        return message.toLowerCase().replaceAll(REGEXP_SPECIAL_CHAR, REGEXP_STAR_FOR_REPLACEMENT);
    }

    public static List<String> getSwearWordsRegexed() {
        if (RegexpSwearWords == null || RegexpSwearWords.isEmpty()) {
            RegexpSwearWords = RegexpSwearWords == null ? new ArrayList<>() : RegexpSwearWords;//Init if null
            String[] swearWordsPlain = FileReadHelper.getLinesAsArray(FILENAME);
            for (String swearWordPlain : swearWordsPlain) {
                RegexpSwearWords.add(plainSwearWordToRegexpSwearWord(swearWordPlain));
            }
        }
        return RegexpSwearWords;
    }

    private static String plainSwearWordToRegexpSwearWord(String inp) {
        StringBuilder sb = new StringBuilder();
        for (char c : inp.toLowerCase().toCharArray()) {
            sb.append(String.format(TEMPLATE_REGEXP_MATCH_ON_CHAR_OR_STAR, c));
        }
        return String.format(TEMPLATE_REGEXP_MATCH, sb.toString());
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
            event.getMessage().addReaction("U+1F621").queue();
        }

        notifyListeners(event);
    }
}
