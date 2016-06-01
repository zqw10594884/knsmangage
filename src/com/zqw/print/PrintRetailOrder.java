package com.zqw.print;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Stroke;
import java.awt.print.PageFormat;
import java.awt.print.PrinterException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import com.zqw.bean.CurtainCustomer;
import com.zqw.bean.Global;
import com.zqw.bean.OrderGoods;
import com.zqw.bean.SaleOrderGoods;
import com.zqw.bean.SaleOrderLst;
import com.zqw.util.DataUtil;

public class PrintRetailOrder extends PrintOrder {

	private SaleOrderLst sol;
	private CurtainCustomer cs;
	private int parameter;

	public PrintRetailOrder() {
		super();
	}

	public PrintRetailOrder(SaleOrderLst sol, CurtainCustomer cs, int parameter) {
		this.cs = cs;
		this.sol = sol;
		this.parameter = parameter;
	}

	// xy起点偏移
	int offSetX = 60;
	int offSetY = 110;
	// 行高
	int rowH = 30;
	// 每个格子的宽度 所有值的加和= 595 - offSetX*2
	int[] colLp = { 155, 45, 275 };
	int[] colMp = { 40, 140, 40, 55, 100, 100 };
	int[] colIp = { 75, 155, 135, 110 };
	String[] titleLp = { "窗帘", "数量", "备   注" };
	String[] titleMp = { "形式", "品       名", "数量", "高", "花边", "罗马圈" };
	String[] titleIp = { "位置", "窗帘", "罗马杆", "备   注" };
	String[] titleCs = { "位置", "窗帘", "罗马杆", "罗马圈", "辅料", "小计" };

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
			Font fontTitle1 = new Font("新宋体", Font.PLAIN, 20);
			Font font = new Font("新宋体", Font.PLAIN, 15);
			Stroke stroke = new BasicStroke(1.5f);// 设置线宽为3.0

			// 表头
			g2.setFont(fontTitle);
			String title = "";
			if (parameter == Global.EMPLOYEE_LP) {
				title = "裁布工单";
			} else if (parameter == Global.EMPLOYEE_MP) {
				title = "加工工单";
			} else if (parameter == Global.EMPLOYEE_IP) {
				title = "安装工单";
			}
			if (sol.getLocation().equals("帝王")) {
				title+="_帝王";
			} else if (sol.getLocation().equals("凯妮丝")) {
				title+="_凯妮丝";
			}
			g2.drawString(title, x + 170, y - 50);

			g2.setFont(font);
			Date nowTime = new Date();
			SimpleDateFormat time = new SimpleDateFormat("yyyy MM dd ");
			g2.drawString(time.format(nowTime), x, y - 50);

			g2.drawString("订单编号：" + sol.getId(), x, y - 13);
			g2.drawString("地址：" + cs.getName(), x + 150, y - 13);

			// 表格
			g2.setFont(font);
			g2.setStroke(stroke);

			// 表格标题
			if (parameter == Global.EMPLOYEE_LP) {
				y = drawLine(g2, y, x, w, colLp, titleLp);
			} else if (parameter == Global.EMPLOYEE_MP) {
				y = drawLine(g2, y, x, w, colMp, titleMp);
			} else if (parameter == Global.EMPLOYEE_IP) {
				y = drawLine(g2, y, x, w, colIp, titleIp);
			}

			List<SaleOrderGoods> goodsLst = sol.getGoodsLst();
			for (int j = 0; j < goodsLst.size(); j++) {
				SaleOrderGoods g = goodsLst.get(j);
				// 画行
				String[] data = null;
				if (parameter == Global.EMPLOYEE_LP) {
					String[] temp = { g.getClothSerialNumber(),
							g.getClothNumber() + "", "", "", g.getClothRemark() };
					data = temp;
					y = drawLine(g2, y, x, w, colLp, data);
				} else if (parameter == Global.EMPLOYEE_MP) {
					String ring = g.getCurtainRingSerialNumber().substring(4);
					String lace = g.getCurtainLaceSerialNumber().substring(5);
					String high = g.getHightLocation().substring(0, 1) + "_"
							+ g.getCurtainHight();
					String style = null;
					if (g.getCurtainStyle().equals("打孔*1.7")) {
						style = "孔";
					} else if (g.getCurtainStyle().equals("挂钩*1.5")) {
						style = "挂";
					} else if (g.getCurtainStyle().equals("挂钩+0.5")) {
						style = "展";
					}
					String[] temp = { style, g.getClothSerialNumber(),
							g.getClothNumber() + "", high, lace, ring };
					data = temp;
					y = drawLine(g2, y, x, w, colMp, data);
				} else if (parameter == Global.EMPLOYEE_IP) {
					String[] temp = { g.getCurtainLocation(),
							g.getClothSerialNumber(),
							g.getCurtainRodSerialNumber(), g.getClothRemark() };
					data = temp;
					y = drawLine(g2, y, x, w, colIp, data);
				}
			}
			String other = "";

			String[] Total = null;

			y = drawLine(g2, y, x, w, TotalCol, Total);
			g2.drawLine(x, y, x + w, y);
			y += rowH;
			// g2.drawString("订货电话：" + Global.Tel, x - 10, y - 13);
			// g2.drawString("农行卡：" + Global.ABCcard, x + 170, y - 13);
			// g2.drawString(order.getId() + "", x - 20, 800);
			y += rowH;
			// g2.drawString("确认货物无误后签字_____________", x + 200, y - 15);
			g2.setFont(fontTitle1);
			g2.drawString(cs.getName(), x + 280, 700);
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
