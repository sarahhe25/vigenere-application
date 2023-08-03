package UseCase;

import Entities.Vigenere;

public class VigenereDecryptionUseCase implements DecryptionBoundary {
    /**
     * Vigenere decryption algorithm method
     * @param vigenere
     * @return plaintext in String
     */
    @Override
    public String decrypt(Vigenere vigenere) {
        StringBuilder decryptedText = new StringBuilder();
        String ciphertext = vigenere.getMessage().toUpperCase();
        String key = vigenere.getKey().toUpperCase();
        int keyIndex = 0;

        for (char c : ciphertext.toCharArray()) {
            if (Character.isLetter(c)) {
                int shift = key.charAt(keyIndex) - 'A';
                char decryptedChar = (char) (((c - 'A' - shift + 26) % 26) + 'A');
                decryptedText.append(decryptedChar);
                keyIndex = (keyIndex + 1) % key.length();
            } else {
                decryptedText.append(c);
            }
        }

        return decryptedText.toString();

    }
}
