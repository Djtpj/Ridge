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
    public <T extends Config> T getConfig(String key) {

        ArrayList<Config<?>> configs = this.configs;

        Predicate<Config<?>> filter = key::equals;

        configs.removeIf(filter.negate());

        return (T) configs.get(0);
    }

    @Override
    public AppConfigManager write(String path) {

        JSONArray json = new JSONArray();

        for (Config config : configs) {
            json.add(config.writeToJSON());
        }

        FileUtilities.writeToFile(json.toJSONString(), path);

        return this;
    }

    @Override
    public AppConfigManager write() {

        write(this.path);

        return this;
    }

    @Override
    public AppConfigManager read(String path) {

        read(new File(path));

        return this;
    }

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
