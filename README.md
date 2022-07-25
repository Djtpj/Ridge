# Ridge
Ridge is a simple App Development Suite for Java. It is programmed with Java, by Java Programmers for Java Programmers.

# Getting Started
**Ridge does not yet have Maven Hosting!**

You can find the JAR file under [the most recent release](github.com/Djtpj/Ridge/releases/latest), and add it to your classpath.

# Configs
A config is an object that is in charge of serializing and deserializing objects. The more used Java primitives come pre made with the Ridge library (Strings, Booleans, Integers, Floats, and Longs).

### Defining your own configs
To create your own config, create a class and then extend the `me.djtpj.files.config.Config` class. Enter the type you want to serialize and deserialize as the type parameter for the `Config`class when you extend it.

A config requires one method: `read(JSONObject jsonObject)`. This is where you tell the config how to deserialize the value. You have to assign the value once it is parsed to the value variable.

If the type you are trying to deserialize or serialize isn't a JSON primitive, then you will need to override the write method, and write your own JSON Serialization code.

Note: this uses the [json-simple library](https://github.com/fangyidong/json-simple) for JSON handling.

Here is the code for the `StringConfig` file 
```java
public class StringConfig extends Config<String> {
    public StringConfig(String key, String value) {
        super(key, value);
    }

    @Override
    public void read(JSONObject jsonObject) {
        value = (String) jsonObject.get(key);
    }
}
```

# ConfigManagers
A config manager is used to, well, manage your configs! 
This will handle the group reading and writing of the configs to and from JSON.
You can configure your own ConfigManager, but it is recommended to use the `DefaultConfigManager` class. 
It is also recommended to extend this class, and make it a singleton so that you may have general access to your config manager.

## Writing
You add configs to your config manager via the `ConfigManager.addConfig()` function.

Once you have added your configs, and assigned them values and keys they can now be written! Call the `ConfigManager.write()` function to write all of your configs.

Here is an example of writing
```java
// Define the ConfigManager
DefaultConfigManager config = new DefaultConfigManager("config.json");

// Add the default config values
config.addConfig(new IntegerConfig("screenWidth", 1920));
config.addConfig(new IntegerConfig("screenHeight", 1080));
config.addConfig(new BooleanConfig("maximized", true));

// Write the configs to "config.json" (locally)
config.write();
```

## Reading
Reading your configs essentially updates the values from the JSON.

The ConfigManager will only grab values and data based off of the keys defined via adding. 

Here is an example of reading
```java
// Define the ConfigManager
DefaultConfigManager config = new DefaultConfigManager("config.json");

// Add the default values
config.addConfig(new IntegerConfig("screenWidth", 3840));
config.addConfig(new IntegerConfig("screenHeight", 2160));
config.addConfig(new BooleanConfig("maximized", false));

// Read the configs from "config.json" (locally)
config.read("config.json");
``` 
If we ran this code after running the previous snippet, our ConfigManager would stilll have the same values as before, even though we defined different default values.

# Getting
