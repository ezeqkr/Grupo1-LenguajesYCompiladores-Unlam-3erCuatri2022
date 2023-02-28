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

            BufferedWriter bw = null;
            try {

                bw = new BufferedWriter(fileWriter);
                int i = 1;

                for(String celda : lista){
                    bw.write(i + ". " + celda + "\n");
                    i++;
                }
                i=1;
                for(String celda : lista){
                    bw.write("|" + celda);
                    i++;
                }
                bw.write("|");

            }catch(Exception e) {
                e.printStackTrace();
            }
            finally {
                bw.close();
            }

    }
}
