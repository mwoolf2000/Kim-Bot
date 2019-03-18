package main.commands;
import net.dv8tion.jda.core.entities.Message;

public class Roll implements Command {



    @Override
    public String getDescription() {
        return "rolls a die, i.e., generates a random number from 1-6";
    }

    @Override
    public String getUsage() {
        return "`!roll`";
    }

    //method to simulate rolling a die, i.e., generates a random number from 1 - 6.
    @Override
    public void runCommand(Message event, String messageContent) {
        //generates a random number between 1 and 6.
        int diceNum = (int) (Math.random() * 6) + 1;
        //sends that number as the dice roll
        event.getChannel().sendMessage("\uD83C\uDFB2 Your Roll: **" + diceNum + "** \uD83C\uDFB2" ).queue();
    }
}
