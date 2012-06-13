/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package trab_ia_jogovelha;


     
    
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
    public char[] getBoard(){
    	char[] board = new char[9];
    	for (int r=0; r<ROWS; r++) {
            for (int c=0; c<COLS; c++) {
            	if(contents[r][c].getFace() == null){
            		board[r*3 + c] = '0';
            	} else{
            		board[r*3 + c] = contents[r][c].getFace().charAt(0);
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
                contents[r][c] = new Tile(r, c, Integer.toString((r*COLS+c+1)) );
            }
        }
        emptyTile = contents[ROWS-1][COLS-1];
        emptyTile.setFace(null);
    }
    
    /*
     * Desenha o quadro para um determinado nodo
     */
    public void result(Node result) {
        for (int r=0; r<ROWS; r++) {
            for (int c=0; c<COLS; c++) {
            	contents[r][c] = new Tile(r, c, Integer.toString((result.getBoard()[r*3 + c])));
            	if(result.getBoard()[r*3 + c] == '0'){
            		emptyTile = contents[r][c];
            		emptyTile.setFace(null);
            	}
            }
        }
    }
    
    public void result(char[] board) {
        for (int r=0; r<ROWS; r++) {
            for (int c=0; c<COLS; c++) {
            	contents[r][c] = new Tile(r, c, Character.toString(board[r*3 + c]));
            	if(board[r*3 + c] == '0'){
            		emptyTile = contents[r][c];
            		emptyTile.setFace(null);
            	}
            }
        }
    }
    
    /*
     * Realiza a interface de troca dos quadros
     */
    public boolean moveTile(int r, int c) {
        return checkEmpty(r, c, -1, 0) || checkEmpty(r, c, 1, 0)
            || checkEmpty(r, c, 0, -1) || checkEmpty(r, c, 0, 1);
    }
    
    private boolean checkEmpty(int r, int c, int rdelta, int cdelta) {
        int rNeighbor = r + rdelta;
        int cNeighbor = c + cdelta;
        if (isLegalRowCol(rNeighbor, cNeighbor) 
                  && contents[rNeighbor][cNeighbor] == emptyTile) {
            exchangeTiles(r, c, rNeighbor, cNeighbor);
            return true;
        }
        return false;
    }
    
    public boolean isLegalRowCol(int r, int c) {
        return r>=0 && r<ROWS && c>=0 && c<COLS;
    }
    
    private void exchangeTiles(int r1, int c1, int r2, int c2) {
        Tile temp = contents[r1][c1];
        contents[r1][c1] = contents[r2][c2];
        contents[r2][c2] = temp;
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
