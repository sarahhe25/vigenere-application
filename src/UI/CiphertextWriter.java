package UI;

import java.io.IOException;

/**
 * The CiphertextWriter interface defines the contract for writing ciphertext to a file.
 */
public interface CiphertextWriter {

    /**
     * Abstract method to be implemented
     * Writes ciphertext content to a file.
     * @param content  The ciphertext content to be written.
     * @param filename The name of the file to write to.
     * @throws IOException If an I/O error occurs while writing the file.
     */
    void writeToFile(String content, String filename) throws IOException;
}
