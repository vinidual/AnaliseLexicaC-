import java.io.FileNotFoundException;
import java.io.FileReader;


public class Arquivo {
	
	private static String nome;
	private static String extensao;
	private FileReader arq;

	public void criaArquivo(String nomeDeArquivo) {
		int indice = Math.abs(nomeDeArquivo.lastIndexOf("."));
		nome = nomeDeArquivo.substring(0, indice);
		extensao = nomeDeArquivo.substring(indice+1);
	}

	public boolean extensaoValida() {
		try{
			if( extensao.compareTo("cm") == 0 )
				return true;
		} catch( NullPointerException ex ){
			System.out.println("Extensao é nula");
		}
		return false;
	}
	
	public String retornaExtensao(){
		return extensao;
	}
	
	public String retornaNome(){
		return nome;
	}

	public boolean nomeValido() {
		System.out.println(nome);
		if( nome.isEmpty() ||  !nome.matches("[a-zA-Z0-9]+") )
			return false;
		return true;
	}

	public boolean arquivoExiste() {
		try {
			String nomeDoArquivo = nome + "." + extensao;
			arq = new FileReader( nomeDoArquivo );
		} catch( FileNotFoundException ex ){
			System.out.println( ex.getMessage() );
			return false;
		}
		return true;
	}
	
	

}
