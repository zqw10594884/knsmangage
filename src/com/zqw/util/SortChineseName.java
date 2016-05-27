package com.zqw.util;

import java.text.Collator;
import java.util.Comparator;

import com.zqw.bean.OrderLst;

/**
 * @Title： SortChineseName.java
 * @Description: 中文字符排序
 * @Function: 中文字符排序
 * @Copyright: Copyright (c) 2012-11-19
 * @Author : zhangzhao
 * @Version 0.1
 */
public class SortChineseName implements Comparator<OrderLst> {
	Collator cmp = Collator.getInstance(java.util.Locale.CHINA);

	@Override
	public int compare(OrderLst o1, OrderLst o2) {
		if (cmp.compare(o1.getCurtainShop(), o2.getCurtainShop()) > 0) {
			return 1;
		} else if (cmp.compare(o1.getCurtainShop(), o2.getCurtainShop()) < 0) {
			return -1;
		}
		return 0;
	}
}
