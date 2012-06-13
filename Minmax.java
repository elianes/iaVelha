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
public class Minmax {
    private List<Node> sucessores;  //lista de filhos do estado
    private JogoVelha jogada;
    private Node jogadaCerta;
    
    public Minmax(Node estado){
		this.sucessores = new LinkedList<Node>();
		sucessores.add(estado);
    }
    
    public void minMax(Node estado){
       
        while(!sucessores.isEmpty()){
            if(jogada.getJogador() == 1){
              maximoSuc();
            }else{
              minimoSuc();
            }
        }
   
    }

    private void maximoSuc() {
        int maior, index;
        maior = sucessores.get(0).getHeristica();
        index=0;
        for(int i=1; i<sucessores.size();i++){
            if(sucessores.get(i).getHeristica()>maior){
                maior =  sucessores.get(0).getHeristica();
                index=i;
            }
        }
        jogadaCerta =sucessores.get(index);
    }

    private void minimoSuc() {
          int menor, index;
        menor = sucessores.get(0).getHeristica();
        index=0;
        for(int i=1; i<sucessores.size();i++){
            if(sucessores.get(i).getHeristica()<menor){
                menor =  sucessores.get(0).getHeristica();
                index=i;
            }
        }
        jogadaCerta =sucessores.get(index);
    }
			
		
}
