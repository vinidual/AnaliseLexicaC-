# LexicalAnalysisC-

Trabalho realizado para a disciplina de Validação e Verificação de Software ministrada pelo professor Dr. Fábio Fagundes Silveira na Unifesp campus de São José dos Campos. Atividade realizada pelos alunos:

  - Karen Saori Suzuki
  - Vinícius Duarte de Almeida

LexicalAnalysisC- é um programa em Java para realizar a análise léxica da [linguagem C-](http://www.cs.dartmouth.edu/~cs57/Project/C-%20Spec.pdf) implementado com metodologia TDD utilizando o framework [JUnit4](http://junit.org/).

O objetivo desse trabalho é colocar em prática o TDD para entender seu funcionamento e filosofia no desenvolvimento de software. Para seu desenvolvimento foi utilizado a IDE [Eclipse](https://projects.eclipse.org/releases/mars) Mars.

## O Problema

A atividade proposta trata de analisar os lexemas de um arquivo de entrada e atribuir marcas, ou Tokens, a esses lexemas, possibilitando encontrar erros léxicos, se houver, no arquivo e informando ao usuário o resultado da análise.

**Arquivo lexicamente correto**: será exibida a mensagem `Successfull Analysis`.

**Arquivo lexicamente incorreto**: serão exibidas mensagens `identificando` os lexemas inválidos
e `localizando` o número da linha em que o evento ocorreu.

## Requisitos

**Arquivo de entrada**: o nome do arquivo deve ser de `extensão` necessariamente `.cm`

**Sintaxe da Linguagem C-**: a análise léxica deve se basear na sintaxe da linguagem C- tendo como 
referência as condições abaixo.

  - As palavras reservadas da linguagem são as seguintes:
  
```
if    else    int   void    while   return
```

  - Os símbolos especiais da linguagem são:
  
```
+   -   *   /   <   <=    >   >=    ==    !=    =   ;   ,   (   )   [   ]   {   }   /*    */
```

  - Símbolos referente espaços em branco no arquivo:
  
```
\n    \r    [ ]
```

  - Os marcadores de identificador e de número são definidos pelas expressões regulares a seguir:
   
```
identificador (ID): letra+
número (NUM): dígito+
letra: a|..|z|A|..|Z
dígito: 0|..|9
```

Nesse projeto utilizaremos uma variante do `identificador` para que seja aceito identificadores alfanuméricos, porém que iniciem com letras, assim a expressão regular correspondente é modificada para:

```
identificador (ID): letra.(letra|digito)*
```

Qualquer lexema lido que não se enquadre nos mencionados acima será tratado como erro.

## Estrutura

As classes java do programa estão organizadas em duas pastas, `src/br.com.Model` e `test/br.com.Test`.

Na primeira temos os seguintes arquivos:

- `FileCheck`: classe responsável por verificar se tal arquivo a ser lido existe e validar a extensão.
  
- `FileScanner`: classe responsável por executar os procedimentos de leitura dos caracteres do arquivo
  e armazená-los em um *buffer*, após validação feita em `FileCheck`.
  
- `LexicalAnalysis`: classe responsável por varrer o *buffer* criado e verificar os lexemas atribuindo marcas, ou *Tokens*, após executação dos processos em `FileScanner`.

Na segunda temos:

- `LexicalAnalysisTDD`: classe responsável por implementar as *tasks* elaboradas para abordar o comportamentos dos métodos utilizados no programa.

As demais pastas referenciam biliotecas (JRE e JUnit) e arquivos de configuração do programa.

## *Tasks*

São tarefas pensadas antes de implementar o código dos testes, servem para manter um lembrete do que deve ser implementado. 

Após concluir um número satisfatório de *tasks* iniciamos sua implementação no arquivo `LexicalAnalysisTDD` gerando somente código necessário na aplicação. Além disso, concretizamos nossos casos de teste implementando as *tasks* como métodos da classe `LexicalAnalysisTDD`.

As *tasks* criadas foram as seguintes:

- *fileInexistent*: método para verificar existência do arquivo de entrada.

- *fileExist*: método para verificar a inexistência do arquivo de entrada.

- *fileExtensionInvalid*: método para testar o comportamento do programa com arquivos de extensão inválida.

- *fileExtensionValid*: método para testar o comportamento do programa com arquivos de extensão válida.

- *fileScannerCreated*: método para verificar se foi possível criar o arquivo de leitura da entrada.

- *validCaracterVectorReaded*: método para verificar se o *buffer* para armazenar os caracteres foi criado corretamente.

- *validTokenReserved*: método para determinar se um lexema é identificado corretamente como uma palavra reservada.

- *lexicalError*: método para testar erros léxicos que podem ocorrer.

- *successfullAnalysis*: método para verificar se um arquivo de entrada realmente está correto.

## Desenvolvimento ##

O objetivo do TDD é gerar código a partir dos testes, assim descreveremos como ocorreu esse desenvolvimento.

### Teste arquivo Existente/Inexistente ###

Testes que verificam se o arquivo de entrada existe no diretório do projeto.

**Teste arquivo inexistente**

Nesse teste o programa envia uma mensagem de erro `file not found`.

Começamos criando o teste para um nome de arquivo inexistente:

      @Test
      public void fileInexistent() {
      	File file = new FileCheck("file");
      	assertFalse(file.exists());
      }

Rodamos o teste e encontramos o seguinte erro: 
`Não existe classe FileCheck`.

Criamos a classe `FileCheck` em src/br/com/Model:

      public class FileCheck {
      	private String filename;
      	private File file;
      	
      	public FileCheck(String filename){
      		this.filename = filename;
      		this.file = new File(filename);
      	}
      }

Executamos o teste novamente, obtendo o erro:
`Não foi definido o método exists(String) na classe FileCheck`.

Implementamos o método `exists()`:

      public boolean exists() {
        if (file.exists()){
          return true;
        }
        else{
          System.out.println("File not found");
          return false;
        }
      }
    
Rodamos o teste e ele funcionou corretamente, retornando `file not found`.

**Teste arquivo existente**

Para esse teste colocamos um arquivo no projeto, `testSucess.cm`, na pasta `src` e testamos se o método nos dá o resultado esperado

com o seguinte código:

      @Test
      public void fileExist() {
        File file;
        file = new FileCheck("src/testSuccess.cm");
        assertTrue(file.exists());
      }

Rodamos e verificamos sucesso no teste.

Podemos refatorar os testes, movendo a declaração de `File file` como objeto do escopo global da classe de teste. Assim não precisamos toda vez ter que declará-los. Assim ficamos com o seguinte código:

      @Test
    	public void fileInexistent(){
    		file = new FileCheck("file");
    		assertFalse(file.exists());
    	}
    	@Test
    	public void fileExist() {
    		file = new FileCheck("src/testSuccess.cm");
    		assertTrue(file.exists());
    	}

### Teste arquivo de extensão valida/inválida ###

Nesses testes o programa verificara se o arquivo é de extensão `.cm` como declarado nos requisitos.

Caso não seja, o programa envia uma mensagem de erro `Please, enter a file with .cm extension`.

Para esses testes, entramos com uma `String` qualquer com uma extensão, não importando se o arquivo exista ou não, já que esse teste só verificará a extensão.

**Teste arquivo de extensão invalida**

Implementamos o teste:

      @Test
      public void fileExtensionInvalid() {
          filename = "testInvalid.txt";
          file = new FileCheck();
          assertFalse(file.verifyExtension(filename));
      }

Verificamos que existe o seguinte erro:
`Não foi definido o método verifyExtension(String) na classe FileCheck`.

Implementamos na classe `FileCheck` o seguinte código:

      public boolean verifyExtension(String filename) {
          if(filename.endsWith(".cm"))
              return true;
          System.out.println("Please, enter a file with .cm extension");
          return false;
      }

Podemos refatorar a classe `FileCheck`, já que os métodos desta usa `String filename` como parâmetro, assim não precisamos toda vez ter que inseri-lo. Podemos montar um construtor da classe com esse parâmetro e declarar a `String` como objeto da classe:

      public class FileCheck {
          private String filename;
          
          public FileCheck(String filename){
              this.filename = filename;
          }
          
          public boolean exists() {
              File file = new File(filename);
              if (file.exists())
                  return true;
              else{
                  System.out.println("File not found");
                  return false;
              }
          }
          
          public boolean verifyExtension() {
              if(filename.endsWith(".cm"))
                  return true;
              System.out.println("Please, enter a file with .cm extension");
              return false;
          }
      }

E modificamos no teste:

      private FileCheck file;
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
    
Além disso podemos tirar a `String filename` e inserir a `String` do teste diretamente no construtor, pois ela só é usada para colocar no construtor:

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
    
Fazemos também a alteração no construtor da classe.

**Teste arquivo extensão válida**

      @Test
      public void fileExtensionValid() {
          file = new FileCheck("testValid.cm");
          assertTrue(file.verifyExtension());
      }

Rodamos e não foram encontrados erros.

### Teste de criação do arquivo de varredura (Scanner) ###

Esse teste verifica se ocorreu a criação do arquivo de varredura da entrada para posteriormente criar o *buffer* de caracteres a serem analisados.

A implementação do teste:

      @Test
      public void fileScannerCreated() {
          file = new FileCheck("src/file.cm");
          assertTrue(fs.createFileScanner(file.getFile()));
      }
    
O objeto `fs` se refere à classe `FileScanner` e como provavelmente utilizaremos para outros testes resolvemos criar o objeto no escopo global da classe de teste.

Houve a necessidade de criar o método `getFile()` na classe `FileCheck` para usarmos a referencia do arquivo de entrada como parâmetro.

Criamos o método `getFile` em `FileCheck`:

      public File getFile(){
          File file = new File(filename);
          return file;
      }

Ainda na classe `File Check`:

Podemos refatorar o código já que os métodos `exists()` e o `getFile()` criam um objeto `File`. Assim declaramos como objeto global da classe e modificamos o construtor e os métodos `exists()` e `getFile()`:

      private File file;
      public FileCheck(String filename){
          this.filename = filename;
          this.file = new File(filename);
      }
      public boolean exists() {
          if (file.exists())
              return true;
          else{
              System.out.println("File not found");
              return false;
          }
      }
      public File getFile(){
          return file;
      }

### Teste de validação do arquivo de varredura criado ###

Verifica se o arquivo foi lido e criado conforme o esperado:

      @Test
    	public void validCaracterVectorReaded(){
    		char[] expected = {'1','2','3',';'};
    		file = new FileCheck("src/testNumValid.cm");
    		fs.createFileScanner(file.getFile());
    		assertArrayEquals(expected, fs.readFile());
    	}

Foi necessário criar o método `readFile()` na classe `FileScanner`:

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

### Teste para verificação de *Tokens* ###

      @Test
      public void validTokenReserved(){
      	file = new FileCheck("src/file.cm");
      	fs.createFileScanner(file.getFile());
      	assertTrue(la.checkTokenReserved("int"));
      }
    
Foi necessário implementar a classe `LexicalAnalysis` que é responsável por realizar toda a análise léxica, que é instânciada pelo objeto `la`, que foi declarado no escopo global da classe de teste.

A implementação inicial da classe `LexicalAnalysis`:

      public class LexicalAnalysis {
        public boolean checkTokenReserved( String str ) {
      		if( str.compareTo("if") == 0 ||
      			str.compareTo("else") == 0 ||
      			str.compareTo("int") == 0 ||
      			str.compareTo("void") == 0 ||
      			str.compareTo("while") == 0 ||
      			str.compareTo("return") == 0 
      		)
      			return true;
      		return false;
      	}
      }
    
### Teste de erro léxico ###

Verifica se há ocorrência do *Token* `ERR` no arquivo de entrada.
```
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
```    	
Foram necessárias diversas adições à classe `LexicalAnalysis` que resultaram no arquivo atual.

`expected` é um `ArrayList<String>`.

O método `getResult()` retorna um `ArrayList<String>` com todos os lexemas do arquivo de entrada.

O método `getError()` retorna uma `String` com todos os erros léxicos do arquivo de entrada ou a mensagem
`Successfull Analysis` caso contrário.

### Teste de análise com sucesso ###

Teste para verificar se retorna a mensagem `Successfull Analysis` caso o arquivo esteja lexicamente correto.
```
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
```
Na classe `LexicalAnalysis` o método `lexicalAnalysis(char[] buffer)` inicia a análise para o *buffer*

O método `readChar(String lex)` analisa cada caracter covertido em `String`.

Os demais métodos `letterReaded(String lex)`, `numberReaded(String lex)`, `symbolReaded(String lex)` e `checkLastLexema()` auxiliam no processo.




















