package snake;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.net.URL;
import java.util.ArrayList;
import java.util.Random;

import javax.swing.ImageIcon;

/*
 * 
 * @TODO:
 * 
 * Auslagern der randomom Zahl in eigene Methode 
 * Dieser wird die länge der jeweiligen Liste übergeben 
 * Diese Länge wird x10 genommen
 * und bei jedem 10ner Schritt wird ein anderes Element angesprochen
 */

/*
 * Hält alle Bilder für Snake 
 * Hier wird auch die Schnittstelle sein, ander man neue Bilder hineinLädt.
 */
public class Pictures {
	
	ArrayList<String> head;
	ArrayList<String> body;
	ArrayList<String> tail;
	
	String path;
	
	private ArrayList<ImageIcon> headList;
	private ArrayList<ImageIcon> bodyList;
	private ArrayList<ImageIcon> tailList;
	
	
	public Pictures()  {
		//DefaultListen 
//		path = new File("").getAbsolutePath()+"\\SnaPeNew_ALPHA_2.jar\\";
//		
//		headList = loadImg(getDefaultHeadList());
//		bodyList = loadImg(getDefaultBodyList());
//		tailList = loadImg(getDefaultTailList());
//		writePathTotxt();
//		
//		System.out.println(headList);
//		
//		
//		System.out.println(path);
//		try {
//			loadAllLists();
//		} catch (IOException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
	}
	
	/*
	 * Eigentlich sollten alle Namen in eine Liste geladen werden: kopf | body | tail
	 * 
	 * alles sollte eine eigene txt datei besitzen, wo die namen enthalten sind 
	 * Über diese Textdatei werden die jeweiligen Bilder der jeweiligen Liste hinzugefügt 
	 * 
	 */
	
	
	
	private void writePathTotxt() {
		String path1 = System.getProperty("user.home");
		   String path = path1+"//Snake/"; 
	        String fileName = "test.txt"; 
	        String dirName = "bums"; 
	        File file1 = new File(path+"/");
	        file1.mkdirs();
		
		
		
		FileWriter fw= null;
		
		try {
			fw = new FileWriter(new File(path+"\\body.txt"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			System.out.println("hier schreibe ich die daiten");
			for(ImageIcon s:bodyList) {
				System.out.println(s.toString());
				fw.write(s.toString()+"\r\n");
			}
			System.out.println("ende");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			fw.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	private ArrayList<ImageIcon> loadImg(ArrayList<String> pathList) {
		ArrayList<ImageIcon> defaultList = new ArrayList<>();
		for(String location:pathList) {
			defaultList.add(new ImageIcon(location));
		}
		return defaultList;
	}

	private void loadAllLists() throws IOException {
		
		
		
		//gehe durch kopfTxt datei und füge alle namen in eine liste 
		head = readList(head);
		System.out.println(head);
		//gehe durch bodyTxt datei und füge alle Namen in eine Liste 
		//gehe durch tailTxt datei und füe alle Namen in eine Liste
		
		/*
		 * Nachdem die Namen in einer String liste gespeichert wurden, 
		 * übergebe diese Liste an eine Methode, welche die diesbezügliche ArrayListe mit ImageIcon´s bestückt bzw vorläd 
		 * aus dieser liste wird dann jeweils ein Element ausgewürfelt 
		 * 
		 */
	}
	
	
	private ArrayList<String> getDefaultTailList() {
		ArrayList<String> defaultList = new ArrayList<>();
		defaultList.add(path+"pickleRick.gif");
		return defaultList;
	}


	private ArrayList<String> getDefaultHeadList() {
		ArrayList<String> defaultList = new ArrayList<>();
		defaultList.add(path+"pepeTrippy.gif");
		return defaultList;
	}


	private ArrayList<String> getDefaultBodyList() {
		ArrayList<String> defaultList = new ArrayList<>();
		defaultList.add(path+"happyPepe.gif");
		defaultList.add(path+"pepeAscii.gif");
		defaultList.add(path+"pepeDance.gif");
		defaultList.add(path+"pepejinny.gif");
		defaultList.add(path+"pepeSadDance.gif");
		defaultList.add(path+"pepeSweating.gif");
		return defaultList;
	}


	private ArrayList<String> readList(ArrayList<String> destination) throws IOException {
		ArrayList<String> gifList = new ArrayList<String>();
		System.out.println("aufgerufen...");
		File testFile = new File(path);
		File[] testfileAr = testFile.listFiles();
		for(File f: testfileAr) {
			/*
			 * 
			 * 	D:\lokale Daten\Resources\ das ist mein pfad den ich rausschneiden muss 
			 */
			if(f.toString().contains(".gif")) {
				String a = f.toString().substring(path.length(), f.toString().length());
				a = a.replace("\\", "");
				gifList.add(a);
			}
		}
//		String path = System.getProperty("user.home"+"\\"+destination+".txt");
		
//		try {
//			FileReader fr = new FileReader(new File(path));
//		} catch (FileNotFoundException e) {
//			//Der Pfad indem alle Dateien liegen
//			String thisPath = new File(".").getAbsolutePath();
//			FileReader fr2 = new FileReader(thisPath);
//			
//			FileWriter fw = new FileWriter(new File(thisPath+destination+".txt"));
//			
//		}
		
		return gifList;
	}


	private int getRandomNumber(int max) {
		
		Random rand = new Random();
		
		int randomNumber = rand.nextInt(max);
		System.out.println(randomNumber+"        "+ max);
		
		
		return randomNumber;
	}
	
	/*
	 * Wird Bald Obsolet -> Siehe Listen
	 * Hierüber wird ein zufällig ausgähltes Bild für den Schlangenkörper erhalten 
	 */
	public ImageIcon getRandomBodyPic() {

//		return chooseRandomPicOfList(bodyList);
		
		
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
	
	public ImageIcon getImageUrl(String name) {
		URL url = getClass().getClassLoader().getResource(name);
		return  new ImageIcon(url);
	}
	/*
	 * Hierüber erhält man ein Bild für den Kopf der Schlange
	 */
	public ImageIcon getHeadPic() {
		return getImageUrl("pepeTrippy.gif");
//		return chooseRandomPicOfList(headList);
		
		//Erhält eine zufällig ausgewählte zahl 
		//nimmt diese zahl x10 
		//sucht anhand dessen ein Bild aus der jeweiligen liste heraus 
	}
	/*
	 * Hierüber erhält man ein Bild für den Schwanz der Schlange
	 */
	public ImageIcon getTailPic() {
		return getImageUrl("pickleRick.gif");
//		return chooseRandomPicOfList(tailList);
	}
	/*
	 * Hier wird eine Zufällig erwürfelte Zahl und eine auswahlliste hingegeben
	 * Gibt dementsprechend ein Bild wieder (aus der liste ausgewählt)
	 */
	private ImageIcon chooseRandomPicOfList(ArrayList<ImageIcon> picList) {
		return picList.get(getRandomNumber(picList.size()));
	}
	
	/*
	 * Hier werden alle Bilder für das Tutorial übergeben
	 */
	public void getTutorialPics() {
		//holung der drei verschiedenen tutorial bilder 
		//Rückgabe dieser Bilder inform einem Array
	}
	/*
	 * Hier liegt das Ziel Bild (Tutorial)
	 */
	public ImageIcon getGameGoalPic() {
		return getImageUrl("gameZiel.png");
	}
	/*
	 * Hier liegt das BewegungsBild (Tutorial)
	 */
	public ImageIcon getMovementPic() {
		return getImageUrl("movementImage.png");
	}
	/*
	 * Hier liegt das Break Bild (Tutorial)
	 */
	public ImageIcon getBreakPic() {
		return getImageUrl("leertasteTutorial.png");
	}
	/*
	 * Hier wird man ein weiteres Bild für den Körper hinzufügen können
	 */
	public void addBodyPicToList() {
		
	}
	/*
	 * Hier wird man ein weiteres Bild für den Körper hinzufügen können
	 */
	public void addHeadPicToList() {
		
	}
	/*
	 * Hier wird man ein weiteres Bild für den Schwanz hinzufügen können 
	 */
	public void addTailPicToList() {
		
	}
	
}
