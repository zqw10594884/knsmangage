package com.zqw.ui;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Enumeration;

import javax.swing.AbstractButton;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import com.zqw.bean.Global;
import com.zqw.bean.User;
import com.zqw.util.DBUtil;

public class loginWi extends JFrame {

	private JPanel contentPane;
	private JTextField userName;
	private JPasswordField password;
	private JRadioButton orderRa;
	private JRadioButton orderCheck;
	private JRadioButton manageRa;
	private JRadioButton orderingRa;
	private JButton loginBtn;
	private ButtonGroup buttonGroup1;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					loginWi frame = new loginWi();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	private void loginBtnActionPerformed(java.awt.event.ActionEvent evt) {
		String name = userName.getText().trim();
		String pass = password.getText().trim();
		User user = (User)DBUtil.getClass(User.class, "name", name,"String" , "eq");
		if (name.equals(user.getName()) && pass.equals(user.getPassword())) {
			Enumeration<AbstractButton> enu = buttonGroup1.getElements();
			while (enu.hasMoreElements()) {
				AbstractButton radioButton = enu.nextElement();
				Global.CURRENTUSER = user.getRealName();
				if (radioButton.getName() == "manage"
						&& radioButton.isSelected() && user.getAuthority() < 20) {
					java.awt.EventQueue.invokeLater(new Runnable() {
						public void run() {
							MainAddWi mainAdd = new MainAddWi();
							mainAdd.setVisible(true);
							mainAdd.setLocationRelativeTo(null);
						}
					});
					this.dispose();
				} else if (radioButton.getName() == "order"
						&& radioButton.isSelected() && user.getAuthority() < 20) {
					java.awt.EventQueue.invokeLater(new Runnable() {
						public void run() {
							MainWi main = new MainWi();
							main.setVisible(true);
							main.setLocationRelativeTo(null);
						}
					});
					this.dispose();
				} else if (radioButton.getName() == "ordering"
						&& radioButton.isSelected() && user.getAuthority() < 20) {
					java.awt.EventQueue.invokeLater(new Runnable() {
						public void run() {
							ManageGoods main = new ManageGoods();
							main.setVisible(true);
							main.setLocationRelativeTo(null);
						}
					});
					this.dispose();
				} else if (radioButton.getName() == "orderCheck"
						&& radioButton.isSelected() && user.getAuthority() < 20) {
					java.awt.EventQueue.invokeLater(new Runnable() {
						public void run() {
							ManageOrderWi main = new ManageOrderWi();
							main.setVisible(true);
							main.setLocationRelativeTo(null);
						}
					});
					this.dispose();
				} else {

				}

			}

		}
	}

	/**
	 * Create the frame.
	 */
	public loginWi() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 344, 277);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		buttonGroup1 = new javax.swing.ButtonGroup();

		orderRa = new JRadioButton();
		buttonGroup1.add(orderRa);
		orderRa.setText("订单");
		orderRa.setSelected(true);
		orderRa.setName("order");
		orderRa.setBounds(40, 38, 65, 23);
		contentPane.add(orderRa);

		orderCheck = new JRadioButton();
		buttonGroup1.add(orderCheck);
		orderCheck.setText("结账");
		orderCheck.setName("orderCheck");
		orderCheck.setBounds(107, 38, 60, 23);
		contentPane.add(orderCheck);

		orderingRa = new JRadioButton();
		buttonGroup1.add(orderingRa);
		orderingRa.setText("订货");
		orderingRa.setName("ordering");
		orderingRa.setBounds(229, 38, 65, 23);
		contentPane.add(orderingRa);

		manageRa = new JRadioButton();
		buttonGroup1.add(manageRa);
		manageRa.setText("管理");
		manageRa.setName("manage");
		manageRa.setBounds(169, 38, 58, 23);
		contentPane.add(manageRa);

		userName = new JTextField();
		userName.setBounds(118, 89, 128, 21);
		contentPane.add(userName);

		JLabel label = new JLabel();
		label.setText("用户名：");
		label.setBounds(60, 92, 54, 15);
		contentPane.add(label);

		JLabel label_1 = new JLabel();
		label_1.setText("密   码：");
		label_1.setBounds(60, 119, 54, 15);
		contentPane.add(label_1);

		password = new JPasswordField();
		password.setBounds(118, 116, 128, 21);
		contentPane.add(password);

		loginBtn = new JButton();
		loginBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				loginBtnActionPerformed(e);
			}
		});
		loginBtn.setText("登录");
		loginBtn.setBounds(114, 164, 84, 36);
		contentPane.add(loginBtn);
	}
}
