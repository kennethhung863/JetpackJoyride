package JetpackJoyride;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

public class MissileIndicator extends MovingObject{
	//declare variables
	int counter=0;
	BufferedImage img[]=new BufferedImage[4];

	public MissileIndicator() {
		//initialize values of variables
		x=1100;
		y=r.nextInt(500)+50;
		velX=0;
		velY=0;
		sizeX=50;
		sizeY=50;
		counter=0;
        try {
            img[0] = ImageIO.read(new File("C:\\eclipse\\Kenneth ICS 4U1\\Graphics7\\src\\Indicator\\1.png"));
            img[1] = ImageIO.read(new File("C:\\eclipse\\Kenneth ICS 4U1\\Graphics7\\src\\Indicator\\2.png"));
            img[2] = ImageIO.read(new File("C:\\eclipse\\Kenneth ICS 4U1\\Graphics7\\src\\Indicator\\3.png"));
            img[3]=null;

        } catch (IOException ex) {
            ex.printStackTrace();
        }
	}
	//get/set method for counter
	public int getCounter() {
		return counter;
	}
	public void setCounter(int counter) {
		this.counter = counter;
	}
	//cant go past 50 and 540 boundaries
	public void move(boolean gameOver,int vel) {
		if (gameOver==false) {
			velX=vel;
			//make it in bounds if it goes out
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

	//paints indicator animation
	public void paint(Graphics g) {		
		Graphics2D g2d=(Graphics2D) g;
		g2d.drawImage(img[frame],x,y,null);
	}
}
