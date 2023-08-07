package UI;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

/**
 * Implements the CiphertextReader interface to read ciphertext from a file.
 */
public class CiphertextFileReader implements CiphertextReader {

    /**
     * Reads ciphertext from a file and returns it as a string.
     * @param filename The name of the file to read from.
     * @return A string containing the content of the file.
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
