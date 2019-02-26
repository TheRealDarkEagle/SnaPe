package snake;

import java.awt.Container;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class MainMenu extends JPanel{

	private JFrame mainMenu;
	private JFrame optionMenu;
	private JFrame creditMenu;
	private JFrame scoreMenu;
	
	
	public MainMenu() {
		mainMenu();
	}
	/*
	 * Main Menu 
	 * Wird 4 Buttons besitzen? Play Credits Option Highscore
	 * play -> startet das spiel 
	 * credits -> neues Fenster -> Musiktitel programmierer etc (was eben so wichtig ist)
	 * Options -> Lautstärke einstellen quacken deaktivieren eigene bilder einfügen? vllt auch gifs vollständig deaktivieren können? 
	 */
	private void mainMenu() {
		mainMenu = new JFrame("Main Menu");
//		mainMenu.add(playGround);
		mainMenu.setSize(200, 250);
		//StartBtn startet das Spiel
		JButton startBtn = new JButton("Start Game");
		startBtn.addActionListener((e)->{
			mainMenu.setVisible(false);
//			startGame();
		});
		//Öffnet die Optionen
		JButton OptionBtn = new JButton("Options");
		OptionBtn.addActionListener((e)->{
			mainMenu.setVisible(false);
			optionMenu.setVisible(true);
		});
		//Öffnet die Credits
		JButton credBtn = new JButton("Credits");
		credBtn.addActionListener((e)->{
			mainMenu.setVisible(false);
			creditMenu.setVisible(true);
		});
		//Öffnet den Highscore
		JButton scoreBtn = new JButton("Highscore");
		scoreBtn.addActionListener((e)->{
			mainMenu.setVisible(false);
			scoreMenu.setVisible(true);
		});
		JButton closeBtn = new JButton("Close");
		closeBtn.addActionListener((e)->{
		});
		
		Container pane = mainMenu.getContentPane();
		GroupLayout gl = new GroupLayout(pane);
		pane.setLayout(gl);
		gl.setAutoCreateContainerGaps(true);
		gl.setAutoCreateGaps(true);
		gl.setHorizontalGroup(gl.createParallelGroup(Alignment.CENTER)
				.addComponent(startBtn)
				.addGap(10)
				.addComponent(OptionBtn)
				.addGap(10)
				.addComponent(credBtn)
				.addGap(10)
				.addComponent(scoreBtn)
				.addComponent(closeBtn));
		gl.setVerticalGroup(gl.createSequentialGroup()
				.addComponent(startBtn)
				.addComponent(OptionBtn)
				.addComponent(credBtn)
				.addComponent(scoreBtn)
				.addComponent(closeBtn));
	}
	
}
