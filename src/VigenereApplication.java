import UI.VigenereConsoleUI;

/**
 * The VigenereApplication class is the main entry point for the Vigenere cipher application.
 * It initializes the console user interface and starts the application.
 */
public class VigenereApplication {
    public static void main(String[] args) {
        VigenereConsoleUI consoleUI = new VigenereConsoleUI();
        consoleUI.start();
    }
}
