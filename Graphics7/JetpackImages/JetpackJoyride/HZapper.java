package JetpackJoyride;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

public class HZapper extends Zapper{
	//zapper animation
	BufferedImage img[]=new BufferedImage[4];{
        try {
            img[0] = ImageIO.read(new File("C:\\eclipse\\Kenneth ICS 4U1\\Graphics7\\src\\Hzapper\\1.png"));
            img[1] = ImageIO.read(new File("C:\\eclipse\\Kenneth ICS 4U1\\Graphics7\\src\\Hzapper\\2.png"));
            img[2] = ImageIO.read(new File("C:\\eclipse\\Kenneth ICS 4U1\\Graphics7\\src\\Hzapper\\3.png"));
            img[3] = ImageIO.read(new File("C:\\eclipse\\Kenneth ICS 4U1\\Graphics7\\src\\Hzapper\\4.png"));
        } catch (IOException ex) {
            ex.printStackTrace();
        }
	}
	//zapper constructor with initial values for horizontal zapper
	public HZapper(int p) {
		velX=-1;
		velY=0;
		sizeX=160;
		sizeY=30;
		frame=0;
		x=randomX();
		y=randomY(p);
	}
	//move zapper
	public void move(boolean gameOver,int vel) {
		if (gameOver==false) {
			velX=vel;
			//regenerate if it is out of bounds
			if (x<-300) {
			x=randomX();
			y=randomY(r.nextInt(3));
			}
			x+=velX;
			y+=velY;
		}
	}
	//paint zapper in gameObject
	public void paint(Graphics g) {
		Graphics2D g2d=(Graphics2D) g;
		g2d.setColor(Color.PINK);
        g2d.drawImage(img[frame], x-10, y-20, null);

	}
	//abstract method inheritance: different for hzapper and vzapper
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
}
