package cz.osu.smash.cipher.app.exceptions;

public class IllegalConsoleInputException extends Exception {
    public IllegalConsoleInputException() {
        super("\t-nebyl vložen žáden text");
    }
}
