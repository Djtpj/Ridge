package me.djtpj.files.config;

import org.json.simple.JSONObject;

public class BooleanConfig extends Config<Boolean> {
    public BooleanConfig(String key, Boolean value) {
        super(key, value);
    }

    @Override
    public void read(JSONObject jsonObject) {
        value = (boolean) jsonObject.get(key);
    }
}
