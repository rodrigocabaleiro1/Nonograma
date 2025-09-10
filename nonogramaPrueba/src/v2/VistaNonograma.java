package v2;

//import controlador.Controlador;
//import modelo.JuegoNonograma;

import javax.swing.*;
import java.awt.*;

public class VistaNonograma extends JFrame {
    private JButton[][] botones;
    private JButton btnEvaluar, btnReset, btnSolucion, ayuda;
    private JLabel[] pistasFilas;
    private JLabel[] pistasColumnas;
    private Controlador controlador;
    private JuegoNonograma juego;

    public VistaNonograma(JuegoNonograma juego) {
        this.juego = juego;
        int size = juego.getSize();

        setTitle("Nonograma " + size + "x" + size);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        //setLocationRelativeTo(null);
        setLayout(new BorderLayout());
        setBounds(100, 50, 1024, 768);

        // Título
        JLabel titulo = new JLabel("Nonograma " + size + "x" + size, SwingConstants.CENTER);
        titulo.setFont(new Font("Arial", Font.BOLD, 20));
        add(titulo, BorderLayout.NORTH);

        // Panel central
        JPanel centro = new JPanel(new BorderLayout());
        add(centro, BorderLayout.CENTER);

        // Pistas columnas
        JPanel panelPistasCol = new JPanel(new GridLayout(1, size));
        pistasColumnas = new JLabel[size];
        for (int j = 0; j < size; j++) {
            pistasColumnas[j] = new JLabel("", SwingConstants.CENTER);
            panelPistasCol.add(pistasColumnas[j]);
        }
        centro.add(panelPistasCol, BorderLayout.NORTH);

        // Pistas filas
        JPanel panelPistasFil = new JPanel(new GridLayout(size, 1));
        pistasFilas = new JLabel[size];
        for (int i = 0; i < size; i++) {
            pistasFilas[i] = new JLabel("", SwingConstants.RIGHT);
            panelPistasFil.add(pistasFilas[i]);
        }
        centro.add(panelPistasFil, BorderLayout.WEST);

        // Grilla de botones
        JPanel grid = new JPanel(new GridLayout(size, size));
        botones = new JButton[size][size];
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                botones[i][j] = new JButton();
                botones[i][j].setBackground(Color.WHITE);
                int fila = i, col = j;
                botones[i][j].addActionListener(e -> {
                    if (controlador != null) controlador.celdaClick(fila, col);
                });
                grid.add(botones[i][j]);
            }
        }
        centro.add(grid, BorderLayout.CENTER);

        // Panel de botones abajo
        JPanel abajo = new JPanel();
        btnEvaluar = new JButton("Evaluar");
        btnReset = new JButton("Reset");
        btnSolucion = new JButton("Mostrar Solución");
        abajo.add(btnEvaluar);
        abajo.add(btnReset);
        abajo.add(btnSolucion);
        add(abajo, BorderLayout.SOUTH);

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

    // Setear controlador
    public void setControlador(Controlador c) {
        this.controlador = c;
    }

    // Actualizar celda
    public void actualizarCelda(int fila, int col, int estado) {
        JButton b = botones[fila][col];
        switch (estado) {
            case 0:
                b.setBackground(Color.WHITE);
                b.setText("");
                break;
            case 1:
                b.setBackground(Color.BLACK);
                b.setText("");
                break;
            case 2:
                b.setBackground(Color.WHITE);
                b.setText("X");
                b.setForeground(Color.RED);
                break;
        }
    }

    // Actualizar toda la grilla
    public void actualizarGrilla(int[][] usuario) {
        for (int i = 0; i < juego.getSize(); i++) {
            for (int j = 0; j < juego.getSize(); j++) {
                actualizarCelda(i, j, usuario[i][j]);
            }
        }
    }

    // Mostrar solución completa
    public void mostrarSolucion(int[][] solucion) {
        for (int i = 0; i < juego.getSize(); i++) {
            for (int j = 0; j < juego.getSize(); j++) {
                if (solucion[i][j] == 1) {
                    botones[i][j].setBackground(Color.BLACK);
                    botones[i][j].setText("");
                } else {
                    botones[i][j].setBackground(Color.WHITE);
                    botones[i][j].setText("");
                }
            }
        }
    }

    // Setear pistas filas
    public void setPistasFilas(String[] pistas) {
        for (int i = 0; i < pistas.length; i++) {
            pistasFilas[i].setText(pistas[i]);
        }
    }

    // Setear pistas columnas
    public void setPistasColumnas(String[] pistas) {
        for (int j = 0; j < pistas.length; j++) {
            pistasColumnas[j].setText(pistas[j]);
        }
    }
}
