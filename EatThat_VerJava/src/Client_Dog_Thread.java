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

class Client_Dog_Thread extends Thread {

	Client_Multi t_cmul = null;

	public Client_Dog_Thread(Client_Multi cli_mul) {
		this.t_cmul = cli_mul;
	}

	public void run() {

		try {
			while (true) {
				for (int i = 1; i <= 2; i++) {

					int add = 1600;
					int random = (int) (Math.random() * 2900);
					int time = random + add;
					int ready = 1000;
					System.out.println(time);
					t_cmul.repaint();
					t_cmul.caumove = i;
					if (t_cmul.caumove == 2) {

						sound_library.caution_play();
					}
					try {

						Thread.sleep(ready);

					} catch (InterruptedException t) {
					}
					t_cmul.caumove = 1;
					t_cmul.repaint();
					t_cmul.headmove = i;
					t_cmul.repaint();

					try {

						Thread.sleep(time - 1000);

					} catch (InterruptedException t) {
					}
				}
			}
		} catch (Exception e) {
		}
	}

}