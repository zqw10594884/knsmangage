package com.zqw.bean;

public class CurtainShopGoods {
	private int id;
	private String serialNumber;
	private double sellingPrice;
	private double number;
	private String curtainShop;
	private String remarks;



	public CurtainShopGoods() {
		super();
	}



	public CurtainShopGoods(String serialNumber, double sellingPrice,
			 String curtainShop) {
		super();
		this.serialNumber = serialNumber;
		this.sellingPrice = sellingPrice;
		this.curtainShop = curtainShop;
	}

	
	
	public CurtainShopGoods(int id, String serialNumber, double sellingPrice,
			double number, String curtainShop, String remarks) {
		super();
		this.id = id;
		this.serialNumber = serialNumber;
		this.sellingPrice = sellingPrice;
		this.number = number;
		this.curtainShop = curtainShop;
		this.remarks = remarks;
	}



	public CurtainShopGoods(int id, String serialNumber, double sellingPrice,
			String curtainShop, String remarks) {
		super();
		this.id = id;
		this.serialNumber = serialNumber;
		this.sellingPrice = sellingPrice;
		this.curtainShop = curtainShop;
		this.remarks = remarks;
	}

	public String getRemarks() {
		return remarks;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}

	
	public double getNumber() {
		return number;
	}

	public void setNumber(double number) {
		this.number = number;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getSerialNumber() {
		return serialNumber;
	}

	public void setSerialNumber(String serialNumber) {
		this.serialNumber = serialNumber;
	}

	public double getSellingPrice() {
		return sellingPrice;
	}

	public void setSellingPrice(double sellingPrice) {
		this.sellingPrice = sellingPrice;
	}

	public String getCurtainShop() {
		return curtainShop;
	}

	public void setCurtainShop(String curtainShop) {
		this.curtainShop = curtainShop;
	}

}
