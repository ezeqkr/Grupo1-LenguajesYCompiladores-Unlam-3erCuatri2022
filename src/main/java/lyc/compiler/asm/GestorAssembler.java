package lyc.compiler.asm;

import lyc.compiler.simbolsTable.SimbolRow;

import java.util.*;

public class GestorAssembler {

    ArrayList<String> listInst = new ArrayList<String>();

    public GestorAssembler(){
    }

    public void generarAssembler(List<SimbolRow> TablaDeSimbolos , ArrayList<String> polacaInversa){

        Stack<String> pilaOperandos  = new Stack<String>();
        Queue<String> colaEtiquetas  = new LinkedList<String>();
        Stack<Integer> pilaNroCelda = new Stack<Integer>();

        ArrayList<String> codigo = new ArrayList<String>(); // sentencias de programacion del programador

        listInst.add(".MODEL LARGE");
        listInst.add(".386");
        listInst.add(".STACK 200h\n");

        listInst.add(".DATA\n");
        ///agrego la tabla de simbolos
        for(SimbolRow simbolo : TablaDeSimbolos){
            listInst.add(String.format("%-20s %-5s %-30s", simbolo.getNombre(),"dd", simbolo.getValor()));
        }

        //cabecera de instrucciones
        listInst.add("\n.CODE");
        listInst.add("\nMOV AX, @DATA");
        listInst.add("MOV DS, AX");
        listInst.add("MOV ES, AX\n");


        //codigo del programador
        int cantVariablesAuxiliares = 0;
        int cantEtiquetas = 0;
        int nroCelda = 1;
        for(String celda : polacaInversa){

            if(!pilaNroCelda.isEmpty() && nroCelda == pilaNroCelda.peek()){
                codigo.add(colaEtiquetas.remove() + ":");
                pilaNroCelda.pop();
            }
            switch (celda){
                case ":=":
                {
                    String op2 = pilaOperandos.pop();
                    String op1 = pilaOperandos.pop();
                    codigo.add("FLD " + op1);
                    codigo.add("FSTP " + op2);
                    codigo.add("");
                    break;
                }
                case "+":
                {
                    String op2 = pilaOperandos.pop();
                    String op1 = pilaOperandos.pop();
                    String varAux = "@aux" + (cantVariablesAuxiliares+1);
                    cantVariablesAuxiliares++;
                    codigo.add("FLD " + op1);
                    codigo.add("FLD " + op2);
                    codigo.add("FADD");
                    codigo.add("FSTP " + varAux);
                    codigo.add("");
                    pilaOperandos.add(varAux);
                    break;
                }
                case "-":
                {
                    String op2 = pilaOperandos.pop();
                    String op1 = pilaOperandos.pop();
                    String varAux = "@aux" + (cantVariablesAuxiliares+1);
                    cantVariablesAuxiliares++;
                    codigo.add("FLD " + op1);
                    codigo.add("FLD " + op2);
                    codigo.add("FSUB");
                    codigo.add("FSTP " + varAux);
                    codigo.add("");
                    pilaOperandos.add(varAux);
                    break;
                }
                case "/":
                {
                    String op2 = pilaOperandos.pop();
                    String op1 = pilaOperandos.pop();
                    String varAux = "@aux" + (cantVariablesAuxiliares+1);
                    cantVariablesAuxiliares++;
                    codigo.add("FLD " + op1);
                    codigo.add("FLD " + op2);
                    codigo.add("FDIV");
                    codigo.add("FSTP " + varAux);
                    codigo.add("");
                    pilaOperandos.add(varAux);
                    break;
                }
                case "*":
                {
                    String op2 = pilaOperandos.pop();
                    String op1 = pilaOperandos.pop();
                    String varAux = "@aux" + (cantVariablesAuxiliares+1);
                    cantVariablesAuxiliares++;
                    codigo.add("FLD " + op1);
                    codigo.add("FLD " + op2);
                    codigo.add("FMUL");
                    codigo.add("FSTP " + varAux);
                    codigo.add("");
                    pilaOperandos.add(varAux);
                    break;
                }
                case "CMP":
                {
                    String op2 = pilaOperandos.pop();
                    String op1 = pilaOperandos.pop();
                    codigo.add("FLD " + op1);
                    codigo.add("FLD " + op2);
                    codigo.add("FXCH");
                    codigo.add("FCOM");
                    codigo.add("FSTSW AX");
                    codigo.add("SAHF");
                    break;
                }
                case "BLE":
                case "BGE":
                case "BLT":
                case "BGT":
                case "BEQ":
                case "BNE":
                {
                    String etiqueta = "etiqueta" + (cantEtiquetas+1); // etiqueta 1 // etiqueta 2
                    cantEtiquetas++;  // 1 // 2
                    colaEtiquetas.add(etiqueta); // cola = [etiqueta1] // cola = [etiqueta1, etiqueta2]
                    codigo.add(celda + " " + etiqueta); // BLE etiqueta1 // BNE etiqueta2
                    codigo.add("");
                    break;
                }
                case "BI":
                {
                    String etiqueta = "etiqueta" + (cantEtiquetas+1); 
                    cantEtiquetas++;
                    colaEtiquetas.add(etiqueta); 
                    String aux = colaEtiquetas.remove();
                    codigo.add("BI " + colaEtiquetas.remove());
                    codigo.add("");
                    codigo.add(aux + ":");
                    colaEtiquetas.add("etiqueta" + cantEtiquetas);
                    break;
                }
                case "ET":
                {
                    String etiqueta = "etiqueta" + (colaEtiquetas.size()); // etiqueta 2: 
                    cantEtiquetas++;//3
                    colaEtiquetas.add(etiqueta); //etiqueta
                    codigo.add(etiqueta + ":");
                    codigo.add("");
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

        if (!colaEtiquetas.isEmpty())
        {
            codigo.add(colaEtiquetas.remove() + ":");
        }

        for(String instruccion : codigo){
            listInst.add(instruccion);
        }

        listInst.add("\nMOV AX, 4C00h");
        listInst.add("INT 21h");
        listInst.add("END");

        System.out.println("############# CODIGO ASSEMBLER #############");
        for(String instruccion : listInst){
            System.out.println(instruccion);

        }
    }

    public ArrayList<String> getListInst(){
        return listInst;
    }


}
