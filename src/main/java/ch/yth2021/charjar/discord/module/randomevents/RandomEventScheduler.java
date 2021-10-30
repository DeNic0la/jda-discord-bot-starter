package ch.yth2021.charjar.discord.module.randomevents;

import ch.yth2021.charjar.discord.bot.Application;
import net.dv8tion.jda.api.entities.TextChannel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

public class RandomEventScheduler {
    private static final Logger logger = LoggerFactory.getLogger(Application.class);
    private static final Timer timer = new Timer();
    private final TextChannel textChannel;
    public static final List<RandomEvent> possibleRandomEvents = List.of(
            new RandomEvent("water yo plants", "\uD83D\uDCA7")
    );
    private static Integer currentTaskIndex;

    public RandomEventScheduler(String channelId) {
        this.textChannel = Application.getJDA().getTextChannelById(channelId);
    }

    public void startScheduler() {
        new RandomTask().run();

    }

    public static Integer getCurrentTaskIndex() {
        return currentTaskIndex;
    }

    public static class RandomEvent {
        private final String message;
        private final String reactionEmoji;

        public RandomEvent(String message, String reactionEmoji) {
            this.message = message;
            this.reactionEmoji = reactionEmoji;
        }

        public String getMessage() {
            return message;
        }

        public String getReactionEmoji() {
            return reactionEmoji;
        }
    }

    class RandomTask extends TimerTask {
        @Override
        public void run() {
            // TODO: increase bound to make random events less common
            int delay = (5 + new Random().nextInt(20)) * 1000;
            currentTaskIndex = (delay % possibleRandomEvents.size());
            timer.schedule(new RandomTask(), delay);

            textChannel.sendMessage(String.format("%s %s", possibleRandomEvents.get(currentTaskIndex).getMessage(), possibleRandomEvents.get(currentTaskIndex).getReactionEmoji())).queue();
        }
    }

}
