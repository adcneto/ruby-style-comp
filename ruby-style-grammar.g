class RubyStyleParser extends Parser;

program		:		"begin" (declare)* commands end
					;

end 			:		"end"
					;

declare		:		(type T_id (T_comma Tid)*)*
					;

type 			:		"int" | "string" | "float"
					;

commands	:		(command)*
					;

command		:		cmdAttr | cmdWrite | cmdRead | cmdIf | cmdWhile | cmdDo
					;

cmdAttr		:		T_id Op_attr expr
					;

expr			:		term (aritOp term)*
					;

aritOp		:		Op_plus | Op_minus | Op_multi | Op_div
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

cond 			:		term (logOp term)+
					;

logOp			:		Op_equals | Op_least | Op_greater | Op_diff | Op_leq | Op_get
					;