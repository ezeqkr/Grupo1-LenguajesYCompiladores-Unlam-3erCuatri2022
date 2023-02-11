package lyc.compiler.simbolsTable;

public class SimbolRow {

    private String id;
    private String nombre;
    private String valor; 
    private Integer longitud;

    public SimbolRow(String id, String nombre, String valor, Integer longitud) {
        this.id = id;
        this.nombre = nombre;
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
        if (longitud == null) {
            return longitud;
        }
        return longitud;
    }

    public void setLongitud(Integer longitud) {
        this.longitud = longitud;
    }
}
