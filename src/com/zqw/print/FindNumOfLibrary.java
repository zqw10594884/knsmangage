package com.zqw.print;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.zqw.bean.OrderGoods;
import com.zqw.bean.OrderLst;
import com.zqw.util.DBUtil;

public class FindNumOfLibrary {

	private static ArrayList<OrderLst> csl;

	public static void main(String[] args) {
		getNum();
	}

	private static void getNum() {
		Date date = new Date(2016, 07, 01);
		Date date1 = new Date(2016, 8, 1);
		csl = (ArrayList<OrderLst>) DBUtil.getLstClass("", "eq",
				OrderLst.class, "libraryPerson", "董", "String");
		for (int i = 0; i < csl.size(); i++) {
			if (csl.get(i).getId() >=1845  && csl.get(i).getId() <= 2254) {
				List<OrderGoods> og = csl.get(i).getGoodsLst();
				for (int j = 0; j < og.size(); j++) {
					if (og.get(j).getSerialNumber().contains("A-")||og.get(j).getSerialNumber().contains("B-")) {
						System.out.println(csl.get(i).getId() +"*"+og.get(j).getSerialNumber()+"*"+og.get(j).getNumber());
					}
				}
			}
		}
	}

}
