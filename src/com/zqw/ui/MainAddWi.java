package com.zqw.ui;

import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.WindowConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;

import com.zqw.bean.CurtainShop;
import com.zqw.bean.CurtainShopGoods;
import com.zqw.bean.Global;
import com.zqw.bean.Goods;
import com.zqw.util.DBUtil;
import com.zqw.util.UIutil;

@SuppressWarnings("serial")
public class MainAddWi extends JDialog implements ListSelectionListener {

	/**
	 * Create the frame.
	 */
	public MainAddWi() {
		initComponents();
		initListener();
		initCurtainShop();
		initGoods();
		initTable();
	}

	public int getReturnStatus() {
		return state;
	}

	private void initListener() {
		curtainShopBtnAdd
				.addActionListener(new java.awt.event.ActionListener() {
					public void actionPerformed(java.awt.event.ActionEvent evt) {
						curtainShopBtnAddActionPerformed(evt);
					}
				});
		curtainShopModifyBtn
				.addActionListener(new java.awt.event.ActionListener() {
					public void actionPerformed(java.awt.event.ActionEvent evt) {
						curtainShopModifyBtnActionPerformed(evt);
					}
				});
		goodModifyBtn.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				goodModifyBtnActionPerformed(evt);
			}
		});
		addBtn.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				addBtnActionPerformed(evt);
			}
		});
		modifyBtn.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				modifyBtnActionPerformed(evt);
			}
		});
		goodjListBtnAdd.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				goodjListBtnAddActionPerformed(e);
			}
		});
	}

	private void initComponents() {
		setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 922, 617);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel label = new JLabel();
		label.setText("店  名");
		label.setFont(new Font("微软雅黑", Font.PLAIN, 14));
		label.setBounds(70, 10, 36, 19);
		contentPane.add(label);

		JLabel label_1 = new JLabel();
		label_1.setText("货 物");
		label_1.setFont(new Font("微软雅黑", Font.PLAIN, 14));
		label_1.setBounds(232, 10, 32, 19);
		contentPane.add(label_1);

		scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 37, 157, 494);
		contentPane.add(scrollPane);

		scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(179, 39, 133, 494);
		contentPane.add(scrollPane_1);

		curtainShopBtnAdd = new JButton();
		curtainShopBtnAdd.setText("添加");
		curtainShopBtnAdd.setBounds(20, 541, 62, 23);
		contentPane.add(curtainShopBtnAdd);

		curtainShopBtnDel = new JButton();
		curtainShopBtnDel.setText("删除");
		curtainShopBtnDel.setBounds(88, 541, 62, 23);
		contentPane.add(curtainShopBtnDel);

		goodjListBtnAdd = new JButton();

		goodjListBtnAdd.setText("添加");
		goodjListBtnAdd.setBounds(176, 543, 62, 23);
		contentPane.add(goodjListBtnAdd);

		goodjListBtnDel = new JButton();
		goodjListBtnDel.setText("删除");
		goodjListBtnDel.setBounds(250, 543, 62, 23);
		contentPane.add(goodjListBtnDel);

		JPanel panel = new JPanel();
		panel.setBorder(new TitledBorder(null, "\u8D27\u7269\u6DFB\u52A0",
				TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel.setBounds(322, 10, 283, 418);
		contentPane.add(panel);
		panel.setLayout(null);

		JLabel label_2 = new JLabel();
		label_2.setText("型 号：");
		label_2.setBounds(10, 30, 42, 15);
		panel.add(label_2);

		serialNumber = new JTextField();
		serialNumber.setBounds(56, 27, 66, 21);
		panel.add(serialNumber);

		JLabel label_3 = new JLabel();
		label_3.setText("货 源：");
		label_3.setBounds(126, 30, 42, 15);
		panel.add(label_3);

		factory = new JTextField();
		factory.setBounds(172, 27, 66, 21);
		panel.add(factory);

		sellingPrice = new JTextField();
		sellingPrice.setBounds(172, 54, 66, 21);
		panel.add(sellingPrice);

		JLabel label_4 = new JLabel();
		label_4.setText("售 价：");
		label_4.setBounds(126, 57, 42, 15);
		panel.add(label_4);

		purchasePrice = new JTextField();
		purchasePrice.setBounds(56, 54, 66, 21);
		panel.add(purchasePrice);

		JLabel label_5 = new JLabel();
		label_5.setText("进 价：");
		label_5.setBounds(10, 57, 42, 15);
		panel.add(label_5);

		JLabel label_6 = new JLabel();
		label_6.setText("电 话：");
		label_6.setBounds(10, 84, 42, 15);
		panel.add(label_6);

		telephone = new JTextField();
		telephone.setBounds(56, 81, 124, 21);
		panel.add(telephone);

		scrollPane_2 = new JScrollPane();
		scrollPane_2.setBounds(10, 109, 268, 266);
		panel.add(scrollPane_2);

		addBtn = new JButton();
		addBtn.setText("添加");
		addBtn.setBounds(69, 385, 67, 23);
		panel.add(addBtn);

		delBtn = new JButton();
		delBtn.setText("删除");
		delBtn.setBounds(146, 385, 67, 23);
		panel.add(delBtn);

		JPanel panel_1 = new JPanel();
		panel_1.setBorder(new TitledBorder(null, "\u8D27\u7269\u4FEE\u6539",
				TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel_1.setBounds(322, 426, 283, 140);
		contentPane.add(panel_1);
		panel_1.setLayout(null);

		goodSerialNumberModify = new JTextField();
		goodSerialNumberModify.setBounds(56, 23, 119, 21);
		panel_1.add(goodSerialNumberModify);

		JLabel label_7 = new JLabel();
		label_7.setText("型 号：");
		label_7.setBounds(10, 26, 42, 15);
		panel_1.add(label_7);

		JLabel label_8 = new JLabel();
		label_8.setText("进 价：");
		label_8.setBounds(10, 53, 42, 15);
		panel_1.add(label_8);

		goodPurchasePriceModify = new JTextField();
		goodPurchasePriceModify.setBounds(56, 50, 119, 21);
		panel_1.add(goodPurchasePriceModify);

		JLabel label_9 = new JLabel();
		label_9.setText("备 注：");
		label_9.setBounds(10, 109, 42, 15);
		panel_1.add(label_9);

		goodRemarksModify = new JTextField();
		goodRemarksModify.setBounds(56, 106, 202, 21);
		panel_1.add(goodRemarksModify);

		goodModifyBtn = new JButton();
		goodModifyBtn.setText("修改");
		goodModifyBtn.setBounds(194, 24, 67, 23);
		panel_1.add(goodModifyBtn);
		
		goodNumberTF = new JTextField();
		goodNumberTF.setBounds(57, 77, 71, 21);
		panel_1.add(goodNumberTF);
		
		label_13 = new JLabel();
		label_13.setText("数量：");
		label_13.setBounds(11, 80, 42, 15);
		panel_1.add(label_13);

		scrollPane_3 = new JScrollPane();
		scrollPane_3.setBounds(616, 10, 283, 268);
		contentPane.add(scrollPane_3);

		JPanel panel_2 = new JPanel();
		panel_2.setBorder(new TitledBorder(null,
				"\u5BA2\u6237\u8D27\u7269\u4FEE\u6539", TitledBorder.LEADING,
				TitledBorder.TOP, null, null));
		panel_2.setBounds(616, 288, 288, 149);
		contentPane.add(panel_2);
		panel_2.setLayout(null);

		JLabel label_10 = new JLabel();
		label_10.setText("型 号：");
		label_10.setBounds(10, 59, 42, 15);
		panel_2.add(label_10);

		serialNumberModify = new JTextField();
		serialNumberModify.setBounds(56, 56, 122, 21);
		panel_2.add(serialNumberModify);

		JLabel label_11 = new JLabel();
		label_11.setText("售 价：");
		label_11.setBounds(10, 86, 42, 15);
		panel_2.add(label_11);

		sellingPriceModify = new JTextField();
		sellingPriceModify.setBounds(56, 83, 122, 21);
		panel_2.add(sellingPriceModify);

		JLabel label_12 = new JLabel();
		label_12.setText("备注：");
		label_12.setBounds(10, 113, 40, 15);
		panel_2.add(label_12);

		remarksModify = new JTextField();
		remarksModify.setText(" ");
		remarksModify.setBounds(56, 110, 133, 21);
		panel_2.add(remarksModify);

		curtainShopModify = new JLabel();
		curtainShopModify.setText(" ");
		curtainShopModify.setBounds(10, 21, 210, 25);
		panel_2.add(curtainShopModify);

		modifyBtn = new JButton();
		modifyBtn.setText("修改");
		modifyBtn.setBounds(199, 109, 67, 23);
		panel_2.add(modifyBtn);

		JPanel panel_3 = new JPanel();
		panel_3.setBorder(new TitledBorder(null,
				"\u5BA2\u6237\u4FE1\u606F\u4FEE\u6539", TitledBorder.LEADING,
				TitledBorder.TOP, null, null));
		panel_3.setBounds(614, 436, 285, 130);
		contentPane.add(panel_3);
		panel_3.setLayout(null);

		JLabel label_14 = new JLabel();
		label_14.setText("姓 名：");
		label_14.setBounds(10, 27, 42, 15);
		panel_3.add(label_14);

		curtainShopNameModify = new JTextField();
		curtainShopNameModify.setBounds(56, 24, 115, 21);
		panel_3.add(curtainShopNameModify);

		JLabel label_15 = new JLabel();
		label_15.setText("电 话：");
		label_15.setBounds(10, 54, 42, 15);
		panel_3.add(label_15);

		curtainShopTelModify = new JTextField();
		curtainShopTelModify.setBounds(56, 51, 115, 21);
		panel_3.add(curtainShopTelModify);

		JLabel label_16 = new JLabel();
		label_16.setText("所 属：");
		label_16.setBounds(10, 81, 42, 15);
		panel_3.add(label_16);

		curtainShopOwner = new JComboBox();
		curtainShopOwner.setBounds(62, 78, 62, 21);
		panel_3.add(curtainShopOwner);

		curtainShopModifyBtn = new JButton();
		curtainShopModifyBtn.setText("修改");
		curtainShopModifyBtn.setBounds(189, 78, 67, 23);
		panel_3.add(curtainShopModifyBtn);
	}

	private void initCurtainShop() {
		curtainShopLst = (ArrayList<CurtainShop>) DBUtil.getLstClass("name",
				"", CurtainShop.class, "");
		curtainShopjList = new JList();
		UIutil.initCurtainShop(this, curtainShopjList, curtainShopLst);
		scrollPane.setViewportView(curtainShopjList);
	}

	private void initGoods() {
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
	}

	private void initTable() {
		String[] columnNames = { "编号", "卖价", "数量", "备注" }; // 列名
		String[][] tableVales = {}; // 数据
		tableModel = new DefaultTableModel(tableVales, columnNames);
		table = new JTable(tableModel);
		// 设置列宽
		setTableColumn(table, 1, 30, 2, 30, 3, 70);
		TableColumn column1 = table.getColumnModel().getColumn(1);
		column1.setPreferredWidth(30);
		column1.setMaxWidth(30);
		column1.setMinWidth(30);
		TableColumn column2 = table.getColumnModel().getColumn(2);
		column2.setPreferredWidth(30);
		column2.setMaxWidth(30);
		column2.setMinWidth(30);
		TableColumn column3 = table.getColumnModel().getColumn(3);
		column3.setPreferredWidth(70);
		column3.setMaxWidth(70);
		column3.setMinWidth(70);
		// 增加点击事件
		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				rowClicked();
			}
		});
		scrollPane_2.setViewportView(table);

		// 重置goodOwnerTable
		String[] goodOwnerCN = { "客户", "卖价", "备注" }; // 列名
		String[][] goodOwnerTV = {}; // 数据
		goodOwnertableModel = new DefaultTableModel(goodOwnerTV, goodOwnerCN);
		goodOwnerTable = new JTable(goodOwnertableModel);
		setTableColumn(goodOwnerTable, 0, 160);
		scrollPane_3.setViewportView(goodOwnerTable);
	}

	@SuppressWarnings("unchecked")
	@Override
	public void valueChanged(ListSelectionEvent e) {
		// TODO Auto-generated method stub
		if (e.getValueIsAdjusting()) {
			if (curtainShopjList.getSelectedIndex() != curtainShopLstIndex) {
				curtainShopIndex = curtainShopjList.getSelectedIndex();
				curtainShopLstIndex = curtainShopIndex;
				curtainShop = curtainShopLst.get(curtainShopIndex);
				String sql = "select new CurtainShopGoods(g.id,g.serialNumber,g.sellingPrice,g.curtainShop,g.remarks) from CurtainShopGoods g where curtainShop = :name order by serialNumber";
				curtainShopGoodLst = (List<CurtainShopGoods>) DBUtil
						.getClassLst(sql, curtainShop.getName());
				tableAddLine(curtainShopGoodLst);
				// 加载修改 客户信息
				curtainShopNameModify.setText(curtainShop.getName());
				curtainShopTelModify.setText(curtainShop.getTelephone());
				curtainShopOwner.setModel(new javax.swing.DefaultComboBoxModel(
						new String[] { "张其伟", "刘会珍" }));
				curtainShopOwner.setSelectedIndex(curtainShop.getOwner());

			} else {// goodjList
				int index = goodjList.getSelectedIndex();
				goodsLstIndex = index;
				goods = goodsLst.get(index);
				purchasePrice.setText(goods.getPurchasePrice() + "");
				purchasePrice.setEditable(false);
				serialNumber.setText(goods.getSerialNumber());
				serialNumber.setEditable(false);
				factory.setText(goods.getFactory());
				factory.setEditable(false);
				telephone.setText(goods.getTelephone());
				telephone.setEditable(false);
				sellingPrice.setText("");
				// 加载修改 货物信息
				goodSerialNumberModify.setText(goods.getSerialNumber());
				goodPurchasePriceModify.setText(goods.getPurchasePrice() + "");
				goodRemarksModify.setText(goods.getRemark());
				goodNumberTF.setText(goods.getNumber());
				// 加载同时拥有此货物的客户
				List<CurtainShopGoods> Lst = (List<CurtainShopGoods>) DBUtil
						.getLstClass("curtainShop", "eq",
								CurtainShopGoods.class, "serialNumber",
								goods.getSerialNumber(), "String");
				for (int i = 0; i < goodOwnertableModel.getRowCount();) {
					goodOwnertableModel.removeRow(0);
				}
				for (int i = 0; i < Lst.size(); i++) {
					String[] rowValues = { Lst.get(i).getCurtainShop(),
							Lst.get(i).getSellingPrice() + "" };
					goodOwnertableModel.addRow(rowValues);
				}

			}
		}
	}

	private void tableAddLine(List<CurtainShopGoods> Lst) {
		for (int i = 0; i < tableModel.getRowCount();) {
			tableModel.removeRow(0);
		}
		for (int i = 0; i < Lst.size(); i++) {
			tableAddLine(Lst.get(i));
		}
	}

	private void tableAddLine(CurtainShopGoods g) {
		String[] rowValues = { g.getSerialNumber(), g.getSellingPrice() + "",
				g.getNumber() + "", g.getRemarks() };
		tableModel.addRow(rowValues); // 添加一行
	}

	private void goodModifyBtnActionPerformed(java.awt.event.ActionEvent evt) {
		String serialNumber = goodSerialNumberModify.getText().trim();
		String purchasePrice = goodPurchasePriceModify.getText().trim();
		String remark = goodRemarksModify.getText().trim();
		String number = goodNumberTF.getText().trim();
		Goods goods = goodsLst.get(goodsLstIndex);
		if (serialNumber.length() > 0
				&& !goods.getSerialNumber().equals(serialNumber)) {
			String hgl = "update CurtainShopGoods csg set csg.serialNumber=? WHERE csg.serialNumber=?";
			DBUtil.updateField(hgl, serialNumber, goods.getSerialNumber());
			String sglOrderGood = "update OrderGoods og set og.serialNumber=? WHERE og.serialNumber=?";
			DBUtil.updateField(sglOrderGood, serialNumber,
					goods.getSerialNumber());
		}
		goods.setSerialNumber(serialNumber);
		goods.setPurchasePrice(Double.parseDouble(purchasePrice));
		goods.setRemark(remark);
		goods.setNumber(number);
		DBUtil.update(goods);
	}

	private void curtainShopModifyBtnActionPerformed(
			java.awt.event.ActionEvent evt) {
		CurtainShop curtainShop = curtainShopLst.get(curtainShopIndex);
		String curtainShopName = curtainShopNameModify.getText();
		String curtainShopTel = curtainShopTelModify.getText();
		int owner = curtainShopOwner.getSelectedIndex();
		if (curtainShopName.length() > 0
				&& !curtainShop.getName().equals(curtainShopName)) {
			String hgl = "update CurtainShopGoods csg set csg.curtainShop=? WHERE csg.curtainShop=?";
			DBUtil.updateField(hgl, curtainShopName, curtainShop.getName());
		}
		curtainShop.setTelephone(curtainShopTel);
		curtainShop.setName(curtainShopName);
		curtainShop.setOwner(owner);
		DBUtil.update(curtainShop);
	}

	private void modifyBtnActionPerformed(java.awt.event.ActionEvent evt) {
		String sellingPrice = sellingPriceModify.getText().trim();
		String remarks = remarksModify.getText().trim();
		CurtainShopGoods csg = curtainShopGoodLst.get(curtainShopGoodIndex);
		csg.setSellingPrice(Double.parseDouble(sellingPrice));
		csg.setRemarks(remarks);
		DBUtil.update(csg);
		tableAddLine(curtainShopGoodLst);
	}

	private void addBtnActionPerformed(java.awt.event.ActionEvent evt) {
		if (sellingPrice.getText().length() > 0) {
			CurtainShopGoods csg = new CurtainShopGoods(serialNumber.getText(),
					Double.parseDouble(sellingPrice.getText().trim()),
					curtainShop.getName());
			String sql = "select g.serialNumber from CurtainShopGoods as g where g.serialNumber=:name0 and  g.curtainShop=:name1";
			if (DBUtil.get(sql, csg.getSerialNumber(), csg.getCurtainShop()) == null) {
				tableAddLine(csg);
				DBUtil.insert(csg);
			} else {
				JOptionPane.showMessageDialog(this, "此货物已存在", "alert",
						JOptionPane.ERROR_MESSAGE);
			}
		} else {
			JOptionPane.showMessageDialog(this, "请输入价格", "alert",
					JOptionPane.ERROR_MESSAGE);
		}
	}

	private void goodjListBtnAddActionPerformed(java.awt.event.ActionEvent evt) {
		// TODO add your handling code here:
		
		AddGood dialog = new AddGood(new JFrame(),
				true);
		dialog.addWindowListener(new java.awt.event.WindowAdapter() {
			public void windowClosing(java.awt.event.WindowEvent e) {
				// dialog.dispose();
			}
		});
		dialog.setVisible(true);
		dialog.setLocationRelativeTo(null);
		String sql = "select g.serialNumber from Goods as g where g.serialNumber=:name0";
		if (Global.goods != null && Global.goods.getSerialNumber() != null) {
			if (DBUtil.get(sql, Global.goods.getSerialNumber(), "") == null) {
				DBUtil.insert(Global.goods);
				Goods goods = new Goods(Global.goods.getSerialNumber(),
						Global.goods.getPurchasePrice(),
						Global.goods.getFactory(), Global.goods.getTelephone(),
						Global.goods.getDistance());
				goodsLst.add(goods);
				initGoods();
			}
		}
	}

	private void curtainShopBtnAddActionPerformed(java.awt.event.ActionEvent evt) {
		AddCurtainShop dialog = new AddCurtainShop(new javax.swing.JFrame(),
				true);
		dialog.addWindowListener(new java.awt.event.WindowAdapter() {
			public void windowClosing(java.awt.event.WindowEvent e) {
				// System.exit(0);
			}
		});
		dialog.setVisible(true);
		dialog.setLocationRelativeTo(null);
		
		
		
		String sql = "select c.name from CurtainShop as c where c.name=:name0";
		if (Global.curtainShop != null && Global.curtainShop.getName() != null) {
			if (DBUtil.get(sql, Global.curtainShop.getName(), "") == null) {
				DBUtil.insert(Global.curtainShop);
				CurtainShop cs = new CurtainShop(
						Global.curtainShop.getAddress(),
						Global.curtainShop.getTelephone(),
						Global.curtainShop.getName(),
						Global.curtainShop.getOwner());
				curtainShopLst.add(cs);
				initCurtainShop();
			}

		}
	}

	/**
	 * 
	 * @param table
	 * @param arg
	 *            {列id，要设置的宽度}
	 */
	private void setTableColumn(JTable table, int... arg) {
		for (int i = 0; i < arg.length; i += 2) {
			TableColumn column1 = table.getColumnModel().getColumn(arg[i]);
			column1.setPreferredWidth(arg[i + 1]);
			column1.setMaxWidth(arg[i + 1]);
			column1.setMinWidth(arg[i + 1]);
		}
	}

	protected void rowClicked() {
		curtainShopGoodIndex = table.getSelectedRow();
		// 初始化客户货物信息
		CurtainShopGoods csg = curtainShopGoodLst.get(curtainShopGoodIndex);
		curtainShopModify.setText(csg.getCurtainShop());
		serialNumberModify.setText(csg.getSerialNumber());
		serialNumberModify.setEditable(false);
		sellingPriceModify.setText(csg.getSellingPrice() + "");
		remarksModify.setText(csg.getRemarks());
	}

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MainAddWi frame = new MainAddWi();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	private JPanel contentPane;
	private JTextField serialNumber;
	private JTextField factory;
	private JTextField sellingPrice;
	private JTextField purchasePrice;
	private JTextField telephone;
	private JTable table;
	private JTextField goodSerialNumberModify;
	private JTextField goodPurchasePriceModify;
	private JTextField goodRemarksModify;
	private JTable goodOwnerTable;
	private JTextField serialNumberModify;
	private JTextField sellingPriceModify;
	private JTextField remarksModify;
	private JTextField curtainShopNameModify;
	private JTextField curtainShopTelModify;
	private int goodsLstIndex = -1;
	private int curtainShopLstIndex = -1;
	private int curtainShopGoodIndex = -1;
	private int curtainShopIndex;
	private int state = 0;
	private CurtainShop curtainShop = null;
	private Goods goods = null;
	// End of variables declaration//GEN-END:variables
	private ArrayList<CurtainShop> curtainShopLst = new ArrayList<CurtainShop>();
	private ArrayList<Goods> goodsLst = new ArrayList<Goods>();
	private List<CurtainShopGoods> curtainShopGoodLst = null;
	private javax.swing.JList curtainShopjList;
	private javax.swing.JList goodjList;
	private JScrollPane scrollPane_2;
	private JScrollPane scrollPane;
	private JScrollPane scrollPane_1;
	private JComboBox curtainShopOwner;
	private DefaultTableModel goodOwnertableModel;
	private DefaultTableModel tableModel;
	private JLabel curtainShopModify;
	private JScrollPane scrollPane_3;
	private JButton curtainShopBtnAdd;
	private JButton curtainShopBtnDel;
	private JButton goodjListBtnAdd;
	private JButton goodjListBtnDel;
	private JButton goodModifyBtn;
	private JButton addBtn;
	private JButton delBtn;
	private JButton curtainShopModifyBtn;
	private JButton modifyBtn;
	private JTextField goodNumberTF;
	private JLabel label_13;
}
