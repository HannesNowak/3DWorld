package visualisierung;

import java.awt.*;
import java.awt.event.*;

import javax.swing.*;

public class Menu extends JPanel implements ActionListener {
	private static final long serialVersionUID = -7730230907235635452L;
	public static int w = Window.w, h = 50;
	
	
	JButton angleAdd, angleSub, anglePlay;
	
	public Menu() {
		setBounds(0, 0, w, 50);
		setLayout(null);
		setDoubleBuffered(true);
		setBackground(new Color(7, 7, 40));
		
		angleSub = new JButton("\u23F4");
		angleSub.setBounds(0, 0, 75, 50);
		angleSub.setForeground(Color.WHITE);
		angleSub.setBackground(new Color(19, 19, 40));
		angleSub.setFocusPainted(false);
		angleSub.setBorderPainted(false);
		angleSub.addActionListener(this);
		add(angleSub);
		
		anglePlay = new JButton("\u23EF");
		anglePlay.setBounds(80, 0, 75, 50);
		anglePlay.setForeground(Color.WHITE);
		anglePlay.setBackground(new Color(19, 19, 40));
		anglePlay.setFocusPainted(false);
		anglePlay.setBorderPainted(false);
		anglePlay.addActionListener(this);
		add(anglePlay);
		
		angleAdd = new JButton("\u23F5");
		angleAdd.setBounds(160, 0, 75, 50);
		angleAdd.setForeground(Color.WHITE);
		angleAdd.setBackground(new Color(19, 19, 40));
		angleAdd.setFocusPainted(false);
		angleAdd.setBorderPainted(false);
		angleAdd.addActionListener(this);
		add(angleAdd);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		
	}
}
