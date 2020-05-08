package fr.deltadesnoc.utils;

import fr.deltadesnoc.Main;

import java.io.File;
import java.io.IOException;

public class FileManager {

    private static final File DIR = new File(System.getenv("APPDATA"), "pokecraft");
    private Config config;
    public FileManager(){

        if(!DIR.exists())
            DIR.mkdirs();
        try {
            config = new Config("config.json");
        } catch (IOException e) {
            Main.logger.warn(e.getMessage());
        }
    }
    public static File getDIR() {
        return DIR;
    }
    public Config getConfig() {
        return config;
    }
}
