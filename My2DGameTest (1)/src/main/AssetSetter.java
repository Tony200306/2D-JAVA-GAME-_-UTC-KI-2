package main;

import object.OBJ_FinishLine;

public class AssetSetter {
	
	GamePanel gp;
	public AssetSetter(GamePanel gp) {
		this.gp = gp;
	}

	public void setObject() {
		
		gp.obj[0] = new OBJ_FinishLine();
		gp.obj[0].worldX = 25 * gp.tileSize;
		gp.obj[0].worldY = 14 * gp.tileSize;
		
	}
}
