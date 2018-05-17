package operation;

import java.awt.Color;
import java.awt.Component;

import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;

import operation.LoginListener;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class Login extends JFrame implements MouseListener {

	private static final String TIP = "请输入工号";
	private static final String TIP1 = "请输入密码";

	public Login() {
		// 实例化一个窗体;
		JFrame frame = new JFrame();
		frame.setTitle("中南大学图书馆");
		frame.setLocation(550, 400);
		frame.setDefaultCloseOperation(3);

		ImageIcon background = new ImageIcon(Login.class.getClassLoader().getResource("images/登录界面.PNG"));
		JLabel bkLabel = new JLabel(background);
		bkLabel.setBounds(0, 0, background.getIconWidth(), background.getIconHeight());
		frame.setSize(background.getIconWidth(), background.getIconHeight());
		frame.getLayeredPane().add(bkLabel, new Integer(Integer.MIN_VALUE));// 将背景图组设置在最下面
		JPanel ctPanel = (JPanel) frame.getContentPane();
		ctPanel.setOpaque(false);

		// 实例化eastpanel并添加到窗体上
		JPanel eastpanel = new JPanel();
		eastpanel.setBounds(750, 326, 340, 70);
		eastpanel.setBackground(Color.WHITE);

		frame.add(eastpanel);
		JButton button2 = new JButton("登录");
		button2.setBounds(600, 220, 120, 30);
		frame.getLayeredPane().add(eastpanel, new Integer(Integer.MAX_VALUE));

		JPanel northpanel = new JPanel();
		northpanel.setBounds(805, 120, 285, 120);
		northpanel.setLayout(null);
		frame.add(northpanel);
		frame.getLayeredPane().add(northpanel, new Integer(Integer.MAX_VALUE));
		
		JPanel middlepanel = new JPanel();
		middlepanel.setBounds(760, 240, 330, 50);
		middlepanel.setLayout(null);
		frame.add(middlepanel);
		frame.getLayeredPane().add(middlepanel, new Integer(Integer.MAX_VALUE));
		
		JTextField zhanghao = new JTextField(TIP);
		JTextField mima = new JTextField(TIP1);
		zhanghao.setBackground(Color.white);
		mima.setBackground(Color.WHITE);
		zhanghao.setForeground(Color.gray);
		mima.setForeground(Color.gray);
		zhanghao.setBounds(1, 2, 280, 43);
		mima.setBounds(1, 70, 280, 43);
		zhanghao.addMouseListener(this);
		mima.addMouseListener(this);
		zhanghao.setName("zhanghao");
		mima.setName("mima");

		JLabel label = new JLabel();
		label.setText("登录身份：");
		label.setBounds(5, 5, 110, 40);
		label.setVisible(true);
		JRadioButton manager = new JRadioButton("管理员");
		JRadioButton reader = new JRadioButton("读者");
		manager.setVisible(true);
		manager.setBounds(110, 5, 120, 40);
		reader.setVisible(true);
		reader.setBounds(245, 5, 80, 40);
		
		ButtonGroup group = new ButtonGroup();
		group.add(manager);
		group.add(reader);
		middlepanel.add(manager);
		middlepanel.add(reader);
		middlepanel.add(label);
		manager.setBackground(Color.white);
		reader.setBackground(Color.WHITE);
		middlepanel.setBackground(Color.white);
		northpanel.setBackground(Color.WHITE);
		northpanel.add(zhanghao);
		northpanel.add(mima);
		northpanel.setFocusable(true);
		eastpanel.add(button2);

		frame.setVisible(true);
		//System.out.println("1");
		LoginListener lis = new LoginListener(frame, zhanghao, mima,manager,reader);
		button2.addActionListener(lis);
		//System.out.println("2");
	}

	public void mouseClicked(MouseEvent e) {
		Component component = e.getComponent();
		String name = component.getName();
		if (e.getButton() == MouseEvent.BUTTON1) {
			if ("zhanghao".equals(name) || "mima".equals(name)) {
				JTextField tf = (JTextField) component;
				if (TIP.equals(tf.getText())) {
					tf.setText("");
				} else if (TIP1.equals(tf.getText())) {
					tf.setText("");
				}
			}
		}
	}

	@Override
	public void mouseEntered(MouseEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseExited(MouseEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mousePressed(MouseEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseReleased(MouseEvent arg0) {
		// TODO Auto-generated method stub

	}
}
