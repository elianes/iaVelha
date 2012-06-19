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
 * Cria o painel de interface com o usuário com botões e quadro de jogo
 */
public class JogoVelhaUI extends JPanel {

    private int entrada[] = new int[9];
    private static JogoVelha jogoVelha;
    private int disputa; // para saber se é pc X pc ou jogador X pc
    private Node node;
    private GraphicsPanel jogoVelhaBoardUI;
    private JogoVelhaModel jogoVelhaModel;
    private static int indexStep;
    private ArrayList<Node> result;
    private JPanel controlPanel;
    private JButton jogadorXpc;
    private JButton pcXpc;
    private JButton minmax;
    private JButton minmaxCLimite;
    private JButton corteAB;
    private JButton Inicial;
    private JLabel label;
    private JTextField text;

    public JogoVelhaUI() {


        jogoVelha = new JogoVelha();

        jogoVelhaModel = new JogoVelhaModel();

        jogadorXpc = new JButton("Jogador X PC");
        jogadorXpc.addActionListener(new JogadorXpc());

        pcXpc = new JButton("PC X PC");
        pcXpc.addActionListener(new PcXPc());

        minmax = new JButton("MinMax");
        minmax.addActionListener(new MinmaxExc());

        minmaxCLimite = new JButton("Minmax com limite");
        minmaxCLimite.addActionListener(new MinmaxCLimiteExc());

        corteAB = new JButton("Corte alfa-beta");
        corteAB.addActionListener(new CorteABExc());

        Inicial = new JButton("jogar novamente");
        Inicial.addActionListener(new Inicial());

        label = new JLabel("Selecione como vai ser a jogada");

        text = new JTextField();

        controlPanel = new JPanel();
        controlPanel.setLayout(new GridLayout(6, 2));

        controlPanel.add(Inicial);
        controlPanel.add(jogadorXpc);
        controlPanel.add(pcXpc);
        controlPanel.add(minmax);
        controlPanel.add(minmaxCLimite);
        controlPanel.add(corteAB);
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
            jogoVelhaModel.result(row, col);
            node = new Node(jogoVelhaModel.getBoard());
            this.repaint();
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
    public class Inicial implements ActionListener {

        public void actionPerformed(ActionEvent e) {
            jogoVelha = new JogoVelha();
            int[] board = {0, 0, 0, 0, 0, 0, 0, 0, 0};
            node = new Node(board);
            jogoVelhaModel.result(node);
            jogoVelhaBoardUI.repaint();
        }
    }

    public class JogadorXpc implements ActionListener {

        public void actionPerformed(ActionEvent e) {

            jogoVelha.setDisputa(1);
            label.setText("Agora selecione o algoritmo.");
            label.getText();

        }
    }

    public class PcXPc implements ActionListener {

        public void actionPerformed(ActionEvent e) {
            jogoVelha.setDisputa(2);
            label.setText("Agora selecione o algoritmo.");
            label.getText();
        }
    }

    public class MinmaxExc implements ActionListener {

        public void actionPerformed(ActionEvent e) {
            Node saida;
            if (jogoVelha.getDisputa() == 2) {   //para decidir qual tipo de jogo   
                jogoVelha.minMax_PCXPC();
                saida = jogoVelha.getResul();
                do {
                    saida.printBoard();
                    jogoVelhaModel.result(saida);
                    jogoVelhaBoardUI.repaint();
                    saida = jogoVelha.getResul();
                } while (saida != null);

            } else {
                jogoVelha.minMax_UserXPC();

            }


        }
    }

    public class MinmaxCLimiteExc implements ActionListener {

        public void actionPerformed(ActionEvent e) {

            int limite = 5;   //pegar o limite do usuario
            text.getAction();
            label.setText(text.getText());
            jogoVelha.setLimite(limite);
            Node saida;
            if (jogoVelha.getDisputa() == 2) {   //para decidir qual tipo de jogo
                jogoVelha.minMax_PCXPC();
                saida = jogoVelha.getResul();
                while (saida != null) {
                    saida.printBoard();
                    jogoVelhaModel.result(saida);
                    jogoVelhaBoardUI.repaint();
                    saida = jogoVelha.getResul();

                }

            } else {
                jogoVelha.minMax_UserXPC();
            }
        }
    }

    public class CorteABExc implements ActionListener {

        public void actionPerformed(ActionEvent e) {
            int[] board = {0, 1, 0, 2, 0, 0, 0, 0, 0};
            jogoVelhaModel.result(board);
            jogoVelhaBoardUI.repaint();
            printBoard(jogoVelhaModel.getBoard());

        }
    }

    public void printBoard(int[] x) {
        for (int i = 0; i < 9; i += 3) {
            System.out.println(x[i] + "  " + x[i + 1] + "  " + x[i + 2]);
        }
        System.out.println(".......");
    }
}
