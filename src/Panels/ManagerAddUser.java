package Panels;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import Bean.Books;
import Bean.Users;

/**
 * 新增书籍页面
 */
public class ManagerAddUser extends JFrame {
	
	private ManagerAddUser self;

	private JLabel forId = new JLabel("编号");
	private JLabel forName = new JLabel("姓名");
	private JLabel forPass = new JLabel("密码");
	private JLabel forDepart = new JLabel("学院");
	private JLabel forClass = new JLabel("班级");
	private JTextField idField = new JTextField();
	private JTextField nameField = new JTextField();
	private JTextField passField = new JTextField();
	private JTextField departField = new JTextField();
	private JTextField classField = new JTextField();
	private JButton add = new JButton("添加");
	
	public ManagerAddUser() {
		this.self = this;
		this.setSize(248, 368);// 设置控制面板
		Dimension screen = getToolkit().getScreenSize();
		Dimension frame = this.getSize();
		this.setLocation((screen.width - frame.width) / 2, (screen.height - frame.height) / 2);
		this.setVisible(true);
		this.setResizable(false);
		this.setLayout(null);
		this.setTitle("图书馆管理系统");
		
		forId.setSize(80, 30);
		forId.setLocation(20, 30);
		forName.setSize(80, 30);
		forName.setLocation(20, 80);
		forPass.setSize(80, 30);
		forPass.setLocation(20, 130);
		forDepart.setSize(80, 30);
		forDepart.setLocation(20, 180);
		forClass.setSize(80, 30);
		forClass.setLocation(20, 230);
		idField.setSize(130, 30);
		idField.setLocation(80, 30);
		nameField.setSize(130, 30);
		nameField.setLocation(80, 80);
		passField.setSize(130, 30);
		passField.setLocation(80, 130);
		departField.setSize(130, 30);
		departField.setLocation(80, 180);
		classField.setSize(130, 30);
		classField.setLocation(80, 230);
		add.setSize(80, 30);
		add.setLocation(80, 280);
		add.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				String id = idField.getText();
				String name = nameField.getText();
				String pass = passField.getText();
				String depart = departField.getText();
				String clazz = classField.getText();
				if(Users.addUser(id, name, pass, depart, clazz)) {// 添加成功
					self.setVisible(false);
					JOptionPane.showMessageDialog(null, "添加成功", "提示信息", JOptionPane.INFORMATION_MESSAGE);
				} else {
					JOptionPane.showMessageDialog(null, "添加失败", "提示信息", JOptionPane.ERROR_MESSAGE);
				}
			}
		});
		
		//添加控件
		this.add(forId);
		this.add(forName);
		this.add(forPass);
		this.add(forDepart);
		this.add(forClass);
		this.add(idField);
		this.add(nameField);
		this.add(passField);
		this.add(departField);
		this.add(classField);
		this.add(add);
	}
}
