package test;

import java.util.ArrayList;

import com.zqw.bean.CurtainShop;
import com.zqw.bean.OrderLst;
import com.zqw.util.DBUtil;

public class erwteadf {
	public static void main(String[] args) {
		OrderLst ol = new OrderLst();
		CurtainShop cs = new CurtainShop();
		DBUtil.insert(ol);
	}
}
