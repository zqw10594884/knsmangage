package com.zqw.bean;

import java.awt.Font;
import java.util.ArrayList;
import java.util.List;

import com.zqw.util.DBUtil;

public class Global {
	public static CurtainShop curtainShop = new CurtainShop();
	public static Goods goods = new Goods();
	public static String ABCcard = "6228481748769035679  姓名:刘会珍";
	public static String Tel = "18032318933";
	public static String CURRENTUSER = "test";
	public static int EMPLOYEE = 0;
	public static int CUSTOMER = 1;
	public static int OWN = 2;
	public static Font f = new Font("Courier", Font.PLAIN, 13);
	public static List<CurtainShopGoods> goodsLst = new ArrayList<CurtainShopGoods>();

	public static List<CurtainShop> CSLst = (List<CurtainShop>) DBUtil
			.getLstClass("", "", CurtainShop.class, "");

	public static List<CurtainCustomer> CCLst = (List<CurtainCustomer>) DBUtil
			.getLstClass("", "", CurtainCustomer.class, "");

	public static List<OrderLst> OLLst = (List<OrderLst>) DBUtil.getLstClass(
			"id", "", OrderLst.class, "");

	public static List<SaleOrderLst> SOLLst = (List<SaleOrderLst>) DBUtil
			.getLstClass("id", "", SaleOrderLst.class, "");
	public static List<SaleOrderGoods> SOGLst = (List<SaleOrderGoods>) DBUtil
			.getLstClass("id", "", SaleOrderGoods.class, "");
}
