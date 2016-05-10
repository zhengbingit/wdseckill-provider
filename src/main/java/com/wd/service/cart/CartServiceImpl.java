package com.wd.service.cart;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.wd.dao.cart.ICartDao;
import com.wd.entity.Cart;
import com.wd.entity.Pages;

public class CartServiceImpl implements ICartService {
	private ICartDao cartDao;
	public ICartDao getCartDao() {
		return cartDao;
	}
	public void setCartDao(ICartDao cartDao) {
		this.cartDao = cartDao;
	}

	public boolean addCart(Cart cart) {
		return cartDao.addCart(cart) == true ? true : false;
	}

	public Pages listCart(int pageNum, int pageSize, int u_id) {
		Page<?> page = PageHelper.startPage(pageNum, pageSize);
		Pages pages = new Pages();
		pages.setList(cartDao.listCart(u_id));
		pages.setPages(page.getPages());
		return pages;
	}

	public Cart getCart(int c_id) {
		return cartDao.getCart(c_id);
	}

	public boolean deleteCart(int c_id) {
		return cartDao.deleteCart(c_id);
	}

	public int getRow(int u_id) {
		return cartDao.getRow(u_id);
	}

}
