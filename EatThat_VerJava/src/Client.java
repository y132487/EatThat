import java.io.*;
import java.net.*;
import javax.swing.*;

class Client extends Thread {

	boolean m_isConnected = false;
	Socket m_server = null;
	Client_Lobby m_clobby = null;
	Client_Multi m_cmul = null;
	PrintWriter out;
	InputStream in;

	public Client(Socket server, Client_Lobby cli_lobby, String name) {

		this.m_server = server;
		this.m_clobby = cli_lobby;

		this.send("3", name);

	}

	public void send(String type, String name) {
		try {

			out = new PrintWriter(m_server.getOutputStream(), true);
			out.println(type + "" + name); // substring 0, 1, 2 �غ��

		} catch (Exception e) {

			JOptionPane.showMessageDialog(m_clobby, "������ ���� ����. ������");
			System.exit(-1);

		}
	}

	public void closeClient() {
		try {
			out.close();
			in.close();
			m_server.close();
			System.out.println("Ŭ������");

		} catch (IOException e) {
		}
	}

	public void run() {
		while (m_server.isConnected()) {

			try {
				in = m_server.getInputStream();

				int leng = 0;
				if ((leng = in.available()) != 0) {

					byte[] data1 = new byte[leng];
					in.read(data1);
					String str = new String(data1);
					int type = Integer.parseInt(str.substring(0, 1));
					String text = str.substring(1);

					if (type == 1) {
						Main_Activity.Menu.c_lobby.cli_mul.enemy_bar -= 5; //������ �� ������ static���� ���θ޴��� �̸� �����س��ұ� ������ �� ���� ��ü�鿡�� ������ ����
					} else if (type == 2) {
						Main_Activity.Menu.c_lobby.cli_mul.enemy_bar += 55;
					} else if (type == 3) {
						m_clobby.enemyname = text;
					} else if (type == 4) {
						m_clobby.m_log.append(text);
					} else if (type == 5) {
						m_clobby.startGame();

					}

				} else {
					yield();
				}
			} catch (Exception e) {
				JOptionPane.showMessageDialog(m_clobby, "������ ���� ����. ������");
				System.exit(-1);
			}
		}

	}

}
