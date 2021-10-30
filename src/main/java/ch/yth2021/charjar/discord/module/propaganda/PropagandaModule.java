package ch.yth2021.charjar.discord.module.propaganda;

import ch.yth2021.charjar.API.User;
import ch.yth2021.charjar.discord.bot.Application;
import ch.yth2021.charjar.discord.module.BasicEventModule;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class PropagandaModule implements BasicEventModule {
    private static HashMap<String, String> props = new HashMap<>();
    private static final String REGEXP_HASHTAG = "#(\\S+)";
    private static Logger logger = LoggerFactory.getLogger(Application.class);

    @Override
    public void onMessageReceived(MessageReceivedEvent event) {
        HashMap<String, String> propagandaList = getPropagandaList();
        List<String> emotesToAdd = new ArrayList<>();
        List<String> hashtags = getHashtags(event.getMessage().getContentDisplay());
        for (String hashtag : hashtags) {
            String emote = propagandaList.getOrDefault(hashtag, "");
            if (!emote.isBlank()) {
                emotesToAdd.add(emote);
            }
        }
        if (emotesToAdd.isEmpty()) {
            return;
        }
        User u = new User(event.getAuthor().getId());
        try {
            u.modPoints(emotesToAdd.size());
        } catch (Exception e) {
            logger.debug("Could not modify user");
        }
        for (String emote : emotesToAdd) {
            event.getMessage().addReaction(emote).queue();
        }
    }

    public static List<String> getHashtags(String message) {
        List<String> hashtags = new ArrayList<>();
        Pattern pat = Pattern.compile(REGEXP_HASHTAG);
        Matcher matcher = pat.matcher(message);
        while (matcher.find()) {
            hashtags.add(matcher.group(1).toLowerCase());
        }
        return hashtags;
    }

    private static HashMap<String, String> getPropagandaList() {
        if (props.isEmpty()) {
            Set<Map.Entry<Object, Object>> entries = Application.properties.propagandaProperties.entrySet();
            for (Map.Entry<Object, Object> entry : entries) {
                props.put(entry.getKey().toString().toLowerCase(), entry.getValue().toString());
            }
        }
        return props;
    }
}
