package objekte;

import java.awt.Graphics2D;

import berechnungen.MyCamera;

public class MyLine {
	public MyVector v0, v1;
	
	/**Linie aus 2 Vektoren erstellen
	 * @param v0
	 * @param v1
	 */
	public MyLine(MyVector v0, MyVector v1) {
		this.v0 = v0;
		this.v1 = v1;
	}

	public void render(Graphics2D g2d, MyCamera cam) {
		g2d.drawLine((int) v0.x, (int) v0.y, (int) v1.x, (int) v1.y);
	}
}
