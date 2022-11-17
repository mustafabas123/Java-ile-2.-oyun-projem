package Main;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.security.PublicKey;

public class KeyHandler implements KeyListener{
	public boolean playerDown1,playerUp1,playerDown2,playerUp2;
	public boolean fire1,fire2;
	
	GamePanel gp;
	public KeyHandler(GamePanel gp) {
		this.gp=gp;
	}

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyPressed(KeyEvent e) {
		int c=e.getKeyCode();
		if(c==KeyEvent.VK_W) {
			playerUp1=true;
		}

		if(c==KeyEvent.VK_S) {
			playerDown1=true;
		}	
		if(c==KeyEvent.VK_UP) {
			playerUp2=true;
		}			
		if(c==KeyEvent.VK_DOWN) {
			playerDown2=true;
		}
			
		if(c==KeyEvent.VK_CONTROL) {
			gp.atesler1.add(new Ates(50,gp.uzayGemisiY1+23));
			
		}

		if(c==KeyEvent.VK_SPACE) {
			gp.atesler2.add(new Ates(700,gp.uzayGemisiY2+23));
		}

		
	}

	@Override
	public void keyReleased(KeyEvent e) {
		int c=e.getKeyCode();
		if(c==KeyEvent.VK_W) {
			playerUp1=false;
		}

		if(c==KeyEvent.VK_S) {
			playerDown1=false;
		}	
		if(c==KeyEvent.VK_UP) {
			playerUp2=false;
		}			
		if(c==KeyEvent.VK_DOWN) {
			playerDown2=false;
		}
		
	}

}
