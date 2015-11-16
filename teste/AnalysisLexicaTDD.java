import static org.junit.Assert.*;
import org.junit.Test;


public class AnalysisLexicaTDD {
	FileCm file;
	
	@Test
	public void fileInexistent() {
		file = new FileCm("file");
		assertFalse(file.exists());
	}	
	
	@Test
	public void fileExist() {
		file = new FileCm("src/testSuccess.cm");
		assertTrue(file.exists());
	}
	
	@Test
	public void fileExtensionInvalid() {
		file = new FileCm("src/test.txt");
		assertTrue(file.exists());
		assertFalse(file.verifyExtension());
	}
	
	@Test
	public void fileExtensionValid() {
		file = new FileCm("src/testSuccess.cm");
		assertTrue(file.valid());
	}
	
	@Test
	public void convertCharValid(){
		char []expecteds = {'1','2','3',';',10};
		file = new FileCm("src/testNumValid.cm");
		assertArrayEquals(expecteds, file.convertVectorChar());
	}
	
}
