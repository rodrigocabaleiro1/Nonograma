package vfinal;

import javax.swing.SwingUtilities;

public class Main {

	public static void main(String[] args) {
		
		System.out.println("tablero");
		Nonograma nono = new Nonograma(5);
		System.out.print(nono);
		int [][] pistas = nono.pistasFilas();
		StringBuilder mensaje = new StringBuilder();
		for(int f= 0; f< pistas.length; f++) {
			mensaje.append("[ ");
			for(int c= 0; c< pistas[1].length; c++) {
				
				mensaje.append("");
				mensaje.append(pistas[f][c] + " " );
			}
			mensaje.append("]\n");
		}
		System.out.print(mensaje.toString());
		System.out.print(nono.revelarCeldaNegra());
		nono.imprimirJuegoActual();
		
		SwingUtilities.invokeLater(() -> new MenuInicial().setVisible(true));
	}

	}

