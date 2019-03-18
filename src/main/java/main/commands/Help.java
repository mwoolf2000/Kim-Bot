package main.commands;
import main.Main;
import net.dv8tion.jda.core.entities.Message;

public class Help implements Command {

    @Override
    public String getDescription() {
        return "Prints this help message.";
    }

    @Override
    public String getUsage() {
        return "`!help`";
    }

    @Override
    public void runCommand(Message event, String messageContent) {
        //make a string builder, like a string but it can be changed around.
        StringBuilder helpBuilder = new StringBuilder("**\uD83D\uDCD6 Command Descriptions and Usage Examples. \uD83D\uDCD6**\n\n");
        //for-each loop that loops through each class in the command map.
        for (Command command: Main.commandMap.values()) {
            //check to make sure there is a usage and a description.
            if (command.getUsage() != null && command.getDescription() != null) {
                //append them to the string builder
                helpBuilder.append(command.getUsage() + "\n\t" + command.getDescription() + "\n\n");
            }
        }
        //send the string builder in a DM
        event.getAuthor().openPrivateChannel().complete().sendMessage(helpBuilder.toString()).queue();
    }
}
