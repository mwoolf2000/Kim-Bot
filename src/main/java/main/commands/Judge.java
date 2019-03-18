package main.commands;
import net.dv8tion.jda.core.entities.Message;

import java.io.File;

public class Judge implements Command {
    //properties
    private File[] judgeArray;
    private File rareImage;

    //constructor
    public Judge (File[] judgeArray, File rareImage) {
        this.judgeArray = judgeArray;
        this.rareImage = rareImage;
    }

    @Override
    public String getDescription() {
        return "Mr.Game & Watch judge meme command; ROLL NINE!";
    }

    @Override
    public String getUsage() {
        return "`!judge`";
    }

    @Override
    public void runCommand(Message event, String messageContent) {
    String nineMessage = "";
    //generate a random number between 1 - 9 to pick a roll image
    int judgeNum = (int) (Math.random() * (judgeArray.length));
    System.out.println("\tJudge roll 0 - 8: " + judgeNum);

    //generate another random number for the one in 100 chance of the fat judge 69
    int judge69Num = (int) (Math.random() * (100));
    System.out.println("\tRare judge roll 0-99: " + judge69Num);
    //test for the 1 in 100 chance of a fat judge 69.
    if (judge69Num == 69) {
        event.getChannel().sendMessage("***@everyone 「ＪＵＤＧＥ」***").addFile(rareImage).queue();
        return;
    }

    //if ROLL NINE cast a nigga into the shadow realm.
    if (judgeNum == 8) {
        //event.getChannel().sendMessage("***OH SHIT A NIGGA JUST GOT CAST INTO THE SHADOW REALM!!!***").queue();
        nineMessage += "***OH SHIT A NIGGA JUST GOT CAST INTO THE SHADOW REALM!!!***";
    }
    //send the corresponding judge
    event.getChannel().sendMessage(nineMessage +" ").addFile(judgeArray[judgeNum]).queue();

    }
}
