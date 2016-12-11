package view;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class StaticContent {

    private static String staticDirPath = new File("").getAbsolutePath() + "/src/view/static/";

    public static String get(String filename) throws IOException {
        System.out.println("Request file: " +  filename);
        return new String(Files.readAllBytes(Paths.get(staticDirPath + filename)));
    }

    public static boolean isFileExist(String filename) {
        return new File(staticDirPath + filename).exists();
    }
}
