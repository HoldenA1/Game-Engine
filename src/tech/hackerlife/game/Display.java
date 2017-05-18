package tech.hackerlife.game;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import tech.hackerlife.game.menu.*;
import tech.hackerlife.game.util.Logger;

public class Display extends JPanel implements ActionListener {
	private static final long serialVersionUID = 1L;
	Timer tm = new Timer(16, this);

	int width, height, k;
	Color textColor = new Color(0, 0, 0);

	public Display(int _width, int _height, int _k) {
		this.width = _width;
		this.height = _height;
		this.k = _k;
		
		tm.start();
	}

	public void paintComponent(Graphics g) {
		super.paintComponent(g);

		try {
			switch (Main.state) {
			case MAIN_MENU:
				MainMenuGUI.update(g, width, height, textColor);
				break;
			case GAME:
				setBackground(new Color(32, 32, 32));
				Game.update(g, width, height, k);
				break;
			}
		} catch (Exception e) {
			Main.destroyWindow();
			e.printStackTrace();
			Logger.logError(e);
			JOptionPane.showMessageDialog(null, "Game Crashed!\nCheck error logs for details", "Error", 0);
			Main.exitProcedure();
		}
	}

	public void actionPerformed(ActionEvent e) {
		repaint();
	}

}