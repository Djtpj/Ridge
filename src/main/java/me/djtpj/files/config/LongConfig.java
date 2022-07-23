package me.djtpj.files.config;

import org.json.simple.JSONObject;

public class LongConfig extends Config<Long> {
    public LongConfig(String key, Long value) {
        super(key, value);
    }

    @Override
    public void read(JSONObject jsonObject) {
        value = (long) jsonObject.get(key);
    }
}
