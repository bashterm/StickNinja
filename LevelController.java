import java.util.*
import java.awt.Graphics;
public class levelController {
	public int level;
	Character character;
	List<Ball> balls = new List<Ball>();
	Game game;

	double deltaTime = 0;
	double lastTime;
	double newTime;

	public levelController(Game source){
		character = new Character([0,0], 1);
		game = source;
		game.myChracter = character;
	}

	void CheckCollisions(){
		for(int i = 0; i < balls.count(); i++){
			if(CheckPlayerOverlap(balls[i])){
				game.gameOver = true;
			}
			for(int j = i; j < balls.count(); j++){
				if(CheckOverlap(balls[i], balls[j])){
					CalculateBallCollisionOutput(balls[i], balls[j]);
				}
			}
		}
	}
	boolean CheckPlayerOverlap(Ball ball){
		boolean output;

		return output;
	}
	boolean CheckOverlap(Ball ball1, Ball ball2){
		boolean output;
		//could either compare distances to sum of radii or rotate world axis and subtract new x coordinates
		//The former is probably more efficient but I haven't checked
		return output;
	}
	void CalculateBallCollisionOutput(Ball ball1, Ball ball2){

	}

	public Image drawObjects(Graphics g){
		newTime = System.currentTimeMillis();
		deltaTime = newTime - lastTime;
		lastTime = newTime;

		character.Update(deltaTime);
		for(int i = 0; i < balls.count(); i++){
			balls[i].Update(deltaTime);
			g.setColor(balls[i].color);
			g.drawCenteredCircle(g,balls[i].position[0],balls[i].position[1],r)
		}
		g.setColor(Color.black);
		g = character.Draw(g);
		return g;
	}

	void drawCenteredCircle(Graphics g, int x, int y, int r) {
		x = x-(r/2);
		y = y-(r/2);
		g.fillOval(x,y,r,r);
	}

	private class Ball {
		public int[] position;
		public Color color;
		int[] velocity;
		double radius;
		double density;
		double mass;
		double[] momentum;
		double timeToWallCollision;
		int wallToBeHit;//0 for vertical wall, 1 for horizontal wall

		public Ball(int[] startPos, int[] startVel, double r, double d){
			positions = startPos;
			velocity = startVel;
			radius = r;
			density = d;
			mass = 4 / 3 * (3.1415926535) * r * r * r;
			momentum = [mass * velocity[0], mass * velocity[1]];
			color = (255 * density / 40, 255 * density / 40, 255 * density / 40);
		}

		public void Update(double deltaTime){
			position[0] += velocity[0] * deltaTime;
			position[1] += velocity[1] * deltaTime;
			timeToWallCollision -= deltaTime;
			if(timeToWallCollision <= 0){
				velocity[wallToBeHit] *= -1;
				ChangeVelocity(velocity);
			}
		}

		public void ChangeVelocity(int[] newVelocity){
			velocity = newVelocity;
			momentum = [mass * velocity[0], mass * velocity[1]];

			double yTime = velocity[1] > 0 ? 400 - pos[1]) / velocity[1] : (-pos[1]) / velocity[1];

			double xTime = velocity[0] > 0 ? 400 - pos[0]) / velocity[0] : (-pos[0]) / velocity[0];
			timeToWallCollision = xTime < yTime ? xTime : yTime;
			wallToBeHit = xTime < yTime ? 0 : 1;
			if(xTime == yTime){
				wallToBeHit = 2;
			}
		}
	}
}
