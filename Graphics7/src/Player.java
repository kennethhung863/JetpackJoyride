import java.awt.Color;
import java.awt.Container;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.awt.image.ImageObserver;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;

public class Player extends MovingObject implements ImageObserver{
	private GameObject game;
	int flight=0;
	int jetpack=0;
	/*
	 * first dimension for with jetpack or without
	 * second dimension is for different animations with jetpack/without jetpack
	 * third dimension are the images for animations 
	 * running: 0,0,x
	 * falling:0,1,x
	 * start: 1,0,x
	 * flying: 1,1,x
	 */
	BufferedImage img[][][] =new BufferedImage[2][2][3];{
		try {
				img[0][0][0]=ImageIO.read(new File("C:\\eclipse\\Kenneth ICS 4U1\\Graphics7\\src\\Running\\1.png"));
				img[0][0][1]=ImageIO.read(new File("C:\\eclipse\\Kenneth ICS 4U1\\Graphics7\\src\\Running\\2.png"));
				img[0][0][2]=ImageIO.read(new File("C:\\eclipse\\Kenneth ICS 4U1\\Graphics7\\src\\Running\\3.png"));
				
				img[0][1][0]=ImageIO.read(new File("C:\\eclipse\\Kenneth ICS 4U1\\Graphics7\\src\\Falling\\1.png"));
				img[0][1][1]=ImageIO.read(new File("C:\\eclipse\\Kenneth ICS 4U1\\Graphics7\\src\\Falling\\1.png"));
				img[0][1][2]=ImageIO.read(new File("C:\\eclipse\\Kenneth ICS 4U1\\Graphics7\\src\\Falling\\1.png"));
	            
				img[1][0][0]=ImageIO.read(new File("C:\\eclipse\\Kenneth ICS 4U1\\Graphics7\\src\\Startup\\1.png"));
				img[1][0][1]=ImageIO.read(new File("C:\\eclipse\\Kenneth ICS 4U1\\Graphics7\\src\\Startup\\2.png"));
				img[1][0][2]=ImageIO.read(new File("C:\\eclipse\\Kenneth ICS 4U1\\Graphics7\\src\\Startup\\3.png"));

				img[1][1][0]=ImageIO.read(new File("C:\\eclipse\\Kenneth ICS 4U1\\Graphics7\\src\\Flying\\1.png"));
				img[1][1][1]=ImageIO.read(new File("C:\\eclipse\\Kenneth ICS 4U1\\Graphics7\\src\\Flying\\2.png"));
				img[1][1][2]=ImageIO.read(new File("C:\\eclipse\\Kenneth ICS 4U1\\Graphics7\\src\\Flying\\1.png"));
		}
		 catch (IOException ex1) {
		    ex1.printStackTrace();
		}
	};


	public Player() {
		x=250;
		y=540;
		velY=0;
		sizeX=50;
		sizeY=50;
		frame=0;
	}

	public int getFlight() {
		return flight;
	}
	public void setFlight(int flight) {
		this.flight = flight;
	}
	public int getJetpack() {
		return jetpack;
	}

	public void setJetpack(int jetpack) {
		this.jetpack = jetpack;
	}

	//cant go past 50 and 800 boundaries
	public void move(boolean gameOver,int vel) {
		if (gameOver==false) {
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
		//g2d.setColor(Color.BLUE);
		g2d.drawImage(img[jetpack][flight][frame], x, y, this);


	}
	@Override
	public boolean imageUpdate(Image img, int infoflags, int x, int y, int width, int height) {
		// TODO Auto-generated method stub
		return false;
	}
	
}
