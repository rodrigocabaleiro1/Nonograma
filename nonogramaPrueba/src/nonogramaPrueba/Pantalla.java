package nonogramaPrueba;

import java.awt.EventQueue;
import java.awt.Point;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import java.awt.BorderLayout;
import java.awt.CardLayout;

import javax.swing.JTable;
import javax.swing.JTextArea;

import java.awt.Color;
import java.awt.Container;

import javax.swing.JInternalFrame;
import javax.swing.JLabel;

public class Pantalla extends JFrame {

	private static final long serialVersionUID = 1L;
	private int tamano;
	JButton[][] botones;
	String[][] valorBotones;
	JLabel [][] pistasFilas;
	JLabel [][] pistasColumnas;
	private CardLayout cardLayout;
	JPanel panelPrincipal;
	JPanel juego;
	JPanel menuPrincipal;
	


	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Pantalla frame = new Pantalla();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}


	public Pantalla() {
		
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 480, 500);
		getContentPane().setLayout(new BorderLayout(0, 0));
		setTitle("Trabajo Practico N°1 : NONOGRAMA");
		
		getContentPane().setLayout(new BorderLayout());;
		panelPrincipal = new JPanel();
		panelPrincipal.setLayout(new BorderLayout(0, 0));
		menuPrincipal();
		agregarElementosPanel(getContentPane(), panelPrincipal, BorderLayout.CENTER);
	}


	private void menuPrincipal() {
		//creo la pantalla
        JPanel menu = new JPanel();
        menu.setLayout(new GridBagLayout()); // Centrar elementos
        
        
        //ajusto posiciones
        GridBagConstraints contenedor = new GridBagConstraints();
        contenedor.insets = new Insets(15, 15, 15, 15);
        
        //defino titulo
        JLabel titulo = new JLabel("Nonograma");
        titulo.setFont(new Font("Arial", Font.BOLD, 36));
        
       //boton de empezar a jugar 
        JButton jugar = new JButton("Empezar Juego (5x5)");
        jugar.setFont(new Font("Arial", Font.PLAIN, 20));
        definirFuncionalidadJugar(jugar, 20, panelPrincipal);
        
        //agrego el titulo del meni
        contenedor.gridx = 0;
        contenedor.gridy = 0;
        menu.add(titulo, contenedor);
        
        //agrego el boton de empezar a jugar
        contenedor.gridy = 1;
        menu.add(jugar, contenedor);
        
        agregarElementosPanel(panelPrincipal, menu, BorderLayout.CENTER);
		
	}
	
	private void definirFuncionalidadJugar(JButton boton, int longitud, JPanel panel) {
		// Agregar acción
        boton.addActionListener(new ActionListener() {         

            @Override
            public void actionPerformed(ActionEvent e) {
            	panelPrincipal.removeAll();
            	tamano = longitud;
            	juego();
            	panelPrincipal.revalidate();
            	panelPrincipal.repaint();
        	}
        });
	}


	private void juego() {
		//Definir Paneles
		JPanel header = new JPanel();
		JPanel foother = new JPanel();
		JPanel body = new JPanel();
		JPanel margenIzquierdo = new JPanel();
		JPanel margenDerecho = new JPanel();
		JPanel pistasArriba = new JPanel();
		JPanel pistasIzquierda = new JPanel();
		JPanel tablero = new JPanel();
		JPanel contenedorPistasArriba = new JPanel();
		JPanel margenIzquierdoPistasArriba = new JPanel();
		
		//Dimensiones de los paneles
		//modificarAlturaPanel(header, 30);
		modificarAlturaPanel(foother, 15);
		modificarAnchoPanel(margenIzquierdo, 10);
		modificarAnchoPanel(margenDerecho, 10);
		modificarAlturaPanel(pistasArriba, 16);
		modificarAnchoPanel(pistasIzquierda, 16);
		modificarAnchoPanel(margenIzquierdoPistasArriba, 16);
		
		//Manejo de Layouts
		body.setLayout(new BorderLayout(0, 0)); // Establecer BorderLayout al body
		contenedorPistasArriba.setLayout(new BorderLayout(0, 0)); //Establecer BorderLayout para organizar pistas arriba		
		tablero.setLayout(new GridLayout(tamano, tamano, 0, 0));	// Definir layout del tablero como una grilla	
		pistasArriba.setLayout(new GridLayout(2, tamano, 0, 16));
		pistasIzquierda.setLayout(new GridLayout(tamano, 2, 16, 0));		
		header.setLayout(new BorderLayout(0,20));
		
		header.setBorder(BorderFactory.createEmptyBorder(20, 16, 10, 20)); // márgenes externos
		
		
		
		//Organizar paneles
		agregarElementosPanel(panelPrincipal, header, BorderLayout.NORTH);
		agregarElementosPanel(panelPrincipal, foother, BorderLayout.SOUTH);
		agregarElementosPanel(panelPrincipal, body, BorderLayout.CENTER);	
		agregarElementosPanel(panelPrincipal, margenIzquierdo, BorderLayout.WEST);
		agregarElementosPanel(panelPrincipal, margenDerecho, BorderLayout.EAST);	
		agregarElementosPanel(body, contenedorPistasArriba, BorderLayout.NORTH);
		agregarElementosPanel(body, pistasIzquierda, BorderLayout.WEST);
		agregarElementosPanel(body, tablero, BorderLayout.CENTER);
		agregarElementosPanel(contenedorPistasArriba, pistasArriba, BorderLayout.CENTER);
		agregarElementosPanel(contenedorPistasArriba, margenIzquierdoPistasArriba, BorderLayout.WEST);
		
		//Colors--------------
		definirColorDeFondo(header, Color.DARK_GRAY);
		definirColorDeFondo(foother, Color.DARK_GRAY);
		definirColorDeFondo(pistasArriba, Color.LIGHT_GRAY);
		definirColorDeFondo(pistasIzquierda, Color.LIGHT_GRAY);
		definirColorDeFondo(tablero, Color.LIGHT_GRAY);
		definirColorDeFondo(margenIzquierdo, Color.DARK_GRAY);
		definirColorDeFondo(margenDerecho, Color.DARK_GRAY);
		definirColorDeFondo(margenIzquierdoPistasArriba, Color.LIGHT_GRAY);
		
		//Agregar Bordes
		body.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));
		tablero.setBorder(BorderFactory.createLineBorder(Color.BLACK, 1));
		
		
		//Funcionalidades
		llenarTablero(tablero);
		crearPistas(pistasArriba, pistasIzquierda);
		evaluarJugada(header);
		ayuda(header);
		
		
	}


	private void evaluarJugada(JPanel contenedor) {
		//Terminar partida
		JButton evaluar = new JButton("Evaluar resultado");
		agregarElementosPanel(contenedor, evaluar, BorderLayout.EAST);
		definirColorDeFondo(evaluar, Color.WHITE);
		
		evaluar.setBorder(BorderFactory.createLineBorder(Color.BLACK,2));
		evaluar.setMargin(new Insets(10,10,10,10));
	}
	
	private void ayuda(JPanel contenedor) {
		//Terminar partida
		JButton ayuda = new JButton(" ? ");
		agregarElementosPanel(contenedor, ayuda, BorderLayout.WEST);
		definirColorDeFondo(ayuda, Color.CYAN);
		ayuda.setBorder(BorderFactory.createEmptyBorder(10,10,10,10));
		ayuda.addActionListener(e -> mostrarAyuda());
	}
	
	private void mostrarAyuda() {
        JFrame nueva = new JFrame("Reglas del Juego");
        nueva.setSize(480, 640);
        nueva.setLocationRelativeTo(null);

        // 👇 importante: que solo cierre esta ventana, no la principal
        nueva.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        
        JPanel panel = new JPanel();
        agregarElementosPanel(nueva, panel, BorderLayout.CENTER);
        reglas(panel);
        
        nueva.setVisible(true);
        
    }
	
	private void reglas(JPanel panel) {
		JLabel contenedor = new JLabel();
		
		// PAra que el texto tenga formato agregue codigo HTML, un JLabel puede interpretarlo
		String texto = "<html>\r\n"
				+ "<div style= 'width:290px'>\r\n"
				+ "	<div style='font-size:18;margin-bottom: 5px;' >\r\n"
				+ "		Introduccion\r\n"
				+ "	</div>\r\n"
				+ "	<div style = 'font-size:12;margin-bottom: 10px;'>\r\n"
				+ "		El nonograma es un tipo de rompecabezas originario de Japón, que se juega sobre una\r\n"
				+ "		cuadrícula en blanco y negro. Es un juego de ingenio con reglas simples y soluciones desafiantes. Cada casilla de la cuadrícula puede estar pintada de negro o marcada con una X\r\n"
				+ "		(celda vacía). Las pistas numéricas, ubicadas a la izquierda de cada fila y sobre cada columna,\r\n"
				+ "		indican las longitudes y el orden de las secuencias de celdas negras que deben aparecer en esa\r\n"
				+ "		fila o columna.\r\n"
				+ "	</div>\r\n"
				+ "	<div style='font-size:18; margin-bottom: 10px;'>\r\n"
				+ "		Reglas:\r\n"
				+ "	</div>\r\n"
				+ "	<div style='font-size:12;'>\r\n"
				+ "		<div style='margin-bottom: 5px;'>– Se tiene una grilla de casillas de igual largo y ancho, que deben ser pintadas de negro o marcadas con\r\n"
				+ "		una X.</div>\r\n"
				+ "		<div style='margin-bottom: 5px;'>– En las pistas, cada número indica una cadena de celdas negras consecutivas.</div> \r\n"
				+ "		<div style='margin-bottom: 5px;'>– Entre dos cadenas de celdas negras debe existir al menos una celda libre.</div>\r\n"
				+ "		<div style='margin-bottom: 5px;'>– Al costado de cada fila en la grilla, aparecen los largos de las cadenas de casillas en\r\n"
				+ "		negro para esa fila.</div>\r\n"
				+ "		<div style='margin-bottom: 5px;'>– Sobre cada columna en la grilla, aparecen los largos de las cadenas de casillas en negro\r\n"
				+ "		para esa columna.</div>\r\n"
				+ "		<div style='margin-bottom: 5px;'>– El objetivo es encontrar y marcar todas las casillas negras.</div>\r\n"
				+ "		<div style='margin-bottom: 5px;'>- El juego no puede finalizar si hay al menos una celda blanca en la grilla.</div>	\r\n"
				+ "	</div>\r\n"
				+ "<div>\r\n"
				+ "</html>";
		contenedor.setText(texto);
		agregarElementosPanel(panel, contenedor);
	}


	//provisorio
	private void crearPistas(JPanel arriba, JPanel izquierda) {
		pistasFilas = new JLabel[2][tamano];
		pistasColumnas = new JLabel [tamano][2];
		
		
		
		
		//definimos cada pista
		for (int i = 0; i < 2; i++) {
			for(int j = 0; j< tamano; j++) {
				JLabel pista = new JLabel("4");
				JLabel pista2 = new JLabel("6");
				pistasColumnas[j][i] = pista;
				pistasFilas[i][j] = pista;
                arriba.add(pista2);
                izquierda.add(pista);
			}
		}
		
	}


	private void llenarTablero(JPanel tablero) {
		//definir celdas-----------------
				botones = new JButton[tamano][tamano];
				valorBotones = new String[tamano][tamano];
				
				//Crear Botones
				for (int fila = 0; fila < tamano; fila++) {
		            for (int columna = 0; columna < tamano; columna++) {
		            	JButton boton = new JButton();
		            	definirColorDeFondo(boton, Color.white); // color inicial
		            	definirFuncionalidadCeldas(boton, fila, columna);
		            	botones[fila][columna] = boton;
		                tablero.add(boton);
		            }
				}
		
	}

	private void definirFuncionalidadCeldas(JButton boton, int fila, int columna) {
		// Agregar acción: cambiar color al hacer click
        boton.addActionListener(new ActionListener() {
            private int estado = 0;
            private Point ubicacion = new Point(fila, columna);
            

            @Override
            public void actionPerformed(ActionEvent e) {
            	estado++; // alterna el estado
                if (estado >= 3) {
                	estado = 0;
                	valorBotones[ubicacion.x][ubicacion.y] = "";
                }
                
                if (estado == 1) {
                	definirColorDeFondo(boton, Color.black);
                	valorBotones[ubicacion.x][ubicacion.y] = "NEGRO";
                	
                } else {
                	definirColorDeFondo(boton, Color.white);
                }
                if(estado == 2) {
                	mostrarX(boton);
                	valorBotones[ubicacion.x][ubicacion.y] = "X";
                } else {
                	ocultarX(boton);
                }
        	}

			

        });
	}
	private void ocultarX(JButton boton) {
		boton.setText("");
		
	}
	private void mostrarX(JButton boton) {
		boton.setText("X");
		boton.setForeground(Color.RED); //pinto el texto de rojo
		
	}
	private void modificarAnchoPanel(JPanel panel, int ancho) {
		FlowLayout fl_panel = (FlowLayout) panel.getLayout();
		fl_panel.setHgap(ancho);
	}


	private void modificarAlturaPanel(JPanel panel, int altura) {
		FlowLayout fl_Panel = (FlowLayout) panel.getLayout();
		fl_Panel.setVgap(altura);
	}


	private void definirColorDeFondo(JPanel panel, Color color) {
		panel.setBackground(color);
	}
	
	private void definirColorDeFondo(JButton boton, Color color) {
		boton.setBackground(color);
	}
	
	private void agregarElementosPanel(Container container, JPanel elemento, String layout) {
		container.add(elemento, layout);
	}
	private void agregarElementosPanel(Container container, JButton elemento, String layout) {
		container.add(elemento, layout);
	}
	private void agregarElementosPanel(Container container, JLabel elemento) {
		container.add(elemento);
	}
}
