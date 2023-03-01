package lyc.compiler.files;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class IntermediateCodeGenerator implements FileGenerator {

    private ArrayList<String> lista = new ArrayList<String>();
    public IntermediateCodeGenerator(ArrayList<String> listaPolacaInversa){
        this.lista = listaPolacaInversa;
    }
    @Override
    public void generate(FileWriter fileWriter) throws IOException {

        try {
            int i = 1;

            for(String celda : lista){
                fileWriter.write(i + ". " + celda + "\n");
                i++;
            }
            i=1;
            for(String celda : lista){
                fileWriter.write("|" + celda);
                i++;
            }
            fileWriter.write("|");

        }catch(Exception e) {
            e.printStackTrace();
        }
    }
}
