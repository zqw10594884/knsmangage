package com.zqw.ui;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;

import javax.swing.ImageIcon;
import javax.swing.JPanel;

import com.zqw.util.ImageTool;


public class ImagePanel extends JPanel {
	private int locatian = 0;
	private String pictureName;
	public ImagePanel(int locatian,String pictureName){
		this.locatian = locatian;
		this.pictureName = pictureName;
	}
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		if (locatian == 0) {
			BufferedImage bi =  ImageTool.zoomPicture(new File("D:\\test\\card\\5.jpg"), 1);
			ImageIcon img = new ImageIcon(bi);
			g.drawImage(img.getImage(), 0, 0, null);

		}else if(locatian == 1){
			BufferedImage bi =  ImageTool.zoomPicture(new File("D:\\test\\goods\\5.jpg"), 1);
			ImageIcon img = new ImageIcon(bi);
			g.drawImage(img.getImage(), 0, 0, null);
		}
	}
}
