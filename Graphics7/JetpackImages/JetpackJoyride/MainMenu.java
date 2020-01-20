package JetpackJoyride;
import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileInputStream;
import java.io.IOException;

import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.UIManager;

import JetpackJoyride.*;
import sun.audio.AudioData;
import sun.audio.AudioPlayer;
import sun.audio.AudioStream;
import sun.audio.ContinuousAudioDataStream;

public class MainMenu {
	static int soundEffect=1;
	static JFrame frame;
	static ContinuousAudioDataStream loop=null;
	static AudioPlayer AP=AudioPlayer.player;

	public static void main(String[] args) throws InterruptedException{
		menu();}
	

	public static class AL implements ActionListener{
		public final void actionPerformed(ActionEvent e) {
			music();
		}
	}
	
	public static void music() {
		//AudioPlayer AP=AudioPlayer.player;
		AudioStream AS;
		AudioData AD;
		
		//ContinuousAudioDataStream loop=null;
		//For sound without looping just do
		//AudioDataStream variableName=null;
		
		try {
			AS=new AudioStream(new FileInputStream("tSound.wav"));
			AD=AS.getData();
			loop=new ContinuousAudioDataStream(AD);
			//variableName=new AudioDataStream(AD);
		}catch(IOException error) {
			System.out.print("No sound for you.");
		}
		
		AP.start(loop);
		if(soundEffect==0) {
			AP.stop(loop);}
		//AP.start(variableName);
	}
	
	public static void menu() {
		music();
		Font font=new Font("Copperplate Gothic Bold",Font.PLAIN,0);
		frame = new JFrame("Main Menu");
		frame.setBackground(UIManager.getColor("Button.focus"));
		frame.setSize(852,480);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
		ImageIcon background=new ImageIcon("D:\\testCode\\MainMenu\\src\\backgroundImg.jpeg");
		frame.setLayout(null);
		JLabel back=new JLabel(background);
		back.setBounds(0, 0, 846, 480);
		back.setForeground(Color.RED);
		back.setFont(new Font("Century Schoolbook", Font.PLAIN, 98));
		back.setText("");
		back.setLayout(new BoxLayout(back, BoxLayout.Y_AXIS));
		frame.add(back);
		
		JLabel label=new JLabel("ARCADE");
		label.setAlignmentX(Component.CENTER_ALIGNMENT);
		label.setForeground(new Color(148, 50, 251));
		label.setFont(new Font("BankGothic Md BT", Font.BOLD, 72));
		back.add(label);
		
		JLabel space=new JLabel("  ");
		back.add(space);
		space.setAlignmentX(Component.CENTER_ALIGNMENT);
		space.setFont (font.deriveFont (25.0f));
		
		JLabel space1=new JLabel("  ");
		back.add(space1);
		space1.setAlignmentX(Component.CENTER_ALIGNMENT);
		space1.setFont (font.deriveFont (25.0f));
		
		//Add button #1
		JButton button=new JButton(" Option 1: Space Shooter ");
		button.setToolTipText("This button is for Space Shooter");
		back.add(button);
		button.setAlignmentX(Component.CENTER_ALIGNMENT);
		//Set button font size
		button.setFont (font.deriveFont (25.0f));
		
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.setVisible(false);
				SpaceShooter();
			}
		});
		
		
		//Add button #2
		JButton button1=new JButton("Option 2: Jetpack Joyride");
		button1.setToolTipText("This button is for Jetpack Joyride");
		back.add(button1);
		button1.setAlignmentX(Component.CENTER_ALIGNMENT);
		//Set button font size
		button1.setFont (font.deriveFont (25.0f));
		
		button1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.setVisible(false);
				try {
					JetpackJoyride();
				} catch (InterruptedException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		
		
		//Add button #3
		JButton button2=new JButton("     Option 3: Goalkeeper     ");
		button2.setToolTipText("This button is for Goalkeeper Simulator");
		back.add(button2);
		button2.setAlignmentX(Component.CENTER_ALIGNMENT);
		//Set button size
		button2.setFont (font.deriveFont (25.0f));
		
		button2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.setVisible(false);
				Goalkeeper();
			}
		});
		
		
		//Add button #4
		JButton button3=new JButton(" Option 4: Super Meat Boy ");
		button3.setToolTipText("This option is for Super Meat Boy");
		back.add(button3);
		button3.setAlignmentX(Component.CENTER_ALIGNMENT);
		//Set button font size
		button3.setFont (font.deriveFont (25.0f));
		
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.setVisible(false);
				SuperMeatBoy();
			}
		});
		  
		
		//Add button #5
		JButton button4=new JButton("                          Exit                          ");
		button4.setToolTipText("This program is for Battleship"); 
		back.add(button4);
		button4.setAlignmentX(Component.CENTER_ALIGNMENT);
		//Set button font size
		button4.setFont (font.deriveFont (25.0f));
		
		button4.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.setVisible(false);
				System.out.println("Thanks for coming!");
				AP.stop(loop);
			}
		});
		
		frame.setLocationRelativeTo(null);
		frame.setResizable(false);
	}
	
	public static void SpaceShooter() {
		System.out.println("Welcome to spaceshooter");
		AP.stop(loop);
	}
	
	public static void JetpackJoyride() throws InterruptedException {
		Test.main(null);

		AP.stop(loop);
	}

	public static void Goalkeeper() {
		AP.stop(loop);
	}

	public static void SuperMeatBoy() {
		AP.stop(loop);
	}
	
}
