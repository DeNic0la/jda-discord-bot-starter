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
    private Timer timer;
    private final TextChannel textChannel;
    public static final List<RandomEvent> possibleRandomEvents = List.of(
            new RandomEvent("water yo plants", "\uD83D\uDCA7")
    );
    private Integer currentTaskIndex;

    public RandomEventScheduler(String channelId) {
        this.textChannel = Application.getJDA().getTextChannelById(channelId);
    }

    public void stopScheduler() {
        timer.cancel();
        timer.purge();
        timer = null;
    }

    public void startScheduler() {
        if (timer == null) {
            timer = new Timer();
            new RandomTask().run();
        }
    }

    public Integer getCurrentTaskIndex() {
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
            int delay = (5 + new Random().nextInt(100)) * 1000;
            currentTaskIndex = new Random().nextInt(possibleRandomEvents.size());
            timer.schedule(new RandomTask(), delay);

            textChannel.sendMessage(String.format("%s %s", possibleRandomEvents.get(currentTaskIndex).getMessage(),
                            possibleRandomEvents.get(currentTaskIndex).getReactionEmoji()))
                    .queue();
        }
    }

}
