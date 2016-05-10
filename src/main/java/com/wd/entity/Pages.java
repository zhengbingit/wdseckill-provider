package com.wd.entity;

import java.io.Serializable;
import java.util.List;

public class Pages implements Serializable{

	private static final long serialVersionUID = -8685779549550585331L;

	private int pages;

	private List<?> list;

	public int getPages() {
		return pages;
	}

	public void setPages(int pages) {
		this.pages = pages;
	}

	public List<?> getList() {
		return list;
	}

	public void setList(List<?> list) {
		this.list = list;
	}

	@Override
	public String toString() {
		return "Pages [pages=" + pages + ", list=" + list + "]";
	}

	public Pages(int pages, List<?> list) {
		super();
		this.pages = pages;
		this.list = list;
	}

	public Pages() {
		super();
		// TODO Auto-generated constructor stub
	}

}
