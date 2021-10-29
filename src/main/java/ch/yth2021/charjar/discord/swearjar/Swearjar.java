package ch.yth2021.charjar.discord.swearjar;

import ch.yth2021.charjar.API.User;
import ch.yth2021.charjar.discord.bot.Application;
import org.apache.commons.io.IOUtils;

import java.io.InputStream;

public class Swearjar {
    private static InputStream filePath = Application.class.getClassLoader().getResourceAsStream("swearwords.txt");

    public static void startService(String message) {
        int swearwordCounter = 0;
        for (String swearword : getSwearwords()) {
            if (message.contains(swearword)) {
                swearwordCounter++;
            }
        }
        new User(Application.getClientId()).modPoints(-swearwordCounter);
    }

    private static String[] getSwearwords() {
        String[] swearwords = new String[0];
        String message;
        try {
            message = IOUtils.toString(filePath, "UTF-8");
            swearwords = message.split("\n");
        } catch (Exception e) {
        }

        return swearwords;
    }
    
}
