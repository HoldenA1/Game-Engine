package tech.hackerlife.game.util;

import java.awt.event.*;

import tech.hackerlife.game.Game;
import tech.hackerlife.game.GameState;
import tech.hackerlife.game.Main;
import tech.hackerlife.game.menu.Selector;

public class Mouse implements MouseListener, MouseMotionListener{
	
	private static int mouseX = -1;
	private static int mouseY = -1;
	private static int mouseB = -1;
	
	public static int getX() {
		return mouseX;
	}
	
	public static int getY() {
		return mouseY;
	}
	public static int getButton() {
		return mouseB;
	}

	public void mouseClicked(MouseEvent e) {

		
	}

	public void mouseEntered(MouseEvent e) {

		
	}

	public void mouseExited(MouseEvent e) {

		
	}

	public void mousePressed(MouseEvent e) {
		Selector.select();
		
	}

	public void mouseReleased(MouseEvent e) {
		mouseB = -1;
		
	}

	public void mouseDragged(MouseEvent e) {
		mouseX = e.getX();
		mouseY = e.getY();
	}

	public void mouseMoved(MouseEvent e) {
		mouseX = e.getX();
		mouseY = e.getY();
	}
	
//	private boolean onButton() {
//		
//	}

	public static void updateSelected() {
		if (Selector.mainMenu) {
			if (mouseX ) {
				Selector.selected = 0;
				Game.init();
				Main.state = GameState.GAME;
			} else if (selected == 1) {
				selected = 0;
				mainMenu = false;
				optionsMenu = true;
			} else if (selected == 2) {
				Main.exitProcedure();
			}
		} else if (optionsMenu) {
			if (selected == 0 && !resSel) {
				selected = 0;
				resSel = true;
			} else if (selected == 1 && !resSel) {
				windowedSelect();
			} else if (selected == 2 && !resSel) {
				Main.displayFPS = !Main.displayFPS;
			} else if (selected == 3 && !resSel) {
				selected = 0;
				optionsMenu = false;
				mainMenu = true;
			} else if (selected == 0 && resSel) {
				selected = 0;
				resSel = false;
			} else if (selected == 1 && resSel) {
				resSelect(0);
			} else if (selected == 2 && resSel) {
				resSelect(1);
			} else if (selected == 3 && resSel) {
				resSelect(2);
			} else if (selected == 4 && resSel) {
				resSelect(3);
			}
		}
	}

}