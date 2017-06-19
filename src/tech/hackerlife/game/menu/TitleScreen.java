package tech.hackerlife.game.menu;

import java.awt.Graphics;
import tech.hackerlife.game.util.Reader;

public class TitleScreen extends MainMenuGUI {
	static int floater = 0, d = 1;
	
	protected static void display(Graphics g) {
		draw(g, Reader.loadBufferedImage("/menu/title.png"), 925, 600 + floater, 800, 400);
		if (floater >= 20) d = -1;
		else if (floater <= -20) d = 1;
		floater += d;

		int playy = 150, opty = 250, quity = 350, xa = 150;
		
		draw(g, "PLAY", xa, playy);
		draw(g, "OPTIONS", xa, opty);
		draw(g, "QUIT", xa, quity);

		if (Selector.selected == 0)
			draw(g, "<<<", 350, playy);
		else if (Selector.selected == 1)
			draw(g ,"<<<", 475, opty);
		else if (Selector.selected == 2)
			draw(g, "<<<", 350, quity);
	}
}