package Main;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import tile.TileManager;




public class GamePanel extends JPanel implements Runnable{
	//Screen's setting
	final int originalTileSize=16;//20pixels
	final int scale=3;
	public int TileSize=originalTileSize*scale;//20x3=60
	public int MaxScreenCol=16;
	public int MaxScreenRow=12;
	public int ScreenWidth=MaxScreenCol*TileSize;
	public int ScreenHeight=MaxScreenRow*TileSize;
	
	//fps
	int FPS=60;
	
	//Game's setting
	int uzayGemisiY1=250;
	int uzayGemisiY2=250;
	int uzayGemisiDirY=20;
	int atesDir=3;
	BufferedImage image1;
	BufferedImage image2;
	public ArrayList<Ates> atesler1=new ArrayList<>();
	public ArrayList<Ates> atesler2=new ArrayList<>();
	
	//System
	Thread gameTheard;
	TileManager tManager=new TileManager(this);
	KeyHandler keyHandler=new KeyHandler(this);
	
	int MaviIsabetliAtis=0;
	int KirmiziIsabetliAtis=0;
	
	public void kontrolEt1() {
		for(Ates ates1:atesler1) {
			if(new Rectangle(ates1.getAtesX(),ates1.getAtesY(),20,10).intersects(new Rectangle(700,uzayGemisiY2,image2.getWidth()/3,image2.getHeight()/3))) {
				MaviIsabetliAtis++;
			}
		}
		
		
	}
	public void kontrolEt2() {
		for(Ates ates2:atesler2) {
			if(new Rectangle(ates2.getAtesX(),ates2.getAtesY(),20,10).intersects(new Rectangle(0,uzayGemisiY1,image2.getWidth()/3,image2.getHeight()/3))) {
				KirmiziIsabetliAtis++;
			}
		}
		
		
	}
	
	public GamePanel() {		
		try {
			image1=ImageIO.read(getClass().getResourceAsStream("/res/spaceShip1.png"));
			image2=ImageIO.read(getClass().getResourceAsStream("/res/spaceShip2.png"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		this.setPreferredSize(new Dimension(ScreenWidth,ScreenHeight));
		this.setBackground(Color.black);
		this.setDoubleBuffered(true);
		this.setFocusable(true);
		this.addKeyListener(keyHandler);
	}

	@Override
	public void run() {
		double drawInterval=1000000000/FPS;
		double delta=0;
		long lastTime=System.nanoTime();
		long currentTime;
		long timer=0;
		int drawCount=0;
		while(gameTheard!=null) {
			currentTime=System.nanoTime();
			delta+=(currentTime-lastTime)/drawInterval;
			timer+=(currentTime-lastTime);
			lastTime=currentTime;
			if(delta>=1) {
				update();			
			    repaint();
			    delta--;
			    drawCount++;
			}
		
	}
}
	public void startGameThread() {
		gameTheard=new Thread(this);
		gameTheard.start();
	}
	public void update() {
		for(Ates ates1:atesler1) {
			ates1.setAtesX(ates1.getAtesX()+atesDir);
		}
		for(Ates ates2:atesler2) {
			ates2.setAtesX(ates2.getAtesX()-atesDir);
		}
		if(keyHandler.playerDown1==true || keyHandler.playerDown2==true|| keyHandler.playerUp1==true||keyHandler.playerUp2) {
			if(keyHandler.playerDown1==true) {
				if(uzayGemisiY1>=520) {
					uzayGemisiY1=520;
				}
				else {
					uzayGemisiY1+=uzayGemisiDirY;
				}
				
			}
			else if(keyHandler.playerUp1==true) {
				if(uzayGemisiY1<=0) {
					uzayGemisiY1=0;
				}
				else {
					uzayGemisiY1-=uzayGemisiDirY;
				}
				
			}
			else if(keyHandler.playerDown2) {
				if(uzayGemisiY2>=520) {
					uzayGemisiY2=520;
				}
				else {
					uzayGemisiY2+=uzayGemisiDirY;
				}
				
			}
			else if(keyHandler.playerUp2) {
				if(uzayGemisiY2<=0) {
					uzayGemisiY2=0;
				}
				else {
					uzayGemisiY2-=uzayGemisiDirY;
				}
				
			}
		}
		
	}
	public void paintComponent(Graphics g) {
		Graphics2D g2=(Graphics2D) g;
		super.paintComponent(g);
		tManager.draw(g2);
		g2.drawImage(image1, 0, uzayGemisiY1,image1.getWidth()/3,image1.getHeight()/3,this);
		g2.drawImage(image2,700,uzayGemisiY2,image2.getWidth()/3,image2.getHeight()/3,this);
		g2.setColor(Color.blue);
		for(Ates ates1:atesler1) {
			g2.fillRect(ates1.getAtesX(),ates1.getAtesY(),20,10);
		}
		g2.setColor(Color.red);
		for(Ates ates2:atesler2) {
			g2.fillRect(ates2.getAtesX(),ates2.getAtesY(),10,10);
		}
		kontrolEt1();
		kontrolEt2();
		if(MaviIsabetliAtis==3) {
			gameTheard=null;
			String message="Oyunu mavi kazandı";
			JOptionPane.showMessageDialog(this, message);
			System.exit(0);
		}
		if(KirmiziIsabetliAtis==3) {
			gameTheard=null;
			String message="Oyunu kirmizi kazandı";
			JOptionPane.showMessageDialog(this, message);
			System.exit(0);
		}
		g2.dispose();
	}
}
