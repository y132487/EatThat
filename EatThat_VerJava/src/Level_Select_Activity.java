import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.image.BufferedImage;
import javax.swing.JDialog;
import javax.swing.JFrame;

class Level_Select extends JDialog {

	private BufferedImage Background = MainImage.Load("img/level_menu.png");;
	private BufferedImage level_title = MainImage.Load("img/level_title.png");;
	private BufferedImage[] easy = { null, MainImage.Load("img/easy1.png"),
			MainImage.Load("img/easy2.png") };
	private BufferedImage[] hard = { null, MainImage.Load("img/hard1.png"),
			MainImage.Load("img/hard2.png") };
	private BufferedImage[] back = { null, MainImage.Load("img/back1.png"),
			MainImage.Load("img/back2.png") };

	private Image backbuff;
	private Graphics buff;
	private JFrame parentFrame;
	private int easyLotate = 1;
	private int hardLotate = 1;
	private int backLotate = 1;

	public Level_Select(JFrame parentFrame) {

		this.parentFrame = parentFrame;
		setLocation(1, 1);
		setTitle("EatThat_SelectLevel");
		setDefaultCloseOperation(JDialog.DO_NOTHING_ON_CLOSE);
		setLayout(new FlowLayout());
		setResizable(false);
		setPreferredSize(new Dimension(600, 500));
		addMouseListener(new MenuClick());
		addMouseMotionListener(new MenuMotion());
		pack();
		setVisible(true);
	}

	public void paint(Graphics g) {
		backbuff = createImage(900, 900);
		buff = backbuff.getGraphics();
		update(g);
	}

	public void update(Graphics g) {
		Draw_bg();
		Draw_level_title();
		Draw_easy(easyLotate);
		Draw_hard(hardLotate);
		Draw_back(backLotate);
		g.drawImage(backbuff, 0, 0, this);
	}

	public void Draw_bg() {
		buff.drawImage(Background, 0, 0, null);
		setPreferredSize(new Dimension(Background.getWidth(),
				Background.getHeight()));
	}

	public void Draw_level_title() {
		buff.drawImage(level_title, 150, 40, 300, 200, null);
	}

	public void Draw_easy(int i) {
		buff.drawImage(easy[i], 60, 250, 220, 82, null);
	}

	public void Draw_hard(int i) {
		buff.drawImage(hard[i], 320, 250, 220, 82, null);
	}

	public void Draw_back(int i) {
		buff.drawImage(back[i], 180, 370, 220, 82, null);
	}

	class MenuMotion implements MouseMotionListener {
		public void mouseDragged(MouseEvent e) {
		}

		public void mouseMoved(MouseEvent e) {
			int x = e.getX();
			int y = e.getY();

			if (x >= 60 && x <= 60 + 220 && y >= 250 && y <= 250 + 82) {
				easyLotate = 2;
				repaint();
			} else {
				easyLotate = 1;
				repaint();
			}
			if (x >= 320 && x <= 320 + 220 && y >= 250 && y <= 250 + 82) {
				hardLotate = 2;
				repaint();
			} else {
				hardLotate = 1;
				repaint();
			}
			if (x >= 180 && x <= 180 + 220 && y >= 370 && y <= 370 + 82) {
				backLotate = 2;
				repaint();
			} else {
				backLotate = 1;
				repaint();
			}
		}
	}

	class MenuClick implements MouseListener {

		public void mouseClicked(MouseEvent e) {
		}

		public void mouseEntered(MouseEvent e) {
		}

		public void mouseExited(MouseEvent e) {
		}

		public void mousePressed(MouseEvent e) {

		}

		public void mouseReleased(MouseEvent e) {
			int x = e.getX();
			int y = e.getY();

			if (x >= 60 && x <= 60 + 220 && y >= 250 && y <= 250 + 82) {
				sound_library.menu_stop();
				sound_library.caution_play();
				setVisible(false);
				new Single(30);
			}
			if (x >= 320 && x <= 320 + 220 && y >= 250 && y <= 250 + 82) {
				sound_library.menu_stop();
				sound_library.caution_play();
				setVisible(false);
				new Single(20);
			}
			if (x >= 180 && x <= 180 + 220 && y >= 370 && y <= 370 + 82) {
				sound_library.caution_play();
				parentFrame.setVisible(true);
				dispose();

			}

		}

	}

}
