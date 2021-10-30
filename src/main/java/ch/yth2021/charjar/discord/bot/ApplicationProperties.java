package ch.yth2021.charjar.discord.bot;

import com.google.common.base.Strings;

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
        return Strings.isNullOrEmpty(secretProperties.getProperty("points-api")) ? properties.getProperty("points-api") : secretProperties.getProperty("points-api");
    }

    public String getQuizURL() {
        return Strings.isNullOrEmpty(secretProperties.getProperty("quiz-api")) ? properties.getProperty("quiz-api") : secretProperties.getProperty("quiz-api");
    }

}
