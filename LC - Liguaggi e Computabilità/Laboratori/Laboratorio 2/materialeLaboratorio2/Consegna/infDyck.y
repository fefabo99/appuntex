%{
  import java.io.*;
  import java.lang.*;
%}

%token OPEN_PAREN;
%token CLOSE_PAREN;
%token <sval> SKIP;
%token <ival> SKIPM;
%token <sval> MATRICOLA;
%token <sval> COLONS;

%start s

%%

parens  : OPEN_PAREN s CLOSE_PAREN
        | OPEN_PAREN CLOSE_PAREN

//twopoints : TWOPOINTS {System.out.println("Due punti" + $1); }

exp     : parens

exps    : exp SKIPM SKIPM SKIP { if ($2 != $3){System.out.print("Err: " );}
                              System.out.println("txt: " + $4);}
        | exp COLONS SKIP { System.out.println("txt: " + $3);}
        | exp SKIP { System.out.println("S: "+$2); }      
        | exp

s       : SKIP { System.out.println("txt: "+$1); }
        | SKIPM SKIPM SKIP { if ($1 != $2){System.out.print("Err: " );}
                              System.out.println("txt: " + $3);}
        | COLONS SKIP {System.out.println("txt: " + $2);}
        | SKIPM SKIPM MATRICOLA { System.out.println($3); }
        | exps
        | s exps

%%

void yyerror(String s)
{
 System.out.println("err:"+s);
 System.out.println("   :"+yylval.sval);
}

static Yylex lexer;
int yylex()
{
 try {
  return lexer.yylex();
 }
 catch (IOException e) {
  System.err.println("IO error :"+e);
  return -1;
 }
}

public static void main(String args[])
{
 System.out.println("[Quit with CTRL-D]");
 Parser par = new Parser();
 lexer = new Yylex(new InputStreamReader(System.in), par);
 par.yyparse();
}
