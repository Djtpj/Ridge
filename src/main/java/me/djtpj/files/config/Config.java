package me.djtpj.files.config;

import lombok.Getter;
import lombok.Setter;
import org.json.simple.JSONObject;

import java.util.Objects;

@Getter
public abstract class Config<T> {

    final String key;

    @Setter
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Config<?> config = (Config<?>) o;
        return Objects.equals(key, config.key) && Objects.equals(value, config.value);
    }
}
