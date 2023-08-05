import java.util.Scanner;

public class VigenereConsoleUI {
    private Scanner scanner;
    private VigenerePresenter presenter;
    public VigenereConsoleUI() {
        scanner = new Scanner(System.in);
        presenter = new VigenerePresenter();
    }

    /**
     * Main menu. Users choose to encrypt, decrypt, or exit program.
     */
    public void start() {
        while (true) {
            System.out.println();
            System.out.println("=========");
            System.out.println("Main Menu");
            System.out.println("=========");
            System.out.println("Choose an option:");
            System.out.println("Encrypt a message (Enter E)");
            System.out.println("Decrypt a message (Enter D)");
            System.out.println("Exit (Enter X)");
            System.out.print("Enter your choice: ");

            String choice = scanner.nextLine().trim().toUpperCase();

            switch (choice) {
                case "E":
                    presenter.encryptMessage(scanner);
                    break;
                case "D":
                    presenter.decryptMessage(scanner);
                    break;
                case "X":
                    System.out.println("Exiting. Thank you for using the Vigenere Cipher App.");
                    scanner.close();
                    return; // exit
                default:
                    System.out.println("Please enter 'E', 'D', or 'X' for encryption, decryption, " +
                            "or exit, respectively");
            }
        }
    }
}
