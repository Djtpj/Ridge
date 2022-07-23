package me.djtpj.files.config;

import org.json.simple.JSONObject;

public class StringConfig extends Config<String> {
    public StringConfig(String key, String value) {
        super(key, value);
    }

    @Override
    public void read(JSONObject jsonObject) {
        value = (String) jsonObject.get(key);
    }
}
