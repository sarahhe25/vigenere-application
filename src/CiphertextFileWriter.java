import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class CiphertextFileWriter implements CiphertextWriter {
    /**
     * Write ciphertext to file
     * create new file
     * @param filename file to write to
     * @param content the ciphertext to write into the file
     */
    @Override
    public void writeToFile(String filename, String content) {
        // create new file; overwrites existing files
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filename))) {
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
