// $ANTLR 2.7.6 (2005-12-22): "ruby_style_grammar.g" -> "RubyStyleParser.java"$

import antlr.TokenBuffer;
import antlr.TokenStreamException;
import antlr.TokenStreamIOException;
import antlr.ANTLRException;
import antlr.LLkParser;
import antlr.Token;
import antlr.TokenStream;
import antlr.RecognitionException;
import antlr.NoViableAltException;
import antlr.MismatchedTokenException;
import antlr.SemanticException;
import antlr.ParserSharedInputState;
import antlr.collections.impl.BitSet;

public class RubyStyleParser extends antlr.LLkParser       implements RubyStyleParserTokenTypes
 {

  Symbol symbol;
	SymbolTable table;
	Program program;
	boolean condition = false;

	public void init(){
	  table = new SymbolTable();
		program = new Program();
	}
	
	public void execute(){
	   program.run();
	}

protected RubyStyleParser(TokenBuffer tokenBuf, int k) {
  super(tokenBuf,k);
  tokenNames = _tokenNames;
}

public RubyStyleParser(TokenBuffer tokenBuf) {
  this(tokenBuf,1);
}

protected RubyStyleParser(TokenStream lexer, int k) {
  super(lexer,k);
  tokenNames = _tokenNames;
}

public RubyStyleParser(TokenStream lexer) {
  this(lexer,1);
}

public RubyStyleParser(ParserSharedInputState state) {
  super(state,1);
  tokenNames = _tokenNames;
}

	public final void program() throws RecognitionException, TokenStreamException {
		
		
		try {      // for error handling
			match(LITERAL_begin);
			System.out.println("Entrou no begin");
			{
			_loop3:
			do {
				if (((LA(1) >= LITERAL_int && LA(1) <= LITERAL_float))) {
					declare();
				}
				else {
					break _loop3;
				}
				
			} while (true);
			}
			commands();
			match(LITERAL_end);
		}
		catch (RecognitionException ex) {
			reportError(ex);
			recover(ex,_tokenSet_0);
		}
	}
	
	public final void declare() throws RecognitionException, TokenStreamException {
		
		
		try {      // for error handling
			{
			type();
			match(T_id);
			
															    String id = LT(0).getText();
																	if (table.getById(id) != null){
																		String errorMsg = "Variavel " + id + " ja foi declarada";
																  	System.err.println(errorMsg);
																    throw new RecognitionException(errorMsg);
																	}
																	else{
																  	table.add(new Symbol(id, 0, false));
																	}
														 	
			{
			_loop7:
			do {
				if ((LA(1)==T_comma)) {
					match(T_comma);
					match(T_id);
					
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
				else {
					break _loop7;
				}
				
			} while (true);
			}
			}
		}
		catch (RecognitionException ex) {
			reportError(ex);
			recover(ex,_tokenSet_1);
		}
	}
	
	public final void commands() throws RecognitionException, TokenStreamException {
		
		
		try {      // for error handling
			{
			_loop11:
			do {
				if ((_tokenSet_2.member(LA(1)))) {
					command();
				}
				else {
					break _loop11;
				}
				
			} while (true);
			}
		}
		catch (RecognitionException ex) {
			reportError(ex);
			recover(ex,_tokenSet_3);
		}
	}
	
	public final void type() throws RecognitionException, TokenStreamException {
		
		
		try {      // for error handling
			switch ( LA(1)) {
			case LITERAL_int:
			{
				match(LITERAL_int);
				break;
			}
			case LITERAL_string:
			{
				match(LITERAL_string);
				break;
			}
			case LITERAL_float:
			{
				match(LITERAL_float);
				break;
			}
			default:
			{
				throw new NoViableAltException(LT(1), getFilename());
			}
			}
		}
		catch (RecognitionException ex) {
			reportError(ex);
			recover(ex,_tokenSet_4);
		}
	}
	
	public final void command() throws RecognitionException, TokenStreamException {
		
		
		try {      // for error handling
			switch ( LA(1)) {
			case T_id:
			{
				cmdAttr();
				break;
			}
			case LITERAL_puts:
			{
				cmdWrite();
				break;
			}
			case LITERAL_gets:
			{
				cmdRead();
				break;
			}
			case LITERAL_if:
			{
				cmdIf();
				break;
			}
			case LITERAL_while:
			{
				cmdWhile();
				break;
			}
			default:
			{
				throw new NoViableAltException(LT(1), getFilename());
			}
			}
		}
		catch (RecognitionException ex) {
			reportError(ex);
			recover(ex,_tokenSet_5);
		}
	}
	
	public final void cmdAttr() throws RecognitionException, TokenStreamException {
		
		
		try {      // for error handling
			match(T_id);
			
											    symbol = table.getById(LT(0).getText());
											    CommandAttr cmdAttr = new CommandAttr(symbol);
													if (symbol == null){
												  	String errorMsg = "Variavel nao foi declarada";
													  System.err.println(errorMsg);
													  throw new RecognitionException(errorMsg);
													} else {
														program.add(cmdAttr);
													}
												
			match(Op_attr);
			expr();
			
											if (symbol != null){
												cmdAttr.setValue(LT(0).getText());
											}
										
		}
		catch (RecognitionException ex) {
			reportError(ex);
			recover(ex,_tokenSet_5);
		}
	}
	
	public final void cmdWrite() throws RecognitionException, TokenStreamException {
		
		
		try {      // for error handling
			match(LITERAL_puts);
			{
			switch ( LA(1)) {
			case T_id:
			{
				match(T_id);
				
																    symbol = table.getById(LT(0).getText());
																		if (symbol == null){
																	  	String errorMsg = "Variavel nao foi declarada";
																		  System.err.println(errorMsg);
																		  throw new RecognitionException(errorMsg);
																		}
																		else{
																	  	program.add(new CommandWrite(symbol));
																		}
																	
				break;
			}
			case T_num:
			{
				match(T_num);
				program.add(new CommandWrite(LT(0).getText()));
				break;
			}
			case T_text:
			{
				match(T_text);
				program.add(new CommandWrite(LT(0).getText()));
				break;
			}
			default:
			{
				throw new NoViableAltException(LT(1), getFilename());
			}
			}
			}
		}
		catch (RecognitionException ex) {
			reportError(ex);
			recover(ex,_tokenSet_5);
		}
	}
	
	public final void cmdRead() throws RecognitionException, TokenStreamException {
		
		
		try {      // for error handling
			match(LITERAL_gets);
			match(T_id);
			
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
		catch (RecognitionException ex) {
			reportError(ex);
			recover(ex,_tokenSet_5);
		}
	}
	
	public final void cmdIf() throws RecognitionException, TokenStreamException {
		
		
		try {      // for error handling
			match(LITERAL_if);
			cond();
			System.out.println(condition);
			commands();
			{
			_loop24:
			do {
				if ((LA(1)==LITERAL_else)) {
					match(LITERAL_else);
					commands();
				}
				else {
					break _loop24;
				}
				
			} while (true);
			}
			match(LITERAL_endif);
		}
		catch (RecognitionException ex) {
			reportError(ex);
			recover(ex,_tokenSet_5);
		}
	}
	
	public final void cmdWhile() throws RecognitionException, TokenStreamException {
		
		
		try {      // for error handling
			match(LITERAL_while);
			commands();
			match(LITERAL_endwhile);
		}
		catch (RecognitionException ex) {
			reportError(ex);
			recover(ex,_tokenSet_5);
		}
	}
	
	public final void expr() throws RecognitionException, TokenStreamException {
		
		
		try {      // for error handling
			term();
			{
			_loop16:
			do {
				if ((LA(1)==Op_arit)) {
					match(Op_arit);
					term();
				}
				else {
					break _loop16;
				}
				
			} while (true);
			}
		}
		catch (RecognitionException ex) {
			reportError(ex);
			recover(ex,_tokenSet_5);
		}
	}
	
	public final void term() throws RecognitionException, TokenStreamException {
		
		
		try {      // for error handling
			{
			switch ( LA(1)) {
			case T_id:
			{
				match(T_id);
				break;
			}
			case T_num:
			{
				match(T_num);
				break;
			}
			case T_text:
			{
				match(T_text);
				break;
			}
			default:
			{
				throw new NoViableAltException(LT(1), getFilename());
			}
			}
			}
		}
		catch (RecognitionException ex) {
			reportError(ex);
			recover(ex,_tokenSet_6);
		}
	}
	
	public final void cond() throws RecognitionException, TokenStreamException {
		
		
		try {      // for error handling
			match(T_id);
			
												condition = false;
										    symbol = table.getById(LT(0).getText());
										    ConditionVerifier cv;
										    System.out.println(symbol.getValue());
												if (symbol == null){
											  	String errorMsg = "Variavel nao foi declarada";
												  System.err.println(errorMsg);
												  throw new RecognitionException(errorMsg);
												} else {
													 cv = new ConditionVerifier(symbol.getValue());
												}
											
			match(Op_rel);
			
										if (symbol != null){
											cv.setOperator(LT(0).getText());
										}
									
			match(T_id);
			
										Symbol symbolRight = table.getById(LT(0).getText());
										if (symbol != null && symbolRight != null){
											cv.setRight(symbolRight.getValue());
											condition = cv.verify();
										}
									
		}
		catch (RecognitionException ex) {
			reportError(ex);
			recover(ex,_tokenSet_7);
		}
	}
	
	
	public static final String[] _tokenNames = {
		"<0>",
		"EOF",
		"<2>",
		"NULL_TREE_LOOKAHEAD",
		"\"begin\"",
		"\"end\"",
		"T_id",
		"T_comma",
		"\"int\"",
		"\"string\"",
		"\"float\"",
		"Op_attr",
		"Op_arit",
		"T_num",
		"T_text",
		"\"puts\"",
		"\"gets\"",
		"\"if\"",
		"\"else\"",
		"\"endif\"",
		"\"while\"",
		"\"endwhile\"",
		"Op_rel",
		"T_ws"
	};
	
	private static final long[] mk_tokenSet_0() {
		long[] data = { 2L, 0L};
		return data;
	}
	public static final BitSet _tokenSet_0 = new BitSet(mk_tokenSet_0());
	private static final long[] mk_tokenSet_1() {
		long[] data = { 1279840L, 0L};
		return data;
	}
	public static final BitSet _tokenSet_1 = new BitSet(mk_tokenSet_1());
	private static final long[] mk_tokenSet_2() {
		long[] data = { 1278016L, 0L};
		return data;
	}
	public static final BitSet _tokenSet_2 = new BitSet(mk_tokenSet_2());
	private static final long[] mk_tokenSet_3() {
		long[] data = { 2883616L, 0L};
		return data;
	}
	public static final BitSet _tokenSet_3 = new BitSet(mk_tokenSet_3());
	private static final long[] mk_tokenSet_4() {
		long[] data = { 64L, 0L};
		return data;
	}
	public static final BitSet _tokenSet_4 = new BitSet(mk_tokenSet_4());
	private static final long[] mk_tokenSet_5() {
		long[] data = { 4161632L, 0L};
		return data;
	}
	public static final BitSet _tokenSet_5 = new BitSet(mk_tokenSet_5());
	private static final long[] mk_tokenSet_6() {
		long[] data = { 4165728L, 0L};
		return data;
	}
	public static final BitSet _tokenSet_6 = new BitSet(mk_tokenSet_6());
	private static final long[] mk_tokenSet_7() {
		long[] data = { 2064448L, 0L};
		return data;
	}
	public static final BitSet _tokenSet_7 = new BitSet(mk_tokenSet_7());
	
	}
