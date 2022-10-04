package semina;

import java.sql.*;

public class DB_Connection {
	private static int cnt = 0;
	private Connection connection;
	private ResultSet rs;
	private Statement st;
	private String url = "jdbc:mysql://localhost:3306/db?serverTimezone=UTC";
	private String user = "root";
	private String password = "1234";
	private String driverName = "com.mysql.cj.jdbc.Driver";

	/** DB ���� */
	public void connect() {
		try {
			Class.forName(driverName);
			connection = DriverManager.getConnection(url, user, password);
			st = connection.createStatement();
			if (cnt == 0) {
				System.out.println("[Connection successful!]");
				cnt++;
			}
		} catch (ClassNotFoundException e) {
			System.out.println("[�ε� ����]\n" + e.getStackTrace());
		} catch (SQLException e) {
			System.out.println("[���� ����]\n" + e.getStackTrace());
		}
	}

	/** sign_Dialog�� ȸ������ ��ư */

	/** �����ͺ��̽��� ���� ���� */
	public boolean Enrollment(String myId, String myPw) {
		try {
			String SQL1 = "INSERT INTO new_table(user_id, user_pw) VALUES('" + myId + "','" + myPw + "');";
			PreparedStatement pstmt = connection.prepareStatement(SQL1);
			int re = pstmt.executeUpdate();
			if (re == 1) {
				return true;
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		return false;
	}

	/** sign_Dialog�� �ߺ�Ȯ�� ��ư */
	/** �����ͺ��̽����� ���� Ž�� */
	public boolean isAdmin(String id) {
		try {
			String SQL = "SELECT * FROM new_table WHERE user_id = '" + id + "';".toString();
			rs = st.executeQuery(SQL);
			if (rs.next()) {
				if (rs.getString("user_id").equals(id)) {
					return true;
				}
			}
		} catch (Exception e) {
			System.out.println("[�����ͺ��̽� �˻� ����] : " + e.getMessage());
		}
		return false;
	}

	public boolean login(String id, String pw) {
		boolean id_c = false;
		boolean pw_c = false;
		try {
			String SQL = "SELECT * FROM new_table WHERE user_id = '" + id + "';".toString();
			
			rs = st.executeQuery(SQL);
			if(rs.next()) {
				if(rs.getString("user_id").equals(id)) {
					id_c = true;
				}
			}
			SQL = "SELECT * FROM new_table WHERE user_pw = '" + pw + "';".toString();
			rs = st.executeQuery(SQL);
			if(rs.next()) {
				if(rs.getString("user_pw").equals(pw)) {
					pw_c = true;
				}
			}
			if(id_c == true && pw_c == true) {
				System.out.println("�α��� ����");
				return true;
			}
		}
		catch(Exception e) {
			System.out.println("[�����ͺ��̽� �˻� ����] : " + e.getMessage());
		}System.out.println("�α��� ����");
		return false;
	}

	/** Login_GUI�� �α��� ��ư */

	/**
	 * 1. ������ id�� ��ġ�ϴ���, 2. ������ pw�� ��ġ�ϴ��� boolean üũ�ϰ� �Ѵ� true�� �� �α���(return true)
	 */

}
