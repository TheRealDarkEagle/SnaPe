package snake;

import java.awt.Dimension;
import java.util.ArrayList;

public class SchlangenLogik {
	
	
	public SchlangenLogik(String task) {
		this.task = task;
	}
	public SchlangenLogik() {
	}
	private String task;
	
	/*
	 * Gibt die Position nach links an
	 */
	public int left(int xPosition) {
		return xPosition - 50;
	}
	/*
	 * Gibt die Position nach rechts an 
	 */
	private int right(int xPosition) {
		return xPosition+50;
	}
	/*
	 * gibt die Position nach unten an 
	 */
	private int up (int yPosition) {
		return yPosition - 50;
	}
	/*
	 * Gibt die Position nach oben an 
	 */
	private int down (int yPosition) {
		return yPosition+50;
	}
	/*
	 * Width zuerst 
	 * danach height
	 * was wird verändert? nur der Kopf bekommt eine völlig neue position 
	 * der rest übernimmt die position der vorherigen teile
	 */
	public ArrayList<SnakeObjekt> changeKoord(ArrayList<SnakeObjekt> snake) {
		
		ArrayList<SnakeObjekt> newSnakeKoords = new ArrayList<SnakeObjekt>();
		int oldWidth=0;
		int oldHeight=0;
		for(int i = 0; i < snake.size();i++) {
			//Besitzt die "alten" werte
			Dimension snakeDim = snake.get(i).getPosition();
			int width = snakeDim.width;
			int height = snakeDim.height;
			if(i == 0) {
				switch(task) {
				case "up":
					//y Achse wird angesprochen
					newSnakeKoords.add(new SnakeObjekt(new Dimension(up((int) snakeDim.getWidth()),(int) snakeDim.getHeight()),snake.get(i).getIcon()));
					break;
				case "down": 
					//y Achse wird angesprochen
					newSnakeKoords.add(new SnakeObjekt(new Dimension(down((int) snakeDim.getWidth()),(int) snakeDim.getHeight()), snake.get(i).getIcon()));
					break;
				case "left":
					//x Achse wird angesprochen
					newSnakeKoords.add(new SnakeObjekt(new Dimension((int)snakeDim.getWidth(),left((int) snakeDim.getHeight())),snake.get(i).getIcon()));
					break;
				case "right":
					//x achse wird angesprochen
					newSnakeKoords.add(new SnakeObjekt(new Dimension((int)snakeDim.getWidth(),right((int) snakeDim.getHeight())),snake.get(i).getIcon()));
				}	
			}else {
				newSnakeKoords.add(new SnakeObjekt(new Dimension(oldWidth, oldHeight),snake.get(i).getIcon()));
			}
			oldWidth = width;
			oldHeight = height;
		}
		return newSnakeKoords;
	}
	
	public boolean eatDaApple(Dimension snakeHead, Dimension food) {
		Dimension test = new Dimension(snakeHead);
		if((int)test.getHeight() == (int)food.getHeight() && 
		   (int)test.getWidth() == (int)food.getWidth()) {
			return true;
		}
		return false;
	}
	
	public boolean isCollision(ArrayList<SnakeObjekt> snake) {
		ArrayList<SnakeObjekt> copyOfSnake = snake;
		for(int i =1; i<copyOfSnake.size();i++) {
			Dimension snakeBody = copyOfSnake.get(i).getPosition();	
			if((snake.get(0).getPosition().getWidth()==snakeBody.getWidth() && snake.get(0).getPosition().getHeight()==snakeBody.getHeight()) || 
			   (snake.get(0).getPosition().getWidth()<=-1 || snake.get(0).getPosition().getWidth()>=1225) || 
			   (snake.get(0).getPosition().getHeight()<=-1 || snake.get(0).getPosition().getHeight()>=1225)) {
				return true;
			}
		}
		return false;
	}
}
