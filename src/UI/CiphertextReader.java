package UI;

import java.io.IOException;

/**
 * The CiphertextReader interface defines the contract for reading ciphertext from a file.
 */
public interface CiphertextReader {

    /**
     * abstract method to be implemented
     * reads ciphertext from a file and returns it as a string.
     * @param filename The name of the file to read from.
     * @return A string containing the content of the file.
     * @throws IOException If an I/O error occurs while reading the file.
     */
    String readFromFile(String filename) throws IOException;
}
