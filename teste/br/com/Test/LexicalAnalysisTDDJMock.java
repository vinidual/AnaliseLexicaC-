package br.com.Test;

/*
import static org.junit.Assert.assertEquals;

import java.util.ArrayList;

import org.jmock.Expectations;
import org.jmock.integration.junit4.*;
import org.junit.Rule;
import org.junit.Test;

import br.com.Model.FileCheck;
import br.com.Model.FileScanner;
import br.com.Model.LexicalAnalysis;

public class LexicalAnalysisTDDJMock {
	
	private String dir;
	private String filename;
	
	private FileCheck file = new FileCheck();
	private FileScanner fs = new FileScanner();
	private LexicalAnalysis la = new LexicalAnalysis();
	private ArrayList<String> expected = new ArrayList<String>();
	
	@Rule 
	public JUnitRuleMockery ctx = new JUnitRuleMockery();
	
	/*@Test
	public void invalidExtensionFile() {
		dir = "src";
		filename = "file.";
		FileCheckInterface mock = ctx.mock(FileCheckInterface.class);
		file.createFile(filename, dir);
		ctx.checking(new Expectations(){{
			oneOf(mock).validExtension();
		}});
		file.validExtension();
	}
	

}
*/
