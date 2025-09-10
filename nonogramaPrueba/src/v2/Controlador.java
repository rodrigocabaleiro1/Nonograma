package v2;

//import modelo.JuegoNonograma;
//import modelo.PistasNonograma;
//import vista.VistaNonograma;

import javax.swing.*;

public class Controlador {
    private JuegoNonograma juego;
    private VistaNonograma vista;

    public Controlador(JuegoNonograma juego, VistaNonograma vista) {
        this.juego = juego;
        this.vista = vista;
        actualizarVista();
    }

    private void actualizarVista() {
        vista.actualizarGrilla(juego.getUsuario());
        vista.setPistasFilas(PistasNonograma.calcularFilas(juego.getSolucion()));
        vista.setPistasColumnas(PistasNonograma.calcularColumnas(juego.getSolucion()));
    }

    public void celdaClick(int fila, int col) {
        juego.toggleCell(fila, col);
        actualizarVista();
    }

    public void reset() {
        juego.reset();
        actualizarVista();
    }

    public void evaluar() {
        if (juego.evaluar()) {
            JOptionPane.showMessageDialog(vista, "¡Ganaste!");
        } else {
            JOptionPane.showMessageDialog(vista, "Fallaste. Intenta de nuevo.");
        }
    }

    public void mostrarSolucion() {
        vista.mostrarSolucion(juego.getSolucion());
    }
}
