import static org.junit.Assert.*;

import java.util.Scanner;

import org.junit.Test;

public class AnaliseLexicaTDD {
	
	private String nomeDeArquivo;
	private Arquivo arquivo = new Arquivo();
	
	@Test
	public void arquivoExtensaoInvalida() {
		nomeDeArquivo = "arquivo.";
		arquivo.criaArquivo(nomeDeArquivo);
		assertFalse(arquivo.extensaoValida());
	}
	
	@Test
	public void arquivoExtensaoValida() { 
		nomeDeArquivo = ".cc.cm";
		arquivo.criaArquivo(nomeDeArquivo);
		assertTrue(arquivo.extensaoValida());
	}

	@Test
	public void arquivoNomeInvalido() { 
		nomeDeArquivo = "#.cm";
		arquivo.criaArquivo(nomeDeArquivo);
		assertFalse(arquivo.nomeValido());
	}
	
	@Test
	public void arquivoNomeValido() { 
		nomeDeArquivo = "qweasdzxc256.cm";
		arquivo.criaArquivo(nomeDeArquivo);
		assertTrue(arquivo.nomeValido());
	}
	
	@Test
	public void arquivoExistente() { 
		nomeDeArquivo = "src/arquivo.cm";
		arquivo.criaArquivo(nomeDeArquivo);
		assertTrue(arquivo.arquivoExiste());
	}
	
	@Test
	public void arquivoInexistente() { 
		nomeDeArquivo = "src/analise.cm";
		arquivo.criaArquivo(nomeDeArquivo);
		assertFalse(arquivo.arquivoExiste());
	}
	
	
	
}
