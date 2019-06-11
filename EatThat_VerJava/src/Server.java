import java.io.*;
import java.net.*;
import javax.swing.*;

class Server extends Thread {

	ServerSocket m_server = null;
	Socket m_client = null;
	Server_Lobby m_slobby = null;
	Server_Multi m_smul = null;
	PrintWriter out;
	InputStream in;

	public Server(Server_Lobby s_lobby, String name) {

		this.m_slobby = s_lobby;

		try {
			m_server = new ServerSocket(3333);
			m_client = m_server.accept();
			this.send("3", name); //��Ʈ��ũ ������ڸ��� �̸� ����. �̸�ǥ�� ������ �ٷ� ������

		} catch (Exception e) {
			JOptionPane.showMessageDialog(m_slobby, "lost connection!");
			System.exit(-1);
		}
	}

	public void send(String type, String name) {
		try {
			out = new PrintWriter(m_client.getOutputStream(), true); //Ŭ���̾�Ʈ�� �����͸� ����
			out.println(type + "" + name); 

		} catch (Exception e) {
			JOptionPane.showMessageDialog(m_slobby,
					"warning! cannot found connection");
			System.exit(-1);
		}
	}

	public void closeServer() {
		try {
			in.close();
			out.close();
			m_client.close();
			m_server.close();
			System.out.println("��������");

		} catch (IOException e) {
		}
	}

	public void run() {

		try {
			while (m_client == null) {
				yield();
			}

			in = m_client.getInputStream(); //Ŭ���̾�Ʈ�� ���� �����͸� ����

			while (m_client.isConnected()) {

				int leng = 0;

				if ((leng = in.available()) != 0) {
					byte[] data1 = new byte[leng];
					in.read(data1);
					String str = new String(data1); //���� �����͸� ���ڿ��� ����
					int type = Integer.parseInt(str.substring(0, 1)); //str�������� �Ծ���ڸ� �߷����� ������ type�� ����
					String text = str.substring(1);//������ �κ��� ����̸��̳� ä�úκ����� �ؽ�Ʈ ǥ�������� ��

					
					//��ȣ�Ծ�, �߷��� type�� ���ڷ� �Ʒ��� ������ ���ý�����
					if (type == 1) {//ü�°���
						Main_Activity.Menu.s_lobby.ser_mul.enemy_bar -= 5;//������ �� ������ static���� ���θ޴��� �̸� �����س��ұ� ������ �� ���� ��ü�鿡�� ������ ����. ���� �̷��� �� ������ ���� ä��â�̶� ����â�� ���� �����صּ� �̷��� �ؾ߸� �ߴ�
					} else if (type == 2) {//ü������
						Main_Activity.Menu.s_lobby.ser_mul.enemy_bar += 55;
					} else if (type == 3) {//����̸� ǥ��
						m_slobby.enemyname = text;
					} else if (type == 4) {//ä��â �ؽ�Ʈ �ø�
						m_slobby.m_log.append(text);
					} else if (type == 5) {//Ŭ���̾�Ʈ�� ���� �����ȣ����...���� ����
						m_slobby.START = true;
						m_slobby.m_log.append(text);
					} else if (type == 6) {//���� �ȵ�
						m_slobby.START = false;
						m_slobby.m_log.append(text);

					}

				} else {
					yield();
				}
			}
		} catch (Exception e) {
			JOptionPane.showMessageDialog(m_slobby, "cannot found connection");
			System.exit(-1);
		}
	}

}