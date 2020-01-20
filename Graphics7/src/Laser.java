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

public class Laser implements  ImageObserver{
	Random r=new Random();
	int velX,sizeX=30,sizeY=30;
	int xl=-sizeX,xr=1200-sizeX-xl;
	int y;
	int laserHeight=0;
	int counter=0;
	boolean counterChanger=false;
	BufferedImage img[]=new BufferedImage[3];

	public Laser(int p) {
        try {
            img [0]= ImageIO.read(new File("C:\\eclipse\\Kenneth ICS 4U1\\Graphics7\\src\\Laser\\Left.png"));
            img [1]= ImageIO.read(new File("C:\\eclipse\\Kenneth ICS 4U1\\Graphics7\\src\\Laser\\Right.png"));
            
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        //y coordinate in 6 possible places
		y=50+p*(82);
	}
	public int getVelX() {
		return velX;
	}
	public void setVelX(int velX) {
		this.velX = velX;
	}
	public int getY() {
		return y;
	}
	public void setY(int y) {
		this.y = y;
	}


	public int getSizeX() {
		return sizeX;
	}
	public void setSizeX(int sizeX) {
		this.sizeX = sizeX;
	}
	public int getSizeY() {
		return sizeY;
	}
	public void setSizeY(int sizeY) {
		this.sizeY = sizeY;
	}
	public int getLaserHeight() {
		return laserHeight;
	}



	public void setLaserHeight(int laserHeight) {
		this.laserHeight = laserHeight;
	}



	public int getXl() {
		return xl;
	}



	public void setXl(int xl) {
		this.xl = xl;
	}



	public int getXr() {
		return xr;
	}



	public void setXr(int xr) {
		this.xr = xr;
	}



	public int getCounter() {
		return counter;
	}



	public void setCounter(int counter) {
		this.counter = counter;
	}



	public boolean getCounterChanger() {
		return counterChanger;
	}



	public void setCounterChanger(boolean counterChanger) {
		this.counterChanger = counterChanger;
	}



	//reset x value
	public void move(boolean gameOver,int vel) {
		velX=vel;
		if (gameOver==false) {
			//move lasers until it is at specified, allow velocity to change if laser animation is done
			xl+=xl<50&&counterChanger==false?velX:0;
			xr-=xr>1200-sizeX-xl&&counterChanger==false?velX:0;
		}
	}
	public void paint(Graphics g) {
		Graphics2D g2d=(Graphics2D) g;
		//shoots beam after counter reaches certain parameter
		if (laserHeight>=5 && counter>=(75-velX)) {
			g2d.setColor(Color.RED);
			if (counter%2==0) {
				g2d.fillOval(xl-7,y-5,sizeX+10,sizeY+10);
				g2d.fillOval(xr-3,y-5,sizeX+10,sizeY+10);	
				g2d.fillRect(xl,y+5,xr-xl+sizeX,sizeY-10);

			}

			g2d.fillOval(xl-5,y-3,sizeX+5,sizeY+5);
			g2d.fillOval(xr,y-3,sizeX+5,sizeY+5);	
			g2d.fillRect(xl,y+10,xr-xl+sizeX,sizeY-20);
		}
		g2d.setColor(Color.PINK);
		if (counter>15&&counterChanger==false) {
		//	g2d.drawOval(xl-(sizeX+50+counter)/2,y-(sizeY+50+counter),sizeX+50-counter,sizeY+50-counter);
			//g2d.drawOval(xr,y,sizeX+50-counter,sizeY+50-counter);
			g2d.drawOval((xl+counter/2)-sizeX+5,(y+counter/2)-sizeY+5,85-counter,85-counter);
			g2d.drawOval((xr+counter/2)-sizeX+5,(y+counter/2)-sizeY+5,85-counter,85-counter);
			//g2d.drawOval((xr+counter/2)-sizeX-5,(y+counter/2)-sizeY-5,100-counter,100-counter);
		}
		g2d.fillRect(xl,y+(sizeY/2)-(laserHeight/2),xr-xl,laserHeight);

		g2d.drawImage(img[0],xl,y,null);
		g2d.drawImage(img[1],xr,y,null);
		//g2d.fillRect(xl, y, sizeX, sizeY);
		//g2d.fillRect(xr, y, sizeX, sizeY);


		

//g2d.drawImage(img, x-10, y-20, this);

	}


	@Override
	public boolean imageUpdate(Image img, int infoflags, int x, int y, int width, int height) {
		// TODO Auto-generated method stub
		return false;
	}
}
