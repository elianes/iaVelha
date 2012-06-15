/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package trab_ia_jogovelha;

import java.util.List;
import java.util.LinkedList;
import java.util.Queue;

/**
 *
 * @author eliane
 */
public class JogoVelha {

    private int[] board = new int[9];
    private Queue<Node> fifo;
    private int jogador;   //jogador 1 = X e o jogador 2 = O
    private int adversario;
    private int disputa;

    public void setjogador(int jog) {
        this.jogador = jog;
    }

    public void setBoard(int[] board) {
        this.board = board;
    }
    
     void setDisputa(int i) {
        this.disputa = i;
        
    }
     
    public int getDisputa(){
        return this.disputa;
    }

    public int getJogador() {
        return this.jogador;
    }

    public int[] getBoard() {
        return this.board;
    }

    /*
     * public void geraArvore() { Node next; Node newNode;
     *
     * int jogaAgora = 1; int jogaDepois = 2; int ganhou; do { this.nivel++;
     * this.jogador = jogaAgora; this.adversario = jogaDepois;
     * geraFilhos(this.father); jogaAgora = this.adversario; jogaDepois =
     * this.jogador;
     *
     * } while (true); }
     */
    public void geraArvore() {
        Node newFilho, father;
        int[] boardInicial = {0, 0, 0, 0, 0, 0, 0, 0, 0};
        father = new Node(boardInicial);
        int cont = 0;
        int jogador = 1;
        int nivel;
        this.fifo = new LinkedList<Node>();
  
        do {
            for (int i = 0; i <= 8; i++) {
                if (board[i] == 0) {
                    board[i] = jogador;  //1 ou 2 = X ou O
                    newFilho = new Node(father);
                    fifo.add(newFilho);
                    cont++;
                    System.out.println("acrescentou um filho");
                }
            }
             System.out.println("saui do for");
            nivel = father.getNivel();
            father = this.fifo.remove();
            if (father != null) {   
                if (nivel != father.getNivel()) {  //no proximo nivel será trocado de jogador
                    jogador = jogador == 1 ? 2 : 1;
                }
            }
        } while (this.fifo != null);

    }

    public void encontreNode(int[] board) {
    }

    public void minMax() {
    }

    public void printBoard() {
        for (int i = 0; i < 9; i += 3) {
            System.out.println(board[i] + "  " + board[i + 1] + "  "
                    + board[i + 2]);
        }
        System.out.println(".......");
    }

   
}