package Panels;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

import Bean.Borrows;

/**
 * 普通用户还书界面
 */
public class CommonReturn extends JFrame {
	
	private CommonReturn self;
	
	private Object[] columnNames = {"借书号", "索书号", "借书日期"};
	
	private JButton borrow = new JButton("还书");
	private JScrollPane scrollPane;
	private JTable table;
	private DefaultTableModel model;
	private Object[][] data;
	
	private String user_id;
	
	public CommonReturn(Object[][] data, String user_id) {
		this.self = this;
		this.data = data;
		this.user_id = user_id;
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
		int rows = table.getRowCount();
		scrollPane = new JScrollPane(table);
		
		borrow.setSize(80, 30);
		borrow.setLocation(210, 30);
		borrow.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				int index = table.getSelectedRow();
				int book_id = (int) table.getValueAt(index, 1);
				if(Borrows.returnBook(book_id, user_id)) {// 还书成功
					self.setVisible(false);
					JOptionPane.showMessageDialog(null, "还书成功", "提示信息", JOptionPane.INFORMATION_MESSAGE);
					new CommonReturn(Borrows.searchUserAllUnreturnBook(user_id), user_id);
				} else {
					JOptionPane.showMessageDialog(null, "还书失败，请重试", "提示信息", JOptionPane.ERROR_MESSAGE);
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
		
		init();
	}
	
	private void init() {
		//添加控件
		this.add(borrow);
		this.add(scrollPane);
	}
	
}
