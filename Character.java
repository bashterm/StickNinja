public class Character {
	public int state;
	
	int[] velocity;
	int size;
	int[] position;
	int direction;

	int[,] animationStates;
	String name;

	double deltaTime = 0;
	double lastTime;
	double newTime;

	double[,] relativePartPositions;

	Animation[] myAnimations;

	int[,,] defaultPoses = new int[,,]{[[-10, -40], [-10, 0], [10, 40], [10, 0], [0, 0], [0, 40], [-13, 40], [-13, 0], [13, 40], [13, 0], [0, 45], [0, 60]]}
	public Character(int[] position, int size){
		/*for(int i = 0; i < animationStates[0].length; i++){
			myAnimations[i] = new Animation();//add hard coded animations here
		}*/
		myAnimations[0] = new Animation(new int[,,]);
		myAnimations[1] = new Animation(new int[[[0, 0.2, 0.6, 0.8],[0, 4, -4, 0]],[[0, 0.2, 0.6, 0.8],[0, -4, 4, 0]],[[0, 0.2, 0.6, 0.8],[0, 4, -4, 0]],[[0, 0.2, 0.6, 0.8],[0, -4, 4, 0]]]);

		this.position = position;
		this.size = size;

		this.velocity = new int[]{0,0};
		this.direction = 1;
		lastTime = System.currentTimeMillis();
		for(int i = 0; i < 12; i++){
			relativePartPositions[i] = defaultPoses[0][i];
		}
	}

	void Draw(){
		ModifyPartPositions();

		drawLine(relativePartPositions[0], relativePartPositions[1]);
		drawLine(relativePartPositions[2], relativePartPositions[3]);

		drawLine(relativePartPositions[1], relativePartPositions[3]);

		drawLine(relativePartPositions[4], relativePartPositions[5]);

		drawLine(relativePartPositions[6], relativePartPositions[7]);
		drawLine(relativePartPositions[8], relativePartPositions[9]);

		drawLine(relativePartPositions[6], relativePartPositions[8]);

		drawLine(relativePartPositions[5], relativePartPositions[10]);

		drawCircle(relativePartPositions[5], relativePartPositions[11]);
	}

	public void Update(){
		newTime = System.currentTimeMillis();
		deltaTime = newTime - lastTime;
		lastTime = newTimel
		animationStates[state][0] += deltaTime;
		if(animationStates[state][0] > animationState[1]){
			animationStates[state][0] = 0;
		}
		CheckForInput();

		Draw();
	}
	void CheckForInput(){
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
	void drawLine(int[] start, int[] end){

	}

	void drawCircle(int[] bottom, int[] center){

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

	class Animation {
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
}
