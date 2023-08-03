package Entities;

import Entities.Vigenere;

public class VigenereFactory {
    public Vigenere createVigenere(String message, String key) {
        return new Vigenere(message, key);
    }
}
