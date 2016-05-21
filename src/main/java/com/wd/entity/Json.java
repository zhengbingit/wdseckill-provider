package com.wd.entity;

import java.io.Serializable;

public class Json implements Serializable{

	private static final long serialVersionUID = 5253449284562328374L;
	
	private int result_code;
	private int buyer_id;
	private int item_id;
	private double total_price;
	public int getResult_code() {
		return result_code;
	}
	public void setResult_code(int result_code) {
		this.result_code = result_code;
	}
	public int getBuyer_id() {
		return buyer_id;
	}
	public void setBuyer_id(int buyer_id) {
		this.buyer_id = buyer_id;
	}
	public int getItem_id() {
		return item_id;
	}
	public void setItem_id(int item_id) {
		this.item_id = item_id;
	}
	public double getTotal_price() {
		return total_price;
	}
	public void setTotal_price(double total_price) {
		this.total_price = total_price;
	}
	@Override
	public String toString() {
		return "Json [result_code=" + result_code + ", buyer_id=" + buyer_id + ", item_id=" + item_id + ", total_price="
				+ total_price + "]";
	}
	public Json() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Json(int result_code, int buyer_id, int item_id, double total_price) {
		super();
		this.result_code = result_code;
		this.buyer_id = buyer_id;
		this.item_id = item_id;
		this.total_price = total_price;
	}
	
}
