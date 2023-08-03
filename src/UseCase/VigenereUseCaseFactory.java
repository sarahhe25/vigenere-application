package UseCase;

public class VigenereUseCaseFactory {
    public EncryptionBoundary createEncryptionUseCase() {
        return new VigenereEncryptionUseCase();
    }

    public DecryptionBoundary createDecryptionUseCase() {
        return new VigenereDecryptionUseCase();
    }
}
