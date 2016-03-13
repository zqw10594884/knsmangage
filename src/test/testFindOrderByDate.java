package test;

import java.util.ArrayList;

import com.zqw.util.DBUtil;

public class testFindOrderByDate {
	public static void main(String[] args) {

		String hqlGoods = "select  o.id from Order o where o.date between '2015/4/1 0:00:00' and '2015/5/1 0:00:00'";
		ArrayList<?> a = (ArrayList<?>) DBUtil.getClassLst(hqlGoods, "");
		for (int i = 0; i < a.size(); i++) {
			System.out.println(a.get(i));
		}
	}
}
