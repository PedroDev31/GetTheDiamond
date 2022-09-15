package objeto;

import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;

import main.FerramentaDeUtilidade;
import main.GamePanel;

public class SuperObjeto {
	
	public BufferedImage imagem, imagem1, imagem2;
	public String nome;
	public boolean collision = false;
	public int worldX, worldY;
	public Rectangle solidArea = new Rectangle(0, 0, 44, 44);
	public int solidAreaDefaultX = 0;
	public int solidAreaDefaultY = 0;
	FerramentaDeUtilidade uFerramenta = new FerramentaDeUtilidade();
	
	public void draw(Graphics2D g2, GamePanel gp ){
		
		
		int screenX = worldX - gp.player.worldX + gp.player.screenX;
		int screenY = worldY - gp.player.worldY + gp.player.screenY;
		
		//Condição para melhorar performance do jogo
		
		//Essa condição faz com que o programa desenhe os "blocos" apenas no raio de alcance da visao 
		//do Player,oque não é visto não é desenhado
		if(worldX + gp.tileSize > gp.player.worldX - gp.player.screenX &&
		   worldX - gp.tileSize < gp.player.worldX + gp.player.screenX &&
		   worldY + gp.tileSize > gp.player.worldY - gp.player.screenY &&
		   worldY - gp.tileSize < gp.player.worldY + gp.player.screenY   ) {
			
			g2.drawImage(imagem, screenX, screenY, gp.tileSize, gp.tileSize, null);
		}
	}

	
}


