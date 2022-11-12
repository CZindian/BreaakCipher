package cz.osu.smash.cipher.app;

import cz.osu.smash.cipher.app.exceptions.UnsupportedAlphabetCharacterException;

import static cz.osu.smash.cipher.app.utils.Constants.MAX_INT_CHAR_VALUE;
import static cz.osu.smash.cipher.app.utils.Utils.getAlphabetLetter;
import static cz.osu.smash.cipher.app.utils.Utils.getLetterIdx;

public class Decrypt {

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

    private static int getCharValueIfMoreThanMaximal(int charValue) {

        if (charValue >= MAX_INT_CHAR_VALUE) {
            charValue = charValue - MAX_INT_CHAR_VALUE;
        }

        return charValue;

    }

    private static int getCharValueIfLessThanMinimal(int charValue) {

        if (charValue < 0) {
            charValue = charValue + MAX_INT_CHAR_VALUE;
        }

        return charValue;

    }

}
