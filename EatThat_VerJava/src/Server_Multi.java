import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.awt.image.ImageObserver;
import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Observable;
import java.util.Observer;
import javax.imageio.ImageIO;
import javax.swing.*;

class Server_Multi extends JFrame {

	private BufferedImage Background = MainImage.Load("img/BG.jpg");;
	private BufferedImage[] caution = { null,
			MainImage.Load("img/caution1.png"),
			MainImage.Load("img/caution2.png") };
	private BufferedImage[] doghead = { null, MainImage.Load("img/dog1.png"),
			MainImage.Load("img/dog2.png") };
	private BufferedImage[] human = { null, MainImage.Load("img/human1.png"),
			MainImage.Load("img/human2.png") };

	public Image backbuff;
	public Graphics buff;
	public Graphics enebar;
	public Graphics foodbar;
	public Graphics enetext;
	public Graphics foodtext;
	public int enemy_bar = 670;
	public int food_bar = 670;
	public int guymove = 1;
	public int headmove = 1;
	public int caumove = 1;
	public boolean judge = false; // true 상태에 키를 누를경우 패배
	public String myname;
	public String enemyname;

	Server_Lobby s_lobby = null;
	Server socket = null;
	Server_Dog_Thread t_sdog = new Server_Dog_Thread(this);

	public Server_Multi(String name, String enemyname, Server_Lobby s_lobby) {
		this.s_lobby = s_lobby;
		this.myname = name;
		this.enemyname = enemyname;
		setLocation(1, 1);
		setTitle("개밥훔쳐먹기_서버");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLayout(new FlowLayout());
		setResizable(false);
		setPreferredSize(new Dimension(800, 650));
		pack();
		addKeyListener(new KeyListen());
		setVisible(true);

		t_sdog.start();
	}

	public void setSocket(Server socket) {
		this.socket = socket;
		socket.send("5", "");

	}

	public void paint(Graphics g) {

		backbuff = createImage(900, 900);
		buff = backbuff.getGraphics();
		enebar = backbuff.getGraphics();
		foodbar = backbuff.getGraphics();
		enetext = backbuff.getGraphics();
		foodtext = backbuff.getGraphics();

		update(g);

	}

	public void update(Graphics g) {

		Draw_bg();
		Draw_caution(caumove);
		Draw_doghead(headmove);
		Draw_human(guymove);
		Draw_enemy(enemy_bar);
		Draw_food(food_bar);
		Draw_enemyname();
		Draw_myname();

		if (enemy_bar >= 670) {
			enemy_bar = 670;
		} else if (enemy_bar <= 0) {
			t_sdog.stop();
			JOptionPane.showMessageDialog(this, "패배하였습니다. 대기실로 이동");

			s_lobby.setVisible(true);
			dispose();
		}

		if (food_bar >= 670) {
			food_bar = 670;
		} else if (food_bar <= 0) {
			t_sdog.stop();
			JOptionPane.showMessageDialog(this, "승리하였습니다. 대기실로 이동");

			s_lobby.setVisible(true);
			dispose();

		}

		g.drawImage(backbuff, 0, 0, this);

	}

	public void Draw_doghead(int i) {
		buff.drawImage(doghead[i], 450, 340, 250, 250, null);
	}

	public void Draw_human(int i) {
		buff.drawImage(human[i], 70, 350, 250, 250, null);
	}

	public void Draw_caution(int i) {
		buff.drawImage(caution[i], 410, 310, 120, 120, null);
	}

	public void Draw_bg() {
		buff.drawImage(Background, 0, 0, null);
		setPreferredSize(new Dimension(Background.getWidth(),
				Background.getHeight()));
	}

	public void Draw_enemy(int a) {

		if (enemy_bar <= 150) {
			enebar.setColor(Color.red);
		} else {
			enebar.setColor(Color.blue);
		}
		enebar.fillRect(95, 602, a, 20);

	}

	public void Draw_food(int a) {
		if (food_bar <= 150) {
			foodbar.setColor(Color.red);
		} else {
			foodbar.setColor(Color.blue);
		}
		foodbar.fillRect(95, 625, a, 20);
	}

	public void Draw_enemyname() {

		Font font = new Font("궁서체", Font.TYPE1_FONT, 20);
		enetext.setFont(font);
		enetext.setColor(Color.magenta);
		enetext.drawString(enemyname, 10, 620);
	}

	public void Draw_myname() {
		Font font = new Font("궁서체", Font.TYPE1_FONT, 20);
		foodtext.setFont(font);
		foodtext.setColor(Color.blue);
		foodtext.drawString(myname, 10, 640);
	}

	class KeyListen implements KeyListener {
		public void keyPressed(KeyEvent e) {
			if (e.getKeyCode() == e.VK_SPACE) {

				guymove = 2;
				repaint();
			}

		}
		//소켓으로 데이터 보내기 로직
		@Override
		public void keyReleased(KeyEvent e) {
			if (e.getKeyCode() == e.VK_SPACE) {
				sound_library.eating_play();
				switch (headmove) {

				case 1:
					socket.send("1", ""); //채팅이 아니기 때문에 텍스트 부분은 넣지 않고 규약만 보낸다
					food_bar -= 5;
					break;

				case 2:
					sound_library.dogbarking_play();
					socket.send("2", "");
					food_bar += 55;
					break;

				}
				guymove = 1;
				repaint();

			}

		}

		@Override
		public void keyTyped(KeyEvent e) {

		}
	}

}
