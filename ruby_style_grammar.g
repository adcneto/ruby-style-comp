class RubyStyleParser extends Parser;

program		:		"begin" (declare)* commands end
					;

end 			:		"end"
					;

declare		:		(type T_id (T_comma T_id)*)*
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

cmdIf 		:		"if" cond commands ("else" commands)* end
					;

cmdWhile	:		"while" commands end
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

T_text	:		'\"' ('a'..'z'|'A'..'Z'|'0'..'9'|' ')* '\"'
				;

Op_attr	:		'='
				;

Op_arit	:		'+' | '-' | '*' | '/'
				;

Op_rel	:		'<' | '>' | "<=" | ">=" | "==" | "!="
				;