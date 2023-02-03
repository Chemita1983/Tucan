package infraestructure.utils;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

/**
 * I/O Utility class
 */
public class IOUtils {

    /**
     * Read a file from path, and returns in a list of string by each line of file
     *
     * @param path source of the file
     * @return list of string by each line of file
     * @throws Exception when error occurs
     */
    public static List<String> readFile(String path) throws Exception {

        List<String> lines;
        try {
            lines = Files.readAllLines(Paths.get(path), StandardCharsets.UTF_8);
        } catch (IOException e) {
            throw new Exception("Error processing file");
        }

        return lines;
    }


}
