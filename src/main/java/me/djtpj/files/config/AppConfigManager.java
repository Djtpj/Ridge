package me.djtpj.files.config;

import java.io.File;

public interface AppConfigManager {
    AppConfigManager addConfig(Config config);

    AppConfigManager removeConfig(String key);

    Config[] getConfigs();

    <T> T getConfig(String key);

    AppConfigManager write(String path);

    AppConfigManager write();

    AppConfigManager read(String path);

    AppConfigManager read(File file);
}
