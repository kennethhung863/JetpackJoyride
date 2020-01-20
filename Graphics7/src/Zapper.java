import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.util.Random;
public abstract class Zapper extends MovingObject {
	//variables for hzapper and vzapper
	Random r=new Random();
	public int velX,velY,sizeX,sizeY,x,y,frame;
	BufferedImage img[];
	//
	public abstract void move(boolean gameOver,int vel);
	public abstract void paint(Graphics g);
	
	//random y value generator
	public int randomY(int a) {
		switch (a) {
		case 0:
			return 50;
		case 1:
			return 540;
		default:
			return r.nextInt(390)+100;
		}
		
	}	
	//random x value generator
	public int randomX() {
		return r.nextInt(4000)+2000;
	
	}	
	
	
	//get/set methods
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
	public void setFrame(int frame) {
		this.frame = frame;
	}
}
