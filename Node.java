import java.util.*;

class Node{
  Node Pai;
  int movimento;
  char Jogador;
  FourLine Jogo;
  int NumPassos;

  //Inicializa um nó genérico atraves de uma board
  Node(FourLine Board,char Jogador){
    this.Pai = null;
    this.movimento = 11;
    this.Jogador = Jogador;
    this.Jogo = new FourLine(Board.Board);
    this.NumPassos = 0;
  }
  //Inicializa um nó atraves de uma board o seu Pai movimento e o Jogador
  Node(FourLine Board,Node Pai,int movimento,char Jogador){
    this.Pai = Pai;
    this.movimento = movimento;
    this.Jogador = Jogador;
    this.Jogo = new FourLine(Board.Board);
    this.NumPassos = Pai.NumPassos + 1;
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
