package UI;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class SimpleCiphertextReader implements CiphertextReader {
    /**
     * @param filename
     * @return content of file in a string
     */
    @Override
    public String readFromFile(String filename) {
        StringBuilder content = new StringBuilder();
        try (BufferedReader reader = new BufferedReader(new FileReader(filename))) {
            String line;
            while ((line = reader.readLine()) != null) {
                content.append(line).append(System.lineSeparator());
            }
        } catch (IOException e) {
            System.out.println("Error reading ciphertext from file: " + e.getMessage());
        }
        return content.toString();
    }
}
