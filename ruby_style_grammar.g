class RubyStyleParser extends Parser;
{
  Symbol symbol;
	SymbolTable table;
	Program program;
	ConditionVerifier cv;

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

cmdAttr		:		T_id{
								    symbol = table.getById(LT(0).getText());
								    CommandAttr cmdAttr = new CommandAttr(symbol);
										if (symbol == null){
									  	String errorMsg = "Variavel nao foi declarada";
										  System.err.println(errorMsg);
										  throw new RecognitionException(errorMsg);
										} else {
											program.add(cmdAttr);
										}
									} 

							Op_attr expr {
								if (symbol != null){
									cmdAttr.setValue(LT(0).getText());
								}
							}
					;

expr			:		term (Op_arit term)*
					;

term 			:		(T_id | T_num | T_text)
					;

cmdWrite	:		"puts" (T_id {
												    symbol = table.getById(LT(0).getText());
														if (symbol == null){
													  	String errorMsg = "Variavel nao foi declarada";
														  System.err.println(errorMsg);
														  throw new RecognitionException(errorMsg);
														}
														else{
													  	program.add(new CommandWrite(symbol));
														}
													}
														

										| T_num  { program.add(new CommandWrite(LT(0).getText())); }
										| T_text { program.add(new CommandWrite(LT(0).getText())); }
										)
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

cmdIf 		:		"if" cond {
													CommandIf cmd = new CommandIf(cv);
													program.add(cmd);
													program.setCurrentBlock(cmd);
													program.setCurrentScope("if");
												} 
							commands 
							("else" { program.setCurrentScope("else"); }
							commands)* 
							"endif" { 
												program.setCurrentBlock(null); 
												program.setCurrentScope("");
											}
					;

cmdWhile	:		"while" cond {
													CommandWhile cmd = new CommandWhile(cv);
													program.add(cmd);
													program.setCurrentBlock(cmd);
													program.setCurrentScope("while");
												}
							commands 
							"endwhile"  { 
														program.setCurrentBlock(null); 
														program.setCurrentScope("");
													}
					;


cond 		  :		(T_id{
							    symbol = table.getById(LT(0).getText());
									if (symbol == null){
								  	String errorMsg = "Variavel nao foi declarada";
									  System.err.println(errorMsg);
									  throw new RecognitionException(errorMsg);
									} else {
										 cv = new ConditionVerifier("0", symbol);
									}
								}
								|
								T_num {cv = new ConditionVerifier(LT(0).getText(), null);}
								)  
						Op_rel{
							if (symbol != null){
								cv.setOperator(LT(0).getText());
							}
						} 
						(T_id{
							Symbol symbolRight = table.getById(LT(0).getText());
							if (symbol != null && symbolRight != null){
								cv.setRight("0", symbolRight);
							}
						}
						|
						T_num {cv.setRight(LT(0).getText(), null);}
						)
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