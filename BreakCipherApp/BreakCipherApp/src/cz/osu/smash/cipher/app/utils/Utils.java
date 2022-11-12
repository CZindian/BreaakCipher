package cz.osu.smash.cipher.app.utils;

import cz.osu.smash.cipher.app.exceptions.IllegalConsoleInputException;
import cz.osu.smash.cipher.app.exceptions.UnsupportedAlphabetCharacterException;

import java.util.Scanner;

public class Utils {

    public static String getConsoleInput(Scanner scanner) {

        String input;
        input = scanner.nextLine();
        return input;

    }

    public static void checkValidity(String input) throws IllegalConsoleInputException {

        if (input.length() == 0) {
            throw new IllegalConsoleInputException();
        }

    }

    /**
     * @param c single character
     * @return current index of character from list of supported alphabet characters
     * @throws UnsupportedAlphabetCharacterException is thrown, when there is character, which is not supported
     */
    public static int getLetterIdx(char c) throws UnsupportedAlphabetCharacterException {

        String[] letters = Constants.getAlphabetLetters();
        String stringC = String.valueOf(c);
        int ret = -1;

        for (int i = 0; i < letters.length; i++) {
            if (stringC.equals(letters[i])) {
                ret = i;
                break;
            }
        }

        if (ret == -1) {
            throw new UnsupportedAlphabetCharacterException(c);
        }

        return ret;

    }

    /**
     * @param charIdx index of character from supported alphabet array
     * @return current letter from list of supported alphabet characters
     * @throws UnsupportedAlphabetCharacterException is thrown, when there is character, which is not supported
     */
    public static String getAlphabetLetter(int charIdx)
            throws UnsupportedAlphabetCharacterException {

        String[] letters = Constants.getAlphabetLetters();
        String ret = null;

        for (int i = 0; i < letters.length; i++) {
            if (charIdx == i) {
                ret = letters[i];
                break;
            }
        }

        if (ret == null) {
            throw new UnsupportedAlphabetCharacterException(charIdx);
        }

        return ret;

    }


}
