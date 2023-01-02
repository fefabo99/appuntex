/* * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
 * Copyright (C) 2000 Gerwin Klein <lsf@jflex.de>                          *
 * All rights reserved.                                                    *
 *                                                                         *
 * Thanks to Larry Bell and Bob Jamison for suggestions and comments.      *
 *                                                                         *
 * License: BSD                                                            *
 *                                                                         *
 * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * */

%%

%byaccj

%{
  private Parser yyparser;

  public Yylex(java.io.Reader r, Parser yyparser) {
    this(r);
    this.yyparser = yyparser;
  }
%}

NUM = [0-9]+ ("." [0-9]+)?
NL  = \n | \r | \r\n
LOG10 = ("log10"|"LOG10")
LN = ("ln"|"LN")
GLOBAL = "global"
OPENLB = "{"
CLOSELB = "}"
%%

/* operators */
"+" | 
"-" | 
"*" | 
"/" | 
"^" | 
"%" |
"(" | 
")"    { return (int) yycharat(0); }

":" { yyparser.yylval = new ParserVal(""+yycharat(0)); 
                        return Parser.COLON; }
{OPENLB} { yyparser.yylval = new ParserVal(""+yycharat(0)); 
                        return Parser.OPENLB; }
{CLOSELB} { yyparser.yylval = new ParserVal(""+yycharat(0)); 
                        return Parser.CLOSELB; }
";" { yyparser.yylval = new ParserVal(""+yycharat(0)); 
                        return Parser.SEMICOLON; }
/* LOG10 */
{LOG10} {return Parser.LOG10; }

/* STUDENTE */
"Studente" { yyparser.yylval = new ParserVal(yytext());
                        return Parser.STUDENTE; }
/* ZERO */
"0" { yyparser.yylval = new ParserVal(yytext());
                        return Parser.ZERO; }
/* NOME */
"nome" { yyparser.yylval = new ParserVal(yytext());
                        return Parser.NOME; }

"," { yyparser.yylval = new ParserVal(""+yycharat(0)); 
                        return Parser.COMMA; }
/* MATRICOLASTR */
"matricola" { yyparser.yylval = new ParserVal(yytext());
                        return Parser.MATRICOLASTR; }
/* MATRICOLA */
"845420" { yyparser.yylval = new ParserVal(yytext());
                        return Parser.MATRICOLA; }

/* LN */
{LN} {return Parser.LN; }

/* newline */
{NL}   { return Parser.NL; }

/* float */
{NUM}  { yyparser.yylval = new ParserVal(Double.parseDouble(yytext()));
         return Parser.NUM; }

[a-zA-Z]+ { yyparser.yylval = new ParserVal(yytext()); 
                        return Parser.ID; }

/* whitespace */
[ \t]+ {}

\b     { System.err.println("Sorry, backspace doesn't work"); }

/* error fallback */
[^]    { System.err.println("Error: unexpected character '"+yytext()+"'"); return -1; }

/* Cose da fare dopo 
Riconoscimento degli altri token, definizione delle regole
grammaticali nel file y (struttra JSON) e test di stampa
A che cazzo servono gli stati? 
*/