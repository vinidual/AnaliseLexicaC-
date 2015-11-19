package br.com.Test;
import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.Test;

import br.com.Model.FileCheck;
import br.com.Model.FileScanner;
import br.com.Model.LexicalAnalysis;

public class LexicalAnalysisTDD {
	
	private FileCheck file;
	private FileScanner fs = new FileScanner();
	private LexicalAnalysis la = new LexicalAnalysis();
	private ArrayList<String> expected = new ArrayList<String>();
	
	@Test
	public void fileInexistent() {
		file = new FileCheck("file");
		assertFalse(file.exists());
	}
	
	@Test
	public void fileExist() {
		file = new FileCheck("src/testSuccess.cm");
		assertTrue(file.exists());
	}

	
	@Test
	public void fileExtensionInvalid() {
		file = new FileCheck("testInvalid.txt");
		assertFalse(file.verifyExtension());
	}
	
	@Test
	public void fileExtensionValid() {
		file = new FileCheck("testValid.cm");
		assertTrue(file.verifyExtension());
	}
	
	@Test
	public void fileScannerCreated() {
		file = new FileCheck("src/file.cm");
		assertTrue(fs.createFileScanner(file.getFile()));
	}
	
	@Test
	public void validCaracterVectorReaded(){
		char[] expected = {'1','2','3',';'};
		file = new FileCheck("src/testNumValid.cm");
		fs.createFileScanner(file.getFile());
		assertArrayEquals(expected, fs.readFile());
	}
	
	@Test
	public void validTokenReserved(){
		file = new FileCheck("src/file.cm");
		fs.createFileScanner(file.getFile());
		assertTrue(la.checkTokenReserved("int"));
	}
	
	@Test
	public void lexicalError1() {
		file = new FileCheck("src/file.cm");
		fs.createFileScanner(file.getFile());
		char[] buffer = {'#','$','a','\n'};
		la.lexicalAnalysis(buffer);
		expected.clear();
		expected.add("ERR");
		expected.add("ERR");
		expected.add("ID");
		assertEquals(expected, la.getResult());
	}
	
	@Test
	public void lexicalError2() {
		file = new FileCheck("src/file.cm");
		fs.createFileScanner(file.getFile());
		char[] buffer = {'#','$', '~','a','\n','&'};
		la.lexicalAnalysis(buffer);
		expected.clear();
		expected.add("ERR");
		expected.add("ERR");
		expected.add("ID");
		System.out.println(la.getError());
		assertFalse(la.getError().compareTo("Successfull Analysis")==0);
	}
	
	@Test
	public void successfullAnalysis1() {
		file = new FileCheck("src/file.cm");
		fs.createFileScanner(file.getFile());
		char[] buffer = fs.readFile();
		la.lexicalAnalysis(buffer);
		expected.clear();
		expected.add("ID");
		expected.add("ATR");
		expected.add("NUM");
		expected.add("COMPT");
		assertEquals(expected, la.getResult());
	}
	
	@Test
	public void successfullAnalysis2() {
		file = new FileCheck("src/file.cm");
		fs.createFileScanner(file.getFile());
		char[] buffer = fs.readFile();
		la.lexicalAnalysis(buffer);
		assertTrue(la.getError().compareTo("Successfull Analysis")==0);
	}
	
	
}
