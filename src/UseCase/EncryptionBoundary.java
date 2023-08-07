package UseCase;

import Entities.Vigenere;

/**
 * This interface defines the contract for encryption operations.
 * Implementing should provide the necessary logic to decrypt messages
 * using a specific decryption algorithm.
 */
public interface EncryptionBoundary {

    /**
     * Encrypts the given Vigenere cipher using a specific encryption algorithm.
     *
     * @param vigenere The Vigenere object containing the plaintext message and encryption key.
     * @return The encrypted ciphertext.
     */
    String encrypt(Vigenere vigenere);
}
