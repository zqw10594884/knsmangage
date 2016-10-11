package com.zqw.util;

import java.net.InetAddress;
import java.net.NetworkInterface;
import java.text.DecimalFormat;
import java.util.Enumeration;
import java.util.List;

import javax.swing.AbstractButton;
import javax.swing.ButtonGroup;
import javax.swing.table.DefaultTableModel;

import org.hibernate.criterion.Order;

import com.zqw.bean.OrderGoods;
import com.zqw.bean.OrderLst;

public class DataUtil {
	public static int getProfit(OrderLst order) {
		return getProfit(order.getGoodsLst());
	}

	public static int getProfit(List<OrderGoods> lst) {
		double total = 0;
		for (int i = 0; i < lst.size(); i++) {
			OrderGoods g = lst.get(i);
			if ("免费样品".equals(g.getRemark())) {
				// do null;
			} else {
				total += ((g.getSellingPrice() - g.getPurchasePrice()) * g
						.getNumber());
			}
		}
		return (int) total;
	}

	public static int getTotal(OrderLst order) {
		return getTotal(order.getGoodsLst());
	}

	public static int getTotal(List<OrderGoods> lst) {
		double total = 0;
		for (int i = 0; i < lst.size(); i++) {
			OrderGoods g = lst.get(i);
			if ("免费样品".equals(g.getRemark())) {
				// do null;
			} else {
				total += (g.getSellingPrice() * g.getNumber());
			}
		}
		return (int) total;
	}

	public static double getFlowersCol(double distance, String flag,
			double number) {
		number /= 2;
		int a = (int) (number / distance);
		double b = number - a * distance;
		double result = 0;
		if (b == 0) {
			result = a * distance;
		} else if (flag.equals("less")) {
			result = a * distance;
		} else if (flag.equals("auto")) {
			if ((distance - 2 * b) < 0) {
				result = a * (distance + 1);
			} else {
				result = a * distance;
			}
		} else if (flag.equals("more")) {
			result = a * (distance + 1);
		}
		return result * 2;
	}

	public static String getSelectRa(ButtonGroup buttonGroup1) {
		Enumeration<AbstractButton> enu = buttonGroup1.getElements();
		String result = null;
		while (enu.hasMoreElements()) {
			AbstractButton radioButton = enu.nextElement();
			if (radioButton.isSelected()) {
				result = radioButton.getName();
			}
		}
		return result;
	}

	public static String formatDouble(double d) {
		DecimalFormat df = new DecimalFormat(".00");
		return df.format(d);
	}

	public static int getProfitm(List<OrderGoods> og) {
		int Profit = 0;
		for (int i = 0; i < og.size(); i++) {
			
			double purchasePrice = og.get(i).getPurchasePrice();
			double sellingPrice =  og.get(i).getSellingPrice();
			double numberD =  og.get(i).getNumber();
			Profit += (sellingPrice - purchasePrice) * numberD;
		}
		return Profit;
	}

	public static int getTotalm(List<OrderGoods> og) {
		int Total = 0;
		for (int i = 0; i <  og.size(); i++) {
			double sellingPrice =  og.get(i).getSellingPrice();
			double numberD =  og.get(i).getNumber();
			Total += sellingPrice * numberD;
		}
		return Total;
	}
	
	private static String getMACAddress(InetAddress ia)throws Exception{
        //获得网络接口对象（即网卡），并得到mac地址，mac地址存在于一个byte数组中。
        byte[] mac = NetworkInterface.getByInetAddress(ia).getHardwareAddress();
        
        //下面代码是把mac地址拼装成String
        StringBuffer sb = new StringBuffer();
        
        for(int i=0;i<mac.length;i++){
            if(i!=0){
                sb.append("-");
            }
            //mac[i] & 0xFF 是为了把byte转化为正整数
            String s = Integer.toHexString(mac[i] & 0xFF);
            sb.append(s.length()==1?0+s:s);
        }
        
        //把字符串所有小写字母改为大写成为正规的mac地址并返回
        return sb.toString().toUpperCase();
    }
}
