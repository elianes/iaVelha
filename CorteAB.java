/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package trab_ia_jogovelha;

import java.util.LinkedList;
import java.util.List;

/**
 *
 * @author eliane
 */
public class CorteAB {
	private JogoVelha jogada;
    private Node estadoAtual,estadoFinal;
    private int alfa,beta,avaliacao;
    private List<Node> filhos;
    private int jogador;
    
    public CorteAB(int jogador, Node estado,int alf,int bet){
    	this.alfa=alf;
    	this.beta=bet;
    	this.estadoAtual=estado;
    	this.filhos= new LinkedList<Node>();
    	this.jogador=jogador;
    	 	
    	
    	
    }
    public int getAvaliacao(){
    	return this.avaliacao;
    }
    public Node goal(){
    	return estadoFinal;
    }
    
    public int run(){
    	if (this.estadoAtual.ganhou()>0 || this.estadoAtual.equals(this.estadoFinal)){
    		this.estadoFinal=estadoAtual;
    		return 0;
    		///this.estadoAtual(); devolve o atual
    		//return ;
    	}
    	CorteAB jogar;
    	this.estadoAtual.geraFilhos();// fazer algo para gerar od filhos do nodo.
    	if(this.jogador==1){
    		while(!this.filhos.isEmpty()){
    			jogar = new CorteAB(1,this.filhos.remove(0),alfa,beta);
    			avaliacao = jogar.run();
    			if(avaliacao > alfa){
    				alfa = avaliacao;
    			}
    		}
    		return alfa;		
    	}
    	if(this.jogador==1){
    		while(!this.filhos.isEmpty()){
    			jogar = new CorteAB(1,this.filhos.remove(0),alfa,beta);
    			avaliacao = jogar.run();
    			if(avaliacao < beta){
    				alfa = avaliacao;
    			}
    		}
    		return beta;		
    	}
    	return 0;
     }
    public Node getEstadoAtual(){
    	return this.estadoAtual;
    }
    
   
   
}
