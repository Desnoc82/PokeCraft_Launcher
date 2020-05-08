package fr.deltadesnoc.utils;

import com.google.gson.*;
import fr.deltadesnoc.Main;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class Config {

    private Gson gson;
    private Map<String, String> valeurs = new HashMap<>();
    private File file;

    public Config(String path) throws IOException {
        file = new File(FileManager.getDIR(), path);
        file.createNewFile();
        GsonBuilder builder = new GsonBuilder();
        gson = builder.create();
    }

    public void set(final String key,final String value){
        valeurs.put(key,value);
        try {
            FileWriter fileWriter = new FileWriter(file);
            fileWriter.write(gson.toJson(valeurs));
            fileWriter.close();
        } catch (IOException e) {
            Main.logger.warn(e.getMessage());
        }

    }
    public String get(final String key){
        StringBuilder builder = new StringBuilder();
        try(BufferedReader bufferedReader = Files.newBufferedReader(Paths.get(file.getAbsolutePath()))){
            String line;
            while((line = bufferedReader.readLine()) != null){
                builder.append(line).append("\n");
            }
        }catch (IOException e){
            Main.logger.warn(e.getMessage());
        }
        return Objects.requireNonNull(parseJSON(builder.toString(), key)).toString().replaceAll("\"","");
    }

    private JsonElement parseJSON(String string, String key) {
        JsonParser jsonParser = new JsonParser();
        JsonElement element = jsonParser.parse(string);
        if(element.isJsonObject()) {
            JsonObject jsonObject = element.getAsJsonObject();
            return jsonObject.get(key);
        }
        return null;
    }
}
