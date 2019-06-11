import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

class Server_Lobby extends JFrame implements ActionListener {

	Server_Multi ser_mul;

	JButton m_exit = new JButton("EXIT");
	JButton m_start = new JButton("START");
	TextArea m_log = new TextArea(15, 30);
	TextField m_msg = new TextField(30);

	public String myname;
	public String enemyname;
	Server socket = null;

	public boolean START = false;

	public Server_Lobby(String name) {

		myname = name;
		setLocation(1, 1);
		setTitle("Server Lobby");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLayout(new BorderLayout());
		setResizable(false);
		setPreferredSize(new Dimension(300, 370));
		pack();
		setVisible(true);

		setCompo();
		m_msg.addActionListener(this);
		m_start.addActionListener(this);

		m_exit.addActionListener(this);
	}

	public void setSocket(Server server) {
		this.socket = server;

	}

	public void setCompo() {

		JPanel chat_set = new JPanel();
		m_log.setEditable(false);
		chat_set.add(m_log);
		chat_set.add(m_msg);
		chat_set.add(m_start);
		chat_set.add(m_exit);
		chat_set.setLayout(new FlowLayout());
		add(chat_set, BorderLayout.CENTER);

	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == m_msg) {
			String field = m_msg.getText();
			if (field.equals("")) {
			} else {
				String chatlog = myname + "->" + m_msg.getText();  //채팅 텍스트가 들어가는 부분
				socket.send("4", chatlog); //규약번호 4번은 채팅을 실행하겠다는 의미, 뒷부분은 채팅내용
				m_log.append(myname + "-> " + m_msg.getText() + "\n");//일단 내 창에 내가 입력한 채팅을 띄움
				m_msg.setText("");
			}
		}
		if (e.getSource() == m_start) {
			if (START == true) {
				ser_mul = new Server_Multi(myname, enemyname, this);
				ser_mul.setSocket(socket);

				START = false;
				setVisible(false);
			} else {

			}
		}

		if (e.getSource() == m_exit) {
			socket.closeServer();
			System.exit(-1);
		}
	}

}
