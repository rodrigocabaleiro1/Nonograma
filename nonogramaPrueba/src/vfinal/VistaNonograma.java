package vfinal;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class VistaNonograma extends Pantalla {
    private JButton[][] botones;
    private String[][] valorBotones;
    private JButton btnEvaluar, btnReset, btnSolucion, ayuda, revelarCelda, volverAlMenu;
    private JLabel[][] pistasFilas;
    private JLabel[][] pistasColumnas;
    private Controlador controlador;
    private int tamano;
    private int usosRevelarCelda;

    public VistaNonograma(int tamano, int [][] pistasFilas, int [][]pistasColumnas) {
    	super("Nonograma " + tamano + "x" + tamano, 100, 50, 1024, 768);
    	usosRevelarCelda = 0;
    	this.tamano = tamano;
        setLayout(new BorderLayout());

        //Crear Paneles y el titulo
        JLabel titulo = new JLabel("Nonograma " + tamano + "x" + tamano, SwingConstants.CENTER);
        JPanel centro = new JPanel(new BorderLayout());
        JPanel panelPistasColumnas = new JPanel(new GridLayout(tamano/2, tamano));
        JPanel panelPistasFilas = new JPanel(new GridLayout(tamano, tamano/2));
        JPanel tablero = new JPanel(new GridLayout(tamano, tamano));
        JPanel abajo = new JPanel();
        
        
        establecerEstilos(titulo, panelPistasColumnas);
        organizarPaneles(titulo, centro, panelPistasColumnas, panelPistasFilas, tablero, abajo);
        generarPistas(panelPistasColumnas, panelPistasFilas, pistasColumnas, pistasFilas);
        llenarTablero(tablero);
        organizarBotonesAbajo(abajo);
    }
    
  //---------------------------------------------------------------------
    //Organizar Elementos
  //---------------------------------------------------------------------

    private void organizarPaneles(JLabel titulo, JPanel centro, JPanel panelPistasColumnas, JPanel panelPistasFilas,
			JPanel tablero, JPanel abajo) {
		agregarElementosPanel(getContentPane(), titulo, BorderLayout.NORTH);
        agregarElementosPanel(getContentPane(), centro, BorderLayout.CENTER);
        agregarElementosPanel(centro, panelPistasColumnas, BorderLayout.NORTH);
        agregarElementosPanel(centro, panelPistasFilas, BorderLayout.WEST);
        agregarElementosPanel(getContentPane(), abajo, BorderLayout.SOUTH);
        agregarElementosPanel(centro, tablero, BorderLayout.CENTER);
	}
    
    private void establecerEstilos(JLabel titulo, JPanel panelPistasColumnas) {
		titulo.setFont(new Font("Arial", Font.BOLD, 20));
		establecerBorde(panelPistasColumnas, 0, 10*(tamano/2), 0, 0);//10px de margen izquierdo por cantidad de secuencias maximas de celdas
	}
    
	private void organizarBotonesAbajo(JPanel abajo) {
		// Panel de botones abajo
        btnEvaluar = new JButton("Evaluar");
        btnReset = new JButton("Reset");
        btnSolucion = new JButton("Mostrar Solución");
        
        agregarElementosPanel(abajo, btnEvaluar);
        agregarElementosPanel(abajo, btnReset);
        agregarElementosPanel(abajo, btnSolucion);
        ayuda(abajo);
        revelarCelda(abajo);
        volverAlMenu(abajo);
        eventosEvaluarResetSolucion();
	}
private void eventosEvaluarResetSolucion() {
	// Eventos botones
	btnEvaluar.addActionListener(e -> {
	    if (controlador != null) controlador.evaluar();
	});
	btnReset.addActionListener(e -> {
	    if (controlador != null) controlador.reset();
	});
	btnSolucion.addActionListener(e -> {
	    if (controlador != null) controlador.mostrarSolucion();
	});
}

    
    public void setControlador(Controlador c) {
        this.controlador = c;
    }

    //-------------------------------------------------------
    //	Manejo de celdas en pantalla
    //-------------------------------------------------------
    
    //Actualizar celda
    public void actualizarCelda(int fila, int col, String estado) {
        JButton boton = botones[fila][col];
        if (estado == null) {
        	valorBotones[fila][col] = "";
        	vaciarBoton(boton);
        }else if (estado.toUpperCase() == "NEGRO") {
        	valorBotones[fila][col] = "NEGRO";
        	marcarDeNegro(boton);
        	
        } else if (estado.toUpperCase() == "X"){
        	valorBotones[fila][col] = "X";
        	mostrarX(boton);
        }	else {
        	valorBotones[fila][col] = "";
        	vaciarBoton(boton);
        }
    }

    //Actualizar toda la grilla
    public void actualizarGrilla(String[][] usuario) {
        for (int i = 0; i < tamano; i++) {
            for (int j = 0; j < tamano; j++) {
                actualizarCelda(i, j, usuario[i][j]);
            }
        }
    }

    
    // Mostrar solución completa
    public void mostrarSolucion(boolean[][] solucion) {
        for (int i = 0; i < tamano; i++) {
            for (int j = 0; j < tamano; j++) {
                if (solucion[i][j]) {
                	definirColorDeFondo(botones[i][j], Color.BLACK);
                	quitarTexto(botones[i][j]);
                    
                } else {
                	definirColorDeFondo(botones[i][j], Color.WHITE);
                	mostrarX(botones[i][j]);
                }
            }
        }
    }


    //-------------------------------------------------------
    //	elementos relacionados a la generacion del tablero de juego
    //-------------------------------------------------------  
    private void generarPistas(JPanel arriba, JPanel izquierda, int [][] pistasArriba, int [][]pistasIzquierda) {
		pistasFilas = new JLabel[tamano/2][tamano];
		pistasColumnas = new JLabel [tamano][tamano/2];
		llenarPistasColumnas(arriba, pistasArriba);
		llenarPistasFilas(izquierda, pistasIzquierda);	
	}
    
    
    private void llenarPistasColumnas(JPanel arriba, int [][] pistasArriba) {
    	for (int i = 0; i < tamano/2; i++) {
			for(int j = 0; j< tamano; j++) {
				pistasColumnas[j][i] = new JLabel(analizarPista (pistasArriba[i][j]));
				agregarElementosPanel(arriba,pistasColumnas[j][i]);         
			}
		}
    }
    
    private void llenarPistasFilas(JPanel izquierda, int [][] pistasIzquierda) {
    	for (int i = 0; i < tamano; i++) {
			for(int j = 0; j< tamano/2; j++) {
				pistasFilas[j][i] = new JLabel(" " + analizarPista(pistasIzquierda[i][j]));	
				agregarElementosPanel(izquierda, pistasFilas[j][i]);          
			}
		}
    }
    
    private String analizarPista(int pista) {// si la pista es cero devuelve un caracter vacio
    	if (pista == 0) {
    		return " ";
    	}
    	return ""+pista;
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
		            	agregarElementosPanel(tablero, boton);
		            }
				}
	}
    //---------------------------------------------------------------
    //	Funcionalidad Botones
    //---------------------------------------------------------------
    
    private void definirFuncionalidadCeldas(JButton boton, int fila, int columna) {
		// Agregar acción: cambiar color al hacer click
        boton.addActionListener(new ActionListener() {
            private int estado = 0;
            private Point ubicacion = new Point(fila, columna);
            

            @Override
            public void actionPerformed(ActionEvent e) {
            	estado++; // alterna el estado
                if (estado >= 3 || estado == 0) {
                	estado = 0;
                	controlador.celdaClick(ubicacion.x, ubicacion.y, "");
                }
                
                else if (estado == 1) {
                	controlador.celdaClick(ubicacion.x, ubicacion.y, "NEGRO");
                }	
                else if(estado == 2) {
                	controlador.celdaClick(ubicacion.x, ubicacion.y, "X");
                }
            }
        });
    }
    
    private void ayuda(JPanel contenedor) {
		ayuda = new JButton(" ? ");
		agregarElementosPanel(contenedor, ayuda);
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
		String texto = textoAyuda();
		contenedor.setText(texto);
		agregarElementosPanel(panel, contenedor);
	}
	
	private void volverAlMenu(JPanel contenedor) {
		volverAlMenu = new JButton("Menu Principal");
		agregarElementosPanel(contenedor, volverAlMenu);
		volverAlMenu.addActionListener(e -> abrirMenu());	
	}
	private void abrirMenu() {
		controlador.mostrarMenuInicio();
		dispose(); // cerrar ventana
	}
	private void revelarCelda(JPanel contenedor) {
		revelarCelda = new JButton("Pista");
		agregarElementosPanel(contenedor, revelarCelda);
		revelarCelda.addActionListener(e -> mostrarCeldaCorrecta());	
	}
    
    private void mostrarCeldaCorrecta() {
		if (usosRevelarCelda == 0) {
			controlador.revelarCeldaNegra();
		}
    	usosRevelarCelda++;
	}


    //-------------------------------------------------------
    //	Control estetica Botones
    //-------------------------------------------------------
	private void quitarTexto(JButton boton) {
		boton.setText("");
		
	}
	private void mostrarX(JButton boton) {
		boton.setText("X");
		boton.setForeground(Color.RED); //pinto el texto de rojo
		definirColorDeFondo(boton, Color.WHITE);
	}
	private void marcarDeNegro(JButton boton) {
		definirColorDeFondo(boton, Color.BLACK);
		quitarTexto(boton);
	}
	private void vaciarBoton(JButton boton) {
		quitarTexto(boton);
		definirColorDeFondo(boton, Color.WHITE);
	}
	
	private String textoAyuda() {
		return "<html>\r\n"
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
	}
}