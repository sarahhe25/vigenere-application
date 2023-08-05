import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

import java.io.*;
import java.nio.file.Path;

import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class VigenereFilesTest {

    // test regular read from file
    @Test
    public void testReadFromFile(@TempDir Path tempDir) throws IOException {
        // Arrange
        String expectedContent = "This is a test ciphertext.\nSecond Line\n";
        File tempFile = tempDir.resolve("testFile.txt").toFile();

        try (FileWriter writer = new FileWriter(tempFile)) {
            writer.write(expectedContent);
        }

        CiphertextReader reader = new CiphertextFileReader();

        String actualContent = reader.readFromFile(tempFile.getAbsolutePath());
        assertEquals(expectedContent, actualContent);
    }

    // test regular write to file
    @Test
    public void testWriteToFile(@TempDir Path tempDir) throws IOException {
        // Arrange
        String content = "This is line 1." + System.lineSeparator() + "This is line 2.";
        File tempFile = tempDir.resolve("testFile.txt").toFile();

        CiphertextWriter writer = new CiphertextFileWriter();
        writer.writeToFile(tempFile.getAbsolutePath(), content);

        assertTrue(tempFile.exists());

        try (BufferedReader fileReader = new BufferedReader(new FileReader(tempFile))) {
            String line1 = fileReader.readLine();
            String line2 = fileReader.readLine();
            assertEquals("This is line 1.", line1);
            assertEquals("This is line 2.", line2);
        }
    }

    @Test
    public void testReadFromFile_EmptyFile(@TempDir Path tempDir) throws IOException {
        File tempFile = tempDir.resolve("emptyFile.txt").toFile();
        tempFile.createNewFile();

        CiphertextReader reader = new CiphertextFileReader();

        // Assert that the method handles the empty file and returns an empty string.
        String content = reader.readFromFile(tempFile.getAbsolutePath());

        assertEquals("", content);
        tempFile.delete();
    }

    @Test
    public void testReadFromFile_FileNotFound() throws IOException {
        String nonExistingFile = "nonExistingFile.txt";
        CiphertextReader reader = new CiphertextFileReader();
        String content = reader.readFromFile(nonExistingFile);
        assertEquals("", content); // Or assert null if the method returns null for file not found.
    }

    @Test
    public void testWriteToFile_EmptyContent(@TempDir Path tempDir) throws IOException {
        String emptyContent = "";
        File tempFile = tempDir.resolve("emptyFile.txt").toFile();

        CiphertextWriter writer = new CiphertextFileWriter();
        writer.writeToFile(tempFile.getAbsolutePath(), emptyContent);

        assertTrue(tempFile.exists());
        assertEquals(0, tempFile.length());
        tempFile.delete();
    }
}