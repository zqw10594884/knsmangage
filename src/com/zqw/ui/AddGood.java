package com.zqw.ui;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JTextField;

import com.zqw.bean.Global;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class AddGood extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JTextField numberTF;
	private JTextField telephoneTF;
	private JTextField factoryTF;
	private JTextField purchasePriceTF;
	private JTextField serialNumberTF;
	private JButton addBtn;
	private JButton cancelButton;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			AddGood dialog = new AddGood(new JFrame(), true);
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 * 
	 * @param b
	 * @param jFrame
	 */
	public AddGood(JFrame parent, boolean modal) {
		super(parent, modal);
		setBounds(100, 100, 233, 228);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		{
			JLabel label = new JLabel();
			label.setText("型 号：");
			label.setBounds(10, 13, 42, 15);
			contentPanel.add(label);
		}
		{
			JLabel label = new JLabel();
			label.setText("进 价：");
			label.setBounds(10, 40, 42, 15);
			contentPanel.add(label);
		}
		{
			JLabel label = new JLabel();
			label.setText("厂 家：");
			label.setBounds(11, 67, 42, 15);
			contentPanel.add(label);
		}
		{
			JLabel label = new JLabel();
			label.setText("电 话：");
			label.setBounds(11, 94, 42, 15);
			contentPanel.add(label);
		}
		{
			JLabel label = new JLabel();
			label.setText("数 量：");
			label.setBounds(11, 125, 42, 15);
			contentPanel.add(label);
		}
		{
			numberTF = new JTextField();
			numberTF.setBounds(57, 122, 136, 21);
			contentPanel.add(numberTF);
		}
		{
			telephoneTF = new JTextField();
			telephoneTF.setBounds(57, 91, 136, 21);
			contentPanel.add(telephoneTF);
		}
		{
			factoryTF = new JTextField();
			factoryTF.setBounds(57, 64, 136, 21);
			contentPanel.add(factoryTF);
		}
		{
			purchasePriceTF = new JTextField();
			purchasePriceTF.setBounds(56, 37, 137, 21);
			contentPanel.add(purchasePriceTF);
		}
		{
			serialNumberTF = new JTextField();
			serialNumberTF.setBounds(56, 10, 137, 21);
			contentPanel.add(serialNumberTF);
		}
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				addBtn = new JButton("添加");
				addBtn.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						addActionPerformed(e);
					}
				});
				addBtn.setActionCommand("OK");
				buttonPane.add(addBtn);
				getRootPane().setDefaultButton(addBtn);
			}
			{
				cancelButton = new JButton("关闭");
				cancelButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						cancelButtonActionPerformed(e);
					}
				});
				cancelButton.setActionCommand("Cancel");
				buttonPane.add(cancelButton);
			}
		}
	}

	private void cancelButtonActionPerformed(java.awt.event.ActionEvent evt) {
		this.dispose();
	}

	private void addActionPerformed(java.awt.event.ActionEvent evt) {
		if (serialNumberTF.getText().trim().length() != 0) {
			Global.goods.setSerialNumber(serialNumberTF.getText());
		} else {
			JOptionPane.showMessageDialog(this, "请输入型号", "alert",
					JOptionPane.ERROR_MESSAGE);
			Global.goods = null;
			this.dispose();
			return;
		}
		if (purchasePriceTF.getText().trim().length() != 0) {
			Global.goods.setPurchasePrice(Double.parseDouble(purchasePriceTF
					.getText().trim()));
		} else {
			Global.goods = null;
			JOptionPane.showMessageDialog(this, "请输入价格", "alert",
					JOptionPane.ERROR_MESSAGE);
			this.dispose();
			return;
		}
		if (factoryTF.getText().trim().length() != 0) {
			Global.goods.setFactory(factoryTF.getText().trim());
		} else {
			Global.goods = null;
			JOptionPane.showMessageDialog(this, "请输入厂家", "alert",
					JOptionPane.ERROR_MESSAGE);
			this.dispose();
			return;
		}
		if (telephoneTF.getText().trim().length() != 0) {
			Global.goods.setTelephone(telephoneTF.getText().trim());
		} else {
			Global.goods = null;
			JOptionPane.showMessageDialog(this, "请输入电话", "alert",
					JOptionPane.ERROR_MESSAGE);
			this.dispose();
			return;
		}
		if (numberTF.getText().trim().length() != 0) {
			Global.goods.setNumber(numberTF.getText().trim());
		} else {
			Global.goods = null;
			JOptionPane.showMessageDialog(this, "请输入电话", "alert",
					JOptionPane.ERROR_MESSAGE);
			this.dispose();
			return;
		}
		this.dispose();
	}

}
