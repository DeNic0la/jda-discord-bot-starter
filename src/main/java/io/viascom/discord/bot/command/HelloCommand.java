package io.viascom.discord.bot.command;

import net.dv8tion.jda.api.events.interaction.SlashCommandEvent;

public class HelloCommand extends BotCommand {

    public HelloCommand() {
        super("hello", "Say hello");
    }

    @Override
    public void initCommandOptions() {
    }

    @Override
    public void execute(SlashCommandEvent event) {
        System.out.println("Command /hello got used");
        //Replay with 'Hello'
        event.reply("Hello").queue();
    }
}
