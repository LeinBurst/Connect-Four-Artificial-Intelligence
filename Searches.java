import java.util.*;

class Searches{
  static Scanner stdin = new Scanner(System.in);

  public static int value_X_O(int numX,int numO){
    if(numX != 0 && numO != 0) return 0;
    else if(numO == 0){
      switch(numX){
        case 1:
          return 1;
        case 2:
          return 10;
        case 3:
          return 50;
        case 4:
          return 512;
      }
    }
    else switch(numO){
      case 1:
        return -1;
      case 2:
        return -10;
      case 3:
        return -50;
      case 4:
        return -512;
    }
    return 0;
  }

  public static int value_board_linhas(int i, int j, FourLine Jogo){
    int numX = 0, numO = 0;
    for(int n = 0; n < 4; n++){
      if(Jogo.Board[i][j+n] == 'X') numX += 1;
      else if(Jogo.Board[i][j+n] == 'O') numO += 1;
    }
    return value_X_O(numX,numO);
  }

  public static int value_board_colunas(int i,int j, FourLine Jogo){
    int numX = 0, numO = 0;
    for(int n = 0; n < 4; n++){
      if(Jogo.Board[i+n][j] == 'X') numX += 1;
      else if(Jogo.Board[i+n][j] == 'O') numO += 1;
    }
    return value_X_O(numX,numO);
  }

  public static int value_board_Diagonais1(int i,int j,FourLine Jogo){
    int numX = 0, numO = 0;
    for(int n = 0; n < 4; n++){
      if(Jogo.Board[i+n][j+n] == 'X') numX += 1;
      else if(Jogo.Board[i+n][j+n] == 'O') numO += 1;
    }
    return value_X_O(numX,numO);
  }

  public static int value_board_Diagonais2(int i,int j,FourLine Jogo){
    int numX = 0, numO = 0;
    for(int n = 0; n < 4; n++){
      if(Jogo.Board[i+n][j-n] == 'X') numX += 1;
      else if(Jogo.Board[i+n][j-n] == 'O') numO += 1;
    }
    return value_X_O(numX,numO);
  }

  //Calcula o valor de uma jogada
  public static int value_board(FourLine Jogo){
    int counter = 0;
    for(int i = 0; i < 6;i++){
      for(int j = 0; j < 7;j++){
        if(j < 4) counter += value_board_linhas(i,j,Jogo);
        //if(counter >= 512) return 512;
        //if(counter <= -512) return -512;
        if(i < 3) counter += value_board_colunas(i,j,Jogo);
        //if(counter >= 512) return 512;
        //if(counter <= -512) return -512;
        if(i < 3 && j < 4)counter += value_board_Diagonais1(i,j,Jogo);
        //if(counter >= 512) return 512;
        //if(counter <= -512) return -512;
        if(i < 3 && j > 2)counter += value_board_Diagonais2(i,j,Jogo);
        //if(counter >= 512) return 512;
        //if(counter <= -512) return -512;
      }
    }
    return counter;
  }

  //Função Recursiva que escolhe a próxima jogada do MiniMax
  public static int Jogada_Minimax(int profundidade_final,int profundidade,Node Pai){
    if(profundidade == 0) return value_board(Pai.Jogo);
    int[] resultado_valor = new int[8];
    for(int i = 1; i < 8; i++){
      Pai.Filhos[i] = Pai.Fazer_Filho(i,Pai);
    }
    if(Pai.Jogador == 'X'){
      int valor_filho = 0;
      int valor = Integer.MAX_VALUE;
      int resultado_movimento = -1;
      for(int i = 1; i < 8;i++){
        if(Pai.Filhos[i].movimento == 10) continue;
        valor_filho = Jogada_Minimax(profundidade_final,profundidade-1,Pai.Filhos[i]);
        if(valor_filho < valor){
          valor = valor_filho;
          resultado_movimento = i;
        }
      }
      if(profundidade == profundidade_final) return resultado_movimento;
      else return valor;
    }
    else{
      int valor_filho = 0;
      int valor = Integer.MIN_VALUE;
      int resultado_movimento = -1;
      for(int i = 1; i < 8;i++){
        if(Pai.Filhos[i].movimento == 10) continue;
        valor_filho = Jogada_Minimax(profundidade_final,profundidade-1,Pai.Filhos[i]);
        if(valor_filho > valor){
          valor = valor_filho;
          resultado_movimento = i;
        }
      }
      if(profundidade == profundidade_final) return resultado_movimento;
      else return valor;
    }
  }

  //Função Recursiva que escolhe a próxima jogada do AlphaBeta
  public static int Jogada_AlphaBeta(int profundidade_final,int profundidade,Node Pai,int alpha,int beta){
    if(profundidade == 0) return value_board(Pai.Jogo);
    for(int i = 1; i < 8; i++){
      Pai.Filhos[i] = Pai.Fazer_Filho(i,Pai);
    }
    if(Pai.Jogador == 'X'){
      int valor = Integer.MAX_VALUE;
      int valor_filho = 0;
      int resultado_movimento = -1;
      for(int i = 1; i < 8;i++){
        if(Pai.Filhos[i].movimento == 10) continue;
        valor_filho = Jogada_AlphaBeta(profundidade_final,profundidade-1,Pai.Filhos[i],alpha,beta);
        if(valor_filho < valor){
          valor = valor_filho;
          beta = valor_filho;
          resultado_movimento = i;
        }
        if(beta <= alpha) break;
      }
      if(profundidade == profundidade_final) return resultado_movimento;
      else return valor;
    }
    else{
      int valor_filho = 0;
      int valor = Integer.MIN_VALUE;
      int resultado_movimento = -1;
      for(int i = 1; i < 8;i++){
        if(Pai.Filhos[i].movimento == 10) continue;
        valor_filho = Jogada_AlphaBeta(profundidade_final,profundidade-1,Pai.Filhos[i],alpha,beta);
        if(valor_filho > valor){
          valor = valor_filho;
          alpha = valor_filho;
          resultado_movimento = i;
        }
        if(beta <= alpha) break;
      }
      if(profundidade == profundidade_final) return resultado_movimento;
      else return valor;
    }
  }

  //Controladores dos Jogos
  //Controla o jogo do algoritmmo Minimax em que o Humano é o primeiro a jogar
  public static void Jogo_Minimax1(int profundidade){
    int i = 0;
    int jogada = 0;
    FourLine Jogo = new FourLine();
    Jogo.PrintFourLine();
    for(i = 0; i < 21;i++){
      System.out.print("Jogador 1 escolha a sua jogada:");
      jogada = stdin.nextInt();
      while(Jogo.inserirJogada('X',jogada) == false){
        System.out.println("Jogada Impossivel!!");
        System.out.print("Insira Nova Jogada:");
        jogada = stdin.nextInt();
      }
      Jogo.PrintFourLine();
      if(Jogo.Vitoria() == true) return;
      Node Node_Jogo = new Node(Jogo);
      System.out.print("MiniMax faça a sua Jogada:");
      jogada = Jogada_Minimax(profundidade,profundidade,Node_Jogo);
      System.out.println(jogada);
      Jogo.inserirJogada('O',jogada);
      Jogo.PrintFourLine();
      if(Jogo.Vitoria() == true) return;
    }
    System.out.println("Empate (╯°O°）╯  ┻━┻");
  }

  //Controla o jogo do algoritmmo Minimax em que o Computador é o primeiro a jogar
  public static void Jogo_Minimax2(int profundidade){
    int i = 0;
    int jogada = 0;
    FourLine Jogo = new FourLine();
    Jogo.PrintFourLine();
    for(i = 0; i < 21;i++){
      Node Node_Jogo = new Node(Jogo);
      System.out.print("MiniMax faça a sua Jogada:");
      jogada = Jogada_Minimax(profundidade,profundidade,Node_Jogo);
      System.out.println(jogada);
      Jogo.inserirJogada('O',jogada);
      Jogo.PrintFourLine();
      if(Jogo.Vitoria() == true) return;
      System.out.print("Jogador 1 escolha a sua jogada:");
      jogada = stdin.nextInt();
      while(Jogo.inserirJogada('X',jogada) == false){
        System.out.println("Jogada Impossivel!!");
        System.out.print("Insira Nova Jogada:");
        jogada = stdin.nextInt();
      }
      Jogo.PrintFourLine();
    }
    System.out.println("Empate (╯°O°）╯  ┻━┻");
  }

  //Controla o jogo do algoritmo AlphaBeta em que o Humano é o primeiro a jogar
  public static void Jogo_AlphaBeta1(int profundidade){
    int i = 0;
    int jogada = 0;
    FourLine Jogo = new FourLine();
    Jogo.PrintFourLine();
    for(i = 0; i < 21;i++){
      System.out.print("Jogador 1 escolha a sua jogada:");
      jogada = stdin.nextInt();
      while(Jogo.inserirJogada('X',jogada) == false){
        System.out.println("Jogada Impossivel!!");
        System.out.print("Insira Nova Jogada:");
        jogada = stdin.nextInt();
      }
      Jogo.PrintFourLine();
      if(Jogo.Vitoria() == true) return;
      Node Node_Jogo = new Node(Jogo);
      System.out.print("MiniMax faça a sua Jogada:");
      jogada = Jogada_AlphaBeta(profundidade,profundidade,Node_Jogo,Integer.MIN_VALUE,Integer.MAX_VALUE);
      System.out.println(jogada);
      Jogo.inserirJogada('O',jogada);
      Jogo.PrintFourLine();
      if(Jogo.Vitoria() == true) return;
    }
    System.out.println("Empate (╯°O°）╯  ┻━┻");
  }

  //Controla o jogo do algoritmo AlphaBeta em que o Computador é o primeiro a jogar
  public static void Jogo_AlphaBeta2(int profundidade){
    int i = 0;
    int jogada = 0;
    FourLine Jogo = new FourLine();
    Jogo.PrintFourLine();
    for(i = 0; i < 21;i++){
      Node Node_Jogo = new Node(Jogo);
      System.out.print("MiniMax faça a sua Jogada:");
      jogada = Jogada_AlphaBeta(profundidade,profundidade,Node_Jogo,Integer.MIN_VALUE,Integer.MAX_VALUE);
      System.out.println(jogada);
      Jogo.inserirJogada('O',jogada);
      Jogo.PrintFourLine();
      if(Jogo.Vitoria() == true) return;
      System.out.print("Jogador 1 escolha a sua jogada:");
      jogada = stdin.nextInt();
      while(Jogo.inserirJogada('X',jogada) == false){
        System.out.println("Jogada Impossivel!!");
        System.out.print("Insira Nova Jogada:");
        jogada = stdin.nextInt();
      }
      Jogo.PrintFourLine();
    }
    System.out.println("Empate (╯°O°）╯  ┻━┻");
  }
}
