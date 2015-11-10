
public class Arquivo {
	
	private String nome;
	private String extensao;

	public void criaArquivo(String nomeDeArquivo) {
		int indice = Math.abs(nomeDeArquivo.lastIndexOf("."));
		this.nome = nomeDeArquivo.substring(0, indice);
		this.extensao = nomeDeArquivo.substring(indice+1);
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
		return this.extensao;
	}
	
	public String retornaNome(){
		return this.nome;
	}

}
