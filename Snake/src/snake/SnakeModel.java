package snake;

import java.awt.Container;
import java.awt.Dimension;
import java.awt.Frame;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import java.util.Random;
import javax.swing.GroupLayout;
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
	
	
	private JFrame optionFrame;
	private SnakeView view;
	private Snake snake = new Snake();
	private JFrame gameOver;
	private Frame[] frame;
	private int score;
	private boolean isPause;
	private JPanel playGround;
	private Food food;
	boolean isFood; 
	private String task; 
	private int sleepTimer; 
	private int level;
	private int[] levelTreshHold = {2,5,10,20,35,55,80,110,160,210,265,331};
	private JFrame parentFrame;
	private Music gameSound;
	private JFrame tutorialFrame;
	
	
	public SnakeModel(SnakeView parent) {
		OptionMenu om = new OptionMenu();
		optionFrame = om.getOptionMenu();
		this.view = parent;
		TutorialWindow tuto = new TutorialWindow();
		tutorialFrame = tuto.getTutorial();
		this.parentFrame = parent;
		parentFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		initGame(parent);
	}
	
	/*
	 * @TODO: 
	 * Alle Bilder in eigene Klasse unterbringen 
	 */
	
	private void resetGame() {
		this.snake = new Snake();
		gameSound.stopMusic();
		this.gameSound = new Music();
		this.level = 0;
		this.score = 0;
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
		playGround.setSize(1500,1000);
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
		score = -1;
		frame = parent.getFrames();
		isFood = false;
		setGameOver();
		tutorialFrame.setVisible(true);
		tutorialFrame.setAlwaysOnTop(true);
		tutorialFrame.toFront();
		startGame();
	}
	private void setGameInfos(int score) {
		try {
			setLevel();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	/*
	 * Zeichnet die Figuren dementsprechend ob etwas darauf liegt 
	 */
	private void doDrawing(Graphics g) {
		if(isPause || tutorialFrame.isVisible() || optionFrame.isVisible()) {
			if(!optionFrame.isVisible()) {
				repaint();
			}
			return;
		}else {
			ArrayList<SnakeObjekt> safetyCopy = snake.getList();
			view.changetitel(score, level);
			RenderingHints rh = new RenderingHints(RenderingHints.KEY_COLOR_RENDERING,RenderingHints.VALUE_COLOR_RENDER_QUALITY);
			super.paintComponent(g);
			try {
				Thread.sleep(sleepTimer);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			Graphics2D g2d = (Graphics2D) g;
			g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
//			g2d.setRenderingHints(rh);
			if(!isFood) {
				food = new Food(getFoodKoord());
				snake.newSnakeBody();
				isFood = true;
				setGameInfos(score++);
			}
			g2d.drawImage(food.getImage().getImage(), 
							(int)food.getPosition().getHeight(),
							(int) food.getPosition().getWidth(), 50, 50, null);
			
			for(int i = 0; i < snake.getList().size();i++) {
				int xSnake = (int) safetyCopy.get(i).getPosition().getHeight();
				int ySnake = (int) safetyCopy.get(i).getPosition().getWidth();
				if(i == 0) {
					g2d.drawImage(safetyCopy.get(i).getIcon().getImage(), xSnake, ySnake, 50, 50, null);
				}else if(i == safetyCopy.size()-1) {
					g2d.drawImage(safetyCopy.get(i).getIcon().getImage(), xSnake, ySnake, 50, 50, null);
				}else {
					g2d.drawImage(safetyCopy.get(i).getIcon().getImage(), xSnake, ySnake, 50, 50, null);
				}
			}
			
//			for(SnakeObjekt s: snakeList) {
//				s = snakeList.get(i);
//				int xSnake = (int)s.getPosition().getHeight();
//				int ySnake = (int)s.getPosition().getWidth();
//				
//				i++;
//			}
			
			isFood = kollisionDetection();	
			if(isCrashed(safetyCopy)) {
				//gehe aus Spiel heraus -> denn Game Over 
				playGround.setVisible(false);
				gameOver.setVisible(true);
				return;
			}else {
				movingSnake(snake.getList(), task);
				repaint();	
			}
		}		

	}
	/*
	 * Ändert das Level und damit alle GameEigenschaften, welche damit zusammenhängen
	 */
	private void  setLevel() throws InterruptedException {
			if(levelTreshHold[level] == score-1) {
				gameSound.playLvlUp();
				level++;
				parentFrame.setTitle("testos");
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
		}if(!optionFrame.isVisible()) {
			repaint();
		}
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
		case KeyEvent.VK_ESCAPE:
			optionFrame.setVisible(true);
				break;
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
	 * Gibt den Variablen startwerte 	
	 */
	public void startGame() {
		gameSound.backgroundSound(level);
		playGround.setVisible(true);
		score = 0;
		level = 0;
		sleepTimer = 150;
		setFocusable(true);
		task = "up";
		isFood = false;
		Graphics g = getGraphics();
		paintComponent(g);
	}
	/*
	 * Ist für die anfangsMusik zuständig
	 */
	/*
	 * Gibt die Positionslisten an Logik weiter -> erhält neu berechnete Werte zurück
	 */
	private void movingSnake(ArrayList<SnakeObjekt> snakeKoords,String task){
		SchlangenLogik brain = new SchlangenLogik(task);
		snake.setList(brain.changeKoord(snakeKoords));
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
		for(SnakeObjekt snake: snake.getList()) {
			if(snake.getPosition().equals(food)) {
				return getFoodKoord();
			}
		}
		return food;
	}
	/*
	 * Prüft ob eine Kollision mit food stattfindet
	 */
	private boolean kollisionDetection() {
		SchlangenLogik bigBrain = new SchlangenLogik();
		//gibt die koordinaten der schlange und vom food an die logik weiter 
		if(bigBrain.eatDaApple(snake.getHeadDim(), food.getPosition())) {
			gameSound.playFoodEaten();
			return false;
		}
		return true;
	}
}

