
package trab_ia_jogovelha;

import java.util.List;
import java.util.ArrayList;

/*
 * Nodo para a arvore, contem:
 * 		char[] board - quadro do jogo
 * 		Node father - nodo pai
 *		int lastEmpthy - possição do zero (vazio)
 */
public class Node {
	private char[] board;
	private Node father;
	private int lastEmpthy;
	
	public  Node(Node father){
		this.father = father;
	}
	
	public  Node(char[] board){
		this.board = board;
	}

	/*
	 * set e get
	 */
	public void setLastEmpthy(int x){
		this.lastEmpthy=x;
	}
	
	public int getLastEmpthy(){
		return this.lastEmpthy;
	}
	
	public char[] getBoard(){
		return this.board;
	}

	public Node getFather() {
		return this.father;
	}
	
	/*
	 * Métodos de movimentos
	 */
	public void up() {
		char value;
		int empthy = father.getLastEmpthy();
		this.board = father.getBoard().clone();
		value = board[empthy - 3];
		board[empthy - 3] = '0';
		board[empthy] = value;
		lastEmpthy = empthy - 3;
	}

	public void down() {
		char value;
		int empthy = father.getLastEmpthy();
		this.board = father.getBoard().clone();
		value = board[empthy + 3];
		board[empthy + 3] = '0';
		board[empthy] = value;
		lastEmpthy = empthy + 3;
	}

	public void rigth() {
		char value;
		int empthy = father.getLastEmpthy();
		this.board = father.getBoard().clone();
		value = board[empthy + 1];
		board[empthy + 1] = '0';
		board[empthy] = value;
		lastEmpthy = empthy + 1;
	}

	public void left() {
		char value;
		int empthy = father.getLastEmpthy();
		this.board = father.getBoard().clone();
		value = board[empthy - 1];
		board[empthy - 1] = '0';
		board[empthy] = value;
		lastEmpthy = empthy - 1;
	}
	
	/*
	 * Busca posição do zero
	 */
	public void findEmpthy() {
		for (int i = 0; i < 9; i++)
			if (board[i] == '0') {
				lastEmpthy = i;
				return;
			}
		lastEmpthy = 0;
	}
	
}
