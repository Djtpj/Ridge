package me.djtpj.files.config;

import junit.framework.TestCase;
import me.djtpj.files.FileUtilities;
import me.djtpj.files.config._default.DefaultConfigManager;

import java.io.File;

public class IntegerConfigTest extends TestCase {

    public void testRead() {
        File jsonFile = new File("foofig.json");
        jsonFile.deleteOnExit();

        String json = "[{\"key1\":1},{\"key2\":2},{\"key3\":3}]";

        FileUtilities.writeToFile(json, jsonFile.getAbsolutePath());

        DefaultConfigManager configManager = new DefaultConfigManager("foofig.json");

        configManager.read(jsonFile);
    }
}