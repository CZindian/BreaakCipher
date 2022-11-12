package cz.osu.smash.cipher.app;

import cz.osu.smash.cipher.app.exceptions.UnsupportedAlphabetCharacterException;

import static cz.osu.smash.cipher.app.utils.Constants.MAX_INT_CHAR_VALUE;
import static cz.osu.smash.cipher.app.utils.Utils.getAlphabetLetter;
import static cz.osu.smash.cipher.app.utils.Utils.getLetterIdx;

public class Decrypt {

    /**
     * @param possibleKey simple character from the alphabet
     * @param input encrypted message
     * @return decrypted message
     * @throws UnsupportedAlphabetCharacterException is thrown when consoleInput contains letter,
     * which is not defined in alphabet
     */
    public static String getDecryptedSentence(String possibleKey, String input)
            throws UnsupportedAlphabetCharacterException {

        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < input.length(); i++) {

            int charValue = getLetterIdx(input.charAt(i)) - getLetterIdx(possibleKey.charAt(0));
            charValue = getCharValueIfLessThanMinimal(charValue);
            charValue = getCharValueIfMoreThanMaximal(charValue);

            possibleKey = String.valueOf(input.charAt(i));
            sb.append(getAlphabetLetter(charValue));

        }

        return sb.toString();

    }

    /**
     * @param charValue current character int value
     * @return valid character number, that is in range of alphabet
     */
    private static int getCharValueIfMoreThanMaximal(int charValue) {

        if (charValue >= MAX_INT_CHAR_VALUE) {
            charValue = charValue - MAX_INT_CHAR_VALUE;
        }

        return charValue;

    }

    /**
     * @param charValue current character int value
     * @return valid character number, that is in range of alphabet
     */
    private static int getCharValueIfLessThanMinimal(int charValue) {

        if (charValue < 0) {
            charValue = charValue + MAX_INT_CHAR_VALUE;
        }

        return charValue;

    }

}
