import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.awt.image.ImageObserver;
import java.io.File;
import java.io.IOException;
import java.util.Random;

import javax.imageio.ImageIO;

public class Missile extends MovingObject implements ImageObserver{
	Random r=new Random();
	
	BufferedImage img[] =new BufferedImage[8];{
		try {
				img[0]=ImageIO.read(new File("C:\\eclipse\\Kenneth ICS 4U1\\Graphics7\\src\\Missile\\1.png"));
				img[1]=ImageIO.read(new File("C:\\eclipse\\Kenneth ICS 4U1\\Graphics7\\src\\Missile\\1.png"));
				img[2]=ImageIO.read(new File("C:\\eclipse\\Kenneth ICS 4U1\\Graphics7\\src\\Missile\\3.png"));
				img[3]=ImageIO.read(new File("C:\\eclipse\\Kenneth ICS 4U1\\Graphics7\\src\\Missile\\4.png"));
				img[4]=ImageIO.read(new File("C:\\eclipse\\Kenneth ICS 4U1\\Graphics7\\src\\Missile\\5.png"));
				img[5]=ImageIO.read(new File("C:\\eclipse\\Kenneth ICS 4U1\\Graphics7\\src\\Missile\\6.png"));
				img[6]=ImageIO.read(new File("C:\\eclipse\\Kenneth ICS 4U1\\Graphics7\\src\\Missile\\7.png"));
				img[7]=ImageIO.read(new File("C:\\eclipse\\Kenneth ICS 4U1\\Graphics7\\src\\Missile\\8.png"));
		}
		 catch (IOException ex1) {
		    ex1.printStackTrace();
		}
	};

	public Missile() {
		x=r.nextInt(500)+2000;
		y=r.nextInt(500)+50;
		velX=-15;
		velY=0;
		sizeX=30;sizeY=30;frame=0;
	}
	

	//cant go past 50 and 800 boundaries
	public void move(boolean gameOver,int vel) {
		if (gameOver==false) {
			velX=vel;
			if (y>540) {
				y=540;
			}
			if (y<50) {
				y=50;
			}
			x+=velX;
		}
	}
	public void paint(Graphics g) {		
		Graphics2D g2d=(Graphics2D) g;
		g2d.setColor(Color.BLUE);
		//g2d.fillRect(x, y, sizeX, sizeY);
		
		
        g2d.drawImage(img[frame], x, y-10, this);
	}
	@Override
	public boolean imageUpdate(Image img, int infoflags, int x, int y, int width, int height) {
		// TODO Auto-generated method stub
		return false;
	}
	
}
