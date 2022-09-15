package main;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.text.DecimalFormat;

import javax.imageio.ImageIO;

import objeto.OBJ_Diamante;
import objeto.OBJ_Stairs;
import objeto.OBJ_vida;
import objeto.SuperObjeto;

public class UI {

		GamePanel gp;
		Graphics2D g2;
		Font arial_40;
		public boolean messageOn = false;
		public String mensagem = "ai";
		int contadorMensagem = 0;
		public String currentDialogue = "";
		public int comandNum = 0;
		
		BufferedImage vida_cheia, vida_metade, vida_vazia;
		
		
		
		public UI(GamePanel gp) {
			this.gp = gp;
			
			arial_40 = new Font("Arial", Font.TRUETYPE_FONT, 40);
		
		//	OBJ_Diamante diamante = new OBJ_Diamante(gp);
		//	diamanteImagem = diamante.imagem;
		//	OBJ_Stairs stair = new OBJ_Stairs(gp);
		//	stairImagem = stair.imagem;
			
			//CRIAR  OBJETO
			SuperObjeto vida = new OBJ_vida(gp);
			vida_vazia  = vida.imagem;
			vida_cheia  = vida.imagem1;
			vida_metade = vida.imagem2;
		}
		
		
		
		public void mostrarMensagem(String text) {
			
			mensagem  = text;
			messageOn = true;
		}
		public void draw(Graphics2D g2) {
			
			this.g2 = g2;
			
			g2.setFont(arial_40);
			g2.setColor(Color.WHITE);
			
			//JOGO RODANDO
			if(gp.gameState == gp.playState) {
				//marca aí zé
				drawPlayerVida();
			}
			//JOGO PAUSADO
			if(gp.gameState == gp.pauseState) {
				drawPauseScreen();
				drawPlayerVida();

			}
			//Diálogo
			if(gp.gameState == gp.dialogueState) {
				drawDialogueScreen();
				drawPlayerVida();

			}
			// Menu
			if(gp.gameState == gp.secondMenu){
				drawSecondMenu();
			}
			
			//second menu
			if(gp.gameState == gp.menuState){
				drawMenu();
			}
			
		}
		
		public void drawPlayerVida() {
			
			gp.player.life = 1;
			int x = gp.tileSize/2; 
			int y = gp.tileSize/2;
			int i = 0;
			///VIDA VAZIA 
			while(i < gp.player.maxLife/2) {
				g2.drawImage(vida_vazia, x, y,  null);
				i++;
				x += gp.tileSize;
			}
			
			 x = gp.tileSize/2;
			 y = gp.tileSize/2;
		     i = 0;
		     //MEIA VIDA
		     while(i < gp.player.life) {
					g2.drawImage(vida_metade, x, y,  null);
					i++;
					if(i < gp.player.life) {
						g2.drawImage(vida_cheia, x, y, null);
					}
					i++;
					x += gp.tileSize;

				}
		}
		
		public void drawPauseScreen() {
	
			g2.setFont(g2.getFont().deriveFont(Font.PLAIN, 80F));
			Color c = new Color(97, 4, 4, 200);
			g2.setColor(c);

			String text = "JOGO PAUSADO";
			int x = getXforCenteredText(text);
			int y = 300;
			
			
			g2.drawString(text, x, y);
		}
		
		public void drawDialogueScreen() {
			
			//JANELA
			int x = gp.tileSize*2;
			int y = gp.tileSize/2;
			int width = gp.screenWidth - (gp.tileSize*4);
			int height = gp.tileSize*4;
			
			drawSubWindow(x , y, width, height);
			
			g2.setFont(g2.getFont().deriveFont(Font.PLAIN, 32F));
			x += gp.tileSize;
			y += gp.tileSize;
			
			for(String line : currentDialogue.split("\n")) {
				g2.drawString(line, x, y);	
				y += 40;
			}
			
		}
		
		public void drawSubWindow(int x, int y, int width, int height) {
			Color c = new Color(0 , 0 ,0, 200);
			g2.setColor(c);
			g2.fillRoundRect(x ,y , width, height, 35 , 35);
			
			c = new Color(255, 255, 255);
			g2.setColor(c);
			g2.setStroke(new BasicStroke(5));
			g2.drawRoundRect(x + 5, y + 5, width - 10,height - 10 ,25 , 25);
			
			
		}
		
		public void drawSecondMenu() {
		
		//imagem
		int x = 0;
		int y = 0;
		// gp.screenWidth/2;
		// gp.tileSize*2;
		g2.drawImage(setupBackground(), x, y,gp.screenWidth, gp.screenHeight, null);
		// START E LOAD
		if(comandNum == 0){
			x = 260; //POSIÇÃO DO NEW GAME 57 px de diferença
			y = 315;
			g2.drawImage(setupDiamond(), x, y,gp.tileSize*1, gp.tileSize*1, null);
		}
		
		if(comandNum == 1){
			x = 260; //POSIÇÃO DO LOAD GAME
			y = 365;
			g2.drawImage(setupDiamond(), x, y,gp.tileSize*1, gp.tileSize*1, null);
		}
		
		if(comandNum == 2){
			x = 260; //POSIÇÃO DO EXIT
			y = 505;
			g2.drawImage(setupDiamond(), x, y,gp.tileSize*1, gp.tileSize*1, null);
		}
		}
		
		public void drawMenu() {
			
			//imagem
			int x = 0;
			int y = 0;
			// gp.screenWidth/2;
			// gp.tileSize*2;
			g2.drawImage(setupMenu(), x, y,gp.screenWidth, gp.screenHeight, null);
		
		
		}
		
		
		public int getXforCenteredText(String text) {
			
			int lenght = (int)g2.getFontMetrics().getStringBounds(text, g2).getWidth();
			int x = gp.screenWidth/2 - lenght/2;
			
			return x;
			
		}

public BufferedImage setupMenu() {
			
			//FerramentaDeUtilidade uFerramenta = new FerramentaDeUtilidade();
			BufferedImage imagemDeFundo = null;
			try {
				imagemDeFundo = ImageIO.read(getClass().getResourceAsStream("/objetos/menu1.jpg"));
				//imagemDeFundo = uFerramenta.scaleImage(imagemDeFundo, gp.tileSize, gp.tileSize);
			}catch(IOException e){
				e.printStackTrace();
			}
			return imagemDeFundo;
		}

public BufferedImage setupBackground() {
	
	//FerramentaDeUtilidade uFerramenta = new FerramentaDeUtilidade();
	BufferedImage imagemDeFundo = null;
	try {
		imagemDeFundo = ImageIO.read(getClass().getResourceAsStream("/objetos/Menu.bmp"));
		//imagemDeFundo = uFerramenta.scaleImage(imagemDeFundo, gp.tileSize, gp.tileSize);
	}catch(IOException e){
		e.printStackTrace();
	}
	return imagemDeFundo;
}

public BufferedImage setupDiamond() {
	
	FerramentaDeUtilidade uFerramenta = new FerramentaDeUtilidade();
	BufferedImage imagemDeFundo = null;
	try {
		imagemDeFundo = ImageIO.read(getClass().getResourceAsStream("/objetos/diamante.png"));
		imagemDeFundo = uFerramenta.scaleImage(imagemDeFundo, gp.tileSize, gp.tileSize);
	}catch(IOException e){
		e.printStackTrace();
	}
	return imagemDeFundo;
}

}




