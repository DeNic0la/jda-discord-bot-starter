package ch.yth2021.charjar.discord.bot.listener;

import ch.yth2021.charjar.API.User;
import ch.yth2021.charjar.API.model.APIRespondedBullshitException;
import ch.yth2021.charjar.discord.bot.Application;
import ch.yth2021.charjar.discord.module.quiz.QuizModule;
import ch.yth2021.charjar.discord.module.randomevents.RandomEventScheduler;
import net.dv8tion.jda.api.entities.Message;
import net.dv8tion.jda.api.events.message.guild.react.GuildMessageReactionAddEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

import java.io.IOException;
import java.time.Duration;
import java.time.OffsetDateTime;

public class ReactionListener extends ListenerAdapter {
    private final static Long EXPIRES_AFTER = 10L;
    private final static int REWARD = 5;

    @Override
    public void onGuildMessageReactionAdd(GuildMessageReactionAddEvent event) {

        Application.getCommandExecutor().submit(() -> {
            if (!Application.properties.getDiscordClientId().equals(event.getUserId()) && event.getMessageId().equals(QuizModule.currentQuestion.getId())) {
                QuizModule.userReactedWithEmoteToCurrentQuestion(event.getUserId(), event.getReactionEmote().getName(), event.getChannel());
            }

            event.getChannel().retrieveMessageById(event.getMessageId()).queue(m -> {
                if (isSentByBot(m)) {
                    if (isRandomEventAndUserReactedProperly(event, m) && !messageExpired(m, EXPIRES_AFTER)) {
                        giveUserPoints(event.getUserId());
                        event.getChannel().sendMessage("Good Job " + event.getUser().getName() + "! **" + REWARD + "** points were added to your wallet!").queue();
                    } else {
                        event.getReaction().clearReactions().queue();
                    }
                }
            });
            event.getChannel().retrieveMessageById(event.getMessageId()).queue(m -> {
                if (isSentByBot(m)) {
                    if (isRandomEventAndUserReactedProperly(event, m)) {
                        giveUserPoints(event.getUserId());
                    }
                }
            });
        });
    }

    private boolean messageExpired(Message m, Long expiredAfterSeconds) {
        var difference = Duration.between(m.getTimeCreated(), OffsetDateTime.now());
        return difference.getSeconds() > expiredAfterSeconds;
    }

    private void giveUserPoints(String userId) {
        User user = new User(userId);
        try {
            user.modPoints(REWARD);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (APIRespondedBullshitException e) {
            e.printStackTrace();
        }
    }

    private boolean isRandomEventAndUserReactedProperly(GuildMessageReactionAddEvent e, Message m) {
        var currentRandomEvent = RandomEventScheduler.possibleRandomEvents.get(Application.randomEventScheduler.getCurrentTaskIndex());
        return m.getContentStripped().contains(currentRandomEvent.getMessage()) && e.getReactionEmote().getName().equals(currentRandomEvent.getReactionEmoji());
    }

    private boolean isSentByBot(Message m) {
        return m.getAuthor().getId().equals(Application.properties.getDiscordClientId());
    }
}
