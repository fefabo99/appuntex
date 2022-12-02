
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

"+" |
"-" { yyparser.yylval = new ParserVal(""+yycharat(0));
      return (int) yycharat(0); }

[^] {} /*butta il resto*/
