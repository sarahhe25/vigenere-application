package UseCase;

import Entities.Vigenere;

public interface EncryptionBoundary {
    String encrypt(Vigenere vigenere);
}
