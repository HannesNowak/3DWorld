package basis;

import java.awt.Graphics2D;

import visualisierung.MyCamera;

public class MyLine {
	public MyPoint p0, p1;
	
	/**Linie aus 2 Punkten erstellen
	 * @param p0
	 * @param p1
	 */
	public MyLine(MyPoint p0, MyPoint p1) {
		this.p0 = p0;
		this.p1 = p0;
	}

	public void render(Graphics2D g2d, MyCamera cam) {
		g2d.drawLine((int) p0.x, (int) p0.y, (int) p1.x, (int) p1.y);
	}
}
