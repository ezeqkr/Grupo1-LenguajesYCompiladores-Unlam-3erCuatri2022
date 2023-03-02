package lyc.compiler.asm;

import lyc.compiler.simbolsTable.SimbolRow;

import java.util.*;

public class GestorAssembler {

    ArrayList<String> instrucciones = new ArrayList<String>();


    public GestorAssembler(){
    }

    public void generarAssembler(List<SimbolRow> TablaDeSimbolos , ArrayList<String> polacaInversa){

        Stack<String> pilaOperandos  = new Stack<String>();
        Queue<String> colaEtiquetas  = new LinkedList<String>();
        Stack<Integer> pilaNroCelda = new Stack<Integer>();

        ArrayList<String> codigoProgramador = new ArrayList<String>();


        instrucciones.add("-------------- INICIO DE CODIGO ASSEMBLER ---------------");
        instrucciones.add(".MODEL LARGE");
        instrucciones.add(".386");
        instrucciones.add(".STACK 200h\n");

        ///tabla de simbolos
        instrucciones.add(".DATA\n");
        for(SimbolRow simbolo : TablaDeSimbolos){
            instrucciones.add(String.format("%-20s %-5s %-30s", simbolo.getNombre(),"dd", simbolo.getValor()));













            System.out.println("nombre" + simbolo.getNombre() + "valor" + simbolo.getValor());













        }

        //cabecera de instrucciones
        instrucciones.add("\n.CODE");
        instrucciones.add("\nMOV AX, @DATA");
        instrucciones.add("MOV DS, AX");
        instrucciones.add("MOV ES, AX\n");


        //codigo del programador
        int cantVariablesAuxiliares = 0;
        int cantEtiquetas = 0;
        int nroCelda = 1;
        for(String celda : polacaInversa){

            if(!pilaNroCelda.isEmpty() && nroCelda == pilaNroCelda.peek()){
                codigoProgramador.add(colaEtiquetas.remove() + ":");
                pilaNroCelda.pop();
            }
            switch (celda){
                case ":=":
                {
                    String op2 = pilaOperandos.pop();
                    String op1 = pilaOperandos.pop();
                    codigoProgramador.add("FLD " + op1);
                    codigoProgramador.add("FSTP " + op2);
                    codigoProgramador.add("");
                    break;
                }
                case "+":
                {
                    String op2 = pilaOperandos.pop();
                    String op1 = pilaOperandos.pop();
                    String varAux = "@aux" + (cantVariablesAuxiliares+1);
                    cantVariablesAuxiliares++;
                    codigoProgramador.add("FLD " + op1);
                    codigoProgramador.add("FLD " + op2);
                    codigoProgramador.add("FADD");
                    codigoProgramador.add("FSTP " + varAux);
                    codigoProgramador.add("");
                    pilaOperandos.add(varAux);
                    break;
                }
                case "-":
                {
                    String op2 = pilaOperandos.pop();
                    String op1 = pilaOperandos.pop();
                    String varAux = "@aux" + (cantVariablesAuxiliares+1);
                    cantVariablesAuxiliares++;
                    codigoProgramador.add("FLD " + op1);
                    codigoProgramador.add("FLD " + op2);
                    codigoProgramador.add("FSUB");
                    codigoProgramador.add("FSTP " + varAux);
                    codigoProgramador.add("");
                    pilaOperandos.add(varAux);
                    break;
                }
                case "/":
                {
                    String op2 = pilaOperandos.pop();
                    String op1 = pilaOperandos.pop();
                    String varAux = "@aux" + (cantVariablesAuxiliares+1);
                    cantVariablesAuxiliares++;
                    codigoProgramador.add("FLD " + op1);
                    codigoProgramador.add("FLD " + op2);
                    codigoProgramador.add("FDIV");
                    codigoProgramador.add("FSTP " + varAux);
                    codigoProgramador.add("");
                    pilaOperandos.add(varAux);
                    break;
                }
                case "*":
                {
                    String op2 = pilaOperandos.pop();
                    String op1 = pilaOperandos.pop();
                    String varAux = "@aux" + (cantVariablesAuxiliares+1);
                    cantVariablesAuxiliares++;
                    codigoProgramador.add("FLD " + op1);
                    codigoProgramador.add("FLD " + op2);
                    codigoProgramador.add("FMUL");
                    codigoProgramador.add("FSTP " + varAux);
                    codigoProgramador.add("");
                    pilaOperandos.add(varAux);
                    break;
                }
                case "CMP":
                {
                    String op2 = pilaOperandos.pop();
                    String op1 = pilaOperandos.pop();
                    codigoProgramador.add("FLD " + op1);
                    codigoProgramador.add("FLD " + op2);
                    codigoProgramador.add("FXCH");
                    codigoProgramador.add("FCOMP");
                    codigoProgramador.add("FSTSW AX");
                    codigoProgramador.add("SAHF");
                    break;
                }
                case "BLE":
                case "BGE":
                case "BLT":
                case "BGT":
                case "BEQ":
                case "BNE":
                {
                    String etiqueta = "etiqueta" + (cantEtiquetas+1);
                    cantEtiquetas++;
                    colaEtiquetas.add(etiqueta);
                    codigoProgramador.add(celda + " " + etiqueta);
                    codigoProgramador.add("");
                    break;
                }
                case "BI":
                {
                    codigoProgramador.add("BI " + colaEtiquetas.remove());
                    codigoProgramador.add("");
                    break;
                }
                case "ET":
                {
                    String etiqueta = "etiqueta" + (cantEtiquetas+1);
                    cantEtiquetas++;
                    colaEtiquetas.add(etiqueta);
                    codigoProgramador.add(etiqueta + ":");
                    codigoProgramador.add("");
                    break;
                }
                default: {
                    if(celda.startsWith("#")){
                        int nroCeldaSalto = Integer.parseInt(celda.substring(1));
                        if(nroCeldaSalto >= nroCelda){
                            pilaNroCelda.add(nroCeldaSalto);
//                            System.out.println("STACKKKKKKKKKKK " + nroCeldaSalto);
                        }
                    }
                    else{
                        pilaOperandos.add(celda);
                    }
                    break;
                }
            }
            nroCelda++;
        }

        ///hago esto para que no se me rompa la logica agregando el codigo de la cabecera y etc
        for(String instruccion : codigoProgramador){
            instrucciones.add(instruccion);
        }

//        System.out.println("**************** MOSTRANDO POLACA /**********************");
//        int i = 1;
//        for(String celda : polacaInversa){
//            System.out.println(i + " - " + celda);
//            i++;
//        }
//        System.out.println("**************** FIN DE LA POLACA /**********************");

//        System.out.println("**************** MOSTRANDO PILA /**********************");
//        while(!pilaOperandos.isEmpty()){
//            System.out.println(pilaOperandos.pop());
//        }
//        System.out.println("**************** FIN DE LA PILA /**********************");

        //pie de fin de codigo
        instrucciones.add("\nMOV AX, 4C00h");
        instrucciones.add("INT 21h");
        instrucciones.add("END");

        instrucciones.add("-------------- FIN DE CODIGO ASSEMBLER ---------------");


        ///mostrando TODAS las instrucciones (incluye cabecera y etc)
        for(String instruccion : instrucciones){
            System.out.println(instruccion);

        }
    }

    public ArrayList<String> getInstrucciones(){
        return instrucciones;
    }


}
