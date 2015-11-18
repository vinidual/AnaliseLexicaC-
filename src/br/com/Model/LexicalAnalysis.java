package br.com.Model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class LexicalAnalysis {
	
	private static String lexema = "";
	
	private static int numLines = 1;
	
	private static ArrayList<String> result = new ArrayList<String>();
	
	private static Map<String,String> error = new HashMap<String,String>();
	
	private static enum Token {
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
	
	public void lexicalAnalysis( char[] buffer ){
		result.clear();
		for( int i=0; i < buffer.length; i++ ){
			this.readChar(Character.toString(buffer[i]));
		}
		this.checkLastLexema();
	}
	
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
				error.put("<<", ""+numLines);
			}
		}
		else if( lex.matches(">") ){
			if( token != Token.GRE ){
				token = Token.GRE;
			}
			else {
				symbolReaded("ERR");
				error.put(">>", ""+numLines);
			}
		}
		else if( lex.matches("!") ){
			if( token != Token.DIF ){
				token = Token.DIF;
			}
			else {
				symbolReaded("ERR");
				error.put("!!", ""+numLines);
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
		else if( lex.matches("[ |\t|\"|\'|\r]") ){
			//nothing
		}
		else if( lex.matches("\n") ){
			numLines += 1;
		}
		else {
			symbolReaded("ERR");
			error.put(lex, ""+numLines);
		}
	}
	
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
	
	public void symbolReaded(String symbol){
		if( token == Token.NUM )
			result.add("NUM");
		if( token == Token.ID )
			result.add("ID");
		result.add(symbol);
		lexema = "";
	}
	
	public void checkLastLexema(){
		if( lexema.matches("[a-zA-Z]+.*") )
			result.add("ID");
		else
			if( lexema.matches("[0-9]+") )
				result.add("NUM");
	}

	public Object getResult() {
		return result;
	}
	
	public String getError(){
		String error_log = "Successfull Analysis";
		if( error.size() > 0 ){
			error_log = "";
			for( String key : error.keySet() ){
				error_log += "Syntax Error: character '" + key + "' at line '" + error.get(key) + "'.\n";
			}
		}
		return error_log;
	}
	
}
