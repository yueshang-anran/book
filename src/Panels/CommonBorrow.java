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
 * 普通用户借书界面
 */
public class CommonBorrow extends JFrame {
	
	private CommonBorrow self;
	
	private Object[] columnNames = {"索书号", "书名", "出版社", "分类", "状态"};
	private Object[] types = {"-请选择搜索方式-", "按索书号搜索", "按书名搜索", "按出版社搜索", "按分类搜索"};
	
	private JComboBox searchType = new JComboBox(types);	//下拉框
	private JTextField searchData = new JTextField("在这里输入搜索内容");
	private JButton search = new JButton("搜索");
	private JButton borrow = new JButton("借书");
	private JScrollPane scrollPane;				//滚动条
	private JTable table;						//表格
	private DefaultTableModel model;
	private Object[][] data = Books.searchAllBook();
	
	private String id;			//索书号
	private String name;		//书名
	private String publisher;	//出版社
	private String classify;	//分类
	private int status;			//状态
	private String user_id;		//用户id
	
	public CommonBorrow(Object[][] data, String user_id) {
		this.self = this;
		this.data = data;
		this.user_id = user_id;
		this.setSize(500, 400);// 设置控制面板
		Dimension screen = getToolkit().getScreenSize();
		Dimension frame = this.getSize();
		this.setLocation((screen.width - frame.width) / 2, (screen.height - frame.height) / 2);
		this.setVisible(true);
		this.setResizable(false);
		this.setLayout(null);			//将容器的布局设为绝对布局
		this.setTitle("图书馆管理系统");
		
		model = new DefaultTableModel(data, columnNames);
		table = new JTable(model);
		scrollPane = new JScrollPane(table);					//滚动条
		
		searchType.setSize(140, 30);
		searchType.setLocation(20, 30);
		searchData.setSize(120, 30);
		searchData.setLocation(170, 30);
		search.setSize(80, 30);
		search.setLocation(300, 30);
		
		//显示搜索结果
		search.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				String text = searchData.getText();
				int index = searchType.getSelectedIndex();
				self.setVisible(false);
				if(index == 0) {// 搜索全部
					new CommonBorrow(Books.searchAllBook(), user_id);
				} else if(index == 1) {// 按索书号
					new CommonBorrow(Books.searchBookById(text), user_id);
				} else if(index == 2) {// 按书名
					new CommonBorrow(Books.searchBookByName(text), user_id);
				} else if(index == 3) {// 按出版社
					new CommonBorrow(Books.searchBookByPublisher(text), user_id);
				} else {// 按分类
					new CommonBorrow(Books.searchBookByClassify(text), user_id);
				}
			}
		});
		
		borrow.setSize(80, 30);
		borrow.setLocation(390, 30);
		
		//借书操作
		borrow.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				int index = table.getSelectedRow();		//行号
				int book_id = Integer.parseInt((String) table.getValueAt(index, 0));
				String status = (String) table.getValueAt(index, 4);
				if(status.equals("在架可借")) {
					if(Borrows.borrowBook(book_id, user_id)) {// 借阅成功
						self.setVisible(false);
						JOptionPane.showMessageDialog(null, "借阅成功", "提示信息", JOptionPane.INFORMATION_MESSAGE);
						new CommonBorrow(Books.searchAllBook(), user_id);
					} else {
						JOptionPane.showMessageDialog(null, "借阅失败，请重试", "提示信息", JOptionPane.ERROR_MESSAGE);
					}
				} else {
					JOptionPane.showMessageDialog(null, "本书已借出", "提示信息", JOptionPane.WARNING_MESSAGE);
				}
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
		this.add(searchType);
		this.add(searchData);
		this.add(search);
		this.add(borrow);
		this.add(scrollPane);
	}

	public void setData(Object[][] data) {
		this.data = data;
	}
	
}
