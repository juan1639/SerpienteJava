package snakePack;

import java.awt.Color;
import java.awt.Graphics;

public class Manzana {

	private int x;
	private int y;
	public int columnas;
	public int filas;
	public int ancho;
	public int alto;

	public Manzana(int columnas, int filas, int ancho, int alto) {

		this.columnas = columnas;
		this.filas = filas;

		this.ancho = ancho;
		this.alto = alto;

		crea_nueva_manzana(columnas, filas);
	}

	public void crea_nueva_manzana(int columnas, int filas) {
		
		this.x = (int) (Math.random() * columnas);
		this.y = (int) (Math.random() * (filas - 1));
		this.y += 1;
	}

	public void dibuja(Graphics g, int[] rgb) {
		
		int x = this.x * this.ancho;
		int y = this.y * this.alto;

		g.setColor(new Color(rgb[0], rgb[1], rgb[2]));
		//g.fillRect(x, y, this.ancho, this.alto);
		g.fillOval(x, y, this.ancho, this.alto);
	}

	// Getters & Setters
	public int getX() {
		return this.x;
	}

	public int getY() {
		return this.y;
	}
}
