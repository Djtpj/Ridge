package me.djtpj.files.config;

import java.io.File;
import java.util.HashMap;

public interface AppConfig {
    void addConfig(String key, Object value);

    void removeConfig(String key);

    HashMap<String, Object> getConfigs();

    <T> T getConfig(String key);

    void write(String path);

    void write();

    void read(String path);

    void read(File file);
}
