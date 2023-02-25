package lyc.compiler;

import java_cup.runtime.Symbol;
import lyc.compiler.ParserSym;
import lyc.compiler.model.*;
import static lyc.compiler.constants.Constants.*;
import lyc.compiler.validations.Validate;
import lyc.compiler.simbolsTable.SimbolTable;
import lyc.compiler.simbolsTable.SimbolRow;

%%

%public
%class Lexer
%unicode
%cup
%line
%column
%throws CompilerException
%eofval{
  return symbol(ParserSym.EOF);
%eofval}

%{ 
  SimbolTable simbolTable = SimbolTable.getSingletonInstance();
%}

%{
  private Symbol symbol(int type) {
    return new Symbol(type, yyline, yycolumn);
  }

  private Symbol symbol(int type, Object value) {        
    return new Symbol(type, yyline, yycolumn, value);
  }

  private void addSymbol(String id, Object value) {  
    SimbolRow simbolRow = new SimbolRow(id, value.toString(),"",0);
    simbolTable.setSimbol(simbolRow);
  }
  
%}

/* SECCION TOKENS */

Percent = "%"
Assig = "="
OpenBracket = "("
CloseBracket = ")"
OpenCurlyBracket = "{"
CloseCurlyBracket = "}"
OpenSquareBracket = "["
CloseSquareBracket = "]"
Dot = "."
DosPuntos = ":"
Coma = ","
SemiColon = ";"
And = "&"
Or = "||"
Not = "not" | "NOT"
LessThan = "<"
GreaterThan = ">"
Equal = "=="
NotEqual = "!="
LessThanEqual = "<="
GreaterThanEqual = ">="
quot = "\""
singlequot = "\'"
int = "Int"
float = "Float"
string = "String"
write = "write"
if = "if"
while = "while" | "WHILE"
// write = "write" | "WRITE"
//if = "if" | "IF"
else = "else" | "ELSE"
Init = "init" | "INIT"
Read = "read" | "READ"
ListIndexOfLast = "ListIndexOfLast"
PrintReverseStringN ="PrintReverseStringN"

//Conjuntos
Letter = [a-zA-Z]
Digit = [0-9]
DigitSC = [1-9]
LineTerminator = \r|\n|\r\n
InputCharacter = [^\r\n]
Identation =  [ \t\f]
WhiteSpace = {LineTerminator} | {Identation}
SpecialCar = [><:\+\-\*,\/@\%\.\[\];\(\)= ¿¡!]
Character = {Letter} | {Digit}| {WhiteSpace} | {SpecialCar}
Texto_Invalido = [^\{Character}\n]

Identifier = {Letter} ({Letter}|{Digit}|_)*
IntegerConstant = {DigitSC}{Digit}*| 0
FloatConstant = -? {Digit}+{Dot}{Digit}* | {Dot}{Digit}+ ///////////////////////// faltaría ver como agregar "-"?
CharacterConstant = \'([^\"\\\\]|\\\\.)\'
StringConstant = \"([^\"\\\\]|\\\\.)*\" // \"{Character}*\"
// Comment = "/*" ({Letter}|{Digit}|{WhiteSpace})* "*/"
// Comment = "/*" ({Character})* "*/"
Comment = "/*" ~ "*/"

Plus = "+"
Mult = "*"
Sub = "-"
Div = "/"

%%


/* keywords para probar branch */

<YYINITIAL> {

  /* SECCION DE PALABRAS RESERVADAS */
  {while}                                  { return symbol(ParserSym.WHILE); }
  {write}                                  { return symbol(ParserSym.WRITE); }
  {if}                                     { return symbol(ParserSym.IF); }
  {else}                                   { return symbol(ParserSym.ELSE); }
  {Init}                                   { return symbol(ParserSym.INIT); }
  {Read}                                   { return symbol(ParserSym.READ); }
  {int}                                    { return symbol(ParserSym.INT); }
  {float}                                  { return symbol(ParserSym.FLOAT); }
  {string}                                 { return symbol(ParserSym.STRING); }
  {ListIndexOfLast}                        { return symbol(ParserSym.LIST_INDEX_OF_LAST); }
  {PrintReverseStringN}                    { return symbol(ParserSym.PRINT_REVERSE); }

  
  /* Operadores */
  {Percent}                                { return symbol(ParserSym.PERCENT); }
  {Plus}                                   { return symbol(ParserSym.PLUS); }
  {Sub}                                    { return symbol(ParserSym.SUB); }
  {Mult}                                   { return symbol(ParserSym.MULT); }
  {Div}                                    { return symbol(ParserSym.DIV); }
  {Assig}                                  { return symbol(ParserSym.ASSIG); }
  {OpenBracket}                            { return symbol(ParserSym.OPEN_BRACKET); }
  {CloseBracket}                           { return symbol(ParserSym.CLOSE_BRACKET); }
  {OpenCurlyBracket}                       { return symbol(ParserSym.OPEN_CURLY_BRACKET); }
  {CloseCurlyBracket}                      { return symbol(ParserSym.CLOSE_CURLY_BRACKET); }
  {OpenSquareBracket}                      { return symbol(ParserSym.OPEN_SQUARE_BRACKET); }
  {CloseSquareBracket}                     { return symbol(ParserSym.CLOSE_SQUARE_BRACKET); }
  {Dot}                                    { return symbol(ParserSym.DOT); }
  {DosPuntos}                              { return symbol(ParserSym.DOS_PUNTOS); }
  {Coma}                                   { return symbol(ParserSym.COMA); }
  {SemiColon}                              { return symbol(ParserSym.SEMI_COLON); }
  {And}                                    { return symbol(ParserSym.AND); }
  {Or}                                     { return symbol(ParserSym.OR); }
  {Not}                                    { return symbol(ParserSym.NOT); }
  {LessThan}                               { return symbol(ParserSym.LESS_THAN); }
  {GreaterThan}                            { return symbol(ParserSym.GREATER_THAN); }
  {Equal}                                  { return symbol(ParserSym.EQUAL); }
  {NotEqual}                               { return symbol(ParserSym.NOT_EQUAL); }
  {LessThanEqual}                          { return symbol(ParserSym.LESS_THAN_EQUAL); }
  {GreaterThanEqual}                       { return symbol(ParserSym.GREATER_THAN_EQUAL); }
  {quot}                                   { return symbol(ParserSym.QUOT); }
  {singlequot}                             { return symbol(ParserSym.SINGLE_QUOT); }
  {Percent}                                { return symbol(ParserSym.PERCENT); }

/* identifiers */
  {Identifier}                             {
                                              Validate.validateIdentifier(yytext());
                                              //addSymbol("Id", yytext());
                                              return symbol(ParserSym.IDENTIFIER, yytext());
                                           }
  /* Constants */
  {IntegerConstant}                        {
                                              Validate.validateInt(yytext());
                                              //addSymbol("Int", yytext()); 
                                              return symbol(ParserSym.INTEGER_CONSTANT, yytext());                                              
                                           }
  {FloatConstant}                          {  Validate.validateFloat(yytext());
                                              //addSymbol("Float", yytext());
                                              return symbol(ParserSym.FLOAT_CONSTANT, yytext()); }
  
  {CharacterConstant}                      {  return symbol(ParserSym.CHARACTER_CONSTANT, yytext()); }
  
  {StringConstant}                         {  Validate.validateString(yytext());
                                              //addSymbol("String", yytext());
                                              return symbol(ParserSym.STRING_CONSTANT, yytext()); }



  /* whitespace */
  {WhiteSpace}                             { /* ignore */ }
  {Comment}                                { /* ignore */ }
  {Texto_Invalido}                         {System.out.println("ERROR CARACTER INVALIDO");throw new UnknownCharacterException(yytext()); }
}


/* error fallback */
[^]                              { throw new UnknownCharacterException(yytext()); }

