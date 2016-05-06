package com.wd.entity;

import java.io.Serializable;
import java.util.Date;

public class Orders implements Serializable{
	
	private static final long serialVersionUID = 7682986141400387777L;
	
	private int o_id;
	private int u_id;
	private int i_id;
	private int o_count;
	private Date o_time;
	private Item item;
	
	public int getO_id() {
		return o_id;
	}
	public void setO_id(int o_id) {
		this.o_id = o_id;
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
	public int getO_count() {
		return o_count;
	}
	public void setO_count(int o_count) {
		this.o_count = o_count;
	}
	public Date getO_time() {
		return o_time;
	}
	public void setO_time(Date o_time) {
		this.o_time = o_time;
	}
	public Item getItem() {
		return item;
	}
	public void setItem(Item item) {
		this.item = item;
	}
	@Override
	public String toString() {
		return "Orders [o_id=" + o_id + ", u_id=" + u_id + ", i_id=" + i_id + ", o_count=" + o_count + ", o_time="
				+ o_time + ", item=" + item + "]";
	}
	public Orders(int o_id, int u_id, int i_id, int o_count, Date o_time, Item item) {
		super();
		this.o_id = o_id;
		this.u_id = u_id;
		this.i_id = i_id;
		this.o_count = o_count;
		this.o_time = o_time;
		this.item = item;
	}
	public Orders() {
		super();
		// TODO Auto-generated constructor stub
	}
	
}
