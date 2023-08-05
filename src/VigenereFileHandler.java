import java.io.IOException;
import java.util.Scanner;

public class VigenereFileHandler {
    private static CiphertextReader reader;
    private static CiphertextWriter writer;

    /**
     * Constructor
     * @param reader initialize reader object
     * @param writer initialize writer object
     */
    public VigenereFileHandler(CiphertextReader reader, CiphertextWriter writer) {
        this.reader = reader;
        this.writer = writer;
    }

    /**
     * New feature in the encryption process
     * Save ciphertext to file
     * @param filename file location to save to
     * @param ciphertext ciphertext from encryption process
     */
    public void writeCiphertextToFile(String filename, String ciphertext) {
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
     * @param scanner scanner object
     * @return ciphertext string
     */
     public String readCiphertextFromFile(Scanner scanner) {
        System.out.print("Enter the name of an existing file to get the ciphertext: ");
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