package objekte;

import java.awt.*;

import basis.*;

public class MyCube extends MyObject {

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
		edgeLength = l;
		multiplier = factor;
		points = new MyPoint[] {
			new MyPoint(x-l/2, y-l/2, z-l/2).multiply(multiplier),
			new MyPoint(x+l/2, y-l/2, z-l/2).multiply(multiplier),
			new MyPoint(x+l/2, y+l/2, z-l/2).multiply(multiplier),
	    	new MyPoint(x-l/2, y+l/2, z-l/2).multiply(multiplier),
	    	new MyPoint(x-l/2, y-l/2, z+l/2).multiply(multiplier),
	    	new MyPoint(x+l/2, y-l/2, z+l/2).multiply(multiplier),
	    	new MyPoint(x+l/2, y+l/2, z+l/2).multiply(multiplier),
	    	new MyPoint(x-l/2, y+l/2, z+l/2).multiply(multiplier)
		};
		vecs = new MyVector[points.length];
		for (int i = 0; i < points.length; i++) vecs[i] = MyPoint.toVector(points[i]);
		points2D = points;
		lines = new MyLine[points.length*3/2];
		
		surfaces = new MySurface[] {
			//TODO vermutlich noch Anpassen
			new MySurface(this, new MyVector(1, 0, 0), new MyVector[] {new MyVector(0, .5f, .5f), new MyVector(0, -.5f, .5f), new MyVector(0, -.5f, -.5f), new MyVector(0, .5f, -.5f)}, Color.RED),
			new MySurface(this, new MyVector(-1, 0, 0), new MyVector[] {new MyVector(0, .5f, .5f), new MyVector(0, -.5f, .5f), new MyVector(0, -.5f, -.5f), new MyVector(0, .5f, -.5f)}, Color.YELLOW),
			new MySurface(this, new MyVector(0, 1, 0), new MyVector[] {new MyVector(.5f, 0, .5f), new MyVector(-.5f, 0, .5f), new MyVector(-.5f, 0, -.5f), new MyVector(.5f, 0, -.5f)}, Color.GREEN),
			new MySurface(this, new MyVector(0, -1, 0), new MyVector[] {new MyVector(.5f, 0, .5f), new MyVector(-.5f, 0, .5f), new MyVector(-.5f, 0, -.5f), new MyVector(.5f, 0, -.5f)}, Color.BLUE),
			new MySurface(this, new MyVector(0, 0, 1), new MyVector[] {new MyVector(.5f, .5f, 0), new MyVector(-.5f, .5f, 0), new MyVector(-.5f, -.5f, 0), new MyVector(.5f, -.5f, 0)}, Color.WHITE),
			new MySurface(this, new MyVector(0, 0, -1), new MyVector[] {new MyVector(.5f, .5f, 0), new MyVector(-.5f, .5f, 0), new MyVector(-.5f, -.5f, 0), new MyVector(.5f, -.5f, 0)}, Color.ORANGE),
//			new MySurface(new MyPoint[] {points2D[0], points2D[1], points2D[2], points2D[3]}, Color.RED),
//			new MySurface(new MyPoint[] {points2D[0], points2D[1], points2D[5], points2D[4]}, Color.YELLOW),
//			new MySurface(new MyPoint[] {points2D[0], points2D[3], points2D[7], points2D[4]}, Color.GREEN),
//			new MySurface(new MyPoint[] {points2D[1], points2D[2], points2D[6], points2D[5]}, Color.BLUE),
//			new MySurface(new MyPoint[] {points2D[2], points2D[3], points2D[7], points2D[6]}, Color.WHITE),
//			new MySurface(new MyPoint[] {points2D[4], points2D[5], points2D[6], points2D[7]}, Color.ORANGE)
		};
		for (MySurface s : surfaces) {
			System.out.println(s.pos.x + " " + s.pos.y + " " + s.pos.z);
		}
	}
}
