package com.zqw.listener;

import java.awt.Component;

import javax.swing.JCheckBox;
import javax.swing.JList;
import javax.swing.ListCellRenderer;

import com.zqw.bean.CheckListItem;
import com.zqw.bean.Global;

public class CheckListRenderer extends JCheckBox implements ListCellRenderer {

	public Component getListCellRendererComponent(JList list, Object value,

	int index, boolean isSelected, boolean hasFocus) {

		setEnabled(list.isEnabled());
		setFont(Global.f);
		
		if (((CheckListItem) value).isSelected()) {
			setSelected(((CheckListItem) value).isSelected());
		}
		if (isSelected) {
			this.setForeground(((CheckListItem) value).getC());
			this.setBackground(list.getSelectionBackground());
		} else {
			this.setForeground(((CheckListItem) value).getC());
			this.setBackground(list.getBackground());
		}
		setText(value.toString());
		return this;

	}

}