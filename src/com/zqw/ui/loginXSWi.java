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

public class loginXSWi extends JFrame {

	private JPanel contentPane;
	private JTextField userName;
	private JPasswordField password;
	private JRadioButton orderRa;
	private JRadioButton checkRa;
	private JButton loginBtn;
	private ButtonGroup buttonGroup1;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					loginXSWi frame = new loginXSWi();
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
		User user = (User) DBUtil.getClass(User.class, "name", name, "String",
				"eq");
		if (name.equals(user.getName()) && pass.equals(user.getPassword())) {
			Enumeration<AbstractButton> enu = buttonGroup1.getElements();
			while (enu.hasMoreElements()) {
				AbstractButton radioButton = enu.nextElement();
				Global.User = user;
				if ("orderRa".equals(radioButton.getName())
						&& radioButton.isSelected()
						&& (user.getAuthority() == 21)) {
					java.awt.EventQueue.invokeLater(new Runnable() {
						public void run() {
							SaleMain mainAdd = new SaleMain();
							mainAdd.setVisible(true);
							mainAdd.setLocationRelativeTo(null);
						}
					});
					this.dispose();
				} else if (radioButton.getName() == "checkRa"
						&& radioButton.isSelected()
						&& (user.getAuthority() == 21)) {
					java.awt.EventQueue.invokeLater(new Runnable() {
						public void run() {
							MainJZWi main = new MainJZWi();
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
	public loginXSWi() {
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
		orderRa.setBounds(91, 43, 65, 23);
		contentPane.add(orderRa);

		checkRa = new JRadioButton();
		buttonGroup1.add(checkRa);
		checkRa.setText("结账");
		checkRa.setName("library");
		checkRa.setBounds(158, 43, 60, 23);
		contentPane.add(checkRa);

		userName = new JTextField();
		userName.setBounds(118, 89, 128, 21);
		contentPane.add(userName);

		JLabel label = new JLabel();
		label.setText("用户名：");
		label.setBounds(45, 89, 54, 15);
		contentPane.add(label);

		JLabel label_1 = new JLabel();
		label_1.setText("密  码：");
		label_1.setBounds(45, 116, 54, 15);
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
		loginBtn.setBounds(118, 175, 84, 36);
		contentPane.add(loginBtn);
	}
}
