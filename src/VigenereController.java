import java.io.IOException;
import java.util.Scanner;

public class VigenereController {
    private final EncryptionBoundary encryptor;
    private final DecryptionBoundary decryptor;
    private final CiphertextWriter ciphertextWriter;
    private final CiphertextReader ciphertextReader;

    public VigenereController(EncryptionBoundary encryptor, DecryptionBoundary decryptor,
                              CiphertextWriter ciphertextWriter, CiphertextReader ciphertextReader) {
        this.encryptor = encryptor;
        this.decryptor = decryptor;
        this.ciphertextWriter = ciphertextWriter;
        this.ciphertextReader = ciphertextReader;
    }

    public void encryptAndPrint() {
        Scanner scanner = new Scanner(System.in);

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
                ciphertextWriter.writeToFile(filename, encryptedText);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }

    public void decryptAndPrint() {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter 'text' to enter ciphertext manually, 'file' to read ciphertext from a file, or 'exit' to quit: ");
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
            String ciphertext = ciphertextReader.readFromFile(filename);

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
