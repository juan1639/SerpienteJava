package snakePack;

import java.awt.Color;
import java.awt.Graphics;

public class SegmentoSnake {

	public int x;
	public int y;
	public int ancho;
	public int alto;

	public SegmentoSnake(int x, int y, int ancho, int alto) {

		this.x = x;
		this.y = y;
		this.ancho = ancho;
		this.alto = alto;
	}

	public void dibuja(Graphics g, int[] rgb) {

		int x = this.x * this.ancho;
		int y = this.y * this.alto;

		g.setColor(new Color(rgb[0], rgb[1], rgb[2]));
		g.fillRect(x, y, this.ancho, this.alto);
	}
}
