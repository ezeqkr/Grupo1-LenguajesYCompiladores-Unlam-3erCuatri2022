package lyc.compiler;

import java_cup.runtime.Symbol;
import lyc.compiler.ParserSym;
import lyc.compiler.model.*;
import static lyc.compiler.constants.Constants.*;
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


LineTerminator = \r|\n|\r\n
InputCharacter = [^\r\n]
Identation =  [ \t\f]

Plus = "+"
Mult = "*"
Sub = "-"
Div = "/"
Equal= "==" 
NotEqual = "=!"
Assig = "="
OpenBracket = "("
CloseBracket = ")"
GreaterEqual = ">=" 
Greater = ">"
LessEqual= "<=" 
Less = "<"



//Conjuntos
Letter = [a-zA-Z]
Digit = [0-9]


WhiteSpace = {LineTerminator} | {Identation}
Identifier = {Letter} ({Letter}|{Digit})*
IntegerConstant = {Digit}+
Comment = "/*" ({Letter}|{Digit}|{WhiteSpace})* "*/"

%%


/* keywords para probar branch*/

<YYINITIAL> {
  /* identifiers */
  {Identifier}                             { 
                                              // Validator.validateSymbol(ParserSym.IDENTIFIER, yytext());
                                              addSymbol("IDENTIFIER", yytext()); 
                                              return symbol(ParserSym.IDENTIFIER, yytext());
                                           }
  /* Constants */
  {IntegerConstant}                        { 
                                             addSymbol("INTEGER_CONSTANT", yytext()); 
                                             return symbol(ParserSym.INTEGER_CONSTANT, yytext()); 
                                           }
  /* Comments */
  {Comment}                                { return symbol(ParserSym.COMMENT, yytext()); }

  /* operators */
  {Plus}                                    { return symbol(ParserSym.PLUS); }
  {Sub}                                     { return symbol(ParserSym.SUB); }
  {Mult}                                    { return symbol(ParserSym.MULT); }
  {Div}                                     { return symbol(ParserSym.DIV); }
  {Assig}                                   { return symbol(ParserSym.ASSIG); }
  {OpenBracket}                             { return symbol(ParserSym.OPEN_BRACKET); }
  {CloseBracket}                            { return symbol(ParserSym.CLOSE_BRACKET); }

  /* whitespace */
  {WhiteSpace}                   { /* ignore */ }
}


/* error fallback */
[^]                              { throw new UnknownCharacterException(yytext()); }
