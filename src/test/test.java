package test;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class test {
	public static void main(String[] args) {
		InetAddress a;
		try {
			a = InetAddress.getLocalHost();
			System.out.println("主机名称: " + a.getHostName());
			SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");// 设置日期格式
			String s = df.format(new Date());
			System.out.println(s);// new Date()为获取当前系统时间

		} catch (UnknownHostException e) {
			e.printStackTrace();
		}
	}
}
