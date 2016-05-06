package com.wd.entity;

import java.io.Serializable;
import java.util.Date;

public class Cart implements Serializable{
	
	private static final long serialVersionUID = -7822141839538708126L;
	
	private int c_id;
	private int u_id;
	private int i_id;
	private int c_count;
	private Date c_time;
	private Item item;
	
	public int getC_id() {
		return c_id;
	}
	public void setC_id(int c_id) {
		this.c_id = c_id;
	}
	public int getU_id() {
		return u_id;
	}
	public void setU_id(int u_id) {
		this.u_id = u_id;
	}
	public int getI_id() {
		return i_id;
	}
	public void setI_id(int i_id) {
		this.i_id = i_id;
	}
	public int getC_count() {
		return c_count;
	}
	public void setC_count(int c_count) {
		this.c_count = c_count;
	}
	public Cart() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Date getC_time() {
		return c_time;
	}
	public void setC_time(Date c_time) {
		this.c_time = c_time;
	}
	public Item getItem() {
		return item;
	}
	public void setItem(Item item) {
		this.item = item;
	}
	public Cart(int c_id, int u_id, int i_id, int c_count, Date c_time, Item item) {
		super();
		this.c_id = c_id;
		this.u_id = u_id;
		this.i_id = i_id;
		this.c_count = c_count;
		this.c_time = c_time;
		this.item = item;
	}
	@Override
	public String toString() {
		return "Cart [c_id=" + c_id + ", u_id=" + u_id + ", i_id=" + i_id + ", c_count=" + c_count + ", c_time="
				+ c_time + ", item=" + item + "]";
	}
	
}
