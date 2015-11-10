import static org.junit.Assert.*;

import java.util.Scanner;

import org.junit.Test;

public class AnaliseLexicaTDD {
	
	@Test
	public void ArquivoExtensaoInvalida() { // 1
		String nomeDeArquivo = "arquivo";
		Arquivo arquivo = new Arquivo();
		arquivo.criaArquivo(nomeDeArquivo);
		assertFalse(arquivo.extensaoValida());
	}
	
	@Test
	public void ArquivoExtensaoValida() { // 1
		String nomeDeArquivo = ".cc.cm";
		Arquivo arquivo = new Arquivo();
		arquivo.criaArquivo(nomeDeArquivo);
		assertTrue(arquivo.extensaoValida());
	}

}
