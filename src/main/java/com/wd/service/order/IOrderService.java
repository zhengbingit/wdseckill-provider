package com.wd.service.order;

import com.wd.entity.Orders;
import com.wd.entity.Pages;
/**
 * 订单Service
 * @author zhengbinMac
 *
 */
public interface IOrderService {
	
	/**
	 * 增加订单条目
	 * @param orders
	 * @return boolean
	 */
	public boolean addOrdersService(Orders orders);
	
	/**
	 * 根据用户id获得该用户的所有订单信息
	 * @param orders
	 * @return Orders
	 */
	public Pages listOrdersService(int pageNum, int pageSize, int u_id);
	
}
