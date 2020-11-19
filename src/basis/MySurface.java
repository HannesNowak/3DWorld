package basis;

import java.awt.*;

import objekte.*;
import visualisierung.*;
import visualisierung.Window;

public class MySurface implements Comparable<MySurface> {
	
	public MyPoint pos, objPos; // pos = Mitte vom Surface, objPos = Position vom Objekt
	public MyPoint[] points;
	public MyVector vec; // vec = Vektor von objPos zu pos
	public MyVector[] vecs;
	public MyObject obj; //TODO nötig?
	public float scale, objLength;
	public int multiplier;
	public boolean drawSurface = true, drawCorners = false, drawEdges = false;
	public Color surfaceC, cornerC, edgeC;

	public MySurface(MyObject obj, MyVector vec, MyVector[] vecs, float scale, boolean drawSurface, Color surfaceC, boolean drawCorners, Color cornerC, boolean drawEdges, Color edgeC) {
		this.objPos = obj.pos;
		this.multiplier = obj.multiplier;
		this.objLength = obj.edgeLength;
		this.vec = vec;
		this.vecs = vecs;
		this.scale = scale;
		this.drawSurface = drawSurface;
		this.surfaceC = surfaceC;
		this.drawCorners = drawCorners;
		this.cornerC = cornerC;
		this.drawEdges = drawEdges;
		this.edgeC = edgeC;
		pos = objPos.add((vec.multiply(multiplier*objLength)));
		points = new MyPoint[vecs.length];
		for (int i = 0; i < points.length; i++) {
			points[i] = pos.add(vecs[i].multiply(multiplier*objLength));
		}
	}
	
	public MySurface(MyObject obj, MyVector vec, MyVector[] vecs, float scale) {
		this(obj, vec, vecs, scale, Color.WHITE);
	}
	
	public MySurface(MyObject obj, MyVector vec, MyVector[] vecs, Color surfaceC) {
		this(obj, vec, vecs, 1, surfaceC);
	}
	
	public MySurface(MyObject obj, MyVector vec, MyVector[] vecs, float scale, Color surfaceC) {
		this(obj, vec, vecs, 1, true, surfaceC, false, Color.BLUE, false, Color.RED);
	}
	public MySurface(MyObject obj, MyVector vec, MyVector[] vecs) {
		this(obj, vec, vecs, 1, true, Color.WHITE, false, Color.BLUE, false, Color.RED);
	}
	
	public void update() {
		pos = objPos.add((vec.multiply(scale)));
	}

	@Override
	public int compareTo(MySurface s) {
		float compare = ((MySurface) s).pos.z;
		return (int) (this.pos.z - compare);
	}

	public void render(Graphics2D g2d, MyCamera cam) {
		int[] xPoints = new int[points.length], yPoints = new int[points.length];
		for (int i = 0; i < points.length; i++) {
			//TODO Cam Offset
			xPoints[i] = (int) points[i].x - (int) cam.pos.x;
			yPoints[i] = (int) points[i].y + (int) cam.pos.y;
			System.out.println("Render: i: " + i + " " + xPoints[i] + " " + yPoints[i]);
		}
		g2d.setColor(surfaceC);
		g2d.fillPolygon(xPoints, yPoints, points.length);
	}
}
