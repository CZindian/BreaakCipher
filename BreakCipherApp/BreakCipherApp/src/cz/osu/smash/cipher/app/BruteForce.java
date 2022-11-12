package cz.osu.smash.cipher.app;

import cz.osu.smash.cipher.app.exceptions.UnsupportedAlphabetCharacterException;
import cz.osu.smash.cipher.app.utils.Constants;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import static cz.osu.smash.cipher.app.Decrypt.getDecryptedSentence;

public class BruteForce {

    //region Attributes
    private static String[] alphabet;
    private static Set<String> czechDictionary;
    private static Map<String, String> decryptedSentences;
    private static String possibleCombinations;
    //endregion

    public static void run(String consoleInput) throws UnsupportedAlphabetCharacterException {

        initAlphabet();
        initCzechDictionary();
        resetAttributes();

        System.out.println("Hledám všechny kombinace...");
        searchAllCombinations(consoleInput);
        System.out.println("Byly nalezeny všechny kombinace...");

        System.out.println("Hledám klíče...");
        searchAllValidCombinations();
        System.out.println("Byly nalezeny všechny možné klíče...");

    }

    private static void searchAllCombinations(String consoleInput)
            throws UnsupportedAlphabetCharacterException {

        decryptedSentences = new HashMap<>();
        for (String possibleKey : alphabet) {
            decryptedSentences.put(
                    possibleKey,
                    getDecryptedSentence(possibleKey, consoleInput)
            );
        }

    }

    private static void searchAllValidCombinations() {

        StringBuilder sb = new StringBuilder();

        for (Map.Entry<String, String> entry : decryptedSentences.entrySet()) {
            String key = entry.getKey();
            String sentence = entry.getValue();
            appendTo(sb, key, sentence);
        }

        possibleCombinations = sb.toString();

    }

    private static void appendTo(StringBuilder sb, String key, String sentence) {
        if (isValid(sentence)) {
            sb.append("klíč '").append(key).append("' : ").
                    append("hodnota '").append(sentence).append("'").append("\n");
        }
    }

    private static boolean isValid(String sentence) {

        sentence = sentence.replace(".", " ");
        String lowerCaseSentence = sentence.toLowerCase();
        String[] words = lowerCaseSentence.split(" ");

        return areAllWordsInDictionary(words);
    }

    private static boolean areAllWordsInDictionary(String[] words) {

        boolean ret = false;
        int matchCount = 0;

        for (int i = 0; i < words.length; i++) {

            if (czechDictionary.contains(words[i]) && i == matchCount) {
                ret = true;
                matchCount++;
            }

        }
        return ret;

    }

    public static String getPossibleCombinations() {
        return possibleCombinations;
    }

    //region Util methods
    private static void initAlphabet() {
        if (alphabet == null) {
            alphabet = Constants.getAlphabetLetters();
        }
    }

    private static void initCzechDictionary() {
        if (czechDictionary == null) {
            czechDictionary = StorageManager.loadDictionary();
        }
    }

    private static void resetAttributes() {
        decryptedSentences = null;
        possibleCombinations = null;
    }
    //endregion

}
