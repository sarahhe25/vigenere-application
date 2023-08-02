import java.io.IOException;
import java.util.Scanner;

public class NewVigenereController {
    private final EncryptionBoundary encryptor;
    private final DecryptionBoundary decryptor;
    private final CiphertextWriter writer;
    private final CiphertextReader reader;

    public NewVigenereController(EncryptionBoundary encryptor, DecryptionBoundary decryptor,
                              CiphertextWriter writer, CiphertextReader reader) {
        this.encryptor = encryptor;
        this.decryptor = decryptor;
        this.writer = writer;
        this.reader = reader;
    }

    public void start() {
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("Choose an option:");
            System.out.println("1. Encrypt a message");
            System.out.println("2. Decrypt a message");
            System.out.println("3. Exit");
            System.out.print("Enter your choice: ");

            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume the new line

            switch (choice) {
                case 1:
                    encryptMessage(scanner);
                    break;
                case 2:
                    decryptMessage(scanner);
                    break;
                case 3:
                    System.out.println("Exiting VigenereCipherApp. Goodbye!");
                    return;
                default:
                    System.out.println("Invalid choice. Please choose 1, 2, or 3.");
            }
        }
    }

    private void encryptMessage(Scanner scanner) {
        System.out.print("Enter the plaintext message: ");
        String plaintext = scanner.nextLine();

        System.out.print("Enter the encryption key: ");
        String key = scanner.nextLine();

        String encryptedText = encryptor.encrypt(plaintext, key);
        System.out.println("Encrypted ciphertext: " + encryptedText);

        System.out.print("Do you want to save the ciphertext to a file? (yes/no): ");
        String saveChoice = scanner.nextLine().toLowerCase();

        if ("yes".equals(saveChoice)) {
            System.out.print("Enter the filename to save ciphertext: ");
            String filename = scanner.nextLine();
            try {
                writer.writeToFile(filename, encryptedText);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }

    private void decryptMessage(Scanner scanner) {
        System.out.print("Enter 'text' to enter ciphertext manually, 'file' to read ciphertext from a file, " +
                "or 'exit' to quit: ");
        String choice = scanner.nextLine().toLowerCase();

        if ("exit".equals(choice)) {
            return;
        } else if ("text".equals(choice)) {
            System.out.print("Enter the ciphertext to decrypt: ");
            String ciphertext = scanner.nextLine();
            System.out.print("Enter the decryption key: ");
            String decryptionKey = scanner.nextLine();

            String decryptedText = decryptor.decrypt(ciphertext, decryptionKey);
            System.out.println("Decrypted message: " + decryptedText);
        } else if ("file".equals(choice)) {
            System.out.print("Enter the filename to read ciphertext from: ");
            String filename = scanner.nextLine();
            String ciphertext = reader.readFromFile(filename);

            if (ciphertext.isEmpty()) {
                System.out.println("No ciphertext found in the file.");
                return;
            }

            System.out.print("Enter the decryption key: ");
            String decryptionKey = scanner.nextLine();

            String decryptedText = decryptor.decrypt(ciphertext, decryptionKey);
            System.out.println("Decrypted message: " + decryptedText);
        } else {
            System.out.println("Invalid choice. Please choose 'text', 'file', or 'exit'.");
        }
    }
}
