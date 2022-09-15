package main;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;

import javax.swing.JPanel;

import entity.Entity;
import entity.Player;
import objeto.SuperObjeto;
import tile.TileManager;

public class GamePanel extends JPanel implements Runnable {
	//Screen settings
	final int originalTileSize = 16; //16x16 
	final int scale = 3;
	
	public final int tileSize = originalTileSize * scale; //48x48 tile
	public final int maxScreenCol = 16;
	public final int maxScreenRow = 12;
	public final int screenWidth  = tileSize * maxScreenCol;// 768
	public final int screenHeight = tileSize * maxScreenRow;//576
	
	//World settings
	public final int maxWorldCol = 50;
	public final int maxWorldRow = 50;
	
	//FPS
	int FPS = 60;
	
	TileManager tileM = new TileManager(this);
	public keyHandler keyH = new keyHandler(this);
	Som music = new Som();
	Som se = new Som();
	
	public CollisionChecker cChecker = new CollisionChecker(this);
	public AssetSetter aSetter = new AssetSetter(this);
	//ENTITY OBJECT
	public Player player = new Player(this,keyH);
	public SuperObjeto obj[] = new SuperObjeto[10];
	public Entity npc[] = new Entity[10];
	public UI ui = new UI(this);
	Thread gameThread;
	
	//GAME STATE

		public int gameState;
		public final int menuState     = 0;
		public final int secondMenu    = 1;
		public final int playState     = 2;
		public final int pauseState    = 3;
		public final int dialogueState = 4;
		
	
	public GamePanel() {
		
		this.setPreferredSize(new Dimension(screenWidth, screenHeight));
		this.setBackground(Color.black);
		this.setDoubleBuffered(true);
		this.addKeyListener(keyH);
		this.setFocusable(true);
	}
	public void setupGame() {
		aSetter.setObject();
		aSetter.setNPC();
		//playMusic(0);
		//stopMusic();
		gameState = menuState;
	}
	
	public void startGameThread() {
		
		gameThread = new Thread(this);
		gameThread.start();
	}

	@Override
	public void run() {
		
		double drawInterval = 1000000000/FPS;//0.01666..secs
		long timer = 0;
		double nextDrawTime = System.nanoTime() + drawInterval;	
		
		while(gameThread != null) {
			
			//System.out.println("ai calica");

			update();
			repaint();			
			
			try {
				double remainingTime = nextDrawTime - System.nanoTime();
				remainingTime = remainingTime/1000000;
				
				if(remainingTime < 0) {
					remainingTime = 0;
				}
				Thread.sleep((long) remainingTime);
				
				nextDrawTime += drawInterval;
				
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
	
	public void update() {
		if(gameState == playState) {
			//PLAYER
			player.update();
			//NPC
			for(int i = 0; i < npc.length; i++) {
				if(npc[i] != null) {
					npc[i].update();
				}
			}
		}
		if(gameState == pauseState) {
		//marca aí zé
		}
	}
	
	public void paintComponent(Graphics g) {
		
		super.paintComponent(g);
		Graphics2D g2 = (Graphics2D)g;
		
		//DEBUG
		long drawStart = System.nanoTime();
		if(keyH.checkDrawTime == true) {
			drawStart = System.nanoTime();
		}
		//TITLE SCREEN
		if(gameState == menuState) {
			ui.draw(g2);
			
		}
		
		else {
			
			//TILE
			tileM.draw(g2);
			//OBJETO
			for(int i = 0; i < obj.length; i++) {
				if(obj[i] != null){
					obj[i].draw(g2, null);
				}
				}
			//NPC 01
			for(int i = 0; i < npc.length; i++) {
			if(npc[i] != null) {
				npc[i].draw(g2);
			}	
			}
			
			//PLAYER
			player.draw(g2);
			//UI
			ui.draw(g2);
		}
		
		
		//DEBUG]
		if(keyH.checkDrawTime == true) {
			drawStart = System.nanoTime();
		
		long drawEnd = System.nanoTime();
		long passed = drawEnd - drawStart;
		
		g2.setColor(Color.white);
		g2.drawString("Draw Time:"+ passed , 10, 400);
		System.out.println("Draw Time: "+passed);
		}
		
		g2.dispose();
		
	}
	public void playMusic(int i) {
		
		music.setFile(i);
		music.play();
		music.loop();
	}
	public void stopMusic() {
		music.stop();
	}
	public void playSE(int i) {
		se.setFile(i);
		se.play();
	}

}
