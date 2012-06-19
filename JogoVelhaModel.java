
package iaVelha;
/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */


     
    
/*
 * Cria o quadro de jogo
 */

public class JogoVelhaModel {
    


    private static final int ROWS = 3;
    private static final int COLS = 3;
    
    private Tile[][] contents;  
    private Tile     emptyTile; 
    
    public JogoVelhaModel() {
        contents = new Tile[ROWS][COLS];
        reset();               
    }
    
    String getFace(int row, int col) {
        return contents[row][col].getFace();
    }
    
    /*
     * Retorna um vetor de char com o quadro criado pelo usuário
     */
    public int[] getBoard(){
    	int[] board = new int[9];
    	for (int r=0; r<ROWS; r++) {
            for (int c=0; c<COLS; c++) {
            	if(contents[r][c].getFace().equals("X")){
            		board[r*3 + c] = 1;
            	} 
            	if(contents[r][c].getFace().equals("O")){
            		board[r*3 + c] = 2;
            	}
            	if(contents[r][c].getFace().equals(" ")){
            		board[r*3 + c] = 0;
            	}
            }
        }
    	return board;
    }
    
    /*
     * Desenha o quadro inicial
     */
    public void reset() {
        for (int r=0; r<ROWS; r++) {
            for (int c=0; c<COLS; c++) {
                contents[r][c] = new Tile(r, c, " ");
            }
        }
    }
    
    /*
     * Desenha o quadro para um determinado nodo
     */
    public void result(Node result) {
        for (int r=0; r<ROWS; r++) {
            for (int c=0; c<COLS; c++) {
            	if(result.getBoard()[r*3 + c] == 1){
            		contents[r][c].setFace("X");
            	}
            	if(result.getBoard()[r*3 + c] == 2){
            		contents[r][c].setFace("O");
            	}
            }
        }
    }
    
    public void result(int[] board) {
        for (int r=0; r<ROWS; r++) {
            for (int c=0; c<COLS; c++) {
            	if(board[r*3 + c] == 1){
            		contents[r][c].setFace("X");
            	}
            	if(board[r*3 + c] == 2){
            		contents[r][c].setFace("O");
            	}
            }
        }
    }
    
    public void result(int row, int col) {
    	contents[row][col].setFace("X");
	}
    
}

class Tile {
    private int row;     
    private int col;     
    private String face;  
    
    public Tile(int row, int col, String face) {
        this.row = row;
        this.col = col;
        this.face = face;
    }
    
    public void setFace(String newFace) {
        face = newFace;
    }
    
    public String getFace() {
        return face;
    }
}
