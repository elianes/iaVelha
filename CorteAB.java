/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */


import java.util.LinkedList;
import java.util.List;

/**
 *
 * @author eliane
 */
public class CorteAB {
    private JogoVelha jogada;
    private Node estadoAtual;
    private static Node resultado;
    private int alfa,beta,avaliacao;
    private List<Node> filhos;
    private int jogador;
    
    public CorteAB(int jogador, Node estado,int alf,int bet){
    	this.alfa=alf;
    	this.beta=bet;
    	this.estadoAtual=estado;
    	this.filhos= new LinkedList<Node>();
    	this.jogador=jogador;
    	this.resultado=null;
    	 	
    	
    	
    }
    public int getAvaliacao(){
    	return this.avaliacao;
    }
  
    public Node getResultado(){
    	return resultado;
    }
    
    public int run(){
    	if (this.estadoAtual.ganhou()>0 || this.estadoAtual.ganhou()==-1){
    		if(resultado==null){
    			resultado=estadoAtual;
    		}
    		return resultado.getHeristica();
      	}
    	CorteAB jogar;
    	Node aux;
    	for( aux=this.estadoAtual.getFilho();aux!=null;aux=this.estadoAtual.getFilho()){
    		this.filhos.add(aux);// pega uma lista com os filhos do Node
    	}
    	
    	if(this.jogador==1){
    		
    		while(!this.filhos.isEmpty()){
    			jogar = new CorteAB(2,this.filhos.remove(0),alfa,beta);
    			
    			avaliacao = jogar.run();
    			if(avaliacao > alfa){
    				alfa = avaliacao;
    			}
    			
    		}
    		return alfa;		
    	}
    	if(this.jogador==2){
    		while(!this.filhos.isEmpty()){
    			
    			jogar = new CorteAB(1,this.filhos.remove(0),alfa,beta);
    			
    			avaliacao = jogar.run();
    			if(avaliacao < beta){
    				beta = avaliacao;
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
