package main.commands;
import net.dv8tion.jda.core.entities.Message;

public class Event implements Command {
    //properties
    private String response;

    //constructor
    public Event (String response) {
        this.response = response;
    }
    @Override
    public String getDescription() {
        return null;
    }

    @Override
    public String getUsage() {
        return null;
    }

    @Override
    public void runCommand (Message event, String messageContents) {
        event.getChannel().sendMessage(response).queue();
    }
}
