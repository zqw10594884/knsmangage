package com.zqw.ui;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.List;

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
import javax.swing.border.LineBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;

import com.zqw.bean.CheckListItem;
import com.zqw.bean.CurtainShop;
import com.zqw.bean.Global;
import com.zqw.bean.OrderLst;
import com.zqw.bean.SaleOrderGoods;
import com.zqw.bean.SaleOrderLst;
import com.zqw.bean.User;
import com.zqw.util.DBUtil;
import com.zqw.util.HibUtil;
import com.zqw.util.UIutil;
import javax.swing.JPasswordField;
import javax.swing.JComboBox;

public class ManageSaleOrderWi extends JFrame implements ListSelectionListener {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JScrollPane scrollPane_3;
	private DefaultTableModel tableModel;

	private JTextField shopName;
	private JButton modifyBtn;
	private JButton submitOrderBtn;
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
	private JTextField textField;
	private JPasswordField password;
	private JTextField userName;
	private ActionListener submitOrderBtnAL;
	private JButton button;
	private JTable saleTable;
	private String[] columnNames = { "位置", "形式", "高度位置", "高度", "宽度", "窗帘布",
			"花边", "窗帘杆", "窗帘圈" };

	public ManageSaleOrderWi() {
		initComponents();
		initTable();
		initData();
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
					ManageSaleOrderWi frame = new ManageSaleOrderWi();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	@SuppressWarnings("unchecked")
	public void valueChanged(ListSelectionEvent e) {
		if (e.getValueIsAdjusting()) {
			for (int i = 0; i < tableModel.getRowCount();) {
				tableModel.removeRow(0);
			}
			int index = checkedjList.getSelectedIndex();
			for (int i = 0; i < retailLst.get(index).getGoodsLst().size(); i++) {
				String[] rowValues = creatRow((SaleOrderGoods) retailLst
						.get(index).getGoodsLst().get(i));
				tableModel.addRow(rowValues);
			}
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

	@SuppressWarnings("unchecked")
	private void initData() {
		checkedjList = new JList<>();
		initJlist();
		scrollPane_3.setViewportView(checkedjList);
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
		lblNewLabel.setBounds(24, 136, 54, 15);
		lblNewLabel.setFont(new Font("宋体", Font.PLAIN, 14));
		contentPane.add(lblNewLabel);

		shopName = new JTextField();
		shopName.setEditable(false);
		shopName.setBounds(67, 134, 130, 21);
		contentPane.add(shopName);
		shopName.setColumns(10);

		scrollPane_3 = new JScrollPane();
		scrollPane_3.setBounds(500, 10, 270, 344);
		contentPane.add(scrollPane_3);

		submitOrderBtnAL = new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				submitOrderactionPerformed(e);
			}
		};
		submitOrderBtn = new JButton("结账");
		submitOrderBtn.setEnabled(false);
		submitOrderBtn.setFont(new Font("宋体", Font.PLAIN, 14));
		submitOrderBtn.setBounds(408, 203, 62, 23);
		contentPane.add(submitOrderBtn);

		nameLab = new JLabel("");
		nameLab.setFont(new Font("仿宋", Font.BOLD, 30));
		nameLab.setBounds(24, 64, 343, 42);
		contentPane.add(nameLab);

		nameLab.setText("");

		JLabel label = new JLabel("地址：");
		label.setFont(new Font("宋体", Font.PLAIN, 14));
		label.setBounds(24, 163, 54, 15);
		contentPane.add(label);

		textField = new JTextField();
		textField.setEditable(false);
		textField.setColumns(10);
		textField.setBounds(67, 161, 262, 21);
		contentPane.add(textField);

		JLabel label_1 = new JLabel("用户名：");
		label_1.setFont(new Font("宋体", Font.PLAIN, 12));
		label_1.setBounds(24, 32, 54, 15);
		contentPane.add(label_1);

		userName = new JTextField();
		userName.setBounds(77, 29, 82, 21);
		contentPane.add(userName);
		userName.setColumns(10);

		JLabel label_2 = new JLabel("密码：");
		label_2.setFont(new Font("宋体", Font.PLAIN, 12));
		label_2.setBounds(179, 35, 54, 15);
		contentPane.add(label_2);

		password = new JPasswordField();
		password.setBounds(219, 30, 89, 18);
		contentPane.add(password);

		JButton btnNewButton = new JButton("登录");
		btnNewButton.setFont(new Font("宋体", Font.PLAIN, 12));
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String name = userName.getText().trim();
				String pass = password.getText().trim();
				userName.setText("");
				password.setText("");
				User user = (User) DBUtil.getClass(User.class, "name", name,
						"String", "eq");
				if (name.equals(user.getName())
						&& pass.equals(user.getPassword())) {
					if (user.getAuthority() == 22 || user.getAuthority() == 23) {
						submitOrderBtn.setEnabled(true);
						Global.User = user;
						nameLab.setText(user.getRealName());
						initJlist();
						if (submitOrderBtn.getActionListeners().length < 1) {
							submitOrderBtn.addActionListener(submitOrderBtnAL);
						}
					}
				}

			}
		});
		btnNewButton.setBounds(318, 28, 69, 23);
		contentPane.add(btnNewButton);

		button = new JButton("删除");
		button.setFont(new Font("宋体", Font.PLAIN, 14));
		button.setEnabled(false);
		button.setBounds(336, 203, 62, 23);
		contentPane.add(button);

		JComboBox comboBox = new JComboBox();
		comboBox.setBounds(24, 204, 76, 21);
		contentPane.add(comboBox);

		JButton button_1 = new JButton("更改状态");
		button_1.setFont(new Font("宋体", Font.PLAIN, 14));
		button_1.setEnabled(false);
		button_1.setBounds(120, 203, 89, 23);
		contentPane.add(button_1);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(6, 364, 764, 237);
		contentPane.add(scrollPane);

		saleTable = new JTable();
		saleTable.setToolTipText("窗帘");
		saleTable.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		scrollPane.setViewportView(saleTable);
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
			if (Global.User.getAuthority() == 20) {// 裁布
				if (currentRetailOrder.getLibraryPerson() == null
						|| currentRetailOrder.getLibraryPerson().length() == 0) {
					currentRetailOrder.setOrderState(35);
					currentRetailOrder.setLibraryPerson(Global.User.getName());
				}
			} else if (Global.User.getAuthority() == 21) {// 售货员
				if (currentRetailOrder.getLibraryPerson() == null
						|| currentRetailOrder.getLibraryPerson().length() == 0) {
				}
			} else if (Global.User.getAuthority() == 22) {// 裁缝
				if (currentRetailOrder.getMachiningPerson() == null
						|| currentRetailOrder.getLibraryPerson().length() == 0) {
					currentRetailOrder.setOrderState(21);
					currentRetailOrder
							.setMachiningPerson(Global.User.getName());
				}
			} else if (Global.User.getAuthority() == 23) {// 安装工
				if (currentRetailOrder.getInstallPerson() == null
						|| currentRetailOrder.getLibraryPerson().length() == 0) {
					currentRetailOrder.setOrderState(20);
					currentRetailOrder.setInstallPerson(Global.User.getName());
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
		UIutil.initRetailOrderJlist(this, checkedjList, listAdapter, true,
				retailLst);
	}

	private void addLatelyLstToMain(int index) {

		for (int i = 0; i < tableModel.getRowCount();) {
			tableModel.removeRow(0);
		}
		currentRetailOrder = retailLst.get(index);
		List<SaleOrderGoods> Lst = currentRetailOrder.getGoodsLst();

		for (int i = 0; i < Lst.size(); i++) {
			String[] rowValues = { Lst.get(i).getClothSerialNumber(),
					Lst.get(i).getClothNumber() + "",
					Lst.get(i).getClothRemark() };
			tableModel.addRow(rowValues); // 添加一行
		}
		shopName.setText(currentRetailOrder.getCustomer().getName());
	}

	protected void clearAll() {
		for (int i = 0; i < tableModel.getRowCount();) {
			tableModel.removeRow(0);
		}
		saleTable.setModel(new DefaultTableModel());
	}
}
