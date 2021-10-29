package io.viascom.discord.bot.handler;

import io.viascom.discord.bot.Application;
import io.viascom.discord.bot.command.BotCommand;
import net.dv8tion.jda.api.interactions.commands.Command;
import net.dv8tion.jda.api.interactions.commands.build.CommandData;

public class CommandHandler {

    public void initCommands() {
        //Load all to Discord known commands
        Application.getJDA().getGuildById(Application.HACKATHON_SERVER_ID).retrieveCommands().queue(currentCommands -> {
            //Load command options
            var commands = Application.getCommands().values();
            commands.forEach(BotCommand::initCommandOptions);

            //Get all commands which are not registered but known to discord
            var commandsToRemove = currentCommands.stream().filter(command -> commands.stream().noneMatch(entry -> compareCommands(entry, command)));
            commandsToRemove.forEach(command -> {
                System.out.println("Removed unneeded command '/" + command.getName() + "'");
                Application.getJDA().getGuildById(Application.HACKATHON_SERVER_ID).deleteCommandById(command.getId()).queue();
            });

            //Get all commands which are registered but not known to discord
            var commandsToUpdateOrAdd = commands.stream().filter(command -> currentCommands.stream().noneMatch(entry -> compareCommands(command, entry)));
            commandsToUpdateOrAdd.forEach(command -> {
                printCommand(command);
                Application.getJDA().getGuildById(Application.HACKATHON_SERVER_ID).upsertCommand(command).queue();
            });
        });
    }

    private void printCommand(BotCommand command) {
        var commandText = "";
        commandText += "Add / Update commands\n"
                       + "\t-> init command '/" + command.getName() + "'";

        if (!command.getSubcommandGroups().isEmpty()) {
            commandText += "\n" + command.getSubcommandGroups().stream().reduce("", (subcommandGroupText, subcommandGroupData) -> "\t\t--> "
                                                                                                                                  + subcommandGroupData.getName()
                                                                                                                                  + "\n"
                                                                                                                                  + subcommandGroupData
                                                                                                                                      .getSubcommands()
                                                                                                                                      .stream()
                                                                                                                                      .reduce("", (s, subcommandData) ->
                                                                                                                                          "\t\t\t---> "
                                                                                                                                          + subcommandData.getName()
                                                                                                                                          + "\n", String::concat), String::concat);
        }

        if (!command.getSubcommands().isEmpty()) {
            commandText += "\n" + command.getSubcommands().stream().reduce("", (s, subcommandData) -> "\t\t---> " + subcommandData.getName() + "\n", String::concat);
        }

        System.out.println(commandText);
    }

    /**
     * Compare two command specifications.
     *
     * @param commandData
     * @param command
     * @return true if both specifications are the same
     */
    private boolean compareCommands(CommandData commandData, Command command) {
        var nameAndDescriptionCheck = commandData.getName().equals(command.getName()) && commandData.getDescription().equals(command.getDescription());
        var optionsCheck = commandData.getOptions().stream().map(optionData -> new Command.Option(optionData.toData())).allMatch(option -> command.getOptions().contains(option));

        var subCommandGroupsCheck = commandData.getSubcommandGroups().stream().map(subcommandGroupData -> new Command.SubcommandGroup(subcommandGroupData.toData())).allMatch(
            subcommandGroup -> command.getSubcommandGroups().contains(subcommandGroup));

        var subCommandCheck = commandData.getSubcommands().stream().map(subcommandData -> new Command.Subcommand(subcommandData.toData())).allMatch(
            subcommand -> command.getSubcommands().contains(subcommand));

        return nameAndDescriptionCheck && optionsCheck && subCommandGroupsCheck && subCommandCheck;
    }

}
