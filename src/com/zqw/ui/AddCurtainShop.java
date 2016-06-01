/*
 * AddCurtainShop.java
 *
 * Created on __DATE__, __TIME__
 */

package com.zqw.ui;

import javax.swing.JOptionPane;

import com.zqw.bean.Global;
import com.zqw.bean.Goods;
import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.GroupLayout;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JComboBox;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.DefaultComboBoxModel;

/**
 * 
 * @author __USER__
 */
public class AddCurtainShop extends javax.swing.JDialog {
	private JTextField name;
	private JTextField address;
	private JTextField telephone;
	private JComboBox curtainShopOwner;
	private JButton add;
	private JButton jButton1;

	/** Creates new form AddCurtainShop */
	public AddCurtainShop(java.awt.Frame parent, boolean modal) {
		super(parent, modal);
		getContentPane().setLayout(null);

		JLabel label = new JLabel();
		label.setText("姓 名：");
		label.setBounds(20, 16, 42, 15);
		getContentPane().add(label);

		JLabel label_1 = new JLabel();
		label_1.setText("地 址：");
		label_1.setBounds(20, 43, 42, 15);
		getContentPane().add(label_1);

		JLabel label_2 = new JLabel();
		label_2.setText("电 话：");
		label_2.setBounds(20, 70, 42, 15);
		getContentPane().add(label_2);

		name = new JTextField();
		name.setBounds(75, 10, 137, 21);
		getContentPane().add(name);

		address = new JTextField();
		address.setBounds(75, 37, 137, 21);
		getContentPane().add(address);

		telephone = new JTextField();
		telephone.setBounds(75, 64, 137, 21);
		getContentPane().add(telephone);

		add = new JButton();
		add.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				addActionPerformed(e);
			}
		});
		add.setText("添加");
		add.setBounds(51, 133, 57, 23);
		getContentPane().add(add);

		jButton1 = new JButton();
		jButton1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				jButton1ActionPerformed(e);
			}
		});
		jButton1.setText("关闭");
		jButton1.setBounds(118, 133, 65, 23);
		getContentPane().add(jButton1);

		curtainShopOwner = new JComboBox();
		curtainShopOwner.setModel(new DefaultComboBoxModel(new String[] {"张其伟", "刘会珍"}));
		curtainShopOwner.setBounds(75, 95, 81, 21);
		getContentPane().add(curtainShopOwner);

		JLabel label_3 = new JLabel();
		label_3.setText("所 属：");
		label_3.setBounds(20, 98, 42, 15);
		getContentPane().add(label_3);
	}

	/**
	 * This method is called from within the constructor to initialize the form.
	 * WARNING: Do NOT modify this code. The content of this method is always
	 * regenerated by the Form Editor.
	 */
	// GEN-BEGIN:initComponents
	// <editor-fold defaultstate="collapsed" desc="Generated Code">

	private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {
		// TODO add your handling code here:
		this.dispose();
	}

	private void addActionPerformed(java.awt.event.ActionEvent evt) {
		if (name.getText().trim().length() != 0) {
			Global.curtainShop.setName(name.getText());
		} else {
			JOptionPane.showMessageDialog(this, "请输入名字", "alert",
					JOptionPane.ERROR_MESSAGE);
			Global.curtainShop = null;
			this.dispose();
			return;
		}
		if (telephone.getText().trim().length() != 0) {
			Global.curtainShop.setTelephone(telephone.getText().trim());
		} else {
			JOptionPane.showMessageDialog(this, "请输入电话", "alert",
					JOptionPane.ERROR_MESSAGE);
			Global.curtainShop = null;
			this.dispose();
			return;
		}
		if (address.getText().trim().length() != 0) {
			int owner = curtainShopOwner.getSelectedIndex();
			Global.curtainShop.setOwner(owner);
			Global.curtainShop.setAddress(address.getText().trim());
		} else {
			JOptionPane.showMessageDialog(this, "请输入价格", "alert",
					JOptionPane.ERROR_MESSAGE);
			Global.curtainShop = null;
			this.dispose();
			return;
		}
		this.dispose();
	}
}