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
    public void testEncryptMessage() {
        String plaintext = "HELLO";
        String key = "KEY";
        Vigenere vigenere = vigenereFactory.createVigenere(plaintext, key);

        String expectedCiphertext = "RIJVSU";
        String actualCiphertext = encryptionUseCase.encrypt(vigenere);

        assertEquals(expectedCiphertext, actualCiphertext);
    }

    @Test
    public void testDecryptMessage() {
        String ciphertext = "RIJVSU";
        String key = "KEY";
        Vigenere vigenere = vigenereFactory.createVigenere(ciphertext, key);

        String expectedPlaintext = "HELLO";
        String actualPlaintext = decryptionUseCase.decrypt(vigenere);

        assertEquals(expectedPlaintext, actualPlaintext);
    }

    @Test
    public void testEmptyKey() {
        String plaintext = "HELLO";
        String key = "";
        Vigenere vigenere = vigenereFactory.createVigenere(plaintext, key);

        String expectedCiphertext = plaintext; // Empty key does not affect encryption
        String actualCiphertext = encryptionUseCase.encrypt(vigenere);

        assertEquals(expectedCiphertext, actualCiphertext);
    }

    @Test
    public void testInvalidCharacterInKey() {
        String plaintext = "HELLO";
        String key = "KEY!";
        Vigenere vigenere = vigenereFactory.createVigenere(plaintext, key);

        assertThrows(IllegalArgumentException.class, () -> encryptionUseCase.encrypt(vigenere));
    }

    @Test
    public void testDecryptInvalidCiphertext() {
        String invalidCiphertext = "RIJVSU!";
        String key = "KEY";
        Vigenere vigenere = vigenereFactory.createVigenere(invalidCiphertext, key);

        assertThrows(IllegalArgumentException.class, () -> decryptionUseCase.decrypt(vigenere));
    }
}
