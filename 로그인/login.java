package semina;
import java.awt.*;
import javax.swing.*;
import java.awt.event.*;

public class login extends JFrame {
	private JTextField id = new JTextField("id �Է�");
	private JTextField pw = new JTextField("pw �Է�");
	private JButton log = new JButton("�α���");
	private JButton jb = new JButton("ȸ������");
	DB_Connection db = new DB_Connection();
	public login() {
		db.connect();
		setTitle("�α���");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		Container c = getContentPane();
		c.setLayout(new GridLayout(2, 2));
		c.add(id);
		c.add(pw);
		c.add(log);
		c.add(jb);
		log.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				db.login(id.getText(), pw.getText());
			}
		});
		jb.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				new sign_Dialog();
			}
		});
		setSize(500, 200);
		setVisible(true);
	}
	public static void main(String[] args) {
		new login();
	}
}
