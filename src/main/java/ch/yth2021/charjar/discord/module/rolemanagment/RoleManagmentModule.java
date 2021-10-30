package ch.yth2021.charjar.discord.module.rolemanagment;

import ch.yth2021.charjar.API.User;
import ch.yth2021.charjar.API.model.APIRespondedBullshitException;
import ch.yth2021.charjar.discord.bot.Application;
import ch.yth2021.charjar.discord.module.BasicEventModule;
import net.dv8tion.jda.api.entities.Guild;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.requests.restaction.RoleAction;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.awt.*;
import java.io.IOException;
import java.util.List;
import java.util.Objects;


public class RoleManagmentModule implements BasicEventModule {
    private final Logger logger = LoggerFactory.getLogger(Application.class);
    public static List<CustomRole> roles = List.of(
            CustomRole.builder().name("Asshole").color(new Color(125, 91, 37)).minBalance(0).maxBalance(5).build(),
            CustomRole.builder().name("On thin ice").color(Color.BLUE).minBalance(6).maxBalance(10).build(),
            CustomRole.builder().name("Very Neutral").color(Color.YELLOW).minBalance(11).maxBalance(20).build(),
            CustomRole.builder().name("Nice").color(Color.PINK).minBalance(21).maxBalance(50).build(),
            CustomRole.builder().name("The kindest person on Earth").color(new Color(159, 33, 255)).minBalance(51).maxBalance(500).build());

    @Override
    public void onMessageReceived(MessageReceivedEvent event) {
        var member = event.getMember();
        var user = new User(member.getUser().getId());
        try {
            // get required role
            var points = user.getPoints();
            var requiredRole = roles.stream().filter(role -> points >= role.getMinBalance() && points <= role.getMaxBalance()).findFirst().get();
            member.getRoles().stream().filter(r ->
                            roles.stream().anyMatch(r2 -> Objects.equals(r.getName(), r2.getName()))
                                    && !Objects.equals(r.getName(), requiredRole.getName()))
                    .map(r -> event.getGuild().getRolesByName(r.getName(), true).get(0))
                    .forEach(r -> event.getGuild().removeRoleFromMember(member, r).queue());
            event.getGuild().addRoleToMember(member, event.getGuild().getRolesByName(requiredRole.getName(), true).get(0)).queue();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (APIRespondedBullshitException e) {
            e.printStackTrace();
        }
    }

    private static RoleAction createRole(Guild guild, String name, Color color) {
        return guild.createRole().setName(name)
                .setMentionable(true)
                .setColor(color);
    }
}
