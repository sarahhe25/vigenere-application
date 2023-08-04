import Entities.Vigenere;
import Entities.VigenereFactory;
import UseCase.VigenereDecryptionUseCase;
import UseCase.VigenereEncryptionUseCase;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.Assert.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertEquals;

// test use case algorithms and vigenerefactory object creation
public class VigenereCipherTest {

    private VigenereEncryptionUseCase encryptionUseCase;
    private VigenereDecryptionUseCase decryptionUseCase;
    private VigenereFactory vigenereFactory;

    @BeforeEach
    public void setUp() {
        encryptionUseCase = new VigenereEncryptionUseCase();
        decryptionUseCase = new VigenereDecryptionUseCase();
        vigenereFactory = new VigenereFactory();
    }

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

    @Test
    public void testEncryption() {
        String plaintext = "HELLO";
        String key = "KEY";
        Vigenere vigenere = vigenereFactory.createVigenere(plaintext, key);

        String expectedCiphertext = "RIJVS";
        String actualCiphertext = encryptionUseCase.encrypt(vigenere);

        assertEquals(expectedCiphertext, actualCiphertext);
    }

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
