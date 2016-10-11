package com.zqw.ui;

import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;

import com.zqw.bean.CurtainShop;
import com.zqw.bean.Global;
import com.zqw.bean.OrderLst;
import com.zqw.bean.SaleOrderGoods;
import com.zqw.bean.SaleOrderLst;
import com.zqw.util.DBUtil;
import com.zqw.util.HibUtil;
import com.zqw.util.UIutil;

public class ManageSaleOrderWi extends JFrame implements ListSelectionListener {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JScrollPane scrollPane_3;
	private DefaultTableModel tableModel;

	private JTextField customerName;
	private JButton modifyBtn;
	private JButton submitBtn;
	private ActionListener modifyAL;
	private ActionListener submitOrderAL;
	private MouseAdapter listAdapter;
	private CurtainShop curtainShop = null;
	private int latelyLstindex = -1;
	private JList<String> checkedjList;
	// private ArrayList<OrderLst> wholesaleLst;
	private ArrayList<SaleOrderLst> retailLst;
	private ArrayList<OrderLst> pandectList = new ArrayList<OrderLst>();
	private SaleOrderLst currentRetailOrder;
	private JLabel nameLab;
	private final ButtonGroup buttonGroup = new ButtonGroup();
	private JTextField customerAdr;
	private ActionListener submitBtnAL;
	private JButton delBtn;
	private JTable saleTable;
	private String[] columnNames = { "位置", "形式", "高度位置", "高度", "宽度", "窗帘布",
			"花边", "窗帘杆", "窗帘圈" };
	private ActionListener delBtnAl;
	private JButton submitOrderBtn;
	private ActionListener submitOrderBtnAl;
	private JTextField customerTel1;
	private JTextField customerTel2;
	private JTextField customerDeposit;
	private JTextField textField_3;
	private JTextField salePersonTF;
	private JTextField measurePersonTF;
	private JTextField libraryPersonTF;
	private JTextField machiningPersonTF;
	private JTextField examinationClerkTF;
	private JTextField installPersonTF;

	public ManageSaleOrderWi(int power) {
		initComponents();
		initTable();
		initData(power);
	}

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					// User user = (User) DBUtil.getClass(User.class, "name",
					// "chen", "String", "eq");
					// Global.User = user;
					ManageSaleOrderWi frame = new ManageSaleOrderWi(0);
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	@SuppressWarnings("unchecked")
	public void valueChanged(ListSelectionEvent e) {
//		if (e.getValueIsAdjusting()) {
//			for (int i = 0; i < tableModel.getRowCount();) {
//				tableModel.removeRow(0);
//			}
//			int index = checkedjList.getSelectedIndex();
//			for (int i = 0; i < retailLst.get(index).getGoodsLst().size(); i++) {
//				String[] rowValues = creatRow((SaleOrderGoods) retailLst
//						.get(index).getGoodsLst().get(i));
//				tableModel.addRow(rowValues);
//			}
//		}
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

	@SuppressWarnings("unchecked")
	private void initData(int power) {
		checkedjList = new JList<>();
		initJlist();
		scrollPane_3.setViewportView(checkedjList);
		if (power == 0) {
			delBtn.addActionListener(delBtnAl);
			delBtn.setEnabled(true);
			submitOrderBtn.addActionListener(submitOrderBtnAl);
			submitOrderBtn.setEnabled(true);
			submitBtn.addActionListener(submitOrderBtnAl);
			submitBtn.setEnabled(true);
		} else if (power == 1) {
		}
	}

	private void initTable() {

		String[][] tableVales = new String[][] {};
		tableModel = new DefaultTableModel(tableVales, columnNames);
		saleTable.setModel(tableModel);
		saleTable.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		int[] width = { 69, 78, 58, 38, 38, 120, 120, 120, 120, };
		for (int i = 0; i < width.length; i++) {
			TableColumn col0 = saleTable.getColumnModel().getColumn(i);
			col0.setPreferredWidth(width[i]);
		}
	}

	/**
	 * Create the frame.
	 */

	private void initComponents() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 796, 649);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel lblNewLabel = new JLabel("姓名：");
		lblNewLabel.setBounds(27, 103, 54, 15);
		lblNewLabel.setFont(new Font("宋体", Font.PLAIN, 14));
		contentPane.add(lblNewLabel);

		customerName = new JTextField();
		customerName.setEditable(false);
		customerName.setBounds(70, 101, 130, 21);
		contentPane.add(customerName);
		customerName.setColumns(10);

		scrollPane_3 = new JScrollPane();
		scrollPane_3.setBounds(500, 10, 270, 321);
		contentPane.add(scrollPane_3);

		submitBtnAL = new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				submitOrderactionPerformed(e);
			}
		};
		submitBtn = new JButton("提交");
		submitBtn.setEnabled(false);
		submitBtn.setFont(new Font("宋体", Font.PLAIN, 14));
		submitBtn.setBounds(27, 297, 62, 23);
		contentPane.add(submitBtn);

		nameLab = new JLabel("");
		nameLab.setFont(new Font("仿宋", Font.BOLD, 30));
		nameLab.setBounds(24, 20, 343, 42);
		contentPane.add(nameLab);

		nameLab.setText("");

		JLabel label = new JLabel("地址：");
		label.setFont(new Font("宋体", Font.PLAIN, 14));
		label.setBounds(27, 163, 54, 15);
		contentPane.add(label);

		customerAdr = new JTextField();
		customerAdr.setEditable(false);
		customerAdr.setColumns(10);
		customerAdr.setBounds(70, 161, 262, 21);
		contentPane.add(customerAdr);

		delBtn = new JButton("删除");
		delBtnAl = new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				currentRetailOrder = retailLst.get(checkedjList
						.getSelectedIndex());
				DBUtil.del(currentRetailOrder);
			}
		};

		delBtn.setFont(new Font("宋体", Font.PLAIN, 14));
		delBtn.setEnabled(false);
		delBtn.setBounds(108, 297, 62, 23);
		contentPane.add(delBtn);

		submitOrderBtn = new JButton("结账");
		submitOrderBtnAl = new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				currentRetailOrder = retailLst.get(checkedjList
						.getSelectedIndex());
				currentRetailOrder.setOrderState(10);
				DBUtil.update(currentRetailOrder);
			}
		};

		submitOrderBtn.setFont(new Font("宋体", Font.PLAIN, 14));
		submitOrderBtn.setEnabled(false);
		submitOrderBtn.setBounds(187, 297, 62, 23);
		contentPane.add(submitOrderBtn);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(6, 341, 764, 260);
		contentPane.add(scrollPane);

		saleTable = new JTable();
		saleTable.setToolTipText("窗帘");
		saleTable.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		scrollPane.setViewportView(saleTable);

		customerTel1 = new JTextField();
		customerTel1.setEditable(false);
		customerTel1.setColumns(10);
		customerTel1.setBounds(70, 132, 130, 21);
		contentPane.add(customerTel1);

		JLabel label_1 = new JLabel("电话：");
		label_1.setFont(new Font("宋体", Font.PLAIN, 14));
		label_1.setBounds(27, 134, 54, 15);
		contentPane.add(label_1);

		customerTel2 = new JTextField();
		customerTel2.setEditable(false);
		customerTel2.setColumns(10);
		customerTel2.setBounds(210, 132, 130, 21);
		contentPane.add(customerTel2);

		JLabel label_2 = new JLabel("订金：");
		label_2.setFont(new Font("宋体", Font.PLAIN, 14));
		label_2.setBounds(210, 103, 54, 15);
		contentPane.add(label_2);

		customerDeposit = new JTextField();
		customerDeposit.setEditable(false);
		customerDeposit.setColumns(10);
		customerDeposit.setBounds(253, 101, 79, 21);
		contentPane.add(customerDeposit);

		JLabel label_3 = new JLabel("备注：");
		label_3.setFont(new Font("宋体", Font.PLAIN, 14));
		label_3.setBounds(27, 188, 54, 15);
		contentPane.add(label_3);

		textField_3 = new JTextField();
		textField_3.setEditable(false);
		textField_3.setColumns(10);
		textField_3.setBounds(70, 188, 412, 21);
		contentPane.add(textField_3);

		JLabel label_4 = new JLabel("销售：");
		label_4.setFont(new Font("宋体", Font.PLAIN, 14));
		label_4.setBounds(27, 230, 54, 15);
		contentPane.add(label_4);

		salePersonTF = new JTextField();
		salePersonTF.setEditable(false);
		salePersonTF.setColumns(10);
		salePersonTF.setBounds(70, 224, 62, 21);
		contentPane.add(salePersonTF);

		measurePersonTF = new JTextField();
		measurePersonTF.setEditable(false);
		measurePersonTF.setColumns(10);
		measurePersonTF.setBounds(197, 224, 62, 21);
		contentPane.add(measurePersonTF);

		JLabel label_5 = new JLabel("尺寸：");
		label_5.setFont(new Font("宋体", Font.PLAIN, 14));
		label_5.setBounds(154, 230, 54, 15);
		contentPane.add(label_5);

		libraryPersonTF = new JTextField();
		libraryPersonTF.setEditable(false);
		libraryPersonTF.setColumns(10);
		libraryPersonTF.setBounds(325, 224, 62, 21);
		contentPane.add(libraryPersonTF);

		JLabel label_6 = new JLabel("裁布：");
		label_6.setFont(new Font("宋体", Font.PLAIN, 14));
		label_6.setBounds(282, 230, 54, 15);
		contentPane.add(label_6);

		machiningPersonTF = new JTextField();
		machiningPersonTF.setEditable(false);
		machiningPersonTF.setColumns(10);
		machiningPersonTF.setBounds(70, 255, 62, 21);
		contentPane.add(machiningPersonTF);

		JLabel label_7 = new JLabel("加工：");
		label_7.setFont(new Font("宋体", Font.PLAIN, 14));
		label_7.setBounds(27, 261, 54, 15);
		contentPane.add(label_7);

		examinationClerkTF = new JTextField();
		examinationClerkTF.setEditable(false);
		examinationClerkTF.setColumns(10);
		examinationClerkTF.setBounds(197, 255, 62, 21);
		contentPane.add(examinationClerkTF);

		JLabel label_8 = new JLabel("检验：");
		label_8.setFont(new Font("宋体", Font.PLAIN, 14));
		label_8.setBounds(154, 261, 54, 15);
		contentPane.add(label_8);

		installPersonTF = new JTextField();
		installPersonTF.setEditable(false);
		installPersonTF.setColumns(10);
		installPersonTF.setBounds(325, 255, 62, 21);
		contentPane.add(installPersonTF);

		JLabel label_9 = new JLabel("安装：");
		label_9.setFont(new Font("宋体", Font.PLAIN, 14));
		label_9.setBounds(282, 261, 54, 15);
		contentPane.add(label_9);
		listAdapter = new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				int index = checkedjList.getSelectedIndex();
				addLatelyLstToMain(index);
			}
		};
	}

	/**
	 * 添加修改删除按钮
	 */
	private void addModifyBtnAndDeleteBtn() {
		if (modifyBtn.getActionListeners().length == 0) {
			modifyBtn.addActionListener(modifyAL);
			modifyBtn.setEnabled(true);
		}
	}

	/**
	 * 提交订单
	 * 
	 * @param e
	 */
	private void submitOrderactionPerformed(ActionEvent e) {
		if (tableModel.getRowCount() > 0) {
			if (Global.User.getAuthority() == 21) {// 售货员
				if (currentRetailOrder.getLibraryPerson() == null
						|| currentRetailOrder.getLibraryPerson().length() == 0) {
					if (salePersonTF.getText().length() == 0) {
						currentRetailOrder.setSalePerson(Global.User.getName());
					}
				}
			} else if (Global.User.getAuthority() == 22) {// 裁缝
				if (currentRetailOrder.getMachiningPerson() == null
						|| currentRetailOrder.getLibraryPerson().length() == 0) {
					if (currentRetailOrder.getOrderState() == 35
							&& machiningPersonTF.getText().length() == 0) {
						currentRetailOrder.setOrderState(30);
						currentRetailOrder.setMachiningPerson(Global.User
								.getName());
					}
				}
			} else if (Global.User.getAuthority() == 23) {// 安装工
				if (currentRetailOrder.getInstallPerson() == null
						|| currentRetailOrder.getLibraryPerson().length() == 0) {
					if (currentRetailOrder.getOrderState() == 30
							&& installPersonTF.getText().length() == 0) {
						currentRetailOrder.setOrderState(20);
						currentRetailOrder.setInstallPerson(Global.User
								.getName());
					}
				}
			} else if (Global.User.getAuthority() == 24) {// 检验
				if (currentRetailOrder.getInstallPerson() == null
						|| currentRetailOrder.getLibraryPerson().length() == 0) {
					// currentRetailOrder.setInstallPerson(Global.User.getName());
				}
			}else if (Global.User.getAuthority() < 20) {// 结账
				if (currentRetailOrder.getInstallPerson() == null
						|| currentRetailOrder.getLibraryPerson().length() == 0) {
					 
				}
			}
			initJlist();
			DBUtil.update(currentRetailOrder);
		} else {

		}
	}

	private void initJlist() {
		if (retailLst == null) {
			Session session = HibUtil.getSession();
			Criteria c = session.createCriteria(SaleOrderLst.class);
			c.add(Restrictions.gt("orderState", 34));
		}
		retailLst = (ArrayList<SaleOrderLst>) UIutil.initRetailOrderJlist(this,
				checkedjList, listAdapter, true, retailLst);
	}

	private void addLatelyLstToMain(int index) {
		for (int i = 0; i < tableModel.getRowCount();) {
			tableModel.removeRow(0);
		}
		currentRetailOrder = retailLst.get(index);

		customerName.setText(currentRetailOrder.getCustomer().getName());
		customerAdr.setText(currentRetailOrder.getCustomer().getAddress());
		for (int i = 0; i < currentRetailOrder.getGoodsLst().size(); i++) {
			String[] rowValues = creatRow((SaleOrderGoods) currentRetailOrder
					.getGoodsLst().get(i));
			tableModel.addRow(rowValues);
		}

		// for (int i = 0; i < tableModel.getRowCount();) {
		// tableModel.removeRow(0);
		// }
		// currentRetailOrder = retailLst.get(index);
		// List<SaleOrderGoods> Lst = currentRetailOrder.getGoodsLst();
		//
		// for (int i = 0; i < Lst.size(); i++) {
		// String[] rowValues = { Lst.get(i).getClothSerialNumber(),
		// Lst.get(i).getClothNumber() + "",
		// Lst.get(i).getClothRemark() };
		// tableModel.addRow(rowValues); // 添加一行
		// }
		// shopName.setText(currentRetailOrder.getCustomer().getName());
	}

	protected void clearAll() {
		for (int i = 0; i < tableModel.getRowCount();) {
			tableModel.removeRow(0);
		}
		saleTable.setModel(new DefaultTableModel());
	}
}
