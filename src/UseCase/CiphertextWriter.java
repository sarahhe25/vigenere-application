package UseCase;

import java.io.IOException;

public interface CiphertextWriter {
    void writeToFile(String content, String filename) throws IOException;
}
