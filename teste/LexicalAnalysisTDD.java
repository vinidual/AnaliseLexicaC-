import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.Test;

public class LexicalAnalysisTDD {
	
	private String dir;
	private String filename;
	private FileCheck file = new FileCheck();
	private FileScanner fs = new FileScanner();
	
	@Test
	public void invalidExtensionFile() {
		dir = "src";
		filename = "file.";
		file.createFile(filename, dir);
		assertFalse(file.validExtension());
	}
	
	@Test
	public void validExtensionFile() {
		dir = "src";
		filename = ".cc.cm";
		file.createFile(filename, dir);
		assertTrue(file.validExtension());
	}

	@Test
	public void invalidFileName() {
		dir = "src";
		filename = "#.cm";
		file.createFile(filename, dir);
		assertFalse(file.validName());
	}
	
	@Test
	public void validFileName() {
		dir = "src";
		filename = "qweasdzxc256.cm";
		file.createFile(filename, dir);
		assertTrue(file.validName());
	}
	
	@Test
	public void existFile() { 
		dir = "src";
		filename = "file.cm";
		file.createFile(filename, dir);
		assertTrue(file.existFile());
	}
	
	@Test
	public void notExistFile() {
		dir = "src";
		filename = "analise.cm";
		file.createFile(filename, dir);
		assertFalse(file.existFile());
	}
	
	@Test
	public void fileScannerCreated() {
		dir = "src";
		filename = "file.cm";
		file.createFile(filename, dir);
		assertTrue(fs.createFileScanner(file.getFile()));
	}
	
	@Test
	public void validCaracterVectorReaded(){
		dir = "src";
		filename = "testNumValid.cm";
		char[] expected = {'1','2','3',';'};
		file.createFile(filename, dir);
		fs.createFileScanner(file.getFile());
		assertArrayEquals(expected, fs.readFile());
	}
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Test
	public void validTokenReserved(){
		dir = "src";
		filename = "file.cm";
		file.createFile(filename, dir);
		fs.createFileScanner(file.getFile());
		char[] buffer = fs.readFile();
		LexicalAnalysis la = new LexicalAnalysis();
		for( int i=0; i < buffer.length; i++ ){
			la.readChar(Character.toString(buffer[i]));
		}
		ArrayList expected = new ArrayList();
		expected.add("ID");
		expected.add("ATR");
		expected.add("NUM");
		expected.add("COMPT");
		assertEquals(expected, la.getResult());
	}
	
	public void lexicalError() {
		
	}
	
	public void successfullAnalysis() {
	
	}
	
	
}
