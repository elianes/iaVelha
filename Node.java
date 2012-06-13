package trab_ia_jogovelha;

import java.util.List;
import java.util.ArrayList;

/*
 * Nodo para a arvore, contem: int[] board - quadro do jogo Node father - nodo
 * pai No jogo quando é X = 1 e O = 2 no vetor de inteiros. O nivel 0 é igual o
 * jogo vazio, ou seja, o vetor vazio.
 */
public class Node {

    private int[] board = new int[8];
    private Node father;
    private int heuristica = 0;
    private int nivel = 0;
   
    
 

    public Node(Node father) {
        this.father = father;
    }

    public Node(int[] board, int nivel, int heuro) {
        this.board = board;
        this.heuristica = heuro;
        this.nivel = nivel;
     

    }

    /*
     * set e get
     */
    public int[] getBoard() {
        return this.board;
    }

    public Node getFather() {
        return this.father;
    }
    
    public int getHeristica(){
        return this.heuristica;
    }
    
   
//avalia qual jogador ganhou: 1 = X ou 2 = O
    public int ganhou() {
        int jogGanho = 0;
        int i, cont = 0;
        for (i = 0; i < 8; i++) {
            if (i == 0 || i == 3 || i == 6) {  //avalia linhas
                if ((this.board[i] == this.board[i + 1]) && (this.board[i + 1] == this.board[i + 2])) {
                    jogGanho = this.board[i];
                }
            }

            if (i == 0 || i == 1 || i == 2) {    //avalia as colunas
                if ((this.board[i] == this.board[i + 1]) && (this.board[i + 1] == this.board[i + 2])) {
                    jogGanho = this.board[i];
                }
            }
            if (i == 6) {    //avalia a diagonal
                if ((this.board[0] == this.board[4]) && (this.board[4] == this.board[8])) {
                    jogGanho = this.board[4];
                }
            }
            if (i == 7) {    //avalia a diagonal
                if ((this.board[2] == this.board[4]) && (this.board[4] == this.board[6])) {
                    jogGanho = this.board[4];
                }
            }
        }
        return jogGanho;
    }
}
