package ch.yth2021.charjar.discord.bot.command;

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
        var res = new RandomEventScheduler(channelId);
        res.startScheduler();
        event.reply("random events scheduler is running").queue();
    }
}
