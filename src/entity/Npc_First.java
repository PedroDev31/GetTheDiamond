package entity;

import java.util.Random;

import main.GamePanel;

public class Npc_First extends Entity{

	private int actionLockCounter;
	public Npc_First(GamePanel gp) {
	    
		super(gp);
		direction = "down";
		speed = 4;
		getImage();
		setDialogue();
	}


public void getImage() {
	
	up1    = setup ("/npc/oldman_up_1"   );
	up2    = setup ("/npc/oldman_up_2"   );
	down1  = setup ("/npc/oldman_down_1" );
	down2  = setup ("/npc/oldman_down_2" );
	left1  = setup ("/npc/oldman_left_1" );
	left2  = setup ("/npc/oldman_left_2" );
	right1 = setup ("/npc/oldman_right_1");
	right2 = setup ("/npc/oldman_right_2");
}

public void setDialogue() {
	
	dialogues [0] = "Olá, Rafael, O grande";
	dialogues [1] = "Tenho um serviço para você";
	dialogues [2] = "Não,você não tem direito de escolha";
	dialogues [3] = "Prenda imediatamente aquele que\n..........";

	
}
//É AQUI ONDE PROGRAMOS O COMPORTAMENTO DE X COISA
public void setAction(){
	
	actionLockCounter++;
	
	if(actionLockCounter == 100) {
		
		
		Random random = new Random();
		int i = random.nextInt(100);
		
		if(i  <= 25) {
			 direction = "right";
		}
	
		if(i > 25 && i <= 50) {
			 direction = "left";
			}
	;
		if(i > 50 && i <= 75) {
			 direction = "right";
		}
		
		if(i > 75 && i  <= 100) {
			 direction = "left";
		}
		
	
	actionLockCounter = 0;
	}
	}
 	public void speak(){
 		//método específico de NPC para NPC
 		super.speak();
 	}
}