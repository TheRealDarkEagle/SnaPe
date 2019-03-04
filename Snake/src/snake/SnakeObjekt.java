package snake;

import java.awt.Dimension;

import javax.swing.ImageIcon;

/*
 * wird noch Erweitertert -> man soll nur noch das SnakeObjekt ansprechen und diese beinhaltet alle Körperteile -> KOPF KÖRPER SCHWANZ 
 * Hier wird auch ein neues Objekt erzeugt 
 * Hier werden auch alle einzelobjekte zusammengefügt -> Position und Bild 
 * 
 * 
 */

/*
 * Hierüber sollen die Bilder für die Schlange geladen werden 
 * Damit sie nicht mehr über umwege in die View geladen werden,
 * nur um dann hierhin gegeben zu werden 
 * 
 * Es soll nur noch eine createNewBody methode geben, welche sich die nötigen Daten selbst bezieht -> position und bild für das Körperstück
 */

public class SnakeObjekt {
	
	
	private Dimension position;
	private ImageIcon icon;
	
	public SnakeObjekt(Dimension position, ImageIcon icon) {
		this.position = position;
		this.icon = icon;
	
	}

	/*
	 * Gibt die Position der Schlange wider
	 */
	public Dimension getPosition() {
		return this.position;
	}
	/*
	 * Gibt das Bild der Schlange 
	 */
	public ImageIcon getIcon() {
		return this.icon;
	}
}
