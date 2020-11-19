package basis;

public class MyMatrix {
	
	public static float[][] vectorToMatrix(MyVector v) {
		return new float[][] {{v.x}, {v.y}, {v.z}};
	}
	public static float[][] pointToMatrix(MyPoint p) {
		return new float[][] {{p.x}, {p.y}, {p.z}};
	}
	
	public static MyVector matrixToVector(float[][] matrix) {
		return new MyVector(matrix[0][0], matrix[1][0], matrix[2][0]);
	}
	
	public static MyPoint matrixToPoint(float[][] matrix) {
		return new MyPoint(matrix[0][0], matrix[1][0], matrix[2][0]);
	}
	
	public static float[][] matrixMultiplikation(float[][] a, MyVector v) {
		float[][] b = vectorToMatrix(v);
		return matrixMultiplikation(a, b);
	}
	
	public static float[][] matrixMultiplikation(float[][] a, float[][] b) {
		int spaltenA = a[0].length;
		int zeilenA = a.length;
		int spaltenB = b[0].length;
		int zeilenB = b.length;
		
		if (spaltenA != zeilenB) {
			System.out.println("Es müssen genau so viele Zeilen bei A, wie Spalten bei B sein!");
			return null;
		}
		
		float[][] res = new float[zeilenA][spaltenB];
		
		for (int i = 0; i < zeilenA; i++) {
			for (int j = 0; j < spaltenB; j++) {
				float summe = 0;
				for (int k = 0; k < spaltenA; k++) {
					summe += a[i][k] * b[k][j];
				}
				res[i][j] = summe;
			}
		}
		return res;
	}
	
	public static void writeMatrix(float[][] m, String text) {
		int spalten = m[0].length;
		int zeilen = m.length;
		System.out.println(text + ": " + zeilen + " x " + spalten);
		System.out.println("------------");
		for (int i = 0; i < zeilen; i++) {
			for (int j = 0; j < spalten; j++) {
				System.out.print(m[i][j] + " ");
			}
			System.out.println();
		}
		System.out.println();
	}
}
