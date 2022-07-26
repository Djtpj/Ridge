package me.djtpj.files.config._default;

import lombok.Getter;
import me.djtpj.files.FileUtilities;
import me.djtpj.files.config.AppConfigManager;
import me.djtpj.files.config.Config;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.File;
import java.util.ArrayList;
import java.util.function.Predicate;

public class DefaultConfigManager implements AppConfigManager {

    @Getter
    transient final String path;

    private final ArrayList<Config<?>> configs;

    /**
     * @param configFile the path to the config.json file
     */
    public DefaultConfigManager(String configFile) {
        this.path = configFile;
        this.configs = new ArrayList<>();
    }

    /**
     * @param configFile the config.json file
     */
    public DefaultConfigManager(File configFile) {
        this.path = configFile.getAbsolutePath();

        this.configs = new ArrayList<>();
    }

    /**
     * @param config the config to add
     */
    @Override
    public AppConfigManager addConfig(Config config) {
        configs.add(config);
        return this;
    }

    @Override
    public AppConfigManager removeConfig(String key) {
        configs.removeIf(config -> config.getKey().equals(key));
        return this;
    }

    @Override
    public Config[] getConfigs() {
        return configs.toArray(new Config[0]);
    }

    @Override
    public Config<?> getConfig(String key) {

        for (Config<?> config : configs) {
            if (config.getKey().equals(key)) return config;
        }

        return null;
    }

    @Override
    public AppConfigManager setConfig(Config config) {
        removeConfig(config.getKey());

        configs.add(config);

        return this;
    }

    /**
     * Only writes JSON values as specified by the stored configs
     * @param path the path to write to
     */
    @Override
    public AppConfigManager write(String path) {

        JSONArray json = new JSONArray();

        for (Config config : configs) {
            json.add(config.writeToJSON());
        }

        FileUtilities.writeToFile(json.toJSONString(), path);

        return this;
    }

    /**
     * Writes to the defined path
     */
    @Override
    public AppConfigManager write() {

        write(this.path);

        return this;
    }

    /**
     * @param path the path to read from
     */
    @Override
    public AppConfigManager read(String path) {

        read(new File(path));

        return this;
    }

    /** Updates the stored configs values
     * @param file the file read from
     */
    @Override
    public AppConfigManager read(File file) {
        String json = FileUtilities.readTextFromFile(file);

        JSONParser jsonParser = new JSONParser();

        try {
            JSONArray jsonArray = (JSONArray) jsonParser.parse(json);


            if (jsonArray.size() > 0 && configs.size() > 0) {
                for (int i = 0; i < jsonArray.size(); i++) {
                    configs.get(i).read((JSONObject) jsonArray.get(i));
                }
            }

        } catch (ParseException e) {
            throw new RuntimeException(e);
        }

//        DefaultConfigManager read = gson.fromJson(json, new TypeToken<DefaultConfigManager>(){}.getType());

        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        DefaultConfigManager config = (DefaultConfigManager) o;
        return path.equals(config.path) && configs.equals(config.configs);
    }
}
