package servidor.iaVelha;
/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */


import javax.swing.JFrame;

/**
 *
 * @author eliane
 */
public class Trab_IA_jogoVelha {

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
       
	*/
          	int visitados,jogadas;
    	JogoVelha game=new JogoVelha();
    	game.CorteAB_pcxpc();
    	visitados=game.getVisitados();
    	jogadas=game.getJogadas();
    	game.getResultado();
    	System.out.print("Foram visitados " + visitados + "\n");
    	System.out.print("Foram feitas " + jogadas + "jogadas \n");
       
     
          
    	
    }
}
