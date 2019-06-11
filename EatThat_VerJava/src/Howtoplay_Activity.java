import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JDialog;
import javax.swing.JFrame;

class Howtoplay extends JDialog {

	private BufferedImage[] manual = { null, MainImage.Load("img/manual1.png"),
			MainImage.Load("img/manual2.png"),
			MainImage.Load("img/manual3.png") };
	private BufferedImage[] next = { null, MainImage.Load("img/next1.png"),
			MainImage.Load("img/next2.png") };
	private BufferedImage[] prev = { null, MainImage.Load("img/prev1.png"),
			MainImage.Load("img/prev2.png") };
	private BufferedImage[] back = { null, MainImage.Load("img/back1.png"),
			MainImage.Load("img/back2.png") };

	private Image backbuff;
	private Graphics buff;
	private Graphics page;
	private JFrame parentFrame;
	private int picLotate = 1;
	private int nextLotate = 1;
	private int prevLotate = 1;
	private int backLotate = 1;

	public Howtoplay(JFrame parentFrame) {

		this.parentFrame = parentFrame;
		setLocation(1, 1);
		setTitle("EatThat_HowToPlay");
		setDefaultCloseOperation(JDialog.DO_NOTHING_ON_CLOSE);
		setLayout(new FlowLayout());
		setResizable(false);
		setPreferredSize(new Dimension(810, 760));
		addMouseListener(new MenuClick());
		addMouseMotionListener(new MenuMotion());
		pack();
		setVisible(true);
	}

	public void paint(Graphics g) {
		backbuff = createImage(900, 900);
		buff = backbuff.getGraphics();
		page = backbuff.getGraphics();
		update(g);
	}

	public void update(Graphics g) {
		Draw_manual(picLotate);
		Draw_next(nextLotate);
		Draw_prev(prevLotate);
		Draw_back(backLotate);
		Draw_pagetext(picLotate);
		g.drawImage(backbuff, 0, 0, this);
	}

	public void Draw_manual(int i) {
		buff.drawImage(manual[i], 0, 0, null);
		setPreferredSize(new Dimension(manual[i].getWidth(),
				manual[i].getHeight()));
	}

	public void Draw_prev(int i) {
		buff.drawImage(prev[i], 50, 670, 180, 62, null);
	}

	public void Draw_next(int i) {
		buff.drawImage(next[i], 230, 670, 180, 62, null);
	}

	public void Draw_back(int i) {
		buff.drawImage(back[i], 600, 670, 180, 62, null);
	}

	public void Draw_pagetext(int i) {
		Font font = new Font("±Ã¼­Ã¼", Font.TYPE1_FONT, 40);
		page.setFont(font);
		page.setColor(Color.black);
		page.drawString("(" + i + "/3)", 450, 720);
	}

	class MenuMotion implements MouseMotionListener {
		public void mouseDragged(MouseEvent e) {
		}

		public void mouseMoved(MouseEvent e) {
			int x = e.getX();
			int y = e.getY();

			if (x >= 50 && x <= 50 + 180 && y >= 670 && y <= 670 + 62) {
				prevLotate = 2;
				repaint();
			} else {
				prevLotate = 1;
				repaint();
			}
			if (x >= 230 && x <= 230 + 180 && y >= 670 && y <= 670 + 62) {
				nextLotate = 2;
				repaint();
			} else {
				nextLotate = 1;
				repaint();
			}
			if (x >= 600 && x <= 600 + 180 && y >= 670 && y <= 670 + 62) {
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

			if (x >= 50 && x <= 50 + 180 && y >= 670 && y <= 670 + 62) {
				picLotate -= 1;
				if (picLotate <= 1) {
					picLotate = 1;
				}
				repaint();
			}
			if (x >= 230 && x <= 230 + 180 && y >= 670 && y <= 670 + 62) {
				picLotate += 1;
				if (picLotate >= 3) {
					picLotate = 3;
				}
				repaint();
			}
			if (x >= 600 && x <= 600 + 180 && y >= 670 && y <= 670 + 62) {
				sound_library.caution_play();
				parentFrame.setVisible(true);
				dispose();
			}

		}

	}
}
