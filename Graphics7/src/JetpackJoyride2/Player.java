package JetpackJoyride2;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class Player extends MovingObject{
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
				img[0][0][0]=ImageIO.read(new File("JetpackImages\\Running\\1.png"));
				img[0][0][1]=ImageIO.read(new File("JetpackImages\\Running\\2.png"));
				img[0][0][2]=ImageIO.read(new File("JetpackImages\\Running\\3.png"));
				
				img[0][1][0]=ImageIO.read(new File("JetpackImages\\Falling\\1.png"));
				img[0][1][1]=ImageIO.read(new File("JetpackImages\\Falling\\1.png"));
				img[0][1][2]=ImageIO.read(new File("JetpackImages\\Falling\\1.png"));
	            
				img[1][0][0]=ImageIO.read(new File("JetpackImages\\Startup\\1.png"));
				img[1][0][1]=ImageIO.read(new File("JetpackImages\\Startup\\2.png"));
				img[1][0][2]=ImageIO.read(new File("JetpackImages\\Startup\\3.png"));

				img[1][1][0]=ImageIO.read(new File("JetpackImages\\Flying\\1.png"));
				img[1][1][1]=ImageIO.read(new File("JetpackImages\\Flying\\2.png"));
				img[1][1][2]=ImageIO.read(new File("JetpackImages\\Flying\\1.png"));
		}
		 catch (IOException ex1) {
		    ex1.printStackTrace();
		}
	};

	//initializing variable values
	public Player() {
		x=250;
		y=540;
		velY=0;
		sizeX=50;
		sizeY=50;
		frame=0;
	}
	//get/set methods for flight and jetpack
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
		if (!gameOver) {
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
	//paint the player animation
	public void paint(Graphics g) {
		Graphics2D g2d=(Graphics2D) g;
		g2d.drawImage(img[jetpack][flight][frame], x, y, null);
	}	
}
