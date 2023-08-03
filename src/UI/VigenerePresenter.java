package UI;

import java.util.Scanner;

import Entities.VigenereFactory;
import UseCase.*;

public class VigenerePresenter {
    private final Scanner scanner;
    private final VigenereFactory vigenereFactory;
    private final EncryptionBoundary encryptor;
    private final DecryptionBoundary decryptor;
    private final FileHandler fileHandler;


    public VigenerePresenter() {
        scanner = new Scanner(System.in);
        vigenereFactory = new VigenereFactory();
        encryptor = new VigenereEncryptionUseCase();
        decryptor = new VigenereDecryptionUseCase();
        CiphertextReader reader = new SimpleCiphertextReader();
        CiphertextWriter writer = new SimpleCiphertextWriter();
        fileHandler = new FileHandler(reader, writer);
    }

    /**
     * Input validator for message user inputs
     * only allows alphabet, spaces, and punctuation
     * @return user input message
     */
    private String readMessageInput() {
        String message;
        while (true) {
            message = scanner.nextLine().trim();
            // Use regex pattern to validate the input
            if (message.matches("^[a-zA-Z ,.!?]+$")) {
                return message;
            } else {
                System.out.print("Invalid input. Please enter alphabetic letters, spaces, or punctuations: ");
            }
        }
    }

    /**
     * Input validator for key user inputs
     * only allows alphabetic letters
     * @return user input key
     */
    private String readKeyInput() {
        String key;
        while (true) {
            key = scanner.nextLine().trim();
            if (key.matches("[a-zA-Z]+")) { // regex only alphabetic letters
                return key;
            } else {
                System.out.println("Invalid key input. Please enter only alphabetic letters.");
            }
        }
    }

    /**
     * Part of encryption logic. Takes user input for message and key, calls encryption use case
     * @param scanner
     */
    void encryptMessage(Scanner scanner) {
        System.out.print("Enter the plaintext message (only alphabetic letters, spaces, or punctuations): ");
        String plaintext = readMessageInput();

        System.out.print("Enter the encryption key (only alphabetic letters): ");
        String key = readKeyInput();

        String ciphertext = encryptor.encrypt(vigenereFactory.createVigenere(plaintext, key));
        System.out.println("Encrypted ciphertext: " + ciphertext);

        saveToFileOption(scanner, ciphertext);
    }

    /**
     * New feature in encryption, gives user the option to save ciphertext to a file
     * @param scanner
     * @param ciphertext
     */
    private void saveToFileOption(Scanner scanner, String ciphertext) {
        System.out.print("Do you want to save the ciphertext to a file? (Y/N): ");
        String saveChoice = scanner.nextLine().toUpperCase();
        if (saveChoice.equals("Y")) {
            System.out.print("Enter the filename to save ciphertext (old files will be overwritten): ");
            String filename = scanner.nextLine();
            fileHandler.saveCiphertextToFile(filename, ciphertext);
            System.out.println("Returning to main menu.");
        } else {
            System.out.println("Returning to main menu.");
        }
    }

    /**
     * Part of decryption logic.
     * Allows users to choose whether to enter text manually or decrypt from file
     * Delegates decryption process to helper methods
     * @param scanner
     */
    void decryptMessage(Scanner scanner) {
        System.out.println();
        System.out.println("Choose an option:");
        System.out.println("Decrypt from manual input (Enter M)");
        System.out.println("Decrypt from file (Enter F)");
        System.out.println("Exit (Enter X)");
        System.out.print("Enter choice here: ");
        String choice = scanner.nextLine().trim().toUpperCase();
        String ciphertext;

        switch (choice) {
            case "M":
                System.out.print("Enter the ciphertext to decrypt (only alphabetic letters, spaces, or punctuations): ");
                ciphertext = readMessageInput();
                decryptCiphertext(ciphertext);
                break;
            case "F":
                ciphertext = fileHandler.readCiphertextFromFile(scanner);
                if (!ciphertext.isEmpty()) { // if no file exists or file is empty
                    decryptCiphertext(ciphertext);
                } else {
                    System.out.println("Returning to decryption menu. Please choose a new file or input manually.");
                    decryptMessage(scanner); // Go back to the "Decrypt Message" menu
                }
                break;
            case "X":
                System.out.println("Exiting decryption. Returning to main menu.");
                break;
            default:
                System.out.println("Invalid choice. Please choose 'M', 'F', or 'X'.");
        }
    }

    /**
     * Enter key and call decryption use case.
     */
    private void decryptCiphertext(String ciphertext) {
        System.out.print("Enter the decryption key (only alphabet letters): ");
        String decryptionKey = readKeyInput();

        String decryptedText = decryptor.decrypt(vigenereFactory.createVigenere(ciphertext, decryptionKey));
        System.out.println("Decrypted message: " + decryptedText);
    }
}