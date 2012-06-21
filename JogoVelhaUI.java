/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
//package servidor.iaVelha;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

import javax.swing.*;


/*
 * Cria o painel de interface com o usuário com botões e quadro de jogo
 */
public class JogoVelhaUI extends JPanel {

	private GraphicsPanel jogoVelhaBoardUI;
	private JogoVelhaModel jogoVelhaModel;
	
	private Node node;
	private int npassos, nvisitados, ipasso = 0;
	private JogoVelha game;
	private Queue<Node> fifoResul = new  LinkedList<Node>();
	
	
	private JPanel controlPanel;
	private JPanel statusBar;
    private JLabel label;
    private JTextField text;
    private JButton jogadorXpc;
	private JButton pcXpc;
	private JButton minmax;
	private JButton minmaxCLimite;
	private JButton corteAB;
	private JButton next;
	public boolean minmaxuser;
	public boolean corteabuser;
	
	public JogoVelhaUI() {
		game = new JogoVelha();
		jogoVelhaModel = new JogoVelhaModel();
               int[] boardInicial = {0, 0, 0, 0, 0, 0, 0, 0, 0};
                node = new Node(boardInicial);
		jogadorXpc = new JButton("Jogador X PC");
		jogadorXpc.addActionListener(new JogadorXpc());

		pcXpc = new JButton("PC X PC");
		pcXpc.addActionListener(new PcXPc());

		corteAB = new JButton("Corte Alfa-Beta");
		corteAB.addActionListener(new CorteABExc());

		minmax = new JButton("MinMax");
		minmax.addActionListener(new MinmaxExc());

		minmaxCLimite = new JButton("MinMax com limite");
		minmaxCLimite.addActionListener(new MinmaxCLimiteExc());

		label = new JLabel("PC X PC ou Jogador X PC ?");
		text = new JTextField();

		next= new JButton("Mostrar Passos");
		next.addActionListener(new Next());
		
		controlPanel = new JPanel();
		controlPanel.setLayout(new GridLayout(7, 2));

		controlPanel.add(jogadorXpc);
		controlPanel.add(pcXpc);
		controlPanel.add(corteAB);
		controlPanel.add(minmax);
		controlPanel.add(minmaxCLimite);
		controlPanel.add(text);
		controlPanel.add(next);
		jogoVelhaBoardUI = new GraphicsPanel();

		this.setLayout(new BorderLayout());
		this.add(controlPanel, BorderLayout.WEST);
		this.add(jogoVelhaBoardUI, BorderLayout.EAST);
		this.add(label, BorderLayout.SOUTH);

	}

	public class GraphicsPanel extends JPanel implements MouseListener {

		private static final int ROWS = 3;
		private static final int COLS = 3;
		private static final int CELL_SIZE = 60;
		private Font _biggerFont;

		public GraphicsPanel() {
			_biggerFont = new Font("Monospaced", Font.BOLD, CELL_SIZE / 2);
			this.setPreferredSize(new Dimension(CELL_SIZE * COLS, CELL_SIZE
					* ROWS));
			this.setBackground(Color.black);
			this.addMouseListener(this);
		}

		public void paintComponent(Graphics g) {
			super.paintComponent(g);
			for (int r = 0; r < ROWS; r++) {
				for (int c = 0; c < COLS; c++) {
					int x = c * CELL_SIZE;
					int y = r * CELL_SIZE;
					String text = jogoVelhaModel.getFace(r, c);

					if (text != null) {
						g.setColor(Color.white);
						g.fillRect(x + 2, y + 2, CELL_SIZE - 4, CELL_SIZE - 4);
						if (text.equals("X")) {
							g.setColor(Color.blue);
						} else {
							g.setColor(Color.red);
						}
						g.setFont(_biggerFont);
						g.drawString(text, x + 20, y + (3 * CELL_SIZE) / 4);
					}
				}
			}
		}

		public void mouseClicked(MouseEvent e) {
			int col = e.getX() / CELL_SIZE;
			int row = e.getY() / CELL_SIZE;
                        
			if(game.getDisputa() == 1) {
				if(jogoVelhaModel.result(row, col)) {
					if(minmaxuser) {
                                                node = new Node(jogoVelhaModel.getBoard());
                                                node.printBoard();
                                               	node = game.minMax_UserXPC(node); 
                                                game.setEstadoInicial(node);
					        jogoVelhaModel.result(node);
                                        }
					if(corteabuser) {
						node = game.CorteAB_pcxuser(jogoVelhaModel.getBoard(),2);
						jogoVelhaModel.result(node);
					}
					this.repaint();
				}
			}
		}

		public void mousePressed(MouseEvent e) {
		}

		public void mouseReleased(MouseEvent e) {
		}

		public void mouseEntered(MouseEvent e) {
		}

		public void mouseExited(MouseEvent e) {
		}
	}

	/*
	 * Botões de ação do jogo
	 */
	public class JogadorXpc implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			game = new JogoVelha();
			minmaxuser = false;
			corteabuser = false;
			jogoVelhaModel.reset();
            game.setDisputa(1);
			label.setText(" Escolha o Algoritmo");
			jogoVelhaBoardUI.repaint();
		}
	}
	
	public class PcXPc implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			game = new JogoVelha();
			jogoVelhaModel.reset();
			game.setDisputa(2);
			label.setText(" Escolha o Algoritmo");
			jogoVelhaBoardUI.repaint();
		}
	}

	public class CorteABExc implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			if(game.getDisputa() == -1) {
				label = new JLabel("PC X PC ou Jogador X PC ?");
			}
			else {
				if (game.getDisputa() == 2) {
					fifoResul = game.CorteAB_pcxpc();
					nvisitados = game.getVisitados();
			    	npassos = game.getJogadas();
			    	label.setText("Nodos Visitados " + nvisitados + " Jogadas " + npassos);
				} else {
					corteabuser = true;
	            	label.setText(" Aguardando Jogada do Usuário");
				}
		    }
	    	jogoVelhaBoardUI.repaint();
		}
	}

	
	public class MinmaxExc implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			if(game.getDisputa() == -1) {
				label = new JLabel("PC X PC ou Jogador X PC ?");
			}
			else {
	            if (game.getDisputa() == 2) {   //para decidir qual tipo de jogo   
	            	fifoResul = game.minMax_PCXPC();
	            	nvisitados = game.getVisitados();
			npassos = game.getJogadas();
			label.setText(" Nodos Visitados " + nvisitados + " Jogadas " + npassos);
	            } else {	
                        game.geraArvore();
	            	minmaxuser = true;
	            	label.setText(" Aguardando Jogada do Usuário");
	            }
	            jogoVelhaBoardUI.repaint();
	          }
		}

	}

	public class MinmaxCLimiteExc implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			int limite = 5;   //pegar o limite do usuario
                        label.setText("Defina o limite.");
			String entrada = text.getText();    // Abilio: Pegar a entrada que do campo! 
			 // Parser String to Int, agora é só passar na função :)
           while(entrada == null){
               entrada = text.getText();  
              label.setText("Você não definiu o limite.");
             
               limite = Integer.parseInt(entrada);
           }
                 
            game.setLimite(limite);
            Node saida;
            if (game.getDisputa() == 2) {   //para decidir qual tipo de jogo
            	game.minMax_PCXPC();
               	fifoResul = game.minMax_PCXPC();
               	nvisitados = game.getVisitados();
    	    	npassos = game.getJogadas();
    	    	label.setText("Nodos Visitados " + nvisitados + " Jogadas " + npassos);
            } else {
            	game.geraArvore();
            	minmaxuser = true;
            	label.setText(" Aguardando Jogada do Usuário");
            }
			jogoVelhaBoardUI.repaint();
		}
	}


	public class Next implements ActionListener {
		public void actionPerformed(ActionEvent e) {
	            if (fifoResul.isEmpty()) {
	                ipasso = 0;
	                jogoVelhaModel.reset();
	                next.setText("Mostrar Jogada");
	                label.setText("Inicie um novo jogo");
	            }
	            else {
			    	label.setText("Nodos Visitados " + nvisitados + " Jogadas " + npassos);
			    	//int[] board = { 0, 1, 0, 2, 0, 0, 0, 0, 0 };
					jogoVelhaModel.result(fifoResul.remove());
					next.setText("Jogada " + (ipasso + 1));
		            ipasso++;
				}
	            jogoVelhaBoardUI.repaint();
		}
	}
	
}
