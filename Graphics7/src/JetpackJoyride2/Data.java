package JetpackJoyride2;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;

public class Data extends MovingObject {
	//initial values of each field
	int distance=0;
	int coin=0;
	int totalCoin=0;
	int highScore=0;
	
	//set and get methods to be accessed from gameObject
	public int getDistance() {
		return distance;
	}

	public void setDistance(int distance) {
		this.distance = distance;
	}

	public int getHighScore() {
		return highScore;
	}

	public void setHighScore(int highScore) {
		this.highScore = highScore;
	}

	public int getCoin() {
		return coin;
	}

	public void setCoin(int coins) {
		coin = coins;
	}

	//change distance and high score as the game goes on
	public void move(boolean gameOver,int vel) {
		if (gameOver==false) {
			distance++;
			}
		//if current distance is the high score, update the hs
		if (distance>=highScore) {
			highScore=distance;
		}
	}

	//display text on screen
	public void paint(Graphics g) {		
		Graphics2D g2d=(Graphics2D) g;
		g2d.setFont(new Font("Times Roman", Font.PLAIN, 25));
		g2d.setColor(Color.WHITE);
		g2d.drawString("Distance: "+distance, 10, 20);
		g2d.setFont(new Font("Times Roman", Font.PLAIN, 20));
		g2d.drawString("High Score: "+highScore, 10, 45);
		g2d.drawString("Coins: "+coin, 10, 75);
	}
	//get/set methods for totalcoin
	public int getTotalCoin() {
		return totalCoin;
	}
	public void setTotalCoin(int totalCoin) {
		this.totalCoin = totalCoin;
	}
}
