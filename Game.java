import java.awt.Color;
import java.awt.Graphics;
import java.awt.*; //Imports everything under java.awt. * Is just a wildcard
import java.lang.Thread;

import javax.swing.*;
//class game with all aspects of canvas and some of runnable
public class Game extends Canvas implements Runnable {
	//I actually have no idea how to run this or whether it should extend Applet or Canvas
	//Probably should extend Applet because Canvas is for making guis using swing.
	boolean start;
	public Thread gameThread;

	Image sceneBackground = createImage(500, 500);
	Image sceneObjects = createImage(500, 500);

	Image defaultSceneObjects = createImage(500, 500);

	Graphics sceneGraphics;
	Graphics sceneObjectsGraphics;

	//LevelController currentLevel;
	//public Character myCharacter;
	public Image makeImageTransparent (Image image){
		for (int i = 0; i < image.getHeight(); i++) { //compiler says we need some sort of ImageObserver.
	        for (int j = 0; j < image.getWidth(); j++) { //compiler says same thing
	            image.setRGB(j, i, 0xFF000000);
	        }
	    }
    	return image;
	}

	/*public void init(){
		gameThread = new Thread(this, UpdateStuff);
		gameThread.start();
		setFrame(new JFrame(NAME));
		getFrame().setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		getFrame().setLayout(new BorderLayout());
		getFrame().add(this, BorderLayout.CENTER);
		getFrame().pack();
		getFrame().setResizable(false);
		getFrame().setLocationRelativeTo(null);
		getFrame().setVisible(true);

		CreateScene();
		//currentLevel = new LevelController(this);
	}*/
	@Override
	public void paint(Graphics g){
		DrawScene(g);
	}

	public void run(){
		while(true){
			Thread game = Thread.currentThread();
			if(game == gameThread){
				//currentLevel.update();
			}
			EditScene();
			repaint();
			try {
				gameThread.sleep(50);
			} catch (InteruptedException e){;}
		}
	}
	public synchronized void start(){
		gameThread = new Thread(this, UpdateStuff);
		gameThread.start();
		/*setFrame(new JFrame(NAME));
		getFrame().setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		getFrame().setLayout(new BorderLayout());
		getFrame().add(this, BorderLayout.CENTER);
		getFrame().pack();
		getFrame().setResizable(false);
		getFrame().setLocationRelativeTo(null);
		getFrame().setVisible(true);*/

		CreateScene();
	}
	public synchronized void stop(){
		
	}
	void CreateScene(){
		defaultSceneObjects = makeImageTransparent(defaultSceneObjects);
		sceneGraphics = sceneBackground.getGraphics();
		sceneGraphics.setColor(Color.black);
		for(int i = 0; i < 33; i++){
			sceneGraphics.drawLine(0, i * 500 / 33, 500, i * 500 / 33);
		}
		sceneGraphics.setColor(Color.red);
		sceneGraphics.drawLine(500 / 33, 0, 500 / 33, 500);
	}

	void DrawScene(Graphics g){
		g.drawImage(sceneBackground, 0, 0, this);
		g.drawImage(sceneObjects, 0, 0, this);
	}

	void EditScene(){
		sceneObjects = defaultSceneObjects;
		sceneObjectsGraphics = sceneObjects.getGraphics();
		sceneObjectsGraphics = LevelController.drawObject(sceneObjectsGraphics);
	}
}
