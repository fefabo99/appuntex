
%%

%byaccj

%{
  /* store a reference to the parser object */
  private Parser yyparser;

  /* constructor taking an additional parser object */
  public Yylex(java.io.Reader r, Parser yyparser) {
    this(r);
    this.yyparser = yyparser;
  }
%}
%%

"("                   { yyparser.yylval = new ParserVal(""+yycharat(0));
                        return Parser.OPEN_PAREN; }
")"                   { yyparser.yylval = new ParserVal(""+yycharat(0));
                        return Parser.CLOSE_PAREN; }
                        
"["                   { yyparser.yylval = new ParserVal(""+yycharat(0));
                        return Parser.OPEN_PAREN; }
"]"                   { yyparser.yylval = new ParserVal(""+yycharat(0));
                        return Parser.CLOSE_PAREN; }
"845420" { yyparser.yylval = new ParserVal(yytext());
                        return Parser.MATRICOLA; }
"::"+                 { yyparser.yylval = new ParserVal(yytext());
                        return Parser.COLONS; }
[A-Z] { yyparser.yylval = new ParserVal(yycharat(0));
                        return Parser.SKIPM; }
":"? [a-z]+            { yyparser.yylval = new ParserVal(yytext());
                        return Parser.SKIP; }
[^] {} /*butta il resto*/
