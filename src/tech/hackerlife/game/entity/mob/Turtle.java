package tech.hackerlife.game.entity.mob;

import java.awt.*;
import tech.hackerlife.game.Game;

public class Turtle extends Mob {
	static String characterUp = "/textures/entities/turtle/back.png",
			characterDown = "/textures/entities/turtle/up.png",
			characterLeft = "/textures/entities/turtle/left.png",
			characterRight = "/textures/entities/turtle/right.png",
			characterUpWalk = "/textures/entities/turtle/walkback.png",
			characterDownWalk = "/textures/entities/turtle/walkup.png",
			characterLeftWalk = "/textures/entities/turtle/walkleft.png",
			characterRightWalk = "/textures/entities/turtle/walkright.png",
			characterUpWalk2 = "/textures/entities/turtle/walkback2.png",
			characterDownWalk2 = "/textures/entities/turtle/walkup2.png",
			characterLeftWalk2 = "/textures/entities/turtle/walkleft2.png",
			characterRightWalk2 = "/textures/entities/turtle/walkright2.png";
	
	AI ai = new AI();
	
	public Turtle() {
		super(new Point(0,0), characterUp, characterDown, characterLeft, characterRight, characterUpWalk, characterDownWalk, 
				characterLeftWalk, characterRightWalk, characterUpWalk2, characterDownWalk2, characterLeftWalk2, characterRightWalk2, 2, 3);
	}
	
	public void update() {
		ai.updateAI();
		walk(Game.playerSpeed, ai.goLeft(), ai.goRight(), ai.goUp(), ai.goDown());
		loopMap();
	}

}