Durante o jogo apenas é preciso escolher a coluna onde queremos largar uma peça
por exemplo se eu quiser largar na sétima coluna apenas coloco 7

Os primeiros 3 digitos a inserir selecionam todo o jogo:
O primeiro seleciona o algoritmo:
  1 -> MiniMax
  2 -> Alpha Beta
  3 -> Monte Carlo Tree Search
O segundo seleciona quem começa o Jogo
  1 -> Humano
  2 -> IA
E o terceiro seleciona a profundidade de pesquisa ou no caso do MCTS o número de iterações
Apôs estes 3 dígitos é só escolher a coluna a jogar mediante o Jogo


Compilador :
openjdk 10.0.2 2018-07-17
OpenJDK Runtime Environment (build 10.0.2+13-Ubuntu-1ubuntu0.18.04.4)
OpenJDK 64-Bit Server VM (build 10.0.2+13-Ubuntu-1ubuntu0.18.04.4, mixed mode)
Linha de comando para COmpilar: javac main.java | javac Node.java | javac FourLine.java | javac Searches.java | javac NodeMCTS.java | javac MCTS.java
