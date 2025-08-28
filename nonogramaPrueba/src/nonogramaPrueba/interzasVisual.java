package nonogramaPrueba;


import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

public class interzasVisual extends JFrame {
	
	//Datos de la pantalla
	private int ancho = 900;
	private int alto = 700;
	private String titulo = "Nonograma - Tp N°1";
	
	private CardLayout cardLayout;
	private JPanel mainPanel; //contenedor con varias "pantallas"
	
	private int grillas = 20;
	private JButton[][] grillasBotones;
	
	public interzasVisual() {
		
		//Datos de la pantalla
		setTitle(titulo);
		setSize(ancho, alto);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null); 
        
        //Usa CarLayout para cambiar entre pantallas
        cardLayout = new CardLayout();
        mainPanel = new JPanel(cardLayout);
        
        //creo las pantallas
        JPanel menuPanel = crearMenuPanel();
        JPanel juegoPanel = crarJuegoPanel(grillas);
        
        //agrego las pantallas
        mainPanel.add(menuPanel, "menu");
        mainPanel.add(juegoPanel, "juego");
        
        add(mainPanel);
        setVisible(true);
	}
	
	//Diseño del menu
	private JPanel crearMenuPanel() {
    	//creo la pantalla
        JPanel panel = new JPanel();
        panel.setLayout(new GridBagLayout()); // Centrar elementos
        //ajusto posiciones
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(15, 15, 15, 15);
        //defino titulo
        JLabel titulo = new JLabel("Nonograma");
        titulo.setFont(new Font("Arial", Font.BOLD, 36));
       //boton de empezar a jugar 
        JButton btnJugar = new JButton("Empezar Juego (5x5)");
        btnJugar.setFont(new Font("Arial", Font.PLAIN, 20));
        btnJugar.addActionListener(e -> cardLayout.show(mainPanel, "juego"));
        //agrego el titulo del meni
        gbc.gridx = 0;
        gbc.gridy = 0;
        panel.add(titulo, gbc);
        //agrego el boton de empezar a juagr
        gbc.gridy = 1;
        panel.add(btnJugar, gbc);

        return panel;
	}
	
	//DIseño del juego
    private JPanel crarJuegoPanel(int tamanioGrilla) {
    	JPanel panel = new JPanel(new BorderLayout());

        // Panel superior para pistas de columnas
        JPanel topPanel = new JPanel();
        topPanel.setPreferredSize(new Dimension(0, 80));
        topPanel.setBackground(Color.LIGHT_GRAY);
        topPanel.add(new JLabel("Pistas columnas"));

        // Panel izquierdo para pistas de filas
        JPanel leftPanel = new JPanel();
        leftPanel.setPreferredSize(new Dimension(150, 0));
        leftPanel.setBackground(Color.LIGHT_GRAY);
        leftPanel.add(new JLabel("Pistas filas"));

        // Panel central para la grilla
        JPanel gridPanel = new JPanel(new GridLayout(tamanioGrilla, tamanioGrilla));
        JButton[][] grillaDeValores = new JButton[tamanioGrilla][tamanioGrilla];

        for (int i = 0; i < tamanioGrilla; i++) {
            for (int j = 0; j < tamanioGrilla; j++) {
                JButton cell = new JButton();
                cell.setBackground(Color.WHITE);
                cell.setFocusPainted(false);
                cell.setFont(new Font("Arial", Font.BOLD, 50));

                cell.addActionListener(new ActionListener() {
                    private int state = 0; // 0=blanco, 1=negro, 2=cruz

                    @Override
                    public void actionPerformed(ActionEvent e) {
                        state = (state + 1) % 3;
                        switch (state) {
                            case 0: // Blanco
                                cell.setBackground(Color.WHITE);
                                cell.setText("");
                                break;
                            case 1: // Negro
                                cell.setBackground(Color.BLACK);
                                cell.setText("");
                                break;
                            case 2: // Cruz
                                cell.setBackground(Color.WHITE);
                                cell.setForeground(Color.RED);
                                cell.setFont(new Font("Arial", Font.BOLD, 50));
                                cell.setText("X");
                                break;
                        }
                    }
                });

                grillaDeValores[i][j] = cell;
                gridPanel.add(cell);
            }
        }

        // Agregar todo al panel de juego
        panel.add(topPanel, BorderLayout.NORTH);
        panel.add(leftPanel, BorderLayout.WEST);
        panel.add(gridPanel, BorderLayout.CENTER);

        return panel;
    }


	public static void main(String[] args) {
        SwingUtilities.invokeLater(interzasVisual::new);
    }

}
