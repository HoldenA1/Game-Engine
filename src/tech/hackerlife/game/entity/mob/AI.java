package tech.hackerlife.game.entity.mob;

import java.util.Random;

public class AI {
	Random random = new Random();
	int r, c = 0;
	
	public void updateAI() {
		if (c<0) {
			r = random.nextInt(4);
			c = random.nextInt(60) + 60;
		}
		c--;
	}

	public boolean goRight() {
		if(r==0)return true;
		else return false;
	}
	
	public boolean goLeft() {
		if(r==1)return true;
		else return false;
	}
	
	public boolean goUp() {
		if(r==2)return true;
		else return false;
	}
	
	public boolean goDown() {
		if(r==3)return true;
		else return false;
	}
	
}