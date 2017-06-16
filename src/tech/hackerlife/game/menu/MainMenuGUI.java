package tech.hackerlife.game.menu;

import java.awt.*;
import tech.hackerlife.game.Main;
import tech.hackerlife.game.util.*;

public class MainMenuGUI {
	static int temp;
	static double floater = 0, d = (Main.HEIGHT >> 4) / 337.5;
	static String curH, h2, h3, h4, h5;

	static Image background = Reader.loadBufferedImage("/menu/menuback.png");
	static Image title = Reader.loadBufferedImage("/menu/title.png");

	public static void update(Graphics g, int width, int height, Color textColor) {
		Mouse.updateSelected();
		
		g.drawImage(background, 0, 0, width, height, null);
		g.setFont(new Font("Dialog", Font.BOLD, (height >> 3) / 3));
		g.setColor(textColor);

		if (Selector.mainMenu)
			mainMenu(g, width, height);
		else if (Selector.optionsMenu) {
			// called if the resolution selector is chosen
			if (Selector.resSel)
				OptionsMenu.resOpen(g, width, height, textColor);
			// default options menu
			else
				OptionsMenu.options(g, width, height, textColor);
		}
	}

	public static void mainMenu(Graphics g, int width, int height) {
		temp = (int) (width * 0.265);
		g.drawImage(title, temp, (height / 2) + (int) floater, width / 2, height / 6, null);
		if (floater < -((height >> 2) / 27))
			d = (double) (height >> 4) / 337.5;
		else if (floater > (height >> 2) / 27)
			d = (double) -((height  >> 4) / 337.5);
		floater += d;

		int mem = (width >> 2) / 5;
		g.drawString("PLAY", mem, height / 12);

		g.drawString("OPTIONS", mem, (height >> 1) / 3);

		g.drawString("QUIT", mem, height >> 2);

		if (Selector.selected == 0)
			g.drawString("<<<", width >> 3, height / 12);
		else if (Selector.selected == 1)
			g.drawString("<<<", width / 6, height / 6);
		else if (Selector.selected == 2)
			g.drawString("<<<", width / 9, height >> 2);
	}

}