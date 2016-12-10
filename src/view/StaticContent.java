package view;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class StaticContent {

    public static String get(String filename) throws IOException {
        return new String(Files.readAllBytes(
                Paths.get(new File("").getAbsolutePath() + "/src/view/static/" + filename)));
    }
}
