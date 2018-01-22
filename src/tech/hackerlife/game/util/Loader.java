package tech.hackerlife.game.util;

import java.awt.*;
import tech.hackerlife.game.*;

public class Loader implements Runnable {	
	
	static long lastTime = System.currentTimeMillis();

	public void run() {
		Game.init();
	}
	
	public static void loadingScreen(Graphics g) {
		g.fillRect(0, 0, Main.WIDTH, Main.HEIGHT);
		g.setFont(new Font("Dialog", Font.BOLD, Main.HEIGHT / 24));
		g.setColor(Color.WHITE);
		if (System.currentTimeMillis() >= 2000 + lastTime) {
			g.drawString("Loading...", Main.WIDTH / 20, (int)(Main.HEIGHT * 0.9));
			lastTime = System.currentTimeMillis();
		} else if (System.currentTimeMillis() >= 1500 + lastTime) {
			g.drawString("Loading...", Main.WIDTH / 20, (int)(Main.HEIGHT * 0.9));
		} else if (System.currentTimeMillis() >= 1000 + lastTime) {
			g.drawString("Loading..", Main.WIDTH / 20, (int)(Main.HEIGHT * 0.9));
		} else if (System.currentTimeMillis() >= 500 + lastTime) {
			g.drawString("Loading.", Main.WIDTH / 20, (int)(Main.HEIGHT * 0.9));
		} else {
			g.drawString("Loading", Main.WIDTH / 20, (int)(Main.HEIGHT * 0.9));
		}
	}
}