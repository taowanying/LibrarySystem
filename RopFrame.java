package operation;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
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

public class RopFrame {
	private static final String TIP2 = "请输入书刊名称、关键字或作者";
	private static final String TIP3 = "请输入要借阅书刊的ISBN";
	private static final String TIP4 = "请输入要续借书刊的ISBN";
	private static final String TIP5 = "请输入要归还书刊的ISBN";
	private static final String TIP6 = "请输入要缴纳罚款的ISBN";
	private static final String TIP7 = "请输入相应的借书日期";
	private static final String TIP8 = "请输入旧密码";
	private static final String TIP9 = "请输入新密码，不可超过10位";

	public RopFrame(String name, String user) throws Exception {
		// 实例化窗口
		javax.swing.JFrame frame = new javax.swing.JFrame();
		frame.setLayout(null);
		frame.setTitle("中南大学图书馆");
		frame.setLocation(600, 260);
		frame.setSize(930, 650);
		frame.setDefaultCloseOperation(3);

		// 实例化菜单栏面板
		JPanel menu = new JPanel();
		menu.setBounds(150, 5, 600, 40);
		// 实例化欢迎面板
		JPanel Welcome = new JPanel();
		Welcome.setBounds(5, 540, 900, 35);
		frame.add(Welcome);
		// 实例化查询面板
		JPanel Query = new JPanel();
		Query.setBounds(5, 50, 900, 520);
		Query.setBackground(Color.WHITE);
		Query.setLayout(null);
		// 实例化按期归还面板
		JPanel Return_intime = new JPanel();
		Return_intime.setBounds(5, 50, 900, 520);
		Return_intime.setBackground(Color.white);
		Return_intime.setLayout(null);
		// 实例化过期缴费面板
		JPanel fine = new JPanel();
		fine.setBounds(5, 50, 900, 520);
		fine.setBackground(Color.white);
		fine.setLayout(null);
		// 实例化查看信息面板
		JPanel information = new JPanel();
		information.setBounds(5, 50, 900, 520);
		information.setBackground(Color.white);
		information.setLayout(null);
		// 实例化使用帮助面板
		JPanel usemanual = new JPanel();
		usemanual.setBounds(5, 50, 900, 520);
		usemanual.setBackground(Color.white);
		usemanual.setLayout(null);

		// 获取系统日期
		JLabel readername = new JLabel();
		Calendar cal = Calendar.getInstance();
		readername.setText("    今天是 " + cal.get(cal.YEAR) + "年" + (cal.get(cal.MONTH) + 1) + "月" + cal.get(cal.DATE)
				+ "日                      " + "欢迎你：" + name + "  |  ");
		readername.setBounds(5, 5, 100, 30);
		JButton b_exit = new JButton();
		b_exit.setBounds(480, 5, 60, 30);
		b_exit.setText("退出");
		JButton b_mylibrary = new JButton();
		b_mylibrary.setBounds(350, 5, 120, 30);
		b_mylibrary.setText("我的图书馆");
		Welcome.add(readername);
		Welcome.add(b_mylibrary);
		Welcome.add(b_exit);

		// 添加菜单栏
		JMenuBar bar = new JMenuBar();
		JMenu menucx = new JMenu("查询书籍");
		JMenu menujy = new JMenu("借阅书籍");
		JMenu menugh = new JMenu("归还书籍");
		JMenu menugr = new JMenu("个人中心");
		JMenu menubz = new JMenu("使用帮助");
		JMenuItem itemjssk = new JMenuItem("检索书刊");
		JMenuItem itemjyxs = new JMenuItem("借阅新书");
		JMenuItem itemxjjs = new JMenuItem("续借旧书");
		JMenuItem itemaqgh = new JMenuItem("归还书刊");
		JMenuItem itemgqjf = new JMenuItem("过期缴费");
		JMenuItem itemckxx = new JMenuItem("查看信息");
		JMenuItem itemxgmm = new JMenuItem("修改密码");
		JMenuItem itembz = new JMenuItem("关于此系统的使用");
		menucx.add(itemjssk);
		menujy.add(itemjyxs);
		menujy.add(itemxjjs);
		menugh.add(itemaqgh);
		menugh.add(itemgqjf);
		menugr.add(itemckxx);
		menugr.add(itemxgmm);
		menubz.add(itembz);
		bar.add(menucx);
		bar.add(menujy);
		bar.add(menugh);
		bar.add(menugr);
		bar.add(menubz);
		menu.add(bar);
		frame.add(menu);
		frame.setVisible(true);

		// 实例化查询界面组件并添加
		JTextField info = new JTextField(TIP2);
		info.setForeground(Color.GRAY);
		info.setBounds(150, 10, 550, 35);
		JButton chaxun = new JButton();
		chaxun.setText("查询");
		chaxun.setBounds(730, 10, 100, 35);
		Query.add(info);
		Query.add(chaxun);
		JButton chaxundetail = new JButton();
		chaxundetail.setText("结果中查询");
		chaxundetail.setBounds(490, 10, 140, 35);
		String labels[] = { "书名" ,"作者","出版社","分类号","索书号" };
		JComboBox comboBox = new JComboBox(labels);
		comboBox.setBounds(40, 10, 140, 35);
		String[] bcolumnNames = { "ISBN", "书名", "作者", "出版社", "关键字", "副本数", "借出数" }; // 列名
		Object[][] btableVales = null; // 数据
		DefaultTableModel tableModel = new DefaultTableModel(btableVales, bcolumnNames); // 表格模型对象
		JTable table = new JTable(tableModel);
		JScrollPane JSP = new JScrollPane(table);
		JSP.setBounds(0, 60, 920, 360);
		JButton xiangqing = new JButton("详细信息");
		xiangqing.setBounds(390, 450, 160, 30);
		Query.add(xiangqing);
		table.setRowSelectionAllowed(true);
		table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

		// 设置查询书刊按钮的监听
		itemjssk.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				chaxun.setText("查询");
				chaxun.setBounds(730, 10, 100, 35);
				info.setBounds(150, 10, 550, 35);
				Query.remove(chaxundetail);
				Query.remove(comboBox);
				tableModel.setRowCount(0);
				info.setText(TIP2);
				frame.remove(Return_intime);
				frame.remove(fine);
				frame.remove(information);
				frame.remove(usemanual);
				frame.add(Query);
				frame.revalidate();
				frame.repaint();
			}
		});

		// 设置查询按钮的监听
		chaxun.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				tableModel.setRowCount(0);
				chaxun.setText("重新查询");
				chaxun.setBounds(660, 10, 140, 35);
				info.setBounds(190, 10, 270, 35);
				Query.add(comboBox);
				Query.add(chaxundetail);
				try {
					// 在数据库中查询所有记录
					Class.forName("com.mysql.jdbc.Driver");
					Connection cn = DriverManager.getConnection(
							"jdbc:mysql://127.0.0.1:3306/librarysystem?characterEncoding=utf8&useSSL=true", "root",
							"twy97620");
					System.out.println("Connecting to database...");
					System.out.println("Creating CXstatement...");
					//先清除临时表中的数据
					PreparedStatement dps = cn.prepareStatement("delete from temp_Book");
					int drow = dps.executeUpdate();
					dps.close();
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
							PreparedStatement ps1 = cn.prepareStatement("insert into temp_Book select * from book");
							int rows = ps1.executeUpdate();
							System.out.println("临时表中插入"+rows+"行");
							ps1.close();
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
						sb.append(" or bookkeyword").append(" like ").append("'%").append(info.getText()).append("%'");
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
							StringBuffer sb1 = new StringBuffer();
							sb1.append("insert into temp_Book ").append(sb.toString());
							PreparedStatement ps1 = cn.prepareStatement(sb1.toString());
							int rows = ps1.executeUpdate();
							System.out.println("临时表中插入"+rows+"行");
							ps1.close();
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
				info.setText("");
			}
		});

		// 设置结果中查询按钮的监听
				chaxundetail.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						tableModel.setRowCount(0);
						
						//chaxun.setText("重新查询");
						//Query.add(comboBox);
						//Query.add(chaxundetail);
						try {
							// 在数据库中查询所有记录
							Class.forName("com.mysql.jdbc.Driver");
							Connection cn = DriverManager.getConnection(
									"jdbc:mysql://127.0.0.1:3306/librarysystem?characterEncoding=utf8&useSSL=true", "root",
									"twy97620");
							System.out.println("Connecting to database...");
							System.out.println("Creating CXstatement...");
							
							// 搜索指定信息
							if (!info.getText().equals("")) {//输入不为空
								StringBuffer sb1 = new StringBuffer();
								if(comboBox.getSelectedItem().toString().equals("作者")){
									sb1.append("select * from temp_Book where tBookwriter like '%");
									sb1.append(info.getText()).append("%'");
								}else if(comboBox.getSelectedItem().toString().equals("书名")){
									sb1.append("select * from temp_Book where tBookname like '%");
									sb1.append(info.getText()).append("%'");
								}else if(comboBox.getSelectedItem().toString().equals("出版社")){
									sb1.append("select * from temp_Book where tBookpublish like '%");
									sb1.append(info.getText()).append("%'");
								}else if(comboBox.getSelectedItem().toString().equals("分类号")){
									sb1.append("select * from temp_Book where tBookcategory like '%");
									sb1.append(info.getText()).append("%'");
								}else if(comboBox.getSelectedItem().toString().equals("索书号")){
									sb1.append("select * from temp_Book where tBookNo like '%");
									sb1.append(info.getText()).append("%'");
								}
								PreparedStatement ps1 = cn.prepareStatement(sb1.toString());
								ResultSet rs1 = ps1.executeQuery();
								System.out.println(String.format("execute sql is %s", sb1.toString()));
								if (rs1.next()) {// 结果集不为空
									rs1.previous();
									System.out.println("搜索成功！");
									//先清除临时表中的数据
									PreparedStatement dps = cn.prepareStatement("delete from temp_Book");
									int drow = dps.executeUpdate();
									dps.close();
									while (rs1.next()) {
										String ISBN = rs1.getString("tISBN");
										String bookname = rs1.getString("tbookname");
										String bookwriter = rs1.getString("tbookwriter");
										String bookpublish = rs1.getString("tbookpublish");
										String bookkeyword = rs1.getString("tbookkeyword");
										String bookcopy = rs1.getString("tbookcopy");
										String bookoutnum = rs1.getString("tbookoutnum");
										tableModel.addRow(new Object[] { ISBN, bookname, bookwriter, bookpublish, bookkeyword,
												bookcopy, bookoutnum });
										StringBuffer sb2 = new StringBuffer("insert into temp_Book values('");
										sb2.append(rs1.getString("tISBN")).append("','");
										sb2.append(rs1.getString("tBookNo")).append("','");
										sb2.append(rs1.getString("tBookname")).append("','");
										sb2.append(rs1.getString("tBookwriter")).append("','");
										sb2.append(rs1.getString("tBookpublish")).append("','");
										sb2.append(rs1.getString("tBookpubdate")).append("','");
										sb2.append(rs1.getString("tBookprice")).append("','");
										sb2.append(rs1.getString("tBooksubstract")).append("','");
										sb2.append(rs1.getString("tBookkeyword")).append("','");
										sb2.append(rs1.getString("tBookCopy")).append("','");
										sb2.append(rs1.getString("tBookoutnum")).append("','");
										sb2.append(rs1.getString("tBookcategory")).append("','");
										sb2.append(rs1.getString("tBookRID")).append("')");
										PreparedStatement ps2 = cn.prepareStatement(sb2.toString());
										System.out.println(String.format("execute sql is %s", sb2.toString()));
										int rows = ps2.executeUpdate();
										System.out.println("临时表中插入"+rows+"行");
										ps2.close();
									}
									int countRows = table.getRowCount();// 获取当前表格总行数
									((DefaultTableModel) table.getModel()).insertRow(countRows,
											new Object[] { " ", " ", " ", " ", " ", " ", " ", " " });
									table.setBorder(new LineBorder(new Color(128, 128, 128), 3));
									table.setRowHeight(40);// 指定每一行的行高40
									table.getColumnModel().getColumn(0).setPreferredWidth(560);
									table.getColumnModel().getColumn(1).setPreferredWidth(550);
									table.getColumnModel().getColumn(2).setPreferredWidth(380);
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
								rs1.close();
								ps1.close();
							} else {
									System.out.println("查询失败！");
									JTextField text1 = new JTextField("  查询失败！请输入具体查询信息");
									JFrame nobook = new JFrame();
									nobook.setTitle("提示");
									nobook.setLocation(750, 500);
									nobook.setSize(400, 100);
									nobook.setDefaultCloseOperation(2);
									nobook.getContentPane().add(text1);
									nobook.setVisible(true);
							}
							cn.close();
						} catch (SQLException se) {// Handle errors for JDBC
							se.printStackTrace();
						} catch (Exception e1) {// Handle errors for Class.forName
							e1.printStackTrace();
						} finally {
						}
						info.setText("");
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

		// 实例化借阅新书界面组件并添加
		JTextField borrowISBN = new JTextField(TIP3);
		borrowISBN.setForeground(Color.GRAY);
		borrowISBN.setBounds(10, 30, 300, 40);
		JButton jieyue = new JButton("借阅");
		jieyue.setBounds(320, 30, 100, 40);

		// 设置借阅新书按钮的监听
		itemjyxs.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JFrame fBorrowNew = new JFrame();
				fBorrowNew.setTitle("借阅新书");
				fBorrowNew.setLocation(750, 500);
				fBorrowNew.setSize(445, 150);
				fBorrowNew.setDefaultCloseOperation(2);
				fBorrowNew.setLayout(null);
				fBorrowNew.add(borrowISBN);
				fBorrowNew.add(jieyue);
				fBorrowNew.setVisible(true);
			}
		});

		// 设置借阅按钮的监听
		jieyue.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					// 借阅新书
					Class.forName("com.mysql.jdbc.Driver");
					Connection cn = DriverManager.getConnection(
							"jdbc:mysql://127.0.0.1:3306/librarysystem?characterEncoding=utf8&useSSL=true", "root",
							"twy97620");
					System.out.println("Connecting to database...");
					System.out.println("jieyue...");
					// 输入ISBN为空
					if (borrowISBN.getText().equals("") || borrowISBN.getText().equals(TIP3)) {
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
						sb.append("select * from vbook where ISBN='");
						sb.append(borrowISBN.getText()).append("'");
						sb.append(" and 副本数>借出数");
						PreparedStatement ps = cn.prepareStatement(sb.toString());
						ResultSet rs = ps.executeQuery();
						System.out.println(String.format("execute sql is %s", sb.toString()));

						if (rs.next()) {// 结果集不为空
							StringBuffer sb2 = new StringBuffer();
							sb2.append("select * from borrow where ISBN='").append(borrowISBN.getText());
							sb2.append("' and RID='").append(user);
							sb2.append("' and indate is null");// 必须还书后才能再次借阅同一本书刊
							PreparedStatement ps2 = cn.prepareStatement(sb2.toString());
							ResultSet rs2 = ps2.executeQuery();
							System.out.println(String.format("execute sql is %s", sb2.toString()));
							if (!rs2.next())// 表示前一本书刊已经归还
							{
								StringBuffer sb1 = new StringBuffer();
								sb1.append("insert into borrow values('");
								sb1.append(borrowISBN.getText()).append("'");
								sb1.append(",'").append(user).append("',");
								sb1.append("date(now()),null,date_add(date(now()),interval 1 week),0,0,null,'000001')");
								PreparedStatement ps1 = cn.prepareStatement(sb1.toString());
								int row = ps1.executeUpdate();
								System.out.println(String.format("execute sql is %s", sb1.toString()));
								System.out.println(row);
								ps1.close();
								JTextField text1 = new JTextField("  借阅成功！可在我的图书馆查看借阅记录");
								JFrame b_success = new JFrame();
								b_success.setTitle("提示");
								b_success.setLocation(750, 500);
								b_success.setSize(400, 100);
								b_success.setDefaultCloseOperation(2);
								b_success.add(text1);
								b_success.setVisible(true);
							} else {
								System.out.println("借阅失败！前一本书刊未归还");
								JTextField text1 = new JTextField("  借阅失败！不可同时借阅两本相同的书刊");
								JFrame b_fail = new JFrame();
								b_fail.setTitle("提示");
								b_fail.setLocation(750, 500);
								b_fail.setSize(400, 100);
								b_fail.setDefaultCloseOperation(2);
								b_fail.add(text1);
								b_fail.setVisible(true);
							}
							rs2.close();
							ps2.close();
						} else {
							System.out.println("借阅失败！");
							JTextField text1 = new JTextField("  借阅失败！输入ISBN有误或当前该书均已借出");
							JFrame b_fail = new JFrame();
							b_fail.setTitle("提示");
							b_fail.setLocation(750, 500);
							b_fail.setSize(400, 100);
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

		// 实例化续借旧书界面组件并添加
		JTextField xujieISBN = new JTextField(TIP4);
		xujieISBN.setForeground(Color.GRAY);
		xujieISBN.setBounds(10, 30, 300, 40);
		JButton xujie = new JButton("续借");
		xujie.setBounds(320, 30, 100, 40);
		// 设置续借旧书按钮的监听
		itemxjjs.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JFrame fBorrowOld = new JFrame();
				fBorrowOld.setTitle("续借旧书");
				fBorrowOld.setLocation(750, 500);
				fBorrowOld.setSize(445, 150);
				fBorrowOld.setDefaultCloseOperation(2);
				fBorrowOld.setLayout(null);
				fBorrowOld.add(xujieISBN);
				fBorrowOld.add(xujie);
				fBorrowOld.setVisible(true);
			}
		});

		// 设置续借按钮的监听
		xujie.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					// 续借旧书
					Class.forName("com.mysql.jdbc.Driver");
					Connection cn = DriverManager.getConnection(
							"jdbc:mysql://127.0.0.1:3306/librarysystem?characterEncoding=utf8&useSSL=true", "root",
							"twy97620");
					System.out.println("Connecting to database...");
					System.out.println("xujie...");
					// 输入ISBN为空
					if (xujieISBN.getText().equals("") || xujieISBN.getText().equals(TIP4)) {
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
						sb.append("select * from borrow where ISBN='");
						sb.append(xujieISBN.getText()).append("'");
						sb.append(" and RID='").append(user).append("'");
						PreparedStatement ps = cn.prepareStatement(sb.toString());
						ResultSet rs = ps.executeQuery();
						System.out.println(String.format("execute sql is %s", sb.toString()));

						if (rs.next()) {// 结果集不为空
							StringBuffer sb1 = new StringBuffer();
							sb1.append(
									"update borrow set returndate=date_add(returndate,interval 1 week) where ISBN='");
							sb1.append(xujieISBN.getText()).append("'");
							sb1.append(" and RID='").append(user).append("'");
							sb1.append(" and indate is null");
							StringBuffer sb2 = new StringBuffer();
							sb2.append("update borrow set xujie=xujie+1 where ISBN='");
							sb2.append(xujieISBN.getText()).append("'");
							sb2.append(" and RID='").append(user).append("'");
							PreparedStatement ps1 = cn.prepareStatement(sb1.toString());
							PreparedStatement ps2 = cn.prepareStatement(sb2.toString());
							int row = ps1.executeUpdate();
							int row1 = ps2.executeUpdate();
							System.out.println(String.format("execute sql is %s", sb1.toString()));
							System.out.println(String.format("execute sql is %s", sb2.toString()));
							System.out.println(row + " " + row1);
							ps1.close();
							ps2.close();
							JTextField text1 = new JTextField("  续借成功！可在我的图书馆查看续借记录");
							JFrame b_success = new JFrame();
							b_success.setTitle("提示");
							b_success.setLocation(750, 500);
							b_success.setSize(400, 100);
							b_success.setDefaultCloseOperation(2);
							b_success.add(text1);
							b_success.setVisible(true);
						} else {
							System.out.println("续借失败！");
							;
							JTextField text1 = new JTextField("            续借失败！输入ISBN有误");
							JFrame b_fail = new JFrame();
							b_fail.setTitle("提示");
							b_fail.setLocation(750, 500);
							b_fail.setSize(400, 100);
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

		// 实例化归还书刊界面组件并添加
		JTextArea AQGHText = new JTextArea(
				"请在上方输入文本框中输入要归还书刊的ISBN" + "\n" + "若需一次性归还所有书刊，请直接点击归还按钮" + "\n\n" + "以下为你当前的借阅记录:" + "\n\n");
		JTextField returnISBN = new JTextField(TIP5);
		returnISBN.setForeground(Color.GRAY);
		returnISBN.setBounds(120, 5, 550, 35);
		JButton guihuan = new JButton();
		guihuan.setText("归还");
		guihuan.setBounds(690, 5, 100, 35);
		JScrollPane jsp_return = new JScrollPane(AQGHText);
		jsp_return.setBounds(120, 50, 680, 435);
		Return_intime.add(returnISBN);
		Return_intime.add(guihuan);
		Return_intime.add(jsp_return);
		// 设置归还书刊按钮的监听
		itemaqgh.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				returnISBN.setText(TIP5);
				AQGHText.setText(
						"请在上方输入文本框中输入要归还书刊的ISBN" + "\n" + "若需一次性归还所有书刊，请直接点击归还按钮" + "\n\n" + "以下为你当前的借阅记录:" + "\n\n");
				try {
					// 显示当前借阅记录
					Class.forName("com.mysql.jdbc.Driver");
					Connection cn = DriverManager.getConnection(
							"jdbc:mysql://127.0.0.1:3306/librarysystem?characterEncoding=utf8&useSSL=true", "root",
							"twy97620");
					System.out.println("Connecting to database...");
					System.out.println("查询当前借阅记录...");
					StringBuffer sb = new StringBuffer();
					sb.append("select * from vborrow where 学号='");
					sb.append(user).append("'");
					PreparedStatement ps = cn.prepareStatement(sb.toString());
					ResultSet rs = ps.executeQuery();
					System.out.println(String.format("execute sql is %s", sb.toString()));
					if (rs.next()) {// 结果集不为空
						rs.previous();
						System.out.println("查询成功！");
						String str;
						while (rs.next()) {
							ResultSetMetaData rsmd = (ResultSetMetaData) rs.getMetaData();
							for (int i = 2; i < rsmd.getColumnCount(); i++) {
								str = rs.getString(i);
								AQGHText.append(rsmd.getColumnName(i) + " : " + str + "\n");
							}
							AQGHText.append("\n");
						}
						AQGHText.append("\n");
					} else {
						System.out.println("暂无当前借阅记录！");
						AQGHText.append("\n\n\n" + "当前没有要归还的书刊！");
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
				frame.remove(Query);
				frame.remove(fine);
				frame.remove(information);
				frame.remove(usemanual);
				frame.add(Return_intime);
				frame.revalidate();
				frame.repaint();
			}
		});
		// 设置归还按钮的监听
		guihuan.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					// 归还书刊
					Class.forName("com.mysql.jdbc.Driver");
					Connection cn = DriverManager.getConnection(
							"jdbc:mysql://127.0.0.1:3306/librarysystem?characterEncoding=utf8&useSSL=true", "root",
							"twy97620");
					System.out.println("Connecting to database...");
					System.out.println("guihuan...");
					// 输入ISBN为空
					if (returnISBN.getText().equals("") || returnISBN.getText().equals(TIP5)) {
						StringBuffer sb = new StringBuffer();
						sb.append("select ISBN from borrow where indate is null and RID='");
						sb.append(user).append("'");
						PreparedStatement ps = cn.prepareStatement(sb.toString());
						ResultSet rs = ps.executeQuery();

						if (rs.next()) {// 结果集不为空
							rs.previous();
							System.out.println("存在待归还书刊...");
							while (rs.next()) {
								String _ISBN = rs.getString(1);
								StringBuffer sb2 = new StringBuffer();
								sb2.append("update book set bookoutnum=bookoutnum-1 where ISBN='");
								sb2.append(_ISBN).append("'");
								PreparedStatement ps2 = cn.prepareStatement(sb2.toString());
								ps2.executeUpdate();
								System.out.println(String.format("execute sql is %s", sb2.toString()));
								ps2.close();
							}
							StringBuffer sb1 = new StringBuffer();
							sb1.append("update borrow set indate=date(now()) where indate is null and RID='");
							sb1.append(user).append("'");
							PreparedStatement ps1 = cn.prepareStatement(sb1.toString());
							ps1.executeUpdate();
							System.out.println(String.format("execute sql is %s", sb1.toString()));
							ps1.close();
							JTextField text1 = new JTextField("  归还成功！可在我的图书馆查看归还记录");
							JFrame b_success = new JFrame();
							b_success.setTitle("提示");
							b_success.setLocation(750, 500);
							b_success.setSize(400, 100);
							b_success.setDefaultCloseOperation(2);
							b_success.add(text1);
							b_success.setVisible(true);
						} else {
							System.out.println("不存在待归还书刊...");
							JTextField text1 = new JTextField("            归还失败！不存在待归还书刊");
							JFrame b_fail = new JFrame();
							b_fail.setTitle("提示");
							b_fail.setLocation(750, 500);
							b_fail.setSize(400, 100);
							b_fail.setDefaultCloseOperation(2);
							b_fail.add(text1);
							b_fail.setVisible(true);
						}
					} else {
						StringBuffer sb = new StringBuffer();
						sb.append("select * from borrow where ISBN='");
						sb.append(returnISBN.getText()).append("'");
						sb.append(" and RID='").append(user).append("'");
						sb.append(" and indate is null");
						PreparedStatement ps = cn.prepareStatement(sb.toString());
						ResultSet rs = ps.executeQuery();
						System.out.println(String.format("execute sql is %s", sb.toString()));

						if (rs.next()) {// 结果集不为空
							StringBuffer sb1 = new StringBuffer();
							sb1.append("update borrow set indate=date(now()) where ISBN='");
							sb1.append(returnISBN.getText()).append("'");
							sb1.append(" and RID='").append(user).append("'");
							sb1.append(" and indate is null");
							StringBuffer sb2 = new StringBuffer();
							sb2.append("update book set bookoutnum=bookoutnum-1 where ISBN='");
							sb2.append(returnISBN.getText()).append("'");
							PreparedStatement ps1 = cn.prepareStatement(sb1.toString());
							PreparedStatement ps2 = cn.prepareStatement(sb2.toString());
							int row = ps1.executeUpdate();
							int row1 = ps2.executeUpdate();
							System.out.println(String.format("execute sql is %s", sb1.toString()));
							System.out.println(String.format("execute sql is %s", sb2.toString()));
							System.out.println(row + " " + row1);
							ps1.close();
							ps2.close();
							JTextField text1 = new JTextField("  归还成功！可在我的图书馆查看归还记录");
							JFrame b_success = new JFrame();
							b_success.setTitle("提示");
							b_success.setLocation(750, 500);
							b_success.setSize(400, 100);
							b_success.setDefaultCloseOperation(2);
							b_success.add(text1);
							b_success.setVisible(true);
						} else {
							System.out.println("归还失败！");
							JTextField text1 = new JTextField("            归还失败！输入ISBN有误");
							JFrame b_fail = new JFrame();
							b_fail.setTitle("提示");
							b_fail.setLocation(750, 500);
							b_fail.setSize(400, 100);
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

		// 实例化过期缴费界面组件并添加
		JTextArea GQJFText = new JTextArea(
				"请在上方输入文本框中输入要缴纳罚款的ISBN和借书日期" + "\n" + "若需一次性缴纳所有罚款，请直接点击缴纳按钮" + "\n\n" + "以下为你的待缴费记录:" + "\n\n");
		JTextField fineISBN = new JTextField(TIP6);
		fineISBN.setForeground(Color.GRAY);
		fineISBN.setBounds(120, 5, 270, 35);
		JTextField borrowdate = new JTextField(TIP7);
		borrowdate.setForeground(Color.GRAY);
		borrowdate.setBounds(400, 5, 270, 35);
		JButton jiaona = new JButton();
		jiaona.setText("缴纳");
		jiaona.setBounds(690, 5, 100, 35);
		JScrollPane jsp_fine = new JScrollPane(GQJFText);
		jsp_fine.setBounds(120, 50, 680, 435);
		fine.add(fineISBN);
		fine.add(borrowdate);
		fine.add(jiaona);
		fine.add(jsp_fine);

		// 设置过期缴费按钮的监听
		itemgqjf.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				fineISBN.setText(TIP6);
				borrowdate.setText(TIP7);
				GQJFText.setText("请在上方输入文本框中输入要缴纳罚款的ISBN和借书日期" + "\n" + "若需一次性缴纳所有罚款，请直接点击缴纳按钮" + "\n\n");
				try {
					// 显示当前借阅记录
					Class.forName("com.mysql.jdbc.Driver");
					Connection cn = DriverManager.getConnection(
							"jdbc:mysql://127.0.0.1:3306/librarysystem?characterEncoding=utf8&useSSL=true", "root",
							"twy97620");
					System.out.println("Connecting to database...");
					System.out.println("查询待缴费记录...");
					StringBuffer sb = new StringBuffer();
					sb.append("call calculateFine('");
					sb.append(user).append("')");
					System.out.println(String.format("execute sql is %s", sb.toString()));
					PreparedStatement ps = cn.prepareStatement(sb.toString());
					int rows = ps.executeUpdate();
					System.out.println(String.format("\n affected rows: %d", rows));
					ps.close();
					StringBuffer sb1 = new StringBuffer();
					sb1.append("select * from vFine where 处理状态='未交' and 学号='");
					sb1.append(user).append("'");
					PreparedStatement ps1 = cn.prepareStatement(sb1.toString());
					ResultSet rs1 = ps1.executeQuery();
					System.out.println(String.format("execute sql is %s", sb1.toString()));
					if (rs1.next()) {// 结果集不为空
						rs1.previous();
						System.out.println("查询成功！");
						String str;
						while (rs1.next()) {
							ResultSetMetaData rsmd = (ResultSetMetaData) rs1.getMetaData();
							for (int i = 2; i < rsmd.getColumnCount(); i++) {
								str = rs1.getString(i);
								GQJFText.append(rsmd.getColumnName(i) + " : " + str + "\n");
							}
							GQJFText.append("\n");
						}
						GQJFText.append("\n");
					} else {
						System.out.println("暂无待缴费记录！");
						GQJFText.append("\n\n\n" + "当前没有要缴纳的罚款！");
					}
					rs1.close();
					ps1.close();
					cn.close();
				} catch (SQLException se) {// Handle errors for JDBC
					se.printStackTrace();
				} catch (Exception e1) {// Handle errors for Class.forName
					e1.printStackTrace();
				} finally {
				}
				frame.remove(Query);
				frame.remove(Return_intime);
				frame.remove(information);
				frame.remove(usemanual);
				frame.add(fine);
				frame.revalidate();
				frame.repaint();
			}
		});

		// 设置缴纳按钮的监听
		jiaona.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					// 缴纳罚款
					Class.forName("com.mysql.jdbc.Driver");
					Connection cn = DriverManager.getConnection(
							"jdbc:mysql://127.0.0.1:3306/librarysystem?characterEncoding=utf8&useSSL=true", "root",
							"twy97620");
					System.out.println("Connecting to database...");
					System.out.println("jiaona...");
					StringBuffer sb = new StringBuffer();
					sb.append("update borrow set FKCLState='已交' where FKCLState='未交' and RID='");
					sb.append(user).append("'");
					if (!fineISBN.getText().equals("") && !fineISBN.getText().equals(TIP6))
						sb.append(" and ISBN='").append(fineISBN.getText()).append("'");
					if (!borrowdate.getText().equals("") && !borrowdate.getText().equals(TIP7))
						sb.append(" and outdate='").append(borrowdate.getText()).append("'");
					PreparedStatement ps = cn.prepareStatement(sb.toString());
					System.out.println(String.format("execute sql is %s", sb.toString()));
					int rows = ps.executeUpdate();
					if (rows != 0) {// 结果集不为空
						System.out.println("缴纳成功...");
						JTextField text1 = new JTextField("  缴纳成功！可在我的图书馆查看缴费记录");
						JFrame b_success = new JFrame();
						b_success.setTitle("提示");
						b_success.setLocation(750, 500);
						b_success.setSize(400, 100);
						b_success.setDefaultCloseOperation(2);
						b_success.add(text1);
						b_success.setVisible(true);
					} else {
						System.out.println("不存在相应待缴费记录...");
						JTextField text1 = new JTextField("            缴费失败！不存在相应待缴费记录");
						JFrame b_fail = new JFrame();
						b_fail.setTitle("提示");
						b_fail.setLocation(750, 500);
						b_fail.setSize(400, 100);
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

		// 实例化查看信息界面组件并添加
		JTextArea CKXXText = new JTextArea("");
		JScrollPane jsp_ckxx = new JScrollPane(CKXXText);
		jsp_ckxx.setBounds(380, 10, 420, 435);
		information.add(jsp_ckxx);
		JLabel touxiangLabel = new JLabel();
		touxiangLabel.setIcon(new ImageIcon("C:\\Users\\lenovo-pc\\Pictures\\表情包\\22.png"));
		touxiangLabel.setBounds(100, 60, 340, 340);
		information.add(touxiangLabel);
		// 设置查看信息按钮的监听
		itemckxx.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				CKXXText.setText("");
				try {
					// 显示当前用户基本信息
					Class.forName("com.mysql.jdbc.Driver");
					Connection cn = DriverManager.getConnection(
							"jdbc:mysql://127.0.0.1:3306/librarysystem?characterEncoding=utf8&useSSL=true", "root",
							"twy97620");
					System.out.println("Connecting to database...");
					System.out.println("查询当前用户信息...");
					StringBuffer sb = new StringBuffer();
					sb.append(
							"select RID,Rname,Rsex,Rschool,Rmajor,Rgrade,Rdate,Rcount,Rlatest from reader where RID='");
					sb.append(user).append("'");
					System.out.println(String.format("execute sql is %s", sb.toString()));
					PreparedStatement ps = cn.prepareStatement(sb.toString());
					ResultSet rs = ps.executeQuery();
					System.out.println("查询成功！");
					if (rs.next()) {
						CKXXText.append("学号" + " : " + rs.getString(1) + "\n\n");
						CKXXText.append("姓名" + " : " + rs.getString(2) + "\n\n");
						CKXXText.append("性别" + " : " + rs.getString(3) + "\n\n");
						CKXXText.append("学院" + " : " + rs.getString(4) + "\n\n");
						CKXXText.append("专业" + " : " + rs.getString(5) + "\n\n");
						CKXXText.append("年级" + " : " + rs.getString(6) + "\n\n");
						CKXXText.append("注册日期" + " : " + rs.getString(7) + "\n\n");
						CKXXText.append("登录记录（次）" + " : " + rs.getString(8) + "\n\n");
						CKXXText.append("最近一次访问" + " : " + rs.getString(9));
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
				frame.remove(Query);
				frame.remove(Return_intime);
				frame.remove(fine);
				frame.remove(usemanual);
				frame.add(information);
				frame.revalidate();
				frame.repaint();
			}
		});

		// 实例化修改密码界面组件并添加
		JTextField oldkey = new JTextField(TIP8);
		oldkey.setForeground(Color.GRAY);
		oldkey.setBounds(40, 30, 300, 40);
		JTextField newkey = new JTextField(TIP9);
		newkey.setForeground(Color.GRAY);
		newkey.setBounds(40, 80, 300, 40);
		JButton xiugai = new JButton("修改");
		xiugai.setBounds(150, 130, 100, 40);
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
		xiugai.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					// 修改密码
					Class.forName("com.mysql.jdbc.Driver");
					Connection cn = DriverManager.getConnection(
							"jdbc:mysql://127.0.0.1:3306/librarysystem?characterEncoding=utf8&useSSL=true", "root",
							"twy97620");
					System.out.println("Connecting to database...");
					System.out.println("xiugaimima...");

					if (oldkey.getText().equals("") || oldkey.getText().equals(TIP8) || newkey.getText().equals("")
							|| newkey.getText().equals(TIP9)) {
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
						sb.append("select Rkey from reader where RID='");
						sb.append(user).append("'");
						PreparedStatement ps = cn.prepareStatement(sb.toString());
						ResultSet rs = ps.executeQuery();
						System.out.println(String.format("execute sql is %s", sb.toString()));
						if (rs.next()) {
							if (rs.getString(1).equals(oldkey.getText())) {
								StringBuffer sb1 = new StringBuffer();
								sb1.append("update reader set Rkey='").append(newkey.getText());
								sb1.append("' where RID='").append(user).append("'");
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
		JTextArea BZText = new JTextArea("\n\n\n" + "查询书籍功能：输入书刊名称、关键词或者作者即可搜索到相应书刊，查看馆中是否可以有副本可以借阅" + "\n\n"
				+ "借阅书籍功能：分为借阅新书和续借旧书，要借阅同一本书时必须先将前一本书归还后才能再次借阅该本书刊，每续借旧书一次，归还日期延后一周，续借无需缴费" + "\n\n"
				+ "归还书籍功能：分为按期归还和过期缴费，若为按期归还书刊则要缴纳一定的罚款，请及时在过期缴费界面中缴纳罚款" + "\n\n"
				+ "个人中心：分为查看信息和修改密码，个人中心里可以查看到用户自己的基本信息和登录历史，修改密码窗口可以设置新密码" + "\n\n"
				+ "我的图书馆：包括图书借阅记录——在借图书、即将到期以及借阅历史，缴费记录——已支付和未支付记录");
		JScrollPane jsp_bz = new JScrollPane(BZText);
		jsp_bz.setBounds(10, 30, 880, 435);
		usemanual.add(jsp_bz);
		// 设置使用帮助按钮的监听
		itembz.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.remove(Query);
				frame.remove(Return_intime);
				frame.remove(information);
				frame.remove(fine);
				frame.add(usemanual);
				frame.revalidate();
				frame.repaint();
			}
		});

		// 实例化我的图书馆窗口界面
		JLabel borrowrecord = new JLabel("图书借阅记录");
		Font f = new Font("宋体", Font.BOLD, 20);
		borrowrecord.setFont(f);
		borrowrecord.setBounds(10, 70, 180, 40);
		JLabel finerecord = new JLabel("缴费记录");
		finerecord.setBounds(165, 70, 120, 40);
		finerecord.setFont(f);
		JRadioButton borrowing = new JRadioButton("在借图书");
		borrowing.setBounds(10, 120, 140, 50);
		JRadioButton neardate = new JRadioButton("即将到期");
		neardate.setBounds(10, 190, 140, 50);
		JRadioButton borrowed = new JRadioButton("借阅历史");
		borrowed.setBounds(10, 260, 140, 50);
		JRadioButton paied = new JRadioButton("已支付");
		paied.setBounds(160, 120, 120, 50);
		JRadioButton unpaid = new JRadioButton("未支付");
		unpaid.setBounds(160, 190, 120, 50);

		JTextArea outputrecord = new JTextArea("");
		JScrollPane jsp_record = new JScrollPane(outputrecord);
		jsp_record.setBounds(280, 10, 500, 470);
		ButtonGroup group = new ButtonGroup();
		group.add(borrowing);
		group.add(neardate);
		group.add(borrowed);
		group.add(paied);
		group.add(unpaid);
		JButton xianshi = new JButton("显示");
		xianshi.setBounds(90, 435, 120, 40);
		JPanel my_lib = new JPanel();
		my_lib.setLayout(null);
		my_lib.setBounds(10, 10, 810, 530);
		my_lib.add(borrowrecord);
		my_lib.add(finerecord);
		my_lib.add(borrowing);
		my_lib.add(neardate);
		my_lib.add(borrowed);
		my_lib.add(paied);
		my_lib.add(unpaid);
		my_lib.add(jsp_record);
		my_lib.add(xianshi);
		// 设置我的图书馆按钮的监听
		b_mylibrary.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				outputrecord.setText("");
				JFrame mylibrary = new JFrame();
				mylibrary.add(my_lib);
				mylibrary.setTitle("我的图书馆");
				mylibrary.setLocation(620, 280);
				mylibrary.setSize(830, 550);
				mylibrary.setDefaultCloseOperation(2);
				mylibrary.setLayout(null);
				mylibrary.add(oldkey);
				mylibrary.add(newkey);
				mylibrary.add(xiugai);
				mylibrary.setVisible(true);
			}
		});

		// 设置显示按钮的监听
		xianshi.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				outputrecord.setText("");
				try {
					// 显示记录
					Class.forName("com.mysql.jdbc.Driver");
					Connection cn = DriverManager.getConnection(
							"jdbc:mysql://127.0.0.1:3306/librarysystem?characterEncoding=utf8&useSSL=true", "root",
							"twy97620");
					System.out.println("Connecting to database...");
					System.out.println("display...");

					if (borrowing.isSelected()) {
						StringBuffer sb = new StringBuffer();
						sb.append("select * from vBorrow where 学号='").append(user).append("'");
						PreparedStatement ps = cn.prepareStatement(sb.toString());
						ResultSet rs = ps.executeQuery();
						System.out.println(String.format("execute sql is %s", sb.toString()));
						if (rs.next()) {
							rs.previous();
							String str;
							while (rs.next()) {
								ResultSetMetaData rsmd = (ResultSetMetaData) rs.getMetaData();
								for (int i = 2; i <= rsmd.getColumnCount(); i++) {
									str = rs.getString(i);
									outputrecord.append(rsmd.getColumnName(i) + " : " + str + "\n");
								}
								outputrecord.append("\n");
							}
							outputrecord.append("\n");
						} else {
							outputrecord.append("当前暂无记录！");
						}
						rs.close();
						ps.close();
						cn.close();
					} else if (neardate.isSelected()) {
						StringBuffer sb = new StringBuffer();
						sb.append("select * from vBorrow where 学号='").append(user).append("'");
						sb.append(" and to_days(应还日期)-to_days(date(now()))<=1");
						PreparedStatement ps = cn.prepareStatement(sb.toString());
						ResultSet rs = ps.executeQuery();
						System.out.println(String.format("execute sql is %s", sb.toString()));
						if (rs.next()) {
							rs.previous();
							String str;
							while (rs.next()) {
								ResultSetMetaData rsmd = (ResultSetMetaData) rs.getMetaData();
								for (int i = 2; i <= rsmd.getColumnCount(); i++) {
									str = rs.getString(i);
									outputrecord.append(rsmd.getColumnName(i) + " : " + str + "\n");
								}
								outputrecord.append("\n");
							}
							outputrecord.append("\n");
						} else {
							outputrecord.append("当前暂无记录！");
						}
						rs.close();
						ps.close();
						cn.close();
					} else if (borrowed.isSelected()) {
						StringBuffer sb = new StringBuffer();
						sb.append("select * from vHistory where 学号='").append(user).append("'");
						PreparedStatement ps = cn.prepareStatement(sb.toString());
						ResultSet rs = ps.executeQuery();
						System.out.println(String.format("execute sql is %s", sb.toString()));
						if (rs.next()) {
							rs.previous();
							String str;
							while (rs.next()) {
								ResultSetMetaData rsmd = (ResultSetMetaData) rs.getMetaData();
								for (int i = 2; i <= rsmd.getColumnCount(); i++) {
									str = rs.getString(i);
									outputrecord.append(rsmd.getColumnName(i) + " : " + str + "\n");
								}
								outputrecord.append("\n");
							}
							outputrecord.append("\n");
						} else {
							outputrecord.append("当前暂无记录！");
						}
						rs.close();
						ps.close();
						cn.close();
					} else if (paied.isSelected()) {
						StringBuffer sb = new StringBuffer();
						sb.append("select * from vFine where 学号='").append(user).append("'");
						sb.append(" and 处理状态='已交'");
						PreparedStatement ps = cn.prepareStatement(sb.toString());
						ResultSet rs = ps.executeQuery();
						System.out.println(String.format("execute sql is %s", sb.toString()));
						if (rs.next()) {
							rs.previous();
							String str;
							while (rs.next()) {
								ResultSetMetaData rsmd = (ResultSetMetaData) rs.getMetaData();
								for (int i = 2; i < rsmd.getColumnCount(); i++) {
									str = rs.getString(i);
									outputrecord.append(rsmd.getColumnName(i) + " : " + str + "\n");
								}
								outputrecord.append("\n");
							}
							outputrecord.append("\n");
						} else {
							outputrecord.append("当前暂无记录！");
						}
						rs.close();
						ps.close();
						cn.close();
					} else if (unpaid.isSelected()) {

						StringBuffer sb = new StringBuffer();
						sb.append("call calculateFine('");
						sb.append(user).append("')");
						System.out.println(String.format("execute sql is %s", sb.toString()));
						PreparedStatement ps = cn.prepareStatement(sb.toString());
						int rows = ps.executeUpdate();
						System.out.println(String.format("\n affected rows: %d", rows));
						ps.close();
						StringBuffer sb1 = new StringBuffer();
						sb1.append("select * from vFine where 处理状态='未交' and 学号='");
						sb1.append(user).append("'");
						PreparedStatement ps1 = cn.prepareStatement(sb1.toString());
						ResultSet rs1 = ps1.executeQuery();
						System.out.println(String.format("execute sql is %s", sb1.toString()));
						if (rs1.next()) {// 结果集不为空
							rs1.previous();
							System.out.println("查询成功！");
							String str;
							while (rs1.next()) {
								ResultSetMetaData rsmd = (ResultSetMetaData) rs1.getMetaData();
								for (int i = 2; i < rsmd.getColumnCount(); i++) {
									str = rs1.getString(i);
									outputrecord.append(rsmd.getColumnName(i) + " : " + str + "\n");
								}
								outputrecord.append("\n");
							}
							outputrecord.append("\n");
						} else {
							outputrecord.append("当前暂无记录！");
						}
						rs1.close();
						ps1.close();
						ps.close();
						cn.close();
					} else {
						JTextField text2 = new JTextField("            显示失败！请指定要显示的记录");
						JFrame wrong = new JFrame();
						wrong.setTitle("提示");
						wrong.setLocation(750, 500);
						wrong.setSize(400, 100);
						wrong.setDefaultCloseOperation(2);
						wrong.add(text2);
						wrong.setVisible(true);
					}
				} catch (SQLException se) {// Handle errors for JDBC
					se.printStackTrace();
				} catch (Exception e1) {// Handle errors for Class.forName
					e1.printStackTrace();
				} finally {
				}
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
