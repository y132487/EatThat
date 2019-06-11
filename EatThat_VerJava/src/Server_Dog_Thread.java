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

class Server_Dog_Thread extends Thread {

	Server_Multi t_smul = null;

	public Server_Dog_Thread(Server_Multi ser_mul) {
		this.t_smul = ser_mul;
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
					t_smul.repaint();
					t_smul.caumove = i;
					if (t_smul.caumove == 2) {

						sound_library.caution_play();
					}
					try {

						Thread.sleep(ready);

					} catch (InterruptedException t) {
					}
					t_smul.caumove = 1;
					t_smul.repaint();
					t_smul.headmove = i;
					t_smul.repaint();

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