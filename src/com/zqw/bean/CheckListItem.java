package com.zqw.bean;

import java.awt.Color;

public class CheckListItem {
	private String label;
	private boolean isSelected = false;
	private Color c = Color.black;

	public CheckListItem() {
		super();
	}

	public CheckListItem(String label, boolean isSelected) {
		super();
		this.label = label;
		this.isSelected = isSelected;
	}

	public CheckListItem(String label, boolean isSelected, Color c) {
		super();
		this.label = label;
		this.isSelected = isSelected;
		this.c = c;
	}

	

	public CheckListItem(String label) {
		this.label = label;
	}

	public boolean isSelected() {
		return isSelected;
	}

	public void setSelected(boolean isSelected) {
		this.isSelected = isSelected;
	}

	public String toString() {
		return label;
	}

	public Color getC() {
		return c;
	}

	public void setC(Color c) {
		this.c = c;
	}


}