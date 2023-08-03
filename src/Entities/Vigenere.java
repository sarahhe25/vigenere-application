package Entities;

/**
 * Class representing Vigenere Cipher Entities
 */
public class Vigenere {
    private String message;
    private String key;
    private static final int ALPHABET_SIZE = 26;

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

    public int getAlphabetSize() {
        return ALPHABET_SIZE;
    }
}
