package v2;

import java.util.Random;

public class JuegoNonograma {
    private int size;
    private int[][] solucion;   // 1 = negro, 0 = blanco
    private int[][] usuario;    // 0=blanco, 1=negro, 2=X

    public JuegoNonograma(int size) {
        this.size = size;
        this.solucion = new int[size][size];
        this.usuario = new int[size][size];
        generarAleatorio();
    }

    public int getSize() {
        return size;
    }

    public int[][] getSolucion() {
        return solucion;
    }

    public int[][] getUsuario() {
        return usuario;
    }

    public void toggleCell(int fila, int col) {
        usuario[fila][col] = (usuario[fila][col] + 1) % 3;
    }

    public void reset() {
        usuario = new int[size][size];
    }

    public boolean evaluar() {
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                int u = (usuario[i][j] == 1) ? 1 : 0;
                if (u != solucion[i][j]) return false;
            }
        }
        return true;
    }

    private void generarAleatorio() {
        Random rand = new Random();
        for (int i = 0; i < size; i++) {
            int j = 0;
            while (j < size) {
                if (rand.nextBoolean()) {
                    int longitud = 1 + rand.nextInt(2);
                    for (int k = 0; k < longitud && j < size; k++) {
                        solucion[i][j] = 1;
                        j++;
                    }
                    if (j < size) {
                        solucion[i][j] = 0;
                        j++;
                    }
                } else {
                    solucion[i][j] = 0;
                    j++;
                }
            }
        }
    }
    
    
}
