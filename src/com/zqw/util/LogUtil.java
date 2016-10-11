package com.zqw.util;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.zqw.bean.Global;
import com.zqw.bean.Logs;

public class LogUtil {
	public static void setLog(int id, String state, String content) {
		Logs log = new Logs();
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");// 设置日期格式
		try {
			log.setOrderId(id+"");
			log.setDate(df.format(new Date()));
			log.setUser(Global.User.getName() + "_"
					+ InetAddress.getLocalHost().getHostName());
			log.setOperation(state);
			log.setContent(content);
			DBUtil.insert(log);

		} catch (UnknownHostException e) {
			e.printStackTrace();
		}
	}
}
