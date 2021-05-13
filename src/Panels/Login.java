package Panels;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import Bean.Books;
import Bean.Users;
import Utils.MD5Utils;

/**
 * 登录界面
 */
public class Login extends JFrame {
	
	private Login self;
	
	private String userid;// 登陆用户名和密码
	private String password;
	
	private JLabel unLabel = new JLabel("编号");// 登陆面板控件
	private JTextField unField = new JTextField();
	private JLabel pwLabel = new JLabel("密码");
	private JPasswordField pwField = new JPasswordField();
	private JButton login = new JButton("登录");
	private JTextField warn = new JTextField();
	
	public Login() {
		this.self = this;
		this.setSize(300, 250);// 设置登陆面板
		Dimension screen = getToolkit().getScreenSize();
		Dimension frame = this.getSize();
		this.setLocation((screen.width - frame.width) / 2, (screen.height - frame.height) / 2);
		this.setVisible(true);
		this.setResizable(false);		//生成的窗体不可以自由改变大小
		this.setLayout(null);			//将容器的布局设为绝对布局
		this.setTitle("图书馆管理系统");
		
		unLabel.setSize(50, 30);
		unLabel.setLocation(50, 30);
		unField.setSize(120, 30);
		unField.setLocation(110, 30);
		pwLabel.setSize(50, 30);
		pwLabel.setLocation(50, 80);
		pwField.setSize(120, 30);
		pwField.setLocation(110, 80);
		login.setSize(80, 30);
		login.setLocation(110, 160);
		login.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				userid = unField.getText();
				password = String.valueOf(pwField.getPassword());
				int user_type = checkUser(userid, MD5Utils.md5(password));
				if(user_type == 1) {// 是学生用户
					self.setVisible(false);
					Users user = Users.getUserById(userid);
					new Common(user);// 打开公用学生面板
				} else if(user_type == 2) {
					self.setVisible(false);
					new Manager(Books.searchAllBook());
				} else {
					warn.setText("用户名或密码错误！");
				}
			}
		});
		warn.setSize(220, 30);
		warn.setLocation(110, 120);
		warn.setForeground(Color.red);
		warn.setEditable(false);
		warn.setBorder(null);
		
		//添加控件
		this.add(unLabel);
		this.add(unField);
		this.add(pwLabel);
		this.add(pwField);
		this.add(login);
		this.add(warn);
	}

	public int checkUser(String id, String password) {
		return Users.checkUser(id, password);
	}
}
