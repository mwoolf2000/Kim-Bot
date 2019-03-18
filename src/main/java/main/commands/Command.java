package main.commands;
import net.dv8tion.jda.core.entities.Message;

//an interface for all main.commands to implement, just a to do list of sorts, things that all main.commands must have.
public interface Command {
    //describe the command.
    String getDescription();

    //describe how to use the command
    String getUsage();

    //the stuff the command acutally does
    void runCommand(Message event, String messageContent);
}
