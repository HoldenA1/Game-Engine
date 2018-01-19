package tech.hackerlife.game.menu;

import java.awt.*;
import tech.hackerlife.game.Main;
import tech.hackerlife.game.util.*;

public class MainMenuGUI {
	static Image background = Reader.loadBufferedImage("/menu/menuback.png");
	static Color textColor = new Color(0, 0, 0);
	public static MenuState menuState = MenuState.TITLE_SCREEN;

	public static void update() {
		switch (menuState) {
		case TITLE_SCREEN: 
			TitleScreen.update();
			break;
		case OPTIONS_MENU: break;
		}
	}
	
	public static void render(Graphics g) {
		g.drawImage(background, 0, 0, Main.WIDTH, Main.HEIGHT, null);
		g.setFont(new Font("Dialog", Font.BOLD, (int)(Main.scale * 60)));
		g.setColor(textColor);

		switch (menuState) {
		case TITLE_SCREEN: 
			TitleScreen.render(g);
			break;
		case OPTIONS_MENU: 
			OptionsMenu.render(g, textColor);
			break;
		}
	}

	protected static void draw(Graphics g, String str, int x, int y) {
		g.drawString(str, (int)(x * Main.scale), (int)(y * Main.scale));
	}
	
	protected static void draw(Graphics g, Image img, int x, int y, int width, int height) {
		g.drawImage(img, (int)(x * Main.scale), (int)(y * Main.scale), (int)(width * Main.scale), (int)(height * Main.scale), null);
	}
	
	protected static void drawRectangle(Graphics g, int x, int y, int width, int height) {
		g.drawRect((int)(x * Main.scale), (int)(y * Main.scale), (int)(width * Main.scale), (int)(height * Main.scale));
	}
	
	protected static void fillRectangle(Graphics g, int x, int y, int width, int height) {
		g.fillRect((int)(x * Main.scale), (int)(y * Main.scale), (int)(width * Main.scale), (int)(height * Main.scale));
	}

}