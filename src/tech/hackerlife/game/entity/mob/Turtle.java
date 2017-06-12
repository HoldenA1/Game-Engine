package tech.hackerlife.game.entity.mob;

import java.awt.*;
import tech.hackerlife.game.Game;

public class Turtle extends Mob {
	static String characterUp = "/textures/character/back.png",
			characterDown = "/textures/character/up.png",
			characterLeft = "/textures/character/left.png",
			characterRight = "/textures/character/right.png",
			characterUpWalk = "/textures/character/walkback.png",
			characterDownWalk = "/textures/character/walkup.png",
			characterLeftWalk = "/textures/character/walkleft.png",
			characterRightWalk = "/textures/character/walkright.png",
			characterUpWalk2 = "/textures/character/walkback2.png",
			characterDownWalk2 = "/textures/character/walkup2.png";
	
	AI ai = new AI();
	
	public Turtle() {
		super(new Point(0,0), characterUp, characterDown, characterLeft, characterRight, characterUpWalk, characterDownWalk, 
				characterLeftWalk, characterRightWalk, characterUpWalk2, characterDownWalk2);
	}
	
	public void update(Graphics g) {
		ai.updateAI();
		walk(Game.playerSpeed, ai.goLeft(), ai.goRight(), ai.goUp(), ai.goDown());
		render(g);
	}

}