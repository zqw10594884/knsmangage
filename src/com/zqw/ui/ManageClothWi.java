package com.zqw.ui;

import java.awt.Color;
import java.awt.Container;
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
import java.util.Date;
import java.util.List;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.ListModel;
import javax.swing.ListSelectionModel;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;

import com.zqw.bean.CheckListItem;
import com.zqw.bean.CurtainShop;
import com.zqw.bean.Global;
import com.zqw.bean.Goods;
import com.zqw.bean.LittleCloth;
import com.zqw.bean.OrderGoods;
import com.zqw.bean.OrderLst;
import com.zqw.print.PrintOrder;
import com.zqw.util.DBUtil;

import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;

public class ManageClothWi extends JFrame implements ListSelectionListener {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JScrollPane scrollPane;
	private JScrollPane scrollPane_3;
	private DefaultTableModel tableModel;
	private JTable table;
	private JTextField serialNumber;
	private JTextField number;
	private JButton submitOrderBtn;
	private ActionListener modifyAL;
	private ActionListener submitOrderAL;
	private MouseAdapter listAdapter;
	private CurtainShop curtainShop = null;
	private int latelyLstindex = -1;
	private JList<CheckListItem> checkedjList;
	private ArrayList<OrderLst> checkedLst;
	private ArrayList<OrderLst> pandectList = new ArrayList<OrderLst>();
	private OrderLst currentOrder;
	private JList<CheckListItem> goodjList;
	private JScrollPane scrollPane_1;
	private ArrayList<Goods> goodsLst;
	private JButton modifyBtn;
	private JButton delBtn;
	private JList littleClothjList;
	private ArrayList<LittleCloth> littleClothLst;
	private int goodLstIndex;
	private JComboBox defectiveCB;
	private LittleCloth currentlc;
	private ArrayList<LittleCloth> lcLst = new ArrayList<LittleCloth>();
	private Goods currentGoods;

	public ManageClothWi() {
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
					ManageClothWi frame = new ManageClothWi();
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
			if (e.getSource().equals(goodjList)) {
				goodLstIndex = goodjList.getSelectedIndex();
				currentGoods = goodsLst.get(goodLstIndex);
				serialNumber.setText(currentGoods.getSerialNumber());
			} else if (e.getSource().equals(littleClothjList)) {
				
				String s = (String) littleClothjList.getSelectedValue();
				for (int i = 0; i < tableModel.getRowCount();) {
					tableModel.removeRow(0);
				}
				for (int i = 0; i < littleClothLst.size(); i++) {
					LittleCloth lc = littleClothLst.get(i);
					if (s.equals(lc.getSerialNumber())) {
						String[] rowValues = { lc.getSerialNumber(),
								lc.getNumber() + "", lc.getRemark() };
						tableModel.addRow(rowValues);
					}
				}
				table.setModel(tableModel);
			}
		}
	}

	@SuppressWarnings("unchecked")
	private void initData() {

		goodsLst = (ArrayList<Goods>) DBUtil.getLstClass("serialNumber", "",
				Goods.class, "");
		String[] Lst = new String[goodsLst.size()];
		for (int i = 0; i < goodsLst.size(); i++) {
			Lst[i] = goodsLst.get(i).getSerialNumber();
		}
		goodjList = new JList(Lst);
		goodjList.addListSelectionListener(this);
		goodjList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		scrollPane_1.setViewportView(goodjList);

		littleClothLst = (ArrayList<LittleCloth>) DBUtil.getLstClass(
				"serialNumber", "", LittleCloth.class, "");
		DefaultListModel lm = new DefaultListModel<String>();
		for (int i = 0; i < littleClothLst.size(); i++) {
			String s = littleClothLst.get(i).getSerialNumber();
			if (!lm.contains(s)) {
				lm.addElement(s);
			}
		}
		littleClothjList = new JList();
		littleClothjList.setModel(lm);
		littleClothjList.addListSelectionListener(this);
		littleClothjList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		scrollPane_3.setViewportView(littleClothjList);

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
		setBounds(100, 100, 798, 613);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel label_1 = new JLabel("编号：");
		label_1.setBounds(228, 40, 54, 15);
		label_1.setFont(new Font("宋体", Font.PLAIN, 14));
		contentPane.add(label_1);

		serialNumber = new JTextField();
		serialNumber.setEditable(false);
		serialNumber.setBounds(271, 37, 130, 21);
		contentPane.add(serialNumber);
		serialNumber.setColumns(10);

		JLabel label_3 = new JLabel("数量：");
		label_3.setBounds(228, 71, 45, 15);
		label_3.setFont(new Font("宋体", Font.PLAIN, 14));
		contentPane.add(label_3);

		number = new JTextField();
		number.setBounds(271, 68, 41, 21);
		contentPane.add(number);
		number.setColumns(10);

		scrollPane = new JScrollPane();
		scrollPane.setBounds(218, 99, 346, 470);
		contentPane.add(scrollPane);
		table = new JTable();

		table.setBounds(10, 10, 401, 119);
		table.setBorder(new LineBorder(new Color(0, 0, 0)));
		scrollPane.setViewportView(table);

		scrollPane_3 = new JScrollPane();
		scrollPane_3.setBounds(574, 37, 198, 532);
		contentPane.add(scrollPane_3);

		littleClothjList = new JList();
		scrollPane_3.setViewportView(littleClothjList);

		JLabel jlabel_2 = new JLabel("布头列表");
		jlabel_2.setFont(new Font("宋体", Font.PLAIN, 14));
		jlabel_2.setBounds(646, 10, 70, 15);
		contentPane.add(jlabel_2);

		submitOrderBtn = new JButton("提交");
		submitOrderBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				submitOrderactionPerformed(e);
			}
		});
		submitOrderBtn.setFont(new Font("宋体", Font.PLAIN, 14));
		submitOrderBtn.setBounds(411, 67, 62, 23);
		contentPane.add(submitOrderBtn);

		modifyAL = new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				modifyALActionPerformed(e);
			}
		};

		scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(10, 37, 198, 532);
		contentPane.add(scrollPane_1);

		JLabel label = new JLabel("货物编号");
		label.setFont(new Font("宋体", Font.PLAIN, 14));
		label.setBounds(76, 10, 70, 15);
		contentPane.add(label);

		defectiveCB = new JComboBox();
		defectiveCB.setModel(new DefaultComboBoxModel(new String[] { "请选择",
				"有残次", "无残次" }));
		defectiveCB.setBounds(322, 68, 79, 21);
		contentPane.add(defectiveCB);

		modifyBtn = new JButton("修改");
		modifyBtn.setFont(new Font("宋体", Font.PLAIN, 14));
		modifyBtn.setBounds(488, 67, 62, 23);
		contentPane.add(modifyBtn);

		delBtn = new JButton("删除");
		delBtn.setFont(new Font("宋体", Font.PLAIN, 14));
		delBtn.setBounds(488, 36, 62, 23);
		contentPane.add(delBtn);
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
		int index = defectiveCB.getSelectedIndex();
		if (index != 0) {
			currentlc = new LittleCloth();
			currentlc.setSerialNumber(serialNumber.getText());
			currentlc.setPurchasePrice(currentGoods.getPurchasePrice());
			currentlc.setNumber(Double.parseDouble(number.getText().trim()));
			currentlc.setProductionDate(new Date());
			currentlc.setProductionPerson(Global.User.getName());
			if (index == 1) {
				currentlc.setDefective(true);
			} else if (index == 2) {
				currentlc.setDefective(false);
			}
			lcLst.add(currentlc);
			DBUtil.insert(currentlc);
		} else {

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
			OrderGoods og = (OrderGoods) currentOrder.getGoodsLst().get(selectedRow);
			og.setNumber(Double.parseDouble(tableModel.getValueAt(selectedRow,
					1).toString()));
			removeModifyBtnAndDeleteBtn();
		}
	}

	private void print(int parameter, OrderLst order, CurtainShop curtainShop) {
		Book book = new Book();
		PageFormat pf = new PageFormat();
		pf.setOrientation(PageFormat.PORTRAIT);
		Paper p = new Paper();
		p.setSize(590, 840);
		p.setImageableArea(10, 10, 590, 840);
		pf.setPaper(p);
		PrintOrder printOrder = new PrintOrder(order, curtainShop, parameter);
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
