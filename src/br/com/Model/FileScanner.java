package br.com.Model;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class FileScanner {

	private static File f;
	private static FileReader rf;
	private static BufferedReader br;
	private char []cbuffer;
	
	public boolean createFileScanner( File file ){
		try {
			f = file;
			rf = new FileReader(f);
			br = new BufferedReader(rf);
			return true;
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		return false;
	}
	
	public char[] readFile(){
		try {
			cbuffer = new char [(int) f.length()];
			br.read(cbuffer);
			return cbuffer;
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}
	
}
