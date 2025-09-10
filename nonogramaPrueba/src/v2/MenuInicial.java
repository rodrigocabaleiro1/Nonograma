package v2;

//import modelo.JuegoNonograma;
//import controlador.Controlador;

import javax.swing.*;
import javax.swing.border.EmptyBorder;

import java.awt.*;

public class MenuInicial extends JFrame {

    public MenuInicial() {
        setTitle("Nonograma - Seleccionar dificultad");
        setSize(640, 480);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        
        //
        JPanel contenedor = new JPanel();
        
        JLabel titulo = new JLabel("SELECCIONAR DIFICULTAD", SwingConstants.CENTER);
        titulo.setFont(new Font("Arial", Font.BOLD, 18));
        titulo.setForeground(Color.white);
        add(titulo);
        
        //Estilos del contenedor
        contenedor.setLayout(new GridLayout(0, 1)); // n filas, 1 columna   
        contenedor.setBorder(new EmptyBorder(20, 20, 20, 20)); //margenes
        definirColorDeFondo(contenedor, Color.DARK_GRAY);
        
        //	Iniciar Botones
        JButton btn5x5 = new JButton("5 x 5");
        JButton btn10x10 = new JButton("10 x 10");
        JButton btn15x15 = new JButton("15 x 15");
        JButton btn20x20 = new JButton("20 x 20");
        
        //ColorearBotones
        definirColorDeFondo(btn5x5, new Color(0, 184, 255)); //azul
        definirColorDeFondo(btn10x10, new Color(124, 255, 95)); //verde
        definirColorDeFondo(btn15x15, new Color(240, 220, 93)); //amarillo
        definirColorDeFondo(btn20x20, new Color(255, 80, 80));	//rojo
        btn5x5.setForeground(Color.BLACK); //pinto el texto
        btn10x10.setForeground(Color.BLACK); //pinto el texto
        btn15x15.setForeground(Color.BLACK); //pinto el texto
        btn20x20.setForeground(Color.BLACK); //pinto el texto

        //	ordenar Elementos en pantalla
        agregarElementosPanel(getContentPane(), contenedor, BorderLayout.CENTER);
        agregarElementosPanel(contenedor, titulo);
        agregarElementosPanel(contenedor, btn5x5);
        agregarElementosPanel(contenedor, btn10x10);
        agregarElementosPanel(contenedor, btn15x15);
        agregarElementosPanel(contenedor, btn20x20);
        
        //	Eventos
        btn5x5.addActionListener(e -> iniciarJuego(5));
        btn10x10.addActionListener(e -> iniciarJuego(10));
        btn15x15.addActionListener(e -> iniciarJuego(15));
        btn20x20.addActionListener(e -> iniciarJuego(20));
    }

    private void iniciarJuego(int size) {
        JuegoNonograma juego = new JuegoNonograma(size);
        VistaNonograma vista = new VistaNonograma(juego);
        Controlador controlador = new Controlador(juego, vista);

        vista.setControlador(controlador);
        vista.setVisible(true);

        dispose(); // cerrar menú inicial
    }
    
    //-------------------------------------------------------
    //metodos que quiza se repitan en otra clase
    //-------------------------------------------------------
    private void agregarElementosPanel(Container container, JPanel elemento, String layout) {
		container.add(elemento, layout);
	}
    private void agregarElementosPanel(Container container, JButton elemento, String layout) {
		container.add(elemento, layout);
	}
    private void agregarElementosPanel(Container container, JButton elemento) {
		container.add(elemento);
	}
	private void agregarElementosPanel(Container container, JLabel elemento) {
		container.add(elemento);
	}
	private void definirColorDeFondo(JPanel panel, Color color) {
		panel.setBackground(color);
	}
	
	private void definirColorDeFondo(JButton boton, Color color) {
		boton.setBackground(color);
	}
}
