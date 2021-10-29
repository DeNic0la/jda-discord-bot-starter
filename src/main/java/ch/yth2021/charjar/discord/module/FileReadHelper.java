package ch.yth2021.charjar.discord.module;

import ch.yth2021.charjar.discord.bot.Application;
import org.apache.commons.io.IOUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;

public class FileReadHelper {
    private static final HashMap<String, String[]> cache = new HashMap<>();
    private static final Logger logger = LoggerFactory.getLogger(Application.class);

    public static String[] getLinesAsArray(String filename) {
        return cache.computeIfAbsent(filename, FileReadHelper::getLinesFromFile);
    }

    private static String[] getLinesFromFile(String filename) {
        InputStream filePath = Application.class.getClassLoader().getResourceAsStream(filename);
        try {
            String message = IOUtils.toString(filePath, StandardCharsets.UTF_8);
            return message.split("\n");
        } catch (Exception e) {
            logger.debug("There was an Error reading " + filename + " using empty array.", e);
            return new String[0];
        }
    }
}
