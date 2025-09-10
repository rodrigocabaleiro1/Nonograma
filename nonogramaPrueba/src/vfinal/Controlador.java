package vfinal;

import java.awt.Point;

import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;

public class Controlador {
    private Nonograma juego;
    private VistaNonograma vista;

    public Controlador() {
        mostrarMenuInicio();
    }


    public void celdaClick(int fila, int col, String valor) {
        juego.actualizarCelda(fila, col, valor);
        vista.actualizarCelda(fila, col, valor);
    }

    public void reset() {
        juego.reset();
        vista.actualizarGrilla(new String [juego.tamano()][juego.tamano()]);
    }

    public void evaluar() {
    	if(juego.evaluar() == null) {
    		JOptionPane.showMessageDialog(vista, "¡ATENCION! Todas las celdas deben estar pintadas de Negro o marcardas con una X");
    	}
    	else if (juego.evaluar()) {
            JOptionPane.showMessageDialog(vista, "¡Ganaste!");
        } else {
            JOptionPane.showMessageDialog(vista, "Fallaste. Intenta de nuevo.");
        }
    }

    public void mostrarSolucion() {
        vista.mostrarSolucion(juego.obtenerSolucion());
    }
    public void iniciarJuego(int tamano) {
    	this.juego = new Nonograma(tamano);
        this.vista = new VistaNonograma(tamano, juego.pistasFilas(), juego.pistasColumnas());
    	vista.setControlador(this);
        vista.setVisible(true);
    }
    
    public void revelarCeldaNegra() {
    	Point coordenadas = juego.revelarCeldaNegra();
    	vista.actualizarCelda(coordenadas.x, coordenadas.y, "NEGRO");
    }
    public void mostrarMenuInicio() {
    	SwingUtilities.invokeLater(() -> new MenuInicial(this).setVisible(true));
    }
}