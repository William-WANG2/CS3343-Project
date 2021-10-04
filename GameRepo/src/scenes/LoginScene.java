package scenes;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JPanel;

/*
 * Create by gsp
 * initialize Login panel
 */
public class LoginScene extends JPanel {
	public LoginScene() { 
		
	}
	
	public void init(int x, int y) {
		// initialize start button 
		JButton btStart = new JButton("Start");
		btStart.setFont(new Font("Courier New", Font.BOLD, 20));
		btStart.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// Debug
//				System.out.print("here");
			}
		});
		this.setLayout(null);
		btStart.setBounds(x-150, y / 3, 100, 50);
		// Debug
//		System.out.println("startBtn - " + (y - 200) + " ");
		this.add(btStart);
		
		// initialize setting button
	}
	
	@Override
	public void paint(Graphics g) {
//		Image image = new ImageIcon("").getImage();
//		g.drawImage(image, 50, 50, 100, 100, null);
		
//		g.drawImage(icon.getImage(), 
//				icon.getX(), icon.getY(),
//				icon.getWidth(), icon.getHeight(),
//				null);
		super.paint(g);
		
		
		
	}
	
	
	
}
