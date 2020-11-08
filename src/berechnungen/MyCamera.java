package berechnungen;

import java.awt.event.*;

public class MyCamera implements MouseMotionListener, MouseListener, KeyListener  {
	public float x, y, z, angleX, angleY, angleZ;
	public int mouseX, mouseY, geschwindigkeit = 8;
	
	boolean[] tasten = new boolean[200];
	boolean vorne, hinten, links, rechts, runter, hoch, exit;
	
	public MyCamera(float x, float y, float z, float angleX, float angleY, float angleZ) {
		this.x = x;
		this.y = y;
		this.z = z;
		this.angleX = angleX;
		this.angleY = angleY;
		this.angleZ = angleZ;
	}
	
	public void update() {
		vorne = tasten[KeyEvent.VK_W] || tasten[KeyEvent.VK_UP];
		hinten = tasten[KeyEvent.VK_S] || tasten[KeyEvent.VK_DOWN];
		links = tasten[KeyEvent.VK_A] || tasten[KeyEvent.VK_LEFT];
		rechts = tasten[KeyEvent.VK_D] || tasten[KeyEvent.VK_RIGHT];
		runter = tasten[KeyEvent.VK_SHIFT];
		hoch = tasten[KeyEvent.VK_SPACE];
		exit = tasten[KeyEvent.VK_ESCAPE];
		
		
		//TODO Camera bewegen und nicht Angle Rotieren
		if (rechts) x += geschwindigkeit*1;
		if (links) x -= geschwindigkeit*1;
		if (hoch) y += geschwindigkeit*1;
		if (runter) y -= geschwindigkeit*1;
		if (vorne) z += geschwindigkeit*1;
		if (hinten) z -= geschwindigkeit*1;
		
//		if (rechts) angleX += geschwindigkeit*0.005f;
//		if (links) angleX -= geschwindigkeit*0.005f;
//		if (hoch) angleY += geschwindigkeit*0.005f;
//		if (runter) angleY -= geschwindigkeit*0.005f;
//		if (vorne) angleZ += geschwindigkeit*0.005f;
//		if (hinten) angleZ -= geschwindigkeit*0.005f;
	}

	@Override
	public void keyPressed(KeyEvent e) {
		tasten[e.getKeyCode()] = true;
	}

	@Override
	public void keyReleased(KeyEvent e) {
		if (e.getKeyCode() == KeyEvent.VK_ESCAPE) System.exit(0);
		tasten[e.getKeyCode()] = false;
	}

	@Override
	public void keyTyped(KeyEvent e) {
		
	}

	@Override
	public void mouseDragged(MouseEvent e) {
		angleX += ((float)(e.getX() - mouseX)/100);
		angleY += ((float)(e.getY() - mouseY)/100);
//		angleX += ((float)(e.getX() - angleX)/1000);
//		angleY += ((float)(e.getY() - angleX)/1000);
		
		mouseX = e.getX();
		mouseY = e.getY();
	}

	@Override
	public void mouseMoved(MouseEvent e) {
		
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		
	}

	@Override
	public void mousePressed(MouseEvent e) {
		mouseX = e.getX();
		mouseY = e.getY();
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		
	}

	@Override
	public void mouseExited(MouseEvent e) {
		
	}
}
