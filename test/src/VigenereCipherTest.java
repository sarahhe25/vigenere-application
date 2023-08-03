import static org.junit.jupiter.api.Assertions.*;

import Entities.VigenereFactory;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import Entities.*;
import UseCase.*;

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
    public void testEncryption() {
        String plaintext = "HELLO, WORLD!";
        String key = "KEYLESS";
        Vigenere vigenere = vigenereFactory.createVigenere(plaintext, key);

        String expectedCiphertext = "RIJWS, OGBPB!";
        String actualCiphertext = encryptionUseCase.encrypt(vigenere);

        assertEquals(expectedCiphertext, actualCiphertext);
    }

    @Test
    public void testDecryption() {
        String ciphertext = "RIJWS, OGBPB!";
        String key = "KEYLESS";
        Vigenere vigenere = vigenereFactory.createVigenere(ciphertext, key);

        String expectedPlaintext = "HELLO, WORLD!";
        String actualPlaintext = decryptionUseCase.decrypt(vigenere);

        assertEquals(expectedPlaintext, actualPlaintext);
    }

    // tests for invalid user inputs is already handled in presenter with regex.
    // User will not be able to input invalid message or key.
    @Test
    public void testInvalidInput() {
        String message = "";
        String key = "";
        Vigenere vigenere = vigenereFactory.createVigenere(message, key);

        String expectedEncryptedText = "";
        String actualEncryptedText = encryptionUseCase.encrypt(vigenere);

        assertEquals(expectedEncryptedText, actualEncryptedText);

        String expectedDecryptedText = "";
        String actualDecryptedText = decryptionUseCase.decrypt(vigenere);
        assertEquals(expectedDecryptedText, actualDecryptedText);
    }

}
