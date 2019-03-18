package main.commands;
import net.dv8tion.jda.core.entities.Message;

public class VideoChannel implements Command {
    @Override
    public String getDescription() {
        return "Makes a video channel link for the voice channel the user who sent the command is in.";
    }

    @Override
    public String getUsage() {
        return "`!videochannel`";
    }

    //method for sending a video channel link for the voice channel a user is in.
    @Override
    public void runCommand(Message event, String messageContent) {
        //find out what voice channel the user is in, save it in a string.
        String voiceChannelID = event.getMember().getVoiceState().getChannel().getId();
        //find out what guild the user is in, save it in a string
        String guildID = event.getGuild().getId();
        //make the link into a string.
        String videoChannelLink = "http://discordapp.com/channels/" + guildID + "/" + voiceChannelID;
        //send that string.
        event.getChannel().sendMessage(videoChannelLink).queue();
    }
}
