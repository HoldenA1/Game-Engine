package tech.hackerlife.game;

import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import javax.swing.*;
import tech.hackerlife.game.menu.MainMenuGUI;
import tech.hackerlife.game.util.*;

public class Main extends JPanel {

	private static final long serialVersionUID = 1L;

	static final String NAME = "Pirate Game";
	
	public static Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
	static BufferedImage cursorImg = new BufferedImage(16, 16, BufferedImage.TYPE_INT_ARGB);
	static Cursor blankCursor = Toolkit.getDefaultToolkit().createCustomCursor(cursorImg, new Point(0, 0),
			"blank cursor");

	public static int HEIGHT = (int) screenSize.getHeight(), WIDTH = (HEIGHT << 4) / 9;
	public static double scale;

	public static boolean decoration = true, displayFPS = false, running = true, displayOn = true;

	public static GameState state = GameState.MAIN_MENU;

	public static JFrame f = new JFrame(NAME);

	static Main s;

	Thread load;

	//updates and fps vars
	int counter = 0, fps = 0;
	long time = System.currentTimeMillis(), dTime;
	long lastTime = System.nanoTime();
	long timer = System.currentTimeMillis();
	final double ns = 1000000000.0 / 60.0;
	double delta = 0;
	int updates = 0;

	public static void main(String[] args) {
		try {

			init();
			createWindow();

		} catch (Exception e) {
			destroyWindow();
			e.printStackTrace();
			Logger.logError(e);
			JOptionPane.showMessageDialog(null, "Game Crashed!\nCheck error logs for details", "Error", 0);
			exitProcedure();
		}
	}

	public void paintComponent(Graphics g) {
		try {

			super.paintComponent(g);

			long now = System.nanoTime();
			delta += (now - lastTime) / ns;
			lastTime = now;
			while (delta >= 1) {
				if (Main.state == GameState.MAIN_MENU) {
					MainMenuGUI.update();
				} else if (Main.state == GameState.GAME) {
					Game.update();
				}

				updates++;
				delta--;

			}

			if (Main.state == GameState.MAIN_MENU) {
				MainMenuGUI.render(g);
			} else if (Main.state == GameState.GAME) {
				Game.render(g);
			} else if (Main.state == GameState.LOADING) {
				if (load == null || !load.isAlive()) {
					load = new Thread(new Loader());
					load.start();
				}
				
				Loader.loadingScreen(g);
			}

			if (System.currentTimeMillis() - timer > 1000) { //prints the updates per second
				timer += 1000;
				System.out.println("ups: " + updates);
				updates = 0;
			}

			if (Main.displayFPS) { //prints fps
				fpsCounter(g);
			}

			repaint();

		} catch (Exception e) {
			Main.destroyWindow();
			e.printStackTrace();
			Logger.logError(e);
			JOptionPane.showMessageDialog(null, "Game Crashed!\nCheck error logs for details", "Error", 0);
			Main.exitProcedure();
		}
	}

	public void fpsCounter(Graphics g) {
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

	public static void createWindow() {
		ImageIcon g = new ImageIcon(Reader.loadBufferedImage("/menu/icon.png"));
		s = new Main();
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