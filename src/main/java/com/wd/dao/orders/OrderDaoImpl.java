package com.wd.dao.orders;

import java.util.ArrayList;
import java.util.List;

import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.support.SqlSessionDaoSupport;

import com.wd.entity.Orders;

public class OrderDaoImpl extends SqlSessionDaoSupport implements IOrderDao {

	public void setSqlSessionFactory(SqlSessionFactory sqlSessionFactory) {
		System.out.println("载入SqlSessionFactory->"+this.getClass().getName());
		super.setSqlSessionFactory(sqlSessionFactory);
	}
	
	public boolean addOrders(Orders orders) {
		try{
			super.getSqlSession().insert("com.wd.dao.orders.mapper.addOrder", orders);
			return true;
		}catch(Exception e){
			return false;
		}
	}

	public List<Orders> listOrders(int u_id) {
		try{
			List<Orders> list = new ArrayList<Orders>();
			list = super.getSqlSession().selectList("com.wd.dao.orders.mapper.getUserOrders", u_id);
			return list;
		}catch(Exception e){
			System.out.println(e);
			return null;
		}
	}

}
