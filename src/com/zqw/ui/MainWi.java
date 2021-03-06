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
import java.awt.print.Printable;
import java.awt.print.PrinterException;
import java.awt.print.PrinterJob;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
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
import com.zqw.bean.CurtainShop;
import com.zqw.bean.CurtainShopGoods;
import com.zqw.bean.Global;
import com.zqw.bean.Goods;
import com.zqw.bean.OrderGoods;
import com.zqw.bean.OrderLst;
import com.zqw.print.PrintOrder;
import com.zqw.print.PrintPandect;
import com.zqw.print.PrintSettlement;
import com.zqw.util.DBUtil;
import com.zqw.util.DataUtil;
import com.zqw.util.LogUtil;
import com.zqw.util.SortChineseName;
import com.zqw.util.UIutil;

public class MainWi extends JFrame implements ListSelectionListener {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JScrollPane scrollPane;
	private JScrollPane scrollPane_1;
	private JScrollPane scrollPane_2;
	private JScrollPane scrollPane_3;
	private DefaultTableModel tableModel;
	private JTable table;

	private JTextField shopName;
	private JTextField telephone;
	private JTextField serialNumber;
	private JTextField sellingPrice;
	private JTextField number;

	private JLabel total;
	private JLabel profit;
	private JButton orderPrintBtn;
	private JButton addBtn;
	private JButton deleteBtn;
	private JButton manageBtn;
	private JButton modifyBtn;
	private JButton submitOrderBtn;
	private ActionListener printBtnAL;
	private ActionListener modifyAL;
	private ActionListener deleteAl;
	private MouseAdapter submitOrderAL;
	private MouseAdapter listAdapter;
	private CurtainShop curtainShop = null;
	private int curtainShopLstIndex = -1;
	private int latelyLstindex = -1;
	private JList<CheckListItem> curtainShopjList;
	private JList<CheckListItem> goodsjList;
	private JList<CheckListItem> latelyjList;
	private List<CurtainShopGoods> goodsLst = new ArrayList<CurtainShopGoods>();
	private ArrayList<CurtainShop> curtainShopLst = new ArrayList<CurtainShop>();
	private ArrayList<CurtainShop> curtainShopLstByLocation = new ArrayList<CurtainShop>();
	private ArrayList<OrderLst> latelyLst;
	private ArrayList<OrderLst> pandectList = new ArrayList<OrderLst>();
	private JCheckBox goodChangeCB;
	private JComboBox<String> flowersComboBox;
	private OrderLst currentOrder;
	private JButton btnNewButton;
	private JList locationJlist;
	private JTextField preferentialAmountTF;
	private JLabel label_7;
	private JTextField orderRemarksTF;
	private JTextField goodRemarksTF;
	private Goods currentGoods;
	// 生命周期 点击 客户货物——生成 提交、点击其他客户货物——消亡
	private ArrayList<Goods> libraryGoodsLst = new ArrayList<Goods>();
	// 生命周期 同订单生命周期 记录此次提交中 此订单中发生改变的货物
	private JButton historyListDelBtn;
	private JButton pandectPrintBtn;
	private JButton historyBtn;
	private JLabel libraryNum;

	public MainWi() {
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
					MainWi frame = new MainWi();
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
			if (e.getSource().equals(curtainShopjList)) {// 客户
				curtainShopLstIndex = curtainShopjList.getSelectedIndex();
				curtainShop = curtainShopLstByLocation.get(curtainShopLstIndex);
				removeModifyBtnAndDeleteBtn();
				shopName.setText(curtainShop.getName());
				shopName.setEditable(false);
				telephone.setText(curtainShop.getTelephone());
				telephone.setEditable(false);
				currentOrder = new OrderLst();
				currentOrder.setCurtainShop(curtainShop.getName());
				currentOrder.setGoodsLst(new ArrayList<OrderGoods>());
				goodsLst = (List<CurtainShopGoods>) DBUtil.getLstClass(
						"serialNumber", "eq", CurtainShopGoods.class,
						"curtainShop", curtainShop.getName(), "String");
				UIutil.initCurtainShopGoodsLstFromName(this, goodsjList,
						goodsLst);
				initTable();
				// 重置版面信息
				number.setText("");
				sellingPrice.setText("");
				goodRemarksTF.setText("");
				preferentialAmountTF.setText("");
				orderRemarksTF.setText("");
				goodRemarksTF.setText("");
				sellingPrice.setEditable(false);
				serialNumber.setText("");
				serialNumber.setEditable(false);
				// freeGoodsBC.setSelected(false);
				goodChangeCB.setSelected(false);
				flowersComboBox.setSelectedIndex(0);
				removePrintBtn();
				removeSubmit();
			} else if (e.getSource().equals(goodsjList)) {// 客户货物
				number.setText("");
				goodChangeCB.setSelected(false);
				flowersComboBox.setSelectedIndex(0);
				int index = goodsjList.getSelectedIndex();
				CurtainShopGoods goods = goodsLst.get(index);
				currentGoods = (Goods) DBUtil
						.getClass(Goods.class, "serialNumber",
								goods.getSerialNumber(), "String", "eq");
				goodRemarksTF.setText("");
				libraryNum.setText(currentGoods.getNumber());
				sellingPrice.setText(goods.getSellingPrice() + "");
				sellingPrice.setEditable(false);
				serialNumber.setText(goods.getSerialNumber());
				serialNumber.setEditable(false);
			} else if (e.getSource().equals(locationJlist)) {
				String location = locationJlist.getSelectedValue().toString();
				curtainShopLstByLocation.clear();
				for (int i = 0; i < curtainShopLst.size(); i++) {
					CurtainShop cs = curtainShopLst.get(i);
					if (cs.getAddress().equals(location)) {
						curtainShopLstByLocation.add(cs);
					}
				}
				UIutil.initCurtainShop(this, curtainShopjList,
						curtainShopLstByLocation);
			}
		}
	}

	@SuppressWarnings("unchecked")
	private void initData() {
		curtainShopjList = new JList<CheckListItem>();
		scrollPane_1.setViewportView(curtainShopjList);
		curtainShopLst = (ArrayList<CurtainShop>) DBUtil.getLstClass("name",
				"", CurtainShop.class, "");
		ArrayList<String> item = new ArrayList<String>();
		for (int i = 0; i < curtainShopLst.size(); i++) {
			String s = curtainShopLst.get(i).getAddress();
			if (!item.contains(s)) {
				item.add(s);
			}
		}

		UIutil.initJlist(this, locationJlist, item);

		goodsjList = new JList<CheckListItem>();
		scrollPane_2.setViewportView(goodsjList);

		latelyjList = new JList<>();
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
		latelyLst = (ArrayList<OrderLst>) UIutil.initOrderJlist(this,
				latelyjList, listAdapter, true, null, 1);
		scrollPane_3.setViewportView(latelyjList);
	}

	private void initTable() {
		String[] columnNames = { "编号", "进价", "卖价", "数量", "备注" };
		String[][] tableVales = {};
		tableModel = new DefaultTableModel(tableVales, columnNames);

		table.setModel(tableModel);
		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				int selectedRow = table.getSelectedRow();
				OrderGoods og = currentOrder.getGoodsLst().get(selectedRow);
				if (selectedRow != -1) {
					serialNumber.setText(og.getSerialNumber());
					sellingPrice.setText(og.getSellingPrice() + "");
					number.setText(og.getNumber() + "");
					goodRemarksTF.setText(og.getRemark());
					libraryNum.setText("");
					addModifyBtnAndDeleteBtn();
				}
			}
		});
		// table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		TableColumn col0 = table.getColumnModel().getColumn(0);
		col0.setPreferredWidth(150);
		TableColumn col1 = table.getColumnModel().getColumn(1);
		col1.setPreferredWidth(10);
		TableColumn col2 = table.getColumnModel().getColumn(2);
		col2.setPreferredWidth(10);
		TableColumn col3 = table.getColumnModel().getColumn(3);
		col3.setPreferredWidth(50);
		TableColumn col4 = table.getColumnModel().getColumn(4);
		col4.setPreferredWidth(180);
	}

	/**
	 * Create the frame.
	 */

	private void initComponents() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1164, 649);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel lblNewLabel = new JLabel("店名：");
		lblNewLabel.setBounds(488, 15, 54, 15);
		lblNewLabel.setFont(new Font("宋体", Font.PLAIN, 14));
		contentPane.add(lblNewLabel);

		shopName = new JTextField();
		shopName.setBounds(531, 13, 130, 21);
		contentPane.add(shopName);
		shopName.setColumns(10);

		JLabel label = new JLabel("电话：");
		label.setBounds(671, 15, 54, 15);
		label.setFont(new Font("宋体", Font.PLAIN, 14));
		contentPane.add(label);

		telephone = new JTextField();
		telephone.setBounds(735, 13, 106, 21);
		contentPane.add(telephone);

		telephone.setColumns(10);

		JLabel label_1 = new JLabel("编号：");
		label_1.setBounds(488, 49, 54, 15);
		label_1.setFont(new Font("宋体", Font.PLAIN, 14));
		contentPane.add(label_1);

		serialNumber = new JTextField();
		serialNumber.setBounds(531, 46, 130, 21);
		contentPane.add(serialNumber);
		serialNumber.setColumns(10);

		JLabel label_2 = new JLabel("价格：");
		label_2.setBounds(629, 77, 48, 15);
		label_2.setFont(new Font("宋体", Font.PLAIN, 14));
		contentPane.add(label_2);

		sellingPrice = new JTextField();
		sellingPrice.setBounds(677, 74, 48, 21);
		contentPane.add(sellingPrice);
		sellingPrice.setColumns(10);

		JLabel label_3 = new JLabel("数量：");
		label_3.setBounds(735, 77, 45, 15);
		label_3.setFont(new Font("宋体", Font.PLAIN, 14));
		contentPane.add(label_3);

		number = new JTextField();
		number.setBounds(794, 74, 47, 21);
		contentPane.add(number);
		number.setColumns(10);

		printBtnAL = new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				printActionPerformed(e);
			}
		};
		orderPrintBtn = new JButton("打印");
		orderPrintBtn.setBounds(779, 105, 62, 23);
		orderPrintBtn.setFont(new Font("宋体", Font.PLAIN, 14));
		orderPrintBtn.setEnabled(false);
		contentPane.add(orderPrintBtn);

		addBtn = new JButton("添加");
		addBtn.setBounds(629, 105, 62, 23);
		addBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				addActionPerformed(e);
			}
		});
		addBtn.setFont(new Font("宋体", Font.PLAIN, 14));
		contentPane.add(addBtn);

		deleteAl = new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				deleteActionPerformed(e);
			}
		};
		deleteBtn = new JButton("删除");
		deleteBtn.setBounds(557, 105, 62, 23);
		deleteBtn.setFont(new Font("宋体", Font.PLAIN, 14));
		deleteBtn.setEnabled(false);
		contentPane.add(deleteBtn);

		manageBtn = new JButton("管理");
		manageBtn.setBounds(479, 576, 70, 23);
		manageBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				refreshActionPerformed(e);
			}
		});
		manageBtn.setFont(new Font("宋体", Font.PLAIN, 14));
		contentPane.add(manageBtn);

		total = new JLabel("");
		total.setBounds(787, 580, 38, 15);
		contentPane.add(total);

		JLabel lblNewLabel_1 = new JLabel("总价：");
		lblNewLabel_1.setBounds(739, 580, 52, 15);
		lblNewLabel_1.setFont(new Font("宋体", Font.PLAIN, 14));
		contentPane.add(lblNewLabel_1);

		JLabel label_4 = new JLabel("利润：");
		label_4.setBounds(831, 580, 45, 15);
		label_4.setFont(new Font("宋体", Font.PLAIN, 14));
		contentPane.add(label_4);

		profit = new JLabel("");
		profit.setBounds(887, 580, 54, 15);
		contentPane.add(profit);

		scrollPane = new JScrollPane();
		scrollPane.setBounds(488, 162, 353, 344);
		contentPane.add(scrollPane);
		table = new JTable();

		table.setBounds(10, 10, 401, 119);
		table.setBorder(new LineBorder(new Color(0, 0, 0)));
		scrollPane.setViewportView(table);

		scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(154, 47, 159, 561);
		contentPane.add(scrollPane_1);

		scrollPane_2 = new JScrollPane();
		scrollPane_2.setBounds(323, 44, 146, 561);
		contentPane.add(scrollPane_2);

		scrollPane_3 = new JScrollPane();
		scrollPane_3.setBounds(851, 27, 291, 539);
		contentPane.add(scrollPane_3);

		JLabel jlabel = new JLabel("客户");
		jlabel.setFont(new Font("宋体", Font.PLAIN, 14));
		jlabel.setBounds(213, 19, 54, 15);
		contentPane.add(jlabel);

		JLabel jlabel_1 = new JLabel("客户货物");
		jlabel_1.setFont(new Font("宋体", Font.PLAIN, 14));
		jlabel_1.setBounds(368, 15, 70, 15);
		contentPane.add(jlabel_1);

		JLabel jlabel_2 = new JLabel("历史订单");
		jlabel_2.setFont(new Font("宋体", Font.PLAIN, 14));
		jlabel_2.setBounds(980, 10, 70, 15);
		contentPane.add(jlabel_2);

		pandectPrintBtn = new JButton("目录打印");
		pandectPrintBtn.setFont(new Font("宋体", Font.PLAIN, 14));
		pandectPrintBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				pandectActionPerformed(e);
			}
		});
		pandectPrintBtn.setBounds(956, 576, 94, 23);
		contentPane.add(pandectPrintBtn);

		submitOrderBtn = new JButton("提交");
		submitOrderAL = new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				submitOrderactionPerformed(e);
			}
		};
		submitOrderBtn.setFont(new Font("宋体", Font.PLAIN, 14));
		submitOrderBtn.setEnabled(false);
		submitOrderBtn.setBounds(697, 105, 62, 23);
		contentPane.add(submitOrderBtn);

		goodChangeCB = new JCheckBox("换货");
		goodChangeCB.setFont(new Font("宋体", Font.PLAIN, 14));
		goodChangeCB.setBounds(488, 73, 54, 23);
		contentPane.add(goodChangeCB);

		flowersComboBox = new JComboBox<String>();
		flowersComboBox.setModel(new DefaultComboBoxModel(new String[] { "不对花",
				"对花", "多对", "少对", "对整花" }));
		flowersComboBox.setFont(new Font("宋体", Font.PLAIN, 13));
		flowersComboBox.setBounds(553, 75, 66, 21);
		contentPane.add(flowersComboBox);

		modifyAL = new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				modifyALActionPerformed(e);
			}
		};
		modifyBtn = new JButton("修改");
		modifyBtn.setFont(new Font("宋体", Font.PLAIN, 14));
		modifyBtn.setBounds(488, 105, 62, 23);
		modifyBtn.setEnabled(false);
		contentPane.add(modifyBtn);

		historyListDelBtn = new JButton("删除");
		historyListDelBtn.setFont(new Font("宋体", Font.PLAIN, 14));
		historyListDelBtn.setBounds(1072, 576, 70, 23);
		historyListDelBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				orderDeleteAction();
			}
		});
		contentPane.add(historyListDelBtn);

		btnNewButton = new JButton("刷新");
		btnNewButton.setFont(new Font("宋体", Font.PLAIN, 14));
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				DBUtil.refresh();
				initTable();
				initData();
			}
		});
		btnNewButton.setBounds(559, 576, 70, 23);
		contentPane.add(btnNewButton);

		JScrollPane scrollPane_4 = new JScrollPane();
		scrollPane_4.setBounds(10, 49, 135, 552);
		contentPane.add(scrollPane_4);

		locationJlist = new JList();
		scrollPane_4.setViewportView(locationJlist);

		JLabel label_5 = new JLabel("地址");
		label_5.setFont(new Font("宋体", Font.PLAIN, 14));
		label_5.setBounds(54, 19, 54, 15);
		contentPane.add(label_5);

		preferentialAmountTF = new JTextField();
		preferentialAmountTF.setBounds(531, 516, 66, 21);
		contentPane.add(preferentialAmountTF);
		preferentialAmountTF.setColumns(10);

		JLabel label_6 = new JLabel("优惠：");
		label_6.setFont(new Font("宋体", Font.PLAIN, 14));
		label_6.setBounds(488, 519, 45, 15);
		contentPane.add(label_6);

		label_7 = new JLabel("订单备注：");
		label_7.setFont(new Font("宋体", Font.PLAIN, 14));
		label_7.setBounds(488, 550, 72, 15);
		contentPane.add(label_7);

		orderRemarksTF = new JTextField();
		orderRemarksTF.setColumns(10);
		orderRemarksTF.setBounds(570, 547, 271, 21);
		contentPane.add(orderRemarksTF);

		JLabel label_8 = new JLabel("货物备注：");
		label_8.setFont(new Font("宋体", Font.PLAIN, 14));
		label_8.setBounds(488, 134, 72, 15);
		contentPane.add(label_8);

		goodRemarksTF = new JTextField();
		goodRemarksTF.setColumns(10);
		goodRemarksTF.setBounds(567, 131, 274, 21);
		contentPane.add(goodRemarksTF);

		JLabel label_9 = new JLabel("库存数量：");
		label_9.setFont(new Font("宋体", Font.PLAIN, 14));
		label_9.setBounds(681, 49, 78, 15);
		contentPane.add(label_9);

		historyBtn = new JButton("历史订单");
		historyBtn.setFont(new Font("宋体", Font.PLAIN, 14));
		historyBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ManageOrderWi mainAdd = new ManageOrderWi();
				mainAdd.setModal(true);
				mainAdd.setVisible(true);
				int state = mainAdd.getReturnStatus();
				if (state == 0) {
					DBUtil.refresh();
					initTable();
					initData();
				}
			}
		});
		historyBtn.setBounds(639, 576, 94, 23);
		contentPane.add(historyBtn);

		libraryNum = new JLabel("");
		libraryNum.setForeground(Color.RED);
		libraryNum.setFont(new Font("宋体", Font.BOLD, 22));
		libraryNum.setBounds(755, 44, 86, 23);
		contentPane.add(libraryNum);
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
		if (deleteBtn.getActionListeners().length == 0) {
			deleteBtn.addActionListener(deleteAl);
			deleteBtn.setEnabled(true);
		}
	}

	/**
	 * 设置打印为true
	 */
	private void addPrintBtn() {
		if (orderPrintBtn.getActionListeners().length == 0) {
			orderPrintBtn.setEnabled(true);
			orderPrintBtn.addActionListener(printBtnAL);
		} else {

		}
	}

	/**
	 * 设置提交为true
	 */
	private void addSubmit() {
		if (submitOrderBtn.getActionListeners().length == 0) {
			submitOrderBtn.setEnabled(true);
			submitOrderBtn.addMouseListener(submitOrderAL);
		} else {

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
		if (deleteBtn.getActionListeners().length > 0) {
			deleteBtn.removeActionListener(deleteAl);
			deleteBtn.setEnabled(false);
		}
	}

	/**
	 * 打印置为 false
	 */
	private void removePrintBtn() {
		if (orderPrintBtn.getActionListeners().length > 0) {
			orderPrintBtn.setEnabled(false);
			orderPrintBtn.removeActionListener(printBtnAL);
		} else {

		}
	}

	/**
	 * 提交置为 false
	 */
	private void removeSubmit() {
		if (submitOrderBtn.getActionListeners().length > 0) {
			submitOrderBtn.setEnabled(false);
			submitOrderBtn.removeMouseListener(submitOrderAL);
		} else {

		}
	}

	private void refreshActionPerformed(java.awt.event.ActionEvent evt) {
		MainAddWi mainAdd = new MainAddWi();
		mainAdd.setModal(true);
		mainAdd.setVisible(true);
		int state = mainAdd.getReturnStatus();
		if (state == 0) {
			DBUtil.refresh();
			initTable();
			initData();
		}
	}

	/**
	 * 提交订单
	 * 
	 * @param e
	 */
	private void submitOrderactionPerformed(MouseEvent e) {
		String log = "";
		if (tableModel.getRowCount() > 0) {
			if (currentOrder.getOrderState() == 0) {
				currentOrder.setOrderState(40);
			}
			currentOrder.setSubmitTime(new Date());
			currentOrder.setArrears(DataUtil.getTotalm(currentOrder
					.getGoodsLst()) - currentOrder.getPreferentialAmount());
			if (preferentialAmountTF.getText().trim().length() > 0) {
				currentOrder.setPreferentialAmount(Integer
						.parseInt(preferentialAmountTF.getText().trim()));
			}
			currentOrder.setRemarks(orderRemarksTF.getText().trim());
			for (int i = 0; i < currentOrder.getGoodsLst().size(); i++) {
				log += currentOrder.getGoodsLst().get(i).getSerialNumber()
						+ "_" + currentOrder.getGoodsLst().get(i).getNumber()
						+ "*";
			}
			// 判断id是否为空
			if (currentOrder.getId() < 1) {
				if (DBUtil.insert(currentOrder) == -1) {
					System.out
							.println("false-------------------------------------------------------");
				}
				// DBUtil.insert(currentOrder);
				LogUtil.setLog(currentOrder.getId(), "Submit", log);
				latelyLst.add(currentOrder);
			} else {
				DBUtil.update(currentOrder);
				LogUtil.setLog(currentOrder.getId(), "Update", log);
			}
			for (int i = 0; i < libraryGoodsLst.size(); i++) {
				DBUtil.update(libraryGoodsLst.get(i));
			}
			libraryGoodsLst.clear();
			UIutil.initOrderJlist(this, latelyjList, listAdapter, true,
					latelyLst, 1);
			removeSubmit();
		} else {

		}
	}

	/**
	 * 打印出库单
	 * 
	 * @param evt
	 */
	private void printActionPerformed(ActionEvent evt) {
		String log = "";
		if (currentOrder.getGoodsLst().size() > 0
				&& currentOrder.getOrderState() == 31) {
			currentOrder.setOrderState(20);
			currentOrder.setDeliveryTime(new Date());
			currentOrder.setPreferentialAmount(Integer
					.parseInt(preferentialAmountTF.getText().trim()));
			List<OrderGoods> lst = currentOrder.getGoodsLst();
			for (int i = 0; i < lst.size(); i++) {
				lst.get(i).setDate(new Date());
				log += lst.get(i).getSerialNumber() + "_"
						+ lst.get(i).getNumber() + "*";
			}
			removePrintBtn();
			UIutil.initOrderJlist(this, latelyjList, listAdapter, true,
					latelyLst, 1);
			print(new PrintOrder(currentOrder, curtainShop, Global.CUSTOMER));
			DBUtil.update(currentOrder);
			LogUtil.setLog(currentOrder.getId(), "Print", log);
		} else {

		}
	}

	/**
	 * 打印目录
	 */
	private void pandectActionPerformed(java.awt.event.ActionEvent evt) {
		ListModel<CheckListItem> l = latelyjList.getModel();
		if (!pandectList.isEmpty()) {
			pandectList.clear();
		} else {

		}
		for (int i = 0; i < l.getSize(); i++) {
			CheckListItem cli = l.getElementAt(i);
			if (cli.isSelected() && latelyLst.get(i).getOrderState() == 20) {
				pandectList.add(latelyLst.get(i));
				latelyLst.get(i).setOrderState(15);
				DBUtil.update(latelyLst.get(i));
			}
		}
		Collections.sort(pandectList, new SortChineseName());
		print(new PrintPandect(pandectList));
		print(new PrintSettlement(pandectList));
		UIutil.initOrderJlist(this, latelyjList, listAdapter, true, latelyLst,
				1);
	}

	private void orderDeleteAction() {
		if (latelyLst != null) {
			String log = "";
			for (int i = 0; i < currentOrder.getGoodsLst().size(); i++) {
				Goods g;
				OrderGoods og = currentOrder.getGoodsLst().get(i);
				g = (Goods) DBUtil.getClass(Goods.class, "serialNumber",
						og.getSerialNumber(), "String", "eq");
				g.setNumber((Double.parseDouble(g.getNumber()) + og.getNumber())
						+ "");
				log += og.getSerialNumber() + "_" + og.getNumber() + "*";

				DBUtil.update(g);
			}
			latelyLst.remove(currentOrder);
			UIutil.initOrderJlist(this, latelyjList, listAdapter, true,
					latelyLst, 1);
			LogUtil.setLog(currentOrder.getId(), "Del", log);
			DBUtil.del(currentOrder);
			UIutil.delFromCurtainShopGoods();
		} else {
		}
	}

	private void deleteActionPerformed(java.awt.event.ActionEvent evt) {
		int selectedRow = table.getSelectedRow();
		if (selectedRow != -1) {
			Goods g;
			OrderGoods og = currentOrder.getGoodsLst().get(selectedRow);
			if (getLibraryGoods(og.getSerialNumber()) == null) {
				currentGoods = (Goods) DBUtil.getClass(Goods.class,
						"serialNumber", og.getSerialNumber(), "String", "eq");
			} else {
				currentGoods = getLibraryGoods(og.getSerialNumber());
			}
			String num = (Double.parseDouble(currentGoods.getNumber()) + og
					.getNumber()) + "";
			libraryNum.setText(num);
			currentGoods.setNumber(num);
			setLibraryGoods(currentGoods);
			tableModel.removeRow(selectedRow);
			profit.setText(DataUtil.getProfitm(currentOrder.getGoodsLst()) + "");
			total.setText(DataUtil.getTotalm(currentOrder.getGoodsLst()) + "");
			currentOrder.getGoodsLst().remove(selectedRow);
			removeModifyBtnAndDeleteBtn();
			addSubmit();
		}
	}

	private void addActionPerformed(java.awt.event.ActionEvent evt) {
		if (serialNumber.getText().length() > 1) {
			OrderGoods og = new OrderGoods();
			String[] rowValues = creatRow(og, currentOrder, true);
			tableModel.addRow(rowValues);
			currentOrder.getGoodsLst().add(og);
			profit.setText(DataUtil.getProfitm(currentOrder.getGoodsLst()) + "");
			total.setText(DataUtil.getTotalm(currentOrder.getGoodsLst()) + "");
			addSubmit();
		}
	}

	private void modifyALActionPerformed(ActionEvent e) {
		int selectedRow = table.getSelectedRow();
		if (selectedRow != -1) {
			OrderGoods og = currentOrder.getGoodsLst().get(selectedRow);

			if (getLibraryGoods(og.getSerialNumber()) == null) {
				currentGoods = (Goods) DBUtil.getClass(Goods.class,
						"serialNumber", og.getSerialNumber(), "String", "eq");
			} else {
				currentGoods = getLibraryGoods(og.getSerialNumber());
			}
			String remarkText = goodRemarksTF.getText();
			String goodChange = goodChangeCB.isSelected() ? "换货-" : "";
			String s = flowersComboBox.getSelectedItem().toString();
			String flowers = "不对花".endsWith(s) ? "" : s;
			String remark = remarkText + goodChange + flowers;
			tableModel.setValueAt(number.getText(), selectedRow, 3);
			tableModel.setValueAt(remark, selectedRow, 4);
			creatRow(currentOrder.getGoodsLst().get(selectedRow), currentOrder,
					true);

			profit.setText(DataUtil.getProfitm(currentOrder.getGoodsLst()) + "");
			total.setText(DataUtil.getTotalm(currentOrder.getGoodsLst()) + "");
			removeModifyBtnAndDeleteBtn();
			addSubmit();
		}
	}

	private String[] creatRow(OrderGoods og, OrderLst ol, boolean isAdd) {
		String serialNumber = this.serialNumber.getText().trim();
		String sellingPrice = this.sellingPrice.getText().trim();
		String number = this.number.getText().trim();
		double libraryNumber = currentGoods.getNumber() == null ? 0 : Double
				.parseDouble(currentGoods.getNumber());
		if (number.length() > 0 && serialNumber.length() > 0
				&& sellingPrice.length() > 0) {

			double numberD = Double.parseDouble(number);
			currentGoods.setNumber((libraryNumber - numberD + og.getNumber())
					+ "");
			libraryNum.setText((libraryNumber - numberD + og.getNumber()) + "");

			setLibraryGoods(currentGoods);

			String remarkText = goodRemarksTF.getText();
			String goodChange = goodChangeCB.isSelected() ? "换货-" : "";
			String s = flowersComboBox.getSelectedItem().toString();
			String flowers = "不对花".equals(s) ? "" : s;
			String remark = remarkText + goodChange + flowers;

			og.setDate(ol.getDeliveryTime());
			og.setOwner(curtainShop.getOwner());
			og.setCurtainShop(curtainShop.getName());
			og.setSerialNumber(serialNumber);
			og.setPurchasePrice(currentGoods.getPurchasePrice());
			og.setSellingPrice(Double.parseDouble(sellingPrice.trim()));
			og.setNumber(numberD);
			og.setRemark(remark);
			if (isAdd) {
				String[] rowValues = { serialNumber,
						currentGoods.getPurchasePrice() + "", sellingPrice,
						DataUtil.formatDouble(numberD), remark };
				return rowValues;
			} else {
				return null;
			}

		} else {
			JOptionPane.showMessageDialog(this, "请输入货物信息", "alert",
					JOptionPane.ERROR_MESSAGE);
			return null;
		}
	}

	/**
	 * 0有重复的覆盖，1没重复添加
	 * 
	 * @param g
	 * @return 0，1
	 */
	private int setLibraryGoods(Goods g) {
		if (libraryGoodsLst.size() == 0) {
			libraryGoodsLst.add(g);
			return 1;
		} else {
			for (int i = 0; i < libraryGoodsLst.size(); i++) {
				if (libraryGoodsLst.get(i).getSerialNumber()
						.equals(g.getSerialNumber())) {
					libraryGoodsLst.set(i, g);
					return 0;
				}
			}
		}
		libraryGoodsLst.add(g);
		return 1;
	}

	private Goods getLibraryGoods(String serialNumber) {
		if (libraryGoodsLst.size() == 0) {
			return null;
		} else {
			for (int i = 0; i < libraryGoodsLst.size(); i++) {
				if (libraryGoodsLst.get(i).getSerialNumber()
						.equals(serialNumber)) {
					return libraryGoodsLst.get(i);
				}
			}
		}
		return null;
	}

	@SuppressWarnings("unchecked")
	private void addLatelyLstToMain(int index) {
		currentOrder = latelyLst.get(index);
		goodsLst = (List<CurtainShopGoods>) DBUtil.getLstClass("serialNumber",
				"eq", CurtainShopGoods.class, "curtainShop",
				currentOrder.getCurtainShop(), "String");

		UIutil.initCurtainShopGoodsLstFromName(this, goodsjList, goodsLst);
		UIutil.tableAddAll(currentOrder.getGoodsLst(), tableModel);

		curtainShop = (CurtainShop) currentOrder.getNameClass();
		// 清空货物版面信息
		goodRemarksTF.setText("");// 清空货物备注
		sellingPrice.setText("");
		number.setText("");
		libraryNum.setText("");

		orderRemarksTF.setText(currentOrder.getRemarks());// 设置订单备注

		shopName.setText(curtainShop.getName());
		telephone.setText(curtainShop.getTelephone());
		total.setText(DataUtil.getTotalm(currentOrder.getGoodsLst()) + "");
		profit.setText(DataUtil.getProfitm(currentOrder.getGoodsLst()) + "");
		preferentialAmountTF.setText(currentOrder.getPreferentialAmount() + "");

		addPrintBtn();
	}

	private void print(Printable printOrder) {
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
