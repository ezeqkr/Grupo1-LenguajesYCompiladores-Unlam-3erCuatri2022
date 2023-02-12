package lyc.compiler.validations;

public class Validate {

    // falta generar metodo switch y arreglar los metodos con las funciones de java.

    public static void validateInt(char[] yytext) {
        String cad = yytext.toString();
        int entero = Integer.parseInt(cad);
        if (entero < -32768 || entero > 32767) {
            System.out.println("\nERROR LEXICO : Rango entero Invalido (16 bits con signo):" + cad + " \n");
            System.exit(0);
        }
    }

    public static void validateFloat(char[] yytext) {
        String cad = yytext.toString();
        float flotante = Float.parseFloat(cad);
        long cota_i = -2147483648;
        long cota_s = 2147483647;
        if (flotante <= cota_i || flotante >= cota_s) {
            System.out.println("\nERROR LEXICO : Rango reales Invalido (32 bits):" + cad + "%s\n");
            System.exit(0);
        }
    }

    public static void validateString(char[] yytext) {
        String cad = yytext.toString();
        int cadena = cad.length();
        if (cadena > 30) {
            System.out.println("\nERROR LEXICO : Longitud String Invalido ( 30 caracteres ):" + cad + " %s\n");
            System.exit(0);
        }
    }
}