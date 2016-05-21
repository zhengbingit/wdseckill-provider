package com.wd.service.pay;

import java.util.Date;

import com.wd.dao.cart.ICartDao;
import com.wd.dao.items.IItemDao;
import com.wd.dao.orders.IOrderDao;
import com.wd.dao.user.IUserDao;
import com.wd.entity.Cart;
import com.wd.entity.Item;
import com.wd.entity.Json;
import com.wd.entity.Orders;

public class PayServiceImpl implements IPayService {
	private IUserDao userDao;
	private IItemDao itemDao;
	private ICartDao cartDao;
	private IOrderDao orderDao;
	public IUserDao getUserDao() {
		return userDao;
	}
	public void setUserDao(IUserDao userDao) {
		this.userDao = userDao;
	}
	public IItemDao getItemDao() {
		return itemDao;
	}
	public void setItemDao(IItemDao itemDao) {
		this.itemDao = itemDao;
	}
	public ICartDao getCartDao() {
		return cartDao;
	}
	public void setCartDao(ICartDao cartDao) {
		this.cartDao = cartDao;
	}
	public IOrderDao getOrderDao() {
		return orderDao;
	}
	public void setOrderDao(IOrderDao orderDao) {
		this.orderDao = orderDao;
	}


	public Json payOneService(int buyer_id, int item_id, int quantity) {
		Json result = new Json();
		result.setBuyer_id(buyer_id);
		result.setItem_id(item_id);
		Item item = itemDao.getItem(item_id);
		//判断库存
		if(item.getI_stock() < quantity) {
			//库存不足
			System.out.println("库存：" + item.getI_stock());
			result.setResult_code(10);
			return result;
		}else {
			itemDao.editItemSales(item.getI_id(), quantity);
			itemDao.editItemStock(item.getI_id(), quantity);
		}
		//判断是否开抢
		if(item.getI_iskill() == 1){
			if(!item.getI_killtime().before(new Date())) {
				//商品销售时间未到
				result.setResult_code(11);
				return result;
			}
		}
		//更新余额
		//总价
		double i_money = item.getI_price() * quantity + item.getI_postage();
		result.setTotal_price(i_money);
		//判断用户余额
		double u_money = userDao.getMoney(buyer_id);
		if(u_money < i_money) {
			result.setResult_code(1);
			return result;
		}
		//增加我的订单
		Orders order = new Orders();
		order.setI_id(item_id);
		order.setO_count(quantity);
		order.setO_time(new Date());
		order.setU_id(buyer_id);
		if(orderDao.addOrders(order) && userDao.updateMoney(buyer_id, i_money)) {
			result.setResult_code(0);
			return result;
		}else {
			result.setResult_code(1);
			return result;
		}
	}


	public int payListService(int[] c_id, int u_id) {
		int result_int = 0;
		for (int i = 0; i < c_id.length; i++) {
			Cart cart = cartDao.getCart(c_id[i]);
			System.out.println(cart);
			Item item = cart.getItem();
			//判断库存
			if(item.getI_stock() < item.getI_stock()) {
				//库存不足
				result_int = 10;
				break;
			}else {
				itemDao.editItemSales(cart.getItem().getI_id(), cart.getC_count());
				itemDao.editItemStock(cart.getItem().getI_id(), cart.getC_count());
			}
			//判断是否开抢
			if(item.getI_iskill() == 1){
				if(!item.getI_killtime().before(new Date())) {
					//商品销售时间未到
					result_int = 11;
					break;
				}
			}
			//更新余额
			//总价
			double i_money = item.getI_price() * cart.getC_count() + item.getI_postage();
			//判断用户余额
			double u_money = userDao.getMoney(u_id);
			if(u_money < i_money) {
				result_int = 1;
				break;
			}
			//增加我的订单
			Orders order = new Orders();
			order.setI_id(cart.getItem().getI_id());
			order.setU_id(u_id);
			order.setO_count(cart.getC_count());
			order.setO_time(new Date());
			System.out.println(order);
			if(orderDao.addOrders(order) && userDao.updateMoney(u_id, i_money) && cartDao.deleteCart(c_id[i])) {
				result_int = 0;
			}else {
				result_int = 1;
				break;
			}
		}
		return result_int;
	}

}
