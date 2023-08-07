import Entities.Vigenere;
import Entities.VigenereFactory;
import UseCase.VigenereDecryptionUseCase;
import UseCase.VigenereEncryptionUseCase;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.Assert.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * JUnit tests for verifying the correctness of Vigenere cipher encryption and decryption functionality.
 * This test suite covers scenarios related to VigenereFactory object creation, encryption algorithm,
 * and decryption algorithm. The tests ensure that the encryption and decryption processes produce the
 * expected results for various inputs and edge cases.
 */
public class VigenereCipherTest {

    private VigenereEncryptionUseCase encryptionUseCase;
    private VigenereDecryptionUseCase decryptionUseCase;
    private VigenereFactory vigenereFactory;

    /**
     * Set up the necessary components for each test case.
     * Initialize encryption and decryption use cases along with the VigenereFactory.
     */
    @BeforeEach
    public void setUp() {
        encryptionUseCase = new VigenereEncryptionUseCase();
        decryptionUseCase = new VigenereDecryptionUseCase();
        vigenereFactory = new VigenereFactory();
    }

    /**
     * Test the creation of a Vigenere object using the VigenereFactory.
     * Ensures that the created Vigenere object contains the expected message and key.
     */
    @Test
    public void testCreateVigenere() {
        // Arrange
        String message = "HELLO";
        String key = "KEY";

        // Act
        VigenereFactory vigenereFactory = new VigenereFactory();
        Vigenere vigenere = vigenereFactory.createVigenere(message, key);

        // Assert
        assertNotNull(vigenere);
        assertEquals(message, vigenere.getMessage());
        assertEquals(key, vigenere.getKey());
    }

    /**
     * Test the encryption algorithm of the Vigenere cipher.
     * Compares the output ciphertext with the expected result for a given plaintext and key.
     */
    @Test
    public void testEncryption() {
        String plaintext = "HELLO";
        String key = "KEY";
        Vigenere vigenere = vigenereFactory.createVigenere(plaintext, key);

        String expectedCiphertext = "RIJVS";
        String actualCiphertext = encryptionUseCase.encrypt(vigenere);

        assertEquals(expectedCiphertext, actualCiphertext);
    }

    /**
     * Test the decryption algorithm of the Vigenere cipher.
     * Compares the output decrypted text with the expected result for a given ciphertext and key.
     */
    @Test
    public void testDecryption() {
        String ciphertext = "RIJVS";
        String key = "KEY";
        Vigenere vigenere = vigenereFactory.createVigenere(ciphertext, key);

        String expectedPlaintext = "HELLO";
        String actualPlaintext = decryptionUseCase.decrypt(vigenere);

        assertEquals(expectedPlaintext, actualPlaintext);
    }

    // test cases for invalid user inputs are not necessary
    // because presenter uses regex to handle invalid inputs and will keep prompting until valid input is entered
    // still include one edge case to test algorithm

    /**
     * Test an edge case with empty input for both plaintext and key.
     * Ensures that an empty input does not affect the encryption algorithm.
     */
    @Test
    public void testEmptyInput() {
        String plaintext = " ";
        String key = "";
        Vigenere vigenere = vigenereFactory.createVigenere(plaintext, key);

        String expectedCiphertext = plaintext; // Empty key does not affect encryption
        String actualCiphertext = encryptionUseCase.encrypt(vigenere);

        assertEquals(expectedCiphertext, actualCiphertext);
    }

}
