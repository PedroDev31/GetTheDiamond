package main;

import objeto.OBJ_Stairs;
import entity.Npc_First;
import objeto.OBJ_Diamante;
import objeto.OBJ_Door;
import objeto.OBJ_Salto;

public class AssetSetter {
	public GamePanel gp;
	
	public AssetSetter(GamePanel gp) {
		this.gp = gp;
	}
	
	public void setObject() {
		
		
	}
	
	public void setNPC() {
		gp.npc[0] = new Npc_First(gp);
		gp.npc[0].worldX = gp.tileSize*/*olhar no programa*/21;
		gp.npc[0].worldY = gp.tileSize*/*olhar no programa*/21;
		
		gp.npc[1] = new Npc_First(gp);
		gp.npc[1].worldX = gp.tileSize*/*olhar no programa*/21;
		gp.npc[1].worldY = gp.tileSize*/*olhar no programa*/22;
		
		gp.npc[2] = new Npc_First(gp);
		gp.npc[2].worldX = gp.tileSize*/*olhar no programa*/21;
		gp.npc[2].worldY = gp.tileSize*/*olhar no programa*/23;
		
	}

}
