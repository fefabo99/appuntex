/* * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
 * Copyright (C) 2001 Gerwin Klein <lsf@jflex.de>                          *
 * All rights reserved.                                                    *
 *                                                                         *
 * This is a modified version of the example from                          *
 *   http://www.lincom-asg.com/~rjamison/byacc/                            *
 *                                                                         *
 * Thanks to Larry Bell and Bob Jamison for suggestions and comments.      *
 *                                                                         *
 * License: BSD                                                            *
 *                                                                         *
 * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * */

%{
  import java.io.*;
%}
      
%token NL          /* newline  */
// %token S            /* space */
%token <dval> NUM  /* a number */
%token LOG10
%token LN
%token OPENLB
%token CLOSELB
%token COLON
%token SEMICOLON
%token <sval> ID 
%token <sval> MATRICOLASTR
%token <sval> STUDENTE
%token <sval> NOME
%token MATRICOLA
%token NL
%token ZERO
%token COMMA


%left '-' '+' ','
%left '*' '/' '%'
%left LOG10 LN
%left NEG          /* negation--unary minus */
%right '^'         /* exponentiation        */

%start s
%%
body1  : NOME COLON ID COMMA { System.out.println($1 + "=" + $3); }
       ;
body2  : MATRICOLASTR COLON ZERO { System.out.println($1 + "=" + "845420"); }

s     : STUDENTE COLON OPENLB NL body1 NL body2 NL CLOSELB { System.out.println("[" +$1 + "]"); }
       ;
%%

  private Yylex lexer;


  private int yylex () {
    int yyl_return = -1;
    try {
      yylval = new ParserVal(0);
      yyl_return = lexer.yylex();
    }
    catch (IOException e) {
      System.err.println("IO error :"+e);
    }
    return yyl_return;
  }


  public void yyerror (String error) {
    System.err.println ("Error: " + error);
  }


  public Parser(Reader r) {
    lexer = new Yylex(r, this);
  }


  static boolean interactive;

  public static void main(String args[]) throws IOException {
    System.out.println("BYACC/Java with JFlex Calculator Demo");

    Parser yyparser;
    if ( args.length > 0 ) {
      // parse a file
      yyparser = new Parser(new FileReader(args[0]));
    }
    else {
      // interactive mode
      System.out.println("[Quit with CTRL-D]");
      System.out.print("Expression: ");
      interactive = true;
	    yyparser = new Parser(new InputStreamReader(System.in));
    }

    yyparser.yyparse();
    
    if (interactive) {
      System.out.println();
      System.out.println("Have a nice day");
    }
  }
