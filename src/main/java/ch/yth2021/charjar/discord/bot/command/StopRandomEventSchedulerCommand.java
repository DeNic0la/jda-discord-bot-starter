package ch.yth2021.charjar.discord.bot.command;

import ch.yth2021.charjar.discord.module.randomevents.RandomEventScheduler;
import net.dv8tion.jda.api.events.interaction.SlashCommandEvent;

public class StopRandomEventSchedulerCommand extends BotCommand {
    public StopRandomEventSchedulerCommand() {
        super("stoprandomevents", "Stop the scheduler of random events");
    }

    @Override
    public void initCommandOptions() {
    }

    @Override
    public void execute(SlashCommandEvent event) {
        var channelId = event.getChannel().getId();

        RandomEventScheduler.stopScheduler();
        event.reply("random events scheduler has been stopped").queue();
    }
}
