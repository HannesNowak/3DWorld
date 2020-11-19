package basis;

import visualisierung.*;

public class MyVector {
	
	public float x, y, z;
	
	public MyVector(float x, float y, float z) {
		this.x = x;
		this.y = y;
		this.z = z;
	}

	/**Länge des Vektors erhalten
	 * @return Länge
	 */
	public float getLength() {
		return (float) Math.sqrt(x*x + y*y + z*z);
	}
	
	public static float skalarProdukt(MyVector v1, MyVector v2) {
		return v1.x*v2.x+v1.y*v2.y+v1.z*v2.z;
	}
	
	// senkrechter Vektor zur Ebene aus v1 und v2
	public static MyVector kreuzProdukt(MyVector v1, MyVector v2) {
		return new MyVector(v1.y*v2.z-v1.z*v2.y, v1.z*v2.x-v1.x*v2.z, v1.x*v2.y-v1.y*v2.x);
	}
	
	// Volumen des Spats
	public static float spatProdukt(MyVector v1, MyVector v2, MyVector v3) {
		return skalarProdukt(kreuzProdukt(v1, v2), v3);
	}
	
	public MyVector multiply(float factor) {
		return new MyVector(this.x*factor, this.y*factor, this.z*factor);
	}
	
	//TODO Centering mit Camera verbinden
	public MyVector center(MyCamera cam) {
//		System.out.println(this.x + " " + cam.x);
		return new MyVector(this.x + cam.pos.x, this.y + cam.pos.y, this.z + cam.pos.z);
	}
	
	//TODO Vector durch Camera bewegen
	public void moveByCamera(MyCamera cam) {
//		System.out.println(this.x);
//		this.x -= cam.x/1000;
//		this.y -= cam.y/1000;
//		this.z -= cam.z/1000;
	}
	
	public static MyVector averageVector(MyVector[] vecs) {
		float sumX = 0, sumY = 0, sumZ = 0;
		for (MyVector v : vecs) {
			sumX += v.x;
			sumY += v.y;
			sumZ += v.z;
		}
		return new MyVector(sumX/vecs.length, sumY/vecs.length, sumZ/vecs.length);
	}
	
	public static MyPoint toPoint(MyVector v) {
		return new MyPoint(v.x, v.y, v.z);
	}
	
	public MyVector add(MyPoint p) {
		return add(MyPoint.toVector(p));
	}
	
	public MyVector add(MyVector v) {
		return new MyVector(x+v.x, y+v.y, z+v.z);
	}
}
