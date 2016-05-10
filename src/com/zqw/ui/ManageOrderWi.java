package com.zqw.ui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.swing.DefaultComboBoxModel;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;

import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;

import com.zqw.bean.CheckListItem;
import com.zqw.bean.CurtainShop;
import com.zqw.bean.CurtainShopGoods;
import com.zqw.bean.OrderGoods;
import com.zqw.bean.OrderLst;
import com.zqw.listener.MyListRenderer;
import com.zqw.util.DBUtil;
import com.zqw.util.DataUtil;

public class ManageOrderWi extends JFrame implements ListSelectionListener {

	/**
	 * 
	 */
	private static final long serialVersionUID = 8982508561960138821L;
	private JPanel contentPane;
	private JPanel orderPanel;
	private JTextField shopName;
	private JTextField telephone;
	private JTextField orderTotalArrears;
	private JTextField orderId;
	private JTextField orderDate;
	private JTextField orderArrears;
	// --------------------------------------------
	private ArrayList<CurtainShop> curtainShopLst = new ArrayList<CurtainShop>();
	private DefaultTableModel tableModel;
	private int curtainShopLstIndex = -1;
	private int curtainShopIndex;
	private CurtainShop curtainShop = null;
	private List<OrderLst> curtainShopOrderLst = null;
	private List<OrderGoods> curtainShopOrderGoodsLst = null;
	private List<OrderGoods> curtainShopOrderGoodsAllLst = null;
	private List<OrderLst> curtainShopOrderUncheckLst = null;
	private List<CurtainShopGoods> goodsLst;
	private OrderLst currentOrderLst = null;
	private KnsJFreeChart kjc = null;
	private JPanel tablePanel;
	private JScrollPane scrollPane;
	private JLabel lblNewLabel_2;
	private JLabel lblNewLabel_3;
	private DateChooser beginTime;
	private DateChooser lastTime;
	private ActionListener querySalesAction;
	private JButton orderCheckoutBtn;
	private JButton orderDeleteBtn;
	private ActionListener orderDeleteAl;
	private ActionListener orderCheckoutAl;
	private JScrollPane scrollPane_1;
	private JScrollPane scrollPane_2;
	private JList<CheckListItem> curtainShopjList;
	private JList<CheckListItem> curtainShopOrderjList;
	private JScrollPane scrollPane_3;
	private JTable orderTable;
	private JList<CheckListItem> uncheckOrderjLst;
	private JLabel total;
	private JLabel orderProfit;
	private JLabel orderPay;
	private MouseAdapter listAdapter;
	private int orderOrUncheckOrder = -1;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ManageOrderWi frame = new ManageOrderWi();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public ManageOrderWi() {
		initComponents();
		initCurtainShopLst(null, null);
		initTable();
	}

	@SuppressWarnings("unchecked")
	@Override
	public void valueChanged(ListSelectionEvent e) {
		if (e.getValueIsAdjusting()) {// curtainShopjList
			int indexCS = curtainShopjList.getSelectedIndex();
			if (e.getSource().equals(curtainShopjList)) {
				if (indexCS != curtainShopLstIndex && indexCS != -1) {
					curtainShopIndex = curtainShopjList.getSelectedIndex();
					curtainShopLstIndex = curtainShopIndex;
					curtainShop = curtainShopLst.get(curtainShopIndex);
					curtainShopOrderLst = (List<OrderLst>) DBUtil.getLstClass(
							"", "eq", OrderLst.class, "curtainShop",
							curtainShop.getName(), "String");
					initCurtainShopOrderLst();
					// 客户信息
					this.shopName.setText(curtainShop.getName());
					this.shopName.setEditable(false);
					this.telephone.setText(curtainShop.getTelephone());
					this.telephone.setEditable(false);
					this.orderTotalArrears.setText(UIutil
							.getCurtainShopArrears(curtainShop.getName(),
									curtainShopOrderLst)
							+ "");
					this.orderTotalArrears.setEditable(false);
					// 清空订单信息
					emptyOrder();
					// 客户统计图表
					curtainShopOrderGoodsAllLst = (List<OrderGoods>) DBUtil
							.getLstClass("", "eq", OrderGoods.class,
									"curtainShop", curtainShop.getName(),
									"String");
					customerSalesStatistics();

				}
			} else if (e.getSource().equals(curtainShopOrderjList)) {// orderjList
				int index = curtainShopOrderjList.getSelectedIndex();
				orderOrUncheckOrder = 0;
				if (index != -1) {
					currentOrderLst = curtainShopOrderLst.get(index);
					curtainShopOrderGoodsLst = currentOrderLst.getGoodsLst();

					// 按钮状态控制
					if (orderCheckoutBtn.getActionListeners().length == 0) {
						orderCheckoutBtn.setEnabled(true);
						orderCheckoutBtn.addActionListener(orderCheckoutAl);
					}
					if (orderDeleteBtn.getActionListeners().length == 0) {
						orderDeleteBtn.setEnabled(true);
						orderDeleteBtn.addActionListener(orderDeleteAl);
					}

					// 订单信息
					this.orderId.setText(currentOrderLst.getId() + "");
					this.orderId.setEditable(false);
					this.orderDate.setText(currentOrderLst.getDeliveryTime()
							.toString());
					this.orderDate.setEditable(false);
					this.orderArrears
							.setText(currentOrderLst.getArrears() + "");
					this.orderArrears.setEditable(false);
					UIutil.tableAddAll(curtainShopOrderGoodsLst, tableModel);
					total.setText(DataUtil.getTotal(curtainShopOrderGoodsLst)
							+ "");
					orderProfit.setText(DataUtil
							.getProfit(curtainShopOrderGoodsLst) + "");
				}
			} else if (e.getSource().equals(uncheckOrderjLst)) {
				int index = uncheckOrderjLst.getSelectedIndex();
				orderOrUncheckOrder = 1;
				if (index != -1) {
					currentOrderLst = curtainShopOrderUncheckLst.get(index);
					// 客户信息
					this.shopName.setText(currentOrderLst.getCurtainShop());
					this.shopName.setEditable(false);
					this.telephone.setText("");
					this.telephone.setEditable(false);
					this.orderTotalArrears.setEditable(false);
					curtainShopOrderGoodsLst = UIutil
							.getOrderGoodsFromOrderId(currentOrderLst.getId());
					// 按钮状态控制
					if (orderCheckoutBtn.getActionListeners().length == 0) {
						orderCheckoutBtn.setEnabled(true);
						orderCheckoutBtn.addActionListener(orderCheckoutAl);
					}
					if (orderDeleteBtn.getActionListeners().length == 0) {
						orderDeleteBtn.setEnabled(true);
						orderDeleteBtn.addActionListener(orderDeleteAl);
					}

					// 订单信息
					this.orderId.setText(currentOrderLst.getId() + "");
					this.orderId.setEditable(false);
					this.orderDate.setText(currentOrderLst.getDeliveryTime()
							.toString());
					this.orderDate.setEditable(false);
					this.orderArrears
							.setText(currentOrderLst.getArrears() + "");
					this.orderArrears.setEditable(false);
					UIutil.tableAddAll(curtainShopOrderGoodsLst, tableModel);
				}
			}
		}
	}

	@SuppressWarnings("unchecked")
	private void initCurtainShopLst(ArrayList<CurtainShop> csl,
			List<OrderLst> arrearsLst) {
		if (csl == null) {
			csl = (ArrayList<CurtainShop>) DBUtil.getLstClass("name", "",
					CurtainShop.class, "");
			curtainShopLst = UIutil
					.initCurtainShop(this, curtainShopjList, csl);
		}
	}

	private void initCurtainShopOrderLst() {
		
		DefaultListModel<CheckListItem> checkboxModel = new DefaultListModel<CheckListItem>();
		for (int i = 0; i < curtainShopOrderLst.size(); i++) {
			OrderLst ol = curtainShopOrderLst.get(i);
			CheckListItem cli = new CheckListItem(ol.getDeliveryTime() + ol.getCurtainShop());
			checkboxModel.add(i, cli);
		}
		curtainShopOrderjList.setModel(checkboxModel);
		curtainShopOrderjList.setCellRenderer(new MyListRenderer());
		curtainShopOrderjList.removeListSelectionListener(this);
		curtainShopOrderjList.addListSelectionListener(this);
		curtainShopOrderjList
				.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
	}

	private void initTable() {
		String[] columnNames = { "编号", "进价", "卖价", "数量", "备注" }; // 列名
		String[][] tableVales = {}; // 数据
		tableModel = new DefaultTableModel(tableVales, columnNames);
		orderTable.setModel(tableModel);
		TableColumn col0 = orderTable.getColumnModel().getColumn(0);
		col0.setPreferredWidth(100);
		TableColumn col1 = orderTable.getColumnModel().getColumn(1);
		col1.setPreferredWidth(50);
		TableColumn col2 = orderTable.getColumnModel().getColumn(2);
		col2.setPreferredWidth(50);
		TableColumn col3 = orderTable.getColumnModel().getColumn(3);
		col3.setPreferredWidth(50);
		TableColumn col4 = orderTable.getColumnModel().getColumn(4);
		col4.setPreferredWidth(150);
	}

	private void initComponents() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1206, 720);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JPanel panel = new JPanel();
		panel.setBorder(new TitledBorder(null, "\u5BA2\u6237\u4FE1\u606F",
				TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel.setBounds(354, 10, 620, 57);
		contentPane.add(panel);
		panel.setLayout(null);

		JLabel lblNewLabel = new JLabel("店名：");
		lblNewLabel.setFont(new Font("宋体", Font.PLAIN, 14));
		lblNewLabel.setBounds(32, 29, 42, 15);
		panel.add(lblNewLabel);

		JLabel label = new JLabel("电话：");
		label.setFont(new Font("宋体", Font.PLAIN, 14));
		label.setBounds(205, 29, 54, 15);
		panel.add(label);

		JLabel label_1 = new JLabel("总欠款：");
		label_1.setFont(new Font("宋体", Font.PLAIN, 14));
		label_1.setBounds(355, 29, 70, 15);
		panel.add(label_1);

		shopName = new JTextField();
		shopName.setBounds(75, 26, 120, 21);
		panel.add(shopName);
		shopName.setColumns(10);

		telephone = new JTextField();
		telephone.setBounds(245, 26, 94, 21);
		panel.add(telephone);
		telephone.setColumns(10);

		orderTotalArrears = new JTextField();
		orderTotalArrears.setEnabled(false);
		orderTotalArrears.setBounds(429, 26, 42, 21);
		panel.add(orderTotalArrears);
		orderTotalArrears.setColumns(10);

		orderPanel = new JPanel();
		orderPanel.setBorder(new TitledBorder(null, "\u8BA2\u5355\u4FE1\u606F",
				TitledBorder.LEADING, TitledBorder.TOP, null, null));
		orderPanel.setBounds(354, 70, 620, 256);
		contentPane.add(orderPanel);
		orderPanel.setLayout(null);

		JLabel label_2 = new JLabel("订单编号：");
		label_2.setFont(new Font("宋体", Font.PLAIN, 14));
		label_2.setBounds(20, 26, 72, 22);
		orderPanel.add(label_2);

		JLabel label_3 = new JLabel("日期：");
		label_3.setFont(new Font("宋体", Font.PLAIN, 14));
		label_3.setBounds(157, 26, 60, 22);
		orderPanel.add(label_3);

		JLabel label_4 = new JLabel("欠款：");
		label_4.setFont(new Font("宋体", Font.PLAIN, 14));
		label_4.setBounds(297, 26, 46, 22);
		orderPanel.add(label_4);

		orderId = new JTextField();
		orderId.setColumns(10);
		orderId.setBounds(84, 27, 52, 21);
		orderPanel.add(orderId);

		orderDate = new JTextField();
		orderDate.setColumns(10);
		orderDate.setBounds(199, 27, 78, 21);
		orderPanel.add(orderDate);

		orderArrears = new JTextField();
		orderArrears.setEnabled(false);
		orderArrears.setColumns(10);
		orderArrears.setBounds(340, 27, 46, 21);
		orderPanel.add(orderArrears);

		orderCheckoutBtn = new JButton("结账");
		orderCheckoutBtn.setEnabled(false);
		orderCheckoutAl = new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				orderCheckoutAction();
			}
		};
		orderCheckoutBtn.setFont(new Font("宋体", Font.PLAIN, 14));
		orderCheckoutBtn.setBounds(396, 25, 67, 23);
		orderPanel.add(orderCheckoutBtn);

		orderDeleteBtn = new JButton("删除");
		orderDeleteAl = new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				orderDeleteAction();
			}
		};
		orderDeleteBtn.setFont(new Font("宋体", Font.PLAIN, 14));
		orderDeleteBtn.setEnabled(false);
		orderDeleteBtn.setBounds(473, 25, 67, 23);
		orderPanel.add(orderDeleteBtn);

		scrollPane_3 = new JScrollPane();
		scrollPane_3.setBounds(10, 58, 600, 165);
		orderPanel.add(scrollPane_3);

		orderTable = new JTable();
		scrollPane_3.setViewportView(orderTable);

		orderProfit = new JLabel("");
		orderProfit.setBounds(66, 231, 54, 15);
		orderPanel.add(orderProfit);

		JLabel label_8 = new JLabel("利润：");
		label_8.setBounds(20, 231, 44, 15);
		orderPanel.add(label_8);

		orderPay = new JLabel("");
		orderPay.setBounds(188, 231, 54, 15);
		orderPanel.add(orderPay);

		JLabel label_9 = new JLabel("付款：");
		label_9.setBounds(142, 231, 44, 15);
		orderPanel.add(label_9);

		JLabel label_10 = new JLabel("总价：");
		label_10.setBounds(243, 231, 44, 15);
		orderPanel.add(label_10);

		total = new JLabel("");
		total.setBounds(289, 231, 54, 15);
		orderPanel.add(total);

		tablePanel = new JPanel();
		tablePanel.setBorder(new LineBorder(new Color(0, 0, 0)));
		tablePanel.setBounds(354, 371, 620, 281);
		contentPane.add(tablePanel);
		tablePanel.setLayout(null);

		JLabel label_5 = new JLabel("店名");
		label_5.setFont(new Font("宋体", Font.PLAIN, 14));
		label_5.setBounds(62, 17, 54, 15);
		contentPane.add(label_5);

		JLabel lblNewLabel_1 = new JLabel("订单");
		lblNewLabel_1.setFont(new Font("宋体", Font.PLAIN, 14));
		lblNewLabel_1.setBounds(213, 17, 54, 15);
		contentPane.add(lblNewLabel_1);

		querySalesAction = new ActionListener() {
			@SuppressWarnings("unchecked")
			public void actionPerformed(ActionEvent e) {
				Date begin = beginTime.getDate();
				Date last = lastTime.getDate();
				String sql1 = "select new OrderGoods(g.serialNumber,g.sellingPrice,g.purchasePrice,g.number) from OrderGoods g where curtainShop =:name0 and g.date >=:name1 and g.date <=:name2 order by g.serialNumber";
				curtainShopOrderGoodsAllLst = (List<OrderGoods>) DBUtil
						.getClassLst(sql1, curtainShop.getName() + "", begin,
								last);
				customerSalesStatistics();
			}
		};
		JButton querySales = new JButton("查询");
		querySales.addActionListener(querySalesAction);

		querySales.setBounds(888, 338, 73, 23);
		contentPane.add(querySales);
		querySales.setFont(new Font("宋体", Font.PLAIN, 14));

		lblNewLabel_2 = new JLabel("开始时间：");
		lblNewLabel_2.setFont(new Font("宋体", Font.PLAIN, 14));
		lblNewLabel_2.setBounds(364, 345, 73, 15);
		contentPane.add(lblNewLabel_2);

		lblNewLabel_3 = new JLabel("结束时间：");
		lblNewLabel_3.setFont(new Font("宋体", Font.PLAIN, 14));
		lblNewLabel_3.setBounds(560, 346, 73, 15);
		contentPane.add(lblNewLabel_3);

		beginTime = new DateChooser();
		beginTime.setBounds(448, 340, 102, 25);
		contentPane.add(beginTime);

		lastTime = new DateChooser();
		lastTime.setBounds(643, 340, 102, 25);
		contentPane.add(lastTime);

		scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(10, 42, 131, 630);
		contentPane.add(scrollPane_1);

		curtainShopjList = new JList<CheckListItem>();
		scrollPane_1.setViewportView(curtainShopjList);

		scrollPane_2 = new JScrollPane();
		scrollPane_2.setBounds(151, 42, 193, 630);
		contentPane.add(scrollPane_2);

		curtainShopOrderjList = new JList<CheckListItem>();
		scrollPane_2.setViewportView(curtainShopOrderjList);

		JLabel label_6 = new JLabel("利润：");
		label_6.setBounds(396, 657, 44, 15);
		contentPane.add(label_6);

		JLabel profit = new JLabel("");
		profit.setBounds(442, 657, 54, 15);
		contentPane.add(profit);

		JComboBox comboBox = new JComboBox();
		comboBox.setModel(new DefaultComboBoxModel(new String[] { "客户", "所有",
				"自己", "家里" }));
		comboBox.setBounds(787, 342, 73, 21);
		contentPane.add(comboBox);

		JScrollPane scrollPane_4 = new JScrollPane();
		scrollPane_4.setBounds(984, 48, 196, 604);
		contentPane.add(scrollPane_4);

		uncheckOrderjLst = new JList<CheckListItem>();
		listAdapter = new MouseAdapter() {
			public void mouseClicked(MouseEvent event) {
				JList<CheckListItem> list = (JList<CheckListItem>) event
						.getSource();
				// Get index of item clicked
				// 获得用户点击项的索引
				int index = list.locationToIndex(event.getPoint());
				CheckListItem item = list.getModel().getElementAt(index);
				// 设置列表中项的选择状态
				item.setSelected(!item.isSelected());
				// 重新绘制列表中项
				list.repaint(list.getCellBounds(index, index));
			}

			public void mousePressed(MouseEvent e) {
			}

			public void mouseReleased(MouseEvent e) {
			}
		};
		curtainShopOrderUncheckLst = UIutil.initLatelyJlist(this,
				uncheckOrderjLst, listAdapter, false, null, 2);
		scrollPane_4.setViewportView(uncheckOrderjLst);

		JLabel lblZui = new JLabel("未结账订单");
		lblZui.setFont(new Font("宋体", Font.BOLD, 14));
		lblZui.setBounds(1034, 14, 84, 21);
		contentPane.add(lblZui);
	}

	private void emptyOrder() {
		this.orderId.setText("");
		this.orderId.setEditable(false);
		this.orderDate.setText("");
		this.orderDate.setEditable(false);
		this.orderArrears.setText("");
		this.orderArrears.setEditable(false);
		// 清空订单表格
		for (int i = 0; i < tableModel.getRowCount();) {
			tableModel.removeRow(0);
		}
	}

	private void customerSalesStatistics() {
		goodsLst = (List<CurtainShopGoods>) DBUtil.getLstClass("serialNumber",
				"eq", CurtainShopGoods.class, "curtainShop",
				curtainShop.getName(), "String");
		ArrayList<String[]> datasetLstTest = new ArrayList<String[]>();
		if (curtainShopOrderGoodsAllLst.size() > 0) {
			int counter = 0;
			for (int i = 0; i < goodsLst.size(); i++) {
				String[] s = new String[3];
				s[1] = 3 + "";
				s[2] = goodsLst.get(i).getSerialNumber();
				int totalNumber = 0;
				while (curtainShopOrderGoodsAllLst.get(counter)
						.getSerialNumber().equals(s[2])) {
					totalNumber += curtainShopOrderGoodsAllLst.get(counter)
							.getNumber();
					counter++;
					if (counter == curtainShopOrderGoodsAllLst.size()) {
						counter = 0;
						break;
					} else {

					}
				}
				s[0] = totalNumber + "";
				datasetLstTest.add(s);
			}
		}
		kjc = new KnsJFreeChart();
		kjc.setDatasetLst(datasetLstTest);
		JFreeChart chart1 = kjc.createChart(kjc.createDataset());
		ChartPanel cp = new ChartPanel(chart1);
		cp.setPreferredSize(new Dimension(goodsLst.size() * 30 + 100, 300));
		cp.setMouseZoomable(false);
		tablePanel.removeAll();
		scrollPane = new JScrollPane(cp);
		scrollPane.setBounds(10, 10, 600, 281);
		tablePanel.setVisible(true);
		tablePanel.add(scrollPane);
		tablePanel.updateUI();
	}

	private void orderCheckoutAction() {// 结账
		if (currentOrderLst != null) {
			orderArrears.setText(0 + "");// 欠款置0
			currentOrderLst.setArrears(0);
			currentOrderLst.setOrderState(10);
			curtainShopOrderUncheckLst.remove(currentOrderLst);
			DBUtil.update(currentOrderLst);
			updateUncheckList(curtainShopOrderUncheckLst, currentOrderLst);
			UIutil.initLatelyJlist(this, uncheckOrderjLst, listAdapter, false,
					curtainShopOrderUncheckLst, 2);

			orderCheckoutBtn.setEnabled(false);
			orderCheckoutBtn.removeActionListener(orderCheckoutAl);
			orderDeleteBtn.setEnabled(false);
			orderDeleteBtn.removeActionListener(orderDeleteAl);
		} else {

		}
	}

	private void updateUncheckList(List<OrderLst> olLst, OrderLst ol) {
		for (int i = 0; i < olLst.size(); i++) {
			if (olLst.get(i).getId() == ol.getId()) {
				olLst.set(i, ol);
			}
		}
	}

	private void orderDeleteAction() {
		if (currentOrderLst != null) {
			curtainShopOrderUncheckLst.remove(currentOrderLst);
			updateUncheckList(curtainShopOrderUncheckLst, currentOrderLst);
			UIutil.initLatelyJlist(this, uncheckOrderjLst, listAdapter, false,
					curtainShopOrderUncheckLst, 2);
			DBUtil.del(currentOrderLst);
			orderCheckoutBtn.setEnabled(false);
			orderCheckoutBtn.removeActionListener(orderCheckoutAl);
			orderDeleteBtn.setEnabled(false);
			orderDeleteBtn.removeActionListener(orderDeleteAl);
			emptyOrder();
			UIutil.delFromCurtainShopGoods();
		} else {
		}
	}
}
