package com.wd.service.order;

import java.util.List;

import com.wd.dao.orders.IOrderDao;
import com.wd.entity.Orders;

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

	public List<Orders> listOrdersService(int u_id) {
		return orderDao.listOrders(u_id);
	}

}
