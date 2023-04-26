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
	
	
	public int checkObject(Entity entity, boolean player) {
		
		int index = 999;
		for(int i = 0; i < gp.obj.length; i++) {
			if(gp.obj[i] != null) {
				//get entity solid area position
				entity.solidArea.x = entity.worldX + entity.solidArea.x;
				entity.solidArea.y = entity.worldY + entity.solidArea.y;
				//get object solid area position
				gp.obj[i].solidArea.x = gp.obj[i].worldX + gp.obj[i].solidArea.x;
				gp.obj[i].solidArea.y = gp.obj[i].worldY + gp.obj[i].solidArea.y;

					switch(entity.direction) {
					case "up":
						entity.solidArea.y -= entity.speed;
						if(entity.solidArea.intersects(gp.obj[i].solidArea)) {
							if(gp.obj[i].collision == true) {
								entity.collisionOn = true;
							}
							if(player == true) {
								index = i;
							}
						}
						break;
					case "down":
						entity.solidArea.y += entity.speed;
						if(entity.solidArea.intersects(gp.obj[i].solidArea)) {
							if(gp.obj[i].collision == true) {
								entity.collisionOn = true;
							}
							if(player == true) {
								index = i;
							}
						}
						break;
					case "left":
						entity.solidArea.x -= entity.speed;
						if(entity.solidArea.intersects(gp.obj[i].solidArea)) {
							if(gp.obj[i].collision == true) {
								entity.collisionOn = true;
							}
							if(player == true) {
								index = i;
							}
						}
						break;
					case "right":
						entity.solidArea.x += entity.speed;
						if(entity.solidArea.intersects(gp.obj[i].solidArea)) {
							if(gp.obj[i].collision == true) {
								entity.collisionOn = true;
							}
							if(player == true) {
								index = i;
							}
						}
						break;
					}
					entity.solidArea.x = entity.solidAreaDefaultX;
					entity.solidArea.y = entity.solidAreaDefaultY;
					gp.obj[i].solidArea.x = gp.obj[i].solidAreaDefaultX;
					gp.obj[i].solidArea.y = gp.obj[i].solidAreaDefaultY;

			}
		}
		return index;
		
	}
}










