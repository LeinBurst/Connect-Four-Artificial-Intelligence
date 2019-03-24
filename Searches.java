import java.util.*;

class Searches{
  static Scanner stdin = new Scanner(System.in);

  //Calcula o valor de uma jogada é auxiliar á Jogada_Minimax()
  public static int value_board(FourLine Jogo){
    int numX,numO;
    int counter = 0;
    // Counter Linhas
    for(int i = 5; i >= 0;i--){
      for(int j = 0; j < 4;j++){
        numX = 0;
        numO = 0;
        if(Jogo.Board[i][j] == 'X') numX++;
        else if(Jogo.Board[i][j] == 'O')numO++;
        if(Jogo.Board[i][j+1] == 'X') numX++;
        else if(Jogo.Board[i][j+1] == 'O')numO++;
        if(Jogo.Board[i][j+2] == 'X') numX++;
        else if(Jogo.Board[i][j+2] == 'O')numO++;
        if(Jogo.Board[i][j+3] == 'X') numX++;
        else if(Jogo.Board[i][j+3] == 'O')numO++;
        if(numX == 4)return 512;
        else if(numO == 4)return -512;
        else if(numO == 3 && numX == 0) counter -= 50;
        else if(numO == 2 && numX == 0) counter -= 10;
        else if(numO == 1 && numX == 0) counter -= 1;
        else if(numX == 3 && numO == 0) counter += 50;
        else if(numX == 2 && numO == 0) counter += 10;
        else if(numX == 1 && numX == 0) counter += 1;
      }
    }
    //Counter Colunas
    for(int i = 5; i > 2;i--){
      for(int j = 0; j < 7;j++){
        numX = 0;
        numO = 0;
        if(Jogo.Board[i][j] == 'X') numX++;
        else if(Jogo.Board[i][j] == 'O')numO++;
        if(Jogo.Board[i-1][j] == 'X') numX++;
        else if(Jogo.Board[i-1][j] == 'O')numO++;
        if(Jogo.Board[i-2][j] == 'X') numX++;
        else if(Jogo.Board[i-2][j] == 'O')numO++;
        if(Jogo.Board[i-3][j] == 'X') numX++;
        else if(Jogo.Board[i-3][j] == 'O')numO++;
        if(numX == 4)return 512;
        else if(numO == 4)return -512;
        else if(numO == 3 && numX == 0) counter -= 50;
        else if(numO == 2 && numX == 0) counter -= 10;
        else if(numO == 1 && numX == 0) counter -= 1;
        else if(numX == 3 && numO == 0) counter += 50;
        else if(numX == 2 && numO == 0) counter += 10;
        else if(numX == 1 && numX == 0) counter += 1;
      }
    }
    //Counter Diagonais
    for(int i = 5; i > 2;i--){
      for(int j = 0; j < 4;j++){
        numX = 0;
        numO = 0;
        if(Jogo.Board[i][j] == 'X') numX++;
        else if(Jogo.Board[i][j] == 'O')numO++;
        if(Jogo.Board[i-1][j+1] == 'X') numX++;
        else if(Jogo.Board[i-1][j+1] == 'O')numO++;
        if(Jogo.Board[i-2][j+2] == 'X') numX++;
        else if(Jogo.Board[i-2][j+2] == 'O')numO++;
        if(Jogo.Board[i-3][j+3] == 'X') numX++;
        else if(Jogo.Board[i-3][j+3] == 'O')numO++;
        if(numX == 4)return 512;
        else if(numO == 4)return -512;
        else if(numO == 3 && numX == 0) counter -= 50;
        else if(numO == 2 && numX == 0) counter -= 10;
        else if(numO == 1 && numX == 0) counter -= 1;
        else if(numX == 3 && numO == 0) counter += 50;
        else if(numX == 2 && numO == 0) counter += 10;
        else if(numX == 1 && numX == 0) counter += 1;
      }
    }
    for(int i = 5; i > 2;i--){
      for(int j = 3; j < 7;j++){
        numX = 0;
        numO = 0;
        if(Jogo.Board[i][j] == 'X') numX++;
        else if(Jogo.Board[i][j] == 'O')numO++;
        if(Jogo.Board[i-1][j-1] == 'X') numX++;
        else if(Jogo.Board[i-1][j-1] == 'O')numO++;
        if(Jogo.Board[i-2][j-2] == 'X') numX++;
        else if(Jogo.Board[i-2][j-2] == 'O')numO++;
        if(Jogo.Board[i-3][j-3] == 'X') numX++;
        else if(Jogo.Board[i-3][j-3] == 'O')numO++;
        if(numX == 4)return 512;
        else if(numO == 4)return -512;
        else if(numO == 3 && numX == 0) counter -= 50;
        else if(numO == 2 && numX == 0) counter -= 10;
        else if(numO == 1 && numX == 0) counter -= 1;
        else if(numX == 3 && numO == 0) counter += 50;
        else if(numX == 2 && numO == 0) counter += 10;
        else if(numX == 1 && numX == 0) counter += 1;
      }
    }
    return counter;
  }

  //Função Recursiva que escolhe a próxima jogada do MiniMax
  public static int Jogada_Minimax(int profundidade_final,int profundidade,Node Pai){
    if(Pai.movimento == 10 && Pai.Pai.movimento != 11) return value_board(Pai.Pai.Jogo);
    else if(Pai.movimento == 10)return -513;
    if(profundidade == 0) return value_board(Pai.Jogo);
    int[] resultado_valor = new int[8];
    for(int i = 1; i < 8; i++){
      Pai.Filhos[i] = Pai.Fazer_Filho(i,Pai);
    }
    for(int i = 1;i < 8;i++){
      resultado_valor[i] = Jogada_Minimax(profundidade_final,profundidade-1,Pai.Filhos[i]);
    }
    if(Pai.Jogador == 'X'){
      int valor = 513;
      int resultado_movimento = -1;
      for(int i = 1; i < 8;i++){
        if(resultado_valor[i] == -513) continue;
        if(resultado_valor[i] < valor){
          valor = resultado_valor[i];
          resultado_movimento = i;
        }
      }
      if(profundidade == profundidade_final) return resultado_movimento;
      else return valor;
    }
    else{
      int valor = -513;
      int resultado_movimento = -1;
      for(int i = 1; i < 8;i++){
        if(resultado_valor[i] == -513) continue;
        if(resultado_valor[i] > valor){
          valor = resultado_valor[i];
          resultado_movimento = i;
        }
      }
      if(profundidade == profundidade_final) return resultado_movimento;
      else return valor;
    }
  }
  
  //Função Recursiva que escolhe a próxima jogada do AlphaBeta
  public static int Jogada_AlphaBeta(int profundidade_final,int profundidade,Node Pai,int alpha,int beta){
    if(Pai.movimento == 10 && Pai.Pai.movimento != 11) return value_board(Pai.Pai.Jogo);
    else if(Pai.movimento == 10)return -513;
    if(profundidade == 0) return value_board(Pai.Jogo);
    for(int i = 1; i < 8; i++){
      Pai.Filhos[i] = Pai.Fazer_Filho(i,Pai);
    }
    if(Pai.Jogador == 'X'){
      int valor = 513;
      int valor_filho = 0;
      int resultado_movimento = -1;
      for(int i = 1; i < 8;i++){
        valor_filho = Jogada_AlphaBeta(profundidade_final,profundidade-1,Pai.Filhos[i],alpha,beta);
        if(valor_filho == -513) continue;
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
      int valor = -513;
      int resultado_movimento = -1;
      for(int i = 1; i < 8;i++){
        valor_filho = Jogada_AlphaBeta(profundidade_final,profundidade-1,Pai.Filhos[i],alpha,beta);
        if(valor_filho == -513) continue;
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
      jogada = Jogada_AlphaBeta(profundidade,profundidade,Node_Jogo,-513,513);
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
      jogada = Jogada_AlphaBeta(profundidade,profundidade,Node_Jogo,-513,513);
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
