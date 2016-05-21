package com.wd.service.pay;

import com.wd.entity.Json;

public interface IPayService {
	
	
	/**
	 * 下单测试，仅针对单个商品下单
	 *	操作：
		 *	减库存
		 *	增销量
		 *	减余额
		 *	增订单
	 *	判断：
		 *	判库存
		 *	判余额
		 *	判是否开抢
	 * @param buyer_id
	 * @param item_id
	 * @param quantity
	 * @return
	 */
	public Json payOneService(int buyer_id, int item_id, int quantity);
	
	/**
	 * 通过购物车购买
	 * @return
	 */
	public int payListService(int[] c_id, int u_id);
}
