package main;
import main.commands.*;
import net.dv8tion.jda.core.AccountType;
import net.dv8tion.jda.core.JDA;
import net.dv8tion.jda.core.JDABuilder;
import net.dv8tion.jda.core.events.message.MessageReceivedEvent;
import net.dv8tion.jda.core.hooks.ListenerAdapter;
import javax.security.auth.login.LoginException;
import java.io.*;
import java.util.*;

public class Main extends ListenerAdapter {
    //makin' a jda, whatever the fuck that means.
    private static JDA jda;

    //create a map for all of the commands to be mapped to.
    public static Map<String, Command> commandMap = new HashMap<>();

    //create a 2d array for the auto responses, first String is the message it will look for, second is the one it will send as a response.
    private static final String responseArray[][] = new String[][]{
            {"yuh", "aye"},
            {"aye", "yuh"},
            {"yee" , "haw"},
            {"\uD83E\uDD20 ", "Howdy Partner \uD83E\uDD20"},
            {"```java", "🙏⛪ Praise be unto the java lord. ⛪🙏"},
            {"```css\n>", "```css\n>Who are you quoting?\n```"},
            {"cool", "beans"},
            {"beans", "I;m Thinking About Thos Beans"}
    };

    //files to be passed into the File Response class.
    private static final File contra = new File("./assets/contra.webm");
    private static final File hbomber = new File("./assets/hbomberguy.mp4");
    private static final File hhgreg = new File("./assets/hhgreg.mp4");
    private static final File breadBook = new File("./assets/breadBook.pdf");

    //files for judge command
    private static final File[] judgeFileArray = {
            new File("./assets/roll/1roll.png"),
            new File("./assets/roll/2roll.png"),
            new File("./assets/roll/3roll.png"),
            new File("./assets/roll/4roll.png"),
            new File("./assets/roll/5roll.png"),
            new File("./assets/roll/6roll.png"),
            new File("./assets/roll/7roll.png"),
            new File("./assets/roll/8roll.png"),
            new File("./assets/roll/9roll.png")
    };
    //rare mr game and watch file
    private static final File rareJudge = new File("./assets/roll/10roll.png");

    //main runner method.
    public static void main(String[] args) throws LoginException, IOException {
        //adds all of the commands to the commandMap.
        commandMap.put("flip", new Flip());
        commandMap.put("8ball", new EightBall(new String[]{
                "Yeah sure i guess lol.",
                "No, go fuck yourself, asshole.",
                "Maybe idk, what do i look like a fuckin' genie to you? you know what kid, fuck you.",
                "Eh, id give it a solid and firm, **probably.**",
                "Probably not, lmao.",
                "Ask again later.",
                "My sources say no.",
                "Concentrate and try again.",
                "I'm busy rn, go bother someone else about your dumb bullshit, cunt."}));
        commandMap.put("bruh", new Bruh());
        commandMap.put("videochannel", new VideoChannel());
        commandMap.put("roll", new Roll());
        //these next three use the FileResponse class, and that takes a command, description, and usage, all as a constructor.
        commandMap.put("contra", new FileResponse(contra, "Sends a video of best girl, contrapoints, saying \"FUCKING. NEOLIBERALISM.\"", "`!contra`"));
        commandMap.put("hbomber", new FileResponse(hbomber, "Sends a video of hbomberguy saying \"what? fuck you.\"", "`!hbomberguy`"));
        commandMap.put("hhgreg", new FileResponse(hhgreg, "HHGREG, PANASONIC BLU RAY 99 DOLLARS", "`!hhgregg`"));
        commandMap.put("breadbook", new FileResponse(breadBook,"READ THE FUCKING BREAD BOOK", "`!breadbook`"));
        commandMap.put("judge", new Judge(judgeFileArray, rareJudge));
        commandMap.put("help", new Help());
        commandMap.put("hangman", new HangMan());


        //read the token from a file in the git ignore.
        File tokenFile = new File ("./.token");
        Scanner tokenScanner = new Scanner(tokenFile);
        String token = tokenScanner.nextLine();
        //Logs into my bot account and tells it to add an event listener to the main class.
        JDABuilder builder = new JDABuilder(AccountType.BOT).setToken(token).addEventListener(new Main());
        jda = builder.build();
    }

    // Stuff the bot does when it sees a message
    @Override
    public void onMessageReceived(MessageReceivedEvent event) {
        //checks to make sure the user sending a command in isn't a bot to prevent a loop
        if (event.getAuthor().isBot()) {
            return;
        }
        //Generates a 1 in 100 thousand chance of the bot randomly calling you a libtard. "(Math.random() * ((max - min) + 1)) + min" <- this is here to copy later if i need it lole
        int rand = (int) (Math.random() * 100000);
        if (rand == 69) {
            event.getChannel().sendMessage("Shut up, libtard.").queue();
        }

        String prefix = "!";

        //store the message in a string variable so i don't go crazy writing event.getMessage().getContentRaw().toLowerCase() for the 800th time and cut a vein
        String contents = event.getMessage().getContentRaw();

        //if the message starts with the command prefix, do stuff.
        if (contents.startsWith(prefix)) {
            //remove the prefix from the message.
            String strippedPrefix = contents.substring(prefix.length());

            //take the first word of the command and store just that.
            String commandName = strippedPrefix.split(" ")[0];

            //store the parameters of the command
            String commandContent = strippedPrefix.substring(commandName.length()).trim();

            //if the message matches a key on the command map, then do stuff.
            if (commandMap.containsKey(commandName.toLowerCase())) {

                //pass in the command parameters into the class for said command.
                commandMap.get(commandName).runCommand(event.getMessage(), commandContent);
            } else if (!commandMap.containsKey(commandName.toLowerCase())) {
                event.getChannel().sendMessage("\uD83E\uDD14\uD83D\uDCCB**Sorry but I don't see that command in my list, try `!help` for help.**\uD83D\uDCCB\uD83E\uDD14").queue();
            }
        }
        //otherwise check if it's something that doesn't need a prefix, like the yuh aye thing
        else {
            //loop through the 2d array of automatic responses
            for (int i = 0; i < responseArray.length; i++) {

                //if a message matches the first string in one of the arrays.
                if (contents.toLowerCase().equals(responseArray[i][0])) {
                    //send the second string in that array, i.e, the response assiocted with that message.
                    event.getChannel().sendMessage(responseArray[i][1]).queue();
                    break;
                }
            }
            //just print out the message the bot sees
            System.out.println("Message from " + event.getAuthor().getName() + ": " + event.getMessage().getContentDisplay());
        }
    }
}

//shout out to party frank.