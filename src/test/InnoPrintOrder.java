package test;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Stroke;
import java.awt.Toolkit;
import java.awt.print.Book;
import java.awt.print.PageFormat;
import java.awt.print.Paper;
import java.awt.print.Printable;
import java.awt.print.PrinterException;
import java.awt.print.PrinterJob;
import java.util.ArrayList;

public class InnoPrintOrder implements Printable {

	private ArrayList<String[]> contentLst;

	public InnoPrintOrder() {
		super();
	}

	public InnoPrintOrder(ArrayList<String[]> contentLst) {
		super();
		this.contentLst = contentLst;
	}

	// xy起点偏移
	int offSetX = 60;
	int offSetY = 110;
	// 行高
	int rowH = 30;

	// 每个格子的宽度 所有值的加和= 595 - offSetX*2

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
			Font fontTitle = new Font("新宋体", Font.BOLD, 30);
			// Font fontTitle1 = new Font("新宋体", Font.PLAIN, 20);
			Font font = new Font("新宋体", Font.PLAIN, 15);
			Font fontB = new Font("新宋体", Font.BOLD, 17);

			Stroke stroke = new BasicStroke(1.5f);// 设置线宽为3.0
			// 页眉
			g2.setFont(fontB);
			g2.drawString("LASERLAB", x, y - 70);
			MyDrawString("http://www.laserlabllc.com/", x + 250, y - 70, 0.9, g2);
			y += rowH;
			// 表头
			g2.setFont(fontTitle);
			g2.drawString("检测报告", x + 150, y - 50);
			y += rowH;
			g2.setFont(fontB);
			g2.drawString("测试条件", x, y - 13);
			g2.drawString("测试结果", x + 250, y - 13);
			y += rowH;
			// 表格
			g2.setFont(font);
			g2.setStroke(stroke);

			for (int j = 0; j < contentLst.size(); j++) {
				MyDrawString(contentLst.get(j)[0], x, y - 13, 1.1, g2);
				MyDrawString(contentLst.get(j)[1], x + 250, y - 13, 1.1, g2);
				y += rowH;
			}
			g2.drawString("使用者签名：_____________", x, y - 13);
			g2.drawString("时间：_____________", x + 250, y - 13);
			y += rowH;
			g2.drawString("监督者签名：_____________", x, y - 13);
			g2.drawString("时间：_____________", x + 250, y - 13);
			
			Image src = Toolkit.getDefaultToolkit().getImage(
					getClass().getResource("D:\\test.png"));
			g2.drawImage(src, 200, 200, c);
			g2.drawString("test", 200, 200);

//			int img_Height = src.getHeight(c);
//
//			int img_width = src.getWidth(c);
//			
//			g2.drawImage(src, (int) x,
//					(int) (y + 1 * 15 + img_Height + 11), c);
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

	public static void MyDrawString(String str, int x, int y, double rate,
			Graphics g) {
		String tempStr = new String();
		int orgStringWight = g.getFontMetrics().stringWidth(str);
		int orgStringLength = str.length();
		int tempx = x;
		int tempy = y;
		while (str.length() > 0) {
			tempStr = str.substring(0, 1);
			str = str.substring(1, str.length());
			g.drawString(tempStr, tempx, tempy);
			tempx = (int) (tempx + (double) orgStringWight
					/ (double) orgStringLength * rate);
		}
	}

	public static void main(String[] args) {
		ArrayList<String[]> contentLst = new ArrayList<String[]>();
		String[] s1 = { "仪器名称:", "测试批号:" };
		String[] s2 = { "仪器型号:", "流水号:" };
		String[] s3 = { "激光波长:", "测试结果:" };
		String[] s4 = { "激光功率:", "匹配度:" };
		String[] s5 = { "测试人:", "样品名称:" };
		String[] s6 = { "积分时间:", "一维码:" };
		String[] s7 = { "测试时间:", "机器序列号:" };
		String[] s8 = { "匹配度阀值:", "数据库分类:" };
		contentLst.add(s1);
		contentLst.add(s2);
		contentLst.add(s3);
		contentLst.add(s4);
		contentLst.add(s5);
		contentLst.add(s6);
		contentLst.add(s7);
		contentLst.add(s8);

		Book book = new Book();
		PageFormat pf = new PageFormat();
		pf.setOrientation(PageFormat.PORTRAIT);
		Paper p = new Paper();
		p.setSize(590, 840);
		p.setImageableArea(10, 10, 590, 840);
		pf.setPaper(p);
		InnoPrintOrder printOrder = new InnoPrintOrder(contentLst);
		book.append(printOrder, pf);
		PrinterJob job = PrinterJob.getPrinterJob();
		job.setPageable(book);
		try {
			job.print();
		} catch (PrinterException e) {
			e.printStackTrace();
		}
	}
}
