package ch.yth2021.charjar.discord.bot;

import java.io.IOException;
import java.util.Properties;

public class ApplicationProperties {

    Properties properties = new Properties();
    Properties secretProperties = new Properties();

    ApplicationProperties() {
        try {
            properties.load(Application.class.getClassLoader().getResourceAsStream("config.properties"));
            secretProperties.load(Application.class.getClassLoader().getResourceAsStream("secret.properties"));
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    public String getDiscordToken() {
        return secretProperties.getProperty("discord-token");
    }

    public String getDiscordClientId() {
        return secretProperties.getProperty("discord-client-id");
    }

    public String getApiURL() {
        String property = secretProperties.getProperty("points-api");
        if (property.length() > 0) {
            property = properties.getProperty("points-api");
        }
        return property;
    }

}
