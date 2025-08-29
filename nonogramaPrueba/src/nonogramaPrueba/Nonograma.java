package nonogramaPrueba;

import java.awt.Point;
import java.util.Random;

public class Nonograma {
	private boolean [][] tablero;
	private int [][] pistasColumnas;
	private int [][] pistasFilas;
	
	public Nonograma() {
		this.tablero = new boolean [5][5];
		this.pistasColumnas = new int [2][5];
		this.pistasFilas = new int [5][2];
		distribuirCeldas();
		generarPistas();
	}
	
	public Nonograma (int tamano) {
		this.tablero = new boolean [tamano][tamano];
		this.pistasColumnas = new int [2][tamano];
		this.pistasFilas = new int [tamano][2];
		distribuirCeldas();
		generarPistas();
	}
	
	public int [][]pistasColumnas() {
		return pistasColumnas;
	}
	
	/* retorna una matriz que contiene las pistas para todas las filas del tablero*/
	public int [][]pistasFilas() {
		return pistasFilas;
	}
	
	public Point revelarCeldaNegra() {
		Random random = new Random();
		boolean celdaEncontrada = false;
		Point punto = new Point();
		
		while(!celdaEncontrada) {
			punto.x = random.nextInt(tamano());	//fila
			punto.y = random.nextInt(tamano());	//columna
			if(tablero[punto.x][punto.y]) {
				celdaEncontrada = true;
			}
		}
		return punto;
	}
	//------------------------------------------------------------
	// METODOS PRIVADOS
	//-------------------------------------------------------------
	private int tamano() {
		return this.tablero.length;
	}
	
	private void distribuirCeldas() {
		pintarTablero();
	}
	
	private void pintarTablero() {
		for(int f= 0; f< tamano(); f++) {
			for(int c= 0; c< tamano(); c++) {
				pintarCelda(f,c);
			}
		}
	}
	
	private void generarPistas() {
		boolean filasCorrectas = false;
		boolean columnasCorrectas = false;
		while(!columnasCorrectas || !filasCorrectas) {
			filasCorrectas = analizarFilas();
			columnasCorrectas = analizarColumnas();
		if (!filasCorrectas || !columnasCorrectas) {
			pintarTablero();
			reiniciarPistas();
		}
		
		}
	}
	
	private void reiniciarPistas() {
		pistasColumnas = new int [2][tamano()];
		pistasFilas = new int [tamano()][2];	
	}

	private boolean analizarColumnas() {
		boolean todoCorrecto = true;
		for(int c= 0; c< tamano(); c++) {
			todoCorrecto &= columnaCorrecta(c);
		}
		return todoCorrecto;
	}
	private boolean columnaCorrecta(int columna) {
		int cantidadPistas = 0; //cantidad de pistas en la columna actual
		boolean columnaCorrecta = true;

		int secuencia = 0; //cantidad de celdas negras seguidas
		for(int f= 0; f< tamano(); f++) {
			if(cantidadPistas >= 2) {
				columnaCorrecta = false;
			}
			else {
				if(tablero[f][columna]) {
					secuencia ++;
					if(secuenciaInvalida(secuencia)) {
						columnaCorrecta = false;
					}
					
				}if(finSecuencia(columna, secuencia, f) || columnaFinalizaEnCeldaNegra(columna, f)) {
					pistasColumnas[cantidadPistas][columna] = secuencia;
					cantidadPistas ++;
					secuencia = 0;
				}
			}
		}
		if (cantidadPistas == 0) {
			columnaCorrecta = false;
		}
		return columnaCorrecta;
	}

	private boolean secuenciaInvalida(int secuencia) {
		return secuencia >= tamano();
	}

	private boolean columnaFinalizaEnCeldaNegra(int columna, int fila) {
		return tablero[fila][columna] && fila== tamano()-1;
	}
	private boolean filaFinalizaEnCeldaNegra(int columna, int fila) {
		return tablero[fila][columna] && columna == tamano()-1;
	}

	private boolean finSecuencia(int columna, int secuencia, int fila) {
		return secuencia > 0 && !tablero[fila][columna];
	}

	private boolean analizarFilas() {
		boolean todoCorrecto = true;
		for(int f= 0; f< tamano(); f++) {
			todoCorrecto &= filaCorrecta(f);
		}
		return todoCorrecto;
	}

	private boolean filaCorrecta(int fila) {
		int cantidadPistas = 0; //cantidad de pistas en la columna actual
		boolean filaCorrecta = true;

		int secuencia = 0; //cantidad de celdas negras seguidas
		for(int c= 0; c< tamano(); c++) {
			if(cantidadPistas >= 2) {
				filaCorrecta = false;
			}
			else {
				if(tablero[fila][c]) {
					secuencia ++;
					if(secuenciaInvalida(secuencia)) {
						filaCorrecta = false;
					}
				}if(finSecuencia(c, secuencia, fila) || filaFinalizaEnCeldaNegra(c, fila)) {
					pistasFilas[fila][cantidadPistas] = secuencia;
					cantidadPistas ++;
					secuencia = 0;
				}
			}
		}
		if (cantidadPistas == 0) {
			filaCorrecta = false;
		}
		return filaCorrecta;
	}

	private boolean valorAleatorio() {
		Random random = new Random();
		return random.nextBoolean();
	}
	
	private void pintarCelda(int fila, int columna){
		this.tablero[fila][columna] = valorAleatorio();
	}	
	

	@Override
	public String toString() {
		StringBuilder mensaje = new StringBuilder();
		for(int f= 0; f< tamano(); f++) {
			mensaje.append("[ ");
			for(int c= 0; c< tamano(); c++) {
				
				mensaje.append("");
				if(tablero[f][c]) {
					mensaje.append("O ");}
				else {
					mensaje.append("X ");}
			}
			mensaje.append("]\n");
		}
		return mensaje.toString();
	}
	
}
