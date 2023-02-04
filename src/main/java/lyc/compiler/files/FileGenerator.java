package lyc.compiler.files;

import java.io.FileWriter;
import java.io.IOException;

public interface FileGenerator {

    void generate(FileWriter fileWriter) throws IOException;

}
