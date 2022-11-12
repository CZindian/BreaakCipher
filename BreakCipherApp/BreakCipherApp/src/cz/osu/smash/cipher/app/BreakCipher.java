package cz.osu.smash.cipher.app;

import cz.osu.smash.cipher.app.exceptions.IllegalConsoleInputException;
import cz.osu.smash.cipher.app.exceptions.UnsupportedAlphabetCharacterException;

import java.util.Scanner;

import static cz.osu.smash.cipher.app.utils.Utils.checkValidity;
import static cz.osu.smash.cipher.app.utils.Utils.getConsoleInput;

public class BreakCipher {

    //region Attributes
    private static String consoleInput;
    private static String possibleKeys;
    //endregion

    public static void run() {

        System.out.println("Vítejte!");
        System.out.println("Tento program vám pomůže zjistit šifrovací klíče pro šifru typu 'autoklíč'.");
        System.out.println("Vložte zašifrovanou zprávu:");
        listenConsoleInput();
        bruteForceCall();
        listenConsoleInputExit();

    }

    private static void bruteForceCall() {

        try {
            BruteForce.run(consoleInput);
            System.out.println(
                    "Klíče, které mohly být zvoleny pro danou šifru:\n" +
                            BruteForce.getPossibleCombinations()
            );
        } catch (UnsupportedAlphabetCharacterException e) {
            System.out.println(e.getMessage());
            bruteForceCall();
        }

    }

    private static void listenConsoleInput() {

        try {
            consoleInput = getConsoleInput(
                    new Scanner(System.in)
            );
            checkValidity(consoleInput);

        } catch (IllegalConsoleInputException e) {
            System.out.println(e.getMessage());
            System.out.println("Vložte zašifrovanou zprávu:");
            listenConsoleInput();

        }

    }

    private static void listenConsoleInputExit() {

        System.out.println("Chcete program opustit? (a/n)");
        consoleInput = getConsoleInput(
                new Scanner(System.in)
        );

        if (consoleInput.equals("a"))
            System.exit(1);
        else
            listenConsoleInput();

    }

}
