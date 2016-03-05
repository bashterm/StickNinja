import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.Random;

import javax.swing.*;

public class Game extends JFrame implements Runnable {
	boolean start;

	//LevelController currentLevel;
	//public Character myCharacter;
	final TextField redText = new TextField("0");
	public static void main(String[] args){
		add(redText);
		setLayout(new FlowLayout(FlowLayout.CENTER));
		setSize(450, 300);
		setLocationRelativeTo(null);
		setVisible(true);
		//currentLevel = new LevelController(this);
	}
}