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
    private int visitados = 0;

    public Minmax(Node entrada) {
        this.entrada = entrada;
        //this.filhos = new LinkedList<Node>();
    }

   
    public void setEntrada(Node entrada) {
        this.entrada = entrada;

    }
    
 public int getVisitados(){
    	return this.visitados;
    }
   
    public Node getJogadaCerta() {
        return this.jogadaCerta;
    }

    public void run() {

   int maior = -1000;
        Node maiorNode;
        this.filhos = this.entrada.getListaFilhos();
        int i = 0;
        if (this.filhos.isEmpty()) {
            return;
        }
        maiorNode = this.entrada;
        for (i = 0; i < filhos.size(); i++) {
                this.filho = this.filhos.get(i);
                visitados++;
                int x = menorSuc();
                if (maior < x) {
                    this.jogadaCerta = this.filho;
                     maior = x;
                }
     } 
    }

    public int menorSuc() {

        List<Node> netos;
        Node neto, aux;
        netos = this.filho.getListaFilhos();
        int i = 0;
        int menor = 100;
        for (i = 0; i < netos.size(); i++) {
            neto = netos.get(i);
            visitados++;
            if (((-1)*neto.getHeristica()) < menor) {
                menor = neto.getHeristica()*(-1);
            }

            if (neto.ganhou() == this.filho.getJogada() ) {
                menor = -100;
                return menor;

            }
        }
        return menor;
    }
    
}

