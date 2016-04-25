package com.wd.service.cart;

import java.util.List;

import com.wd.dao.cart.ICartDao;
import com.wd.entity.Cart;

public class CartServiceImpl implements ICartService {
	private ICartDao cartDao;
	public ICartDao getCartDao() {
		return cartDao;
	}
	public void setCartDao(ICartDao cartDao) {
		this.cartDao = cartDao;
	}

	public boolean addCart(Cart cart) {
		if(cartDao.addCart(cart)) {
			return true;
		}else {
			return false;
		}
	}

	public List<Cart> listCart(int u_id) {
		return cartDao.listCart(u_id);
	}

}
