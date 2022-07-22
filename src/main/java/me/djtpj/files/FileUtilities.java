package me.djtpj.files;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class FileUtilities {
    public static String readTextFromFile(File file) {
        StringBuilder results = new StringBuilder();

        for (String line : readTextFromFileAsLines(file)) {
            results.append(line);
        }

        return results.toString();
    }

    public static String[] readTextFromFileAsLines(File file) {
        ArrayList<String> lines = new ArrayList<>();
        try {
            BufferedReader br = new BufferedReader(new FileReader(file));

            while (br.readLine() != null) {
                lines.add(br.readLine());
            }
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return lines.toArray(new String[0]);
    }

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

    public static void writeToFile(String absPath, String content) {
        try {
            FileWriter writer = new FileWriter(absPath);
            writer.write(content);
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
