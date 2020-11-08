package objekte;

import java.awt.Color;

import berechnungen.MyCamera;
import berechnungen.MyMatrix;
import visualisierung.Window;

public class MyObject {
	public float x, y, z, angleX, angleY, angleZ;
	public float[] coords;
	public MyVector[] points, points2D;
	public MyLine[] lines;
	public MySurface[] surfaces;
	
	public MyObject(float x, float y) {
		this.x = x;
		this.y = y;
		coords = new float[]{x, y};
	}
	
	public MyObject(float x, float y, float z) {
		this.x = x;
		this.y = y;
		this.z = z;
		coords = new float[]{x, y, z};
	}
	
	public MyObject(float x, float y, float z, float angleX, float angleY, float angleZ) {
		this.x = x;
		this.y = y;
		this.z = z;
		this.angleX = angleX;
		this.angleY = angleY;
		this.angleZ = angleZ;
		coords = new float[]{x, y, z};
	}
	
	public MyObject(float[] coords) {
		this.coords = coords;
		x = coords[0];
		y = coords[1];
		z = coords.length>2 ? coords[2] : null;
	}
	
	public MyObject(float[][] matrix) {
		if (matrix.length > 2) coords = new float[] {matrix[0][0], matrix[1][0], matrix[2][0]};
		else coords = new float[] {matrix[0][0], matrix[1][0]};
		x = coords[0];
		y = coords[1];
		if (coords.length>2) z = coords[2];
	}
	
	public void update(float angleX, float angleY, float angleZ, MyCamera cam) {
		for (int i = 0; i < points.length; i++) {
			points[i].moveByCamera(cam);
			points[i] = points[i].rotation(cam.angleX-angleX, cam.angleY-angleY, cam.angleZ-angleZ);
			points2D[i] = points[i].convert();
		}
		
		// Aktuell nur für MyCube
		for (int i = 0; i < 4; i++) {
			lines[i*3] = new MyLine(points2D[i].center(cam), points2D[(i+1)%4].center(cam));
			lines[i*3+1] = new MyLine(points2D[i+4].center(cam), points2D[((i+1)%4)+4].center(cam));
			lines[i*3+2] = new MyLine(points2D[i].center(cam), points2D[i+4].center(cam));
		}
		
		surfaces[0] = new MySurface(points[0].center(cam), points[1].center(cam), points[2].center(cam), points[3].center(cam), Color.RED);
		surfaces[1] = new MySurface(points[0].center(cam), points[1].center(cam), points[5].center(cam), points[4].center(cam), Color.YELLOW);
		surfaces[2] = new MySurface(points[0].center(cam), points[3].center(cam), points[7].center(cam), points[4].center(cam), Color.GREEN);
		surfaces[3] = new MySurface(points[1].center(cam), points[2].center(cam), points[6].center(cam), points[5].center(cam), Color.BLUE);
		surfaces[4] = new MySurface(points[2].center(cam), points[3].center(cam), points[7].center(cam), points[6].center(cam), Color.WHITE);
		surfaces[5] = new MySurface(points[4].center(cam), points[5].center(cam), points[6].center(cam), points[7].center(cam), new Color(255, 127, 0));
	}

	public MyVector rotation(float angleX, float angleY, float angleZ) {
		return rotationX(angleX).rotationY(angleY).rotationZ(angleZ);
	}
	
	public MyVector rotationX(float angle) {
		this.angleX = angle;
		float[][] rx = new float[][] {
			{1, 0, 0},
			{0, (float) Math.cos(angle), (float) Math.sin(angle)},
			{0, (float) -Math.sin(angle), (float) Math.cos(angle)}};
		return MyMatrix.matrixToVector(MyMatrix.matrixMultiplikation(rx, (MyVector) this));
	}
	
	public MyVector rotationY(float angle) {
		this.angleY = angle;
		float[][] ry = new float[][] {
			{(float) Math.cos(angle), 0, (float) -Math.sin(angle)},
			{0, 1, 0},
			{(float) Math.sin(angle), 0, (float) Math.cos(angle)}};
		return MyMatrix.matrixToVector(MyMatrix.matrixMultiplikation(ry, (MyVector) this));
	}
	
	public MyVector rotationZ(float angle) {
		this.angleZ = angle;
		float[][] rz = new float[][] {
			{(float) Math.cos(angleZ), (float) Math.sin(angleZ), 0},
			{(float) -Math.sin(angleZ), (float) Math.cos(angleZ), 0},
			{0, 0, 1}};
		return MyMatrix.matrixToVector(MyMatrix.matrixMultiplikation(rz, (MyVector) this));
	}
}
