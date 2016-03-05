import java.applet.Applet;
import java.awt.BorderLayout;

import javax.swing.JFrame;

public class GameInitializer extends Applet {
	private Game game = new Game();

	@Override
	public void init(){
		setLayout(new BorderLayout());
        add(game, BorderLayout.CENTER);
        setMaximumSize(Game.WIDTH, Game.HEIGHT);
        setMinimumSize(Game.WIDTH, Game.HEIGHT);
        setPreferredSize(Game.WIDTH, Game.HEIGHT);
	}

	public static void main(String[] args) {
        game.setMinimumSize(Game.WIDTH, Game.HEIGHT);
        game.setMaximumSize(Game.WIDTH, Game.HEIGHT);
        game.setPreferredSize(Game.WIDTH, Game.HEIGHT);

        game.frame = new JFrame(Game.NAME);

        game.frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        game.frame.setLayout(new BorderLayout());

        game.frame.add(game, BorderLayout.CENTER);
        game.frame.pack();

        game.frame.setResizable(false);
        game.frame.setLocationRelativeTo(null);
        game.frame.setVisible(true);

        game.start();
    }
}
}