import java.util.ArrayList;

public class LexicalAnalysis {
	
	private String lexema = "";
	
	@SuppressWarnings("unused")
	private int numLines = 0;
	
	@SuppressWarnings("rawtypes")
	private ArrayList result = new ArrayList();
	
	private enum Token {
		ID, NUM, RESERVED,
		SUM, SUB, MULT, DIV,
		LOW, GRE, LOWE, GREE,
		EQL, DIF,
		ATR,
		COM, COMPT,
		OPR, CPR, OBR, CBR, OKE, CKE,
		OCM, CCM,
		ERR
	}
	
	Token token;
	
	public boolean checkTokenReserved( String str ) {
		if( str.compareTo("if") == 0 ||
			str.compareTo("else") == 0 ||
			str.compareTo("int") == 0 ||
			str.compareTo("void") == 0 ||
			str.compareTo("while") == 0 ||
			str.compareTo("return") == 0 
		)
			return true;
		return false;
	}
	
	public void readChar( String lex ){
		if( lex.matches("[a-zA-Z]") ){
			letterReaded(lex);
		}
		else if( lex.matches("[0-9]") ){
			numberReaded(lex);
		}
		else if( lex.matches("\\+") ){
			symbolReaded("SUM");
		}
		else if( lex.matches("-") ){
			symbolReaded("SUB");
		}
		else if( lex.matches("\\*") ){
			symbolReaded("MULT");
		}
		else if( lex.matches("/") ){
			symbolReaded("DIV");
		}
		else if( lex.matches("=") ){
			if( token == Token.ATR ){
				symbolReaded("EQL");
				token = null;
			}	
			else if( token == Token.DIF ){
				symbolReaded("DIF");
				token = null;
			}
			else if( token == Token.LOW ){
				symbolReaded("LOWE");
				token = null;
			}
			else if( token == Token.GRE ){
				symbolReaded("GREE");
				token = null;
			}
			else {
				symbolReaded("ATR");
				token = Token.ATR;
			}
		}
		else if( lex.matches("<") ){
			if( token != Token.LOW ){
				token = Token.LOW;
			}
			else {
				symbolReaded("ERR");
			}
		}
		else if( lex.matches(">") ){
			if( token != Token.GRE ){
				token = Token.GRE;
			}
			else {
				symbolReaded("ERR");
			}
		}
		else if( lex.matches("!") ){
			if( token != Token.DIF ){
				token = Token.DIF;
			}
			else {
				symbolReaded("ERR");
			}
		}
		else if( lex.matches("\\(") ){
			symbolReaded("OPR");
		}
		else if( lex.matches("\\)") ){
			symbolReaded("CPR");
		}
		else if( lex.matches("\\[") ){
			symbolReaded("OBR");
		}
		else if( lex.matches("\\]") ){
			symbolReaded("CBR");
		}
		else if( lex.matches("\\{") ){
			symbolReaded("OKE");
		}
		else if( lex.matches("\\}") ){
			symbolReaded("CKE");
		}
		else if( lex.matches(",") ){
			symbolReaded("COM");
		}
		else if( lex.matches(";") ){
			symbolReaded("COMPT");
		}
		else if( lex.matches(" ") ){
			//nothing
		}
		else if( lex.matches("\n") ){
			numLines += 1;
		}
		else {
			symbolReaded("ERR");
		}
	}
	
	@SuppressWarnings("unchecked")
	public void letterReaded(String lex){
		if( lexema.matches("[0-9]+") ){
			lexema = lex;
			token = Token.NUM;
			result.add("NUM");
		}
		else if( checkTokenReserved(lexema) ){
			lexema = lex;
			token = Token.RESERVED;
			result.add("RESERVED");
		}
		else {
			lexema += lex;
			token = Token.ID;
		}
	}
	
	@SuppressWarnings("unchecked")
	public void numberReaded(String lex){
		if( lexema.matches("[a-zA-Z]+") ){
			lexema += lex;
			token = Token.ID;
		}
		else if( checkTokenReserved(lexema) ){
			lexema = lex;
			token = Token.RESERVED;
			result.add("RESERVED");
		}
		else {
			lexema += lex;
			token = Token.NUM;
		}
	}
	
	@SuppressWarnings("unchecked")
	public void symbolReaded(String symbol){
		if( token == Token.NUM )
			result.add("NUM");
		if( token == Token.ID )
			result.add("ID");
		result.add(symbol);
		lexema = "";
	}

	public Object getResult() {
		return result;
	}
	
}
