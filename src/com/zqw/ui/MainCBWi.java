package com.zqw.ui;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.print.Book;
import java.awt.print.PageFormat;
import java.awt.print.Paper;
import java.awt.print.PrinterException;
import java.awt.print.PrinterJob;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.ListModel;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;

import com.zqw.bean.CheckListItem;
import com.zqw.bean.CurtainCustomer;
import com.zqw.bean.CurtainShop;
import com.zqw.bean.Global;
import com.zqw.bean.OrderGoods;
import com.zqw.bean.OrderLst;
import com.zqw.bean.SaleOrderGoods;
import com.zqw.bean.SaleOrderLst;
import com.zqw.print.PrintOrder;
import com.zqw.print.PrintRetailOrder;
import com.zqw.util.DBUtil;
import com.zqw.util.UIutil;

import javax.swing.JRadioButton;
import javax.swing.ButtonGroup;

public class MainCBWi extends JFrame implements ListSelectionListener {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JScrollPane scrollPane;
	private JScrollPane scrollPane_3;
	private DefaultTableModel tableModel;
	private JTable table;

	private JTextField shopName;
	private JTextField serialNumber;
	private JTextField number;
	private JButton modifyBtn;
	private JButton submitOrderBtn;
	private ActionListener modifyAL;
	private ActionListener submitOrderAL;
	private MouseAdapter listAdapter;
	private CurtainShop curtainShop = null;
	private int latelyLstindex = -1;
	private JList<CheckListItem> checkedjList;
	private ArrayList<OrderLst> wholesaleLst;
	private ArrayList<SaleOrderLst> retailLst;
	private ArrayList<OrderLst> pandectList = new ArrayList<OrderLst>();
	private OrderLst currentOrder;
	private SaleOrderLst currentRetailOrder;
	private JLabel nameLab;
	private final ButtonGroup buttonGroup = new ButtonGroup();
	private JRadioButton wholesaleRB;
	private JRadioButton retailRB;

	public MainCBWi() {
		initComponents();
		initTable();
		initData();
	}

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MainCBWi frame = new MainCBWi();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	@SuppressWarnings("unchecked")
	public void valueChanged(ListSelectionEvent e) {
		if (e.getValueIsAdjusting()) {

		}
	}

	@SuppressWarnings("unchecked")
	private void initData() {
		checkedjList = new JList<>();
		listAdapter = new MouseAdapter() {
			public void mouseClicked(MouseEvent event) {
				JList<CheckListItem> list = (JList<CheckListItem>) event
						.getSource();
				// Get index of item clicked
				// 获得用户点击项的索引
				latelyLstindex = list.locationToIndex(event.getPoint());
				if (event.getX() < 17) {
					CheckListItem item = list.getModel().getElementAt(
							latelyLstindex);
					// 设置列表中项的选择状态
					item.setSelected(!item.isSelected());
				} else {
					addLatelyLstToMain(latelyLstindex);
				}
				// 重新绘制列表中项
				list.repaint(list.getCellBounds(latelyLstindex, latelyLstindex));
			}

			public void mousePressed(MouseEvent e) {
			}

			public void mouseReleased(MouseEvent e) {
			}
		};
		initJlist();
		scrollPane_3.setViewportView(checkedjList);
	}

	private void initTable() {
		String[] columnNames = { "编号", "数量", "备注" };
		String[][] tableVales = {};
		tableModel = new DefaultTableModel(tableVales, columnNames);

		table.setModel(tableModel);
		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				int selectedRow = table.getSelectedRow();
				if (selectedRow != -1) {
					serialNumber.setText(tableModel.getValueAt(selectedRow, 0)
							.toString());
					number.setText(tableModel.getValueAt(selectedRow, 1)
							.toString());
					modifyBtn.getActionListeners();
					addModifyBtnAndDeleteBtn();
				}
			}
		});
		// table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		TableColumn col0 = table.getColumnModel().getColumn(0);
		col0.setPreferredWidth(100);
		TableColumn col1 = table.getColumnModel().getColumn(1);
		col1.setPreferredWidth(50);
		TableColumn col2 = table.getColumnModel().getColumn(2);
		col2.setPreferredWidth(50);
	}

	/**
	 * Create the frame.
	 */

	private void initComponents() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 796, 649);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel lblNewLabel = new JLabel("姓名：");
		lblNewLabel.setBounds(31, 107, 54, 15);
		lblNewLabel.setFont(new Font("宋体", Font.PLAIN, 14));
		contentPane.add(lblNewLabel);

		shopName = new JTextField();
		shopName.setEditable(false);
		shopName.setBounds(74, 105, 130, 21);
		contentPane.add(shopName);
		shopName.setColumns(10);

		JLabel label_1 = new JLabel("编号：");
		label_1.setBounds(220, 110, 54, 15);
		label_1.setFont(new Font("宋体", Font.PLAIN, 14));
		contentPane.add(label_1);

		serialNumber = new JTextField();
		serialNumber.setEditable(false);
		serialNumber.setBounds(263, 107, 130, 21);
		contentPane.add(serialNumber);
		serialNumber.setColumns(10);

		JLabel label_3 = new JLabel("数量：");
		label_3.setBounds(31, 144, 45, 15);
		label_3.setFont(new Font("宋体", Font.PLAIN, 14));
		contentPane.add(label_3);

		number = new JTextField();
		number.setBounds(74, 141, 41, 21);
		contentPane.add(number);
		number.setColumns(10);

		scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 193, 480, 376);
		contentPane.add(scrollPane);
		table = new JTable();

		table.setBounds(10, 10, 401, 119);
		table.setBorder(new LineBorder(new Color(0, 0, 0)));
		scrollPane.setViewportView(table);

		scrollPane_3 = new JScrollPane();
		scrollPane_3.setBounds(500, 37, 270, 532);
		contentPane.add(scrollPane_3);

		JButton untreatedPrintBtn = new JButton("备货打印");
		untreatedPrintBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				untreatedPrintActionPerformed(e);
			}
		});
		untreatedPrintBtn.setFont(new Font("宋体", Font.PLAIN, 14));
		untreatedPrintBtn.setBounds(600, 579, 93, 23);
		contentPane.add(untreatedPrintBtn);

		submitOrderBtn = new JButton("提交");
		submitOrderBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				submitOrderactionPerformed(e);
			}
		});
		submitOrderBtn.setFont(new Font("宋体", Font.PLAIN, 14));
		submitOrderBtn.setBounds(219, 140, 62, 23);
		contentPane.add(submitOrderBtn);

		modifyAL = new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				modifyALActionPerformed(e);
			}
		};
		modifyBtn = new JButton("修改");
		modifyBtn.setFont(new Font("宋体", Font.PLAIN, 14));
		modifyBtn.setBounds(140, 140, 62, 23);
		modifyBtn.setEnabled(false);
		contentPane.add(modifyBtn);

		JButton refreshBtn = new JButton("刷新");
		refreshBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				refreshactionPerformed(e);

			}
		});
		refreshBtn.setFont(new Font("宋体", Font.PLAIN, 14));
		refreshBtn.setBounds(304, 140, 62, 23);
		contentPane.add(refreshBtn);

		nameLab = new JLabel("");
		nameLab.setFont(new Font("仿宋", Font.BOLD, 34));
		nameLab.setBounds(52, 37, 152, 42);
		contentPane.add(nameLab);

		nameLab.setText(Global.CURRENTUSER);
		JButton button = new JButton("残布管理");
		button.setFont(new Font("宋体", Font.PLAIN, 14));
		button.setBounds(22, 579, 93, 23);
		contentPane.add(button);

		wholesaleRB = new JRadioButton("批发");
		wholesaleRB.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				initJlist();
			}
		});
		wholesaleRB.setSelected(true);
		buttonGroup.add(wholesaleRB);
		wholesaleRB.setBounds(529, 8, 68, 23);
		contentPane.add(wholesaleRB);

		retailRB = new JRadioButton("零售");
		retailRB.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				initJlist();
			}
		});
		buttonGroup.add(retailRB);
		retailRB.setBounds(599, 8, 68, 23);
		contentPane.add(retailRB);
	}

	private void refreshactionPerformed(ActionEvent e) {
		// DBUtil.refresh();
		// UIutil.initLatelyJlist(this, checkedjList, listAdapter, true, null,
		// 3);
	}

	/**
	 * 添加修改删除按钮
	 */
	private void addModifyBtnAndDeleteBtn() {
		// TODO Auto-generated method stub
		if (modifyBtn.getActionListeners().length == 0) {
			modifyBtn.addActionListener(modifyAL);
			modifyBtn.setEnabled(true);
		}
	}

	/**
	 * 移除修改删除按钮
	 */
	private void removeModifyBtnAndDeleteBtn() {
		if (modifyBtn.getActionListeners().length > 0) {
			modifyBtn.removeActionListener(modifyAL);
			modifyBtn.setEnabled(false);
		}
	}

	/**
	 * 提交订单
	 * 
	 * @param e
	 */
	private void submitOrderactionPerformed(ActionEvent e) {
		if (tableModel.getRowCount() > 0) {
			currentOrder.setOrderState(31);
			if (currentOrder.getLibraryPerson() == null
					|| currentOrder.getLibraryPerson().length() == 0) {
				currentOrder.setLibraryPerson(Global.CURRENTUSER);
			}
			initJlist();
			DBUtil.update(currentOrder);
		} else {

		}
	}

	private void initJlist() {
		if (wholesaleRB.isSelected()) {
			wholesaleLst = (ArrayList<OrderLst>) UIutil.initOrderJlist(this,
					checkedjList, listAdapter, true, wholesaleLst, 3);
		} else if (retailRB.isSelected()) {
			retailLst = (ArrayList<SaleOrderLst>) UIutil.initRetailOrderJlist(
					this, checkedjList, listAdapter, true, retailLst);
		}
	}

	/**
	 * 打印备货单
	 */
	private void untreatedPrintActionPerformed(ActionEvent evt) {
		ListModel<CheckListItem> l = checkedjList.getModel();

		if (wholesaleRB.isSelected()) {
			if (l.getSize() > 0) {
				for (int i = 0; i < l.getSize(); i++) {
					CheckListItem cli = l.getElementAt(i);
					if (cli.isSelected()) {
						OrderLst ol = wholesaleLst.get(i);
						if (ol.getOrderState() == 40) {
							CurtainShop cs = (CurtainShop) DBUtil.getClass(
									CurtainShop.class, "name",
									(ol.getCurtainShop()), "String", "eq");
							ol.setOrderState(30);
							DBUtil.update(ol);
							UIutil.initOrderJlist(this, checkedjList,
									listAdapter, true, wholesaleLst, 3);
							PrintOrder printOrder = new PrintOrder(ol, cs,
									Global.EMPLOYEE_LP);
							print(printOrder);
						}
					}
				}
			}
		} else {
			if (l.getSize() > 0) {
				for (int i = 0; i < l.getSize(); i++) {
					CheckListItem cli = l.getElementAt(i);
					if (cli.isSelected()) {
						SaleOrderLst sol = retailLst.get(i);
						if (sol.getOrderState() > 29) {
							CurtainCustomer cs = sol.getCustomer();
							sol.setOrderState(30);
							DBUtil.update(sol);
							UIutil.initRetailOrderJlist(this, checkedjList,
									listAdapter, true, retailLst);
							PrintOrder printOrderLp = new PrintRetailOrder(sol,
									cs, Global.EMPLOYEE_LP);
							print(printOrderLp);
							PrintOrder printOrderMp = new PrintRetailOrder(sol,
									cs, Global.EMPLOYEE_MP);
							print(printOrderMp);
							PrintOrder printOrderIp = new PrintRetailOrder(sol,
									cs, Global.EMPLOYEE_IP);
							print(printOrderIp);
							PrintOrder printOrderCS = new PrintRetailOrder(sol,
									cs, Global.EMPLOYEE_CS);
							print(printOrderCS);
						}
					}
				}
			}

		}

	}

	/**
	 * 修改货物
	 * 
	 * @param e
	 */
	private void modifyALActionPerformed(ActionEvent e) {
		int selectedRow = table.getSelectedRow();
		if (selectedRow != -1) {
			tableModel.setValueAt(number.getText(), selectedRow, 1);
			OrderGoods og = (OrderGoods) currentOrder.getGoodsLst().get(
					selectedRow);
			og.setNumber(Double.parseDouble(tableModel.getValueAt(selectedRow,
					1).toString()));
			removeModifyBtnAndDeleteBtn();
		}
	}

	private void addLatelyLstToMain(int index) {

		for (int i = 0; i < tableModel.getRowCount();) {
			tableModel.removeRow(0);
		}

		if (wholesaleRB.isSelected()) {
			currentOrder = wholesaleLst.get(index);
			List<OrderGoods> Lst = currentOrder.getGoodsLst();

			for (int i = 0; i < Lst.size(); i++) {
				String[] rowValues = { Lst.get(i).getSerialNumber(),
						Lst.get(i).getNumber() + "", Lst.get(i).getRemark() };
				tableModel.addRow(rowValues); // 添加一行
			}
			shopName.setText(currentOrder.getCurtainShop());
		} else {
			currentRetailOrder = retailLst.get(index);
			List<SaleOrderGoods> Lst = currentRetailOrder.getGoodsLst();

			for (int i = 0; i < Lst.size(); i++) {
				String[] rowValues = { Lst.get(i).getClothSerialNumber(),
						Lst.get(i).getClothNumber() + "",
						Lst.get(i).getClothRemark() };
				tableModel.addRow(rowValues); // 添加一行
			}
			shopName.setText(currentRetailOrder.getCustomer().getName());
		}
	}

	private void print(PrintOrder printOrder) {
		Book book = new Book();
		PageFormat pf = new PageFormat();
		pf.setOrientation(PageFormat.PORTRAIT);
		Paper p = new Paper();
		p.setSize(590, 840);
		p.setImageableArea(10, 10, 590, 840);
		pf.setPaper(p);
		book.append(printOrder, pf);
		PrinterJob job = PrinterJob.getPrinterJob();
		job.setPageable(book);
		try {
			job.print();
		} catch (PrinterException e) {
			e.printStackTrace();
		}
	}
}
