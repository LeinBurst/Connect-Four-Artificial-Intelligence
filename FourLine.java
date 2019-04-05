import java.util.*;

class FourLine{
  char[][] Board;

  //Inicializar uma 'variavel' FourLine sem nada
  FourLine(){
    this.Board = new char[6][7];
    for(int i = 0; i < 6; i++){
      for(int j = 0; j < 7; j++){
        this.Board[i][j] = '-';
      }
    }
  }

  //Inicializar uma 'variavel' FourLine atravez de uma board
  FourLine(char[][] Jogo){
    this.Board = new char[6][7];
    for(int i = 0; i < 6; i++){
      for(int j = 0; j < 7; j++){
        this.Board[i][j] = Jogo[i][j];
      }
    }
  }

  //Dá output da board do jogo
  void PrintFourLine(){
    for(int i = 0; i < 6; i++){
      for(int j = 0; j < 7; j++){
        System.out.print(this.Board[i][j] + " ");
      }
      System.out.println(i+1);
    }
    System.out.println("1 2 3 4 5 6 7");
    System.out.println("");
  }

  //Insere uma jogada e dá return a true se foi possível e false quaso o contrário
  boolean inserirJogada(char a,int j){
    int i = 5;
    j--;
    while(this.Board[i][j] !=  '-'){
      i--;
      if(i < 0)return false;
    }
    this.Board[i][j] = a;
    return true;
  }

  int JogadasPossiveis(){
    int counter = 0;
    for(int j = 0; j < 7;j++){
      if(this.Board[0][j] == '-') counter++;
    }
    return counter;
  }

  //Função auxiliar da Vitoria() que verifica se já alguem tem quatro em Linha
  boolean VitoriaLinhas(char a,int i, int j){
    if(j < 4){
      if((this.Board[i][j+1] == a)&&(this.Board[i][j+2] == a)&&(this.Board[i][j+3] == a)) return true;
      return false;
    }
    return false;
  }

  //Função auxiliar da Vitoria() que verifica se já alguem tem quatro em Coluna
  boolean VitoriaColunas(char a,int i, int j){
    if(i > 2){
      if((this.Board[i-1][j] == a)&&(this.Board[i-2][j] == a)&&(this.Board[i-3][j] == a)) return true;
      return false;
    }
    return false;
  }

  //Função auxiliar da Vitoria() que verifica se já alguem tem quatro em Diagonal
  boolean VitoriaDiagonais(char a, int i, int j){
    if(i > 2 && j < 4){
      if((this.Board[i-1][j+1] == a) && (this.Board[i-2][j+2] == a) && (this.Board[i-3][j+3] == a)) return true;
    }
    if(i > 2 && j > 2){
      if((this.Board[i-1][j-1] == a) && (this.Board[i-2][j-2] == a) && (this.Board[i-3][j-3] == a)) return true;
      return false;
    }
    return false;
  }

  //Testa se alguém já ganhou o jogo
  boolean Vitoria(){
    for(int i = 5; i >= 0;i--){
      for(int j = 0; j < 7;j++){
        if(this.Board[i][j] != '-'){
          if(VitoriaLinhas(this.Board[i][j],i,j) == true){
            if(this.Board[i][j] == 'X')System.out.println("Jogador 1 Ganhou o Jogo");
            else System.out.println("Jogador 2 Ganhou o Jogo");
            return true;
          }
          else if(VitoriaColunas(this.Board[i][j],i,j) == true){
            if(this.Board[i][j] == 'X')System.out.println("Jogador 1 Ganhou o Jogo");
            else System.out.println("Jogador 2 Ganhou o Jogo");
            return true;
          }
          else if(VitoriaDiagonais(this.Board[i][j],i,j) == true){
            if(this.Board[i][j] == 'X')System.out.println("Jogador 1 Ganhou o Jogo");
            else System.out.println("Jogador 2 Ganhou o Jogo");
            return true;
          }
        }
      }
    }
    return false;
  }

  int NoPrint_Vitoria(){
    for(int i = 5; i >= 0;i--){
      for(int j = 0; j < 7;j++){
        if(this.Board[i][j] != '-'){
          if(VitoriaLinhas(this.Board[i][j],i,j) == true){
            if(this.Board[i][j] == 'X')return 2;
            else if(this.Board[i][j] == 'O')return 1;
          }
          else if(VitoriaColunas(this.Board[i][j],i,j) == true){
            if(this.Board[i][j] == 'X')return 2;
            else if(this.Board[i][j] == 'O')return 1;
          }
          else if(VitoriaDiagonais(this.Board[i][j],i,j) == true){
            if(this.Board[i][j] == 'X')return 2;
            else if(this.Board[i][j] == 'O')return 1;
          }
        }
      }
    }
    return 0;
  }

}
