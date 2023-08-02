public class Vigenere {
    private String message;
    private String key;

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
}
