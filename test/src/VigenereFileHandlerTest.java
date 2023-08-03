import UI.CiphertextReader;
import UI.CiphertextWriter;
import UI.VigenereFileHandler;
import static org.mockito.Mockito.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;

public class VigenereFileHandlerTest {

    private VigenereFileHandler fileHandler;
    private CiphertextWriter mockWriter;
    private CiphertextReader mockReader;

    @BeforeEach
    public void setUp() {
        mockWriter = mock(CiphertextWriter.class);
        mockReader = mock(CiphertextReader.class);
        fileHandler = new VigenereFileHandler(mockWriter, mockReader);
    }

    @Test
    public void testSaveCiphertextToFile() throws IOException {
        String filename = "test_file.txt";
        String ciphertext = "RIJVSU";

        fileHandler.saveCiphertextToFile(filename, ciphertext);

        verify(mockWriter).writeToFile(eq(filename), eq(ciphertext));
    }

    @Test
    public void testReadCiphertextFromFile() throws IOException {
        String filename = "test_file.txt";
        String expectedCiphertext = "RIJVSU";

        when(mockReader.readFromFile(eq(filename))).thenReturn(expectedCiphertext);

        String actualCiphertext = fileHandler.readCiphertextFromFile(filename);

        assertEquals(expectedCiphertext, actualCiphertext);
        verify(mockReader).readFromFile(eq(filename));
    }

    @Test
    public void testReadCiphertextFromNonExistentFile() throws IOException {
        String filename = "non_existent_file.txt";

        when(mockReader.readFromFile(eq(filename))).thenReturn("");

        String actualCiphertext = fileHandler.readCiphertextFromFile(filename);

        assertEquals("", actualCiphertext);
        verify(mockReader).readFromFile(eq(filename));
    }

    @Test
    public void testReadCiphertextFromFileError() throws IOException {
        String filename = "error_file.txt";

        when(mockReader.readFromFile(eq(filename))).thenThrow(new IOException("File read error"));

        assertThrows(IOException.class, () -> fileHandler.readCiphertextFromFile(filename));
    }
}
