public class Main {
    private static final int ALPHABET_SIZE = 26;

    public static String encrypt(String plaintext, String key) {
        StringBuilder encryptedText = new StringBuilder();
        int keyLength = key.length();
        int keyIndex = 0;

        for (char c : plaintext.toCharArray()) {
            if (Character.isLetter(c)) {
                char base = Character.isUpperCase(c) ? 'A' : 'a';
                int shift = key.charAt(keyIndex) - 'A';
                int charIndex = c - base;
                int encryptedCharIndex = (charIndex + shift) % ALPHABET_SIZE;
                char encryptedChar = (char) (base + encryptedCharIndex);
                encryptedText.append(encryptedChar);

                keyIndex = (keyIndex + 1) % keyLength;
            } else {
                encryptedText.append(c); // Non-letter characters are left unchanged
            }
        }

        return encryptedText.toString();
    }

    public static String decrypt(String encryptedText, String key) {
        StringBuilder decryptedText = new StringBuilder();
        int keyLength = key.length();
        int keyIndex = 0;

        for (char c : encryptedText.toCharArray()) {
            if (Character.isLetter(c)) {
                char base = Character.isUpperCase(c) ? 'A' : 'a';
                int shift = key.charAt(keyIndex) - 'A';
                int charIndex = c - base;
                int decryptedCharIndex = (charIndex - shift + ALPHABET_SIZE) % ALPHABET_SIZE;
                char decryptedChar = (char) (base + decryptedCharIndex);
                decryptedText.append(decryptedChar);

                keyIndex = (keyIndex + 1) % keyLength;
            } else {
                decryptedText.append(c); // Non-letter characters are left unchanged
            }
        }

        return decryptedText.toString();
    }

    public static void main(String[] args) {
        String plaintext = "Hello, World!";
        String key = "KEY";

        System.out.println("Original: " + plaintext);

        String encryptedText = encrypt(plaintext, key);
        System.out.println("Encrypted: " + encryptedText);

        String decryptedText = decrypt(encryptedText, key);
        System.out.println("Decrypted: " + decryptedText);
    }
}
