package test;

import java.util.List;

import com.zqw.bean.CurtainShopGoods;
import com.zqw.util.DBUtil;

public class testq {
	public static void main(String[] args) {
		String sqlDel_ordergoods_is_null = "DELETE  FROM  _ordergoods  WHERE  Order_id is null";
		DBUtil.delBySql(sqlDel_ordergoods_is_null);
	}
}
