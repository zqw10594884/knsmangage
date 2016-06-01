package com.zqw.util;

import java.awt.Color;
import java.awt.Component;
import java.awt.Font;

import javax.swing.DefaultListCellRenderer;
/*
 JList wmslist = new JList();
 DefaultListModel listModel2 = new DefaultListModel();
 listModel2.addElement("固定电话:" + rs.getString("phone"));
 listModel2.addElement("手机号码:" + rs.getString("mbphone"));
 listModel2.addElement("联系人:" + rs.getString("contact"));
 listModel2.addElement("承运商:" + rs.getString("ship_via"));
 listModel2.addElement("地址:" + rs.getString("address"));
 wmslist.setModel(listModel2);
 //wmslist.setCellRenderer(new MyRenderer(1, Color.RED));
 wmslist.setCellRenderer(new MyRenderer(new int[]{2, 3}, Color.RED));
 */
import javax.swing.JList;

class MyRenderer extends DefaultListCellRenderer {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Font font1;
	private Color rowcolor;
	private int row;
	private int[] rows;

	public MyRenderer() {
		this.font1 = getFont();
		font1.deriveFont((float) (font1.getSize() + 10));
	}

	public MyRenderer(int row, Color color) {
		this.rowcolor = color;
		this.row = row;
	}

	public MyRenderer(int[] rows, Color color) {
		this.rowcolor = color;
		this.rows = rows;
	}

	public Component getListCellRendererComponent(JList<?> list, Object value,
			int index, boolean isSelected, boolean cellHasFocus) {
		super.getListCellRendererComponent(list, value, index, isSelected,
				cellHasFocus);
		if (rows == null) {
			if (index == row) {
				setBackground(this.rowcolor);
				setFont(getFont().deriveFont((float) (getFont().getSize() + 2)));
			}
		} else {
			for (int i = 0; i < rows.length; i++) {
				if (index == rows[i]) {
					setBackground(this.rowcolor);
					setFont(getFont().deriveFont((float) (getFont().getSize())));
				}
			}
		}

		return this;
	}
}