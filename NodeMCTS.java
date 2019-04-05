import java.util.*;

class NodeMCTS{
  NodeMCTS Filhos[];
  int NumFilhos;
  NodeMCTS Pai;
  int movimento;
  char Jogador;
  FourLine Jogo;
  int NumPassos;
  int Simul;
  int Vitorias;
  int Value;

  NodeMCTS(){
    this.Jogo = new FourLine();
    this.NumFilhos = this.Jogo.JogadasPossiveis();
    this.Filhos = new NodeMCTS[this.NumFilhos];
    this.Pai = null;
    this.movimento = -1;
    this.Jogador = 'X';
    this.NumPassos = 0;
    this.Simul = 0;
    this.Vitorias = 0;
    this.Value = 0;
  }

  NodeMCTS(FourLine Jogo,char Jogador){
    this.Jogo = Jogo;
    this.NumFilhos = this.Jogo.JogadasPossiveis();
    this.Filhos = new NodeMCTS[this.NumFilhos];
    this.Pai = null;
    this.movimento = -1;
    this.Jogador = Jogador;
    this.NumPassos = 0;
    this.Simul = 0;
    this.Vitorias = 0;
    this.Value = Searches.value_board(Jogo);
  }

  boolean Fazer_Filho_MCTS(int movimento,NodeMCTS pai,FourLine Board){
    this.Jogo = new FourLine(Board.Board);
    if(pai.Jogador == 'X') this.Jogador = 'O';
    else this.Jogador = 'X';
    if(this.Jogo.inserirJogada(this.Jogador,movimento) == false) return false;
    this.NumFilhos = this.Jogo.JogadasPossiveis();
    this.Filhos = new NodeMCTS[this.NumFilhos];
    this.Pai = pai;
    this.movimento = movimento;
    this.NumPassos = pai.NumPassos + 1;
    this.Simul = 1;
    this.Vitorias = 0;
    this.Value = Searches.value_board(Board);
    return true;
  }
}
