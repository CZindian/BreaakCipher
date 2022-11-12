package cz.osu.smash.cipher.app;

import cz.osu.smash.cipher.app.exceptions.IllegalConsoleInputException;
import cz.osu.smash.cipher.app.exceptions.UnsupportedAlphabetCharacterException;

import java.util.Scanner;

import static cz.osu.smash.cipher.app.utils.TextColors.*;
import static cz.osu.smash.cipher.app.utils.Utils.checkValidity;
import static cz.osu.smash.cipher.app.utils.Utils.getConsoleInput;

public class BreakCipher {

    private static String consoleInput;

    /**
     * Main run method.
     */
    public static void run() {

        System.out.println(ANSI_GREEN + "Vítejte!" + ANSI_RESET);
        System.out.println(ANSI_GREEN + "Tento program vám pomůže zjistit šifrovací klíče pro šifru typu 'autoklíč'." + ANSI_RESET);
        System.out.println(ANSI_GREEN + "Vložte zašifrovanou zprávu:" + ANSI_RESET);
        manageProgram();

    }

    private static void manageProgram() {

        listenConsoleInput();
        bruteForceCall();
        listenConsoleInputExit();

    }

    /**
     * Calls brute force method on current input.
     */
    private static void bruteForceCall() {

        try {
            BruteForce.run(consoleInput);
            printResult();
        } catch (UnsupportedAlphabetCharacterException e) {
            System.out.println(ANSI_RED + e.getMessage() + ANSI_RESET);
            System.out.println(ANSI_GREEN + "Vložte zašifrovanou zprávu z abecedy:" + ANSI_RESET);
            bruteForceCall();
        }

    }

    private static void printResult() {

        if (BruteForce.getPossibleCombinations().length() == 0) {
            System.out.println(ANSI_RED + "Nebyla nalezena žádná smysluplná kombinace." + ANSI_RESET);
        } else {
            System.out.println(
                    ANSI_YELLOW + "Klíče, které mohly být zvoleny pro danou šifru:\n" +
                            BruteForce.getPossibleCombinations() + ANSI_RESET
            );
        }

    }

    private static void listenConsoleInput() {

        try {
            consoleInput = getConsoleInput(
                    new Scanner(System.in)
            );
            checkValidity(consoleInput);

        } catch (IllegalConsoleInputException e) {
            System.out.println(ANSI_RED + e.getMessage() + ANSI_RESET);
            System.out.println(ANSI_GREEN + "Vložte zašifrovanou zprávu z abecedy:" + ANSI_RESET);
            listenConsoleInput();

        }

    }

    private static void listenConsoleInputExit() {

        System.out.println(ANSI_RED + "Chcete program opustit? (a/n)" + ANSI_RESET);
        consoleInput = getConsoleInput(
                new Scanner(System.in)
        );

        if (consoleInput.equals("a")) {
            System.exit(1);
        } else {
            System.out.println(ANSI_GREEN + "Vložte zašifrovanou zprávu:" + ANSI_RESET);
            manageProgram();
        }

    }

}
