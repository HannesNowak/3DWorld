package objekte;

import java.awt.*;
import java.util.Arrays;

import berechnungen.MyCamera;

public class MyCube extends MyObject implements Comparable<MyCube> {

	/**
	 * @param x xPosition des Mittelpunktes
	 * @param y yPosition des Mittelpunktes
	 * @param z zPosition des Mittelpunktes
	 * @param l Kantenlänge des Würfels
	 */
	public MyCube(float x, float y, float z, float l) {
		this(x, y, z, l, 1);
	}

	/**
	 * @param x xPosition des Mittelpunktes
	 * @param y yPosition des Mittelpunktes
	 * @param z zPosition des Mittelpunktes
	 * @param l Kantenlänge des Würfels
	 * @param factor Koordinaten Multiplikator
	 */
	public MyCube(float x, float y, float z, float l, int factor) {
		this(x, y, z, 0, 0, 0, l, factor);
	}
	
	/**
	 * @param x xPosition des Mittelpunktes
	 * @param y yPosition des Mittelpunktes
	 * @param z zPosition des Mittelpunktes
	 * @param angleX Vector xRichtung
	 * @param angleY Vector yRichtung
	 * @param angleZ Vector zRichtung
	 * @param l Kantenlänge des Würfels
	 * @param factor Koordinaten Multiplikator
	 */
	public MyCube(float x, float y, float z, float angleX, float angleY, float angleZ, float l, int factor) {
		super(x, y, z, angleX, angleY, angleZ);
		points = new MyVector[] {
			new MyVector(x-l/2, y-l/2, z-l/2).multiply(factor),
			new MyVector(x+l/2, y-l/2, z-l/2).multiply(factor),
			new MyVector(x+l/2, y+l/2, z-l/2).multiply(factor),
	    	new MyVector(x-l/2, y+l/2, z-l/2).multiply(factor),
	    	new MyVector(x-l/2, y-l/2, z+l/2).multiply(factor),
	    	new MyVector(x+l/2, y-l/2, z+l/2).multiply(factor),
	    	new MyVector(x+l/2, y+l/2, z+l/2).multiply(factor),
	    	new MyVector(x-l/2, y+l/2, z+l/2).multiply(factor)
		};
		points2D = new MyVector[points.length];
		lines = new MyLine[points.length*3/2];
		surfaces = new MySurface[lines.length/2];
	}

	public void render(Graphics2D g2d, MyCamera cam) {
		g2d.setColor(Color.GRAY);
		
		// Punkte malen
		for (MyVector v : points2D) {
			v.render(g2d, cam);
		}
		
		// Kanten malen
		for (MyLine l : lines) {
			l.render(g2d, cam);
		}
		
		// Flächen malen
		Arrays.sort(surfaces);
		for (MySurface s : surfaces) {
			g2d.setColor(s.c);
			s.render(g2d, cam);
		}
	}

	@Override
	public int compareTo(MyCube c) {
		float compare = ((MyCube) c).z;
		return (int) (this.z - compare);
	}
}
