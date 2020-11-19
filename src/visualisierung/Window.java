package visualisierung;

import java.awt.*;
import java.util.*;

import javax.swing.*;

import basis.MySurface;
import objekte.*;

public class Window extends JPanel implements Runnable {
	
	private static final long serialVersionUID = -8255319694373975038L;
	
	public static int w = Toolkit.getDefaultToolkit().getScreenSize().width;
	public static int h = Toolkit.getDefaultToolkit().getScreenSize().height;
	
	public static JFrame frame = new JFrame();
	
	Thread t;
//	camera(width/2, height/2, (height/2) / tan(PI/6), width/2, height/2, 0, 0, 1, 0);
//	public MyCamera cam = new MyCamera(frame.getWidth()/2, frame.getHeight()/2, (float) ((getHeight()/2) / Math.tan(Math.PI/6)), 0, 0, 0);
	public MyCamera cam = new MyCamera(0, 0, 0, 0, 0, -1);
	MyObject[] objects;
	MySurface[] surfaces;
	
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
		
		objects = new MyCube[27];
		
		objects[0] = new MyCube(60, 60, 60, 50f);
		objects[1] = new MyCube(0, 60, 60, 50f);
		objects[2] = new MyCube(-60, 60, 60, 50f);
		objects[3] = new MyCube(60, 0, 60, 50f);
		objects[4] = new MyCube(0, 0, 60, 50f);
		objects[5] = new MyCube(-60, 0, 60, 50f);
		objects[6] = new MyCube(60, -60, 60, 50f);
		objects[7] = new MyCube(0, -60, 60, 50f);
		objects[8] = new MyCube(-60, -60, 60, 50f);

		objects[9] = new MyCube(60, 60, 0, 50f);
		objects[10] = new MyCube(0, 60, 0, 50f);
		objects[11] = new MyCube(-60, 60, 0, 50f);
		objects[12] = new MyCube(60, 0, 0, 50f);
		objects[13] = new MyCube(0, 0, 0, 50f);
		objects[14] = new MyCube(-60, 0, 0, 50f);
		objects[15] = new MyCube(60, -60, 0, 50f);
		objects[16] = new MyCube(0, -60, 0, 50f);
		objects[17] = new MyCube(-60, -60, 0, 50f);
		
		objects[18] = new MyCube(60, 60, -60, 50f);
		objects[19] = new MyCube(0, 60, -60, 50f);
		objects[20] = new MyCube(-60, 60, -60, 50f);
		objects[21] = new MyCube(60, 0, -60, 50f);
		objects[22] = new MyCube(0, 0, -60, 50f);
		objects[23] = new MyCube(-60, 0, -60, 50f);
		objects[24] = new MyCube(60, -60, -60, 50f);
		objects[25] = new MyCube(0, -60, -60, 50f);
		objects[26] = new MyCube(-60, -60, -60, 50f);
		
		surfaces = new MySurface[objects.length*6];
		for (int i = 0; i < objects.length; i++) {
			for (int j = 0; j < objects[i].surfaces.length; j++) {
				surfaces[j+(i*6)] = objects[i].surfaces[j];
			}
		}
		
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
		
		for (MyObject obj : objects) {
			obj.update(cam);
		}
//		System.out.println("Pos: X: " + cam.pos.x + " | Y: " + cam.pos.y + " | Z: " + cam.pos.z);
//		System.out.println("Dir: X: " + cam.dir.x + " | Y: " + cam.dir.y + " | Z: " + cam.dir.z);
		
		Arrays.sort(surfaces);
		for (MySurface s : surfaces) {
			s.render(g2d, cam);
		}
		
//		for (MyCube c : cubes) {
//			c.render(g2d, cam);
//		}
		
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
