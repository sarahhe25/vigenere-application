package UI;

import java.io.IOException;

public interface CiphertextReader {
    String readFromFile(String filename) throws IOException;
}