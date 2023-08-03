package UseCase;

import UseCase.VigenereDecryptionUseCase;
import UseCase.VigenereEncryptionUseCase;

public class VigenereUseCaseFactory {
    public EncryptionBoundary createEncryptionUseCase() {
        return new VigenereEncryptionUseCase();
    }

    public DecryptionBoundary createDecryptionUseCase() {
        return new VigenereDecryptionUseCase();
    }
}
