package Commands;

import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.entities.Message;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

import java.util.List;

public class ClearMessagesCommand extends ListenerAdapter {
    public void onMessageReceived(MessageReceivedEvent event) {
        String[] args = event.getMessage().getContentRaw().split("\\s+");

        if(args[0].equalsIgnoreCase("!clear")){
            if(args.length < 2){
                // To many messages
                EmbedBuilder usage = new EmbedBuilder();
                usage.setColor(0xffb200);
                usage.setTitle("Specify amount to delete.");
                usage.setDescription("Usage: '!clear [# of messages]'");
                event.getChannel().sendMessage(usage.build()).queue();
            }
            else {
                try{
                    List<Message> messages = event.getChannel().getHistory().retrievePast(Integer.parseInt(args[1])).complete();
                    for(Message message : messages) {
                        event.getChannel().deleteMessageById(message.getId()).queue();
                    }

                    // Success
                    EmbedBuilder success = new EmbedBuilder();
                    success.setColor(0x22ff2a);
                    success.setTitle("Successfully deleted " + messages.size() + " messages!");
                    event.getChannel().sendMessage(success.build()).queue();
                }
                catch (IllegalArgumentException exception){
                    if(exception.toString().startsWith("java.lang.IllegalArgumentException: Message retrieval")){
                        // To many messages
                        EmbedBuilder error = new EmbedBuilder();
                        error.setColor(0xff3923);
                        error.setTitle("To many messages selected!");
                        error.setDescription("Between 1-100 messages can be deleted at once.");
                        event.getChannel().sendMessage(error.build()).queue();
                    }
                    else {
                        // Messages too old
                        EmbedBuilder error = new EmbedBuilder();
                        error.setColor(0xff3923);
                        error.setTitle("Selected messages are older than 2 weeks.");
                        error.setDescription("Messages older than 2 weeks cannot be deleted");
                        event.getChannel().sendMessage(error.build()).queue();
                    }
                }

            }
        }
    }
}
