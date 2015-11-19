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
referencia as condições abaixo.

  - As palavras reservadas da linguagem são as seguintes:
```
if    else    int   void    while   return
```
  - Os símbolos especiais da linguagem são:
```
+   -   *   /   <   <=    >   >=    ==    !=    =   ;   ,   (   )   [   ]   {   }   /*    */
```
Qualquer lexema lido que não seja os mencionados acima será tratado como erro.

## Estrutura

A estrutura de diretórios do projeto está organizada da seguinte maneira:


 
