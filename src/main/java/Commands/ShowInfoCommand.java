package Commands;

import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

public class ShowInfoCommand extends ListenerAdapter {
    public void onMessageReceived(MessageReceivedEvent event) {
        String message = event.getMessage().getContentRaw();
        System.out.println(message);
        if(message.equalsIgnoreCase("!info")){
            EmbedBuilder info = new EmbedBuilder();
            info.setTitle(" INFORMATION: ");
            info.setDescription("This TFTournamentBot helps organize TFT tournaments:\n");
            info.addField("Available Commands: ", "", false);
            info.addField("Open tournament: ", "!open [#]", true);
            info.addField("Add player", "!add [IGN]", true);
            info.addField("Record a game", "!result [IGN]", true);
            info.addField("Show bracket: ", "!bracket", true);
            info.addField("Clear Messages: ", "!clear [#]", true);
            info.addField("Close tournament: ", "!close", true);
            info.addBlankField(true);
            info.addBlankField(true);
            info.addBlankField(false);
            info.setFooter("Creator: Tuken");
            info.setColor(0x00bbff);

            event.getChannel().sendTyping().queue();
            event.getChannel().sendMessage(info.build()).queue();
            info.clear();
        }
    }
}
