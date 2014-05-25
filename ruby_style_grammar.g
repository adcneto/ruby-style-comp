class RubyStyleParser extends Parser;
{
  Symbol symbol;
	SymbolTable table;
	Program program;
	
	public void init(){
	  table = new SymbolTable();
		program = new Program();
	}
	
	public void execute(){
	   program.run();
	}
}

program		:		"begin" {System.out.println("Entrou no begin");} (declare)* commands "end"
					;

declare		:		(type T_id {
												    String id = LT(0).getText();
														if (table.getById(id) != null){
															String errorMsg = "Variavel " + id + " ja foi declarada";
													  	System.err.println(errorMsg);
													    throw new RecognitionException(errorMsg);
														}
														else{
													  	table.add(new Symbol(id, 0, false));
														}
											 	 }

							(T_comma T_id {
													    id = LT(0).getText();
															if (table.getById(id) != null){
																String errorMsg = "Variavel " + id + " ja foi declarada";
														  	System.err.println(errorMsg);
														    throw new RecognitionException(errorMsg);
															}
															else{
														  	table.add(new Symbol(id, 0, false));
															}
												 	 }
							)*)
					;

type 			:		"int" | "string" | "float"
					;

commands	:		(command)*
					;

command		:		cmdAttr | cmdWrite | cmdRead | cmdIf | cmdWhile 
					;

cmdAttr		:		T_id Op_attr expr
					;

expr			:		term (Op_arit term)*
					;

term 			:		T_id | T_num | T_text
					;

cmdWrite	:		"puts" term
					;

cmdRead		:		"gets" T_id {
													  symbol = table.getById(LT(0).getText());
													  if (symbol == null){
													  	String errorMsg = "Variavel nao foi declarada";
														  System.err.println(errorMsg);
														  throw new RecognitionException(errorMsg);
														}
														else{
														  program.add(new CommandRead(symbol));
														}
													}
					;

cmdIf 		:		"if" cond commands ("else" commands)* "endif"
					;

cmdWhile	:		"while" commands "endwhile"
					;


cond 		:		term (Op_rel term)+
			;
 

class RubyStyleLexer extends Lexer;

options
{
  caseSensitive = true;
  charVocabulary = '\0'..'\377';
  k =2;
}

T_id		:		('a'..'z')('a'..'z'|'A'..'Z'|'0'..'9')*
			;

T_num		:		('0'..'9')+
			;

T_text		:		'"' ('a'..'z'|'A'..'Z'|'0'..'9'|' ')* '"'
			;
			
T_comma 	: 		','
			;

Op_attr		:		":="
			;

Op_arit		:		'+' | '-' | '*' | '/'
			;

Op_rel		:		'<' | '>' | "<=" | ">=" | "==" | "!=" 
			;

T_ws    	:		('\n' | '\r' | '\t' | ' ') { $setType(Token.SKIP); }
			;