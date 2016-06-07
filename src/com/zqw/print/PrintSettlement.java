package com.zqw.print;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.util.List;
import java.awt.Stroke;
import java.awt.print.PageFormat;
import java.awt.print.Printable;
import java.awt.print.PrinterException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import com.zqw.bean.CurtainShop;
import com.zqw.bean.OrderGoods;
import com.zqw.bean.OrderLst;

public class PrintSettlement implements Printable {

	private ArrayList<OrderLst> pandectList;

	public PrintSettlement() {
		super();
	}

	public PrintSettlement(ArrayList<OrderLst> pandectList) {
		super();
		this.pandectList = pandectList;
	}

	// xy起点偏移
	int offSetX = 40;
	int offSetY = 110;
	// 行高
	int rowH = 30;
	// 每个格子的宽度 所有值的加和= 595 - offSetX*2
	int[] col = { 150, 45, 45, 45, 50, 65, 65, 50 };
	String[] title = { "客  户", "归属", "窗帘", "辅料", "辅料_", "窗帘_张", "窗帘_刘", "小计" };

	/**
	 * @param Graphic指明打印的图形环境
	 * @param PageFormat指明打印页格式
	 *            （页面大小以点为计量单位，1点为1英才的1/72，1英寸为25.4毫米。A4纸大致为595×842点）
	 * @param pageIndex指明页号
	 **/
	public int print(Graphics gra, PageFormat pf, int pageIndex)
			throws PrinterException {
		Component c = null;
		// 转换成Graphics2D
		Graphics2D g2 = (Graphics2D) gra;
		// 设置打印颜色为黑色
		g2.setColor(Color.black);

		// 打印起点坐标
		int x = (int) pf.getImageableX() + offSetX;
		int y = (int) pf.getImageableY() + offSetY;
		// int w = (int) pf.getWidth();
		int w = 595 - offSetX * 2;
		int colW = 475 / 4;
		int h = (int) pf.getHeight();

		switch (pageIndex) {
		case 0:
			Font fontTitle = new Font("新宋体", Font.PLAIN, 30);
			// Font fontTitle1 = new Font("新宋体", Font.PLAIN, 20);
			Font font = new Font("新宋体", Font.PLAIN, 15);
			Stroke stroke = new BasicStroke(1.5f);// 设置线宽为3.0

			// 表头
			g2.setFont(fontTitle);
			g2.drawString("凯妮丝红日窗帘批发", x + 100, y - 50);
			g2.setFont(font);
			Date nowTime = new Date();
			SimpleDateFormat time = new SimpleDateFormat("yyyy MM dd ");
			g2.drawString(time.format(nowTime), x, y - 50);
			// 表格
			g2.setFont(font);
			g2.setStroke(stroke);
			// 表格标题
			y = drawLine(g2, y, x, w, col, title);
			DateFormat df = new SimpleDateFormat("MM/dd");
			double total = 0;
			double totalOther = 0;
			double totalOtherL = 0;
			double totalCurtain = 0;
			double totalCurtain_L = 0;
			double totalCurtain_Z = 0;
			for (int j = 0; j < pandectList.size(); j++) {
				OrderLst g = pandectList.get(j);
				List<OrderGoods> og = g.getGoodsLst();
				CurtainShop cs = (CurtainShop) g.getNameClass();
				String ower = cs.getOwner() == 0 ? "张" : "刘";
				double tempCurtain = 0;
				double tempOther = 0;
				double tempOtherL = 0;
				double tempCurtainL = 0;
				for (int i = 0; i < og.size(); i++) {
					String s = og.get(i).getSerialNumber().substring(0, 2);
					if (s.contains("A-") || s.contains("B-")) {
						tempCurtain += og.get(i).getSellingPrice()
								* og.get(i).getNumber();
						tempCurtainL += (og.get(i).getSellingPrice() - og
								.get(i).getPurchasePrice())
								* og.get(i).getNumber();
					} else {
						tempOther += og.get(i).getSellingPrice()
								* og.get(i).getNumber();
						tempOtherL += (og.get(i).getSellingPrice() - og.get(i)
								.getPurchasePrice()) * og.get(i).getNumber();
					}
				}
				if (cs.getOwner() == 0) {
					total += (tempCurtain - tempCurtainL);
					String[] temp = { g.getNameClass().getName(), ower,
							(int) tempCurtain + "", (int) tempOther + "",
							(int) tempOtherL + "", (int) tempCurtainL + "", "",
							(int) (tempCurtain - tempCurtainL) + "" };
					y = drawLine(g2, y, x, w, col, temp);
					totalCurtain_Z += tempCurtainL;
				} else {
					total += tempCurtain;
					String[] temp = { g.getNameClass().getName(), ower,
							(int) tempCurtain + "", (int) tempOther + "",
							(int) tempOtherL + "", "", (int) tempCurtainL + "",
							(int) tempCurtain + "" };
					y = drawLine(g2, y, x, w, col, temp);
					totalCurtain_L += tempCurtainL;
				}
				totalOtherL += tempOtherL;
				totalOther += tempOther;
				totalCurtain += tempCurtain;
			}
			String[] temp = { "总计", "", (int) totalCurtain + "",
					(int) totalOther + "", (int) totalOtherL + "",
					(int) totalCurtain_Z + "", (int) totalCurtain_L + "",
					(int) total + "" };
			y = drawLine(g2, y, x, w, col, temp);

			g2.drawLine(x, y, x + w, y);
			y += rowH;

			return PAGE_EXISTS;
		default:
			return NO_SUCH_PAGE;
		}

	}

	private int drawLine(Graphics2D g2, int y, int x, int w, int[] col,
			String[] str) {
		// TODO Auto-generated method stub
		int tempW = 0;
		g2.drawLine(x, y, x + w, y);
		for (int i = 0; i <= col.length; i++) {
			g2.drawLine(x + tempW, y, x + tempW, y + rowH);
			if (i < col.length) {
				if (str != null) {
					g2.drawString(str[i], x + tempW + 5, y + rowH - 5);
				}
				tempW += col[i];
			}
		}
		return y += rowH;
	}
}
