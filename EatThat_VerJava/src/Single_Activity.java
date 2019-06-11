import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.awt.image.ImageObserver;
import java.io.File;
import java.io.IOException;
import java.util.Observable;
import java.util.Observer;
import javax.imageio.ImageIO;
import javax.swing.*;

class Single extends JFrame {

	private BufferedImage Background = MainImage.Load("img/BG.jpg");
	private BufferedImage[] caution = { null,
			MainImage.Load("img/caution1.png"),
			MainImage.Load("img/caution2.png") };
	private BufferedImage[] doghead = { null, MainImage.Load("img/dog1.png"),
			MainImage.Load("img/dog2.png") };
	private BufferedImage[] human = { null, MainImage.Load("img/human1.png"),
			MainImage.Load("img/human2.png") };

	private Image backbuff;
	private Graphics buff;
	private Graphics hungbar;
	private Graphics foodbar;
	private Graphics hungtext;
	private Graphics foodtext;
	private Thread aThread1;
	private Thread aThread2;
	private barThread bar = new barThread();
	private dogThread dog = new dogThread();
	private int hungry_bar = 670;
	private int food_bar = 670;
	public int guymove = 1;
	public int headmove = 1;
	public int caumove = 1;
	private int difficulty = 0;
	public boolean judge = false; // true 상태에 키를 누를경우 패배

	public Single(int n) {
		difficulty = n;
		int music = (int) (Math.random() * 4) + 1;
		if (music == 1) {
			sound_library.bgm1.loop();
		} else if (music == 2) {
			sound_library.bgm2.loop();
		} else if (music == 3) {
			sound_library.bgm3.loop();
		} else if (music == 4) {
			sound_library.bgm4.loop();
		}
		setLocation(1, 1);
		setTitle("개밥훔쳐먹기_싱글플레이");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLayout(new FlowLayout());
		setResizable(false);
		setPreferredSize(new Dimension(800, 650));
		pack();
		addKeyListener(new KeyListen());

		setVisible(true);
	}

	public void paint(Graphics g) {

		backbuff = createImage(900, 900);
		buff = backbuff.getGraphics();
		hungbar = backbuff.getGraphics();
		foodbar = backbuff.getGraphics();
		hungtext = backbuff.getGraphics();
		foodtext = backbuff.getGraphics();
		update(g);
	}

	public void update(Graphics g) {

		Draw_bg();
		Draw_caution(caumove);
		Draw_doghead(headmove);
		Draw_human(guymove);
		Draw_hungry(hungry_bar);
		Draw_food(food_bar);
		Draw_hungrytext();
		Draw_foodtext();

		bar.start();
		dog.start();

		if (hungry_bar >= 670) {
			hungry_bar = 670;
		} else if (hungry_bar <= 0) {
			sound_library.bgm1.stop();
			sound_library.bgm2.stop();
			sound_library.bgm3.stop();
			sound_library.bgm4.stop();
			sound_library.hungry_play();
			aThread1.stop();
			aThread2.stop();
			try {
				Thread.sleep(2000);
			} catch (Exception a) {
			}
			setVisible(false);
			new Loser(difficulty);
		} else if (headmove == 2) {
			judge = true;
		} else if (headmove == 1) {
			judge = false;
		}

		if (food_bar <= 0) {
			sound_library.bgm1.stop();
			sound_library.bgm2.stop();
			sound_library.bgm3.stop();
			sound_library.bgm4.stop();
			sound_library.pangpare.play();
			aThread1.stop();
			aThread2.stop();
			try {
				Thread.sleep(2000);
			} catch (Exception a) {
			}
			setVisible(false);
			new Winner(difficulty);
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

	public void Draw_hungry(int a) {
		if (hungry_bar <= 150) {
			hungbar.setColor(Color.red);
		} else {
			hungbar.setColor(Color.blue);
		}
		hungbar.fillRect(95, 602, a, 20);

	}

	public void Draw_food(int a) {
		if (food_bar <= 150) {
			foodbar.setColor(Color.red);
		} else {
			foodbar.setColor(Color.blue);
		}
		foodbar.fillRect(95, 625, a, 20);
	}

	public void Draw_hungrytext() {
		Font font = new Font("궁서체", Font.TYPE1_FONT, 20);
		hungtext.setFont(font);
		hungtext.setColor(Color.blue);
		hungtext.drawString("HUNGRY", 10, 620);
	}

	public void Draw_foodtext() {
		Font font = new Font("궁서체", Font.TYPE1_FONT, 20);
		foodtext.setFont(font);
		foodtext.setColor(Color.blue);
		foodtext.drawString("FEED", 10, 640);
	}

	public void Draw_bar() {
		hungry_bar -= 1;

		try {
			Thread.sleep(difficulty);
		} catch (InterruptedException e) {
		}
		repaint();
	}

	public void Draw_Explain() {

		buff.fillRect(100, 200, 100, 100);
		buff.drawString("훔쳐먹는 사람", 100, 350);
		// buff.fillRect(600,200,100,100);
		buff.drawString("            개", 600, 350);
		buff.drawString(
				"설명 - [스페이스바]를 연타해서 HUNGRY 게이지가 0이 되지 않도록 하고 FOOD 게이지가 사라질 때 까지 연타한다",
				100, 450);
		buff.drawString("개가 딴 곳을 보고 있을 때만 [스페이스바]를 눌러야하며 개가 바라볼 땐 가만히 있어야한다.",
				100, 480);
		buff.drawString(
				"HUNGRY 게이지가 0이 되거나 개한테 걸릴 경우 게임오버, FOOD 게이지가 0이 되면 승리", 100,
				510);
	}

	class barThread implements Runnable {

		public void start() {

			if (aThread1 == null) {
				aThread1 = new Thread(this);
				aThread1.start();

			}

		}

		public void stop() {
			if (aThread1 != null) {
				aThread1 = null;

			}
		}

		public void run() {

			while (true) {
				Draw_bar();
			}
		}

	}

	class dogThread implements Runnable {
		public void start() {

			if (aThread2 == null) {
				aThread2 = new Thread(this);
				aThread2.start();

			}

		}

		public void stop() {
			if (aThread2 != null) {
				aThread2 = null;

			}
		}

		public void run() {
			while (true) {
				for (int i = 1; i <= 2; i++) {

					int add = 1600;
					int random = (int) (Math.random() * 2900);
					int time = random + add;
					int ready = 1000;
					System.out.println(time);
					caumove = i;
					if (caumove == 2) {
						sound_library.caution_play();
					}
					try {

						Thread.sleep(ready);

					} catch (InterruptedException e) {
					}
					caumove = 1;
					headmove = i;

					try {

						Thread.sleep(time - 1000);

					} catch (InterruptedException e) {
					}
					// headmove=1;

				}

			}
		}

	}

	class KeyListen implements KeyListener {
		public void keyPressed(KeyEvent e) {
			if (e.getKeyCode() == e.VK_SPACE) {
				if (judge) {
					sound_library.bgm1.stop();
					sound_library.bgm2.stop();
					sound_library.bgm3.stop();
					sound_library.bgm4.stop();
					sound_library.dogbarking_play();
					aThread1.stop();
					aThread2.stop();
					try {
						Thread.sleep(2000);

					} catch (Exception a) {
					}
					setVisible(false);
					new Loser(difficulty);

				}/*
				 * else{ sound_library.eating_play(); }
				 */
				guymove = 2;
			}

		}

		@Override
		public void keyReleased(KeyEvent e) {
			if (e.getKeyCode() == e.VK_SPACE) {
				sound_library.eating_play();
				hungry_bar += 10;
				food_bar -= 5;
				guymove = 1;

			}

		}

		@Override
		public void keyTyped(KeyEvent e) {

		}
	}

}
