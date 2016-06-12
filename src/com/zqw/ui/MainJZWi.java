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

public class MainJZWi extends JFrame implements ListSelectionListener {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JScrollPane scrollPane;
	private JScrollPane scrollPane_3;
	private DefaultTableModel tableModel;
	private JTable table;

	private JTextField shopName;
	private JButton modifyBtn;
	private JButton submitOrderBtn;
	private ActionListener modifyAL;
	private ActionListener submitOrderAL;
	private MouseAdapter listAdapter;
	private CurtainShop curtainShop = null;
	private int latelyLstindex = -1;
	private JList<CheckListItem> checkedjList;
	private ArrayList<OrderLst> wholesaleLst;
	private ArrayList<SaleOrderLst> retailLst;
	private ArrayList<OrderLst> pandectList = new ArrayList<OrderLst>();
	private SaleOrderLst currentRetailOrder;
	private JLabel nameLab;
	private final ButtonGroup buttonGroup = new ButtonGroup();
	private JTextField textField;
	private JPasswordField password;
	private JTextField userName;
	private ActionListener submitOrderBtnAL;

	public MainJZWi() {
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
					MainJZWi frame = new MainJZWi();
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

		}
	}

	@SuppressWarnings("unchecked")
	private void initData() {
		checkedjList = new JList<>();
		listAdapter = new MouseAdapter() {
			public void mouseClicked(MouseEvent event) {
				JList<CheckListItem> list = (JList<CheckListItem>) event
						.getSource();
				// Get index of item clicked
				// 获得用户点击项的索引
				latelyLstindex = list.locationToIndex(event.getPoint());
				if (event.getX() < 17) {
					CheckListItem item = list.getModel().getElementAt(
							latelyLstindex);
					// 设置列表中项的选择状态
					item.setSelected(!item.isSelected());
				} else {
					addLatelyLstToMain(latelyLstindex);
				}
				// 重新绘制列表中项
				list.repaint(list.getCellBounds(latelyLstindex, latelyLstindex));
			}

			public void mousePressed(MouseEvent e) {
			}

			public void mouseReleased(MouseEvent e) {
			}
		};
		// initJlist();
		scrollPane_3.setViewportView(checkedjList);
	}

	private void initTable() {
		String[] columnNames = { "编号", "数量", "备注" };
		String[][] tableVales = {};
		tableModel = new DefaultTableModel(tableVales, columnNames);

		table.setModel(tableModel);
		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				int selectedRow = table.getSelectedRow();
				if (selectedRow != -1) {
					modifyBtn.getActionListeners();
					addModifyBtnAndDeleteBtn();
				}
			}
		});
		TableColumn col0 = table.getColumnModel().getColumn(0);
		col0.setPreferredWidth(100);
		TableColumn col1 = table.getColumnModel().getColumn(1);
		col1.setPreferredWidth(50);
		TableColumn col2 = table.getColumnModel().getColumn(2);
		col2.setPreferredWidth(50);
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
		lblNewLabel.setBounds(24, 179, 54, 15);
		lblNewLabel.setFont(new Font("宋体", Font.PLAIN, 14));
		contentPane.add(lblNewLabel);

		shopName = new JTextField();
		shopName.setEditable(false);
		shopName.setBounds(67, 177, 130, 21);
		contentPane.add(shopName);
		shopName.setColumns(10);

		scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 236, 480, 333);
		contentPane.add(scrollPane);
		table = new JTable();

		table.setBounds(10, 10, 401, 119);
		table.setBorder(new LineBorder(new Color(0, 0, 0)));
		scrollPane.setViewportView(table);

		scrollPane_3 = new JScrollPane();
		scrollPane_3.setBounds(500, 37, 270, 532);
		contentPane.add(scrollPane_3);

		submitOrderBtnAL = new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				submitOrderactionPerformed(e);
			}
		};
		submitOrderBtn = new JButton("提交");
		submitOrderBtn.setEnabled(false);
		submitOrderBtn.setFont(new Font("宋体", Font.PLAIN, 14));
		submitOrderBtn.setBounds(408, 203, 62, 23);
		contentPane.add(submitOrderBtn);

		nameLab = new JLabel("");
		nameLab.setFont(new Font("仿宋", Font.BOLD, 30));
		nameLab.setBounds(24, 99, 343, 42);
		contentPane.add(nameLab);

		nameLab.setText("");

		JLabel label = new JLabel("地址：");
		label.setFont(new Font("宋体", Font.PLAIN, 14));
		label.setBounds(24, 206, 54, 15);
		contentPane.add(label);

		textField = new JTextField();
		textField.setEditable(false);
		textField.setColumns(10);
		textField.setBounds(67, 204, 262, 21);
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
			if (Global.User.getAuthority() == 20) {// 裁布
				c.add(Restrictions.gt("orderState", 34));
			} else if (Global.User.getAuthority() == 21) {// 售货员
				c.add(Restrictions.gt("orderState", 19));
			} else if (Global.User.getAuthority() == 22) {// 裁缝
				c.add(Restrictions.and(Restrictions.gt("orderState", 29),
						Restrictions.lt("orderState", 36)));
			} else if (Global.User.getAuthority() == 23) {// 安装工
				c.add(Restrictions.and(Restrictions.gt("orderState", 19),
						Restrictions.lt("orderState", 31)));
			}
			retailLst = (ArrayList<SaleOrderLst>) DBUtil
					.getLstClass(session, c);
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
}
