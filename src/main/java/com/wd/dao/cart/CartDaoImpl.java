package com.wd.dao.cart;

import java.util.List;

import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.support.SqlSessionDaoSupport;

import com.wd.entity.Cart;

public class CartDaoImpl extends SqlSessionDaoSupport implements ICartDao {
	
	public void setSqlSessionFactory(SqlSessionFactory sqlSessionFactory) {
		System.out.println("载入SqlSessionFactory->"+this.getClass().getName());
		super.setSqlSessionFactory(sqlSessionFactory);
	}
	
	public boolean addCart(Cart cart) {
		try{
			super.getSqlSession().insert("com.wd.dao.cart.mapper.addCart", cart);
			return true;
		}catch(Exception e){
			return false;
		}
	}

	public boolean deleteCart(int c_id) {
		try{
			super.getSqlSession().update("com.wd.dao.cart.mapper.deleteCart", c_id);
			return true;
		}catch(Exception e){
			System.out.println(e);
			return false;
		}
	}

	public List<Cart> listCart(int u_id) {
		try{
			List<Cart> list_cart = super.getSqlSession().selectList("com.wd.dao.cart.mapper.getCart", u_id);
			return list_cart;
		}catch(Exception e){
			return null;
		}
	}

	public Cart getCart(int c_id) {
		return super.getSqlSession().selectOne("com.wd.dao.cart.mapper.getOneCart",c_id);
	}

	public int getRow(int u_id) {
		return super.getSqlSession().selectOne("com.wd.dao.cart.mapper.getRow", u_id);
	}

}
