package lyc.compiler.validations;

import lyc.compiler.simbolsTable.DataType;

import javax.xml.crypto.Data;

public class Validate {

    final static int rangoString = 40;	
    final static int rangoIdentifier = 30;	

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
            System.out.println("\nERROR LEXICO : Rango reales Invalido (32 bits):" + yytext + "\n");
            System.exit(0);
        }
    }

    public static void validateString(String yytext) {
        int cadena = yytext.length();
        if (cadena > rangoString - 2 ) {
            System.out.println("\nERROR LEXICO : Longitud String Invalido ( 40 caracteres ):" + yytext + "\n");
            System.exit(0);
        }
    }
    
    public static void validateIdentifier(String yytext) {
        int cadena = yytext.length();
        if (cadena > rangoIdentifier - 2 ) {
            System.out.println("\nERROR LEXICO : Longitud de Identificador Invalido ( 30 caracteres ):" + yytext + "\n");
            System.exit(0);
        }
    }

    public static void validateTypes(DataType tipo1, DataType tipo2) throws Exception {
        if( tipo1 != tipo2) {
            throw new Exception("Error: tipos de datos no coinciden");
        }
    }

}