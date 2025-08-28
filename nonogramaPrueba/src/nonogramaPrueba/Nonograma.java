package nonogramaPrueba;

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
		if (!filasCorrectas || columnasCorrectas) {
			pintarTablero();
		}
		}
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
				}else if(secuencia > 0 && !tablero[f][columna]) {
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
				}else if(secuencia > 0 && !tablero[fila][c]) {
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
