package lyc.compiler.files;

import java.io.FileWriter;
import java.io.IOException;

import lyc.compiler.simbolsTable.SimbolTable;

public class SymbolTableGenerator implements FileGenerator{

    @Override
    public void generate(FileWriter fileWriter) throws IOException {
        SimbolTable simbolTable = SimbolTable.getSingletonInstance();
        fileWriter.write(simbolTable.toString());
    }
}
