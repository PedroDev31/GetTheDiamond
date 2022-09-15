package entity;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import java.util.Scanner;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

import main.FerramentaDeUtilidade;
import main.GamePanel;
import main.Som;
import main.keyHandler;

public class Player extends Entity{
	
    keyHandler keyH;

    
    boolean oPressed = true;
    
    public final int screenX;
    public final int screenY;
   // public int qntdDiamante = 0;


	public BufferedImage imagemDeFundo;
    
    public Player(GamePanel gp, keyHandler keyH){
    	super(gp);
        this.keyH = keyH;
        
        screenX = gp.screenWidth/2 - (gp.tileSize/2);
        screenY = gp.screenHeight/2 - (gp.tileSize/2);
        
        solidArea = new Rectangle();
        //POSICAO DO QUADRADO DE COLISAO
        solidArea.x = 8;
        solidArea.y = 20;
        solidAreaDefaultX = solidArea.x;
        solidAreaDefaultY = solidArea.y;
        //AREA DO QUADRADO COLISAO
        solidArea.width  = 28;
        solidArea.height = 28;
        
        setDefaultValues();
        getPlayerImage();
    }
    


	public void setDefaultValues() {
    
     worldX 	= gp.tileSize * 8;
     worldY 	= gp.tileSize * 27; 
     speed 		= 4; 
     direction  = "down";
     
     //LAYER STATUS
     maxLife = 6;
     life = maxLife;
     
    	
      }
	//public void getImagemDeFundo
    public void getPlayerImage() {
    	
    	up1    = setup ("/player/boy_up_1"   );
    	up2    = setup ("/player/boy_up_2"   );
    	down1  = setup ("/player/boy_down_1" );
    	down2  = setup ("/player/boy_down_2" );
    	left1  = setup ("/player/boy_left_1" );
    	left2  = setup ("/player/boy_left_2" );
    	right1 = setup ("/player/boy_right_1");
    	right2 = setup ("/player/boy_right_2");
    	
    	//setImagemDeFundo(setup("/objetos/menu1"));
    }
    

    public void update() {
    	if(keyH.upPressed == true || keyH.downPressed  == true || 
    	 keyH.leftPressed == true || keyH.rightPressed == true) {
    		
    		if(keyH.upPressed         == true) {
        		direction = "up";
        		//worldY -= speed;
    			
    		}
    		
    		else if(keyH.downPressed  == true) {
    			direction = "down";
    			//worldY += speed;
    			
    		}
    		
    		else if(keyH.leftPressed  == true) {
    			direction = "left";
    			//worldX -= speed;
    			
    		}
    		
    		else if(keyH.rightPressed == true) {
    			direction = "right";
    			//worldX += speed;
    			
    		}
    		
    		//CHECAGEM DE COLISÃO COM TILE
    		collisionOn = false;
    		gp.cChecker.checkTile(this);
    		
    		//CHECAR COLISÃO COM O OBJETO
    		int objIndex = gp.cChecker.checkObjeto(this, true);
    		pickUpObject(objIndex);
    		
    		//CHECAR COLISÃO DO NPC
    		int npcIndex = gp.cChecker.checkEntity(this, gp.npc);
    		interactNPC(npcIndex);
    		
    		//SE A COLISÃO É FALSA, PALYER CONSEGUE SE MOVER
    		if(collisionOn == false) {
    			
    			switch(direction) {
    			
    			case "up"   : worldY -= speed; break;
    			case "down" : worldY += speed; break;
    			case "left" : worldX -= speed; break;
    			case "right": worldX += speed; break;
    			}
    		}
    		
        	spriteCounter++;
        	if(spriteCounter > 12) 
        	{
        		
    		if(spriteNum == 1) 
        	{
    				spriteNum = 2;
        	}
        		
        	else if(spriteNum == 2) 
        	{
        			spriteNum = 1;
        	}
        		spriteCounter = 0;
        	}
        	
        	
    	}
    	else {
    		spriteNum = 1;
    	}
    
    }
    
    public void pickUpObject(int i) {
    	
    	if(i != 999) {
    	
    	}
    }
     public void interactNPC(int i) {
    	 if(i != 999) {
    	if(gp.keyH.enterPressed == true) {
    		  gp.gameState = gp.dialogueState;
        	  gp.npc[i].speak();
    	 }
     	}
    	 gp.keyH.enterPressed = false;
       }
    
    
    
    public void draw(Graphics2D g2) {
    	
    	BufferedImage image = null;
    	
    	switch(direction) {
    	case "up":
    		if(spriteNum == 1) {
    			image = up1;
    		}
    		
    		if(spriteNum == 2) {
    			image = up2;
    		}

    		break;
    	
    	case "down":
    		if(spriteNum == 1) {
    			image = down1;
    		}
    		
    		if(spriteNum == 2) {
    			image = down2;
    		}
    		break;
    		
    	case "left":
    		if(spriteNum == 1) {
    			image = left1;
    		}
    		
    		if(spriteNum == 2) {
    			image = left2;
    		}
    		break;
    	
    	case "right":
    		if(spriteNum == 1) {
    			image = right1;
    		}
    		
    		if(spriteNum == 2) {
    			image = right2;
    		}
    		break;
    	}
    	g2.drawImage(image, screenX ,screenY, null);
    	g2.setColor(Color.RED);
    	g2.drawRect(screenX + solidArea.x,screenY + solidArea.y, solidArea.width, solidArea.height);
    }



	public BufferedImage getImagemDeFundo() {
		return imagemDeFundo;
	}



	public void setImagemDeFundo(BufferedImage imagemDeFundo) {
		this.imagemDeFundo = imagemDeFundo;
	}
}
