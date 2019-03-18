package main.commands;
import net.dv8tion.jda.core.entities.Message;

public class EightBall implements Command {
    //properties
    private  String[] answers;

    //constructor
    public EightBall (String[] answers) {
    this.answers = answers;
    }

    //add description for the 8ball command.
    @Override
    public String getDescription() {
        return "Ask a yes or no question and get an answer, might be sassy tho.";
    }

    //add a usage example
    @Override
    public String getUsage() {
        return "`!8ball` `Question`";
    }

    //8ball command, gives a random snarky yes or no response to a question.
    @Override
    public void runCommand(Message event, String messageContent) {

        //checks to make sure there is a question.
        if (messageContent.equals("")) {
            event.getChannel().sendMessage("I need a question dumbass.").queue();
            return;
        }
        //if someone asks if "ed gay" and NOT if "ed not gay", then inform them, yes, ed, is indeed, gay.
        if (messageContent.equals("ed gay") || (messageContent.contains("ed gay") && !messageContent.contains("not"))) {
            event.getChannel().sendMessage(answers[0]).queue();
            return;
        }

        //RNG to pick a answer from the array above
        int eightBallNum = (int) (Math.random() * (answers.length));

        //send the message
        event.getChannel().sendMessage(answers[eightBallNum]).queue();
    }
}

