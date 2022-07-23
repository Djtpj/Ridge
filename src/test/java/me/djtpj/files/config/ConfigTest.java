package me.djtpj.files.config;

import junit.framework.TestCase;

import java.io.File;

public class ConfigTest extends TestCase {
    // test read
    // test write

    File jsonFile = new File("foofig.json");

    @Override
    protected void setUp() {
        jsonFile.deleteOnExit();
    }

    public void testWrite() {
        System.out.println("woop");
    }

    public void testRead() {
        System.out.println("woop woop");
    }
}
