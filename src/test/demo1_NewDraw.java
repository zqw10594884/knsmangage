package test;
import java.awt.*;
import java.awt.image.*;
import java.io.*;
import javax.imageio.*;


public class demo1_NewDraw{

	public static void main(String[] args) throws Exception{
		int width=150;
		int height=30;
		String out=new String("今夕是何年？");
		double rate=0.9;
		
		BufferedImage image=new BufferedImage(width,height,BufferedImage.TYPE_INT_BGR);
		Graphics g=image.getGraphics();//创建画笔
		g.setColor(new Color(200,192,184));//背景颜色
		g.fill3DRect(0, 0, width, height, true);//背景
		g.setColor(Color.BLACK);//文字颜色
		g.setFont(new Font("宋体",Font.BOLD,20));//设置字体
		
		//居中显示
		int x=(int)(width/2-rate*g.getFontMetrics().stringWidth(out)/2);
		int y=height/2+g.getFontMetrics().getHeight()/3;

		MyDrawString(out, x, y, rate, g);
		
		ImageIO.write(image, "png", new File("d:/2.png"));
	}	
	
	public static void MyDrawString(String str,int x,int y,double rate,Graphics g){
		String tempStr=new String();
		int orgStringWight=g.getFontMetrics().stringWidth(str);
		int orgStringLength=str.length();
		int tempx=x;
		int tempy=y;
		while(str.length()>0)
		{
			tempStr=str.substring(0, 1);
			str=str.substring(1, str.length());
			g.drawString(tempStr, tempx, tempy);
			tempx=(int)(tempx+(double)orgStringWight/(double)orgStringLength*rate);
		}
	}

}