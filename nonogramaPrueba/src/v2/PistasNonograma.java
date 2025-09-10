package v2;

import java.util.ArrayList;

public class PistasNonograma {

    public static String[] calcularFilas(int[][] solucion) {
        String[] pistas = new String[solucion.length];
        for (int i = 0; i < solucion.length; i++) {
            pistas[i] = calcularLinea(solucion[i]);
        }
        return pistas;
    }

    public static String[] calcularColumnas(int[][] solucion) {
        String[] pistas = new String[solucion.length];
        for (int j = 0; j < solucion.length; j++) {
            int[] columna = new int[solucion.length];
            for (int i = 0; i < solucion.length; i++) {
                columna[i] = solucion[i][j];
            }
            pistas[j] = calcularLinea(columna);
        }
        return pistas;
    }

    private static String calcularLinea(int[] linea) {
        ArrayList<Integer> bloques = new ArrayList<>();
        int contador = 0;

        for (int celda : linea) {
            if (celda == 1) {
                contador++;
            } else {
                if (contador > 0) {
                    bloques.add(contador);
                    contador = 0;
                }
            }
        }
        if (contador > 0) bloques.add(contador);

        if (bloques.isEmpty()) return "0";

        StringBuilder sb = new StringBuilder();
        for (int b : bloques) sb.append(b).append(" ");
        return sb.toString().trim();
    }
}
