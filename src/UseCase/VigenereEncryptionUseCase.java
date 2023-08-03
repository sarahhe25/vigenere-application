package UseCase;

import Entities.Vigenere;

public class VigenereEncryptionUseCase implements EncryptionBoundary {
    /**
     * vigenere encryption algorithm method
     * @param vigenere a vigenere object, contains message and key and static alphabet size
     * @return encrypted ciphertext String
     */
    @Override
    public String encrypt(Vigenere vigenere) {
        StringBuilder encryptedText = new StringBuilder();
        String key = vigenere.getKey().toUpperCase();
        String plaintext = vigenere.getMessage().toUpperCase();
        int keyIndex = 0;

        // Algorithm
        for (char c : plaintext.toCharArray()) {
            if (Character.isLetter(c)) {
                int shift = key.charAt(keyIndex) - 'A';
                char encryptedChar = (char) (((c - 'A' + shift) % Vigenere.getAlphabetSize()) + 'A');
                encryptedText.append(encryptedChar);
                keyIndex = (keyIndex + 1) % key.length();
            } else {
                encryptedText.append(c);
            }
        }

        return encryptedText.toString();
    }
}
