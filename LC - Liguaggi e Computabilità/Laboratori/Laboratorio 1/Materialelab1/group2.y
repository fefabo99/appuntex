%{
  import java.io.*;
%}

%%

input: /* null */ { $$ = new ParserVal("");
                    System.out.println(": \""+$$.sval+"\""); }
     | input negS { $$ = new ParserVal($1.sval+$2.sval);
                   System.out.println(": "+$$.sval); }
     | input '+' { $$ = new ParserVal($1.sval+$2.sval);
                   System.out.println(": "+$$.sval); }

negS: '-' { $$ = $1; }
    | negS '-' { $$ = new ParserVal($1.sval + '-'); }
    ;

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
