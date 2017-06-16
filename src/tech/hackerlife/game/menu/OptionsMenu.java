package tech.hackerlife.game.menu;

import java.awt.*;
import tech.hackerlife.game.Main;
import tech.hackerlife.game.util.Reader;

public class OptionsMenu {
	static int tempX, tempY, tempZ, color = 255;
	static String curH, h2, h3, h4, h5;
	static Color boxColor = new Color(color, color, color, 100);
	static Image check = Reader.loadBufferedImage("/menu/check.png");
	
	public static void options(Graphics g, int width, int height, Color textColor) {
		int mem = (width >> 2) / 5;
		// draws option settings
		g.drawString("RESOLUTION: ", mem, height / 12);
		g.drawString("WINDOWED: ", mem, height / 6);
		g.drawString("FPS COUNTER: ", mem, height >> 2);

		mem = (height >> 2) / 5;
		// inside boxes
		g.setColor(boxColor);
		tempX = (int) (width * 0.22);
		g.fillRect(tempX, mem, width / 7, mem);
		g.fillRect(tempX, height >> 3, mem, mem);
		//fps box inside
		tempX = (int) (width * 0.23);
		tempY = (int) (height * 0.21);
		g.fillRect(tempX, tempY, mem, mem);
		// draws boxes
		g.setColor(textColor);
		tempX = (int) (width * 0.22);
		// windowed box
		g.drawRect(tempX, height >> 3, mem, mem);
		// res box
		g.drawRect(tempX, mem, width / 7, mem);
		// fps box
		tempX = (int) (width * 0.23);
		tempY = (int) (height * 0.21);
		g.drawRect(tempX, tempY, mem, mem);

		// draws resolution number
		g.setColor(textColor);
		curH = Integer.toString(Main.HEIGHT);
		tempX = (int) (width * 0.25);
		tempY = (int) (height * 0.09);
		g.drawString(curH, tempX, tempY);

		tempX = (int) (width * 0.22);
		if (!Main.decoration) {
			tempX = (int) (width * 0.22);
			g.drawImage(check, tempX, height >> 3, mem, mem, null);
		}
		if (Main.displayFPS) {
			tempX = (int) (width * 0.23);
			tempY = (int) (height * 0.21);
			g.drawImage(check, tempX, tempY, mem, mem, null);
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
			g.drawString("<<<", tempX, height >> 2);
		} else if (Selector.selected == 3)
			g.drawString("<<<", width >> 3, tempY);
	}

	public static void resOpen(Graphics g, int width, int height, Color textColor) {
		int mem = (height >> 2) / 5;
		g.drawString(">>> RESOLUTION: ", width / 20, height / 12);
		g.drawString("WINDOWED: ", width / 20, height / 6);
		g.drawString("FPS COUNTER: ", width / 20, height >> 2);

		// draws windowed check box
		tempX = (int) (width * 0.22);
		g.setColor(boxColor);
		g.fillRect(tempX, height >> 3, mem, mem);
		g.setColor(textColor);
		g.drawRect(tempX, height >> 3, mem, mem);
		if (!Main.decoration)
			g.drawImage(check, tempX, height >> 3, mem, mem, null);
		//fps box
		g.setColor(boxColor);
		tempX = (int) (width * 0.23);
		tempY = (int) (height * 0.21);
		g.fillRect(tempX, tempY, mem, mem);
		g.setColor(textColor);
		g.drawRect(tempX, tempY, mem, mem);
		g.setColor(boxColor);
		if (Main.displayFPS) {
			tempX = (int) (width * 0.23);
			tempY = (int) (height * 0.21);
			g.drawImage(check, tempX, tempY, mem, mem, null);
		}

		// this draws the outside selector box
		tempX = (int) (width * 0.26);
		int s = (width >> 1) / 5;
		if (Selector.array.length >= 4) {
			tempY = (int) (height * 0.26);
			g.fillRect(tempX, mem, s, tempY);
		} else if (Selector.array.length >= 3) {
			tempY = (int) (height * 0.21);
			g.fillRect(tempX, mem, s, tempY);
		} else if (Selector.array.length >= 2) {
			tempY = (int) (height * 0.16);
			g.fillRect(tempX, mem, s, tempY);
		} else if (Selector.array.length >= 1) {
			tempY = (int) (height * 0.11);
			g.fillRect(tempX, mem, s, tempY);
		} else {
			tempY = (int) (height * 0.05);
			g.fillRect(tempX, mem, s, tempY);
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
			g.drawRect(tempX, mem, s, tempY);
			tempX = (int) (width * 0.37);
			g.drawString("<", tempX, height / 12);
		} else if (Selector.selected == 1) {
			tempX = (int) (width * 0.26);
			tempY = (int) (height * 0.05);
			tempZ = (int) (height * 0.11);
			g.drawRect(tempX, tempZ, s, tempY);
			tempX = (int) (width * 0.37);
			tempY = (int) (height * 0.15);
			g.drawString("<", tempX, tempY);
		} else if (Selector.selected == 2) {
			tempX = (int) (width * 0.26);
			tempY = (int) (height * 0.05);
			tempZ = (int) (height * 0.16);
			g.drawRect(tempX, tempZ, s, tempY);
			tempX = (int) (width * 0.37);
			tempY = (int) (height * 0.2);
			g.drawString("<", tempX, tempY);
		} else if (Selector.selected == 3) {
			tempX = (int) (width * 0.26);
			tempY = (int) (height * 0.05);
			tempZ = (int) (height * 0.21);
			g.drawRect(tempX, tempZ, s, tempY);
			tempX = (int) (width * 0.37);
			tempY = (int) (height * 0.25);
			g.drawString("<", tempX, tempY);
		} else if (Selector.selected == 4) {
			tempX = (int) (width * 0.26);
			tempY = (int) (height * 0.05);
			tempZ = (int) (height * 0.26);
			g.drawRect(tempX, tempZ, s, tempY);
			tempX = (int) (width * 0.37);
			tempY = (int) (height * 0.3);
			g.drawString("<", tempX, tempY);
		}
	}

}
