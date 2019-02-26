package snake;

import java.awt.Dimension;

import javax.swing.ImageIcon;

public class Food {
	
	public Food(Dimension position) {
	this.position = position;	
	}
	
	private Dimension position;
	private ImageIcon appearence = new ImageIcon(getClass().getClassLoader().getResource("jumpingEgg.gif"));

	public Dimension getPosition() {
		return this.position;
	}
	public ImageIcon getImage() {
		return this.appearence;
	}
	
}

