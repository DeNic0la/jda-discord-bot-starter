package ch.yth2021.charjar.discord.module.randomevents;

import ch.yth2021.charjar.discord.bot.Application;
import net.dv8tion.jda.api.entities.TextChannel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Date;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

public class RandomEventScheduler {
    private static final Logger logger = LoggerFactory.getLogger(Application.class);
    private static final Timer timer = new Timer();
    private final TextChannel textChannel;

    public RandomEventScheduler(String channelId) {
        this.textChannel = Application.getJDA().getTextChannelById(channelId);
    }

    public void startScheduler() {
        new RandomTask().run();

    }

    class RandomTask extends TimerTask {
        @Override
        public void run() {
            logger.info("hewwo world");
            textChannel.sendMessage("scheduled message").queue();

            System.out.println(new Date());
            int delay = (5 + new Random().nextInt(5)) * 1000;
            timer.schedule(new RandomTask(), delay);
        }
    }

}
