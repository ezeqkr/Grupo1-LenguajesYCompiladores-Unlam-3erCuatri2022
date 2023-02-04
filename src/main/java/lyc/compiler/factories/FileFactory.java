package lyc.compiler.factories;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;


public final class  FileFactory {

    private FileFactory(){}
    public static BufferedReader create(String filename) throws IOException {
        return new BufferedReader(new FileReader(filename));
    }

}
