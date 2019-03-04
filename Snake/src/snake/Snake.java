package snake;

import java.awt.Dimension;
import java.util.ArrayList;

public class Snake {
	
	public Snake() {
		createStartingSnake();
	}

	private ArrayList<SnakeObjekt> snake = new ArrayList<>();
	private Pictures pic = new Pictures();
	
	public void newSnakeBody() {
		this.snake.add(snake.size()-1,new SnakeObjekt(new Dimension(
				(int)snake.get(snake.size()-1).getPosition().getWidth()+50,
				(int)snake.get(snake.size()-1).getPosition().getHeight()+50), 
				pic.getRandomBodyPic()));
	}



	/*
	 * Hier wird die Anfangsschlange erzeugt 
	 * Mit Positionsangaben und Bildern 
	 * 
	 */
	private void createStartingSnake() {
		this.snake.add(new SnakeObjekt(new Dimension(500,500),pic.getHeadPic()));  	//Kopf
		this.snake.add(new SnakeObjekt(new Dimension(550,550),pic.getTailPic())); 	//Schwanz
	}
	
	public ArrayList<SnakeObjekt> getList(){
		return this.snake;
	}
	
	public void setList(ArrayList<SnakeObjekt> snake) {
		this.snake = snake;
	}
	
	public Dimension getHeadDim() {
		return new Dimension(this.snake.get(0).getPosition());
	}

}
