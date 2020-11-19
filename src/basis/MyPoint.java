package basis;

import java.awt.*;

import visualisierung.*;

public class MyPoint {
	
	public float x, y, z;
	
	public MyPoint(float x, float y, float z) {
		this.x = x;
		this.y = y;
		this.z = z;
	}

	public void render(Graphics2D g2d, MyCamera cam) {
		g2d.drawLine((int) x, (int) y, (int) x, (int) y);
	}
	
	public static MyVector toVector(MyPoint p) {
		return new MyVector(p.x, p.y, p.z);
	}

	//TODO Centering mit Camera verbinden
	public MyPoint center(MyCamera cam) {
		return new MyPoint(this.x + cam.pos.x, this.y + cam.pos.y, this.z + cam.pos.z);
	}

	public static MyPoint averagePoint(MyPoint[] points) {
		float sumX = 0, sumY = 0, sumZ = 0;
		for (MyPoint p : points) {
			sumX += p.x;
			sumY += p.y;
			sumZ += p.z;
		}
		return new MyPoint(sumX/points.length, sumY/points.length, sumZ/points.length);
	}

	public MyPoint multiply(int factor) {
		this.x = x*factor;
		this.y = y*factor;
		this.z = z*factor;
		return this;
	}
	
	public MyPoint add(MyPoint p) {
		return new MyPoint(x+p.x, y+p.y, z+p.z);
	}
	
	public MyPoint add(MyVector v) {
		return add(MyVector.toPoint(v));
	}
}
