import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.MouseInfo;
import java.awt.Rectangle;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

import javax.imageio.ImageIO;
import javax.swing.JPanel;

/* Programmer Name: Kenneth Hung
 * Date: June 13 2019
 * Purpose: Jetpack Joyride program where user avoids obstacles in an endless game.
 * They can buy powerups and look for help in the menu.
 * There are 3 obstacles: zappers, missiles, and lasers
 * TODO add more^
 * TODO delete System.out.println(); lines, commented lines
 * TODO reset coinMagnet, shield, revive and COIN after testing
 * check other TODO in program
 * 
 */
public class GameObject extends JPanel{
	//declare objects/variables
	Random r=new Random();
	BufferedImage img[]=new BufferedImage[10] ;{
    try {
    	for (int i=0; i<img.length;i++) {
    		img [i]= ImageIO.read(new File("C:\\eclipse\\Kenneth ICS 4U1\\Graphics7\\src\\backgroundImg.jpg"));}    	
     } catch (IOException ex) {
         ex.printStackTrace();}	
     };
     BufferedImage title;{
    	 try {
     		title= ImageIO.read(new File("C:\\eclipse\\Kenneth ICS 4U1\\Graphics7\\src\\title.png")); 	

    	 }catch (IOException ex) {
             ex.printStackTrace();}	
         };
     
	//background velocity and x value
	int velX=-3;
	int x[]=new int[img.length]; {
			for (int i=0,j=0; i<x.length;i++,j+=514) {
				x[i]=j;		}
	};
	
	//game objects + instantiating them in arrays: index=number on screen at a time
	Player p=new Player();
	Missile m[]=new Missile[2];{
		for (int i=0;i<m.length;i++) {
			m[i]=new Missile();}
	}
	MissileIndicator mi[]=new MissileIndicator[2];{
		for (int i=0;i<mi.length;i++) {
			mi[i]=new MissileIndicator();
			mi[i].setFrame(3);}
		
	}
	Zapper z[]=new Zapper[4];{
	for (int i=0; i<z.length;i++) {
		//50% chance of generating horizontal or vertical zapper
		z[i]=r.nextInt(2)==0?new HZapper(r.nextInt(3)):new VZapper(r.nextInt(3)); 
		}
	}	
	Laser l[]=new Laser[2];{
		for (int i=0; i<l.length;i++) {
			l[i]=new Laser(r.nextInt(6)+1);
		}
	}
	//game data
	Data d=new Data();
	int cy=r.nextInt(390)+50;
	Coin c[]=new Coin[20];
	int cx[]=new int[c.length]; {		
		for (int i=0;i<c.length;i++) {
			c[i]=new Coin();
			c[i].setX(2000+50*i);
			c[i].setY(cy);
			cx[i]=c[i].getX();
		}
	}

	//other variables
	boolean gameOver=false;
	boolean valid= false;
	int coinVelX=velX;
	int counter=0;
	boolean menu=true;
	boolean shop=false;
	boolean help=false;
	boolean credit=false;
	Rectangle button[]=new Rectangle[3];{
		for (int i=0;i<button.length;i++) {
			button[i]=new Rectangle(900,100+125*i,200,75);
		}	};
	Rectangle backButton=new Rectangle(10,10,100,50);
		//shop variables
	Rectangle shopButton[]=new Rectangle[3];{
		for (int i=0;i<button.length;i++) {
			shopButton[i]=new Rectangle(850,100+125*i,200,75);
		}	};
	boolean shopImageDisplay[]= new boolean[shopButton.length];{
		for (int i=0;i<shopImageDisplay.length;i++) {
			shopImageDisplay[i]=false;
		}	};
	BufferedImage shopImage[]=new BufferedImage[shopButton.length];{
			try {
				shopImage[0]=ImageIO.read(new File("C:\\eclipse\\Kenneth ICS 4U1\\Graphics7\\src\\ShopImage\\1.png"));
				shopImage[1]=ImageIO.read(new File("C:\\eclipse\\Kenneth ICS 4U1\\Graphics7\\src\\ShopImage\\2.png"));
				shopImage[2]=ImageIO.read(new File("C:\\eclipse\\Kenneth ICS 4U1\\Graphics7\\src\\ShopImage\\3.png"));
			} catch (IOException e) {
				e.printStackTrace();}
	};
	String shopText[][]= {{"500","150","100"},{"Prototype space magnet makes coins attracted to you!","A big shield to protect you from scary obstacles.","YOLT! You only live TWICE now!"}};
	boolean coinMagnet=false,collisionOn=true,revive=false;
	int shield=0,collisionTimer=0;
	int tempDistance=0;
	
	//timed operations
	Timer timer=new Timer();
	
	TimerTask indicate=new TimerTask() {
		public void run() {
			for (MissileIndicator a:mi) {
				//every other counter value (even) change indicator picture
				if (a.getCounter()%2==0&&a.getCounter()<=25) {
					a.setFrame(0);
				}
				else {
					a.setFrame(1);
				}
				//at the end, display the last warning image
				if (a.getCounter()>25) {
					a.setFrame(2);
				}
				//set image to null if counter has reached max parameter, counter++ when its below max parameter
				a.setFrame(a.getCounter()>=30?3:a.getFrame());
				a.setCounter(a.getCounter()<30?a.getCounter()+1:a.getCounter());
				}
			}
		};
	TimerTask press=new TimerTask() {
		public void run() {
			if (menu==false) {
			//if the player is under the ceiling, has not reached max speed and key is pressed, add velocity
				if (p.getY()>50 && p.getVelY()>-8 && valid==true) {
					p.setVelY(p.getVelY()-(1));
					//if pressed, add counter for playerFrame
					counter++;
					//System.out.println("press: "+p.getVelY());
				}
			}
			}
	};	
	TimerTask release=new TimerTask() {
		public void run() {
			if (menu==false) {
			//if the p is above the ground and key is released, max speed for gravity is set so it's not too hard to recover
			if (p.getY()<540 && p.getVelY()<6 &&valid==false) {
				p.setVelY(p.getVelY()+(1));
				counter=0;
				//System.out.println("release: "+p.getVelY());
			}
			}
		}
	};
	//player animation task
	TimerTask playerFrame=new TimerTask() {
		public void run() {
			//set animation depending on where player is (falling, flying, ground, etc)
			p.setJetpack(valid&&gameOver==false?1:0);
			p.setFlight(p.getJetpack()==0?p.getY()==540&&gameOver==false?0:1:counter>2&&gameOver==false?1:0);
			p.setFrame(p.getFrame()<2&&gameOver==false?p.getFrame()+1:0);
		}
	};
	//missile animation task
	TimerTask missileFrame=new TimerTask() {
		public void run() {
			//set animation cycle for missile
			for (int i=0; i<m.length;i++) {
				m[i].setFrame(m[i].getFrame()<7&&gameOver==false?m[i].getFrame()+1:0);
			}
		}
	};
	//zapper animation task
	TimerTask zapperFrame=new TimerTask() {
		public void run() {
			//set animation cycle for zapper
			for (int i=0; i<z.length;i++) {
				z[i].setFrame(z[i].getFrame()<3&&gameOver==false?z[i].getFrame()+1:0);
			}
		}
	};
	//laser movement task
	TimerTask laser=new TimerTask() {
		public void run() {
			if (gameOver==false) {
			for (int i=0; i<l.length; i++) {
				//if counterchanger is false and height limit hasnt been reached and laser side is in bounds, increase laserheight
				//if counterchanger is true(laser disappearing) and counter is less than 50, then shrink laserheight
				l[i].setLaserHeight(l[i].getCounterChanger()==false&&l[i].getLaserHeight()<5&&l[i].getXl()>=50?l[i].getLaserHeight()+1:l[i].getLaserHeight() + (l[i].getCounterChanger()==true&&l[i].getCounter()<50?-1:0));
				//change xl when laser disappears
				l[i].setXl(l[i].getLaserHeight()<=0&&l[i].getCounterChanger()==true?l[i].getXl()-(Math.abs(velX))+1:l[i].getXl());
				l[i].setXr(l[i].getLaserHeight()<=0&&l[i].getCounterChanger()==true?l[i].getXr()+(Math.abs(velX))+1:l[i].getXr());
				//move method in here otherwise it moves too fast
				if (gameOver==false) {
					l[i].move(gameOver,(Math.abs(velX))+1);
					//once counter exceeds parameter, reverse direction of counter
					if (l[i].getCounter()>=100-Math.abs(velX)+1) {
						l[i].setCounterChanger(true);
					}
					//if counterchanger is true, reverse the addition to subtraction
					l[i].setCounter(l[i].getCounter()+(l[i].getCounterChanger()?-1:1));
				}
			}	

			}
		}
	};
	TimerTask coinFrame=new TimerTask(){
		public void run() {
			for (int i=0;i<c.length;i++) {
				c[i].setFrame(c[i].getFrame()<3&&gameOver==false?c[i].getFrame()+1:0);
			}
		}
		
	};
	//paint method: calculations that are repeated like intersections are placed in here since the method is in while true loop
	public void paint(Graphics g) {
		super.paint(g);
		Graphics2D g2d = (Graphics2D) g;
		g2d.setColor(Color.black);
		g2d.fillRect(0,0,3000,3000);
		//background
		Arrays.sort(x);
		for (int i=0; i<img.length;i++) {
			g2d.drawImage(img[i],x[i],0,this);
			if (x[i]<=-514) {
				x[i]=x[img.length-1]+514;
			}
			if (gameOver==false) {
				x[i]+=velX;
			}
		}  
		
		//menu option
		if (menu==true) {
			menu(g2d);
			d.setCoin(0);
		}
		//in the shop
		else if (shop==true) {
			shop(g2d);
		}
		//help menu
		else if(help==true) {
			help(g2d);
		}	
		//credit menu
		else if(credit==true) {
			credit(g2d);
		}
		//in the game
		else {
			//speed up interval
			velX+=d.getDistance()%1000==0?-1:0;  
			
			//player code
			p.paint(g);
			//shield value and animation
			if (shield>0) {
				g2d.setColor(Color.WHITE);
				g2d.setFont(new Font("Times Roman", Font.BOLD, 15));
				g2d.drawString(Integer.toString(shield),p.getX()+p.getSizeX()/2,p.getY());
				//shield animation
				if (collisionTimer>0) {
					g2d.drawOval(p.getX()+75-250+collisionTimer/2,p.getY()+75-250+collisionTimer/2,400-collisionTimer,400-collisionTimer);
				}
			}
			p.move(gameOver,0);
	
			//coin code
			Arrays.sort(cx);
			cy=r.nextInt(390)+50;
			//coin reset if out of bounds
			if (c[0].getX()<-2000) {
				for (int i=0;i<c.length;i++) {
					c[i]=new Coin();
					c[i].setX(2000+50*i);
					c[i].setY(cy);
					coinVelX=velX;
				}
			}
			for (int i=0;i<c.length;i++) {
				//coin magnet: if magnet within range, move towards player and if player purchased the magnet
				if (c[i].getX()-p.getX()<=150&&Math.abs(c[i].getY()-p.getY())<=150&&coinMagnet) {
					g2d.setColor(Color.YELLOW);
					g2d.drawOval(p.getX()+75-250+300/2,p.getY()+75-250+300/2,400-300,400-300);
					c[i].setVelY(c[i].getY()>p.getY()?-4:4);
				//	c[i].setVelX(c[i].getX()>p.getX()?(-Math.abs(c[i].getVelX()))-2:(Math.abs(c[i].getVelX()))+2);
					
				}
				c[i].paint(g2d);
				c[i].move(gameOver,velX);
			}
			
			
			
			
			for (int i=0; i<m.length; i++) {
				//missile code
				if (gameOver==false) {
					//repaint if out of bounds
					if (m[i].getX()>-10000) {
						mi[i].paint(g);
						//player tracking
						if (mi[i].getY()>p.getY() && mi[i].getCounter()<30) {
							mi[i].setVelY(-1);
						}
						else if (mi[i].getY()<p.getY()&& mi[i].getCounter()<30) {
							mi[i].setVelY(1);
						}
						mi[i].move(gameOver,0);
						
						//after indicator, send missiles
						if (mi[i].getCounter()>=30) {
							mi[i].setVelY(0);
							m[i].setY(mi[i].getY());
							m[i].paint(g);
							m[i].move(gameOver,velX-5);
						}
					}
					//if out of bounds, reset missiles
					else {
						mi[i]=new MissileIndicator();
						m[i]=new Missile();
					}
				}
				//paint it after game ends
				else {
					m[i].paint(g);
				}
			}
	
			
			//checks zapper collision, if they are too close, regenerate new zapper (combination algorithm)
			//total different values
			int n=z.length;
			//total values used
			int combo=2;
			//initializing values into list array
			int list[]=new int[combo];{
				for (int i=0;i<list.length;i++) {
					list[i]=i;
				}}
			//checks for different algorithms starting at highest index
			int index=combo-1;
			do {
				do {	
					//action with initial combination list: check rectangle collisions
					if ((new Rectangle(z[list[0]].getX(),z[list[0]].getY(),(z[list[0]].getSizeX()+100),z[list[0]].getSizeY())).intersects(new Rectangle(z[list[1]].getX(),z[list[1]].getY(),z[list[1]].getSizeX()+100,z[list[1]].getSizeY()))) {
						System.out.println("yay");
						z[list[0]]=r.nextInt(2)==0?new HZapper(r.nextInt(3)):new VZapper(r.nextInt(3)); 
					}	
					//if there are more combinations, add 1 to element to acquire next combination
					if (list[index]<(n-combo+index)) {
						list[index]++;
						//if on first index, set new values to x, x+1 to check for new combinations
						if (index!=combo-1) {
							list[index+1]=list[index]+1;
							//check new combinations in second index now that first index has shifted
							index++;
						}
					}
					//while there are more combinations
					}while(list[index]<n-combo+index);
				//no more combinations, look in next index of array
				index--;
				//while there are still more indexes to look through
			}while(index>=0);	
			
			//zapper code
			for (int i=0; i<z.length;i++) {				
				z[i].paint(g);
				z[i].move(gameOver,velX);
				
				//collision: all game objects
				Rectangle rp=new Rectangle(p.getX(),p.getY(),p.getSizeX(),p.getSizeY());
				Rectangle rz[]=new Rectangle[z.length];
				rz[i]=new Rectangle(z[i].getX(),z[i].getY(),z[i].getSizeX(),z[i].getSizeY());
	
				Rectangle rm[]=new Rectangle[m.length];
				Rectangle rl[]=new Rectangle[l.length];
				Rectangle rc[]=new Rectangle[c.length];
				for (int j=0;j<c.length;j++) {
					rc[j]=new Rectangle(c[j].getX(),c[j].getY(),c[j].getSizeX(),c[j].getSizeY());
					if (rp.intersects(rc[j])) {
						c[j].setX(-50);
						d.setCoin(d.getCoin()+1);
						d.setTotalCoin(d.getTotalCoin()+1);
					}
				}
	
				
				//if collision, then game over
				for (int j=0; j<m.length;j++) {
					for (int k=0;k<l.length;k++) {
						rl[k]=new Rectangle(l[k].getXl(),l[k].getY()+7,l[k].getXr()-l[k].getXl()+l[k].getSizeX(),l[k].getSizeY()-15);
						rm[j]=new Rectangle(m[j].getX(),m[j].getY(),m[j].getSizeX(),m[j].getSizeY());
						//collision with zapper, missiles, lasers and when shield isnt activated
						if ((rp.intersects(rz[i]) || rp.intersects(rm[j]) || (rp.intersects(rl[k])&&l[k].getCounter()>=(75-Math.abs(velX)+1)))&&collisionOn) {
							//if there are shield upgrades, take away collision, else its game over
							if (shield>0) {
								collisionOn=false;
							}
							else {
								gameOver=true;
							}
						}
						//when collision is off, start shield timer and once it reaches parameter reset values and subtract shield
						else if (!collisionOn) {
							collisionTimer++;
							//resets value after collisiontimer eaches parameter
							if (collisionTimer>400) {
								collisionOn=true;
								collisionTimer=0;
								shield--;
							}
						}
				}
			}
			
			//laser code
			for (int j=0; j<l.length; j++) {
				l[j].paint(g);
				if (l[j].getCounter()<-150) {
					l[j]=new Laser(r.nextInt(6)+1);
				}
			}
		}
			
		//data code
		d.move(gameOver,0);
		d.paint(g);
		//game over action
		//TODO add background rectangle
		if (gameOver==true) {
			g2d.setFont(new Font("Times Roman", Font.BOLD, 70));
			g2d.setColor(Color.WHITE);
			int top=200;
			g2d.drawString("GAME OVER",350,top);
			g2d.setFont(new Font("Times Roman", Font.PLAIN, 30));
			g2d.drawString("Distance: "+d.getDistance(),350,top+100);
			g2d.drawString("Coins: "+d.getCoin(),350,top+150);
			g2d.drawString("Total Coins: "+d.getTotalCoin(),75,top);

			if (d.getDistance()<d.getHighScore()) {
				g2d.drawString(d.getHighScore()-d.getDistance()+"m away from your high score!",350,top+200);
			}
			else {
				g2d.drawString("NEW HIGH SCORE!!",350,top+200);
			}
			g2d.drawString("Press enter to return to main menu",350,top+300);
			if (revive) {
				g2d.setColor(Color.RED);
				g2d.drawString("Press E to use quick revive (once per game)",350,top+350);
			}
			g2d.drawString("You can buy revives in the shop!.",350,top+400);

		}
		//black rectangle to cover excess screen space
		g2d.setColor(Color.black);
		g2d.fillRect(1200,0,2000,2000);
		}
	}

	//constructor: what is made in main method
		public GameObject() {
				//runs timed operations like animations
				//missileIndicator startup delay
		        timer.scheduleAtFixedRate(indicate,10000,100);
		        timer.scheduleAtFixedRate(missileFrame,0,50);
		        timer.scheduleAtFixedRate(playerFrame,0,150);
		        timer.scheduleAtFixedRate(zapperFrame,0,85);
		        timer.scheduleAtFixedRate(laser,30000,50);
		        timer.scheduleAtFixedRate(coinFrame,0,100);
	        //keylistener for input
			KeyListener listener = new KeyListener() {
				public void keyTyped(KeyEvent e) {
				}
				public void keyPressed(KeyEvent e) {
					//press spacebar
					if (e.getKeyCode()==KeyEvent.VK_SPACE) {
						valid=true;
						//press action
						try {
							timer.scheduleAtFixedRate(press, 0, 25);
						}
						catch (IllegalStateException ise) {
						}	
					}
					//press enter to start
					if (e.getKeyCode()==KeyEvent.VK_ENTER&&menu==true) {
						menu=false;
						velX=-3;
						reset();

					}				
					//return to main menu
					//revive used: slow speed initially so then its not sudden, reset game but stores info like distance so game continues
					if (e.getKeyCode()==KeyEvent.VK_E&&revive&&gameOver) {
						gameOver=false;
						int temp= velX;
						Data tempData=d;
						tempDistance=(d.getDistance());
						reset();
						velX=temp-1;
						d.setDistance(tempDistance);					
						d.setCoin(tempData.getCoin());
						revive=false;
					}
					else if (e.getKeyCode()==KeyEvent.VK_ENTER&&gameOver==true) {
						gameOver=false;
						reset();
						menu=true;
					}
					
				}
				public void keyReleased(KeyEvent e) {
					valid=false;
					//spacebar, release action
					try {
						timer.scheduleAtFixedRate(release, 0, 50);
					}
					catch (IllegalStateException ise) {
					}
				}
			};
			MouseListener mListener=new MouseListener() {
				public void mouseClicked(MouseEvent e) {
				//	System.out.println("clicked");
					Rectangle click=new Rectangle(e.getX(),e.getY(),1,1);
					//enter shop
					if (click.intersects(button[0])&&menu==true) {
						shop=true;
						menu=false;
						//System.out.println("shop");
					}
					//enter help
					else if (click.intersects(button[1])&&menu==true) {
						help=true;
						menu=false;
						//System.out.println("help");
					}
					//enter credits
					else if (click.intersects(button[2])&&menu) {
						credit=true;
						menu=false;
						//System.out.println("credits");
					}
					//back button
					if (click.intersects(backButton)&&(shop||help||credit)) {
						shop=false;
						help=false;
						credit=false;
						menu=true;
						
					}
					//shop selections: if user has enough coins, change variable values (affects game)
					if (click.intersects(shopButton[0])&&shop==true&&d.getTotalCoin()>=500&&coinMagnet==false) {
						coinMagnet=true;
						d.setTotalCoin(d.getTotalCoin()-500);
					}
					else if(click.intersects(shopButton[1])&&shop==true&&d.getTotalCoin()>=150) {
						shield++;
						d.setTotalCoin(d.getTotalCoin()-150);
					}
					else if(click.intersects(shopButton[2])&&shop==true&&d.getTotalCoin()>=100) {
						revive=true;
						d.setTotalCoin(d.getTotalCoin()-100);
					}
				}
				public void mousePressed(MouseEvent e) {					
				}
				public void mouseReleased(MouseEvent e) {	
				}
				public void mouseEntered(MouseEvent e) {	
				}
				public void mouseExited(MouseEvent e) {
					
				}
				
			};
			MouseMotionListener mmListener=new MouseMotionListener() {
				public void mouseDragged(MouseEvent e) {
				}
				public void mouseMoved(MouseEvent e) {	
					//hover icons
					Rectangle hover=new Rectangle(e.getX(),e.getY(),1,1);
					//in the shop
					for (int i=0;i<shopButton.length;i++) {
						shopImageDisplay[i]=hover.intersects(shopButton[i])?true:false;

					}
				}
				
			};
			addKeyListener(listener);
			addMouseListener(mListener);
			addMouseMotionListener(mmListener);
			setFocusable(true);
		}

		public void reset() {
				//background velocity
				velX=-3;				
				
				//game objects + instantiating them in arrays: index=number on screen at a time
				p=new Player();
				for (int i=0;i<m.length;i++) {
					m[i]=new Missile();}
			
				for (int i=0;i<mi.length;i++) {
					//mi[i]=new MissileIndicator();
					//mi[i].setFrame(3);
					}
					
				
				for (int i=0; i<z.length;i++) {
					//50% chance of generating horizontal or vertical zapper
					z[i]=r.nextInt(2)==0?new HZapper(r.nextInt(3)):new VZapper(r.nextInt(3)); 
					}
			
				for (int i=0; i<l.length;i++) {
					l[i]=new Laser(r.nextInt(6)+1);
				}				
				for (int i=0; i<c.length;i++) {
					c[i]=new Coin();
				}
				
				//game data
				d.setDistance(0);
				
				//other variables
				gameOver=false;
				valid= false;
				counter=0;
				menu=false;
		}

	public void menu(Graphics2D g2d) {
		//button rectangles
		g2d.drawImage(title,150,75,null);
		for (int i=0;i<button.length;i++) {
			g2d.setColor(Color.LIGHT_GRAY);
			g2d.fillRect(900,100+125*i,200,75);
			g2d.setColor(Color.BLACK);
			g2d.drawRect(900+1,100+125*i+1,200-3,75-3);
		}
		//static background
		velX=0;
		//black outline
		g2d.setFont(new Font("Times Roman", Font.BOLD, 31));
		g2d.drawString("SHOP",955-2,145);
		g2d.drawString("HELP",955-2,145+125);
		g2d.drawString("CREDITS",935-2,145+125*2);
		//text
		g2d.setColor(Color.WHITE);
		g2d.setFont(new Font("Times Roman", Font.BOLD, 30));
		g2d.drawString("SHOP",955,145);
		g2d.drawString("HELP",955,145+125);
		g2d.drawString("CREDITS",935,145+125*2);
		g2d.setFont(new Font("Times Roman", Font.BOLD, 40));
		g2d.drawString("PRESS ENTER TO START!",300,500);
	}
	public void shop(Graphics2D g2d) {
		g2d.setColor(new Color(185,200,225));
		g2d.fillRect(0,0,3000,3000);
		for (int i=0;i<button.length;i++) {
			g2d.setColor(Color.LIGHT_GRAY);
			g2d.fillRect(800,100+125*i,200,75);
			g2d.setColor(Color.BLACK);
			g2d.drawRect(800+1,100+125*i+1,197,72);
		}
		backButton(g2d);
		
		g2d.setFont(new Font("Times Roman", Font.PLAIN, 25));
		g2d.setColor(Color.WHITE);
		for (int i=0;i<shopImageDisplay.length;i++) {
			//icons and text on hover
			if (shopImageDisplay[i]) {
				g2d.drawImage(shopImage[i],250,150,null);
				g2d.drawString(shopText[0][i],250,450);
				g2d.drawString(shopText[1][i],250,500);
				c[0].setX(300);
				c[0].setY(427);
				c[0].setFrame(0);
				c[0].paint(g2d);
			}
			//print out shop descriptions and info
			g2d.drawString("Price: ",100,450);
			g2d.drawString("Description: ",100,500);
			g2d.drawString("Coin Magnet",830,145);
			g2d.drawString(coinMagnet?"OWNED":"",1010,145);
			g2d.drawString("Shield",860,270);
			g2d.drawString("x"+shield,1010,270);
			g2d.drawString("Quick Revive",830,390);
			g2d.drawString(revive?"Owned":"",1010,390);
			g2d.drawString("Coins: "+d.getTotalCoin(),75,250);
		}
	}
	public void help(Graphics2D g2d) {
		//screen text
		g2d.setColor(new Color(185,200,225));
		g2d.fillRect(325,100,575,300);
		g2d.setColor(Color.WHITE);
		g2d.setFont(new Font("Arial", Font.BOLD, 22));
		g2d.drawString("Press space to move up and release to fall down.",350,150);
		g2d.setFont(new Font("Times Roman", Font.PLAIN, 20));
		g2d.drawString("Avoid incoming obstacles. Hitting obstacles will end the game!",350,200);
		g2d.drawString("- Zappers can be horizontal or vertical",350,225);
		g2d.drawString("- Missiles have indicators and will follow your movement!",350,250);
		g2d.drawString("- Lasers will have a short warning, followed by a laser beam!",350,275);
		g2d.setFont(new Font("Arial", Font.BOLD, 22));
		g2d.drawString("Good luck JETPACKER and have a JOYFUL RIDE!",350,350);
		backButton(g2d);
	}
	public void credit(Graphics2D g2d) {
		//screen text
		g2d.setColor(new Color(185,200,225));
		g2d.fillRect(325,110,525,300);
		g2d.setColor(Color.WHITE);
		g2d.setFont(new Font("Arial", Font.PLAIN, 20));
		g2d.drawString("This wonderful and original game was brought to you",350,150);
		g2d.drawString("by the following amazing people:",350,175);
		g2d.drawString("- Kenneth Hung (hey that's me!)",350,225);
		g2d.drawString("- Bryce Pena",350,250);
		g2d.drawString("- Ammar Faridi",350,275);
		g2d.drawString("- Praveen Yakandawela",350,300);
		g2d.drawString("Thank you to all of our fans for your support!",350,350);
		g2d.drawString("Donate to our PayPal today!",350,375);
		backButton(g2d);
	}
	//paints button
	public void backButton(Graphics2D g2d) {
		g2d.setColor(Color.LIGHT_GRAY);
		g2d.fillRect(10,10,100,50);
		g2d.setColor(Color.BLACK);
		g2d.drawRect(11,11,97,47);
		g2d.setColor(Color.WHITE);
		g2d.setFont(new Font("Times Roman", Font.PLAIN, 20));
		g2d.drawString("BACK",33,40);
	}
}