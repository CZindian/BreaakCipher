package cz.osu.smash.cipher.app;

import cz.osu.smash.cipher.Main;

import java.io.*;
import java.util.*;

public class StorageManager {

    private static final String FILE_NAME = "czech_words_dictionary.txt";

    public static Set<String> loadDictionary() {

        BufferedReader reader = getBufferedReader();
        Set<String> words = new HashSet<>();

        try (Scanner scan = new Scanner(reader)) {
            fill(words, scan);
        }

        return words;

    }

    private static BufferedReader getBufferedReader() {

        InputStream inputStream = Main.class.getResourceAsStream("/" + FILE_NAME);
        assert inputStream != null;
        InputStreamReader iss = new InputStreamReader(inputStream);

        return new BufferedReader(iss);

    }

    /**
     * Fills set by dictionary words
     *
     * @param words Set for dictionary words
     * @param scan  input reader
     */
    private static void fill(Set<String> words, Scanner scan) {

        while (scan.hasNext()) {
            words.add(scan.nextLine());
        }

    }

}
