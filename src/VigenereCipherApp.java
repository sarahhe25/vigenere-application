import Entities.VigenereFactory;
import UseCase.*;

public class VigenereCipherApp {

    public static void main(String[] args) {
        VigenereFactory vigenereFactory = new VigenereFactory();
        VigenereUseCaseFactory useCaseFactory = new VigenereUseCaseFactory();
        CiphertextWriter fileHandler = new SimpleCiphertextWriter();
        CiphertextReader ciphertextReader = new SimpleCiphertextReader();


        EncryptionBoundary encryptionUseCase = useCaseFactory.createEncryptionUseCase();
        DecryptionBoundary decryptionUseCase = useCaseFactory.createDecryptionUseCase();

        VigenereController vigenereController = new VigenereController(
                encryptionUseCase,
                decryptionUseCase,
                fileHandler,
                ciphertextReader,
                vigenereFactory
        );

        vigenereController.start();
    }
}
