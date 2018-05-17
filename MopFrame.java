package operation;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Calendar;

import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.border.LineBorder;
import javax.swing.table.DefaultTableModel;

import com.mysql.jdbc.ResultSetMetaData;

public class MopFrame {
	private static final String TIP2 = "请输入书刊名称、ISBN或作者,若查询所有书刊请直接点击按钮";
	private static final String TIP3 = "请输入待修改书刊的ISBN";
	private static final String TIP4 = "请输入待删除书刊的ISBN";
	private static final String TIP5 = "请输入读者名字、学院或学号,若查询所有读者请直接点击按钮";
	private static final String TIP6 = "请输入待修改读者的学号";
	private static final String TIP7 = "请输入待删除读者的学号";
	private static final String TIP8 = "请输入读者学号,若查询所有记录请直接点击按钮";
	private static final String TIP9 = "请输入书刊ISBN,若查询所有记录请直接点击按钮";
	private static final String TIP10 = "请输入旧密码";
	private static final String TIP11 = "请输入新密码，不可超过10位";

	public MopFrame(String name, String user) throws Exception {
		// 实例化窗口
		javax.swing.JFrame frame = new javax.swing.JFrame();
		frame.getContentPane().setLayout(null);
		frame.setTitle("中南大学图书馆");
		frame.setLocation(600, 260);
		frame.setSize(1130, 650);
		frame.setDefaultCloseOperation(3);

		// 实例化菜单栏面板
		JPanel menu = new JPanel();
		menu.setBounds(250, 5, 600, 40);
		// 实例化欢迎面板
		JPanel Welcome = new JPanel();
		Welcome.setBounds(105, 550, 900, 35);
		frame.getContentPane().add(Welcome);
		// 实例化新增图书面板
		JPanel add = new JPanel();
		add.setBounds(5, 50, 1110, 500);
		add.setBackground(Color.WHITE);
		add.setLayout(null);
		// 实例化修改图书面板
		JPanel change = new JPanel();
		change.setBounds(5, 50, 1110, 500);
		change.setBackground(Color.WHITE);
		change.setLayout(null);
		// 实例化查询面板
		JPanel Query = new JPanel();
		Query.setBounds(5, 50, 1110, 500);
		Query.setBackground(Color.WHITE);
		Query.setLayout(null);
		// 实例化查询读者面板
		JPanel RQuery = new JPanel();
		RQuery.setBounds(5, 50, 1110, 500);
		RQuery.setBackground(Color.WHITE);
		RQuery.setLayout(null);
		// 实例化新增读者面板
		JPanel Radd = new JPanel();
		Radd.setBounds(5, 50, 1110, 500);
		Radd.setBackground(Color.WHITE);
		Radd.setLayout(null);
		// 实例化修改读者面板
		JPanel Rchange = new JPanel();
		Rchange.setBounds(5, 50, 1110, 500);
		Rchange.setBackground(Color.WHITE);
		Rchange.setLayout(null);
		// 实例化查询借阅记录面板
		JPanel jieyueQuery = new JPanel();
		jieyueQuery.setBounds(5, 50, 1110, 500);
		jieyueQuery.setBackground(Color.WHITE);
		jieyueQuery.setLayout(null);
		// 实例化查询罚款记录面板
		JPanel fineQuery = new JPanel();
		fineQuery.setBounds(5, 50, 1110, 500);
		fineQuery.setBackground(Color.WHITE);
		fineQuery.setLayout(null);
		// 实例化使用帮助面板
		JPanel usemanual = new JPanel();
		usemanual.setBounds(5, 50, 1110, 500);
		usemanual.setBackground(Color.WHITE);
		usemanual.setLayout(null);
		// 实例化查看信息面板
		JPanel information = new JPanel();
		information.setBounds(5, 50, 1110, 500);
		information.setBackground(Color.WHITE);
		information.setLayout(null);

		// 获取系统日期
		JLabel managername = new JLabel();
		Calendar cal = Calendar.getInstance();
		managername.setText("    今天是 " + cal.get(cal.YEAR) + "年" + (cal.get(cal.MONTH) + 1) + "月" + cal.get(cal.DATE)
				+ "日                      " + "欢迎你：" + name + "  |  ");
		managername.setBounds(5, 5, 100, 30);
		JButton b_exit = new JButton();
		b_exit.setBounds(480, 5, 60, 30);
		b_exit.setText("退出");
		Welcome.add(managername);
		Welcome.add(b_exit);

		// 添加菜单栏
		JMenuBar bar = new JMenuBar();
		JMenu menutsgl = new JMenu("图书管理");
		JMenu menudzgl = new JMenu("读者管理");
		JMenu menujlcx = new JMenu("记录查询");
		JMenu menugrzx = new JMenu("个人中心");
		JMenu menusybz = new JMenu("使用帮助");
		JMenuItem itemxzsk = new JMenuItem("新增书刊");
		JMenuItem itemxgsk = new JMenuItem("修改书刊");
		JMenuItem itemscsk = new JMenuItem("删除书刊");
		JMenuItem itemcxsk = new JMenuItem("查询书刊");
		JMenuItem itemxzdz = new JMenuItem("新增读者");
		JMenuItem itemxgdz = new JMenuItem("修改读者");
		JMenuItem itemscdz = new JMenuItem("删除读者");
		JMenuItem itemcxdz = new JMenuItem("查询读者");
		JMenuItem itemjyjl = new JMenuItem("借阅记录");
		JMenuItem itemfkjl = new JMenuItem("罚款记录");
		JMenuItem itemckxx = new JMenuItem("查看信息");
		JMenuItem itemxgmm = new JMenuItem("修改密码");
		JMenuItem itembz = new JMenuItem("关于此系统的使用");
		menutsgl.add(itemxzsk);
		menutsgl.add(itemxgsk);
		menutsgl.add(itemscsk);
		menutsgl.add(itemcxsk);
		menudzgl.add(itemxzdz);
		menudzgl.add(itemxgdz);
		menudzgl.add(itemscdz);
		menudzgl.add(itemcxdz);
		menujlcx.add(itemjyjl);
		menujlcx.add(itemfkjl);
		menugrzx.add(itemckxx);
		menugrzx.add(itemxgmm);
		menusybz.add(itembz);
		bar.add(menutsgl);
		bar.add(menudzgl);
		bar.add(menujlcx);
		bar.add(menugrzx);
		bar.add(menusybz);
		menu.add(bar);
		frame.getContentPane().add(menu);
		frame.setVisible(true);

		// 实例化查询界面组件并添加
		JTextField info = new JTextField(TIP2);
		info.setForeground(Color.GRAY);
		info.setBounds(220, 10, 550, 35);
		JButton chaxun = new JButton();
		chaxun.setText("查询");
		chaxun.setBounds(790, 10, 100, 35);
		Query.add(info);
		Query.add(chaxun);

		String[] bcolumnNames = { "ISBN", "书名", "作者", "出版社", "关键字", "副本数", "借出数" }; // 列名
		Object[][] btableVales = null; // 数据
		DefaultTableModel tableModel = new DefaultTableModel(btableVales, bcolumnNames); // 表格模型对象
		JTable table = new JTable(tableModel);
		JScrollPane JSP = new JScrollPane(table);
		JSP.setBounds(10, 60, 1090, 360);
		JButton xiangqing = new JButton("详细信息");
		xiangqing.setBounds(450, 450, 160, 30);
		Query.add(xiangqing);
		table.setRowSelectionAllowed(true);
		table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

		// 设置查询书刊按钮的监听
		itemcxsk.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				info.setText(TIP2);
				tableModel.setRowCount(0);
				frame.remove(add);
				frame.remove(change);
				frame.remove(Radd);
				frame.remove(Rchange);
				frame.remove(RQuery);
				frame.remove(jieyueQuery);
				frame.remove(fineQuery);
				frame.remove(usemanual);
				frame.remove(information);
				frame.add(Query);
				frame.revalidate();
				frame.repaint();
			}
		});

		// 设置查询按钮的监听
		chaxun.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				tableModel.setRowCount(0);
				try {
					// 在数据库中查询所有记录
					Class.forName("com.mysql.jdbc.Driver");
					Connection cn = DriverManager.getConnection(
							"jdbc:mysql://127.0.0.1:3306/librarysystem?characterEncoding=utf8&useSSL=true", "root",
							"twy97620");
					System.out.println("Connecting to database...");
					System.out.println("Creating CXstatement...");
					// 搜索全部书刊信息
					if (info.getText().equals("") || info.getText().equals(TIP2)) {
						PreparedStatement ps = cn.prepareStatement("select * from book");
						ResultSet rs = ps.executeQuery();
						if (rs.next()) {// 结果集不为空
							rs.previous();
							System.out.println("搜索成功！");
							while (rs.next()) {
								String ISBN = rs.getString("ISBN");
								String bookname = rs.getString("bookname");
								String bookwriter = rs.getString("bookwriter");
								String bookpublish = rs.getString("bookpublish");
								String bookkeyword = rs.getString("bookkeyword");
								String bookcopy = rs.getString("bookcopy");
								String bookoutnum = rs.getString("bookoutnum");
								tableModel.addRow(new Object[] { ISBN, bookname, bookwriter, bookpublish, bookkeyword,
										bookcopy, bookoutnum });
							}
							int countRows = table.getRowCount();// 获取当前表格总行数
							((DefaultTableModel) table.getModel()).insertRow(countRows,
									new Object[] { " ", " ", " ", " ", " ", " ", " ", " " });
							table.setBorder(new LineBorder(new Color(128, 128, 128), 3));
							table.setRowHeight(40);// 指定每一行的行高40
							table.getColumnModel().getColumn(0).setPreferredWidth(560);
							table.getColumnModel().getColumn(1).setPreferredWidth(550);
							table.getColumnModel().getColumn(2).setPreferredWidth(400);
							table.getColumnModel().getColumn(3).setPreferredWidth(530);
							table.getColumnModel().getColumn(4).setPreferredWidth(500);
							table.getColumnModel().getColumn(5).setPreferredWidth(180);
							table.getColumnModel().getColumn(6).setPreferredWidth(180);
							Query.add(JSP);
						} else {
							System.out.println("查询失败！");
							JTextField text1 = new JTextField("  查询失败！当前图书馆中暂无书刊");
							JFrame nobook = new JFrame();
							nobook.setTitle("提示");
							nobook.setLocation(750, 500);
							nobook.setSize(400, 100);
							nobook.setDefaultCloseOperation(2);
							nobook.getContentPane().add(text1);
							nobook.setVisible(true);
						}
						rs.close();
						ps.close();
					} else {// 搜索指定书刊信息
						StringBuffer sb = new StringBuffer();
						sb.append("select * from book where");
						sb.append(" bookname").append(" like ").append("'%").append(info.getText()).append("%'");
						sb.append(" or ISBN").append(" like ").append("'%").append(info.getText()).append("%'");
						sb.append(" or bookwriter").append(" like ").append("'%").append(info.getText()).append("%'");
						PreparedStatement ps = cn.prepareStatement(sb.toString());
						ResultSet rs = ps.executeQuery();
						System.out.println(String.format("execute sql is %s", sb.toString()));
						if (rs.next()) {// 结果集不为空
							rs.previous();
							System.out.println("搜索成功！");
							while (rs.next()) {
								String ISBN = rs.getString("ISBN");
								String bookname = rs.getString("bookname");
								String bookwriter = rs.getString("bookwriter");
								String bookpublish = rs.getString("bookpublish");
								String bookkeyword = rs.getString("bookkeyword");
								String bookcopy = rs.getString("bookcopy");
								String bookoutnum = rs.getString("bookoutnum");
								tableModel.addRow(new Object[] { ISBN, bookname, bookwriter, bookpublish, bookkeyword,
										bookcopy, bookoutnum });
							}
							int countRows = table.getRowCount();// 获取当前表格总行数
							((DefaultTableModel) table.getModel()).insertRow(countRows,
									new Object[] { " ", " ", " ", " ", " ", " ", " ", " " });
							table.setBorder(new LineBorder(new Color(128, 128, 128), 3));
							table.setRowHeight(40);// 指定每一行的行高40
							table.getColumnModel().getColumn(0).setPreferredWidth(560);
							table.getColumnModel().getColumn(1).setPreferredWidth(550);
							table.getColumnModel().getColumn(2).setPreferredWidth(400);
							table.getColumnModel().getColumn(3).setPreferredWidth(530);
							table.getColumnModel().getColumn(4).setPreferredWidth(500);
							table.getColumnModel().getColumn(5).setPreferredWidth(180);
							table.getColumnModel().getColumn(6).setPreferredWidth(180);
							Query.add(JSP);
						} else {
							System.out.println("查询失败！");
							JTextField text1 = new JTextField("  查询失败！当前图书馆中暂无相关书刊");
							JFrame nobook = new JFrame();
							nobook.setTitle("提示");
							nobook.setLocation(750, 500);
							nobook.setSize(400, 100);
							nobook.setDefaultCloseOperation(2);
							nobook.getContentPane().add(text1);
							nobook.setVisible(true);
						}
						rs.close();
						ps.close();
					}
					cn.close();
				} catch (SQLException se) {// Handle errors for JDBC
					se.printStackTrace();
				} catch (Exception e1) {// Handle errors for Class.forName
					e1.printStackTrace();
				} finally {
				}
			}
		});

		// 设置详细信息按钮的监听
		xiangqing.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					// 在数据库中查询所有记录
					Class.forName("com.mysql.jdbc.Driver");
					Connection cn = DriverManager.getConnection(
							"jdbc:mysql://127.0.0.1:3306/librarysystem?characterEncoding=utf8&useSSL=true", "root",
							"twy97620");
					System.out.println("Connecting to database...");
					System.out.println("Creating xiangqingstatement...");
					StringBuffer sb = new StringBuffer("select * from book where ISBN='");
					int selectedRow = table.getSelectedRow(); // 获得选中行索引
					Object selectedISBN = tableModel.getValueAt(selectedRow, 0);
					sb.append(selectedISBN).append("'");
					PreparedStatement ps = cn.prepareStatement(sb.toString());
					ResultSet rs = ps.executeQuery();
					System.out.println(String.format("execute sql is %s", sb.toString()));
					System.out.println("查看详情成功！");
					JTextArea bookdetail = new JTextArea();
					JScrollPane jsp_detail = new JScrollPane(bookdetail);
					JFrame fbookdetail = new JFrame();
					fbookdetail.setTitle("详细信息");
					fbookdetail.setLocation(750, 500);
					fbookdetail.setSize(550, 400);
					fbookdetail.setDefaultCloseOperation(2);
					fbookdetail.getContentPane().add(jsp_detail);
					fbookdetail.setVisible(true);

					if (rs.next()) {// 结果集不为空
						System.out.println("搜索成功！");
						String str = rs.getString("BookRID");
						StringBuffer sb1 = new StringBuffer("select RoomAddress from room where RoomID='");
						sb1.append(str).append("'");
						PreparedStatement ps1 = cn.prepareStatement(sb1.toString());
						ResultSet rs1 = ps1.executeQuery();
						System.out.println(String.format("execute sql is %s", sb1.toString()));
						rs.previous();
						while (rs.next()) {
							bookdetail.append("ISBN:" + rs.getString("ISBN") + "\n\n");
							bookdetail.append("索书号:" + rs.getString("BookNo") + "\n\n");
							bookdetail.append("书名:" + rs.getString("Bookname") + "\n\n");
							bookdetail.append("作者:" + rs.getString("Bookwriter") + "\n\n");
							bookdetail.append("出版社:" + rs.getString("Bookpublish") + "\n\n");
							bookdetail.append("出版日期:" + rs.getString("Bookpubdate") + "\n\n");
							bookdetail.append("价格:" + rs.getString("Bookprice") + "\n\n");
							bookdetail.append("摘要:" + rs.getString("Booksubstract") + "\n\n");
							bookdetail.append("关键字:" + rs.getString("Bookkeyword") + "\n\n");
							bookdetail.append("副本数:" + rs.getString("BookCopy") + "\n\n");
							bookdetail.append("借出数:" + rs.getString("Bookoutnum") + "\n\n");
							bookdetail.append("类别:" + rs.getString("Bookcategory") + "\n\n");
						}
						if (rs1.next())
							bookdetail.append("馆藏地点:" + rs1.getString("RoomAddress") + "\n");
						rs1.close();
						ps1.close();
					}
					bookdetail.append("\n");
					rs.close();
					ps.close();
					cn.close();
				} catch (SQLException se) {// Handle errors for JDBC
					se.printStackTrace();
				} catch (Exception e1) {// Handle errors for Class.forName
					e1.printStackTrace();
				} finally {
				}
			}
		});

		// 实例化新增书刊面板组件
		JLabel LISBN = new JLabel("ISBN（必填）：");
		LISBN.setBounds(40, 30, 210, 40);
		JLabel LBookNo = new JLabel("索书号（必填）：");
		LBookNo.setBounds(40, 80, 210, 40);
		JLabel LBookname = new JLabel("书名（必填）：");
		LBookname.setBounds(40, 130, 210, 40);
		JLabel LBookwriter = new JLabel("作者（必填）：");
		LBookwriter.setBounds(40, 180, 210, 40);
		JLabel LBookpublish = new JLabel("出版社（必填）：");
		LBookpublish.setBounds(40, 230, 210, 40);
		JLabel LBookpubdate = new JLabel("出版日期（必填）：");
		LBookpubdate.setBounds(40, 280, 210, 40);
		JLabel LBookprice = new JLabel("价格（必填）：");
		LBookprice.setBounds(40, 330, 210, 40);
		JLabel LBooksubstract = new JLabel("摘要（必填）：");
		LBooksubstract.setBounds(550, 70, 210, 40);
		JLabel LBookkeyword = new JLabel("关键字（必填）：");
		LBookkeyword.setBounds(550, 220, 210, 40);
		JLabel LBookCopy = new JLabel("副本数（必填）：");
		LBookCopy.setBounds(40, 380, 210, 40);
		JLabel LBookcategory = new JLabel("类别（必填）：");
		LBookcategory.setBounds(550, 330, 210, 40);
		JLabel LBookRID = new JLabel("馆藏地点（必填）：");
		LBookRID.setBounds(550, 380, 210, 40);
		JTextField TISBN = new JTextField();
		TISBN.setBounds(250, 30, 290, 40);
		JTextField TBookNo = new JTextField();
		TBookNo.setBounds(250, 80, 290, 40);
		JTextField TBookname = new JTextField();
		TBookname.setBounds(250, 130, 290, 40);
		JTextField TBookwriter = new JTextField();
		TBookwriter.setBounds(250, 180, 290, 40);
		JTextField TBookpublish = new JTextField();
		TBookpublish.setBounds(250, 230, 290, 40);
		JTextField TBookpubdate = new JTextField();
		TBookpubdate.setBounds(250, 280, 290, 40);
		JTextField TBookprice = new JTextField();
		TBookprice.setBounds(250, 330, 290, 40);
		JTextArea TBooksubstract = new JTextArea();
		JScrollPane jsp_subtract = new JScrollPane(TBooksubstract);
		jsp_subtract.setBounds(760, 30, 290, 140);
		JTextArea TBookkeyword = new JTextArea();
		JScrollPane jsp_keyword = new JScrollPane(TBookkeyword);
		jsp_keyword.setBounds(760, 180, 290, 140);
		JTextField TBookCopy = new JTextField();
		TBookCopy.setBounds(250, 380, 290, 40);
		JTextField TBookcategory = new JTextField();
		TBookcategory.setBounds(760, 330, 290, 40);
		JButton tianjia = new JButton("添加");
		tianjia.setBounds(500, 450, 120, 30);
		add.add(LISBN);
		add.add(LBookNo);
		add.add(LBookname);
		add.add(LBookwriter);
		add.add(LBookpublish);
		add.add(LBookpublish);
		add.add(LBookpubdate);
		add.add(LBookprice);
		add.add(LBooksubstract);
		add.add(LBookkeyword);
		add.add(LBookCopy);
		add.add(LBookcategory);
		add.add(LBookRID);
		add.add(TISBN);
		add.add(TBookNo);
		add.add(TBookname);
		add.add(TBookwriter);
		add.add(TBookpublish);
		add.add(TBookpubdate);
		add.add(TBookprice);
		add.add(jsp_subtract);
		add.add(jsp_keyword);
		add.add(TBookCopy);
		add.add(TBookcategory);
		add.add(tianjia);
		String labels[] = { "请选择" };
		JComboBox comboBox = new JComboBox(labels);
		// 设置新增书刊按钮的监听
		itemxzsk.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					// 在数据库中添加一条记录
					Class.forName("com.mysql.jdbc.Driver");
					Connection cn = DriverManager.getConnection(
							"jdbc:mysql://127.0.0.1:3306/librarysystem?characterEncoding=utf8&useSSL=true", "root",
							"twy97620");
					System.out.println("Connecting to database...");
					StringBuffer sb = new StringBuffer("select RoomID from room");
					PreparedStatement ps = cn.prepareStatement(sb.toString());
					ResultSet rs = ps.executeQuery();

					if (rs.next()) {
						rs.previous();
						while (rs.next()) {
							comboBox.addItem(rs.getString("RoomID"));
						}
					}
					rs.close();
					ps.close();
					cn.close();
					comboBox.setBounds(760, 380, 290, 40);
					add.add(comboBox);
				} catch (SQLException se) {// Handle errors for JDBC
					se.printStackTrace();
					System.out.println("添加失败！");
					JTextField text = new JTextField("            添加失败！输入ISBN有误 ");
					JFrame fail = new JFrame();
					fail.setTitle("提示");
					fail.setLocation(750, 500);
					fail.setSize(400, 100);
					fail.setDefaultCloseOperation(2);
					fail.getContentPane().add(text);
					fail.setVisible(true);
				} catch (Exception e1) {// Handle errors for Class.forName
					e1.printStackTrace();
				} finally {
				}
				TISBN.setText("");
				TBookNo.setText("");
				TBookname.setText("");
				TBookwriter.setText("");
				TBookpublish.setText("");
				TBookpubdate.setText("");
				TBookprice.setText("");
				TBooksubstract.setText("");
				TBookkeyword.setText("");
				TBookCopy.setText("");
				TBookcategory.setText("");
				tableModel.setRowCount(0);
				frame.remove(Query);
				frame.remove(change);
				frame.remove(Radd);
				frame.remove(RQuery);
				frame.remove(Rchange);
				frame.remove(jieyueQuery);
				frame.remove(fineQuery);
				frame.remove(usemanual);
				frame.remove(information);
				frame.add(add);
				frame.revalidate();
				frame.repaint();
			}
		});

		// 设置添加信息按钮的监听
		tianjia.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					// 在数据库中添加一条记录
					Class.forName("com.mysql.jdbc.Driver");
					Connection cn = DriverManager.getConnection(
							"jdbc:mysql://127.0.0.1:3306/librarysystem?characterEncoding=utf8&useSSL=true", "root",
							"twy97620");
					System.out.println("Connecting to database...");
					System.out.println("Creating tianjaistatement...");
					if (!TISBN.getText().equals("") && !TBookNo.getText().equals("") && !TBookname.getText().equals("")
							&& !TBookwriter.getText().equals("") && !TBookpublish.getText().equals("")
							&& !TBookpubdate.getText().equals("") && !TBookprice.getText().equals("")
							&& !TBooksubstract.getText().equals("") && !TBookkeyword.getText().equals("")
							&& !TBookCopy.getText().equals("") && !TBookcategory.getText().equals("")) {
						StringBuffer sb11 = new StringBuffer("select * from book where ISBN='");
						sb11.append(TISBN.getText()).append("'");
						PreparedStatement ps11 = cn.prepareStatement(sb11.toString());
						ResultSet rs11 = ps11.executeQuery();
						if (!rs11.next()) {
							StringBuffer sb = new StringBuffer("call pinsertBook('");
							sb.append(TISBN.getText()).append("','");
							sb.append(TBookNo.getText()).append("','");
							sb.append(TBookname.getText()).append("','");
							sb.append(TBookwriter.getText()).append("','");
							sb.append(TBookpublish.getText()).append("','");
							sb.append(TBookpubdate.getText()).append("','");
							sb.append(TBookprice.getText()).append("','");
							sb.append(TBooksubstract.getText()).append("','");
							sb.append(TBookkeyword.getText()).append("','");
							sb.append(TBookCopy.getText()).append("','");
							sb.append(TBookcategory.getText()).append("','");
							sb.append(comboBox.getSelectedItem()).append("')");
							PreparedStatement ps = cn.prepareStatement(sb.toString());
							int rows = ps.executeUpdate();
							System.out.println(String.format("execute sql is %s", sb.toString()));
							ps.close();
							if (rows != 0) {
								System.out.println("添加成功！");
								JTextField text = new JTextField("                 添加成功！ ");
								JFrame success = new JFrame();
								success.setTitle("提示");
								success.setLocation(750, 500);
								success.setSize(300, 100);
								success.setDefaultCloseOperation(2);
								success.getContentPane().add(text);
								success.setVisible(true);
							}
						} else {
							System.out.println("添加失败！");
							JTextField text = new JTextField("            添加失败！该ISBN图书已经存在 ");
							JFrame fail = new JFrame();
							fail.setTitle("提示");
							fail.setLocation(750, 500);
							fail.setSize(400, 100);
							fail.setDefaultCloseOperation(2);
							fail.getContentPane().add(text);
							fail.setVisible(true);
						}
					} else {
						System.out.println("添加失败！");
						JTextField text = new JTextField("            添加失败！必填信息必须正确输入 ");
						JFrame fail = new JFrame();
						fail.setTitle("提示");
						fail.setLocation(750, 500);
						fail.setSize(400, 100);
						fail.setDefaultCloseOperation(2);
						fail.getContentPane().add(text);
						fail.setVisible(true);
					}
					cn.close();
				} catch (SQLException se) {// Handle errors for JDBC
					se.printStackTrace();
				} catch (Exception e1) {// Handle errors for Class.forName
					e1.printStackTrace();
				} finally {
				}
			}
		});

		// 实例化修改界面组件
		JLabel xgBookname = new JLabel("书名：");
		xgBookname.setBounds(40, 50, 210, 40);
		JLabel xgBookwriter = new JLabel("作者：");
		xgBookwriter.setBounds(40, 100, 210, 40);
		JLabel xgBookpublish = new JLabel("出版社：");
		xgBookpublish.setBounds(40, 150, 210, 40);
		JLabel xgBookpubdate = new JLabel("出版日期：");
		xgBookpubdate.setBounds(40, 200, 210, 40);
		JLabel xgBookprice = new JLabel("价格：");
		xgBookprice.setBounds(40, 250, 210, 40);
		JLabel xgBooksubstract = new JLabel("摘要：");
		xgBooksubstract.setBounds(550, 90, 210, 40);
		JLabel xgBookkeyword = new JLabel("关键字：");
		xgBookkeyword.setBounds(550, 240, 210, 40);
		JLabel xgBookCopy = new JLabel("副本数：");
		xgBookCopy.setBounds(40, 300, 210, 40);
		JLabel xgBookcategory = new JLabel("类别：");
		xgBookcategory.setBounds(550, 350, 210, 40);
		JLabel xgBookRID = new JLabel("馆藏地点：");
		xgBookRID.setBounds(40, 350, 210, 40);
		JTextField changeBookname = new JTextField();
		changeBookname.setBounds(250, 50, 290, 40);
		JTextField changeBookwriter = new JTextField();
		changeBookwriter.setBounds(250, 100, 290, 40);
		JTextField changeBookpublish = new JTextField();
		changeBookpublish.setBounds(250, 150, 290, 40);
		JTextField changeBookpubdate = new JTextField();
		changeBookpubdate.setBounds(250, 200, 290, 40);
		JTextField changeBookprice = new JTextField();
		changeBookprice.setBounds(250, 250, 290, 40);
		JTextArea changeBooksubstract = new JTextArea();
		JScrollPane jsp_changesubtract = new JScrollPane(changeBooksubstract);
		jsp_changesubtract.setBounds(760, 50, 290, 140);
		JTextArea changeBookkeyword = new JTextArea();
		JScrollPane jsp_changekeyword = new JScrollPane(changeBookkeyword);
		jsp_changekeyword.setBounds(760, 200, 290, 140);
		JTextField changeBookCopy = new JTextField();
		changeBookCopy.setBounds(250, 300, 290, 40);
		JTextField changeBookcategory = new JTextField();
		changeBookcategory.setBounds(760, 350, 290, 40);
		JTextField changedISBN = new JTextField(TIP3);
		changedISBN.setForeground(Color.GRAY);
		changedISBN.setBounds(250, 10, 500, 30);
		JButton chakanxinxi = new JButton("查看信息");
		chakanxinxi.setBounds(760, 10, 120, 30);
		JButton xiugai = new JButton("修改");
		xiugai.setBounds(510, 450, 120, 30);
		change.add(xgBookname);
		change.add(xgBookwriter);
		change.add(xgBookpublish);
		change.add(xgBookpublish);
		change.add(xgBookpubdate);
		change.add(xgBookprice);
		change.add(xgBooksubstract);
		change.add(xgBookkeyword);
		change.add(xgBookCopy);
		change.add(xgBookcategory);
		change.add(xgBookRID);
		change.add(changeBookname);
		change.add(changeBookwriter);
		change.add(changeBookpublish);
		change.add(changeBookpubdate);
		change.add(changeBookprice);
		change.add(jsp_changesubtract);
		change.add(jsp_changekeyword);
		change.add(changeBookCopy);
		change.add(changeBookcategory);
		change.add(chakanxinxi);
		change.add(xiugai);
		change.add(changedISBN);

		String xglabels[] = { "请选择" };
		JComboBox xgcomboBox = new JComboBox(xglabels);
		xgcomboBox.setBounds(250, 350, 290, 40);
		change.add(xgcomboBox);
		// 设置修改书刊按钮的监听
		itemxgsk.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				changedISBN.setText(TIP3);
				xgcomboBox.setSelectedItem("请选择");
				// changeBookNo.setText("");
				changeBookname.setText("");
				changeBookwriter.setText("");
				changeBookpublish.setText("");
				changeBookpubdate.setText("");
				changeBookprice.setText("");
				changeBookkeyword.setText("");
				changeBooksubstract.setText("");
				changeBookCopy.setText("");
				changeBookcategory.setText("");
				frame.remove(add);
				frame.remove(Query);
				frame.remove(Radd);
				frame.remove(jieyueQuery);
				frame.remove(RQuery);
				frame.remove(Rchange);
				frame.remove(fineQuery);
				frame.remove(usemanual);
				frame.remove(information);
				frame.add(change);
				// frame.remove(information);
				// frame.remove(usemanual);
				frame.revalidate();
				frame.repaint();
			}
		});

		// 设置查看信息按钮的监听
		chakanxinxi.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					// 查看待删除书刊
					Class.forName("com.mysql.jdbc.Driver");
					Connection cn = DriverManager.getConnection(
							"jdbc:mysql://127.0.0.1:3306/librarysystem?characterEncoding=utf8&useSSL=true", "root",
							"twy97620");
					System.out.println("Connecting to database...");
					System.out.println("chakanxinxi...");
					// 输入ISBN为空
					if (changedISBN.getText().equals("") || changedISBN.getText().equals(TIP3)) {
						JTextField text = new JTextField("              输入不可为空！");
						JFrame error = new JFrame();
						error.setTitle("错误");
						error.setLocation(750, 500);
						error.setSize(300, 100);
						error.setDefaultCloseOperation(2);
						error.add(text);
						error.setVisible(true);
					} else {
						StringBuffer sb = new StringBuffer();
						sb.append("select * from book where ISBN='");
						sb.append(changedISBN.getText()).append("'");
						PreparedStatement ps = cn.prepareStatement(sb.toString());
						ResultSet rs = ps.executeQuery();
						System.out.println(String.format("execute sql is %s", sb.toString()));
						if (rs.next()) {// 结果集不为空
							StringBuffer sb1 = new StringBuffer();
							sb1.append("select RoomID from room");
							PreparedStatement ps1 = cn.prepareStatement(sb1.toString());
							ResultSet rs1 = ps1.executeQuery();
							System.out.println(String.format("execute sql is %s", sb1.toString()));
							if (rs1.next()) {
								rs1.previous();
								while (rs1.next()) {
									xgcomboBox.addItem(rs1.getString("RoomID"));
								}
							}
							rs1.close();
							ps1.close();
							changeBookname.setText(rs.getString("Bookname"));
							changeBookwriter.setText(rs.getString("Bookwriter"));
							changeBookpublish.setText(rs.getString("Bookpublish"));
							changeBookpubdate.setText(rs.getString("Bookpubdate"));
							changeBookprice.setText(rs.getString("Bookprice"));
							changeBookkeyword.setText(rs.getString("Bookkeyword"));
							changeBooksubstract.setText(rs.getString("Booksubstract"));
							changeBookCopy.setText(rs.getString("BookCopy"));
							changeBookcategory.setText(rs.getString("Bookcategory"));
							xgcomboBox.setSelectedItem(rs.getString("BookRID"));
						} else {
							System.out.println("     查看失败！输入ISBN有误");
							JTextField text1 = new JTextField("      查看失败！输入ISBN有误");
							JFrame b_fail = new JFrame();
							b_fail.setTitle("提示");
							b_fail.setLocation(750, 500);
							b_fail.setSize(420, 100);
							b_fail.setDefaultCloseOperation(2);
							b_fail.add(text1);
							b_fail.setVisible(true);
						}
						rs.close();
						ps.close();
					}
					cn.close();
				} catch (SQLException se) {// Handle errors for JDBC
					se.printStackTrace();
				} catch (Exception e1) {// Handle errors for Class.forName
					e1.printStackTrace();
				} finally {
				}
			}
		});

		// 设置修改按钮的监听
		xiugai.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					// 修改书刊
					Class.forName("com.mysql.jdbc.Driver");
					Connection cn = DriverManager.getConnection(
							"jdbc:mysql://127.0.0.1:3306/librarysystem?characterEncoding=utf8&useSSL=true", "root",
							"twy97620");
					System.out.println("Connecting to database...");
					System.out.println("xiugai...");

					StringBuffer sb = new StringBuffer();
					sb.append("update book set Bookname='").append(changeBookname.getText()).append("',Bookwriter='");
					sb.append(changeBookwriter.getText()).append("',Bookpublish='");
					sb.append(changeBookpublish.getText()).append("',Bookpubdate='");
					sb.append(changeBookpubdate.getText()).append("',Bookprice='");
					sb.append(changeBookprice.getText()).append("',Booksubstract='");
					sb.append(changeBooksubstract.getText()).append("',Bookkeyword='");
					sb.append(changeBookkeyword.getText()).append("',Bookcopy='");
					sb.append(changeBookCopy.getText()).append("',Bookcategory='");
					sb.append(changeBookcategory.getText()).append("',BookRID='");
					sb.append(xgcomboBox.getSelectedItem()).append("' where ISBN='");
					sb.append(changedISBN.getText()).append("' and bookoutnum<=");
					sb.append(changeBookCopy.getText());
					PreparedStatement ps = cn.prepareStatement(sb.toString());
					int rows = ps.executeUpdate();
					System.out.println(String.format("execute sql is %s", sb.toString()));

					if (rows != 0) {// 结果集不为空
						System.out.println("     修改成功！可在系统中查询修改后的书刊");
						JTextField text1 = new JTextField("      修改成功！可在系统中查询修改后的书刊");
						JFrame xgsuccess = new JFrame();
						xgsuccess.setTitle("提示");
						xgsuccess.setLocation(750, 500);
						xgsuccess.setSize(420, 100);
						xgsuccess.setDefaultCloseOperation(2);
						xgsuccess.add(text1);
						xgsuccess.setVisible(true);
					} else {
						System.out.println("     修改失败！修改后的副本数不可小于当前借出数");
						JTextField text1 = new JTextField("      修改失败！修改后的副本数不可小于当前借出数");
						JFrame b_fail = new JFrame();
						b_fail.setTitle("提示");
						b_fail.setLocation(750, 500);
						b_fail.setSize(420, 120);
						b_fail.setDefaultCloseOperation(2);
						b_fail.add(text1);
						b_fail.setVisible(true);
					}
					ps.close();
					cn.close();
				} catch (SQLException se) {// Handle errors for JDBC
					se.printStackTrace();
				} catch (Exception e1) {// Handle errors for Class.forName
					e1.printStackTrace();
				} finally {
				}
			}
		});

		// 实例化删除书刊界面组件
		JButton shanchubook = new JButton("删除");
		shanchubook.setBounds(320, 30, 100, 40);
		JTextArea deletedISBN = new JTextArea(TIP4);
		deletedISBN.setForeground(Color.GRAY);
		deletedISBN.setBounds(10, 30, 300, 40);
		// 设置删除书刊按钮的监听
		itemscsk.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				deletedISBN.setText(TIP4);
				JFrame deletebook = new JFrame();
				deletebook.setTitle("删除书刊");
				deletebook.setLocation(750, 500);
				deletebook.setSize(445, 150);
				deletebook.setDefaultCloseOperation(2);
				deletebook.setLayout(null);
				deletebook.add(shanchubook);
				deletebook.add(deletedISBN);
				deletebook.setVisible(true);
			}
		});

		// 设置删除按钮的监听
		shanchubook.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					// 删除书刊
					Class.forName("com.mysql.jdbc.Driver");
					Connection cn = DriverManager.getConnection(
							"jdbc:mysql://127.0.0.1:3306/librarysystem?characterEncoding=utf8&useSSL=true", "root",
							"twy97620");
					System.out.println("Connecting to database...");
					System.out.println("deleting...");
					// 输入ISBN为空
					if (deletedISBN.getText().equals("") || deletedISBN.getText().equals(TIP4)) {
						JTextField text = new JTextField("              输入不可为空！");
						JFrame error = new JFrame();
						error.setTitle("错误");
						error.setLocation(750, 500);
						error.setSize(300, 100);
						error.setDefaultCloseOperation(2);
						error.add(text);
						error.setVisible(true);
					} else {
						StringBuffer sb = new StringBuffer();
						sb.append("delete from book where ISBN='");
						sb.append(deletedISBN.getText()).append("'");
						sb.append(" and bookoutnum=0");
						PreparedStatement ps = cn.prepareStatement(sb.toString());
						int rows = ps.executeUpdate();
						System.out.println(String.format("execute sql is %s", sb.toString()));
						ps.close();
						if (rows != 0) {// 结果集不为空
							JTextField text1 = new JTextField("             删除成功！");
							JFrame b_success = new JFrame();
							b_success.setTitle("提示");
							b_success.setLocation(750, 500);
							b_success.setSize(260, 100);
							b_success.setDefaultCloseOperation(2);
							b_success.add(text1);
							b_success.setVisible(true);
						} else {
							System.out.println("删除失败！输入ISBN有误或当前仍有该书未归还");
							JTextField text1 = new JTextField("  删除失败！输入ISBN有误或当前仍有该书未归还");
							JFrame b_fail = new JFrame();
							b_fail.setTitle("提示");
							b_fail.setLocation(750, 500);
							b_fail.setSize(420, 100);
							b_fail.setDefaultCloseOperation(2);
							b_fail.add(text1);
							b_fail.setVisible(true);
						}
					}
					cn.close();
				} catch (SQLException se) {// Handle errors for JDBC
					se.printStackTrace();
				} catch (Exception e1) {// Handle errors for Class.forName
					e1.printStackTrace();
				} finally {
				}
			}
		});

		// 实例化查询读者界面组件并添加
		JTextField Rinfo = new JTextField(TIP5);
		Rinfo.setForeground(Color.GRAY);
		Rinfo.setBounds(220, 10, 550, 35);
		JButton Rchaxun = new JButton();
		Rchaxun.setText("查询");
		Rchaxun.setBounds(790, 10, 100, 35);
		RQuery.add(Rinfo);
		RQuery.add(Rchaxun);

		String[] RcolumnNames = { "学号", "姓名", "性别", "学院", "专业", "年级", "注册日期" }; // 列名
		Object[][] RtableVales = null; // 数据
		DefaultTableModel RtableModel = new DefaultTableModel(RtableVales, RcolumnNames); // 表格模型对象
		JTable Rtable = new JTable(RtableModel);
		JScrollPane RJSP = new JScrollPane(Rtable);
		RJSP.setBounds(10, 60, 1090, 360);
		Rtable.setRowSelectionAllowed(true);
		Rtable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		JButton Rxiangqing = new JButton("详细信息");
		Rxiangqing.setBounds(450, 450, 160, 30);
		RQuery.add(Rxiangqing);
		// 设置查询读者按钮的监听
		itemcxdz.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Rinfo.setText(TIP5);
				RtableModel.setRowCount(0);
				frame.remove(add);
				frame.remove(change);
				// frame.remove(information);
				// frame.remove(usemanual);
				frame.remove(Query);
				frame.remove(Radd);
				frame.remove(Rchange);
				frame.remove(jieyueQuery);
				frame.remove(fineQuery);
				frame.remove(usemanual);
				frame.remove(information);
				frame.add(RQuery);
				frame.revalidate();
				frame.repaint();
			}
		});

		// 设置查询读者按钮的监听
		Rchaxun.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				RtableModel.setRowCount(0);
				try {
					// 在数据库中查询所有记录
					Class.forName("com.mysql.jdbc.Driver");
					Connection cn = DriverManager.getConnection(
							"jdbc:mysql://127.0.0.1:3306/librarysystem?characterEncoding=utf8&useSSL=true", "root",
							"twy97620");
					System.out.println("Connecting to database...");
					System.out.println("Creating CXstatement...");
					// 搜索全部读者信息
					if (Rinfo.getText().equals("") || Rinfo.getText().equals(TIP5)) {
						PreparedStatement ps = cn.prepareStatement("select * from reader");
						ResultSet rs = ps.executeQuery();
						if (rs.next()) {// 结果集不为空
							rs.previous();
							System.out.println("搜索成功！");
							while (rs.next()) {
								String RID = rs.getString("RID");
								String Rname = rs.getString("Rname");
								String Rsex = rs.getString("Rsex");
								String Rschool = rs.getString("Rschool");
								String Rmajor = rs.getString("Rmajor");
								String Rgrade = rs.getString("Rgrade");
								String Rdate = rs.getString("Rdate");
								RtableModel.addRow(new Object[] { RID, Rname, Rsex, Rschool, Rmajor, Rgrade, Rdate });
							}
							int countRows = Rtable.getRowCount();// 获取当前表格总行数
							((DefaultTableModel) Rtable.getModel()).insertRow(countRows,
									new Object[] { " ", " ", " ", " ", " ", " ", " ", " " });
							Rtable.setBorder(new LineBorder(new Color(128, 128, 128), 3));
							Rtable.setRowHeight(40);// 指定每一行的行高40
							Rtable.getColumnModel().getColumn(0).setPreferredWidth(560);
							Rtable.getColumnModel().getColumn(1).setPreferredWidth(400);
							Rtable.getColumnModel().getColumn(2).setPreferredWidth(300);
							Rtable.getColumnModel().getColumn(3).setPreferredWidth(530);
							Rtable.getColumnModel().getColumn(4).setPreferredWidth(500);
							Rtable.getColumnModel().getColumn(5).setPreferredWidth(180);
							Rtable.getColumnModel().getColumn(6).setPreferredWidth(280);
							RQuery.add(RJSP);
						} else {
							System.out.println("查询失败！");
							JTextField text1 = new JTextField("  查询失败！当前系统中暂无读者");
							JFrame noreader = new JFrame();
							noreader.setTitle("提示");
							noreader.setLocation(750, 500);
							noreader.setSize(400, 100);
							noreader.setDefaultCloseOperation(2);
							noreader.getContentPane().add(text1);
							noreader.setVisible(true);
						}
						rs.close();
						ps.close();
					} else {// 搜索指定读者信息
						StringBuffer sb = new StringBuffer();
						sb.append("select * from reader where");
						sb.append(" Rname").append(" like ").append("'%").append(Rinfo.getText()).append("%'");
						sb.append(" or RID").append(" like ").append("'%").append(Rinfo.getText()).append("%'");
						sb.append(" or Rschool").append(" like ").append("'%").append(Rinfo.getText()).append("%'");
						PreparedStatement ps = cn.prepareStatement(sb.toString());
						ResultSet rs = ps.executeQuery();
						System.out.println(String.format("execute sql is %s", sb.toString()));
						if (rs.next()) {// 结果集不为空
							rs.previous();
							System.out.println("搜索成功！");
							while (rs.next()) {
								String RID = rs.getString("RID");
								String Rname = rs.getString("Rname");
								String Rsex = rs.getString("Rsex");
								String Rschool = rs.getString("Rschool");
								String Rmajor = rs.getString("Rmajor");
								String Rgrade = rs.getString("Rgrade");
								String Rdate = rs.getString("Rdate");
								RtableModel.addRow(new Object[] { RID, Rname, Rsex, Rschool, Rmajor, Rgrade, Rdate });
							}
							int countRows = Rtable.getRowCount();// 获取当前表格总行数
							((DefaultTableModel) Rtable.getModel()).insertRow(countRows,
									new Object[] { " ", " ", " ", " ", " ", " ", " ", " " });
							Rtable.setBorder(new LineBorder(new Color(128, 128, 128), 3));
							Rtable.setRowHeight(40);// 指定每一行的行高40
							Rtable.getColumnModel().getColumn(0).setPreferredWidth(560);
							Rtable.getColumnModel().getColumn(1).setPreferredWidth(400);
							Rtable.getColumnModel().getColumn(2).setPreferredWidth(300);
							Rtable.getColumnModel().getColumn(3).setPreferredWidth(530);
							Rtable.getColumnModel().getColumn(4).setPreferredWidth(500);
							Rtable.getColumnModel().getColumn(5).setPreferredWidth(180);
							Rtable.getColumnModel().getColumn(6).setPreferredWidth(280);
							RQuery.add(RJSP);
						} else {
							System.out.println("查询失败！");
							JTextField text1 = new JTextField("  查询失败！当前系统中暂无对应读者");
							JFrame noreader = new JFrame();
							noreader.setTitle("提示");
							noreader.setLocation(750, 500);
							noreader.setSize(400, 100);
							noreader.setDefaultCloseOperation(2);
							noreader.getContentPane().add(text1);
							noreader.setVisible(true);
						}
						rs.close();
						ps.close();
					}
					cn.close();
				} catch (SQLException se) {// Handle errors for JDBC
					se.printStackTrace();
				} catch (Exception e1) {// Handle errors for Class.forName
					e1.printStackTrace();
				} finally {
				}
			}
		});

		// 设置详细信息按钮的监听
		Rxiangqing.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					// 在数据库中查询所有读者
					Class.forName("com.mysql.jdbc.Driver");
					Connection cn = DriverManager.getConnection(
							"jdbc:mysql://127.0.0.1:3306/librarysystem?characterEncoding=utf8&useSSL=true", "root",
							"twy97620");
					System.out.println("Connecting to database...");
					System.out.println("Creating xiangqingstatement...");
					StringBuffer sb = new StringBuffer("select * from reader where RID='");
					int selectedRow = Rtable.getSelectedRow(); // 获得选中行索引
					Object selectedRID = RtableModel.getValueAt(selectedRow, 0);
					sb.append(selectedRID).append("'");
					PreparedStatement ps = cn.prepareStatement(sb.toString());
					ResultSet rs = ps.executeQuery();
					System.out.println(String.format("execute sql is %s", sb.toString()));
					System.out.println("查看详情成功！");
					JTextArea readerdetail = new JTextArea();
					JScrollPane jsp_detail = new JScrollPane(readerdetail);
					JFrame freaderdetail = new JFrame();
					freaderdetail.setTitle("详细信息");
					freaderdetail.setLocation(750, 500);
					freaderdetail.setSize(550, 400);
					freaderdetail.setDefaultCloseOperation(2);
					freaderdetail.getContentPane().add(jsp_detail);
					freaderdetail.setVisible(true);

					if (rs.next()) {// 结果集不为空
						System.out.println("搜索成功！");
						rs.previous();
						while (rs.next()) {
							readerdetail.append("学号:" + rs.getString("RID") + "\n\n");
							readerdetail.append("姓名:" + rs.getString("Rname") + "\n\n");
							readerdetail.append("性别:" + rs.getString("Rsex") + "\n\n");
							readerdetail.append("学院:" + rs.getString("Rschool") + "\n\n");
							readerdetail.append("专业:" + rs.getString("Rmajor") + "\n\n");
							readerdetail.append("年级:" + rs.getString("Rgrade") + "\n\n");
							readerdetail.append("登录密码:" + rs.getString("Rkey") + "\n\n");
							readerdetail.append("注册日期:" + rs.getString("Rdate") + "\n\n");
							readerdetail.append("登录次数:" + rs.getString("Rcount") + "\n\n");
							readerdetail.append("最近一次登录时间:" + rs.getString("Rlatest") + "\n\n");
						}
					}
					readerdetail.append("\n");
					rs.close();
					ps.close();
					cn.close();
				} catch (SQLException se) {// Handle errors for JDBC
					se.printStackTrace();
				} catch (Exception e1) {// Handle errors for Class.forName
					e1.printStackTrace();
				} finally {
				}
			}
		});

		// 实例化新增读者面板组件
		JLabel LRID = new JLabel("学号（必填）：");
		LRID.setBounds(240, 30, 210, 40);
		JLabel LRname = new JLabel("姓名（必填）：");
		LRname.setBounds(240, 80, 210, 40);
		JLabel LRsex = new JLabel("性别（必填）：");
		LRsex.setBounds(240, 130, 210, 40);
		JLabel LRschool = new JLabel("学院（必填）：");
		LRschool.setBounds(240, 180, 210, 40);
		JLabel LRmajor = new JLabel("专业（必填）：");
		LRmajor.setBounds(240, 230, 210, 40);
		JLabel LRgrade = new JLabel("年级（必填）：");
		LRgrade.setBounds(240, 280, 210, 40);
		JLabel LRkey = new JLabel("初始密码（必填）：");
		LRkey.setBounds(240, 330, 210, 40);
		JLabel LRdate = new JLabel("注册日期（必填）：");
		LRdate.setBounds(240, 380, 210, 40);
		JTextField TRID = new JTextField();
		TRID.setBounds(450, 30, 290, 40);
		JTextField TRname = new JTextField();
		TRname.setBounds(450, 80, 290, 40);
		String sexlabels[] = { "男", "女" };
		JComboBox sexcomboBox = new JComboBox(sexlabels);
		sexcomboBox.setBounds(450, 130, 290, 40);
		JTextField TRschool = new JTextField();
		TRschool.setBounds(450, 180, 290, 40);
		JTextField TRmajor = new JTextField();
		TRmajor.setBounds(450, 230, 290, 40);
		JTextField TRgrade = new JTextField();
		TRgrade.setBounds(450, 280, 290, 40);
		JTextField TRkey = new JTextField();
		TRkey.setBounds(450, 330, 290, 40);

		JTextField TRdate = new JTextField();
		TRdate.setBounds(450, 380, 290, 40);

		JButton Rtianjia = new JButton("添加");
		Rtianjia.setBounds(500, 450, 120, 30);
		Radd.add(LRID);
		Radd.add(LRname);
		Radd.add(LRsex);
		Radd.add(LRschool);
		Radd.add(LRmajor);
		Radd.add(LRgrade);
		Radd.add(LRkey);
		Radd.add(LRdate);
		Radd.add(TRID);
		Radd.add(TRschool);
		Radd.add(TRname);
		Radd.add(sexcomboBox);
		Radd.add(TRmajor);
		Radd.add(TRgrade);
		Radd.add(TRkey);
		Radd.add(TRdate);
		Radd.add(Rtianjia);

		// 设置新增读者按钮的监听
		itemxzdz.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				TRID.setText("");
				TRschool.setText("");
				TRname.setText("");
				TRmajor.setText("");
				TRgrade.setText("");
				TRkey.setText("");
				TRdate.setText("");
				frame.remove(Query);
				frame.remove(change);
				frame.remove(add);
				frame.remove(RQuery);
				frame.remove(Rchange);
				frame.remove(jieyueQuery);
				frame.remove(fineQuery);
				frame.remove(usemanual);
				frame.remove(information);
				frame.add(Radd);
				frame.revalidate();
				frame.repaint();
			}
		});

		// 设置添加信息按钮的监听
		Rtianjia.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					// 在数据库中添加一条记录
					Class.forName("com.mysql.jdbc.Driver");
					Connection cn = DriverManager.getConnection(
							"jdbc:mysql://127.0.0.1:3306/librarysystem?characterEncoding=utf8&useSSL=true", "root",
							"twy97620");
					System.out.println("Connecting to database...");
					System.out.println("Creating tianjaistatement...");
					if (!TRID.getText().equals("") && !TRschool.getText().equals("") && !TRname.getText().equals("")
							&& !TRmajor.getText().equals("") && !TRgrade.getText().equals("")
							&& !TRkey.getText().equals("") && !TRdate.getText().equals("")) {
						StringBuffer sb11 = new StringBuffer("select * from reader where RID='");
						sb11.append(TRID.getText()).append("'");
						PreparedStatement ps11 = cn.prepareStatement(sb11.toString());
						ResultSet rs11 = ps11.executeQuery();
						System.out.println(String.format("execute sql is %s", sb11.toString()));
						if (!rs11.next()) {// 不存在该学号读者
							StringBuffer sb = new StringBuffer("call pinsertReader('");
							sb.append(TRID.getText()).append("','");
							sb.append(TRname.getText()).append("','");
							sb.append(sexcomboBox.getSelectedItem()).append("','");
							sb.append(TRschool.getText()).append("','");
							sb.append(TRmajor.getText()).append("','");
							sb.append(TRgrade.getText()).append("','");
							sb.append(TRkey.getText()).append("','");
							sb.append(TRdate.getText()).append("')");

							PreparedStatement ps = cn.prepareStatement(sb.toString());
							int rows = ps.executeUpdate();
							System.out.println(String.format("execute sql is %s", sb.toString()));
							ps.close();
							if (rows != 0) {
								System.out.println("添加成功！");
								JTextField text = new JTextField("                 添加成功！ ");
								JFrame success = new JFrame();
								success.setTitle("提示");
								success.setLocation(750, 500);
								success.setSize(300, 100);
								success.setDefaultCloseOperation(2);
								success.getContentPane().add(text);
								success.setVisible(true);
							} 
							}
						else {
								System.out.println("添加失败！");
								JTextField text = new JTextField("            添加失败！该学号读者已经存在 ");
								JFrame fail = new JFrame();
								fail.setTitle("提示");
								fail.setLocation(750, 500);
								fail.setSize(400, 100);
								fail.setDefaultCloseOperation(2);
								fail.getContentPane().add(text);
								fail.setVisible(true);
							}
						rs11.close();
						ps11.close();
					} else {
						System.out.println("添加失败！");
						JTextField text = new JTextField("            添加失败！必填信息必须正确输入 ");
						JFrame fail = new JFrame();
						fail.setTitle("提示");
						fail.setLocation(750, 500);
						fail.setSize(400, 100);
						fail.setDefaultCloseOperation(2);
						fail.getContentPane().add(text);
						fail.setVisible(true);
					}
					cn.close();
				} catch (SQLException se) {// Handle errors for JDBC
					se.printStackTrace();
				} catch (Exception e1) {// Handle errors for Class.forName
					e1.printStackTrace();
				} finally {
				}
			}
		});

		// 实例化修改读者界面组件
		JTextArea changedRID = new JTextArea(TIP6);
		changedRID.setBounds(300, 10, 300, 30);
		changedRID.setForeground(Color.gray);
		JButton Rchakanxinxi = new JButton("查看信息");
		Rchakanxinxi.setBounds(610, 10, 160, 30);
		JLabel changeRname = new JLabel("姓名：");
		changeRname.setBounds(240, 80, 210, 40);
		JLabel changeRsex = new JLabel("性别：");
		changeRsex.setBounds(240, 130, 210, 40);
		JLabel changeRschool = new JLabel("学院：");
		changeRschool.setBounds(240, 180, 210, 40);
		JLabel changeRmajor = new JLabel("专业：");
		changeRmajor.setBounds(240, 230, 210, 40);
		JLabel changeRgrade = new JLabel("年级：");
		changeRgrade.setBounds(240, 280, 210, 40);
		JLabel changeRkey = new JLabel("登录密码：");
		changeRkey.setBounds(240, 330, 210, 40);
		JLabel changeRdate = new JLabel("注册日期：");
		changeRdate.setBounds(240, 380, 210, 40);
		// JTextField xgRID = new JTextField();
		// xgRID.setBounds(450, 30, 290, 40);
		JTextField xgRname = new JTextField();
		xgRname.setBounds(450, 80, 290, 40);
		String xgsexlabels[] = { "男", "女" };
		JComboBox xgsexcomboBox = new JComboBox(xgsexlabels);
		xgsexcomboBox.setBounds(450, 130, 290, 40);
		JTextField xgRschool = new JTextField();
		xgRschool.setBounds(450, 180, 290, 40);
		JTextField xgRmajor = new JTextField();
		xgRmajor.setBounds(450, 230, 290, 40);
		JTextField xgRgrade = new JTextField();
		xgRgrade.setBounds(450, 280, 290, 40);
		JTextField xgRkey = new JTextField();
		xgRkey.setBounds(450, 330, 290, 40);

		JTextField xgRdate = new JTextField();
		xgRdate.setBounds(450, 380, 290, 40);

		JButton Rxiugai = new JButton("修改");
		Rxiugai.setBounds(500, 450, 120, 30);

		Rchange.add(changedRID);
		Rchange.add(changeRname);
		Rchange.add(changeRsex);
		Rchange.add(changeRschool);
		Rchange.add(changeRmajor);
		Rchange.add(changeRgrade);
		Rchange.add(changeRkey);
		Rchange.add(changeRdate);
		// Rchange.add(TRID);
		Rchange.add(xgRschool);
		Rchange.add(xgRname);
		Rchange.add(xgsexcomboBox);
		Rchange.add(xgRmajor);
		Rchange.add(xgRgrade);
		Rchange.add(xgRkey);
		Rchange.add(xgRdate);
		Rchange.add(Rxiugai);
		Rchange.add(Rchakanxinxi);

		// 设置修改读者按钮的监听
		itemxgdz.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				changedRID.setText(TIP6);
				// xgcomboBox.setSelectedItem("请选择");
				// changeBookNo.setText("");
				xgRschool.setText("");
				xgRname.setText("");
				xgRmajor.setText("");
				xgRgrade.setText("");
				xgRkey.setText("");
				xgRdate.setText("");
				frame.remove(add);
				frame.remove(Query);
				frame.remove(Radd);
				frame.remove(change);
				frame.remove(RQuery);
				frame.remove(jieyueQuery);
				frame.remove(fineQuery);
				frame.remove(usemanual);
				frame.remove(information);
				frame.add(Rchange);
				// frame.remove(information);
				// frame.remove(usemanual);
				frame.revalidate();
				frame.repaint();
			}
		});

		// 设置查看信息按钮的监听
		Rchakanxinxi.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				xgRschool.setText("");
				xgRname.setText("");
				xgRmajor.setText("");
				xgRgrade.setText("");
				xgRkey.setText("");
				xgRdate.setText("");
				try {
					// 查看待修改读者
					Class.forName("com.mysql.jdbc.Driver");
					Connection cn = DriverManager.getConnection(
							"jdbc:mysql://127.0.0.1:3306/librarysystem?characterEncoding=utf8&useSSL=true", "root",
							"twy97620");
					System.out.println("Connecting to database...");
					System.out.println("Rchakanxinxi...");
					// 输入RID为空
					if (changedRID.getText().equals("") || changedRID.getText().equals(TIP6)) {
						JTextField text = new JTextField("              输入不可为空！");
						JFrame error = new JFrame();
						error.setTitle("错误");
						error.setLocation(750, 500);
						error.setSize(300, 100);
						error.setDefaultCloseOperation(2);
						error.add(text);
						error.setVisible(true);
					} else {
						StringBuffer sb = new StringBuffer();
						sb.append("select * from reader where RID='");
						sb.append(changedRID.getText()).append("'");
						PreparedStatement ps = cn.prepareStatement(sb.toString());
						ResultSet rs = ps.executeQuery();
						System.out.println(String.format("execute sql is %s", sb.toString()));
						if (rs.next()) {// 结果集不为空
							xgRschool.setText(rs.getString("Rschool"));
							xgRname.setText(rs.getString("Rname"));
							xgRmajor.setText(rs.getString("Rmajor"));
							xgRgrade.setText(rs.getString("Rgrade"));
							xgRkey.setText(rs.getString("Rkey"));
							xgRdate.setText(rs.getString("Rdate"));
							xgsexcomboBox.setSelectedItem(rs.getString("Rsex"));
						} else {
							System.out.println("     查看失败！输入学号有误");
							JTextField text1 = new JTextField("      查看失败！输入学号有误");
							JFrame b_fail = new JFrame();
							b_fail.setTitle("提示");
							b_fail.setLocation(750, 500);
							b_fail.setSize(420, 100);
							b_fail.setDefaultCloseOperation(2);
							b_fail.add(text1);
							b_fail.setVisible(true);
						}
						rs.close();
						ps.close();
					}
					cn.close();
				} catch (SQLException se) {// Handle errors for JDBC
					se.printStackTrace();
				} catch (Exception e1) {// Handle errors for Class.forName
					e1.printStackTrace();
				} finally {
				}
			}
		});

		// 设置修改按钮的监听
		Rxiugai.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					// 修改读者
					Class.forName("com.mysql.jdbc.Driver");
					Connection cn = DriverManager.getConnection(
							"jdbc:mysql://127.0.0.1:3306/librarysystem?characterEncoding=utf8&useSSL=true", "root",
							"twy97620");
					System.out.println("Connecting to database...");
					System.out.println("xiugai...");

					StringBuffer sb = new StringBuffer();
					sb.append("update reader set Rname='").append(xgRname.getText()).append("',Rsex='");
					sb.append(xgsexcomboBox.getSelectedItem()).append("',Rschool='");
					sb.append(xgRschool.getText()).append("',Rmajor='");
					sb.append(xgRmajor.getText()).append("',Rgrade='");
					sb.append(xgRgrade.getText()).append("',Rkey='");
					sb.append(xgRkey.getText()).append("',Rdate='");
					sb.append(xgRdate.getText()).append("'");
					sb.append(" where RID='").append(changedRID.getText()).append("'");

					PreparedStatement ps = cn.prepareStatement(sb.toString());
					int rows = ps.executeUpdate();
					System.out.println(String.format("execute sql is %s", sb.toString()));

					if (rows != 0) {// 结果集不为空
						System.out.println("     修改成功！可在系统中查询修改后的读者");
						JTextField text1 = new JTextField("      修改成功！可在系统中查询修改后的读者");
						JFrame xgsuccess = new JFrame();
						xgsuccess.setTitle("提示");
						xgsuccess.setLocation(750, 500);
						xgsuccess.setSize(420, 100);
						xgsuccess.setDefaultCloseOperation(2);
						xgsuccess.add(text1);
						xgsuccess.setVisible(true);
					}
					ps.close();
					cn.close();
				} catch (SQLException se) {// Handle errors for JDBC
					se.printStackTrace();
				} catch (Exception e1) {// Handle errors for Class.forName
					e1.printStackTrace();
				} finally {
				}
			}
		});

		// 实例化删除读者界面组件
		JButton shanchureader = new JButton("删除");
		shanchureader.setBounds(320, 30, 100, 40);
		JTextArea deletedRID = new JTextArea(TIP7);
		deletedRID.setForeground(Color.GRAY);
		deletedRID.setBounds(10, 30, 300, 40);
		// 设置删除读者按钮的监听
		itemscdz.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				deletedISBN.setText(TIP7);
				JFrame deletereader = new JFrame();
				deletereader.setTitle("删除读者");
				deletereader.setLocation(750, 500);
				deletereader.setSize(445, 150);
				deletereader.setDefaultCloseOperation(2);
				deletereader.setLayout(null);
				deletereader.add(shanchureader);
				deletereader.add(deletedRID);
				deletereader.setVisible(true);
			}
		});

		// 设置删除按钮的监听
		shanchureader.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					// 删除读者
					Class.forName("com.mysql.jdbc.Driver");
					Connection cn = DriverManager.getConnection(
							"jdbc:mysql://127.0.0.1:3306/librarysystem?characterEncoding=utf8&useSSL=true", "root",
							"twy97620");
					System.out.println("Connecting to database...");
					System.out.println("deleting...");
					// 输入学号为空
					if (deletedRID.getText().equals("") || deletedRID.getText().equals(TIP7)) {
						JTextField text = new JTextField("              输入不可为空！");
						JFrame error = new JFrame();
						error.setTitle("错误");
						error.setLocation(750, 500);
						error.setSize(300, 100);
						error.setDefaultCloseOperation(2);
						error.add(text);
						error.setVisible(true);
					} else {
						StringBuffer sb11 = new StringBuffer("select * from vborrow where 学号='");
						sb11.append(deletedRID.getText()).append("'");
						PreparedStatement ps11 = cn.prepareStatement(sb11.toString());
						ResultSet rs11 = ps11.executeQuery();
						if(!rs11.next()){//该读者所借图书已归还
						StringBuffer sb = new StringBuffer();
						sb.append("delete from reader where RID='");
						sb.append(deletedRID.getText()).append("'");
						PreparedStatement ps = cn.prepareStatement(sb.toString());
						int rows = ps.executeUpdate();
						System.out.println(String.format("execute sql is %s", sb.toString()));
						ps.close();
						if (rows != 0) {// 结果集不为空
							JTextField text1 = new JTextField("             删除成功！");
							JFrame b_success = new JFrame();
							b_success.setTitle("提示");
							b_success.setLocation(750, 500);
							b_success.setSize(260, 100);
							b_success.setDefaultCloseOperation(2);
							b_success.add(text1);
							b_success.setVisible(true);
						} else {
							System.out.println("       删除失败！输入学号有误");
							JTextField text1 = new JTextField("    删除失败！输入学号有误");
							JFrame b_fail = new JFrame();
							b_fail.setTitle("提示");
							b_fail.setLocation(750, 500);
							b_fail.setSize(420, 100);
							b_fail.setDefaultCloseOperation(2);
							b_fail.add(text1);
							b_fail.setVisible(true);
						}
						}else {
							System.out.println("       删除失败！输入学号有误");
							JTextField text1 = new JTextField("    删除失败！该读者仍有图书未归还");
							JFrame b_fail = new JFrame();
							b_fail.setTitle("提示");
							b_fail.setLocation(750, 500);
							b_fail.setSize(420, 100);
							b_fail.setDefaultCloseOperation(2);
							b_fail.add(text1);
							b_fail.setVisible(true);
						}
					}
					cn.close();
				} catch (SQLException se) {// Handle errors for JDBC
					se.printStackTrace();
				} catch (Exception e1) {// Handle errors for Class.forName
					e1.printStackTrace();
				} finally {
				}
			}
		});

		// 实例化查询借阅记录界面组件并添加
		JTextField jieyueRID = new JTextField(TIP8);
		jieyueRID.setForeground(Color.GRAY);
		jieyueRID.setBounds(60, 10, 450, 35);
		JTextField jieyueISBN = new JTextField(TIP9);
		jieyueISBN.setForeground(Color.GRAY);
		jieyueISBN.setBounds(520, 10, 450, 35);
		JButton jieyuechaxun = new JButton();
		jieyuechaxun.setText("查询");
		jieyuechaxun.setBounds(980, 10, 100, 35);
		jieyueQuery.add(jieyueRID);
		jieyueQuery.add(jieyueISBN);
		jieyueQuery.add(jieyuechaxun);
		JRadioButton now = new JRadioButton("当前借阅");
		JRadioButton history = new JRadioButton("借阅历史");
		now.setVisible(true);
		now.setBackground(Color.WHITE);
		now.setBounds(400, 50, 160, 30);
		history.setVisible(true);
		history.setBackground(Color.white);
		history.setBounds(570, 50, 160, 30);
		ButtonGroup group = new ButtonGroup();
		group.add(now);
		group.add(history);
		jieyueQuery.add(now);
		jieyueQuery.add(history);
		String[] jcolumnNames = { "学号", "书名", "作者", "借出日期", "应还日期", "归还日期", "续借次数" }; // 列名
		Object[][] jtableVales = null; // 数据
		DefaultTableModel jtableModel = new DefaultTableModel(jtableVales, jcolumnNames); // 表格模型对象
		JTable jtable = new JTable(jtableModel);
		JScrollPane jJSP = new JScrollPane(jtable);
		jJSP.setBounds(10, 100, 1090, 360);

		jtable.setRowSelectionAllowed(true);
		jtable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

		// 设置查询借阅记录按钮的监听
		itemjyjl.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				jieyueRID.setText(TIP8);
				jieyueISBN.setText(TIP9);
				jtableModel.setRowCount(0);
				frame.remove(add);
				frame.remove(change);
				frame.remove(Radd);
				frame.remove(Rchange);
				frame.remove(RQuery);
				frame.remove(fineQuery);
				frame.remove(usemanual);
				frame.remove(information);
				frame.remove(Query);
				frame.add(jieyueQuery);
				frame.revalidate();
				frame.repaint();
			}
		});

		// 设置查询按钮的监听
		jieyuechaxun.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				jtableModel.setRowCount(0);
				try {
					// 在数据库中查询所有记录
					Class.forName("com.mysql.jdbc.Driver");
					Connection cn = DriverManager.getConnection(
							"jdbc:mysql://127.0.0.1:3306/librarysystem?characterEncoding=utf8&useSSL=true", "root",
							"twy97620");
					System.out.println("Connecting to database...");
					System.out.println("Creating CXstatement...");
					// 搜索借阅记录
					StringBuffer sb = new StringBuffer();
					sb.append(
							"select RID,Bookname,Bookwriter,outdate,returndate,indate,xujie from borrow,book where borrow.ISBN=book.ISBN");
					if (!jieyueRID.getText().equals("") && !jieyueRID.getText().equals(TIP8))
						sb.append(" and RID='").append(jieyueRID.getText()).append("'");
					if (!jieyueISBN.getText().equals("") && !jieyueISBN.getText().equals(TIP9))
						sb.append(" and ISBN='").append(jieyueISBN.getText()).append("'");
					if (now.isSelected())
						sb.append(" and indate is null");
					if (history.isSelected())
						sb.append(" and indate is not null");
					PreparedStatement ps = cn.prepareStatement(sb.toString());
					ResultSet rs = ps.executeQuery();
					if (rs.next()) {// 结果集不为空
						rs.previous();
						System.out.println("搜索成功！");
						while (rs.next()) {
							String RID = rs.getString("RID");
							String bookname = rs.getString("bookname");
							String bookwriter = rs.getString("Bookwriter");
							String outdate = rs.getString("outdate");
							String returndate = rs.getString("returndate");
							String indate = rs.getString("indate");
							String xujie = rs.getString("xujie");
							jtableModel.addRow(
									new Object[] { RID, bookname, bookwriter, outdate, returndate, indate, xujie });
						}
						int countRows = jtable.getRowCount();// 获取当前表格总行数
						((DefaultTableModel) jtable.getModel()).insertRow(countRows,
								new Object[] { " ", " ", " ", " ", " ", " ", " ", " " });
						jtable.setBorder(new LineBorder(new Color(128, 128, 128), 3));
						jtable.setRowHeight(40);// 指定每一行的行高40
						jtable.getColumnModel().getColumn(0).setPreferredWidth(470);
						jtable.getColumnModel().getColumn(1).setPreferredWidth(550);
						jtable.getColumnModel().getColumn(2).setPreferredWidth(400);
						jtable.getColumnModel().getColumn(3).setPreferredWidth(470);
						jtable.getColumnModel().getColumn(4).setPreferredWidth(470);
						jtable.getColumnModel().getColumn(5).setPreferredWidth(470);
						jtable.getColumnModel().getColumn(6).setPreferredWidth(240);
						jieyueQuery.add(jJSP);
					} else {
						System.out.println("查询失败！");
						JTextField text1 = new JTextField("  查询失败！当前系统中暂无借阅记录");
						JFrame norecord = new JFrame();
						norecord.setTitle("提示");
						norecord.setLocation(750, 500);
						norecord.setSize(400, 100);
						norecord.setDefaultCloseOperation(2);
						norecord.getContentPane().add(text1);
						norecord.setVisible(true);
					}
					rs.close();
					ps.close();
					cn.close();
				} catch (SQLException se) {// Handle errors for JDBC
					se.printStackTrace();
				} catch (Exception e1) {// Handle errors for Class.forName
					e1.printStackTrace();
				} finally {
				}
			}
		});

		// 实例化查询罚款记录界面组件并添加
		JTextField fineRID = new JTextField(TIP8);
		fineRID.setForeground(Color.GRAY);
		fineRID.setBounds(60, 10, 450, 35);
		JTextField fineISBN = new JTextField(TIP9);
		fineISBN.setForeground(Color.GRAY);
		fineISBN.setBounds(520, 10, 450, 35);
		JButton finechaxun = new JButton();
		finechaxun.setText("查询");
		finechaxun.setBounds(980, 10, 100, 35);
		fineQuery.add(fineRID);
		fineQuery.add(fineISBN);
		fineQuery.add(finechaxun);

		String[] fcolumnNames = { "学号", "书名", "借出日期", "应还日期", "归还日期", "罚款金额", "支付状态" }; // 列名
		Object[][] ftableVales = null; // 数据
		DefaultTableModel ftableModel = new DefaultTableModel(ftableVales, fcolumnNames); // 表格模型对象
		JTable ftable = new JTable(ftableModel);
		JScrollPane fJSP = new JScrollPane(ftable);
		fJSP.setBounds(10, 100, 1090, 360);

		ftable.setRowSelectionAllowed(true);
		ftable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

		// 设置查询罚款记录按钮的监听
		itemfkjl.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				fineRID.setText(TIP8);
				fineISBN.setText(TIP9);
				ftableModel.setRowCount(0);
				frame.remove(add);
				frame.remove(change);
				frame.remove(Radd);
				frame.remove(Rchange);
				frame.remove(RQuery);
				frame.remove(Query);
				frame.remove(jieyueQuery);
				frame.remove(usemanual);
				frame.remove(information);
				frame.add(fineQuery);
				frame.revalidate();
				frame.repaint();
			}
		});

		// 设置查询按钮的监听
		finechaxun.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ftableModel.setRowCount(0);
				try {
					// 在数据库中查询所有记录
					Class.forName("com.mysql.jdbc.Driver");
					Connection cn = DriverManager.getConnection(
							"jdbc:mysql://127.0.0.1:3306/librarysystem?characterEncoding=utf8&useSSL=true", "root",
							"twy97620");
					System.out.println("Connecting to database...");
					System.out.println("Creating CXstatement...");
					// 搜索罚款记录
					StringBuffer sb = new StringBuffer();
					sb.append(
							"select RID,Bookname,outdate,returndate,indate,fine,FKCLState from borrow,book where borrow.ISBN=book.ISBN");
					if (!fineRID.getText().equals("") && !fineRID.getText().equals(TIP8))
						sb.append(" and RID='").append(fineRID.getText()).append("'");
					if (!fineISBN.getText().equals("") && !fineISBN.getText().equals(TIP9))
						sb.append(" and ISBN='").append(fineISBN.getText()).append("'");

					sb.append(" and fine>0");

					PreparedStatement ps = cn.prepareStatement(sb.toString());
					ResultSet rs = ps.executeQuery();
					System.out.println(String.format("execute sql is %s", sb.toString()));
					if (rs.next()) {// 结果集不为空
						rs.previous();
						System.out.println("搜索成功！");
						while (rs.next()) {
							String RID = rs.getString("RID");
							String bookname = rs.getString("bookname");
							String fine = rs.getString("fine");
							String outdate = rs.getString("outdate");
							String returndate = rs.getString("returndate");
							String indate = rs.getString("indate");
							String FKCLState = rs.getString("FKCLState");
							ftableModel.addRow(
									new Object[] { RID, bookname, outdate, returndate, indate, fine, FKCLState });
						}
						int countRows = ftable.getRowCount();// 获取当前表格总行数
						((DefaultTableModel) ftable.getModel()).insertRow(countRows,
								new Object[] { " ", " ", " ", " ", " ", " ", " ", " " });
						ftable.setBorder(new LineBorder(new Color(128, 128, 128), 3));
						ftable.setRowHeight(40);// 指定每一行的行高40
						ftable.getColumnModel().getColumn(0).setPreferredWidth(530);
						ftable.getColumnModel().getColumn(1).setPreferredWidth(550);
						ftable.getColumnModel().getColumn(2).setPreferredWidth(400);
						ftable.getColumnModel().getColumn(3).setPreferredWidth(400);
						ftable.getColumnModel().getColumn(4).setPreferredWidth(400);
						ftable.getColumnModel().getColumn(5).setPreferredWidth(240);
						ftable.getColumnModel().getColumn(6).setPreferredWidth(240);
						fineQuery.add(fJSP);
					} else {
						System.out.println("查询失败！");
						JTextField text1 = new JTextField("  查询失败！当前系统中暂无罚款记录");
						JFrame norecord = new JFrame();
						norecord.setTitle("提示");
						norecord.setLocation(750, 500);
						norecord.setSize(400, 100);
						norecord.setDefaultCloseOperation(2);
						norecord.getContentPane().add(text1);
						norecord.setVisible(true);
					}
					rs.close();
					ps.close();
					cn.close();
				} catch (SQLException se) {// Handle errors for JDBC
					se.printStackTrace();
				} catch (Exception e1) {// Handle errors for Class.forName
					e1.printStackTrace();
				} finally {
				}
			}
		});

		// 实例化查看信息界面组件并添加
		JTextArea CKXXText = new JTextArea("");
		JScrollPane jsp_ckxx = new JScrollPane(CKXXText);
		jsp_ckxx.setBounds(460, 30, 420, 435);
		information.add(jsp_ckxx);
		JLabel touxiangLabel = new JLabel();
		touxiangLabel.setIcon(new ImageIcon("C:\\Users\\lenovo-pc\\Pictures\\表情包\\22.png"));
		touxiangLabel.setBounds(160, 60, 340, 340);
		information.add(touxiangLabel);
		// 设置查看信息按钮的监听
		itemckxx.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				CKXXText.setText("");
				try {
					// 显示当前管理员基本信息
					Class.forName("com.mysql.jdbc.Driver");
					Connection cn = DriverManager.getConnection(
							"jdbc:mysql://127.0.0.1:3306/librarysystem?characterEncoding=utf8&useSSL=true", "root",
							"twy97620");
					System.out.println("Connecting to database...");
					System.out.println("查询当前管理员信息...");
					StringBuffer sb = new StringBuffer();
					sb.append("select ManaID,Mname,Msex,MTelephone,MAddress from manager where ManaID='");
					sb.append(user).append("'");
					System.out.println(String.format("execute sql is %s", sb.toString()));
					PreparedStatement ps = cn.prepareStatement(sb.toString());
					ResultSet rs = ps.executeQuery();
					System.out.println("查询成功！");
					if (rs.next()) {
						CKXXText.append("\n\n\n   工号" + " : " + rs.getString(1) + "\n\n");
						CKXXText.append("   姓名" + " : " + rs.getString(2) + "\n\n");
						CKXXText.append("   性别" + " : " + rs.getString(3) + "\n\n");
						CKXXText.append("   电话" + " : " + rs.getString(4) + "\n\n");
						CKXXText.append("   地址" + " : " + rs.getString(5) + "\n\n");
					}
					rs.close();
					ps.close();
					cn.close();
				} catch (SQLException se) {// Handle errors for JDBC
					se.printStackTrace();
				} catch (Exception e1) {// Handle errors for Class.forName
					e1.printStackTrace();
				} finally {
				}
				frame.remove(add);
				frame.remove(change);
				frame.remove(Radd);
				frame.remove(Rchange);
				frame.remove(RQuery);
				frame.remove(Query);
				frame.remove(jieyueQuery);
				frame.remove(fineQuery);
				frame.remove(usemanual);
				frame.add(information);
				frame.revalidate();
				frame.repaint();
			}
		});

		// 实例化修改密码界面组件并添加
		JTextField oldkey = new JTextField(TIP10);
		oldkey.setForeground(Color.GRAY);
		oldkey.setBounds(40, 30, 300, 40);
		JTextField newkey = new JTextField(TIP11);
		newkey.setForeground(Color.GRAY);
		newkey.setBounds(40, 80, 300, 40);
		JButton keyxiugai = new JButton("修改");
		keyxiugai.setBounds(150, 130, 100, 40);
		// 设置修改密码按钮的监听
		itemxgmm.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JFrame fchangekey = new JFrame();
				fchangekey.setTitle("修改密码");
				fchangekey.setLocation(750, 500);
				fchangekey.setSize(400, 220);
				fchangekey.setDefaultCloseOperation(2);
				fchangekey.setLayout(null);
				fchangekey.add(oldkey);
				fchangekey.add(newkey);
				fchangekey.add(xiugai);
				fchangekey.setVisible(true);
			}
		});

		// 设置修改按钮的监听
		keyxiugai.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					// 修改密码
					Class.forName("com.mysql.jdbc.Driver");
					Connection cn = DriverManager.getConnection(
							"jdbc:mysql://127.0.0.1:3306/librarysystem?characterEncoding=utf8&useSSL=true", "root",
							"twy97620");
					System.out.println("Connecting to database...");
					System.out.println("xiugaimima...");

					if (oldkey.getText().equals("") || oldkey.getText().equals(TIP10) || newkey.getText().equals("")
							|| newkey.getText().equals(TIP11)) {
						JTextField text1 = new JTextField("            修改失败！输入不可为空");
						JFrame c_fail = new JFrame();
						c_fail.setTitle("提示");
						c_fail.setLocation(750, 500);
						c_fail.setSize(400, 100);
						c_fail.setDefaultCloseOperation(2);
						c_fail.add(text1);
						c_fail.setVisible(true);
					} else if (newkey.getText().length() > 10) {// 新密码长度超过10位
						JTextField text3 = new JTextField("            修改失败！新密码不可超过10位");
						JFrame wrong_newkey = new JFrame();
						wrong_newkey.setTitle("提示");
						wrong_newkey.setLocation(750, 500);
						wrong_newkey.setSize(400, 100);
						wrong_newkey.setDefaultCloseOperation(2);
						wrong_newkey.add(text3);
						wrong_newkey.setVisible(true);
					} else {
						StringBuffer sb = new StringBuffer();
						sb.append("select Mkey from manager where ManaID='");
						sb.append(user).append("'");
						PreparedStatement ps = cn.prepareStatement(sb.toString());
						ResultSet rs = ps.executeQuery();
						System.out.println(String.format("execute sql is %s", sb.toString()));
						if (rs.next()) {
							if (rs.getString(1).equals(oldkey.getText())) {
								StringBuffer sb1 = new StringBuffer();
								sb1.append("update manager set Mkey='").append(newkey.getText());
								sb1.append("' where ManaID='").append(user).append("'");
								PreparedStatement ps1 = cn.prepareStatement(sb1.toString());
								int rows1 = ps1.executeUpdate();
								System.out.println(String.format("execute sql is %s", sb1.toString()));
								System.out.println("affected rows:" + rows1);
								ps1.close();
								JTextField text4 = new JTextField("            修改成功！新密码已生效");
								JFrame change_success = new JFrame();
								change_success.setTitle("提示");
								change_success.setLocation(750, 500);
								change_success.setSize(400, 100);
								change_success.setDefaultCloseOperation(2);
								change_success.add(text4);
								change_success.setVisible(true);
							} else {
								JTextField text2 = new JTextField("            修改失败！旧密码输入错误");
								JFrame wrong_oldkey = new JFrame();
								wrong_oldkey.setTitle("提示");
								wrong_oldkey.setLocation(750, 500);
								wrong_oldkey.setSize(400, 100);
								wrong_oldkey.setDefaultCloseOperation(2);
								wrong_oldkey.add(text2);
								wrong_oldkey.setVisible(true);
							}
						}
						rs.close();
						ps.close();
						cn.close();
					}
				} catch (SQLException se) {// Handle errors for JDBC
					se.printStackTrace();
				} catch (Exception e1) {// Handle errors for Class.forName
					e1.printStackTrace();
				} finally {
				}
			}
		});
		// 实例化使用帮助界面组件并添加
		JTextArea BZText = new JTextArea("\n\n\n\n" + "图书管理功能：分为新增、修改、删除和查询功能，点击菜单栏可以对书刊进行相应的操作，注意删除书刊是时若当前有未归还的则无法删除！"
				+ "\n\n" + "读者管理功能：分为新增、修改、删除和查询功能，点击菜单栏可以对书刊进行相应的操作" + "\n\n"
				+ "记录查询功能：分为查询借阅记录和罚款记录,可以按读者学号或者书刊ISBN进行详细查询" + "\n\n"
				+ "个人中心：分为查看信息和修改密码，个人中心里可以查看到管理员自己的基本信息，修改密码窗口可以设置新密码" + "\n\n");
		JScrollPane jsp_bz = new JScrollPane(BZText);
		jsp_bz.setBounds(80, 30, 970, 435);
		usemanual.add(jsp_bz);
		// 设置使用帮助按钮的监听
		itembz.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.remove(add);
				frame.remove(change);
				frame.remove(Radd);
				frame.remove(Rchange);
				frame.remove(RQuery);
				frame.remove(Query);
				frame.remove(jieyueQuery);
				frame.remove(fineQuery);
				frame.remove(information);
				frame.add(usemanual);
				frame.revalidate();
				frame.repaint();
			}
		});
		// 设置退出按钮的监听
		b_exit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.dispose();
				@SuppressWarnings("unused")
				Login login = new Login();
			}
		});
	}
}
