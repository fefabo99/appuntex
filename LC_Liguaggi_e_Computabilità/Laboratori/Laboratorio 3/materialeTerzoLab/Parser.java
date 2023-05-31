//### This file created by BYACC 1.8(/Java extension  1.15)
//### Java capabilities added 7 Jan 97, Bob Jamison
//### Updated : 27 Nov 97  -- Bob Jamison, Joe Nieten
//###           01 Jan 98  -- Bob Jamison -- fixed generic semantic constructor
//###           01 Jun 99  -- Bob Jamison -- added Runnable support
//###           06 Aug 00  -- Bob Jamison -- made state variables class-global
//###           03 Jan 01  -- Bob Jamison -- improved flags, tracing
//###           16 May 01  -- Bob Jamison -- added custom stack sizing
//###           04 Mar 02  -- Yuval Oren  -- improved java performance, added options
//###           14 Mar 02  -- Tomas Hurka -- -d support, static initializer workaround
//### Please send bug reports to tom@hukatronic.cz
//### static char yysccsid[] = "@(#)yaccpar	1.8 (Berkeley) 01/20/90";






//#line 1 "simple.y"

  import java.io.*;
//#line 20 "Parser.java"




public class Parser
{

boolean yydebug;        //do I want debug output?
int yynerrs;            //number of errors so far
int yyerrflag;          //was there an error?
int yychar;             //the current working character

//########## MESSAGES ##########
//###############################################################
// method: debug
//###############################################################
void debug(String msg)
{
  if (yydebug)
    System.out.println(msg);
}

//########## STATE STACK ##########
final static int YYSTACKSIZE = 500;  //maximum stack size
int statestk[] = new int[YYSTACKSIZE]; //state stack
int stateptr;
int stateptrmax;                     //highest index of stackptr
int statemax;                        //state when highest index reached
//###############################################################
// methods: state stack push,pop,drop,peek
//###############################################################
final void state_push(int state)
{
  try {
		stateptr++;
		statestk[stateptr]=state;
	 }
	 catch (ArrayIndexOutOfBoundsException e) {
     int oldsize = statestk.length;
     int newsize = oldsize * 2;
     int[] newstack = new int[newsize];
     System.arraycopy(statestk,0,newstack,0,oldsize);
     statestk = newstack;
     statestk[stateptr]=state;
  }
}
final int state_pop()
{
  return statestk[stateptr--];
}
final void state_drop(int cnt)
{
  stateptr -= cnt; 
}
final int state_peek(int relative)
{
  return statestk[stateptr-relative];
}
//###############################################################
// method: init_stacks : allocate and prepare stacks
//###############################################################
final boolean init_stacks()
{
  stateptr = -1;
  val_init();
  return true;
}
//###############################################################
// method: dump_stacks : show n levels of the stacks
//###############################################################
void dump_stacks(int count)
{
int i;
  System.out.println("=index==state====value=     s:"+stateptr+"  v:"+valptr);
  for (i=0;i<count;i++)
    System.out.println(" "+i+"    "+statestk[i]+"      "+valstk[i]);
  System.out.println("======================");
}


//########## SEMANTIC VALUES ##########
//public class ParserVal is defined in ParserVal.java


String   yytext;//user variable to return contextual strings
ParserVal yyval; //used to return semantic vals from action routines
ParserVal yylval;//the 'lval' (result) I got from yylex()
ParserVal valstk[];
int valptr;
//###############################################################
// methods: value stack push,pop,drop,peek.
//###############################################################
void val_init()
{
  valstk=new ParserVal[YYSTACKSIZE];
  yyval=new ParserVal();
  yylval=new ParserVal();
  valptr=-1;
}
void val_push(ParserVal val)
{
  if (valptr>=YYSTACKSIZE)
    return;
  valstk[++valptr]=val;
}
ParserVal val_pop()
{
  if (valptr<0)
    return new ParserVal();
  return valstk[valptr--];
}
void val_drop(int cnt)
{
int ptr;
  ptr=valptr-cnt;
  if (ptr<0)
    return;
  valptr = ptr;
}
ParserVal val_peek(int relative)
{
int ptr;
  ptr=valptr-relative;
  if (ptr<0)
    return new ParserVal();
  return valstk[ptr];
}
final ParserVal dup_yyval(ParserVal val)
{
  ParserVal dup = new ParserVal();
  dup.ival = val.ival;
  dup.dval = val.dval;
  dup.sval = val.sval;
  dup.obj = val.obj;
  return dup;
}
//#### end semantic value section ####
public final static short NUMBER=257;
public final static short AND=258;
public final static short DUAL=259;
public final static short DUALASSGN=260;
public final static short BOOL=261;
public final static short IDENTIFIER=262;
public final static short IF=263;
public final static short WHILE=264;
public final static short SKIP=265;
public final static short THEN=266;
public final static short ELSE=267;
public final static short FI=268;
public final static short DO=269;
public final static short END=270;
public final static short DONE=271;
public final static short READ=272;
public final static short WRITE=273;
public final static short BEGIN=274;
public final static short ASSGNOP=275;
public final static short YYERRCODE=256;
final static short yylhs[] = {                           -1,
    3,    0,    2,    2,    6,    1,    4,    4,    4,    4,
    4,    7,    4,    8,    4,    9,   10,    4,    5,    5,
    5,    5,    5,    5,    5,    5,    5,    5,    5,   11,
   11,   11,
};
final static short yylen[] = {                            2,
    0,    4,    0,    3,    0,    5,    1,    2,    2,    3,
    5,    0,    5,    0,    3,    0,    0,    7,    1,    1,
    1,    3,    3,    3,    3,    3,    3,    3,    3,    1,
    3,    3,
};
final static short yydefred[] = {                         0,
    1,    0,    3,    0,    0,    0,    0,   16,    7,    2,
    0,    0,    0,    0,    0,    0,   19,   30,   21,    0,
    0,    0,    0,    8,    0,   12,    0,    4,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    3,   15,    0,   29,   32,    0,    0,    0,
    0,    0,    0,    0,    3,    0,   31,    0,    0,    0,
    0,    0,    3,   13,    0,   18,
};
final static short yydgoto[] = {                          2,
   13,    4,    3,   14,   21,   40,   43,   27,   23,   58,
   22,
};
final static short yysindex[] = {                      -264,
    0,    0,    0, -153, -247, -238,  -35,    0,    0,    0,
 -223,  -35, -226,  -12, -210,  -35,    0,    0,    0,  -35,
   38, -189,  -35,    0,   38,    0, -192,    0, -177,   38,
   32,   -7,  -35,  -35,  -35,  -35,  -35,  -35,  -35, -182,
  -37,   38,    0,    0,  -35,    0,    0,    6,    6,   -6,
   -6,   38,   38,   38,    0,  -37,    0, -181, -139,   38,
 -111,   -7,    0,    0, -125,    0,
};
final static short yyrindex[] = {                         0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0, -178,    0,    0,    0,    0,    0,    0,    0,
 -171,  -41,    0,    0,   37,    0,    0,    0,    0,   42,
    0,   44,    0,    0,    0,    0,    0,    0,    0,    0,
    0, -172,    0,    0,    0,    0,    0,  -29,  -14,  -34,
  -19,  -24,   -9,   -8,    0,    0,    0,    0,    0,   43,
 -224,    0,    0,    0,    0,    0,
};
final static short yygindex[] = {                         0,
    0,   15,    0,    0,   26,    0,    0,    0,    0,    0,
   16,
};
final static int YYTABLESIZE=261;
static short yytable[];
static { yytable();}
static void yytable(){
yytable = new short[]{                         20,
   20,   20,   56,   20,   20,   20,   27,   27,   27,    1,
   27,   26,   27,   26,   15,   26,   22,   20,   20,   20,
   20,   28,   28,   28,   27,   28,   25,   28,   25,   26,
   25,   23,   24,   47,   22,   32,   16,   25,   24,   28,
   26,   30,    6,    6,   25,   31,   28,   35,   42,   23,
   24,   29,   36,   37,   38,   39,   57,   59,   48,   49,
   50,   51,   52,   53,   54,   37,   38,   39,   41,   61,
   60,   62,   46,   35,   34,   44,   33,   65,   36,   35,
   34,   45,   33,   55,   36,   20,   20,   63,   20,   14,
   20,   37,   38,   39,    5,    9,   17,   37,   38,   39,
   10,   11,    0,   20,   20,   20,    5,    0,    6,    7,
    8,    9,    0,    0,    0,    0,   10,    0,   11,   12,
    5,    0,    6,    7,    8,    9,    0,    0,   64,    0,
    0,    0,   11,   12,    5,    0,    6,    7,    8,    9,
    0,    0,    0,    0,    0,   66,   11,   12,    5,    0,
    6,    7,    8,    9,    0,    0,    0,    0,    0,    0,
   11,   12,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,   17,    0,   18,   20,   18,   19,   20,    0,    0,
    0,   27,    0,    0,   27,    0,   26,    0,    0,   26,
    0,   22,    0,    0,   22,    0,   28,    0,    0,   28,
   41,   25,    0,    0,   25,    0,   23,   24,    0,   23,
   24,
};
}
static short yycheck[];
static { yycheck(); }
static void yycheck() {
yycheck = new short[] {                         41,
   42,   43,   40,   45,   40,   47,   41,   42,   43,  274,
   45,   41,   47,   43,  262,   45,   41,   59,   60,   61,
   62,   41,   42,   43,   59,   45,   41,   47,   43,   59,
   45,   41,   41,   41,   59,   20,  275,   12,  262,   59,
  267,   16,  267,  268,   59,   20,   59,   42,   23,   59,
   59,  262,   47,   60,   61,   62,   41,   43,   33,   34,
   35,   36,   37,   38,   39,   60,   61,   62,  258,   55,
   45,   56,   41,   42,   43,  268,   45,   63,   47,   42,
   43,  259,   45,  266,   47,   42,   43,  269,   45,  268,
   47,   60,   61,   62,  266,   59,  269,   60,   61,   62,
   59,   59,   -1,   60,   61,   62,  260,   -1,  262,  263,
  264,  265,   -1,   -1,   -1,   -1,  270,   -1,  272,  273,
  260,   -1,  262,  263,  264,  265,   -1,   -1,  268,   -1,
   -1,   -1,  272,  273,  260,   -1,  262,  263,  264,  265,
   -1,   -1,   -1,   -1,   -1,  271,  272,  273,  260,   -1,
  262,  263,  264,  265,   -1,   -1,   -1,   -1,   -1,   -1,
  272,  273,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,
   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,
   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,
   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,
   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,
   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,
   -1,  257,   -1,  261,  266,  261,  262,  269,   -1,   -1,
   -1,  266,   -1,   -1,  269,   -1,  266,   -1,   -1,  269,
   -1,  266,   -1,   -1,  269,   -1,  266,   -1,   -1,  269,
  258,  266,   -1,   -1,  269,   -1,  266,  266,   -1,  269,
  269,
};
}
final static short YYFINAL=2;
final static short YYMAXTOKEN=275;
final static String yyname[] = {
"end-of-file",null,null,null,null,null,null,null,null,null,null,null,null,null,
null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,
null,null,null,null,null,null,null,null,null,null,"'('","')'","'*'","'+'",null,
"'-'",null,"'/'",null,null,null,null,null,null,null,null,null,null,null,"';'",
"'<'","'='","'>'",null,null,null,null,null,null,null,null,null,null,null,null,
null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,
null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,
null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,
null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,
null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,
null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,
null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,
null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,
null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,
null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,
null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,
null,null,null,null,null,null,"NUMBER","AND","DUAL","DUALASSGN","BOOL",
"IDENTIFIER","IF","WHILE","SKIP","THEN","ELSE","FI","DO","END","DONE","READ",
"WRITE","BEGIN","ASSGNOP",
};
final static String yyrule[] = {
"$accept : program",
"$$1 :",
"program : BEGIN $$1 commands END",
"commands :",
"commands : commands command ';'",
"$$2 :",
"ifThen : IF exp $$2 THEN commands",
"command : SKIP",
"command : READ IDENTIFIER",
"command : WRITE exp",
"command : IDENTIFIER ASSGNOP exp",
"command : DUALASSGN IDENTIFIER IDENTIFIER DUAL exp",
"$$3 :",
"command : ifThen ELSE $$3 commands FI",
"$$4 :",
"command : ifThen $$4 FI",
"$$5 :",
"$$6 :",
"command : WHILE $$5 exp $$6 DO commands DONE",
"exp : NUMBER",
"exp : boolean_exp",
"exp : IDENTIFIER",
"exp : exp '<' exp",
"exp : exp '=' exp",
"exp : exp '>' exp",
"exp : exp '+' exp",
"exp : exp '-' exp",
"exp : exp '*' exp",
"exp : exp '/' exp",
"exp : '(' exp ')'",
"boolean_exp : BOOL",
"boolean_exp : boolean_exp AND boolean_exp",
"boolean_exp : '(' boolean_exp ')'",
};

//#line 86 "simple.y"


public enum I { HALT, STORE, JMP_FALSE, GOTO,
DATA, LD_INT, LD_VAR,
READ_INT, WRITE_INT,
LT, EQ, GT, ADD, SUB, MULT, DIV };

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
 //System.out.println("[Quit with CTRL-D]");
 Parser par = new Parser();
 lexer = new Yylex(new InputStreamReader(System.in), par);
 par.yyparse();
 print_code();
}

private static String text(I istr)
{
String names[]=
{ "HALT", "STORE", "JMP_FALSE", "GOTO",
"DATA", "LD_INT", "LD_VAR",
"READ_INT", "WRITE_INT",
"LT", "EQ", "GT", "ADD", "SUB", "MULT", "DIV" };
 return ""+istr.ordinal()+"/"+names[istr.ordinal()];
}
private static I[] code_op= new I[999];
private static int[] code_arg= new int[999];
private static boolean[] code_mark= new boolean[999];
private static int stC =0;
public static void print_code()
{
 I istr; int arg;
 int i; for (i=0;i<stC;++i)
 {
  istr=code_op[i];arg=code_arg[i];
  System.out.println(""+(i)+"-"+text(istr)+" "+arg);
  if(code_mark[i])System.out.println();
 }
}
public static void gen_code(I istr, int arg)
{
 code_op[stC]=istr;code_arg[stC]=arg;
 stC++;//System.out.println(""+(stC++)+"-"+text(istr)+" "+arg);
}
public static void mark_blank()
{
 code_mark[stC-1]=true;
}
public static int gen_label()
{
 return stC;
}
public static int reserve_loc()
{
 //System.out.println(""+(stC)+"-");
 return stC++;
}
public static void back_patch(int addr, I istr, int arg)
{
 code_op[addr]=istr;code_arg[addr]=arg;
 //System.out.println(addr+":"+text(istr)+" "+arg);
}
/*=========================================================================
  symbol:   method gen_code(I,int)
  symbol:   method gen_label()
  symbol:   method reserve_loc()
  symbol:   method back_patch(int,I,int)
{ print_code ();
fetch_execute_cycle(); }
**************************** End Grammar File ***************************/
//#line 371 "Parser.java"
//###############################################################
// method: yylexdebug : check lexer state
//###############################################################
void yylexdebug(int state,int ch)
{
String s=null;
  if (ch < 0) ch=0;
  if (ch <= YYMAXTOKEN) //check index bounds
     s = yyname[ch];    //now get it
  if (s==null)
    s = "illegal-symbol";
  debug("state "+state+", reading "+ch+" ("+s+")");
}





//The following are now global, to aid in error reporting
int yyn;       //next next thing to do
int yym;       //
int yystate;   //current parsing state from state table
String yys;    //current token string


//###############################################################
// method: yyparse : parse input and execute indicated items
//###############################################################
int yyparse()
{
boolean doaction;
  init_stacks();
  yynerrs = 0;
  yyerrflag = 0;
  yychar = -1;          //impossible char forces a read
  yystate=0;            //initial state
  state_push(yystate);  //save it
  val_push(yylval);     //save empty value
  while (true) //until parsing is done, either correctly, or w/error
    {
    doaction=true;
    if (yydebug) debug("loop"); 
    //#### NEXT ACTION (from reduction table)
    for (yyn=yydefred[yystate];yyn==0;yyn=yydefred[yystate])
      {
      if (yydebug) debug("yyn:"+yyn+"  state:"+yystate+"  yychar:"+yychar);
      if (yychar < 0)      //we want a char?
        {
        yychar = yylex();  //get next token
        if (yydebug) debug(" next yychar:"+yychar);
        //#### ERROR CHECK ####
        if (yychar < 0)    //it it didn't work/error
          {
          yychar = 0;      //change it to default string (no -1!)
          if (yydebug)
            yylexdebug(yystate,yychar);
          }
        }//yychar<0
      yyn = yysindex[yystate];  //get amount to shift by (shift index)
      if ((yyn != 0) && (yyn += yychar) >= 0 &&
          yyn <= YYTABLESIZE && yycheck[yyn] == yychar)
        {
        if (yydebug)
          debug("state "+yystate+", shifting to state "+yytable[yyn]);
        //#### NEXT STATE ####
        yystate = yytable[yyn];//we are in a new state
        state_push(yystate);   //save it
        val_push(yylval);      //push our lval as the input for next rule
        yychar = -1;           //since we have 'eaten' a token, say we need another
        if (yyerrflag > 0)     //have we recovered an error?
           --yyerrflag;        //give ourselves credit
        doaction=false;        //but don't process yet
        break;   //quit the yyn=0 loop
        }

    yyn = yyrindex[yystate];  //reduce
    if ((yyn !=0 ) && (yyn += yychar) >= 0 &&
            yyn <= YYTABLESIZE && yycheck[yyn] == yychar)
      {   //we reduced!
      if (yydebug) debug("reduce");
      yyn = yytable[yyn];
      doaction=true; //get ready to execute
      break;         //drop down to actions
      }
    else //ERROR RECOVERY
      {
      if (yyerrflag==0)
        {
        yyerror("syntax error");
        yynerrs++;
        }
      if (yyerrflag < 3) //low error count?
        {
        yyerrflag = 3;
        while (true)   //do until break
          {
          if (stateptr<0)   //check for under & overflow here
            {
            yyerror("stack underflow. aborting...");  //note lower case 's'
            return 1;
            }
          yyn = yysindex[state_peek(0)];
          if ((yyn != 0) && (yyn += YYERRCODE) >= 0 &&
                    yyn <= YYTABLESIZE && yycheck[yyn] == YYERRCODE)
            {
            if (yydebug)
              debug("state "+state_peek(0)+", error recovery shifting to state "+yytable[yyn]+" ");
            yystate = yytable[yyn];
            state_push(yystate);
            val_push(yylval);
            doaction=false;
            break;
            }
          else
            {
            if (yydebug)
              debug("error recovery discarding state "+state_peek(0)+" ");
            if (stateptr<0)   //check for under & overflow here
              {
              yyerror("Stack underflow. aborting...");  //capital 'S'
              return 1;
              }
            state_pop();
            val_pop();
            }
          }
        }
      else            //discard this token
        {
        if (yychar == 0)
          return 1; //yyabort
        if (yydebug)
          {
          yys = null;
          if (yychar <= YYMAXTOKEN) yys = yyname[yychar];
          if (yys == null) yys = "illegal-symbol";
          debug("state "+yystate+", error recovery discards token "+yychar+" ("+yys+")");
          }
        yychar = -1;  //read another
        }
      }//end error recovery
    }//yyn=0 loop
    if (!doaction)   //any reason not to proceed?
      continue;      //skip action
    yym = yylen[yyn];          //get count of terminals on rhs
    if (yydebug)
      debug("state "+yystate+", reducing "+yym+" by rule "+yyn+" ("+yyrule[yyn]+")");
    if (yym>0)                 //if count of rhs not 'nil'
      yyval = val_peek(yym-1); //get current semantic value
    yyval = dup_yyval(yyval); //duplicate yyval if ParserVal is used as semantic value
    switch(yyn)
      {
//########## USER-SUPPLIED ACTIONS ##########
case 1:
//#line 29 "simple.y"
{ gen_code( I.DATA, 10 );mark_blank(); }
break;
case 2:
//#line 31 "simple.y"
{ gen_code( I.HALT, -99 );mark_blank();
      /*System.exit(0);*/ }
break;
case 4:
//#line 35 "simple.y"
{mark_blank();}
break;
case 5:
//#line 38 "simple.y"
{ val_peek(1).ival/*for_jmp_false*/ = reserve_loc();mark_blank(); }
break;
case 6:
//#line 39 "simple.y"
{ yyval.ival = val_peek(4).ival; }
break;
case 8:
//#line 42 "simple.y"
{ gen_code( I.READ_INT,  -99 ); 
                     gen_code( I.STORE,  val_peek(0).ival ); }
break;
case 9:
//#line 44 "simple.y"
{ gen_code( I.WRITE_INT, -99  ); }
break;
case 10:
//#line 45 "simple.y"
{ gen_code( I.STORE, val_peek(2).ival ); }
break;
case 11:
//#line 46 "simple.y"
{gen_code( I.STORE, val_peek(3).ival); gen_code( I.LD_VAR, val_peek(3).ival); gen_code( I.STORE, val_peek(2).ival); }
break;
case 12:
//#line 48 "simple.y"
{ val_peek(1).ival/*for_goto*/ += 1000*reserve_loc();mark_blank(); /*RISERVAperJdopoIF*/
         back_patch( val_peek(1).ival%1000/*for_jmp_false*/, /*DEFINISCEilJdopoThen*/
                     I.JMP_FALSE, gen_label() ); }
break;
case 13:
//#line 52 "simple.y"
{ back_patch( (int)val_peek(4).ival/1000/*for_goto*/, /*DEFINISCEilJdopoIF*/
                     I.GOTO, gen_label() ); }
break;
case 14:
//#line 54 "simple.y"
{ val_peek(0).ival/*for_goto*/ += 1000*reserve_loc();mark_blank(); /*RISERVAperJdopoIF*/
         back_patch( val_peek(0).ival%1000/*for_jmp_false*/, /*DEFINISCEilJdopoThen*/
                     I.JMP_FALSE, gen_label() ); }
break;
case 15:
//#line 57 "simple.y"
{ back_patch( (int)val_peek(2).ival/1000/*for_goto*/, /*DEFINISCEilJdopoIF*/
                     I.GOTO, gen_label() ); }
break;
case 16:
//#line 59 "simple.y"
{ val_peek(0).ival/*for_goto*//*quiSaltoVSquiScrivo*/ = 1000*gen_label(); }
break;
case 17:
//#line 60 "simple.y"
{ val_peek(2).ival/*for_jmp_false*/ += reserve_loc();mark_blank(); }
break;
case 18:
//#line 63 "simple.y"
{ gen_code( I.GOTO, (int)val_peek(6).ival/1000/*for_goto*/ );
        back_patch( val_peek(6).ival%1000/*for_jmp_false*/,
                    I.JMP_FALSE, gen_label() ); }
break;
case 19:
//#line 68 "simple.y"
{ gen_code( I.LD_INT, val_peek(0).ival ); }
break;
case 21:
//#line 70 "simple.y"
{ gen_code( I.LD_VAR, val_peek(0).ival ); }
break;
case 22:
//#line 71 "simple.y"
{ gen_code( I.LT, -99 ); }
break;
case 23:
//#line 72 "simple.y"
{ gen_code( I.EQ, -99 ); }
break;
case 24:
//#line 73 "simple.y"
{ gen_code( I.GT, -99 ); }
break;
case 25:
//#line 74 "simple.y"
{ gen_code( I.ADD, -99 ); }
break;
case 26:
//#line 75 "simple.y"
{ gen_code( I.SUB, -99 ); }
break;
case 27:
//#line 76 "simple.y"
{ gen_code( I.MULT, -99 ); }
break;
case 28:
//#line 77 "simple.y"
{ gen_code( I.DIV/*.ordinal()*/, -99 ); }
break;
case 30:
//#line 81 "simple.y"
{ gen_code( I.LD_INT, val_peek(0).ival ); }
break;
case 31:
//#line 82 "simple.y"
{ gen_code( I.MULT, -99 ); }
break;
//#line 638 "Parser.java"
//########## END OF USER-SUPPLIED ACTIONS ##########
    }//switch
    //#### Now let's reduce... ####
    if (yydebug) debug("reduce");
    state_drop(yym);             //we just reduced yylen states
    yystate = state_peek(0);     //get new state
    val_drop(yym);               //corresponding value drop
    yym = yylhs[yyn];            //select next TERMINAL(on lhs)
    if (yystate == 0 && yym == 0)//done? 'rest' state and at first TERMINAL
      {
      if (yydebug) debug("After reduction, shifting from state 0 to state "+YYFINAL+"");
      yystate = YYFINAL;         //explicitly say we're done
      state_push(YYFINAL);       //and save it
      val_push(yyval);           //also save the semantic value of parsing
      if (yychar < 0)            //we want another character?
        {
        yychar = yylex();        //get next character
        if (yychar<0) yychar=0;  //clean, if necessary
        if (yydebug)
          yylexdebug(yystate,yychar);
        }
      if (yychar == 0)          //Good exit (if lex returns 0 ;-)
         break;                 //quit the loop--all DONE
      }//if yystate
    else                        //else not done yet
      {                         //get next state and push, for next yydefred[]
      yyn = yygindex[yym];      //find out where to go
      if ((yyn != 0) && (yyn += yystate) >= 0 &&
            yyn <= YYTABLESIZE && yycheck[yyn] == yystate)
        yystate = yytable[yyn]; //get new state
      else
        yystate = yydgoto[yym]; //else go to new defred
      if (yydebug) debug("after reduction, shifting from state "+state_peek(0)+" to state "+yystate+"");
      state_push(yystate);     //going again, so push state & val...
      val_push(yyval);         //for next action
      }
    }//main loop
  return 0;//yyaccept!!
}
//## end of method parse() ######################################



//## run() --- for Thread #######################################
/**
 * A default run method, used for operating this parser
 * object in the background.  It is intended for extending Thread
 * or implementing Runnable.  Turn off with -Jnorun .
 */
public void run()
{
  yyparse();
}
//## end of method run() ########################################



//## Constructors ###############################################
/**
 * Default constructor.  Turn off with -Jnoconstruct .

 */
public Parser()
{
  //nothing to do
}


/**
 * Create a parser, setting the debug to true or false.
 * @param debugMe true for debugging, false for no debug.
 */
public Parser(boolean debugMe)
{
  yydebug=debugMe;
}
//###############################################################



}
//################### END OF CLASS ##############################
