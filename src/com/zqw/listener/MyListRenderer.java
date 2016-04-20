package com.zqw.listener;

import java.awt.Component;

import javax.swing.JCheckBox;
import javax.swing.JComponent;
import javax.swing.JList;
import javax.swing.ListCellRenderer;

import com.sun.xml.internal.ws.org.objectweb.asm.Label;
import com.zqw.bean.CheckListItem;
import com.zqw.bean.Global;

public class MyListRenderer extends  Label implements ListCellRenderer {

	public Component getListCellRendererComponent(JList list, Object value,

	int index, boolean isSelected, boolean hasFocus) {

		setEnabled(list.isEnabled());
		setFont(Global.f);
		
		setText(value.toString());
		return this;

	}

}