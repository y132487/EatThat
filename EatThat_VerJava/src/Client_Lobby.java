import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.Socket;

import javax.swing.*;

class Client_Lobby extends JFrame implements ActionListener {

	JButton m_exit = new JButton("EXIT");
	JButton m_ready = new JButton("READY");
	JButton m_notready = new JButton("NOT READY");
	TextArea m_log = new TextArea(15, 30);
	TextField m_msg = new TextField(30);
	public String myname;
	public String enemyname;
	public String IP;
	Client socket = null;

	public int START = 0;
	Client_Multi cli_mul;

	public Client_Lobby(String IP, String name) {

		this.IP = IP;
		this.myname = name;
		setLocation(1, 1);
		setTitle("Client Lobby");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLayout(new BorderLayout());
		setResizable(false);
		setPreferredSize(new Dimension(300, 370));
		pack();
		setVisible(true);

		m_msg.addActionListener(this);
		m_ready.addActionListener(this);
		m_notready.addActionListener(this);
		m_exit.addActionListener(this);
		setCompo();
		toServer(IP);

	}

	public void startGame() {
		cli_mul = new Client_Multi(this.IP, myname, enemyname, this);
		cli_mul.setSocket(socket);
		m_ready.setEnabled(true);
		m_notready.setEnabled(false);
		setVisible(false);
		START = 0;
	}

	public void toServer(String IP) {// 여기서 클라이언트 실행.
		try {

			Socket server = new Socket(IP, 3333);
			socket = new Client(server, this, myname);

			boolean result = server.isConnected();
			if (result)
				System.out.println("connected");
			else
				System.out.println("disconnected");

			socket.start();

		} catch (Exception e) {
			JOptionPane.showMessageDialog(this, "cannot found server");
			System.exit(-1);
		}
	}

	public void setCompo() {

		JPanel chat_set = new JPanel();
		m_log.setEditable(false);
		chat_set.add(m_log);
		chat_set.add(m_msg);
		chat_set.add(m_ready);
		chat_set.add(m_notready);
		chat_set.add(m_exit);
		m_notready.setEnabled(false);
		chat_set.setLayout(new FlowLayout());
		add(chat_set, BorderLayout.CENTER);

	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == m_msg) {
			String field = m_msg.getText();
			if (field.equals("")) {
			} else {
				String chatlog = myname + "->" + m_msg.getText();
				socket.send("4", chatlog);
				m_log.append(myname + "-> " + m_msg.getText() + "\n");
				m_msg.setText("");
			}
		}

		if (e.getSource() == m_ready) {
			m_ready.setEnabled(false);
			m_notready.setEnabled(true);
			socket.send("5", "[system]" + myname + "님 준비 완료!");//레디완료
		} else if (e.getSource() == m_notready) {
			m_ready.setEnabled(true);
			m_notready.setEnabled(false);
			socket.send("6", "[system]" + myname + "님 준비 취소!");//레디안됨

		}

		if (e.getSource() == m_exit) {
			socket.closeClient();
			System.exit(-1);
		}

	}

}
