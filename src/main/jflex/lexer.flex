package lyc.compiler;

import java_cup.runtime.Symbol;
import lyc.compiler.ParserSym;
import lyc.compiler.model.*;
import static lyc.compiler.constants.Constants.*;

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
  private Symbol symbol(int type) {
    return new Symbol(type, yyline, yycolumn);
  }
  private Symbol symbol(int type, Object value) {
    return new Symbol(type, yyline, yycolumn, value);
  }
%}


/* SECCION TOKENS */

/* SECCION DE PALABRAS RESERVADAS */
while = "while" | "WHILE"
write = "write" | "WRITE"
if = "if" | "IF"
else = "else" | "ELSE"
Init = "init" | "INIT"
Read = "read" | "READ"

Plus = "+"
Mult = "*"
Sub = "-"
Div = "/"
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
LessThan = "<"
GreaterThan = ">"
Equal = "=="
NotEqual = "!="
LessThanEqual = "<="
GreaterThanEqual = ">="
And = "&"
Or = "|"
Not = "not" | "NOT"
quot = "\""
singlequot = "\'"


//Conjuntos
Letter = [a-zA-Z]
Digit = [0-9]
LineTerminator = \r|\n|\r\n
InputCharacter = [^\r\n]
Identation =  [ \t\f]
//SpecialCar = [!@#$%^&*()\-=_+[]{}|;':,./<>?]  // no se si esta anda bien..
//Texto_Invalido = [^{CARACTER} 	\n]

CTE_String = \"([^\"\\\\]|\\\\.)*\"
//CTE_Float = ({DIGITO}+"."{DIGITO}+)|("."{DIGITO}+)|({DIGITO}+".")
CTE_Int = ({DIGITO}+)|("-"{DIGITO}+)

WhiteSpace = {LineTerminator} | {Identation}
Identifier = {Letter} ({Letter}|{Digit}|_)*
IntegerConstant = {Digit}+
Comment = "/*" ({Letter}|{Digit}|{WhiteSpace})* "*/"


%%


/* keywords para probar branch*/

<YYINITIAL> {
  /* identifiers */
  {Identifier}                             { return symbol(ParserSym.IDENTIFIER, yytext()); }
  /* Constants */
  {IntegerConstant}                        { return symbol(ParserSym.INTEGER_CONSTANT, yytext()); }
  //{CTE_Float}                              { return symbol(ParserSym.FLOAT_CONSTANT, yytext()); }
  {CTE_String}                             { return symbol(ParserSym.STRING_CONSTANT, yytext()); }
  /* keywords */
  {while}                                  { return symbol(ParserSym.WHILE); }
  {write}                                  { return symbol(ParserSym.WRITE); }
  {if}                                     { return symbol(ParserSym.IF); }
  {else}                                   { return symbol(ParserSym.ELSE); }
  {Init}                                   { return symbol(ParserSym.INIT); }
  {Read}                                   { return symbol(ParserSym.READ); }

 /* operators */
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
  {LessThan}                               { return symbol(ParserSym.LESS_THAN); }
  {GreaterThan}                            { return symbol(ParserSym.GREATER_THAN); }
  {Equal}                                  { return symbol(ParserSym.EQUAL); }
  {NotEqual}                               { return symbol(ParserSym.NOT_EQUAL); }
  {LessThanEqual}                          { return symbol(ParserSym.LESS_THAN_EQUAL); }
  {GreaterThanEqual}                       { return symbol(ParserSym.GREATER_THAN_EQUAL); }
  {And}                                    { return symbol(ParserSym.AND); }
  {Or}                                     { return symbol(ParserSym.OR); }
  {Not}                                    { return symbol(ParserSym.NOT); }
  {quot}                                   { return symbol(ParserSym.QUOT); }
  {singlequot}                             { return symbol(ParserSym.SINGLE_QUOT); }
  {Percent}                                { return symbol(ParserSym.PERCENT); }

  /* whitespace */
  {WhiteSpace}                             { /* ignore */ }
  {Comment}                                { /* ignore */}
  //{SpecialCar}                             { throw new UnknownCharacterException(yytext()); }
  //{Texto_Invalido}                         { throw new UnknownCharacterException(yytext()); }

}


/* error fallback */
[^]                              { throw new UnknownCharacterException(yytext()); }
