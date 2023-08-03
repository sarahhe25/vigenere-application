package UI;

import java.io.IOException;
import java.util.Scanner;

public class FileHandler {
    private static CiphertextReader reader;
    private static CiphertextWriter writer;

    public FileHandler(CiphertextReader reader, CiphertextWriter writer) {
        this.reader = reader;
        this.writer = writer;
    }

    /**
     * New feature in the encryption process
     * Save ciphertext to file
     * @param filename
     * @param ciphertext
     */
    void saveCiphertextToFile(String filename, String ciphertext) {
        try {
            writer.writeToFile(filename, ciphertext);
            System.out.println("Ciphertext saved to file: " + filename);
        } catch (IOException e) {
            System.out.println("Error saving ciphertext to file: " + e.getMessage());
        }
    }
    /**
     * New feature in the decryption process
     * Read ciphertext from file
     * @param scanner
     * @return ciphertext string
     */
     String readCiphertextFromFile(Scanner scanner) {
        System.out.print("Enter the filename to read ciphertext from: ");
        String filename = scanner.nextLine();

        try {
            String ciphertext = reader.readFromFile(filename);

            if (ciphertext.isEmpty()) {
                System.out.println("No ciphertext found in the file.");
                return "";
            }
            return ciphertext;
        } catch (IOException e) {
            System.out.println("Error reading ciphertext from file: " + e.getMessage());
            return "";
        }
    }
}