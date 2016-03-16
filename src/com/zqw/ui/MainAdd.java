/*
 * main.java
 *
 * Created on __DATE__, __TIME__
 */

package com.zqw.ui;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.List;

import javax.swing.GroupLayout;
import javax.swing.JDialog;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;

import com.zqw.bean.CurtainShop;
import com.zqw.bean.CurtainShopGoods;
import com.zqw.bean.Global;
import com.zqw.bean.Goods;
import com.zqw.util.DBUtil;

/**
 * 
 * @author __USER__
 */
public class MainAdd extends JDialog implements ListSelectionListener {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private DefaultTableModel goodOwnertableModel;

	/** Creates new form main */
	public MainAdd() {
		initComponents();
		getData();
		initCurtainShop();
		initGoods();
		initTable();
	}

	public int getReturnStatus() {
		return state;
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
		jScrollPane3.setViewportView(table);

		// 重置goodOwnerTable
		String[] goodOwnerCN = { "客户", "卖价", "备注" }; // 列名
		String[][] goodOwnerTV = {}; // 数据
		goodOwnertableModel = new DefaultTableModel(goodOwnerTV, goodOwnerCN);
		goodOwnerTable = new JTable(goodOwnertableModel);
		setTableColumn(goodOwnerTable,0,160);
		scrollPane.setViewportView(goodOwnerTable);
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

	// GEN-BEGIN:initComponents
	// <editor-fold defaultstate="collapsed" desc="Generated Code">
	private void initComponents() {

		jPanel1 = new javax.swing.JPanel();
		curtainShopBtnAdd = new javax.swing.JButton();
		curtainShopBtnAdd.setBounds(20, 529, 62, 23);
		curtainShopBtnDel = new javax.swing.JButton();
		curtainShopBtnDel.setBounds(88, 529, 62, 23);
		jScrollPane1 = new javax.swing.JScrollPane();
		jScrollPane1.setBounds(10, 25, 157, 494);
		jLabel1 = new javax.swing.JLabel();
		jLabel1.setBounds(78, 0, 36, 19);
		jPanel3 = new javax.swing.JPanel();
		jPanel4 = new javax.swing.JPanel();
		jPanel4.setBounds(0, 0, 278, 411);
		factory = new javax.swing.JTextField();
		factory.setBounds(177, 17, 66, 21);
		sellingPrice = new javax.swing.JTextField();
		sellingPrice.setBounds(177, 44, 66, 21);
		telephone = new javax.swing.JTextField();
		telephone.setBounds(61, 71, 124, 21);
		jLabel7 = new javax.swing.JLabel();
		jLabel7.setBounds(15, 74, 42, 15);
		jLabel4 = new javax.swing.JLabel();
		jLabel4.setBounds(15, 47, 42, 15);
		jLabel3 = new javax.swing.JLabel();
		jLabel3.setBounds(15, 20, 42, 15);
		serialNumber = new javax.swing.JTextField();
		serialNumber.setBounds(61, 17, 66, 21);
		purchasePrice = new javax.swing.JTextField();
		purchasePrice.setBounds(61, 44, 66, 21);
		jLabel5 = new javax.swing.JLabel();
		jLabel5.setBounds(131, 20, 42, 15);
		jLabel6 = new javax.swing.JLabel();
		jLabel6.setBounds(131, 47, 42, 15);
		jPanel6 = new javax.swing.JPanel();
		jPanel6.setBounds(287, 437, 289, 115);
		curtainShopNameModify = new javax.swing.JTextField();
		curtainShopTelModify = new javax.swing.JTextField();
		jLabel11 = new javax.swing.JLabel();
		jLabel12 = new javax.swing.JLabel();
		jLabel13 = new javax.swing.JLabel();
		curtainShopOwner = new javax.swing.JComboBox();
		jPanel7 = new javax.swing.JPanel();
		jPanel7.setBounds(0, 421, 278, 131);
		goodSerialNumberModify = new javax.swing.JTextField();
		jLabel14 = new javax.swing.JLabel();
		goodPurchasePriceModify = new javax.swing.JTextField();
		jLabel15 = new javax.swing.JLabel();
		jLabel16 = new javax.swing.JLabel();
		goodRemarksModify = new javax.swing.JTextField();
		jPanel5 = new javax.swing.JPanel();
		jPanel5.setBounds(287, 288, 286, 139);
		serialNumberModify = new javax.swing.JTextField();
		serialNumberModify.setBounds(61, 17, 122, 21);
		jLabel8 = new javax.swing.JLabel();
		jLabel8.setBounds(15, 20, 42, 15);
		jLabel9 = new javax.swing.JLabel();
		jLabel9.setBounds(15, 47, 42, 15);
		sellingPriceModify = new javax.swing.JTextField();
		sellingPriceModify.setBounds(61, 44, 122, 21);
		jLabel10 = new javax.swing.JLabel();
		jLabel10.setBounds(15, 74, 40, 15);
		remarksModify = new javax.swing.JTextField();
		remarksModify.setBounds(61, 71, 210, 21);
		curtainShopModify = new javax.swing.JLabel();
		curtainShopModify.setBounds(187, 27, 84, 30);

		setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

		curtainShopBtnAdd.setText("\u6dfb\u52a0");
		curtainShopBtnAdd
				.addActionListener(new java.awt.event.ActionListener() {
					public void actionPerformed(java.awt.event.ActionEvent evt) {
						curtainShopBtnAddActionPerformed(evt);
					}
				});

		curtainShopBtnDel.setText("\u5220\u9664");
		curtainShopBtnDel
				.addActionListener(new java.awt.event.ActionListener() {
					public void actionPerformed(java.awt.event.ActionEvent evt) {
						curtainShopBtnDelActionPerformed(evt);
					}
				});

		jLabel1.setFont(new java.awt.Font("微软雅黑", 0, 14));
		jLabel1.setText("\u5e97  \u540d");
		jScrollPane2 = new javax.swing.JScrollPane();
		jScrollPane2.setBounds(177, 25, 133, 494);
		jScrollPane2.setViewportView(goodjList);
		jLabel2 = new javax.swing.JLabel();
		jLabel2.setBounds(232, 0, 32, 19);

		jLabel2.setFont(new java.awt.Font("微软雅黑", 0, 14));
		jLabel2.setText("\u8d27 \u7269");

		jPanel4.setBorder(javax.swing.BorderFactory.createTitledBorder(
				javax.swing.BorderFactory.createLineBorder(new java.awt.Color(
						0, 0, 0)), "\u8d27\u7269\u6dfb\u52a0"));

		jLabel7.setText("\u7535 \u8bdd\uff1a");

		jLabel4.setText("\u8fdb \u4ef7\uff1a");

		jLabel3.setText("\u578b \u53f7\uff1a");

		jLabel5.setText("\u8d27 \u6e90\uff1a");

		jLabel6.setText("\u552e \u4ef7\uff1a");
		jScrollPane3 = new javax.swing.JScrollPane();
		jScrollPane3.setBounds(5, 102, 268, 266);

		jPanel6.setBorder(javax.swing.BorderFactory.createTitledBorder(
				javax.swing.BorderFactory.createLineBorder(new java.awt.Color(
						0, 0, 0)), "\u5ba2\u6237\u4fe1\u606f\u4fee\u6539"));

		jLabel11.setText("\u7535 \u8bdd\uff1a");

		jLabel12.setText("\u59d3 \u540d\uff1a");

		jLabel13.setText("\u6240 \u5c5e\uff1a");

		curtainShopOwner.setModel(new javax.swing.DefaultComboBoxModel(
				new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
		curtainShopModifyBtn = new javax.swing.JButton();

		curtainShopModifyBtn.setText("\u4fee\u6539");
		curtainShopModifyBtn
				.addActionListener(new java.awt.event.ActionListener() {
					public void actionPerformed(java.awt.event.ActionEvent evt) {
						curtainShopModifyBtnActionPerformed(evt);
					}
				});

		javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(
				jPanel6);
		jPanel6Layout
				.setHorizontalGroup(jPanel6Layout
						.createParallelGroup(Alignment.LEADING)
						.addGroup(
								jPanel6Layout
										.createSequentialGroup()
										.addContainerGap()
										.addGroup(
												jPanel6Layout
														.createParallelGroup(
																Alignment.LEADING)
														.addGroup(
																jPanel6Layout
																		.createSequentialGroup()
																		.addComponent(
																				jLabel11)
																		.addPreferredGap(
																				ComponentPlacement.RELATED)
																		.addComponent(
																				curtainShopTelModify,
																				GroupLayout.DEFAULT_SIZE,
																				115,
																				Short.MAX_VALUE))
														.addGroup(
																jPanel6Layout
																		.createSequentialGroup()
																		.addComponent(
																				jLabel12)
																		.addPreferredGap(
																				ComponentPlacement.RELATED)
																		.addComponent(
																				curtainShopNameModify,
																				GroupLayout.PREFERRED_SIZE,
																				115,
																				GroupLayout.PREFERRED_SIZE))
														.addGroup(
																jPanel6Layout
																		.createSequentialGroup()
																		.addComponent(
																				jLabel13)
																		.addPreferredGap(
																				ComponentPlacement.UNRELATED)
																		.addComponent(
																				curtainShopOwner,
																				GroupLayout.PREFERRED_SIZE,
																				GroupLayout.DEFAULT_SIZE,
																				GroupLayout.PREFERRED_SIZE)))
										.addGap(23)
										.addComponent(curtainShopModifyBtn,
												GroupLayout.PREFERRED_SIZE, 67,
												GroupLayout.PREFERRED_SIZE)
										.addGap(18)));
		jPanel6Layout
				.setVerticalGroup(jPanel6Layout
						.createParallelGroup(Alignment.LEADING)
						.addGroup(
								jPanel6Layout
										.createSequentialGroup()
										.addGroup(
												jPanel6Layout
														.createParallelGroup(
																Alignment.BASELINE)
														.addComponent(jLabel12)
														.addComponent(
																curtainShopNameModify,
																GroupLayout.PREFERRED_SIZE,
																GroupLayout.DEFAULT_SIZE,
																GroupLayout.PREFERRED_SIZE))
										.addPreferredGap(
												ComponentPlacement.RELATED)
										.addGroup(
												jPanel6Layout
														.createParallelGroup(
																Alignment.BASELINE)
														.addComponent(jLabel11)
														.addComponent(
																curtainShopTelModify,
																GroupLayout.PREFERRED_SIZE,
																GroupLayout.DEFAULT_SIZE,
																GroupLayout.PREFERRED_SIZE))
										.addPreferredGap(
												ComponentPlacement.RELATED)
										.addGroup(
												jPanel6Layout
														.createParallelGroup(
																Alignment.BASELINE)
														.addComponent(jLabel13)
														.addComponent(
																curtainShopOwner,
																GroupLayout.PREFERRED_SIZE,
																GroupLayout.DEFAULT_SIZE,
																GroupLayout.PREFERRED_SIZE)
														.addComponent(
																curtainShopModifyBtn))
										.addContainerGap(
												GroupLayout.DEFAULT_SIZE,
												Short.MAX_VALUE)));
		jPanel6.setLayout(jPanel6Layout);

		jPanel7.setBorder(javax.swing.BorderFactory.createTitledBorder(
				javax.swing.BorderFactory.createLineBorder(new java.awt.Color(
						0, 0, 0)), "\u8d27\u7269\u8d27\u7269\u4fee\u6539"));

		jLabel14.setText("\u578b \u53f7\uff1a");

		jLabel15.setText("\u8fdb \u4ef7\uff1a");

		jLabel16.setText("\u5907 \u6ce8\uff1a");
		goodModifyBtn = new javax.swing.JButton();

		goodModifyBtn.setText("\u4fee\u6539");
		goodModifyBtn.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				goodModifyBtnActionPerformed(evt);
			}
		});

		javax.swing.GroupLayout jPanel7Layout = new javax.swing.GroupLayout(
				jPanel7);
		jPanel7Layout
				.setHorizontalGroup(jPanel7Layout
						.createParallelGroup(Alignment.LEADING)
						.addGroup(
								jPanel7Layout
										.createSequentialGroup()
										.addContainerGap()
										.addGroup(
												jPanel7Layout
														.createParallelGroup(
																Alignment.LEADING)
														.addGroup(
																jPanel7Layout
																		.createSequentialGroup()
																		.addGroup(
																				jPanel7Layout
																						.createParallelGroup(
																								Alignment.LEADING)
																						.addGroup(
																								jPanel7Layout
																										.createSequentialGroup()
																										.addComponent(
																												jLabel16)
																										.addPreferredGap(
																												ComponentPlacement.RELATED)
																										.addComponent(
																												goodRemarksModify,
																												GroupLayout.DEFAULT_SIZE,
																												182,
																												Short.MAX_VALUE))
																						.addGroup(
																								jPanel7Layout
																										.createParallelGroup(
																												Alignment.LEADING,
																												false)
																										.addGroup(
																												jPanel7Layout
																														.createSequentialGroup()
																														.addComponent(
																																jLabel15)
																														.addPreferredGap(
																																ComponentPlacement.RELATED)
																														.addComponent(
																																goodPurchasePriceModify))
																										.addGroup(
																												jPanel7Layout
																														.createSequentialGroup()
																														.addComponent(
																																jLabel14)
																														.addPreferredGap(
																																ComponentPlacement.RELATED)
																														.addComponent(
																																goodSerialNumberModify,
																																GroupLayout.PREFERRED_SIZE,
																																119,
																																GroupLayout.PREFERRED_SIZE))))
																		.addContainerGap())
														.addGroup(
																Alignment.TRAILING,
																jPanel7Layout
																		.createSequentialGroup()
																		.addComponent(
																				goodModifyBtn,
																				GroupLayout.PREFERRED_SIZE,
																				67,
																				GroupLayout.PREFERRED_SIZE)
																		.addGap(89)))));
		jPanel7Layout
				.setVerticalGroup(jPanel7Layout
						.createParallelGroup(Alignment.LEADING)
						.addGroup(
								jPanel7Layout
										.createSequentialGroup()
										.addGroup(
												jPanel7Layout
														.createParallelGroup(
																Alignment.BASELINE)
														.addComponent(jLabel14)
														.addComponent(
																goodSerialNumberModify,
																GroupLayout.PREFERRED_SIZE,
																GroupLayout.DEFAULT_SIZE,
																GroupLayout.PREFERRED_SIZE))
										.addPreferredGap(
												ComponentPlacement.RELATED)
										.addGroup(
												jPanel7Layout
														.createParallelGroup(
																Alignment.BASELINE)
														.addComponent(jLabel15)
														.addComponent(
																goodPurchasePriceModify,
																GroupLayout.PREFERRED_SIZE,
																GroupLayout.DEFAULT_SIZE,
																GroupLayout.PREFERRED_SIZE))
										.addPreferredGap(
												ComponentPlacement.RELATED)
										.addGroup(
												jPanel7Layout
														.createParallelGroup(
																Alignment.BASELINE)
														.addComponent(jLabel16)
														.addComponent(
																goodRemarksModify,
																GroupLayout.PREFERRED_SIZE,
																GroupLayout.DEFAULT_SIZE,
																GroupLayout.PREFERRED_SIZE))
										.addPreferredGap(
												ComponentPlacement.RELATED)
										.addComponent(goodModifyBtn)
										.addContainerGap(16, Short.MAX_VALUE)));
		jPanel7.setLayout(jPanel7Layout);

		jPanel5.setBorder(javax.swing.BorderFactory.createTitledBorder(
				javax.swing.BorderFactory.createLineBorder(new java.awt.Color(
						0, 0, 0)), "\u5ba2\u6237\u8d27\u7269\u4fee\u6539"));

		jLabel8.setText("\u578b \u53f7\uff1a");

		jLabel9.setText("\u552e \u4ef7\uff1a");

		jLabel10.setText("\u5907\u6ce8\uff1a");

		remarksModify.setText(" ");

		curtainShopModify.setText(" ");

		jPanel5.getAccessibleContext().setAccessibleName(
				"\u5ba2\u6237\u8d27\u7269\u4fee\u6539");

		javax.swing.GroupLayout layout = new javax.swing.GroupLayout(
				getContentPane());
		layout.setHorizontalGroup(layout.createParallelGroup(Alignment.LEADING)
				.addGroup(
						Alignment.TRAILING,
						layout.createSequentialGroup()
								.addComponent(jPanel1,
										GroupLayout.PREFERRED_SIZE, 320,
										GroupLayout.PREFERRED_SIZE)
								.addPreferredGap(ComponentPlacement.RELATED,
										GroupLayout.DEFAULT_SIZE,
										Short.MAX_VALUE)
								.addComponent(jPanel3,
										GroupLayout.PREFERRED_SIZE, 583,
										GroupLayout.PREFERRED_SIZE)
								.addContainerGap()));
		layout.setVerticalGroup(layout
				.createParallelGroup(Alignment.LEADING)
				.addGroup(
						Alignment.TRAILING,
						layout.createSequentialGroup()
								.addContainerGap()
								.addGroup(
										layout.createParallelGroup(
												Alignment.TRAILING)
												.addComponent(
														jPanel1,
														Alignment.LEADING,
														GroupLayout.DEFAULT_SIZE,
														562, Short.MAX_VALUE)
												.addComponent(
														jPanel3,
														Alignment.LEADING,
														GroupLayout.DEFAULT_SIZE,
														562, Short.MAX_VALUE))
								.addContainerGap()));
		jPanel1.setLayout(null);
		jPanel1.add(jLabel1);
		jPanel1.add(jLabel2);
		jPanel1.add(jScrollPane1);
		jPanel1.add(jScrollPane2);
		jPanel1.add(curtainShopBtnAdd);
		jPanel1.add(curtainShopBtnDel);
		goodjListBtnAdd = new javax.swing.JButton();
		goodjListBtnAdd.setBounds(176, 529, 62, 23);
		jPanel1.add(goodjListBtnAdd);

		goodjListBtnAdd.setText("\u6dfb\u52a0");
		goodjListBtnDel = new javax.swing.JButton();
		goodjListBtnDel.setBounds(250, 529, 62, 23);
		jPanel1.add(goodjListBtnDel);

		goodjListBtnDel.setText("\u5220\u9664");
		goodjListBtnDel.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				goodjListBtnDelActionPerformed(evt);
			}
		});
		goodjListBtnAdd.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				goodjListBtnAddActionPerformed(evt);
			}
		});
		getContentPane().setLayout(layout);
		jPanel3.setLayout(null);
		jPanel3.add(jPanel4);
		jPanel4.setLayout(null);
		jPanel4.add(jLabel7);
		jPanel4.add(telephone);
		jPanel4.add(jLabel3);
		jPanel4.add(serialNumber);
		jPanel4.add(jLabel4);
		jPanel4.add(purchasePrice);
		jPanel4.add(jLabel5);
		jPanel4.add(factory);
		jPanel4.add(jLabel6);
		jPanel4.add(sellingPrice);
		jPanel4.add(jScrollPane3);
		addBtn = new javax.swing.JButton();
		addBtn.setBounds(61, 378, 67, 23);
		jPanel4.add(addBtn);

		addBtn.setText("\u6dfb\u52a0");
		delBtn = new javax.swing.JButton();
		delBtn.setBounds(138, 378, 67, 23);
		jPanel4.add(delBtn);

		delBtn.setText("\u5220\u9664");
		delBtn.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				delBtnActionPerformed(evt);
			}
		});
		addBtn.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				addBtnActionPerformed(evt);
			}
		});
		jPanel3.add(jPanel6);
		jPanel3.add(jPanel5);
		jPanel5.setLayout(null);
		jPanel5.add(jLabel8);
		jPanel5.add(serialNumberModify);
		jPanel5.add(jLabel9);
		jPanel5.add(sellingPriceModify);
		jPanel5.add(curtainShopModify);
		jPanel5.add(jLabel10);
		jPanel5.add(remarksModify);
		modifyBtn = new javax.swing.JButton();
		modifyBtn.setBounds(110, 102, 67, 23);
		jPanel5.add(modifyBtn);

		modifyBtn.setText("\u4fee\u6539");
		modifyBtn.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				modifyBtnActionPerformed(evt);
			}
		});
		jPanel3.add(jPanel7);

		scrollPane = new JScrollPane();
		scrollPane.setBounds(288, 10, 288, 268);
		jPanel3.add(scrollPane);

		pack();
	}// </editor-fold>

	private void initCurtainShop() {
		String[] Lst = new String[curtainShopLst.size()];
		for (int i = 0; i < curtainShopLst.size(); i++) {
			Lst[i] = curtainShopLst.get(i).getName();
		}
		curtainShopjList = new JList(Lst);
		curtainShopjList.addListSelectionListener(this);
		curtainShopjList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

		jScrollPane1.setViewportView(curtainShopjList);
	}

	private void initGoods() {
		String[] Lst = new String[goodsLst.size()];
		for (int i = 0; i < goodsLst.size(); i++) {
			Lst[i] = goodsLst.get(i).getSerialNumber();
		}
		goodjList = new JList(Lst);
		goodjList.addListSelectionListener(this);
		goodjList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		jScrollPane2.setViewportView(goodjList);
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

	@SuppressWarnings("unchecked")
	private void getData() {
		String hqlCurtainShop = "select new CurtainShop(cs.id,cs.name,cs.telephone,cs.address,cs.owner) from CurtainShop cs order by name";
		curtainShopLst = (ArrayList<CurtainShop>) DBUtil.getClassLst(
				hqlCurtainShop, "");

		// String sql="from Goods";
		String hqlGoods = "select new Goods(g.id,g.serialNumber,g.purchasePrice,g.factory,g.telephone,g.bankCard,g.remark) from Goods g order by serialNumber";
		goodsLst = (ArrayList<Goods>) DBUtil.getClassLst(hqlGoods, "");
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

	private void delBtnActionPerformed(java.awt.event.ActionEvent evt) {
		// TODO add your handling code here:

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

	private void curtainShopBtnDelActionPerformed(java.awt.event.ActionEvent evt) {
		// TODO add your handling code here:
	}

	private void goodjListBtnAddActionPerformed(java.awt.event.ActionEvent evt) {
		// TODO add your handling code here:
		AddGoodsShop dialog = new AddGoodsShop(new javax.swing.JFrame(), true);
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

	private void goodjListBtnDelActionPerformed(java.awt.event.ActionEvent evt) {
		// TODO add your handling code here:
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

	// TODO add your handling code here:

	/**
	 * @param args
	 *            the command line arguments
	 */
	public static void main(String args[]) {
		java.awt.EventQueue.invokeLater(new Runnable() {
			public void run() {
				MainAdd mainAdd = new MainAdd();
				mainAdd.setVisible(true);
				mainAdd.setLocationRelativeTo(null);
			}
		});
	}

	// GEN-BEGIN:variables
	// Variables declaration - do not modify
	private javax.swing.JButton addBtn;
	private javax.swing.JButton curtainShopBtnAdd;
	private javax.swing.JButton curtainShopBtnDel;
	private javax.swing.JButton goodjListBtnAdd;
	private javax.swing.JButton goodjListBtnDel;
	private javax.swing.JButton delBtn;
	private javax.swing.JButton goodModifyBtn;
	private javax.swing.JButton curtainShopModifyBtn;
	private javax.swing.JButton modifyBtn;
	private javax.swing.JTextField curtainShopNameModify;
	private javax.swing.JComboBox curtainShopOwner;
	private javax.swing.JTextField curtainShopTelModify;
	private javax.swing.JTextField factory;
	private javax.swing.JTextField goodPurchasePriceModify;
	private javax.swing.JTextField goodRemarksModify;
	private javax.swing.JTextField goodSerialNumberModify;
	private javax.swing.JTextField purchasePrice;
	private javax.swing.JTextField remarksModify;
	private javax.swing.JTextField sellingPrice;
	private javax.swing.JTextField sellingPriceModify;
	private javax.swing.JTextField serialNumber;
	private javax.swing.JTextField serialNumberModify;
	private javax.swing.JTextField telephone;
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
	private javax.swing.JTable table;
	private DefaultTableModel tableModel;
	private javax.swing.JScrollPane jScrollPane1;
	private javax.swing.JScrollPane jScrollPane2;
	private javax.swing.JScrollPane jScrollPane3;
	private javax.swing.JLabel curtainShopModify;
	private javax.swing.JLabel jLabel1;
	private javax.swing.JLabel jLabel10;
	private javax.swing.JLabel jLabel11;
	private javax.swing.JLabel jLabel12;
	private javax.swing.JLabel jLabel13;
	private javax.swing.JLabel jLabel14;
	private javax.swing.JLabel jLabel15;
	private javax.swing.JLabel jLabel16;
	private javax.swing.JLabel jLabel2;
	private javax.swing.JLabel jLabel3;
	private javax.swing.JLabel jLabel4;
	private javax.swing.JLabel jLabel5;
	private javax.swing.JLabel jLabel6;
	private javax.swing.JLabel jLabel7;
	private javax.swing.JLabel jLabel8;
	private javax.swing.JLabel jLabel9;
	private javax.swing.JPanel jPanel1;
	private javax.swing.JPanel jPanel3;
	private javax.swing.JPanel jPanel4;
	private javax.swing.JPanel jPanel5;
	private javax.swing.JPanel jPanel6;
	private javax.swing.JPanel jPanel7;
	private JTable goodOwnerTable;
	private JScrollPane scrollPane;
}