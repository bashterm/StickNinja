import java.awt.Graphics;
public class Character {
	public int state;
	//need to add another slot to everything for walking left or right because the character is drawn differently for each
	enum states = {Standing, Walking, Ducking, Rolling, Jumping, Diving, Landing};
	public boolean leftPressed;
	public boolean rightPressed;
	public boolean upPressed;
	public boolean downPressed;
	public boolean spacePressed;
	
	int[] velocity;
	int size;
	int[] position;
	int direction;

	int[,] animationStates;
	String name;

	//double deltaTime = 0;
	//double lastTime;
	//double newTime;

	double[,] relativePartPositions;

	Animation[] myAnimations;
	Controller myController;

	int[,,] defaultPoses = new int[,,]{[[-10, -40], [-10, 0], [10, 40], [10, 0], [0, 0], [0, 40], [-13, 40], [-13, 0], [13, 40], [13, 0], [0, 45], [0, 60]]}
	public Character(int[] position, int size){
		animationStates = [[0, 0],[0, 0.8]];

		myAnimations[0] = new Animation(new int[,,]);
		myAnimations[1] = new Animation(new int[[[0, 0.2, 0.6, 0.8],[0, 4, -4, 0]],[[0, 0.2, 0.6, 0.8],[0, -4, 4, 0]],[[0, 0.2, 0.6, 0.8],[0, 4, -4, 0]],[[0, 0.2, 0.6, 0.8],[0, -4, 4, 0]]]);

		myController = new Controller();
		this.position = position;
		this.size = size;

		this.velocity = new int[]{0,0};
		this.direction = 1;
		lastTime = System.currentTimeMillis();
		for(int i = 0; i < 12; i++){
			relativePartPositions[i] = defaultPoses[0][i];
		}
	}

	Image Draw(Graphics g){
		ModifyPartPositions();

		g = drawLine(g, relativePartPositions[0], relativePartPositions[1]);
		g = drawLine(g, relativePartPositions[2], relativePartPositions[3]);

		g = drawLine(g, relativePartPositions[1], relativePartPositions[3]);

		g = drawLine(g, relativePartPositions[4], relativePartPositions[5]);

		g = drawLine(g, relativePartPositions[6], relativePartPositions[7]);
		g = drawLine(g, relativePartPositions[8], relativePartPositions[9]);

		g = drawLine(g, relativePartPositions[6], relativePartPositions[8]);

		g = drawLine(g, relativePartPositions[5], relativePartPositions[10]);

		g = drawCenteredCircle(g, relativePartPositions[5], relativePartPositions[11]);
		return g;
	}
	public void Update(double deltaTime){
		//newTime = System.currentTimeMillis();
		//deltaTime = newTime - lastTime;
		//lastTime = newTime;
		animationStates[state][0] += deltaTime;
		if(animationStates[state][0] > animationState[1]){
			animationStates[state][0] = 0;
		}
		CheckForInput();

		//Draw();
	}
	void CheckForInput(){
		if(state != (int)states.Rolling && state !=(int)states.Landing){
			velocity[0] = 0;
		}

		if(myController.right && state != (int)states.Rolling && state != states.Landing && state != (int)states.Jumping){
			if(state != (int)states.Walking || direction == -1){
				state = (int)states.Walking;
				direction = 1;
				animationStates[state][0] = 0;
				for(int i = 0; i < 12; i++){
					relativePartPositions[i] = defaultPoses[state][i];
				}
			}
		} 
		if(myController.left && state != (int)states.Rolling && state != states.Landing && state != (int)states.Jumping){
			if(state != (int)states.Walking || direction == 1){
				state = (int)states.Walking;
				direction = -1;
				animationStates[state][0] = 0;
				for(int i = 0; i < 12; i++){
					relativePartPositions[i] = defaultPoses[state][i];
				}
			}
		} 
		if(myController.up && (state == (int)states.Diving || state == (int)states.Ducking){

		} 
		if(myController.down && state != (int)states.Landing && state != (int)states.Rolling && state != (int)states.Jumping){

		} 
		if(myController.space && canJump && state != (int)states.Diving && state != (int)states.Landing){
			
		}

		if(state == (int)states.Walking && !myController.left && !myController.right){
			state = 0;
			animationStates[state][0] = 0;
			for(int i = 0; i < 12; i++){
				relativePartPositions[i] = defaultPoses[state][i];
			}
		}
		/*if(right arrow){
			if(state != 1){
				animationStates[state][0] = 0;
				state = 1;
				direction = 1;
				for(int i = 0; i < 12; i++){
					relativePartPositions[i] = defaultPoses[state][i];
				}
			}
		} etc*/
	}
	Image drawLine(Graphics g, int[] start, int[] end){
		g.drawLine(start[0], start[1], end[0], end[1]);
		return g;
	}

	Image drawCenteredCircle(Graphics g, int[] center, int[] bottom]) {
		x = x-(center[0] - bottom[0]) / 2;
		y = y-(center[0] - bottom[0]) / 2;
		g.fillOval(center[0],center[1],r,r);
		return g;
	}

	void ModifyPartPositions(){
		relativePartPositions[0] += myAnimations[state].getOffset(0, animationStates[state][0]);
		relativePartPositions[2] += myAnimations[state].getOffset(1, animationStates[state][0]);

		relativePartPositions[7] += myAnimations[state].getOffset(2, animationStates[state][0]);
		relativePartPositions[9] += myAnimations[state].getOffset(3, animationStates[state][0]);

	}

	public void checkBallCollision(ballX, ballY, ballR, ballS){
		int[] relativeBallPosition = [ballX - position[0], ballY - position[1]];


	}
	private class Animation {
		//int[,] headTimesAndOffset;
		//int[,] neckTimesAndOffset;
		//int[,] shouldCenterTimesAndOffset;
		//int[,] LeftShoulderTimesAndOffset;
		//int[,] RightShoulderTimesAndOffset;
		int[,] LeftHandTimesAndOffset;
		int[,] RightHandTimesAndOffset;
		//int[,] TorsoTimesAndOffset;
		//int[,] LeftTorsoTimesAndOffset;
		//int[,] RightTorsoTimesAndOffset;
		int[,] LeftFootTimesAndOffset;
		int[,] RightFootTimesAndOffset;

		int[,,] partTimesAndOffset = int[,,]{LeftHandTimesAndOffset, RightHandTimesAndOffset, LeftFootTimesAndOffset, RightFootTimesAndOffset};

		public Animation(int[,,] givenTimesAndOffsets){
			for(int i = 0; i < givenTimesAndOffsets.length; i++){
				partTimesAndOffset[i] = givenTimesAndOffsets[i];
			}
		};

		public void changeState(int newState){
			currentRangeAndTimes = partRangeAndTimes[newState];
		}
		public double getOffset(int part, double time){
			double output = 0;
			int[] temp = partTimesAndOffset[part];
			for(int i = 0; i < temp[0].length; i++){
				if(temp[0][i] > time){
					output = temp[1][i - 1] + (temp[1][i] - temp[1][i - 1]) * (time - temp[0][i - 1]) / (temp[0][i] - temp[0][i - 1]);
					break;
				}
			}
			return output;
		}
	}

	private class Controller extends KeyAdapter {
        boolean up, down, left, right, space;

        public void keyPressed(KeyEvent event) {
            switch (event.getKeyCode()) {
            case KeyEvent.VK_UP:
            	up = true;
                break;
            case KeyEvent.VK_DOWN:
            	down = true;
                break;
            case KeyEvent.VK_LEFT:
            	left = true;
                break;
            case KeyEvent.VK_RIGHT:
            	right = true;
                break;
            case KeyEvent.VK_SPACE:
                space = true;
                break;
            default:
            }
        }
        
        public void keyReleased(KeyEvent event) {
            switch (event.getKeyCode()) {
            case KeyEvent.VK_W:
                up = false;
                break;
            case KeyEvent.VK_S:
                down = false;
                break;
            case KeyEvent.VK_A:
                left = false;
                break;
            case KeyEvent.VK_D:
                right = false;
                break;
            case KeyEvent.VK_SPACE:
                space = false;
                break;
            default:
            }
        }
    }
}