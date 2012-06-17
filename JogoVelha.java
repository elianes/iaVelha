/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

import java.util.List;
import java.util.LinkedList;
import java.util.Queue;

/**
 *
 * @author eliane
 */
public class JogoVelha {

    private int[] board = null;
    private Queue<Node> fifo;
    private int jogador;   //jogador 1 = X e o jogador 2 = O
    private int adversario;
    private int disputa;
    private Node estadoFinal;
    private Node estadoInicial;

    public JogoVelha() {
        int[] boardInicial = {0, 0, 0, 0, 0, 0, 0, 0, 0};
        this.board = boardInicial;
        this.fifo = new LinkedList<Node>();
        jogador = 1;
        estadoFinal = null;

    }

    //SET's
    public void setjogador(int jog) {
        this.jogador = jog;
    }

    public void setBoard(int[] board) {
        this.board = board;
    }

    public void setDisputa(int i) {
        this.disputa = i;

    }

    public void setFinal(Node x) {
        this.estadoFinal = x;
    }

    //GET's
    public int getDisputa() {
        return this.disputa;
    }

    public int getJogador() {
        return this.jogador;
    }

    public int[] getBoard() {
        return this.board;
    }
    
    public Node getEstadoFinal(){
        return this.estadoFinal;
    }
    /*
     * autor: vanderson metodo que roda o alfabeta
     *
     * P@rametros:{Node} Return: {void}
     */
    public void CorteAB() {
        int coef;
        Node root = this.geraArvore();
        CorteAB x = new CorteAB(jogador, root, Integer.MIN_VALUE, Integer.MAX_VALUE);
        coef = x.run();
        this.setFinal(x.getResultado());
        return;
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
    public Node geraArvore() {// modifiquei para ele retornar um node no caso a raiz da arvore para começarmos a busca : vanderson
        Node newFilho, father;
        int[] boardInicial = {0, 0, 0, 0, 0, 0, 0, 0, 0};
        father = new Node(boardInicial);
        board = father.getBoard();
        this.estadoInicial = father;
        int jogador = 1;
        int nivel;

        do {
            for (int i = 0; i <= 8; i++) {
                if (board[i] == 0) {
                    board[i] = jogador;  //1 ou 2 = X ou O
                    newFilho = new Node(father, board);
                    board[i] = 0;//tem que zerar o vetor depois senao vai encher tudo de 1 : vanderson
                    father.insertFilhos(newFilho);//insere o filho na lista de filhos do node :vanderson
                    fifo.add(newFilho);
                    //System.out.println("acrescentou um filho /n");
                    //this.printBoard(newFilho.getBoard());
                }
            }
            //System.out.println("saui do for");
            nivel = father.getNivel();
            if (!fifo.isEmpty()) {
                father = this.fifo.remove();
                board = father.getBoard();
                if (father != null) {
                    if (nivel != father.getNivel()) {  //no proximo nivel será trocado de jogador
                        jogador = jogador == 1 ? 2 : 1;
                    }
                }
            }
        } while (!this.fifo.isEmpty());
        return this.estadoInicial;
    }

    public void encontreNode(int[] board) {
    }

    public void minMax(Node entrada) {
      
        Minmax game;
        game = new Minmax(entrada);
        
        game.run();
        this.estadoFinal = game.getJogadaCerta();
    }

    public void printBoard() {
        for (int i = 0; i < 9; i += 3) {
            System.out.println(board[i] + "  " + board[i + 1] + "  "
                    + board[i + 2]);
        }
        System.out.println(".......");
    }
    /*
     * autor vanderson debugers
     */

    public void printBoard(int[] x) {
        for (int i = 0; i < 9; i += 3) {
            System.out.println(x[i] + "  " + x[i + 1] + "  "
                    + x[i + 2]);
        }
        System.out.println(".......");
    }
    /*
     * autor: vanderson classe que imprime o resultado na tela
     *
     */

    public void getResultado() {
        Node aux;
        aux = this.estadoFinal;
        while (aux.getFather() != null) {
            this.printBoard(aux.getBoard());
            System.out.print(aux.getHeristica());
            aux = aux.getFather();
        }
        return;
    }

   
}
