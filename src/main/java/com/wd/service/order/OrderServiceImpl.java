package com.wd.service.order;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.wd.dao.orders.IOrderDao;
import com.wd.entity.Orders;
import com.wd.entity.Pages;

public class OrderServiceImpl implements IOrderService {
	private IOrderDao orderDao;
	public IOrderDao getOrderDao() {
		return orderDao;
	}
	public void setOrderDao(IOrderDao orderDao) {
		this.orderDao = orderDao;
	}

	public boolean addOrdersService(Orders orders) {
		return orderDao.addOrders(orders) == true ? true : false;
	}

	public Pages listOrdersService(int pageNum, int pageSize, int u_id) {
		Page<?> page = PageHelper.startPage(pageNum, pageSize);
		Pages pages = new Pages();
		pages.setList(orderDao.listOrders(u_id));
		pages.setPages(page.getPages());
		return pages;
	}

}
