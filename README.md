# LexicalAnalysisC-

Trabalho realizado para a disciplina de Validação e Verificação de Software ministrada pelo professor Dr. Fábio Fagundes Silveira na Unifesp campus de São José dos Campos. Atividade realizada pelos alunos:

  - Karen Saori Suzuki
  - Vinícius Duarte de Almeida

LexicalAnalysisC- é um programa em Java para realizar a análise léxica da [linguagem C-](http://www.cs.dartmouth.edu/~cs57/Project/C-%20Spec.pdf) utilizando metodologia TDD com o framework [JUnit4](http://junit.org/).

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
  - Os marcadores de identificador e de número, definidos pelas expressões regulares a seguir:
```
identificador (ID): letra+
número (NUM): dígito+
letra: a|..|z|A|..|Z
dígito: 0|..|9
```
Qualquer lexema lido que não seja os mencionados acima será tratado como erro.

## Estrutura

- A estrutura de diretórios do projeto está organizada da seguinte maneira:
As classes java do programa estão organizadas em duas pastas, `src/br.com.Model` e `test/br.com.Test`.

Na primeira temos os seguintes arquivos:

- `FileCheck`: classe responsável por validar o nome, extensão e diretório do arquivo de entrada e verificar se 
  tal arquivo a ser lido existe.
  
- `FileScanner`: classe responsável por executar os procedimentos de leitura dos caracteres do arquivo
  e armazená-los em um *buffer*, pós validação feita em `FileCheck`.
  
- `LexicalAnalysis`: classe responsável por varrer o *buffer* criado e verificar os lexemas atribuindo marcas, ou *Tokens*, pós executação dos processos em `FileScanner`.
