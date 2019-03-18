package main.commands;
import net.dv8tion.jda.core.entities.Message;
import java.io.File;

public class FileResponse implements Command {
    //properties
    private File file;
    private String description;
    private String usage;

    //constructor
    public FileResponse (File file, String description, String usage) {
        this.file = file;

        //the description and usage both use the constructor in this class, because, since it has many main.commands, all that send different files, it needs many descriptions and usages
        this.description = description;
        this.usage = usage;
    }


    @Override
    public String getDescription() {
        return description;
    }

    @Override
    public String getUsage() {
        return usage;
    }

    @Override
    public void runCommand(Message event, String messageContent) {
        event.getChannel().sendMessage(" ").addFile(file).queue();
    }
}
