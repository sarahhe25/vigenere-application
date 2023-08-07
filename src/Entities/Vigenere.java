package Entities;

/**
 * Class representing a Vigenere Cipher Entity
 */
public class Vigenere {
    private String message;
    private String key;
    private static final int ALPHABET_SIZE = 26;

    /**
     * Constructor
     * @param message message to be encrypted or decrypted
     * @param key key used in the encryption / decryption process
     */
    public Vigenere(String message, String key) {
        this.message = message;
        this.key = key;
    }

    public String getMessage() {
        return message;
    }

    public String getKey() {
        return key;
    }

    public static int getAlphabetSize() {
        return ALPHABET_SIZE;
    }
}
