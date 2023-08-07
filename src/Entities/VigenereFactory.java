package Entities;

/**
 * The VigenereFactory class is responsible for creating instances of the Vigenere class,
 * which represents a Vigenere cipher with a specific message and key.
 */
public class VigenereFactory {

    /**
     * Creates a vigenere object from user input
     * @param message user input message
     * @param key user input key
     * @return a vigenere object
     */
    public Vigenere createVigenere(String message, String key) {
        return new Vigenere(message, key);
    }
}
