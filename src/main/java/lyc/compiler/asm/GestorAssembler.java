package lyc.compiler.asm;

import lyc.compiler.simbolsTable.SimbolRow;

import java.util.*;

public class GestorAssembler {

    ArrayList<String> listInst = new ArrayList<String>();

    public GestorAssembler(){
    }

    public void generarAssembler(List<SimbolRow> TablaDeSimbolos , ArrayList<String> polacaInversa){

        Stack<String> pilaOperandos  = new Stack<String>();
//        Queue<String> colaEtiquetas  = new LinkedList<String>();
        Stack<String> pilaEtiquetas  = new Stack<String>();

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
        boolean esNum = false;
        for(String celda : polacaInversa){

            //if(!pilaNroCelda.isEmpty() && nroCelda == pilaNroCelda.peek()){
                while(!pilaNroCelda.isEmpty() && nroCelda == pilaNroCelda.peek()){
                    codigo.add(pilaEtiquetas.pop() + ":");
                    pilaNroCelda.pop();
                }
            //}
            switch (celda){
                case ":=":
                {
                    String op2 = pilaOperandos.pop();
                    try {
                        Integer.parseInt(op2);
                        esNum = true;
                    } catch (NumberFormatException excepcion) {
                        esNum = false;
                    }
                    if(esNum)
                    {
                        op2 = "_"+op2;
                        esNum = false;
                    }
                    String op1 = pilaOperandos.pop();
                    try {
                        Integer.parseInt(op1);
                        esNum = true;
                    } catch (NumberFormatException excepcion) {
                        esNum = false;
                    }
                    if(esNum)
                    {
                        op1 = "_"+op1;
                        esNum = false;
                    }
                    codigo.add("FLD " + op1);
                    codigo.add("FSTP " + op2);
                    codigo.add("");
                    break;
                }
                case "+":
                {
                    String op2 = pilaOperandos.pop();
                    try {
                        Integer.parseInt(op2);
                        esNum = true;
                    } catch (NumberFormatException excepcion) {
                        esNum = false;
                    }
                    if(esNum)
                    {
                        op2 = "_"+op2;
                        esNum = false;
                    }
                    String op1 = pilaOperandos.pop();
                    try {
                        Integer.parseInt(op1);
                        esNum = true;
                    } catch (NumberFormatException excepcion) {
                        esNum = false;
                    }
                    if(esNum)
                    {
                        op1 = "_"+op1;
                        esNum = false;
                    }
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
                    try {
                        Integer.parseInt(op2);
                        esNum = true;
                    } catch (NumberFormatException excepcion) {
                        esNum = false;
                    }
                    if(esNum)
                    {
                        op2 = "_"+op2;
                        esNum = false;
                    }
                    String op1 = pilaOperandos.pop();
                    try {
                        Integer.parseInt(op1);
                        esNum = true;
                    } catch (NumberFormatException excepcion) {
                        esNum = false;
                    }
                    if(esNum)
                    {
                        op1 = "_"+op1;
                        esNum = false;
                    }
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
                    try {
                        Integer.parseInt(op2);
                        esNum = true;
                    } catch (NumberFormatException excepcion) {
                        esNum = false;
                    }
                    if(esNum)
                    {
                        op2 = "_"+op2;
                        esNum = false;
                    }
                    String op1 = pilaOperandos.pop();
                    try {
                        Integer.parseInt(op1);
                        esNum = true;
                    } catch (NumberFormatException excepcion) {
                        esNum = false;
                    }
                    if(esNum)
                    {
                        op1 = "_"+op1;
                        esNum = false;
                    }
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
                    try {
                        Integer.parseInt(op2);
                        esNum = true;
                    } catch (NumberFormatException excepcion) {
                        esNum = false;
                    }
                    if(esNum)
                    {
                        op2 = "_"+op2;
                        esNum = false;
                    }
                    String op1 = pilaOperandos.pop();
                    try {
                        Integer.parseInt(op1);
                        esNum = true;
                    } catch (NumberFormatException excepcion) {
                        esNum = false;
                    }
                    if(esNum)
                    {
                        op1 = "_"+op1;
                        esNum = false;
                    }
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
                    try {
                        Integer.parseInt(op2);
                        esNum = true;
                    } catch (NumberFormatException excepcion) {
                        esNum = false;
                    }
                    if(esNum)
                    {
                        op2 = "_"+op2;
                        esNum = false;
                    }
                    String op1 = pilaOperandos.pop();
                    try {
                        Integer.parseInt(op1);
                        esNum = true;
                    } catch (NumberFormatException excepcion) {
                        esNum = false;
                    }
                    if(esNum)
                    {
                        op1 = "_"+op1;
                        esNum = false;
                    }
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
                    String etiqueta = "etiqueta" + (cantEtiquetas+1); // etiqueta1 // etiqueta 3
                    cantEtiquetas++;//1 // 3
                    pilaEtiquetas.add(etiqueta);//PILA = [etiqueta1] // PILA = [etiqueta3, etiqueta2]
                    codigo.add(celda + " " + etiqueta);
                    codigo.add("");
                    break;
                }
                case "BI":
                {
                    String etiqueta = "etiqueta" + (cantEtiquetas+1); // etiqueta2
                    cantEtiquetas++;//2
                    pilaEtiquetas.add(etiqueta); // PILA = [etiqueta2, etiqueta1]
                    String aux = pilaEtiquetas.pop(); // PILA = [etiqueta1]
                    codigo.add("BI " + aux);
                    codigo.add("");
                    codigo.add(pilaEtiquetas.pop()+ ":"); // PILA = []
                    pilaEtiquetas.add(aux);// PILA = [etiqueta2]
                    break;
                }
                case "ET":
                {
                    String etiqueta = "etiqueta" + (cantEtiquetas+1); // etiqueta
                    cantEtiquetas++;
                    pilaEtiquetas.add(etiqueta);
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

        if (!pilaEtiquetas.isEmpty())
        {
            codigo.add(pilaEtiquetas.pop() + ":");
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
