package ch.yth2021.charjar.discord.bot.command;

import ch.yth2021.charjar.discord.bot.Application;
import ch.yth2021.charjar.discord.module.quiz.QuizModule;
import net.dv8tion.jda.api.events.interaction.SlashCommandEvent;

public class QuizCommand extends BotCommand {

    public QuizCommand() {
        super("quiz", "Get a Challenging quiz");
    }

    @Override
    public void execute(SlashCommandEvent event) {
        Application.getCommandExecutor().execute(() ->
                {
                    event.reply("Here is your Quiz").queue();
                    QuizModule.generateQuiz(event.getChannel());

                }
        );
    }
}
