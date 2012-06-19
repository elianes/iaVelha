package iaVelha;
/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */


import java.util.Queue;

import javax.swing.JFrame;

/**
 *
 * @author eliane
 */
public class Trab_IA_jogoVelha {
	private static Queue<Node> fifoResul;
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        
    	/*
    	JFrame window = new JFrame("TIC - TAC - TOE");
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setContentPane(new JogoVelhaUI());
        window.pack();  
        window.show();  
        window.setResizable(false);
        
        // pc x pc
    	Node aux;
         int visitados,jogadas;
    	JogoVelha game=new JogoVelha();
    	fifoResul=game.CorteAB_pcxpc();
    	visitados=game.getVisitados();
    	jogadas=game.getJogadas();
    	while(!fifoResul.isEmpty()){
    		aux=fifoResul.remove();
    		printBoard(aux.getBoard());
    		
    	}
    	/*
    	 * pc x user
    	 */
      	Node aux;
        int visitados,jogadas;
        JogoVelha game=new JogoVelha();
        int[] entrada={0,2,2, 2, 1, 1, 1, 1, 0};
        aux=game.CorteAB_pcxuser(entrada,2);
        visitados=game.getVisitados();
        jogadas=game.getJogadas();
       	printBoard(aux.getBoard());
   		
        
    	
    	//game.getResultado();
    	System.out.print("Foram visitados " + visitados + "\n");
    	System.out.print("Foram feitas " + jogadas + "jogadas \n");
       
        
          
    	
    }
    /*
     * autor vanderson debugers
     */

    public static void printBoard(int[] x) {
        for (int i = 0; i < 9; i += 3) {
            System.out.println(x[i] + "  " + x[i + 1] + "  "
                    + x[i + 2]);
        }
        System.out.println(".......");
    }
}
