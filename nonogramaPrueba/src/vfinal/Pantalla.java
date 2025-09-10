package vfinal;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public abstract class Pantalla extends JFrame{
	
	public Pantalla(String titulo, int xPosicion, int yPosicion, int anchoVentana, int altoVentana) {
		setTitle(titulo);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setBounds(xPosicion, yPosicion, anchoVentana, altoVentana);
	}
	
	protected void modificarAnchoPanel(JPanel panel, int ancho) {
		FlowLayout fl_panel = (FlowLayout) panel.getLayout();
		fl_panel.setHgap(ancho);
	}


	protected void modificarAlturaPanel(JPanel panel, int altura) {
		FlowLayout fl_Panel = (FlowLayout) panel.getLayout();
		fl_Panel.setVgap(altura);
	}


	protected void definirColorDeFondo(JPanel panel, Color color) {
		panel.setBackground(color);
	}
	
	protected void definirColorDeFondo(JButton boton, Color color) {
		boton.setBackground(color);
	}
	
	protected void agregarElementosPanel(Container container, JPanel elemento, String layout) {
		container.add(elemento, layout);
	}
	protected void agregarElementosPanel(Container container, JLabel elemento, String layout) {
		container.add(elemento, layout);
	}
	protected void agregarElementosPanel(Container container, JButton elemento, String layout) {
		container.add(elemento, layout);
	}
	protected void agregarElementosPanel(Container container, JLabel elemento) {
		container.add(elemento);
	}
	protected void agregarElementosPanel(Container container, JButton elemento) {
		container.add(elemento);
	}
}
