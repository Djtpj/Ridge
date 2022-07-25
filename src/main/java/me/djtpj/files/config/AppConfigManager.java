package me.djtpj.files.config;

import java.io.File;

/**
 * App Config Manager's control JSON Configurations in Ridge apps
 */
public interface AppConfigManager {

    /**
     * @param config the config to add
     */
    AppConfigManager addConfig(Config config);

    /**
     * @param key the key of the configuration to remove
     */
    AppConfigManager removeConfig(String key);

    Config[] getConfigs();

    <T extends Config> T getConfig(String key);

    AppConfigManager setConfig(Config config);

    /** Writes the configurations to the specified path in JSON format
     * @param path the path to write to
     */
    AppConfigManager write(String path);

    AppConfigManager write();

    /** Reads the configurations from the specified path as dictated
     * @param path the path to read from
     */
    AppConfigManager read(String path);

    AppConfigManager read(File file);
}
