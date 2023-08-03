package UI;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class SimpleCiphertextWriter implements CiphertextWriter {
    /**
     * @param filename
     * @param content
     */
    @Override
    public void writeToFile(String filename, String content) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filename))) {
            writer.write(content);
        } catch (IOException e) {
            System.out.println("Error saving ciphertext to file: " + e.getMessage());
        }
    }
}
