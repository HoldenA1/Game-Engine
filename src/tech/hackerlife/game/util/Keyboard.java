package tech.hackerlife.game.util;

import java.awt.event.*;
import tech.hackerlife.game.*;
import tech.hackerlife.game.menu.*;

public class Keyboard implements KeyListener {
	public static boolean[] keys = new boolean[200];
	public static boolean up, down, left, right, interact, escape, enter, sprint;

	static int mDown = KeyEvent.VK_DOWN, mUp = KeyEvent.VK_UP, select = KeyEvent.VK_ENTER, back = KeyEvent.VK_ESCAPE;

	public static void update() {
		up = keys[KeyEvent.VK_UP] || keys[KeyEvent.VK_W];
		down = keys[KeyEvent.VK_DOWN] || keys[KeyEvent.VK_S];
		left = keys[KeyEvent.VK_LEFT] || keys[KeyEvent.VK_A];
		right = keys[KeyEvent.VK_RIGHT] || keys[KeyEvent.VK_D];
		interact = keys[KeyEvent.VK_SPACE];
		enter = keys[KeyEvent.VK_ENTER];
		sprint = keys[KeyEvent.VK_SHIFT];
		escape = keys[KeyEvent.VK_ESCAPE];
	}

	public void keyPressed(KeyEvent e) {
		switch (Main.state) {
		case MAIN_MENU:
			menuControls(e);
			break;
		case GAME:
			keys[e.getKeyCode()] = true;
			break;
		}
		
	}
	
	public static void menuControls(KeyEvent e) {
		if (e.getKeyCode() == mUp)
			Selector.up();
		else if (e.getKeyCode() == mDown)
			Selector.down();
		else if (e.getKeyCode() == select)
			Selector.select();
		else if (e.getKeyCode() == back)
			Selector.back();
	}

	public void keyReleased(KeyEvent e) {
		if (Main.state.equals(GameState.GAME)) keys[e.getKeyCode()] = false;
	}

	public void keyTyped(KeyEvent e) {
	}

}