package tech.hackerlife.game.menu;

import java.util.ArrayList;

import tech.hackerlife.game.*;

public class Selector {

	public static int selected = 0;
	public static boolean resSel = false, optionsMenu = false, mainMenu = true;
	public static int[] array = getOrderedRes();
	static ArrayList<Integer> ar = new ArrayList<Integer>();
	
	public static int[] getOrderedRes() {
		ar = new ArrayList<Integer>();
		ar.add(2160);
		ar.add(1440);
		ar.add(1080);
		ar.add(720);
		ar.add(640);
		
		algorithm();
		
		int[] a = new int[ar.size()];
		for(int i = 0; i < ar.size(); i++) {
			a[i] = ar.get(i);
		}
		return a;
	}
	
	private static void algorithm() {
		for(int i = 0; i < ar.size(); i++) {
			if (ar.get(i) == Main.HEIGHT || Main.screenSize.getHeight() < ar.get(i)) {
				ar.remove(i);
				algorithm();
				break;
			}
		}
	}

	public static void up() {
		selected--;
		if (mainMenu) {
			if (selected < 0)
				selected = 2;
		} else if (optionsMenu) {
			if (resSel) {
				if (selected < 0)
					selected = array.length;
			} else {
				if (selected < 0)
					selected = 3;
			}
		}
	}

	public static void down() {
		selected++;
		if (mainMenu) {
			if (selected > 2)
				selected = 0;
		} else if (optionsMenu) {
			if (resSel) {
				if (selected > array.length)
					selected = 0;
			} else {
				if (selected > 3)
					selected = 0;
			}
		}
	}

	public static void back() {
		if (optionsMenu && resSel) {
			resSel = false;
			selected = 0;
		} else if (optionsMenu) {
			optionsMenu = false;
			mainMenu = true;
			selected = 0;
		}
	}

	public static void select() {
		if (mainMenu) {
			if (selected == 0) {
				selected = 0;
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

	public static void resSelect(int index) {
		selected = 0;
		if (Main.HEIGHT != array[index]) {
			Main.HEIGHT = array[index];
			Main.WIDTH = Main.HEIGHT * 16 / 9;
			array = getOrderedRes();
			Main.destroyWindow();
			Main.createWindow();
		}
		resSel = false;
	}

	public static void windowedSelect() {
		selected = 0;
		Main.decoration = !Main.decoration;
		Main.destroyWindow();
		Main.createWindow();
	}

}