%{
  import java.io.*;
  import java.lang.*;
%}

%token OPEN_PAREN;
%token CLOSE_PAREN;
%token <sval> SKIP;
%token <sval> SKIPM;
%token <dval> MATRICOLA;
%token <sval> COLONS;

%start s

%%

parens  : OPEN_PAREN s CLOSE_PAREN
        | OPEN_PAREN CLOSE_PAREN

//twopoints : TWOPOINTS {System.out.println("Due punti" + $1); }

exp     : parens

exps    : exp SKIPM SKIP {System.out.println("S: Err"+ $2); }
        | exp SKIP { System.out.println("S: "+$2); }
        | SKIPM SKIP { System.out.println("Err:" + $1 + " txt: " + $2);}
        | exp

s       : SKIP { System.out.println("txt: "+$1); }
        | COLONS SKIP {if (($1.length()%2)==0)
                    System.out.println($2);
                  else
                    System.out.print(":" + $2);
                 }
        | MATRICOLA SKIP { System.out.println("txt: "+ $1); }
        | exps SKIPM SKIP {System.out.println("S: "+$2 + $3); }
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
