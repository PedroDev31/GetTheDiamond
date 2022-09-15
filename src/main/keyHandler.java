package main;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class keyHandler implements KeyListener {
	
	GamePanel gp;
	
	public boolean upPressed, downPressed, leftPressed, rightPressed, enterPressed;
	public boolean checkDrawTime = false;

	public keyHandler(GamePanel gp) {
		this.gp = gp;
	}
	
	
	@Override
	public void keyTyped(KeyEvent e) {
		
		
	}

	@Override
	public void keyPressed(KeyEvent e) 
	{
		
	int code = e.getKeyCode();	
	//MENU
	
	if(gp.gameState == gp.menuState) {
		
		if(code == KeyEvent.VK_S) {//S DE START KKKKKK
			gp.gameState = gp.secondMenu;
		}
		
	}
	

	if(gp.gameState == gp.secondMenu) {//SELEÇÃO NO MENU 
		if(code == KeyEvent.VK_W) {
			gp.ui.comandNum--;
			if(gp.ui.comandNum < 0) {
				gp.ui.comandNum = 2;
			}
		}
		
		
		if(code == KeyEvent.VK_S) {
			gp.ui.comandNum++;
				if(gp.ui.comandNum > 2) {
					gp.ui.comandNum = 0;
				}
			
		}
		
		if(code == KeyEvent.VK_ENTER) {
			if(gp.ui.comandNum == 0) {
				gp.gameState = gp.playState;
				//gp.playMusic(0);
			}
			else if(gp.ui.comandNum == 0) {
				gp.gameState = gp.playState;
				//gp.playMusic(0);
			}
			else {
				gp.gameState = gp.playState;
				System.exit(0);			
				 }
		} 
		
	}
	
	// JOGO RODANDO
	if(gp.gameState == gp.playState){
		
		if(code == KeyEvent.VK_W) {
			
			upPressed    = true;
		}
		if(code == KeyEvent.VK_S) {
			
			downPressed  = true;
		}
		if(code == KeyEvent.VK_A) {
		
			leftPressed  = true;
		}
		if(code == KeyEvent.VK_D) {
		
			rightPressed = true;
		}
		if(code == KeyEvent.VK_P) {
			gp.gameState = gp.pauseState;
		}
		
		if(code == KeyEvent.VK_ENTER) {
			enterPressed = true;
		}
		//DEBUG
		if(code == KeyEvent.VK_T) {
			if(checkDrawTime == false){
				checkDrawTime = true;
			}
			else if(checkDrawTime == true){
				checkDrawTime = false;
			}
		
		}
		
	}
	//JOGO PAUSADO
	else if(gp.gameState == gp.pauseState){
		if(code == KeyEvent.VK_P) {
			gp.gameState = gp.playState;
		
		}
	}
	// DIALOGO
	else if(gp.gameState == gp.dialogueState){}
	if(code == KeyEvent.VK_ENTER) {
		gp.gameState = gp.playState;
	}
	}

	
	@Override
	public void keyReleased(KeyEvent e) 
	{
		int code = e.getKeyCode();	
		
			if(code == KeyEvent.VK_W) {
				
				upPressed    = false;
			}
			if(code == KeyEvent.VK_S) {
				
				downPressed  = false;
			}
			if(code == KeyEvent.VK_A) {
			
				leftPressed  = false;
			}
			if(code == KeyEvent.VK_D) {
			
				rightPressed = false;
			}
	}
	

}
