package UI;

import Entities.VigenereFactory;
import UseCase.DecryptionBoundary;
import UseCase.EncryptionBoundary;
import UseCase.VigenereDecryptionUseCase;
import UseCase.VigenereEncryptionUseCase;

import java.util.Scanner;

/**
 * The VigenerePresenter class acts as a presenter between the user interface and the use case layer.
 * It handles user inputs, interactions, and calls appropriate use cases for encryption and decryption.
 */
public class VigenerePresenter {
    private final Scanner scanner;
    private final VigenereFactory vigenereFactory;
    private final EncryptionBoundary encryptor;
    private final DecryptionBoundary decryptor;
    private final VigenereFileHandler fileHandler;

    /**
     * Constructor to initialize VigenerePresenter and set
     */
    public VigenerePresenter() {
        scanner = new Scanner(System.in);
        vigenereFactory = new VigenereFactory();
        encryptor = new VigenereEncryptionUseCase();
        decryptor = new VigenereDecryptionUseCase();
        CiphertextReader reader = new CiphertextFileReader();
        CiphertextWriter writer = new CiphertextFileWriter();
        fileHandler = new VigenereFileHandler(reader, writer);
    }

    /**
     * Input validator for message user inputs
     * only allows alphabet, spaces, and punctuation
     * keep prompting user until valid message is inputted
     * @return user input message
     */
    private String readMessageInput() {
        String message;
        while (true) {
            message = scanner.nextLine().trim();
            // Use regex pattern to validate the input; allows alphabet, punctuation, space
            if (message.matches("^[a-zA-Z ,.!?]+$")) {
                return message;
            } else {
                System.out.print("Invalid input. Please enter only alphabetic letters, space and punctuation allowed: ");
            }
        }
    }

    /**
     * Input validator for key user inputs
     * only allows alphabetic letters
     * keep prompting user until valid key is inputted
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
     * @param scanner scanner object
     */
    void encryptMessage(Scanner scanner) {
        System.out.println();
        System.out.println("===============");
        System.out.println("Encryption Menu");
        System.out.println("===============");
        System.out.print("Enter the plaintext message (only alphabetic letters, space, and punctuation): ");
        String plaintext = readMessageInput();

        System.out.print("Enter the encryption key (only alphabetic letters): ");
        String key = readKeyInput();

        String ciphertext = encryptor.encrypt(vigenereFactory.createVigenere(plaintext, key));
        System.out.println("Encrypted ciphertext: " + ciphertext);

        saveToFileOption(scanner, ciphertext);
    }

    /**
     * New feature in encryption, gives user the option to save ciphertext to a file
     * @param scanner scanner object
     * @param ciphertext generated ciphertext from encryption process
     */
    private void saveToFileOption(Scanner scanner, String ciphertext) {
        System.out.print("Do you want to save the ciphertext to a file? (Y/N): ");
        String saveChoice = scanner.nextLine().toUpperCase();
        if (saveChoice.equals("Y")) {
            System.out.print("Enter the file name to save ciphertext (replaces existing files): ");
            String filename = scanner.nextLine();
            fileHandler.writeCiphertextToFile(filename, ciphertext);
            System.out.println("Returning to main menu.");
        } else {
            System.out.println("Returning to main menu.");
        }
    }

    /**
     * Part of decryption logic.
     * Allows users to choose whether to enter text manually or decrypt from file
     * Delegates decryption process to helper methods
     * @param scanner scanner object
     */
    void decryptMessage(Scanner scanner) {
        System.out.println();
        System.out.println("===============");
        System.out.println("Decryption Menu");
        System.out.println("===============");
        System.out.println("Choose an option:");
        System.out.println("Decrypt from manual input (Enter M)");
        System.out.println("Decrypt from file (Enter F)");
        System.out.println("Exit (Enter X)");
        System.out.print("Enter choice here: ");
        String choice = scanner.nextLine().trim().toUpperCase();
        String ciphertext;

        switch (choice) {
            case "M":
                System.out.print("Enter the ciphertext to decrypt (only alphabetic letters, space, and punctuation): ");
                ciphertext = readMessageInput();
                decryptCiphertext(ciphertext);
                break;
            case "F":
                ciphertext = fileHandler.readCiphertextFromFile(scanner);
                if (!ciphertext.isEmpty()) { // if no file exists or file is empty
                    decryptCiphertext(ciphertext);
                } else {
                    System.out.println("Returning to decryption menu. Please choose an existing file or input manually.");
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
     * Enter key and call decryption use case
     * @param ciphertext user input decrypted message
     */
    private void decryptCiphertext(String ciphertext) {
        System.out.print("Enter the decryption key (only alphabet letters): ");
        String decryptionKey = readKeyInput();

        String decryptedText = decryptor.decrypt(vigenereFactory.createVigenere(ciphertext, decryptionKey));
        System.out.println("Decrypted message: " + decryptedText);
    }
}