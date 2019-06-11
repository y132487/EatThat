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
			this.send("3", name); //네트워크 연결되자마자 이름 받음. 이름표시 로직을 바로 실행함

		} catch (Exception e) {
			JOptionPane.showMessageDialog(m_slobby, "lost connection!");
			System.exit(-1);
		}
	}

	public void send(String type, String name) {
		try {
			out = new PrintWriter(m_client.getOutputStream(), true); //클라이언트로 데이터를 보냄
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
			System.out.println("서버종료");

		} catch (IOException e) {
		}
	}

	public void run() {

		try {
			while (m_client == null) {
				yield();
			}

			in = m_client.getInputStream(); //클라이언트로 부터 데이터를 받음

			while (m_client.isConnected()) {

				int leng = 0;

				if ((leng = in.available()) != 0) {
					byte[] data1 = new byte[leng];
					in.read(data1);
					String str = new String(data1); //받은 데이터를 문자열에 저장
					int type = Integer.parseInt(str.substring(0, 1)); //str변수에서 규약숫자를 추려내어 정수형 type에 넣음
					String text = str.substring(1);//나머지 부분은 상대이름이나 채팅부분으로 텍스트 표현역할을 함

					
					//신호규약, 추려낸 type의 숫자로 아래의 로직을 선택실행함
					if (type == 1) {//체력감소
						Main_Activity.Menu.s_lobby.ser_mul.enemy_bar -= 5;//생소할 순 있으나 static으로 메인메뉴를 미리 선언해놓았기 때문에 그 하위 객체들에게 간섭이 가능. 굳이 이렇게 한 이유는 내가 채팅창이랑 게임창을 따로 구분해둬서 이렇게 해야만 했다
					} else if (type == 2) {//체력증가
						Main_Activity.Menu.s_lobby.ser_mul.enemy_bar += 55;
					} else if (type == 3) {//상대이름 표시
						m_slobby.enemyname = text;
					} else if (type == 4) {//채팅창 텍스트 올림
						m_slobby.m_log.append(text);
					} else if (type == 5) {//클라이언트로 부터 레디신호받음...레디 눌름
						m_slobby.START = true;
						m_slobby.m_log.append(text);
					} else if (type == 6) {//레디 안됨
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