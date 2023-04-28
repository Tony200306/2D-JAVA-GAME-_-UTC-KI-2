package main;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.time.LocalTime;

import javax.swing.JLabel;
import javax.swing.JPanel;

public class UI {

	GamePanel gp;
	Font arial_40, arial_100B;
	public static boolean gameFinished = false;
	
	public UI (GamePanel gp) {
		this.gp = gp;
		
		arial_40 = new Font("Arial", Font.PLAIN, 40);
		arial_100B = new Font("Arial", Font.BOLD, 100);
	}
	
	
public void draw(Graphics2D g2) {
		
		if (gameFinished == true) {
			
			g2.setFont(arial_100B);
			g2.setColor(Color.YELLOW);
			
			String text = "Game Over!";
			int length = (int) g2.getFontMetrics().getStringBounds(text, g2).getWidth();

			int x = gp.ScreenWidth/2 - length/2;
			int y = gp.ScreenHeight/2 - gp.tileSize * 3;

			g2.drawString(text, x, y);

			gp.gameThread = null;

		}
		
		if(gp.gameState == gp.playState)
		{		
			
		}
		if(gp.gameState == gp.pauseState)
		{		
			g2.setFont(new Font("Arial", Font.BOLD, 10000));
			g2.setColor(Color.BLACK);
			drawPauseScreen(g2);

			g2.setFont(new Font("Arial", Font.BOLD, 100));
			g2.setColor(Color.blue);
			drawPauseScreen(g2);
		}
	}
	
public void drawPauseScreen(Graphics2D g2) {
	
		String text = "PAUSE";
		int x = getXforCenterText(text, g2);
		int y = gp.ScreenHeight/2 + 500;

		g2.drawString(text, x, y);
	String text2 = "PAUSE";
	int x2 = getXforCenterText(text, g2);
	int y2 = gp.ScreenHeight/2 ;

	g2.drawString(text, x2, y2);
	}
	
	public int getXforCenterText(String text, Graphics2D g2 ) {
		int length = (int) g2.getFontMetrics().getStringBounds(text, g2).getWidth();
		int x = gp.ScreenWidth/2 - length/2;
		return x;
	}
}
