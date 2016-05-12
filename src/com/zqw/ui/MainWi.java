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
import com.zqw.util.DBUtil;
import com.zqw.util.DataUtil;
import com.zqw.util.SortChineseName;

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
	private ActionListener submitOrderAL;
	private MouseAdapter listAdapter;
	private CurtainShop curtainShop = null;
	private int curtainShopLstIndex = -1;
	private int latelyLstindex = -1;
	private JList<CheckListItem> curtainShopjList;
	private JList<CheckListItem> goodsjList;
	private JList<CheckListItem> latelyjList;
	private List<CurtainShopGoods> goodsLst = new ArrayList<CurtainShopGoods>();
	private ArrayList<CurtainShop> curtainShopLst = new ArrayList<CurtainShop>();
	private ArrayList<OrderLst> latelyLst;
	private ArrayList<OrderLst> pandectList = new ArrayList<OrderLst>();
	private JCheckBox goodChangeCB;
	private JCheckBox freeGoodsBC;
	private JComboBox<String> flowersComboBox;
	private OrderLst currentOrder;
	private JButton btnNewButton;

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
				curtainShop = curtainShopLst.get(curtainShopLstIndex);
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
						"curtainShop", currentOrder.getCurtainShop(), "String");
				UIutil.initCurtainShopGoodsLstFromName(this, goodsjList,
						goodsLst);
				initTable();
				// 重置版面信息
				number.setText("");
				sellingPrice.setText("");
				sellingPrice.setEditable(false);
				serialNumber.setText("");
				serialNumber.setEditable(false);
				freeGoodsBC.setSelected(false);
				goodChangeCB.setSelected(false);
				flowersComboBox.setSelectedIndex(0);
				removePrintBtn();
				removeSubmit();
			} else if (e.getSource().equals(goodsjList)) {// 客户货物
				number.setText("");
				freeGoodsBC.setSelected(false);
				goodChangeCB.setSelected(false);
				flowersComboBox.setSelectedIndex(0);
				int index = goodsjList.getSelectedIndex();
				CurtainShopGoods goods = goodsLst.get(index);
				sellingPrice.setText(goods.getSellingPrice() + "");
				sellingPrice.setEditable(false);
				serialNumber.setText(goods.getSerialNumber());
				serialNumber.setEditable(false);
			} else {

			}
		}
	}

	@SuppressWarnings("unchecked")
	private void initData() {
		curtainShopjList = new JList<CheckListItem>();
		scrollPane_1.setViewportView(curtainShopjList);
		curtainShopLst = (ArrayList<CurtainShop>) DBUtil.getLstClass("name",
				"", CurtainShop.class, "");
		UIutil.initCurtainShop(this, curtainShopjList, curtainShopLst);

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
		latelyLst = (ArrayList<OrderLst>) UIutil.initLatelyJlist(this,
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
				if (selectedRow != -1) {
					serialNumber.setText(tableModel.getValueAt(selectedRow, 0)
							.toString());
					sellingPrice.setText(tableModel.getValueAt(selectedRow, 1)
							.toString());
					number.setText(tableModel.getValueAt(selectedRow, 3)
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
		TableColumn col3 = table.getColumnModel().getColumn(3);
		col3.setPreferredWidth(50);
		TableColumn col4 = table.getColumnModel().getColumn(4);
		col4.setPreferredWidth(150);
	}

	/**
	 * Create the frame.
	 */

	private void initComponents() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1118, 649);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel lblNewLabel = new JLabel("店名：");
		lblNewLabel.setBounds(335, 23, 54, 15);
		lblNewLabel.setFont(new Font("宋体", Font.PLAIN, 14));
		contentPane.add(lblNewLabel);

		shopName = new JTextField();
		shopName.setBounds(378, 21, 130, 21);
		contentPane.add(shopName);
		shopName.setColumns(10);

		JLabel label = new JLabel("电话：");
		label.setBounds(518, 22, 54, 15);
		label.setFont(new Font("宋体", Font.PLAIN, 14));
		contentPane.add(label);

		telephone = new JTextField();
		telephone.setBounds(561, 20, 86, 21);
		contentPane.add(telephone);
		telephone.setColumns(10);

		JLabel label_1 = new JLabel("编号：");
		label_1.setBounds(335, 57, 54, 15);
		label_1.setFont(new Font("宋体", Font.PLAIN, 14));
		contentPane.add(label_1);

		serialNumber = new JTextField();
		serialNumber.setBounds(378, 54, 130, 21);
		contentPane.add(serialNumber);
		serialNumber.setColumns(10);

		JLabel label_2 = new JLabel("价格：");
		label_2.setBounds(518, 56, 54, 15);
		label_2.setFont(new Font("宋体", Font.PLAIN, 14));
		contentPane.add(label_2);

		sellingPrice = new JTextField();
		sellingPrice.setBounds(572, 54, 48, 21);
		contentPane.add(sellingPrice);
		sellingPrice.setColumns(10);

		JLabel label_3 = new JLabel("数量：");
		label_3.setBounds(630, 57, 45, 15);
		label_3.setFont(new Font("宋体", Font.PLAIN, 14));
		contentPane.add(label_3);

		number = new JTextField();
		number.setBounds(674, 54, 41, 21);
		contentPane.add(number);
		number.setColumns(10);

		printBtnAL = new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				printActionPerformed(e);
			}
		};
		orderPrintBtn = new JButton("打印");
		orderPrintBtn.setBounds(729, 19, 62, 23);
		orderPrintBtn.setFont(new Font("宋体", Font.PLAIN, 14));
		orderPrintBtn.setEnabled(false);
		contentPane.add(orderPrintBtn);

		addBtn = new JButton("添加");
		addBtn.setBounds(729, 79, 62, 23);
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
		deleteBtn.setBounds(657, 79, 62, 23);
		deleteBtn.setFont(new Font("宋体", Font.PLAIN, 14));
		deleteBtn.setEnabled(false);
		contentPane.add(deleteBtn);

		manageBtn = new JButton("客户管理");
		manageBtn.setBounds(335, 576, 95, 23);
		manageBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				refreshActionPerformed(e);
			}
		});
		manageBtn.setFont(new Font("宋体", Font.PLAIN, 14));
		contentPane.add(manageBtn);

		total = new JLabel("");
		total.setBounds(581, 580, 54, 15);
		contentPane.add(total);

		JLabel lblNewLabel_1 = new JLabel("总价：");
		lblNewLabel_1.setBounds(523, 580, 48, 15);
		lblNewLabel_1.setFont(new Font("宋体", Font.PLAIN, 14));
		contentPane.add(lblNewLabel_1);

		JLabel label_4 = new JLabel("利润：");
		label_4.setBounds(645, 580, 52, 15);
		label_4.setFont(new Font("宋体", Font.PLAIN, 14));
		contentPane.add(label_4);

		profit = new JLabel("");
		profit.setBounds(697, 580, 54, 15);
		contentPane.add(profit);

		scrollPane = new JScrollPane();
		scrollPane.setBounds(334, 110, 462, 456);
		contentPane.add(scrollPane);
		table = new JTable();

		table.setBounds(10, 10, 401, 119);
		table.setBorder(new LineBorder(new Color(0, 0, 0)));
		scrollPane.setViewportView(table);

		freeGoodsBC = new JCheckBox("免费样品");
		freeGoodsBC.setBounds(335, 79, 81, 23);
		freeGoodsBC.setFont(new Font("宋体", Font.PLAIN, 14));
		contentPane.add(freeGoodsBC);

		scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(10, 38, 159, 561);
		contentPane.add(scrollPane_1);

		scrollPane_2 = new JScrollPane();
		scrollPane_2.setBounds(179, 38, 146, 561);
		contentPane.add(scrollPane_2);

		scrollPane_3 = new JScrollPane();
		scrollPane_3.setBounds(801, 27, 291, 539);
		contentPane.add(scrollPane_3);

		JLabel jlabel = new JLabel("客户");
		jlabel.setFont(new Font("宋体", Font.PLAIN, 14));
		jlabel.setBounds(72, 10, 54, 15);
		contentPane.add(jlabel);

		JLabel jlabel_1 = new JLabel("客户货物");
		jlabel_1.setFont(new Font("宋体", Font.PLAIN, 14));
		jlabel_1.setBounds(220, 13, 70, 15);
		contentPane.add(jlabel_1);

		JLabel jlabel_2 = new JLabel("历史订单");
		jlabel_2.setFont(new Font("宋体", Font.PLAIN, 14));
		jlabel_2.setBounds(930, 10, 70, 15);
		contentPane.add(jlabel_2);

		JButton pandectPrintBtn = new JButton("目录打印");
		pandectPrintBtn.setFont(new Font("宋体", Font.PLAIN, 14));
		pandectPrintBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				pandectActionPerformed(e);
			}
		});
		pandectPrintBtn.setBounds(811, 576, 94, 23);
		contentPane.add(pandectPrintBtn);

		JButton untreatedPrintBtn = new JButton("备货打印");
		untreatedPrintBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				untreatedPrintActionPerformed(e);
			}
		});
		untreatedPrintBtn.setFont(new Font("宋体", Font.PLAIN, 14));
		untreatedPrintBtn.setBounds(915, 576, 93, 23);
		contentPane.add(untreatedPrintBtn);

		submitOrderBtn = new JButton("提交");
		submitOrderAL = new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				submitOrderactionPerformed(e);
			}
		};
		submitOrderBtn.setFont(new Font("宋体", Font.PLAIN, 14));
		submitOrderBtn.setEnabled(false);
		submitOrderBtn.setBounds(657, 19, 62, 23);
		contentPane.add(submitOrderBtn);

		goodChangeCB = new JCheckBox("换货");
		goodChangeCB.setFont(new Font("宋体", Font.PLAIN, 14));
		goodChangeCB.setBounds(421, 79, 54, 23);
		contentPane.add(goodChangeCB);

		flowersComboBox = new JComboBox<String>();
		flowersComboBox.setModel(new DefaultComboBoxModel(new String[] {"不对花", "对花", "多对", "少对", "对整花"}));
		flowersComboBox.setFont(new Font("宋体", Font.PLAIN, 13));
		flowersComboBox.setBounds(481, 80, 66, 21);
		contentPane.add(flowersComboBox);

		modifyAL = new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				modifyALActionPerformed(e);
			}
		};
		modifyBtn = new JButton("修改");
		modifyBtn.setFont(new Font("宋体", Font.PLAIN, 14));
		modifyBtn.setBounds(585, 79, 62, 23);
		modifyBtn.setEnabled(false);
		contentPane.add(modifyBtn);

		JButton historyListDelBtn = new JButton("删除");
		historyListDelBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				orderDeleteAction();
			}
		});

		historyListDelBtn.setFont(new Font("宋体", Font.PLAIN, 14));
		historyListDelBtn.setBounds(1022, 576, 70, 23);
		contentPane.add(historyListDelBtn);

		btnNewButton = new JButton("刷新");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				DBUtil.refresh();
				initTable();
				initData();
			}
		});
		btnNewButton.setBounds(438, 576, 70, 23);
		contentPane.add(btnNewButton);
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
			submitOrderBtn.addActionListener(submitOrderAL);
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
			submitOrderBtn.removeActionListener(submitOrderAL);
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
	private void submitOrderactionPerformed(ActionEvent e) {
		if (tableModel.getRowCount() > 0) {
			currentOrder.setOrderState(40);
			currentOrder.setSubmitTime(new Date());
			currentOrder.setArrears(Integer.parseInt(total.getText()));
			// 判断id是否为空
			if (currentOrder.getId() < 1) {
				currentOrder.setId(UIutil.getMaxIdFromOrderLst());
				DBUtil.insert(currentOrder);
				latelyLst.add(currentOrder);
			} else {
				DBUtil.update(currentOrder);
			}
			UIutil.initLatelyJlist(this, latelyjList, listAdapter, true,
					latelyLst, 1);
			removeSubmit();
		} else {

		}
	}

	/**
	 * 打印备货单
	 */
	private void untreatedPrintActionPerformed(ActionEvent evt) {
		ListModel<CheckListItem> l = latelyjList.getModel();
		if (l.getSize() > 0) {
			for (int i = 0; i < l.getSize(); i++) {
				CheckListItem cli = l.getElementAt(i);
				if (cli.isSelected()) {
					OrderLst ol = latelyLst.get(i);
					CurtainShop cs = (CurtainShop) DBUtil.getClass(
							CurtainShop.class, "name", ol.getCurtainShop(),
							"eq");
					DBUtil.update(ol);
					ol.setOrderState(30);
					UIutil.initLatelyJlist(this, latelyjList, listAdapter,
							true, latelyLst, 1);
					print(Global.EMPLOYEE, ol, cs);
				}
			}
		}
	}

	/**
	 * 打印出库单
	 * 
	 * @param evt
	 */
	private void printActionPerformed(ActionEvent evt) {

		if (currentOrder.getGoodsLst().size() > 0
				&& currentOrder.getOrderState() >= 30) {
			currentOrder.setOrderState(20);
			currentOrder.setArrears(Integer.parseInt(total.getText()));
			currentOrder.setDeliveryTime(new Date());
			List<OrderGoods> lst = currentOrder.getGoodsLst();
			for (int i = 0; i < lst.size(); i++) {
				lst.get(i).setDate(new Date());
			}
			removePrintBtn();
			UIutil.initLatelyJlist(this, latelyjList, listAdapter, true,
					latelyLst, 1);
			print(Global.CUSTOMER, currentOrder, curtainShop);
			print(Global.OWN, currentOrder, curtainShop);
			DBUtil.update(currentOrder);
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
			if (cli.isSelected()) {
				pandectList.add(latelyLst.get(i));
			}
		}
		Collections.sort(pandectList, new SortChineseName());
		// print
		Book book = new Book();
		PageFormat pf = new PageFormat();
		pf.setOrientation(PageFormat.PORTRAIT);
		Paper p = new Paper();
		p.setSize(590, 840);
		p.setImageableArea(10, 10, 590, 840);
		pf.setPaper(p);
		PrintPandect pp = new PrintPandect(pandectList);
		book.append(pp, pf);
		PrinterJob job = PrinterJob.getPrinterJob();
		job.setPageable(book);
		try {
			job.print();
		} catch (PrinterException e) {
			e.printStackTrace();
		}

	}

	private void orderDeleteAction() {
		if (latelyLst != null) {
			latelyLst.remove(currentOrder);
			UIutil.initLatelyJlist(this, latelyjList, listAdapter, true,
					latelyLst, 1);
			DBUtil.del(currentOrder);
			UIutil.delFromCurtainShopGoods();
		} else {
		}
	}

	private void deleteActionPerformed(java.awt.event.ActionEvent evt) {
		int selectedRow = table.getSelectedRow();
		if (selectedRow != -1) {
			tableModel.removeRow(selectedRow);
			profit.setText(DataUtil.getProfitm(tableModel) + "");
			total.setText(DataUtil.getTotalm(tableModel) + "");
			currentOrder.getGoodsLst().remove(selectedRow);
			removeModifyBtnAndDeleteBtn();
		}
	}

	private void addActionPerformed(java.awt.event.ActionEvent evt) {
		if (serialNumber.getText().length() > 1) {
			String[] rowValues = creatRow();
			tableModel.addRow(rowValues);
			OrderGoods og = new OrderGoods();
			updateOrderGoods(tableModel.getRowCount() - 1, og, currentOrder);
			currentOrder.getGoodsLst().add(og);

			profit.setText(DataUtil.getProfitm(tableModel) + "");
			total.setText(DataUtil.getTotalm(tableModel) + "");
			addSubmit();
		}
	}

	private void modifyALActionPerformed(ActionEvent e) {
		int selectedRow = table.getSelectedRow();
		if (selectedRow != -1) {
			String freeGoods = freeGoodsBC.isSelected() ? "免费样品-" : "";
			String goodChange = goodChangeCB.isSelected() ? "换货-" : "";
			String s = flowersComboBox.getSelectedItem().toString();
			String flowers = "不对花".endsWith(s) ? "" : s;
			String remark = freeGoods + goodChange + flowers;
			tableModel.setValueAt(number.getText(), selectedRow, 3);
			tableModel.setValueAt(remark, selectedRow, 4);
			updateOrderGoods(selectedRow,
					currentOrder.getGoodsLst().get(selectedRow), currentOrder);
			profit.setText(DataUtil.getProfitm(tableModel) + "");
			total.setText(DataUtil.getTotalm(tableModel) + "");
			removeModifyBtnAndDeleteBtn();
		}
	}

	private String[] creatRow() {
		String serialNumber = this.serialNumber.getText().trim();
		String sellingPrice = this.sellingPrice.getText().trim();
		String number = this.number.getText().trim();
		if (number.length() > 0 && serialNumber.length() > 0
				&& sellingPrice.length() > 0) {
			double numberD = Double.parseDouble(number);
			Goods g = UIutil.getGoodsFromName(serialNumber);
			String freeGoods = freeGoodsBC.isSelected() ? "免费样品-" : "";
			String goodChange = goodChangeCB.isSelected() ? "换货-" : "";
			String s = flowersComboBox.getSelectedItem().toString();
			String flowers = "不对花".equals(s) ? "" : s;
			String remark = freeGoods + goodChange + flowers;
			String[] rowValues = { serialNumber, g.getPurchasePrice() + "",
					sellingPrice, DataUtil.formatDouble(numberD), remark };
			return rowValues;
		} else {
			JOptionPane.showMessageDialog(this, "请输入货物信息", "alert",
					JOptionPane.ERROR_MESSAGE);
			return null;
		}
	}

	private void updateOrderGoods(int i, OrderGoods og, OrderLst ol) {
		// TODO Auto-generated method stub
		og.setOrderId(ol.getId());
		og.setDate(ol.getDeliveryTime());
		og.setOwner(curtainShop.getOwner());
		og.setCurtainShop(curtainShop.getName());
		og.setSerialNumber(tableModel.getValueAt(i, 0).toString());
		og.setPurchasePrice(Double.parseDouble(tableModel.getValueAt(i, 1)
				.toString()));
		og.setSellingPrice(Double.parseDouble(tableModel.getValueAt(i, 2)
				.toString()));
		og.setNumber(Double.parseDouble(tableModel.getValueAt(i, 3).toString()));
		og.setRemark(tableModel.getValueAt(i, 4).toString());
	}

	@SuppressWarnings("unchecked")
	private void addLatelyLstToMain(int index) {
		currentOrder = latelyLst.get(index);
		goodsLst = (List<CurtainShopGoods>) DBUtil.getLstClass("serialNumber",
				"eq", CurtainShopGoods.class, "curtainShop",
				currentOrder.getCurtainShop(), "String");
		UIutil.initCurtainShopGoodsLstFromName(this, goodsjList, goodsLst);
		UIutil.tableAddAll(currentOrder.getGoodsLst(), tableModel);
		curtainShop = (CurtainShop) DBUtil.getClass(CurtainShop.class, "name",
				currentOrder.getCurtainShop(), "eq");
		shopName.setText(currentOrder.getCurtainShop());
		telephone.setText(curtainShop.getTelephone());
		total.setText(currentOrder.getArrears() + "");
		addPrintBtn();
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
