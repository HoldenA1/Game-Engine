package tech.hackerlife.game;

import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import javax.swing.*;
import tech.hackerlife.game.menu.MainMenuGUI;
import tech.hackerlife.game.util.*;

public class Main {
	public static final String NAME = "Pirate Game";
	public static Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
	static BufferedImage cursorImg = new BufferedImage(16, 16, BufferedImage.TYPE_INT_ARGB);
	public static Cursor blankCursor = Toolkit.getDefaultToolkit().createCustomCursor(
		    cursorImg, new Point(0, 0), "blank cursor");
	public static int HEIGHT = (int) screenSize.getHeight(), WIDTH = (HEIGHT << 4) / 9;
	public static double scale;
	public static boolean decoration = true, displayFPS = false, running = true, displayOn = true;
	public static GameState state = GameState.MAIN_MENU;
	public static JFrame f = new JFrame(NAME);
	public static Display s;
	public static Thread display;
	
	public static void main(String[] args) throws Exception {
		try {
			init();
			createWindow();
		
			long lastTime = System.nanoTime();
			long timer = System.currentTimeMillis();
			final double ns = 1000000000.0 / 60.0;
			double delta = 0;
			int updates = 0;
			while(running) {
				long now = System.nanoTime();
				delta += (now - lastTime) / ns;
				lastTime = now;
				while (delta >= 1) {
					switch (Main.state) {
					case MAIN_MENU:
						MainMenuGUI.update();
						break;
					case GAME:
						Game.update();
						break;
					case LOADING:
						Game.init();
						break;
					default:
						break;
					}
					updates++;
					delta--;
				}
				if (System.currentTimeMillis() - timer > 1000) {
					timer += 1000;
					System.out.println("ups: " + updates);
					updates = 0;
				}
			}
		} catch (Exception e) {
			Main.destroyWindow();
			e.printStackTrace();
			Logger.logError(e);
			JOptionPane.showMessageDialog(null, "Game Crashed!\nCheck error logs for details", "Error", 0);
			Main.exitProcedure();
		}
	}
	
	public static void createWindow() {
		ImageIcon g = new ImageIcon(Reader.loadBufferedImage("/menu/icon.png"));
		s = new Display(WIDTH, HEIGHT);
		scale = HEIGHT / 1440.0;

		f.setUndecorated(decoration);
		f.add(s);
		s.addKeyListener(new Keyboard());
		s.addMouseListener(new Mouse());
		s.addMouseMotionListener(new Mouse());
		s.setFocusable(true);
		s.requestFocusInWindow();
		f.setVisible(true);
		f.setSize(WIDTH, HEIGHT);
		f.setLocationRelativeTo(null);
		f.setResizable(false);
		f.setIconImage(g.getImage());
		f.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
	    f.addWindowListener(new WindowAdapter() {
	        public void windowClosing(WindowEvent event) {
	            exitProcedure();
	        }
	    });
	}
	
	public static void init() {
		try {
			Reader.parseSettings();
			WIDTH = (HEIGHT << 4) / 9;
		} catch (Exception e) {
			destroyWindow();
			e.printStackTrace();
			Logger.logError(e);
			JOptionPane.showMessageDialog(null, "Game Crashed!\nCheck error logs for details", "Error", 0);
			exitProcedure();
		}
	}
	
	public static void destroyWindow() {

		f.remove(s);
		f.dispose();
	}
	
	public static void exitProcedure() {
		running = false;
		Logger.appendSettings();
	    f.dispose();
	    System.exit(0);
	}

}