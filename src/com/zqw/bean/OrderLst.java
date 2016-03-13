package com.zqw.bean;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class OrderLst {
	private List<OrderGoods> goodsLst;
	private String curtainShop;
	private String untreatedPerson;
	private Date deliveryTime;
	private Date submitTime;
	private int id;
	private int arrears;
	private int orderState;

	public OrderLst() {
		super();
	}

	public OrderLst(int arrears, String curtainShop, Date date, int id, int orderState) {
		super();
		this.curtainShop = curtainShop;
		this.deliveryTime = date;
		this.id = id;
		this.arrears = arrears;
		this.orderState = orderState;
	}

	public String getUntreatedPerson() {
		return untreatedPerson;
	}

	public void setUntreatedPerson(String untreatedPerson) {
		this.untreatedPerson = untreatedPerson;
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
	 * @return 订单状态 0 完成 1出库 2备货中 3提交
	 */
	public int getOrderState() {
		return orderState;
	}

	/**
	 * 
	 * @param printState
	 *            订单状态 0 完成 1出库 2备货中 3提交
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

	/**
	 * 
	 * @return String 形式的订单状态
	 */
	public String getOrderStateToString() {
		String state = "";
		if (0 == orderState) {
			// do nothing
		} else if (1 == orderState) {
			state = "出库";
		} else if (2 == orderState) {
			state = "备货中";
		} else if (3 == orderState) {
			state = "提交";
		}
		return state;
	}
}
