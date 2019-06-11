import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.image.BufferedImage;
import javax.swing.*;

class Loser extends JFrame {

	private BufferedImage losepic = MainImage.Load("img/losepic.png");;
	private BufferedImage[] retry = { null, MainImage.Load("img/retry1.png"),
			MainImage.Load("img/retry2.png") };
	private BufferedImage[] menu = { null, MainImage.Load("img/menu1.png"),
			MainImage.Load("img/menu2.png") };
	private Image backbuff;
	private Graphics buff;
	private int retryLotate = 1;
	private int menuLotate = 1;
	private int difficult = 0;

	public Loser(int n) {
		difficult = n;
		sound_library.loser_play();
		setLocation(1, 1);
		setTitle("Looser");
		setLayout(new FlowLayout());
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setResizable(false);
		setPreferredSize(new Dimension(420, 608));
		addMouseMotionListener(new ButtonMotion());
		addMouseListener(new ButtonClick());
		pack();

		setVisible(true);

	}

	public void paint(Graphics g) {

		backbuff = createImage(900, 900);
		buff = backbuff.getGraphics();
		update(g);
	}

	public void update(Graphics g) {
		Draw_winpic();
		Draw_retry(retryLotate);
		Draw_menu(menuLotate);
		g.drawImage(backbuff, 0, 0, this);

	}

	public void Draw_winpic() {
		buff.drawImage(losepic, 0, 0, null);
		setPreferredSize(new Dimension(losepic.getWidth(), losepic.getHeight()));
	}

	public void Draw_retry(int i) {
		buff.drawImage(retry[i], 20, 450, 180, 67, null);
	}

	public void Draw_menu(int i) {
		buff.drawImage(menu[i], 220, 450, 180, 67, null);
	}

	class ButtonMotion implements MouseMotionListener {

		public void mouseDragged(MouseEvent e) {
		}

		public void mouseMoved(MouseEvent e) {
			int x = e.getX();
			int y = e.getY();

			if (x >= 20 && x <= 20 + 180 && y >= 450 && y <= 450 + 67) {
				retryLotate = 2;
				repaint();
			} else {
				retryLotate = 1;
				repaint();
			}
			if (x >= 220 && x <= 220 + 180 && y >= 450 && y <= 450 + 67) {
				menuLotate = 2;
				repaint();
			} else {
				menuLotate = 1;
				repaint();
			}

		}

	}

	class ButtonClick implements MouseListener {

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
			if (x >= 20 && x <= 20 + 180 && y >= 450 && y <= 450 + 67) {
				sound_library.loser_stop();
				setVisible(false);
				new Single(difficult);
			}
			if (x >= 220 && x <= 220 + 180 && y >= 450 && y <= 450 + 67) {
				sound_library.loser_stop();
				setVisible(false);
				new Main_Activity();
			}

		}

	}

}
