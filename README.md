# LexicalAnalysisC-

Trabalho realizado para a disciplina de Validação e Verificação de Software ministrada pelo professor Dr. Fábio Fagundes Silveira na Unifesp campus de São José dos Campos. Atividade realizada pelos alunos:

  - Karen Saori Suzuki
  - Vinícius Duarte de Almeida

LexicalAnalysisC- é um programa em Java para realizar a análise léxica da [linguagem C-](http://www.cs.dartmouth.edu/~cs57/Project/C-%20Spec.pdf) implementado com metodologia TDD utilizando o framework [JUnit4](http://junit.org/).

O objetivo desse trabalho é por em prática o TDD para entender seu funcionamento e filosofia no desenvolvimento de software. Para seu desenvolvimento foi utilizado a IDE [Eclipse](https://projects.eclipse.org/releases/mars) Mars

## O Problema

A atividade proposta trata de analisar os lexemas de um arquivo de entrada e atribuir marcas, ou Tokens, a esses lexemas, possibilitando encontrar erros léxicos, se houver, no arquivo e informando ao usuário o resultado da análise.

**Arquivo lexicamente correto**: será exibida a mensagem `Successfull Analysis`.

**Arquivo lexicamente incorreto**: serão exibidas mensagens `identificando` os lexemas inválidos
e `localizando` o número da linha em que o evento ocorreu.

## Requisitos

**Arquivo de entrada**: o nome do arquivo deve ser `alfanumérico` e a `extensão` necessariamente `.cm`

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

- `FileCheck`: classe responsável por validar o nome, extensão e diretório do arquivo de entrada e verificar se 
  tal arquivo a ser lido existe.
  
- `FileScanner`: classe responsável por executar os procedimentos de leitura dos caracteres do arquivo
  e armazená-los em um *buffer*, pós validação feita em `FileCheck`.
  
- `LexicalAnalysis`: classe responsável por varrer o *buffer* criado e verificar os lexemas atribuindo marcas, ou *Tokens*, pós executação dos processos em `FileScanner`.

Na segunda temos:

- `LexicalAnalysisTDD`: classe responsável por implementar as *tasks* elaboradas para abordar o comportamentos dos métodos utilizados no programa.

As demais pastas referenciam biliotecas (JRE e JUnit) e arquivos de configuração do programa.

## *Tasks*

São tarefas pensadas antes de implementar o código dos testes, servem para manter um lembrete do que deve ser implementado. Após concluir um número satisfatório de *tasks* iniciamos sua implementação no arquivo `LexicalAnalysisTDD` gerando somente código necessário na aplicação. Além disso, concretizamos nossos casos de teste.

- As *tasks* criadas foram as seguintes:



