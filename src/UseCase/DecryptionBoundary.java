package UseCase;

import Entities.Vigenere;

/**
 * The DecryptionBoundary interface defines the contract for decryption operations.
 * Implementing classes should provide the necessary logic to decrypt messages
 * using a specific decryption algorithm.
 */
public interface DecryptionBoundary {

    /**
     * Decrypts the given Vigenere cipher using a specific decryption algorithm.
     *
     * @param vigenere The Vigenere object containing the ciphertext and decryption key.
     * @return The decrypted message.
     */
    String decrypt(Vigenere vigenere);
}
