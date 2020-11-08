package objekte;

import java.awt.Color;
import java.awt.Graphics2D;

import berechnungen.MyCamera;

public class MySurface implements Comparable<MySurface> {
	public MyVector[] points;
	public int[] xPoints, yPoints;
	public float z;
	public Color c;
	
	/**Fl‰che aus 4 Vektoren erstellen (Reihenfolge beachten!) (Farbe: Weiﬂ)
	 * @param v0
	 * @param v1
	 * @param v2
	 * @param v3
	 */
	public MySurface(MyVector v0, MyVector v1, MyVector v2, MyVector v3) {
		this(v0, v1, v2, v3, Color.WHITE);
	}
	
	/**Fl‰che aus 4 Vektoren erstellen (Reihenfolge beachten!)
	 * @param v0
	 * @param v1
	 * @param v2
	 * @param v3
	 * @param c
	 */
	public MySurface(MyVector v0, MyVector v1, MyVector v2, MyVector v3, Color c) {
		this.c = c;
		points = new MyVector[4];
		xPoints = new int[4];
		yPoints = new int[4];
		points[0] = v0;
		points[1] = v1;
		points[2] = v2;
		points[3] = v3;
		z = (v0.z+v1.z+v2.z+v3.z)/4;
	}
	
	public void setXPoints() {
		xPoints[0] = (int) points[0].x;
		xPoints[1] = (int) points[1].x;
		xPoints[2] = (int) points[2].x;
		xPoints[3] = (int) points[3].x;
	}
	
	public void setYPoints() {
		yPoints[0] = (int) points[0].y;
		yPoints[1] = (int) points[1].y;
		yPoints[2] = (int) points[2].y;
		yPoints[3] = (int) points[3].y;
	}

	@Override
	public int compareTo(MySurface s) {
		float compare = ((MySurface) s).z;
		return (int) (this.z - compare);
	}

	public void render(Graphics2D g2d, MyCamera cam) {
		setXPoints();
		setYPoints();
		g2d.fillPolygon(xPoints, yPoints, points.length);
	}
}
