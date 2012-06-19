/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
//package iaVelha;

//import com.sun.org.apache.xerces.internal.impl.xpath.regex.Match;
//import java.util.List;
import java.util.LinkedList;
import java.util.Queue;

/**
 *
 * @author eliane
 */
public class JogoVelha {

    private int[] board = null;
    private Queue<Node> fifo;
    private Queue<Node> fifoResul;
    private int jogador;   //jogador 1 = X e o jogador 2 = O
    private int disputa;
    private int nVisitados;
    private int jogadas;
    private Node estadoInicial;

    private int limite;

    public JogoVelha() {
        int[] boardInicial = {0, 0, 0, 0, 0, 0, 0, 0, 0};
        this.board = boardInicial;
        this.fifo = new LinkedList<Node>();
        this.fifoResul = new LinkedList<Node>();
        jogador = 1;
        nVisitados = 0;
        this.limite = 0;
        this.disputa = -1;//Não setado
    }

    public void addResul(Node e) {
        this.fifoResul.add(e);
    }

    public Node getResul() {
        if (this.fifoResul.isEmpty()) {
            return null;
        }
        return this.fifoResul.remove();
    }

    public void setjogador(int jog) {
        this.jogador = jog;
    }

    public void setBoard(int[] board) {
        this.board = board;
    }

    public void setLimite(int limite) {
        this.limite = limite;
    }

    void setDisputa(int i) {
        this.disputa = i;

    }

    public int getDisputa() {
        return this.disputa;
    }

    public int getJogador() {
        return this.jogador;
    }

    public int[] getBoard() {
        return this.board;
    }

    public void somaVisitados(int x) {
        this.nVisitados += x;
    }
    /*
     * autor: vanderson metodo que roda o alfabeta na função pcxpc
     *
     * P@rametros:{Node} Return: {void}
     */

    public Queue<Node> CorteAB_pcxpc() {
        valorRetorno coef;
        Node root = this.geraArvore();
        CorteAB x = new CorteAB();
        coef = x.run(root, jogador, -9999, 99999);//o x sempre começa
        this.somaVisitados(x.getVisitados());
        this.jogadas++;
        this.fifoResul.add(coef.getEstado());
        while (coef.getEstado().ganhou() == 0) {
            coef = x.run(coef.getEstado(), jogador == 1 ? 2 : 1, -9999, 99999);
            this.fifoResul.add(coef.getEstado());
            this.somaVisitados(x.getVisitados());
            this.jogadas++;
        }
        return this.fifoResul;
    }

    public int getVisitados() {
        return this.nVisitados;
    }

    public int getJogadas() {
        return this.jogadas;
    }

    public Node CorteAB_pcxuser(int [] jogada,int jogador ) {
        valorRetorno coef;
        Node root,aux;
        root=this.geraArvore(jogada,jogador);
        CorteAB x = new CorteAB();
        coef = x.run(root, jogador, -9999, 99999);//o x sempre começa
        aux = coef.getEstado();
        this.somaVisitados(x.getVisitados());
        this.jogadas++;
        return aux;
    }


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
                    board[i] = 0;//tem que zerar o vetor depois senao vai encher tudo de 1 ou 2: vanderson
                    father.insertFilhos(newFilho);//insere o filho na lista de filhos do node :vanderson
                    fifo.add(newFilho);
                }
            }
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
    public Node geraArvore(int[]root,int joga) {// modifiquei para ele retornar um node no caso a raiz da arvore para começarmos a busca : vanderson
        Node newFilho, father;
        int[] boardInicial = root;
        father = new Node(boardInicial);
        board = father.getBoard();
        this.estadoInicial = father;
        int jogador =joga;
        int nivel;
        do {
            for (int i = 0; i <= 8; i++) {
                if (board[i] == 0) {
                    board[i] = jogador;  //1 ou 2 = X ou O
                    newFilho = new Node(father, board);
                    board[i] = 0;//tem que zerar o vetor depois senao vai encher tudo de 1 ou 2: vanderson
                    father.insertFilhos(newFilho);//insere o filho na lista de filhos do node :vanderson
                    fifo.add(newFilho);
                }
            }
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
      
    public Queue<Node> minMax_PCXPC(){
         Minmax game;
        geraArvore();
        game = new Minmax(this.estadoInicial);
         Node saida = this.estadoInicial.getFilho((int)(Math.random()*8) );
            if (this.limite == 0) {  //para decidir se é com limite de profundidade
                do {
                    this.fifoResul.add(saida);
                    game.setEntrada(saida);
                    game.run();
                    saida = game.getJogadaCerta();
                } while (!saida.filhosIsNull());
                  this.fifoResul.add(saida);   
            } else {    //com limite de profundidade
                do{
                    this.fifoResul.add(saida);
                    game.setEntrada(saida);
                    game.run();
                    saida = game.getJogadaCerta();
                    
                }while(this.limite != saida.getNivel());
                do{
                    this.fifoResul.add(saida);
                    saida = saida.getFilho();
                }while(!saida.filhosIsNull());
                 this.fifoResul.add(saida);
            }
            return fifoResul;
    }
    
    public void minMax_UserXPC(){
        
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

        while (!this.fifoResul.isEmpty()) {
            aux = this.fifoResul.remove();
            this.printBoard(aux.getBoard());
        }
        return;
    }
}