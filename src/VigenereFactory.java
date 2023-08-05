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
