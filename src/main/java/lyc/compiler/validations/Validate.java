package lyc.compiler.validations;

public class Validate {

    public static void validateInt(String yytext) {
        int entero = Integer.parseInt(yytext);
        if (entero < -32768 || entero > 32767) {
            System.out.println("\nERROR LEXICO : Rango entero Invalido (16 bits con signo):" + yytext + " \n");
            System.exit(0);
        }
    }

    public static void validateFloat(String yytext) {
        float flotante = Float.parseFloat(yytext);
        long cota_i = -2147483648;
        long cota_s = 2147483647;
        if (flotante <= cota_i || flotante >= cota_s) {
            System.out.println("\nERROR LEXICO : Rango reales Invalido (32 bits):" + yytext + "%s\n");
            System.exit(0);
        }
    }

    public static void validateString(String yytext) {
        int cadena = yytext.length();
        if (cadena > 30) {
            System.out.println("\nERROR LEXICO : Longitud String Invalido ( 30 caracteres ):" + yytext + " %s\n");
            System.exit(0);
        }
    }
}