package ch.yth2021.charjar.discord.module.quiz;

import ch.yth2021.charjar.API.Quiz;
import ch.yth2021.charjar.API.User;
import ch.yth2021.charjar.API.model.APIRespondedBullshitException;
import ch.yth2021.charjar.discord.module.BasicEventModule;
import net.dv8tion.jda.api.entities.Message;
import net.dv8tion.jda.api.entities.MessageChannel;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;

import java.io.IOException;
import java.util.Collections;
import java.util.List;

public class QuizModule implements BasicEventModule {

    private static String currentAnswer = "";

    private static Message currentQuestion;

    private static final String[] ALPHABET = {"A", "B", "C", "D", "E"};
    private static final String GOOD_EMOTE = "U+1F4AF";
    private static final String BAD_EMOTE = "U+1F4A9";

    public static void generateQuiz(MessageChannel channel) {
        Quiz q;
        try {
            q = new Quiz();
        } catch (IOException e) {
            e.printStackTrace();
            return;
        } catch (APIRespondedBullshitException e) {
            e.printStackTrace();
            return;
        }

        StringBuilder sb = new StringBuilder();
        sb.append(q.getQuestion()).append("\n");
        List<String> answers = q.getAnswers();
        answers.add(q.getCorrectAnswer());
        Collections.shuffle(answers);
        int correctAnswer = answers.indexOf(q.getCorrectAnswer());
        for (int j = 0; j < answers.size(); j++) {
            String s = answers.get(j);
            sb.append(ALPHABET[j]).append(") ").append(s).append("\n");
        }

        if (currentQuestion != null) {
            currentQuestion.delete().queue();
        }
        currentAnswer = ALPHABET[correctAnswer];
        channel.sendMessage(sb.toString()).queue(msg -> {
            currentQuestion = msg;
        });
    }
    @Override
    public void onMessageReceived(MessageReceivedEvent event) {
        String message = event.getMessage().getContentDisplay();
        if (!currentAnswer.isBlank() && message.length() == 1) {
            User u = new User(event.getAuthor().getId());
            int toMod = 0;
            if (message.toUpperCase().equals(currentAnswer)) {
                toMod = 5;
                event.getMessage().addReaction(GOOD_EMOTE).queue();
            } else {
                toMod = -1;
                event.getMessage().addReaction(BAD_EMOTE).queue();
            }
            try {
                u.modPoints(toMod);
            } catch (Exception e) {
                e.printStackTrace();
            }
            currentAnswer = "";
            if (currentQuestion != null) {
                currentQuestion.delete().queue();
            }
        }

    }
}
