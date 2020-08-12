import Commands.ShowInfoCommand;
import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.entities.Activity;
import net.dv8tion.jda.api.utils.cache.CacheFlag;

import javax.print.attribute.standard.Compression;
import javax.security.auth.login.LoginException;
import java.util.Arrays;

public class Main {
    static DiscordBot discordBot;

    public static void main(String[] args) {
        setUpBot();

    }

    private static void setUpBot() {
        // Tworzenie bota na Discordzie
        try {
            discordBot = new DiscordBot();
        } catch (Exception exception) {
            System.out.println(Arrays.toString(exception.getStackTrace()));
        }
    }
}
