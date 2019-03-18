package main.commands;
import net.dv8tion.jda.core.entities.Message;

import java.io.File;

public class Flip implements Command {

    // Description of what the command does.
    @Override
    public String getDescription() {
        return "Flips a coin.";
    }

    // Description of how to use the command.
    @Override
    public String getUsage() {
        return "`!flip`";
    }

    @Override
    public void runCommand(Message event, String messageContent) {
            //generate a random number between 0 and 1.
            int coinNum = (int) (Math.random() * 2);
            //make a file for the coin flip gif that gets sent.
            File coinGif = new File("/home/kimjongskill/IdeaProjects/Kim-Bot/assets/coin-flip.gif");
            //if the number generated was 0, then say it was heads.
            if (coinNum == 0) {
                event.getChannel().sendMessage("**Heads!**").addFile(coinGif).queue();
            }
            //if the number generated was 1, then say it was tails
            else if (coinNum == 1) {
                event.getChannel().sendMessage("**Tails!**").addFile(coinGif).queue();
            }
    }
}
