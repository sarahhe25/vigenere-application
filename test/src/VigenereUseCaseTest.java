import Entities.*;
import UseCase.*;
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
        String expectedCiphertext = "RIJVS";
        String actualCiphertext = encryptionUseCase.encrypt(vigenere);
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
    }
}
