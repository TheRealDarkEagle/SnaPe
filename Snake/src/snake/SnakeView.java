package snake;

import java.awt.Container;
import java.awt.Dimension;
import java.awt.EventQueue;

import javax.swing.GroupLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
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
		initUI();
	}
	
	
	private static final long serialVersionUID = 1L;
	
	
	private void initUI() {
		Dimension buttonDim = new Dimension(100, 30);
		setTitle("Jetzt wird SnaPed");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLocation(2800, 300);
//		setLocation(getMiddleXOfScreens(), y);
		setTitle("Main Menu");
		setSize(145, 245);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		//StartBtn startet das Spiel
		JButton startBtn = new JButton("Start Game");
		startBtn.setMinimumSize(buttonDim);
		startBtn.setMaximumSize(buttonDim);
		startBtn.addActionListener((e)->{
			SnakeModel playGround = new SnakeModel(this);
			setVisible(false);
			JFrame gameFrame = new JFrame("Jetzt wird SnaPed");
			playGround.setSize(1500, 1025);
			gameFrame.add(playGround);
			gameFrame.setSize(1500, 1025);
			gameFrame.setVisible(true);
			gameFrame.setDefaultCloseOperation(EXIT_ON_CLOSE);
		});
		//Öffnet die Optionen
//		JButton OptionBtn = new JButton("Options");
//		OptionBtn.setMinimumSize(buttonDim);
//		OptionBtn.setMaximumSize(buttonDim);
//		OptionBtn.addActionListener((e)->{
//			setTitle("Option Menu");
//		});
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
//				.addComponent(OptionBtn)
//				.addGap(10)
//				.addComponent(credBtn)
//				.addGap(10)
//				.addComponent(scoreBtn)
				.addComponent(closeBtn));
		gl.setVerticalGroup(gl.createSequentialGroup()
				.addComponent(startBtn)
//				.addComponent(OptionBtn)
//				.addComponent(credBtn)
//				.addComponent(scoreBtn)
				.addComponent(closeBtn));
		
		
	}
		
		
//		setResizable(false);
		

	/*
	 * Hier wird die Width aller bildschirme berechent
	 */
//	private int getMiddleXOfScreens() {
//		GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
//		GraphicsDevice[] devices = ge.getScreenDevices();
//		int width;
//		for(GraphicsDevice d:devices) {
//			d.
//		}
//		return 0;
//	}


	public static void main(String[] args) {
		EventQueue.invokeLater(()->{
			SnakeView game = new SnakeView();
			game.setVisible(true);
		});
	}
 

	
	
	


}


	
	
