/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package trab_ia_jogovelha;

import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

import javax.swing.*;


/*
 * Cria o painel de interface com o usuário
 *      com botões e quadro de jogo
 */
public class JogoVelhaUI extends JPanel {
	
	private static JogoVelha velha;
	private Node node;
	private GraphicsPanel jogoVelhaBoardUI;
	private JogoVelhaModel jogoVelhaModel;
	
	private static int indexStep;
	private ArrayList<Node> result;

	private JPanel controlPanel;
	private JButton marcar;
        private JButton initial;
	private JButton minmax;
	private JButton minmaxCLimite;
	private JButton corteAB;
	private JLabel label;
	private JTextField text;
	
	public JogoVelhaUI() {
		velha = new JogoVelha();
		jogoVelhaModel = new JogoVelhaModel();
		
		initial = new JButton("Initial Game");
		initial.addActionListener(new Initial());

		minmax = new JButton("MinMax");
		minmax.addActionListener(new MinmaxExc());

		minmaxCLimite = new JButton("Minmax com limite");
		minmaxCLimite.addActionListener(new MinmaxCLimiteExc());

		corteAB = new JButton("Corte alfa-beta");
		corteAB.addActionListener(new CorteABExc());

		marcar = new JButton("Marcar");
		marcar.addActionListener(new Marcar());

		label = new JLabel(" Initial Game in Puzzle or Text Field");

		text = new JTextField();
		
		controlPanel = new JPanel();
		controlPanel.setLayout(new GridLayout(6, 2));

		controlPanel.add(initial);
		controlPanel.add(minmax);
		controlPanel.add(minmaxCLimite);
		controlPanel.add(corteAB);
		controlPanel.add(marcar);
		controlPanel.add(text);
		
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
			_biggerFont = new Font("SansSerif", Font.BOLD, CELL_SIZE / 2);
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
					//String text = jogoVelhaModel.getFace(r, c);
					if (text != null) {
						g.setColor(Color.white);
						g.fillRect(x + 2, y + 2, CELL_SIZE - 4, CELL_SIZE - 4);
						g.setColor(Color.black);
						g.setFont(_biggerFont);
						//g.drawString(text, x + 20, y + (3 * CELL_SIZE) / 4);
					}
				}
			}
		}

		public void mousePressed(MouseEvent e) {
			int col = e.getX() / CELL_SIZE;
			int row = e.getY() / CELL_SIZE;
			if (!jogoVelhaModel.moveTile(row, col)) {
				Toolkit.getDefaultToolkit().beep();
			}
			this.repaint();
		}

		public void mouseClicked(MouseEvent e) {
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
	public class Initial implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent ae) {
            throw new UnsupportedOperationException("Not supported yet.");
        }
		
	}

	public class MinmaxExc implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			
		}
	}

	public class Marcar implements ActionListener {
		public synchronized void actionPerformed(ActionEvent e) {
			
		}
	}

	public class MinmaxCLimiteExc implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			
		}
	}

	public class CorteABExc implements ActionListener {
		public void actionPerformed(ActionEvent e) {
		
		}
	}
}
