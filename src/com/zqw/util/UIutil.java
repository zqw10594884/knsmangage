package com.zqw.util;

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
import com.zqw.bean.Global;
import com.zqw.bean.Goods;
import com.zqw.bean.OrderGoods;
import com.zqw.bean.OrderLst;
import com.zqw.bean.SaleOrderLst;
import com.zqw.listener.CheckListRenderer;
import com.zqw.listener.MyListRenderer;

public class UIutil {

	static String hqlCurtainShop_All = "select new CurtainShop(cs.id,cs.name,cs.telephone,cs.address,cs.owner) from CurtainShop cs order by convert(name, 'gbk')";
	static String hqlCurtainShop_From_Name = "select new CurtainShop(cs.id,cs.name,cs.telephone,cs.address,cs.owner) from CurtainShop cs where name = :name0";
	static String hqlCurtainShop_From_Name_Have_Arrears = "select new OrderLst(g.arrears,g.curtainShop,g.date,g.id,g.orderState) from OrderLst g where  orderState = :name0";
	static String hqlCurtainShopOrderLst_From_Name = "select new OrderLst(g.arrears,g.curtainShop,g.date,g.id,g.orderState) from OrderLst g where curtainShop = :name";
	static String hqlCurtainShopGoods_From_Name = "select new CurtainShopGoods(g.id,g.serialNumber,g.sellingPrice,g.curtainShop,g.remarks) from CurtainShopGoods g where curtainShop = :name order by serialNumber";
	static String hqlGoods_From_Name = "select new Goods(g.id,g.serialNumber,g.purchasePrice,g.factory,g.telephone,g.bankCard,g.remark) from Goods g where serialNumber = :name0";
	static String hqlOrderLst_Max = "select max(id) from OrderLst";
	static String hqlSaleOrderLst_Max = "select max(id) from SaleOrderLst";
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

	public static int getMaxIdFromSaleOrderLst() {
		int id = 0;
		if ((Integer) DBUtil.get(hqlSaleOrderLst_Max, "", "") != null) {
			id += 1;
		}
		return id;
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

	public static List<SaleOrderLst> initRetailOrderJlist(
			ListSelectionListener UI, JList jList, MouseAdapter listAdapter,
			boolean checkbox, List<SaleOrderLst> orderLst) {

		DefaultListModel<CheckListItem> checkboxModel = new DefaultListModel<CheckListItem>();
		DefaultListModel<String> model = new DefaultListModel<String>();
		if (orderLst == null) {
			orderLst = (ArrayList<SaleOrderLst>) DBUtil.getLstClass("", "gt",
					SaleOrderLst.class, "orderState", "29", "int");
			if (Global.User.getAuthority() == 20) {

			} else if (Global.User.getAuthority() == 21) {

			} else if (Global.User.getAuthority() == 22) {

			} else if (Global.User.getAuthority() == 23) {

			}
		}
		for (int i = 0; i < orderLst.size(); i++) {// 遍历并插入
			SaleOrderLst ol = orderLst.get(i);
			CheckListItem cli = null;
			cli = new CheckListItem("(" + ol.getOrderStateToString() + ")"
					+ "  " + ol.getSimpleDate() + "  "
					+ ol.getCustomer().getName() + "(" + ol.getLibraryPerson()
					+ ")", false);
			if (ol.getOrderState() >= 40) {
				cli.setC(Color.red);
			} else if (ol.getOrderState() >= 30 && ol.getOrderState() < 40) {
				cli.setC(Color.blue);
			}
			checkboxModel.add(i, cli);
			// 结账界面历史订单
			model.add(i, ol.getCustomer().getName() + ol.getSimpleDate());
		}

		if (checkbox) {
			jList.setModel(checkboxModel);
			jList.setCellRenderer(new CheckListRenderer());
			if (jList.getMouseListeners().length < 3) {
				jList.addMouseListener(listAdapter);
			}
		} else {
			jList.setModel(model);
			jList.addListSelectionListener(UI);
		}
		jList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		jList.setSelectionBackground(new Color(177, 232, 58));// 186,212,239,177,232,58
		jList.setSelectionForeground(Color.red);
		return orderLst;
	}

	@SuppressWarnings("unchecked")
	/**
	 * 
	 * @param UI
	 * @param jList
	 * @param listAdapter
	 * @param checkbox
	 * @param orderLst
	 * @param initClass MainWi = 1, ManageOrderWi = 2. MainCBWi = 3.
	 * @return
	 */
	public static List<OrderLst> initOrderJlist(ListSelectionListener UI,
			JList jList, MouseAdapter listAdapter, boolean checkbox,
			List<OrderLst> orderLst, int initClass) {
		DefaultListModel<CheckListItem> checkboxModel = new DefaultListModel<CheckListItem>();
		DefaultListModel<String> model = new DefaultListModel<String>();

		if (orderLst == null) {

			switch (initClass) {
			case 1:
				if (Global.User.getAuthority() < 20) {
					orderLst = (ArrayList<OrderLst>) DBUtil.getLstClass("",
							"gt", OrderLst.class, "orderState", "19", "int");
				} else {
					orderLst = (ArrayList<OrderLst>) DBUtil.getLstClass("",
							"gt", OrderLst.class, "orderState", "20", "int");
				}
				break;
			case 2:
				orderLst = (ArrayList<OrderLst>) DBUtil.getLstClass("", "eq",
						OrderLst.class, "orderState", "20", "int");
				break;
			case 3:
				ArrayList<OrderLst> originalLst = (ArrayList<OrderLst>) DBUtil
						.getLstClass("", "gt", OrderLst.class, "orderState",
								"29", "int");
				orderLst = filter(originalLst);// 过滤器 筛选有布或者纱的订单

				break;
			case 4:

				break;

			default:
				break;
			}
		}

		for (int i = 0; i < orderLst.size(); i++) {// 遍历并插入
			OrderLst ol = orderLst.get(i);
			CheckListItem cli = null;
			if (initClass == 3) {
				cli = new CheckListItem("(" + ol.getOrderStateToString() + ")"
						+ "  " + ol.getSimpleDate() + "  "
						+ ol.getCurtainShop() + "(" + ol.getLibraryPerson()
						+ ")", false);
			} else {
				cli = new CheckListItem("(" + ol.getOrderStateToString() + ")"
						+ "  " + ol.getSimpleDate() + "  "
						+ ol.getCurtainShop(), false);
			}
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
			jList.setModel(checkboxModel);
			jList.setCellRenderer(new CheckListRenderer());
			if (jList.getMouseListeners().length < 3) {
				jList.addMouseListener(listAdapter);
			}
		} else {
			jList.setModel(model);
			jList.addListSelectionListener(UI);
		}
		jList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		jList.setSelectionBackground(new Color(177, 232, 58));// 186,212,239,177,232,58
		jList.setSelectionForeground(Color.red);
		return orderLst;
	}

	private static List<OrderLst> filter(ArrayList<OrderLst> lst) {
		List<OrderLst> orderLst = new ArrayList<OrderLst>();
		for (int i = 0; i < lst.size(); i++) {
			List<OrderGoods> ogLst = lst.get(i).getGoodsLst();
			for (int j = 0; j < ogLst.size(); j++) {
				String s = ogLst.get(j).getSerialNumber();
				if (s.contains("A-") || s.contains("B-")) {
					orderLst.add(lst.get(i));
					break;
				}
			}
		}
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

		if (Global.User.getAuthority() < 20) {
			String[] rowValues = { g.getSerialNumber(),
					g.getPurchasePrice() + "", g.getSellingPrice() + "",
					g.getNumber() + "", g.getRemark() };
			tableModel.addRow(rowValues); // 添加一行
		} else {
			String[] rowValues = { g.getSerialNumber(), "", "",
					g.getNumber() + "", g.getRemark() };
			tableModel.addRow(rowValues); // 添加一行
		}
	}

}
