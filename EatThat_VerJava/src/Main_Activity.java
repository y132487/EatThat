import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.*;

public class Main_Activity extends JFrame {

	private BufferedImage Background = MainImage.Load("img/mainmenu.png");; // ���
	private BufferedImage[] single = { null, MainImage.Load("img/single1.png"),
			MainImage.Load("img/single2.png") };
	private BufferedImage[] multi = { null, MainImage.Load("img/multi1.png"),
			MainImage.Load("img/multi2.png") };
	private BufferedImage[] how = { null, MainImage.Load("img/how1.png"),
			MainImage.Load("img/how2.png") };

	private Image backbuff;
	private Graphics buff;
	public int singleLotate = 1;
	public int multiLotate = 1;
	public int howLotate = 1;

	static Main_Activity Menu; //���ΰ�ü�� ����ƽ���� �����Ͽ� Ŭ������ ��ȣ������ �����ϵ��� �Ͽ���

	Server server;
	Howtoplay how_dialog;
	Server_Lobby s_lobby;
	Client_Lobby c_lobby;

	public Main_Activity() {

		sound_library.menu_loop();
		setLocation(1, 1);
		setTitle("�������ĸԱ�_���θ޴�");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLayout(new FlowLayout());
		setResizable(false);
		setVisible(true);
		setPreferredSize(new Dimension(900, 650));
		addMouseListener(new MenuClick());
		addMouseMotionListener(new MenuMotion());
		pack();
	}

	public void paint(Graphics g) {

		backbuff = createImage(900, 900);
		buff = backbuff.getGraphics();
		update(g);
	}

	public void update(Graphics g) {

		Draw_Mainmenu();
		Draw_single(singleLotate);
		Draw_multi(multiLotate);
		Draw_how(howLotate);
		g.drawImage(backbuff, 0, 0, this);

	}

	public void Draw_Mainmenu() {
		buff.drawImage(Background, 0, 0, null);
		setPreferredSize(new Dimension(Background.getWidth(),
				Background.getHeight()));
	}

	public void Draw_single(int i) {
		buff.drawImage(single[i], 60, 240, 350, 130, null);
	}

	public void Draw_multi(int i) {
		buff.drawImage(multi[i], 60, 370, 350, 130, null);
	}

	public void Draw_how(int i) {
		buff.drawImage(how[i], 60, 500, 350, 130, null);
	}

	class MenuMotion implements MouseMotionListener {

		@Override
		public void mouseDragged(MouseEvent e) {
		}

		@Override
		public void mouseMoved(MouseEvent e) {
			int x = e.getX();
			int y = e.getY();

			if (x >= 60 && x <= 60 + 350 && y >= 240 && y <= 240 + 130) {
				singleLotate = 2;
				repaint();
			} else {
				singleLotate = 1;
				repaint();
			}
			if (x >= 60 && x <= 60 + 350 && y >= 370 && y <= 370 + 130) {
				multiLotate = 2;
				repaint();
			} else {
				multiLotate = 1;
				repaint();
			}
			if (x >= 60 && x <= 60 + 350 && y >= 500 && y <= 500 + 130) {
				howLotate = 2;
				repaint();
			} else {
				howLotate = 1;
				repaint();
			}
		}

	}

	class MenuClick implements MouseListener {

		@Override
		public void mouseClicked(MouseEvent e) {
		}

		@Override
		public void mouseEntered(MouseEvent e) {
		}

		@Override
		public void mouseExited(MouseEvent e) {
		}

		@Override
		public void mousePressed(MouseEvent e) {
			int x = e.getX();
			int y = e.getY();

			if (x >= 60 && x <= 60 + 350 && y >= 240 && y <= 240 + 130) {

			}
			if (x >= 60 && x <= 60 + 350 && y >= 370 && y <= 370 + 130) {

			}
			if (x >= 60 && x <= 60 + 350 && y >= 500 && y <= 500 + 130) {

			}

		}

		@Override
		public void mouseReleased(MouseEvent e) {
			int x = e.getX();
			int y = e.getY();

			if (x >= 60 && x <= 60 + 350 && y >= 240 && y <= 240 + 130) {
				sound_library.caution_play();
				Level_Select level_dialog = new Level_Select(Main_Activity.this);
				level_dialog.setVisible(true);
				setVisible(false);
				// new Level_Select();

			}
			//���⼭���� ��Ƽ����
			if (x >= 60 && x <= 60 + 350 && y >= 370 && y <= 370 + 130) {
				int state;
				String name = "";
				String IP = "127.0.0.1";

				String[] buttons = { "SERVER", "CLIENT" };
				JOptionPane option = new JOptionPane();
				state = option.showOptionDialog(null, "Select your Socket",
						"��Ƽ�÷��� ����", JOptionPane.YES_NO_OPTION,
						JOptionPane.QUESTION_MESSAGE, null, buttons, "");
				System.out.println("state : " + state);
				if (state != -1) {
					if (state == 1) {
						IP = JOptionPane.showInputDialog(null, "IP Adress");
					}
					while (name.length() == 0 || name.length() > 4) {
						name = JOptionPane.showInputDialog(null,
								"Nickname(less than four letters)");
						if (name == null) {
							break;
						}
					}

					if (name != null) {
						if (state == 0) {
							sound_library.menu_stop();
							setVisible(false);
							/*
							 * ser_mul=new Server_Multi(name); server=new
							 * Server(ser_mul,name); server.start();
							 * ser_mul.setSocket(server);
							 * 
							 * Server_Dog_Thread t_sdog=new
							 * Server_Dog_Thread(ser_mul); t_sdog.start();
							 */

							s_lobby = new Server_Lobby(name);
							Server server = new Server(s_lobby, name);//����� �̸��� ǥ���ϱ� ���� �̸��� ����
							server.start();
							s_lobby.setSocket(server);

						} else if (state == 1) {
							sound_library.menu_stop();
							setVisible(false);
							/*
							 * cli_mul=new Client_Multi(IP,name);
							 * Client_Dog_Thread t_cdog=new
							 * Client_Dog_Thread(cli_mul); t_cdog.start();
							 */

							c_lobby = new Client_Lobby(IP, name);
							//Ŭ���̾�Ʈ�� ���⼭ �ٷ� Ŭ���̾�Ʈ�� ���� �� �ʿ� ����
							//Ŭ���̾�Ʈ �κ񿡼� ���� ������

						}
					}
				}
			}
			if (x >= 60 && x <= 60 + 350 && y >= 500 && y <= 500 + 130) {
				sound_library.caution_play();
				how_dialog = new Howtoplay(Main_Activity.this);
				setVisible(false);
			}
			if (x >= 765 && x <= 765 + 20 && y >= 275 && y <= 275 + 20) {
				Creator c_egg = new Creator(Main_Activity.this);

			}
			if (x >= 650 && x <= 650 + 20 && y >= 275 && y <= 275 + 20) {
				ProfileEGG p_egg = new ProfileEGG(Main_Activity.this);
				setVisible(false);
			}
		}

	}

	public static void main(String[] args) {
		Menu = new Main_Activity();
	}

}
