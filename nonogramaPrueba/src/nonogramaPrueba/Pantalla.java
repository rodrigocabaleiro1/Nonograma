package nonogramaPrueba;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;

import javax.swing.BoxLayout;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import java.awt.BorderLayout;
import java.awt.CardLayout;

import javax.swing.JTable;
import java.awt.Color;
import java.awt.Container;

import javax.swing.JInternalFrame;
import javax.swing.JLabel;

public class Pantalla extends JFrame {

	private static final long serialVersionUID = 1L;
	private int tamano;
	JButton[][] botones;
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
		setBounds(100, 100, 480, 480);
		getContentPane().setLayout(new BorderLayout(0, 0));
		setTitle("Trabajo Practico N°1 : NONOGRAMA");
		
		getContentPane().setLayout(new BorderLayout());;
		//Usa CarLayout para cambiar entre pantallas
		panelPrincipal = new JPanel();
		
		panelPrincipal.setLayout(new BorderLayout(0, 0));
		menuPrincipal();
		
		//panelPrincipal.add(menuPrincipal, "menu");
		//panelPrincipal.add(juego, "juego");
		//juego(panelPrincipal);
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
        definirFuncionalidadJugar(jugar, 5, panelPrincipal);
        
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
		
		modificarAlturaPanel(header, 20);
		modificarAlturaPanel(foother, 15);
		modificarAnchoPanel(margenIzquierdo, 10);
		modificarAnchoPanel(margenDerecho, 10);
		modificarAlturaPanel(pistasArriba, 16);
		modificarAnchoPanel(pistasIzquierda, 16);
		modificarAnchoPanel(margenIzquierdoPistasArriba, 16);
		
		body.setLayout(new BorderLayout(0, 0)); // Establecer BorderLayout al body
		contenedorPistasArriba.setLayout(new BorderLayout(0, 0)); //Establecer BorderLayout para organizar pistas arriba		
		tablero.setLayout(new GridLayout(tamano, tamano, 0, 0));	// Definir layout del tablero como una grilla	
		pistasArriba.setLayout(new GridLayout(2, tamano, 0, 16));
		pistasIzquierda.setLayout(new GridLayout(tamano, 2, 16, 0));		
		
	
		
		
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
		
		llenarTablero(tablero);
		crearPistas(pistasArriba, pistasIzquierda);
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
				
				//Crear Botones
				for (int fila = 0; fila < tamano; fila++) {
		            for (int columna = 0; columna < tamano; columna++) {
		            	JButton boton = new JButton();
		            	definirColorDeFondo(boton, Color.white); // color inicial
		            	definirFuncionalidadCeldas(boton);
		            	botones[fila][columna] = boton;
		                tablero.add(boton);
		            }
				}
		
	}

	private void definirFuncionalidadCeldas(JButton boton) {
		// Agregar acción: cambiar color al hacer click
        boton.addActionListener(new ActionListener() {
            private int estado = 0;
            

            @Override
            public void actionPerformed(ActionEvent e) {
            	estado++; // alterna el estado
                if (estado >= 3) {
                	estado = 0;
                }
                
                if (estado == 1) {
                	definirColorDeFondo(boton, Color.black);
                } else {
                	definirColorDeFondo(boton, Color.white);
                }
                if(estado == 2) {
                	mostrarX(boton);
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
}
