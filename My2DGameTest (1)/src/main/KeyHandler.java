package main;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import entity.Entity;

public class KeyHandler implements KeyListener{
	
	GamePanel gp;
	public boolean upPressed;
	public boolean downPressed;
	public boolean leftPressed;
	public boolean rightPressed;
	
	public KeyHandler(GamePanel gp) {
		this.gp = gp;
	}
	
	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyPressed(KeyEvent e) {
		
		int code = e.getKeyCode();	// return the integer code of the key.
		if (upPressed == false && downPressed == false && leftPressed == false && rightPressed == false) { //Only receive key when player is not moving.
			
			if(code == KeyEvent.VK_W) {
				upPressed = true;
				gp.playSE(4);
			}
			if(code == KeyEvent.VK_S) {
				downPressed = true;
				gp.playSE(4);
			}
			if(code == KeyEvent.VK_A) {
				leftPressed = true;
				gp.playSE(4);

			}
			if(code == KeyEvent.VK_D) {
				rightPressed = true;
				gp.playSE(4);

			}
			
		}
		
		if(code == KeyEvent.VK_P) {
			if(gp.gameState == gp.playState)
			{
				gp.gameState = gp.pauseState;
			}
		else if(gp.gameState == gp.pauseState)
			{
				gp.gameState = gp.playState;
			}
		}
		
		if(code == KeyEvent.VK_R && gp.gameThread == null) {
//			GamePanel gp = new GamePanel();
//			gp.setupGame();			
//			gp.startGameThread();
		}
	}

	@Override
	public void keyReleased(KeyEvent e) {
		
		// do nothing !
//		if(Entity.collisionOn == true) {
//			
//			int code = e.getKeyCode();
//			
//			if(code == KeyEvent.VK_W) {
//				upPressed = false;
//			}
//			if(code == KeyEvent.VK_S) {
//				downPressed = false;
//			}
//			if(code == KeyEvent.VK_A) {
//				leftPressed = false;
//			}
//
//			if(code == KeyEvent.VK_D) {
//				rightPressed = false;
//			}
//		}
	
//		int code = e.getKeyCode();
//		
//		if(code == KeyEvent.VK_W) {
//			upPressed = false;
//		}
//		if(code == KeyEvent.VK_S) {
//			downPressed = false;
//		}
//		if(code == KeyEvent.VK_A) {
//			leftPressed = false;
//		}
//
//		if(code == KeyEvent.VK_D) {
//			rightPressed = false;
//		}
		
	}
	

}
