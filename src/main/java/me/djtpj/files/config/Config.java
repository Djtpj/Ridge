package me.djtpj.files.config;

import lombok.Getter;
import org.json.simple.JSONObject;

@Getter
public abstract class Config<T> {

    final String key;

    T value;

    public Config(String key, T value) {
        this.key = key;
        this.value = value;
    }

    public abstract void read(JSONObject jsonObject);

    public JSONObject writeToJSON() {
        JSONObject result = new JSONObject();

        result.put(key, value);

        return result;
    }
}
