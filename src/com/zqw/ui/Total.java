package com.zqw.ui;

import java.awt.EventQueue;
import java.awt.Font;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;

import com.zqw.bean.CurtainShop;
import com.zqw.bean.OrderGoods;
import com.zqw.util.DBUtil;
import com.zqw.util.UIutil;

public class Total implements ListSelectionListener{

	private JFrame frame;
	private JTable table;
	private DefaultTableModel tableModel;
	private JScrollPane scrollPane;
	private ArrayList<OrderGoods> goodsLst;
	String[] name = { "C-孔-850", "C-孔-水晶", "C-孔-福鑫", "C-孔-鑫发770", "C-孔-雅恒730",
			"C-孔-龙卷风", "C-布带-挂钩", "C-布带-无纺", "C-布带-有纺", "C-布带-隐形", "C-花边-纽排",
			"D-挂钩" };
	private ArrayList<CurtainShop> curtainShopLst;
	private JList curtainShopjList;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Total window = new Total();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public Total() {
		initialize();
		getData();
		initTable();
		initCurtainShop();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	
	private void initCurtainShop() {
		curtainShopLst = (ArrayList<CurtainShop>) DBUtil.getLstClass("name",
				"", CurtainShop.class, "");
		curtainShopjList = new JList();
		UIutil.initCurtainShop(this, curtainShopjList, curtainShopLst);
		scrollPane.setViewportView(curtainShopjList);
	}
	private void getData() {
		goodsLst = (ArrayList<OrderGoods>) DBUtil.getLstClass("", "",
				OrderGoods.class, "", "", "");
	}

	private void initTable() {
		String[] columnNames = { "850", "水晶", "福鑫", "鑫发770", "雅恒730", "龙卷风",
				"挂钩", "无纺", "有纺", "隐形", "纽排", "挂钩" };
		String[][] tableVales = getTableDate(goodsLst);
		tableModel = new DefaultTableModel(tableVales, columnNames);

		table = new JTable(tableModel);
		scrollPane.setViewportView(table);
	}

	private String[][] getTableDate(ArrayList<OrderGoods> Lst) {
		int[][] tableNumLst = new int[12][12];
		for (int i = 0; i < Lst.size(); i++) {
			OrderGoods og = Lst.get(i);
			for (int j = 0; j < tableNumLst.length; j++) {
				if (og.getDate() != null && og.getDate().getMonth() == j) {
					for (int j2 = 0; j2 < tableNumLst[j].length; j2++) {
						if (og.getSerialNumber().contains(name[j2])) {
							tableNumLst[j][j2] += og.getNumber();
						}
					}
				}
			}
		}
		String[][] tableVales = new String[12][12];
		for (int i = 0; i < tableNumLst.length; i++) {
			for (int j = 0; j < tableNumLst[i].length; j++) {
				tableVales[i][j] = tableNumLst[i][j] + "";
			}
		}
		return tableVales;
	}

	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 978, 578);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);

		scrollPane = new JScrollPane();
		scrollPane.setBounds(210, 287, 742, 243);
		frame.getContentPane().add(scrollPane);
		
		curtainShopjList = new JList();
		curtainShopjList.setBounds(10, 38, 190, 492);
		frame.getContentPane().add(curtainShopjList);
		
		JLabel label = new JLabel();
		label.setText("店  名");
		label.setFont(new Font("微软雅黑", Font.PLAIN, 14));
		label.setBounds(83, 10, 36, 19);
		frame.getContentPane().add(label);
	}

	@Override
	public void valueChanged(ListSelectionEvent e) {
		// TODO Auto-generated method stub
		
	}
}
