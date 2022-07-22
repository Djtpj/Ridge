package me.djtpj.files.config._default;

import lombok.Getter;
import me.djtpj.files.config.AppConfig;

import java.io.File;
import java.util.HashMap;

public class DefaultConfig implements AppConfig {

    @Getter
    final String path;

    private HashMap<String, Object> configs;

    public DefaultConfig(String path) {
        this.path = path;
        this.configs = new HashMap<>();
    }

    @Override
    public void addConfig(String key, Object value) {

    }

    @Override
    public void removeConfig(String key) {

    }

    @Override
    public HashMap<String, Object> getConfigs() {
        return null;
    }

    @Override
    public <T> T getConfig(String key) {
        return null;
    }

    @Override
    public void write(String path) {
        // write the configs
    }

    @Override
    public void write() {

    }

    @Override
    public void read(String path) {

    }

    @Override
    public void read(File file) {

    }
}
