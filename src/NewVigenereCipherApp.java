public class NewVigenereCipherApp {
    public static void main(String[] args) {
        VigenereUseCaseFactory factory = new VigenereUseCaseFactory();
        CiphertextWriter fileHandler = new SimpleCiphertextWriter();
        CiphertextReader ciphertextReader = new SimpleCiphertextReader();

        EncryptionBoundary encryptionUseCase = factory.createEncryptionUseCase();
        DecryptionBoundary decryptionUseCase = factory.createDecryptionUseCase();

        NewVigenereController vigenereController = new NewVigenereController(
                encryptionUseCase,
                decryptionUseCase,
                fileHandler,
                ciphertextReader
        );

        vigenereController.start();
    }
}
