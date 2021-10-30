package ch.yth2021.charjar.discord.module.quiz;

import ch.yth2021.charjar.API.Quiz;
import ch.yth2021.charjar.API.model.APIRespondedBullshitException;
import ch.yth2021.charjar.discord.module.BasicEventModule;
import net.dv8tion.jda.api.entities.TextChannel;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class QuizModule implements BasicEventModule {
    private static final String CHAR_A = "U+FE0F";
    private static final String CHAR_B = "U+FE0F";
    private static final String CHAR_C = "U+FE0F";
    private static final String CHAR_D = "U+FE0F";

    private static final List<WatchedQuestion> watchedQuestions = new ArrayList<>();

    @Override
    public void onMessageReceived(MessageReceivedEvent event) {
        String message = event.getMessage().getContentDisplay();
        if (!message.toLowerCase().equals("getquiz")) {
            return;
        }

        try {
            Quiz q = new Quiz();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (APIRespondedBullshitException e) {
            e.printStackTrace();
        }

        WatchedQuestion wq = new WatchedQuestion();
        watchedQuestions.add(wq);
        TextChannel textChannel = event.getMessage().getTextChannel();
        textChannel.sendMessage("Question").queue(msg -> {
            wq.Question = msg;
            msg.reply("answer1").queue(aw -> {
                aw.addReaction("U+1F970").queue();
                wq.wrongAnswers.add(aw);
            });
            msg.reply("answer2").queue(aw -> {
                aw.addReaction("U+1F970").queue();
                wq.wrongAnswers.add(aw);
            });
            msg.reply("answer3").queue(aw -> {
                aw.addReaction("U+1F970").queue();
                wq.wrongAnswers.add(aw);
            });
        });


    }
}
