import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.awt.image.ImageObserver;
import java.io.File;
import java.io.IOException;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

import javax.imageio.ImageIO;

public class MissileIndicator extends MovingObject implements  ImageObserver{
	Random r=new Random();

	int x=1100,y=r.nextInt(500)+50,velX=0,velY=0,
		sizeX=50,sizeY=50,counter=0;

	BufferedImage img[]=new BufferedImage[4];
	public static int i=0;
	int frame=0;

	public MissileIndicator() {
        try {
            img[0] = ImageIO.read(new File("C:\\eclipse\\Kenneth ICS 4U1\\Graphics7\\src\\Indicator\\1.png"));
            img[1] = ImageIO.read(new File("C:\\eclipse\\Kenneth ICS 4U1\\Graphics7\\src\\Indicator\\2.png"));
            img[2] = ImageIO.read(new File("C:\\eclipse\\Kenneth ICS 4U1\\Graphics7\\src\\Indicator\\3.png"));
            img[3]=null;

        } catch (IOException ex) {
            ex.printStackTrace();
        }
      //  timer.scheduleAtFixedRate(indicate,0,500);

	}
	public int getX() {
		return x;  
	}
	public void setX(int x) {
		this.x = x;
	}
	public int getY() {
		return y;
	}
	public void setY(int y) {
		this.y = y;
	}
	public int getVelY() {
		return velY;
	}
	public void setVelY(int velY) {
		this.velY = velY;
	}
	
	public int getSizeX() {
		return sizeX;
	}
	public void setSizeX(int sizeX) {
		this.sizeX = sizeX;
	}
	public int getSizeY()  {
		return sizeY;
	}
	public void setSizeY(int sizeY) {
		this.sizeY = sizeY;
	}
	

	
	public int getFrame() {
		return frame;
	}
	public void setFrame(int valid) {
		this.frame = valid;
	}
	public int getCounter() {
		return counter;
	}
	public void setCounter(int counter) {
		this.counter = counter;
	}
	//cant go past 50 and 800 boundaries
	public void move(boolean gameOver,int vel) {
		if (gameOver==false) {
			velX=vel;
		
			if (y>540) {
				y=540;
				velY=0;
			}
			if (y<50) {
				y=50;
				velY=0;
			}
			y+=velY;
		}
	}

	
	public void paint(Graphics g) {		
		Graphics2D g2d=(Graphics2D) g;
		g2d.setColor(Color.RED);
		
			g2d.drawImage(img[frame],x,y,null);
			
		
	       // g2d.drawImage(img[1], x, y, this);

		
	}


	@Override
	public boolean imageUpdate(Image img, int infoflags, int x, int y, int width, int height) {
		// TODO Auto-generated method stub
		return false;
	}
	
}
