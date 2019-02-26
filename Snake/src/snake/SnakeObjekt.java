package snake;

import java.awt.Dimension;
import javax.swing.ImageIcon;

public class SnakeObjekt {
	
	public SnakeObjekt(Dimension position, ImageIcon icon) {
		this.position = position;
		this.icon = icon;
	}

	private Dimension position;
	private ImageIcon icon;
	
	/*
	 * Gibt die Position der Schlange wider
	 */
	public Dimension getPosition() {
		return position;
	}
	/*
	 * Setzt die Position der Schlange
	 */
	public void setPosition(Dimension position) {
		this.position = position;
	}
	/*
	 * Gibt das Bild der Schlange 
	 */
	public ImageIcon getIcon() {
		return icon;
	}
}
