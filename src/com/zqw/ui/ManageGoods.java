/*
 * ManageGoods.java
 *
 * Created on __DATE__, __TIME__
 */

package com.zqw.ui;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;


import com.zqw.bean.Goods;
import com.zqw.bean.PurchaseOrder;
import com.zqw.util.DBUtil;

/**
 * 
 * @author __USER__
 */
public class ManageGoods extends javax.swing.JFrame implements
		ListSelectionListener {

	/** Creates new form ManageGoods */
	public ManageGoods() {
		initComponents();
		initRadioButton();
		getData(true);
		initGoods();
		initTable();
	}

	@SuppressWarnings("unchecked")
	@Override
	public void valueChanged(ListSelectionEvent e) {
		if (e.getValueIsAdjusting()) {
			int index = goodjList.getSelectedIndex();
			goodsLstIndex = index;
			goods = goodsLst.get(index);
			setGoods(goods, false, true);
			number.setText("");
		}
	}

	private void setGoods(Goods goods, boolean flag, boolean numberFlag) {
		purchasePrice.setText(goods.getPurchasePrice() + "");
		purchasePrice.setEditable(flag);
		serialNumber.setText(goods.getSerialNumber());
		serialNumber.setEditable(flag);
		factory.setText(goods.getFactory());
		factory.setEditable(flag);
		telephone.setText(goods.getTelephone());
		telephone.setEditable(flag);
		bankCard.setText(goods.getBankCard());
		bankCard.setEditable(flag);
		number.setEditable(numberFlag);
	}

	private void initGoods() {
		String[] Lst = new String[goodsLst.size()];
		for (int i = 0; i < goodsLst.size(); i++) {
			Lst[i] = goodsLst.get(i).getSerialNumber();
		}
		goodjList = new JList(Lst);
		goodjList.addListSelectionListener(this);
		goodjList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		jScrollPane1.setViewportView(goodjList);
	}

	private void initTable() {
		String[] columnNames = { "编号", "数量", "小计", "付款日期", "状态", };
		String[][] tableVales = {};
		tableModel = new DefaultTableModel(tableVales, columnNames);
		table = new JTable(tableModel);
		jScrollPane2.setViewportView(table);
		refreshTable(true);
	}
	
	@SuppressWarnings("unchecked")
	private void getData(boolean flag) {
		if (flag) {
			String hqlGoods = "select new Goods(g.id,g.serialNumber,g.purchasePrice,g.factory,g.telephone,g.bankCard,g.remark) from Goods g order by serialNumber";
			goodsLst = (ArrayList<Goods>) DBUtil.getClassLst(hqlGoods, "");
		}
		String purGoods = "select new PurchaseOrder(p.id,p.serialNumber,p.purchasePrice,p.number,p.date,p.state) from PurchaseOrder p order by serialNumber";
		purchaseOrderLst = (ArrayList<PurchaseOrder>) DBUtil.getClassLst(
				purGoods, "");
	}

	private void initRadioButton() {
		// TODO Auto-generated method stub

	}

	// GEN-BEGIN:initComponents
	// <editor-fold defaultstate="collapsed" desc="Generated Code">
	private void initComponents() {

		buttonGroup1 = new javax.swing.ButtonGroup();
		jPanel4 = new javax.swing.JPanel();
		jScrollPane1 = new javax.swing.JScrollPane();
		goodjList = new javax.swing.JList();
		curtains = new javax.swing.JRadioButton();
		curtains1 = new javax.swing.JRadioButton();
		curtains2 = new javax.swing.JRadioButton();
		jPanel5 = new javax.swing.JPanel();
		jLabel5 = new javax.swing.JLabel();
		jLabel1 = new javax.swing.JLabel();
		serialNumber = new javax.swing.JTextField();
		jLabel3 = new javax.swing.JLabel();
		factory = new javax.swing.JTextField();
		jLabel2 = new javax.swing.JLabel();
		purchasePrice = new javax.swing.JTextField();
		jLabel6 = new javax.swing.JLabel();
		number = new javax.swing.JTextField();
		jLabel7 = new javax.swing.JLabel();
		telephone = new javax.swing.JTextField();
		bankCard = new javax.swing.JTextField();
		jScrollPane2 = new javax.swing.JScrollPane();
		table = new javax.swing.JTable();
		addBtn = new javax.swing.JButton();
		delBtn = new javax.swing.JButton();
		receiptBtn = new javax.swing.JButton();
		editBtn = new javax.swing.JButton();
		jLabel4 = new javax.swing.JLabel();
		saveBtn = new javax.swing.JButton();
		paymentBtn = new javax.swing.JButton();
		viewBtn = new javax.swing.JButton();

		setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

		goodjList.setModel(new javax.swing.AbstractListModel() {
			String[] strings = { "Item 1", "Item 2", "Item 3", "Item 4",
					"Item 5" };

			public int getSize() {
				return strings.length;
			}

			public Object getElementAt(int i) {
				return strings[i];
			}
		});
		jScrollPane1.setViewportView(goodjList);

		curtains.setText("\u7a97\u5e18");

		curtains1.setText("\u536b\u6d74");

		curtains2.setText("\u706f");

		javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(
				jPanel4);
		jPanel4.setLayout(jPanel4Layout);
		jPanel4Layout
				.setHorizontalGroup(jPanel4Layout
						.createParallelGroup(
								javax.swing.GroupLayout.Alignment.LEADING)
						.addGroup(
								jPanel4Layout
										.createSequentialGroup()
										.addComponent(curtains)
										.addPreferredGap(
												javax.swing.LayoutStyle.ComponentPlacement.RELATED)
										.addComponent(curtains1)
										.addPreferredGap(
												javax.swing.LayoutStyle.ComponentPlacement.RELATED)
										.addComponent(curtains2)
										.addGap(4, 4, 4))
						.addComponent(jScrollPane1,
								javax.swing.GroupLayout.DEFAULT_SIZE, 151,
								Short.MAX_VALUE));
		jPanel4Layout
				.setVerticalGroup(jPanel4Layout
						.createParallelGroup(
								javax.swing.GroupLayout.Alignment.LEADING)
						.addGroup(
								jPanel4Layout
										.createSequentialGroup()
										.addGroup(
												jPanel4Layout
														.createParallelGroup(
																javax.swing.GroupLayout.Alignment.BASELINE)
														.addComponent(curtains)
														.addComponent(curtains1)
														.addComponent(curtains2))
										.addPreferredGap(
												javax.swing.LayoutStyle.ComponentPlacement.RELATED)
										.addComponent(
												jScrollPane1,
												javax.swing.GroupLayout.PREFERRED_SIZE,
												572,
												javax.swing.GroupLayout.PREFERRED_SIZE)
										.addContainerGap(
												javax.swing.GroupLayout.DEFAULT_SIZE,
												Short.MAX_VALUE)));

		jLabel5.setText("\u5361  \u53f7\uff1a");

		jLabel1.setText("\u578b  \u53f7\uff1a");

		jLabel3.setText("\u5382  \u5bb6\uff1a");

		jLabel2.setText("\u8fdb  \u4ef7\uff1a");

		jLabel6.setText("\u7535  \u8bdd\uff1a");

		jLabel7.setText("\u6570\u91cf\uff1a");

		javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(
				jPanel5);
		jPanel5.setLayout(jPanel5Layout);
		jPanel5Layout
				.setHorizontalGroup(jPanel5Layout
						.createParallelGroup(
								javax.swing.GroupLayout.Alignment.LEADING)
						.addGroup(
								jPanel5Layout
										.createSequentialGroup()
										.addContainerGap()
										.addGroup(
												jPanel5Layout
														.createParallelGroup(
																javax.swing.GroupLayout.Alignment.LEADING)
														.addGroup(
																jPanel5Layout
																		.createSequentialGroup()
																		.addGroup(
																				jPanel5Layout
																						.createParallelGroup(
																								javax.swing.GroupLayout.Alignment.LEADING)
																						.addGroup(
																								jPanel5Layout
																										.createParallelGroup(
																												javax.swing.GroupLayout.Alignment.TRAILING)
																										.addComponent(
																												jLabel1)
																										.addComponent(
																												jLabel3)
																										.addComponent(
																												jLabel2))
																						.addComponent(
																								jLabel6))
																		.addGap(15,
																				15,
																				15)
																		.addGroup(
																				jPanel5Layout
																						.createParallelGroup(
																								javax.swing.GroupLayout.Alignment.TRAILING)
																						.addComponent(
																								telephone,
																								javax.swing.GroupLayout.Alignment.LEADING,
																								javax.swing.GroupLayout.DEFAULT_SIZE,
																								236,
																								Short.MAX_VALUE)
																						.addComponent(
																								factory,
																								javax.swing.GroupLayout.Alignment.LEADING,
																								javax.swing.GroupLayout.DEFAULT_SIZE,
																								236,
																								Short.MAX_VALUE)
																						.addComponent(
																								serialNumber,
																								javax.swing.GroupLayout.Alignment.LEADING,
																								javax.swing.GroupLayout.DEFAULT_SIZE,
																								236,
																								Short.MAX_VALUE)
																						.addGroup(
																								javax.swing.GroupLayout.Alignment.LEADING,
																								jPanel5Layout
																										.createSequentialGroup()
																										.addComponent(
																												purchasePrice,
																												javax.swing.GroupLayout.PREFERRED_SIZE,
																												90,
																												javax.swing.GroupLayout.PREFERRED_SIZE)
																										.addPreferredGap(
																												javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
																										.addComponent(
																												jLabel7)
																										.addPreferredGap(
																												javax.swing.LayoutStyle.ComponentPlacement.RELATED)
																										.addComponent(
																												number,
																												javax.swing.GroupLayout.PREFERRED_SIZE,
																												90,
																												javax.swing.GroupLayout.PREFERRED_SIZE)))
																		.addGap(73,
																				73,
																				73))
														.addGroup(
																jPanel5Layout
																		.createSequentialGroup()
																		.addComponent(
																				jLabel5)
																		.addGap(15,
																				15,
																				15)
																		.addComponent(
																				bankCard,
																				javax.swing.GroupLayout.DEFAULT_SIZE,
																				297,
																				Short.MAX_VALUE)
																		.addContainerGap()))));
		jPanel5Layout
				.setVerticalGroup(jPanel5Layout
						.createParallelGroup(
								javax.swing.GroupLayout.Alignment.LEADING)
						.addGroup(
								jPanel5Layout
										.createSequentialGroup()
										.addContainerGap()
										.addGroup(
												jPanel5Layout
														.createParallelGroup(
																javax.swing.GroupLayout.Alignment.BASELINE)
														.addComponent(
																serialNumber,
																javax.swing.GroupLayout.PREFERRED_SIZE,
																javax.swing.GroupLayout.DEFAULT_SIZE,
																javax.swing.GroupLayout.PREFERRED_SIZE)
														.addComponent(jLabel1))
										.addPreferredGap(
												javax.swing.LayoutStyle.ComponentPlacement.RELATED)
										.addGroup(
												jPanel5Layout
														.createParallelGroup(
																javax.swing.GroupLayout.Alignment.BASELINE)
														.addComponent(
																factory,
																javax.swing.GroupLayout.PREFERRED_SIZE,
																javax.swing.GroupLayout.DEFAULT_SIZE,
																javax.swing.GroupLayout.PREFERRED_SIZE)
														.addComponent(jLabel3))
										.addPreferredGap(
												javax.swing.LayoutStyle.ComponentPlacement.RELATED)
										.addGroup(
												jPanel5Layout
														.createParallelGroup(
																javax.swing.GroupLayout.Alignment.BASELINE)
														.addComponent(jLabel2)
														.addComponent(
																purchasePrice,
																javax.swing.GroupLayout.PREFERRED_SIZE,
																javax.swing.GroupLayout.DEFAULT_SIZE,
																javax.swing.GroupLayout.PREFERRED_SIZE)
														.addComponent(
																number,
																javax.swing.GroupLayout.PREFERRED_SIZE,
																javax.swing.GroupLayout.DEFAULT_SIZE,
																javax.swing.GroupLayout.PREFERRED_SIZE)
														.addComponent(jLabel7))
										.addPreferredGap(
												javax.swing.LayoutStyle.ComponentPlacement.RELATED)
										.addGroup(
												jPanel5Layout
														.createParallelGroup(
																javax.swing.GroupLayout.Alignment.BASELINE)
														.addComponent(
																telephone,
																javax.swing.GroupLayout.PREFERRED_SIZE,
																javax.swing.GroupLayout.DEFAULT_SIZE,
																javax.swing.GroupLayout.PREFERRED_SIZE)
														.addComponent(jLabel6))
										.addPreferredGap(
												javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
										.addGroup(
												jPanel5Layout
														.createParallelGroup(
																javax.swing.GroupLayout.Alignment.BASELINE)
														.addComponent(
																bankCard,
																javax.swing.GroupLayout.PREFERRED_SIZE,
																javax.swing.GroupLayout.DEFAULT_SIZE,
																javax.swing.GroupLayout.PREFERRED_SIZE)
														.addComponent(jLabel5))
										.addContainerGap(1, Short.MAX_VALUE)));

		table.setModel(new javax.swing.table.DefaultTableModel(new Object[][] {
				{ null, null, null, null }, { null, null, null, null },
				{ null, null, null, null }, { null, null, null, null } },
				new String[] { "Title 1", "Title 2", "Title 3", "Title 4" }));
		jScrollPane2.setViewportView(table);

		addBtn.setText("\u6dfb\u52a0");
		addBtn.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				addBtnActionPerformed(evt);
			}
		});

		delBtn.setText("\u5220\u9664");
		delBtn.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				delBtnActionPerformed(evt);
			}
		});

		receiptBtn.setText("\u6536\u8d27");
		receiptBtn.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				receiptBtnActionPerformed(evt);
			}
		});

		editBtn.setText("\u7f16\u8f91");
		editBtn.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				editBtnActionPerformed(evt);
			}
		});

		jLabel4.setFont(new java.awt.Font("微软雅黑", 0, 24));
		jLabel4.setText("\u8ba2    \u8d27");

		saveBtn.setText("\u4fdd\u5b58");
		saveBtn.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				saveBtnActionPerformed(evt);
			}
		});

		paymentBtn.setText(" \u4ed8\u6b3e");
		paymentBtn.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				paymentBtnActionPerformed(evt);
			}
		});

		viewBtn.setText("\u67e5\u770b");
		viewBtn.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				viewBtnActionPerformed(evt);
			}
		});

		javax.swing.GroupLayout layout = new javax.swing.GroupLayout(
				getContentPane());
		getContentPane().setLayout(layout);
		layout.setHorizontalGroup(layout
				.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
				.addGroup(
						layout.createSequentialGroup()
								.addComponent(jPanel4,
										javax.swing.GroupLayout.PREFERRED_SIZE,
										javax.swing.GroupLayout.DEFAULT_SIZE,
										javax.swing.GroupLayout.PREFERRED_SIZE)
								.addGroup(
										layout.createParallelGroup(
												javax.swing.GroupLayout.Alignment.LEADING)
												.addGroup(
														layout.createSequentialGroup()
																.addGap(47, 47,
																		47)
																.addComponent(
																		jPanel5,
																		javax.swing.GroupLayout.PREFERRED_SIZE,
																		javax.swing.GroupLayout.DEFAULT_SIZE,
																		javax.swing.GroupLayout.PREFERRED_SIZE))
												.addGroup(
														layout.createSequentialGroup()
																.addGap(14, 14,
																		14)
																.addComponent(
																		jScrollPane2,
																		javax.swing.GroupLayout.PREFERRED_SIZE,
																		470,
																		javax.swing.GroupLayout.PREFERRED_SIZE))
												.addGroup(
														layout.createSequentialGroup()
																.addPreferredGap(
																		javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
																.addComponent(
																		addBtn)
																.addPreferredGap(
																		javax.swing.LayoutStyle.ComponentPlacement.RELATED)
																.addComponent(
																		delBtn)
																.addPreferredGap(
																		javax.swing.LayoutStyle.ComponentPlacement.RELATED)
																.addComponent(
																		paymentBtn,
																		javax.swing.GroupLayout.PREFERRED_SIZE,
																		68,
																		javax.swing.GroupLayout.PREFERRED_SIZE)
																.addPreferredGap(
																		javax.swing.LayoutStyle.ComponentPlacement.RELATED)
																.addComponent(
																		receiptBtn)
																.addPreferredGap(
																		javax.swing.LayoutStyle.ComponentPlacement.RELATED)
																.addComponent(
																		viewBtn)
																.addPreferredGap(
																		javax.swing.LayoutStyle.ComponentPlacement.RELATED)
																.addComponent(
																		editBtn)
																.addPreferredGap(
																		javax.swing.LayoutStyle.ComponentPlacement.RELATED)
																.addComponent(
																		saveBtn)))
								.addGap(32, 32, 32))
				.addGroup(
						javax.swing.GroupLayout.Alignment.TRAILING,
						layout.createSequentialGroup()
								.addContainerGap(289, Short.MAX_VALUE)
								.addComponent(jLabel4,
										javax.swing.GroupLayout.PREFERRED_SIZE,
										132,
										javax.swing.GroupLayout.PREFERRED_SIZE)
								.addGap(246, 246, 246)));
		layout.setVerticalGroup(layout
				.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
				.addGroup(
						javax.swing.GroupLayout.Alignment.TRAILING,
						layout.createSequentialGroup()
								.addComponent(jLabel4,
										javax.swing.GroupLayout.DEFAULT_SIZE,
										javax.swing.GroupLayout.DEFAULT_SIZE,
										Short.MAX_VALUE)
								.addPreferredGap(
										javax.swing.LayoutStyle.ComponentPlacement.RELATED)
								.addGroup(
										layout.createParallelGroup(
												javax.swing.GroupLayout.Alignment.LEADING)
												.addGroup(
														layout.createSequentialGroup()
																.addComponent(
																		jPanel5,
																		javax.swing.GroupLayout.PREFERRED_SIZE,
																		168,
																		javax.swing.GroupLayout.PREFERRED_SIZE)
																.addPreferredGap(
																		javax.swing.LayoutStyle.ComponentPlacement.RELATED)
																.addGroup(
																		layout.createParallelGroup(
																				javax.swing.GroupLayout.Alignment.BASELINE)
																				.addComponent(
																						addBtn)
																				.addComponent(
																						delBtn)
																				.addComponent(
																						receiptBtn)
																				.addComponent(
																						viewBtn)
																				.addComponent(
																						editBtn)
																				.addComponent(
																						saveBtn)
																				.addComponent(
																						paymentBtn))
																.addGap(12, 12,
																		12)
																.addComponent(
																		jScrollPane2,
																		javax.swing.GroupLayout.PREFERRED_SIZE,
																		387,
																		javax.swing.GroupLayout.PREFERRED_SIZE)
																.addContainerGap())
												.addGroup(
														javax.swing.GroupLayout.Alignment.TRAILING,
														layout.createSequentialGroup()
																.addComponent(
																		jPanel4,
																		javax.swing.GroupLayout.PREFERRED_SIZE,
																		606,
																		javax.swing.GroupLayout.PREFERRED_SIZE)
																.addContainerGap()))));

		pack();
	}// </editor-fold>
		// GEN-END:initComponents

	private void saveBtnActionPerformed(java.awt.event.ActionEvent evt) {
		int index = goodjList.getSelectedIndex();
		Goods goods = goodsLst.get(index);
		goods.setBankCard(bankCard.getText());
		goods.setFactory(factory.getText());
		goods.setPurchasePrice(Double.parseDouble(purchasePrice.getText()));
		goods.setSerialNumber(serialNumber.getText());
		goods.setTelephone(telephone.getText());
		DBUtil.update(goods);
		setGoods(goods, false, true);
	}

	private void editBtnActionPerformed(java.awt.event.ActionEvent evt) {
		int index = goodjList.getSelectedIndex();
		Goods goods = goodsLst.get(index);
		setGoods(goods, true, false);
	}

	private void viewBtnActionPerformed(java.awt.event.ActionEvent evt) {// 2045838
		int selectedRow = table.getSelectedRow();
		if (selectedRow != -1) {
			PurchaseOrder po = purchaseOrderLst.get(selectedRow);
			String hqlGoods = "select new Goods(g.id,g.serialNumber,g.purchasePrice,g.factory,g.telephone,g.bankCard) from Goods as g where g.serialNumber = :name0";
			Goods g = (Goods) DBUtil.get(hqlGoods, po.getSerialNumber().trim(),"");
			setGoods(g, false, true);
			number.setText(po.getNumber() + "");
			number.setEditable(false);
		}
	}

	private void receiptBtnActionPerformed(java.awt.event.ActionEvent evt) {
		int selectedRow = table.getSelectedRow();
		if (selectedRow != -1) {
			PurchaseOrder po = purchaseOrderLst.get(selectedRow);
			po.setState(3);
			DBUtil.update(po);
			refreshTable(false);
		}
	}

	private void paymentBtnActionPerformed(java.awt.event.ActionEvent evt) {
		int selectedRow = table.getSelectedRow();
		Date date = new Date();
		if (selectedRow != -1) {
			PurchaseOrder po = purchaseOrderLst.get(selectedRow);
			po.setState(2);
			po.setDate(date);
			DBUtil.update(po);
			refreshTable(false);
		}
	}

	private void delBtnActionPerformed(java.awt.event.ActionEvent evt) {
		int selectedRow = table.getSelectedRow();
		if (selectedRow != -1) {
			PurchaseOrder po = purchaseOrderLst.get(selectedRow);
			tableModel.removeRow(selectedRow);
			purchaseOrderLst.remove(selectedRow);
			DBUtil.del(po);
		}
	}

	private void addBtnActionPerformed(java.awt.event.ActionEvent evt) {
		String serialNumber = this.serialNumber.getText().trim();
		double purchasePrice = Double.parseDouble(this.purchasePrice.getText()
				.trim());
		String number = this.number.getText().trim();
		Date date = null;
		if (number.length() > 0) {
			PurchaseOrder po = new PurchaseOrder(serialNumber, purchasePrice,
					Double.parseDouble(number), date, 1);
			purchaseOrderLst.add(po);
			DBUtil.insert(po);
			tableAddLine(po);
		} else {
			JOptionPane.showMessageDialog(this, "请输入数量", "alert",
					JOptionPane.ERROR_MESSAGE);
		}
	}

	private void refreshTable(boolean flag) {
		if (flag) {
			getData(false);
		}
		for (int i = 0; i < tableModel.getRowCount();) {
			tableModel.removeRow(0);
		}
		for (int i = 0; i < purchaseOrderLst.size(); i++) {
			tableAddLine(purchaseOrderLst.get(i));
		}
	}

	private void tableAddLine(PurchaseOrder g) {
		String total = g.getPurchasePrice() * g.getNumber() + "";
		String state = null;
		String date = "";
		if (g.getState() == 1) {
			state = "";
		} else if (g.getState() == 2) {
			state = "付款";
		} else if (g.getState() == 3) {
			state = "收货";
		}
		if (g.getDate() != null) {
			SimpleDateFormat time = new SimpleDateFormat("yyyy MM dd ");
			date = time.format(g.getDate());
		}
		String[] rowValues = { g.getSerialNumber(), g.getNumber() + "", total,
				date, state };
		tableModel.addRow(rowValues);
	}

	/**
	 * @param args
	 *            the command line arguments
	 */
//	public static void main(String args[]) {
//		java.awt.EventQueue.invokeLater(new Runnable() {
//			public void run() {
//				new ManageGoods().setVisible(true);
//			}
//		});
//	}

	// GEN-BEGIN:variables
	// Variables declaration - do not modify
	private javax.swing.JButton addBtn;
	private javax.swing.JTextField bankCard;
	private javax.swing.ButtonGroup buttonGroup1;
	private javax.swing.JRadioButton curtains;
	private javax.swing.JRadioButton curtains1;
	private javax.swing.JRadioButton curtains2;
	private javax.swing.JButton delBtn;
	private javax.swing.JButton editBtn;
	private javax.swing.JTextField factory;
	private javax.swing.JList goodjList;
	private javax.swing.JLabel jLabel1;
	private javax.swing.JLabel jLabel2;
	private javax.swing.JLabel jLabel3;
	private javax.swing.JLabel jLabel4;
	private javax.swing.JLabel jLabel5;
	private javax.swing.JLabel jLabel6;
	private javax.swing.JLabel jLabel7;
	private javax.swing.JPanel jPanel4;
	private javax.swing.JPanel jPanel5;
	private javax.swing.JScrollPane jScrollPane1;
	private javax.swing.JScrollPane jScrollPane2;
	private javax.swing.JTextField number;
	private javax.swing.JButton paymentBtn;
	private javax.swing.JTextField purchasePrice;
	private javax.swing.JButton receiptBtn;
	private javax.swing.JButton saveBtn;
	private javax.swing.JTextField serialNumber;
	private javax.swing.JTable table;
	private javax.swing.JTextField telephone;
	private javax.swing.JButton viewBtn;
	// End of variables declaration//GEN-END:variables
	private ArrayList<Goods> goodsLst = new ArrayList<Goods>();
	private ArrayList<PurchaseOrder> purchaseOrderLst = new ArrayList<PurchaseOrder>();
	private DefaultTableModel tableModel;
	private Goods goods = null;
	private int goodsLstIndex = -1;

}