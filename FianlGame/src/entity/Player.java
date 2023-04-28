package entity;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

import main.GamePanel;
import main.KeyHandler;
import main.Main;


public class Player extends Entity{

	GamePanel gp;
	KeyHandler keyH;
	
	public final int screenX;
	public final int screenY;
	
	public Player(GamePanel gp, KeyHandler keyH) {
		
		this.gp = gp;
		this.keyH = keyH;
		
		screenX = gp.ScreenWidth / 2 - (gp.tileSize /2); //ScreenWidth / 2
		screenY = gp.ScreenHeight / 2 - (gp.tileSize /2); //gp.ScreenHeight / 2
		 
		solidArea = new Rectangle();
		solidArea.x = 0;
		solidArea.y = 0;
		solidAreaDefaultX = solidArea.x;
		solidAreaDefaultY = solidArea.y;
		solidArea.width = 47;
		solidArea.height = 47;
		
		setDefaultValues();
		getPlayerImage();
	}
	public void setDefaultValues() {
		worldX = gp.tileSize * 40;
		worldY = gp.tileSize * 34;
		speed = 24;						//48
		direction = "down";
	}
	public void getPlayerImage( ) {
		try {
			
			up1 = ImageIO.read(getClass().getResource("/player/playerSquare.png"));
			up2 = ImageIO.read(getClass().getResource("/player/playerSquare.png"));
			down1 = ImageIO.read(getClass().getResource("/player/playerSquare.png"));
			down2 = ImageIO.read(getClass().getResource("/player/playerSquare.png"));
			left1 = ImageIO.read(getClass().getResource("/player/playerSquare.png"));
			left2 = ImageIO.read(getClass().getResource("/player/playerSquare.png"));
			right1 = ImageIO.read(getClass().getResource("/player/playerSquare.png"));
			right2 = ImageIO.read(getClass().getResource("/player/playerSquare.png"));
			run = ImageIO.read(getClass().getResource("/player/run.png"));
			
		}catch (IOException e) {
			e.printStackTrace();
		}
	}
	public void update() {
		
		if(keyH.upPressed == true || keyH.downPressed == true || keyH.leftPressed == true || keyH.rightPressed == true) {
			
			if(keyH.upPressed == true) {
				direction = "up";
				//worldY -= speed;
			}
			else if(keyH.downPressed == true) {
				direction = "down";
				//worldY += speed;
			}
			else if(keyH.leftPressed == true) {
				direction = "left";
				//worldX-= speed;
			}
			else if(keyH.rightPressed == true) {
				direction = "right";
				//worldX += speed;
			}
			
			//check tile collision
			collisionOn = false;
			gp.cChecker.CheckTile(this);
			
			
			//if collision false, player can move 
			if(collisionOn == false) {
				switch(direction) {
				case "up":
					worldY -= speed;
					break;
				case "down":
					worldY += speed;
					break;
				case "left":
					worldX-= speed;
					break;
				case "right":
					worldX += speed;
					break;
				}
			}
			else if(collisionOn == true) {
				switch(direction) {
				case "up":

					keyH.upPressed = false;
					break;
				case "down":
					keyH.downPressed = false;
					break;
				case "left":
					keyH.leftPressed = false;
					break;
				case "right":
					keyH.rightPressed = false;
					break;
				}
			}
			
			
//			spriteCounter ++;
//			if(spriteCounter > 12) {   //12
//				if(spriteNum == 1) {
//					spriteNum = 2;
//				}
//				else if(spriteNum == 2) {
//					spriteNum = 1;
//				}
//				spriteCounter = 1;
//			}
			
		}
			
	}
	

	public void draw(Graphics2D g2) {
		
//		g2.setColor(Color.white);
//		
//		g2.fillRect(x, y, gp.tileSize, gp.tileSize);
		
		BufferedImage image = null;

		switch (direction) {
			
		case "up":
//			if(spriteNum == 1) {
//				image = up1;
//			}
//			if(spriteNum == 2) {
//				image = up2;
//			}
			image = run;
			break;
		case "down":
			image = run;
			break;
		case "left":
			image = run;
			break;
		case "right":
			image = run;
			break;
		}
		g2.drawImage(image, screenX, screenY, gp.tileSize, gp.tileSize, null);
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
