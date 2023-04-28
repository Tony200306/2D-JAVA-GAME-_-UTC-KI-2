package main;

import entity.Entity;

public class CollisionChecker {
	
	GamePanel gp;
	
	//declare object
	public static int cout_step_coin  ;//Dem nhung coin an duoc
	public static int  coin_check;//Set tu class Tilemanager luc load map

	public CollisionChecker(GamePanel gp) {
		this.gp = gp;
	}
	
	
	public  static void setCout_step_coin( int n){
		coin_check = n ;
	}
	
	public void CheckTile (Entity entity) {
		
		int entityLeftWorldX = entity.worldX +entity.solidArea.x;
		int entityRightWorldX = entity.worldX +entity.solidArea.x + entity.solidArea.width;
		int entityTopWorldY = entity.worldY +entity.solidArea.y;
		int entityBottomWorldY = entity.worldY +entity.solidArea.y + entity.solidArea.height;
		
		int entityLeftCol = entityLeftWorldX / gp.tileSize;
		int entityRightCol = entityRightWorldX / gp.tileSize;
		int entityTopRow = entityTopWorldY / gp.tileSize;
		int entityBottomRow = entityBottomWorldY / gp.tileSize;
		
		int tileNum1, tileNum2;
		
//		switch(entity.direction) {
//		case "up":
//			entityTopRow = (entityTopWorldY - entity.speed) / gp.tileSize;
//			tileNum1 = gp.tileM.mapTileNum[entityLeftCol][entityTopRow];
//			tileNum2 = gp.tileM.mapTileNum[entityRightCol][entityTopRow];
//			if(gp.tileM.tile[tileNum1].collision == true || gp.tileM.tile[tileNum2].collision == true) {
//				entity.collisionOn = true;
//					
//			}
//			break;
//		case "down":
//			entityBottomRow = (entityBottomWorldY + entity.speed) / gp.tileSize;
//			tileNum1 = gp.tileM.mapTileNum[entityLeftCol][entityBottomRow];
//			tileNum2 = gp.tileM.mapTileNum[entityRightCol][entityBottomRow];
//			if(gp.tileM.tile[tileNum1].collision == true || gp.tileM.tile[tileNum2].collision == true) {
//				entity.collisionOn = true;
//			}
//			break;
//		case "left":
//			entityLeftCol = (entityLeftWorldX - entity.speed) / gp.tileSize;
//			tileNum1 = gp.tileM.mapTileNum[entityLeftCol][entityTopRow];
//			tileNum2 = gp.tileM.mapTileNum[entityLeftCol][entityTopRow];
//			if(gp.tileM.tile[tileNum1].collision == true || gp.tileM.tile[tileNum2].collision == true) {
//				entity.collisionOn = true;
//			}
//			break;
//		case "right":
//			entityRightCol = (entityRightWorldX + entity.speed) / gp.tileSize;
//			tileNum1 = gp.tileM.mapTileNum[entityRightCol][entityTopRow];
//			tileNum2 = gp.tileM.mapTileNum[entityRightCol][entityBottomRow];
//			if(gp.tileM.tile[tileNum1].collision == true || gp.tileM.tile[tileNum2].collision == true) {
//				entity.collisionOn = true;
//			}
//			break;
//			
//		}
		
		
		switch(entity.direction) {
		case "up":
			entityTopRow = (entityTopWorldY - entity.speed) / gp.tileSize;
			tileNum1 = gp.tileM.mapTileNum[entityLeftCol][entityTopRow]; // DINH NGHIA GOC CUA HINH CHU NHAT KIEM TRA VA CHAM
			tileNum2 = gp.tileM.mapTileNum[entityRightCol][entityTopRow];
			if( gp.tileM.tile[tileNum1].collision == true || gp.tileM.tile[tileNum2].collision == true  ) {
				if( gp.tileM.tile[tileNum1] == gp.tileM.tile[10]) {
					gp.playSE(2);
					entity.collisionOn = false ;
					gp.tileM.mapTileNum[entityLeftCol][entityTopRow] = 0 ;
					gp.tileM.mapTileNum[entityRightCol][entityTopRow] = 0;
					cout_step_coin ++;  // coin ate +1
				}
				else if( gp.tileM.tile[tileNum1] == gp.tileM.tile[11] ) {
					if(cout_step_coin  > 0 ){
						GamePanel.setGameEnd(true);
						entity.collisionOn = false ;
						System.out.println("Done CHECK COIN");
					}
					else entity.collisionOn = true;
				}
				else entity.collisionOn = true;

			}
			break;
		case "down":
			entityBottomRow = (entityBottomWorldY + entity.speed) / gp.tileSize;
			tileNum1 = gp.tileM.mapTileNum[entityLeftCol][entityBottomRow];
			tileNum2 = gp.tileM.mapTileNum[entityRightCol][entityBottomRow];


			if(gp.tileM.tile[tileNum1].collision == true || gp.tileM.tile[tileNum2].collision == true) {
				if(gp.tileM.tile[tileNum1] == gp.tileM.tile[10]   || gp.tileM.tile[tileNum2] == gp.tileM.tile[10] ) {
					gp.playSE(2);
					entity.collisionOn = false ;
					gp.tileM.mapTileNum[entityLeftCol][entityBottomRow] =0;
					gp.tileM.mapTileNum[entityRightCol][entityBottomRow] = 0;
					cout_step_coin++;

				}
				else entity.collisionOn = true;
			}
			break;
		case "left":
			entityLeftCol = (entityLeftWorldX - entity.speed) / gp.tileSize;
			tileNum1 = gp.tileM.mapTileNum[entityLeftCol][entityTopRow];
			tileNum2 = gp.tileM.mapTileNum[entityLeftCol][entityTopRow];


			if(gp.tileM.tile[tileNum1].collision == true || gp.tileM.tile[tileNum2].collision == true) {
				if(gp.tileM.tile[tileNum1] == gp.tileM.tile[10]) {
					gp.playSE(2);
					entity.collisionOn = false ;
					gp.tileM.mapTileNum[entityLeftCol][entityTopRow] = 0;
					gp.tileM.mapTileNum[entityLeftCol][entityTopRow] = 0;
					cout_step_coin++;
				}
				else entity.collisionOn = true;
				if(gp.tileM.tile[tileNum1] == gp.tileM.tile[12] || gp.tileM.tile[tileNum1] == gp.tileM.tile[13])
				{					
					gp.playSE(6);
					GamePanel.setYouDead(true);
					UI.gameFinished = true;
				}
			}
			break;
		case "right":
			entityRightCol = (entityRightWorldX + entity.speed) / gp.tileSize;
			tileNum1 = gp.tileM.mapTileNum[entityRightCol][entityTopRow];
			tileNum2 = gp.tileM.mapTileNum[entityRightCol][entityBottomRow];


			if(gp.tileM.tile[tileNum1].collision == true || gp.tileM.tile[tileNum2].collision == true) {
				if(gp.tileM.tile[tileNum1] == gp.tileM.tile[10]) {
					gp.playSE(2);
					entity.collisionOn = false ;
					gp.tileM.mapTileNum[entityRightCol][entityTopRow] = 0;
					gp.tileM.mapTileNum[entityRightCol][entityBottomRow] = 0;
					cout_step_coin++;
				}
				else entity.collisionOn = true;
				if(gp.tileM.tile[tileNum1] == gp.tileM.tile[12] || gp.tileM.tile[tileNum1] == gp.tileM.tile[13])
				{
					gp.playSE(6);
					GamePanel.setYouDead(true);
					UI.gameFinished = true;
				}
			}
			break;
		}

	}
	
}










