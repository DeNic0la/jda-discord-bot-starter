package ch.yth2021.charjar.discord.bot.command;

import ch.yth2021.charjar.discord.bot.Application;
import ch.yth2021.charjar.discord.module.randomevents.RandomEventScheduler;
import net.dv8tion.jda.api.events.interaction.SlashCommandEvent;

public class StartRandomEventSchedulerCommand extends BotCommand {
    public StartRandomEventSchedulerCommand() {
        super("startrandomevents", "Start the scheduler of random events");
    }

    @Override
    public void initCommandOptions() {
    }

    @Override
    public void execute(SlashCommandEvent event) {
        var channelId = event.getChannel().getId();
        if (Application.randomEventScheduler == null) {
            Application.randomEventScheduler = new RandomEventScheduler(channelId);
        }
        Application.randomEventScheduler.startScheduler();
        event.reply("random events scheduler is running").queue();
    }
}
