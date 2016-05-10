package com.zqw.ui;

import java.awt.Color;
import java.awt.event.MouseAdapter;
import java.util.ArrayList;
import java.util.List;

import javax.swing.DefaultListModel;
import javax.swing.JList;
import javax.swing.ListSelectionModel;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;

import com.zqw.bean.CheckListItem;
import com.zqw.bean.CurtainShop;
import com.zqw.bean.CurtainShopGoods;
import com.zqw.bean.Goods;
import com.zqw.bean.OrderGoods;
import com.zqw.bean.OrderLst;
import com.zqw.listener.CheckListRenderer;
import com.zqw.listener.MyListRenderer;
import com.zqw.util.DBUtil;

public class UIutil {

	static String hqlCurtainShop_All = "select new CurtainShop(cs.id,cs.name,cs.telephone,cs.address,cs.owner) from CurtainShop cs order by convert(name, 'gbk')";
	static String hqlCurtainShop_From_Name = "select new CurtainShop(cs.id,cs.name,cs.telephone,cs.address,cs.owner) from CurtainShop cs where name = :name0";
	static String hqlCurtainShop_From_Name_Have_Arrears = "select new OrderLst(g.arrears,g.curtainShop,g.date,g.id,g.orderState) from OrderLst g where  orderState = :name0";
	static String hqlCurtainShopOrderLst_From_Name = "select new OrderLst(g.arrears,g.curtainShop,g.date,g.id,g.orderState) from OrderLst g where curtainShop = :name";
	static String hqlCurtainShopGoods_From_Name = "select new CurtainShopGoods(g.id,g.serialNumber,g.sellingPrice,g.curtainShop,g.remarks) from CurtainShopGoods g where curtainShop = :name order by serialNumber";
	static String hqlGoods_From_Name = "select new Goods(g.id,g.serialNumber,g.purchasePrice,g.factory,g.telephone,g.bankCard,g.remark) from Goods g where serialNumber = :name0";
	static String hqlOrderLst_Max = "select max(id) from OrderLst";
	static String hqlOrderGoods_From_CurtainShopName = "select new OrderGoods(g.serialNumber,g.sellingPrice,g.purchasePrice,g.number,g.date) from OrderGoods g where curtainShop = :name order by serialNumber";
	static String hqlOrderGoods_From_OrderId = "select new OrderGoods(g.serialNumber,g.sellingPrice,g.purchasePrice,g.number) from OrderGoods g where orderId = :name";
	static String sqlDel_ordergoods_is_null = "DELETE  FROM  _ordergoods  WHERE  Order_id is null";

	public static ArrayList<CurtainShop> initCurtainShop(
			ListSelectionListener UI, JList<CheckListItem> curtainShopjList,
			ArrayList<CurtainShop> curtainShopLst) {

		ArrayList<String> item = new ArrayList<String>();
		for (int i = 0; i < curtainShopLst.size(); i++) {
			item.add(curtainShopLst.get(i).getName());
		}
		initJlist(UI, curtainShopjList, item);
		return curtainShopLst;
	}

	public static void initCurtainShopGoodsLstFromName(
			ListSelectionListener mainWi, JList<CheckListItem> goodsjList,
			List<CurtainShopGoods> goodsLst) {
		ArrayList<String> item = new ArrayList<String>();
		for (int i = 0; i < goodsLst.size(); i++) {
			item.add(goodsLst.get(i).getSerialNumber());
		}
		initJlist(mainWi, goodsjList, item);
	}

	public static void initJlist(ListSelectionListener UI,
			JList<CheckListItem> jList, ArrayList<String> item) {
		DefaultListModel<CheckListItem> checkboxModel = new DefaultListModel<CheckListItem>();
		for (int i = 0; i < item.size(); i++) {
			CheckListItem cli = new CheckListItem(item.get(i));
			checkboxModel.add(i, cli);
		}
		jList.setModel(checkboxModel);
		jList.setCellRenderer(new MyListRenderer());
		jList.removeListSelectionListener(UI);
		jList.addListSelectionListener(UI);
		jList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
	}

	public static void delFromCurtainShopGoods() {
		DBUtil.delBySql(sqlDel_ordergoods_is_null);
	}

	public static Goods getGoodsFromName(String serialNumber) {
		return (Goods) DBUtil.get(hqlGoods_From_Name, serialNumber, "");
	}

	public static CurtainShop getCurtainShopFromName(String name) {
		return (CurtainShop) DBUtil.get(hqlCurtainShop_From_Name, name, "");
	}

	public static int getMaxIdFromOrderLst() {
		return (Integer) DBUtil.get(hqlOrderLst_Max, "", "") + 1;
	}

	public static List<OrderLst> getCurtainShopOrderLstFromName(String name) {
		return (List<OrderLst>) DBUtil.getClassLst(
				hqlCurtainShopOrderLst_From_Name, name);
	}

	public static int getCurtainShopArrears(String name,
			List<OrderLst> curtainShopOrderLst) {
		int arrears = 0;
		for (int j = 0; j < curtainShopOrderLst.size(); j++) {
			OrderLst csol = curtainShopOrderLst.get(j);
			if (csol.getArrears() > 0) {
				arrears += csol.getArrears();
			}
		}
		return arrears;
	}

	public static List<OrderGoods> getOrderGoodsFromCurtainShopName(String name) {
		return (List<OrderGoods>) DBUtil.getClassLst(
				hqlOrderGoods_From_CurtainShopName, name);
	}

	public static List<OrderGoods> getOrderGoodsFromOrderId(int id) {
		return (List<OrderGoods>) DBUtil.getClassLst(
				hqlOrderGoods_From_OrderId, id + "");
	}

	public static void updateOrderLstArrears(OrderLst ol, String string) {
		String hqlupdate_OrderLst_Arrears = "update OrderLst o set o.arrears =:name0 where o.id =:name1 ";
		DBUtil.update(hqlupdate_OrderLst_Arrears, string, ol.getId());
	}

	@SuppressWarnings("unchecked")
	/**
	 * 
	 * @param UI
	 * @param latelyjList
	 * @param listAdapter
	 * @param checkbox
	 * @param orderLst
	 * @param initClass MainWi = 1, ManageOrderWi = 2.
	 * @return
	 */
	public static List<OrderLst> initLatelyJlist(ListSelectionListener UI,
			JList latelyjList, MouseAdapter listAdapter, boolean checkbox,
			List<OrderLst> orderLst, int initClass) {
		DefaultListModel<CheckListItem> checkboxModel = new DefaultListModel<CheckListItem>();
		DefaultListModel<String> model = new DefaultListModel<String>();
		if (orderLst == null) {
			if (initClass == 1) {
				orderLst = (ArrayList<OrderLst>) DBUtil.getLstClass("", "gt",
						OrderLst.class, "orderState", "19", "int");
			} else if (initClass == 2) {
				orderLst = (ArrayList<OrderLst>) DBUtil.getLstClass("", "eq",
						OrderLst.class, "orderState", "20", "int");
			}else if (initClass == 3) {
				orderLst = (ArrayList<OrderLst>) DBUtil.getLstClass("", "gt",
						OrderLst.class, "orderState", "29", "int");
			}
		}
		for (int i = 0; i < orderLst.size(); i++) {// 遍历并插入历史订单
			OrderLst ol = orderLst.get(i);
			CheckListItem cli = new CheckListItem("("
					+ ol.getOrderStateToString() + ")" + "  "
					+ ol.getSimpleDate() + "  " + ol.getCurtainShop(), false);
			if (ol.getOrderState() >= 40) {
				cli.setC(Color.red);
			} else if (ol.getOrderState() >= 30 && ol.getOrderState() < 40) {
				cli.setC(Color.blue);
			}
			checkboxModel.add(i, cli);
			// 结账界面历史订单
			model.add(i, ol.getCurtainShop() + ol.getSimpleDate());
		}
		if (checkbox) {
			latelyjList.setModel(checkboxModel);
			latelyjList.setCellRenderer(new CheckListRenderer());
			if (latelyjList.getMouseListeners().length < 3) {
				latelyjList.addMouseListener(listAdapter);
			}
		} else {
			latelyjList.setModel(model);
			latelyjList.addListSelectionListener(UI);
		}
		latelyjList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		latelyjList.setSelectionBackground(new Color(177, 232, 58));// 186,212,239,177,232,58
		latelyjList.setSelectionForeground(Color.red);
		return orderLst;
	}

	public static List<OrderLst> isCurtainShopHaveArrears() {
		return (List<OrderLst>) DBUtil.getClassLst(
				hqlCurtainShop_From_Name_Have_Arrears, 1);
	}

	public static void tableAddAll(List<OrderGoods> Lst,
			DefaultTableModel tableModel) {
		for (int i = 0; i < tableModel.getRowCount();) {
			tableModel.removeRow(0);
		}
		for (int i = 0; i < Lst.size(); i++) {
			tableAddLine(Lst.get(i), tableModel);
		}
	}

	public static void tableAddLine(OrderGoods g, DefaultTableModel tableModel) {
		String[] rowValues = { g.getSerialNumber(), g.getPurchasePrice() + "",
				g.getSellingPrice() + "", g.getNumber() + "", g.getRemark() };
		tableModel.addRow(rowValues); // 添加一行
	}

}
