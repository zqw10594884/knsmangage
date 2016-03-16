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

import javax.swing.JDialog;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;

import com.zqw.bean.CurtainShop;
import com.zqw.bean.CurtainShopGoods;
import com.zqw.bean.Global;
import com.zqw.bean.Goods;
import com.zqw.util.DBUtil;
import javax.swing.GroupLayout.Alignment;
import javax.swing.GroupLayout;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JScrollPane;

/**
 * 
 * @author __USER__
 */
public class MainAdd extends JDialog implements ListSelectionListener {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
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
		TableColumn column1 = table.getColumnModel().getColumn(1);
		column1.setPreferredWidth(40);
		column1.setMaxWidth(40);
		column1.setMinWidth(40);
		TableColumn column2 = table.getColumnModel().getColumn(2);
		column2.setPreferredWidth(40);
		column2.setMaxWidth(40);
		column2.setMinWidth(40);
		// 增加点击事件
		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				rowClicked();
			}
		});
		jScrollPane3.setViewportView(table);
	}

	// GEN-BEGIN:initComponents
	// <editor-fold defaultstate="collapsed" desc="Generated Code">
	private void initComponents() {
	
		jPanel1 = new javax.swing.JPanel();
		curtainShopBtnAdd = new javax.swing.JButton();
		curtainShopBtnDel = new javax.swing.JButton();
		jScrollPane1 = new javax.swing.JScrollPane();
		jLabel1 = new javax.swing.JLabel();
		jPanel2 = new javax.swing.JPanel();
		jLabel2 = new javax.swing.JLabel();
		jScrollPane2 = new javax.swing.JScrollPane();
		goodjListBtnAdd = new javax.swing.JButton();
		goodjListBtnDel = new javax.swing.JButton();
		jPanel3 = new javax.swing.JPanel();
		delBtn = new javax.swing.JButton();
		delBtn.setBounds(155, 280, 67, 23);
		addBtn = new javax.swing.JButton();
		addBtn.setBounds(70, 280, 67, 23);
		jPanel4 = new javax.swing.JPanel();
		jPanel4.setBounds(281, 0, 286, 124);
		factory = new javax.swing.JTextField();
		sellingPrice = new javax.swing.JTextField();
		telephone = new javax.swing.JTextField();
		jLabel7 = new javax.swing.JLabel();
		jLabel4 = new javax.swing.JLabel();
		jLabel3 = new javax.swing.JLabel();
		serialNumber = new javax.swing.JTextField();
		purchasePrice = new javax.swing.JTextField();
		jLabel5 = new javax.swing.JLabel();
		jLabel6 = new javax.swing.JLabel();
		modifyBtn = new javax.swing.JButton();
		modifyBtn.setBounds(391, 241, 67, 23);
		jPanel6 = new javax.swing.JPanel();
		jPanel6.setBounds(281, 274, 289, 101);
		curtainShopNameModify = new javax.swing.JTextField();
		curtainShopTelModify = new javax.swing.JTextField();
		jLabel11 = new javax.swing.JLabel();
		jLabel12 = new javax.swing.JLabel();
		jLabel13 = new javax.swing.JLabel();
		curtainShopOwner = new javax.swing.JComboBox();
		jPanel7 = new javax.swing.JPanel();
		jPanel7.setBounds(281, 408, 294, 101);
		goodSerialNumberModify = new javax.swing.JTextField();
		jLabel14 = new javax.swing.JLabel();
		goodPurchasePriceModify = new javax.swing.JTextField();
		jLabel15 = new javax.swing.JLabel();
		jLabel16 = new javax.swing.JLabel();
		goodRemarksModify = new javax.swing.JTextField();
		jPanel5 = new javax.swing.JPanel();
		jPanel5.setBounds(281, 123, 286, 107);
		serialNumberModify = new javax.swing.JTextField();
		jLabel8 = new javax.swing.JLabel();
		jLabel9 = new javax.swing.JLabel();
		sellingPriceModify = new javax.swing.JTextField();
		jLabel10 = new javax.swing.JLabel();
		remarksModify = new javax.swing.JTextField();
		curtainShopModify = new javax.swing.JLabel();
		jScrollPane3 = new javax.swing.JScrollPane();
		jScrollPane3.setBounds(10, 8, 258, 262);
		table = new javax.swing.JTable();
		curtainShopModifyBtn = new javax.swing.JButton();
		curtainShopModifyBtn.setBounds(391, 381, 67, 23);
		goodModifyBtn = new javax.swing.JButton();
		goodModifyBtn.setBounds(391, 519, 67, 23);
	
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
	
		jLabel2.setFont(new java.awt.Font("微软雅黑", 0, 14));
		jLabel2.setText("\u8d27 \u7269");
	
		goodjListBtnAdd.setText("\u6dfb\u52a0");
		goodjListBtnAdd.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				goodjListBtnAddActionPerformed(evt);
			}
		});
	
		goodjListBtnDel.setText("\u5220\u9664");
		goodjListBtnDel.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				goodjListBtnDelActionPerformed(evt);
			}
		});
	
		javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(
				jPanel2);
		jPanel2.setLayout(jPanel2Layout);
		jPanel2Layout
				.setHorizontalGroup(jPanel2Layout
						.createParallelGroup(
								javax.swing.GroupLayout.Alignment.LEADING)
						.addGroup(
								jPanel2Layout
										.createSequentialGroup()
										.addGroup(
												jPanel2Layout
														.createParallelGroup(
																javax.swing.GroupLayout.Alignment.LEADING)
														.addGroup(
																jPanel2Layout
																		.createSequentialGroup()
																		.addGap(44,
																				44,
																				44)
																		.addComponent(
																				jLabel2))
														.addGroup(
																jPanel2Layout
																		.createSequentialGroup()
																		.addContainerGap()
																		.addComponent(
																				goodjListBtnAdd)
																		.addPreferredGap(
																				javax.swing.LayoutStyle.ComponentPlacement.RELATED)
																		.addComponent(
																				goodjListBtnDel))
														.addGroup(
																javax.swing.GroupLayout.Alignment.TRAILING,
																jPanel2Layout
																		.createSequentialGroup()
																		.addContainerGap()
																		.addComponent(
																				jScrollPane2,
																				javax.swing.GroupLayout.DEFAULT_SIZE,
																				133,
																				Short.MAX_VALUE)))
										.addContainerGap()));
		jPanel2Layout
				.setVerticalGroup(jPanel2Layout
						.createParallelGroup(
								javax.swing.GroupLayout.Alignment.LEADING)
						.addGroup(
								jPanel2Layout
										.createSequentialGroup()
										.addComponent(jLabel2)
										.addPreferredGap(
												javax.swing.LayoutStyle.ComponentPlacement.RELATED)
										.addComponent(
												jScrollPane2,
												javax.swing.GroupLayout.DEFAULT_SIZE,
												466, Short.MAX_VALUE)
										.addGap(18, 18, 18)
										.addGroup(
												jPanel2Layout
														.createParallelGroup(
																javax.swing.GroupLayout.Alignment.BASELINE)
														.addComponent(
																goodjListBtnAdd)
														.addComponent(
																goodjListBtnDel))
										.addContainerGap()));
	
		javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(
				jPanel1);
		jPanel1.setLayout(jPanel1Layout);
		jPanel1Layout
				.setHorizontalGroup(jPanel1Layout
						.createParallelGroup(
								javax.swing.GroupLayout.Alignment.LEADING)
						.addGroup(
								jPanel1Layout
										.createSequentialGroup()
										.addGroup(
												jPanel1Layout
														.createParallelGroup(
																javax.swing.GroupLayout.Alignment.LEADING)
														.addGroup(
																jPanel1Layout
																		.createSequentialGroup()
																		.addGap(50,
																				50,
																				50)
																		.addComponent(
																				jLabel1))
														.addGroup(
																jPanel1Layout
																		.createSequentialGroup()
																		.addContainerGap()
																		.addGroup(
																				jPanel1Layout
																						.createParallelGroup(
																								javax.swing.GroupLayout.Alignment.LEADING)
																						.addComponent(
																								jScrollPane1,
																								javax.swing.GroupLayout.Alignment.TRAILING,
																								javax.swing.GroupLayout.DEFAULT_SIZE,
																								126,
																								Short.MAX_VALUE)
																						.addGroup(
																								jPanel1Layout
																										.createSequentialGroup()
																										.addComponent(
																												curtainShopBtnAdd,
																												javax.swing.GroupLayout.PREFERRED_SIZE,
																												62,
																												javax.swing.GroupLayout.PREFERRED_SIZE)
																										.addPreferredGap(
																												javax.swing.LayoutStyle.ComponentPlacement.RELATED)
																										.addComponent(
																												curtainShopBtnDel)))))
										.addPreferredGap(
												javax.swing.LayoutStyle.ComponentPlacement.RELATED)
										.addComponent(
												jPanel2,
												javax.swing.GroupLayout.PREFERRED_SIZE,
												javax.swing.GroupLayout.DEFAULT_SIZE,
												javax.swing.GroupLayout.PREFERRED_SIZE)));
		jPanel1Layout
				.setVerticalGroup(jPanel1Layout
						.createParallelGroup(
								javax.swing.GroupLayout.Alignment.LEADING)
						.addGroup(
								jPanel1Layout
										.createSequentialGroup()
										.addComponent(jLabel1)
										.addPreferredGap(
												javax.swing.LayoutStyle.ComponentPlacement.RELATED)
										.addComponent(
												jScrollPane1,
												javax.swing.GroupLayout.DEFAULT_SIZE,
												470, Short.MAX_VALUE)
										.addPreferredGap(
												javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
										.addGroup(
												jPanel1Layout
														.createParallelGroup(
																javax.swing.GroupLayout.Alignment.BASELINE)
														.addComponent(
																curtainShopBtnDel)
														.addComponent(
																curtainShopBtnAdd))
										.addContainerGap())
						.addComponent(jPanel2,
								javax.swing.GroupLayout.DEFAULT_SIZE,
								javax.swing.GroupLayout.DEFAULT_SIZE,
								Short.MAX_VALUE));
	
		delBtn.setText("\u5220\u9664");
		delBtn.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				delBtnActionPerformed(evt);
			}
		});
	
		addBtn.setText("\u6dfb\u52a0");
		addBtn.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				addBtnActionPerformed(evt);
			}
		});
	
		jPanel4.setBorder(javax.swing.BorderFactory.createTitledBorder(
				javax.swing.BorderFactory.createLineBorder(new java.awt.Color(
						0, 0, 0)), "\u8d27\u7269\u6dfb\u52a0"));
	
		jLabel7.setText("\u7535 \u8bdd\uff1a");
	
		jLabel4.setText("\u8fdb \u4ef7\uff1a");
	
		jLabel3.setText("\u578b \u53f7\uff1a");
	
		jLabel5.setText("\u8d27 \u6e90\uff1a");
	
		jLabel6.setText("\u552e \u4ef7\uff1a");
	
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
										.addContainerGap()
										.addGroup(
												jPanel4Layout
														.createParallelGroup(
																javax.swing.GroupLayout.Alignment.LEADING)
														.addGroup(
																jPanel4Layout
																		.createSequentialGroup()
																		.addComponent(
																				jLabel7)
																		.addPreferredGap(
																				javax.swing.LayoutStyle.ComponentPlacement.RELATED)
																		.addComponent(
																				telephone,
																				javax.swing.GroupLayout.PREFERRED_SIZE,
																				124,
																				javax.swing.GroupLayout.PREFERRED_SIZE))
														.addGroup(
																jPanel4Layout
																		.createSequentialGroup()
																		.addGroup(
																				jPanel4Layout
																						.createParallelGroup(
																								javax.swing.GroupLayout.Alignment.LEADING,
																								false)
																						.addGroup(
																								jPanel4Layout
																										.createSequentialGroup()
																										.addComponent(
																												jLabel3)
																										.addPreferredGap(
																												javax.swing.LayoutStyle.ComponentPlacement.RELATED)
																										.addComponent(
																												serialNumber,
																												javax.swing.GroupLayout.PREFERRED_SIZE,
																												66,
																												javax.swing.GroupLayout.PREFERRED_SIZE))
																						.addGroup(
																								jPanel4Layout
																										.createSequentialGroup()
																										.addComponent(
																												jLabel4)
																										.addPreferredGap(
																												javax.swing.LayoutStyle.ComponentPlacement.RELATED)
																										.addComponent(
																												purchasePrice)))
																		.addPreferredGap(
																				javax.swing.LayoutStyle.ComponentPlacement.RELATED)
																		.addGroup(
																				jPanel4Layout
																						.createParallelGroup(
																								javax.swing.GroupLayout.Alignment.LEADING,
																								false)
																						.addGroup(
																								jPanel4Layout
																										.createSequentialGroup()
																										.addComponent(
																												jLabel5)
																										.addPreferredGap(
																												javax.swing.LayoutStyle.ComponentPlacement.RELATED)
																										.addComponent(
																												factory,
																												javax.swing.GroupLayout.PREFERRED_SIZE,
																												66,
																												javax.swing.GroupLayout.PREFERRED_SIZE))
																						.addGroup(
																								jPanel4Layout
																										.createSequentialGroup()
																										.addComponent(
																												jLabel6)
																										.addPreferredGap(
																												javax.swing.LayoutStyle.ComponentPlacement.RELATED)
																										.addComponent(
																												sellingPrice)))))
										.addContainerGap(
												javax.swing.GroupLayout.DEFAULT_SIZE,
												Short.MAX_VALUE)));
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
																javax.swing.GroupLayout.Alignment.LEADING)
														.addGroup(
																jPanel4Layout
																		.createSequentialGroup()
																		.addGroup(
																				jPanel4Layout
																						.createParallelGroup(
																								javax.swing.GroupLayout.Alignment.BASELINE)
																						.addComponent(
																								jLabel3)
																						.addComponent(
																								serialNumber,
																								javax.swing.GroupLayout.PREFERRED_SIZE,
																								javax.swing.GroupLayout.DEFAULT_SIZE,
																								javax.swing.GroupLayout.PREFERRED_SIZE))
																		.addPreferredGap(
																				javax.swing.LayoutStyle.ComponentPlacement.RELATED)
																		.addGroup(
																				jPanel4Layout
																						.createParallelGroup(
																								javax.swing.GroupLayout.Alignment.BASELINE)
																						.addComponent(
																								jLabel4)
																						.addComponent(
																								purchasePrice,
																								javax.swing.GroupLayout.PREFERRED_SIZE,
																								javax.swing.GroupLayout.DEFAULT_SIZE,
																								javax.swing.GroupLayout.PREFERRED_SIZE)))
														.addGroup(
																jPanel4Layout
																		.createSequentialGroup()
																		.addGroup(
																				jPanel4Layout
																						.createParallelGroup(
																								javax.swing.GroupLayout.Alignment.BASELINE)
																						.addComponent(
																								jLabel5)
																						.addComponent(
																								factory,
																								javax.swing.GroupLayout.PREFERRED_SIZE,
																								javax.swing.GroupLayout.DEFAULT_SIZE,
																								javax.swing.GroupLayout.PREFERRED_SIZE))
																		.addPreferredGap(
																				javax.swing.LayoutStyle.ComponentPlacement.RELATED)
																		.addGroup(
																				jPanel4Layout
																						.createParallelGroup(
																								javax.swing.GroupLayout.Alignment.BASELINE)
																						.addComponent(
																								jLabel6)
																						.addComponent(
																								sellingPrice,
																								javax.swing.GroupLayout.PREFERRED_SIZE,
																								javax.swing.GroupLayout.DEFAULT_SIZE,
																								javax.swing.GroupLayout.PREFERRED_SIZE))))
										.addPreferredGap(
												javax.swing.LayoutStyle.ComponentPlacement.RELATED)
										.addGroup(
												jPanel4Layout
														.createParallelGroup(
																javax.swing.GroupLayout.Alignment.BASELINE)
														.addComponent(jLabel7)
														.addComponent(
																telephone,
																javax.swing.GroupLayout.PREFERRED_SIZE,
																javax.swing.GroupLayout.DEFAULT_SIZE,
																javax.swing.GroupLayout.PREFERRED_SIZE))
										.addContainerGap(27, Short.MAX_VALUE)));
	
		modifyBtn.setText("\u4fee\u6539");
		modifyBtn.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				modifyBtnActionPerformed(evt);
			}
		});
	
		jPanel6.setBorder(javax.swing.BorderFactory.createTitledBorder(
				javax.swing.BorderFactory.createLineBorder(new java.awt.Color(
						0, 0, 0)), "\u5ba2\u6237\u4fe1\u606f\u4fee\u6539"));
	
		jLabel11.setText("\u7535 \u8bdd\uff1a");
	
		jLabel12.setText("\u59d3 \u540d\uff1a");
	
		jLabel13.setText("\u6240 \u5c5e\uff1a");
	
		curtainShopOwner.setModel(new javax.swing.DefaultComboBoxModel(
				new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
	
		javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(
				jPanel6);
		jPanel6.setLayout(jPanel6Layout);
		jPanel6Layout
				.setHorizontalGroup(jPanel6Layout
						.createParallelGroup(
								javax.swing.GroupLayout.Alignment.LEADING)
						.addGroup(
								jPanel6Layout
										.createSequentialGroup()
										.addContainerGap()
										.addGroup(
												jPanel6Layout
														.createParallelGroup(
																javax.swing.GroupLayout.Alignment.LEADING)
														.addGroup(
																jPanel6Layout
																		.createSequentialGroup()
																		.addComponent(
																				jLabel11)
																		.addPreferredGap(
																				javax.swing.LayoutStyle.ComponentPlacement.RELATED)
																		.addComponent(
																				curtainShopTelModify,
																				javax.swing.GroupLayout.DEFAULT_SIZE,
																				115,
																				Short.MAX_VALUE))
														.addGroup(
																jPanel6Layout
																		.createSequentialGroup()
																		.addComponent(
																				jLabel12)
																		.addPreferredGap(
																				javax.swing.LayoutStyle.ComponentPlacement.RELATED)
																		.addComponent(
																				curtainShopNameModify,
																				javax.swing.GroupLayout.PREFERRED_SIZE,
																				115,
																				javax.swing.GroupLayout.PREFERRED_SIZE))
														.addGroup(
																jPanel6Layout
																		.createSequentialGroup()
																		.addComponent(
																				jLabel13)
																		.addPreferredGap(
																				javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
																		.addComponent(
																				curtainShopOwner,
																				javax.swing.GroupLayout.PREFERRED_SIZE,
																				javax.swing.GroupLayout.DEFAULT_SIZE,
																				javax.swing.GroupLayout.PREFERRED_SIZE)))
										.addGap(108, 108, 108)));
		jPanel6Layout
				.setVerticalGroup(jPanel6Layout
						.createParallelGroup(
								javax.swing.GroupLayout.Alignment.LEADING)
						.addGroup(
								jPanel6Layout
										.createSequentialGroup()
										.addGroup(
												jPanel6Layout
														.createParallelGroup(
																javax.swing.GroupLayout.Alignment.BASELINE)
														.addComponent(jLabel12)
														.addComponent(
																curtainShopNameModify,
																javax.swing.GroupLayout.PREFERRED_SIZE,
																javax.swing.GroupLayout.DEFAULT_SIZE,
																javax.swing.GroupLayout.PREFERRED_SIZE))
										.addPreferredGap(
												javax.swing.LayoutStyle.ComponentPlacement.RELATED)
										.addGroup(
												jPanel6Layout
														.createParallelGroup(
																javax.swing.GroupLayout.Alignment.BASELINE)
														.addComponent(jLabel11)
														.addComponent(
																curtainShopTelModify,
																javax.swing.GroupLayout.PREFERRED_SIZE,
																javax.swing.GroupLayout.DEFAULT_SIZE,
																javax.swing.GroupLayout.PREFERRED_SIZE))
										.addPreferredGap(
												javax.swing.LayoutStyle.ComponentPlacement.RELATED)
										.addGroup(
												jPanel6Layout
														.createParallelGroup(
																javax.swing.GroupLayout.Alignment.BASELINE)
														.addComponent(jLabel13)
														.addComponent(
																curtainShopOwner,
																javax.swing.GroupLayout.PREFERRED_SIZE,
																javax.swing.GroupLayout.DEFAULT_SIZE,
																javax.swing.GroupLayout.PREFERRED_SIZE))
										.addContainerGap(8, Short.MAX_VALUE)));
	
		jPanel7.setBorder(javax.swing.BorderFactory.createTitledBorder(
				javax.swing.BorderFactory.createLineBorder(new java.awt.Color(
						0, 0, 0)), "\u8d27\u7269\u8d27\u7269\u4fee\u6539"));
	
		jLabel14.setText("\u578b \u53f7\uff1a");
	
		jLabel15.setText("\u8fdb \u4ef7\uff1a");
	
		jLabel16.setText("\u5907 \u6ce8\uff1a");
	
		javax.swing.GroupLayout jPanel7Layout = new javax.swing.GroupLayout(
				jPanel7);
		jPanel7.setLayout(jPanel7Layout);
		jPanel7Layout
				.setHorizontalGroup(jPanel7Layout
						.createParallelGroup(
								javax.swing.GroupLayout.Alignment.LEADING)
						.addGroup(
								jPanel7Layout
										.createSequentialGroup()
										.addContainerGap()
										.addGroup(
												jPanel7Layout
														.createParallelGroup(
																javax.swing.GroupLayout.Alignment.LEADING)
														.addGroup(
																jPanel7Layout
																		.createSequentialGroup()
																		.addComponent(
																				jLabel16)
																		.addPreferredGap(
																				javax.swing.LayoutStyle.ComponentPlacement.RELATED)
																		.addComponent(
																				goodRemarksModify,
																				javax.swing.GroupLayout.DEFAULT_SIZE,
																				216,
																				Short.MAX_VALUE))
														.addGroup(
																jPanel7Layout
																		.createParallelGroup(
																				javax.swing.GroupLayout.Alignment.TRAILING,
																				false)
																		.addGroup(
																				javax.swing.GroupLayout.Alignment.LEADING,
																				jPanel7Layout
																						.createSequentialGroup()
																						.addComponent(
																								jLabel15)
																						.addPreferredGap(
																								javax.swing.LayoutStyle.ComponentPlacement.RELATED)
																						.addComponent(
																								goodPurchasePriceModify))
																		.addGroup(
																				javax.swing.GroupLayout.Alignment.LEADING,
																				jPanel7Layout
																						.createSequentialGroup()
																						.addComponent(
																								jLabel14)
																						.addPreferredGap(
																								javax.swing.LayoutStyle.ComponentPlacement.RELATED)
																						.addComponent(
																								goodSerialNumberModify,
																								javax.swing.GroupLayout.PREFERRED_SIZE,
																								119,
																								javax.swing.GroupLayout.PREFERRED_SIZE))))
										.addContainerGap()));
		jPanel7Layout
				.setVerticalGroup(jPanel7Layout
						.createParallelGroup(
								javax.swing.GroupLayout.Alignment.LEADING)
						.addGap(0, 99, Short.MAX_VALUE)
						.addGroup(
								jPanel7Layout
										.createSequentialGroup()
										.addGroup(
												jPanel7Layout
														.createParallelGroup(
																javax.swing.GroupLayout.Alignment.BASELINE)
														.addComponent(jLabel14)
														.addComponent(
																goodSerialNumberModify,
																javax.swing.GroupLayout.PREFERRED_SIZE,
																javax.swing.GroupLayout.DEFAULT_SIZE,
																javax.swing.GroupLayout.PREFERRED_SIZE))
										.addPreferredGap(
												javax.swing.LayoutStyle.ComponentPlacement.RELATED)
										.addGroup(
												jPanel7Layout
														.createParallelGroup(
																javax.swing.GroupLayout.Alignment.BASELINE)
														.addComponent(jLabel15)
														.addComponent(
																goodPurchasePriceModify,
																javax.swing.GroupLayout.PREFERRED_SIZE,
																javax.swing.GroupLayout.DEFAULT_SIZE,
																javax.swing.GroupLayout.PREFERRED_SIZE))
										.addPreferredGap(
												javax.swing.LayoutStyle.ComponentPlacement.RELATED)
										.addGroup(
												jPanel7Layout
														.createParallelGroup(
																javax.swing.GroupLayout.Alignment.BASELINE)
														.addComponent(jLabel16)
														.addComponent(
																goodRemarksModify,
																javax.swing.GroupLayout.PREFERRED_SIZE,
																javax.swing.GroupLayout.DEFAULT_SIZE,
																javax.swing.GroupLayout.PREFERRED_SIZE))
										.addContainerGap(
												javax.swing.GroupLayout.DEFAULT_SIZE,
												Short.MAX_VALUE)));
	
		jPanel5.setBorder(javax.swing.BorderFactory.createTitledBorder(
				javax.swing.BorderFactory.createLineBorder(new java.awt.Color(
						0, 0, 0)), "\u5ba2\u6237\u8d27\u7269\u4fee\u6539"));
	
		jLabel8.setText("\u578b \u53f7\uff1a");
	
		jLabel9.setText("\u552e \u4ef7\uff1a");
	
		jLabel10.setText("\u5907\u6ce8\uff1a");
	
		remarksModify.setText(" ");
	
		curtainShopModify.setText(" ");
	
		javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(
				jPanel5);
		jPanel5Layout.setHorizontalGroup(
			jPanel5Layout.createParallelGroup(Alignment.LEADING)
				.addGroup(jPanel5Layout.createSequentialGroup()
					.addContainerGap()
					.addGroup(jPanel5Layout.createParallelGroup(Alignment.LEADING)
						.addGroup(jPanel5Layout.createSequentialGroup()
							.addGroup(jPanel5Layout.createParallelGroup(Alignment.TRAILING)
								.addGroup(jPanel5Layout.createSequentialGroup()
									.addComponent(jLabel8)
									.addPreferredGap(ComponentPlacement.RELATED)
									.addComponent(serialNumberModify, GroupLayout.DEFAULT_SIZE, 122, Short.MAX_VALUE))
								.addGroup(jPanel5Layout.createSequentialGroup()
									.addComponent(jLabel9)
									.addPreferredGap(ComponentPlacement.RELATED)
									.addComponent(sellingPriceModify, GroupLayout.DEFAULT_SIZE, 122, Short.MAX_VALUE)))
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(curtainShopModify, GroupLayout.PREFERRED_SIZE, 84, GroupLayout.PREFERRED_SIZE))
						.addGroup(jPanel5Layout.createSequentialGroup()
							.addComponent(jLabel10)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(remarksModify, GroupLayout.DEFAULT_SIZE, 216, Short.MAX_VALUE)))
					.addContainerGap())
		);
		jPanel5Layout.setVerticalGroup(
			jPanel5Layout.createParallelGroup(Alignment.LEADING)
				.addGroup(jPanel5Layout.createSequentialGroup()
					.addGroup(jPanel5Layout.createParallelGroup(Alignment.LEADING)
						.addGroup(jPanel5Layout.createSequentialGroup()
							.addGroup(jPanel5Layout.createParallelGroup(Alignment.BASELINE)
								.addComponent(jLabel8)
								.addComponent(serialNumberModify, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
							.addPreferredGap(ComponentPlacement.RELATED)
							.addGroup(jPanel5Layout.createParallelGroup(Alignment.BASELINE)
								.addComponent(jLabel9)
								.addComponent(sellingPriceModify, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)))
						.addGroup(jPanel5Layout.createSequentialGroup()
							.addContainerGap()
							.addComponent(curtainShopModify, GroupLayout.PREFERRED_SIZE, 30, GroupLayout.PREFERRED_SIZE)))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(jPanel5Layout.createParallelGroup(Alignment.BASELINE)
						.addComponent(jLabel10)
						.addComponent(remarksModify, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
		);
		jPanel5.setLayout(jPanel5Layout);
	
		table.setModel(new javax.swing.table.DefaultTableModel(new Object[][] {
				{ null, null, null, null }, { null, null, null, null },
				{ null, null, null, null }, { null, null, null, null } },
				new String[] { "Title 1", "Title 2", "Title 3", "Title 4" }));
		jScrollPane3.setViewportView(table);
	
		curtainShopModifyBtn.setText("\u4fee\u6539");
		curtainShopModifyBtn
				.addActionListener(new java.awt.event.ActionListener() {
					public void actionPerformed(java.awt.event.ActionEvent evt) {
						curtainShopModifyBtnActionPerformed(evt);
					}
				});
	
		goodModifyBtn.setText("\u4fee\u6539");
		goodModifyBtn.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				goodModifyBtnActionPerformed(evt);
			}
		});
	
		jPanel5.getAccessibleContext().setAccessibleName(
				"\u5ba2\u6237\u8d27\u7269\u4fee\u6539");
	
		javax.swing.GroupLayout layout = new javax.swing.GroupLayout(
				getContentPane());
		layout.setHorizontalGroup(
			layout.createParallelGroup(Alignment.LEADING)
				.addGroup(layout.createSequentialGroup()
					.addComponent(jPanel1, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(jPanel3, GroupLayout.PREFERRED_SIZE, 583, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(19, Short.MAX_VALUE))
		);
		layout.setVerticalGroup(
			layout.createParallelGroup(Alignment.LEADING)
				.addGroup(layout.createSequentialGroup()
					.addContainerGap()
					.addGroup(layout.createParallelGroup(Alignment.LEADING)
						.addComponent(jPanel3, GroupLayout.DEFAULT_SIZE, 552, Short.MAX_VALUE)
						.addGroup(layout.createSequentialGroup()
							.addComponent(jPanel1, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
							.addContainerGap())))
		);
		getContentPane().setLayout(layout);
		jPanel3.setLayout(null);
		jPanel3.add(jScrollPane3);
		jPanel3.add(jPanel4);
		jPanel3.add(addBtn);
		jPanel3.add(delBtn);
		jPanel3.add(jPanel6);
		jPanel3.add(modifyBtn);
		jPanel3.add(jPanel5);
		jPanel3.add(jPanel7);
		jPanel3.add(curtainShopModifyBtn);
		jPanel3.add(goodModifyBtn);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 314, 258, 218);
		jPanel3.add(scrollPane);
		
		goodOwnerjlist = new JList();
		scrollPane.setViewportView(goodOwnerjlist);
	
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
				//加载同时拥有此货物的客户
				List<CurtainShopGoods> Lst = (List<CurtainShopGoods>) DBUtil.getLstClass("curtainShop", "eq", CurtainShopGoods.class, "serialNumber", goods.getSerialNumber(), "String");
				for (int i = 0; i < Lst.size(); i++) {
					String curtainShop =  Lst.get(i).getCurtainShop();
					double sellingPrice =  Lst.get(i).getSellingPrice();
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
	private javax.swing.JPanel jPanel2;
	private javax.swing.JPanel jPanel3;
	private javax.swing.JPanel jPanel4;
	private javax.swing.JPanel jPanel5;
	private javax.swing.JPanel jPanel6;
	private javax.swing.JPanel jPanel7;
	private JList goodOwnerjlist;
}