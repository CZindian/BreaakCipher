package cz.osu.smash.cipher.app;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class StorageManager {

    private static final String FILE_NAME = "czech_words.txt";

    public static Set<String> loadDictionary() {

        Set<String> words = new HashSet<>();
        File file = new File(FILE_NAME);

        try (Scanner scan = new Scanner(file)) {
            fill(words, scan);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        return words;

    }

    private static void fill(Set<String> words, Scanner scan) {

        while (scan.hasNext()) {
            words.add(scan.nextLine());
        }

    }

}
