package tech.hackerlife.game.menu;

import java.awt.*;
import tech.hackerlife.game.Main;
import tech.hackerlife.game.util.*;

public class MainMenuGUI {
	static Image background = Reader.loadBufferedImage("/menu/menuback.png");

	public static void update(Graphics g, int width, int height, Color textColor) {
		g.drawImage(background, 0, 0, width, height, null);
		g.setFont(new Font("Dialog", Font.BOLD, (int)(Main.scale * 60)));
		g.setColor(textColor);

		if (Selector.mainMenu)
			TitleScreen.display(g);
		else if (Selector.optionsMenu) {
			OptionsMenu.options(g, textColor);
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