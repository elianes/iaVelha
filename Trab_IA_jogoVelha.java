/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package trab_ia_jogovelha;

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
        
        JFrame window = new JFrame("Puzzle-8 IA");
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setContentPane(new JogoVelhaUI());
        window.pack();  
        window.show();  
        window.setResizable(false);
	// TODO code application logic here
    }
}
