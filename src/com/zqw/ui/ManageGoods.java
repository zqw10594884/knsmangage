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
import javax.swing.border.EmptyBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;

import com.zqw.bean.Goods;
import com.zqw.bean.PurchaseOrder;
import com.zqw.util.DBUtil;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.JLabel;
import java.awt.Font;

/**
 * 
 * @author __USER__
 */
public class ManageGoods extends javax.swing.JFrame implements
		ListSelectionListener {

	private JPanel contentPane;

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
			libraryNum.setText(goods.getNumber());
		}
	}

	private void setGoods(Goods goods, boolean flag, boolean numberFlag) {
		serialNumber.setText(goods.getSerialNumber());
		serialNumber.setEditable(flag);
		if (serialNumber.getText().contains("A-")
				|| serialNumber.getText().contains("B-")) {
			purchasePrice.setText(goods.getPurchasePrice() - 2 + "");
		} else {
			purchasePrice.setText(goods.getPurchasePrice() + "");
		}
		purchasePrice.setEditable(flag);
		factory.setText(goods.getFactory());
		factory.setEditable(flag);
		telephone.setText(goods.getTelephone());
		telephone.setEditable(flag);
		bankCard.setText(goods.getBankCard());
		bankCard.setEditable(flag);
		libraryNum.setEditable(flag);
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
		String[] columnNames = { "编号", "单价", "数量", "小计", "付款日期", "状态", };
		String[][] tableVales = {};
		tableModel = new DefaultTableModel(tableVales, columnNames);
		table = new JTable(tableModel);
		jScrollPane2.setViewportView(table);
		refreshTable(true);
	}

	@SuppressWarnings("unchecked")
	private void getData(boolean flag) {
		if (flag) {
			goodsLst = (ArrayList<Goods>) DBUtil.getLstClass("serialNumber",
					"", Goods.class, "", "", "");
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

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 715, 672);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		buttonGroup1 = new javax.swing.ButtonGroup();
		jPanel4 = new javax.swing.JPanel();
		jPanel4.setBounds(10, 10, 169, 618);
		jScrollPane1 = new javax.swing.JScrollPane();
		jScrollPane1.setBounds(0, 25, 169, 593);
		goodjList = new javax.swing.JList();
		curtains = new javax.swing.JRadioButton();
		curtains.setFont(new Font("宋体", Font.PLAIN, 13));
		curtains.setBounds(0, 0, 52, 23);
		curtains1 = new javax.swing.JRadioButton();
		curtains1.setFont(new Font("宋体", Font.PLAIN, 13));
		curtains1.setBounds(63, 0, 52, 23);
		curtains2 = new javax.swing.JRadioButton();
		curtains2.setFont(new Font("宋体", Font.PLAIN, 13));
		curtains2.setBounds(126, 0, 40, 23);
		jPanel5 = new javax.swing.JPanel();
		jPanel5.setBounds(189, 49, 499, 156);
		jLabel5 = new javax.swing.JLabel();
		jLabel5.setFont(new Font("宋体", Font.PLAIN, 14));
		jLabel5.setBounds(10, 125, 71, 15);
		jLabel1 = new javax.swing.JLabel();
		jLabel1.setFont(new Font("宋体", Font.PLAIN, 14));
		jLabel1.setBounds(10, 13, 71, 15);
		serialNumber = new javax.swing.JTextField();
		serialNumber.setEditable(false);
		serialNumber.setBounds(91, 13, 349, 21);
		jLabel3 = new javax.swing.JLabel();
		jLabel3.setFont(new Font("宋体", Font.PLAIN, 14));
		jLabel3.setBounds(10, 40, 71, 15);
		factory = new javax.swing.JTextField();
		factory.setEditable(false);
		factory.setBounds(91, 40, 349, 21);
		jLabel2 = new javax.swing.JLabel();
		jLabel2.setFont(new Font("宋体", Font.PLAIN, 14));
		jLabel2.setBounds(10, 67, 92, 15);
		purchasePrice = new javax.swing.JTextField();
		purchasePrice.setEditable(false);
		purchasePrice.setBounds(91, 67, 77, 21);
		jLabel6 = new javax.swing.JLabel();
		jLabel6.setFont(new Font("宋体", Font.PLAIN, 14));
		jLabel6.setBounds(10, 94, 71, 15);
		number = new javax.swing.JTextField();
		number.setBounds(378, 67, 60, 21);
		jLabel7 = new javax.swing.JLabel();
		jLabel7.setFont(new Font("宋体", Font.PLAIN, 14));
		jLabel7.setBounds(315, 70, 57, 15);
		telephone = new javax.swing.JTextField();
		telephone.setEditable(false);
		telephone.setBounds(91, 94, 173, 21);
		bankCard = new javax.swing.JTextField();
		bankCard.setEditable(false);
		bankCard.setBounds(91, 125, 349, 21);
		jScrollPane2 = new javax.swing.JScrollPane();
		jScrollPane2.setBounds(189, 253, 500, 375);
		addBtn = new javax.swing.JButton();
		addBtn.setFont(new Font("宋体", Font.PLAIN, 12));
		addBtn.setBounds(200, 216, 65, 23);
		delBtn = new javax.swing.JButton();
		delBtn.setFont(new Font("宋体", Font.PLAIN, 12));
		delBtn.setBounds(275, 216, 65, 23);
		receiptBtn = new javax.swing.JButton();
		receiptBtn.setFont(new Font("宋体", Font.PLAIN, 12));
		receiptBtn.setBounds(425, 216, 65, 23);
		editBtn = new javax.swing.JButton();
		editBtn.setFont(new Font("宋体", Font.PLAIN, 12));
		editBtn.setBounds(500, 216, 65, 23);
		jLabel4 = new javax.swing.JLabel();
		jLabel4.setBounds(342, 5, 132, 38);
		saveBtn = new javax.swing.JButton();
		saveBtn.setFont(new Font("宋体", Font.PLAIN, 12));
		saveBtn.setBounds(575, 216, 65, 23);
		paymentBtn = new javax.swing.JButton();
		paymentBtn.setFont(new Font("宋体", Font.PLAIN, 12));
		paymentBtn.setBounds(350, 216, 65, 23);

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

		jLabel5.setText("\u5361  \u53f7\uff1a");

		jLabel1.setText("\u578b  \u53f7\uff1a");

		jLabel3.setText("\u5382  \u5bb6\uff1a");

		jLabel2.setText("\u8fdb  \u4ef7\uff1a");

		jLabel6.setText("\u7535  \u8bdd\uff1a");

		jLabel7.setText("\u6570\u91cf\uff1a");

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
		jPanel4.setLayout(null);
		jPanel4.add(curtains);
		jPanel4.add(curtains1);
		jPanel4.add(curtains2);
		jPanel4.add(jScrollPane1);
		jPanel5.setLayout(null);
		jPanel5.add(jLabel1);
		jPanel5.add(jLabel3);
		jPanel5.add(jLabel2);
		jPanel5.add(jLabel6);
		jPanel5.add(telephone);
		jPanel5.add(factory);
		jPanel5.add(serialNumber);
		jPanel5.add(purchasePrice);
		jPanel5.add(jLabel7);
		jPanel5.add(number);
		jPanel5.add(jLabel5);
		jPanel5.add(bankCard);

		libraryNum = new JTextField();
		libraryNum.setEditable(false);
		libraryNum.setBounds(245, 67, 60, 21);
		jPanel5.add(libraryNum);

		JLabel label = new JLabel();
		label.setFont(new Font("宋体", Font.PLAIN, 14));
		label.setText("库存：");
		label.setBounds(178, 70, 57, 15);
		jPanel5.add(label);
		contentPane.setLayout(null);
		contentPane.add(jPanel4);
		contentPane.add(jPanel5);
		contentPane.add(addBtn);
		contentPane.add(delBtn);
		contentPane.add(paymentBtn);
		contentPane.add(receiptBtn);
		contentPane.add(editBtn);
		contentPane.add(saveBtn);
		contentPane.add(jScrollPane2);
		contentPane.add(jLabel4);

	}// </editor-fold>
		// GEN-END:initComponents

	private void saveBtnActionPerformed(java.awt.event.ActionEvent evt) {
		int index = goodjList.getSelectedIndex();
		Goods goods = goodsLst.get(index);
		goods.setBankCard(bankCard.getText());
		goods.setFactory(factory.getText());
		if (serialNumber.getText().contains("A-")
				|| serialNumber.getText().contains("B-")) {
			goods.setPurchasePrice(Double.parseDouble(purchasePrice.getText()) + 2);
		} else {
			goods.setPurchasePrice(Double.parseDouble(purchasePrice.getText()));
		}
		goods.setSerialNumber(serialNumber.getText());
		goods.setTelephone(telephone.getText());
		goods.setNumber(libraryNum.getText().trim());
		DBUtil.update(goods);
		setGoods(goods, false, true);
	}

	private void editBtnActionPerformed(java.awt.event.ActionEvent evt) {
		int index = goodjList.getSelectedIndex();
		Goods goods = goodsLst.get(index);
		setGoods(goods, true, false);
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
		String[] rowValues = { g.getSerialNumber(), g.getPurchasePrice() + "",
				g.getNumber() + "", total, date, state };
		tableModel.addRow(rowValues);
	}

	/**
	 * @param args
	 *            the command line arguments
	 */
	public static void main(String args[]) {
		java.awt.EventQueue.invokeLater(new Runnable() {
			public void run() {
				new ManageGoods().setVisible(true);
			}
		});
	}

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
	// End of variables declaration//GEN-END:variables
	private ArrayList<Goods> goodsLst = new ArrayList<Goods>();
	private ArrayList<PurchaseOrder> purchaseOrderLst = new ArrayList<PurchaseOrder>();
	private DefaultTableModel tableModel;
	private Goods goods = null;
	private int goodsLstIndex = -1;
	private JTextField libraryNum;
}