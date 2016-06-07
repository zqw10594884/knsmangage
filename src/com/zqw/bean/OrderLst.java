package com.zqw.bean;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import com.zqw.util.DBUtil;

public class OrderLst {
	private List<OrderGoods> goodsLst;
	private String curtainShop;
	private String installPerson;
	private String libraryPerson;
	private String machiningPerson;
	private Date deliveryTime;
	private Date submitTime;
	private int id;
	private int arrears;
	private int orderState;
	private CurtainShop nameClass;

	public OrderLst() {
		super();
	}

	public OrderLst(int arrears, String curtainShop, Date date, int id,
			int orderState) {
		super();
		this.curtainShop = curtainShop;
		this.deliveryTime = date;
		this.id = id;
		this.arrears = arrears;
		this.orderState = orderState;
	}

	/**
	 * 
	 * @return 提交时间
	 */
	public Date getSubmitTime() {
		return submitTime;
	}

	/**
	 * 
	 * @param submitTime
	 *            提交时间
	 */
	public void setSubmitTime(Date submitTime) {
		this.submitTime = submitTime;
	}

	/**
	 * 
	 * @return 10 完成 20出库 30备货中 31备货完成 40提交
	 */
	public int getOrderState() {
		return orderState;
	}

	/**
	 * 
	 * @param printState
	 *            10 完成 20出库 30备货中 31备货完成 40提交
	 */
	public void setOrderState(int printState) {
		this.orderState = printState;
	}

	/**
	 * 
	 * @return 欠款
	 */
	public int getArrears() {
		return arrears;
	}

	/**
	 * 
	 * @param arrears
	 *            欠款
	 */
	public void setArrears(int arrears) {
		this.arrears = arrears;
	}

	public String getCurtainShop() {
		return curtainShop;
	}

	public void setCurtainShop(String curtainShop) {
		this.curtainShop = curtainShop;
	}

	/**
	 * 
	 * @return 出库时间
	 */
	public Date getDeliveryTime() {
		return deliveryTime;
	}

	public String getSimpleDate() {
		SimpleDateFormat sdf = new SimpleDateFormat("MM-dd");
		if (orderState > 1) {
			return sdf.format(submitTime);
		} else {
			return sdf.format(deliveryTime);
		}
	}

	/**
	 * 
	 * @param date
	 *            出库时间
	 */
	public void setDeliveryTime(Date date) {
		this.deliveryTime = date;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public List<OrderGoods> getGoodsLst() {
		return goodsLst;
	}

	public void setGoodsLst(List<OrderGoods> goodsLst) {
		this.goodsLst = goodsLst;
	}

	public String getInstallPerson() {
		return installPerson;
	}

	public void setInstallPerson(String installPerson) {
		this.installPerson = installPerson;
	}

	public String getLibraryPerson() {
		return libraryPerson;
	}

	public void setLibraryPerson(String libraryPerson) {
		this.libraryPerson = libraryPerson;
	}

	public String getMachiningPerson() {
		return machiningPerson;
	}

	public void setMachiningPerson(String machiningPerson) {
		this.machiningPerson = machiningPerson;
	}

	/**
	 * 
	 * @return String 形式的订单状态
	 */
	public String getOrderStateToString() {
		String state = "";
		if (10 <= orderState && orderState < 20) {
			// do nothing
		} else if (20 <= orderState && orderState < 30) {
			state = "出库";
		} else if (30 == orderState) {
			state = "备货中";
		} else if (31 == orderState) {
			state = "备货完成";
		} else if (40 <= orderState && orderState < 50) {
			state = "提交";
		}
		return state;
	}

	public CurtainShop getNameClass() {
		return (CurtainShop) DBUtil.getClass(CurtainShop.class, "name", curtainShop,
				"String", "eq");
	}
}
