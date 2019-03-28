import java.util.*;

class Node{
  Node Pai;
  int movimento;
  char Jogador;
  FourLine Jogo;

  //Inicializa um nó genérico atraves de uma board
  Node(FourLine Board){
    this.Pai = null;
    this.movimento = 11;
    this.Jogador = 'X';
    this.Jogo = new FourLine(Board.Board);
  }
  //Inicializa um nó atraves de uma board o seu Pai movimento e o Jogador
  Node(FourLine Board,Node Pai,int movimento,char Jogador){
    this.Pai = Pai;
    this.movimento = movimento;
    this.Jogador = Jogador;
    this.Jogo = new FourLine(Board.Board);
  }
  // Faz um filho com o Movimento x para o Pai y
  Node Fazer_Filho(int movimento,Node Pai){
    char Jogador;
    if(Pai.Jogador == 'X') Jogador = 'O';
    else Jogador = 'X';
    Node Filho = new Node(Pai.Jogo,Pai,movimento,Jogador);
    if(Filho.Jogo.inserirJogada(Jogador,movimento) == false){
      Filho.movimento = 10;
    }
    return Filho;
  }
}
