import java.util.*;

class main{

  static Scanner stdin = new Scanner(System.in);

  // Função usada quando é um jogo de humanos
  public static void Jogo_Humanos(){
    int i = 0;
    int jogada = 0;
    FourLine Jogo = new FourLine();
    Jogo.PrintFourLine();
    for(i = 0; i < 21;i++){
      System.out.println("Jogador 1 escolha a sua jogada:");
      jogada = stdin.nextInt();
      while(Jogo.inserirJogada('X',jogada) == false){
        System.out.println("Jogada Impossivel!!");
        System.out.print("Insira Nova Jogada:");
        jogada = stdin.nextInt();
      }
      Jogo.PrintFourLine();
      if(Jogo.Vitoria() == true) return;
      System.out.println("Jogador 2 escolha a sua jogada:");
      jogada = stdin.nextInt();
      while(Jogo.inserirJogada('O',jogada) == false){
        System.out.println("Jogada Impossivel!!");
        System.out.print("Insira Nova Jogada:");
        jogada = stdin.nextInt();
      }
      Jogo.PrintFourLine();
      if(Jogo.Vitoria() == true) return;
    }
    System.out.println("Empate (╯°O°）╯  ┻━┻");
  }

  public static void main(String[] args){
    int tipo_de_jogo = 0;
    int profundidade = 0;
    int jogador = 0;
    System.out.println("Bem-Vindo ao 4 em Linha");
    FourLine Jogo = new FourLine();
    System.out.println();
    System.out.println("1 -> MiniMax");
    System.out.println("2 -> Alpha-Beta");
    System.out.println("3 -> Monte Carlo Tree Search(MCTS)");
    System.out.println("4 -> Jogo contra Humanos");
    System.out.print("Escolha o seu tipo de jogo:");
    tipo_de_jogo = stdin.nextInt();
    switch(tipo_de_jogo){
      case 1:
        System.out.print("Escolha também quem é que vai primeiro 1 para Humano ou 2 para Computador:");
        jogador = stdin.nextInt();
        System.out.print("Indique a profundidade desejada para o MiniMax:");
        profundidade = stdin.nextInt();
        if(jogador == 1) Searches.Jogo_Minimax1(profundidade);
        else Searches.Jogo_Minimax2(profundidade);
        break;
      case 2:
        System.out.print("Escolha também quem é que vai primeiro 1 para Humano ou 2 para Computador:");
        jogador = stdin.nextInt();
        System.out.print("Indique a profundidade desejada para o Alpha-Beta:");
        profundidade = stdin.nextInt();
        if(jogador == 1) Searches.Jogo_AlphaBeta1(profundidade);
        else Searches.Jogo_AlphaBeta2(profundidade);
        break;
      case 3:
        System.out.print("Escolha também quem é que vai primeiro 1 para Humano ou 2 para Computador:");
        jogador = stdin.nextInt();
        break;
      case 4:
        Jogo.PrintFourLine();
        Jogo_Humanos();
        break;
    }
  }
}
