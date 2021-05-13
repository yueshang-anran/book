package Panels;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

import Bean.Books;
import Bean.Borrows;

/**
 * 管理员界面
 */
public class Manager extends JFrame  {

	private Manager self;
	
	private Object[] columnNames = {"索书号", "书名", "出版社", "分类", "状态"};
	private Object[] types = {"-请选择搜索方式-", "按索书号搜索", "按书名搜索", "按出版社搜索", "按分类搜索"};
	
	private JComboBox searchType = new JComboBox(types);
	private JTextField searchData = new JTextField("在这里输入搜索内容");
	private JButton search = new JButton("搜索");
	private JScrollPane scrollPane;
	private JTable table;
	private DefaultTableModel model;
	private Object[][] data = Books.searchAllBook();
	private JButton records = new JButton("借书记录");
	private JButton addBook = new JButton("新增图书");
	private JButton delBook = new JButton("删除图书");
	private JButton addUser = new JButton("新增用户");
	
	public Manager(Object[][] data) {
		this.self = this;
		this.data = data;
		this.setSize(500, 400);// 设置控制面板
		Dimension screen = getToolkit().getScreenSize();//获取屏幕尺寸
		Dimension frame = this.getSize();				//获取Manager界面的尺寸
		this.setLocation((screen.width - frame.width) / 2, (screen.height - frame.height) / 2);
		this.setVisible(true);
		this.setResizable(false);
		this.setLayout(null);		//将容器的布局设为绝对布局
		this.setTitle("图书馆管理系统");
		
		model = new DefaultTableModel(data, columnNames);
		table = new JTable(model);
		scrollPane = new JScrollPane(table);			//滚动条
		
		searchType.setSize(140, 30);
		searchType.setLocation(10, 30);
		searchData.setSize(120, 30);
		searchData.setLocation(160, 30);
		search.setSize(80, 30);
		search.setLocation(290, 30);
		
		//显示搜索结果
		search.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				String text = searchData.getText();
				int index = searchType.getSelectedIndex();
				self.setVisible(false);
				if(index == 0) {// 搜索全部
					new Manager(Books.searchAllBook());
				} else if(index == 1) {// 按索书号
					new Manager(Books.searchBookById(text));
				} else if(index == 2) {// 按书名
					new Manager(Books.searchBookByName(text));
				} else if(index == 3) {// 按出版社
					new Manager(Books.searchBookByPublisher(text));
				} else {// 按分类
					new Manager(Books.searchBookByClassify(text));
				}
			}
		});
		
		records.setSize(100, 30);
		records.setLocation(380, 30);
		//显示借书记录
		records.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				new ManagerRecords(Borrows.searchAllRecords());
			}
		});
		
		addBook.setSize(100, 30);
		addBook.setLocation(70, 330);
		//新增图书
		addBook.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				new ManagerAddBook();
			}
		});
		
		delBook.setSize(100, 30);
		delBook.setLocation(185, 330);
		//删除图书
		delBook.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				int index = table.getSelectedRow();
				int id = Integer.parseInt((String) table.getValueAt(index, 0));
				if(Books.deleteBook(id)) {// 根据索书号删除书籍
					self.setVisible(false);
					JOptionPane.showMessageDialog(null, "删除成功", "提示信息", JOptionPane.INFORMATION_MESSAGE);
					new Manager(Books.searchAllBook());
				} else {
					JOptionPane.showMessageDialog(null, "删除失败", "提示信息", JOptionPane.ERROR_MESSAGE);
				}
			}
		});
		
		addUser.setSize(100, 30);
		addUser.setLocation(300, 330);
		//新增用户
		addUser.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				new ManagerAddUser();
			}
		});
		scrollPane.setSize(450, 240);
		scrollPane.setLocation(20, 80);
		table.setSize(450, 240);
		table.setLocation(10, 10);
		
		//设置表头不可移动
		table.getTableHeader().setReorderingAllowed(false);
		
		//加上组件并赋予可编辑能力
		DefaultTableCellRenderer render = new DefaultTableCellRenderer();
		render.setHorizontalAlignment(SwingConstants.CENTER);
		table.getColumnModel().getColumn(0).setCellRenderer(render);
		table.getColumnModel().getColumn(1).setCellRenderer(render);
		table.getColumnModel().getColumn(2).setCellRenderer(render);
		table.getColumnModel().getColumn(3).setCellRenderer(render);
		table.getColumnModel().getColumn(4).setCellRenderer(render);
		
		init();
	}
	
	private void init() {
		
		//添加控件
		this.add(search);
		this.add(searchType);
		this.add(searchData);
		this.add(records);
		this.add(scrollPane);
		this.add(addBook);
		this.add(delBook);
		this.add(addUser);
	}
	
	public void setData(Object[][] data) {
		this.data = data;
	}
}
