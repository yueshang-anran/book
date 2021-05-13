package Panels;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;

import Bean.Books;
import Bean.Borrows;
import Bean.Users;

/**
 * 普通用户界面
 */
public class Common extends JFrame {
	
	private Common self;
	
	private String user_id;// 用户编号
	private String user_name;// 用户姓名
	private String user_pass;// 用户密码
	private String user_depart;// 用户学院
	private String user_class;// 用户班级
	
	private JLabel forId;
	private JLabel forName;
	private JLabel forDepart;
	private JLabel forClass;
	private JButton borrowBook = new JButton("我要借书");
	private JButton returnBook = new JButton("我要还书");
	
	public Common(Users user) {
		this.self = this;
		this.user_id = user.getUser_id();
		this.user_name = user.getUser_name();
		this.user_pass = user.getUser_pass();
		this.user_depart = user.getUser_depart();
		this.user_class = user.getUser_class();
		this.setSize(340, 210);// 设置控制面板
		Dimension screen = getToolkit().getScreenSize();   //获取屏幕尺寸
		Dimension frame = this.getSize();				   //获取Common界面的尺寸
		this.setLocation((screen.width - frame.width) / 2, (screen.height - frame.height) / 2);
		this.setVisible(true);
		this.setResizable(false);
		this.setLayout(null);		//将容器的布局设为绝对布局
		this.setTitle("图书馆管理系统");
		
		// 初始化面板
		forId = new JLabel("编号：" + user_id);
		forName = new JLabel("姓名：" + user_name);
		forDepart = new JLabel("学院：" + user_depart);
		forClass = new JLabel("班级：" + user_class);
		
		forId.setSize(200, 30);
		forId.setLocation(20, 20);
		forName.setSize(200, 30);
		forName.setLocation(20, 60);
		forDepart.setSize(200, 30);
		forDepart.setLocation(20, 100);
		forClass.setSize(200, 30);
		forClass.setLocation(20, 140);
		borrowBook.setSize(120, 30);
		borrowBook.setLocation(190, 40);
		borrowBook.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				new CommonBorrow(Books.searchAllBook(), user_id);
			}
		});
		returnBook.setSize(120, 30);
		returnBook.setLocation(190, 100);
		returnBook.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				new CommonReturn(Borrows.searchUserAllUnreturnBook(user_id), user_id);
			}
		});
		
		this.add(forId);
		this.add(forName);
		this.add(forDepart);
		this.add(forClass);
		this.add(borrowBook);
		this.add(returnBook);
	}
}
