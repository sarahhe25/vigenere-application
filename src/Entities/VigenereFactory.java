package Entities;

import Entities.Vigenere;

public class VigenereFactory {
    /**
     * @param message
     * @param key
     * @return
     */
    public Vigenere createVigenere(String message, String key) {
        return new Vigenere(message, key);
    }
}
