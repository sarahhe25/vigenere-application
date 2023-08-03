package UseCase;

import Entities.Vigenere;

public interface DecryptionBoundary {
    String decrypt(Vigenere vigenere);
}
