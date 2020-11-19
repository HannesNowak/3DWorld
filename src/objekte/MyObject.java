package objekte;

import basis.*;
import visualisierung.*;

public class MyObject {
	
	public float xOffset, yOffset, zOffset, edgeLength;
	public int multiplier;
	public MyPoint pos;
	public MyVector dir;
	public MyPoint[] points, points2D;
	public MyVector[] vecs;
	public MyLine[] lines;
	public MySurface[] surfaces;
	
	public MyObject(MyPoint pos, MyVector dir) {
		this.pos = pos;
		this.dir = dir;
	}
	
	public MyObject(float posX, float posY) {
		this(new MyPoint(posX, posY, 0), new MyVector(0, 0, 0));
	}
	
	public MyObject(float posX, float posY, float posZ) {
		this(new MyPoint(posX, posY, posZ), new MyVector(0, 0, 0));
	}
	
	public MyObject(float posX, float posY, float posZ, float dirX, float dirY, float dirZ) {
		this(new MyPoint(posX, posY, posZ), new MyVector(0, 0, 0));
	}
	
//	public MyObject(float[] coords) {
//		this.coords = coords;
//		x = coords[0];
//		y = coords[1];
//		z = coords.length>2 ? coords[2] : null;
//	}
//	
//	public MyObject(float[][] matrix) {
//		if (matrix.length > 2) coords = new float[] {matrix[0][0], matrix[1][0], matrix[2][0]};
//		else coords = new float[] {matrix[0][0], matrix[1][0]};
//		x = coords[0];
//		y = coords[1];
//		if (coords.length>2) z = coords[2];
//	}

	public void update(MyCamera cam) {
		this.xOffset = pos.x-cam.pos.x;
		this.yOffset = pos.y-cam.pos.y;
		this.zOffset = pos.z-cam.pos.z;
		rotation(cam.dir.x-this.dir.x, cam.dir.y-this.dir.y, cam.dir.z-this.dir.z);
		for (int i = 0; i < points.length; i++) {
//			points[i] = MyVector.toPoint(vecs[i].rotation(cam.dir.x-this.dir.x, cam.dir.y-this.dir.y, cam.dir.z-this.dir.z));
			points2D = points;
		}
		for (MySurface s : surfaces) {
			s.update();
		}
		
		// Aktuell nur für MyCube
		for (int i = 0; i < 4; i++) {
			lines[i*3] = new MyLine(points2D[i].center(cam), points2D[(i+1)%4].center(cam));
			lines[i*3+1] = new MyLine(points2D[i+4].center(cam), points2D[((i+1)%4)+4].center(cam));
			lines[i*3+2] = new MyLine(points2D[i].center(cam), points2D[i+4].center(cam));
		}}
	
	public static MyObject translate() {
		return null;
	}

	public void rotation(float dirX, float dirY, float dirZ) {
		rotationX(dirX);
		rotationY(dirY);
		rotationZ(dirZ);
	}
	
	public void rotationX(float angle) {
		dir.x = angle;
		float[][] rx = new float[][] {
			{1, 0, 0},
			{0, (float) Math.cos(angle), (float) Math.sin(angle)},
			{0, (float) -Math.sin(angle), (float) Math.cos(angle)}};
		dir = MyMatrix.matrixToVector(MyMatrix.matrixMultiplikation(rx, dir));
	}
	
	public void rotationY(float angle) {
		dir.y = angle;
		float[][] ry = new float[][] {
			{(float) Math.cos(angle), 0, (float) -Math.sin(angle)},
			{0, 1, 0},
			{(float) Math.sin(angle), 0, (float) Math.cos(angle)}};
		dir = MyMatrix.matrixToVector(MyMatrix.matrixMultiplikation(ry, dir));
	}
	
	public void rotationZ(float angle) {
		dir.z = angle;
		float[][] rz = new float[][] {
			{(float) Math.cos(angle), (float) Math.sin(angle), 0},
			{(float) -Math.sin(angle), (float) Math.cos(angle), 0},
			{0, 0, 1}};
		dir = MyMatrix.matrixToVector(MyMatrix.matrixMultiplikation(rz, dir));
	}
}
