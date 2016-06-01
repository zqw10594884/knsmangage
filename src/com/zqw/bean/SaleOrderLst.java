package com.zqw.bean;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.zqw.util.DBUtil;

public class SaleOrderLst extends KNSOrder {
	private int id;
	private String location;
	private String installPerson;
	private String libraryPerson;
	private String machiningPerson;
	private String salePerson;
	private Date deliveryTime;
	private Date submitTime;
	private int arrears;
	private String customerDeposit;
	private int orderState;
	private CurtainCustomer customer;
	private List<SaleOrderGoods> goodsLst;

	public SaleOrderLst() {
		super();
	}

	public CurtainCustomer getCustomer() {
		return customer;
	}

	public void setCustomer(CurtainCustomer customer) {
		this.customer = customer;
	}

	public String getCustomerDeposit() {
		return customerDeposit;
	}

	public void setCustomerDeposit(String customerDeposit) {
		this.customerDeposit = customerDeposit;
	}

	public String getSalePerson() {
		return salePerson;
	}

	public void setSalePerson(String salePerson) {
		this.salePerson = salePerson;
	}
	
	
	

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
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

	public List<SaleOrderGoods> getGoodsLst() {
		if (goodsLst == null) {
			goodsLst = new ArrayList<SaleOrderGoods>();
		}
		if (id != 0) {
		}
		return goodsLst;
	}

	public void setGoodsLst(List<SaleOrderGoods> goodsLst) {
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
}
