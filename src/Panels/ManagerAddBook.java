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

/**
 * 新增书籍页面
 */
public class ManagerAddBook extends JFrame {
	
	private ManagerAddBook self;

	private JLabel forName = new JLabel("书名");
	private JLabel forPublisher = new JLabel("出版社");
	private JLabel forClassify = new JLabel("分类");
	private JTextField nameField = new JTextField();
	private JTextField publisherField = new JTextField();
	private JTextField classifyField = new JTextField();
	private JButton add = new JButton("添加");
	
	public ManagerAddBook() {
		this.self = this;
		this.setSize(248, 248);// 设置控制面板
		Dimension screen = getToolkit().getScreenSize();
		Dimension frame = this.getSize();
		this.setLocation((screen.width - frame.width) / 2, (screen.height - frame.height) / 2);
		this.setVisible(true);
		this.setResizable(false);
		this.setLayout(null);
		this.setTitle("图书馆管理系统");
		
		forName.setSize(80, 30);
		forName.setLocation(20, 30);
		forPublisher.setSize(80, 30);
		forPublisher.setLocation(20, 80);
		forClassify.setSize(80, 30);
		forClassify.setLocation(20, 130);
		nameField.setSize(130, 30);
		nameField.setLocation(80, 30);
		publisherField.setSize(130, 30);
		publisherField.setLocation(80, 80);
		classifyField.setSize(130, 30);
		classifyField.setLocation(80, 130);
		add.setSize(80, 30);
		add.setLocation(80, 180);
		add.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				String name = nameField.getText();
				String publisher = publisherField.getText();
				String classify = classifyField.getText();
				if(Books.addBook(name, publisher, classify)) {// 添加成功
					self.setVisible(false);
					JOptionPane.showMessageDialog(null, "添加成功", "提示信息", JOptionPane.INFORMATION_MESSAGE);
				} else {
					JOptionPane.showMessageDialog(null, "添加失败", "提示信息", JOptionPane.ERROR_MESSAGE);
				}
			}
		});
		
		//添加控件
		this.add(forName);
		this.add(forPublisher);
		this.add(forClassify);
		this.add(nameField);
		this.add(publisherField);
		this.add(classifyField);
		this.add(add);
	}
}
