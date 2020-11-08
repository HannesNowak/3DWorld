package visualisierung;

import java.awt.*;
import java.util.*;

import javax.swing.*;

import berechnungen.*;
import objekte.*;

public class Window extends JPanel implements Runnable {
	
	private static final long serialVersionUID = -8255319694373975038L;
	
	public static int w = Toolkit.getDefaultToolkit().getScreenSize().width;
	public static int h = Toolkit.getDefaultToolkit().getScreenSize().height;
	
	public static JFrame frame = new JFrame();
	
	Thread t;
//	camera(width/2, height/2, (height/2) / tan(PI/6), width/2, height/2, 0, 0, 1, 0);
	MyCamera cam = new MyCamera(frame.getWidth()/2, frame.getHeight()/2, (float) ((getHeight()/2) / Math.tan(Math.PI/6)), 0, 0, 0);
	MyCube[] cubes;
	
	int ups = 60, maxFps = 120, stroke = 4;
	
	public static boolean running = true;
	
	public static void main(String[] args) {
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
		frame.setLocationRelativeTo(null);
		frame.setSize(w/2, h/2);
		frame.setVisible(true);
		frame.setLayout(null);
		frame.add(new Window());
		frame.add(new Menu());
	}
	
	public Window() {
		setBounds(0,50,w,h-50);
		setLayout(null);
		setDoubleBuffered(true);
		
		// Camera hinzufügen
		addKeyListener(cam);
		addMouseListener(cam);
		addMouseMotionListener(cam);
		
		cubes = new MyCube[8];
//		cubes[0] = new MyCube(0, 0, 0, 200f);
		
//		cubes[0] = new MyCube(25, 25, 25, 50f);
//		cubes[1] = new MyCube(25, 25, -25, 50f);
//		cubes[2] = new MyCube(25, -25, 25, 50f);
//		cubes[3] = new MyCube(25, -25, -25, 50f);
//		cubes[4] = new MyCube(-25, 25, 25, 50f);
//		cubes[5] = new MyCube(-25, 25, -25, 50f);
//		cubes[6] = new MyCube(-25, -25, 25, 50f);
//		cubes[7] = new MyCube(-25, -25, -25, 50f);
		
		cubes[0] = new MyCube(50, 50, 50, 0, 0, 0, 50f, 1);
		cubes[1] = new MyCube(50, 50, -50, 50f);
		cubes[2] = new MyCube(50, -50, 50, 50f);
		cubes[3] = new MyCube(50, -50, -50, 50f);
		cubes[4] = new MyCube(-50, 50, 50, 50f);
		cubes[5] = new MyCube(-50, 50, -50, 50f);
		cubes[6] = new MyCube(-50, -50, 50, 50f);
		cubes[7] = new MyCube(-50, -50, -50, 50f);
		
		t = new Thread(this, "Window");
		t.start();
	}
	
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		Graphics2D g2d = (Graphics2D) g;
		g2d.setColor(new Color(7, 7, 40));
		g2d.fillRect(0, 0, this.getWidth(), this.getHeight());
		g2d.setStroke(new BasicStroke(stroke));
		
		cam.update();
		
		for (MyCube c : cubes) {
			c.update(c.angleX, c.angleY, c.angleZ, cam);
			c.angleX = cam.angleX;
			c.angleY = cam.angleY;
			c.angleZ = cam.angleZ;
		}
		
		Arrays.sort(cubes);
		for (MyCube c : cubes) {
//			System.out.println(c.z);
			c.render(g2d, cam);
		}
		
		g2d.dispose();
	}
	
//	public void paintComponent(Graphics g) {
//		super.paintComponent(g);
//		Graphics2D g2d = (Graphics2D) g;
//		g2d.setColor(new Color(7, 7, 40));
//		g2d.fillRect(0, 0, this.getWidth(), this.getHeight());
//		g2d.setStroke(new BasicStroke(stroke));
//		
//		cam.update();
//		
//		for (MyCube c : cubes) {
//			c.update(c.angleX, c.angleY, c.angleZ, cam);
//			c.angleX = cam.angleX;
//			c.angleY = cam.angleY;
//			c.angleZ = cam.angleZ;
//		}
//		
//		Arrays.sort(cubes);
//		for (MyCube c : cubes) {
//			System.out.println(c.z);
//			c.render(g2d, cam);
//		}
//		
//		g2d.dispose();
//	}
	
	public void run() {
		// Berechnung der fps und ups
		long lastTime = System.nanoTime();
		long timer = System.currentTimeMillis();
		final double updateFrequenz = 1000000000.0 / ups;
		double deltaUpdates = 0, deltaFrames = 0, framesFrequenz = 1000000000.0 / maxFps;
		int frames = 0;
		int updates = 0;
		requestFocus();
		while (running) {
			long now = System.nanoTime();
			deltaUpdates += (now - lastTime) / updateFrequenz;
			while (deltaUpdates >= 1) {
//				update();
				repaint();
				updates++;
				deltaUpdates--;
			}
			deltaFrames += (now - lastTime) / framesFrequenz;
			if (deltaFrames >= 1) {
//				render();
				frames++;
				deltaFrames %= 1;
			}
			lastTime = now;
			// Anzeigen der updates und frames im Fenstertitel und Zurücksetzen von updates und frames
			// --> immer eimal pro Sekunde ausgeführt
			if (System.currentTimeMillis() - timer > 1000) {
				timer += 1000;
				frame.setTitle(updates + " ups | " + frames + " fps");
				updates = 0;
				frames = 0;
			}
		}
	}
}
