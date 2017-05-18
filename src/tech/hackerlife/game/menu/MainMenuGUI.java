package tech.hackerlife.game.menu;

import java.awt.*;
import tech.hackerlife.game.Main;
import tech.hackerlife.game.util.Reader;

public class MainMenuGUI {
	static int temp, tempX, tempY, tempZ, color = 255;
	static double floater = 0, d = (double) (Main.HEIGHT / 54) / 100;
	static String curH, h2, h3, h4, h5;

	static Color boxColor = new Color(color, color, color, 100);

	static Image background = Reader.loadBufferedImage("/menu/menuback.png");
	static Image check = Reader.loadBufferedImage("/menu/check.png");
	static Image title = Reader.loadBufferedImage("/menu/title.png");

	public static void update(Graphics g, int width, int height, Color textColor) {
		g.drawImage(background, 0, 0, width, height, null);
		g.setFont(new Font("Dialog", Font.BOLD, height / 24));
		g.setColor(textColor);

		if (Selector.mainMenu)
			mainMenu(g, width, height);
		else if (Selector.optionsMenu) {
			// called if the resolution selector is chosen
			if (Selector.resSel)
				resOpen(g, width, height, textColor);
			// default options menu
			else
				options(g, width, height, textColor);
		}
	}

	public static void mainMenu(Graphics g, int width, int height) {
		temp = (int) (width * 0.265);
		g.drawImage(title, temp, (height / 2) + (int) floater, width / 2, height / 6, null);
		if (floater < -(height / 108))
			d = (double) (height / 54) / 100;
		else if (floater > height / 108)
			d = (double) -(height / 54) / 100;
		floater += d;

		g.drawString("PLAY", width / 20, height / 12);

		g.drawString("OPTIONS", width / 20, height / 6);

		g.drawString("QUIT", width / 20, height / 4);

		if (Selector.selected == 0)
			g.drawString("<<<", width / 8, height / 12);
		else if (Selector.selected == 1)
			g.drawString("<<<", width / 6, height / 6);
		else if (Selector.selected == 2)
			g.drawString("<<<", width / 9, height / 4);
	}

	public static void options(Graphics g, int width, int height, Color textColor) {
		// draws option settings
		g.drawString("RESOLUTION: ", width / 20, height / 12);
		g.drawString("WINDOWED: ", width / 20, height / 6);
		g.drawString("FPS COUNTER: ", width / 20, height / 4);

		// inside boxes
		g.setColor(boxColor);
		tempX = (int) (width * 0.22);
		g.fillRect(tempX, height / 20, width / 7, height / 20);
		g.fillRect(tempX, height / 8, height / 20, height / 20);
		//fps box inside
		tempX = (int) (width * 0.23);
		tempY = (int) (height * 0.21);
		g.fillRect(tempX, tempY, height / 20, height / 20);
		// draws boxes
		g.setColor(textColor);
		tempX = (int) (width * 0.22);
		// windowed box
		g.drawRect(tempX, height / 8, height / 20, height / 20);
		// res box
		g.drawRect(tempX, height / 20, width / 7, height / 20);
		// fps box
		tempX = (int) (width * 0.23);
		tempY = (int) (height * 0.21);
		g.drawRect(tempX, tempY, height / 20, height / 20);

		// draws resolution number
		g.setColor(textColor);
		curH = Integer.toString(Main.HEIGHT);
		tempX = (int) (width * 0.25);
		tempY = (int) (height * 0.09);
		g.drawString(curH, tempX, tempY);

		tempX = (int) (width * 0.22);
		if (!Main.decoration) {
			tempX = (int) (width * 0.22);
			g.drawImage(check, tempX, height / 8, height / 20, height / 20, null);
		}
		if (Main.displayFPS) {
			tempX = (int) (width * 0.23);
			tempY = (int) (height * 0.21);
			g.drawImage(check, tempX, tempY, height / 20, height / 20, null);
		}

		// draws back button
		tempY = (int) (height * 0.9);
		g.setColor(textColor);
		g.drawString("BACK", width / 20, tempY);

		// draws selector arrows
		if (Selector.selected == 0) {
			tempX = (int) (width * 0.37);
			g.drawString("<<<", tempX, height / 12);
		} else if (Selector.selected == 1) {
			tempX = (int) (width * 0.26);
			g.drawString("<<<", tempX, height / 6);
		} else if (Selector.selected == 2) {
			tempX = (int) (width * 0.27);
			g.drawString("<<<", tempX, height / 4);
		} else if (Selector.selected == 3)
			g.drawString("<<<", width / 8, tempY);
	}

	public static void resOpen(Graphics g, int width, int height, Color textColor) {
		g.drawString(">>> RESOLUTION: ", width / 20, height / 12);
		g.drawString("WINDOWED: ", width / 20, height / 6);
		g.drawString("FPS COUNTER: ", width / 20, height / 4);

		// draws windowed check box
		tempX = (int) (width * 0.22);
		g.setColor(boxColor);
		g.fillRect(tempX, height / 8, height / 20, height / 20);
		g.setColor(textColor);
		g.drawRect(tempX, height / 8, height / 20, height / 20);
		if (!Main.decoration)
			g.drawImage(check, tempX, height / 8, height / 20, height / 20, null);
		//fps box
		g.setColor(boxColor);
		tempX = (int) (width * 0.23);
		tempY = (int) (height * 0.21);
		g.fillRect(tempX, tempY, height / 20, height / 20);
		g.setColor(textColor);
		g.drawRect(tempX, tempY, height / 20, height / 20);
		g.setColor(boxColor);
		if (Main.displayFPS) {
			tempX = (int) (width * 0.23);
			tempY = (int) (height * 0.21);
			g.drawImage(check, tempX, tempY, height / 20, height / 20, null);
		}

		// this draws the outside selector box
		tempX = (int) (width * 0.26);
		if (Selector.array.length >= 4) {
			tempY = (int) (height * 0.26);
			g.fillRect(tempX, height / 20, width / 10, tempY);
		} else if (Selector.array.length >= 3) {
			tempY = (int) (height * 0.21);
			g.fillRect(tempX, height / 20, width / 10, tempY);
		} else if (Selector.array.length >= 2) {
			tempY = (int) (height * 0.16);
			g.fillRect(tempX, height / 20, width / 10, tempY);
		} else if (Selector.array.length >= 1) {
			tempY = (int) (height * 0.11);
			g.fillRect(tempX, height / 20, width / 10, tempY);
		} else {
			tempY = (int) (height * 0.05);
			g.fillRect(tempX, height / 20, width / 10, tempY);
		}

		// draws the first and selected resolution
		g.setColor(textColor);
		curH = Integer.toString(Main.HEIGHT);
		tempX = (int) (width * 0.28);
		tempY = (int) (height * 0.09);
		g.drawString(curH, tempX, tempY);

		// draws the other resolutions
		if (Selector.array.length >= 1) {
			h2 = Integer.toString(Selector.array[0]);
			tempY = (int) (height * 0.15);
			g.drawString(h2, tempX, tempY);
		}
		if (Selector.array.length >= 2) {
			h3 = Integer.toString(Selector.array[1]);
			tempY = (int) (height * 0.2);
			g.drawString(h3, tempX, tempY);
		}
		if (Selector.array.length >= 3) {
			h4 = Integer.toString(Selector.array[2]);
			tempY = (int) (height * 0.25);
			g.drawString(h4, tempX, tempY);
		}
		if (Selector.array.length >= 4) {
			h5 = Integer.toString(Selector.array[3]);
			tempY = (int) (height * 0.3);
			g.drawString(h5, tempX, tempY);
		}

		// draws back button
		tempX = (int) (height * 0.9);
		g.drawString("BACK", width / 20, tempX);

		// draws the boxes around selected resolution
		if (Selector.selected == 0) {
			tempX = (int) (width * 0.26);
			tempY = (int) (height * 0.05);
			g.drawRect(tempX, height / 20, width / 10, tempY);
			tempX = (int) (width * 0.37);
			g.drawString("<", tempX, height / 12);
		} else if (Selector.selected == 1) {
			tempX = (int) (width * 0.26);
			tempY = (int) (height * 0.05);
			tempZ = (int) (height * 0.11);
			g.drawRect(tempX, tempZ, width / 10, tempY);
			tempX = (int) (width * 0.37);
			tempY = (int) (height * 0.15);
			g.drawString("<", tempX, tempY);
		} else if (Selector.selected == 2) {
			tempX = (int) (width * 0.26);
			tempY = (int) (height * 0.05);
			tempZ = (int) (height * 0.16);
			g.drawRect(tempX, tempZ, width / 10, tempY);
			tempX = (int) (width * 0.37);
			tempY = (int) (height * 0.2);
			g.drawString("<", tempX, tempY);
		} else if (Selector.selected == 3) {
			tempX = (int) (width * 0.26);
			tempY = (int) (height * 0.05);
			tempZ = (int) (height * 0.21);
			g.drawRect(tempX, tempZ, width / 10, tempY);
			tempX = (int) (width * 0.37);
			tempY = (int) (height * 0.25);
			g.drawString("<", tempX, tempY);
		} else if (Selector.selected == 4) {
			tempX = (int) (width * 0.26);
			tempY = (int) (height * 0.05);
			tempZ = (int) (height * 0.26);
			g.drawRect(tempX, tempZ, width / 10, tempY);
			tempX = (int) (width * 0.37);
			tempY = (int) (height * 0.3);
			g.drawString("<", tempX, tempY);
		}
	}

}
