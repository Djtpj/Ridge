package me.djtpj.files.config._default;

import junit.framework.TestCase;
import me.djtpj.files.FileUtilities;
import me.djtpj.files.config.IntegerConfig;

import java.io.File;

import static org.junit.Assert.assertArrayEquals;

public class DefaultConfigTest extends TestCase {

    File jsonFile = new File("foofig.json");

    DefaultConfigManager defaultConfig = new DefaultConfigManager(jsonFile.getPath());

    @Override
    protected void setUp() {
        defaultConfig.addConfig(new IntegerConfig("key1", 1));
        defaultConfig.addConfig(new IntegerConfig("key2", 2));
        defaultConfig.addConfig(new IntegerConfig("key3", 3));

    }

    public void testWrite() {


        defaultConfig.write();

        String results = FileUtilities.readTextFromFile(jsonFile);

        String expected = "[{\"key1\":1},{\"key2\":2},{\"key3\":3}]";

        assertEquals(expected, results);
    }

    @Override
    public void tearDown() {
        FileUtilities.cleanUp();
        jsonFile.delete();
    }

    public void testRead() {
        testWrite();

        defaultConfig.read(jsonFile);

        DefaultConfigManager configManager = new DefaultConfigManager(jsonFile.getAbsolutePath());

        configManager.addConfig(new IntegerConfig("key1", 1));
        configManager.addConfig(new IntegerConfig("key2", 2));
        configManager.addConfig(new IntegerConfig("key3", 3));

        assertArrayEquals(defaultConfig.getConfigs(), configManager.getConfigs());
    }
}