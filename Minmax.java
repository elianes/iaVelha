/*

 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
//package trab_ia_jogovelha;

import java.util.LinkedList;
import java.util.List;

/**
 *
 * @author eliane
 */
public class Minmax {

    public List<Node> filhos;  //lista de filhos do estado
    private Node jogadaCerta;
    private Node entrada;
    private Node filho;
   

    public Minmax(Node entrada) {
        this.entrada = entrada;
        //this.filhos = new LinkedList<Node>();
    }

   
    public void setEntrada(Node entrada) {
        this.entrada = entrada;

    }

   
    public Node getJogadaCerta() {
        return this.jogadaCerta;
    }

    public void run() {

        int maior = -100;
        Node maiorNode;
        this.filhos = this.entrada.getListaFilhos();
        int i = 0;
        if (this.filhos.isEmpty()) {
            return;
        }
        maiorNode = this.entrada;
        for (i = 0; i < filhos.size(); i++) {
                this.filho = this.filhos.get(i);
//System.out.println("minmax");
                //this.filho.printBoard();
                int x = menorSuc();
                //System.out.println("menor = " + x + "jogada =" + this.filho.getJogada());
                if (x > maior) {
                    maiorNode = this.filho;
                    maior = x;
                }
                 
    } 
        
        System.out.println("maior node");
        maiorNode.printBoard();
        this.jogadaCerta = maiorNode;
    }

    public int menorSuc() {

        List<Node> netos;
        Node neto, aux;
        netos = this.filho.getListaFilhos();
        int i = 0;
        int menor = 100;
        for (i = 0; i < netos.size(); i++) {
            neto = netos.get(i);
            //          System.out.println("neto");
            //        neto.printBoard();
            if (neto.getHeristica() < menor) {
                menor = neto.getHeristica();
                //this.filho = neto;
            }

            if (neto.ganhou() == neto.getJogada()) {
                menor = -100;

            }
          //  System.out.println("jogada do neto = " + neto.getJogada());

        }

        return menor;
    }
}

