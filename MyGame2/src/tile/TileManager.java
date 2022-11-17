package tile;

import java.awt.Graphics2D;
import java.io.IOException;

import javax.imageio.ImageIO;

import Main.GamePanel;

public class TileManager {
	GamePanel gp;
	Tile[] tile;
	public TileManager(GamePanel gp) {
		this.gp=gp;
		tile=new Tile[10];
		getTileImage();
	}
	public void getTileImage() {
		
		try {			
			tile[0]=new Tile();
			tile[0].image=ImageIO.read(getClass().getResourceAsStream("/res/space.jpg"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public void draw(Graphics2D g2) {
		int col=0;
		int row=0;
		int x=0;
		int y=0;
		while (col<gp.MaxScreenCol && row<gp.MaxScreenRow) {
			g2.drawImage(tile[0].image,x,y,gp.TileSize,gp.TileSize,null);
			col++;
			x+=gp.TileSize;
			if(col==gp.MaxScreenCol) {
				col=0;
				x=0;
				row++;
				y+=gp.TileSize;
			}
			
			
		}
	}

}
