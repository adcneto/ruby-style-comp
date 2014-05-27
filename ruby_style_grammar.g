class RubyStyleParser extends Parser;
{
  Symbol symbol;
	SymbolTable table;
	Program program;
	ConditionVerifier cv;
	ExpressionCalculator ec;
	String type;

	public void init(){
	  table = new SymbolTable();
		program = new Program();
	}
	
	public void execute(){
	   program.run();
	}
}

program		:		"begin" (declare)* commands "end"
					;

declare		:		(type T_id {
												    String id = LT(0).getText();
														if (table.getById(id) != null){
															String errorMsg = "Variavel " + id + " ja foi declarada";
													  	
													    throw new RecognitionException(errorMsg);
														}
														else{
													  	table.add(new Symbol(id, 0, false, type));
														}
											 	 }

							(T_comma T_id {
													    id = LT(0).getText();
															if (table.getById(id) != null){
																String errorMsg = "Variavel " + id + " ja foi declarada";
														  	
														    throw new RecognitionException(errorMsg);
															}
															else{
														  	table.add(new Symbol(id, 0, false, type));
															}
												 	 }
							)*)
					;

type 			:		("int"     { type = Symbol.INT; }
						 	| "string" { type = Symbol.STRING; })
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
										  
										  throw new RecognitionException(errorMsg);
										} else {
											program.add(cmdAttr);
										}
									} 

							Op_attr expr {
								if (symbol != null && symbol.isInt()){
									cmdAttr.setExpressionCalculator(ec);
								}
							}
					;

// expr : (T_num | T_id | T_text) (Op_arit (T_num | T_id))*
expr			:		(T_num {
											if(symbol.isInt()){
												ec = new ExpressionCalculator();
												int num = Integer.parseInt(LT(0).getText());
												ec.values.add(num);
											} else {
												String errorMsg = "Variavel nao e um inteiro";
								  			throw new RecognitionException(errorMsg);			
											}
										}
							|
							T_id{
								Symbol rightSymbol = table.getById(LT(0).getText());
								if (rightSymbol == null){
							  	String errorMsg = "Variavel nao foi declarada";
								  throw new RecognitionException(errorMsg);
								} else if(rightSymbol.getType().equals(symbol.getType())) {
									ec = new ExpressionCalculator();
									ec.values.add(rightSymbol);
								} else {
									String errorMsg = "Tipos incompativeis";
								  throw new RecognitionException(errorMsg);
								}
							}
							|
							T_text{
								if(symbol.isString()){
									symbol.setStringValue(LT(0).getText());
								} else {
									String errorMsg = "Variavel nao e uma string";
								  throw new RecognitionException(errorMsg);			
								} 
							})
							(Op_arit { ec.operators.add(LT(0).getText()); }
								(T_num{ 
									int num = Integer.parseInt(LT(0).getText());
									ec.values.add(num);
							  }
							  |
							  T_id{
							 		Symbol rightSymbol = table.getById(LT(0).getText());
									if (symbol == null){
								  	String errorMsg = "Variavel nao foi declarada";
									  throw new RecognitionException(errorMsg);
									} else if(rightSymbol.isInt()) {
										ec.values.add(rightSymbol);
									} else {
										String errorMsg = "Variavel nao e um inteiro";
								  	throw new RecognitionException(errorMsg);
									} 	
							  })
								)* {
										if(ec != null)
											ec.operators.add(null);
									 }
							
					;

cmdWrite	:		"puts" (T_id {
												    symbol = table.getById(LT(0).getText());
														if (symbol == null){
													  	String errorMsg = "Variavel nao foi declarada";
														  
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