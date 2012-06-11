/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package trab_ia_jogovelha;

import java.util.Queue;

/**
 *
 * @author eliane
 */
public class JogoVelha {

    private int[] board = new int[8];
    private Queue<Node> fifo;
    private Node father;
    
    private int heuristica = 0;
    private int nivel = 0;
    private int jogador;
    private int adversario;

    public void geraArvore() {
        Node next;
        Node newNode;
        int jogaAgora = 1;
        int jogaDepois = 2;
        int ganhou;
        do {
            this.nivel++;
            this.jogador = jogaAgora;
            this.adversario = jogaDepois;
            ganhou = geraFilhos(this.father);
            jogaAgora = this.adversario;
            jogaDepois = this.jogador;
           
        } while (ganhou > 0);
    }

    private int geraFilhos(Node father) {
        Node newFilho;
        int ganhou = 0;
        for (int i = 0; i <= 8; i++) {
            if (board[i] == ' ') {
                board[i] = jogador;  //1 ou 2 = X ou O
                int x = functionAval(this.jogador);
                int y = functionAval(this.adversario);
                heuristica = x-y;
                newFilho = new Node(board, jogador, nivel, heuristica);
                ganhou = newFilho.ganhou();

            }

        }
        return ganhou;
    }

    private int functionAval(int jogada) {
        int i,cont=0;
        for (i = 0; i < 8; i++) {
            if (i == 0 || i == 3 || i == 6) {  //avalia linhas
                if (this.board[i] == jogada || this.board[i]==' ') {
                   if (this.board[i + 1] == jogada || this.board[i+1]==' ') {
                        if (this.board[i + 2] != jogada || this.board[i +2] ==' ') {
                            cont++;
                        }
                    }
                }
               
            }
            
            if(i==0 || i==1 || i==2){    //avalia as colunas
               if (this.board[i] == jogada || this.board[i]==' ') {
                   if (this.board[i + 3] == jogada || this.board[i+3]==' ') {
                        if (this.board[i + 6] != jogada || this.board[i +6] ==' ') {
                            cont++;
                        }
                    }
                }
            }
            
            if(i==6 || i == 7){    //avalia as diagonais
                if (this.board[4] == jogada || this.board[4] == ' ') {
                  if(i==6){
                     if (this.board[0] == jogada || this.board[0] == ' ') {
                       if (this.board[8] == jogada || this.board[8] == ' ') {
                            cont++;
                       }
                     }
                  }else {
                         if (this.board[2] == jogada || this.board[0] == ' ') {
                          if (this.board[0] == jogada || this.board[0] == ' '){   
                            cont++;
                        }
                    }
                }
            }
            } 

        }
        return cont;
    }

    
}
