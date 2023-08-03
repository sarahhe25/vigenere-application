import Entities.Vigenere;
import Entities.VigenereFactory;
import UseCase.CiphertextReader;
import UseCase.CiphertextWriter;
import UseCase.DecryptionBoundary;
import UseCase.EncryptionBoundary;

import java.io.IOException;
import java.util.Scanner;

public class VigenereController {
    private final EncryptionBoundary encryptor;
    private final DecryptionBoundary decryptor;
    private final CiphertextWriter writer;
    private final CiphertextReader reader;
    private final VigenereFactory vigenereFactory;

    public VigenereController(EncryptionBoundary encryptor, DecryptionBoundary decryptor,
                              CiphertextWriter writer, CiphertextReader reader, VigenereFactory vigenereFactory) {
        this.encryptor = encryptor;
        this.decryptor = decryptor;
        this.writer = writer;
        this.reader = reader;
        this.vigenereFactory = vigenereFactory;
        //this.scanner = new Scanner(System.in);
    }

    public void start() {
        try (Scanner scanner = new Scanner(System.in)) {
            while (true) {
                System.out.println();
                System.out.println("Choose an option:");
                System.out.println("Encrypt a message (Enter E)");
                System.out.println("Decrypt a message (Enter D)");
                System.out.println("Exit (Enter X)");
                System.out.print("Enter your choice: ");

                String choice = scanner.nextLine().trim().toUpperCase();

                switch (choice) {
                    case "E":
                        encryptMessage(scanner);
                        break;
                    case "D":
                        decryptMessage(scanner);
                        break;
                    case "X":
                        System.out.println("Exiting. Thanks for using the Vigenere Cipher App");
                        return;
                    default:
                        System.out.println("Please enter 'E', 'D', or 'X' for encryption, decryption, " +
                                "or exit, respectively");
                }
            }
        }
    }

    private void encryptMessage(Scanner scanner) {
        System.out.print("Enter the plaintext message (only alphabet, spaces, or punctuations): ");
        String plaintext = readAlphabeticInput(scanner);

        System.out.print("Enter the encryption key (only alphabet, spaces, or punctuations): ");
        String key = readAlphabeticInput(scanner);

        Vigenere vigenere = vigenereFactory.createVigenere(plaintext, key);

        String encryptedText = encryptor.encrypt(vigenere);
        System.out.println("Encrypted ciphertext: " + encryptedText);

        saveCiphertextToFile(encryptedText, scanner);
    }

    private void decryptMessage(Scanner scanner) {
        System.out.print("Enter 'M' to enter ciphertext manually, 'F' to read ciphertext from a file, or 'X' to quit: ");
        String choice = scanner.nextLine().trim().toUpperCase();

        switch (choice) {
            case "M":
                decryptCiphertextManually(scanner);
                break;
            case "F":
                decryptCiphertextFromFile(scanner);
                break;
            case "X":
                System.out.println("Exiting decryption. Returning to main menu.");
                break;
            default:
                System.out.println("Invalid choice. Please choose 'M', 'F', or 'X'.");
        }
    }

    private void decryptCiphertextManually(Scanner scanner) {
        System.out.print("Enter the ciphertext to decrypt (only alphabet, spaces, or punctuations): ");
        String ciphertext = readAlphabeticInput(scanner);

        System.out.print("Enter the decryption key (only alphabet, spaces, or punctuations): ");
        String decryptionKey = readAlphabeticInput(scanner);

        Vigenere vigenere = vigenereFactory.createVigenere(ciphertext, decryptionKey);

        String decryptedText = decryptor.decrypt(vigenere);
        System.out.println("Decrypted message: " + decryptedText);
    }

    private void decryptCiphertextFromFile(Scanner scanner) {
        System.out.print("Enter the filename to read ciphertext from: ");
        String filename = scanner.nextLine();

        try {
            String ciphertext = reader.readFromFile(filename);

            if (ciphertext.isEmpty()) {
                System.out.println("No ciphertext found in the file.");
                return;
            }

            System.out.print("Enter the decryption key (only alphabet, spaces, or punctuations): ");
            String decryptionKey = readAlphabeticInput(scanner);

            Vigenere vigenere = vigenereFactory.createVigenere(ciphertext, decryptionKey);

            String decryptedText = decryptor.decrypt(vigenere);
            System.out.println("Decrypted message: " + decryptedText);
        } catch (IOException e) {
            System.out.println("Error reading ciphertext from file: " + e.getMessage());
        }
    }

    private void saveCiphertextToFile(String encryptedText, Scanner scanner) {
        System.out.print("Do you want to save the ciphertext to a file? (yes/no): ");
        String saveChoice = scanner.nextLine().toLowerCase();

        if ("yes".equals(saveChoice)) {
            System.out.print("Enter the filename to save ciphertext (old files will be overwritten): ");
            String filename = scanner.nextLine();
            try {
                writer.writeToFile(filename, encryptedText);
                System.out.println("Ciphertext saved to file: " + filename);
            } catch (IOException e) {
                System.out.println("Error saving ciphertext to file: " + e.getMessage());
            }
        }
    }

    private String readAlphabeticInput(Scanner scanner) {
        String input;
        while (true) {
            input = scanner.nextLine().trim();
            // Use regex pattern to validate the input
            if (input.matches("^[a-zA-Z ,.!?]+$")) {
                return input;
            } else {
                System.out.print("Invalid input. Please enter alphabets, spaces, or punctuations: ");
            }
        }
    }
}

