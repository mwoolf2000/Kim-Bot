package main.commands;
import net.dv8tion.jda.core.entities.*;

public class HangMan implements Command {
    //array to store all the ascii art for hangman gallows.
    private String[] hangmanAsciiArray = {
            //first hangman ascii
            "```\n" +
            "  +---+\n" +
            "  |   |\n" +
            "      |\n" +
            "      |\n" +
            "      |\n" +
            "      |\n" +
            "=========```",

            //second hangman ascii
            "```\n" +
            "  +---+\n" +
            "  |   |\n" +
            "  O   |\n" +
            "      |\n" +
            "      |\n" +
            "      |\n" +
            "=========```",

            //third hangman ascii
            "```\n" +
            "  +---+\n" +
            "  |   |\n" +
            "  O   |\n" +
            "  |   |\n" +
            "      |\n" +
            "      |\n" +
            "=========```",

            //fourth hangman ascii
            " '''\n" +
            "  +---+\n" +
            "  |   |\n" +
            "  O   |\n" +
            " /|   |\n" +
            "      |\n" +
            "      |\n" +
            "========='''",

            //fifth hangman ascii
            "```\n" +
            "  +---+\n" +
            "  |   |\n" +
            "  O   |\n" +
            " /|\\  |\n" +
            "      |\n" +
            "      |\n" +
            "=========```",

            //sixth hangman ascii
            "```\n" +
            "  +---+\n" +
            "  |   |\n" +
            "  O   |\n" +
            " /|\\  |\n" +
            " / \\  |\n" +
            "      |\n" +
            "=========```\n" +
            "This nigga dead :pensive:"


    };
    @Override
    public String getDescription() {
        return "play a game of hangman";
    }

    @Override
    public String getUsage() {
        return "!hangman";
    }

    @Override
    public void runCommand(Message event, String messageContent) {
    //store the specif sub command passed into the hangman command
    String subCommand = messageContent.split(" ")[0];
    //store the arugment passed into said sub command
    String commandParam = messageContent.substring(subCommand.length()).trim();

    //tel the user to check there DM
    event.getChannel().sendMessage("check ur DM").queue();
    //ask for a phrase in there DM
    event.getAuthor().openPrivateChannel().complete().sendMessage("Give me a phrase (Note there are always 5 wrong guesses, regardless of length.)\n Format: `!hangman` `phrase` `YOUR PHRASE HERE`").queue();

        //if the next message is from there DM
        if (event.getChannel() == event.getAuthor().openPrivateChannel()) {
//        String phrase = event

        }

    }
}
