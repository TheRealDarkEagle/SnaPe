package snake;

import java.awt.Container;
import java.awt.Dimension;
import java.awt.EventQueue;

import javax.swing.GroupLayout;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JSlider;


/*
 * Hier sollen alle mögliche änderbare Optionen liegen 
 * -----------------------------------------------------------
 * | 														 |
 * | 				    OPTION MENÜ							 |
 * | 														 |
 * | 														 |
 * | 	Gif´s anzeigen: 			on/off					 |
 * | 		50												 |
 * | 	Lautstärke											 |
 * | 	|	|	|-> Gesamt:			*schieberegler*		 	 |
 * | 	|	|		10										 |
 * | 	|	|-> Effekte:			*schieberegler*			 |
 * | 	|		10											 |
 * | 	|->	Musik:					*schieberegler*			 |
 * | 		50												 |
 * | 	Eigene Gif´s implementiere:		---------			 |
 * | 	|		|		|->	kopf		|*button*|			 |
 * | 	|		|		30				---------			 |
 * | 	|		|						---------			 |
 * | 	|		|->	body				|*button*|			 |
 * | 	|		30						---------			 |
 * | 	|								---------			 |
 * | 	|->	tail						|*button*|			 |
 * | 									---------			 |
 * | 			80											 |
 * | 			   ---------------							 |
 * | 			   |Show Tutorial|							 |
 * | 			   ---------------							 |
 * | 					30				5					 |
 * | 						   --------	 ----------			 |
 * | 						   | save |  | cancel |			 |
 * | 						   --------	 ----------			 |
 * -----------------------------------------------------------
 * 
 * Jede Option wird wird durch drücken des Save button 
 * in eine txt datei geschrieben, welche beim starten des spieles gelesen wird 
 * 
 * Diese datei wird default folgende Informationen halten:
 * 
 * showTuto			-> true;
 * showGif 			-> true;
 * Volume:
 * 		fullVolume 	-> 100%;
 * 	  effekt 		-> 100%;
 * 	music 			-> 100%;
 * 
 * die gif implementierung wird die Klasse Pictures ansprechen, somit wird nichts davon in der 
 * txt datei benötigt. 
 * 
 * Das Tutorial Fenster wird abstellbar sein (allerdings wird dies im Tutorial selbst einstellbar sein,
 * jedoch wird die information in der option.txt Datei gespeichert werden
 * 
 * tl;dr:
 *  		7 Buttons
 *  		3 Regler
 * 
 */

/*
 * @TODO:
 * BUGFIXES
 * 
 * 
 * Cancel schließt noch das gesamte Spiel
 */

public class OptionMenu extends JFrame{

	private boolean showGif = true;
	private int fullVol;
	private int effektVol;
	private int soundVol;
	
	public OptionMenu() {
		init();
	}
	
	public void init() {
		JButton changeGif = new JButton("Deaktivieren");
		Dimension smallBtnDim = new Dimension(50,30);
		Dimension bigBtnDim = new Dimension(75,30);
		Dimension LabelDim = new Dimension(250,50);
		//Erstellen der Swing Elemente
		JButton gifOnBtn = new JButton("ON");
		JButton gifOffBtn = new JButton("OFF");
		JButton implGifHeadBtn = new JButton("Auswahl");
		JButton implGifBodyBtn = new JButton("Auswahl");
		JButton implGifTailBtn = new JButton("Auswahl");
		JButton showTuto = new JButton("Show Tutorial");
		JButton saveBtn = new JButton("Save");
		JButton cancelBtn= new JButton("Cancel");
		JSlider fullVolSlider = new JSlider(0, 50, 50);
		JSlider effektVolSlider = new JSlider(0, 50, 50);
		JSlider soundVolSlider = new JSlider(0, 50, 50);
		JLabel showTutoLabel = new JLabel("Show Tutorial");
		JLabel showGifLabel = new JLabel("Gif´s anzeigen");
		JLabel fullVolLabel = new JLabel("Gesamtlautstärke");
		JLabel effektVolLabel = new JLabel("Effektlautstärke");
		JLabel musikVolLabel = new JLabel("Musiklautstärke");
		JLabel importHeadLbl = new JLabel("Import Head Bilder");
		JLabel importBodyLbl = new JLabel("Import Body Bilder");
		JLabel importTailLbl = new JLabel("Import Tail Bilder");
		changeGif.addActionListener((e)->{
			if(showGif) {
				changeGif.setText("Aktivieren");
				showGif= false;
			}else {
				changeGif.setText("Deaktivieren");
				showGif = true;
			}
		});
		//Funktionalität erstellen der Buttons
		gifOnBtn.addActionListener((e)->{
			showGif= true;
		});
		gifOffBtn.addActionListener((e)->{
			showGif = false;
		});
		implGifHeadBtn.addActionListener((e)->{
			//Hier soll sich die FileStruktur des Users Öffnen, um zur richtigen Datei navigieren zu können.
		});
		implGifBodyBtn.addActionListener((e)->{
			//Hier soll sich die FileStruktur des Users Öffnen, um zur richtigen Datei navigieren zu können.
		});
		implGifTailBtn.addActionListener((e)->{
			//Hier soll sich die FileStruktur des Users Öffnen, um zur richtigen Datei navigieren zu können.
		});
		showTuto.addActionListener((e)->{
			TutorialWindow tutoWindow = new TutorialWindow();
			JFrame tutoFrame = tutoWindow.getTutorial();
			tutoFrame.setVisible(true);
			//Zeige tutoWindow an 
			
		});
		saveBtn.addActionListener((e)->{
			//Speichert die neuen Einstellung in vorhandener txt Datei
			
			fullVol = fullVolSlider.getValue();
			effektVol = effektVolSlider.getValue();
			soundVol = soundVolSlider.getValue();
			System.out.println(fullVol);
			System.out.println(effektVol);
			System.out.println(soundVol);
			System.out.println(showGif);
			
		});
		cancelBtn.addActionListener((e)->{
			//Schließt einfach nur das Fenster
			this.dispose();
		});
		fullVolSlider.setToolTipText("Gesamtlautstärke");
		fullVolSlider.setMajorTickSpacing(10);
		fullVolSlider.setMinorTickSpacing(1);
		fullVolSlider.setSnapToTicks(true);
		fullVolSlider.setSize(60, 40);
		
		effektVolSlider.setToolTipText("Effektlautstärke");
		effektVolSlider.setMajorTickSpacing(10);
		effektVolSlider.setMinorTickSpacing(1);
		effektVolSlider.setSnapToTicks(true);
		effektVolSlider.setSize(60, 40);

		soundVolSlider.setToolTipText("Musiklautstärke");
		soundVolSlider.setMajorTickSpacing(10);
		soundVolSlider.setMinorTickSpacing(1);
		soundVolSlider.setSnapToTicks(true);
		soundVolSlider.setSize(60, 40);
		
		placeComponents(changeGif,gifOffBtn,																//Gif buttons
						implGifHeadBtn,implGifBodyBtn,implGifTailBtn,										//Implement buttons
						showTuto,saveBtn,cancelBtn,															//tuto,save,cancel buttons
						fullVolSlider,effektVolSlider,soundVolSlider,										//slider
						showTutoLabel, showGifLabel, fullVolLabel, effektVolLabel, musikVolLabel,			//label
						importHeadLbl,importBodyLbl,importTailLbl);		
	
		setTitle("Option Menü");
		setSize(550, 530);
		setDefaultCloseOperation(this.HIDE_ON_CLOSE);
		setLocationRelativeTo(null);
	}
	/*
	 * 0.gifOnBtn,
	 * 1.gifOffBtn,
	 * 2.implGifHead,
	 * 3.implGifBody,
	 * 4.implGifTail,
	 * 5.showTuto,
	 * 6.saveBtn,
	 * 7.cancelBtn,
	 * 8.fullVolSlider,
	 * 9.effektVolSlider,
	 * 10.soundVolSlider,
	 * 11. showTutoLabel
	 * 12.showGifLabel, 
	 * 13.fullVolLabel, 
	 * 14.effektVolLabel, 
	 * 15.musikVolLabel
	 * 16.importHeadLbl
	 * 17.importBodyLbl
	 * 18.importTailLbl
	 */
	private void placeComponents(JComponent... arg) {
		Container pane = getContentPane();
		GroupLayout gl = new GroupLayout(pane);
		pane.setLayout(gl);
		
		gl.setHorizontalGroup(gl.createSequentialGroup()					//Zwei Gruppen Sequenziell bei horizontal
				.addGap(8)					
				.addGroup(gl.createParallelGroup()							//erste Gruppe paralell angeordnet (Labels)
						.addComponent(arg[12])								
						.addComponent(arg[13])								
						.addComponent(arg[14])
						.addComponent(arg[15])
						.addComponent(arg[16])
						.addComponent(arg[17])
						.addComponent(arg[18]))
				.addGap(60)
				.addComponent(arg[5])
				.addGap(60)
				.addGroup(gl.createParallelGroup()						//zweite Gruppe buttons und slider
						.addGap(50)
						.addComponent(arg[0])
						.addComponent(arg[8])
						.addComponent(arg[9])
						.addComponent(arg[10])
						.addComponent(arg[2])
						.addComponent(arg[3])
						.addComponent(arg[4])
						.addGroup(gl.createSequentialGroup()
						.addComponent(arg[6])
						.addGap(10)
						.addComponent(arg[7]))));
		
		gl.setVerticalGroup(gl.createParallelGroup()
				.addGroup(gl.createSequentialGroup()
						.addGap(10)
						.addComponent(arg[12])
						.addGap(50)
						.addComponent(arg[13])
						.addGap(10)
						.addComponent(arg[14])
						.addGap(10)
						.addComponent(arg[15])
						.addGap(50)
						.addComponent(arg[16])
						.addGap(20)
						.addComponent(arg[17])
						.addGap(20)
						.addComponent(arg[18])
						.addGap(100)
						.addComponent(arg[5]))
				.addGap(200)
				.addGroup(gl.createSequentialGroup()
						.addGap(5)
						.addComponent(arg[0])
						.addGap(44)
						.addComponent(arg[8])
						.addGap(11)
						.addComponent(arg[9])
						.addGap(11)
						.addComponent(arg[10])
						.addGap(42)
						.addComponent(arg[2])
						.addGap(10)
						.addComponent(arg[3])
						.addGap(10)
						.addComponent(arg[4])
						.addGap(150)
						.addGroup(gl.createParallelGroup()
						.addComponent(arg[6])
						.addComponent(arg[7]))));
	}
	
	public JFrame getOptionMenu() {
		return this;
	}
	
}
