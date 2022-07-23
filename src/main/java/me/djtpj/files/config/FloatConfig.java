package me.djtpj.files.config;

import org.json.simple.JSONObject;

public class FloatConfig extends Config<Float> {

    public FloatConfig(String key, Float value) {
        super(key, value);
    }

    @Override
    public void read(JSONObject jsonObject) {
        value = (float) (double) jsonObject.get(key);
    }
}
