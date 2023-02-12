package lyc.compiler.simbolsTable;
import java.util.ArrayList;

public class SimbolTable {
  ArrayList<SimbolRow> simbols;
  private static SimbolTable simbolTable;

  // El constructor es privado, no permite que se genere un constructor por defecto.
  private SimbolTable() {
      this.simbols = new ArrayList<SimbolRow>(); 
      System.out.println("Inicializando Tabla de Simbolos!");
  }

  public static SimbolTable getSingletonInstance() {
      if (simbolTable == null){
          simbolTable = new SimbolTable();
      }
      
      return simbolTable;
  }

    // Getter
  public String getList() {
    return "this.simbols";
  }

  // Setter
  public void setSimbol(SimbolRow simbolRow) {
    this.simbols.add(simbolRow);
  }

  public void print() {    
    int stride = this.simbols.size() / 2;
      for (int row = 0; row < this.simbols.size() / 2; row++) {
        System.out.println(
          String.format("%20s %20s", 
          this.simbols.get(row).getId(),
          this.simbols.get(row + stride).getNombre()));
      }
  }

  @Override
  public String toString() {
    String result = "";
    int stride = this.simbols.size() / 2;
    for (int row = 0; row < this.simbols.size() / 2; row++) {
      result += 
        String.format("%20s %20s\n", 
        this.simbols.get(row).getId(),
        this.simbols.get(row + stride).getNombre());
    }
    return result;
  }
}