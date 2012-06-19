/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package iaVelha;

import java.util.LinkedList;
import java.util.List;

/**
 *
 * @author eliane
 */
public class CorteAB {
    private static Node resultado;
    private int avaliacao;
    private int visitados;
    
    public CorteAB(){
    	visitados=0;
    }
    public int getVisitados(){
    	return this.visitados;
    }
    public int getAvaliacao(){
    	return this.avaliacao;
    }
  
    public Node getResultado(){
    	return resultado;
    }
    public valorRetorno run(Node estado, int jogador, int alpha, int beta){
    	valorRetorno a=null;
    	Node aux=null;
        int i=0;
        if(estado.filhosIsNull()){
        	return this.getHeristica(estado);
        }
        for (; i<9 ;i++) {
        	if(!estado.filhosIsNull()&&estado.getNumFilhos()>i){
        		aux=estado.getFilho(i);
        		int x=(-1)*beta;
        		int y=(-1)*alpha;
        		valorRetorno b = run(aux,jogador ,x,y);
        		b.setHeuristica(b.getHeristica()*(-1));
                visitados++;
            	if(jogador==1){
            		if (a==null || a.getHeristica() < b.getHeristica()){
            			a=b;
                        a.setEstado(estado.getFilho(i));
                        alpha=a.getHeristica();	
            		}
            	}else{
            		if (a==null || a.getHeristica() > b.getHeristica()){
            			a=b;
                        a.setEstado(estado.getFilho(i));
                       alpha=a.getHeristica();	
            		}
            	}
            if (beta<=alpha)
              i=9;
          }
        }
        return a;
    }

    private valorRetorno getHeristica(Node estado) {
    		valorRetorno a=new valorRetorno();
    		  int tmp=estado.ganhou();
    		  if (tmp == estado.getJogada()){
    		    a.setHeuristica(Integer.MAX_VALUE);
    		  }
    		  else if (tmp == (estado.getJogada()== 1 ? 2 : 1)){
    		    a.setHeuristica(Integer.MIN_VALUE);
    		  }
    		  else{
    		    a.setHeuristica(estado.getHeristica());
    		  }
    		  a.setEstado(estado);
    		  a.setFather(estado);
    		  return a;
    		}
    		 
    /*autor vanderson
     * debugers 
     */
    public void printBoard(int[] x) {
        for (int i = 0; i < 9; i += 3) {
            System.out.println(x[i] + "  " + x[i + 1] + "  "
                    + x[i + 2]);
        }
        System.out.println(".......");
    }
   
   
}
