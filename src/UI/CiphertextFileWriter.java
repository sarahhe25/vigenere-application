package UI;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

/**
 * Implements the CiphertextWriter interface to write ciphertext to a new file.
 */
public class CiphertextFileWriter implements CiphertextWriter {

    private final String OUTPUT_PATH = "./output/";
    /**
     * Write ciphertext to file
     * create new file
     * @param filename file to write to
     * @param content the ciphertext to write into the file
     */
    @Override
    public void writeToFile(String filename, String content) {
        // create new file; overwrites existing files
        String strFullPath = OUTPUT_PATH + "/" + filename;
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(strFullPath))) {
            // Split the content into lines
            String[] lines = content.split(System.lineSeparator());
            // Write each line to the file
            for (int i = 0; i < lines.length; i++) {
                writer.write(lines[i]);
                if (i < lines.length - 1) {
                    writer.newLine(); // Append a new line after each line (except the last line)
                }
            }
        } catch (IOException e) {
            System.out.println("Error saving ciphertext to file: " + e.getMessage());
        }
    }

}
