package ch.yth2021.charjar.discord.module.quiz;

import net.dv8tion.jda.api.entities.Message;

import java.util.List;

public class WatchedQuestion {
    public Message Question;
    public List<Message> wrongAnswers;
    public Message correctAnswer;
}
