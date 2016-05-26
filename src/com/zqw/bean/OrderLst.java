package com.zqw.bean;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import com.zqw.util.DBUtil;

public class OrderLst extends KNSOrder {
	private int id;
	private int nameId;
	private String name;
	private String installPerson;
	private String libraryPerson;
	private String machiningPerson;
	private String salePerson;
	private Date deliveryTime;
	private Date submitTime;
	private int arrears;
	private String customerDeposit;
	private int orderState;

	private List goodsLst;
	private CurtainShop nameClass;

	public OrderLst() {
		super();
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getNameId() {
		return nameId;
	}

	public void setNameId(int nameId) {
		this.nameId = nameId;
	}

	public CurtainShop getNameClass() {
		if (nameClass == null) {
			nameClass = ClassDBUtil.getCurtainShopById(nameId);
		}
		return nameClass;
	}

	public void setNameClass(CurtainShop nameClass) {
		this.nameClass = nameClass;
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

	public List getGoodsLst() {
		if (goodsLst == null) {
			goodsLst = (List) DBUtil.getLstClass("", "eq", OrderGoods.class,
					"orderId", id + "", "int");
		}
		return goodsLst;
	}

	public void setGoodsLst(List goodsLst) {
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
