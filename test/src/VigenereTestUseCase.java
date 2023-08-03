import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class VigenereUseCaseTest {
    @Test
    void testVigenereEncryption() {
        VigenereEncryptionUseCase encryptionUseCase = new VigenereEncryptionUseCase();

        // Test case: Basic encryption with key "KEY"
        String message = "HELLO";
        String key = "KEY";
        Vigenere vigenere = new Vigenere(message, key);
        String expectedCiphertext = "RIJVSUYVJN";
        String actualCiphertext = encryptionUseCase.encrypt(vigenere);
        assertEquals(expectedCiphertext, actualCiphertext);

        // Test case: Uppercase and lowercase characters in message and key
        message = "Hello";
        key = "key";
        vigenere = new Vigenere(message, key);
        expectedCiphertext = "Rijvsuyvjn";
        actualCiphertext = encryptionUseCase.encrypt(vigenere);
        assertEquals(expectedCiphertext, actualCiphertext);

        // Test case: Empty message and key should return an empty ciphertext
        message = "";
        key = "KEY";
        vigenere = new Vigenere(message, key);
        expectedCiphertext = "";
        actualCiphertext = encryptionUseCase.encrypt(vigenere);
        assertEquals(expectedCiphertext, actualCiphertext);
    }

    @Test
    void testVigenereDecryption() {
        VigenereDecryptionUseCase decryptionUseCase = new VigenereDecryptionUseCase();

        // Test case: Basic decryption with key "KEY"
        String ciphertext = "RIJVSUYVJN";
        String key = "KEY";
        Vigenere vigenere = new Vigenere(ciphertext, key);
        String expectedPlaintext = "HELLO";
        String actualPlaintext = decryptionUseCase.decrypt(vigenere);
        assertEquals(expectedPlaintext, actualPlaintext);

        // Test case: Uppercase and lowercase characters in ciphertext and key
        ciphertext = "Rijvsuyvjn";
        key = "key";
        vigenere = new Vigenere(ciphertext, key);
        expectedPlaintext = "Hello";
        actualPlaintext = decryptionUseCase.decrypt(vigenere);
        assertEquals(expectedPlaintext, actualPlaintext);

        // Test case: Empty ciphertext and key should return an empty plaintext
        ciphertext = "";
        key = "KEY";
        vigenere = new Vigenere(ciphertext, key);
        expectedPlaintext = "";
        actualPlaintext = decryptionUseCase.decrypt(vigenere);
        assertEquals(expectedPlaintext, actualPlaintext);
    }
}
