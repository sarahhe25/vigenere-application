package UI;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class CiphertextFileWriter implements CiphertextWriter {
    /**
     * Write ciphertext to file
     * If file doesn't exist, create new file
     * @param filename file to write to
     * @param content the ciphertext to write into the file
     */
    @Override
    public void writeToFile(String filename, String content) {
        // append to existing file. Create new file if no existing file
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filename, true))) {
            writer.write(content);
        } catch (IOException e) {
            System.out.println("Error saving ciphertext to file: " + e.getMessage());
        }
    }
}
