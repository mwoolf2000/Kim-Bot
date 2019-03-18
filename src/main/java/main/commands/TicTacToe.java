package main.commands;
import net.dv8tion.jda.core.entities.Message;
import net.dv8tion.jda.core.entities.User;

public class TicTacToe implements Command {
    private char[][] ticTacToeBoard = {
            {' '},{' '},{' '},
            {' '},{' '},{' '},
            {' '},{' '},{' '},
    };


    @Override
    public String getDescription() {
        return "Play a game of tick tac toe with someone";
    }

    @Override
    public String getUsage() {
        return "`!tictactoe` `@opponent`";
    }

    @Override
    public void runCommand(Message event, String messageContent) {
        User challenger = event.getAuthor();

    }
}
