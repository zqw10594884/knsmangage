package com.zqw.ui;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import com.zqw.bean.CheckListItem;
import com.zqw.bean.VIPUser;
import com.zqw.listener.MyListRenderer;
import com.zqw.util.DBUtil;
import com.zqw.util.UIutil;

public class memberRegistration implements ListSelectionListener{

	private JFrame frame;
	private JTextField nameTF;
	private JTextField telTF;
	private JTable table;
	private JButton btnNewButton_1;
	private JButton btnNewButton;
	private ArrayList<VIPUser> vsLst ;
	private JList list;
	private DefaultListModel<CheckListItem> checkboxModel;
	private JLabel label_1;
	private JTextField textField;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					memberRegistration window = new memberRegistration();
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
	public memberRegistration() {
		initialize();
		initdata();
	}

	private void initdata() {
		if (vsLst == null) {
			vsLst = (ArrayList<VIPUser>) DBUtil.getLstClass("tel", "eq",
					VIPUser.class, "");
		}
		if (vsLst.size() > 0) {
			checkboxModel = new DefaultListModel<CheckListItem>();
			for (int i = 0; i < vsLst.size(); i++) {
				VIPUser sol = vsLst.get(i);
				CheckListItem cli = new CheckListItem(sol.getName() + " 电话：" + sol.getTel());
				checkboxModel.add(i, cli);
			}
			list.setModel(checkboxModel);
			list.setCellRenderer(new MyListRenderer());
			list.removeListSelectionListener(this);
			list.addListSelectionListener(this);
			list.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		}
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 569, 467);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);

		nameTF = new JTextField();
		nameTF.setBounds(58, 94, 89, 21);
		frame.getContentPane().add(nameTF);
		nameTF.setColumns(10);

		JLabel lblNewLabel = new JLabel("姓名：");
		lblNewLabel.setBounds(10, 97, 54, 15);
		frame.getContentPane().add(lblNewLabel);

		telTF = new JTextField();
		telTF.setColumns(10);
		telTF.setBounds(205, 94, 117, 21);
		frame.getContentPane().add(telTF);

		JLabel label = new JLabel("电话：");
		label.setBounds(157, 97, 54, 15);
		frame.getContentPane().add(label);

		btnNewButton = new JButton("添加");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (telTF.getText().trim().length() > 0) {
					VIPUser vs = (VIPUser) DBUtil.getClass(VIPUser.class,
							"tel", telTF.getText().trim(), "String", "eq");
					if (vs == null) {
						vs = new VIPUser();
						vs.setName(nameTF.getText());
						vs.setTel(telTF.getText());
						DBUtil.insert(vs);
						checkboxModel.addElement(new CheckListItem(vs.getName() + " 电话：" + vs.getTel()));
					} else {
						JOptionPane.showMessageDialog(null, "用户已存在！！", "alert",
								JOptionPane.ERROR_MESSAGE);
					}
				}
			}
		});
		btnNewButton.setBounds(255, 125, 67, 23);
		frame.getContentPane().add(btnNewButton);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(345, 10, 198, 409);
		frame.getContentPane().add(scrollPane);

		list = new JList();
		scrollPane.setViewportView(list);

		btnNewButton_1 = new JButton("修改");
		btnNewButton_1.setBounds(183, 125, 67, 23);
		frame.getContentPane().add(btnNewButton_1);

		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(10, 158, 321, 261);
		frame.getContentPane().add(scrollPane_1);

		table = new JTable();
		scrollPane_1.setViewportView(table);
		
		label_1 = new JLabel("积分：");
		label_1.setBounds(10, 129, 41, 15);
		frame.getContentPane().add(label_1);
		
		textField = new JTextField();
		textField.setEditable(false);
		textField.setEnabled(false);
		textField.setBounds(58, 126, 66, 21);
		frame.getContentPane().add(textField);
		textField.setColumns(10);
	}

	@Override
	public void valueChanged(ListSelectionEvent e) {
		// TODO Auto-generated method stub
		
	}
}
