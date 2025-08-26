package nonogramaPrueba;

import java.util.Random;

public class Nonograma {
	private boolean [][] tablero;
	private int [] celdasNegrasPorFila;
	private int [] celdasNegrasPorColumna;
	
	public Nonograma() {
		this.tablero = new boolean [5][5];
		this.celdasNegrasPorColumna = new int [5];
		this.celdasNegrasPorFila = new int [5];
		distribuirCeldas();
	}
	
	private int tamano() {
		return this.tablero.length;
	}
	
	private void distribuirCeldas() {
		pintarTableroCompleto();
		generarFilas();
	}
	
	private void pintarTableroCompleto() { //pinta el tablero de celdas negras
		for(int f= 0; f< tamano(); f++) {
			for(int c= 0; c< tamano(); c++) {
				pintarCelda(f,c);
			}
		}
	}
	
	private void generarFilas() {
		
		int celdasNegrasFila;
		int celdasVaciasFila;
		int primeraSecuencia; //cantidad de celdas vacias que tendra la primera secuencia en la fila
		int columnaInicialVacias;
		
		for(int f= 0; f< tamano(); f++) {
			celdasNegrasFila = numeroAleatorio(3) + 2; 	// generamos un numero entre 0 y 2 y le sumamos 2
        													// para que respete el rango de celdas negras maximo
			celdasVaciasFila = tamano() - celdasNegrasFila;
			
			columnaInicialVacias = numeroAleatorio(tamano());
			if (celdasVaciasFila == 1) {
				
				despintarCelda(f, columnaInicialVacias);
			} else {
				int columnaActual = columnaInicialVacias;
				primeraSecuencia = celdasVaciasFila - numeroAleatorio(celdasVaciasFila-1);
				celdasVaciasFila -= primeraSecuencia; //aqui guardamos la extencionde la segunda secuencia
				//solo deben haber dos secuencias
				
				boolean incrementoColumna = definirSentido(columnaActual + 1, primeraSecuencia);
					//+1 es para que el valor de la columnaActual este entre 1 y 5
					//definimos sentido en el que recorremos por columnas
				
				//primera secuencia
				for(int i = primeraSecuencia; i>0; i--) {
					despintarCelda(f, columnaActual);
					columnaActual = desplazarseColumna(incrementoColumna, columnaActual);
				}
				//segunda secuencia
				columnaActual = numeroAleatorio(tamano());
				incrementoColumna = definirSentido(columnaActual + 1, primeraSecuencia);
				for(int i = celdasVaciasFila; i>0; i--) {
					despintarCelda(f, columnaActual);
					columnaActual = desplazarseColumna(incrementoColumna, columnaActual);
				}
			
        
			}
		}
	}
	
	

	private boolean definirSentido(int columnaActual, int secuencia) { //True = --> // False = <---
		if((tamano() - columnaActual) < secuencia) { 
			return false; //
		}
		else {
			return true;
		}
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
	
	private int numeroAleatorio(int numero) {
		Random random = new Random();
		return random.nextInt(numero);
	}
	
	private void pintarCelda(int fila, int columna){
		this.tablero[fila][columna] = true;
	}
	
	private void despintarCelda(int fila, int columna){ //Equivalente a que haya una X en el tablero
		this.tablero[fila][columna] = false;
	}
	private int desplazarseColumna(boolean incremento, int columnaActual) {
		if(incremento) {
			columnaActual++;
		}else {
			columnaActual--;
		}
		return columnaActual;
	}
}
