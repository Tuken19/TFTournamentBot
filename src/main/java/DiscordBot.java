import Commands.*;
import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.entities.Activity;
import net.dv8tion.jda.api.utils.cache.CacheFlag;

import javax.security.auth.login.LoginException;

public class DiscordBot {
    private final JDABuilder builder;

    public DiscordBot() throws LoginException {
        builder = JDABuilder.createDefault(Constants.TOKEN);
        // Disable parts of the cache
        builder.disableCache(CacheFlag.MEMBER_OVERRIDES, CacheFlag.VOICE_STATE);
        // Enable the bulk delete event
        builder.setBulkDeleteSplittingEnabled(false);
        // Set activity (like "playing Something")
        builder.setActivity(Activity.playing("TFT"));
        builder.addEventListeners(new ShowInfoCommand());
        builder.addEventListeners(new OpenTournamentCommand());
        builder.addEventListeners(new AddPlayerCommand());
        builder.addEventListeners(new StartTournamentCommand());
        builder.addEventListeners(new ReceiveResultCommand());
        builder.addEventListeners(new ShowBracketCommand());
        builder.addEventListeners(new ClearMessagesCommand());
        builder.build();
    }

    // Methods
    public JDABuilder getBuilder(){
        return this.builder;
    }

    public void addPlayer(){
        this.builder.addEventListeners(new AddPlayerCommand());
    }

    public void startTournament(){
        this.builder.addEventListeners(new StartTournamentCommand());
    }

    public void showInfo(){
        this.builder.addEventListeners(new ShowInfoCommand());
    }

    public void receiveResult(){
        this.builder.addEventListeners(new ReceiveResultCommand());
    }

}
