package br.com.Test;
import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.Test;

import br.com.Model.FileCheck;
import br.com.Model.FileScanner;
import br.com.Model.LexicalAnalysis;

public class LexicalAnalysisTDD {
	
	private String dir;
	private String filename;
	private FileCheck file = new FileCheck();
	private FileScanner fs = new FileScanner();
	private LexicalAnalysis la = new LexicalAnalysis();
	private ArrayList<String> expected = new ArrayList<String>();
	
	public void invalidExtensionFile1() {
		dir = "src";
		filename = null;
		file.createFile(filename, dir);
		file.validExtension();
		assertFalse(file.validExtension());
	}
	
	@Test
	public void invalidExtensionFile2() {
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
	
	@Test
	public void validTokenReserved(){
		dir = "src";
		filename = "file.cm";
		file.createFile(filename, dir);
		fs.createFileScanner(file.getFile());
		assertTrue(la.checkTokenReserved("int"));
	}
	
	@Test
	public void lexicalError1() {
		dir = "src";
		filename = "file.cm";
		file.createFile(filename, dir);
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
		dir = "src";
		filename = "file.cm";
		file.createFile(filename, dir);
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
		dir = "src";
		filename = "file.cm";
		file.createFile(filename, dir);
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
		dir = "src";
		filename = "testSuccess.cm";
		file.createFile(filename, dir);
		fs.createFileScanner(file.getFile());
		char[] buffer = fs.readFile();
		la.lexicalAnalysis(buffer);
		assertTrue(la.getError().compareTo("Successfull Analysis")==0);
	}
	
	
}
