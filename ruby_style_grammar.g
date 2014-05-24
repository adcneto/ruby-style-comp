class RubyStyleParser extends Parser;

program		:		"begin" {System.out.println("Entrou no begin");} (declare)* commands "end"
					;

declare		:		(type T_id (T_comma T_id)*)
					;

type 			:		"int" | "string" | "float"
					;

commands	:		(command)*
					;

command		:		cmdAttr | cmdWrite | cmdRead | cmdIf | cmdWhile | cmdDo
					;

cmdAttr		:		T_id Op_attr expr
					;

expr			:		term (Op_arit term)*
					;

term 			:		T_id | T_num | T_text
					;

cmdWrite	:		"puts" (term | expr)+
					;

cmdRead		:		"gets" T_id
					;

cmdIf 		:		"if" cond commands ("else" commands)* "endif"
					;

cmdWhile	:		"while" commands "endwhile"
					;

cmdDo			:		"do" commands "while" cond
					;

cond 			:		term (Op_rel term)+
					;
 

class RubyStyleLexer extends Lexer;


T_id		:		('a'..'z')('a'..'z'|'A'..'Z'|'0'..'9')*
				;

T_num		:		('0'..'9')+
				;

T_comma : 	','
				;

T_text	:		'\"' ('a'..'z'|'A'..'Z'|'0'..'9'|' ')* '\"'
				;

Op_attr	:		'='
				;

Op_arit	:		'+' | '-' | '*' | '/'
				;

Op_rel	:		'<' | '>' | "<=" | ">=" | "==" | "!="
				;

T_ws    :		('\n' | '\r' | '\t' | ' ') { $setType(Token.SKIP); }
				;