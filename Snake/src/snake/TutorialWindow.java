package snake;

import java.awt.Container;

import javax.swing.GroupLayout;
import javax.swing.JFrame;
import javax.swing.JLabel;

public class TutorialWindow extends JFrame{
	

	private Pictures pic = new Pictures();
	
	
	/*
	 * Ein Hauptfenster hält alle drei Bilder sowie den jeweiligen Text 
	 * 
	 * 	-------------------------------------
	 * |	^								|    <--- 	hauptfenster
	 * |	|								|   
	 * |	v								|   
	 * | 20pixel		   10 pixel			|   
	 * | <--->	bewegung	<--->	text	|
	 * |		^					^		|
	 * |		|	   25 pixel	    |		|
	 * |		v					v		|
	 * |		pause				text <--|--------	text zum zugehörigen bild
	 * |									|
	 * |									|
	 * |									|
	 * |		ziel	<-----------text----|--------	Bild 
	 * |									|
	 * |									|
	 * -------------------------------------
	 */
	/*
	 * @TODO:
	 * 
	 * Close button einfügen 
	 * "Nicht mehr anzeigen" checkbox einfügen
	 */
	
	public JFrame getTutorial() {
		//Das frame muss drei bilder sowie drei texte halten 
		JLabel breakBild = new JLabel(pic.getBreakPic());
		System.out.println(breakBild.toString());
		JLabel moveBild = new JLabel(pic.getMovementPic());
		JLabel zielBild = new JLabel(pic.getGameGoalPic());
//		breakBild.setSize(200,200);
//		moveBild.setSize(200,200);
//		breakBild.setMinimumSize(new Dimension(300,200));
//		moveBild.setMinimumSize(new Dimension(300,200));
//		zielBild.setMinimumSize(new Dimension(300,200));
		JLabel moveText = new JLabel("Sie steuern Ihren hungrigen Pepe mit den Tasten W -> für hoch,\n\r A -> Für links,\n\r S -> Für unten und D -> für rechts");
		JLabel breakText = new JLabel("Um das Spiel zu pausieren, betätigen Sie die Leertaste");
		JLabel zielText = new JLabel("Ihr Pepe ist hungrig und einsam! Sammeln sie so viele Eier wie sie können um Ihm zu helfen!");
		//Füge alles in frame ein 
		Container pane = getContentPane();
		GroupLayout gl = new GroupLayout(pane);
		pane.setLayout(gl);
		gl.setAutoCreateContainerGaps(true);
		gl.setAutoCreateGaps(true);
		//füge einzelne Objekte zu pane hinzu -> das wie wird im grouplayout gesetzt 
		gl.setHorizontalGroup(gl.createSequentialGroup()
				.addGap(20)
				.addGroup(gl.createParallelGroup()
						.addComponent(moveBild)
						.addComponent(breakBild)
						.addComponent(zielBild))
				.addGroup(gl.createParallelGroup()
						.addComponent(moveText)
						.addComponent(breakText)
						.addComponent(zielText)));
		
		gl.setVerticalGroup(gl.createParallelGroup()
				.addGap(20)
				.addGroup(gl.createSequentialGroup()
						.addComponent(moveBild)
						.addComponent(breakBild)
						.addComponent(zielBild))
				.addGroup(gl.createSequentialGroup()
						.addGap(72)
						.addComponent(moveText)
						.addGap(200)
						.addComponent(breakText)
						.addGap(290)
						.addComponent(zielText)));
		setTitle("Tutorial");
		isFocused();
		pack();
		return this;
	}
	
	
	
	
}
