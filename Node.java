//package iaVelha;

import java.util.LinkedList;
import java.util.List;
import java.util.ArrayList;
import java.util.Arrays;

/*
 * Nodo para a arvore, contem: int[] board - quadro do jogo Node father - nodo
 * pai No jogo quando é X = 1 e O = 2 no vetor de inteiros. O nivel 0 é igual o
 * jogo vazio, ou seja, o vetor vazio.
 */
public class Node {

    private int[] board = new int[9];
    private Node father,melhorJogada;
    private int heuristica = 0;
    private int nivel =0;
    private int jogada;
    private List<Node> filhos=null;
 

    public Node(Node father) {
        this.father = father;
        this.board = father.board.clone();
        this.nivel = father.nivel + 1;
        this.jogada = father.jogada == 1 ? 2 : 1; 
        this.filhos=new LinkedList<Node>();
        
        
    }
    public Node findFilho(int [] procurado){
    	Node aux;
    	if(!this.filhosIsNull()){
    		for(int i=0;i<this.filhos.size();i++){
    				aux=this.getFilho(i);
                                System.out.println("procurando....");
                                aux.printBoard();
                                int [] x = aux.getBoard();
    			if(Arrays.equals(x, procurado))
                            {
    				return aux;
    			}
    			
    		}
    		
    	}
    	return null;
    }
    public int getJogada(){
    	return this.jogada;
    }
    public void setMelhorJogada(Node x){
    	this.melhorJogada=x;
    }
    public void setJogada(int x){
    	this.jogada=x;
    	
    }
    public List<Node> getListaFilhos(){
        return this.filhos;
    }
    
    public Node(int[] board) {
    	this.father=null;
        this.board = board;
        this.filhos=new LinkedList<Node>();
        this.jogada = 1; 
    }
    public Node (Node father, int[] board){
    	this.father=father;
    	this.board=board.clone();
    	this.nivel = father.nivel + 1;
        this.jogada = father.jogada == 1 ? 2 : 1; 
        this.filhos=new LinkedList<Node>();
        calculaHeuristica();
    	
    }
    /*
     * autor: vanderson
     * metodo que insere na lista de filhos do node
     * parametros: {Node}
     */
    public void insertFilhos(Node x){
    	filhos.add(x);
    	return;
    }
    /*
     * set e get
     */
    /*
     * autor: vanderson
     * metodo que devolve a lista de filhos 
     * parametros: {void}
     * return {List<Node>}
     */
    public Node getFilho(){
    	if(!this.filhos.isEmpty()){
    	return filhos.remove(0);
    	}
    	return null;
    }
    public Node getFilho(int i){
    	if(!this.filhos.isEmpty()){
    	return filhos.get(i);
    	}
    	return null;
    }
    public boolean filhosIsNull(){
    	if (this.filhos.isEmpty())
    		return true;
    	return false;
    }
    public int getNumFilhos(){
		return this.filhos.size();
	}
    public int[] getBoard() {
        return this.board;
    }

    public Node getFather() {
 
        return this.father;
    }
    
    public int getNivel(){
        return this.nivel;
    }
    public void setHeuristica(int x){
    	this.heuristica=x;
        return;
    }
    public int getHeristica(){
    	return heuristica;
    }
    
    public void calculaHeuristica(){
    	int x = functionAval(this.jogada);
    	int y = functionAval(this.jogada == 1 ? 2 : 1);
 
        heuristica = y-x;
    	
    }
   
    
       
    private int functionAval(int jogada) {
        int i,cont=0;
        for (i = 0; i < 8; i++) {
            if (i == 0 || i == 3 || i == 6) {  //avalia linhas
                if (this.board[i] == jogada || this.board[i]==0) {
                   if (this.board[i + 1] == jogada || this.board[i+1]==0) {
                        if (this.board[i + 2] == jogada || this.board[i +2] ==0) {
                            cont++;
                        }
                    }
                }
               
            }
            
            if(i==0 || i==1 || i==2){    //avalia as colunas
               if (this.board[i] == jogada || this.board[i]==0) {
                   if (this.board[i + 3] == jogada || this.board[i+3]==0) {
                        if (this.board[i + 6] == jogada || this.board[i +6] ==0) {
                        	cont++;
                        }
                    }
                }
            }
            
            if(i==6 || i == 7){    //avalia as diagonais
                if (this.board[4] == jogada || this.board[4] == 0) {
                  if(i==6){//avalia principal
                     if (this.board[0] == jogada || this.board[0] == 0) {
                       if (this.board[8] == jogada || this.board[8] == 0) {
                    	   cont++;
                       }
                     }
                  }else {//avalia secundaria
                         if (this.board[2] == jogada || this.board[2] == 0) {
                          if (this.board[6] == jogada || this.board[6] == 0){   
                        	  cont++;
                        }
                    }
                }
            }
            } 

        }
        return cont;
    }
   
  
    
    
//avalia qual jogador ganhou: 1 = X ou 2 = O
    public int ganhou() {
        int jogGanho = 0;
        int i,vazio=0, cont = 0;
        for (i = 0; i < 8; i++) {
            if (i == 0 || i == 3 || i == 6) {  //avalia linhas
                if ((this.board[i] == this.board[i + 1]) && (this.board[i + 1] == this.board[i + 2])) {
                    jogGanho = this.board[i];
                }
            }

            if (i == 0 || i == 1 || i == 2) {    //avalia as colunas
                if ((this.board[i] == this.board[i + 3]) && (this.board[i + 3] == this.board[i + 6])) {
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
            if(board[i]==0){//conta os vazios
            	vazio++;
            }
        }
        if(vazio==0){//testa empate
        	return -1;
        }else{
        	return jogGanho;
        }
    }
	public Node getMelhorjogada() {
		
		return this.melhorJogada;
	}
	//debugers
    public void printBoard() {
        for (int i = 0; i < 9; i += 3) {
            System.out.println(board[i] + "  " + board[i + 1] + "  "
                    + board[i + 2]);
        }
        System.out.println(".......");
    }
}