package com.zqw.ui;

import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.swing.ButtonGroup;
import javax.swing.DefaultComboBoxModel;
import javax.swing.DefaultListModel;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;

import com.zqw.bean.CurtainCustomer;
import com.zqw.bean.CurtainShopGoods;
import com.zqw.bean.Global;
import com.zqw.bean.SaleOrderGoods;
import com.zqw.bean.SaleOrderLst;
import com.zqw.util.DBUtil;
import com.zqw.util.UIutil;

public class SaleMain extends JFrame implements ListSelectionListener {

	private JPanel contentPane;
	private JTextField curtainClothName;
	private JTextField curtainClothPrice;
	private JTable saleTable;
	private JTextField customerName;
	private JTextField customerTel1;
	private JTextField customerTel2;
	private JTextField customerAdr;
	private JTextField customerDeposit;
	private JTextField curtainLocation;
	private JTextField curtainTapePrice;
	private JTextField curtainClothRemark;
	private JTextField curtainTapeRemark;
	private JTextField curtainRodPrice;
	private JTextField curtainRodRemark;
	private JTextField curtainTapeName;
	private List<CurtainShopGoods> goodsLst = new ArrayList<CurtainShopGoods>();
	private List<SaleOrderLst> solLatelyLst = new ArrayList<SaleOrderLst>();
	private SaleOrderLst currentSol = new SaleOrderLst();
	private CurtainCustomer currentCC = new CurtainCustomer();
	private JList saleGoodjList;
	private JList saleLatelyjList;
	private JButton addCustomerBtn;
	private JButton addCurtainGood;
	private JScrollPane scrollPane_1;
	private JTextField curtainLaceName;
	private JTextField curtainLacePrice;
	private JTextField curtainLaceRemark;
	private JTextField curtainRingName;
	private JTextField curtainRingPrice;
	private JTextField curtainRingRemark;
	private final ButtonGroup buttonGroup = new ButtonGroup();
	private JTextField curtainHight;
	private JComboBox hightLocationCB;
	private JTextField curtainWidth;
	private JTextField curtainRodName;
	private DefaultTableModel tableModel;
	private String[] columnNames = { "位置", "形式", "高度位置", "高度", "宽度", "窗帘布",
			"花边", "窗帘杆", "窗帘圈" };
	private JComboBox curtainStyleCB;
	private int selectedRow;
	private JTextField rodWidth;
	private MouseAdapter curtainRodMA;
	private MouseAdapter curtainRingMA;
	private MouseAdapter curtainLaceMA;
	private MouseAdapter curtainTapeMA;
	private MouseAdapter curtainClothMA;
	private JComboBox curtainLocationCB;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					SaleMain frame = new SaleMain();
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
	public SaleMain() {
		initComponents();
		initGoodLst();
		initTable();
		initlatelyList();
	}

	private void initlatelyList() {
		ArrayList<String> LatelyItem = new ArrayList<String>();
		solLatelyLst = (List<SaleOrderLst>) DBUtil.getLstClass("", "",
				SaleOrderLst.class, "");
		if (solLatelyLst.size() > 0) {
			for (int i = 0; i < solLatelyLst.size(); i++) {
				SaleOrderLst sol = solLatelyLst.get(i);
				LatelyItem.add("(" + sol.getSubmitTime() + ")"
						+ sol.getCustomer().getName());
			}
			UIutil.initJlist(this, saleLatelyjList, LatelyItem);
		}
	}

	private void initComponents() {
		// TODO Auto-generated method stub
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 987, 717);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JPanel panel = new JPanel();
		panel.setBounds(0, 0, 189, 678);
		contentPane.add(panel);

		JLabel label = new JLabel();
		label.setText("货 物");
		label.setFont(new Font("微软雅黑", Font.PLAIN, 14));

		JScrollPane scrollPane = new JScrollPane();
		GroupLayout gl_panel = new GroupLayout(panel);
		gl_panel.setHorizontalGroup(gl_panel
				.createParallelGroup(Alignment.LEADING)
				.addGroup(
						gl_panel.createSequentialGroup()
								.addGroup(
										gl_panel.createParallelGroup(
												Alignment.LEADING)
												.addGroup(
														gl_panel.createSequentialGroup()
																.addGap(71)
																.addComponent(
																		label))
												.addGroup(
														gl_panel.createSequentialGroup()
																.addContainerGap()
																.addComponent(
																		scrollPane,
																		GroupLayout.DEFAULT_SIZE,
																		160,
																		Short.MAX_VALUE)))
								.addContainerGap()));
		gl_panel.setVerticalGroup(gl_panel.createParallelGroup(
				Alignment.LEADING).addGroup(
				gl_panel.createSequentialGroup()
						.addContainerGap()
						.addComponent(label)
						.addPreferredGap(ComponentPlacement.RELATED)
						.addComponent(scrollPane, GroupLayout.DEFAULT_SIZE,
								613, Short.MAX_VALUE).addContainerGap()));

		saleGoodjList = new JList();
		scrollPane.setViewportView(saleGoodjList);
		panel.setLayout(gl_panel);

		scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(199, 394, 764, 239);
		contentPane.add(scrollPane_1);

		saleTable = new JTable();
		// saleTable.setModel();
		saleTable.setToolTipText("窗帘");
		scrollPane_1.setViewportView(saleTable);

		JLabel saleTotal = new JLabel("总价：");
		saleTotal.setFont(new Font("宋体", Font.PLAIN, 14));
		saleTotal.setBounds(209, 643, 45, 25);
		contentPane.add(saleTotal);

		JLabel label_8 = new JLabel("");
		label_8.setFont(new Font("宋体", Font.PLAIN, 14));
		label_8.setBounds(253, 643, 54, 20);
		contentPane.add(label_8);

		JPanel panel_1 = new JPanel();
		panel_1.setBorder(new TitledBorder(null, "\u5BA2\u6237\u4FE1\u606F",
				TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel_1.setBounds(199, 10, 480, 115);
		contentPane.add(panel_1);
		panel_1.setLayout(null);

		JLabel label_1 = new JLabel("姓名：");
		label_1.setFont(new Font("宋体", Font.PLAIN, 14));
		label_1.setBounds(14, 26, 45, 15);
		panel_1.add(label_1);

		customerName = new JTextField();
		customerName.setColumns(10);
		customerName.setBounds(73, 23, 103, 21);
		panel_1.add(customerName);

		JLabel label_3 = new JLabel("电话：");
		label_3.setFont(new Font("宋体", Font.PLAIN, 14));
		label_3.setBounds(190, 26, 45, 15);
		panel_1.add(label_3);

		customerTel1 = new JTextField();
		customerTel1.setColumns(10);
		customerTel1.setBounds(249, 23, 100, 21);
		panel_1.add(customerTel1);

		customerTel2 = new JTextField();
		customerTel2.setColumns(10);
		customerTel2.setBounds(363, 23, 100, 21);
		panel_1.add(customerTel2);

		JLabel label_6 = new JLabel("地址：");
		label_6.setFont(new Font("宋体", Font.PLAIN, 14));
		label_6.setBounds(16, 52, 45, 16);
		panel_1.add(label_6);

		customerAdr = new JTextField();
		customerAdr.setColumns(10);
		customerAdr.setBounds(77, 51, 383, 21);
		panel_1.add(customerAdr);

		customerDeposit = new JTextField();
		customerDeposit.setColumns(10);
		customerDeposit.setBounds(63, 78, 66, 21);
		panel_1.add(customerDeposit);

		JLabel label_7 = new JLabel("定金：");
		label_7.setFont(new Font("新宋体", Font.PLAIN, 14));
		label_7.setBounds(9, 78, 45, 21);
		panel_1.add(label_7);

		addCustomerBtn = new JButton("添加客户");
		addCustomerBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				addCustomerActionPerformed(e);
			}
		});
		addCustomerBtn.setFont(new Font("宋体", Font.PLAIN, 14));
		addCustomerBtn.setBounds(366, 76, 103, 23);
		panel_1.add(addCustomerBtn);

		JButton customerModifyBtn = new JButton("修改");
		customerModifyBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		customerModifyBtn.setFont(new Font("宋体", Font.PLAIN, 14));
		customerModifyBtn.setBounds(290, 76, 67, 23);
		panel_1.add(customerModifyBtn);

		JButton customerDelBtn = new JButton("删除");
		customerDelBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		customerDelBtn.setFont(new Font("宋体", Font.PLAIN, 14));
		customerDelBtn.setBounds(214, 76, 67, 23);
		panel_1.add(customerDelBtn);

		JButton newButton = new JButton("新建");
		newButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				clearAll();
			}
		});
		newButton.setFont(new Font("宋体", Font.PLAIN, 14));
		newButton.setBounds(138, 76, 67, 23);
		panel_1.add(newButton);

		JPanel panel_2 = new JPanel();
		panel_2.setBounds(689, 10, 274, 374);
		contentPane.add(panel_2);

		JLabel label_9 = new JLabel();
		label_9.setText("历史订单");
		label_9.setFont(new Font("微软雅黑", Font.PLAIN, 14));

		JScrollPane scrollPane_2 = new JScrollPane();
		GroupLayout gl_panel_2 = new GroupLayout(panel_2);
		gl_panel_2
				.setHorizontalGroup(gl_panel_2
						.createParallelGroup(Alignment.LEADING)
						.addGroup(
								gl_panel_2
										.createSequentialGroup()
										.addContainerGap()
										.addGroup(
												gl_panel_2
														.createParallelGroup(
																Alignment.LEADING)
														.addGroup(
																gl_panel_2
																		.createSequentialGroup()
																		.addComponent(
																				scrollPane_2,
																				GroupLayout.DEFAULT_SIZE,
																				169,
																				Short.MAX_VALUE)
																		.addContainerGap())
														.addGroup(
																Alignment.TRAILING,
																gl_panel_2
																		.createSequentialGroup()
																		.addComponent(
																				label_9)
																		.addGap(100)))));
		gl_panel_2.setVerticalGroup(gl_panel_2.createParallelGroup(
				Alignment.LEADING).addGroup(
				gl_panel_2
						.createSequentialGroup()
						.addContainerGap()
						.addComponent(label_9)
						.addPreferredGap(ComponentPlacement.RELATED)
						.addComponent(scrollPane_2, GroupLayout.DEFAULT_SIZE,
								339, Short.MAX_VALUE).addContainerGap()));

		saleLatelyjList = new JList();
		scrollPane_2.setViewportView(saleLatelyjList);
		panel_2.setLayout(gl_panel_2);

		JButton submitBtn = new JButton("提交订单");
		submitBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				submitAction(e);
			}
		});
		submitBtn.setFont(new Font("宋体", Font.PLAIN, 14));
		submitBtn.setBounds(547, 644, 100, 23);
		contentPane.add(submitBtn);

		JLabel label_22 = new JLabel("宽度：");
		label_22.setFont(new Font("宋体", Font.PLAIN, 14));
		label_22.setBounds(924, 181, 56, 15);
		contentPane.add(label_22);

		JPanel panel_3 = new JPanel();
		panel_3.setBorder(new TitledBorder(null, "\u8D27\u7269\u4FE1\u606F",
				TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel_3.setBounds(199, 124, 480, 260);
		contentPane.add(panel_3);
		panel_3.setLayout(null);

		JLabel label_2 = new JLabel("窗帘布：");
		label_2.setBounds(5, 88, 56, 15);
		panel_3.add(label_2);
		label_2.setFont(new Font("宋体", Font.PLAIN, 14));

		JLabel lblNewLabel = new JLabel("布带：");
		lblNewLabel.setBounds(5, 119, 56, 15);
		panel_3.add(lblNewLabel);
		lblNewLabel.setFont(new Font("宋体", Font.PLAIN, 14));

		JLabel label_16 = new JLabel("花边：");
		label_16.setBounds(5, 147, 56, 15);
		panel_3.add(label_16);
		label_16.setFont(new Font("宋体", Font.PLAIN, 14));

		JLabel label_19 = new JLabel("窗帘圈：");
		label_19.setBounds(5, 175, 56, 15);
		panel_3.add(label_19);
		label_19.setFont(new Font("宋体", Font.PLAIN, 14));

		JLabel label_13 = new JLabel("窗帘杆：");
		label_13.setBounds(5, 205, 56, 15);
		panel_3.add(label_13);
		label_13.setFont(new Font("宋体", Font.PLAIN, 14));

		curtainRodName = new JTextField();
		curtainRodName.setBounds(66, 199, 130, 21);
		panel_3.add(curtainRodName);
		curtainRodName.setEditable(false);
		curtainRodMA = new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				reBuildGoodLst("C-杆");
			}
		};
		curtainRodName.addMouseListener(curtainRodMA);
		curtainRodName.setColumns(10);

		curtainRingName = new JTextField();
		curtainRingName.setBounds(66, 169, 130, 21);
		panel_3.add(curtainRingName);
		curtainRingName.setEditable(false);
		curtainRingMA = new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				reBuildGoodLst("C-孔");
			}
		};
		curtainRingName.addMouseListener(curtainRingMA);
		curtainRingName.setColumns(10);

		curtainLaceName = new JTextField();
		curtainLaceName.setBounds(66, 141, 130, 21);
		panel_3.add(curtainLaceName);
		curtainLaceName.setEditable(false);
		curtainLaceMA = new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				reBuildGoodLst("C-花边");
			}
		};
		curtainLaceName.addMouseListener(curtainLaceMA);
		curtainLaceName.setColumns(10);

		curtainTapeName = new JTextField();
		curtainTapeName.setBounds(66, 113, 130, 21);
		panel_3.add(curtainTapeName);
		curtainTapeName.setEditable(false);
		curtainTapeMA = new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				reBuildGoodLst("C-布带");
			}
		};
		curtainTapeName.addMouseListener(curtainTapeMA);
		curtainTapeName.setColumns(10);

		curtainClothName = new JTextField();
		curtainClothName.setBounds(66, 82, 130, 21);
		panel_3.add(curtainClothName);
		curtainClothName.setEditable(false);
		curtainClothMA = new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				reBuildGoodLst("A-");
			}
		};
		curtainClothName.addMouseListener(curtainClothMA);
		curtainClothName.setColumns(10);

		JLabel label_4 = new JLabel("价格：");
		label_4.setBounds(201, 88, 45, 15);
		panel_3.add(label_4);
		label_4.setFont(new Font("宋体", Font.PLAIN, 14));

		JLabel label_10 = new JLabel("价格：");
		label_10.setBounds(201, 119, 45, 15);
		panel_3.add(label_10);
		label_10.setFont(new Font("宋体", Font.PLAIN, 14));

		JLabel label_17 = new JLabel("价格：");
		label_17.setBounds(201, 147, 45, 15);
		panel_3.add(label_17);
		label_17.setFont(new Font("宋体", Font.PLAIN, 14));

		JLabel label_20 = new JLabel("价格：");
		label_20.setBounds(201, 175, 45, 15);
		panel_3.add(label_20);
		label_20.setFont(new Font("宋体", Font.PLAIN, 14));

		JLabel label_14 = new JLabel("价格：");
		label_14.setBounds(201, 205, 45, 15);
		panel_3.add(label_14);
		label_14.setFont(new Font("宋体", Font.PLAIN, 14));

		curtainRodPrice = new JTextField();
		curtainRodPrice.setBounds(251, 199, 38, 21);
		panel_3.add(curtainRodPrice);
		curtainRodPrice.setColumns(10);

		curtainRingPrice = new JTextField();
		curtainRingPrice.setBounds(251, 169, 38, 21);
		panel_3.add(curtainRingPrice);
		curtainRingPrice.setColumns(10);

		curtainLacePrice = new JTextField();
		curtainLacePrice.setBounds(251, 141, 38, 21);
		panel_3.add(curtainLacePrice);
		curtainLacePrice.setColumns(10);

		curtainTapePrice = new JTextField();
		curtainTapePrice.setBounds(251, 113, 38, 21);
		panel_3.add(curtainTapePrice);
		curtainTapePrice.setColumns(10);

		curtainClothPrice = new JTextField();
		curtainClothPrice.setBounds(251, 82, 38, 21);
		panel_3.add(curtainClothPrice);
		curtainClothPrice.setColumns(10);

		JLabel label_15 = new JLabel("备注：");
		label_15.setBounds(294, 205, 45, 15);
		panel_3.add(label_15);
		label_15.setFont(new Font("宋体", Font.PLAIN, 14));

		JLabel label_21 = new JLabel("备注：");
		label_21.setBounds(294, 175, 45, 15);
		panel_3.add(label_21);
		label_21.setFont(new Font("宋体", Font.PLAIN, 14));

		JLabel label_18 = new JLabel("备注：");
		label_18.setBounds(294, 147, 45, 15);
		panel_3.add(label_18);
		label_18.setFont(new Font("宋体", Font.PLAIN, 14));

		JLabel label_12 = new JLabel("备注：");
		label_12.setBounds(294, 119, 45, 15);
		panel_3.add(label_12);
		label_12.setFont(new Font("宋体", Font.PLAIN, 14));

		JLabel label_11 = new JLabel("备注：");
		label_11.setBounds(294, 88, 45, 15);
		panel_3.add(label_11);
		label_11.setFont(new Font("宋体", Font.PLAIN, 14));

		curtainClothRemark = new JTextField();
		curtainClothRemark.setBounds(344, 82, 120, 21);
		panel_3.add(curtainClothRemark);
		curtainClothRemark.setColumns(10);

		curtainTapeRemark = new JTextField();
		curtainTapeRemark.setBounds(344, 113, 120, 21);
		panel_3.add(curtainTapeRemark);
		curtainTapeRemark.setColumns(10);

		curtainLaceRemark = new JTextField();
		curtainLaceRemark.setBounds(344, 141, 120, 21);
		panel_3.add(curtainLaceRemark);
		curtainLaceRemark.setColumns(10);

		curtainRingRemark = new JTextField();
		curtainRingRemark.setBounds(344, 169, 120, 21);
		panel_3.add(curtainRingRemark);
		curtainRingRemark.setColumns(10);

		curtainRodRemark = new JTextField();
		curtainRodRemark.setBounds(344, 199, 120, 21);
		panel_3.add(curtainRodRemark);
		curtainRodRemark.setColumns(10);

		JButton curtainDelBtn = new JButton("删除");
		curtainDelBtn.setBounds(172, 227, 67, 23);
		panel_3.add(curtainDelBtn);
		curtainDelBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

			}
		});
		curtainDelBtn.setFont(new Font("宋体", Font.PLAIN, 14));

		JButton curtainModifyBtn = new JButton("修改");
		curtainModifyBtn.setBounds(260, 227, 67, 23);
		panel_3.add(curtainModifyBtn);
		curtainModifyBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				curtainModifyAction(e);
			}
		});
		curtainModifyBtn.setFont(new Font("宋体", Font.PLAIN, 14));

		addCurtainGood = new JButton("添加货物");
		addCurtainGood.setBounds(364, 227, 100, 23);
		panel_3.add(addCurtainGood);
		addCurtainGood.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (currentSol.getCustomer() != null) {
					addCurtainGoodActionPerformed(e);
				} else {
					JOptionPane.showMessageDialog(null, "请先输入用户信息");
				}
			}
		});
		addCurtainGood.setFont(new Font("宋体", Font.PLAIN, 14));

		curtainStyleCB = new JComboBox();
		curtainStyleCB.setBounds(21, 22, 98, 21);
		panel_3.add(curtainStyleCB);
		curtainStyleCB.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				curtainStyleAction(e);
			}
		});
		curtainStyleCB.setModel(new DefaultComboBoxModel(new String[] {
				"打孔*1.7", "挂钩*1.5", "挂钩+0.5", "卷帘" }));

		curtainWidth = new JTextField();
		curtainWidth.setBounds(69, 57, 70, 21);
		panel_3.add(curtainWidth);
		curtainWidth.setColumns(10);

		JLabel label_5 = new JLabel("安装位置：");
		label_5.setBounds(140, 25, 75, 15);
		panel_3.add(label_5);
		label_5.setFont(new Font("宋体", Font.PLAIN, 14));

		hightLocationCB = new JComboBox();
		hightLocationCB.setBounds(305, 57, 79, 21);
		panel_3.add(hightLocationCB);
		hightLocationCB.setModel(new DefaultComboBoxModel(new String[] {
				"高度位置", "顶高", "总高", "杆到地", "顶装" }));
		hightLocationCB.setToolTipText("");

		curtainLocationCB = new JComboBox();
		curtainLocationCB.setModel(new DefaultComboBoxModel(new String[] {
				"请选择", "客厅", "阳台", "主卧", "次卧", "后卧", "餐厅", "厨房", "卫生间" }));
		curtainLocationCB.setBounds(236, 22, 70, 21);
		panel_3.add(curtainLocationCB);
		curtainLocationCB.setToolTipText("");

		curtainLocation = new JTextField();
		curtainLocation.setBounds(327, 22, 130, 21);
		panel_3.add(curtainLocation);
		curtainLocation.setColumns(10);

		curtainHight = new JTextField();
		curtainHight.setBounds(396, 57, 70, 21);
		panel_3.add(curtainHight);
		curtainHight.setColumns(10);

		JLabel label_23 = new JLabel("宽度：");
		label_23.setFont(new Font("宋体", Font.PLAIN, 14));
		label_23.setBounds(12, 59, 45, 15);
		panel_3.add(label_23);

		rodWidth = new JTextField();
		rodWidth.setColumns(10);
		rodWidth.setBounds(223, 57, 70, 21);
		panel_3.add(rodWidth);

		JLabel label_24 = new JLabel("杆增加：");
		label_24.setFont(new Font("宋体", Font.PLAIN, 14));
		label_24.setBounds(151, 59, 60, 15);
		panel_3.add(label_24);
	}

	protected void clearAll() {
		currentSol = new SaleOrderLst();
		currentCC = new CurtainCustomer();
		customerName.setText("");
		customerTel1.setText("");
		customerTel2.setText("");
		customerAdr.setText("");
		customerDeposit.setText("");
		for (int i = 0; i < tableModel.getRowCount();) {
			tableModel.removeRow(0);
		}
		saleTable.setModel(new DefaultTableModel());
		clearGoodsTable();
	}

	protected void curtainModifyAction(ActionEvent e) {
		saleOrderGoodsBuild((SaleOrderGoods) currentSol.getGoodsLst().get(
				selectedRow));
	}

	protected void curtainStyleAction(ActionEvent e) {
		int index = curtainStyleCB.getSelectedIndex();
		saleGoodjList.setModel(new DefaultListModel());
		curtainRodName.removeMouseListener(curtainRodMA);
		curtainRingName.removeMouseListener(curtainRingMA);
		curtainLaceName.removeMouseListener(curtainLaceMA);
		curtainTapeName.removeMouseListener(curtainTapeMA);
		curtainClothName.removeMouseListener(curtainClothMA);

		curtainRodName.setEditable(false);
		// curtainRodName.setText("");
		curtainRingName.setEditable(false);
		// curtainRingName.setText("");
		curtainLaceName.setEditable(false);
		// curtainLaceName.setText("");
		curtainTapeName.setEditable(false);
		// curtainTapeName.setText("");
		curtainClothName.setEditable(false);
		// curtainClothName.setText("");

		curtainRodPrice.setEditable(false);
		// curtainRodPrice.setText("");
		curtainRingPrice.setEditable(false);
		// curtainRingPrice.setText("");
		curtainLacePrice.setEditable(false);
		// curtainLacePrice.setText("");
		curtainTapePrice.setEditable(false);
		// curtainTapePrice.setText("");
		curtainClothPrice.setEditable(false);
		// curtainClothPrice.setText("");

		curtainRodRemark.setEditable(false);
		// curtainRodRemark.setText("");
		curtainRingRemark.setEditable(false);
		// curtainRingRemark.setText("");
		curtainLaceRemark.setEditable(false);
		// curtainLaceRemark.setText("");
		curtainTapeRemark.setEditable(false);
		// curtainTapeRemark.setText("");
		curtainClothRemark.setEditable(false);
		// curtainClothRemark.setText("");

		switch (index) {
		case 0:
			curtainRodName.addMouseListener(curtainRodMA);
			curtainRingName.addMouseListener(curtainRingMA);
			curtainLaceName.addMouseListener(curtainLaceMA);
			curtainTapeName.addMouseListener(curtainTapeMA);
			curtainClothName.addMouseListener(curtainClothMA);

			curtainRodName.setEditable(false);
			curtainRingName.setEditable(false);
			curtainLaceName.setEditable(false);
			curtainTapeName.setEditable(false);
			curtainClothName.setEditable(false);

			curtainRodPrice.setEditable(true);
			curtainRingPrice.setEditable(true);
			curtainLacePrice.setEditable(true);
			curtainTapePrice.setEditable(true);
			curtainClothPrice.setEditable(true);

			curtainRodRemark.setEditable(true);
			curtainRingRemark.setEditable(true);
			curtainLaceRemark.setEditable(true);
			curtainTapeRemark.setEditable(true);
			curtainClothRemark.setEditable(true);

			break;
		case 1:
			curtainRodName.addMouseListener(curtainRodMA);
			curtainLaceName.addMouseListener(curtainLaceMA);
			curtainTapeName.addMouseListener(curtainTapeMA);
			curtainClothName.addMouseListener(curtainClothMA);

			curtainRodName.setEditable(false);
			curtainLaceName.setEditable(false);
			curtainTapeName.setEditable(false);
			curtainClothName.setEditable(false);

			curtainRodPrice.setEditable(true);
			curtainLacePrice.setEditable(true);
			curtainTapePrice.setEditable(true);
			curtainClothPrice.setEditable(true);

			curtainRodRemark.setEditable(true);
			curtainLaceRemark.setEditable(true);
			curtainTapeRemark.setEditable(true);
			curtainClothRemark.setEditable(true);
			break;
		case 2:
			curtainRodName.addMouseListener(curtainRodMA);
			curtainLaceName.addMouseListener(curtainLaceMA);
			curtainTapeName.addMouseListener(curtainTapeMA);
			curtainClothName.addMouseListener(curtainClothMA);

			curtainRodName.setEditable(false);
			curtainLaceName.setEditable(false);
			curtainTapeName.setEditable(false);
			curtainClothName.setEditable(false);

			curtainRodPrice.setEditable(true);
			curtainLacePrice.setEditable(true);
			curtainTapePrice.setEditable(true);
			curtainClothPrice.setEditable(true);

			curtainRodRemark.setEditable(true);
			curtainLaceRemark.setEditable(true);
			curtainTapeRemark.setEditable(true);
			curtainClothRemark.setEditable(true);
			break;
		case 3:
			curtainClothName.setEditable(true);

			curtainClothPrice.setEditable(true);

			curtainClothRemark.setEditable(true);

			break;
		default:
			break;
		}
	}

	private void submitAction(ActionEvent e) {
		solLatelyLst.add(currentSol);
		ArrayList<String> item = new ArrayList<String>();
		for (int i = 0; i < solLatelyLst.size(); i++) {
			item.add(solLatelyLst.get(i).getSimpleDate() + "   "
					+ solLatelyLst.get(i).getCustomer().getName());
		}
		UIutil.initJlist(this, saleLatelyjList, item);
		DBUtil.insert(currentSol);
	}

	private void addCurtainGoodActionPerformed(ActionEvent e) {
		SaleOrderGoods sog = new SaleOrderGoods();
		saleOrderGoodsBuild(sog);
		currentSol.getGoodsLst().add(sog);
		String[] rowValues = creatRow(sog);
		tableModel.addRow(rowValues);
	}

	private void saleOrderGoodsBuild(SaleOrderGoods sog) {
		double width = Double.parseDouble(curtainWidth.getText().trim());
		String curtainStyle = curtainStyleCB.getSelectedItem().toString();

		sog.setCurtainHight(curtainHight.getText().trim());
		if ("请选择".equals(curtainLocationCB.getSelectedItem().toString())) {
			sog.setCurtainLocation(curtainLocation.getText().trim());
		} else {
			sog.setCurtainLocation(curtainLocationCB.getSelectedItem()
					.toString());
		}
		sog.setHightLocation(hightLocationCB.getSelectedItem().toString());
		sog.setCurtainStyle(curtainStyle);
		sog.setCurtainWidth(curtainWidth.getText().trim());

		if ("卷帘".equals(curtainStyle)) {
			sog.setClothSerialNumber(curtainClothName.getText().trim());
			sog.setClothSellingPrice(Double.parseDouble(curtainClothPrice
					.getText().trim()));
			sog.setClothRemark(curtainClothRemark.getText());
		} else {
			sog.setClothSerialNumber(curtainClothName.getText().trim());
			sog.setCurtainTapeSerialNumber(curtainTapeName.getText().trim());
			sog.setCurtainLaceSerialNumber(curtainLaceName.getText().trim());
			sog.setCurtainRingSerialNumber(curtainRingName.getText().trim());
			sog.setCurtainRodSerialNumber(curtainRodName.getText().trim());

			sog.setClothSellingPrice(Double.parseDouble(curtainClothPrice
					.getText().trim()));
			sog.setCurtainTapeSellingPrice(Double.parseDouble(curtainTapePrice
					.getText().trim()));
			sog.setCurtainLaceSellingPrice(Double.parseDouble(curtainLacePrice
					.getText().trim()));
			sog.setCurtainRingSellingPrice(Double.parseDouble(curtainRingPrice
					.getText().trim()));
			sog.setCurtainRodSellingPrice(Double.parseDouble(curtainRodPrice
					.getText().trim()));

			sog.setClothRemark(curtainClothRemark.getText());
			sog.setCurtainTapeRemark(curtainTapeRemark.getText());
			sog.setCurtainLaceRemark(curtainLaceRemark.getText());
			sog.setCurtainRingRemark(curtainRingRemark.getText());
			sog.setCurtainRodRemark(curtainRodRemark.getText());

			if ("打孔*1.7".equals(curtainStyle)) {
				sog.setClothNumber(width * 1.7);
				sog.setCurtainTapeNumber(width * 1.7);
				sog.setCurtainLaceNumber(width * 1.7);
				sog.setCurtainRingNumber((int) (width * 1.7 * 7));
			} else if ("挂钩*1.5".equals(curtainStyle)) {
				sog.setClothNumber(width * 1.5);
				sog.setCurtainTapeNumber(width * 1.5);
				sog.setCurtainLaceNumber(width * 1.5);
			} else {
				sog.setClothNumber(width + 0.5);
				sog.setCurtainTapeNumber(width + 0.5);
				sog.setCurtainLaceNumber(width + 0.5);
			}
		}
		if (rodWidth.getText().trim().length() > 0) {

			sog.setCurtainRodNumber(width
					+ Integer.parseInt(rodWidth.getText().trim()));
		} else {
			sog.setCurtainRodNumber(width);
		}
	}

	private String[] creatRow(SaleOrderGoods sog) {
		String[] row = new String[columnNames.length];
		row[0] = sog.getCurtainLocation();
		row[1] = sog.getCurtainStyle();
		row[2] = sog.getHightLocation();
		row[3] = sog.getCurtainHight();
		row[4] = sog.getCurtainWidth();
		row[5] = sog.getClothSerialNumber();
		row[6] = sog.getCurtainLaceSerialNumber();
		row[7] = sog.getCurtainRodSerialNumber();
		row[8] = sog.getCurtainRingSerialNumber();
		return row;
	}

	private void addCustomerActionPerformed(ActionEvent e) {

		String address = customerAdr.getText().trim();
		if (address != null && customerName != null && customerTel1 != null
				&& customerDeposit != null) {
			currentCC.setName(customerName.getText().trim());
			currentCC.setTel1(customerTel1.getText().trim());
			currentCC.setTel2(customerTel2.getText().trim());
			currentCC.setAddress(address);
			currentSol.setCustomer(currentCC);
			currentSol.setCustomerDeposit(customerDeposit.getText().trim());
			currentSol.setSubmitTime(new Date());
			currentSol.setOrderState(40);
			currentSol.setSalePerson(Global.CURRENTUSER);
		} else {
			JOptionPane.showMessageDialog(null, "请完善客户信息");
		}

	}

	@SuppressWarnings("unchecked")
	private void initGoodLst() {
		goodsLst = (List<CurtainShopGoods>) DBUtil.getLstClass("serialNumber",
				"eq", CurtainShopGoods.class, "curtainShop", "凯妮丝二店", "String");
	}

	private void reBuildGoodLst(String index) {
		List<CurtainShopGoods> indexLst = new ArrayList<CurtainShopGoods>();
		for (int i = 0; i < goodsLst.size(); i++) {
			CurtainShopGoods c = goodsLst.get(i);
			if (c.getSerialNumber().contains(index)) {
				indexLst.add(c);
			}
		}
		UIutil.initCurtainShopGoodsLstFromName(this, saleGoodjList, indexLst);
	}

	private void initTable() {

		String[][] tableVales = new String[][] {};
		tableModel = new DefaultTableModel(tableVales, columnNames);
		saleTable.setModel(tableModel);
		saleTable.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				tablemouseClicked(e);
			}
		});
		saleTable.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		int[] width = { 69, 78, 58, 38, 38, 120, 120, 120, 120, };
		for (int i = 0; i < width.length; i++) {
			TableColumn col0 = saleTable.getColumnModel().getColumn(i);
			col0.setPreferredWidth(width[i]);
		}
	}

	protected void tablemouseClicked(MouseEvent e) {
		selectedRow = saleTable.getSelectedRow();
		if (selectedRow != -1) {
			SaleOrderGoods sog = (SaleOrderGoods) currentSol.getGoodsLst().get(
					selectedRow);
			curtainClothName.setText(sog.getClothSerialNumber());
			curtainTapeName.setText(sog.getCurtainTapeSerialNumber());
			curtainLaceName.setText(sog.getCurtainLaceSerialNumber());
			curtainRingName.setText(sog.getCurtainRingSerialNumber());
			curtainRodName.setText(sog.getCurtainRodSerialNumber());

			curtainClothPrice.setText(sog.getClothSellingPrice() + "");
			curtainTapePrice.setText(sog.getCurtainTapeSellingPrice() + "");
			curtainLacePrice.setText(sog.getCurtainLaceSellingPrice() + "");
			curtainRingPrice.setText(sog.getCurtainRingSellingPrice() + "");
			curtainRodPrice.setText(sog.getCurtainRodSellingPrice() + "");

			curtainClothRemark.setText(sog.getClothRemark());
			curtainTapeRemark.setText(sog.getCurtainTapeRemark());
			curtainLaceRemark.setText(sog.getClothRemark());
			curtainRingRemark.setText(sog.getClothRemark());
			curtainRodRemark.setText(sog.getClothRemark());

			curtainHight.setText(sog.getCurtainHight());
			curtainWidth.setText(sog.getCurtainWidth());

			String l = sog.getCurtainLocation();
			if (l.equals("客厅") || l.equals("阳台") || l.equals("主卧")
					|| l.equals("次卧") || l.equals("后卧") || l.equals("餐厅")
					|| l.equals("厨房") || l.equals("卫生间")) {
				curtainLocationCB.setSelectedItem(l);
			}else{
				curtainLocation.setText(sog.getCurtainLocation());
			}

			hightLocationCB.setSelectedItem(sog.getHightLocation());
			curtainStyleCB.setSelectedItem(sog.getCurtainStyle());
		}
	}

	@Override
	public void valueChanged(ListSelectionEvent e) {
		if (e.getValueIsAdjusting()) {
			if (e.getSource().equals(saleGoodjList)) {
				String s = saleGoodjList.getSelectedValue().toString();
				if (s.contains("A-")) {
					curtainClothName.setText(s);
				} else if (s.contains("C-孔")) {
					curtainRingName.setText(s);
				} else if (s.contains("C-布带")) {
					curtainTapeName.setText(s);
				} else if (s.contains("C-杆")) {
					curtainRodName.setText(s);
				} else if (s.contains("C-花边")) {
					curtainLaceName.setText(s);
				}
			} else if (e.getSource().equals(saleLatelyjList)) {
				for (int i = 0; i < tableModel.getRowCount();) {
					tableModel.removeRow(0);
				}
				int index = saleLatelyjList.getSelectedIndex();
				currentSol = solLatelyLst.get(index);
				currentCC = currentSol.getCustomer();
				customerName.setText(currentCC.getName());
				customerAdr.setText(currentCC.getAddress());
				customerTel1.setText(currentCC.getTel1());
				customerTel2.setText(currentCC.getTel2());
				customerDeposit.setText(currentSol.getCustomerDeposit());
				for (int i = 0; i < currentSol.getGoodsLst().size(); i++) {
					String[] rowValues = creatRow((SaleOrderGoods) currentSol
							.getGoodsLst().get(i));
					tableModel.addRow(rowValues);
				}
				clearGoodsTable();
			}
		}
	}

	private void clearGoodsTable() {
		curtainClothName.setText("");
		curtainTapeName.setText("");
		curtainLaceName.setText("");
		curtainRingName.setText("");
		curtainRodName.setText("");

		curtainClothPrice.setText("");
		curtainTapePrice.setText("");
		curtainLacePrice.setText("");
		curtainRingPrice.setText("");
		curtainRodPrice.setText("");

		curtainClothRemark.setText("");
		curtainTapeRemark.setText("");
		curtainLaceRemark.setText("");
		curtainRingRemark.setText("");
		curtainRodRemark.setText("");

		curtainHight.setText("");
		curtainWidth.setText("");
		curtainLocation.setText("");
		hightLocationCB.setSelectedIndex(0);
		curtainStyleCB.setSelectedItem(0);
	}
}
