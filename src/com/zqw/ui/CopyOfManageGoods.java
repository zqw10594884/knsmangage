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

import javax.swing.GroupLayout.Alignment;
import javax.swing.GroupLayout;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JTextField;
import javax.swing.JLabel;

/**
 * 
 * @author __USER__
 */
public class CopyOfManageGoods extends javax.swing.JFrame implements
		ListSelectionListener {

	private JPanel contentPane;
	/** Creates new form ManageGoods */
	public CopyOfManageGoods() {
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
//			setGoods(goods, false, true);
//			number.setText("");
		}
	}

//	private void setGoods(Goods goods, boolean flag, boolean numberFlag) {
//		purchasePrice.setText(goods.getPurchasePrice() + "");
//		purchasePrice.setEditable(flag);
//		serialNumber.setText(goods.getSerialNumber());
//		serialNumber.setEditable(flag);
//		factory.setText(goods.getFactory());
//		factory.setEditable(flag);
//		telephone.setText(goods.getTelephone());
//		telephone.setEditable(flag);
//		bankCard.setText(goods.getBankCard());
//		bankCard.setEditable(flag);
//		number.setEditable(numberFlag);
//	}

	private void initGoods() {
		String[] Lst = new String[goodsLst.size()];
		for (int i = 0; i < goodsLst.size(); i++) {
			Lst[i] = goodsLst.get(i).getSerialNumber();
		}
	}

	private void initTable() {
		String[] columnNames = { "编号", "数量", "小计", "付款日期", "状态", };
		String[][] tableVales = {};
		tableModel = new DefaultTableModel(tableVales, columnNames);
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

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1164, 649);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		
		
		addBtn = new javax.swing.JButton();
		addBtn.setBounds(209, 211, 57, 23);
		jLabel4 = new javax.swing.JLabel();
		jLabel4.setBounds(337, 0, 132, 38);

//		setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

		addBtn.setText("\u6dfb\u52a0");

		jLabel4.setFont(new java.awt.Font("微软雅黑", 0, 24));
		jLabel4.setText("\u8ba2    \u8d27");
		contentPane.add(addBtn);
		contentPane.add(jLabel4);
		contentPane.setLayout(null);
//		pack();
	}// </editor-fold>

//	private void addBtnActionPerformed(java.awt.event.ActionEvent evt) {
//		String serialNumber = this.serialNumber.getText().trim();
//		double purchasePrice = Double.parseDouble(this.purchasePrice.getText()
//				.trim());
//		String number = this.number.getText().trim();
//		Date date = null;
//		if (number.length() > 0) {
//			PurchaseOrder po = new PurchaseOrder(serialNumber, purchasePrice,
//					Double.parseDouble(number), date, 1);
//			purchaseOrderLst.add(po);
//			DBUtil.insert(po);
//			tableAddLine(po);
//		} else {
//			JOptionPane.showMessageDialog(this, "请输入数量", "alert",
//					JOptionPane.ERROR_MESSAGE);
//		}
//	}

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
	private javax.swing.ButtonGroup buttonGroup1;
	private javax.swing.JList goodjList;
	private javax.swing.JLabel jLabel4;
	private javax.swing.JTable table;
	// End of variables declaration//GEN-END:variables
	private ArrayList<Goods> goodsLst = new ArrayList<Goods>();
	private ArrayList<PurchaseOrder> purchaseOrderLst = new ArrayList<PurchaseOrder>();
	private DefaultTableModel tableModel;
	private Goods goods = null;
	private int goodsLstIndex = -1;
}