import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;

import javax.swing.JDialog;
import javax.swing.JFrame;

class Creator extends JDialog {

	private BufferedImage Programmer = MainImage.Load("img/Programmer.jpg");;

	private Image backbuff;
	private Graphics buff;
	private JFrame parentFrame;

	public Creator(JFrame parentFrame) {

		this.parentFrame = parentFrame;
		setLocation(1, 1);
		setTitle("Creator");
		setDefaultCloseOperation(JDialog.DO_NOTHING_ON_CLOSE);
		setLayout(new FlowLayout());
		setResizable(false);
		setPreferredSize(new Dimension(250, 340));
		addMouseListener(new Click());
		pack();
		setModal(true);
		setVisible(true);

	}

	public void paint(Graphics g) {
		backbuff = createImage(400, 400);
		buff = backbuff.getGraphics();
		update(g);
	}

	public void update(Graphics g) {
		Draw_bg();
		g.drawImage(backbuff, 0, 0, this);

	}

	public void Draw_bg() {
		buff.drawImage(Programmer, 0, 0, null);
		setPreferredSize(new Dimension(Programmer.getWidth(),
				Programmer.getHeight()));
	}

	class Click implements MouseListener {

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

			if (x >= 10 && x <= 250 && y >= 10 && y <= 340) {
				dispose();

			}

		}

	}
}
