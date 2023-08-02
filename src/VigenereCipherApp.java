import java.util.Scanner;

public class VigenereCipherApp {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        VigenereUseCaseFactory factory = new VigenereUseCaseFactory();
        CiphertextWriter ciphertextWriter = new SimpleCiphertextWriter();
        CiphertextReader reader = new SimpleCiphertextReader();

        while (true) {
            System.out.print("Enter 'encrypt' to encrypt, 'decrypt' to decrypt, or 'exit' to quit: ");
            String choice = scanner.nextLine().toLowerCase();

            if ("exit".equals(choice)) {
                break;
            } else if ("encrypt".equals(choice)) {
                EncryptionBoundary encryptor = factory.createEncryptionUseCase();
                VigenereController controller = new VigenereController(encryptor, null, ciphertextWriter, reader);
                controller.encryptAndPrint();
            } else if ("decrypt".equals(choice)) {
                DecryptionBoundary decryptor = factory.createDecryptionUseCase();
                VigenereController controller = new VigenereController(null, decryptor, ciphertextWriter, reader);
                controller.decryptAndPrint();
            } else {
                System.out.println("Invalid choice. Please choose 'encrypt', 'decrypt', or 'exit'.");
            }
        }

        System.out.println("Exiting. Thanks for using the Vigenere Cipher App!");
    }
}
