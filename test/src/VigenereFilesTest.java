import UI.CiphertextFileReader;
import UI.CiphertextFileWriter;
import UI.CiphertextReader;
import UI.CiphertextWriter;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

import java.io.*;
import java.nio.file.Path;

import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * JUnit tests for validating file operations related to ciphertext using the Vigenere cipher application.
 * This test suite covers scenarios such as reading from and writing to files, handling empty files,
 * and handling non-existing files. It ensures that the file handling components of the application
 * perform as expected under different circumstances.
 */
public class VigenereFilesTest {

    /**
     * Test reading content from a regular file.
     * @param tempDir Temporary directory provided by JUnit for testing
     * @throws IOException if an I/O error occurs during test execution
     */
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

    /**
     * Test writing content to a regular file.
     * @param tempDir Temporary directory provided by JUnit for testing
     * @throws IOException if an I/O error occurs during test execution
     */
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

    /**
     * Test reading from an empty file.
     * @param tempDir Temporary directory provided by JUnit for testing
     * @throws IOException if an I/O error occurs during test execution
     */
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

    /**
     * Test reading from a non-existing file.
     * @throws IOException if an I/O error occurs during test execution
     */
    @Test
    public void testReadFromFile_FileNotFound() throws IOException {
        String nonExistingFile = "nonExistingFile.txt";
        CiphertextReader reader = new CiphertextFileReader();
        String content = reader.readFromFile(nonExistingFile);
        assertEquals("", content); // Or assert null if the method returns null for file not found.
    }

    /**
     * Test writing empty content to a file.
     * @param tempDir Temporary directory provided by JUnit for testing
     * @throws IOException if an I/O error occurs during test execution
     */
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