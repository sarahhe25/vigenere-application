import java.io.FileWriter;
import java.io.IOException;

public class SimpleCiphertextWriter implements CiphertextWriter {
    @Override
    public void writeToFile(String filename, String content) {
        try (FileWriter writer = new FileWriter(filename)) {
            writer.write(content);
            System.out.println("Ciphertext saved to file: " + filename);
        } catch (IOException e) {
            System.out.println("Error saving ciphertext to file: " + e.getMessage());
        }
    }
}
