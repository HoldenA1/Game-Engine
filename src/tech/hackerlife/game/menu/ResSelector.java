package tech.hackerlife.game.menu;

import java.util.ArrayList;
import tech.hackerlife.game.*;

public class ResSelector {
	static ArrayList<Integer> array = new ArrayList<Integer>();
	
	public static int[] getOrderedRes() {
		array = new ArrayList<Integer>();
		array.add(2160);
		array.add(1440);
		array.add(1080);
		array.add(720);
		array.add(640);
		
		algorithm();
		
		int[] a = new int[array.size()];
		for(int i = 0; i < array.size(); i++) {
			a[i] = array.get(i);
		}
		return a;
	}
	
	public static void algorithm() {
		for(int i = 0; i < array.size(); i++) {
			if (array.get(i) == Main.HEIGHT || Main.screenSize.getHeight() < array.get(i)) {
				array.remove(i);
				algorithm();
				break;
			}
		}
	}

}
