package com.zqw.ui;

import java.awt.EventQueue;
import java.awt.Font;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

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
import com.zqw.bean.OrderLst;
import com.zqw.util.DBUtil;
import javax.swing.JButton;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
 
public class Total implements ListSelectionListener {

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
	private DateChooser beginTime;
	private DateChooser lastTime;

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
	}

	private void getData() {
		goodsLst = (ArrayList<OrderGoods>) DBUtil.getLstClass("", "",
				OrderGoods.class, "", "", "");
	}

	private void initTable() {
		String[] columnNames = { "日期", "850", "水晶", "福鑫", "鑫发770", "雅恒730",
				"龙卷风", "挂钩", "无纺", "有纺", "隐形", "纽排", "挂钩" };
		String[][] tableVales = getTableDate(goodsLst);
		tableModel = new DefaultTableModel(tableVales, columnNames);

		table = new JTable(tableModel);
		scrollPane.setViewportView(table);
	}

	private String[][] getTableDate(ArrayList<OrderGoods> Lst) {
		int[][] tableNumLst = new int[12][12];
		int[][] total = new int[12][12];
		for (int i = 0; i < Lst.size(); i++) {
			OrderGoods og = Lst.get(i);
			for (int j = 0; j < tableNumLst.length; j++) {
				if (og.getDate() != null && og.getDate().getMonth() == j) {
					for (int j2 = 0; j2 < tableNumLst[j].length; j2++) {
						if (og.getSerialNumber().contains(name[j2])) {
							tableNumLst[j][j2] += og.getNumber();
							total[j][j2] += (og.getSellingPrice() - og
									.getPurchasePrice()) * og.getNumber();
						}
					}
				}
			}
		}
		String[][] tableVales = new String[24][13];
		for (int i = 0; i < tableVales.length; i += 2) {
			tableVales[i][0] = (i + 2) / 2 + "";
			for (int j = 1; j < tableVales[i].length; j++) {
				tableVales[i][j] = tableNumLst[i / 2][j - 1] + "";
				tableVales[i + 1][j] = total[i / 2][j - 1] + "";
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

		beginTime = new DateChooser();
		beginTime.setBounds(247, 38, 102, 25);
		frame.getContentPane().add(beginTime);

		lastTime = new DateChooser();
		lastTime.setBounds(422, 38, 102, 25);
		frame.getContentPane().add(lastTime);

		JButton btnNewButton = new JButton("New button");
		btnNewButton.addActionListener(new ActionListener() {
			private List<OrderLst> Lst;

			public void actionPerformed(ActionEvent e) {
				Date begin = beginTime.getDate();
				Date last = lastTime.getDate();
				String sql1 = "select new OrderLst(g.serialNumber,g.sellingPrice,g.purchasePrice,g.number) from OrderLst g where libraryPerson =:name0 and g.submitTime >=:name1 and g.submitTime <=:name2 ";
				Lst = (List<OrderLst>) DBUtil.getClassLst(sql1, "董" + "",
						begin, last);

				// customerSalesStatistics();
			}
		});
		btnNewButton.setBounds(575, 40, 93, 23);
		frame.getContentPane().add(btnNewButton);
	}

	@Override
	public void valueChanged(ListSelectionEvent e) {
		// TODO Auto-generated method stub

	}
}
