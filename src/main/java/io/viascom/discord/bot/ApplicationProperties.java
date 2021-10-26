package io.viascom.discord.bot;

import java.io.IOException;
import java.util.Properties;

public class ApplicationProperties {

    Properties properties = new Properties();

    ApplicationProperties() {
        try {
            properties.load(Application.class.getClassLoader().getResourceAsStream("config.properties"));
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    public String getDiscordToken() {
        return properties.getProperty("discord-token");
    }

    public String getDiscordClientId() {
        return properties.getProperty("discord-client-id");
    }

}
