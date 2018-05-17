package operation;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.swing.JFrame;
import javax.swing.JRadioButton;
import javax.swing.JTextField;

import operation.RopFrame;

public class LoginListener implements ActionListener {
	public String Name;
	public String Password;
	public JFrame frame;
	public JTextField text;
	public JTextField pw;
	public JRadioButton manager;
	public JRadioButton reader;

	public LoginListener(JFrame frame, JTextField text, JTextField pw, JRadioButton manager, JRadioButton reader) {
		this.frame = frame;
		this.text = text;
		this.pw = pw;
		this.manager = manager;
		this.reader = reader;
	}

	public void actionPerformed(ActionEvent e) {
		try {
			// 发送密码和用户名到客户端
			String user = text.getText();
			String pass = pw.getText();
			boolean flag1 = manager.isSelected();
			boolean flag2 = reader.isSelected();
			String name = null;
			Class.forName("com.mysql.jdbc.Driver");
			System.out.println("4");
			Connection cn = DriverManager.getConnection(
					"jdbc:mysql://127.0.0.1:3306/librarysystem?characterEncoding=utf8&useSSL=true", "root", "twy97620");
			System.out.println("Connecting to database...");

			if (flag2)// 读者登录
			{
				int cunzai2 = 0;
				PreparedStatement ps1 = cn.prepareStatement("select RID,Rkey,Rname from reader");
				ResultSet rs1 = ps1.executeQuery();
				System.out.println("Reader Login...");
				while (rs1.next()) {
					if (user.equals(rs1.getString("RID")) && pass.equals(rs1.getString("Rkey"))) {
						cunzai2 = 1;
						name = rs1.getString("Rname");
						break;
					}
				}
				
				rs1.close();
				if (cunzai2 == 1) {
					System.out.println("存在该读者");
					StringBuffer sb2 = new StringBuffer();
					sb2.append("update reader set Rcount=Rcount+1,Rlatest=now() where RID='");
					sb2.append(user).append("'");
					PreparedStatement ps2 = cn.prepareStatement(sb2.toString());
					int rows2 = ps2.executeUpdate();
					System.out.println("affected rows:"+rows2);
					frame.dispose();
					@SuppressWarnings("unused")
					RopFrame RopFrame = new RopFrame(name,user);
				} else {
					JTextField text = new JTextField("            工号或密码错误");
					JFrame frame = new JFrame();
					frame.setTitle("错误");
					frame.setLocation(750, 500);
					frame.setSize(300, 100);
					frame.setDefaultCloseOperation(2);
					frame.add(text);
					frame.setVisible(true);
				}
			} else if (flag1) {
				int cunzai1 = 0;
				PreparedStatement ps2 = cn.prepareStatement("select ManaID,Mkey,Mname from manager");
				ResultSet rs2 = ps2.executeQuery();
				System.out.println("Manager Login...");
				while (rs2.next()) {
					if (user.equals(rs2.getString("ManaID")) && pass.equals(rs2.getString("Mkey"))) {
						cunzai1 = 1;
						name = rs2.getString("Mname");
						break;
					}
				}
				
				rs2.close();
				if (cunzai1 == 1) {
					System.out.println("存在该管理员");
					frame.dispose();
					@SuppressWarnings("unused")
					MopFrame MopFrame = new MopFrame(name,user);
				} else {
					JTextField text = new JTextField("            工号或密码错误");
					JFrame frame = new JFrame();
					frame.setTitle("错误");
					frame.setLocation(750, 500);
					frame.setSize(300, 100);
					frame.setDefaultCloseOperation(2);
					frame.add(text);
					frame.setVisible(true);
				}
			}
		} catch (Exception e1) {
		}

	}
}
