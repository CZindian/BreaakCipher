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

    /**
     * Main brute force method – manager.
     *
     * @param consoleInput encrypted message
     * @throws UnsupportedAlphabetCharacterException is thrown when consoleInput contains letter,
     *                                               which is not defined in alphabet
     */
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

    /**
     * Searches all combinations of possible key from alphabet.
     *
     * @param consoleInput encrypted message
     * @throws UnsupportedAlphabetCharacterException is thrown when consoleInput contains letter,
     *                                               which is not defined in alphabet
     */
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

    /**
     * Searches all valid combinations of key and decrypted message.
     */
    private static void searchAllValidCombinations() {

        StringBuilder sb = new StringBuilder();

        for (Map.Entry<String, String> entry : decryptedSentences.entrySet()) {
            String key = entry.getKey();
            String sentence = entry.getValue();
            appendTo(sb, key, sentence);
        }

        possibleCombinations = sb.toString();

    }

    /**
     * Appends valid key and decrypted message.
     * Decrypted message is valid against dictionary.
     *
     * @param sb       StringBuilder of all key : sentence combinations
     * @param key      possible valid key for encryption
     * @param sentence valid sentence
     */
    private static void appendTo(StringBuilder sb, String key, String sentence) {
        if (isValid(sentence)) {
            sb.append("klíč '").append(key).append("' : ").
                    append("hodnota '").append(sentence).append("'").append("\n");
        }
    }

    private static boolean isValid(String sentence) {

        sentence = sentence.replace(".", " ").replace(",", " ");
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

    /**
     * Initialize alphabet.
     */
    //region Util methods
    private static void initAlphabet() {
        if (alphabet == null) {
            alphabet = Constants.getAlphabetLetters();
        }
    }

    /**
     * Initialize dictionary – Set of czech words.
     */
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
