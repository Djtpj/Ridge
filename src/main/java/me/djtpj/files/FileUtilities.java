package me.djtpj.files;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/** Utility of things that should be able to be done quickly in Java, but cannot be done easily or quickly.
 *
 */
public class FileUtilities {

    private static BufferedReader bufferedReader;

    /** Reads all the text from the given file
     * @param file the file to read
     * @return all the text that was read
     */
    public static String readTextFromFile(File file) {
        StringBuilder results = new StringBuilder();

        for (String line : readTextFromFileAsLines(file)) {
            results.append(line);
        }

        return results.toString();
    }

    /** Reads all the lines from the given file
     * @param file the file to read
     * @return the lines from the file
     */
    public static String[] readTextFromFileAsLines(File file) {
        try {
            bufferedReader = new BufferedReader(new FileReader(file));

            Stream<String> lines = bufferedReader.lines();

            return lines.toArray(String[]::new);
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    /** Creates the file in the given location, but only creates if that file does not exist
     * @param file the file to create (the path is read from the file)
     */
    public static void makeFile(File file) {
        if (!file.exists()) {
            try {
                FileOutputStream fos = new FileOutputStream(file);
                fos.flush();
                fos.close();
            } catch (FileNotFoundException e) {
                throw new RuntimeException(e);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }

    /** Lists all the files in a given directory
     * @param absPath the absolute path of the directory to read
     * @return the files in the given directory
     */
    public static File[] listAllFiles(String absPath) {
        Path start = Paths.get(absPath);
        ArrayList<File> files = new ArrayList<>();
        try (Stream<Path> stream = Files.walk(start, Integer.MAX_VALUE)) {
            List<String> collect = stream
                    .map(String::valueOf)
                    .sorted()
                    .collect(Collectors.toList());

            for (String s : collect) {
                if (new File(s).isFile()) {
                    files.add(new File(s));
                }
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        return files.toArray(new File[files.size()]);
    }

    /**
     * @param absPath the path to validate
     * @return whether a path is valid or not
     */
    public static boolean isValidPath(String absPath) {
        if (!absPath.contains("\\")) {
            return false;
        }

        File file = new File(absPath);
        if (file.exists()) {
            return file.canWrite();
        }
        else {
            try {
                file.createNewFile();
                file.delete();
                return true;
            }
            catch (Exception e) {
                return false;
            }
        }
    }

    /** Writes the content string to the file in the given path
     * @param content the text you want to write to a file
     * @param absPath the absolute path of the file to write to
     * @see FileUtilities#isValidPath(String)
     */
    public static void writeToFile(String content, String absPath) {
        try {
            FileWriter writer = new FileWriter(absPath);
            writer.write(content);
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void cleanUp() {
        try {
            bufferedReader.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
