package objekte;

import java.awt.Graphics2D;

import berechnungen.MyCamera;
import berechnungen.MyMatrix;
import visualisierung.Window;

public class MyVector extends MyObject {
	
	/**Vektor mit 2 Koordinaten erstellen
	 * @param x
	 * @param y
	 */
	public MyVector(float x, float y) {
		super(x, y);
	}
	
	/**Vektor mit 3 Koordinaten erstellen
	 * @param x
	 * @param y
	 * @param z
	 */
	public MyVector(float x, float y, float z) {
		super(x, y, z);
	}
	
	/**Vektor mit 3 Koordinaten erstellen
	 * @param x
	 * @param y
	 * @param z
	 */
	public MyVector(float x, float y, float z, float angleX, float angleY, float angleZ) {
		super(x, y, z, angleX, angleY, angleZ);
	}
	
	/**Vektor aus 2 oder 3 Koordinaten erstellen
	 * @param coords
	 */
	public MyVector(float[] coords) {
		super(coords);
	}
	
	/**Vektor aus Matrix erstellen
	 * @param matrix 
	 */
	public MyVector(float[][] matrix) {
		super(matrix);
	}

	/**Länge des Vektors erhalten
	 * @return Länge
	 */
	public float getLength() {
		return (float) Math.sqrt(coords[0]*coords[0] + coords[1]*coords[1] + coords.length>2 ? coords[2]*coords[2] : 0);
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
	
	public MyVector multiply(int factor) {
		return new MyVector(this.x*factor, this.y*factor, this.z*factor);
	}
	
	//TODO Centering mit Camera verbinden
	public MyVector center(MyCamera cam) {
		System.out.println(this.x + " " + cam.x);
		return new MyVector(this.x + cam.x, this.y + cam.y, this.z + cam.z);
	}
	
	public MyVector convert() {
		float[][] conversionMatrix = new float[][] {{1, 0, 0}, {0, 1, 0}};
		return MyMatrix.matrixToVector(MyMatrix.matrixMultiplikation(conversionMatrix, this));
	}
	
	//TODO Vector durch Camera bewegen
	public void moveByCamera(MyCamera cam) {
//		System.out.println(this.x);
//		this.x -= cam.x/1000;
//		this.y -= cam.y/1000;
//		this.z -= cam.z/1000;
	}

	public void render(Graphics2D g2d, MyCamera cam) {
		g2d.drawLine((int) x, (int) y, (int) x, (int) y);
	}
}
