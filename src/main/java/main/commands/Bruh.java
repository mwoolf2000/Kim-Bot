package main.commands;

import net.dv8tion.jda.core.entities.Message;
import java.io.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Scanner;

public class Bruh implements Command {
    private static final File bruhFile = new File("./assets/bruhMoments.txt");
    @Override
    public String getDescription() {
        return "Records, and also reads bruh moments.";
    }

    @Override
    public String getUsage() {
        return "`!bruh`: `new`, `read`, `sendfile`, `info`";
    }

    @Override
    public void runCommand(Message event, String messageContent) {
        //get the specific sub command minus its parameters.
        String subCommand = messageContent.split(" ")[0];
        String commandParam = messageContent.substring(subCommand.length()).trim();


        switch (subCommand) {
            case "new": bruhNew(event, commandParam);
                break;
            case "read": bruhRead(event, commandParam);
                break;
            case "sendfile": bruhSendFile(event);
                break;
            case "info": bruhInfo(event);
                break;
        }


    }
    //a number that counts how many bruh moments there are.
    private int bruhCount () throws IOException{
        //counts how many bruh moments there are
        BufferedReader reader = new BufferedReader(new FileReader(bruhFile));
        int bruhCount = 0;
        while (reader.readLine() != null){
            bruhCount++;
        }
        reader.close();
        return bruhCount;
    }
    //method for recording new bruh moments.
    private String[] bruhArray() throws IOException {
        ArrayList <String> bruhArray = new ArrayList<String>();
        BufferedReader reader = new BufferedReader(new FileReader(bruhFile));
        while (reader.readLine() != null) {
            bruhArray.add(reader.readLine());
        }
        return bruhArray.toArray(new String[]{});
    }
    private void bruhNew (Message event, String bruhString) {
        try {

            //check to make sure the bruh moment is unique
            String [] linesArray  = bruhArray();
            for (int i = 0; i < linesArray.length; i++) {
                if (bruhString.equals(linesArray[i])) {
                    event.getChannel().sendMessage("⚠️**Sorry! But that Bruh Moment already exists, and bruh moments must be unique!**⚠").queue();
                    return;
                }
            }

            //take the string and replace new lines with a null char.
            bruhString = bruhString.replaceAll("\n", "�");
            Scanner bruhScanner = new Scanner(bruhFile);
            FileWriter bruhWriter = new FileWriter(bruhFile, true);
            bruhWriter.write(bruhString + "\n");
            bruhWriter.flush();
            event.getChannel().sendMessage("\uD83D\uDCCB **Bruh Moment #" + bruhCount() + " Recorded!** \uD83D\uDCCB").queue();
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }
    //method to read a selected bruh moment
    private void bruhRead (Message event, String bruhString) {
        int bruhIndex = Integer.parseInt(bruhString);
        if (bruhIndex <= 0) {
            return;
        }
        try {
            Scanner bruhScanner = new Scanner(bruhFile);
            int i = 1;
            for (; i < bruhIndex && bruhScanner.hasNext(); i++) {
                bruhScanner.nextLine();
            }

            if (bruhScanner.hasNext()) {
                //take the string and replace null chars with new lines.
                String bruhNewLine = bruhScanner.nextLine().replaceAll("�","\n");
                event.getChannel().sendMessage(("Bruh Moment #" + i + ": " + bruhNewLine)).queue();
            }
            else if (bruhIndex > bruhCount()) {
                event.getChannel().sendMessage("⚠️**There are only " + bruhCount() + " bruh moments!**⚠️").queue();
            }
        }
        catch (IOException e) {
            e.printStackTrace();
        }

    }

    //method for sending the bruh moments file.
    private void bruhSendFile (Message event) {
        //sends the file of all bruh moments.
        LocalDate bruhTime = LocalDate.now();
        event.getChannel().sendMessage("Date of file: " + bruhTime.toString()).addFile(bruhFile).queue();
    }
    private void bruhInfo (Message event) {
        try {
            double bruhFileSize = (double)bruhFile.length()/100.0;
            int foo = (int)bruhFileSize;
            System.out.println(foo);
            bruhFileSize = foo / 10.0;
            event.getChannel().sendMessage("```md\n#Bruh info\nNumber of bruh moments: " + bruhCount() + "\nSize of bruh file: " + bruhFileSize + " Kilobytes\n```").queue();
        }
        catch (IOException e) {
            e.printStackTrace();
        }

    }
}
