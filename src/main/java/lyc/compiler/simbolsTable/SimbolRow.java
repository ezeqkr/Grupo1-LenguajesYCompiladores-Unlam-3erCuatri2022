package lyc.compiler.simbolsTable;

public class SimbolRow {

    private String id;
    private String nombre;
    private String valor; 
    private Integer longitud;

    public SimbolRow(String nombre, String tipo, String valor, Integer longitud) {
        this.nombre = nombre;
        this.id = tipo;
        this.valor = valor;
        this.longitud = longitud;
    }

    public String getId() {
        if (id == null) {
            return "";
        }
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNombre() {
        if (nombre == null) {
            return "";
        }
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }


    public String getValor() {
        if (valor == null) {
            return "";
        }
        return valor; 
    }

    public void setValor(String valor) {
        this.valor = valor;
    }

    public Integer getLongitud() {
        return longitud;
    }

    public void setLongitud(Integer longitud) {
        this.longitud = longitud;
    }



}
