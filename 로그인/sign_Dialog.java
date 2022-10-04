package semina;

import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

public class sign_Dialog extends JDialog {
    private JPanel signUpPanel = new JPanel();
    private JOptionPane MessageBox = new JOptionPane();
    private JLabel idlabel = new JLabel("���̵�");
    private JLabel pwlabel = new JLabel("��й�ȣ");
    private JLabel pwChecklabel = new JLabel("��й�ȣ Ȯ��");

    private JTextField idText = new JTextField(15);
    private JPasswordField pwText = new JPasswordField(15);
    private JPasswordField pwCheckText = new JPasswordField(15);
    private JButton signUpbtn = new JButton("ȸ������");
    private JButton overlapBtn = new JButton("�ߺ�Ȯ��");
    private boolean check = false;
    DB_Connection dbCon = new DB_Connection();

    public sign_Dialog() {
        dbCon.connect();
        signUpPanel.setLayout(null);
        signUpPanel.setSize(500,1000);
        setTitle("ȸ������");
        setContentPane(signUpPanel);
        idlabel.setBounds(50, 20, 100, 20);
        signUpPanel.add(idlabel);
        idText.setBounds(50, 40, 100, 20);
        signUpPanel.add(idText);
        overlapBtn.setBounds(180, 40, 100, 20);
        overlapBtn.setContentAreaFilled(false);
        overlapBtn.setFocusPainted(false);
        overlapBtn.setForeground(Color.black);
        overlapBtn.setFont(new Font("�������",Font.BOLD,15));


        //                  /*���� ó��*/                  //
        signUpPanel.repaint();
        signUpPanel.revalidate();
        overlapBtn.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {overlapBtn.setCursor(new Cursor(Cursor.HAND_CURSOR));}

            @Override
            public void mouseExited(MouseEvent e) {
                overlapBtn.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
            }

            @Override
            public void mousePressed(MouseEvent e) {
                if (!(idText.getText().length() >= 4)) {
                    MessageBox.showMessageDialog(null, "4���� �̻����� �ۼ����ּ���!");
                } else if (dbCon.isAdmin(idText.getText())) {
                    MessageBox.showMessageDialog(null, "�ߺ��� ���̵��Դϴ�. �ٽ� �Է����ּ���");
                    idText.setText("");
                } else {
                    MessageBox.showMessageDialog(null, "��� ������ ���̵��Դϴ�.");
                    check = true;
                }
            }
        });
        signUpPanel.add(overlapBtn);

        pwlabel.setBounds(50, 90, 100, 20);
        signUpPanel.add(pwlabel);

        pwText.setBounds(50, 110, 100, 20);
        signUpPanel.add(pwText);

        pwChecklabel.setBounds(180, 90, 100, 20);
        signUpPanel.add(pwChecklabel);

        pwCheckText.setBounds(180, 110, 100, 20);
        signUpPanel.add(pwCheckText);

        signUpbtn.setBounds(120, 150, 100, 20);
        signUpbtn.setContentAreaFilled(false);
        signUpbtn.setFocusPainted(false);
        //                  /*ȸ������*/                  //
        signUpbtn.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                signUpbtn.setCursor(new Cursor(Cursor.HAND_CURSOR));
            }

            @Override
            public void mouseExited(MouseEvent e) {
                signUpbtn.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
            }

            @Override
            public void mousePressed(MouseEvent e) {
                String pass = new String(pwText.getPassword());
                String passcheck = new String(pwCheckText.getPassword());

                if (idlabel == null || pass == null || passcheck == null) {
                    MessageBox.showMessageDialog(null, "��ĭ�� �Է����ּ���.");
                } else if (!check) {
                    MessageBox.showMessageDialog(null, "���̵� �ߺ�Ȯ���� �ּ���.");
                } else if (!(pass.length() >= 4)) {
                    MessageBox.showMessageDialog(null, "��й�ȣ�� 4���� �̻����� �ۼ����ּ���.");
                } else if (!pass.equals(passcheck)) {
                    MessageBox.showMessageDialog(null, "��й�ȣ�� �ٽ� Ȯ�����ּ���.");
                }
                else {
                    if (dbCon.Enrollment(idText.getText(), pass)) {
                        MessageBox.showMessageDialog(null, "ȸ�������� �Ϸ�Ǿ����ϴ�.");
                        setVisible(false);
                    } else {
                        MessageBox.showMessageDialog(null, "ȸ�����Կ� �����Ͽ����ϴ�. �ٽ� �õ����ּ���.");
                    }
                }
            }
        });
        signUpPanel.add(signUpbtn);

        setSize(350, 250);
        setVisible(true);
        setLocationRelativeTo(null);
    }
    public static void main(String[] args) {
		new sign_Dialog();
	}
}

