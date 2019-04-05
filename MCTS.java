import java.util.*;

class MCTS{
  static Scanner stdin = new Scanner(System.in);
  static Random Randomizer = new Random();

  public static double ExplorationExploitation(NodeMCTS Node_Jogo,int TotSimul){
    return Node_Jogo.Value + (Math.sqrt(2) * (Math.sqrt(Math.log(TotSimul) / Node_Jogo.Simul)));
  }

  public static NodeMCTS Escolher_Node(NodeMCTS Node_Jogo,int TotSimul){
    if(Node_Jogo.Simul == 1) return Node_Jogo;
    double valor1 = ExplorationExploitation(Node_Jogo.Filhos[0],TotSimul);
    double valor2 = 0;
    int numFilho = 0;
    for(int i = 1; i < Node_Jogo.NumFilhos; i++){
      valor2 = ExplorationExploitation(Node_Jogo.Filhos[i],TotSimul);
      if (valor2 > valor1){
        valor1 = valor2;
        numFilho = i;
      }
    }
    return Escolher_Node(Node_Jogo.Filhos[numFilho],TotSimul);
  }

  public static boolean Simulation(NodeMCTS Node_Jogo){
    FourLine NewBoard = new FourLine(Node_Jogo.Jogo.Board);
    if(Node_Jogo.Jogador == 'X'){
      while(NewBoard.inserirJogada('O',1 + Randomizer.nextInt(7)) == false);
    }
    while(NewBoard.NoPrint_Vitoria() == 0){
      if(NewBoard.JogadasPossiveis() == 0) return false;
      while(NewBoard.inserirJogada('X',1 + Randomizer.nextInt(7)) == false){}
      if(NewBoard.NoPrint_Vitoria() != 0) break;
      if(NewBoard.JogadasPossiveis() == 0) return false;
      while(NewBoard.inserirJogada('O',1 + Randomizer.nextInt(7)) == false){}
    }
    if(NewBoard.NoPrint_Vitoria() == 1  && Node_Jogo.Jogador == 'O') return true;
    return false;
  }

  public static int Atualizar_Simul(NodeMCTS Node_Atual){
    Node_Atual.Simul = 0;
    Node_Atual.Vitorias = 0;
    for(int i = 0; i < Node_Atual.NumFilhos;i++){
      NodeMCTS The_Son = Node_Atual.Filhos[i];
      Node_Atual.Simul += The_Son.Simul;
      Node_Atual.Vitorias += The_Son.Vitorias;
    }
    if(Node_Atual.NumPassos == 0) return 1;
    return Atualizar_Simul(Node_Atual.Pai);
  }

  public static int Jogada(int numJogos, NodeMCTS Node_Jogo){
    int i = 0;
    for(i = 0; i < numJogos; i++){
      int j = 1;
      NodeMCTS Node_Atual;
      if(Node_Jogo.Simul != 0) Node_Atual = Escolher_Node(Node_Jogo,Node_Jogo.Simul);
      else Node_Atual = Node_Jogo;
      for(int control = 0; control < Node_Atual.NumFilhos;control++){
        Node_Atual.Filhos[control] = new NodeMCTS();
        while (Node_Atual.Filhos[control].Fazer_Filho_MCTS(j,Node_Atual,Node_Atual.Jogo) == false){
          j++;
        }
        j++;
        if(Simulation(Node_Atual.Filhos[control]) == true) Node_Atual.Filhos[control].Vitorias = 1;
      }
      Atualizar_Simul(Node_Atual);
    }
    int Melhor_Filho = 0;
    int valor = Node_Jogo.Filhos[0].Simul;
    for(i = 1; i < Node_Jogo.NumFilhos;i++){
      if(valor < Node_Jogo.Filhos[i].Simul){
        valor = Node_Jogo.Filhos[i].Simul;
        Melhor_Filho = i;
      }
    }
    return Node_Jogo.Filhos[Melhor_Filho].movimento;
}

  public static void Jogo(int numJogos){
    int jogada = 0;
    FourLine Jogo = new FourLine();
    Jogo.PrintFourLine();
    for(int i = 0; i < 21;i++){
      System.out.print("Jogador 1 escolha a sua jogada:");
      jogada = stdin.nextInt();
      while(Jogo.inserirJogada('X',jogada) == false){
        System.out.println("Jogada Impossivel!!");
        System.out.print("Insira Nova Jogada:");
        jogada = stdin.nextInt();
      }
      Jogo.PrintFourLine();
      if(Jogo.Vitoria() == true) return;
      NodeMCTS Node_Jogo = new NodeMCTS(Jogo,'X');
      System.out.print("Monte Carlo faça a sua Jogada:");
      jogada = Jogada(numJogos,Node_Jogo);
      System.out.println(jogada);
      Jogo.inserirJogada('O',jogada);
      Jogo.PrintFourLine();
      if(Jogo.Vitoria() == true) return;
    }
    System.out.println("Empate (╯°O°）╯  ┻━┻");
  }
}
