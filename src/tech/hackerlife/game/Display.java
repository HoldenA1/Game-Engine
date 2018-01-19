package tech.hackerlife.game;

import java.awt.*;
import javax.swing.*;
import tech.hackerlife.game.menu.*;

public class Display extends JPanel {
	private static final long serialVersionUID = 1L;

	int width, height;
	static int counter = 0, fps = 0;
	static long time = System.currentTimeMillis(), dTime;

	public Display(int _width, int _height) {
		this.width = _width;
		this.height = _height;
	}

	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		
		switch (Main.state) {
		case MAIN_MENU:
			MainMenuGUI.render(g);
			break;
		case LOADING:
			Game.loadingScreen(g);
			break;
		case GAME:
			Game.render(g);
			break;
		}
		
		if (Main.displayFPS) fpsCounter(g);
		
		repaint();
	}
	
	public static void fpsCounter(Graphics g) {
		counter++;
		dTime = System.currentTimeMillis();
		if (dTime - time > 1000) {
			fps = counter;
			counter = 0;
			time = System.currentTimeMillis();
		}
		g.setFont(new Font("Dialog", Font.BOLD, (Main.HEIGHT >> 4) / 3));
		g.setColor(Color.yellow);
		g.drawString("FPS: " + fps, 10, (Main.HEIGHT >> 4) / 3);
	}
}