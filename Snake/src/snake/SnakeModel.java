package snake;

import java.awt.Container;
import java.awt.Dimension;
import java.awt.Frame;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.net.URL;
import java.util.ArrayList;
import java.util.Random;
import javax.swing.GroupLayout;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

/*
 * @TODO: 
 * Bilder auslagern -> Pictures 
 * Menü´s auslagern -> MainMenu  DENN dies ist nur die Spieleklasse kein Menu 
 */
@SuppressWarnings("serial")
public class SnakeModel extends JPanel implements KeyListener{
	
	private JFrame gameOver;
	private Frame[] frame;
	private int score;
	private boolean isPause;
	private JPanel playGround;
	private Food food;
	private ArrayList<SnakeObjekt> snakeList;
	boolean isFood; 
	private String task; 
	private int sleepTimer; 
	private int level;
	private int[] levelTreshHold = {2,5,10,20,35,55,80,110,160,210,265,331};
	private JFrame parentFrame;
	private Music gameSound;
	
	public SnakeModel(SnakeView parent) {
		this.parentFrame = parent;
		parentFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		initGame(parent);
	}
	
	/*
	 * @TODO: 
	 * GameSound aus Model rausnehmen und KOMPLETT in Music unterbringen
	 * Model ruft nur noch den jeweiligen Soundclip in music auf! 
	 */
	
	private void resetGame() {
		gameSound.stopMusic();
		this.gameSound = new Music();
		this.level = 0;
		this.score = 0;
		parentFrame.setTitle("Jetzt wird SnaPed1");
		startGame();
	}
	
	
	
	/*
	 * Bitte noch alles unter Done nachsortieren in "Blöcke" (logik aussehen etc) 
	 */

	// -------------------------------------- DONE ------------------------------------------------------
	
	
	/*
	 *Game Over Screen 
	 */
	private JFrame setGameOver() {
		gameOver = new JFrame("Game Over");
		Container pane = gameOver.getContentPane();
		GroupLayout gl = new GroupLayout(pane);
		pane.setLayout(gl);
		gameOver.setLocationRelativeTo(frame[0]);
		gameOver.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		gameOver.setVisible(false);
		JLabel gameOverText = new JLabel("        New Game?");
		//Setze "Spiele nochmal? ja/Nein" buttons hinein 
		JButton yesBtn = new JButton("Yes");
		yesBtn.addActionListener((e)->{
			gameOver.setVisible(false);
//			gameSound.close();
			resetGame();
		});
		JButton noBtn = new JButton("No");
		noBtn.addActionListener((e)->{
			parentFrame.dispose();
			System.exit(0);
		});
		gl.setAutoCreateContainerGaps(true);
		gl.setAutoCreateGaps(true);
		gl.setHorizontalGroup(gl.createParallelGroup()
						.addComponent(gameOverText)
						.addGap(50)
				.addGroup(gl.createSequentialGroup()
						.addComponent(yesBtn)
						.addComponent(noBtn)));
		gl.setVerticalGroup(gl.createSequentialGroup()
				.addGroup(gl.createSequentialGroup()
						.addComponent(gameOverText))
				.addGap(50)
				.addGroup(gl.createParallelGroup()
						.addComponent(yesBtn)
						.addComponent(noBtn)));
		gameOver.add(gameOverText);
		gameOver.pack();
		return gameOver;
	}
	
	
	private void configurePlayGround() {
		playGround = new JPanel();
		playGround.setSize(1500, 1025);
		addKeyListener(this);
	}
	/*
	 * Initialisiert startWerte
	 */
	@SuppressWarnings("static-access")
	private void initGame(SnakeView parent) {
		configurePlayGround();
		gameSound = new Music();
		sleepTimer = 150;
		isPause = false;
		score = 0;
		frame = parent.getFrames();
		isFood = false;
		setGameOver();
		startGame();
	}
	private void setGameInfos(int score) {
		try {
			setLevel();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		parentFrame.setTitle("Jetzt wird SnaPed"+ "\t Score: "+ score+"\t Level: "+ level);
	}
	/*
	 * Zeichnet die Figuren dementsprechend ob etwas darauf liegt 
	 */
	private void doDrawing(Graphics g) {
		
		if(isPause) {
			return;
		}else {
			RenderingHints rh = new RenderingHints(RenderingHints.KEY_COLOR_RENDERING,RenderingHints.VALUE_COLOR_RENDER_QUALITY);
			
			super.paintComponent(g);
			try {
				Thread.sleep(sleepTimer);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			Graphics2D g2d = (Graphics2D) g;
			g2d.setRenderingHints(rh);
			if(!isFood) {
				food = new Food(getFoodKoord());
				snakeList.add(snakeList.size()-1, new SnakeObjekt(
							  new Dimension((int) snakeList.get(snakeList.size()-1).getPosition().getWidth(),
									        (int) snakeList.get(snakeList.size()-1).getPosition().getHeight()), 
							  getBodyImage()));
				isFood = true;
				setGameInfos(score++);
			}
			g2d.drawImage(food.getImage().getImage(), (int)food.getPosition().getHeight(),(int) food.getPosition().getWidth(), 50, 50, null);
			for(int i =0 ; i < snakeList.size(); i++) {
				int xSnake = (int)snakeList.get(i).getPosition().getHeight();
				int ySnake = (int)snakeList.get(i).getPosition().getWidth();
				if(i == 0) {
					g2d.drawImage(snakeList.get(i).getIcon().getImage(), xSnake, ySnake, 50, 50, null);
				}else if(i == snakeList.size()-1) {
					g2d.drawImage(snakeList.get(i).getIcon().getImage(), xSnake, ySnake, 50, 50, null);
				}else {
					g2d.drawImage(snakeList.get(i).getIcon().getImage(), xSnake, ySnake, 50, 50, null);
				}
			}
		}		
		isFood = kollisionDetection(snakeList,food);
		ArrayList<SnakeObjekt> safetyCopy = snakeList;
		if(isCrashed(safetyCopy)) {
			//gehe aus Spiel heraus -> denn Game Over 
			playGround.setVisible(false);
			gameOver.setVisible(true);
			return;
		}else {
			snakeList = movingSnake(snakeList, task);
			repaint();	
		}
	}
	/*
	 * Ändert das Level und damit alle GameEigenschaften, welche damit zusammenhängen
	 */
	private void  setLevel() throws InterruptedException {
			if(levelTreshHold[level] == score-1) {
				gameSound.playLvlUp();
				level++;
				gameSound.backgroundSound(level);
				sleepTimer -=10;
		}
	}
	/*
	 * Prüft ob die Schlange sich selbst "frisst" oder außerhalb des Spielfeldes ist
	 */
	private boolean isCrashed(ArrayList<SnakeObjekt> safetyCopy) {
		//nehme kopfposition
		Dimension headDim = safetyCopy.get(0).getPosition();
		//Prüfe wie oft diese position in der Schlange vorhanden ist
		//prüfe alle positionen NACH kopf auf gleichheit
		for(int i = 1; i < safetyCopy.size();i++) {
			Dimension snakeBody = safetyCopy.get(i).getPosition();
			if((headDim.getHeight() == snakeBody.getHeight() && headDim.getWidth() == snakeBody.getWidth()) || 
				headDim.getHeight()<0||headDim.getWidth()<0 || 
				headDim.getHeight()>playGround.getWidth()-50 || 
				headDim.getWidth()>playGround.getHeight()-50) {
				//Hier ist man nun GameOver -> Schließe spielPanel und Öffne GameOver Panel 
				return true;
			}
		}
		return false;
	}
	
	/*
	 * Versetzt das Spiel in "Pause"
	 */
	private void pause() {
		if(!isPause) {
			isPause = true;
		}else if(isPause) {
			isPause = false;
		}
		repaint();
	}
	/*
	 * Hält die verschiedenen Soundtracks für die Geschwindigkeit 
	 */
	@Override
	public void keyTyped(KeyEvent e) {
	}
	@Override
	public void keyReleased(KeyEvent e) {
	}
	public void paintComponent(Graphics g) {
		doDrawing(g);
	}
	@Override
	public void keyPressed(KeyEvent e) {
		switch(e.getKeyCode()) {
		case KeyEvent.VK_SPACE:
			pause();
			break;
		case KeyEvent.VK_UP:
		case KeyEvent.VK_W:
			if(!task.contains("down")) {
				task ="up";
			}
			break;
		case KeyEvent.VK_RIGHT:
		case KeyEvent.VK_D:
			if(!task.contains("left")) {
				task ="right";
			}
			break;
		case KeyEvent.VK_DOWN:
		case KeyEvent.VK_S:
			if(!task.contains("up")) {
				task ="down";	
			}
			break;
		case KeyEvent.VK_LEFT:
		case KeyEvent.VK_A:
			if(!task.contains("right")) {
				task ="left";
			}
			break;
		}
	}
	
	 /*
	  * @TODO: Auslagern nach Pictures -> Hat im Model nichts zu suchen da logik enthalten ist -> hier wird nur das Bild,
	  * welches aus Pictures kommt dem jeweiligen Körperteil zugeordnet
	  *  Gibt ein zufälliges Gif zurück 
	  */
	private ImageIcon getBodyImage() {
		ImageIcon bodyPic;
		Random rand = new Random();
		int number = rand.nextInt(699)+1;
		if(number <=100) {
			bodyPic = (ImageIcon) getImageUrl("pepeAscii.gif");
		}else if(number <=200) {
			bodyPic =  (ImageIcon) getImageUrl("pepeDance.gif");
		}else if(number <=300) {
			bodyPic =  (ImageIcon) getImageUrl("pepejinny.gif");
		}else if(number<=400) {
			bodyPic = (ImageIcon) getImageUrl("pepeSadDance.gif");
		}else if(number<=500) {
			bodyPic = (ImageIcon) getImageUrl("pepeSweating.gif");
		}else {
			bodyPic = (ImageIcon) getImageUrl("happypepe.gif");
		}
		return bodyPic;
	}
	private Icon getImageUrl(String name) {
		URL url = getClass().getClassLoader().getResource(name);
		return  new ImageIcon(url);
	}
	/*
	 * Gibt den Variablen startwerte 	
	 */
	public void startGame() {
		gameSound.backgroundSound(level);
		playGround.setVisible(true);
		score = 0;
		level = 0;
		sleepTimer = 150;
		setFocusable(true);
		snakeList = new ArrayList<>();
		snakeList.add(new SnakeObjekt(new Dimension(500, 500),(ImageIcon) getImageUrl("pepeTrippy.gif"))); 
		snakeList.add(new SnakeObjekt(new Dimension(500+50, 500+50),(ImageIcon) getImageUrl("pickleRick.gif")));
		task = "up";
		isFood = false;
//		musikStart();
		repaint();
	}
	/*
	 * Ist für die anfangsMusik zuständig
	 */
	/*
	 * Gibt die Positionslisten an Logik weiter -> erhält neu berechnete Werte zurück
	 */
	private ArrayList<SnakeObjekt> movingSnake(ArrayList<SnakeObjekt> snakeKoords,String task){
		SchlangenLogik brain = new SchlangenLogik(task);
		ArrayList<SnakeObjekt> snakeKoordsNew = brain.changeKoord(snakeKoords);
		return snakeKoordsNew;
	}
	/*
	 * Gibt einen Zuäflligen Punkt im Frame zurück (Koord)
	 */
	private int randomStartingPoint(int max) {
		Random rand = new Random();
		int randomNumber = rand.nextInt(max);
		int rest = randomNumber%50;
		if(randomNumber-rest<=max-100) {
			return randomNumber-rest;
		}
		return randomStartingPoint(max);
	}
	/*
	 * Gibt neue Dimension für Food zurück
	 */
	private Dimension getFoodKoord() {
		Dimension food =new Dimension(randomStartingPoint(playGround.getHeight()), randomStartingPoint(playGround.getWidth()));
		for(SnakeObjekt snake: snakeList) {
			if(snake.getPosition().equals(food)) {
				return getFoodKoord();
			}
		}
		return food;
	}
	/*
	 * Prüft ob eine Kollision mit food stattfindet
	 */
	private boolean kollisionDetection(ArrayList<SnakeObjekt> snake, Food food) {
		SchlangenLogik bigBrain = new SchlangenLogik();
		//gibt die koordinaten der schlange und vom food an die logik weiter 
		if(bigBrain.eatDaApple(snake, food)) {
			gameSound.playFoodEaten();
			return false;
		}
		return true;
	}
}

