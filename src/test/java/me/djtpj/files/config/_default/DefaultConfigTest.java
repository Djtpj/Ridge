package me.djtpj.files.config._default;

import junit.framework.TestCase;
import me.djtpj.files.FileUtilities;

import java.io.File;

public class DefaultConfigTest extends TestCase {

    File jsonFile = new File("foofig.json");

    DefaultConfigManager defaultConfig = new DefaultConfigManager(jsonFile.getPath());

    @Override
    protected void setUp() throws Exception {
        // TODO: 7/22/2022  
//        defaultConfig.addConfig("key1",1);
//        defaultConfig.addConfig("key2", 2);
//        defaultConfig.addConfig("key3",3);
    }

    public void testWrite() {


        defaultConfig.write();

        String results = FileUtilities.readTextFromFile(jsonFile);

        String expected = "{\n" +
                "  \"configs\": {\n" +
                "    \"key1\": 1,\n" +
                "    \"key2\": 2,\n" +
                "    \"key3\": 3\n" +
                "  }\n" +
                "}";

        assertEquals(clean(expected), clean(results));
    }

    public String clean(String base) {
        return base.replaceAll("[^@a-zA-Z\\d]", "");
    }

    @Override
    public void tearDown() {
        FileUtilities.cleanUp();
        jsonFile.delete();
    }

    public void testRead() {
        testWrite();

        DefaultConfigManager config = new DefaultConfigManager(jsonFile.getPath());

        config.read(jsonFile);

        assertEquals(defaultConfig, config);
    }
}