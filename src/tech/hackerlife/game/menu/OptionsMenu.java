package tech.hackerlife.game.menu;

import java.awt.*;
import tech.hackerlife.game.Main;
import tech.hackerlife.game.util.Reader;

public class OptionsMenu extends MainMenuGUI {
	static int color = 255, xa = 150, resy = 150, winy = 250, fpsy = 350, backy = 1200,
					resboxx = 600, winboxx = 550, fpsboxx = 640, resboxy = 95,
					winboxy = 195, fpsboxy = 295, h = 60, resboxl = 250, resboxh = 70,
					resonbx = 730, resnx = 770;
	static String curH, h2, h3, h4, h5;
	static Color boxColor = new Color(color, color, color, 100);
	static Image check = Reader.loadBufferedImage("/menu/check.png");
	
	public static void options(Graphics g, Color textColor) {
		// draws option settings
		draw(g, "WINDOWED: ", xa, winy);
		draw(g, "FPS COUNTER: ", xa, fpsy);

		// boxes
		g.setColor(boxColor);
		// windowed inside
		fillRectangle(g, winboxx, winboxy, h, h);
		// fps box inside
		fillRectangle(g, fpsboxx, fpsboxy, h, h);
		
		g.setColor(textColor);
		// windowed box
		drawRectangle(g, winboxx, winboxy, h, h);
		// fps box
		drawRectangle(g, fpsboxx, fpsboxy, h, h);
		
		// box checks
		if (!Main.decoration) {
			draw(g, check, winboxx, winboxy, h, h);
		}
		if (Main.displayFPS) {
			draw(g, check, fpsboxx, fpsboxy, h, h);
		}

		// back button
		draw(g, "BACK", xa, backy);
		
		if (Selector.resSel) {
			draw(g, ">>> RESOLUTION: ", xa, resy);

			// outside selector box
			g.setColor(boxColor);
			for (int k = 4; k >= 0; k--) {
				if (Selector.array.length >= k) {
					fillRectangle(g, resonbx, resboxy, resboxl, (k+1)*resboxh);
					break;
				}
			}
			
			// boxes around selected resolution
			g.setColor(textColor);
			for (int i = 0; i < 5; i++) {
				if (Selector.selected == i) {
					drawRectangle(g, resonbx, resboxy + (i*resboxh), resboxl, resboxh);
					draw(g, "<", resnx + resboxl, resy + (i*resboxh));
					break;
				}
			}
			
			// resolution number
			curH = Integer.toString(Main.HEIGHT);
			draw(g, curH + "p", resnx, resy);

			// other resolutions
			for (int j = 0; j < 4; j++) {
				if (Selector.array.length >= j+1) {
					String str = Integer.toString(Selector.array[j]) + "p";
					draw(g, str, resnx, resy + ((j+1)*resboxh));
				}
			}
		} else {
			draw(g, "RESOLUTION: ", xa, resy);

			// resBox inside
			g.setColor(boxColor);
			fillRectangle(g, resboxx, resboxy, resboxl, resboxh);
			// res box
			g.setColor(textColor);
			drawRectangle(g, resboxx, resboxy, resboxl, resboxh);
			
			// resolution number
			curH = Integer.toString(Main.HEIGHT);
			draw(g, curH + "p", 640, resy);

			// selector arrows
			if (Selector.selected == 0) {
				draw(g, "<<<", 890, resy);
			} else if (Selector.selected == 1) {
				draw(g, "<<<", 660, winy);
			} else if (Selector.selected == 2) {
				draw(g, "<<<", 740, fpsy);
			} else if (Selector.selected == 3) {
				draw(g, "<<<", 350, backy);
			}
		}	
	}
	
}