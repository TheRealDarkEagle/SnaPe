package snake;

import java.awt.Container;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;
import java.io.File;
import java.security.cert.PKIXRevocationChecker.Option;

import javax.swing.GroupLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenuBar;
import javax.swing.GroupLayout.Alignment;

/*
 * View zeigt an 
 * Model hält was view zeigen soll
 * Controller berechnet die werte neu 
 * 
 * 
 */
public class SnakeView extends JFrame{
	
	
	
	public SnakeView() {
		getMiddleXOfScreens();
		initUI();
	}
	
	private JFrame gameFrame;
	private int height;
	private int width;
	private static final long serialVersionUID = 1L;
	
	
	private void initUI() {
		OptionMenu option = new OptionMenu();
		JFrame optionMenu = option.getOptionMenu();
		Dimension buttonDim = new Dimension(100, 30);
		setTitle("Jetzt wird SnaPed");
//		setLocation(getMiddleXOfScreens(), y);
		setTitle("Main Menu");
		setSize(145, 245);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		//StartBtn startet das Spiel
		JButton startBtn = new JButton("Start Game");
		startBtn.setMinimumSize(buttonDim);
		startBtn.setMaximumSize(buttonDim);
		startBtn.addActionListener((e)->{
			
			//Optionen Txt Datei erstellen 
			if(new File("C:\\user\\SnakeOptn.txt")==null) {
				
			}
			
			//Wenn datei noch nicht existiert mit standart optionen einstellungen speichern 
			
			
			SnakeModel playGround = new SnakeModel(this);
			setVisible(false);
			gameFrame = new JFrame("Jetzt wird SnaPed");
			gameFrame.add(playGround);
			gameFrame.setSize(1515, 1040);
			gameFrame.setVisible(false);
			gameFrame.setDefaultCloseOperation(gameFrame.EXIT_ON_CLOSE);
			
			gameFrame.setVisible(true);
//			
			pack();
		});
		//Öffnet die Optionen
		JButton OptionBtn = new JButton("Options");
		OptionBtn.setMinimumSize(buttonDim);
		OptionBtn.setMaximumSize(buttonDim);
		OptionBtn.addActionListener((e)->{
			optionMenu.setVisible(true);
		});
		//Öffnet die Credits
//		JButton credBtn = new JButton("Credits");
//		credBtn.setMinimumSize(buttonDim);
//		credBtn.setMaximumSize(buttonDim);
//		credBtn.addActionListener((e)->{
//			setTitle("Credits");
//		});
		//Öffnet den Highscore
//		JButton scoreBtn = new JButton("Highscore");
//		scoreBtn.setMinimumSize(buttonDim);
//		scoreBtn.setMaximumSize(buttonDim);
//		scoreBtn.addActionListener((e)->{
//			
//		});
		JButton closeBtn = new JButton("Close");
		closeBtn.setMinimumSize(buttonDim);
		closeBtn.setMaximumSize(buttonDim);
		closeBtn.addActionListener((e)->{
			System.exit(0);
		});
		
		Container pane = getContentPane();
		GroupLayout gl = new GroupLayout(pane);
		pane.setLayout(gl);
		gl.setAutoCreateContainerGaps(true);
		gl.setAutoCreateGaps(true);
		gl.setHorizontalGroup(gl.createParallelGroup(Alignment.CENTER)
				.addComponent(startBtn)
				.addGap(10)
				.addComponent(OptionBtn)
//				.addGap(10)
//				.addComponent(credBtn)
//				.addGap(10)
//				.addComponent(scoreBtn)
				.addComponent(closeBtn));
		gl.setVerticalGroup(gl.createSequentialGroup()
				.addComponent(startBtn)
				.addComponent(OptionBtn)
//				.addComponent(credBtn)
//				.addComponent(scoreBtn)
				.addComponent(closeBtn));
		
		
	}
		
		
//		setResizable(false);
		

	/*
	 * Hier wird die Width aller bildschirme berechent
	 */
	private void getMiddleXOfScreens() {
		GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
		GraphicsDevice[] devices = ge.getScreenDevices();
		for(int i = 0; i < devices.length;i++) {
			width = devices[i].getDisplayMode().getWidth();
			height += devices[i].getDisplayMode().getHeight();
		}
	}


	public static void main(String[] args) {
		EventQueue.invokeLater(()->{
			SnakeView game = new SnakeView();
			game.setVisible(true);
		});
	}
 
	public void changetitel(int Score, int level) {
		gameFrame.setTitle("Jetzt wird SnaPed" + "      \t Punkte: "+Score+"       \t Level: "+ level);
	}
	
	


}


	
	
