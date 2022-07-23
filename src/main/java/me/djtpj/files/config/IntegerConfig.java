package me.djtpj.files.config;

import org.json.simple.JSONObject;

public class IntegerConfig extends Config<Integer> {
    public IntegerConfig(String key, Integer value) {
        super(key, value);
    }

    @Override
    public void read(JSONObject jsonObject) {
        value = (int) (long) jsonObject.get(key);
    }
}
