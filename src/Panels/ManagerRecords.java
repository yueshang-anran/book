package Panels;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

import Bean.Borrows;

/**
 * 管理员查询普通用户借书情况界面
 */
public class ManagerRecords extends JFrame {
	
	private ManagerRecords self;
	
	private Object[] columnNames = {"借书号", "用户id", "索书号", "借书日期", "还书日期"};
	private Object[] types = {"-请选择搜索方式-", "按用户id查询", "按借书日期查询", "按还书日期查询"};
	
	private JComboBox searchType = new JComboBox(types);
	private JTextField searchData = new JTextField("在这里输入搜索内容");
	private JButton search = new JButton("搜索");	
	private JScrollPane scrollPane;
	private JTable table;
	private DefaultTableModel model;
	private Object[][] data;
	private JLabel tip = new JLabel("日期格式：2020-11-12-10-5-40");
	
	public ManagerRecords(Object[][] data) {
		this.self = this;
		this.data = data;
		this.setSize(500, 400);// 设置控制面板
		Dimension screen = getToolkit().getScreenSize();
		Dimension frame = this.getSize();
		this.setLocation((screen.width - frame.width) / 2, (screen.height - frame.height) / 2);
		this.setVisible(true);
		this.setResizable(false);
		this.setLayout(null);
		this.setTitle("图书馆管理系统");
		
		model = new DefaultTableModel(data, columnNames);
		table = new JTable(model);
		scrollPane = new JScrollPane(table);	//滚动条
		
		searchType.setSize(140, 30);
		searchType.setLocation(60, 30);
		searchData.setSize(120, 30);
		searchData.setLocation(210, 30);
		search.setSize(80, 30);
		search.setLocation(340, 30);
		search.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				String text = searchData.getText();
				int index = searchType.getSelectedIndex();
				self.setVisible(false);
				if(index == 0) {// 搜索全部
					new ManagerRecords(Borrows.searchAllRecords());
				} else if(index == 1) {// 按用户id
					new ManagerRecords(Borrows.searchRecordsByUserId(text));
				} else if(index == 2) {// 按借书日期(年)
					new ManagerRecords(Borrows.searchRecordsByBorrowDate(text));
				} else {// 按还书日期(年)
					new ManagerRecords(Borrows.searchRecordsByReturnDate(text));
				}
			}
		});
		tip.setSize(300, 30);
		tip.setLocation(160, 330);
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
		this.add(searchData);
		this.add(searchType);
		this.add(scrollPane);
		this.add(tip);
	}

	public void setData(Object[][] data) {
		this.data = data;
	}
	
}
